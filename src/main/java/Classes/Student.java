package Classes;

public class Student {

    public int id;
    public String name;
    public String regno;
    public int age;

    public Student() {
        this.id = 0;
        this.name = "";
        this.regno = "";
        this.age = 0;
    }

    public Student(int id, String name, String regno, int age) {
        this.id = id;
        this.name = name;
        this.regno = regno;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        System.out.println("Id Is Set in Model Class = " + id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Name Is Set in Model Class = " + name);
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
        System.out.println("Regno Is Set in Model Class = " + regno);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        System.out.println("age Is Set in Model Class = " + age);
    }

}
