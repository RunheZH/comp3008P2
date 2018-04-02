<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        $("#${row}_${col}").attr("class", "highlighted");
    });


</script>
<style>
    .highlighted {
        border: red solid 8px;
    }
</style>

<div>
    <h2>Step ${imagens - 1} of 4:</h2>
    <div>
        <!-- image_area-->
        <c:if test="${image_size == 64}">
            <c:forEach begin="1" end="8" var="outer">
                <div class="row">
                    <c:forEach begin="1" end="8" var="inner">
                        <img id="${outer}_${inner}" class="images" src="img/${(outer-1)*8+inner}.jpg">
                    </c:forEach>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${image_size == 8}">
            <c:forEach begin="1" end="2" var="outer">
                <div class="row">
                    <c:forEach begin="1" end="4" var="inner">
                        <img id="${outer}_${inner}" class="images" src="img/${(outer-1)*4+inner}.jpg">
                    </c:forEach>
                </div>
            </c:forEach>
        </c:if>
    </div>
    <div>
        <a class="btn btn-primary" href="/request_image?imagens=${imagens}">Next</a>
    </div>


</div>