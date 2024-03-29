package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.entities.StudentToGroup;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentView extends JFrame {

  private final DatabaseService databaseService;

  public void initialize() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(500, 500);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);

    var mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    var downPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    var courseSelection = getCourseSelection();
    mainPanel.add(courseSelection);

    var backBtn = new JButton("Zurück zur Übersicht");

    downPanel.add(backBtn);

    add(mainPanel, BorderLayout.NORTH);
    add(downPanel, BorderLayout.SOUTH);
    setVisible(true);

    backBtn.addActionListener(a -> backToTeacherDashboard());

    courseSelection.addActionListener(event -> {
      clearDownPanel(downPanel);
      var courseSelected = (String) courseSelection.getSelectedItem();
      var lfSelection = getLfSelection(courseSelected);

      downPanel.add(backBtn);
      updateMainPanel(mainPanel, courseSelection, lfSelection);

      lfSelection.addActionListener(eventLf -> {
        clearDownPanel(downPanel);
        downPanel.add(backBtn);
        var lfSelected = (String) lfSelection.getSelectedItem();
        List<String> subject = databaseService.getFachrichtungByLfName(lfSelected);

        if (subject.isEmpty()) {
          clearDownPanel(downPanel);
          var table = createTable(courseSelected, null);
          table.setFillsViewportHeight(true);
          JScrollPane scrollPane = new JScrollPane(table);

          updateMainPanel(mainPanel, courseSelection, lfSelection, scrollPane);
          updateDownPanel(downPanel, table);
        } else {
          clearDownPanel(downPanel);
          var fachrichtungSelection = getFachrichtungSelection(lfSelected);

          updateMainPanel(mainPanel, courseSelection, lfSelection, fachrichtungSelection);

          fachrichtungSelection.addActionListener(fachEvent -> {
            clearDownPanel(downPanel);
            var subjectSelected = (String) fachrichtungSelection.getSelectedItem();

            var table = createTable(courseSelected, subjectSelected);
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);

            updateMainPanel(mainPanel, courseSelection, lfSelection, fachrichtungSelection, scrollPane);
            updateDownPanel(downPanel, table);
          });
        }
      });
    });
  }


  private void updateMainPanel(JPanel mainPanel, java.awt.Component... elements) {
    mainPanel.removeAll();

    for (java.awt.Component o : elements) {
      mainPanel.add(o);
    }

    mainPanel.revalidate();
    mainPanel.repaint();
  }

  private void clearDownPanel(JPanel downPanel) {
    downPanel.removeAll();
    downPanel.revalidate();
    downPanel.repaint();
  }

  private void updateDownPanel(JPanel downPanel, JTable table) {
    downPanel.removeAll();

    var backBtn = new JButton("Zurück zur Übersicht");
    var gruppeErstellenBtn = new JButton("Gruppe erstellen");

    List<StudentToGroup> studentsToGroup = new ArrayList<>();

    gruppeErstellenBtn.addActionListener(e -> {
      for (int i = 0; i < table.getModel().getRowCount(); i++) {
        if (table.getValueAt(i, 3).equals(false)) {
          studentsToGroup.add(new StudentToGroup(
              table.getValueAt(i, 0).toString(),
              table.getValueAt(i, 1).toString(),
              Integer.parseInt(table.getValueAt(i, 2).toString())
          ));
        }
      }

      System.out.println("-------------------------------------------------------------------------------------------------------" + studentsToGroup.size());
      List<StudentToGroup> Test = SortStudents.sort("homogen", studentsToGroup);
      ArrayList<StudentToGroup[]> sortetListOutput = SortStudents.output("5", Test);

      var groupFrame = createGroupForm();
      groupFrame.setLocationRelativeTo(null);
      groupFrame.setVisible(true);
    });
    getStudentsList(studentsToGroup);

    backBtn.addActionListener(a -> backToTeacherDashboard());

    downPanel.add(backBtn);
    downPanel.add(gruppeErstellenBtn);
    downPanel.revalidate();
    downPanel.repaint();
  }

  private JFrame createGroupForm(){
    JFrame frame = new JFrame();

    frame.setTitle("GroupMaker 8");
    frame.setSize(600, 200);
    frame.setLayout(new BorderLayout());
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(2, 0));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new GridLayout(2, 1));
    panelForBtn.setBorder(new EmptyBorder(10, 10, 10, 10));

    var leistungCheckBox = new JCheckBox("nach Leistung");
    var gemischtCheckBox = new JCheckBox("gemischt");

    JButton confirmBtn = new JButton("Gruppen erstellen");
    confirmBtn.setSize(20, 10);

    panel.add(leistungCheckBox);
    panel.add(gemischtCheckBox);

    panelForBtn.add(confirmBtn);

    frame.add(panel, BorderLayout.CENTER);
    frame.add(panelForBtn, BorderLayout.SOUTH);

    return frame;
  }

  public void backToTeacherDashboard() {
    DashboardTeacher dashboardTeacher = new DashboardTeacher(databaseService);
    dashboardTeacher.setVisible(true);
    dashboardTeacher.initialize();
    setVisible(false);
  }

  private JComboBox<String> getCourseSelection() {
    List<String> courseNames = databaseService.getAllCourses();
    return createComboBox(courseNames);
  }

  private JComboBox<String> getLfSelection(String courseName) {
    var lfNames = databaseService.getLfByCourseName(courseName);
    return createComboBox(lfNames);
  }

  private JComboBox<String> getFachrichtungSelection(String lfName) {
    List<String> subjectNames = databaseService.getFachrichtungByLfName(lfName);
    return createComboBox(subjectNames);
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

  private JTable createTable(String courseName, String subjectName) {
    JTable table = new JTable() {
      @Override
      public Class<?> getColumnClass(int column) {
        return switch (column) {
          case 0, 1 -> String.class;
          case 2 -> Integer.class;
          default -> Boolean.class;
        };
      }
    };
    DefaultTableModel model = new DefaultTableModel();

    JTableHeader header = table.getTableHeader();
    header.setBackground(Color.yellow);
    table.setGridColor(Color.GRAY);

    String[] columnNames = {"Nachname", "Vorname", "Leistung", "Abwesend"};
    model.setColumnIdentifiers(columnNames);
    table.setModel(model);

    List<Student> students;

    if (subjectName == null) {
      students = databaseService.getStudentsByCourseName(courseName);
    } else {
      students = databaseService.getStudentsByCourseNameAndSubject(courseName, subjectName);
    }

    for (Student student : students) {
      Object[] o = new Object[4];
      o[0] = student.getLastName();
      o[1] = student.getFirstName();
      o[2] = student.getEvaluation();
      o[3] = false;
      model.addRow(o);
    }
    return table;
  }

  public List<StudentToGroup> getStudentsList(List<StudentToGroup> studentToGroups) {
    return new ArrayList<>(studentToGroups);
  }
}
