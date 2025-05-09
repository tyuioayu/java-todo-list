package com.github.tyuioayu;

import javax.swing.*;
import java.awt.*;

public class CustomListRenderer extends DefaultListCellRenderer {
    private final ImageIcon checkIcon = new ImageIcon("check.png");
    private final ImageIcon uncheckIcon = new ImageIcon("uncheck.png");

    @Override
    public Component getListCellRendererComponent(
        final JList<?> list,
        final Object value,
        final int index,
        final boolean isSelected,
        final boolean cellHasFocus
    ) {
        final JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof TaskItem) {
            label.setIcon(((TaskItem) value).isCompleted() ? checkIcon : uncheckIcon);
        }
        return label;
    }
}
