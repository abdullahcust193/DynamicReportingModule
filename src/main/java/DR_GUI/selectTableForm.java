package DR_GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class selectTableForm extends javax.swing.JFrame {

    private Connection conn;
private selectDbForm select_dbform;
    public selectTableForm(String selectedDb) {

        initComponents();
         
//        String selectedDb = select_dbform.getSelectedComboBoxItem();
        String url = "jdbc:mysql://localhost:3307/";
        String user = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(url, user, password);

           
                try {
                  
                    List<String> tableArray = getTableList(selectedDb);
                    tableCombox.removeAllItems();
                    System.out.println("\nTables in " + selectedDb + " database:");

                    for (String tableName : tableArray) {
                        System.out.println(tableName);
                        tableCombox.addItem(tableName);
                    }

                } catch (SQLException ex) {
                    System.out.println("\nError with Fetching Tables in ComboBox: " + ex.getMessage());
                }
        

//            tableCombox.addActionListener(e -> {
//                try {
//                    String databaseName = (String) dbCombBox.getSelectedItem();
//                    String tableName = (String) tableCombBx.getSelectedItem();
//                    String url2 = "jdbc:mysql://localhost:3307/" + databaseName;
//                    String user2 = "root";
//                    String password2 = "";
//                    conn = DriverManager.getConnection(url2, user2, password2);
//                    getColumns(tableName);
//
//                } catch (SQLException ex) {
//                    System.out.println("\nError with Fetching Column in ComboBox: " + ex.getMessage());
//                }
//            });

        } catch (SQLException e) {
            System.out.println("\nDatabase Connection Error: " + e.getMessage());
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tableCombox = new javax.swing.JComboBox<>();
        tableDoneBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableCombox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Tables" }));

        tableDoneBtn.setText("Done");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tableCombox, 0, 196, Short.MAX_VALUE)
                    .addComponent(tableDoneBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(tableCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableDoneBtn)
                .addContainerGap(135, Short.MAX_VALUE))
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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new selectTableForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> tableCombox;
    private javax.swing.JButton tableDoneBtn;
    // End of variables declaration//GEN-END:variables
}
