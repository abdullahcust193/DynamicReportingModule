package DR_GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
        chckboxPanel.setLayout(new BoxLayout(chckboxPanel, BoxLayout.Y_AXIS));

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
                    System.out.println("\nError with Fetching Tables in ComboBox: " + ex.getMessage());
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
                    System.out.println("\nError with Fetching Column in ComboBox: " + ex.getMessage());
                }
            });

        } catch (SQLException e) {
            System.out.println("\nDatabase Connection Error: " + e.getMessage());
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
        // clear the checkboxesPanel before adding new checkboxes
        chckboxPanel.removeAll();
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
                JCheckBox checkbox = new JCheckBox(columnName);
                chckboxPanel.add(checkbox);
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
            // Clear existing columns
            tbl.setColumnCount(0);
            // Get column names
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
                System.out.println("\nColumn Name: " + columnNames[i - 1]);
            }
            // Set column names in the table model
            tbl.setColumnIdentifiers(columnNames);
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

    public void fetchSelectedColumnsData(String tablename) {

        DefaultTableModel selectedColumnsTableModel = (DefaultTableModel) table2.getModel();
        selectedColumnsTableModel.setRowCount(0);
        selectedColumnsTableModel.setColumnCount(0); // Clear column headers
        List<String> selectedColumns = new ArrayList<>();
        for (Component c : chckboxPanel.getComponents()) {
            if (c instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) c;
                if (checkbox.isSelected()) {
                    selectedColumns.add(checkbox.getText());
                }
            }
        }
        if (selectedColumns.isEmpty()) {
            System.out.println("CheckBox Not Selected.");
            selectedColumnsTableModel.setRowCount(0);
            return;
        }
        String query = "SELECT ";
        for (String column : selectedColumns) {
            query += column + ",";
        }

        query = query.substring(0, query.length() - 1);
        query += " FROM " + tablename;

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            // Get column names
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
            // Set column names in the table model
            selectedColumnsTableModel.setColumnIdentifiers(columnNames);
            // Add rows to the table model
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                selectedColumnsTableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            System.out.println("Error in Select Colums fUCNN: " + e.getMessage());
        }
    }

    private void generateReport(String tableName, String dbName) throws SQLException {
        try {
            // Get selected columns from the database
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + dbName, "root", "")) {
                // Get selected columns from the database
                List<String> selectedColumns = new ArrayList<>();
                for (Component c : chckboxPanel.getComponents()) {
                    if (c instanceof JCheckBox) {
                        JCheckBox checkbox = (JCheckBox) c;
                        if (checkbox.isSelected()) {
                            selectedColumns.add(checkbox.getText());
                        }
                    }
                }
                if (selectedColumns.isEmpty()) {
                    System.out.println("CheckBox Not Selected.");
                    return;
                }

                // Generate SELECT statement
                String selectClause = "SELECT ";
                for (String column : selectedColumns) {
                    selectClause += column + ",";
                }
                selectClause = selectClause.substring(0, selectClause.length() - 1);

// Generate FROM statement
                String fromClause = "FROM " + tableName;

// Generate WHERE statement
                String whereClause = "WHERE ";
                for (Component c : chckboxPanel.getComponents()) {
                    if (c instanceof JCheckBox checkbox) {
                        if (checkbox.isSelected()) {
                            String columnName = checkbox.getText();
                            whereClause += columnName + " = true AND ";
                        }
                    }
                }
                if (whereClause.endsWith("AND ")) {
                    whereClause = whereClause.substring(0, whereClause.length() - 4);
                }

// Combine the clauses into a single query
                String query = selectClause + " " + fromClause + " " + whereClause;

                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                JasperCompileManager.compileReportToFile("report1.jrxml"); // compile the report template

                Map<String, Object> parameters = new HashMap<>(); // create a map to store the report parameters

                JasperPrint jasperPrint = JasperFillManager.fillReport("report1.jasper", parameters, new JRResultSetDataSource(rs)); // fill the report template with the data

                JasperViewer.viewReport(jasperPrint, false); // show the report in the JasperViewer
            }
        } catch (SQLException | JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dbCombBox = new javax.swing.JComboBox<>();
        tableCombBx = new javax.swing.JComboBox<>();
        columnCombBx = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDB = new javax.swing.JTable();
        chckboxPanel = new javax.swing.JPanel();
        showDataBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        selectColumnsBtn = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        dbCombBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Database" }));

        tableCombBx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Table", " " }));

        columnCombBx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Columns" }));

        tableDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableDB);

        chckboxPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chckboxPanelLayout = new javax.swing.GroupLayout(chckboxPanel);
        chckboxPanel.setLayout(chckboxPanelLayout);
        chckboxPanelLayout.setHorizontalGroup(
            chckboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );
        chckboxPanelLayout.setVerticalGroup(
            chckboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        showDataBtn.setText("Show Record");
        showDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDataBtnActionPerformed(evt);
            }
        });

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table2);

        selectColumnsBtn.setText("Show ");
        selectColumnsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectColumnsBtnActionPerformed(evt);
            }
        });

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectColumnsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(showDataBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(dbCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tableCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(columnCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chckboxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dbCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnCombBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chckboxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showDataBtn)
                    .addComponent(printButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectColumnsBtn)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selectColumnsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectColumnsBtnActionPerformed
        // TODO add your handling code here:
         selectColumnsBtn.addActionListener((ActionEvent e) -> {
            String tableName = (String) tableCombBx.getSelectedItem();
            fetchSelectedColumnsData(tableName);
        });

    }//GEN-LAST:event_selectColumnsBtnActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
        printButton.addActionListener((ActionEvent e) -> {
            try {
                // Handle print button click event
                String tableName = (String) tableCombBx.getSelectedItem();
                String dbName = (String) dbCombBox.getSelectedItem();
                generateReport(tableName, dbName);
            } catch (SQLException ex) {
                System.out.println("Error Print Button EventL: " + ex.getMessage());
            }
        });
    }//GEN-LAST:event_printButtonActionPerformed

    private void showDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDataBtnActionPerformed
        // TODO add your handling code here:
        showDataBtn.addActionListener((ActionEvent e) -> {
            try {
                String tableName = (String) tableCombBx.getSelectedItem();
                fetchTableData(tableName);
            } catch (SQLException ex) {
                System.out.println("Show Data Button error: " + ex.getMessage());
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
    private javax.swing.JPanel chckboxPanel;
    private javax.swing.JComboBox<String> columnCombBx;
    private javax.swing.JComboBox<String> dbCombBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton printButton;
    private javax.swing.JButton selectColumnsBtn;
    private javax.swing.JButton showDataBtn;
    private javax.swing.JTable table2;
    private javax.swing.JComboBox<String> tableCombBx;
    private javax.swing.JTable tableDB;
    // End of variables declaration//GEN-END:variables
}
