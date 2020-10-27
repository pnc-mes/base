$(function () {

    var newDatas=[]; //获取一开始的所有物料信息

    var CDocInfo =[]; //保存物料的所有信息
    var name ="";
    var f=0;

    f1();
    //模板项目
    var wgBadInfos = [];
    //检验水平
    var wgBadInfos1 = [];
    //AQL抽检规则
    var wgBadInfos2 = [];
    //检验模板清单
    request({
        url: "/CTP/GetAllCTPInfo",
        data: {
            "ExecType": "00",
            "InitData": JSON.stringify({
                "FiledList": [{
                    "FieldName": "CheckTempName",
                    "FieldOpt": "Order BY",
                    /*   "FieldVal": "00"*/
                }]
            })
        }
    }, function (Body) {
        wgBadInfos = Body.Data;
    });
    //检验水平
    request({
        url: "/CLevel/GetAllCLevelInfo",
        data: {
            "ExecType": "00",
            "InitData": JSON.stringify({
                "FiledList": [{
                    "FieldName": "CheckLevelName",
                    "FieldOpt": "Order BY",
                    /*   "FieldVal": "00"*/
                }]
            })
        }
    }, function (Body) {
        wgBadInfos1 = Body.Data;
    });
    //AQL抽检规则
    request({
        url: "/AQL/GetAllAQLInfo",
        data: {
            "ExecType": "00",
            "InitData": JSON.stringify({
                "FiledList": [{
                    "FieldName": "AQLRuleName",
                    "FieldOpt": "Order BY",
                    /*   "FieldVal": "00"*/
                }]
            })
        }
    }, function (Body) {
        wgBadInfos2 = Body.Data;
    });

    //所有等级
    var BadInfos = [];
    request({url: "/SunPort/Grade/GetAllGradeInfo", data: {"ExecType": "00"}}, function (Body) {
        var resp = Body.Data;
        for (var i = 0; i < resp.length; i++) {
            var item = resp[i];
            for (var j = 0, jlen = BadInfos.length; j <= jlen; j++) {
                if (BadInfos[j] != undefined && BadInfos[j] != "") {
                    if (item.GradeName == BadInfos[j].GradeName) {
                        break;
                    }
                }
                if (j == jlen) {
                    BadInfos.push(item);
                }
            }
        }

    });
    var colNamesArr = [
        {"Caption": "id", "Name": "CTRelRd", "Hidden": true},
        {"Caption": "id", "Name": "CPTRd", "CType": "text", "Hidden": true},
        {"Caption": "检验模板", "Name": "CheckTempName", "CType": "text", "Editable": false},
        {"Caption": "id", "Name": "CLevelRd", "CType": "text", "Hidden": true},
        {"Caption": "检验水平", "Name": "CheckLevelName", "CType": "text", "Editable": false},
        {"Caption": "id", "Name": "AQLRuleRd", "CType": "text", "Hidden": true},
        {"Caption": "AQL抽检规则", "Name": "AQLRuleName", "CType": "text", "Editable": false},

    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.6,
        height: 0.415
    };
    config1.data.length = 0;
    fullTable(config1);//加载空表格

    //模板类型事件   出发时候需要做清空的处理
    $("#TempRelType").change(function(){
        //要触发的事件
        var aa =$(this).val();
        if(aa=="00"){
            $("#guanlian1").hide();
            $("#guanlian2").hide();
        }
        else if(aa=="01"){
            $("#guanlian1").show();
            $("#guanlian2").hide();
        }else if ($(this).val()=="02"){
            $("#guanlian1").hide();
            $("#guanlian2").show();
        }
    });
    //物料关联类型事件
    $("#correlationtype").change(function(){
        //要触发的事件
        var aa =$(this).val();
        if(aa=="00"){
            $("#guanlian3").show();
            $("#guanlian4").hide();
        }else if ($(this).val()=="01"){
            $("#guanlian3").hide();
            $("#guanlian4").show();
        }
    });
    //
    var clickSelect_1 = function () {
        $(".CheckTempName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos[0].CPTRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos.length; i++) {
                if (current_Td_Val == wgBadInfos[i].CheckTempName)
                    str += "<option selected value='" + wgBadInfos[i].CPTRd + "'>" + wgBadInfos[i].CheckTempName + "</option>";
                else
                    str += "<option value='" + wgBadInfos[i].CPTRd + "'>" + wgBadInfos[i].CheckTempName + "</option>";
            }
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            var str_Width = $(this).css("width");
            var index = str_Width.indexOf("px");
            var width = parseInt(str_Width.substring(0, index)) * 0.95;
            $(this).find("select").css("width", "150px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
            $(this).find("select").on("change", function () {
                for (var i = 0; i < wgBadInfos.length; i++) {
                    if (wgBadInfos[i].CheckTempName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(wgBadInfos[i].CPTRd);
                }
            })
        });
    };
    var clickSelect1_1 = function () {
        $(".CheckLevelName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos1[0].CLevelRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos1.length; i++) {
                if (current_Td_Val == wgBadInfos1[i].CheckLevelName)
                str += "<option selected value='" + wgBadInfos1[i].CLevelRd + "'>" + wgBadInfos1[i].CheckLevelName + "</option>";
                else
                str += "<option value='" + wgBadInfos1[i].CLevelRd + "'>" + wgBadInfos1[i].CheckLevelName + "</option>";
            }
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            var str_Width = $(this).css("width");
            var index = str_Width.indexOf("px");
            var width = parseInt(str_Width.substring(0, index)) * 0.95;
            $(this).find("select").css("width", "150px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
            $(this).find("select").on("change", function () {
                for (var i = 0; i < wgBadInfos1.length; i++) {
                    if (wgBadInfos1[i].CheckLevelName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(wgBadInfos1[i].CLevelRd);
                }
            })
        });
    };
    var clickSelect2_1 = function () {
        $(".AQLRuleName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos2[0].AQLRuleRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos2.length; i++) {
                if (current_Td_Val == wgBadInfos2[i].AQLRuleName)
                    str += "<option selected value='" + wgBadInfos2[i].AQLRuleRd + "'>" + wgBadInfos2[i].AQLRuleName + "</option>";
                else
                    str += "<option value='" + wgBadInfos2[i].AQLRuleRd + "'>" + wgBadInfos2[i].AQLRuleName + "</option>";
            }
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            var str_Width = $(this).css("width");
            var index = str_Width.indexOf("px");
            var width = parseInt(str_Width.substring(0, index)) * 0.95;
            $(this).find("select").css("width", "150px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
            $(this).find("select").on("change", function () {
                for (var i = 0; i < wgBadInfos2.length; i++) {
                    if (wgBadInfos2[i].AQLRuleName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(wgBadInfos2[i].AQLRuleRd);
                }
            })
        });
    };

    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {

        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CTRelRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/CTRel/GetCTRelInfo", data: objData,}, function (Body) {
            $("#TempRelName").val(Body.Data.TempRelName);
            $("#TempRelType").val(Body.Data.TempRelType);
            if(Body.Data.TempRelType=="00"){
                $("#guanlian1").hide();
                $("#guanlian2").hide();
            }else if(Body.Data.TempRelType=="01"){
                $("#guanlian1").show();
                $("#guanlian2").hide();
            }else {
                $("#guanlian1").hide();
                $("#guanlian2").show();
            }
            if(Body.Data.RelType=="00"){
            $("#material").showData({
                id:Body.Data.RelInfo.RelRd,
                name:Body.Data.RelInfo.RelName,
                keyfield:"RelRd",
                fields:[
                    {"name":"RelRd"},
                    {"name":"RelName"}
                ]
            });
            }else {
                maType = Body.Data.RelInfo.RelName;
                $("#type").val(Body.Data.RelInfo == null ? "":Body.Data.RelInfo.RelName);
                $("#MaTypeRd").val(Body.Data.RelInfo == null ? "":Body.Data.RelInfo.RelRd);

            }
            var guanliantype=Body.Data.RelType;
            if(guanliantype=="00"){
                $("#guanlian3").show();
                $("#guanlian4").hide();
                $("#correlationtype").val("00");
            }else {
                $("#guanlian3").hide();
                $("#guanlian4").show();
                $("#correlationtype").val("01");
            }
            $("#defaultSelect").showData({
                id:Body.Data.CDOC.CDOCRd,
                name:Body.Data.CDOC.CheckDocName,
                keyfield:"CDOCRd",
                fields:[
                    {"name":"CDOCRd"},
                    {"name":"CheckDocName"}
                ]
            });   //文档
            $("#SupplierSelect").showData({
                id:Body.Data.Supplier.SupplierRd,
                name:Body.Data.Supplier.SupplierName,
                keyfield:"SupplierRd",
                fields:[
                    {"name":"SupplierRd"},
                    {"name":"SupplierName"}
                ]
            }); //供应商
            $("#CustomerSelect").showData({
                id:Body.Data.Customer.CustomerRd,
                name:Body.Data.Customer.CustomerName,
                keyfield:"CustomerRd",
                fields:[
                    {"name":"CustomerRd"},
                    {"name":"CustomerName"}
                ]
            }); //客户
            //点击左侧列表获取表格数据
            if (Body.Data.CTRelDtlInfo.length > 0) {
                var busData = Body.Data.CTRelDtlInfo;
                var CTRelDtlInfo = [];
                for (var i = 0; i < busData.length; i++) {
                    var wgData = {
                        "CTRelRd": busData[i].CTRelRd,
                        "CPTRd": busData[i].CPT.CPTRd,
                        "CheckTempName": busData[i].CPT.CheckTempName,
                        "CLevelRd": busData[i].CLevel.CLevelRd,
                        "CheckLevelName": busData[i].CLevel.CheckLevelName,
                        "AQLRuleRd": busData[i].AQLRule.AQLRuleRd,
                        "AQLRuleName": busData[i].AQLRule.AQLRuleName,
                    };
                    CTRelDtlInfo.push(wgData);
                }
                var config1 = {
                    tableId: 'list4',
                    data: CTRelDtlInfo,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.6,
                    height: 0.415
                };
                fullTable(config1);//加载空表格
            }
            clickSelect_1();
            clickSelect1_1();
            clickSelect2_1();
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);


        });

    };
    /*----------------------定义控件规则-------------------*/
    var config = {
        id: "jstree_demo1",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        event: {
            onClick: onClicks
        }
    };
    var $pages = $('.pages');

    var condition = "";

    var currentPage = 0;
    var PageInfo = {
        "PageSize": 20,
        "PageIndex": currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/CTRel/GetAllCTRelInfo", "CTRelRd", "TempRelName", "TempRelName", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function (event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/CTRel/GetAllCTRelInfo", "CTRelRd", "TempRelName", "TempRelName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/CTRel/GetAllCTRelInfo", "CTRelRd", "TempRelName", "TempRelName", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/CTRel/GetAllCTRelInfo", "CTRelRd", "TempRelName", "TempRelName", condition, currentPage, config);
    });



    $("#_right").hide();

    var treeID = null;
    $(".add1").click(function () {
        addErow("list4");
        //检验清单
        var trObj1 = $("#list4 tbody>tr:eq(1)");
        trObj1.find("td").each(function () {
            if ($(this).attr("class") == "CheckTempName") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < wgBadInfos.length; i++) {
                    if (current_Td_Val == wgBadInfos[i].CheckTempName) {
                        str += "<option selected value='" + wgBadInfos[i].CPTRd + "'>" + wgBadInfos[i].CheckTempName + "</option>";
                    }

                    else {
                        str += "<option value='" + wgBadInfos[i].CPTRd + "'>" + wgBadInfos[i].CheckTempName + "</option>";
                        $(this).prev().text(wgBadInfos[0].CPTRd);
                    }

                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                var str_Width = $(this).css("width");
                var index = str_Width.indexOf("px");
                var width = parseInt(str_Width.substring(0, index)) * 0.95;
                $(this).find("select").css("width", "150px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
        });
         //检验水平下拉框
        var trObj2 = $("#list4 tbody>tr:eq(1)");
        trObj2.find("td").each(function () {
            if ($(this).attr("class") == "CheckLevelName") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < wgBadInfos1.length; i++) {
                    if (current_Td_Val == wgBadInfos1[i].CheckLevelName) {
                        str += "<option selected value='" + wgBadInfos1[i].CLevelRd + "'>" + wgBadInfos1[i].CheckLevelName + "</option>";
                    }

                    else {
                        str += "<option value='" + wgBadInfos1[i].CLevelRd + "'>" + wgBadInfos1[i].CheckLevelName + "</option>";
                        $(this).prev().text(wgBadInfos1[0].CLevelRd);
                    }

                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                var str_Width = $(this).css("width");
                var index = str_Width.indexOf("px");
                var width = parseInt(str_Width.substring(0, index)) * 0.95;
                $(this).find("select").css("width", "150px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
        });
        //AQL抽检规则表格下拉框
        var trObj3 = $("#list4 tbody>tr:eq(1)");
        trObj3.find("td").each(function () {
            if ($(this).attr("class") == "AQLRuleName") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < wgBadInfos2.length; i++) {
                    if (current_Td_Val == wgBadInfos1[i].AQLRuleName) {
                        str += "<option selected value='" + wgBadInfos2[i].AQLRuleRd + "'>" + wgBadInfos2[i].AQLRuleName + "</option>";
                    }

                    else {
                        str += "<option value='" + wgBadInfos2[i].AQLRuleRd + "'>" + wgBadInfos2[i].AQLRuleName + "</option>";
                        $(this).prev().text(wgBadInfos2[0].AQLRuleRd);
                    }

                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                var str_Width = $(this).css("width");
                var index = str_Width.indexOf("px");
                var width = parseInt(str_Width.substring(0, index)) * 0.95;
                $(this).find("select").css("width", "150px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
        });
        clickSelect_1();
        clickSelect1_1();
        clickSelect2_1();
    });
    $(".del1").click(function () {
        derow("list4");
    });
    //检验文档下拉框
    var params = {
        "displaymode": "0",
        "title": "检验文档",
        "binddata": {
            "keyfield": "CDOCRd",
            "fields": [
                {
                    "caption": "检验文档id",
                    "name": "CDOCRd"
                }, {
                    "caption": "检验文档名称",
                    "name": "CheckDocName"
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
                            "FieldName": "CheckDocName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/CDOC/GetAllCDOCInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CDOCRd": datas[i].CDOCRd,
                            "CheckDocName": datas[i].CheckDocName,
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
    //供应商下拉框
    var params = {
        "displaymode": "0",
        "title": "供应商",
        "binddata": {
            "keyfield": "SupRd",
            "fields": [
                {
                    "caption": "供应商id",
                    "name": "SupRd"
                }, {
                    "caption": "供应商名称",
                    "name": "supplierName"
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
                            "FieldName": "supplierName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/Supplier/GetAllSupInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SupRd": datas[i].SupRd,
                            "supplierName": datas[i].SupName,
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
    $("#SupplierSelect").zc_select(params);
    //客户下拉框
    var params = {
        "displaymode": "0",
        "title": "客户管理",
        "binddata": {
            "keyfield": "CusRd",
            "fields": [
                {
                    "caption": "客户id",
                    "name": "CusRd"
                }, {
                    "caption": "客户名称",
                    "name": "customerName"
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
                            "FieldName": "customerName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];

                request({url: "/Customer/GetAllCusInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CusRd": datas[i].CusRd,
                            "customerName": datas[i].CusName,
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
    $("#CustomerSelect").zc_select(params);

    //加载树
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        request({
            url: '/CTRel/GetAllCTRelInfo',
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "TempRelName",
                            "FieldOpt": "Order BY"
                        }]
                })
            }
        }, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].CTRelRd == undefined ? "" : treeData[i].CTRelRd,
                    name: treeData[i].TempRelName == undefined ? "" : treeData[i].TempRelName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
        //外观
        clickSelect_1();
        clickSelect1_1();
        clickSelect2_1();

    };
    loaddata();



    var InitData = {
        "FiledList":[
            {
                "FieldName":"MaterialType",
                "FieldOpt":"=",
                "FieldVal":""
            },
            {
                "FieldName":"MaTypeRd",
                "FieldOpt":"=",
                "FieldVal":""
            },
            {
                "FieldName":"IsDef",
                "FieldOpt":"=",
                "FieldVal":"00"
            },
            {
                "FieldName":"MaterialName",
                "FieldOpt":"order by"
            }
        ]
    };
    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {

        $("#ExecType").val("00");
        $("#djpdlx").val("00");

        $("#_right").show();
        $("#TempRelName").val("");
        $("#creatTime").val("");
        $("#beizhu").val("");
   //     $("#material").clearseldata("MaVerRd");
        $("#TempRelType").val("");
        $("#defaultSelect").clearseldata("CDOCRd");
        $("#SupplierSelect").clearseldata("SupRd");
        $("#CustomerSelect").clearseldata("CusRd");
        $("#guanlian1").hide();
        $("#guanlian2").hide();
        $("#material").clearseldata("MaVerRd");
        $("#guanlian3").show();
        $("#guanlian4").hide();
        //点击新增默认的关联类型为物料，并且显示物料编码
        $("#correlationtype").val("00");
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.6,
            height: 0.415
        };
        config1.data.length = 0;
        fullTable(config1);//加载空表格
        clickSelect_1();
        clickSelect1_1();
        clickSelect2_1();
        treeID = null;
    });
    var rootNode=[];
    var WFInfoData=[];
    //物料类型相关事件
    var clic = function (nodeinfo, handle) {
        App.unblockUI("body");
        //if("00" != nodeinfo.nodeID && "01" != nodeinfo.nodeID && "02" != nodeinfo.nodeID && "03" != nodeinfo.nodeID){
        $("#MaTypeRd").val(nodeinfo.nodeID);
        $("#type").val(nodeinfo.nodeText);

        // if(f==1){
        te=nodeinfo.nodeID;
        if(te!=""&&te!=null){
            //物料扩展字段页面加载数据
            var  datas={
                "MTRd":te
            }
            request({
                url:'/Expand/GetDWExpandInfo',
                data:{"ExecType":"00","BusData":JSON.stringify(datas)}},function (Body) {
                var data=Body.Data;
                if(data.length>0){
                    $("#MType").attr("style","display:block;");
                }else {
                    $("#MType").attr("style","display:none;");
                }
                maps=[];
                var j=1;
                data.forEach(function (e) {
                    maps.push(e.FieldName);
                    if(e.FiledType=="00"){
                        $(".formdata").append(e.DisplayName+"&ensp;"+"<input class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        if(e.Val!=null){
                            $("#"+e.FieldName).val(e.Val);
                        }else {
                            $("#"+e.FieldName).val();
                        }
                        if(j%2==0){
                            $(".formdata").append("<tr>");
                        }
                    }
                    if(e.FiledType=="01"){
                        $(".formdata").append(e.DisplayName+"&ensp;"+" <select class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+" ><option>"+''+"</option></select>&ensp;&ensp;");
                        if(e.Option!=undefined&&e.Option!=null){
                            e.Option.forEach(function (s) {
                                var option=$("<option value="+s.Val+">"+s.DisplayName+"</option>");
                                $("#"+e.FieldName).append(option);
                            });
                        }
                        $("#"+e.FieldName).val(e.Val);
                        if(j%2==0){
                            $(".formdata").append("<tr>");
                        }
                    }
                    j++

                });

            });
        }
        if ("00" == nodeinfo.nodeID) {
            str = "00";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("01" == nodeinfo.nodeID) {
            str = "01";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("02" == nodeinfo.nodeID) {
            str = "02";
            //隐藏工艺流程下面的信息
            $("#a_tab_4").css("display","none");
            $("#tab_4").removeClass("active");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","none");
        }
        if ("03" == nodeinfo.nodeID) {
            str = "03";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","block");
        }
        InitData.FiledList[0].FieldVal = str;
        if (nodeinfo.isRoot) {  //父节点
            var MaTypeInitData = {
                "FiledList": [
                    {
                        "FieldName": "MaterialType",
                        "FieldOpt": "=",
                        "FieldVal": str
                    }
                ]
            };
            request({url: '/MaType/GetAllMTInfo', data: {"ExecType": "00", "InitData": JSON.stringify(MaTypeInitData)}}, function (Body) {
                var treeData = Body.Data.CMTInfo;
                handle.ckAddChild(rules, treeData);
            });
        }

        if(str == "00" || str == "01"){
            $("#maBatch").prop("checked", true);
            $("#maBatch").attr("disabled",true);
            //$("#MaBarCode").val("");
            $("#MaBarCode").attr("readonly",true);
            $("#dd3").css("display", "");
        }else{
            if($("#maBatch").is(":checked")){
                //$("#MaBarCode").val("");
                $("#MaBarCode").attr("readonly",true);
            }else{
                //$("#MaBarCode").val("");
                $("#MaBarCode").attr("readonly",false);
            }
            $("#maBatch").attr("disabled", false);
        }
    };
    var rules = [{
        id: "MTRd",
        text: "MTName",
        children: "CMTInfo"
    },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        }
    ];
    var tCon = {
        id: "t",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        event: {
            onClick: clic

        }
    };
    var list = [];
    var f = true;
    var a = function () {
        var treedataList = [];
        request({url: '/Material/GetMaTypeInfo', data: {"ExecType": "00"}}, function (Body) {
            var treeData = Body.Data;
            for (var i = 0; i < treeData.length; i++) {
                var tree = {
                    id: treeData[i].MaType == undefined ? "" : treeData[i].MaType,
                    name: treeData[i].TName == undefined ? "" : treeData[i].TName
                }
                treedataList.push(tree);
            }
            tCon.data.source = treedataList;
            if(f){
                list = treedataList;
                f = false;
            }
            $.JstreeEx.init(tCon);//先调用后加载
        });
    };
    a();
    var ff = true;
    $("#t").on("dblclick","a",function () {
        var a_Arr = $(this).attr("id").split("_");
        var id = a_Arr[a_Arr.length - 2];
        //if("00" != id && "01" != id && "02" != id && "03" != id){
        ff = true;
        $("#t").css("display","none");
        //}
    });
    $("#t").on("click",function (e) {
        e.stopPropagation();
    });
    $("#type").on({
        "focus": function () {
            if(ff){
                $("#t").css("display", "block");
                tCon.data.source = list;
                $.JstreeEx.init(tCon);
                ff = false;
            }
        },
        "click":function (e) {
            e.stopPropagation();
            if(ff) {
                $("#t").css("display", "block");
                tCon.data.source = list;
                $.JstreeEx.init(tCon);
            }
        },
        "keydown":function (e) {
            if(e.keyCode == 8){
                $(this).val("");
                $("#MaTypeRd").val("");
            }else{
                //$(this).val(inVal);
            }
        }

    })
    $("body").on("click",function () {
        ff = true;
        $("#t").css("display","none");
    });
/*
    function f1() {
        var Mlinfo = function(InitData , page,xldata){

            if(page==null || page==""){
                request({
                    url: "/Material/GetAllMaInfo",
                    data: {"ExecType": "00","InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var  datas= Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "MaRd": datas[i].MaRd,
                            "MaCode": datas[i].MaCode,
                            "MaName": datas[i].MaName,
                            "MaDes": datas[i].MaDes
                        };
                        xldata.push(data);
                        newDatas.push(data);
                    }
                });
            }else{
                request({
                    url: "/Material/GetAllMaInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "MaRd": datas[i].MaRd,
                            "MaCode": datas[i].MaCode,
                            "MaName": datas[i].MaName,
                            "MaDes": datas[i].MaDes
                        };
                        xldata.push(data);
                        newDatas.push(data);
                    }
                });
            }
            return xldata;
        }
        var repMa1 = {
            "displaymode": "1",
            "title": "物料",
            "binddata": {
                "keyfield": "MaRd",
                "fields": [
                    {
                        "caption": "物料id",
                        "name": "MaRd"
                    }, {
                        "caption": "物料代码",
                        "name": "MaCode"
                    }, {
                        "caption": "物料名称",
                        "name": "MaName"
                    }, {
                        "caption": "物料描述",
                        "name": "MaDes"
                    }
                ]
            },
            "showresult": {
                "ishead": true
            },
            "event":{
                "onformatval":function(data){
                    return data.MaCode + "-" + data.MaName;
                },
                "onclick":function(data){
                },
                "onseardata": function (o) {
                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                                "FieldOpt": "like",
                                "FieldVal": "%" + o.condition + "%"
                            }, {
                                "FieldName": "ReMaterial",
                                "FieldOpt": "=",
                                "FieldVal": "00"
                            },
                            {
                                "FieldName": "MaterialName",
                                "FieldOpt": "order by",
                            }
                        ]
                    };
                    var datas = [];
                    Mlinfo(InitData,"",datas);
                    var obj = {
                        data: datas,
                        showrow: 500
                    };
                    return obj;
                },
            }
        };
        $("#material").zc_select(repMa1);
        //  alert(xldata.length);
    }
*/

    function f1() {
        var material = {
            "displaymode": "1",
            "title": "物料",
            "binddata": {
                "keyfield": "MaVerRd",
                "fields": [

                    {
                        "caption": "MaVerRd",
                        "name": "MaVerRd"
                    }, {
                       "caption": "物料代码",
                       "name": "MaCode"
        }, {
                        "caption": "物料名称",
                        "name": "MaName"
                    }
                ]
            },
            "showresult": {"ishead": true},
            "event": {
                "onseardata": function (o) {
                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "MaterialName",
                                "FieldOpt": "like",
                                "FieldVal": "%" + excludeSpecial(o.condition) + "%"
                            }, {
                                "FieldName": "MaterialName",
                                "FieldOpt": "Order BY"
                            },
                            {
                                "FieldName": "MaterialType",
                                "FieldOpt": "in",
                                "FieldVal": "(01,00)"
                            }
                        ]
                    };

                    var obj = {
                        data: {"ExecType": "00", "InitData": JSON.stringify(InitData)},
                        url: "/Material/GetAllMaInfo"
                    };
                    var xldata = [];
                    request(obj, function (Body) {
                        var datas = Body.Data;
                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "MaVerRd": datas[i].MaVerRd,
                                "MaCode": datas[i].MaCode,
                                "MaName": datas[i].MaName,
                                "MaDes": datas[i].MaDes
                            };
                            xldata.push(data);
                        }
                    });
                    var obj = {
                        data: xldata,
                        showrow: 50
                    };
                    return obj;
                }
            }
        };

        $("#material").zc_select(material);


    }
    /*--------------顶部菜单点击编辑按钮----------*/
    $(".cEdit").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            $("#ExecType").val("02");
            $("#cpName").attr("disabled", false);
        } else {
            toastr.warning("请选择左侧要编辑的一项再进行编辑!");
        }

    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/CTRel/SaveCTRelInfo",
                        data: {"ExecType": "01", "busData": "{\"CTRelRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage = 0;
                        condition = '';
                        loaddata();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });


    /*--------------顶部菜单点击保存按钮----------*/

    $(".cSave").click(function () {
        var MaRd =  $("#guanlian3").getseldata().MaVerRd;
        var MTRd =  $("#guanlian4").getseldata().MaType;
        var TempRelName = $("#TempRelName").val().trim();
        var CDOCRd =  $("#defaultSelect").getseldata().CDOCRd;
        var SupRd =  $("#SupplierSelect").getseldata().SupRd;
        var CusRd =  $("#CustomerSelect").getseldata().CusRd;
        var correlationtype = $("#correlationtype").val();
        if (TempRelName == null || TempRelName == "") {
            toastr.warning("等级判断名称不能为空");
            return false;
        }
        var TempRelType = $("#TempRelType").val();
        if (TempRelType == null || TempRelType == "") {
            toastr.warning("模板类型不能为空");
            return false;
        }
   /*     var MaVerRd = $("#material").getseldata().MaVerRd;
        if (MaVerRd == null || MaVerRd == "") {
            toastr.warning("产品不能为空");
            return false;
        }*/
        var list4Data = getTableData("list4");
        /*if (list4Data.length <= 0) {
         toastr.warning("外观检验等级不能为空");
         return false;
         }*/
        var Remark = $("#beizhu").val().trim();
        var newData = {};

        var tableData = getTableData("list4");
        //新增
        if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
            var wglist4Data = [];
            if (list4Data.length > 0) {
                var k = 1;
                for (var i  in  list4Data) {
                    if (list4Data[i].CheckTempName == "" || list4Data[i].CheckTempName == null
                        || list4Data[i].CheckLevelName == "" || list4Data[i].CheckLevelName == null
                        || list4Data[i].AQLRuleName == "" || list4Data[i].AQLRuleName == null
                    ) {
                     //   toastr.warning("第" + k + "行的数据为空，不能保存");
                        return false;
                    }
                    delete list4Data[i].CTRelRd;
                    var wgData = {
                        "CPTRd": list4Data[i].CheckTempName,
                        "CLevelRd": list4Data[i].CheckLevelName,
                        "AQLRuleRd": list4Data[i].AQLRuleName
                    };
                    wglist4Data.push(wgData);
                    k++;
                }
            }
            var RelRd="";

            if(correlationtype=="00"){
                RelRd= MaRd;
            }else{
                RelRd=$("#MaTypeRd").val();
            }
            if (RelRd == null || RelRd == "") {
                toastr.warning("产品或者物料类别不能为空");
                return false;
            }
            if (CDOCRd == null || CDOCRd == "") {
                toastr.warning("文档不能为空");
                return false;
            }
            newData = {
                "TempRelName": TempRelName,
                "TempRelType": TempRelType,
                "CDOCRd": CDOCRd,
                "SupplierRd": SupRd,
                "CustomerRd":CusRd,
                "RelType":correlationtype,
                "RelRd": RelRd,
                "CTRelDtlInfo": wglist4Data,
                "Remark": Remark
            };
            console.log(newData);
            request({
                url: "/CTRel/SaveCTRelInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        }
        //更新
        else if (treeID != null && treeID != "") {
            if (CDOCRd == null || CDOCRd == "") {
                toastr.warning("文档不能为空");
                return false;
            }
            var wglist4Data = [];
            if (list4Data.length > 0) {
                var k = 1;
                for (var i  in  list4Data) {
                    var wgData = {
                        "CPTRd": list4Data[i].CPTRd,
                        "CLevelRd": list4Data[i].CLevelRd,
                        "AQLRuleRd": list4Data[i].AQLRuleRd
                    };
                    wglist4Data.push(wgData);
                    k++;
                }
            }

            if(correlationtype=="00"){
                RelRd= MaRd;
            }else{
                RelRd=$("#MaTypeRd").val();
            }
            if (RelRd == null || RelRd == "") {
                toastr.warning("初次修改检验模板信息，请重新选择物料核对信息是否正确");
                return false;
            }
            newData = {
                "CTRelRd": treeID,
                "TempRelName": TempRelName,
                "TempRelType": TempRelType,
                "CDOCRd": CDOCRd,
                "SupplierRd": SupRd,
                "CustomerRd":CusRd,
                "RelType":correlationtype,
                "RelRd": RelRd,
                "CTRelDtlInfo": wglist4Data,
                "Remark": Remark
            };
            console.log(newData);
            request({
                url: "/CTRel/SaveCTRelInfo",
                data: {"ExecType": "02", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        }
    });
});
