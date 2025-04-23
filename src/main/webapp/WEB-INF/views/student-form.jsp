<%@ include file="header.jsp" %>
<c:if test="${student != null}">
    <h2>Edit Student</h2>
    <form action="${pageContext.request.contextPath}/student/update"
          method="post">
        <input type="hidden" name="id" value="${student.id}" />
</c:if>
<c:if test="${student == null}">
    <h2>Add New Student</h2>
    <form action="${pageContext.request.contextPath}/student/add"
          method="post">
</c:if>
<div class="form-group">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${student.name}"
           required />
</div>

<div class="form-group">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${student.email}"
           required />
</div>

    <div class="form-group">
    <label>Course:</label>
    <select name="courseId" class="form-control" required>
        <option value="">-- Select Course --</option>
        <c:forEach items="${courses}" var="course">
            <option value="${course.id}" 
                ${student.course != null && student.course.id == course.id ? 'selected' : ''}>
                ${course.code} - ${course.name}
            </option>
        </c:forEach>
    </select>
</div>

<button type="submit" class="btn">Save</button>
<a href="${pageContext.request.contextPath}/students"
   class="btn">Cancel</a>
</form>
<%@ include file="footer.jsp" %>