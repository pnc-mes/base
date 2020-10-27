/**
 * Created by panjunfeng on 2017/11/3.
 */
$(function(){

    //表格定义
    var colNamesArr=[
        { "Caption":"箱/批次号", "Name":"BarCode", "CType":"text",Width:100},
        { "Caption":"类型", "Name":"BarType", "CType":"text", "Hidden": true,Width:100},
        { "Caption":"物料代码", "Name":"MaCode", "CType":"text"},
        { "Caption":"物料名称", "Name":"MaName", "CType":"text"},
        {  "Caption":"数量", "Name":"Num", "CType":"text",Width:80},
        {  "Caption":"单位", "Name":"UnitName", "CType":"text",Width:50}
    ];

    var config={
        tableId: "list",
        data: [],
        colArr:colNamesArr,
        width:0.97
    };
    fullTable(config);

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
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
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
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
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

    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#barCode").is(":focus")){
                getDP();
            }
        }
    });

    //保存
    $("#_save").click(function(){
       saveDP();
    });

    //获取打包信息
    function getDP() {
        var barCode = $("#barCode").val().trim();
        if(barCode == undefined || barCode == ""){
            toastr.warning("栈板/箱/批次号不能为空");
            return;
        }

        var flag = false;
        var tableData = getRowData("list");
        if(tableData != undefined){
            tableData.forEach(function(obj){
                if(obj.BarCode == barCode){
                    flag = true;
                    return;
                }
            });
        }

        if(flag){
            toastr.warning("栈板/箱/批次号已经存在");
            return;
        }

        var dPType = $("#packType").val();

        var busData = {
            BarCode: barCode,
            DPType: dPType
        };

        request({
            url: "/Pack/GetDPInfo",
            data: {"ExecType": "00", "BusData": JSON.stringify(busData)}
        }, function (Body) {

            addSrow("list", Body.Data, 0, true);
        });
    }

    //保存打包信息
    function saveDP(){
        //获取打印机、打印模板
        var printTRd = $("#defaultSelect").getseldata().PtRd;

        if(printTRd == undefined){
            toastr.warning("打印模板不能为空");
            return;
        }

        var printRd = $("#defaultSelect1").getseldata().PrRd;

        if(printRd == undefined){
            toastr.warning("打印机不能为空");
            return;
        }
        //是否打印
        var isPrint = "01";
        if($("#isPrint").is(":checked")){
            isPrint = "00";
        }

        var printCopy = Number($("#printCopy").val());
        if(!Number.isInteger(printCopy)){
            toastr.warning("打印份数不是整数");
            return;
        }

        var tableData = getRowData("list");
        if(tableData == undefined || tableData.length <= 0){
            toastr.warning("打包信息不能为空");
            return;
        }
        tableData.forEach(function(obj){
            delete obj.MaCode;
            delete obj.MaName;
            delete obj.Num;
            delete obj.UnitName;
        });

        var busData = {
            DPType: $("#packType").val(),
            BarInfo: tableData,
            IsPrint: isPrint,
            PrintTRd: printTRd,
            PrintRd: printRd,
            PrintCount: 1,
            PrintCopy: printCopy
        };

        request({
            url: "/Pack/SaveDPInfo",
            data: {"ExecType": "00", "BusData": JSON.stringify(busData)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
            var config={
                tableId: "list",
                data: [],
                colArr:colNamesArr,
                width:0.97
            };
            fullTable(config);
        });
    }
});