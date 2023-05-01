/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DR_GUI;

import Classes.Student;
import Controller.StudentController;
import DBController.DBConnect;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author chabd
 */
public class SpecificReportTab extends javax.swing.JFrame {

    /**
     * Creates new form SpecificReportTab
     */
    private Student s;
    private Connection conn = null;
    ResultSet res;
    PreparedStatement pst;
    private String selectedDb;
    private String selectedTable;

    public SpecificReportTab() {
        initComponents();

        try {
            conn = (Connection) DBConnect.connect();
            databaseComboBox();
            updateTable();

        } catch (SQLException ex) {
            System.out.println("\n<- Connection Failed -> ");
            System.out.println("\nEroor:" + ex.getMessage());
        }

    }

    public void loadReport() throws SQLException {
    HashMap<String, Object> parameter = new HashMap<>();
    parameter.put("newID", txt_id.getText());
    panael.removeAll();
    panael.repaint();
    panael.revalidate();

    try {
        JasperDesign jd;
        jd = JRXmlLoader.load("C:\\Users\\hp\\Documents\\GitHub\\DynamicReporting\\DynamicReportingModule\\src\\main\\java\\Reports\\report3.jrxml");
        String sql = "SELECT `id`, `name`, `age`, `registration_no` FROM `home` WHERE id='" + txt_id.getText() + "'";
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jd.setQuery(newQuery);

        JasperReport jp = JasperCompileManager.compileReport(jd);
        JasperPrint jprint = JasperFillManager.fillReport(jp, parameter, conn);
        JRViewer viewReport = new JRViewer(jprint);

        // Set the preferred size of the panel
        int panelWidth = 1100; // Set the desired width
        int panelHeight = 800; // Set the desired height
        panael.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Use an appropriate layout manager to control the size of the report viewer
        panael.setLayout(new BorderLayout());
        panael.add(viewReport, BorderLayout.CENTER);

    } catch (JRException ex) {
        System.out.println("\nJasper Error: " + ex.getMessage());
    }
}


