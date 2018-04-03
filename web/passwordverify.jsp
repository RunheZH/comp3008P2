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
    <%@include file="include/header.jsp" %>
</head>
<body>
<div class="container">
    <h2>Please enter your ${type} password</h2>
    <div class="outerinstruction">
        <h3>To verify your password, you can choose ONE of the password scheme. Note that you cannot go back to the
            previous page</h3>
        <h3>After entering your password and click Next, the system will alert you whether your
            entered correct/wrong password.</h3>
        <h3>You will have THREE chances to enter your password for each password type</h3>
    </div>
    <%@include file="include/SchemeChange.jsp" %>
    <c:if test="${scheme == 1}">
        <!-- octal -->
        <div class="innerinsturction">
            <h2>Current Scheme: Octal</h2>
            <h3> Enter your password in the input box, you can enter your password once.</h3>
            <h3>Please turn your volume up, so you can hear music node.</h3>
        </div>
        <%@include file="include/octal_template_verify.jsp" %>
    </c:if>
    <c:if test="${scheme == 2}">
        <!-- image-->
        <div class="innerinsturction">
            <h2>Current Scheme: Image</h2>
            <h3>choose the image you memorized in order from four groups of images, note that you cannot go back to the
                previous page.</h3>
        </div>
        <%@include file="include/image_template_verify.jsp" %>
    </c:if>
    <c:if test="${scheme == 3}">
        <!--binary-->
        <div class="innerinsturction">
            <h2>Current Scheme: Binary</h2>
            <h3>Enter your password in the input box, you can enter your password once.</h3>
        </div>
        <%@include file="include/binary_template_verify.jsp" %>
    </c:if>
</div>
</body>
</html>
