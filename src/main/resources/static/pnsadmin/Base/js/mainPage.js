$.fn.modalOpen = function(options) {

    var defaults = {
        title: "修改密码",
        width: "100px",
        height: "100px",
        url: '',
        shade: 0.3,
        btn: ['确认', '关闭'],
        btnclass: ['btn btn-primary saveConfirm', 'btn btn-danger'],
        allowOverParent: false
    };
    var options = $.extend(defaults, options);
    var _width = top.$(window).width() > parseInt(options.width.replace('px', '')) ? options.width : top.$(window).width() + 'px';
    var _height = top.$(window).height() > parseInt(options.height.replace('px', '')) ? options.height : top.$(window).height() + 'px';
    layer.open({
        id: options.id,
        type: 2,
        shade: options.shade,
        title: options.title,
        fix: false,
        area: [_width, _height],
        content: options.url,
        btn: options.btn,
        btnclass: options.btnclass,
        yes: function(id,layero) {
            $.ajax({
                url: "User/ChangePwdInfo",
                type: "post",
                data: $(layero).find("iframe")[0].contentWindow.getFormData(),
                success: function (res) {
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        layer.msg(res.Body.MsgDes);
                        layer.closeAll("iframe");
                    } else {
                        alertWarn(res.Body.MsgDes)
                    }
                }
            });
            layer.closeAll();
        },
        cancel: function(id) {
            return true;
        }
    });
}
$.fn.modalAlert = function(content, type) {
    var icon = "";
    var iconType = 0;
    if (type == 'success') {
        icon = "fa-check-circle";
        iconType = 1;
    }
    if (type == 'error') {
        icon = "fa-times-circle";
        iconType = 2;
    }
    if (type == 'warning') {
        icon = "fa-exclamation-circle";
        iconType = 3;
    }
    top.layer.alert(content, {
        icon: iconType,
        title: "系统提示",
        btn: ['确认'],
        btnclass: ['btn btn-primary'],
    });
}

