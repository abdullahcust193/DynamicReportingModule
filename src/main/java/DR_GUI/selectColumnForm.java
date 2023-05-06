package DR_GUI;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class selectColumnForm extends javax.swing.JFrame {

    private Connection conn;
    private String tblName;
    private String dbname;
    private selectionForm select_form;
    private List<String> selectedColumns = new ArrayList<>();
    private List<String> savedSelectedColumns = new ArrayList<>();

    public selectColumnForm(selectionForm select_form, String selectedDb, String selectedTable) {
        initComponents();
           setTitle("Select Columns");
        this.select_form = select_form;

        String url = "jdbc:mysql://localhost:3307/";
        String user = "root";
        String password = "";
        tblName = selectedTable;
        dbname = selectedDb;
//      checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
//      checkBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        checkBoxPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            conn = DriverManager.getConnection(url, user, password);
            try {
                String databaseName = selectedDb;
                String tableName = selectedTable;
                String url2 = "jdbc:mysql://localhost:3307/" + databaseName;
                String user2 = "root";
                String password2 = "";
                conn = DriverManager.getConnection(url2, user2, password2);
                getColumns(tableName);

            } catch (SQLException ex) {
                System.out.println("\nError with Fetching Column in ComboBox: " + ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("\nDatabase Connection Error: " + e.getMessage());
        }
    }

    public void getColumns(String tableName) throws SQLException {
        // clear the checkboxesPanel before adding new checkboxes
        checkBoxPanel.removeAll();
        String query = "SELECT * from " + tableName;
        String columnName;

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("\nNo of columns: " + rsmd.getColumnCount());
            int col = rsmd.getColumnCount();
//            columnCombBx.removeAllItems();
            for (int i = 1; i <= col; i++) {
                System.out.println(rsmd.getColumnName(i) + " ");
                System.out.println("\n");
                columnName = rsmd.getColumnName(i);
//              columnCombBx.addItem(columnName);
                JCheckBox checkbox = new JCheckBox(columnName);
                // set the checkbox to selected if the column is in the saved list
                if (savedSelectedColumns.contains(columnName)) {
                    checkbox.setSelected(true);
                }
                checkBoxPanel.add(checkbox);
                checkBoxPanel.revalidate();
                checkBoxPanel.repaint();
            }
            rs.close();
            stmt.close();
        }
    }

    public List<String> getSelectedColumns() {
        selectedColumns.clear();
        savedSelectedColumns.clear(); // clear the saved list
        Component[] components = checkBoxPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    selectedColumns.add(checkBox.getText());
                    savedSelectedColumns.add(checkBox.getText()); // add to the saved list
                    System.out.println("Colums Selected from SelectColumn Form : " + checkBox.getText());
                }
            }
        }
        return selectedColumns;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        checkBoxPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        doneColumnBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout checkBoxPanelLayout = new javax.swing.GroupLayout(checkBoxPanel);
        checkBoxPanel.setLayout(checkBoxPanelLayout);
        checkBoxPanelLayout.setHorizontalGroup(
            checkBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        checkBoxPanelLayout.setVerticalGroup(
            checkBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        doneColumnBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        doneColumnBtn.setText("Done");
        doneColumnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneColumnBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(doneColumnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(checkBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doneColumnBtn)
                    .addComponent(jButton1))
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void doneColumnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneColumnBtnActionPerformed
        List<String> selectedColumns = getSelectedColumns();
        JOptionPane.showMessageDialog(null, "Selected Columns: " + selectedColumns);
        select_form.setColumnNames(selectedColumns);
        select_form.fetchSelectedColumnsData();
        dispose();
    }//GEN-LAST:event_doneColumnBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new selectColumnForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel checkBoxPanel;
    private javax.swing.JButton doneColumnBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
