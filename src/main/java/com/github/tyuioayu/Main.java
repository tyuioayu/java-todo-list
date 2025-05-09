package com.github.tyuioayu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Main {
    private final TaskList taskList = new TaskList();
    private final JTextField taskField;
    private final JTextField dateField;

    public Main() {
        // Create main window
        final JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500); // Increase window size

        // Main panel
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(10, 10)); // Add margin

        // Task list
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Input panel (for entering task and due date)
        final JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // GridLayout with margin
        inputPanel.add(new JLabel("Task:"));
        taskField = new JTextField();
        inputPanel.add(taskField);

        inputPanel.add(new JLabel("Due Date (dd-mm-yyyy):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        // Buttons panel
        final JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10)); // 5 buttons
        final JButton addButton = new JButton("Add");
        final JButton removeButton = new JButton("Remove");
        final JButton editButton = new JButton("Edit");

        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);

        // Add action listeners to buttons
        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> taskList.getSelectedTask().ifPresent(taskList::removeTask));
        editButton.addActionListener(e -> editTask());

        // Add panels to main panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    // Add a new task
    private void addTask() {
        final String taskText = taskField.getText().trim();
        final String dueDateText = dateField.getText().trim();
        if (!taskText.isEmpty() && !dueDateText.isEmpty()) {
            taskList.addTask(new TaskItem(taskText, dueDateText));
            taskField.setText("");
        }
    }

    // Edit a task
    private void editTask() {
        final int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            final String newTaskText = JOptionPane.showInputDialog("Enter new task:");
            final String newDueDateText = JOptionPane.showInputDialog("Enter new due date (yyyy-mm-dd):");
            if (newTaskText != null && newDueDateText != null) {
                taskList.getSelectedTask().ifPresent(task -> {
                    task.setTask(newTaskText);
                    task.setDueDate(newDueDateText);
                    taskList.repaint();
                });
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
