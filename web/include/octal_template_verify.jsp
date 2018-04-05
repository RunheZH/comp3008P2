<div>
    <script>
        var doBuffer;
        var reBuffer;
        var miBuffer;
        var faBuffer;
        var soBuffer;
        var laBuffer;
        var tiBuffer;
        var hidoBuffer;
        var preSize=0;
        var audioCtx = new (window.AudioContext || window.webkitAudioContext)();
        $(document).ready()
        {
            //loadAudio from server
            var doxhr = new XMLHttpRequest();
            doxhr.open('GET', "/media/41.mp3");
            doxhr.responseType = "arraybuffer";
            doxhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(doxhr.response,function (decodedData) {
                    doBuffer = decodedData;
                })
            }
            doxhr.send();

            //loadAudio from server
            var rexhr = new XMLHttpRequest();
            rexhr.open('GET', "/media/42.mp3");
            rexhr.responseType = "arraybuffer";
            rexhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(rexhr.response,function (decodedData) {
                    reBuffer = decodedData;
                })
            }
            rexhr.send();

            //loadAudio from server
            var mixhr = new XMLHttpRequest();
            mixhr.open('GET', "/media/43.mp3");
            mixhr.responseType = "arraybuffer";
            mixhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(mixhr.response,function (decodedData) {
                    miBuffer = decodedData;
                })
            }
            mixhr.send();

            //loadAudio from server
            var faxhr = new XMLHttpRequest();
            faxhr.open('GET', "/media/44.mp3");
            faxhr.responseType = "arraybuffer";
            faxhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(faxhr.response,function (decodedData) {
                    faBuffer = decodedData;
                })
            }
            faxhr.send();

            //loadAudio from server
            var soxhr = new XMLHttpRequest();
            soxhr.open('GET', "/media/45.mp3");
            soxhr.responseType = "arraybuffer";
            soxhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(soxhr.response,function (decodedData) {
                    soBuffer = decodedData;
                })
            }
            soxhr.send();

            //loadAudio from server
            var laxhr = new XMLHttpRequest();
            laxhr.open('GET', "/media/46.mp3");
            laxhr.responseType = "arraybuffer";
            laxhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(laxhr.response,function (decodedData) {
                    laBuffer = decodedData;
                })
            }
            laxhr.send();

            //loadAudio from server
            var tixhr = new XMLHttpRequest();
            tixhr.open('GET', "/media/47.mp3");
            tixhr.responseType = "arraybuffer";
            tixhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(tixhr.response,function (decodedData) {
                    tiBuffer = decodedData;
                })
            }
            tixhr.send();

            //loadAudio from server
            var hidoxhr = new XMLHttpRequest();
            hidoxhr.open('GET', "/media/51.mp3");
            hidoxhr.responseType = "arraybuffer";
            hidoxhr.onload = function () {
                //Decode the arraybuffer to Audio Data
                audioCtx.decodeAudioData(hidoxhr.response,function (decodedData) {
                    hidoBuffer = decodedData;
                })
            }
            hidoxhr.send();

        }
        function updatehref() {
            console.log("123");
            $("#link").attr("href", "/flow_verify?type=${type}&password="+$("#input_box").val());
        }

        function playAudio(data) {
            var source = audioCtx.createBufferSource();
            //If the user input some number, play the audio
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
                    //If the input is invalid, show the message
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
                //Play the audio
                source.start(audioCtx.currentTime)?source.start(audioCtx.currentTime):source.noteOn(0);

            }
            //If the lenght of the input field, change the preSize.
            else {
                $("#input_length").text($("#input_box").val().length);
                preSize = data.toString().length;
            }
        }
    </script>
    <label>Please enter your ${type} password</label>
    <div>
        <label>You may enter this to following box <span><input autocomplete="off" id="input_box"
                                                                oninput="updatehref();playAudio($('#input_box').val())" maxlength="7"></span></label>
        <p><label>Your input length:<label id="input_length"></label></label></p>

    </div>
    <a id="link" class="btn btn-primary" href="/flow_verify?type=${type}&password=">Next</a>
</div>