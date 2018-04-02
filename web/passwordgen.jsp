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
    <%@include file="include/header.jsp" %>
</head>
<body>
<div class="container">
    <h2>Please remember your password for ${type} Account</h2>
    <h2 class="label-success">For each password, you can use ONE of 3 password schemes.</h2>
    <h2 class="label-success">Note that you cannot go back to the previous page.</h2>
    <%@include file="include/SchemeChange.jsp" %>
    <c:if test="${scheme == 1}">
        <!-- octal -->
        <h2>Current Scheme: Octal</h2>
        <h3>Random generated numbers with digits from 1 - 8. You can practice your password in the input box.</h3>
        <%@include file="include/octal_template_req.jsp" %>
    </c:if>
    <c:if test="${scheme == 2}">
        <!-- image-->
        <h2>Current Scheme: Image</h2>
        <h3>random generated 4 groups of images, group 1-3 has 64 images and the last group has 8 images.</h3>
        <h3>In each group, you need to memorize the image bounded by the red square.</h3>
        <h3>For each password you need to memorize 4 images, note that order matters.</h3>
        <%@include file="include/image_template_req.jsp" %>
    </c:if>
    <c:if test="${scheme == 3}">
        <!--binary-->
        <h2>Current Scheme: Binary</h2>
        <h3>password in binary representation. passwords are consists of digit 1 and 0, 21 digits in total.</h3>
        <%@include file="include/binary_template_req.jsp" %>
    </c:if>
</div>
</body>
</html>
