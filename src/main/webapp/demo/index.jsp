<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="X-FRAME-OPTIONS" content="SAMEORIGIN">
    <%@ include file="/static/common/taglib.jsp"%>
    <title> yudmin- 主页</title>
</head>
<%
    response.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题
%>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <%@ include file="/static/common/tag_top.jsp"%>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div>
               <%-- <iframe width="100%" height="100%" src="empty_page.jsp" frameborder="0"></iframe>--%>

                <%@ include file="empty_page.jsp"%>
            </div>
        </div>
        <!--右侧部分结束-->

    </div>
    <%@ include file="/static/common/jslib.jsp" %>
</body>


</html>
