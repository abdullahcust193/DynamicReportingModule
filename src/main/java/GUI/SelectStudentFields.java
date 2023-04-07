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
public class SelectStudentFields extends javax.swing.JFrame {

    /**
     * Creates new form Table1
     */
    private StudentTableForm s;
    Connection conn = null;

    public SelectStudentFields() {
        initComponents();

    }

    public void getTableId() {
        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("ID");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }
    }

    public void getTableName() {
        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Student Name");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("name"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableRegNo() {
        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Registration No");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("registration_no"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableAge() {
        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Student Age");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdName() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("ID");
            column_heading.addElement("Name");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("name"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdAge() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("ID");
            column_heading.addElement("Age");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdRegno() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("ID");
            column_heading.addElement("Registration No");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("registration_no"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableNameRegno() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Name");
            column_heading.addElement("Registration No");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("name"));
                newRec.addElement(rs.getString("registration_no"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableNameAge() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Name");
            column_heading.addElement("Age");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("name"));
                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableAgeRegNo() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Registration No");
            column_heading.addElement("Age");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("registration_no"));
                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdNameRegno() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Id");
            column_heading.addElement("Name");
            column_heading.addElement("Registration No");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("name"));
                newRec.addElement(rs.getString("registration_no"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdRegAge() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Id");
            column_heading.addElement("Registration No");
            column_heading.addElement("Age");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("registration_no"));
                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableNameRegnoAge() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Name");
            column_heading.addElement("Registration No");
            column_heading.addElement("Age");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("name"));
                newRec.addElement(rs.getString("registration_no"));
                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdNameAge() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("ID");
            column_heading.addElement("Name");
            column_heading.addElement("Age");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("name"));

                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getAllTable() {

        s = new StudentTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Id");
            column_heading.addElement("Name");
            column_heading.addElement("Registration No");
            column_heading.addElement("Student Age");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("name"));
                newRec.addElement(rs.getString("registration_no"));
                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            s.studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nameCheckBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idCheckBox = new javax.swing.JCheckBox();
        regnoCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        ageCheckBox = new javax.swing.JCheckBox();
        selectFields = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Name");

        nameCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Registration No");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("ID");

        idCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        idCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCheckBoxActionPerformed(evt);
            }
        });

        regnoCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Age");

        ageCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

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
                        .addGap(36, 36, 36)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameCheckBox)
                            .addComponent(idCheckBox)
                            .addComponent(regnoCheckBox)
                            .addComponent(ageCheckBox))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(513, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(regnoCheckBox)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ageCheckBox)
                    .addComponent(jLabel4))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectFields, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(264, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Select Student Tables Fields");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(525, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void selectFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFieldsActionPerformed
        // TODO add your handling code here:

        if (idCheckBox.isSelected()) {
            getTableId();
        }
        if (nameCheckBox.isSelected()) {
            getTableName();
        }
        if (regnoCheckBox.isSelected()) {
            getTableRegNo();
        }
        if (ageCheckBox.isSelected()) {
            getTableAge();
        }
        if (idCheckBox.isSelected() && nameCheckBox.isSelected()) {
            getTableIdName();
        }
        if (idCheckBox.isSelected() && ageCheckBox.isSelected()) {
            getTableIdAge();
        }
        if (idCheckBox.isSelected() && regnoCheckBox.isSelected()) {
            getTableIdRegno();
        }

        if (nameCheckBox.isSelected() && regnoCheckBox.isSelected()) {
            getTableNameRegno();
        }

        if (ageCheckBox.isSelected() && regnoCheckBox.isSelected()) {
            getTableAgeRegNo();
        }
        if (nameCheckBox.isSelected() && ageCheckBox.isSelected()) {
            getTableNameAge();
        }
        if (idCheckBox.isSelected() && nameCheckBox.isSelected() && regnoCheckBox.isSelected()) {
            getTableIdNameRegno();
        }
        if (idCheckBox.isSelected() && regnoCheckBox.isSelected() && ageCheckBox.isSelected()) {
            getTableIdRegAge();
        }

        if (nameCheckBox.isSelected() && regnoCheckBox.isSelected() && ageCheckBox.isSelected()) {
            getTableNameRegnoAge();
        }

        if (idCheckBox.isSelected() && nameCheckBox.isSelected() && ageCheckBox.isSelected()) {
            getTableIdNameAge();
        }

        if (nameCheckBox.isSelected() && regnoCheckBox.isSelected() && ageCheckBox.isSelected() && idCheckBox.isSelected()) {
            getAllTable();
        }
//         else {
//            JOptionPane.showMessageDialog(null, "Select any field", "Wrong Data", 2);
//
//        }
        s.setVisible(true);
        this.setVisible(false);


    }//GEN-LAST:event_selectFieldsActionPerformed

    private void idCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCheckBoxActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_idCheckBoxActionPerformed

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
            java.util.logging.Logger.getLogger(SelectStudentFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectStudentFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectStudentFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectStudentFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectStudentFields().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox ageCheckBox;
    private javax.swing.JButton backBtn;
    public javax.swing.JCheckBox idCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JCheckBox nameCheckBox;
    public javax.swing.JCheckBox regnoCheckBox;
    private javax.swing.JButton selectFields;
    // End of variables declaration//GEN-END:variables
}
