//配置url
let url = "";

//获取站点根目录
function getBasePath() {
    if(url == undefined || url == ""){
        let obj = window.location;
        let contextPath = obj.pathname.split("/")[1];
        let basePath = /*obj.protocol+"//"+obj.host+*/"/" + contextPath;
        return basePath;
    }

    return url;
}

//通用数据请求方法
function requests(obj, callback) {
    $.ajax({
        type: obj.type == undefined ? "post" : obj.type,
        url: getBasePath() + obj.url,
        data: obj.data == undefined ? obj.contentType == undefined ? JSON.stringify({}) : {} : obj.data,
        async: obj.async == undefined ? false : obj.async,
        contentType: obj.contentType == undefined ? "application/json;charset=UTF-8" : obj.contentType,
        beforeSend: function () {
            layer.load();
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (res) {
            setTimeout(function(){
                layer.closeAll('loading');
            }, 2000);
            if (res.status == "00" && res.body.msgCode == "0x00000") {
                callback(res.body);
            } else if(res.Status == "00" && res.Body.MsgCode == "0x00000"){
                callback(res.Body);
            } else{
                if(res.body == undefined){
                    layer.msg(res.Body.MsgDes, {icon: 2});
                }else{
                    layer.msg(res.body.msgDes, {icon: 2});
                }
                return;
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 401) {
                window.location.href = getBasePath() + "/Login";
            } else {
                layer.msg("请求失败,请稍后再试!", {icon: 2});
            }
        }
    });
}