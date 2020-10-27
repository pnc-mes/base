/**
 * Created by whl on 2017/6/27.
 */
$(function(){
    /*主页面的表格定义*/
    var colNamesArr = [
        {"Caption": "MaVerRd", "Name": "WoBRd", "Hidden": true},
        {"Caption": "批次号", "Name": "Batch", 'Editable': false},
        {"Caption": "生产数量", "Name": "CanNum", "Editable": false},
        {"Caption": "单位", "Name": "UnitName", "Editable": false},
        {"Caption": "计划完成日期", "Name": "JFDate","Editable": false},
        {"Caption": "实际完工日期", "Name": "SFDate","Editable": false},
        {"Caption": "状态", "Name": "Status","Editable": false}
    ];
    var colNamesArr2 = [
        {"Caption": "批次号", "Name": "Batch", "Editable":true, "CType":"text", Width: 80},
        {"Caption": "数量", "Name": "Num", "Editable":true, "CType":"text", Width:50}
    ];
    var config1 = {
        tableId: 'list5',
        divId:'pager5',
        data: [],
        colArr: colNamesArr,
        multiselect: false,
        width: 0.98
    };
    var config2 = {
        tableId: 'list6',
        divId:'pager6',
        data: [],
        colArr: colNamesArr2,
        multiselect: true,
        width: 0.45
    };
    fullTable(config1);//加载空表格
    fullTable(config2);
    //时间插件
    $("#startDate").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm-dd",//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        dateFormat: 'yy-mm-dd'
    });
    $("#startDate").val("");


    $("#finishDate").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm-dd",//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        dateFormat: 'yy-mm-dd'
    });

    /* *************************** 请求工单列表信息***************************/
    //工单下拉框
    var params_wo = {
        "displaymode": "1",
        "title": "工单",
        "binddata": {
            "keyfield": "WoRd",
            "fields": [
                {
                    "caption": "工单id",
                    "name": "WoRd"
                }, {
                    "caption": "工单名称",
                    "name": "WoCode"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onclick":function (res) {
                //清空工单数量
                $("#WoCount").val("");

                //清空产品名称
                $("#MaVer").val("");
                $("#MaVer").removeAttr("ruid");

                //清空日期
                $("#startDate").val("");
                $("#finishDate").val("");
                //清空打印信息
                //清空打印机
                var woRd = res.WoRd;
                if(woRd != undefined && woRd != ""){
                    //请求详细工单信息
                    request({url:"/WO/GetWOInfo",data: {"ExecType": "00", "BusData": "{\"WoRd\":" + woRd + "}"}},function(Body){

                        $("#WoCount").val(Body.Data.Num);
                        $('#UnCBatNum').val(Body.Data.UnCBatNum);
                        $("#AllNum").val("1");
                        $("#Num").val(Body.Data.UnCBatNum);
                        var maInfo = Body.Data.MaInfo;
                        if(maInfo != undefined && maInfo != null && maInfo != ""){
                            $("#MaVer").val(maInfo.MaName);
                            $("#MaVer").attr("ruid", maInfo.MaVerRd);

                            //date
                            $("#startDate").val(Body.Data.JStartDate.split(" ")[0]);
                            $("#finishDate").val(Body.Data.JFinishDate.split(" ")[0]);

                            loadWf(maInfo.MaVerRd, Body.Data.WFInfo.WFVerRd);
                        }
                    });

                    /*加载关联批次表格信息*/
                    loadAllWoBInfo(woRd);
                }
            },
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"WoCode",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName": "WoCode",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00", "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/WO/GetAllWOInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WoRd": datas[i].WoRd,
                            "WoCode": datas[i].WoCode,
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
    $("#WoCode").zc_select(params_wo);


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

    //加载关联批次表格
    function loadAllWoBInfo(woRd){
        request({url:'/WO/GetAllWOBInfo',data:{"ExecType":'00',"BusData":"{\"WoRd\":"+woRd+"}"}},function(Body){
            if(Body.Data.length>0){
                config1.data=Body.Data;
            }else{
                config1.data=[];
            }
            fullTable(config1);
        });
    }

    //获取工艺流程信息
    function loadWf(MaVerRd, WFVerRd){

        $("#Unit").removeAttr("ruid");
        $("#Unit").text("");

        //$("#SpecVer").val("");
        //$("#SpecVer").html("");

        request({url:"/Commom/GetCMWFInfo",data: {"ExecType": "00", "BusData": "{\"MVerRd\":" + MaVerRd + "}"}},function(Body){

            var unitinfo = Body.Data.MaInfo.UnitInfo;
            var wfinfo = Body.Data.WFInfo;

            if(unitinfo != undefined &&　unitinfo != ""){
                $("#Unit").attr("ruid", unitinfo.UnitRd);
                $("#Unit").text(unitinfo.UnitName);
            }

            if(wfinfo != undefined && wfinfo != ""){
                /*$("#WfVer").attr("ruid", wfinfo.WFVerRd);
                $("#WfVer").val(wfinfo.WFName);*/

                for(var i=0; i<wfinfo.length; i++){
                    if(wfinfo[i].WFVerRd == WFVerRd){
                        $("#WfVer").attr("ruid", wfinfo[i].WFVerRd);
                        $("#WfVer").val(wfinfo[i].WFName);
                        loadSpec(wfinfo[i].SpecInfo);
                        return;
                    }
                }
                //loadSpec(wfinfo.SpecInfo);
            }
        });
    }

    //保存/新增
    $(".cSave").click(function () {

        var data = checkParams();

        if(data != undefined && data != null){
            var IsPrintPack = "01";
            if($("#ifPackage").is(":checked")){
                IsPrintPack = "00";
            }

            var objBusData={
                "WoSource": "01",
                "WoRd": data.woRd,
                "UnitRd": data.unitRd,
                "MaVerRd": data.maVerRd,
                "WFVerRd": data.wfVerRd,
                "SpecVerRd": data.specVerRd,
                "PrintTRd": data.printTRd,
                "PrintRd": data.printRd,
                "SplitBCount": data.allNum,
                "BCount": data.num,
                "IsABatch": data.isABatch,
                "BInfo": data.bInfo,
                "IsPrint": "00",
                "IsPrintPack": IsPrintPack,
                "JStartDate": $("#startDate").val(),
                "JFinishDate": $("#finishDate").val(),
                "Remark": $("#beizhu").text()
            };

            request({url:"/Batch/SaveBatchInfo",data: {"ExecType": "00", "BusData": JSON.stringify(objBusData)}},function(Body){

                toastr.success(Body.MsgDes);
                loadAllWoBInfo(objBusData.WoRd);
                if(!$("#IsABatch").is(':checked')){
                    $("#IsABatch").click();
                }
                //请求详细工单信息
                request({url:"/WO/GetWOInfo",data: {"ExecType": "00", "BusData": "{\"WoRd\":" + objBusData.WoRd + "}"}},function(Body){
                    $("#WoCount").val(Body.Data.Num);
                    $('#UnCBatNum').val(Body.Data.UnCBatNum);
                });
            });
        }
    });

    //加载工序
    var specData = [];
    function loadSpec(data){
        specData = data;

        if(data.length > 0) {
            $("#SpecVer").showData({
                id: data[0].SpecVerRd,
                name: data[0].SpecName,
                keyfield: "SpecVerRd",
                fields: [
                    {"name": "SpecVerRd"},
                    {"name": "SpecName"}
                ]
            });
        }
    }
    var params_spec = {
        "displaymode": "0",
        "title": "选择工序",
        "binddata": {
            "keyfield": "SpecVerRd",
            "fields": [
                {
                    "caption": "工序id",
                    "name": "SpecVerRd"
                }, {
                    "caption": "工序名称",
                    "name": "SpecName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var obj = {
                    data:specData,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#SpecVer").zc_select(params_spec);

    //自动生成批次切换事件
    $("#IsABatch").click(function(){
        var $this = $(this);
        if($this.is(':checked')){
            //不显示
            $("#tab_4_li").css("display","none").prev().addClass("active");
            $("#tab_4").removeClass("active").prev().addClass("active");
            $("#AllNum").attr("readOnly", false);
            $("#Num").attr("readOnly", false);
            $("#AllNum").val("1");
            $("#Num").val($("#UnCBatNum").val());
        }else{
            //显示
            $("#tab_4_li").css("display","block").addClass("active").siblings().removeClass("active");
            $("#tab_4").addClass("active").siblings().removeClass("active");
            $("#AllNum").attr("readOnly", true);
            $("#Num").attr("readOnly", true);
            $("#AllNum").val("0");
            $("#Num").val("1");
        }
        deallrow("list6");
    });

    //新增
    $(".cAdd").click(function(){
        var data = {
            Batch: "",
            Num: "1"
        };
        addSrow("list6", data, "first");
        $("#AllNum").val($("#list6 tr").length - 1);
    });
    //删除
    $(".cDel").click(function(){
        derow("list6");
        $("#AllNum").val($("#list6 tr").length - 1);
    });

    //检查
    function checkParams(){
        //工单RD
        var woRd = $("#WoCode").getseldata().WoRd;
        if(woRd == undefined || woRd == ""){
            toastr.warning("工单不能为空");
            return;
        }

        //物料版本RD
        var maVerRd = $("#MaVer").attr("ruid");
        if(maVerRd == undefined || maVerRd == ""){
            toastr.warning("产品不能为空");
            return;
        }

        //WFVerRd
        var wfVerRd = $("#WfVer").attr("ruid");
        if(wfVerRd == undefined || wfVerRd == ""){
            toastr.warning("工艺流程不能为空");
            return;
        }

        //SpecVerRd
        var specVerRd = $("#SpecVer").getseldata().SpecVerRd;
        if(specVerRd == undefined || specVerRd == ""){
            toastr.warning("工序不能为空");
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

        //是否自动创批
        var isABatch = "00";
        if(!$("#IsABatch").is(':checked')){
            isABatch = "01";
        }

        var bInfo = getRowData("list6");
        var set = new Set();
        for(var i=0; i<bInfo.length; i++){
            var obj = bInfo[i];
            if(obj.Batch.trim() == "" || obj.Num.trim() == ""){
                toastr.warning("批次或数量不能为空");
                return;
            }
            set.add(obj.Batch.trim());
        }
        if(set.size < bInfo.length){
            toastr.warning("请勿添加重复批次");
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
            woRd: woRd,
            maVerRd: maVerRd,
            wfVerRd: wfVerRd,
            specVerRd: specVerRd,
            allNum: allNum,
            num: num,
            unitRd: unitRd,
            isABatch: isABatch,
            bInfo: bInfo,
            printTRd: printTRd,
            printRd: printRd
        };

        return data;
    }
});