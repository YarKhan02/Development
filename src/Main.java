import org.hibernate.Transaction;
import org.hibernate.Session;

import model.Course;
import model.Student;

import java.io.IOException;
import java.sql.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

public class Main {
    public static void create_table(Connection conn, String file_name) throws IOException, SQLException {
        String sql = new String(Files.readAllBytes((Paths.get("src", "sql", file_name + ".sql"))));

        String[] statements = sql.split(";");
        try (Statement stmt = conn.createStatement()) {
            for (String statement : statements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    stmt.execute(statement);
                    // System.out.println("Executed: " + statement);
                }
            }
        }

        System.out.println("SQL file executed successfully.\n");
    }

    public static void insert_data(Connection conn, String file_name, List<Object> params) throws IOException, SQLException {
        String sql = new String(Files.readAllBytes(Paths.get("src", "sql", file_name + ".sql"))).trim();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i)); // generic binding
            }
            pstmt.executeUpdate();
            System.out.println("Data inserted in " + file_name + "\n");
        }
    }

    public static void fetch_record(Connection conn, String file_name) throws IOException, SQLException {
        String sql = new String(Files.readAllBytes(Paths.get("src", "sql", file_name + ".sql"))).trim();

        try (Statement stmt = conn.createStatement()) {
            var record = stmt.executeQuery(sql);
            ResultSetMetaData metaData = record.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (record.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = record.getObject(i);
                    System.out.printf("%s: %s\n", columnName, value);
                }
            }
            System.out.println("\nRecords fetched successfully.");
        }
    }

    public static void insert(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1) Add Student\n2) Add course\nEnter: ");
        int num = scanner.nextInt();

        Transaction tx = session.beginTransaction();

        try {
            if (num == 1) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter student name: ");
                String name = sc.nextLine();
                System.out.print("Enter student email: ");
                String email = sc.nextLine();
                System.out.print("Enter student age: ");
                int age = sc.nextInt();
                Student student = new Student(name, email, age);
                session.save(student);
                tx.commit();
            } else {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter course name: ");
                String courseName = sc.nextLine();
                Course course = new Course(courseName);
                session.save(course);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error inserting record: " + e.getMessage());
        }
    }

    public static void fetch(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1) Students\n2) Courses\nEnter: ");
        int num = scanner.nextInt();

        try {
            if (num == 1) {
                List<Student> students = session.createQuery("from Student", Student.class).list();
                for (Student s : students) {
                    System.out.println(s.getUserName());
                }
            } else {
                List<Course> courses = session.createQuery("from Course", Course.class).list();
                for (Course c : courses) {
                    System.out.println(c.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching record: " + e.getMessage());
        }
    }

    public static void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1) Students\n2) Courses\nEnter: ");
        int num = scanner.nextInt();

        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();

            if (num == 1) {

                Student stu = session.get(Student.class, id);

                if (stu != null) {
                    System.out.print("Enter new name (leave blank to skip): ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    if (!name.isBlank()) {
                        stu.setUserName(name);
                    }

                    System.out.print("Enter new email (leave blank to skip): ");
                    String email = scanner.nextLine();
                    if (!email.isBlank()) {
                        stu.setEmail(email);
                    }

                    System.out.print("Enter new age (leave blank to skip): ");
                    String ageInput = scanner.nextLine();
                    if (!ageInput.isBlank()) {
                        int age = Integer.parseInt(ageInput);
                        stu.setAge(age);
                    }

                    tx.commit();
                    System.out.println("Student updated.");
                } else {
                    System.out.println("Student with ID not found.");
                    tx.rollback();
                }
            } else {
                Course course = session.get(Course.class, id);

                if (course != null) {
                    System.out.print("Enter new name (leave blank to skip): ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    if (!name.isBlank()) {
                        course.setName(name);
                    }

                    tx.commit();
                    System.out.println("Course updated.");
                } else {
                    System.out.println("Course with ID not found.");
                    tx.rollback();
                }
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    public static void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1) Students\n2) Courses\nEnter: ");
        int num = scanner.nextInt();

        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();

            if (num == 1) {
                Student stu = session.get(Student.class, id);
                if (stu != null) {
                    session.delete(stu);
                    tx.commit();
                } else {
                    System.out.println("Student with ID not found.");
                    tx.rollback();
                }
            } else {
                Course cod = session.get(Course.class, id);
                if (cod != null) {
                    session.delete(cod);
                    tx.commit();
                } else {
                    System.out.println("Course with ID not found.");
                    tx.rollback();
                }
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) Insert Data \n2) Fetch Records\n3) Update\n4) Delete\n5) Exit\nEnter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    insert(session);
                } catch (Exception e) {
                    System.out.println("Error inserting: " + e.getMessage());
                }
                break;
            case 2:
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    fetch(session);
                } catch (Exception e) {
                    System.out.println("Error fetching: " + e.getMessage());
                }
                break;
            case 3:
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    update(session);
                } catch (Exception e) {
                    System.out.println("Error updating: " + e.getMessage());
                }
                break;
            case 4:
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    delete(session);
                } catch (Exception e) {
                    System.out.println("Error deleting: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}