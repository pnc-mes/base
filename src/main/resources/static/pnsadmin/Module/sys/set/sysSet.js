/**
 */
$(function () {

    /*request({url: "/AppSet/GetAppSetInfo", data: {"ExecType": "00"}}, function (Body) {
        if (Body.Data != null && Body.Data != undefined) {
            $("#AppLogo").val(Body.Data.appLogo);
            $("#AppName").val(Body.Data.appName);
            if (Body.Data.appLogo != undefined && Body.Data.appLogo != "") {
                $("#imags").attr("src", Body.Data.appLogo);
            } else {
                $("#imags").attr("src","${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/logo.png");
            }
        }
    });*/

    $("#pushImg").click(function () {
        var formData = new FormData();
        var img_file = document.getElementById("imageFile");
        var Obj = img_file.files[0];
        formData.append("classIcon", Obj);
        $.ajax({
            url: getBasePath() + "/AppSet/upload",
            type: "POST",
            data: formData,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.Body.MsgCode == "0x00000") {
                    $("#imags").attr("src", data.Body.Data.file);
                    $("#AppLogo").val(data.Body.Data.file);
                    toastr.success("图片上传完成！");
                } else {
                    toastr.warning("图片上传失败" + data.Body.MsgDes);
                }
            }
        });
    });


    //保存更新保存
    $(".cSplit").click(function () {
        var AppName = $("#AppName").val();
        var AppLogo = $("#AppLogo").val();

        if (AppName == "" || AppName == undefined) {
            toastr.warning("应用名称不能为空！");
            return false;
        }

        if (AppLogo == "" || AppLogo == undefined) {
            toastr.warning("请上传图片！");
            return false;
        }
        var newData = {
            "AppName": AppName,
            "AppLogo": AppLogo,
            "AppSetID": 1
        };
        request({
            url: "/AppSet/SaveAppSetInfo",
            data: {"ExecType": "02", "BusData": JSON.stringify(newData)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
            window.location.reload(true);
            location.reload([true]);
        });


    });

});