    public void updateTable() {

        try {

            int c;
            Statement st = conn.createStatement();
            res = st.executeQuery("SELECT count(ID) as count from home");
            res.next();
            int count = parseInt(res.getString("count"));
            if (count > 0) {

                res = st.executeQuery("select * from home");
                ResultSetMetaData rsd = res.getMetaData();
                c = rsd.getColumnCount();
                DefaultTableModel dft = (DefaultTableModel) table2.getModel();
                dft.setRowCount(0);

                while (res.next()) {

                    Vector v = new Vector();
                    for (int i = 1; i < c; i++) {

                        v.add(res.getString("id"));
                        v.add(res.getString("name"));
                        v.add(res.getString("age"));
                        v.add(res.getString("registration_no"));

                    }
                    dft.addRow(v);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void databaseComboBox() {

        try {

            String sql = "show databases";

            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();

            while (res.next()) {
                String db_name = res.getString("database");
                ComboBox_name.addItem(db_name);

            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }

    }
 public void viewHomeTable() {

        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `home`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Student Id");
            column_heading.addElement("Student Name");
            column_heading.addElement("Registration No");
            column_heading.addElement("Age");
            while (rs.next()) {
                Vector newRec = new Vector();

                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("name"));
                newRec.addElement(rs.getString("registration_no"));
                newRec.addElement(rs.getString("age"));

                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            table_m.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }
    }
    public void loginDb_tables() {

        try {
            DatabaseMetaData metaData;
            metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables("login", null, null, null);
            while (tables.next()) {
                String table_name = tables.getString("Table_NAME");
                ComboBox_table.addItem(table_name);
                System.out.println("Table name: " + tables.getString("Table_NAME"));

            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }

    }

   

    public void viewAdminTable() {

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
            table_m.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void viewCourseTable() {

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
            table_m.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void realEstateDb_tables() {

        try {
            DatabaseMetaData metaData;
            metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables("java_rst_db", null, null, null);
            while (tables.next()) {
                String table_name = tables.getString("Table_NAME");
                ComboBox_table.addItem(table_name);
                System.out.println("Table name: " + tables.getString("Table_NAME"));

            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }

    }

    public void viewREAdminTable() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_rst_db?useSSL=false&allowPublicKeyRetrieval=true", "root", "");
//            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `admins`");
            Vector row = new Vector();
            Vector column_heading = new Vector();
            column_heading.addElement("Admin Id");
            column_heading.addElement("Username");
            column_heading.addElement("Password");
            while (rs.next()) {
                Vector newRec = new Vector();
                newRec.addElement(rs.getString("id"));
                newRec.addElement(rs.getString("username"));
                newRec.addElement(rs.getString("password"));
                row.addElement(newRec);
            }
            DefaultTableModel model = new DefaultTableModel(row, column_heading);
            table_m.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

//    ****
    public void getTableId() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }
    }

    public void getTableName() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableRegNo() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableAge() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdName() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdAge() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdRegno() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableNameRegno() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableNameAge() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableAgeRegNo() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdNameRegno() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdRegAge() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableNameRegnoAge() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getTableIdNameAge() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void getAllTable() {

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
            studentTable.setModel(model);
        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

//    *****
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDelete = new javax.swing.JButton();
        mainFrame = new javax.swing.JInternalFrame();
        jTabbed1 = new javax.swing.JTabbedPane();
        registrationTab = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtregno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtage = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        nameSearch1 = new javax.swing.JTextField();
        IdTxt1 = new javax.swing.JTextField();
        nametxt1 = new javax.swing.JTextField();
        regnotxt1 = new javax.swing.JTextField();
        agetxt1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        asd = new javax.swing.JLabel();
        dsfsdf = new javax.swing.JLabel();
        sdf1 = new javax.swing.JLabel();
        findButton = new javax.swing.JButton();
        clearButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_m = new javax.swing.JTable();
        ComboBox_name = new javax.swing.JComboBox<>();
        ComboBox_table = new javax.swing.JComboBox<>();
        btnView = new javax.swing.JButton();
        j1 = new javax.swing.JLabel();
        j2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        studentRecordTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        generalReportTab = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        studChkbox = new javax.swing.JCheckBox();
        adminChkbox = new javax.swing.JCheckBox();
        courseChkbox = new javax.swing.JCheckBox();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        idCheckBox = new javax.swing.JCheckBox();
        nameCheckBox = new javax.swing.JCheckBox();
        regnoCheckBox = new javax.swing.JCheckBox();
        ageCheckBox = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        selectFields = new javax.swing.JButton();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        printStudentTab = new javax.swing.JPanel();
        panael = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        ReportBtn = new javax.swing.JButton();

        btnDelete.setBackground(new java.awt.Color(204, 204, 255));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(51, 0, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainFrame.setVisible(true);

        jTabbed1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbed1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbed1MouseClicked(evt);
            }
        });

        registrationTab.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Id");

        txtid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Name");

        txtname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Registration No");

        txtregno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Age");

        txtage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAdd.setBackground(new java.awt.Color(204, 204, 255));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(51, 0, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(204, 204, 255));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateButton.setForeground(new java.awt.Color(51, 0, 255));
        updateButton.setText("Update");
        updateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        btnDelete1.setBackground(new java.awt.Color(204, 204, 255));
        btnDelete1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete1.setForeground(new java.awt.Color(51, 0, 255));
        btnDelete1.setText("Delete");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(153, 204, 255));
        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton.setForeground(new java.awt.Color(0, 0, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtregno)
                                .addComponent(txtage))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtregno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(updateButton)
                    .addComponent(btnDelete1)
                    .addComponent(clearButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add New Student", jPanel4);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Enter your Registration No");

        IdTxt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IdTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdTxt1ActionPerformed(evt);
            }
        });

        nametxt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        regnotxt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        agetxt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Age");

        asd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        asd.setText("Registration No");

        dsfsdf.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dsfsdf.setText("Name");

        sdf1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sdf1.setText("Id");

        findButton.setBackground(new java.awt.Color(204, 204, 255));
        findButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        findButton.setForeground(new java.awt.Color(51, 0, 255));
        findButton.setText("FIND");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        clearButton1.setBackground(new java.awt.Color(153, 204, 255));
        clearButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton1.setForeground(new java.awt.Color(0, 0, 255));
        clearButton1.setText("Clear");
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(sdf1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dsfsdf, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asd)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nametxt1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(IdTxt1)
                    .addComponent(agetxt1)
                    .addComponent(regnotxt1)
                    .addComponent(nameSearch1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(findButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IdTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdf1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nametxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dsfsdf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regnotxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agetxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findButton)
                    .addComponent(clearButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("", jPanel3);

        jTabbedPane1.addTab("Find Student Record", jTabbedPane3);

        table_m.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        table_m.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Registration No", "Age"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_m.setFocusable(false);
        table_m.setGridColor(new java.awt.Color(51, 102, 255));
        table_m.setRowHeight(25);
        table_m.setSelectionBackground(new java.awt.Color(51, 153, 255));
        table_m.getTableHeader().setReorderingAllowed(false);
        table_m.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_mMouseClicked(evt);
            }
        });
        table_m.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table_mKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_mKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(table_m);

