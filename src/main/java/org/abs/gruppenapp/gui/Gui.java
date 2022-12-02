package org.abs.gruppenapp.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class Gui extends JFrame {

    public void classSelection() {
        String[] courses = {"Kurs", "2402", "2403"};

        var courseSelection = createComboBox(courses);

        setTitle("Gruppen-App");
        setSize(500, 500);

        var panel = createPanel();
        panel.add(courseSelection, FlowLayout.LEFT);

        updateFrame(panel);

        courseSelection.addActionListener(event -> {
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
        String[] lf = {"LF", "LF1", "LF2"};
        var lfSelection = createComboBox(lf);

        panel.add(lfSelection);
        updateFrame(panel);
    }

    private JComboBox<?> createComboBox(String[] items) {
        JComboBox<?> comboBox = new JComboBox<>(items);
        comboBox.setSelectedIndex(0);

        return comboBox;
    }

    private void updateFrame(JPanel panel) {
        add(panel);
        setVisible(true);
    }
}
