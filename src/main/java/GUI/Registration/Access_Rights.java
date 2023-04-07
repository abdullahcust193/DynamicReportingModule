/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Registration;

/**
 *
 * @author chabd
 */
public class Access_Rights {

    public int id;
    public String module_name;

    public boolean canCreate(Role role) {
        if (role.getTitle().equals("Admin") || role.getTitle().equals("admin")) {
            return true;
        } 
        else {
            return false;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public boolean canEidt() {

        return true;
    }

    public boolean canDelete() {

        return true;
    }

    public boolean canView() {

        return true;
    }
}