        ComboBox_name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ComboBox_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a database" }));
        ComboBox_name.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ComboBox_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboBox_nameMouseClicked(evt);
            }
        });
        ComboBox_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_nameActionPerformed(evt);
            }
        });

        ComboBox_table.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ComboBox_table.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a table", " " }));
        ComboBox_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboBox_tableMouseClicked(evt);
            }
        });
        ComboBox_table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_tableActionPerformed(evt);
            }
        });

        btnView.setBackground(new java.awt.Color(204, 204, 255));
        btnView.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnView.setForeground(new java.awt.Color(51, 0, 255));
        btnView.setText("View Table");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        j1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        j1.setForeground(new java.awt.Color(204, 0, 0));
        j1.setText("No Database is Selected");

        j2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        j2.setForeground(new java.awt.Color(204, 0, 0));
        j2.setText("No Table is Selected");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Select Database");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Select Table");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ComboBox_name, 0, 239, Short.MAX_VALUE)
                                    .addComponent(ComboBox_table, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(j1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(j2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnView)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBox_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ComboBox_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnView)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(475, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout registrationTabLayout = new javax.swing.GroupLayout(registrationTab);
        registrationTab.setLayout(registrationTabLayout);
        registrationTabLayout.setHorizontalGroup(
            registrationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        registrationTabLayout.setVerticalGroup(
            registrationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationTabLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbed1.addTab("Registeration", registrationTab);

        studentRecordTab.setBackground(new java.awt.Color(204, 204, 204));

        table2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Registration No", "Age"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.setFocusable(false);
        table2.setGridColor(new java.awt.Color(51, 102, 255));
        table2.setRowHeight(25);
        table2.setSelectionBackground(new java.awt.Color(51, 153, 255));
        table2.getTableHeader().setReorderingAllowed(false);
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        table2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table2KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table2);

        javax.swing.GroupLayout studentRecordTabLayout = new javax.swing.GroupLayout(studentRecordTab);
        studentRecordTab.setLayout(studentRecordTabLayout);
        studentRecordTabLayout.setHorizontalGroup(
            studentRecordTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1227, Short.MAX_VALUE)
        );
        studentRecordTabLayout.setVerticalGroup(
            studentRecordTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
        );

        jTabbed1.addTab("Student Records", studentRecordTab);

        generalReportTab.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Student Table");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Admin Table");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Course Table");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("1.");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("2.");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("3.");

        studChkbox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        studChkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studChkboxActionPerformed(evt);
            }
        });

        adminChkbox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studChkbox)
                            .addComponent(adminChkbox)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(courseChkbox)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(studChkbox)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(adminChkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(courseChkbox)))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Select Table", jPanel8);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        idCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        idCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCheckBoxActionPerformed(evt);
            }
        });

        nameCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        regnoCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        ageCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel15.setText("ID");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setText("Name");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel17.setText("Registration No");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel18.setText("Age");

        selectFields.setBackground(new java.awt.Color(255, 204, 204));
        selectFields.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        selectFields.setForeground(new java.awt.Color(204, 0, 0));
        selectFields.setText("Select ");
        selectFields.setBorder(null);
        selectFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFieldsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(nameCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(idCheckBox)
                            .addGap(31, 31, 31)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(regnoCheckBox)
                            .addComponent(ageCheckBox))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(selectFields, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idCheckBox)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(nameCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regnoCheckBox)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ageCheckBox)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addComponent(selectFields, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Select Student Table Fields", jPanel6);

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        studentTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Reg No", "Age"
            }
        ));
        jScrollPane3.setViewportView(studentTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Student Table", jPanel9);

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Print Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTabbedPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTabbedPane5))))
                .addContainerGap(447, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane4)
                    .addComponent(jTabbedPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        generalReportTab.addTab("", jPanel7);

        jTabbed1.addTab("Generate Report", generalReportTab);

        panael.setBackground(new java.awt.Color(204, 204, 204));
        panael.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panael.setPreferredSize(new java.awt.Dimension(1100, 800));

        javax.swing.GroupLayout panaelLayout = new javax.swing.GroupLayout(panael);
        panael.setLayout(panaelLayout);
        panaelLayout.setHorizontalGroup(
            panaelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1095, Short.MAX_VALUE)
        );
        panaelLayout.setVerticalGroup(
            panaelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
        );

        idLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        idLabel.setText("Enter ID");

        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        ReportBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ReportBtn.setText("Report");
        ReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout printStudentTabLayout = new javax.swing.GroupLayout(printStudentTab);
        printStudentTab.setLayout(printStudentTabLayout);
        printStudentTabLayout.setHorizontalGroup(
            printStudentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printStudentTabLayout.createSequentialGroup()
                .addGroup(printStudentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(printStudentTabLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReportBtn))
                    .addGroup(printStudentTabLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(panael, javax.swing.GroupLayout.PREFERRED_SIZE, 1101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        printStudentTabLayout.setVerticalGroup(
            printStudentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printStudentTabLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(printStudentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReportBtn))
                .addGap(18, 18, 18)
                .addComponent(panael, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbed1.addTab("Print Student Record", printStudentTab);

        javax.swing.GroupLayout mainFrameLayout = new javax.swing.GroupLayout(mainFrame.getContentPane());
        mainFrame.getContentPane().setLayout(mainFrameLayout);
        mainFrameLayout.setHorizontalGroup(
            mainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbed1)
        );
        mainFrameLayout.setVerticalGroup(
            mainFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainFrameLayout.createSequentialGroup()
                .addComponent(jTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Delete Button Code Here:


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jTabbed1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbed1MouseClicked
        // TODO add your handling code here:
        String sql_s = "SELECT * FROM `home`";
        try {

            PreparedStatement pst = conn.prepareStatement(sql_s);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table2.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3)});

            }

        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }
    }//GEN-LAST:event_jTabbed1MouseClicked

    private void ReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportBtnActionPerformed

        try {
            // TODO add your handling code here:
            loadReport();
        } catch (SQLException ex) {
            System.out.println("\nLoading Report Error:" + ex.getMessage());
        }
    }//GEN-LAST:event_ReportBtnActionPerformed

    private void selectFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFieldsActionPerformed
        // TODO add your handling code here:

        if (idCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableId();
        }
        if (nameCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableName();
        }
        if (regnoCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableRegNo();
        }
        if (ageCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableAge();
        }
        if (idCheckBox.isSelected() && nameCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableIdName();
        }
        if (idCheckBox.isSelected() && ageCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableIdAge();
        }
        if (idCheckBox.isSelected() && regnoCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableIdRegno();
        }

        if (nameCheckBox.isSelected() && regnoCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableNameRegno();
        }

        if (ageCheckBox.isSelected() && regnoCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableAgeRegNo();
        }
        if (nameCheckBox.isSelected() && ageCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableNameAge();
        }
        if (idCheckBox.isSelected() && nameCheckBox.isSelected() && regnoCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableIdNameRegno();
        }
        if (idCheckBox.isSelected() && regnoCheckBox.isSelected() && ageCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableIdRegAge();
        }

        if (nameCheckBox.isSelected() && regnoCheckBox.isSelected() && ageCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableNameRegnoAge();
        }

        if (idCheckBox.isSelected() && nameCheckBox.isSelected() && ageCheckBox.isSelected() && studChkbox.isSelected()) {
            getTableIdNameAge();
        }

        if (nameCheckBox.isSelected() && regnoCheckBox.isSelected() && ageCheckBox.isSelected() && idCheckBox.isSelected() && studChkbox.isSelected()) {
            getAllTable();
        }

    }//GEN-LAST:event_selectFieldsActionPerformed

    private void idCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idCheckBoxActionPerformed

    private void studChkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studChkboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studChkboxActionPerformed

    private void table2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table2KeyReleased

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // Mouse Clicked Table code here:
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        int selectedIndex = table2.getSelectedRow();
        txtid.setText(model.getValueAt(selectedIndex, 0).toString());
        txtname.setText(model.getValueAt(selectedIndex, 1).toString());
        txtage.setText(model.getValueAt(selectedIndex, 2).toString());
        txtregno.setText(model.getValueAt(selectedIndex, 3).toString());
    }//GEN-LAST:event_table2MouseClicked

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed

        // View Button code here:
        System.out.println("\n DB Selected " + j1.getText());
        System.out.println("\n Table Selected " + j2.getText());
        if (ComboBox_name.getSelectedItem().equals("login") && ComboBox_table.getSelectedItem().equals("home")) {
            viewHomeTable();
        } else if (ComboBox_name.getSelectedItem().equals("login") && ComboBox_table.getSelectedItem().equals("admin")) {
            viewAdminTable();
        } else if (ComboBox_name.getSelectedItem().equals("login") && ComboBox_table.getSelectedItem().equals("courses")) {
            viewCourseTable();
        } else if (ComboBox_name.getSelectedItem().equals("java_rst_db") && ComboBox_table.getSelectedItem().equals("admins")) {
            viewREAdminTable();
        } else {
            DefaultTableModel model = (DefaultTableModel) table_m.getModel();
            model.setRowCount(0);

        }
    }//GEN-LAST:event_btnViewActionPerformed

    private void ComboBox_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_tableActionPerformed
        // TODO add your handling code here:
        selectedTable = ComboBox_table.getSelectedItem().toString();
        j2.setText(selectedTable);

    }//GEN-LAST:event_ComboBox_tableActionPerformed

    private void ComboBox_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboBox_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBox_tableMouseClicked

    private void ComboBox_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_nameActionPerformed
        // TODO add your handling code here:
        selectedDb = ComboBox_name.getSelectedItem().toString();
        j1.setText(selectedDb);

        if (ComboBox_name.getSelectedItem().equals("login")) {
            loginDb_tables();
        } else if (ComboBox_name.getSelectedItem().equals("java_rst_db")) {
            realEstateDb_tables();
        } else {
            ComboBox_table.removeAllItems();

        }

    }//GEN-LAST:event_ComboBox_nameActionPerformed

    private void ComboBox_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboBox_nameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBox_nameMouseClicked

    private void table_mKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_mKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_table_mKeyReleased

    private void table_mKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_mKeyPressed
        // TODO add your handling code here:

        //        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP) {
        //
        //
        //
        //        }
    }//GEN-LAST:event_table_mKeyPressed

    private void table_mMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_mMouseClicked
        // Mouse Clicked Table code here:
        DefaultTableModel model = (DefaultTableModel) table_m.getModel();
        int selectedIndex = table_m.getSelectedRow();
        txtid.setText(model.getValueAt(selectedIndex, 0).toString());
        txtname.setText(model.getValueAt(selectedIndex, 1).toString());
        txtage.setText(model.getValueAt(selectedIndex, 3).toString());
        txtregno.setText(model.getValueAt(selectedIndex, 2).toString());
    }//GEN-LAST:event_table_mMouseClicked

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        // TODO add your handling code here:

        // Clear Button code here:
        IdTxt1.setText("");
        nametxt1.setText("");
        agetxt1.setText("");
        regnotxt1.setText("");
        nameSearch1.requestFocus();
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        // Find Button code here:
        //Code for Search BY reg no
        try {

            String nameSql = "select * from home where registration_no = ?";
            PreparedStatement pst_2 = conn.prepareStatement(nameSql);
            pst_2.setString(1, nameSearch1.getText());
            ResultSet rs2 = pst_2.executeQuery();

            if (rs2.next()) {
                JOptionPane.showMessageDialog(this, "Record Found.");
                nameSearch1.setText("");
                IdTxt1.setText(rs2.getString("id"));
                nametxt1.setText(rs2.getString("name"));
                regnotxt1.setText(rs2.getString("registration_no"));
                agetxt1.setText(rs2.getString("age"));
            } else {
                JOptionPane.showMessageDialog(this, "Record Not Found.");
                nameSearch1.setText("");
                nameSearch1.requestFocus();
                IdTxt1.setText("");
                nametxt1.setText("");
                regnotxt1.setText("");
                agetxt1.setText("");

            }

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }//GEN-LAST:event_findButtonActionPerformed

    private void IdTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdTxt1ActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // Clear Button code here:
        txtid.setText("");
        txtname.setText("");
        txtage.setText("");
        txtregno.setText("");
        txtid.requestFocus();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // Delete Button Code Here:

        DefaultTableModel model = (DefaultTableModel) table_m.getModel();
        int selectedIndex = table_m.getSelectedRow();
        int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
        int dialogueResult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogueResult == JOptionPane.YES_NO_OPTION) {
            //Controller Class Object
            StudentController studCntrllr = new StudentController();
            //Call Controller AddToModel Function
            studCntrllr.deleteToModel(id);
            JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
            txtid.setText("");
            txtname.setText("");
            txtregno.setText("");
            txtage.setText("");
            txtid.requestFocus();

        }
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // Update Buuton code here:
        DefaultTableModel model = (DefaultTableModel) table_m.getModel();
        int selectedIndex = table_m.getSelectedRow();
        int i = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());

        String valueId, valueName, valueRegno, valueAge;
        valueId = txtid.getText();
        valueName = txtname.getText();
        valueRegno = txtregno.getText();
        valueAge = txtage.getText();

        //Convert User Input To Integer Data
        int _id = Integer.parseInt(valueId);
        int _age = Integer.parseInt(valueAge);
        //Controller Class Object
        StudentController studCntrllr = new StudentController();
        //Call Controller AddToModel Function
        studCntrllr.updateToModel(_id, valueName, valueRegno, _age, i);

        JOptionPane.showMessageDialog(this, "Record Update Successfully.");
        txtid.setText("");
        txtname.setText("");
        txtregno.setText("");
        txtage.setText("");
        txtid.requestFocus();
        updateTable();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        // ADD BUTTON code here:
        String id = txtid.getText();
        String name = txtname.getText();
        String age = txtage.getText();
        String registration_no = txtregno.getText();
        //Convert User Input To Integer Data
        int _id = Integer.parseInt(id);
        int _age = Integer.parseInt(age);
        //Controller Class Object
        StudentController studCntrllr = new StudentController();
        //Call Controller AddToModel Function

        studCntrllr.addToModel(_id, name, registration_no, _age);
        //Student studModel = new Student();
        JOptionPane.showMessageDialog(this, "Records Added Successfully");
        txtid.setText("");
        txtname.setText("");
        txtage.setText("");
        txtregno.setText("");
        txtid.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(SpecificReportTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpecificReportTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpecificReportTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpecificReportTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpecificReportTab().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBox_name;
    private javax.swing.JComboBox<String> ComboBox_table;
    private javax.swing.JTextField IdTxt1;
    private javax.swing.JButton ReportBtn;
    private javax.swing.JCheckBox adminChkbox;
    private javax.swing.JCheckBox ageCheckBox;
    private javax.swing.JTextField agetxt1;
    private javax.swing.JLabel asd;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnView;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clearButton1;
    private javax.swing.JCheckBox courseChkbox;
    private javax.swing.JLabel dsfsdf;
    private javax.swing.JButton findButton;
    private javax.swing.JTabbedPane generalReportTab;
    public javax.swing.JCheckBox idCheckBox;
    private javax.swing.JLabel idLabel;
    public javax.swing.JLabel j1;
    public javax.swing.JLabel j2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbed1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JInternalFrame mainFrame;
    private javax.swing.JCheckBox nameCheckBox;
    private javax.swing.JTextField nameSearch1;
    private javax.swing.JTextField nametxt1;
    private javax.swing.JPanel panael;
    private javax.swing.JPanel printStudentTab;
    private javax.swing.JPanel registrationTab;
    private javax.swing.JCheckBox regnoCheckBox;
    private javax.swing.JTextField regnotxt1;
    private javax.swing.JLabel sdf1;
    private javax.swing.JButton selectFields;
    private javax.swing.JCheckBox studChkbox;
    private javax.swing.JPanel studentRecordTab;
    public javax.swing.JTable studentTable;
    private javax.swing.JTable table2;
    public javax.swing.JTable table_m;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txtage;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtregno;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
