<div>
    <script src="../js/textinput.js"></script>
    <label>Your password is:${password}</label>
    <div>
        <label>You may enter this to following box <span><input id="input_box"></span></label>
        <button id="reenter_confirm" onclick="compare($('#input_box').val())">Confirm</button>
        <p><label>Your input length:<label id="input_length"></label></label></p>
    </div>
    <a class="btn btn-primary" href="/flow_confirm">Next</a>
</div>