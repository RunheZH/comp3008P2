<div>
    <script>
        var ele_prev;
        $(document).ready(function () {
            $("#image_area").on("click",".images",function (element) {
                $(ele_prev).attr("class","images");
                ele_prev = this;
                $(this).attr("class","images highlighted");
                <c:if test="${image_size == 64}">
                $("#input_password").text("${current_password}"+convertchar($(this).attr("id").substr(0,1))+$(this).attr("id").substr(2,1));
                $("#link").attr("href","/verify_image?type=${type}&imagens=${imagens}&password=${current_password}"+convertchar($(this).attr("id").substr(0,1))+$(this).attr("id").substr(2,1));
                </c:if>
                <c:if test="${image_size == 8}">
                $("#input_password").text("${current_password}"+(parseInt($(this).attr("id").substr(0,1))+parseInt($(this).attr("id").substr(2,1))));
                $("#link").attr("href","/verify_image?type=${type}&imagens=${imagens}&password=${current_password}"+(parseInt($(this).attr("id").substr(0,1))+parseInt($(this).attr("id").substr(2,1))));
                </c:if>
            })
        })
        function convertchar(num_s) {
            return String.fromCharCode(66+parseInt(num_s));
        }
    </script>
    <style>
        .highlighted {
            border: red solid 3px;
        }
    </style>
    <div id = "image_area">
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
    <label>Password:<label id="input_password">${current_password}</label></label>
    <a id = "link"  class="btn btn-primary" href="#">Next</a>
</div>