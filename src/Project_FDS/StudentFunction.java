
package Project_FDS;

import java.io.*;
import java.util.ArrayList;

public class StudentFunction {

    private ArrayList<StudentMain> students = new ArrayList<>();
    private final String filename = "Students_List.txt";

    public StudentFunction() {
        loadStudents();
    }

    // Add Student
    public void add(StudentMain student) {
        students.add(student);
        saveStudents();
        System.out.println("Student added successfully!");
    }

    // View Students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found!");
            return;
        }
        for (StudentMain student : students) {
            System.out.println(student);
        }
    }

    // Update
    public void update(int id, String name, int age, int rollno) {
        for (StudentMain student : students) {
            if (student.getId() == id) {
                student.setName(name);
                student.setAge(age);
                student.setRollno(rollno);
                saveStudents();

                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student ID not found!");
    }

    // Delete
    public void delete(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                saveStudents();
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student ID not found!");
    }
    // Save Students to File
    private void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            for (StudentMain student : students) {
                writer.write(
                        student.getId() + "|" +
                                student.getName() + "|" +
                                student.getAge() + "|" +
                                student.getRollno()
                );

                writer.newLine();
            }

            System.out.println("Records saved to file!");

        } catch (IOException e) {
            System.out.println("Error saving records!");
            e.printStackTrace();
        }
    }

    // Load Students from File
    private void loadStudents() {

        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File not found. Starting with empty records.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length == 4) {

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    int rollno = Integer.parseInt(data[3]);

                    StudentMain student = new StudentMain(
                            id, name, age, rollno
                    );

                    students.add(student);
                }
            }

            System.out.println("Student records loaded successfully!");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading records!");
            e.printStackTrace();
        }
    }
}
