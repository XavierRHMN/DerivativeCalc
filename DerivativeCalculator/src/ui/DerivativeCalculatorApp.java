package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.MathNode;
import model.MathParser;

import java.awt.*;

public class DerivativeCalculatorApp extends JFrame {
    private JTextField inputField;
    private JButton calculateButton;
    private JTextArea displayArea;

    public DerivativeCalculatorApp() {
        initField();
        initButton();
        initDisplayArea();
        addActionListeners();
        layoutComponents();
    }
    private void initDisplayArea() {
        displayArea = new JTextArea(5, 20);
        displayArea.setEditable(false);
    }

    private void initButton() {
        calculateButton = new JButton("Calculate Derivative");
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        calculateButton.setBackground(new Color(50, 150, 200));
        calculateButton.setForeground(Color.WHITE);
    }

    private void initField() {
        inputField = new JTextField(20);
        inputField.setText("0");
        inputField.setFont(new Font("SansSerif", Font.BOLD, 16));
    }

    private void layoutComponents() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 2, 10, 10)); // 1 row, 2 cols, 10px gaps
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px padding around the panel
        inputPanel.add(inputField);
        inputPanel.add(calculateButton);

        setLayout(new BorderLayout(10, 10)); // 10px horizontal and vertical gaps
        add(inputPanel, BorderLayout.NORTH);

        add(new JScrollPane(displayArea), BorderLayout.CENTER);

    }

    private void addActionListeners() {
        MathParser parser = new MathParser();

        calculateButton.addActionListener(e -> {
            String userInput = inputField.getText();
            MathNode expression = parser.parseExpression(userInput);
            MathNode derivative = expression.differentiate();
            displayArea.setFont(new Font("SansSerif", Font.BOLD, 15));
            displayArea.setText(derivative.simplify().toString());
        });
    }
}

