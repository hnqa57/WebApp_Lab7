<%@ include file="header.jsp" %>
<h2>${course.id == 0 ? 'Add New Course' : 'Edit Course'}</h2>

<form action="${pageContext.request.contextPath}/course/add" method="post">
    <input type="hidden" name="id" value="${course.id}" />

    <div class="form-group">
        <label>Course Code</label>
        <input type="text" name="code" value="${course.code}" required />
    </div>

    <div class="form-group">
        <label>Course Name</label>
        <input type="text" name="name" value="${course.name}" required />
    </div>

    <div class="form-group">
        <label>Description</label>
        <input type="text" name="description" value="${course.description}" />
    </div>

    <div class="form-group">
        <label>Credits</label>
        <input type="number" name="credit" placeholder="Credits">  
    </div>

    <button type="submit" class="btn">Save</button>
    <a href="${pageContext.request.contextPath}/courses" class="btn">Cancel</a>
</form>
<%@ include file="footer.jsp" %>
