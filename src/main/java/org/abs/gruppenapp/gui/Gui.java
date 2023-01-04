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


  public void initialize(){
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(500, 500);

    var mainPanel = createPanel();
    var courseSelectionPanel = createPanel();
    var lfSelectionPanel = createPanel();

    var courseSelection = getCourseSelection();
    courseSelectionPanel.add(courseSelection);
    mainPanel.add(courseSelectionPanel);

    add(mainPanel);
    setVisible(true);

    courseSelection.addActionListener(event -> {
      var comboBox = (JComboBox<String>) event.getSource();
      System.out.println("Getting info for: " + comboBox.getSelectedItem());

      var lfSelection = getLfSelection();
      lfSelectionPanel.removeAll();
      lfSelectionPanel.add(lfSelection);

      mainPanel.remove(lfSelectionPanel);
      mainPanel.remove(courseSelectionPanel);
      mainPanel.add(courseSelectionPanel);
      mainPanel.add(lfSelectionPanel);
      mainPanel.revalidate();
      mainPanel.repaint();
//      setVisible(true);
    });






  }

  private JComboBox<String> getCourseSelection(){
    List<Course> courses = (List<Course>) courseRepository.findAll();
    List<String> courseNames = courses.stream().map(Course::getName).toList();

    return createComboBox(courseNames);
  }

  private JComboBox<String> getLfSelection(){
    List<LearningField> learningField = (List<LearningField>) learningFieldRepository.findAll();
    List<String> lfNames = learningField.stream().map(LearningField::getName).toList();

    return createComboBox(lfNames);
  }

  private JPanel createPanel() {
    var panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    return panel;
  }

  private void lfSelection(JPanel panel) {

//    panel.add(lfSelection);
//    updateFrame(panel);
//
//    lfSelection.addActionListener(event -> {
//      var comboBox = (JComboBox<String>) event.getSource();
//      System.out.println("Getting info for: " + comboBox.getSelectedItem());
//      fachrichtungSelection(panel);
//    });
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
    getContentPane().add(panel);
//    add(panel);
    setVisible(true);
  }

  private String getStudent(){
    var student = (List<Student>) studentRepository.findAll();

    return student.get(0).getFirstName();

  }
}
