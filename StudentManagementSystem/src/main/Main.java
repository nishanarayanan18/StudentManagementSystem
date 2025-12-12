package main;

import java.util.List;
import java.util.Scanner;

import model.Student;
import service.StudentService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        System.out.println("Database Connected Successfully!");

        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Students");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

            case 1:
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Gender: ");
                String gender = sc.nextLine();

                System.out.print("Course: ");
                String course = sc.nextLine();

                Student s = new Student(id, name, age, gender, course);
                service.addStudent(s);
                break;

            case 2:
                System.out.print("Enter ID to update: ");
                int uid = sc.nextInt();
                sc.nextLine();

                System.out.print("New Name: ");
                String uname = sc.nextLine();

                System.out.print("New Age: ");
                int uage = sc.nextInt();
                sc.nextLine();

                System.out.print("New Gender: ");
                String ugender = sc.nextLine();

                System.out.print("New Course: ");
                String ucourse = sc.nextLine();

                Student us = new Student(uid, uname, uage, ugender, ucourse);
                service.updateStudent(us);
                break;

            case 3:
                System.out.print("Enter ID to delete: ");
                int did = sc.nextInt();
                sc.nextLine();
                service.deleteStudent(did);
                break;

            case 4:
                List<Student> list = service.getStudents();
                for (Student st : list) {
                    System.out.println(st);
                }
                break;

            case 5:
                System.out.print("Enter ID to search: ");
                int sid = sc.nextInt();
                sc.nextLine();

                Student found = service.searchStudent(sid);
                if (found != null)
                    System.out.println(found);
                else
                    System.out.println("Student not found!");
                break;

            case 6:
                System.out.println("Exiting...");
                System.exit(0);
            }
        }
    }
}
