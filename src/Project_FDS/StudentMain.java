package Project_FDS;

public class StudentMain {
    private int id;
    private String name;
    private int age;
    private int rollno;

    public StudentMain(int id, String name, int age, int rollno) {
        setId(id);
        setName(name);
        setAge(age);
        setRollno(rollno);
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getRollno() {
        return rollno;
    }


    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        this.id = id;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()
                || name.contains("|")) {
            throw new IllegalArgumentException("Invalid student name.");
        }
        this.name = name.trim();
    }
    public void setAge(int age) {
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120.");
        }
        this.age = age;
    }
    public void setRollno(int rollno) {
        if (rollno <= 0) {
            throw new IllegalArgumentException("Roll number must be positive.");
        }
        this.rollno = rollno;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Roll No: " + rollno;
    }
}
