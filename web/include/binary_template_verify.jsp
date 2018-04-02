<script>
    function updatelength() {
        $("#input_length").text($("#input_box").val().length);
    }
    function updatehref() {
        $("#link").attr("href", "/flow_verify?type=${type}&password="+$("#input_box").val());
    }
</script>
<div>
    <label>You may enter this to following box <span><input id="input_box" oninput="updatehref();updatelength()" maxlength="21"></span></label>
    <p><label>Your input length:<label id="input_length"></label></label></p>

</div>
<a id="link" class="btn btn-primary" href="/flow_verify?type=${type}&password=">Next</a>