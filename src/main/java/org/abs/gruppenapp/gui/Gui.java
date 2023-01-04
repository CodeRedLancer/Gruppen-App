package org.abs.gruppenapp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import javax.swing.*;
import java.awt.*;

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

    var mainPanel = createPanel();

    var courseSelection = getCourseSelection();
    mainPanel.add(courseSelection);

    add(mainPanel);
    setVisible(true);

    courseSelection.addActionListener(event -> {
      var comboBox = (JComboBox<String>) event.getSource();
      System.out.println("Getting info for: " + comboBox.getSelectedItem());

      var lfSelection = getLfSelection();

      mainPanel.removeAll();
      mainPanel.add(courseSelection);
      mainPanel.add(lfSelection);
      updateMainPanel(mainPanel);

      lfSelection.addActionListener(eventLf -> {
        var comboBx = (JComboBox<String>) eventLf.getSource();
        System.out.println("Getting info for: " + comboBx.getSelectedItem());

        var fachrichtungSelection = getFachrichtungSelection();

        mainPanel.removeAll();
        mainPanel.add(courseSelection);
        mainPanel.add(lfSelection);
        mainPanel.add(fachrichtungSelection);
        updateMainPanel(mainPanel);
      });
    });
  }

  private void updateMainPanel(JPanel mainPanel) {
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  private JComboBox<String> getCourseSelection() {
    List<Course> courses = (List<Course>) courseRepository.findAll();
    List<String> courseNames = courses.stream().map(Course::getName).toList();

    return createComboBox(courseNames);
  }

  private JComboBox<String> getLfSelection() {
    List<LearningField> learningField = (List<LearningField>) learningFieldRepository.findAll();
    List<String> lfNames = learningField.stream().map(LearningField::getName).toList();

    return createComboBox(lfNames);
  }

  private JComboBox<String> getFachrichtungSelection() {
    List<Subject> subject = (List<Subject>) subjectRepository.findAll();
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
      comboBox.addItem("no course found");
//      comboBox.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
    } else {
      comboBox.setModel(new DefaultComboBoxModel<>(items.toArray(new String[0])));
      comboBox.setSelectedIndex(0);
    }

    return comboBox;
  }
}
