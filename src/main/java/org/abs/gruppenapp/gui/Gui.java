package org.abs.gruppenapp.gui;

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


  public void classSelection() {
    List<Course> courses = (List<Course>) courseRepository.findAll();
    List<String> courseNames = courses.stream().map(Course::getName).toList();

    var courseSelection = createComboBox(courseNames);

    setTitle("GroupMaker 8");
    setSize(500, 500);

    var panel = createPanel();
    panel.add(courseSelection, FlowLayout.LEFT);

    updateFrame(panel);

    courseSelection.addActionListener(event -> {
      var comboBox = (JComboBox<String>) event.getSource();
      System.out.println("Getting info for: " + comboBox.getSelectedItem());
      lfSelection(panel);
    });
  }

  private JPanel createPanel() {
    var flowLayout = new FlowLayout();
    var panel = new JPanel();

    flowLayout.setAlignment(FlowLayout.LEFT);
    panel.setLayout(flowLayout);

    return panel;
  }

  private void lfSelection(JPanel panel) {
    List<LearningField> learningField = (List<LearningField>) learningFieldRepository.findAll();
    List<String> lfNames = learningField.stream().map(LearningField::getName).toList();

    var lfSelection = createComboBox(lfNames);

    panel.add(lfSelection);
    updateFrame(panel);

    lfSelection.addActionListener(event -> {
      var comboBox = (JComboBox<String>) event.getSource();
      System.out.println("Getting info for: " + comboBox.getSelectedItem());
      fachrichtungSelection(panel);
    });
  }

  private void fachrichtungSelection(JPanel panel) {
    List<Subject> subject = (List<Subject>) subjectRepository.findAll();
    List<String> subjectNames = subject.stream().map(Subject::getName).toList();

    var fachrichtungSelection = createComboBox(subjectNames);

    panel.add(fachrichtungSelection);
    updateFrame(panel);
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

  private void updateFrame(JPanel panel) {
    add(panel);
    setVisible(true);
  }

  private String getStudent(){
    var student = (List<Student>) studentRepository.findAll();

    return student.get(0).getFirstName();

  }
}
