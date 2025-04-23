package com.university.controller;
import java.io.*;
import java.util.List;

import com.university.dao.CourseDAO;
import com.university.model.Course;
import com.university.model.Student;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CourseController", urlPatterns = {"/courses", "/course/*"})
public class CourseController extends HttpServlet {
    private CourseDAO courseDAO;

    @Override
    public void init() {
         courseDAO = new CourseDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        String action = request.getParameter("action");

        if (pathInfo == null || pathInfo.equals("/")) {
            listCourse(request, response);
        } 
        else if (pathInfo.equals("/new")) {
            showNewForm(request, response);
        } 
        else if (pathInfo.equals("/edit")) {
            showEditForm(request, response);
        } 
        else if (pathInfo.equals("/view")) {
            viewCourse(request, response);
        }
        else if (pathInfo.equals("/delete")) {
            deleteCourse(request, response);
        } else {
            listCourse(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();

        if ("/add".equals(pathInfo)) {
            addCourse(request, response);
        } else if ("/update".equals(pathInfo)) {
            updateCourse(request, response);
        } else {
            listCourse(request, response);
        }
    }

    private void listCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courseList = courseDAO.getAllCourses();
        request.setAttribute("courseList", courseList);
        request.getRequestDispatcher("/WEB-INF/views/course-list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseDAO.getCourseById(id);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);

    }
    private void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseDAO.getCourseById(id);
        request.setAttribute("course", course);
         request.getRequestDispatcher("/WEB-INF/views/course-view.jsp").forward(request, response);


    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String code = request.getParameter("code");
        int credit = Integer.parseInt(request.getParameter("credit"));

        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setCode(code);
        course.setCredits(credit);
        courseDAO.addCourse(course);
        response.sendRedirect(request.getContextPath() + "/courses");

    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = new Course();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String code = request.getParameter("code");
        int credit = Integer.parseInt(request.getParameter("credit"));
        course.setName(name);
        course.setDescription(description);
        course.setCode(code);
        course.setCredits(credit);



        courseDAO.updateCourse(course);
        response.sendRedirect(request.getContextPath() + "/courses");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        courseDAO.deleteCourse(id);
        response.sendRedirect(request.getContextPath() + "/Courses");
    }
}