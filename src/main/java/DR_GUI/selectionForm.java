package DR_GUI;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class selectionForm extends javax.swing.JFrame {

    private Connection conn;
    private String tableName = "";
    private boolean dbSelected = false;
    private boolean tableSelected = false;
    public String dbName = "";
    private List<String> columnNames;
    private List<String> columnClasses;
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

    public void setColumnNames(List<String> columnNames, List<String> columnClassName) {
        this.columnNames = columnNames;
        this.columnClasses = columnClassName;
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
            String colmClass, colName;
            // Set column headers in table
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model2.addColumn(metaData.getColumnLabel(i));
                colName = metaData.getColumnLabel(i);
                System.out.println("column  name is:: " + colName);
                colmClass = metaData.getColumnClassName(i);
                columnClasses.add(colmClass);
                System.out.println(" class name of column:: " + colName + "is :: " + colmClass);
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

    public void generateXML(List<String> columnNames, List<String> columnClasses) throws TransformerException {
        try {
            // Create a new XML document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create the root element
            Element rootElement = doc.createElement("jasperReport");
            rootElement.setAttribute("xmlns", "http://jasperreports.sourceforge.net/jasperreports");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:schemaLocation", "http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd");
            rootElement.setAttribute("name", "DynamicDataReport");
            rootElement.setAttribute("pageWidth", "595");
            rootElement.setAttribute("pageHeight", "842");
            rootElement.setAttribute("columnWidth", "555");
            rootElement.setAttribute("leftMargin", "20");
            rootElement.setAttribute("rightMargin", "20");
            rootElement.setAttribute("topMargin", "20");
            rootElement.setAttribute("bottomMargin", "20");
            doc.appendChild(rootElement);

            // Create title band
            Element titleElement = doc.createElement("title");
            rootElement.appendChild(titleElement);

            Element titleBandElement = doc.createElement("band");
            titleBandElement.setAttribute("height", "50");
            titleElement.appendChild(titleBandElement);

            Element staticTextElement = doc.createElement("staticText");
            titleBandElement.appendChild(staticTextElement);

            Element reportElementElement = doc.createElement("reportElement");
            reportElementElement.setAttribute("x", "0");
            reportElementElement.setAttribute("y", "0");
            reportElementElement.setAttribute("width", "555");
            reportElementElement.setAttribute("height", "30");
            reportElementElement.setAttribute("forecolor", "#FF0000");
            reportElementElement.setAttribute("backcolor", "#FFFF00");
            staticTextElement.appendChild(reportElementElement);

            Element textElement = doc.createElement("text");
            textElement.setTextContent("Professional Report Title");
            staticTextElement.appendChild(textElement);

            // Create detail band
            Element detailElement = doc.createElement("detail");
            rootElement.appendChild(detailElement);

            Element detailBandElement = doc.createElement("band");
            detailBandElement.setAttribute("height", "30");
            detailElement.appendChild(detailBandElement);

            // Create field elements for each column name
            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);
                String columnClass = columnClasses.get(i);

                // Create field element
                Element fieldElement = doc.createElement("field");
                fieldElement.setAttribute("name", columnName);
                fieldElement.setAttribute("class", columnClass);
                rootElement.appendChild(fieldElement);

                // Create textField element
                Element textFieldElement = doc.createElement("textField");
                detailBandElement.appendChild(textFieldElement);

                reportElementElement.setAttribute("x", "100");
                reportElementElement.setAttribute("y", "0");
                reportElementElement.setAttribute("width", "175");
                reportElementElement.setAttribute("height", "30");
                reportElementElement.setAttribute("uuid", "f708ed29-d9b0-465b-a8de-0a79576abd0f");
                textFieldElement.appendChild(reportElementElement);

                // Create textElement element
                Element textElementElement = doc.createElement("textElement");
                textFieldElement.appendChild(textElementElement);

                // Create font element
                Element fontElement = doc.createElement("font");
                fontElement.setAttribute("size", "12");
                textElementElement.appendChild(fontElement);

                // Create textFieldExpression element
                Element textFieldExpressionElement = doc.createElement("textFieldExpression");
                textFieldExpressionElement.setTextContent("$F{" + columnName + "}");
                textFieldElement.appendChild(textFieldExpressionElement);
            }

            // Write the XML document to a file
            File file = new File("C:\\Users\\chabd\\OneDrive\\Documents\\GitHub\\DynamicReportingModule\\fields.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("XML file generated successfully!");
        } catch (ParserConfigurationException | TransformerException e) {
            // Handle exceptions
        }
    }

    public void printReport() throws JRException, TransformerException {
//        try {
            generateXML(columnNames, columnClasses);

//            JasperDesign design = JRXmlLoader.load("C:\\Users\\chabd\\OneDrive\\Documents\\GitHub\\DynamicReportingModule\\src\\main\\java\\DR_GUI\\report1.jrxml");
//
//            String query = "SELECT ";
//            int numColumns = mainTable.getColumnCount();
//            for (int i = 0; i < numColumns; i++) {
//                query += mainTable.getColumnName(i);
//                if (i != numColumns - 1) {
//                    query += ", ";
//                }
//            }
//            query += " FROM " + tableName; // replace 'myTable' with the name of your table
//            // Create the JRDesignQuery object and set the query text
//            // JRDesignQuery jrQuery = new JRDesignQuery();
//            // jrQuery.setText(query);
//            // Set the query for the JasperDesign object
//            // design.setQuery(jrQuery);
//            System.out.println("Generated query = " + query);
//            JRResultSetDataSource dataList = getData(query);
//            // Compile the JRXML file
//            JasperReport report = net.sf.jasperreports.engine.JasperCompileManager.compileReport(design);
//            // Create a HashMap to hold the report parameters
//            HashMap<String, Object> parameters = new HashMap<>();
//            // Get the column names from the JTable
//            String[] columnNames = new String[mainTable.getColumnCount()];
//            for (int i = 0; i < columnNames.length; i++) {
//                columnNames[i] = mainTable.getColumnName(i);
//            }
//            // Fill the report with data
//            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataList);
//
//            // Show the report in a viewer
//            JasperViewer.viewReport(jasperPrint, false);
//        } catch (TransformerException ex) {
//            JOptionPane.showMessageDialog(this, "error in calling GenerateTemplateDynimcally: " + ex.getMessage());
//        }
    }

    private void printBTnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBTnActionPerformed
        try {
            // TODO add your handling code here:
            printReport();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Jasper error." + ex.getMessage());
            System.out.println("Jasper error= " + ex.getMessage());
        } catch (TransformerException ex) {
            Logger.getLogger(selectionForm.class.getName()).log(Level.SEVERE, null, ex);
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
