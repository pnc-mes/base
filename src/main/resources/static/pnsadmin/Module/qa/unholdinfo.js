/**
 * Created by PNC on 2017/6/27.
 */
$(function(){
    var Status=null;
    var colNamesArr=[
        {"Caption": "批次号", "Name": "Batch", "CType": "text", "Editable": false,Width:100},
        {"Caption": "工单号", "Name": "WoCode", "CType": "text", "Editable": false},
        {"Caption": "生产线别", "Name": "LineName", "CType": "text", "Editable": false},
        {"Caption": "产品/物料", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "数量", "Name": "Num", "CType": "text", "Editable": false,Width:80},
        {"Caption": "冻结原因", "Name": "ReaDes", "CType": "text", "Editable": false},
        {"Caption": "操作时间", "Name": "UpdateDate", "CType": "text", "Editable": false},
        {"Caption": "操作人", "Name": "UpdateMan", "CType": "text", "Editable": false},
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

    //解冻原因
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
                            "FieldVal":"07"
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
    };

    $("#defaultSelect").zc_select(params);

    //输入批次号，点击查询
    var $batchNum = $("#batchNum");
    var findBatch = function () {
        request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch":$batchNum.val()})}},function (Body) {
            var $batchNameTds = $("#list4 .Batch");//获取所有的批次号对应的td对象
            Status=Body.Data.Status;
            if(Status!="02"){
                toastr.warning("此批次未处于冻结状态");
                return false;
            }
            if($batchNameTds.length > 0){
                $batchNameTds.each(function () {//遍历td得到里面的值
                    if($(this).text() == $batchNum.val()){//如果输入的批次号和表格里面的批次号相等，则不添加
                        showWarn("该批次已经存在表格中");
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
    };
    /*$("#find").on("click",function(){
        findBatch();
    });

    $batchNum.on("keyup",function (e) {
        if(e.keyCode == "13")
            findBatch();
    });*/
    $("#inputs").click(function(){
        $("#Modals").modal("show");
    });

    $("#btn_save").click(function () {
        aa();
    });
    function aa(){
        if($("#mo").val() == undefined || $("#mo").val().trim() == ''){
            //$(".inputs").prev().val("'~'");
            $("#Modals").modal("hide");
        }else{
            var arr = $("#mo").val().split("\n");
            // for(var i=0;i<arr.length;i++){
            //     arr[i] = "'" + arr[i] + "'";
            // }
            // var strs = arr.join(',');
            // $(".inputs").prev().val(strs);
            // $('#mo').focus();
            $("#Modals").modal("hide");
        }
    }

    //查询事件
    $("#find").click(function(){
        var arr = $("#mo").val().split("\n");
        for(var i=0;i<arr.length;i++){
            arr[i] = arr[i].trim();
        }
        var strs = arr.join(',');
        var Batch=strs; //$("#tjtm").val().trim();
        var LineRd=$("#scxb").getseldata().LineRd;
        var dates = $("#date").val().split("~");
        var startDate = '', endDate = '';
        if(dates.length == 2){
            startDate = dates[0];
            endDate = dates[1];
        }
        loadData(Batch,LineRd,startDate,endDate);
    });

    //解冻
    $("#save").on("click",function(){
        //获取表格数据
        var data = getSeRowData("list4");
        if(data.length<=0){
            toastr.warning("请选中相应的批次号再提交!");
            return false;
        }

        var tables = [];
        for(var i in data){
            var table = {
                "Batch": data[i].Batch
            };
            tables.push(table);
        }
        var ReaDes = $("#defaultSelect").getseldata().ReaRd;
        if(ReaDes!=undefined) {
            var busData = {
                "ReaDes": ReaDes,
                "BatchInfo": tables
            };
            request({
                url: "/Batch/SaveBOptInfo",
                data: {"ExecType": "01", "BusData": JSON.stringify(busData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                //var Batch=$("#tjtm").val().trim();
                var arr = $("#mo").val().split("\n");
                for(var i=0;i<arr.length;i++){
                    arr[i] = arr[i].trim();
                }
                var strs = arr.join(',');
                var Batch=strs;
                var LineRd=$("#scxb").getseldata().LineRd;
                var dates = $("#date").val().split("~");
                var startDate = '', endDate = '';
                if(dates.length == 2){
                    startDate = dates[0];
                    endDate = dates[1];
                }
                loadData(Batch,LineRd,startDate,endDate);
            });
        }else{
            toastr.warning("解冻原因不能为空");
        }
    });

    //生产线别
    var scxb = {
        "displaymode": "0",
        "title": "生产线别",
        "binddata": {
            "keyfield": "LineRd",
            "fields": [
                {
                    "caption": "生产线别Rd",
                    "name": "LineRd"
                }, {
                    "caption": "生产线别名称",
                    "name": "LineName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"LineName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "LineName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Line/GetAllLineInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "LineRd": datas[i].LineRd,
                            "LineName": datas[i].LineName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#scxb").zc_select(scxb);

    laydate.render({
        elem: '#date',
        range: "~",
        type: 'datetime'
    });

    var loadData=function (Batch,LineRd,StartDate,EndDate) {
        var data={
            "Batch":Batch,
            "LineRd":LineRd,
            "StartDate":StartDate,
            "EndDate":EndDate
        }
        var objData = {
            "ExecType": "00",
            "BusData": JSON.stringify(data),
        };
        request({url: "/Batch/getHoldBatch", data: objData}, function (Body) {
            if(Body.Data.length>0){
                var config1 = {
                    tableId: 'list4',
                    data: Body.Data,
                    colArr: colNamesArr,
                    multiselect: true,
                    width:0.96,
                    height:0.73
                };
                fullTable(config1);//加载空表格
            }else {
                var config1 = {
                    tableId: 'list4',
                    data: [],
                    colArr: colNamesArr,
                    multiselect: true,
                    width:0.96,
                    height:0.73
                };
                fullTable(config1);//加载空表格
            }
        });

    };
    //loadData("","","","","","","");
});