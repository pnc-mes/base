/**
 * Created by yin.yang on 2019/10/16.
 */
//@ sourceURL=test1.js
$(function () {
    var user = JSON.parse(localStorage.user);
    var dataTable1 = [], dataTable2 = [], dataTable3 = [], dataTable4 = [];

    var devmap = new Map(); //存取页面数据 需求量、物料存量、工序-设备

    var _maverrd = ""; //当前选中的物料Rd

    //装料list
    var colNamesArr4 = [
        {"Caption": "StartOpt", "Name": "StartOpt", "CType": "text", "Hidden": true},
        {"Caption": "物料批次", "Name": "Batch", "CType": "text", "Editable": false},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text", "Editable": false},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "CType": "text", "Editable": false},
        {"Caption": "可用数量", "Name": "CanNum", "CType": "text", "Editable": false},
        {"Caption": "备注", "Name": "Remark", "CType": "text", "Editable": false},
        {"Caption": "时间", "Name": "CurrentTime", "CType": "text", "Editable": false},
    ];
    var config4 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr4,
        width: 0.8,
        height: 0.415
    };
    config4.data.length = 0;
    fullTable(config4);//加载空表格
    //loadTable("list3", [], colNamesArr3, true);

    //卸料list
    var colNamesArr5 = [
        {"Caption": "StartOpt", "Name": "StartOpt", "CType": "text", "Hidden": true},
        {"Caption": "物料批次", "Name": "Batch", "CType": "text", "Editable": false},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text", "Editable": false},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "CType": "text", "Editable": false},
        {"Caption": "可用数量", "Name": "CanNum", "CType": "text", "Editable": false},
        {"Caption": "备注", "Name": "Remark", "CType": "text", "Editable": false},
        {"Caption": "时间", "Name": "CurrentTime", "CType": "text", "Editable": false},
    ];
    var config5 = {
        tableId: 'list5',
        data: [],
        colArr: colNamesArr5,
        width: 0.8,
        height: 0.415
    };
    config5.data.length = 0;
    fullTable(config5);//加载空表格


    //查询点击事件
    $("#search_").click(function () {

    });

//todo========================================工单控件start=====================================================
    var WoRds = "";
    var data = [];
    loadUl(data);
    $("#sltWo").click(function () {
        if (user.LineInfo == undefined || user.LineInfo == null) {
            //打开弹出框
            $("#myModal").modal("show");
            request({
                url: "/WO/GetAllWOInfo",
                data: {
                    "ExecType": "00",
                    "InitData": JSON.stringify({
                        "FiledList": [{
                            "FieldName": "Status",
                            "FieldOpt": "in",
                            "FieldVal": "(01,03)"
                        }, {
                            "FieldName": "CreateTime DESC",
                            "FieldOpt": "Order BY"
                        }]
                    })
                }
            }, function (Body) {
                data = [];
                Body.Data.forEach(function (obj) {
                    data.push({id: obj.WoRd, value: obj.WoCode});
                    loadUl(data);
                });
            });
        } else {
            //打开弹出框
            $("#myModal").modal("show");
            request({
                url: "/WO/GetAllWoinfoByLineRd",
                data: {
                    "LineRd": user.LineInfo.LineRd
                }
            }, function (Body) {
                data = [];
                Body.Data.forEach(function (obj) {
                    data.push({id: obj.ruid, value: obj.woCode});
                    loadUl(data);
                });
            });
        }
    });

    //列表赋值
    function loadUl(data) {
        var ul = $("#ulWo");
        ul.empty();
        data.forEach(function (obj) {
            ul.append("<li id='" + obj.id + "'>" + obj.value + "</li>")
        });
        ul.find('li').on('click', function () {
            //console.log($(this)[0].innerText)
            WoRds = $(this).eq(0).attr("id");
            getWo($(this).eq(0).attr("id"));
        });
    }

    $('#searchWo').on('input propertychange', function () {
        var v = $(this).val(), d = [];
        data.forEach(function (obj) {
            if (String(obj.value).toLowerCase().indexOf(v.toLowerCase()) >= 0) {
                d.push(obj)
            }
        });
        loadUl(d);
    });

    //获取工单信息
    function getWo(data) {
        reClear();
        request({
            url: "/WO/GetWOInfo",
            data: {"ExecType": "00", "BusData": JSON.stringify({WoRd: data})}
        }, function (Body) {
            var d = Body.Data;
            $("#WoRd").val(d.WoRd);
            //工单号
            $("#WoCode").val(d.WoCode);

            //显示工序
            emptyInput();
            var wfinfo = d.WFInfo;
            if (wfinfo != undefined) {
                $("#workflow").val(wfinfo.WFName);
                var specinfo = wfinfo.SpecInfo;
                if (specinfo != undefined) {
                    specinfo.forEach(function (obj) {
                        $("#workProcedure").append("<option value='" + obj.SpecVerRd + "'>" + obj.SpecName + "</option>");
                        var devginfo = obj.DevGInfo;
                        if (devginfo != undefined) {
                            devmap.set(String(obj.SpecVerRd), devginfo);
                        }
                    });
                    loadDev(specinfo[0].DevGInfo); //默认加载第一个
                }
            }
            /*//工单类型 d.WOTInfo.WTName
            $("#WTName").text(d.WOTInfo.WTName);
            //生产线别 d.LineInfo[0].LineName
            var lineName = "";
            if (d.LineInfo != undefined) {
                d.LineInfo.forEach(function (obj) {
                    lineName += obj.LineName;
                });
            }
            if (lineName.length > 0) {
                lineName = lineName.substr(0, lineName.length - 1);
            }
            $("#LineName").text(lineName);
            //工单生产数量 d.Num
            $("#Num").text(d.Num);
            //产品单位 d.UnitInfo.UnitName
            $("#UnitName").text(d.UnitInfo.UnitName);
            //产品代码 d.MaInfo.MaCode
            $("#MaCode").text(d.MaInfo.MaCode);
            //产品名称 d.MaInfo.MaName
            $("#MaName").text(d.MaInfo.MaName);
            //产品属性 d.MaInfo.MaDes
            $("#MaDes").text(d.MaInfo.MaDes == undefined ? "" : d.MaInfo.MaDes);*/
            $("#myModal").modal("hide");
        });
    }

//清空
    function reClear() {
        $("#WoRd").val("");
        //工单号
        $("#WoCode").val("");
        /*//工单类型 d.WOTInfo.WTName
        $("#WTName").text("");
        //生产线别 d.LineInfo[0].LineName
        $("#LineName").text("");
        //工单生产数量 d.Num
        $("#Num").text("");
        //产品单位 d.UnitInfo.UnitName
        $("#UnitName").text("");
        //产品代码 d.MaInfo.MaCode
        $("#MaCode").text("");
        //产品名称 d.MaInfo.MaName
        $("#MaName").text("");
        //产品属性 d.MaInfo.MaDes
        $("#MaDes").text("");*/
    }

    //todo========================================工单控件end=====================================================

    //todo=========================================物料批次文本框操作start===========================================
    function slModel(type) {
        var Batch = type == "00" ? $("#CZPCode").val().trim() : $("#XCZPCode").val().trim();
        if (Batch == null || Batch == "") {
            toastr.warning("物料批次不能为空");
            return false;
        }
        var WoRd = WoRds;
        if (WoRd == null || WoRd == "") {
            toastr.warning("请选择工单信息！");
            return false;
        }
        var LuType = $("#findModel").val(); //装卸方式
        if (LuType == null || LuType == "") {
            toastr.warning("请选择装卸方式！");
            return false;
        }
        var SpecRd = $("#workProcedure").val(); //工序
        if (SpecRd == null || SpecRd == "") {
            toastr.warning("请选择上料工序！");
            return false;
        }
        var DevRd = null;
        if (LuType == "01") { //当选择设备上料时
            var DevRd = $("#equipment").val(); //设备
            if (DevRd == null || DevRd == "") {
                toastr.warning("请选择上料设备！");
                return false;
            }
        }
        var WarningVaule = $("#number").val();
        var Remark = $("#SRemark").val();
        var datas = {
            "WoRd": WoRd,
            "LuType": LuType,
            "Type": type,//00上料，01下料
            "SpecRd": SpecRd,
            "DevRd": DevRd,
            "Batch": Batch,
            "WarningVaule": WarningVaule,
            "Remark": Remark
        };
        baseUDMaterial(datas);
        //清空内容
        $("#CZPCode").val("");
        $("#XCZPCode").val("");
        $("#SRemark").val("");
    }

    //上料文本操作
    $('#CZPCode').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            slModel("00");
        }
    });
    //上料事件
    $("#addMaterial").click(function () {
        slModel("00");
    });

    //下料文本框事件
    $('#XCZPCode').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            if ($(this).val().trim() == "") {
                toastr.warning("物料批次不能为空");
                return false;
            }
            var Batch = $(this).val().trim();
            var WoRd = WoRds;
            if (WoRd == null || WoRd == "") {
                toastr.warning("工单不能为空");
                return false;
            }
            var objBusData = JSON.stringify({"Batch": Batch});
            var objData = {
                "ExecType": "00",
                "BusData": objBusData,
            };
            request({url: "/Commom/GetCMBInfo", data: objData}, function (Body) {
                $("#KYnumber").val(Body.Data.CanNum);
                $("#ZSnumber").val(Body.Data.Num);
                $("#UnitName").text(Body.Data.UnitInfo.UnitName);
            });
        }
    });

    //下料事件
    $("#removeMaterial").click(function () {
        slModel("01");
    });

    function baseUDMaterial(data) {
        var type = data.Type;
        request({
            url: "/SunPort/UDMaterialFP/SaveUDMaterial",
            data: {"ExecType": "00", "BusData": JSON.stringify(data)}
        }, function (Body) {
            var objBusData = JSON.stringify({"Batch": data.Batch});
            var objData = {
                "ExecType": "00",
                "BusData": objBusData,
            };
            if (Body.MsgCode == "0x00000") {
                request({url: "/Commom/GetCMBInfo", data: objData}, function (Body) {
                    var MaCode = Body.Data.MaCode;
                    var MaName = Body.Data.MaName;
                    var MaDes = Body.Data.MaDes;
                    var CanNum = Body.Data.CanNum + Body.Data.UnitInfo.UnitName;
                    var Batch = Body.Data.Batch;
                    //获取当前时间
                    var date = new Date();
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    var day = date.getDate();
                    var hour = date.getHours();
                    var minute = date.getMinutes();
                    var second = date.getSeconds();
                    var CurrentTime = year + '年' + month + '月' + day + '日 ' + hour + ':' + minute + ':' + second;
                    var data = {
                        "MaCode": MaCode,
                        "MaName": MaName,
                        "MaDes": MaDes == null ? "" : MaDes,
                        "CanNum": CanNum,
                        "Batch": Batch,
                        "Remark": "",
                        "CurrentTime": CurrentTime,
                    };
                    if (type == "00") {
                        addSrow("list4", data);
                    } else {
                        addSrow("list5", data);
                    }

                });
            }
            toastr.success(Body.MsgDes);
        });

    }

