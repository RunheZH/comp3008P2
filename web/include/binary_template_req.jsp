<div>
    <script>
        function compare(data) {
            if (data === '${password}') {
                alert("Your password is correct");
            } else {
                alert("Your password is wrong");
            }
            $("#input_box").val("");
        }
        function updatelength() {
            $("#input_length").text($("#input_box").val().length)
        }

    </script>
    <label>Your password is:${password}</label>
    <div>
        <label>You may enter this to following box <span><input autocomplete="off" id="input_box" maxlength="21" onkeydown="updatelength()"></span></label>
        <button id="reenter_confirm" onclick="compare($('#input_box').val());">Confirm</button>
        <p><label>Your input length:<label id="input_length"></label></label></p>
    </div>
    <a class="btn btn-primary" href="/flow_confirm">Next</a>
</div>