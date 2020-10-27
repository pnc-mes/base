/**
 * Created by test on 2017/9/6.
 */
$(function(){
    //获取发布APP页面的信息
    //定义表单规则
    var rule = [{
        "ctlid": "AppID", //自定义名字：标签id名字
        "param": "AppID" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "AppName",
        "param": "AppName"
    }, {
        "ctlid": "SAppVer",
        "param": "SAppVer"
    }, {
        "ctlid": "AppVer",
        "param": "AppVer"
    }, {
        "ctlid": "ForUpApp",
        "param": "ForUpApp"
    }, {
        "ctlid": "UpDes",
        "param": "UpDes"
    }];
    //处理上传文件
    $("#file").change(function () {
        var index = $(this).val().lastIndexOf("\\");
        if (index != -1)
            $("#fileName").val($(this).val().substring(index + 1));
        else
            $("#fileName").val($(this).val());
    })
    radio = $('.radio input[name="ForUpApp"]');
    request({url: "/PubApp/GetPAppInfo", data: {"ExecType": "00"}}, function (Body) {
        if(Body.Data == undefined){
            return;
        }

        fillform('pubAddForm',rule,Body.Data);
        var ForUpApp = Body.Data.ForUpApp;

        if(ForUpApp == '00'){
            radio.eq(0).prop('checked',true);
        }
        else if(ForUpApp == '01'){
            radio.eq(1).prop('checked',true);
        }
    });
//保存
    $('._save').on('click',function(){
       var formData = transfer("pubAddForm");
        //编辑
        var AppID = formData.AppID;
        var AppName = formData.AppName;
        var SAppVer = formData.SAppVer;
        var AppVer = formData.AppVer;
        var ForUpApp = null;
        var UpDes = formData.UpDes;
        //处理radio的传值
        if(radio.eq(0).prop('checked')){
            ForUpApp = '00';
        }else if(radio.eq(1).prop('checked')){
            ForUpApp = '01';
        }
        var formdatas = {
            "AppID": AppID,
            "AppName": AppName,
            "SAppVer": SAppVer,
            "AppVer": AppVer,
            "ForUpApp":ForUpApp,
            "UpDes": UpDes
        }
        //获取上传文件的名字
        var file = $('#fileName').text();
         if (window.FormData) {
         var form = new FormData();
            form.append('upload', document.getElementById('file').files[0]);
            form.append('ExecType', "02");
            form.append('BusData', JSON.stringify(formdatas));
         }
        uploadRequest({url:  "/PubApp/SavePAppInfo",    data: form}, function (Body) {
            toastr.success(Body.MsgDes);
        });
       /* $.ajax({
            url: getBasePath() + "/PubApp/SavePAppInfo",
            type: "POST",
            data: form,
            contentType: false,
            processData: false,
            success: function (res) {
                toastr.success(res.Body.MsgDes);
            }
        });*/
    })
});