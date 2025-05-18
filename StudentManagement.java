import java.io.*;
import java.util.*;

class Student {
    String name;
    String roll;
    String grade;

    Student(String roll, String name, String grade) {
        this.roll = roll;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-5s", roll, name, grade);
    }
}

class StudentManagement {
    static final String FILE_NAME = "students.txt";
    static Scanner sc = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudents();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: removeStudent(); break;
                case 3: editStudent(); break;
                case 4: searchStudent(); break;
                case 5: displayAll(); break;
                case 6: saveStudents(); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }


    static void addStudent() {
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();
        if (getStudentByRoll(roll) != null) {
            System.out.println("Roll number already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        if (roll.isEmpty() || name.isEmpty() || grade.isEmpty()) {
            System.out.println("Invalid input. Fields cannot be empty.");
            return;
        }

        students.add(new Student(roll, name, grade));
        System.out.println("Student added successfully.");
    }

    static void removeStudent() {
        System.out.print("Enter Roll No to remove: ");
        String roll = sc.nextLine();
        Student s = getStudentByRoll(roll);
        if (s != null) {
            students.remove(s);
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void editStudent() {
        System.out.print("Enter Roll No to edit: ");
        String roll = sc.nextLine();
        Student s = getStudentByRoll(roll);
        if (s != null) {
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new grade: ");
            String grade = sc.nextLine();

            if (name.isEmpty() || grade.isEmpty()) {
                System.out.println("Invalid input.");
                return;
            }

            s.name = name;
            s.grade = grade;
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void searchStudent() {
        System.out.print("Enter Roll No to search: ");
        String roll = sc.nextLine();
        Student s = getStudentByRoll(roll);
        if (s != null) {
            System.out.println("\nStudent Found:");
            System.out.printf("%-10s %-20s %-5s\n", "Roll", "Name", "Grade");
            System.out.println(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    static void displayAll() {
        System.out.printf("\n%-10s %-20s %-5s\n", "Roll", "Name", "Grade");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static Student getStudentByRoll(String roll) {
        for (Student s : students) {
            if (s.roll.equals(roll)) return s;
        }
        return null;
    }

    static void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s{2,}");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            // File may not exist on first run
        }
    }

    static void saveStudents() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            pw.printf("%-10s %-20s %-5s\n", "Roll", "Name", "Grade");
            for (Student s : students) {
                pw.println(s);
            }
            System.out.println("Student data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }
}
