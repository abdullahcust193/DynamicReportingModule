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
public class SelectCourseFields extends javax.swing.JFrame {

    /**
     * Creates new form SelectCourseFields
     */
    private CourseTableForm cursTable;
    Connection conn = null;

    public SelectCourseFields() {
        initComponents();
    }

    //functions-Start*******************************************************************
    public void getTblCursId() {
        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }
    }

    public void getTblCursName() {
        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Course Name");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("courseName"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursCrdtHurs() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Credit Hours");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("courseCreditHours"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblStudId() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();

            column_heading.addElement("Student Id");

            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("studentId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCrsIdStudId() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");
            column_heading.addElement("Student Id");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));
                newRec.addElement(rs.getString("studentId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursCrdtHursId() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");
            column_heading.addElement("Credit Hours");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));
                newRec.addElement(rs.getString("courseCreditHours"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblIDCursName() {
        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");
            column_heading.addElement("Course Name");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));
                newRec.addElement(rs.getString("courseName"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCrdtHrsStudId() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Credit Hours");
            column_heading.addElement("Student Id");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseCreditHours"));
                newRec.addElement(rs.getString("studentId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblStudidCursName() {
        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Student Id");
            column_heading.addElement("Course Name");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("studentId"));
                newRec.addElement(rs.getString("courseName"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursCrdtHursName() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Name");
            column_heading.addElement("Credit Hours");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseName"));
                newRec.addElement(rs.getString("courseCreditHours"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursId_StudId_CrdtHrs() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");
            column_heading.addElement("Student Id");
            column_heading.addElement("Credit Hours");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));
                newRec.addElement(rs.getString("studentId"));
                newRec.addElement(rs.getString("courseCreditHours"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursId_StudId_Name() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");
            column_heading.addElement("Course Name");
            column_heading.addElement("Student Id");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));
                newRec.addElement(rs.getString("courseName"));
                newRec.addElement(rs.getString("studentId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursStudI_CrdHrs_Name() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Name");

            column_heading.addElement("Credit Hours");
            column_heading.addElement("Student Id");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseName"));

                newRec.addElement(rs.getString("courseCreditHours"));
                newRec.addElement(rs.getString("studentId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursId_CrdHrs_Name() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");
            column_heading.addElement("Course Name");

            column_heading.addElement("Credit Hours");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));
                newRec.addElement(rs.getString("courseName"));

                newRec.addElement(rs.getString("courseCreditHours"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTblCursAll() {

        cursTable = new CourseTableForm();
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `courses`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Course Id");
            column_heading.addElement("Course Name");
            column_heading.addElement("Credit Hours");
            column_heading.addElement("Student Id");

            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("courseId"));
                newRec.addElement(rs.getString("courseName"));
                newRec.addElement(rs.getString("courseCreditHours"));
                newRec.addElement(rs.getString("studentId"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            cursTable.courseTable.setModel(model);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        courseIdCheckBox = new javax.swing.JCheckBox();
        studentIdCheckBox = new javax.swing.JCheckBox();
        crsCrdtHrsCheckBox = new javax.swing.JCheckBox();
        crsNameCheckBox = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Course Id");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Student Id");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Course Credit Hours");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Course Name");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setText("Select");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(studentIdCheckBox)
                            .addComponent(crsCrdtHrsCheckBox)
                            .addComponent(crsNameCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(courseIdCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(courseIdCheckBox))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(studentIdCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(crsCrdtHrsCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(crsNameCheckBox))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Course Fileds");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(666, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        TablesView tv = new TablesView();
        tv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        if (courseIdCheckBox.isSelected()) {
            getTblCursId();
        }
        if (studentIdCheckBox.isSelected()) {
            getTblStudId();
        }
        if (crsNameCheckBox.isSelected()) {
            getTblCursName();
        }
        if (crsCrdtHrsCheckBox.isSelected()) {
            getTblCursCrdtHurs();
        }

        if (courseIdCheckBox.isSelected() && studentIdCheckBox.isSelected()) {
            getTblCrsIdStudId();
        }
        if (courseIdCheckBox.isSelected() && crsCrdtHrsCheckBox.isSelected()) {
            getTblCursCrdtHursId();
        }

        if (courseIdCheckBox.isSelected() && crsNameCheckBox.isSelected()) {
            getTblIDCursName();
        }

        if (studentIdCheckBox.isSelected() && crsCrdtHrsCheckBox.isSelected()) {
            getTblCrdtHrsStudId();
        }
        if (studentIdCheckBox.isSelected() && crsNameCheckBox.isSelected()) {
            getTblStudidCursName();
        }
        if (crsCrdtHrsCheckBox.isSelected() && crsNameCheckBox.isSelected()) {
            getTblCursCrdtHursName();
        }
        if (courseIdCheckBox.isSelected() && studentIdCheckBox.isSelected() && crsCrdtHrsCheckBox.isSelected()) {
            getTblCursId_StudId_CrdtHrs();
        }
        if (courseIdCheckBox.isSelected() && studentIdCheckBox.isSelected() && crsNameCheckBox.isSelected()) {
            getTblCursId_StudId_Name();
        }
        if (studentIdCheckBox.isSelected() && crsCrdtHrsCheckBox.isSelected() && crsNameCheckBox.isSelected()) {
            getTblCursStudI_CrdHrs_Name();
        }
        if (courseIdCheckBox.isSelected() && crsCrdtHrsCheckBox.isSelected() && crsNameCheckBox.isSelected()) {
            getTblCursId_CrdHrs_Name();
        }
        if (courseIdCheckBox.isSelected() && studentIdCheckBox.isSelected() && crsNameCheckBox.isSelected() && crsCrdtHrsCheckBox.isSelected()) {
            getTblCursAll();
        } 
//        else {
//            JOptionPane.showMessageDialog(null, "Select any field", "Wrong Data", 2);
//
//        }
        cursTable.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(SelectCourseFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectCourseFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectCourseFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectCourseFields.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectCourseFields().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox courseIdCheckBox;
    private javax.swing.JCheckBox crsCrdtHrsCheckBox;
    private javax.swing.JCheckBox crsNameCheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox studentIdCheckBox;
    // End of variables declaration//GEN-END:variables
}
