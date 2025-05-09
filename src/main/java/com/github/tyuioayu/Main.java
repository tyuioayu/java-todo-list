package com.github.tyuioayu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final DefaultListModel<TaskItem> listModel;
    private final JList<TaskItem> taskList;
    private final JTextField taskField;
    private final JTextField dateField;
    private final List<TaskItem> data = new ArrayList<>(); // Array to store data

    public Main() {
        // Create main window
        final JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500); // Increase window size

        // Main panel
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Add margin

        // Task list
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setCellRenderer(new CustomListRenderer());
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
        final JButton saveButton = new JButton("Save");
        final JButton toggleButton = new JButton("Completed");

        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(toggleButton);

        // Add action listeners to buttons
        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());
        editButton.addActionListener(e -> editTask());
        saveButton.addActionListener(e -> saveTasks());
        toggleButton.addActionListener(e -> toggleTaskStatus());

        // Add panels to main panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    // Add a new task
    private void addTask() {
        final String taskText = taskField.getText().trim();
        final String dueDateText = dateField.getText().trim();
        if (!taskText.isEmpty() && !dueDateText.isEmpty()) {
            final TaskItem newTask = new TaskItem(taskText, dueDateText);
            listModel.addElement(newTask); // Add to list
            data.add(newTask); // Add to array
            taskField.setText("");
            dateField.setText("");
        }
    }

    // Remove a task
    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex); // Remove from list
            data.remove(selectedIndex); // Remove from array
        }
    }

    // Edit a task
    private void editTask() {
        final int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            final String newTaskText = JOptionPane.showInputDialog("Enter new task:");
            final String newDueDateText = JOptionPane.showInputDialog("Enter new due date (yyyy-mm-dd):");
            if (newTaskText != null && newDueDateText != null) {
                final TaskItem item = listModel.getElementAt(selectedIndex);
                item.setTask(newTaskText);
                item.setDueDate(newDueDateText);
                taskList.repaint(); // Refresh list
            }
        }
    }

    // Mark task as completed
    private void toggleTaskStatus() {
        final int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            final TaskItem item = listModel.getElementAt(selectedIndex);
            item.toggleCompleted();
            taskList.repaint(); // Refresh list
        }
    }

    // Save tasks (only in memory)
    private void saveTasks() {
        JOptionPane.showMessageDialog(null, "Tasks saved!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
