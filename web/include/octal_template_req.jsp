<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    //music related

</script>
<!-- request password -->
<script>
    //var xhr = new Array(8);
    //var buffer = new Array(8);
    var doBuffer;
    var reBuffer;
    var miBuffer;
    var faBuffer;
    var soBuffer;
    var laBuffer;
    var tiBuffer;
    var hidoBuffer;
    var preSize=0;
    //var url = ["/media/41.mp3", "/media/42.mp3", "/media/43.mp3", "/media/44.mp3", "/media/45.mp3", "/media/46.mp3", "/media/47.mp3", "/media/51.mp3"];
    var audioCtx = new (window.AudioContext || window.webkitAudioContext)();
    $(document).ready()
    {
        //loadAudio();
        var doxhr = new XMLHttpRequest();
        doxhr.open('GET', "/media/41.mp3");
        doxhr.responseType = "arraybuffer";
        doxhr.onload = function () {
            audioCtx.decodeAudioData(doxhr.response,function (decodedData) {
                doBuffer = decodedData;
            })
        }
        doxhr.send();

        var rexhr = new XMLHttpRequest();
        rexhr.open('GET', "/media/42.mp3");
        rexhr.responseType = "arraybuffer";
        rexhr.onload = function () {
            audioCtx.decodeAudioData(rexhr.response,function (decodedData) {
                reBuffer = decodedData;
            })
        }
        rexhr.send();

        var mixhr = new XMLHttpRequest();
        mixhr.open('GET', "/media/43.mp3");
        mixhr.responseType = "arraybuffer";
        mixhr.onload = function () {
            audioCtx.decodeAudioData(mixhr.response,function (decodedData) {
                miBuffer = decodedData;
            })
        }
        mixhr.send();

        var faxhr = new XMLHttpRequest();
        faxhr.open('GET', "/media/44.mp3");
        faxhr.responseType = "arraybuffer";
        faxhr.onload = function () {
            audioCtx.decodeAudioData(faxhr.response,function (decodedData) {
                faBuffer = decodedData;
            })
        }
        faxhr.send();

        var soxhr = new XMLHttpRequest();
        soxhr.open('GET', "/media/45.mp3");
        soxhr.responseType = "arraybuffer";
        soxhr.onload = function () {
            audioCtx.decodeAudioData(soxhr.response,function (decodedData) {
                soBuffer = decodedData;
            })
        }
        soxhr.send();

        var laxhr = new XMLHttpRequest();
        laxhr.open('GET', "/media/46.mp3");
        laxhr.responseType = "arraybuffer";
        laxhr.onload = function () {
            audioCtx.decodeAudioData(laxhr.response,function (decodedData) {
                laBuffer = decodedData;
            })
        }
        laxhr.send();

        var tixhr = new XMLHttpRequest();
        tixhr.open('GET', "/media/47.mp3");
        tixhr.responseType = "arraybuffer";
        tixhr.onload = function () {
            audioCtx.decodeAudioData(tixhr.response,function (decodedData) {
                tiBuffer = decodedData;
            })
        }
        tixhr.send();

        var hidoxhr = new XMLHttpRequest();
        hidoxhr.open('GET', "/media/51.mp3");
        hidoxhr.responseType = "arraybuffer";
        hidoxhr.onload = function () {
            audioCtx.decodeAudioData(hidoxhr.response,function (decodedData) {
                hidoBuffer = decodedData;
            })
        }
        hidoxhr.send();

    }

    // function  loadAudio(){
    //     for (var i=0; i<2; i++){
    //
    //         xhr[i] = new XMLHttpRequest();
    //         xhr[i].open('GET',url[i]);
    //         xhr[i].responseType = "arraybuffer";
    //         xhr[i].i = i;
    //         xhr[i].onload = function(){
    //             audioCtx.decodeAudioData(this.response, function (decodedData) {
    //             console.log(this.response);
    //                 buffer[this.i] = decodedData;
    //             })
    //         }
    //         xhr[i].send();
    //         //xhr.close();
    //     }
    // }

    function compare(data) {
        if (data === '${password}') {
            $("#input_box").val('');
            $("#input_length").text($("#input_box").val().length);
            preSize = 0;
            alert("Your password is correct. You can try again to remember the password.");

        } else {
            alert("Your password is wrong");
        }
    }
    // function updatelength() {
    //     $("#input_length").text($("#input_box").val().length)
    // }
    function playAudio(data) {
        var source = audioCtx.createBufferSource();
        if (data.toString().length > preSize && preSize !== 7) {
            var newNum = data.toString().charAt(data.toString().length - 1);
            switch (newNum) {
                case '1':
                    source.buffer = doBuffer;
                    break;
                case '2':
                    source.buffer = reBuffer;
                    break;
                case '3':
                    source.buffer = miBuffer;
                    break;
                case '4':
                    source.buffer = faBuffer;
                    break;
                case '5':
                    source.buffer = soBuffer;
                    break;
                case '6':
                    source.buffer = laBuffer;
                    break;
                case '7':
                    source.buffer = tiBuffer;
                    break;
                case '8':
                    source.buffer = hidoBuffer;
                    break;
                default:
                    alert("The password only include from 1-8");
                    $("#input_box").val(data.toString().substring(0,data.toString().length-1));
                    preSize--;
                    $("#input_length").text($("#input_box").val().length);
                    break;
            }
            preSize++;
            source.connect(audioCtx.destination);
            $("#input_length").text($("#input_box").val().length);
            source.start(audioCtx.currentTime)?source.start(audioCtx.currentTime):source.noteOn(0);

        }
        else {
            $("#input_length").text($("#input_box").val().length);
            preSize = data.toString().length;
        }
    }
</script>
<label>Your password is:${password}</label>
<div>
    <label>You may enter this to following box <span><input autocomplete="off" id="input_box" oninput="playAudio($('#input_box').val())" maxlength="7"></span></label>
    <button id="reenter_confirm" onclick="compare($('#input_box').val())">Confirm</button>
    <p><label>Your input length:<label id="input_length"></label></label></p>
</div>
<a class="btn btn-primary" href="/flow_confirm">Next</a>
