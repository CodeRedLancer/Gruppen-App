package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.entities.Teacher;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherManager extends JFrame {

  private final DatabaseService databaseService;


  public void initialize() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(500, 500);
    setLayout(new BorderLayout());

    var loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    var mainPanel = new JPanel(new BorderLayout());
    var backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    var menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JButton logoutBtn = new JButton("Ausloggen");
    JButton backToDashboardBtn = new JButton("Zurück zur Übersicht");
    JButton addTeacherBtn = new JButton("Lehrer hinzufügen");
    JButton editTeacherBtn = new JButton("Lehrer ändern");
    JButton deleteTeacherBtn = new JButton("Lehrer löschen");
    editTeacherBtn.setEnabled(false);
    deleteTeacherBtn.setEnabled(false);

    JTable table = new JTable();
    table.setFillsViewportHeight(true);
    String[] columnNames = {"ID", "Nachname", "Vorname"};
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columnNames);
    JTableHeader header = table.getTableHeader();
    header.setBackground(Color.yellow);
    table.setGridColor(Color.GRAY);
    table.setModel(model);
    JScrollPane scrollPane = new JScrollPane(table);

    loginPanel.add(logoutBtn);
    backPanel.add(backToDashboardBtn);
    menuPanel.add(addTeacherBtn);
    menuPanel.add(editTeacherBtn);
    menuPanel.add(deleteTeacherBtn);
    mainPanel.add(menuPanel, BorderLayout.NORTH);
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    add(loginPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(backPanel, BorderLayout.SOUTH);

    addDataIntoTable(model);

    table.getSelectionModel().addListSelectionListener(e -> {
      menuPanel.removeAll();
      editTeacherBtn.setEnabled(true);
      deleteTeacherBtn.setEnabled(true);
      menuPanel.add(addTeacherBtn);
      menuPanel.add(editTeacherBtn);
      menuPanel.add(deleteTeacherBtn);

      mainPanel.remove(menuPanel);
      mainPanel.add(menuPanel, BorderLayout.NORTH);

      menuPanel.revalidate();
      menuPanel.repaint();
      mainPanel.revalidate();
      mainPanel.repaint();
    });

    addTeacherBtn.addActionListener(a -> {
      var frame = createTeacherForm();
      frame.setVisible(true);
    });

    editTeacherBtn.addActionListener(a -> {
      int rowNum = table.getSelectedRow();
      var teacherId = table.getValueAt(rowNum, 0).toString();

      var teacher = databaseService.findTeacherByID(Integer.parseInt(teacherId));


      var frame = createTeacherForm(teacher.get());
      frame.setVisible(true);
    });

    deleteTeacherBtn.addActionListener(a -> {
          int rowNum = table.getSelectedRow();
          var value = table.getValueAt(rowNum, 0).toString();
          model.removeRow(rowNum);
          databaseService.removeTeacher(Integer.parseInt(value));
          model.fireTableDataChanged();
        }
    );

    setVisible(true);

    logoutBtn.addActionListener(a -> logout());
    backToDashboardBtn.addActionListener(a -> backToAdminDashboard());
  }

  private void addDataIntoTable(DefaultTableModel model) {
    Object[] row = new Object[3];
    List<Teacher> teachers = databaseService.getAllTeachers();

    for (Teacher teacher : teachers) {
      row[0] = teacher.getTeacherId();
      row[1] = teacher.getLastName();
      row[2] = teacher.getFirstName();
      model.addRow(row);
    }
  }

  public void logout() {
    Login login = new Login(databaseService);
    login.setVisible(true);
    login.initialize();
    setVisible(false);
  }

  public void reloadFrame(){
    TeacherManager teacherManager = new TeacherManager(databaseService);
    teacherManager.setVisible(true);
    teacherManager.initialize();
    setVisible(false);
  }

  public void backToAdminDashboard() {
    DashboardAdmin dashboardAdmin = new DashboardAdmin(databaseService);
    dashboardAdmin.setVisible(true);
    dashboardAdmin.initialize();
    setVisible(false);
  }

  private JFrame createTeacherForm() {
    JFrame frame = new JFrame();

    frame.setTitle("GroupMaker 8");
    frame.setSize(500, 250);
    frame.setLayout(new BorderLayout());

    JPanel panel = new JPanel(new GridLayout(3, 1));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new GridLayout(2, 1));
    panelForBtn.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel lastnameLabel = new JLabel("Nachname");
    JTextField lastnameTextField = new JTextField();
    JLabel firstnameLabel = new JLabel("Vorname");
    JTextField firstnameTextField = new JTextField();
    JButton confirmBtn = new JButton("Speichern");
    confirmBtn.setSize(20, 10);

    panel.add(lastnameLabel);
    panel.add(lastnameTextField);
    panel.add(firstnameLabel);
    panel.add(firstnameTextField);

    panelForBtn.add(confirmBtn);

    frame.add(panel, BorderLayout.CENTER);
    frame.add(panelForBtn, BorderLayout.SOUTH);

    confirmBtn.addActionListener(a -> {
      var lastname = lastnameTextField.getText();
      var firstname = firstnameTextField.getText();

      Teacher teacher = new Teacher();
      teacher.setLastName(lastname);
      teacher.setFirstName(firstname);

      databaseService.saveTeacher(teacher);
      frame.setVisible(false);
      reloadFrame();
    });

    return frame;
  }

  private JFrame createTeacherForm(Teacher foundTeacher) {
    JFrame frame = new JFrame();

    frame.setTitle("GroupMaker 8");
    frame.setSize(500, 250);
    frame.setLayout(new BorderLayout());

    JPanel panel = new JPanel(new GridLayout(3, 1));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new GridLayout(2, 1));
    panelForBtn.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel lastnameLabel = new JLabel("Nachname");
    JTextField lastnameTextField = new JTextField(foundTeacher.getLastName());
    JLabel firstnameLabel = new JLabel("Vorname");
    JTextField firstnameTextField = new JTextField(foundTeacher.getFirstName());
    JButton confirmBtn = new JButton("Speichern");
    confirmBtn.setSize(20, 10);

    panel.add(lastnameLabel);
    panel.add(lastnameTextField);
    panel.add(firstnameLabel);
    panel.add(firstnameTextField);

    panelForBtn.add(confirmBtn);

    frame.add(panel, BorderLayout.CENTER);
    frame.add(panelForBtn, BorderLayout.SOUTH);

    confirmBtn.addActionListener(a -> {
      var lastname = lastnameTextField.getText();
      var firstname = firstnameTextField.getText();

      Teacher teacher = new Teacher();
      teacher.setTeacherId(foundTeacher.getTeacherId());
      teacher.setLastName(lastname);
      teacher.setFirstName(firstname);

      databaseService.saveTeacher(teacher);
      frame.setVisible(false);
      reloadFrame();
    });

    return frame;
  }
}
