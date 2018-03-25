<div>
    <script src="../js/textinput.js"></script>
    <script>
        function updatehref() {
            $("#link").attr("href", "/flow_verify?type=${PasswordType}&password=" + $("#input_box").val());
        }
    </script>
    <label>Please enter your ${PasswordType} password</label>
    <div>
        <label>You may enter this to following box <span><input id="input_box"
                                                                onkeyup="updatelength();updatehref()"></span></label>
        <p><label>Your input length:<label id="input_length"></label></label></p>

    </div>
    <a id="link" class="btn btn-primary" href="/flow_verify?type=${PasswordType}&password=">Next</a>
</div>