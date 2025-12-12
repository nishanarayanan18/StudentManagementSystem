package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";     // change if needed
        String password = "root";     // change if needed
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    // ADD STUDENT
    public void addStudent(Student s) {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO students (id, name, age, gender, course) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getGender());
            ps.setString(5, s.getCourse());

            ps.executeUpdate();
            System.out.println("Student added to DB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE STUDENT
    public void updateStudent(Student s) {
        try (Connection con = getConnection()) {
            String query = "UPDATE students SET name=?, age=?, gender=?, course=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getGender());
            ps.setString(4, s.getCourse());
            ps.setInt(5, s.getId());

            ps.executeUpdate();
            System.out.println("Student updated in DB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE STUDENT
    public void deleteStudent(int id) {
        try (Connection con = getConnection()) {
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Student deleted from DB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();

        try (Connection con = getConnection()) {
            String query = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("course")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // SEARCH STUDENT BY ID
    public Student getStudentById(int id) {
        try (Connection con = getConnection()) {
            String query = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("course")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
