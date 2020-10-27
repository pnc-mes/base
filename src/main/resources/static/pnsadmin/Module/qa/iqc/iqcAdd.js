/**
 * Created by PNC on 2017/6/28.
 */

$(function () {

    var colNamesArr = [
        {"Caption": "不良代码", "Name": "ReaCode", "IsKey": true, "CType": "text"},
        {"Caption": "不良描述", "Name": "ReaDes", "CType": "text"},
    ];
    var config = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width:0.69,
        height:0.5
    };
    fullTable(config);


    // 批次号搜索事件
    $('#bSearch').on('keypress', function (event) {

        if (event.keyCode == "13") {
            var Batch = $(this).val();
            var data = {
                "Batch": Batch
            }
            var fals=true;
            request({url:"/Commom/GetCMBInfo",data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(data)
                }},function (Body) {
                fals=false;
                if (Body.MsgCode == "0x00000") {
                    $("#MaCode").val(Body.Data.MaCode);
                    $("#MaName").val(Body.Data.MaName);
                    $("#Num").val(Body.Data.Num);
                    $("#hidden").val(Body.Data.Status)
                    if (Body.Data.MaType == "00") {
                        $("#cSave").attr("disabled", true);
                        $("#bSearch").attr("disabled", true);
                        $("#defaultSelect").clearseldata("ReaCode");
                        alertError("IQC只能质检原材料批次")
                        return;
                    }
                    if (Body.Data.MaStatus == "01") {
                        $("#cSave").attr("disabled", true);
                        $("#bSearch").attr("disabled", true);
                        alertError("该批次物料不可用")
                        return;
                    }
                    if (Body.Data.IQCStatus == "01") {
                        $("#cSave").attr("disabled", true);
                        $("#bSearch").attr("disabled", true);
                        alertError("该批次物料为免检物料")
                        return;
                    }
                    if (Body.Data.Status == "02") {
                        $("#cSave").attr("disabled", true);
                        $("#bSearch").attr("disabled", true);
                        alertError("该批次已冻结")
                        return;
                    }
                    if (Body.Data.Status == "03") {
                        $("#cSave").attr("disabled", true);
                        $("#bSearch").attr("disabled", true);
                        alertError("该批次已报废")
                        return;
                    }
                    if (Body.Data.Status == "04") {
                        $("#cSave").attr("disabled", true);
                        $("#bSearch").attr("disabled", true);
                        alertError("该批次已完工")
                        return;
                    }
                } else {
                    alertError(Body.MsgDes)

                }
            });
            if(fals){
                var id=$("#bSearch").val();
                $(".printTInfo input").val("");
                $("#bSearch").val(id);
                fals=false;
            }


          /*  $.ajax({
                url: getBasePath() + "/Commom/GetCMBInfo",
                type: "POST",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(data)
                },
                success: function (res) {
                    console.log(res.Body.Data)
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        $("#MaCode").val(res.Body.Data.MaCode);
                        $("#MaName").val(res.Body.Data.MaName);
                        $("#Num").val(res.Body.Data.Num);
                        $("#hidden").val(res.Body.Data.Status)
                        if (res.Body.Data.MaType == "00") {
                            $("#cSave").attr("disabled", true);
                            $("#bSearch").attr("disabled", true);
                            $("#defaultSelect").clearseldata("ReaCode");
                            alertError("IQC只能质检原材料批次")
                            return;
                        }
                        if (res.Body.Data.MaStatus == "01") {
                            $("#cSave").attr("disabled", true);
                            $("#bSearch").attr("disabled", true);
                            alertError("该批次物料不可用")
                            return;
                        }
                        if (res.Body.Data.IQCStatus == "01") {
                            $("#cSave").attr("disabled", true);
                            $("#bSearch").attr("disabled", true);
                            alertError("该批次物料为免检物料")
                            return;
                        }
                        if (res.Body.Data.Status == "02") {
                            $("#cSave").attr("disabled", true);
                            $("#bSearch").attr("disabled", true);
                            alertError("该批次已冻结")
                            return;
                        }
                        if (res.Body.Data.Status == "03") {
                            $("#cSave").attr("disabled", true);
                            $("#bSearch").attr("disabled", true);
                            alertError("该批次已报废")
                            return;
                        }
                        if (res.Body.Data.Status == "04") {
                            $("#cSave").attr("disabled", true);
                            $("#bSearch").attr("disabled", true);
                            alertError("该批次已完工")
                            return;
                        }
                    } else {
                        alertError(res.Body.MsgDes)

                    }
                }
            })*/
        }
    });

    /*质检结果*/
    $("#CkResult").change(function () {

        if ($("#CkResult").val().trim() == "00") {

            $("#cSearch").attr("disabled", true);
            $("#del1").attr("disabled", true);
        } else {

            $("#cSearch").attr("disabled", false);
            $("#del1").attr("disabled", false);
        }
    });

    //原因代码
    var dat1 = [];
    var params = {
        "displaymode": "0",
        "title": "原因代码",
        "binddata": {
            "keyfield": "ReaCode",
            "fields": [
                {
                    "caption": "原因代码id",
                    "name": "ReaCode"
                }, {
                    "caption": "原因描述",
                    "name": "ReaDes"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                var va = res.ReaCode;
                var vb = res.ReaDes;
                var newdata = {
                    "ReaCode": va,
                    "ReaDes": vb
                }
                dat1.push(newdata);

                addSrow("list4", newdata, 0, true);
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ReaDes",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                        url: "/Reason/GetAllReaInfo",
                        data: {
                            "ExecType": "00",
                            "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)
                        }
                    },
                    function (Body) {
                        var datas = Body.Data;
                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "ReaCode": datas[i].ReaCode,
                                "ReaDes": datas[i].ReaDes,
                            }
                            xldata.push(data);

                        }
                    });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    }
    $("#defaultSelect").zc_select(params);

    //删除原因代码
    $(".del1").on("click", function () {
        derow("list4");
    });

    //保存
    $("#cSave").click(function () {

        //     if($("#cSave").val()=="" && $("#cSave").val()=="MaCode"){
        var Batch = $('#bSearch').val();
        if (Batch == "") {
            alertError("请先选择一个批次");
            return;
        }

        if ($("#CkResult option:checked").attr("value") == "00") {
            var str = "00";
            dat1 = [];
        }
        if ($("#CkResult option:checked").attr("value") == "01") {
            var str = "01";
        }
        if ($("#CkResult option:checked").attr("value") == "02") {
            var str = "02";
        }
        //       var dat1 = getSeRowData("list4");

        var objBusData = {
            "Batch": Batch,
            "CkResult": str,
            "Remark": "IQC测试",
            "BadInfo": dat1,
        }

        request({url:"/IQC/SaveIQCBInfo",data: {
                "ExecType": "00",
                "busData": JSON.stringify(objBusData)
            }},function (Body) {
                layer.alert(Body.MsgDes, {
                        icon: 1,
                        skin: 'layer-ext-moon'
                    }, function () {
                        layer.closeAll();
                        if (!confirm("确认要继续新增吗？")) {
                            parent.layer.closeAll();
                        } else {

                            $("#bSearch").val("");
                            $("#MaCode").val("");
                            $("#MaName").val("");
                            $("#Num").val("");
                            $("#CkResult").val($('#cangku option:first').val());
                            $("#cSearch").val($('#type option:first').val());
                            var config = {
                                tableId: "list4",
                                data: [],
                                colArr: colNamesArr,
                                multiselect: true,
                                width:0.69,
                                height:0.5
                            };
                            fullTable(config);
                        }
                    }
                );

                alertSuccess("成功")
                setTimeout(function () {
                    parent.layer.closeAll();
                }, 1500)

        });


      /*  $.ajax({
            url: getBasePath() + "/IQC/SaveIQCBInfo",
            type: "POST",
            data: {
                "ExecType": "00",
                "busData": JSON.stringify(objBusData)
            },
            success: function (res) {
                if (res.Status == "01") {
                    alertError(res.Body.MsgDes)
                }

                if (res.Status == "00") {
                    layer.alert(res.MsgDes, {
                            icon: 1,
                            skin: 'layer-ext-moon'
                        }, function () {
                            layer.closeAll();
                            if (!confirm("确认要继续新增吗？")) {
                                parent.layer.closeAll();
                            } else {

                                $("#bSearch").val("");
                                $("#MaCode").val("");
                                $("#MaName").val("");
                                $("#Num").val("");
                                $("#CkResult").val($('#cangku option:first').val());
                                $("#cSearch").val($('#type option:first').val());
                                var config = {
                                    tableId: "list4",
                                    data: [],
                                    colArr: colNamesArr,
                                    multiselect: true,
                                    width:0.69,
                                    height:0.5
                                };
                                fullTable(config);
                            }
                        }
                    );

                    alertSuccess("成功")
                    setTimeout(function () {
                        parent.layer.closeAll();
                    }, 1500)
                }
            }
        });*/
        /*   }
         else {
         alertError("请先选择一个批次")
         }*/
    });

    //新增
    $("#addIQC").click(function () {
        $(".printTInfo input").val("");
        $("#cSave").attr("disabled", false);
        $("#bSearch").attr("disabled", false);
    });
    /* $("#bSearch").bind('keypress',function(){
     alert(1)
     if(event.keyCode == 13){
     //这里写你要执行的事件;
     var b = {
     Batch:"000010000000002"
     }
     $.ajax({
     url:"../Batch/GetBatchInfo2",
     type:"post",
     data:{
     "ExecType":"00",
     "BusData":JSON.stringify(b)
     },
     success:function(res){
     alert(JSON.stringify(res))
     // 填充form表单
     if(res.Status =="00"/!* && res.Body.MsgCode == "0x000001"*!/) {
     // 1.处理表单数据
     fillform("iqcForm", rule, res.Body.Data);
     }else{
     alertError(res.Body.MsgDes);
     }
     }
     });
     }
     });
     // 不良代码搜索事件
     $("#cSearch").change(function(){
     //这里写你要执行的事件;
     alert($(this).val())
     $.ajax({
     url:"",
     type:"post",
     data:"",
     success:function(res){
     // 填充form表单
     if(res.Status =="00"/!* && res.Body.MsgCode == "0x000001"*!/) {
     // 处理表格数据

     modelArr = ['IQCCentRd', 'FileName', 'LastUpDate'];
     colNamesArr = ['IQCCentRd', '文件名称', '最后一次上传时间'];
     tableId = "list10";
     divId = "pager10";
     fullTable(res.Body.Data.CentInfo, modelArr, colNamesArr, tableId, divId, pageBean);
     }else{
     alertError(res.Body.MsgDes);
     }
     }
     });
     });

     */
});