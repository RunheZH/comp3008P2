<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/3/25
  Time: 下午2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/header.jsp"%>
</head>
<body>
<div class = "container">
    <%@include file="include/SchemeChange.jsp"%>
    <c:if test="${scheme == 1}">
        <!-- octal -->
        <%@include file="include/octal_template_req.jsp"%>
    </c:if>
    <c:if test="${scheme == 2}">
        <!-- image-->
        <%@include file="include/image_template_req.jsp"%>
    </c:if>
    <c:if test="${scheme == 3}">
        <!--binary-->
        <%@include file="include/binary_template_req.jsp"%>
    </c:if>
</div>
</body>
</html>
