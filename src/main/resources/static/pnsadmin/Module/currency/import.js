$(function () {
    //加载空表格
    var colNamesArr = [
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false, "CType": "text"},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false, "CType": "text"},
        {"Caption": "仓库名称", "Name": "StoreName", "Editable": false, "CType": "text",Width:100},
        {"Caption": "库位", "Name": "LCode", "Editable": false, "CType": "text",Width:100},
        {"Caption": "批次号", "Name": "Batch", "Editable": false, "CType": "text"},
        {"Caption": "数量", "Name": "Num", "Editable": false, "CType": "text",Width:80}
    ];
    var config1 = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.98,
        height: 0.82
    };
    fullTable(config1);
    //点击下载模板
    $('#filedowns').on('click', function () {
        //下载路径
        window.location.href =getBasePath()+ '/upload/导入库存数据模板.xlsx';
    })
    //点击上传按钮处理事件
                //看到要上传的是什么
    $("#file").click(function(){
        $("#file").val("");
    });
    $("#file").change(function () {
        var index = $(this).val().lastIndexOf("\\");
        if (index != -1)
            $("#fileName").val($(this).val().substring(index + 1));
        else
            $("#fileName").val($(this).val());
        //显示之后就要请求后台加载文件里面的数据
        if (window.FormData) {
            var formData = new FormData();
            formData.append('upload', document.getElementById('file').files[0]);

            $.ajax({
                url: getBasePath() + "/Tools/readExecl",
                type: "POST",
                data: formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    //填充表格
                    config1.data = data;
                    fullTable(config1);
                    //默认所有的数据都被选中
                    $('#list4').find('input[type="checkbox"]').each(function () {
                       $(this).attr('checked');
                    });
                }
            });
        }
    });


    //点击确认
    $('._save').on('click', function () {
        var selectData = getSeRowData('list4');
        if (!selectData.length) {
            toastr.warning("请选中要上传的数据!");
            return;
        }
        var selectDataLists = [];
        for (var i in selectData) {
            var selectDataList = {
                "MaCode": selectData[i].MaCode,
                "MaName": selectData[i].MaName,
                "StoreName": selectData[i].StoreName,
                "LCode": selectData[i].LCode,
                "Batch": selectData[i].Batch,
                "Num": selectData[i].Num
            }
            selectDataLists.push(selectDataList);
        }


        request({url:"/Tools/SaveImportInfo", data: {"ExecType": "00", "BusData": JSON.stringify(selectDataLists)},id:"aaa"},function(Body){
            if (Body.MsgCode == "0x00000") {
                $('#fileName').val('');
                toastr.success(Body.MsgDes);
                var data = Body.Data;
                if (data != undefined) {
                    var s = "盘点单号: ";
                    data.forEach(function (obj) {
                        s += obj.PDCode + "  ";
                    });
                    $("#pCode").text(s);
                }
            } else {
                toastr.warning(Body.MsgDes);
                return;
            }
        });


       /* $.ajax({
            url: getBasePath() + "/Tools/SaveImportInfo",
            type: 'post',
            dataType: "json",
            data: {"ExecType": "00", "BusData": JSON.stringify(selectDataLists)},
            beforeSend: function () {
                App.blockUI({
                    target: '#aaa',
                    message: "正在处理...",
                    boxed: true
                });
            },
            complete: function () {
                App.unblockUI('#aaa');
            },
            success:function(res){
                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                    $('#fileName').val('');
                    toastr.success(res.Body.MsgDes);
                    var data = res.Body.Data;
                    if (data != undefined) {
                        var s = "盘点单号: ";
                        data.forEach(function (obj) {
                            s += obj.PDCode + "  ";
                        });
                        $("#pCode").text(s);
                    }
                } else {
                    toastr.warning(res.Body.MsgDes);
                    return;
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (XMLHttpRequest.status == 401) {
                    window.location.href = getBasePath() + "/Login";
                } else {
                    toastr.error(textStatus);
                }
            }
        });*/
    });
});





























































