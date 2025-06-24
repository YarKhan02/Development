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

    public static void main(String[] args) {
        System.out.println("Hibernate App Started");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Student st = new Student("Hammad", "hammad@gmail.com", 20);
            session.save(st);

            tx.commit();

            List<Course> courses = session.createQuery("from Course", Course.class).list();
            for (Course c : courses) {
                System.out.println(c.getName());
            }

            List<Student> students = session.createQuery("from Student", Student.class).list();
            for (Student s : students) {
                System.out.println(s.getUserName());
            }

            System.out.println("Student saved with ID: " + st.getId());
        }
    }
}