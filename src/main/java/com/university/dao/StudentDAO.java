package com.university.dao;

import com.university.model.Student;
import com.university.model.Course;
import com.university.util.DBUtil;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final CourseDAO courseDAO = new CourseDAO();

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.*, c.id as course_id, c.code as course_code, c.name as course_name, " +
                     "c.description as course_description, c.credits as course_credits " +
                     "FROM students s LEFT JOIN courses c ON s.course_id = c.id";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = mapStudentFromResultSet(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT s.*, c.id as course_id, c.code as course_code, c.name as course_name, " +
                     "c.description as course_description, c.credits as course_credits " +
                     "FROM students s LEFT JOIN courses c ON s.course_id = c.id WHERE s.id = ?";
        Student student = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = mapStudentFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, course_id, registration_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getCourse().getId());
            pstmt.setTimestamp(4, Timestamp.valueOf(student.getRegistrationDate()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        student.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, course_id = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getCourse().getId());
            pstmt.setInt(4, student.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Student mapStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        
        // Map registration date
        if (rs.getTimestamp("registration_date") != null) {
            student.setRegistrationDate(rs.getTimestamp("registration_date").toLocalDateTime());
        }
        
        // Map course if it exists
        if (rs.getInt("course_id") > 0) {
            Course course = new Course();
            course.setId(rs.getInt("course_id"));
            course.setCode(rs.getString("course_code"));
            course.setName(rs.getString("course_name"));
            course.setDescription(rs.getString("course_description"));
            course.setCredits(rs.getInt("course_credits"));
            student.setCourse(course);
        }
        
        return student;
    }
}