package DR_GUI;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.jasperreports.view.JasperViewer;
import org.w3c.dom.Document;

import org.w3c.dom.Node;

import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.CDATASection;
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
//             jScrollPaneTABLE.setViewportView(mainTable);
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
        if (columnNames.isEmpty() || tableName == null || tableName.isEmpty() || columnNames == null) {
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(printBTn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
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
                            .addComponent(showColumnsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

    private String convertColumnName(String columnName) {
        // Convert "lastname" to "Last Name"
        String[] words = columnName.split("(?=[A-Z])");
        StringBuilder convertedName = new StringBuilder();
        for (String word : words) {
            convertedName.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return convertedName.toString().trim();
    }

    private Element createRootElement(Document doc) {
        Element rootElement = doc.createElementNS("http://jasperreports.sourceforge.net/jasperreports", "jasperReport");
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

        return rootElement;
    }

    private void addFields(Document doc, Element rootElement, List<String> columnNames, List<String> columnClasses) {

        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);
            String columnClass = columnClasses.get(i);

            Element fieldElement = doc.createElement("field");
            fieldElement.setAttribute("name", columnName);
            fieldElement.setAttribute("class", columnClass);
            rootElement.appendChild(fieldElement);

        }
    }

    private void addColumnHeader(Document doc, Element rootElement, List<String> columnNames) {
        Element columnHeaderElement = doc.createElement("columnHeader");
        rootElement.appendChild(columnHeaderElement);

        Element titleBandElement = doc.createElement("band");
        titleBandElement.setAttribute("height", "43");
        titleBandElement.setAttribute("splitType", "Stretch");
        columnHeaderElement.appendChild(titleBandElement);

        int availableWidth = 500; // Total available width of the page
        int spacing = 3; // Spacing between columns

        int totalColumns = columnNames.size();
        int columnWidth = (availableWidth - (totalColumns - 1) * spacing) / totalColumns;

        String convertedColumnName = "";
        int x = 0;
        for (int i = 0; i < totalColumns; i++) {
            String columnName = columnNames.get(i);
            convertedColumnName = convertColumnName(columnName); // Convert the column name
            System.out.println("Column Name Formatted: " + convertedColumnName);
            Element staticTextElement = doc.createElement("staticText");
            titleBandElement.appendChild(staticTextElement);

            Element reportElementElement = doc.createElement("reportElement");
            reportElementElement.setAttribute("x", String.valueOf(x));
            reportElementElement.setAttribute("y", "0");
            reportElementElement.setAttribute("width", String.valueOf(columnWidth));
            reportElementElement.setAttribute("height", "30");
            staticTextElement.appendChild(reportElementElement);

            Element textElementElement = doc.createElement("textElement");
            staticTextElement.appendChild(textElementElement);

            Element fontElement = doc.createElement("font");

            fontElement.setAttribute("size", "14");

            fontElement.setAttribute("isBold", "true");
            textElementElement.appendChild(fontElement);

            Element textElement = doc.createElement("text");
            CDATASection cdataSection = doc.createCDATASection(convertedColumnName);
            textElement.appendChild(cdataSection);
            staticTextElement.appendChild(textElement);

            x += columnWidth + spacing;
        }
    }

    private void addTitleBand(Document doc, Element rootElement) {
        Element titleElement = doc.createElement("title");
        rootElement.appendChild(titleElement);

        Element titleBandElement = doc.createElement("band");
        titleBandElement.setAttribute("height", "50");
        titleElement.appendChild(titleBandElement);

        Element textFieldElement = doc.createElement("textField");
        titleBandElement.appendChild(textFieldElement);

        Element reportElementElement = doc.createElement("reportElement");
        reportElementElement.setAttribute("x", "0");
        reportElementElement.setAttribute("y", "10");
        reportElementElement.setAttribute("width", "555");
        reportElementElement.setAttribute("height", "30");
        textFieldElement.appendChild(reportElementElement);

        Element textElementElement = doc.createElement("textElement");
        textElementElement.setAttribute("textAlignment", "Center");
        textFieldElement.appendChild(textElementElement);

        Element fontElement = doc.createElement("font");
        fontElement.setAttribute("size", "18");
        fontElement.setAttribute("isBold", "true");
        textElementElement.appendChild(fontElement);

        Element textFieldExpressionElement = doc.createElement("textFieldExpression");
        CDATASection cdata = doc.createCDATASection("\"Dynamic Data Report\"");
        textFieldExpressionElement.appendChild(cdata);
        textFieldElement.appendChild(textFieldExpressionElement);
    }

    private void addDetailBand(Document doc, Element rootElement, List<String> columnNames) {

        Element detailElement = doc.createElement("detail");
        rootElement.appendChild(detailElement);
        Element detailBandElement = doc.createElement("band");
        detailBandElement.setAttribute("height", "30");
        detailElement.appendChild(detailBandElement);

        int columnWidth = 100; // Width of each column
        int spacing = 10; // Spacing between columns
        int maxContentWidth = 50; // Maximum content width before minimizing

        int x = 0;
        int numColumns = columnNames.size();
        int pageWidth = Integer.parseInt(rootElement.getAttribute("columnWidth")); // Get the page width from rootElement

        int totalWidth = (numColumns * columnWidth) + ((numColumns - 1) * spacing);
        int actualColumnWidth = columnWidth;

        if (totalWidth > pageWidth) {
            actualColumnWidth = Math.max((pageWidth - ((numColumns - 1) * spacing)) / numColumns, maxContentWidth);
        }

        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);

            Element textFieldElement2 = doc.createElement("textField");
            textFieldElement2.setAttribute("textAdjust", "StretchHeight");
            detailBandElement.appendChild(textFieldElement2);

            Element reportElementElement2 = doc.createElement("reportElement");
            reportElementElement2.setAttribute("x", String.valueOf(x));
            reportElementElement2.setAttribute("y", "0");
            reportElementElement2.setAttribute("width", String.valueOf(actualColumnWidth));
            reportElementElement2.setAttribute("height", "30");
            textFieldElement2.appendChild(reportElementElement2);

            Element textElementElement2 = doc.createElement("textElement");
            textFieldElement2.appendChild(textElementElement2);

            Element fontElement2 = doc.createElement("font");
            fontElement2.setAttribute("size", "12");
            textElementElement2.appendChild(fontElement2);

            Element textFieldExpressionElement2 = doc.createElement("textFieldExpression");
            CDATASection cdata2 = doc.createCDATASection("$F{" + columnName + "}");
            textFieldExpressionElement2.appendChild(cdata2);
            textFieldElement2.appendChild(textFieldExpressionElement2);

            x += actualColumnWidth + spacing;
        }

    }

    private void writeXMLToFile(Document doc, String filePath) throws TransformerException {
        File file = new File(filePath);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        try ( FileOutputStream outputStream = new FileOutputStream(file)) {
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);
        } catch (IOException e) {
            // Handle file write error or log the error
            JOptionPane.showMessageDialog(this, "File Error writeXMLToFile(): " + e.getMessage());
        }
    }

    private String buildQuery() {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");

        int numColumns = mainTable.getColumnCount();
        for (int i = 0; i < numColumns; i++) {
            queryBuilder.append(mainTable.getColumnName(i));
            if (i != numColumns - 1) {
                queryBuilder.append(", ");
            }
        }

        queryBuilder.append(" FROM ").append(tableName);
        String query = queryBuilder.toString();
        System.out.println("Generated query = " + query);
        return query;
    }

    private JasperDesign loadJasperDesign() throws JRException {
        return JRXmlLoader.load("C:\\Users\\hp\\Documents\\GitHub\\DR\\DynamicReportingModule\\fields.jrxml");
    }

    private JasperReport compileReport(JasperDesign design) throws JRException {
        return JasperCompileManager.compileReport(design);
    }

    private HashMap<String, Object> createParameters() {
        // Implement the logic to create and populate the report parameters
        // Example:
        // HashMap<String, Object> parameters = new HashMap<>();
        // parameters.put("param1", value1);
        // parameters.put("param2", value2);
        // ...
        // return parameters;
        return null;

    }

    private JasperPrint fillReport(JasperReport report, Map<String, Object> parameters, JRDataSource dataSource) throws JRException {
        return JasperFillManager.fillReport(report, parameters, dataSource);
    }

    private void displayReport(JasperPrint jasperPrint) {
        // Implement the logic to display the report
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void generateXML(List<String> columnNames, List<String> columnClasses) throws TransformerException {
        String reportFileName = "C:\\Users\\hp\\Documents\\GitHub\\DR\\DynamicReportingModule\\fields.jrxml";
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            //Root Element
            Element rootElement = createRootElement(doc);
            //Fields Tag
            addFields(doc, rootElement, columnNames, columnClasses);
            //Title Tag
            addTitleBand(doc, rootElement);
            //Column Header Tag
            addColumnHeader(doc, rootElement, columnNames);
            //Detail Tag
            addDetailBand(doc, rootElement, columnNames);
            // Write the XML document to a file
            writeXMLToFile(doc, reportFileName);

            System.out.println("XML file generated successfully!");
        } catch (ParserConfigurationException | TransformerException e) {
            // Handle exceptions or log the error
            JOptionPane.showMessageDialog(this, "Error in generateXML() Function: " + e.getMessage());
        }

    }

    public void printReport() {
        try {
            String query = buildQuery();
            generateXML(columnNames, columnClasses);
            JasperDesign design = loadJasperDesign();
            JRResultSetDataSource dataSource = getData(query);
            JasperReport report = compileReport(design);
            HashMap<String, Object> parameters = createParameters();
            JasperPrint jasperPrint = fillReport(report, parameters, dataSource);
            displayReport(jasperPrint);
        } catch (JRException | TransformerException e) {
            // Handle exceptions gracefully or log them for troubleshooting
            JOptionPane.showMessageDialog(this, "Jasper Error: printReport() Function: " + e.getMessage());
        }
    }

    private void printBTnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBTnActionPerformed
        // TODO add your handling code here:
        printReport();

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
