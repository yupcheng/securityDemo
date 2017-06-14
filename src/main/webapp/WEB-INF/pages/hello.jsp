<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>标题: ${title}</h1>
<h1>消息 : ${message}</h1>
<a href="<c:url value="/admin" />" > admin</a>
</body>
</html>