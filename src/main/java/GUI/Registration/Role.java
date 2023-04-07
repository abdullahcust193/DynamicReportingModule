/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Registration;

/**
 *
 * @author chabd
 */
public class Role  {

    private String title;
    public Access_Rights rights;

    public Access_Rights getRights() {
        return rights;
    }

    public void setRights(Access_Rights rights) {
        this.rights = rights;
    }

    public Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