// todo=========================================物料批次文本框操作end============================================


//todo=======================================页面切换事件===============================================
    //工序设备切换事件
    $("#findModel").change(function () {
        if ($("#findModel").val() == "00") {
            $(".shebei").hide();
        } else {
            $(".shebei").show();
        }
        reClear();
        emptyInput();
    });

    //工序切换事件
    $("#workProcedure").change(function () {
        $("#equipment").empty();
        emptyTable();
        var data = devmap.get($(this).val());
        if (data == undefined) {
            data = [];
        }
        loadDev(data);
    });


//todo======================================页面加载项==============================================
    //加载设备
    function loadDev(data) {
        data.forEach(function (obj) {
            $("#equipment").append("<option value='" + obj.DevRd + "'>" + obj.DevName + "</option>")
        });
    }

    //加载简单表格
    function loadTable(id, data, config, chk, event) {
        var config = {
            tableId: id,
            data: data,
            colArr: config,
            multiselect: chk,
            event: event,
            width: 0.99,
            height: 0.3
        };
        fullTable(config);
    }

    //清空文本数据
    function emptyInput() {
        $("#workflow").val("");
        $("#workProcedure").empty();
        $("#equipment").empty();
        //清空物料批次内容
        $("#CZPCode").val("");
        $("#XRemark").val("");
        $("#SRemark").val("");
        emptyTable();
    }

    //清空表格
    function emptyTable() {
        loadTable("list4", [], colNamesArr4, true);
        loadTable("list5", [], colNamesArr5, true);
        dataTable1 = [];
        dataTable2 = [];
        dataTable3 = [];
        dataTable4 = [];
        map = new Map();
        _maverrd = "";
    }

});
