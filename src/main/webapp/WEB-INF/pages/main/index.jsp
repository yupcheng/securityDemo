<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="/static/common/taglib.jsp"%>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
            <%@ include file="/static/common/tag_left.jsp"%>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">

            <%@ include file="/static/common/tag_top.jsp" %>

            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="${ctx}/sys/user/user_admin" frameborder="0"></iframe>
            </div>
        </div>
        <!--右侧部分结束-->

    </div>
    <%@ include file="/static/common/jslib.jsp" %>
</body>


</html>
