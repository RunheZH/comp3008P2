<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/3/19
  Time: 下午1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PasswordGen#${nextstep}</title>
    <%@include file="include/header.jsp"%>
</head>
<body>
<div class = "container">
    <label>Please remember your password for ${type} Account</label>
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
