package Project_FDS;

public class StudentMain {
    private int id;
    private String name;
    private int age;
    private int rollno;

    public StudentMain(int id, String name, int age, int rollno){
        this.id = id;
        this.name = name;
        this.age = age;
        this.rollno = rollno;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getRollno(){
        return rollno;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setRollno(int rollno){
        this.rollno = rollno;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Age: " + age +
                ", Roll No: " + rollno;
    }

}
