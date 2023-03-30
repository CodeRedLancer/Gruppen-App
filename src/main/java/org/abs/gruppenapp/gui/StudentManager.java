package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentManager extends JFrame {

  private final DatabaseService databaseService;

  public void initialize(){
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(500, 500);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);

    var loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    var mainPanel = new JPanel(new BorderLayout());
    var backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    var menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    var checkboxPanel = new JPanel(new GridLayout(1,2));

    JButton logoutBtn = new JButton("Ausloggen");
    JButton backToDashboardBtn = new JButton("Zurück zur Übersicht");
    JButton addStudentBtn = new JButton("Schüler hinzufügen");
    JButton editStudentBtn = new JButton("Schüler ändern");
    JButton deleteStudentBtn = new JButton("Schüler löschen");
    editStudentBtn.setEnabled(false);
    deleteStudentBtn.setEnabled(false);

    var classSelection = getClasses();
    checkboxPanel.add(classSelection);

    JTable table = new JTable();
    table.setFillsViewportHeight(true);
    String[] columnNames = {"ID", "Nachname", "Vorname", "Klasse", "Fachrichtung", "Note"};
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columnNames);
    JTableHeader header = table.getTableHeader();
    header.setBackground(Color.yellow);
    table.setGridColor(Color.GRAY);
    table.setModel(model);
    JScrollPane scrollPane = new JScrollPane(table);

    loginPanel.add(logoutBtn);
    backPanel.add(backToDashboardBtn);
    menuPanel.add(checkboxPanel);
    menuPanel.add(addStudentBtn);
    menuPanel.add(editStudentBtn);
    menuPanel.add(deleteStudentBtn);
    mainPanel.add(menuPanel, BorderLayout.NORTH);
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    add(loginPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(backPanel, BorderLayout.SOUTH);

    addDataIntoTable(model, classSelection.getSelectedItem().toString());

    table.getSelectionModel().addListSelectionListener(e -> {
      menuPanel.removeAll();
      editStudentBtn.setEnabled(true);
      deleteStudentBtn.setEnabled(true);
      menuPanel.add(addStudentBtn);
      menuPanel.add(editStudentBtn);
      menuPanel.add(deleteStudentBtn);

      mainPanel.remove(menuPanel);
      mainPanel.add(menuPanel, BorderLayout.NORTH);

      menuPanel.revalidate();
      menuPanel.repaint();
      mainPanel.revalidate();
      mainPanel.repaint();
    });

    addStudentBtn.addActionListener(a -> {
      var frame = createStudentForm();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });

    editStudentBtn.addActionListener(a -> {
      int rowNum = table.getSelectedRow();
      var studentId = table.getValueAt(rowNum, 0).toString();

      var student = databaseService.findStudentByID(Integer.parseInt(studentId));

      var frame = createStudentForm(student.get());
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });

    deleteStudentBtn.addActionListener(a -> {
      int rowNum = table.getSelectedRow();
      var value = table.getValueAt(rowNum, 0).toString();
      model.removeRow(rowNum);
      databaseService.removeStudent(Integer.parseInt(value));
      reloadFrame();
    });

    setVisible(true);

    logoutBtn.addActionListener(a -> logout());
    backToDashboardBtn.addActionListener(a -> backToAdminDashboard());
  }

  private JComboBox<String> getClasses() {
    List<String> courseNames = databaseService.getAllCourses();
    return createComboBox(courseNames);
  }

  private JComboBox<String> createComboBox(List<String> items) {
    JComboBox<String> comboBox = new JComboBox<>();
    if (items.isEmpty()) {
      comboBox.addItem("data not found");
    } else {
      comboBox.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
      comboBox.setSelectedIndex(0);
    }
    return comboBox;
  }

  public void logout() {
    Login login = new Login(databaseService);
    login.setVisible(true);
    login.initialize();
    setVisible(false);
  }

  public void backToAdminDashboard() {
    DashboardAdmin dashboardAdmin = new DashboardAdmin(databaseService);
    dashboardAdmin.setVisible(true);
    dashboardAdmin.initialize();
    setVisible(false);
  }

  private void addDataIntoTable(DefaultTableModel model, String course) {
    Object[] row = new Object[6];
    List<Student> students = databaseService.getStudentsByCourseName(course);

    for (Student student : students) {
      row[0] = student.getStudentId();
      row[1] = student.getLastName();
      row[2] = student.getFirstName();
      row[3] = student.getCourse().getName();
      row[4] = student.getSubject().getName();
      row[5] = student.getEvaluation();
      model.addRow(row);
    }
  }

  private JFrame createStudentForm(){
    JFrame frame = new JFrame();

    frame.setTitle("GroupMaker 8");
    frame.setSize(550, 250);
    frame.setLayout(new BorderLayout());
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(3, 1));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new GridLayout(2, 1));
    panelForBtn.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel lastnameLabel = new JLabel("Nachname");
    JTextField lastnameTextField = new JTextField();
    JLabel firstnameLabel = new JLabel("Vorname");
    JTextField firstnameTextField = new JTextField();
    JLabel classLabel = new JLabel("Klasse");
    JComboBox<String> classBox = new JComboBox<>();
    JLabel subjectLabel = new JLabel("Fachrichtung");
    JComboBox<String> subjectBox = new JComboBox<>();
    JLabel leistungLabel = new JLabel("Note");
    JTextField leistungTextField = new JTextField();
    JButton confirmBtn = new JButton("Speichern");
    confirmBtn.setSize(20, 10);

    panel.add(lastnameLabel);
    panel.add(lastnameTextField);
    panel.add(firstnameLabel);
    panel.add(firstnameTextField);
    panel.add(classLabel);
    panel.add(classBox);
    panel.add(subjectLabel);
    panel.add(subjectBox);
    panel.add(leistungLabel);
    panel.add(leistungTextField);

    panelForBtn.add(confirmBtn);

    frame.add(panel, BorderLayout.CENTER);
    frame.add(panelForBtn, BorderLayout.SOUTH);

    confirmBtn.addActionListener(a -> {
      var lastname = lastnameTextField.getText();
      var firstname = firstnameTextField.getText();
      var course = classBox.getSelectedItem();
      var subject = subjectBox.getSelectedItem();
      var evaluation = leistungTextField.getText();

      Student student = new Student();
      student.setLastName(lastname);
      student.setFirstName(firstname);
//      student.setCourse(course);
//      student.setSubject(subject);
      student.setEvaluation(Integer.parseInt(evaluation));

      databaseService.saveStudent(student);
      frame.setLocationRelativeTo(null);
      frame.setVisible(false);
      reloadFrame();
    });

    return frame;
  }

  private JFrame createStudentForm(Student foundStudent){
    JFrame frame = new JFrame();

    frame.setTitle("GroupMaker 8");
    frame.setSize(550, 250);
    frame.setLayout(new BorderLayout());
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(3, 1));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new GridLayout(2, 1));
    panelForBtn.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel lastnameLabel = new JLabel("Nachname");
    JTextField lastnameTextField = new JTextField();
    JLabel firstnameLabel = new JLabel("Vorname");
    JTextField firstnameTextField = new JTextField();
    JLabel classLabel = new JLabel("Klasse");
    JComboBox<String> classBox = new JComboBox<>();
    JLabel subjectLabel = new JLabel("Fachrichtung");
    JComboBox<String> subjectBox = new JComboBox<>();
    JLabel leistungLabel = new JLabel("Note");
    JTextField leistungTextField = new JTextField();
    JButton confirmBtn = new JButton("Speichern");
    confirmBtn.setSize(20, 10);

    panel.add(lastnameLabel);
    panel.add(lastnameTextField);
    panel.add(firstnameLabel);
    panel.add(firstnameTextField);
    panel.add(classLabel);
    panel.add(classBox);
    panel.add(subjectLabel);
    panel.add(subjectBox);
    panel.add(leistungLabel);
    panel.add(leistungTextField);

    panelForBtn.add(confirmBtn);

    frame.add(panel, BorderLayout.CENTER);
    frame.add(panelForBtn, BorderLayout.SOUTH);

    confirmBtn.addActionListener(a -> {
      var lastname = lastnameTextField.getText();
      var firstname = firstnameTextField.getText();
      var course = classBox.getSelectedItem();
      var subject = subjectBox.getSelectedItem();
      var evaluation = leistungTextField.getText();

      Student student = new Student();
      student.setStudentId(foundStudent.getStudentId());
      student.setLastName(lastname);
      student.setFirstName(firstname);
//      student.setCourse(course);
//      student.setSubject(subject);
      student.setEvaluation(Integer.parseInt(evaluation));

      databaseService.saveStudent(student);
      frame.setLocationRelativeTo(null);
      frame.setVisible(false);
      reloadFrame();
    });

    return frame;
  }

  public void reloadFrame() {
    StudentManager studentManager = new StudentManager(databaseService);
    studentManager.setVisible(true);
    studentManager.initialize();
    setVisible(false);
  }

}
