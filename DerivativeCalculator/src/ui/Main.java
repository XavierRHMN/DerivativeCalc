package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DerivativeCalculatorApp manager = new DerivativeCalculatorApp();
        manager.setSize(1200, 200);
        manager.setLocationRelativeTo(null);
        manager.setTitle("Derivative Calculator");
        manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manager.setVisible(true);
    }
}

