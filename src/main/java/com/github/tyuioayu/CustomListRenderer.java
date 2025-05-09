package com.github.tyuioayu;

import javax.swing.*;
import java.awt.*;

public class CustomListRenderer extends DefaultListCellRenderer {
    private ImageIcon checkIcon = new ImageIcon("check.png");
    private ImageIcon uncheckIcon = new ImageIcon("uncheck.png");

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof TaskItem) {
            TaskItem task = (TaskItem) value;
            label.setIcon(task.isCompleted() ? checkIcon : uncheckIcon);
        }
        return label;
    }
}
