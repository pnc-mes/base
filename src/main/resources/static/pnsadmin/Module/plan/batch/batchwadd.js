/**
 * Created by whl on 2017/6/27.
 */
$(function(){

    //获取产品信息
    var params_mv = {
        "displaymode": "1",
        "title": "物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "物料id",
                    "name": "MaVerRd"
                },{
                    "caption": "物料代码",
                    "name": "MaCode"
                },{
                    "caption": "物料名称",
                    "name": "MaName"
                }, {
                    "caption": "物料描述",
                    "name": "MaDes"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onclick":function (res) {
                $("#Unit").removeAttr("ruid");
                $("#Unit").text("");
                //查询物料版本
                request({url:"/Material/GetMaInfo",data: {"ExecType": "01", "BusData": "{\"MaVerRd\":" + res.MaVerRd + "}"}},function(Body) {
                    var unitinfo = Body.Data.UnitInfo;
                    if(unitinfo != undefined &&　unitinfo != ""){
                        $("#Unit").attr("ruid", unitinfo.UnitRd);
                        $("#Unit").text(unitinfo.UnitName);
                    }
                });
            },
            "onformatval":function(data){
                console.log(data);
                return data.MaCode + "-" + data.MaName;
            },
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };

                var xldata = [];
                Materialinfo(InitData,page,xldata);
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#MaVer").zc_select(params_mv);

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

    //时间插件
    $("#startDate").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm-dd",//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        dateFormat:"yy-mm-dd"
    });
    $("#finishDate").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm-dd",//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        dateFormat:"yy-mm-dd"
    });

    //保存
    $(".cSave").click(function () {
        var data = checkParams();

        if(data != undefined && data != null){
            var IsPrintPack = "01";
            if($("#ifPackage").is(":checked")){
                IsPrintPack = "00";
            }

            var objBusData={
                "WoSource": "00",
                "WoRd": "",
                "UnitRd": data.unitRd,
                "MaVerRd": data.maVerRd,
                "WFVerRd": data.wfVerRd,
                "SpecVerRd": data.specVerRd,
                "PrintTRd": data.printTRd,
                "PrintRd": data.printRd,
                "SplitBCount": data.allNum,
                "BCount": data.num,
                "IsPrint": "00",
                "IsPrintPack": IsPrintPack,
                "JStartDate": $("#startDate").val(),
                "JFinishDate": $("#finishDate").val(),
                "Remark": $("#beizhu").text()
            };

            request({url:"/Batch/SaveBatchInfo",data: {"ExecType": "00", "BusData": JSON.stringify(objBusData)}},function(Body){

                toastr.success(Body.MsgDes);
            });
        }
    });



    //检查
    function checkParams(){

        //物料版本RD
        var maVerRd = $("#MaVer").getseldata().MaVerRd;
        if(maVerRd == undefined || maVerRd == ""){
            toastr.warning("产品不能为空");
            return;
        }

        //allNum
        var allNum = $("#AllNum").val();
        if(allNum == undefined || allNum == "" || isNaN(allNum)){
            toastr.warning("拆批数量填写有误");
            return;
        }

        //Num
        var num = $("#Num").val();
        if(num == undefined || num == "" || isNaN(num)){
            toastr.warning("批次数量填写有误");
            return;
        }

        //UnitName
        var unitRd = $("#Unit").attr("ruid");
        if(unitRd == undefined || unitRd == ""){
            toastr.warning("单位不能为空");
            return;
        }

        //日期检验
        var startDate = $("#startDate").val();
        var finishDate = $("#finishDate").val();
        if(((new Date(startDate.replace(/-/g,"\/"))) > (new Date(finishDate.replace(/-/g,"\/"))))){
            toastr.warning("起始时间不能大于结束时间");
            return;
        }

        //PrintTRd
        var printTRd = $("#PrintT").getseldata().PtRd;
        /*if(printTRd == undefined || printTRd == ""){
            toastr.warning("打印模板不能为空");
            return;
        }*/

        //PrintRd
        var printRd = $("#Print").getseldata().PrRd;
        /*if(printRd == undefined || printRd == ""){
            toastr.warning("打印机不能为空");
            return;
        }*/

        var data = {
            maVerRd: maVerRd,
            wfVerRd: 0,
            specVerRd: 0,
            allNum: allNum,
            num: num,
            unitRd: unitRd,
            printTRd: printTRd,
            printRd: printRd
        };

        return data;
    }
});