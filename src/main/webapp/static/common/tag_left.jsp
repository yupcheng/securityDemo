<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                    <span class="clear">
                                        <span class="block m-t-xs" style="font-size:20px;">
                                            <i class="fa fa-area-chart"></i>
                                            <strong class="font-bold">hAdmin</strong>
                                        </span>
                                    </span>
                    </a>
                </div>
                <div class="logo-element">hAdmin
                </div>
            </li>
            <c:forEach items="${menuList}" var="menu">
                <c:choose>
                    <c:when test="${empty menu.children}">
                        <li>
                            <a class="J_menuItem" href="${menu.url == null ? "" : menu.url}">
                                <i class="fa fa-magic"></i> <span class="nav-label">${menu.title}</span>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="#"><i class="fa fa-cutlery"></i> <span class="nav-label">${menu.title} </span>
                                <span class="fa arrow"></span></a>
                            <c:forEach items="${menu.children}" var="cmenu">
                                <ul class="nav nav-second-level">
                                    <li><a class="J_menuItem" href="${cmenu.url == null ? "" : menu.url}">${cmenu.title}</a>
                                    </li>
                                </ul>
                            </c:forEach>

                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <li>
                <a class="J_menuItem" href="/"><i class="fa fa-magic"></i> <span class="nav-label">主页</span></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-cutlery"></i> <span class="nav-label">系统管理 </span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a class="J_menuItem" href="form_builder.html">用户管理</a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
</nav>