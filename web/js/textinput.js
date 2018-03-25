function compare(data) {
    if (data === '${password}') {
        alert("Your password is correct");
    } else {
        alert("Your password is wrong");
    }
}
function updatelength() {
    $("#input_length").text($("#input_box").val().length)
}