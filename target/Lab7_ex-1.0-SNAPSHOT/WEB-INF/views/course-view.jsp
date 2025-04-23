<%@ include file="header.jsp" %>
<h2>Course Details</h2>

<c:if test="${course != null}">
    <div style="background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
        <p><strong>ID:</strong> ${course.id}</p>
        <p><strong>Code:</strong> ${course.code}</p>
        <p><strong>Name:</strong> ${course.name}</p>
        <p><strong>Description:</strong> ${course.description}</p>
        <p><strong>Credits:</strong> ${course.credits}</p>
    </div>

    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/course/edit?id=${course.id}" class="btn">Edit</a>
        <a href="${pageContext.request.contextPath}/courses" class="btn">Back to List</a>
    </div>
</c:if>

<c:if test="${course == null}">
    <p>Course not found.</p>
    <a href="${pageContext.request.contextPath}/courses" class="btn">Back to List</a>
</c:if>

<%@ include file="footer.jsp" %>
