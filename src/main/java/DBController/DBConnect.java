/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBController;

import GUI.Registration.AdminPage;
import GUI.Registration.Loading;
import GUI.Registration.Role;
import GUI.Registration.User;
import GUI.Registration.UserPage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author chabd
 */
public class DBConnect {

    Connection conn;
    ResultSet res;
    PreparedStatement pst;

    public static Connection connect() throws SQLException {
        Connection con = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/login?useSSL=false&allowPublicKeyRetrieval=true", "root", "");

        } catch (Exception ex) {
            System.out.println("inter.DBConnect.connect()");
            JOptionPane.showConfirmDialog(null, ex);
        }

        return con;
    }

    public void checkUserCredentials(User user, Role role) {

        try {
            conn = (Connection) DBConnect.connect();
            pst = conn.prepareStatement("select * from admin where adminUsername=? and password=? ");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            res = pst.executeQuery();
            if (res.next()) {
                String r1 = res.getString("userType");
                String un = res.getString("adminUsername");
                if (role.getTitle().equals("Admin") && r1.equals("Admin") ) {
                    AdminPage adminpage = new AdminPage(un);
                    adminpage.setVisible(true);
//                  this.setVisible(false);
                }
                if (role.getTitle().equals("User") && r1.equals("user") ) {
                    UserPage usrpage = new UserPage(un);
                    usrpage.setVisible(true);
                    //this.setVisible(false);
                }
            } else {
                //JOptionPane.showMessageDialog(rootPane, "Username or Password Incorrect", "Waring", 2);
                System.out.println("\nUsername or Password Incorrect");
            }

        } catch (SQLException ex) {
            System.out.println("\nConnection Error:" + ex.getMessage());
        }

    }
}
