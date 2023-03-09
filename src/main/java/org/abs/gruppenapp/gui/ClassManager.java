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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.entities.Course;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClassManager extends JFrame {

  private final DatabaseService databaseService;

  public void initialize(){
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(500, 500);
    setLayout(new BorderLayout());

    var loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    var mainPanel = new JPanel(new BorderLayout());
    var backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    var menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    var checkboxPanel = new JPanel(new GridLayout(1,2));

    JButton logoutBtn = new JButton("Ausloggen");
    JButton backToDashboardBtn = new JButton("Zurück zur Übersicht");
    JButton addClassBtn = new JButton("Klasse erstellen");
    JButton deleteClassBtn = new JButton("Klasse löschen");
    JButton addLfBtn = new JButton("Lernfeld/Unterricht erstellen");
    JButton deleteLfBtn = new JButton("Lernfeld/Unterricht löschen");
    deleteClassBtn.setEnabled(false);
    addLfBtn.setEnabled(false);
    deleteLfBtn.setEnabled(false);

    var classSelection = getClasses();

    checkboxPanel.add(classSelection);

    loginPanel.add(logoutBtn);
    backPanel.add(backToDashboardBtn);
    menuPanel.add(addClassBtn);
    menuPanel.add(deleteClassBtn);
    menuPanel.add(addLfBtn);
    menuPanel.add(deleteLfBtn);
    mainPanel.add(menuPanel, BorderLayout.NORTH);
    mainPanel.add(checkboxPanel, BorderLayout.CENTER);

    add(loginPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    add(backPanel, BorderLayout.SOUTH);

    setVisible(true);

    classSelection.addActionListener(a -> {
      checkboxPanel.removeAll();
      checkboxPanel.revalidate();
      checkboxPanel.repaint();

      mainPanel.remove(checkboxPanel);
      mainPanel.revalidate();
      mainPanel.repaint();

      var courseSelected = (String) classSelection.getSelectedItem();
      var lfTable = getLFTable(courseSelected);

      checkboxPanel.add(classSelection);
      checkboxPanel.add(lfTable);
      checkboxPanel.revalidate();
      checkboxPanel.repaint();
      mainPanel.add(checkboxPanel, BorderLayout.CENTER);
      mainPanel.revalidate();
      mainPanel.repaint();
    });

    addClassBtn.addActionListener(a -> {
      var frame = createClassForm();
      frame.setVisible(true);
    });

    logoutBtn.addActionListener(a -> logout());
    backToDashboardBtn.addActionListener(a -> backToAdminDashboard());
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

  private JComboBox<String> getClasses() {
    List<String> courseNames = databaseService.getAllCourses();
    return createComboBox(courseNames);
  }

  private JTable getLFTable(String course){
    JTable table = new JTable();
    table.setFillsViewportHeight(true);
    String[] columnNames = {"Lernfeld/Unterricht"};
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(columnNames);
    JTableHeader header = table.getTableHeader();
    header.setBackground(Color.yellow);
    table.setGridColor(Color.GRAY);
    table.setModel(model);
    addDataIntoTable(model);

    return table;
  }

  private void addDataIntoTable(DefaultTableModel model) {
    Object[] row = new Object[3];
    List<String> learningFields = databaseService.getAllLearningFields();

    for (String lf : learningFields) {
      row[0] = lf;
      model.addRow(row);
    }
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

  private JFrame createClassForm() {
    JFrame frame = new JFrame();

    frame.setTitle("GroupMaker 8");
    frame.setSize(300, 250);
    frame.setLayout(new BorderLayout());

    JPanel panel = new JPanel(new GridLayout(2, 1));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new GridLayout(2, 1));
    panelForBtn.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel classLabel = new JLabel("Klassennummer");
    JTextField classTextField = new JTextField();
    JButton confirmBtn = new JButton("Speichern");
    confirmBtn.setSize(20, 10);

    panel.add(classLabel);
    panel.add(classTextField);

    panelForBtn.add(confirmBtn);

    frame.add(panel, BorderLayout.CENTER);
    frame.add(panelForBtn, BorderLayout.SOUTH);

    confirmBtn.addActionListener(a -> {
      var classname = classTextField.getText();

      Course course = new Course();
      course.setName(classname);

      databaseService.saveCourse(course);
      frame.setVisible(false);
      reloadFrame();
    });

    return frame;
  }

  public void reloadFrame() {
    ClassManager classManager = new ClassManager(databaseService);
    classManager.setVisible(true);
    classManager.initialize();
    setVisible(false);
  }

}
