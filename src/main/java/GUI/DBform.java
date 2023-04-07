package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class DBform extends javax.swing.JFrame {

    private Connection conn;
    private String databasename;
    private String tablename;

    public DBform() {
        initComponents();
        String url = "jdbc:mysql://localhost:3307/";
        String user = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, user, password);
            List<String> databaseList = getDatabaseList();

            System.out.println("\nDatabase List:");
            for (String dbName : databaseList) {
                System.out.println(dbName);
                dbCombBox.addItem(dbName);
            }

            dbCombBox.addActionListener(e -> {
                try {
                    String databaseName = (String) dbCombBox.getSelectedItem();
                    List<String> tableArray = getTableList(databaseName);

                    tableCombBx.removeAllItems();
                    System.out.println("\nTables in " + databaseName + " database:");
                    for (String tableName : tableArray) {
                        System.out.println(tableName);
                        tableCombBx.addItem(tableName);
                    }
                } catch (SQLException ex) {

                    System.out.println("table combo error: " + ex.getMessage());
                }
            });

            tableCombBx.addActionListener(e -> {
                try {
                    String databaseName = (String) dbCombBox.getSelectedItem();
                    String tableName = (String) tableCombBx.getSelectedItem();
                    String url2 = "jdbc:mysql://localhost:3307/" + databaseName;
                    String user2 = "root";
                    String password2 = "";
                    conn = DriverManager.getConnection(url2, user2, password2);
                    getColumns(tableName);
//                    List<String> columnList = getFieldList(tableName);
//                    columnCombBx.removeAllItems();
//                    System.out.println("\nColumns in " + tableName + " Table:");
//                    for (String column : columnList) {
//                        System.out.println(column);
//                        columnCombBx.addItem(column); // Add each column to the columnComboBox
//                        System.out.println("Added " + column + " to columnCombBx.");
//                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Column Combobox error: " + ex.getMessage());
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Main Error: " + e.getMessage());
        }

    }

    private List<String> getDatabaseList() throws SQLException {
        List<String> databaseList = new ArrayList<>();
        String query = "SHOW DATABASES";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String dbName = rs.getString(1);
                databaseList.add(dbName);
            }
        }
        return databaseList;
    }

    private List<String> getTableList(String databaseName) throws SQLException {
        List<String> tableList = new ArrayList<>();
        String query = "SHOW TABLES IN " + databaseName;
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String tableName = rs.getString(1);
                tableList.add(tableName);
            }
        }
        return tableList;
    }

    public void getColumns(String tableName) throws SQLException {

        String query = "SELECT * from " + tableName;
        String columnName;

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("\nNo of columns: " + rsmd.getColumnCount());
            int col = rsmd.getColumnCount();
            columnCombBx.removeAllItems();
            for (int i = 1; i <= col; i++) {
                System.out.println(rsmd.getColumnName(i) + " ");
                System.out.println("\n");
                columnName = rsmd.getColumnName(i);
                columnCombBx.addItem(columnName);
            }
        }
    }

//    private List<String> getFieldList(String tableName) throws SQLException {
//        List<String> fieldList = new ArrayList<>();
//        String query = "SHOW COLUMNS FROM " + tableName;
//        try (Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(query)) {
//            while (rs.next()) {
//                String columnName = rs.getString("COLUMN_NAME");
//                fieldList.add(columnName);
//            }
//        }
//
//        return fieldList;
//    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        showTables = new javax.swing.JLabel();
        dbCombBox = new javax.swing.JComboBox<>();
        showdb1 = new javax.swing.JLabel();
        tableCombBx = new javax.swing.JComboBox<>();
        showTables1 = new javax.swing.JLabel();
        columnCombBx = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(253, 239, 239));

        showTables.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        showTables.setText("Select Columns");

        dbCombBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dbCombBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the database" }));
        dbCombBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbCombBoxActionPerformed(evt);
            }
        });

        showdb1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        showdb1.setText("Select Database");

        tableCombBx.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableCombBx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the table" }));
        tableCombBx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableCombBxActionPerformed(evt);
            }
        });

        showTables1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        showTables1.setText("Select Tables");

        columnCombBx.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        columnCombBx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the column" }));
        columnCombBx.setMinimumSize(new java.awt.Dimension(157, 31));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showTables1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showdb1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbCombBox, 0, 292, Short.MAX_VALUE)
                    .addComponent(showTables, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableCombBx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(columnCombBx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(534, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(showdb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(showTables1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(showTables)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(columnCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dbCombBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbCombBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_dbCombBoxActionPerformed

    private void tableCombBxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableCombBxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableCombBxActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DBform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> columnCombBx;
    private javax.swing.JComboBox<String> dbCombBox;
    private javax.swing.JPanel panel1;
    private javax.swing.JLabel showTables;
    private javax.swing.JLabel showTables1;
    private javax.swing.JLabel showdb1;
    private javax.swing.JComboBox<String> tableCombBx;
    // End of variables declaration//GEN-END:variables

}
