/**
 * Created by xfxi on 2017/7/19.
 */

$(function(){

    var rePrInfo = sessionStorage.getItem("data");

    sessionStorage.removeItem("data");

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
    $("#defaultSelect").zc_select(params_print);

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
    //$("#PrintT").zc_select(params_printT);

    //确认打印
    $("#confirm").click(function(){

        //printRd
        var printRd = $("#defaultSelect").getseldata().PrRd;
        if(printRd == ""){
            layer.msg("打印机不能为空");
            return;
        }

        //获取打印机、打印模板
        /*var printTRd = $("#defaultSelect").getseldata().PtRd;
        if(printTRd == undefined){
            toastr.warning("打印模板不能为空");
            return;
        }*/

        var busData = {
            RePrInfo: JSON.parse(rePrInfo),
            PrintRd: printRd,
            //PrintTRd: printTRd,
            PrintCount: $("#printCount").val(),
            PrintCopy: $("#printCopy").val()
        };

        saveRePr(busData);
    });

    //重打
    function saveRePr(busData){
        request({url:"/HisPrintLog/SaveRePrInfo",data: {"ExecType": "00", "BusData": JSON.stringify(busData)}},function(Body){
            storage.setItem("sure","0");
            parent.layer.closeAll("iframe");
        });
    }
});