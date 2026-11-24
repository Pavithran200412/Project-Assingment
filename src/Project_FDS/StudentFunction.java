package Project_FDS;

import java.io.*;
import java.util.ArrayList;

public class StudentFunction {

    private ArrayList<StudentMain> students = new ArrayList<>();
    private final String filename = "N:\\Project-Assingment\\src\\Project_FDS\\Students_List.txt";

    public StudentFunction() {
        File file = new File(filename);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        loadStudents();
    }

    // Check duplicate ID
    public boolean isDuplicateId(int id) {
        for (StudentMain student : students) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // Check duplicate roll number while updating
    public boolean isDuplicateRollno(int rollno, int studentId) {
        for (StudentMain student : students) {
            if (student.getRollno() == rollno
                    && student.getId() != studentId) {
                return true;
            }
        }
        return false;
    }

    //Check duplicate Roll no
    public boolean isDuplicateRollno(int rollno) {
        for (StudentMain student : students) {
            if (student.getRollno() == rollno) {
                return true;
            }
        }
        return false;
    }

    // Add Student
    public void add(StudentMain student) {
        if (isDuplicateId(student.getId())) {
            System.out.println("Student ID already exists!");
            return;
        }
        if (isDuplicateRollno(student.getRollno())) {
            System.out.println("Roll number already exists!");
            return;
        }
        students.add(student);
        if (saveStudents()) {
            System.out.println("Student added successfully!");
        } else {
            students.remove(student);
            System.out.println("Add failed. Changes rolled back.");
        }
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

    // Update Student
    public void update(int id, String name, int age, int rollno) {
        for (StudentMain student : students) {
            if (student.getId() == id) {
                String oldName = student.getName();
                int oldAge = student.getAge();
                int oldRollno = student.getRollno();
                try {
                    student.setName(name);
                    student.setAge(age);
                    student.setRollno(rollno);
                    if (saveStudents()) {
                        System.out.println("Student updated successfully!");
                    } else {
                        student.setName(oldName);
                        student.setAge(oldAge);
                        student.setRollno(oldRollno);
                        System.out.println("Update failed. Changes rolled back.");
                    }
                } catch (IllegalArgumentException e) {
                    student.setName(oldName);
                    student.setAge(oldAge);
                    student.setRollno(oldRollno);
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
        System.out.println("Student ID not found!");
    }

    // Delete Student
    public void delete(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                StudentMain removedStudent = students.remove(i);
                if (saveStudents()) {
                    System.out.println("Student deleted successfully!");
                } else {
                    students.add(i, removedStudent);
                    System.out.println("Delete failed. Changes rolled back.");
                }
                return;
            }
        }
        System.out.println("Student ID not found!");
    }

    // Save Students
    private boolean saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (StudentMain student : students) {

                writer.write(student.getId() + "|" + student.getName() + "|" + student.getAge() + "|" + student.getRollno());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
            return false;
        }
    }

    // Load Students
    private void loadStudents() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found. Starting with empty records.");
            return;
        }
        int loaded = 0;
        int skipped = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    String[] data = line.split("\\|", -1);
                    if (data.length != 4) {
                        throw new IllegalArgumentException("Invalid format");
                    }
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    int age = Integer.parseInt(data[2].trim());
                    int rollno = Integer.parseInt(data[3].trim());
                    StudentMain student = new StudentMain(id, name, age, rollno);
                    if (isDuplicateId(id)) {
                        throw new IllegalArgumentException("Duplicate ID");
                    }
                    students.add(student);
                    loaded++;
                } catch (IllegalArgumentException e) {
                    skipped++;
                    System.out.println("Skipping invalid record at line " + lineNumber + ": " + e.getMessage());
                }
            }
            System.out.println("Records loaded: " + loaded);
            System.out.println("Invalid records skipped: " + skipped);
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
    }
}
