function modalConfirm() {
    $.fn.modalConfirm("确认要提交么？", function(status) {
        layer.close(1);
        // alert("1")
    });

}
$.fn.modalConfirm = function(content, callBack) {
    layer.confirm(content, {
        icon: "fa-exclamation-circle",
        title: "系统提示",
        btn: ['确认', '取消'],
        btnclass: ['btn btn-primary queRen', 'btn btn-danger'],
    }, function() {
        callBack(true);
        layer.msg('保存成功', {
            icon: 1
        });
        /*----------------此处代表的是点击确认按钮之后处理的事情-----------------------------------------*/
        /*----------------此处代表的是点击确认按钮之后处理的事情-----------------------------------------*/

    }, function() {
        callBack(false)
        /*layer.msg('也可以这样', {
            time: 20000, //20s后自动关闭
            btn: ['保存1', '取消1']
        });*/
        /*----------------此处处理的是点击取消处理的事情，此处可以不用写，只是在前端处理吧---------------------*/
        /*----------------此处处理的是点击取消处理的事情，此处可以不用写，只是在前端处理吧---------------------*/
    });
}

$.fn.modalClose = function() {
    var index = layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    var $IsdialogClose = top.$("#layui-layer" + index).find('.layui-layer-btn').find("#IsdialogClose");
    var IsClose = $IsdialogClose.is(":checked");
    if ($IsdialogClose.length == 0) {
        IsClose = true;
    }
    if (IsClose) {
        layer.close(index);
        $('this').on('hide.bs.modal', function() {

        })

    }
}