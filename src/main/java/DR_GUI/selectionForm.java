package DR_GUI;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.view.JasperViewer;

public class selectionForm extends javax.swing.JFrame {

    private Connection conn;
    private String tableName = "";
    private boolean dbSelected = false;
    private boolean tableSelected = false;
    public String dbName = "";
    private List<String> columnNames;
    private DefaultTableModel model2;
    private String url = "jdbc:mysql://localhost:3307/";
    private String user = "root";
    private String password = "";

    public selectionForm() {
        initComponents();
        setTitle("Dynamic Reporting");

        try {
            conn = DriverManager.getConnection(url, user, password);
            model2 = (DefaultTableModel) mainTable.getModel();
        } catch (SQLException e) {
            System.out.println("\nDatabase Connection Error: " + e.getMessage());
        }

    }

    public boolean isDbSelected() {
        return dbSelected;
    }

    public void setDbSelected(boolean dbSelected) {
        this.dbSelected = dbSelected;
    }

    public boolean isTableSelected() {
        return tableSelected;
    }

    public void setTableSelected(boolean tableSelected) {
        this.tableSelected = tableSelected;
    }

    public void setDatabaseName(String dbN) {
        dbName = dbN;
        dbSelectMsg.setText(dbN);
        System.out.println("DB Name get from Db from: " + dbName);
    }

    public void setTableName(String tblName) {
        tableName = tblName;
        tblSelectMsg.setText(tblName);
        System.out.println("\nTable Name get from Table From: " + tableName);
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
        String colMsg = "Columns Not Selected";
        if (!columnNames.isEmpty()) {
            colMsg = String.join(", ", columnNames);
        }
        showColumnsLbl.setText(colMsg);

    }

    public void fetchSelectedColumnsData() {

        model2 = (DefaultTableModel) mainTable.getModel();
        model2.setRowCount(0);
        model2.setColumnCount(0); // Clear column headers
        if (columnNames.isEmpty() || tableName == null || tableName.isEmpty()) {
            System.out.println("No columns or table selected.");
            return;
        }
        try {
            conn.createStatement().executeUpdate("USE " + dbName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connection Error: " + ex.getMessage());
        }

        String query = "SELECT ";
        for (String column : columnNames) {
            query += column + ",";
        }

        query = query.substring(0, query.length() - 1);
        query += " FROM " + tableName;
        try {
            ResultSet rs = conn.createStatement().executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            // Set column headers in table
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model2.addColumn(metaData.getColumnLabel(i));
            }
            // Add data to table
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model2.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
        }
//        try (Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(query)) {
//
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnCount = rsmd.getColumnCount();
//            // Get column names
//            String[] columnNam = new String[columnCount];
//            for (int i = 1; i <= columnCount; i++) {
//                columnNam[i - 1] = rsmd.getColumnName(i);
//            }
//            // Set column names in the table model
//            selectedColumnsTableModel.setColumnIdentifiers(columnNam);
//            // Add rows to the table model
//            while (rs.next()) {
//                Object[] rowData = new Object[columnCount];
//                for (int i = 1; i <= columnCount; i++) {
//                    rowData[i - 1] = rs.getObject(i);
//                }
//                selectedColumnsTableModel.addRow(rowData);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error in Select Colums fUCNN: " + e.getMessage());
//        }
    }

    private void openDbForm(boolean dbSelected) {
        selectDbForm showdbs = new selectDbForm(this, dbSelected);
        showdbs.setVisible(true);
    }

    private void openTableForm(boolean tableSelected) {
        selectTableForm sTableForm = new selectTableForm(this, dbName, tableSelected);
        sTableForm.setVisible(true);
    }

