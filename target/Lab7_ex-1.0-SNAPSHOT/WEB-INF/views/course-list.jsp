<%@ include file="header.jsp" %>
<h2>Course List</h2>

<c:if test="${empty courseList}">
    <p>No courses found.</p>
</c:if>

<c:if test="${not empty courseList}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Name</th>
            <th>Credits</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${courseList}">
            <tr>
                <td>${course.id}</td>
                <td>${course.code}</td>
                <td>${course.name}</td>
                <td>${course.credits}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/course/view?id=${course.id}" class="btn">View</a>
                    <a href="${pageContext.request.contextPath}/course/edit?id=${course.id}" class="btn">Edit</a>
                    <a href="${pageContext.request.contextPath}/course/delete?id=${course.id}"
                       onclick="return confirm('Are you sure you want to delete this course?')" class="btn">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<a href="${pageContext.request.contextPath}/course/new" class="btn">Add New Course</a>
<%@ include file="footer.jsp" %>