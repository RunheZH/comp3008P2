<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    //music related

</script>
<c:if test="${nextstep <= 3}">

    <!-- request password -->
    <script>
        //re-enter password
        function compare (data) {
            if (data === '${password}') {
                alert("Your password is correct");
            } else {
                alert("Your password is wrong");
            }
        }
    </script>
    <label>Your password is:${password}</label>
    <div>
        <label>You may enter this to following box <span><input id="input_box"></span></label>
        <button id="reenter_confirm" onclick="compare($('#input_box').val())">Confirm</button>
    </div>
    <a class="btn btn-primary" href="/flow_confirm">Next</a>


</c:if>
<c:if test="${nextstep > 3}">
    <!-- verify password -->

</c:if>