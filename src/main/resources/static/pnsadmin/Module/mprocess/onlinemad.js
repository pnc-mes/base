/**
 * Created by test on 11/6 0006.
 */
$(function () {
    var colNamesArr = [
        {"Caption": "工单号", "Name": "WoCode",  "Editable": false,Width:100},
        {"Caption": "批次号", "Name": "Batch",  "Editable": false,Width:100},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
        {"Caption": "数量", "Name": "Num", "Editable": false,Width:80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.96,
        height:0.57
    };
    fullTable(config1);
    var $batchNum = $("#batchNum");
    var $in_WorkOrder = $("#in_WorkOrder");
    var $out_WorkOrder = $("#out_WorkOrder");
    $("#haveWorkOrder").on("click", function () {
        if ($(this).prop("checked")) {
            $in_WorkOrder.css("display", "block");
            $batchNum.css("display", "none");
            config1.data = [];
            fullTable(config1);
        } else {
            $in_WorkOrder.css("display", "none");
            $batchNum.css("display", "block");
            config1.data = [];
            fullTable(config1);
        }
    });

    //可调出工单信息
    var params = {
        "displaymode": "1",
        "title": "调出工单",
        "binddata": {
            "keyfield": "WoRd",
            "fields": [
                {
                    "caption": "工单ID",
                    "name": "WoRd"
                }, {
                    "caption": "工单号",
                    "name": "WoCode"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var xldata = [];
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"WoCode",
                            "FieldOpt":"LIKE",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"Status",
                            "FieldOpt":"IN",
                            "FieldVal":"('01','03','06')"
                        }
                    ]
                };
                request({
                    url: "/WO/GetAllWOInfo",
                    data: {
                        "ExecType": "00",
                        "InitData":JSON.stringify(InitData)
                    }
                },function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WoRd": datas[i].WoRd,
                            "WoCode": datas[i].WoCode
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            },
            "onclick":function (res) {
                var datasources={
                    "Batch":"",
                    "WoCode":res.WoCode,
                    "MaCode":"",
                    "MaName":"",
                    "CreateTime":"",
                    "CreateTime1":"",
                    "Status":"00"
                };
                request({
                    url: "/Report/GetAllLYMaInfo",
                    data: {"ExecType": "00", "BusData": JSON.stringify(datasources)}
                }, function (Body) {
                    config1.data = Body.Data;
                    fullTable(config1);
                    if(!Body.Data.length){
                        toastr.warning("该工单没有余料");
                    }
                });
            }
        }
    };
    $("#outWorkNum").zc_select(params);
    //可调入工单信息
    var params1 = {
        "displaymode": "1",
        "title": "调入工单",
        "binddata": {
            "keyfield": "WoRd_",
            "fields": [
                {
                    "caption": "工单ID",
                    "name": "WoRd_"
                }, {
                    "caption": "工单号",
                    "name": "WoCode_"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var xldata = [];
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"WoCode",
                            "FieldOpt":"LIKE",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"Status",
                            "FieldOpt":"IN",
                            "FieldVal":"('01','03')"
                        }
                    ]
                };
                request({
                    url: "/WO/GetAllWOInfo",
                    data: {
                        "ExecType": "00",
                        "InitData":JSON.stringify(InitData)
                    }
                },function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WoRd_": datas[i].WoRd,
                            "WoCode_": datas[i].WoCode
                        };
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
    };
    $("#inWorkNum").zc_select(params1);
    //根据物料批次查询产线余料信息
    $("#materBatch").on("keyup",function (e) {
        if(e.keyCode == "13"){
            var batch = $(this).val();
            var datasources={
                "Batch":batch,
                "WoCode":"",
                "MaCode":"",
                "MaName":"",
                "CreateTime":"",
                "CreateTime1":"",
                "Status":"00"
            };
            request({
                url: "/Report/GetAllLYMaInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(datasources)}
            }, function (Body) {
                var tableData = getTableData("list4");
                for(var i in tableData){
                    if(tableData[i].Batch == batch){
                        toastr.warning("不能添加相同的物料批次");
                        return;
                    }
                }
                addSrow("list4",Body.Data);
            });
        }
    });
    //保存
    $("#save").on("click",function () {
        var data = {};
        if($("#haveWorkOrder").prop("checked")) {
            data.DPType = "00";
            if ($("#outWorkNum").getseldata().WoCode == null || $("#outWorkNum").getseldata().WoCode == undefined || $("#outWorkNum").getseldata().WoCode == "") {
                toastr.warning("调出工单号不能为空");
                return;
            }
            data.MOWoCode = $("#outWorkNum").getseldata().WoCode;
        }else{
            data.DPType = "01";
            data.MOWoCode = $("#materBatch").val();
        }
        if ($("#inWorkNum").getseldata().WoCode_ == null || $("#inWorkNum").getseldata().WoCode_ == undefined || $("#inWorkNum").getseldata().WoCode_ == "") {
            toastr.warning("调入工单号不能为空");
            return;
        }
        if($("#outWorkNum").getseldata().WoCode == $("#inWorkNum").getseldata().WoCode_){
            toastr.warning("调入工单和调出工单不能相同");
            return;
        }
        data.MIWoCode = $("#inWorkNum").getseldata().WoCode_;
        var BaInfos = [];
        var tableData = getTableData("list4");
        for(var i in tableData){
            var baInfo = {
                Batch:tableData[i].Batch
            };
            BaInfos.push(baInfo);
        }
        data.BaInfo = BaInfos;
        console.log(data);
        request({url:"/YM/SaveYMInfo",data:{"ExecType":"00","BusData":JSON.stringify(data)}},function (Body) {
            toastr.success(Body.MsgDes);
        })
    });
    $(".cDel").on("click",function () {
       delTr("list4");
    });
});