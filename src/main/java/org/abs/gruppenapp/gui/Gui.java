package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Gui extends JFrame {

  private final DatabaseService databaseService;


  public void initialize() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(500, 500);
    setLayout(new BorderLayout());

    var mainPanel = createPanel();

    var courseSelection = getCourseSelection();
    mainPanel.add(courseSelection);

    add(mainPanel, BorderLayout.NORTH);
    setVisible(true);

    courseSelection.addActionListener(event -> {
      var courseSelected = (String) courseSelection.getSelectedItem();
      var lfSelection = getLfSelection(courseSelected);

      updateMainPanel(mainPanel, courseSelection, lfSelection);

      lfSelection.addActionListener(eventLf -> {
        var lfSelected = (String) lfSelection.getSelectedItem();
        List<String> subject = databaseService.getFachrichtungByLfName(lfSelected);

        if (subject.isEmpty()) {
          var table = createTable(courseSelected, null);
          table.setFillsViewportHeight(true);
          JScrollPane scrollPane = new JScrollPane(table);

          updateMainPanel(mainPanel, courseSelection, lfSelection, scrollPane);

        } else {
          var fachrichtungSelection = getFachrichtungSelection(lfSelected);

          updateMainPanel(mainPanel, courseSelection, lfSelection, fachrichtungSelection);

          fachrichtungSelection.addActionListener(fachEvent -> {
            var subjectSelected = (String) fachrichtungSelection.getSelectedItem();

            var table = createTable(courseSelected, subjectSelected);
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);

            updateMainPanel(mainPanel, courseSelection, lfSelection, fachrichtungSelection, scrollPane);
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

  private JPanel createPanel() {
    var panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    return panel;
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
    JTable table = new JTable();
    DefaultTableModel model = new DefaultTableModel();

    JTableHeader header = table.getTableHeader();
    header.setBackground(Color.yellow);
    table.setGridColor(Color.GRAY);

    String[] columnNames = {"No", "Nachname", "Vorname", "Leistung"};
    model.setColumnIdentifiers(columnNames);
    table.setModel(model);

    List<Student> students;

    if (subjectName == null) {
      students = databaseService.getStudentsByCourseName(courseName);
    } else {
      students = databaseService.getStudentsByCourseNameAndSubject(courseName, subjectName);
    }

    for (int i = 0; i < students.size(); i++) {
      Object[] o = new Object[4];
      o[0] = i + 1;
      o[1] = students.get(i).getLastName();
      o[2] = students.get(i).getFirstName();
      o[3] = students.get(i).getEvaluation();
      model.addRow(o);
    }
    return table;
  }
}
