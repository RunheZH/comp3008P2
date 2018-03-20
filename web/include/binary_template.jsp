<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    //music related

</script>
<c:if test="${nextstep eq 1}">
    <!-- request password -->
    <script>
        //re-enter password
    </script>
    <label>Your password is:${password}</label>
    <label>You may enter this to following box <span><input id="input_box"></span></label>


</c:if>
<c:if test="${nextstep eq 4}">
    <!-- verify password -->

</c:if>