/**
 * Created by xfxi on 2017/7/11.
 */
$(function(){

    //表格定义
    var colNamesArr1 = [
        { "Caption":"拆后批次号", "Name":"SplitBatch", "CType":"text"},
        { "Caption":"不良标记", "Name":"BadFlag", "CType":"text"},
        { "Caption":"数量", "Name":"Num", "Editable":true, "CType":"text"}
    ];

    var colNamesArr2 = [
        { "Caption":"批次", "Name":"Batch", "CType":"text"},
        { "Caption":"数量", "Name":"Num", "CType":"text",Width:80},
        { "Caption":"单位", "Name":"UnitName", "CType":"text",Width:50},
        { "Caption":"拆批人", "Name":"Splitor", "CType":"text"},
        { "Caption":"拆批时间", "Name":"SplitTime", "CType":"text"}
    ];

    var config1 = {
        tableId: "list4",
        data: [],
        colArr:colNamesArr1,
        multiselect:true,
        width:0.98
    };

    var config2 = {
        tableId: "list5",
        data: [],
        colArr:colNamesArr2,
        width:0.98
    };


    fullTable(config1);
    fullTable(config2);

    var batchList = null;
    // 获取所有的批次号
    /*request({url:"/Batch/GetAllBatchInfo",data:{"ExecType":"00"}},function (Body) {
        var printModelNameList = $("#printModelNameList");
        batchList = Body.Data;
        for (var i = 0; i < Body.Data.length; i++){
            printModelNameList.append("<option value='"+ Body.Data[i].Batch +"' />");
        }
    });*/
    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#batch").is(":focus")){
                var batch = $("#batch").val().trim();
                if(batch != undefined && batch != ""){
                    getSUBInfo(batch);
                }
            }
        }
    });

    /*$("#printModelName").on("blur",function () {
        var _batch = null;
        if($(this).val().trim() != "") {
            for (var i = 0; i < batchList.length; i++) {
                if ($(this).val() == batchList[i].batch) {
                    _batch = batchList[i].batch;
                    getSUBInfo(batchList[i].batch);
                    return;
                }
            }
        }else{
            return;
        }
        if(_batch == null){
            toastr.warning("该批次不存在!");
        }
    });*/

    //加载打印模板
    var params = {
        "displaymode": "0",
        "title": "打印模板",
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
        "event": {
            "onseardata": function (o) {
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
                var xldata = [];
                request({url:"/PrintT/GetAllPtInfo",data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},function(Body){

                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PtRd": datas[i].PtRd,
                            "PtName": datas[i].PtName,
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

    //加载打印机
    var params1 = {
        "displaymode": "0",
        "title": "打印机",
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
        "event": {
            "onseardata": function (o) {
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
                var xldata=[];
                request({url:"/Printer/GetAllPrInfo",data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PrRd": datas[i].PrRd,
                            "PrName": datas[i].PrName,
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
    $("#defaultSelect1").zc_select(params1);

    //新增按钮点击事件
    $(".cAdd").click(function(){
        var batch = $("#batch").val().trim();//$("#printModelName").val();
        if(batch == undefined || batch == ""){
            toastr.warning("请选择批次");
            return;
        }
        var sr = "";
        if(splitBatchKey.length > 0){
            splitBatchKey.sort(function (x, y) {
                if (x < y) {
                    return -1;
                }
                if (x > y) {
                    return 1;
                }
                return 0;
            });
            sr = splitBatch.get(splitBatchKey[0]);
            splitBatch.delete(splitBatchKey[0]);
            splitBatchKey.shift();
        }else{
            sr = getCMSR(batch);
        }
        var data = {
            SplitBatch: sr,
            BadFlag: $("#spType").val() == '0' ? '是':'否',
            Num: ""
        };
        addSrow("list4", data, "first");
    });

    //删除按钮点击事件
    $(".cDel").click(function(){

        $("#list4 .cbox:checked").each(function(){
            var s = $(this).parent().next().text().split("-");
            if(s.length >= 2) {
                var batchNum = Number(s[s.length - 1]);
                console.log(batchNum);
                splitBatchKey.unshift(Number(batchNum));
                splitBatch.set(Number(batchNum), $(this).parent().next().text());
            }
        });

        derow("list4");
    });

    //拆分按钮点击事件
    $(".cSplit").click(function(){
        var batch = $("#batch").val().trim();//$("#printModelName").val();
        if(batch != undefined &&　batch != ""){
            var m_tableData = getRowData("list4");
            if(m_tableData != undefined &&　m_tableData.length >0){

                m_tableData.forEach(function(obj){
                   obj.BadFlag = obj.BadFlag == "是" ? "00":"01";
                });

                //获取打印机、打印模板
                var printTRd = "";
                var printRd = "";
                //是否打印
                var isPrint = "01";
                if($("#isPrint").is(":checked")){
                    isPrint = "00";

                    printTRd = $("#defaultSelect").getseldata().PtRd;
                    if(printTRd == undefined){
                        toastr.warning("打印模板不能为空");
                        return;
                    }

                    printRd = $("#defaultSelect1").getseldata().PrRd;
                    if(printRd == undefined){
                        toastr.warning("打印机不能为空");
                        return;
                    }
                }

                var busData = {
                    Batch: batch,
                    SplitInfo: m_tableData,
                    PrintTRd: printTRd,
                    PrintRd: printRd,
                    IsPrint: isPrint
                };

                saveSUBInfo(JSON.stringify(busData));
            }else{
                toastr.warning("请完善拆批信息");
            }
        }else{
            toastr.warning("批次不能为空");
        }
    });

    //批次拆分最大值
    var splitMaxNum = 0;
    //删除过的批次
    var splitBatch = new Map();
    var splitBatchKey = [];

    //获取批次拆分、合并信息
    function getSUBInfo(batch){

        splitMaxNum = 0;
        splitBatch = new Map();
        splitBatchKey = [];

        var _data = {
            Batch: batch
        };

        request({url:"/Batch/GetSUBInfo",data: {"ExecType": "00", "BusData": JSON.stringify(_data)}},function(Body){

            $("#MaVerRd").val(Body.Data.MaVerRd);
            $("#MaCode").val(Body.Data.MaCode);
            $("#MaName").val(Body.Data.MaName);
            $("#Num").val(Body.Data.Num);
            $("#BadNum").val(Body.Data.BadNum);
            $("#UnitName").val(Body.Data.UnitName);
            $("#WoCode").val(Body.Data.WoCode);

            //打印机、打印模板(选中)
            if(Body.Data.PrintTRd != undefined ){
                $("#defaultSelect").showData({
                    id:Body.Data.PrintTRd,
                    name:Body.Data.PtName,
                    keyfield:"PtRd",
                    fields:[
                        {"name":"PtRd"},
                        {"name":"PtName"}
                    ]
                });
            }
            if(Body.Data.PrintRd != undefined ){
                $("#defaultSelect1").showData({
                    id:Body.Data.PrintRd,
                    name:Body.Data.PrName,
                    keyfield:"PrRd",
                    fields:[
                        {"name":"PrRd"},
                        {"name":"PrName"}
                    ]
                });
            }

            var refBatch = Body.Data.RefBatch;
            refBatch.forEach(function(obj){
                var s = obj.Batch.split("-");
                if(s.length >= 2){
                    var batchNum = Number(obj.Batch.split("-")[s.length - 1]);
                    if(splitMaxNum < batchNum){
                        splitMaxNum = batchNum;
                    }
                }
            });

            //关联批次信息
            var config2 = {
                tableId: "list5",
                data: refBatch,
                colArr:colNamesArr2,
                width:0.98
            };

            fullTable(config2);
        });

        deallrow("list4");
    }

    //获取序号信息
    function getCMSR(batch){

        splitMaxNum += 1;

        var num = String(splitMaxNum);

        var sr = "" + batch + "-";

        if(num.length == 1){
            sr += "00" + num;
        }else if(num.length == 2){
            sr += "0" + num;
        }else{
            sr += num;
        }

        return sr;
    }

    //保存拆分、合并批次
    function saveSUBInfo(busData){

        request({url:"/Batch/SaveSUBInfo",data: {"ExecType": "00", "BusData": busData}},function(Body){

            toastr.success(Body.MsgDes);
            getSUBInfo($("#batch").val());
        });
    }
});