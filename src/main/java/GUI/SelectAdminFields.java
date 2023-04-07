/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DBController.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chabd
 */
public class SelectAdminFields extends javax.swing.JFrame {

    /**
     * Creates new form SelectAdminFields
     */
    private AdminTableForm admnTable;
    Connection conn = null;

    public SelectAdminFields() {
        initComponents();
    }

    //functions-Start*******************************************************************
    public void getAdminTableId() {
        admnTable = new AdminTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admin`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Admin Id");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("adminId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            admnTable.adminTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }
    }

    public void getAdminTableName() {
        admnTable = new AdminTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admin`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Admin Username");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("adminUsername"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            admnTable.adminTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getAdminTableAge() {
        admnTable = new AdminTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admin`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Admin Age");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("adminAge"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            admnTable.adminTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getAdminTableIdName() {
        admnTable = new AdminTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admin`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Admin Id");
            column_heading.addElement("Admin Username");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("adminId"));
                newRec.addElement(rs.getString("adminUsername"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            admnTable.adminTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getAdminTableIdAge() {
        admnTable = new AdminTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admin`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Admin Id");
            column_heading.addElement("Admin Age");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("adminId"));
                newRec.addElement(rs.getString("adminAge"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            admnTable.adminTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getAdminTableNameAge() {
        admnTable = new AdminTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admin`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Admin Username");
            column_heading.addElement("Admin Age");
            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("adminUsername"));
                newRec.addElement(rs.getString("adminAge"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            admnTable.adminTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getAdminTableIdNameAge() {
        admnTable = new AdminTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admin`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Admin Id");
            column_heading.addElement("Admin Username");
            column_heading.addElement("Admin Age");
            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("adminId"));
                newRec.addElement(rs.getString("adminUsername"));

                newRec.addElement(rs.getString("adminAge"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            admnTable.adminTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    //function-end****************************************************************
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        anameCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        aidCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        adageCheckBox = new javax.swing.JCheckBox();
        selectFields = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 51, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Select Admin Tables Fields");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Admin Username");

        anameCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Admin ID");

        aidCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        aidCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aidCheckBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Admin Age");

        adageCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        selectFields.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        selectFields.setText("Select ");
        selectFields.setBorder(null);
        selectFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFieldsActionPerformed(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selectFields, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(anameCheckBox)
                            .addComponent(aidCheckBox)
                            .addComponent(adageCheckBox))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(406, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aidCheckBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(anameCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(adageCheckBox)
                    .addComponent(jLabel4))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFields, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(271, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aidCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aidCheckBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_aidCheckBoxActionPerformed

    private void selectFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFieldsActionPerformed
        // TODO add your handling code here:

        if (aidCheckBox.isSelected()) {
            getAdminTableId();
        }
        if (anameCheckBox.isSelected()) {
            getAdminTableName();
        }
        if (adageCheckBox.isSelected()) {
            getAdminTableAge();
        }

        if (aidCheckBox.isSelected() && anameCheckBox.isSelected()) {
            getAdminTableIdName();
        }
        if (aidCheckBox.isSelected() && adageCheckBox.isSelected()) {
            getAdminTableIdAge();
        }

        if (anameCheckBox.isSelected() && adageCheckBox.isSelected()) {
            getAdminTableNameAge();
        }

        if (aidCheckBox.isSelected() && anameCheckBox.isSelected() && adageCheckBox.isSelected()) {
            getAdminTableIdNameAge();
        }
//        else {
//            JOptionPane.showMessageDialog(null, "Select any field", "Wrong Data", 2);
//
//        }
        admnTable.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_selectFieldsActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        TablesView tv = new TablesView();
        tv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SelectAdminFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectAdminFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectAdminFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectAdminFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectAdminFields().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox adageCheckBox;
    public javax.swing.JCheckBox aidCheckBox;
    private javax.swing.JCheckBox anameCheckBox;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton selectFields;
    // End of variables declaration//GEN-END:variables
}
