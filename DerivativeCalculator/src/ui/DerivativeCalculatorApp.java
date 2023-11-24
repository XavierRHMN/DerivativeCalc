package ui;

import javax.swing.*;
import model.MathNode;
import model.MathParser;

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
    }

    private void initField() {
        inputField = new JTextField(20);
        inputField.setText("0");
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(calculateButton);
        panel.add(new JScrollPane(displayArea));
        this.add(panel);
    }

    private void addActionListeners() {
        MathParser parser = new MathParser();

        calculateButton.addActionListener(e -> {
            String userInput = inputField.getText();
            MathNode expression = parser.parseExpression(userInput);
            MathNode derivative = expression.differentiate();
            displayArea.setText(derivative.simplify().toString());
        });
    }
}

