package com.first;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewVehiclesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewVehiclesFrame(Connection conn) {

         AdvanceUi.applyModernTheme();

        setTitle("View Available Vehicles");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // ===== TITLE =====
        JLabel titleLabel = new JLabel(" Available Vehicles", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // ===== TABLE MODEL =====
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Vehicle Name", "Type", "Price/Day", "Status"
        });

        table = new JTable(model);

        // ===== TABLE STYLING =====
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(220, 235, 252));
        table.setGridColor(new Color(230, 230, 230));
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(30, 136, 229));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // ===== LOAD DATA FROM DATABASE =====
        loadVehicleData(conn);

        setVisible(true);
    }

    private void loadVehicleData(Connection conn) {

        try {
            model.setRowCount(0); // Clear existing rows

            // ==========================================
            // ⚠️ KEEP YOUR ORIGINAL QUERY SAME
            // Modify only if your column names differ
            // ==========================================

            String query = "SELECT id, name, type, price_per_day, status FROM vehicles";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDouble("price_per_day"),
                        rs.getString("status")
                });
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading vehicles: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}