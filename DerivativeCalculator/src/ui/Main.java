package ui;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        DerivativeCalculatorApp manager = new DerivativeCalculatorApp();
        manager.setSize(600, 800);
        manager.setLocation(650, 20);
        manager.setTitle("Derivative Calculator");
        manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manager.setVisible(true);
    }
}
