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
    <label for="course">Course:</label>
    <select id="course" name="course" required>
        <option value="">-- Select Course --</option>
        <option value="Computer Science" ${student.course == 'Computer
                                           Science' ? 'selected' : ''}>Computer Science</option>
        <option value="Information Technology" ${student.course ==
                                                 'Information Technology' ? 'selected' : ''}>Information Technology</option>
        <option value="Software Engineering" ${student.course ==
                                               'Software Engineering' ? 'selected' : ''}>Software Engineering</option>
        <option value="Data Science" ${student.course == 'Data Science' ?
                                       'selected' : ''}>Data Science</option>
        <option value="Cybersecurity" ${student.course == 'Cybersecurity'
                                        ? 'selected' : ''}>Cybersecurity</option>
    </select>
</div>

<button type="submit" class="btn">Save</button>
<a href="${pageContext.request.contextPath}/students"
   class="btn">Cancel</a>
</form>
<%@ include file="footer.jsp" %>