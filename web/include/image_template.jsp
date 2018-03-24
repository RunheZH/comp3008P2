<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${image_size == 64}">
    <!--64 pic image-->
    <script>

    </script>
</c:if>

<c:if test="${image_size == 8}">
    <!-- 8 pics image-->
    <script>

    </script>

</c:if>
<div class="content">
    <div class="imagearea">
        <!-- image_area-->
        <c:if test="${image_size == 64}">
            <c:forEach begin="0" end="7" var="outer">
                <c:forEach begin="1" end="8" var="inner">
                    <img id = "${outer}_${inner}" class="images" src="img/${outer*8+inner}.jpg">
                </c:forEach>
            </c:forEach>
        </c:if>
    </div>
    <form action="/request_image?imagens=${imagens}" method="post">
        <input name="selection" type="hidden" >
        <input type="submit" value="Confirm">
    </form>


</div>