/**
 * Created by PNC on 2017/6/27.
 */
$(function(){
    var Status=null;
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

    //冻结原因
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
                            "FieldVal":"06"
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
                request({url:"/Reason/GetAllReaInfo",data:{"ExecType":"00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},function (Body) {
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
        request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch": $batchNum.val().trim()})}},function (Body) {
            Status=Body.Data.Status;
            if(Status=="02"){
                toastr.warning("此批次已处于冻结状态");
                return false;
            }

            var arr = getRowData("list4");
            var b_ = $batchNum.val().trim();
            for(var i=0, len=arr.length; i<len; i++){
                if(b_ == arr[i].Batch){
                    toastr.warning("此批次已存在表格中");
                    return;
                }
            }

            var table = {
                Batch: b_,
                MaName:Body.Data.MaName,
                Num:Body.Data.CanNum,
                UnitName:Body.Data.UnitInfo.UnitName
            };
            addSrow("list4",table);
        })
    };
    $("#find").on("click",function(){
        findBatch();
    });
    $batchNum.on("keyup",function (e) {
        if(e.keyCode == "13")
            findBatch();
    });
    //冻结
    $("#save").on("click",function(){
        //获取表格数据
        var data = getRowData("list4");
        if(data.length<=0){
            toastr.warning("请输入相应的批次号再提交!");
            return false;
        }

        var tables = [];
        for(var i in data){
            var table = {
                "Batch": data[i].Batch
            };
            tables.push(table);
        }
        var ReaDes = $("#defaultSelect").getseldata().ReaDes;

        if(ReaDes!= undefined) {
            var busData = {
                "ReaDes": ReaDes,
                "BatchInfo": tables
            };
            request({
                url: "/Batch/SaveBOptInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(busData)}
            }, function (Body) {
                Status="02";
                deallrow("list4");
                toastr.success(Body.MsgDes);
            });
        }else{
            toastr.warning("冻结原因不能为空");
        }
    });
    //删除
    $(".del1").on("click",function(){
        derow("list4");
    });

    $("#inputs").click(function(){
        $("#Modals").modal("show");
    });

    $("#btn_save").click(function () {
        var arrp = $("#mo").val().split("\n");
        for(var i=0; i<arrp.length; i++){
            request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch": arrp[i].trim()})}},function (Body) {
                Status = Body.Data.Status;
                if (Status == "02") {
                    toastr.warning("此批次已处于冻结状态");
                    return false;
                }

                var arr = getRowData("list4");
                var b_ = arrp[i].trim();
                for (var j = 0, len = arr.length; j < len; j++) {
                    if (b_ == arr[j].Batch) {
                        toastr.warning("此批次已存在表格中");
                        return;
                    }
                }

                var table = {
                    Batch: b_,
                    MaName: Body.Data.MaName,
                    Num: Body.Data.CanNum,
                    UnitName: Body.Data.UnitInfo.UnitName
                };
                addSrow("list4", table);
            });
        }
        $("#Modals").modal("hide");
    });
});