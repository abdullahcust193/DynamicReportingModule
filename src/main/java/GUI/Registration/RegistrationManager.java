/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Registration;

import DBController.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author chabd
 */
public class RegistrationManager {

    private DBConnect call_db;
    private Access_Rights right;

    public void manageUsers(User user, Role role) {
        call_db = new DBConnect();
        if (role.getTitle().equals("Admin") || role.getTitle().equals("admin")) {
            call_db.checkUserCredentials(user, role);
            right = new Access_Rights();

            if (right.canCreate(role) == true) {

                System.out.println("\nAdmin has a Create Rights");

            } else {

                System.out.println("\nAdmin has no Create Rights");
            }

        }
        if (role.getTitle().equals("User") || role.getTitle().equals("user")) {
            call_db.checkUserCredentials(user, role);
            right = new Access_Rights();

            if (right.canCreate(role) == true) {

                System.out.println("\nUser has a Create Rights");

            } else {

                System.out.println("\nUser has no Create Rights");
            }

        } else {

            System.out.println("\nUser Type Not Selected. Please Select First ");

        }

    }

    public void manageRoles() {
    }

}
