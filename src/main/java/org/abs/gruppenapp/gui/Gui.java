package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.entities.Course;
import org.abs.gruppenapp.entities.LearningField;
import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.entities.Subject;
import org.abs.gruppenapp.repository.CourseRepository;
import org.abs.gruppenapp.repository.LearningFieldRepository;
import org.abs.gruppenapp.repository.StudentRepository;
import org.abs.gruppenapp.repository.SubjectRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Gui extends JFrame {

  private final CourseRepository courseRepository;
  private final LearningFieldRepository learningFieldRepository;
  private final SubjectRepository subjectRepository;
  private final StudentRepository studentRepository;


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
      var comboBox = (JComboBox<String>) event.getSource();
      System.out.println("Getting info for: " + comboBox.getSelectedItem());

      var courseSelected = (String) courseSelection.getSelectedItem();

      var lfSelection = getLfSelection(courseSelected);

      updateMainPanel(mainPanel, courseSelection, lfSelection);

      lfSelection.addActionListener(eventLf -> {
        var comboBx = (JComboBox<String>) eventLf.getSource();
        System.out.println("Getting info for: " + comboBx.getSelectedItem());

        var lfSelected = (String) comboBx.getSelectedItem();

        List<Subject> subject = subjectRepository.findByLearningFields_name(lfSelected);

        if (subject.isEmpty()) {
          var table = createTable(courseSelected);
          table.setFillsViewportHeight(true);

          JScrollPane scrollPane = new JScrollPane(table);

          updateMainPanel(mainPanel, courseSelection, lfSelection, scrollPane);

        } else {
          var fachrichtungSelection = getFachrichtungSelection(lfSelected);

          updateMainPanel(mainPanel, courseSelection, lfSelection, fachrichtungSelection);

          fachrichtungSelection.addActionListener(fachEvent -> {
            var table = createTable(courseSelected);
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

    for (java.awt.Component o : elements){
      mainPanel.add(o);
    }

    mainPanel.revalidate();
    mainPanel.repaint();
  }

  private JComboBox<String> getCourseSelection() {
    List<Course> courses = (List<Course>) courseRepository.findAll();
    List<String> courseNames = courses.stream().map(Course::getName).toList();

    return createComboBox(courseNames);
  }

  private JComboBox<String> getLfSelection(String course) {
    List<LearningField> learningField = learningFieldRepository.findByCourses_Name(course);
    var lfNames = learningField.stream().map(LearningField::getName).toList();

    return createComboBox(lfNames);
  }

  private JComboBox<String> getFachrichtungSelection(String lf) {
    List<Subject> subject = subjectRepository.findByLearningFields_name(lf);
    List<String> subjectNames = subject.stream().map(Subject::getName).toList();

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

  private JTable createTable(String course) {
    JTable table = new JTable();
    DefaultTableModel model = new DefaultTableModel();

    String[] columnNames = {"No", "Nachname", "Vorname", "Leistung"};
    List<Student> students = studentRepository.findByCourse_Name(course);

    for (int i = 0; i < students.size(); i++) {
      Object[] o = new Object[4];
      o[0] = i + 1;
      o[1] = students.get(i).getLastName();
      o[2] = students.get(i).getFirstName();
      o[3] = students.get(i).getEvaluation();
      model.addRow(o);
    }

    model.setColumnIdentifiers(columnNames);
    table.setModel(model);

    return table;
  }
}
