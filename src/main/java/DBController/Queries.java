/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.st;

/**
 *
 * @author chabd
 */
public class Queries {

    Connection conn = null;
    
    int myId;

    public void insertData(int id, String name, int age, String regno) {
        try {

            conn = (Connection) DBConnect.connect();
            String sql_add = "INSERT INTO `home`(`id`, `name`, `age`, `registration_no`) VALUES ('" + id + "','" + name + "','" + age + "','" + regno + "')";
            PreparedStatement pst = conn.prepareStatement(sql_add);
            pst.execute();
//       JOptionPane.showMessageDialog(this, "Records Added Successfully");
            System.out.println("\nRecords Added Successfully.");
        } catch (SQLException ex) {
            System.out.println("\n<- Connection Failed -> ");
            System.out.println("\nEroor:" + ex.getMessage());
//            JOptionPane.showMessageDialog(this, ex);
        }

    }

    public void updateData(int id, String name, int age, String regno, int i) {

        try {
            conn = (Connection) DBConnect.connect();
            String sql_up = "Update home Set id='" + id + "',name='" + name + "',age='" + age + "',registration_no='" + regno + "' Where id=" + i;
            PreparedStatement pst = conn.prepareStatement(sql_up);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }

    }

    public void deleteData(int i) {
        try {
            conn = (Connection) DBConnect.connect();
            String sql_del = "DELETE FROM home where id=" + i;
            PreparedStatement pst = conn.prepareStatement(sql_del);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println("\n Error:" + ex.getMessage());
        }
    }
    
   //*************************Methods To Get First Record From Database***********************

    public int getFirst_dbID() {
        int db_id = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.first();
            db_id = rs.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db_id;
    }

    public String getFirst_dbName() {
        String db_name = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.first();
            db_name = rs.getString(2);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_name;
    }

    public int getFirst_Age() {

        int db_age = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.first();
            db_age = rs.getInt(3);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_age;
    }

    public String getFirst_Regno() {
        String db_regno = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.first();
            db_regno = rs.getString(4);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_regno;

    }

//*************************Methods To Get Last Record From Database***********************
    public int getLast_dbID() {
        int db_id = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.last();
            db_id = rs.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db_id;
    }

    public String getLast_dbName() {
        String db_name = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.last();
            db_name = rs.getString(2);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_name;
    }

    public int getLast_dbAge() {

        int db_age = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.last();
            db_age = rs.getInt(3);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_age;
    }

    public String getLast_dbRegno() {
        String db_regno = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");
            rs.last();
            db_regno = rs.getString(4);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_regno;

    }

//*************************Methods To Get Next Record From Database***********************
    public int getNext_dbID() {
        int db_idi = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");

            if (!rs.isLast()) {

                rs.next();
                db_idi = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db_idi;
    }

    public String getNext_dbName() {
        String db_name = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");

            if (!rs.isLast()) {
                rs.next();
                db_name = rs.getString(2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_name;
    }

    public int getNext_Age() {

        int db_age = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");

            if (!rs.isLast()) {

                rs.next();
                db_age = rs.getInt(3);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_age;
    }

    public String getNext_Regno() {
        String db_regno = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");

            if (!rs.isLast()) {

                rs.next();
                db_regno = rs.getString(4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_regno;

    }

//*************************Methods To Get Previous Record From Database***********************
    public int getPrevious_dbID() {
        int db_id = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = st.executeQuery("Select * from home");
            if (!rs.isFirst()) {

                rs.previous();
                db_id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db_id;
    }

    public String getPrevious_dbName() {
        String db_name = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");

            if (!rs.isFirst()) {
                rs.previous();
                db_name = rs.getString(2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_name;
    }

    public int getPrevious_dbAge() {

        int db_age = 0;
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");

            if (!rs.isFirst()) {

                rs.previous();
                db_age = rs.getInt(3);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_age;
    }

    public String getPrevious_dbRegno() {
        String db_regno = "";
        try {
            conn = (Connection) DBConnect.connect();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("Select * from home");

            if (!rs.isFirst()) {

                rs.previous();
                db_regno = rs.getString(4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db_regno;

    }

}
