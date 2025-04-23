<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Management System</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5;
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
            .hero {
                background: #333;
                color: #fff;
                padding: 3rem 0;
                text-align: center;
            }
            .hero h1 {
                font-size: 2.5rem;
                margin-bottom: 1rem;
            }
            .hero p {
                font-size: 1.2rem;
                max-width: 800px;
                margin: 0 auto;
            }
            .container {
                width: 80%;
                margin: 2rem auto;
                flex: 1;
            }
            .features {
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                gap: 2rem;
            }
            .feature-box {
                background: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                padding: 1.5rem;
                width: 300px;
                text-align: center;
            }
            .feature-box h3 {
                color: #333;
                margin-top: 0;
            }
            .btn {
                display: inline-block;
                background: #333;
                color: #fff;
                border: none;
                padding: 0.7rem 1.5rem;
                margin-top: 1rem;
                border-radius: 5px;
                cursor: pointer;
                text-decoration: none;
                font-size: 1rem;
                transition: background 0.3s;
            }
            .btn:hover {
                background: #555;
            }
            footer {
                text-align: center;
                padding: 1rem;
                background: #333;
                color: #fff;
                margin-top: auto;
            }
        </style>
    </head>
    <body>
        <div class="hero">
            <h1>Student Management System</h1>
            <p>A comprehensive web application for managing student information
                using JSP, Servlets, MVC architecture, and MySQL database.</p>
        </div>

        <div class="container">
            <div class="features">
                <div class="feature-box">
                    <h3>View Students</h3>
                    <p>Access and view all students currently in the system.</p>
                    <a href="${pageContext.request.contextPath}/students"
                       class="btn">View All Students</a>
                </div>

                <div class="feature-box">
                    <h3>Add New Student</h3>
                    <p>Register a new student with their details and course
                        information.</p>
                    <a href="${pageContext.request.contextPath}/student/new"
                       class="btn">Add Student</a>
                </div>

                <div class="feature-box">
                    <h3>Manage Records</h3>
                    <p>Edit and update existing student records or remove
                        outdated entries.</p>
                    <a href="${pageContext.request.contextPath}/students"
                       class="btn">Manage Students</a>
                </div>
                       
                <div class="feature-box">
                    <h3>View Course</h3>
                    <p>Access and view all course currently in the system.</p>
                    <a href="${pageContext.request.contextPath}/courses"class="btn">View Course</a>
                </div>
                
                 <div class="feature-box">
                    <h3>Add new Course</h3>
                    <p>Add new course to the system</p>
                    <a href="${pageContext.request.contextPath}/course/new"class="btn">Add new course</a>
                </div>
                
                 <div class="feature-box">
                    <h3>Manage Course</h3>
                    <p>Edit and update existing student records or remove</p>
                    <a href="${pageContext.request.contextPath}/courses"class="btn">Manage Course</a>
                </div>  
                
            </div>
        </div>

        <footer>
            &copy; 2025 Student Management System
        </footer>
    </body>
</html>