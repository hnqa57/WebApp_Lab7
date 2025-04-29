package com.university.controller;

import com.itextpdf.io.source.OutputStream;
import com.university.dao.StudentDAO;
import com.university.dao.CourseDAO;
import com.university.model.Course;
import com.university.model.Student;
import com.university.service.PdfReportService;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "StudentController", urlPatterns = {"/students", "/student/*"})
public class StudentController extends HttpServlet {

    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String action = request.getParameter("action");
  

        if (pathInfo == null && action == null) {
            listStudents(request, response);
        } else if ("/new".equals(pathInfo)) {
            showNewForm(request, response);
        } 
        else if("/search".equals(pathInfo)){
             searchStudent(request, response);
        }
        else if ("/edit".equals(pathInfo)) {
            showEditForm(request, response);
        } else if ("/delete".equals(pathInfo)) {
            deleteStudent(request, response);
        } else if ("/view".equals(pathInfo)) {
            viewStudent(request, response);
        }
        else if("/report".equals(pathInfo)){
            try {
                generateStudentReport(response);
            } catch (Exception ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            listStudents(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if ("/add".equals(pathInfo)) {
            addStudent(request, response);
        } else if ("/update".equals(pathInfo)) {
            updateStudent(request, response);
        } else {
            listStudents(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/views/student-list.jsp").forward(request, response);
    }

    private void viewStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/WEB-INF/views/student-view.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("courses", courseDAO.getAllCourses()); 
        request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        request.setAttribute("student", student);
        request.setAttribute("courses", courseDAO.getAllCourses());
        request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.getCourseById(courseId);

        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setEmail(email);
        newStudent.setCourse(course);

        studentDAO.addStudent(newStudent);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseDAO.getCourseById(courseId);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);

        studentDAO.updateStudent(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }
    public void searchStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       String searchItem = request.getParameter("searchTerm");
        List<Student> students = studentDAO.searchStudents(searchItem);
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/views/student-list.jsp").forward(request, response);
    }

  
    
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect(request.getContextPath() + "/students");
    }
    
 
public void generateStudentReport(HttpServletResponse response) throws Exception {
    List<Student> students = studentDAO.getAllStudents();
    

    PdfReportService pdfService = new PdfReportService();
    ByteArrayOutputStream pdfStream = pdfService.generateStudentReport(students);
    
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=student_report.pdf");
    response.setContentLength(pdfStream.size());
    
    try (ServletOutputStream out = response.getOutputStream()) {
        pdfStream.writeTo(out);
        out.flush();
    }
}
}
