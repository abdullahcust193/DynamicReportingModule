package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DBform extends javax.swing.JFrame {

    private Connection conn;
    private String databasename;
    private String tablename;
    private DefaultTableModel tableModel;

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
    
    private void fetchTableData(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
             DefaultTableModel tbl = (DefaultTableModel) tableDB.getModel();
            // Clear existing data from the table model
            tbl.setRowCount(0);
            
            // Get column names
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
            
            // Add rows to the table model
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                tbl.addRow(rowData);
            }
            
        } catch (SQLException ex) {
            System.out.println("Fetch Table Data error: " + ex.getMessage());
        }
    }

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDB = new javax.swing.JTable();
        showDataBtn = new javax.swing.JButton();

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

        tableDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableDB);

        showDataBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        showDataBtn.setText("Show Table");
        showDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDataBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dbCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showdb1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTables1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTables, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(columnCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(showDataBtn))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showdb1)
                    .addComponent(showTables1)
                    .addComponent(showTables))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dbCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(showDataBtn)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(272, 272, 272))
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

    private void showDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDataBtnActionPerformed
        // TODO add your handling code here:
        showDataBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String tableName = (String) tableCombBx.getSelectedItem();
                        fetchTableData(tableName);
                    } catch (SQLException ex) {
                        System.out.println("Show Data Button error: " + ex.getMessage());
                    }
                }
            });
    }//GEN-LAST:event_showDataBtnActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel1;
    private javax.swing.JButton showDataBtn;
    private javax.swing.JLabel showTables;
    private javax.swing.JLabel showTables1;
    private javax.swing.JLabel showdb1;
    private javax.swing.JComboBox<String> tableCombBx;
    private javax.swing.JTable tableDB;
    // End of variables declaration//GEN-END:variables

}
