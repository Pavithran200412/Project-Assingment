
package Project_FDS;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StudentFunction studentFunction = new StudentFunction();

        while (true) {

            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Student Age: ");
                    int age = input.nextInt();
                    System.out.print("Enter Student Roll No: ");
                    int rollno = input.nextInt();
                    StudentMain student = new StudentMain(
                            id, name, age, rollno
                    );
                    studentFunction.add(student);
                    break;

                case 2:
                    studentFunction.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int updateId = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = input.nextLine();
                    System.out.print("Enter New Age: ");
                    int newAge = input.nextInt();
                    System.out.print("Enter New Roll No: ");
                    int newRollno = input.nextInt();
                    studentFunction.update(
                            updateId,
                            newName,
                            newAge,
                            newRollno
                    );
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteId = input.nextInt();
                    studentFunction.delete(deleteId);
                    break;

                case 5:
                    System.out.println("Thank you!");
                    input.close();
                    return;

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }
}
