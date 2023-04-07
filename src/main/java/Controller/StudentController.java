/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Student;
import DBController.Queries;

/**
 *
 * @author chabd
 */
public class StudentController {

    private Student std;
    private Queries query;

    public int cid;
    public String cname;
    public String cregno;
    public int cage;
    
//************************************CRUD Methods*************************************
    
    public void addToModel(int id, String nam, String reg, int ag) {
        System.out.println("AddToModel Function Call");
        std = new Student();
        std.setId(id);
        std.setName(nam);
        std.setRegno(reg);
        std.setAge(ag);
        System.out.println("ID: " + std.getId() + " name: " + std.getName() + " regno : " + std.getRegno()
                + " age: " + std.getAge());

        query = new Queries();
        query.insertData(id, nam, ag, reg);
//        df.insertData(cid, cname, cage, cregno);
    }

    public void updateToModel(int id, String nam, String reg, int ag, int i) {
        System.out.println("UpdateToModel Function Call");
        addToModel(id, nam, reg, ag);

        System.out.println("ID: " + std.getId() + " name: " + std.getName() + " regno : " + std.getRegno()
                + " age: " + std.getAge());

        query = new Queries();
        query.updateData(id, nam, ag, reg, i);

    }

    public void deleteToModel(int i) {
        System.out.println("DeleteToModel Function Call");
        std = new Student();
        std.setId(0);
        std.setName("");
        std.setRegno("");
        std.setAge(0);

        System.out.println("ID: " + std.getId() + " name: " + std.getName() + " regno : " + std.getRegno()
                + " age: " + std.getAge());

        query = new Queries();
        query.deleteData(i);

    }
    
//*************************Methods To Get First Record From DB*************************
    public int getFirstId() {
        query = new Queries();
        int dbid = query.getFirst_dbID();
        return dbid;

    }

    public String getFirstName() {
        query = new Queries();
        String dbname = query.getFirst_dbName();
        return dbname;

    }

    public String getFirstRegno() {
        query = new Queries();
        String dbregno = query.getFirst_Regno();
        return dbregno;

    }

    public int getFirstAge() {
        query = new Queries();
        int dbage = query.getFirst_Age();
        return dbage;
    }
    
    
    
//*************************Methods To Get Last Record From DB**************************
    public int getLastId() {
        query = new Queries();
        int dbid = query.getLast_dbID();
        return dbid;

    }

    public String getLastName() {
        query = new Queries();
        String dbname = query.getLast_dbName();
        return dbname;

    }

    public String getLastRegno() {
        query = new Queries();
        String dbregno = query.getLast_dbRegno();
        return dbregno;

    }

    public int getLastAge() {
        query = new Queries();
        int dbage = query.getLast_dbAge();
        return dbage;
    }
    
    
//*************************Methods To Get Next Record From DB**************************
    public int getNextId() {
        query = new Queries();
        int dbid = query.getNext_dbID();
        return dbid;

    }

    public String getNextName() {
        query = new Queries();
        String dbname = query.getNext_dbName();
        return dbname;

    }

    public String getNextRegno() {
        query = new Queries();
        String dbregno = query.getNext_Regno();
        return dbregno;

    }

    public int getNextAge() {
        query = new Queries();
        int dbage = query.getNext_Age();
        return dbage;
    }
    
    
//*************************Methods To Get Previous Record From DB**************************
    public int getPreviousId() {
        query = new Queries();
        int dbid = query.getPrevious_dbID();
        return dbid;

    }

    public String getPreviousName() {
        query = new Queries();
        String dbname = query.getPrevious_dbName();
        return dbname;

    }

    public String getPreviousRegno() {
        query = new Queries();
        String dbregno = query.getPrevious_dbRegno();
        return dbregno;

    }

    public int getPreviousAge() {
        query = new Queries();
        int dbage = query.getPrevious_dbAge();
        return dbage;
    }
    
    

}
