import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private double marks;

    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Marks: " + marks;
    }
}

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    String name = details[0].trim();
                    int rollNumber = Integer.parseInt(details[1].trim());
                    double marks = Double.parseDouble(details[2].trim());
                    students.add(new Student(name, rollNumber, marks));
                }
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }

    public void searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student Found: " + student);
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void updateStudent(int rollNumber, double newMarks) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                student.setMarks(newMarks);
                System.out.println("Marks updated successfully: " + student);
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void deleteStudent(int rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getRollNumber() == rollNumber) {
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("Student Records:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the CSV file: ");
        String filePath = scanner.nextLine();
        manager.loadFromCSV(filePath);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Display all students");
            System.out.println("2. Search for a student");
            System.out.println("3. Update a student's marks");
            System.out.println("4. Delete a student record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.displayAllStudents();
                    break;
                case 2:
                    System.out.print("Enter roll number to search: ");
                    int searchRoll = scanner.nextInt();
                    manager.searchStudent(searchRoll);
                    break;
                case 3:
                    System.out.print("Enter roll number to update: ");
                    int updateRoll = scanner.nextInt();
                    System.out.print("Enter new marks: ");
                    double newMarks = scanner.nextDouble();
                    manager.updateStudent(updateRoll, newMarks);
                    break;
                case 4:
                    System.out.print("Enter roll number to delete: ");
                    int deleteRoll = scanner.nextInt();
                    manager.deleteStudent(deleteRoll);
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
