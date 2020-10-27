/**
 * Created by test on 2017/7/14.
 */
/*------------处理表格----------------------------*/
$(function() {

    //表格定义
    var colNamesArr5=[
        { "Caption":"采购单明细Rd", "Name":"PurChDlRd", "CType":"text", "Hidden": true},
        { "Caption":"物料Rd", "Name":"MaVerRd", "CType":"text", "Hidden": true},
        { "Caption":"物料代码", "Name":"MaCode", "CType":"text"},
        { "Caption":"物料名称", "Name":"MaName", "CType":"text"},
        { "Caption":"物料描述", "Name":"MaDes", "CType":"text"},
        { "Caption":"应收数量", "Name":"AllNum", "CType":"text","Editable":true,Width:80},
        { "Caption":"到货日期", "Name":"ArrivalTime", "CType":"text"},
        { "Caption":"创批数", "Name":"SplitBCount", "CType":"text", "Editable":true,Width:80},
        {  "Caption":"每批数量", "Name":"BCount", "CType":"text", "Editable":true,Width:80},
        {  "Caption":"创批结果", "Name":"Solution", "CType":"text"},
        {  "Caption":"打印尾批", "Name":"IsPrintWP", "CType":"checkbox", "Editable":true, "Hidden": true},
        {  "Caption":"单位", "Name":"Unit", "CType":"text",Width:80},
        {  "Caption":"包裹批次", "Name":"IsPrintPack", "CType":"checkbox", "Editable":true, "Hidden": true},
        { "Caption":"甲供料", "Name":"F1", "CType":"text", "Hidden": true}
    ];
    var colNamesArr5_1=[
        { "Caption":"采购单明细Rd", "Name":"PurChDlRd", "CType":"text", "Hidden": true},
        { "Caption":"物料Rd", "Name":"MaVerRd", "CType":"text", "Hidden": true},
        { "Caption":"物料代码", "Name":"MaCode", "CType":"text"},
        { "Caption":"物料名称", "Name":"MaName", "CType":"text"},
        { "Caption":"物料描述", "Name":"MaDes", "CType":"text"},
        { "Caption":"应收数量", "Name":"AllNum", "CType":"text"},
        { "Caption":"入库数量", "Name":"ScanNum", "CType":"text"},
        { "Caption":"到货日期", "Name":"ArrivalTime", "CType":"text", "Hidden": true},
        { "Caption":"创批数", "Name":"SplitBCount", "CType":"text", "Editable":true,Width:80},
        {  "Caption":"每批数量", "Name":"BCount", "CType":"text","Editable":true,Width:80},
        {  "Caption":"创批结果", "Name":"Solution", "CType":"text", "Hidden": true},
        {  "Caption":"打印尾批", "Name":"IsPrintWP", "CType":"checkbox", "Editable":true, "Hidden": true},
        {  "Caption":"单位", "Name":"Unit", "CType":"text",Width:80},
        {  "Caption":"包裹批次", "Name":"IsPrintPack", "CType":"checkbox", "Editable":true, "Hidden": true},
        { "Caption":"甲供料", "Name":"F1", "CType":"text", "Hidden": true}
    ];

    var alldata = [];

    var config5={
        tableId: "list5",
        data: [],
        colArr:colNamesArr5,
        multiselect:true,
        width:0.97
    };
    fullTable(config5);

    //判断原材料入库依据
    var f = true; //true已采购单为准，false以来料收货通知单为准

    request({url:"/Commom/GetCMGCInfo",data: {"ExecType": "00", "BusData": "{\"ModeName\": \"M1\"}"}},function(Body){
        var data = Body.Data;
        if(data != undefined && data.length > 0 && data[0].Value == "M1_01"){
            f = false;
        }
    });

    if(!f){
        $("#Ichs").css("display", "");
    }

    /* -----------------------------------------供应商--------------------------------------- */
    //分页数据
    var currentPage = 0;
    var PageInfo = {
        "PageSize":100,
        "PageIndex":currentPage,
    };

    var params = {
        "displaymode": "1",
        "title": "供应商",
        "binddata": {
            "keyfield": "SupRd",
            "fields": [
                {
                    "caption": "供应商id",
                    "name": "SupRd"
                }, {
                    "caption": "供应商名称",
                    "name": "SupName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onclick":function (res) {
                $("#PurChCode").clearseldata("PurChRd");
                clearAll();

                //$("#PurChCode").html("");
            },
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"SupplierName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName": "SupplierName",
                            "FieldOpt": "Order BY"
                        },{
                            "FieldName":"Status",
                            "FieldOpt":"=",
                            "FieldVal":"00"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Supplier/GetAllSupInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SupRd": datas[i].SupRd,
                            "SupName": datas[i].SupName,
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
    $("#Supplier").zc_select(params);

    /* --------------------------------------------------------------------------------- */

    //加载打印模板
    var params_printT = {
        "displaymode": "0",
        "title": "选择打印模板",
        "binddata": {
            "keyfield": "PtRd",
            "fields": [
                {
                    "caption": "打印模板id",
                    "name": "PtRd"
                }, {
                    "caption": "打印模板名称",
                    "name": "PtName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"TempName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00", "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/PrintT/GetAllPtInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PtRd": datas[i].PtRd,
                            "PtName": datas[i].PtName,
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
    $("#PrintT").zc_select(params_printT);

    //加载打印机
    var params_print = {
        "displaymode": "0",
        "title": "选择打印机",
        "binddata": {
            "keyfield": "PrRd",
            "fields": [
                {
                    "caption": "打印机id",
                    "name": "PrRd"
                }, {
                    "caption": "打印机名称",
                    "name": "PrName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"PrinterName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00", "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Printer/GetAllPrInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PrRd": datas[i].PrRd,
                            "PrName": datas[i].PrName,
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
    $("#Print").zc_select(params_print);

    //新增
    $("#add").click(function(){
        clearForm("mallota");
        alldata = [];

        $("#list5").jqGrid("clearGridData");
    });

    //生成批次打印
    $("#batchPrint").click(function(){
        var busData = checkParams();

        if(busData != undefined && busData != null){
            saveBatch(JSON.stringify(busData));
        }
    });

    //搜索
    $("#search").keyup(function(){
        var name = $("#search").val().trim();

        var json = alldata.filter(function(obj){
            if(obj.MaName.indexOf(name) >= 0 || obj.MaCode.indexOf(name) >= 0
            || obj.MaDes.indexOf(name) >= 0){
                return true;
            }
        });

        if(f) {
            loadFirstTable(json);
        }else{
            loadFirstTable(json, colNamesArr5_1);
        }
    });

    //该页面清空方法
    function clearAll(){
        alldata = [];

        $("#list5").jqGrid("clearGridData");
    }

    //默认加载所有采购订单
    //采购单下拉框
    var params1 = {
        "displaymode": "1",
        "title": "采购单",
        "binddata": {
            "keyfield": "PurChRd",
            "fields": [
                {
                    "caption": "采购单id",
                    "name": "PurChRd"
                },{
                    "caption": "采购单号",
                    "name": "PurChCode",
                    "hidden": true
                },{
                    "caption": "合同号",
                    "name": "Remark"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onclick":function (res) {
                clearAll();

                var purChCode = res.PurChCode;
                if(purChCode == undefined || purChCode == ""){
                    return;
                }

                if(f) {
                    //加载采购单明细
                    var busData = {
                        ExecType: "00",
                        InitData: JSON.stringify({"FiledList":[{"FieldName":"PurChCode","FieldOpt":"=","FieldVal": purChCode}]})
                    };

                    loadPurchList(busData);
                }else{
                    //加载来料收货通知单
                    //loadIch(purChCode);
                    //$("#Ich").html("");
                    $("#Ich").clearseldata("InCRd");
                }
            },
            "onseardata":function (o) {
                var supname = $("#Supplier").getseldata().SupName;
                var filed = [
                    {
                        "FieldName":/*"CONCAT(IFNULL(Remark,''''),IFNULL(PurChCode,''''))"*/"Remark",
                        "FieldOpt":"like",
                        "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                    },
                    {"FieldName":"Status","FieldOpt":"in","FieldVal": "(00,01)"},
                    {"FieldName":"SStatus","FieldOpt":"=","FieldVal": "01"},
                    {"FieldName": "Remark", "FieldOpt": "Order BY"}
                ];
                if(supname != undefined && supname.trim() != ""){
                    var supobj = {"FieldName":"SupplierName","FieldOpt":"=","FieldVal": supname};
                    filed.push(supobj);
                }
                var InitData = {
                    "FiledList": filed
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Purch/GetAllPurchInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PurChRd": datas[i].PurChRd,
                            "PurChCode": datas[i].PurChCode,
                            "Remark": datas[i].Remark
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }/*,
            "onformatval": function(seldata){
                return seldata.Remark + "-" + seldata.PurChCode;
            }*/
        }
    };
    $("#PurChCode").zc_select(params1);

    //加载采购单明细
    function loadPurchList(busData){
        request({url:"/Purch/GetAllPDlInfo",data: busData},function(Body){
            alldata = [];
            var num = 0;

            Body.Data.forEach(function(obj){

                num = Number(obj.Num) - Number(obj.ScanNum);

                if(num > 0){
                    var o = {
                        "PurChDlRd": obj.PurChDlRd,
                        "MaVerRd": obj.MaVerRd,
                        "MaName": obj.MaName,
                        "MaDes": obj.MaDes == undefined ? "" : obj.MaDes,
                        "MaCode": obj.MaCode,
                        "AllNum": num,
                        "ArrivalTime": obj.ArrivalTime == undefined ? "" : new Date(obj.ArrivalTime).toLocaleDateString(),
                        "SplitBCount": 1,
                        "BCount": num,
                        "Unit": obj.UnitName,
                        "Solution": "",
                        "IsPrintWP": "00",
                        "IsPrintPack": "01",
                        "F1": obj.AFeed == "00" ? "甲供料":""
                    };
                    alldata.push(o);
                }
            });

            loadFirstTable(alldata);
        });
    }

    var params2 = {
        "displaymode": "1",
        "title": "收货单",
        "binddata": {
            "keyfield": "InCRd",
            "fields": [
                {
                    "caption": "收货单id",
                    "name": "InCRd"
                }, {
                    "caption": "收货单代码",
                    "name": "InCCode"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onclick":function (res) {
                clearAll();

                var inCCode = res.InCCode;
                if(inCCode == undefined || inCCode == ""){
                    return;
                }

                //加载来料单明细
                var busData = {
                    ExecType: "00",
                    InitData: JSON.stringify({
                        "FiledList":[
                            {"FieldName":"InCCode","FieldOpt":"=","FieldVal": inCCode},
                            {"FieldName": "InCCode", "FieldOpt": "Order BY"}
                        ]
                    })
                };

                loadIchList(busData);
            },
            "onseardata":function (o) {
                var filedList = [
                    {
                        "FieldName":"InCCode",
                        "FieldOpt":"like",
                        "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                    },
                    {"FieldName":"Status","FieldOpt":"in","FieldVal": "(00,01)"},
                    {"FieldName":"SStatus","FieldOpt":"=","FieldVal": "01"},
                    {"FieldName": "InCCode", "FieldOpt": "Order BY"}
                ];
                var purchcode = $("#PurChCode").getseldata().PurChCode;
                if(purchcode != undefined && purchcode != ""){
                    filedList.push({"FieldName":"PurChCode","FieldOpt":"=","FieldVal": purchcode});
                }
                var InitData = {
                    "FiledList": filedList
                };

                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Ich/GetAllIChInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "InCRd": datas[i].InCRd,
                            "InCCode": datas[i].InCCode
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
    $("#Ich").zc_select(params2);

    //加载来料收货明细
    function loadIchList(busData){
        request({url:"/Ich/GetAllIDlInfo",data: busData},function(Body){
            alldata = [];
            var num = 0;

            Body.Data.forEach(function(obj, idx){

                num = Number(obj.Num);// - Number(obj.ScanNum);
                if(num > 0 && obj.IsPrint == "01") {
                    var o = {
                        "PurChDlRd": obj.InCDlRd,
                        "MaVerRd": obj.MaVerRd,
                        "MaName": obj.MaName,
                        "MaCode": obj.MaCode,
                        "MaDes": obj.MaDes == undefined ? "" : obj.MaDes,
                        "ArrivalTime": obj.ArrivalTime == undefined ? "" : obj.ArrivalTime,
                        "AllNum": num,
                        "ScanNum": obj.ScanNum,
                        "SplitBCount": 1,
                        "BCount": num,
                        "Unit": obj.UnitName,
                        "Solution": "",
                        "IsPrintWP": "00",
                        "IsPrintPack": "01",
                        "F1": obj.AFeed == "00" ? "甲供料":""
                    };
                    alldata.push(o);
                }
            });

            loadFirstTable(alldata, colNamesArr5_1);
        });
    }

    //加载第一个表格
    function loadFirstTable(data, colName){
        if(colName == undefined){
            colName = colNamesArr5;
        }
        var config5={
            tableId: "list5",
            data: data,
            colArr: colName,
            multiselect:true,
            width:0.97,
            event: {
                oncellchange: function (data) {
                    var $this = data.row;
                    //总数
                    var allNum = $this.AllNum;
                    //拆批数
                    var splitBCount = $this.SplitBCount;
                    //每批数量
                    var bCount = $this.BCount;

                    if(allNum <= (splitBCount-1) * bCount){
                        toastr.warning("超过已收数量!");
                        return;
                    }
                    var flag  = false;
                    if(allNum < splitBCount * bCount){
                        flag = true;
                    }
                    var s = "";
                    for(var i=0; i<splitBCount; i++){
                        if(i == splitBCount -1 && flag){
                            s += String(allNum - (splitBCount-1) * bCount) + ",";
                        }else {
                            s += String(bCount) + ",";
                        }
                    }
                    if(allNum > 0){
                        s = s.substring(0, s.length - 1);
                    }
                    setCell("list5", {
                        rowid: data.rowed,
                        colname: "Solution",
                        celldata: s
                    });
                }
            }
        };

        fullTable(config5);
    }

    //物料批次创建
    function saveBatch(busData){

        request({url:"/Batch/SaveBatchInfo",data: {"ExecType": "03", "BusData": busData }},function(Body){
            toastr.success(Body.MsgDes);
            clearAll();
        });
    }

    //检查并返回数据
    function checkParams(){
        //工单来源
        var woSource = "";
        //工单
        var woRd = "";
        if(f){
            woSource = "02";

            woRd = $("#PurChCode").getseldata().PurChRd;
        }else{
            woSource = "03";

            woRd = $("#Ich").getseldata().InCRd;
        }
        if(woSource == ""){
            toastr.warning("工单来源不能为空");
            return;
        }
        if(woRd == undefined || woRd == ""){
            toastr.warning("工单不能为空");
            return;
        }

        var printTRd = $("#PrintT").getseldata().PtRd;
        if(printTRd == undefined || printTRd == ""){
            toastr.warning("打印模板不能为空");
            return;
        }

        var printRd = $("#Print").getseldata().PrRd;
        if(printRd == undefined || printRd == ""){
            toastr.warning("打印机不能为空");
            return;
        }

        var binfo = getSeRowData("list5");

        if(binfo == undefined || binfo.length == 0){
            toastr.warning("至少选择一个批次");
            return;
        }

        var b = true;

        binfo.forEach(function(obj){
            obj.WoDlRd = obj.PurChDlRd;
            obj.IsPrintPack = "01";
            obj.IsPrintWP = "00";

            if(obj.AllNum < (obj.SplitBCount-1) * obj.BCount){
                b = false;
                toastr.warning(obj.MaName + "拆批总数量大于应收总数");
                return;
            }
            delete obj.Unit;
            delete obj.MaName;
            delete obj.MaCode;
            delete obj.MaDes;
            delete obj.AllNum;
            delete obj.PurChDlRd;
            delete obj.ArrivalTime;
            delete obj.Solution;
            delete obj.ScanNum;
        });

        if(b){
            var busData = {
                WoSource: woSource,
                WoRd: woRd,
                BInfo: binfo,
                PrintTRd: printTRd,
                PrintRd: printRd,
                IsPrint: "00",
                Remark: ""
            };

            return busData;
        }

        return;
    }
});