    private void openColumnForm() {
        selectColumnForm sColumnForm = new selectColumnForm(this, dbName, tableName);
        sColumnForm.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        selectDbBtn = new javax.swing.JButton();
        dbSelectMsg = new javax.swing.JLabel();
        selectTableBtn = new javax.swing.JButton();
        tblSelectMsg = new javax.swing.JLabel();
        columnSelectBtn = new javax.swing.JButton();
        showColumnsLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        printBTn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setName("*********"); // NOI18N

        selectDbBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        selectDbBtn.setText("Select Database");
        selectDbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDbBtnActionPerformed(evt);
            }
        });

        dbSelectMsg.setForeground(new java.awt.Color(255, 0, 0));
        dbSelectMsg.setText("Database Not Selected");

        selectTableBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        selectTableBtn.setText("Select Table");
        selectTableBtn.setPreferredSize(new java.awt.Dimension(129, 29));
        selectTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTableBtnActionPerformed(evt);
            }
        });

        tblSelectMsg.setForeground(new java.awt.Color(255, 0, 0));
        tblSelectMsg.setText("Table Not Selected");

        columnSelectBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        columnSelectBtn.setText("Select Columns");
        columnSelectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnSelectBtnActionPerformed(evt);
            }
        });

        showColumnsLbl.setForeground(new java.awt.Color(255, 0, 0));
        showColumnsLbl.setText("Columns Not Selected");

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(mainTable);

        printBTn.setText("Print");
        printBTn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBTnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(printBTn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(selectDbBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dbSelectMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tblSelectMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(selectTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(columnSelectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(showColumnsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectDbBtn)
                    .addComponent(selectTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnSelectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tblSelectMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showColumnsLbl))
                    .addComponent(dbSelectMsg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printBTn)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selectDbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDbBtnActionPerformed
        openDbForm(dbSelected);
    }//GEN-LAST:event_selectDbBtnActionPerformed

    private void selectTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectTableBtnActionPerformed
        if (!dbSelected) {
            JOptionPane.showMessageDialog(this, "Please select a database first.");
        } else {
            openTableForm(tableSelected);
        }
    }//GEN-LAST:event_selectTableBtnActionPerformed

    private void columnSelectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_columnSelectBtnActionPerformed

        if (!dbSelected) {
            JOptionPane.showMessageDialog(this, "Please select a database first.");
        } else if (!tableSelected) {
            JOptionPane.showMessageDialog(this, "Please select a table first.");
        } else {
            openColumnForm();
        }

    }//GEN-LAST:event_columnSelectBtnActionPerformed
    private JRResultSetDataSource getData(String qury) {
        // Fetch or generate your data here
        JRResultSetDataSource resultSetDataSource = null;

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(qury);
            // Convert ResultSet to JRResultSetDataSource
            resultSetDataSource = new JRResultSetDataSource(resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL Error in GetData(): " + ex.getMessage());
        }
        return resultSetDataSource;
    }

    private void GenerateTemplateDynimcally() {
        try {
            List<DR_GUI.Field> fields = new ArrayList<>();
            fields.add(new DR_GUI.Field("id", "java.lang.Integer"));
            fields.add(new DR_GUI.Field("name", "java.lang.String"));
            fields.add(new DR_GUI.Field("age", "java.lang.Integer"));
            fields.add(new DR_GUI.Field("registration_no", "java.lang.String"));
            fields.add(new DR_GUI.Field("registration_ID", "java.lang.String"));

            // Generate the JRXML file dynamically
            JasperDesign jasperDesign = new JasperDesign();
            jasperDesign.setName("DynamicDataReport");
            jasperDesign.setPageWidth(595);
            jasperDesign.setPageHeight(842);
            jasperDesign.setColumnWidth(555);
            jasperDesign.setLeftMargin(20);
            jasperDesign.setRightMargin(20);
            jasperDesign.setTopMargin(20);
            jasperDesign.setBottomMargin(20);

            //  JRDesignBand detailBand = new JRDesignBand();
            //  detailBand.setElementGroup(detailBand);
            JRDesignBand detailBand = new JRDesignBand();
            detailBand.setHeight(30);

            // Add fields to the report design
            for (DR_GUI.Field field : fields) {
                JRDesignField jrField = new JRDesignField();
                jrField.setName(field.getName());
                jrField.setValueClass(Class.forName(field.getClassName()));
                jasperDesign.addField(jrField);

                JRDesignTextField textField = new JRDesignTextField();
                textField.setX(0);
                textField.setY(0);
                textField.setWidth(100);
                textField.setHeight(30);
                textField.setExpression(new JRDesignExpression("$F{" + field.getName() + "}"));

                // JRDesignBand detailBand = jasperDesign.getDetail().get(0);
                detailBand.addElement(textField);

                // Save the compiled report to a Jasper file
                JRXmlWriter.writeReport(jasperDesign, new FileOutputStream(new File("DynamicDataReport.jrxml")), "UTF-8");
                System.out.println("DynamicDataReport.jrxml generated successfully.");

            }
            detailBand.setElementGroup(detailBand);

        } catch (ClassNotFoundException | FileNotFoundException | JRException ex) {
            Logger.getLogger(selectionForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printReport() throws JRException {

        GenerateTemplateDynimcally();
//        JasperDesign design = JRXmlLoader.load("C:\\Users\\hp\\Documents\\GitHub\\DynamicReporting\\DynamicReportingModule\\src\\main\\java\\DR_GUI\\report1.jrxml");
//
//        String query = "SELECT ";
//        int numColumns = mainTable.getColumnCount();
//        for (int i = 0; i < numColumns; i++) {
//            query += mainTable.getColumnName(i);
//            if (i != numColumns - 1) {
//                query += ", ";
//            }
//        }
//        query += " FROM " + tableName; // replace 'myTable' with the name of your table
//        // Create the JRDesignQuery object and set the query text
//        // JRDesignQuery jrQuery = new JRDesignQuery();
//        // jrQuery.setText(query);
//        // Set the query for the JasperDesign object
//        // design.setQuery(jrQuery);
//        System.out.println("Generated query = " + query);
//        JRResultSetDataSource dataList = getData(query);
//        // Compile the JRXML file
//        JasperReport report = net.sf.jasperreports.engine.JasperCompileManager.compileReport(design);
//        // Create a HashMap to hold the report parameters
//        HashMap<String, Object> parameters = new HashMap<>();
//        // Get the column names from the JTable
//        String[] columnNames = new String[mainTable.getColumnCount()];
//        for (int i = 0; i < columnNames.length; i++) {
//            columnNames[i] = mainTable.getColumnName(i);
//        }
//        // Fill the report with data
//        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataList);
//
//        // Show the report in a viewer
//        JasperViewer.viewReport(jasperPrint, false);
    }

    private void printBTnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBTnActionPerformed
        try {
            // TODO add your handling code here:
            printReport();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Jasper error." + ex.getMessage());
            System.out.println("Jasper error= " + ex.getMessage());
        }
    }//GEN-LAST:event_printBTnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new selectionForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton columnSelectBtn;
    private javax.swing.JLabel dbSelectMsg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mainTable;
    private javax.swing.JButton printBTn;
    private javax.swing.JButton selectDbBtn;
    private javax.swing.JButton selectTableBtn;
    private javax.swing.JLabel showColumnsLbl;
    private javax.swing.JLabel tblSelectMsg;
    // End of variables declaration//GEN-END:variables

}
