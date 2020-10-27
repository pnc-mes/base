$(function () {
    $(".download").on("click", function () {
        window.location.href = getBasePath() + "/upload/无工单领料导入模板.xls";
    });
    //
    $("#file").change(function () {
        $("#fileName").val($(this).val());
      /*  var index = $(this).val().indexOf(".xls");
        if(index == -1){
            toastr.warning("只能上传.xls结尾的文件");
        }else{
            $("#fileName").val($(this).val());
        }*/
    });
    $(".sure").on("click",function () {
        var filename = $("#file").val().trim();
        if (!filename || !(filename.endsWith('.xls') )) {
            parent.toastr.warning("只能上传.xls结尾的文件");
            return false;
        }
        //上传文件到后台
        var formData = new FormData();

        formData.append('upload', document.getElementById('file').files[0]);


        uploadRequest({url: "/Pick/importnExcel", data: formData}, function (Body) {
            parent.layer.closeAll();
            if(Body.MsgCode == "0x00000"){
                parent.toastr.success(Body.MsgDes);
                //parent.condition = "";
                //parent.currentPage = 0;
                //parent.loadPage();
            }else{
                parent.toastr.warning(Body.MsgDes);
            }
        });



       /* $.ajax({
            url: getBasePath() + "/Pick/importnExcel",
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            beforeSend: function () {
                App.blockUI({
                    target: "body",
                    message: "正在导入,请稍后...",
                    boxed:true
                });
            },
            complete: function () {
                App.unblockUI("body");
            },
            success: function (data) {
                parent.layer.closeAll();
                if(data.Status == "00" && data.Body.MsgCode == "0x00000"){
                    parent.toastr.success(data.Body.MsgDes);
                    //parent.condition = "";
                    //parent.currentPage = 0;
                    //parent.loadPage();
                }else{
                    parent.toastr.warning(data.Body.MsgDes);
                }
            }
        });*/
    });
    $("._close").on("click",function () {
       parent.layer.closeAll();
    });



});
