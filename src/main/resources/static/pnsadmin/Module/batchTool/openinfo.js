/**
 * Created by liufuzhi on 2017/8/31.
 */

$(function(){
    var colNamesArr=[
        {"Caption": "批次号", "Name": "Batch", "CType": "text", "Editable": false,Width:100},
        {"Caption": "产品/物料", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "数量", "Name": "Num", "CType": "text", "Editable": false,Width:80},
        {"Caption": "单位", "Name": "UnitName", "CType": "text", "Editable": false,Width:50}
    ];
    var config={
        tableId: "list4",
        data: [],
        colArr:colNamesArr,
        multiselect:true,
        width:0.96,
        height:0.73
    };
    fullTable(config);

    //打开原因
    var params = {
        "displaymode": "0",
        "title": "原因类型",
        "binddata": {
            "keyfield": "ReaRd",
            "fields": [
                {
                    "caption": "原因id",
                    "name": "ReaRd"
                }, {
                    "caption": "原因描述",
                    "name": "ReaDes"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ReaType",
                            "FieldOpt":"=",
                            "FieldVal":"12"
                        },
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
                request({url:"/Reason/GetAllReaInfo",data:{"ExecType":"00","InitData":JSON.stringify(InitData),"PageInfo": JSON.stringify(page)}},function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "ReaRd": datas[i].ReaRd,
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
    //输入批次号，点击查询
    var $batchNum = $("#batchNum");
    var findBatch = function () {
        request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch":$batchNum.val()})}},function (Body) {
            var $batchNameTds = $("#list4 .Batch");//获取所有的批次号对应的td对象
            if($batchNameTds.length > 0){
                $batchNameTds.each(function () {//遍历td得到里面的值
                    if($(this).text() == $batchNum.val()){//如果输入的批次号和表格里面的批次号相等，则不添加
                        toastr.warning("该批次已经存在表格中");
                        return false;
                    }else{//不存在添加
                        var table = {
                            Batch:$batchNum.val(),
                            MaName:Body.Data.MaName,
                            Num:Body.Data.CanNum,
                            UnitName:Body.Data.UnitInfo.UnitName
                        };
                        addSrow("list4",table);
                    }
                });
            }
            else{
                var table = {
                    Batch:$batchNum.val(),
                    MaName:Body.Data.MaName,
                    Num:Body.Data.CanNum,
                    UnitName:Body.Data.UnitInfo.UnitName
                };
                addSrow("list4",table);
            }
        })
    }
    $batchNum.on("keyup",function (e) {
        var config={
            tableId: "list4",
            data: [],
            colArr:colNamesArr,
            multiselect:true,
            width:0.96,
            height:0.73
        };
        fullTable(config);
        findBatch();
    })
    //打开
    $("#save").on("click",function(){
        //获取表格数据
        var data = getRowData("list4");
        var tables = [];
        for(var i in data){
            var table = {
                "Batch": data[i].Batch
            };
            tables.push(table);
        }
        var ReaRd=$("#defaultSelect").getseldata().ReaRd;
        if(ReaRd!= "") {
            var busData = {
                "ReaDes":$("#defaultSelect").getseldata().ReaDes,
                "BatchInfo": tables
            };
            request({
                url: "/Batch/SaveBOptInfo",
                data: {"ExecType": "04", "BusData": JSON.stringify(busData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
            });
        }else{
            $("#defaultSelect").css("border-color","red");
            $("#defaultSelect").prop("placeholder","不能为空！");
        }
    });
    //删除
    $(".del1").on("click",function(){
        derow("list4");
    })

});