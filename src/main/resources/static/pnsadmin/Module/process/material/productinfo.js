/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理JS
 * 创建人：潘俊峰
 * 创建时间：2017-06-27
 * 修改人：
 * 修改时间：
 */
$(function () {
    var parentTreeID = null;
    var childTreeID = null;





    //左边数据显示规则
    var ruleMaVer = [{
        "ctlid": "MaCode",
        "param": "MaCode"
    }, {
        "ctlid": "MaName",
        "param": "MaName"
    }, {
        "ctlid": "Version",
        "param": "Version"
    },{
        "ctlid": "MaDes",
        "param": "MaDes"
    },{
        "ctlid": "CusMaCode",
        "param": "CusMaCode"
    },{
        "ctlid": "MinSNum",
        "param": "MinSNum"
    },{
        "ctlid": "MaxSNum",
        "param": "MaxSNum"
    },{
        "ctlid": "creatPeople",
        "param": "Creator"
    }, {
        "ctlid": "creatTime",
        "param": "CreateTime"
    }, {
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    }, {
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    }, {
        "ctlid": "beizhu",
        "param": "Remark"
    }];
    //获取序号规则
    var params_sr = {
        "displaymode": "0",
        "binddata": {
            "keyfield": "SNRd",
            "fields": [
                {
                    "caption": "序号ID",
                    "name": "SNRd"
                }, {
                    "caption": "序号名称",
                    "name": "SNName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RuleName",
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
                    data:{"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)},
                    url:"/SN/GetAllSNInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SNRd": datas[i].SNRd,
                            "SNName": datas[i].SNName
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
    $("#defaultSelect2").zc_select(params_sr);
    //获取单位
    var params_un = {
        "displaymode": "0",
        "binddata": {
            "keyfield": "UnitRd",
            "fields": [
                {
                    "caption": "单位ID",
                    "name": "UnitRd"
                }, {
                    "caption": "单位名称",
                    "name": "UnitName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"UnitName",
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
                    data:{"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)},
                    url:"/Unit/GetAllUnitInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UnitRd": datas[i].UnitRd,
                            "UnitName": datas[i].UnitName
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
    $("#defaultSelect1").zc_select(params_un);
    //获取工艺流程
    var params_LC = {
        "displaymode": "1",
        "title": "工艺流程",
        "binddata": {
            "keyfield": "WfVerRd",
            "fields": [
                {
                    "caption": "工艺流程id",
                    "name": "WfVerRd"
                }, {
                    "caption": "工艺流程名称",
                    "name": "WfName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"WFName",
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
                request({url: "/WF/GetAllWfInfo",data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WfVerRd": datas[i].WfVerRd,
                            "WfName": datas[i].WfName,
                        }
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
    $("#defaultSelect3").zc_select(params_LC);
    //获取产品族
    var params_Pro = {
        "displaymode": "0",
        "title": "产品族",
        "binddata": {
            "keyfield": "PdfRd",
            "fields": [
                {
                    "caption": "产品族id",
                    "name": "PdfRd"
                }, {
                    "caption": "产品族名称",
                    "name": "PdfName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"FamilyName",
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
                request({url:"/PF/GetAllPdfInfo", data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PdfRd": datas[i].PdfRd,
                            "PdfName": datas[i].PdfName
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
    $("#defaultSelect").zc_select(params_Pro);
    //获取BOM
    var params_BOM = {
        "displaymode": "1",
        "title": "BOM",
        "binddata": {
            "keyfield": "BomVerRd",
            "fields": [
                {
                    "caption": "BOMid",
                    "name": "BomVerRd"
                }, {
                    "caption": "BOM名称",
                    "name": "BomName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"BomName",
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
                request({url: "/BOM/GetAllBOMInfo", data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "BomVerRd": datas[i].BomVerRd,
                            "BomName": datas[i].BomName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500000
                };
                return obj;
            }
        }
    };
    $("#defaultSelect4").zc_select(params_BOM);
    //TODO:暂时加载工艺流程所有默认版本
    //getAllWorkFlow_DefaultVersion();

    //加载序号管理
    //getSerializeNum();
    //加载单位
    //getUnit();
    //加载产品家族
    //getProductFamily();
    //TODO:暂时加载bom清单所有默认版本
    //getAllBOM_DefaultVersion();

    /*------------------点击事件的处理----------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        if (nodeinfo.isRoot) {  //父节点
            parentTreeID = nodeinfo.nodeID;
            var treeData = getMVTree(parentTreeID);
            if(treeData.length > 0){
                var rule = [{
                    id: "MaVerRd",
                    text: "Version"
                }];
                handle.ckAddChild(rule, treeData);
                getMa(parentTreeID);
                $("#MaCode").prop("readonly",false);
                $("#MaName").prop("readonly",false);
            }
        } else {  //子节点
            clearForm("printForm");
            childTreeID = nodeinfo.nodeID;
            getMV(childTreeID);
            $("#MaCode").prop("readonly",true);
            $("#MaName").prop("readonly",true);
        }
    };


    var config = {
        id: "jstree_demo1",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        //定义事件获取点击的值
        event: {
            onClick: onClicks
        }
    };
    var config1= [
        {
            "FieldName":"MaterialType",
            "FieldOpt":"=",
            "FieldVal":"00"
        }
    ]
    var $pages = $('.pages');

    var condition = "";

    var currentPage = 0;
    var PageInfo = {
        "PageSize":20,
        "PageIndex":currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/Material/GetAllMaInfo","MaRd","MaName","MaterialName",condition,currentPage,config,config1);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }
    });
    $(".input1").keydown(function(event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/Material/GetAllMaInfo","MaRd","MaName","MaterialName",condition,currentPage,config,config1);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })



    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Material/GetAllMaInfo","MaRd","MaName","MaterialName",condition,currentPage,config,config1);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Material/GetAllMaInfo","MaRd","MaName","MaterialName",condition,currentPage,config,config1);
    });



    //加载左边树形
    var load_left = function () {
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"MaterialType",
                    "FieldOpt":"=",
                    "FieldVal":"00"
                }
            ]
        };
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({url:"/Material/GetAllMaInfo", data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo),"InitData":JSON.stringify(InitData)}},function(Body){
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            if(treeData.length<=0){
                return false;
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            var dataArr = [];
            for (var i = 0; i < len; i++) {
                var data = {
                    "id": treeData[i].MaRd,
                    "name": treeData[i].MaName
                };
                dataArr.push(data);
            }
            config.data.source = dataArr;
            $.JstreeEx.init(config);
        });
    }
    load_left();

    //新增
    $(".cAdd").click(function () {
        $("#_right").show();
        $("#MaCode").prop("readonly",false);
        $("#MaName").prop("readonly",false);
        $("#_right").show();
        clearForm("printForm");
        $("#defaultSelect").clearseldata("PdfRd");
        $("#defaultSelect1").clearseldata("UnitRd");
        $("#defaultSelect2").clearseldata("SNRd");
        $("#defaultSelect3").clearseldata("WfVerRd");
        $("#defaultSelect4").clearseldata("BomVerRd");
        $("#list5").empty();
        $("#pager5").empty();
        /*
         var re_obj = {
         data:null,
         colArr:re_colNamesArr,
         tableId:"list5",
         multiselect:true
         };
         fullTable(re_obj);*/
        $("#ExecType").val("00");
        parentTreeID = null;
    });

    //保存
    $(".cSave").click(function () {
        var execType = $("#ExecType").val();
        // 获取所有表单数据封装成json对象
        formData = transfer("printForm");
        if(formData["MaCode"] == ""){
            $("#MaCode").css("border-color", "red");
            $("#MaCode").prop("placeholder", "不能为空！");
            return;
        }
        else if(formData["MaName"]== ""){
            $("#MaName").css("border-color", "red");
            $("#MaName").prop("placeholder", "不能为空！");
            return;
        }
        else if(formData["Version"]== ""){
            $("#Version").css("border-color", "red");
            $("#Version").prop("placeholder", "不能为空！");
            return;
        }

        var PDFRd = $("#defaultSelect").getseldata().PdfRd;
        //alert(PDFRd)
        var WfVerRd = $("#defaultSelect3").getseldata().WfVerRd;
        //alert(WfVerRd)
        var SNRd = $("#defaultSelect2").getseldata().SNRd;
        //alert(SNRd)
        var UnitRd = $("#defaultSelect1").getseldata().UnitRd;
        //alert(UnitRd)
        var BOMVerRd = $("#defaultSelect4").getseldata().BomVerRd;
        //alert(BOMVerRd)
        //是否默认 00默认
        /*var v = "01";
        if ($("#IsDef").is(":checked")) {
            v = "00";
        }*/

        if (parentTreeID == null && "00" == execType) { //新增
            if (formData["MaCode"] != "" && formData["MaName"] != "" && formData["Version"] != "" && UnitRd!="") {

                var newData = {
                    "MaCode": formData["MaCode"],
                    "MaName": formData["MaName"],
                    "MaterialType": "00",
                    "MaDes":$("#MaDes").val(),
                    "Version": formData["Version"],
                    "IsDef": "00",
                    "MaTRd": "-1",
                    "WfVerRd": WfVerRd,
                    "BOMVerRd": BOMVerRd,
                    "SRRd": SNRd,
                    "UnitRd": UnitRd,
                    "SuName": "-1",
                    "SuMaCode":"-1",
                    "CusMaCode":formData["CusMaCode"],
                    "PDFRd": PDFRd,
                    "ReMaterial": "-1",
                    "Shelflife": "-1",
                    "Interval": "-1",
                    "SUnit":  "-1",
                    "IsExem":"-1",
                    "DisRule": "-1",
                    "MinPack": "-1",
                    "ReMaInfo": [],
                    "Status": formData["Status"],
                    "Remark": $("#beizhu").val(),
                    "MinSNum": formData["MinSNum"],
                    "MaxSNum": formData["MaxSNum"]
                };
                saveMa("00", JSON.stringify(newData));
            }else{
                if (UnitRd == "") {
                    $("#defaultSelect1").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect1").find("input:eq(0)").val("");
                    $("#defaultSelect1").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }
        } else if (parentTreeID != null && childTreeID != null) { //编辑
            if (formData["MaCode"] != "" && formData["MaName"] != "" && formData["Version"] != "" && formData["MaType"] != "" && UnitRd!="") {

                var newData = {
                    "MaVerRd": childTreeID,
                    "MaCode": formData["MaCode"],
                    "MaName": formData["MaName"],
                    "MaterialType": "00",
                    "MaDes":$("#MaDes").val(),
                    "Version": formData["Version"],
                    "IsDef": "00",
                    "MaTRd": "-1",
                    "WfVerRd": WfVerRd,
                    "BOMVerRd": BOMVerRd,
                    "SRRd": SNRd,
                    "UnitRd": UnitRd,
                    "SuName": "-1",
                    "SuMaCode":"-1",
                    "CusMaCode":formData["CusMaCode"],
                    "PDFRd": PDFRd,
                    "ReMaterial": "-1",
                    "Shelflife": "-1",
                    "Interval": "-1",
                    "SUnit":  "-1",
                    "IsExem":"-1",
                    "DisRule": "-1",
                    "MinPack": "-1",
                    "ReMaInfo": [],
                    "Status": formData["Status"],
                    "Remark": $("#beizhu").val(),
                    "MinSNum": formData["MinSNum"],
                    "MaxSNum": formData["MaxSNum"]
                };
                saveMa("02", JSON.stringify(newData));
            }else{
                if (UnitRd == "") {
                    $("#defaultSelect1").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect1").find("input:eq(0)").val("");
                    $("#defaultSelect1").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }
        } else if ("03" == execType) { //复制
            saveMa("03", "{\"MaRd\":" + parentTreeID + "}");
        } else if (parentTreeID != null && "04" == execType) { //新增版本
            if (formData["MaCode"] != "" && formData["MaName"] != "" && formData["Version"] != "" && formData["MaType"] != "" && UnitRd!="") {

                var newData = {
                    "MarRd": parentTreeID,
                    "MaCode": formData["MaCode"],
                    "MaName": formData["MaName"],
                    "MaterialType": "00",
                    "MaDes":$("#MaDes").val(),
                    "Version": formData["Version"],
                    "IsDef": "00",
                    "MaTRd": "-1",
                    "WfVerRd": WfVerRd,
                    "BOMVerRd": BOMVerRd,
                    "SRRd": SNRd,
                    "UnitRd": UnitRd,
                    "SuName": "-1",
                    "SuMaCode":"-1",
                    "CusMaCode":formData["CusMaCode"],
                    "PDFRd": PDFRd,
                    "ReMaterial": "-1",
                    "Shelflife": "-1",
                    "Interval": "-1",
                    "SUnit": "-1",
                    "IsExem":"-1",
                    "DisRule": "-1",
                    "MinPack": "-1",
                    "ReMaInfo": [],
                    "Status": formData["Status"],
                    "Remark": $("#beizhu").val(),
                    "MinSNum": formData["MinSNum"],
                    "MaxSNum": formData["MaxSNum"]
                };
                saveMa("04", JSON.stringify(newData));
            }else{
                if (UnitRd == "") {
                    $("#defaultSelect1").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect1").find("input:eq(0)").val("");
                    $("#defaultSelect1").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }
        }
    });


    //复制
    $("#copy").click(function () {
        if (parentTreeID != undefined && parentTreeID != null) {
            $("#ExecType").val("03");
            toastr.info("复制成功，请点击保存!");
        } else {
            toastr.warning("请选择左侧的一项根节点之后再进行复制!");
        }
    });

    //新增版本
    $("#addVer").click(function () {
        childTreeID = null;
        if (parentTreeID != undefined && parentTreeID != null && childTreeID == null) {
            $("#MaCode").prop("readonly",true);
            $("#MaName").prop("readonly",true);
            $("#creatPeople").val("");
            $("#creatTime").val("");
            $("#modifyPeople").val("");
            $("#modifyTime").val("");
            $("#beizhu").val("");

            $("#ExecType").val("04");
        } else {
            toastr.warning("请选择左侧的一项再进行新增版本!");
        }
    });

    //删除
    $("#remove").click(function () {
        if (parentTreeID != undefined && parentTreeID != null) {
            layer.confirm('', {
                type: 0,
                btn: ['确认', '取消'], //按钮
                content: '确认删除吗？',
                icon: "fa-check-circle"
            }, function () {
                var maRd = parentTreeID;
                $("#ExecType").val("01");
                saveMa("01", "{\"MaRd\":" + maRd + "}");
                layer.closeAll("dialog");
                load_left();
                parentTreeID = null;
                $("#_right").hide();
            }, function () {

            });
        } else {
            toastr.warning("请选择左侧的一项根节点再进行删除!");
        }
    });

    //删除版本
    $("#removeVer").click(function () {
        if (childTreeID != undefined && childTreeID != null) {
            layer.confirm('', {
                type: 0,
                btn: ['确认', '取消'], //按钮
                content: '确认删除当前版本吗？',
                icon: "fa-check-circle"
            }, function () {
                $("#ExecType").val("05");
                saveMa("05", "{\"MaVerRd\":" + childTreeID + "}");
                layer.closeAll("dialog");
                childTreeID = null;
                load_left();
            }, function () {

            });
        } else {
            toastr.warning("请选择左侧要删除的版本再进行删除!");
        }
    });

    //获取物料版本列表信息
    function getMVTree(MaRd) {
        var data;
        request({url:"/Material/GetMVTreeInfo", data: {"ExecType": "00", "BusData": "{\"MaRd\":" + MaRd + "}"}},function(Body){
            data = Body.Data;
        });
        return data;
    }

    //获取物料信息
    function getMa(MaRd) {
        parentTreeID = MaRd;
        request({url:"/Material/GetMaInfo", data: {"ExecType": "00", "BusData": "{\"MaRd\":" + MaRd + "}"}},function(Body){
            loadData(Body);
        });
    }

    //获取物料版本信息
    function getMV(MaVerRd) {
        request({url:"/Material/GetMaInfo", data: {"ExecType": "01", "BusData": "{\"MaVerRd\":" + MaVerRd + "}"}},function(Body){
            loadData(Body);
        });
    }

    //加载数据
    function loadData(Body) {
        fillform("printForm", ruleMaVer, Body.Data);
        childTreeID = Body.Data.MaVerRd;
        $("#defaultSelect").showData({
            id:Body.Data.PDFInfo == null? "":Body.Data.PDFInfo.PDFRd,
            name:Body.Data.PDFInfo == null? "":Body.Data.PDFInfo.PDFName,
            keyfield:"PdfRd",
            fields:[
                {"name":"PdfRd"},
                {"name":"PdfName"}
            ]
        });
        $("#defaultSelect1").showData({
            id:Body.Data.UnitInfo.UnitRd,
            name:Body.Data.UnitInfo.UnitName,
            keyfield:"UnitRd",
            fields:[
                {"name":"UnitRd"},
                {"name":"UnitName"}
            ]
        });
        $("#defaultSelect2").showData({
            id:Body.Data.SRInfo == null? "":Body.Data.SRInfo.SRRd,
            name:Body.Data.SRInfo == null? "":Body.Data.SRInfo.SRName,
            keyfield:"SNRd",
            fields:[
                {"name":"SNRd"},
                {"name":"SNName"}
            ]
        });
        $("#defaultSelect3").showData({
            id:Body.Data.WFInfo == null? "":Body.Data.WFInfo.WfVerRd,
            name:Body.Data.WFInfo == null? "":Body.Data.WFInfo.WFName,
            keyfield:"WfVerRd",
            fields:[
                {"name":"WfVerRd"},
                {"name":"WfName"}
            ]
        });
        $("#defaultSelect4").showData({
            id:Body.Data.BOMInfo == null? "":Body.Data.BOMInfo.BOMVerRd,
            name:Body.Data.BOMInfo == null? "":Body.Data.BOMInfo.BOMName,
            keyfield:"BomVerRd",
            fields:[
                {"name":"BomVerRd"},
                {"name":"BomName"}
            ]
        });

        if(Body.Data.MaType!= null)
            $("#MaType").val(Body.Data.MaType.MaTName);
        $("#Status option").each(function(){
            if(Body.Data.Status === $(this).val()){
                $(this).attr("selected","selected");
            }else{
                $(this).attr("selected",false);
            }
        })
    }

    //保存物料信息
    function saveMa(ExecType, BusData) {
        request({url:"/Material/SaveMaInfo", data: {"ExecType": ExecType, "BusData": BusData}},function(Body){
            if($("#ExecType").val() == "01" || $("#ExecType").val() == "05"){
                $("#_right").hide();
            }
            $("#ExecType").val("");
            parentTreeID = null;
            childTreeID = null;
            toastr.success(Body.MsgDes);
            //刷新树
            load_left();
        });
    }

    // 查看BOM层次
    $("#readBOM").on("click",function () {
        if(parentTreeID != null){
            window.sessionStorage.setItem("MaRd",parentTreeID);
            parent.addTabs({id:'999',title: 'BOM层次',close: true,url: getBasePath() + '/Material/Bomlevel'});
        }
    })


});
