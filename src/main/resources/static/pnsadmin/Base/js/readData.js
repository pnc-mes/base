$(function() {
    /*var ws = new WebSocket("ws://127.0.0.1:8000");

    ws.onopen = function () {
        console.log("open");
    };
    ws.onmessage = function (evt) {
        var received_msg = evt.data;
        $("#batch").val(received_msg + "\n");
        var evt = $.Event('keydown', {keyCode: 13});
        $(document).trigger(evt);
    };
    ws.onclose = function () {
        console.log("close");
    };*/

    var lockReconnect = false;  //避免ws重复连接
    var ws = null;          // 判断当前浏览器是否支持WebSocket
    var wsUrl = "ws://127.0.0.1:8000";
    var isMsg = true;
    createWebSocket(wsUrl);   //连接ws

    function createWebSocket(url) {
        try {
            if ('WebSocket' in window) {
                ws = new WebSocket(url);
            } else if ('MozWebSocket' in window) {
                ws = new MozWebSocket(url);
            } else {
                toastr.warning("您的浏览器不支持websocket协议,建议使用新版谷歌、火狐等浏览器，请勿使用IE10以下浏览器，360浏览器请使用极速模式，不要使用兼容模式！");
            }
            initEventHandle();
        } catch (e) {
            reconnect(url);
            console.log(e);
        }
    }

    function initEventHandle() {
        ws.onclose = function () {
            console.log("连接关闭!" + new Date().toUTCString());
            reconnect(wsUrl);
        };
        ws.onerror = function () {
            console.log("连接错误!");
            if(isMsg){
                toastr.warning("自动扫码枪系统断线，请不要着急，系统正在重连，请注意日托集成设备上的小红灯");
                isMsg = false;
            }
            reconnect(wsUrl);
        };
        ws.onopen = function () {
            console.log("连接成功!" + new Date().toUTCString());
            if(!isMsg){
                toastr.success("自动扫码枪系统已经成功连接");
                isMsg = true;
            }
        };
        ws.onmessage = function (event) {    //如果获取到消息，心跳检测重置
            console.log("----");
            //heartCheck.reset().start();      //拿到任何消息都说明当前连接是正常的

            var eventData = event.data;
            handMsg(eventData);
        };
    }

    // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        ws.close();
    };

    function reconnect(url) {
        if (lockReconnect) return;
        lockReconnect = true;
        setTimeout(function () {     //没连接上会一直重连，设置延迟避免请求过多
            createWebSocket(url);
            lockReconnect = false;
        }, 2000);
    }

    //处理消息
    function handMsg(evtdata) {
        console.log(evtdata);
        toastr.success("数据已采集成功");
        document.getElementById('batch').focus();
        $("#batch").val(evtdata + "\n");
        var evt = $.Event('keydown', {keyCode: 13});
        $(document).trigger(evt);
        document.getElementById('batch2').focus();
    }
});