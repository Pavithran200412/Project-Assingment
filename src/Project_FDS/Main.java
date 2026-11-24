package Project_FDS;

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    // Read valid integer
    private static int readInteger(String message) {

        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer!");
            }
        }
    }

    // Read valid name
    private static String readName(String message) {

        while (true) {
            System.out.print(message);
            String name = input.nextLine().trim();
            if (name.matches("[\\p{L}]+([ '-][\\p{L}]+)*")) {
                return name;
            }
            System.out.println("Invalid name! Enter letters only.");
        }
    }

    // Read valid age
    private static int readAge(String message) {

        while (true) {
            int age = readInteger(message);
            if (age >= 1 && age <= 120) {
                return age;
            }
            System.out.println("Age must be between 1 and 120!");
        }
    }

    // Read unique Student ID
    private static int readUniqueId(StudentFunction sf) {

        while (true) {
            int id = readInteger("Enter Student ID: ");
            if (id <= 0) {
                System.out.println("ID must be positive!");
            } else if (sf.isDuplicateId(id)) {
                System.out.println("Student ID already exists! Try another.");
            } else {
                return id;
            }
        }
    }

    // Read unique Roll Number
    private static int readUniqueRollno(StudentFunction sf) {

        while (true) {
            int rollno = readInteger("Enter Student Roll No: ");
            if (rollno <= 0) {
                System.out.println("Roll number must be positive!");
            } else if (sf.isDuplicateRollno(rollno)) {
                System.out.println("Roll number already exists! Try another.");
            } else {
                return rollno;
            }
        }
    }

    private static int readUniqueRollno(
            StudentFunction sf, int studentId) {
        while (true) {
            int rollno = readInteger("Enter Student Roll No: ");
            if (rollno <= 0) {
                System.out.println("Roll number must be positive!");
            }
            else if (sf.isDuplicateRollno(rollno, studentId)) {
                System.out.println("Roll number already exists! Try another.");
            }
            else {
                return rollno;
            }
        }
    }

    public static void main(String[] args) {

        StudentFunction studentFunction = new StudentFunction();
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            int choice = readInteger("Enter Choice: ");

            switch (choice) {

                // ADD STUDENT
                case 1:
                    int id = readUniqueId(studentFunction);
                    String name = readName("Enter Student Name: ");
                    int age = readAge("Enter Student Age: ");
                    int rollno = readUniqueRollno(studentFunction);
                    try {
                        StudentMain student = new StudentMain(id, name, age, rollno);
                        studentFunction.add(student);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // VIEW STUDENTS
                case 2:
                    studentFunction.viewStudents();
                    break;

                // UPDATE STUDENT
                case 3:
                    int updateId = readInteger("Enter Student ID: ");
                    String newName = readName("Enter New Name: ");
                    int newAge = readAge("Enter New Age: ");
                    int newRollno = readUniqueRollno(studentFunction, updateId);
                    studentFunction.update(updateId, newName, newAge, newRollno);
                    break;

                // DELETE STUDENT
                case 4:
                    int deleteId = readInteger("Enter Student ID to Delete: ");
                    studentFunction.delete(deleteId);
                    break;

                // EXIT
                case 5:
                    System.out.println("Thank you for using the system!");
                    input.close();
                    return;
                default:
                    System.out.println("Wrong choice! Select 1 to 5.");
            }
        }
    }
}
