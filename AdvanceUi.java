package com.first;

import javax.swing.*;
import java.awt.*;

public class AdvanceUi {

    public static void applyModernTheme() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        UIManager.put("control", new Color(245, 247, 250));
        UIManager.put("nimbusBase", new Color(30, 136, 229));
        UIManager.put("nimbusFocus", new Color(66, 165, 245));
        UIManager.put("nimbusLightBackground", Color.WHITE);
    }

    public static JButton styledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(30, 136, 229));
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(160, 40));
        return button;
    }

    public static JLabel styledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        return label;
    }

    public static JTextField styledTextField() {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }
}