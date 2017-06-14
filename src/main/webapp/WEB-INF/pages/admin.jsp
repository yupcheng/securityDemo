<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>


<%--
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
<h1>标题: ${title}</h1>
<h1>消息 : ${message}</h1>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>欢迎: ${pageContext.request.userPrincipal.name}
        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a></h2>
</c:if>
</body>
</html>--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

<c:url value="/j_spring_security_logout" var="logoutUrl" />

<!-- csrt for log out-->
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<%--点击退出链接，执行表单提交  -> url=/j_spring_security_logout?_csrf=b63a7bc4-b5d7-4be9-b923-f6e402b63cf0  --%>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        Welcome : ${pageContext.request.userPrincipal.name} |
        <%--<a href="javascript:formSubmit()"> Logout</a>--%>
        <a href="<c:url value="/logout" />">Logout</a>
    </h2>
</c:if>



</body>
</html>