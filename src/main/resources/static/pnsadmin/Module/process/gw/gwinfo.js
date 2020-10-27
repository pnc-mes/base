$(function () {
    var UDM = [];//用于存放上下料配置信息
    var Alarm = [];//用来接收警报信息
    var userData = [];//封装人员向信息
    //定义数组 设备集成 接受添加表格的值
    var tableData = [];
    var jkMap = new Map();
    jkMap.set("00", "进站成功");
    jkMap.set("01", "上机成功");
    jkMap.set("02", "下机成功");
    jkMap.set("03", "出站成功");
    jkMap.set("04", "进站失败");
    jkMap.set("05", "上机失败");
    jkMap.set("06", "下机失败");
    jkMap.set("07", "出站失败");
    jkMap.set("08", "上料成功");
    jkMap.set("09", "下料成功");
    jkMap.set("10", "上料失败");
    jkMap.set("11", "下料失败");
    jkMap.set("12", "进站前");
    jkMap.set("13", "上机前");
    jkMap.set("14", "下机前");
    jkMap.set("15", "出站前");

    var SysMap = new Map();
    SysMap.set("00", "产品实体");
    SysMap.set("01", "工位ID");
    SysMap.set("02", "两者都有");

    //定义变量区分选择框
    var id = 1;
    var colNamesArr4 = [
        {"Caption": "接口地址", "Name": "APIUrl", "CType": "text", "Editable": false},
        {"Caption": "TriggerType", "Name": "TriggerType", "CType": "text", "Editable": false, "Hidden": true},
        {"Caption": "触发点", "Name": "TriggerTypeTest", "CType": "text", "Editable": false},
        {"Caption": "SysVal", "Name": "SysVal", "CType": "text", "Editable": false, "Hidden": true},
        {"Caption": "内置参数", "Name": "SysValTest", "CType": "text", "Editable": false},
    ];
    var config4 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr4,
        width: 0.5,
        multiselect: true,
        height: 0.28
    };
    fullTable(config4);//加载空表格

    //加载打印机
    request({url: "/Printer/GetAllPrInfo", data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].PrRd + '>' + data[i].PrName + '【' + data[i].PrSer + '】</option>';
        }

        $("#prints").html(aa);
        $("#prints").selectpicker({
            width: 300
        });
    });

    //加载打印模板
    request({url: "/PrintT/GetAllPtInfo", data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].PtRd + '>' + data[i].PtName + '</option>';
        }

        $("#PrintTs").html(aa);
        $("#PrintTs").selectpicker({
            width: 200
        });
    });

    //表格合并
    function rowspan(tableData) {
        var j_data = new Map();
        var j_data1 = new Map();
        tableData.forEach(function (obj) {
            var i = 1;
            if (j_data.get(obj.dev) != null) {
                j_data.set(obj.dev, j_data.get(obj.dev) + 1);
                if (j_data1.get(obj.ysdev + obj.dev) != null) {
                    j_data1.set(obj.ysdev + obj.dev, j_data1.get(obj.ysdev + obj.dev) + 1);
                } else {
                    j_data1.set(obj.ysdev + obj.dev, 1)
                }
            } else {
                j_data.set(obj.dev, i);

                j_data1.set(obj.ysdev + obj.dev, 1);

            }
        });
        var chekID = 0;
        var ysdevkey = "";
        var table = ""
        j_data.forEach(function (value, key) {

            j_data1.forEach(function (value1, key1) {
                tableData.forEach(function (obj) {
                    if (obj.dev == key && (obj.ysdev + obj.dev) == key1) {
                        if (chekID == 0) {
                            ysdevkey = key1;
                            chekID = 1;
                            table = table + "<tr><td style='text-align:center;width: 30px'><input type='checkbox' id=" + obj.id + " ></td><td rowspan =" + j_data.get(key) + " style='text-align:center;width: 150px'>" + obj.dev + "</td><td rowspan =" + j_data1.get(obj.ysdev + key) + " style='text-align:center;width: 150px'>" + obj.ysdev + "</td><td style='text-align:center;;width: 100px'>" + obj.cfd + "</td><td style='text-align:center;;width: 100px'>" + obj.zxzl + "</td><td style='text-align:center;;width: 100px'>" + obj.zlType + "</td><td style='text-align:center;;width: 100px'>" + obj.zlnum + "</td></tr>"

                        } else {
                            if (ysdevkey != key1) {
                                ysdevkey = key1;
                                table = table + "<tr><td style='text-align:center;width: 30px'><input type='checkbox' id=" + obj.id + " ></td><td rowspan =" + j_data1.get(obj.ysdev + key) + " style='text-align:center;width: 150px'>" + obj.ysdev + "</td><td style='text-align:center;;width: 100px'>" + obj.cfd + "</td><td style='text-align:center;;width: 100px'>" + obj.zxzl + "</td><td style='text-align:center;;width: 100px'>" + obj.zlType + "</td><td style='text-align:center;;width: 100px'>" + obj.zlnum + "</td></tr>"

                            } else {
                                ysdevkey = key1;
                                table = table + "<tr><td style='text-align:center;width: 30px'><input type='checkbox' id=" + obj.id + " ></td><td style='text-align:center;;width: 100px'>" + obj.cfd + "</td><td style='text-align:center;;width: 100px'>" + obj.zxzl + "</td><td style='text-align:center;;width: 100px'>" + obj.zlType + "</td><td style='text-align:center;;width: 100px'>" + obj.zlnum + "</td></tr>"

                            }

                        }
                    }

                });
            });
            chekID = 0;
        });
        return table;
    };

    //加载设备设备组
    request({url: "/Device/GetAllDevInfo", data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        var dev = ""
        for (var i in data) {
            aa += '<option  value=' + data[i].DevRd + ',0' + '>' + data[i].DevName + '</option>';
            dev += '<option  value=' + data[i].DevRd + '>' + data[i].DevName + '</option>';
        }
        $("#sbsbz #sb").html(aa);
        $("#scsb").html(dev);
        $("#devs").html(dev);

    });
    request({url: "/DeviceG/GetAllDevGInfo", data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].DevGRd + ',1' + '>' + data[i].DevGpName + '</option>';
        }
        $("#sbsbz #sbz").html(aa);
    });
    //加载线体
    request({url: "/Line/GetAllLineInfo", data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].LineRd + '>' + data[i].LineName + '</option>';
        }

        $("#xt").html(aa);
        $("#xt").selectpicker({
            width: 200
        });
        $("#tlinameRd").html(aa);
        $("#tlinameRd").selectpicker({
            width: 200
        });

    });
    //加载工序
    request({url: "/Spec/GetAllSpecInfo", data: {"ExecType": "00",}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].SpecRd + '>' + data[i].SpecName + '</option>';
        }

        $("#gx").html(aa);

    });
    //加载产线作业人
    var InitData = {
        "FiledList": [
            {
                "FieldName": "UserName",
                "FieldOpt": "Order BY"
            }
        ]
    };
    var page = {
        PageIndex: "0",
        PageSize: 1000000
    };
    // obj : 业务数据
    var obj = {
        data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
        url: "/User/GetAllUserInfo"
    };
    request(obj, function (Body) {
        var data = Body.Data;
        //userData=data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].UserRd + '>' + data[i].UserName + '</option>';
        }
        $("#user").html(aa);
        $("#cxzyrdatas").html(aa);
    });

    //加载可用菜单
    request({url: "/Module/GetAllResInfo", data: ""}, function (Body) {
        var data = Body.Data;
        var relInfo = "";
        for (var i in data) {
            relInfo += '<option  value=' + data[i].ruid + '>' + data[i].resName + '</option>';
        }
        $("#xtcaidanDatas").html(relInfo);
        $("#xtcaidanDatas").selectpicker({
            width: 200
        });

    });
    //定义变量来直接点击保存时给个标识
    var reasultFlag = false;

    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        //添加是否为线体ID判断
        var isTrueLineRd = nodeinfo.nodeID;//将点击的树的id赋值给
        if (isTrueLineRd.charAt(0) == 0) {
            //toastr.success("请点击左侧+号查看当前产线工位信息!");
            var config4 = {
                tableId: 'list4',
                data: [],
                colArr: colNamesArr4,
                width: 0.5,
                multiselect: true,
                height: 0.28
            };
            fullTable(config4);//加载空表格
            $("#tlinameRd").selectpicker('val', "");
            $("#slinameRd").val(isTrueLineRd.substring(2));
            $("#sliname").val(nodeinfo.nodeText);
            $("#xt").val(nodeinfo.nodeText);
            $("#saveLineRd").val(isTrueLineRd.substring(2));
            $("#_right").hide();
            return false;
        }
        $("#sbsbz").selectpicker('val', "");
        $("#cxzyrdatas").selectpicker('val', "");
        $("#xtcaidanDatas").selectpicker('val', "");
        UDM = [];
        Alarm = []
        tableData = [];
        reasultFlag = true;
        //定位到过站配置页签
        //过站配置初始化
        $("#CarrierType00").prop("checked", false);
        $("#CarrierType01").prop("checked", false);
        $(".gzpz").tab("show")
        //上下料初始化
        UDM = [];//上下料配置值初始化
        $("#UDMType0").prop("checked", true);
        $("#UDMCarrierType0").prop("checked", false);
        $("#UDMCarrierType0").prop("checked", false);
        $("#DMArea0").prop("checked", false)
        $("#DMArea1").prop("checked", true);
        $("#sl").show();
        $("#xl").hide();
        $("#xlfw").hide();
        //上下料列表初始化
        $("#li").html("");
        //设备集成初始化
        $("#devs").selectpicker('val', "");
        $("#sys").empty();
        document.getElementById("cfd").options[0].selected = true;
        document.getElementById("s2").options[0].selected = true;
        document.getElementById("s3").options[0].selected = true;
        $("#s3").css({"width": "50px"});
        $("#k").show();
        $("#g").show();
        $("#c").hide();
        $("#zxzl").val("")
        //警报配置初始化
        document.getElementById("ExcepType").options[0].selected = true;
        $("#AlarmRev0").prop("checked", true);
        $("#d1").hide();
        $("#user").selectpicker('val', "");
        //警报异常表格初始化
        $("#li1").html("");
        //接口集成初始化
        $("#jkdz").val("");
        $("#cfd1").val("00");
        $("#nzcs").val("00");
        var config4 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr4,
            width: 0.5,
            multiselect: true,
            height: 0.28
        };
        fullTable(config4);//加载空表格


        $("#_right").show();
        treeID = nodeinfo.nodeID;//将点击的树的id赋值给
        var objBusData = JSON.stringify({"StationRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url: "/Station/GetStationInfo", data: objData}, function (Body) {
            $("#StationName").val(Body.Data.StationName);
            $("#sliname").val(Body.Data.LineInfo.LineName);
            $("#slinameRd").val(Body.Data.LineInfo.LineRd);

            $("#PrintCopy").val(Body.Data.PrintCopy);
            if (Body.Data.AssDevInfo != null && Body.Data.AssDevInfo != "") {
                var AssDevInfo = Body.Data.AssDevInfo;
                var devs = [];
                for (var i  in AssDevInfo) {
                    devs.push(AssDevInfo[i].DevRd)
                }
                $("#scsb").selectpicker('val', devs);

            } else {
                $("#scsb").selectpicker('val', "");
            }
            if (Body.Data.LineInfo != null && Body.Data.LineInfo != "") {
                var LineInfo = Body.Data.LineInfo;
                $("#xt").val(LineInfo.LineName);
                $("#saveLineRd").val(LineInfo.LineRd);
            }
            if (Body.Data.SpecInfo != null && Body.Data.SpecInfo != "") {
                var SpecInfo = Body.Data.SpecInfo;
                $("#gx").selectpicker('val', [SpecInfo.SpecRd]);
            }
            if (Body.Data.PrinterInfo != null && Body.Data.PrinterInfo != "") {
                /*$("#print").showData({
                    id: Body.Data.PrinterInfo.PrintRd,
                    name: Body.Data.PrinterInfo.PrName,
                    keyfield: "PrRd",
                    fields: [
                        {"name": "PrRd"},
                        {"name": "PrName"}
                    ]
                });*/
                $("#prints").selectpicker('val', [Body.Data.PrinterInfo.PrintRd]);
            }

            if (Body.Data.PrintTInfo != null && Body.Data.PrintTInfo != "") {
                /*$("#PrintT").showData({
                    id: Body.Data.PrintTInfo.PrintTRd,
                    name: Body.Data.PrintTInfo.PrintTName,
                    keyfield: "PtRd",
                    fields: [
                        {"name": "PtRd"},
                        {"name": "PtName"}
                    ]
                });*/
                $("#PrintTs").selectpicker('val', [Body.Data.PrintTInfo.PrintTRd]);
            }


            if (Body.Data.ExecType == "00") {
                $("#shebei").prop("checked", true);
                $("#show").show();
                $("#cxzyrdatas").selectpicker('val', "");
                $("#xtcaidanDatas").selectpicker('val', "");
                $("#hide").hide();
                $("#hide1").hide();
                var data = Body.Data.ExDetail;
                if (data.length > 0) {
                    var a = [];
                    for (var i  in data) {
                        if (data[i].ExecRd <= 0) {
                            a.push(data[i].ExecGRd + ",1")
                        }
                        if (data[i].ExecGRd <= 0) {
                            a.push(data[i].ExecRd + ",0")
                        }
                    }
                    $("#sbsbz").selectpicker('val', a);
                }


            } else if (Body.Data.ExecType == "01") {
                $("#cxzyr").prop("checked", true);
                $("#hide").show();
                $("#sbsbz").selectpicker('val', "");
                $("#xtcaidanDatas").selectpicker('val', "");
                $("#show").hide();
                $("#hide1").hide();
                var data = Body.Data.ExDetail;

                if (data.length > 0) {
                    var a = [];
                    for (var i  in data) {
                        a.push(data[i].ExecRd)
                    }
                    $("#cxzyrdatas").selectpicker('val', a);

                }
            } else {
                $("#caidan").prop("checked", true);
                $("#hide1").show();
                $("#show").hide();
                $("#hide").hide();
                $("#sbsbz").selectpicker('val', "");
                $("#cxzyrdatas").selectpicker('val', "");
                var data = Body.Data.ExDetail;

                if (data.length > 0) {
                    var a = [];
                    for (var i  in data) {
                        a.push(data[i].ExecRd)
                    }
                    $("#xtcaidanDatas").selectpicker('val', a);

                }
            }
            //过站设置
            if (Body.Data.OStation != null && Body.Data.OStation != "") {
                var O = Body.Data.OStation;
                if (O.Start == "00") {
                    $("#Start").prop("checked", true);
                } else {
                    $("#Start").prop("checked", false);
                }

                if (O.End == "00") {
                    $("#End").prop("checked", true);
                } else {
                    $("#End").prop("checked", false);
                }

                if (O.Up == "00") {
                    $("#Up").prop("checked", true);
                } else {
                    $("#Up").prop("checked", false);
                }

                if (O.Down == "00") {
                    $("#Down").prop("checked", true);
                } else {
                    $("#Down").prop("checked", false);
                }
                //载具解绑
                if (O.CarrierUnBind == "00") {
                    $("#CarrierUnBind").prop("checked", true);
                } else {
                    $("#CarrierUnBind").prop("checked", false);
                }
                //打印标签
                if (O.PrintLabel == "00") {
                    $("#PrintLabel").prop("checked", true);
                } else {
                    $("#PrintLabel").prop("checked", false);
                }
                if (O.CarrierType == "00") {
                    $("#CarrierType00").prop("checked", false);
                    $("#CarrierType01").prop("checked", true);
                } else if (O.CarrierType == "01") {
                    $("#CarrierType01").prop("checked", false);
                    $("#CarrierType00").prop("checked", true);
                } else if (O.CarrierType == "03") {
                    $("#CarrierType01").prop("checked", false);
                    $("#CarrierType00").prop("checked", false);
                }

            }

            //上下料配置
            if (Body.Data.UDM != null && Body.Data.UDM != "") {
                var U = Body.Data.UDM;
                U.forEach(function (i) {
                    var liname = "";
                    if (i.UDMType == "00") {
                        liname = "上料";
                    }
                    if (i.UDMType == "01") {
                        liname = "下料"
                    }
                    $("#li").append("<li name='sxl'>" + liname + "</li>");
                    //添加上下料配置信息
                    var u = {
                        "UDMType": i.UDMType,
                        "UDMCarrierType": i.UDMCarrierType,
                        "DMArea": i.DMArea
                    };
                    UDM.push(u);
                });
            } else {
                $("#UDMType0").prop("checked", true);
                $("#UDMType1").prop("checked", false);
                $("#sl").show();
                $("#xl").hide();
                $("#xlfw").hide();
                $("#li").html("");
            }

            //设备集成
            if (Body.Data.Integrated != null && Body.Data.Integrated != "") {
                var cfdMap = new Map();
                cfdMap.set("00", "进站成功");
                cfdMap.set("01", "上机成功");
                cfdMap.set("02", "下机成功");
                cfdMap.set("03", "出站成功");
                cfdMap.set("04", "进站失败");
                cfdMap.set("05", "上机失败");
                cfdMap.set("06", "下机失败");
                cfdMap.set("07", "出站失败");
                cfdMap.set("08", "漏刷");
                id = 1;
                var i = Body.Data.Integrated;
                i.forEach(function (e) {
                    var cfd = cfdMap.get(e.TriggerType);
                    var data = {
                        "id": id++,
                        "DevRd": e.DevRd, "dev": e.DevName,
                        "DevMapRd": e.DevMapRd, "ysdev": e.DevMapName,
                        "TriggerType": e.TriggerType, "cfd": cfd,
                        "zxzl": e.CmdText,
                        "CmdType": e.CmdType, "zlType": e.CmdType == "00" ? "开关常量" : "开关变量",
                        "CmdVal": e.CmdVal, "zlnum": e.CmdVal == "00" ? "开" : e.CmdVal == "01" ? "关" : "产品实体"
                    };
                    tableData.push(data);
                });
                //调用合并单元格方法
                var table = "";
                table = rowspan(tableData);
                //清空表格
                $("#list").empty();
                $("#list").append(" <tr> <td  style='width: 30px;text-align:center;'><input type='checkbox' id='zd'></td> <td style='width: 150px;text-align:center;'>设备</td> <td style='width: 150px;text-align:center;'>映射设备</td><td style='width: 100px;text-align:center;'>触发点</td><td style='width: 100px;text-align:center;'>执行指令</td> <td style='width: 100px;text-align:center;'>指令类型</td> <td style='width: 100px;text-align:center;'>指令值</td> </tr>")
                $("#zwsj").empty();
                if (table == "") {
                    $("#list").append("  <tr id='zwsj'><td colspan='7' style='text-align:center;'>暂无数据</td></tr>");
                } else {
                    $("#list").append(table);
                }
                table = "";
            } else {
                $("#list").empty();
                $("#list").append(" <tr> <td  style='width: 30px;text-align:center;'><input type='checkbox' id='zd'></td> <td style='width: 100px;text-align:center;'>设备</td> <td style='width: 100px;text-align:center;'>映射设备</td><td style='width: 100px;text-align:center;'>触发点</td><td style='width: 100px;text-align:center;'>执行指令</td> <td style='width: 100px;text-align:center;'>指令类型</td> <td style='width: 100px;text-align:center;'>指令值</td> </tr>")
                $("#zwsj").empty();
                $("#list").append("  <tr id='zwsj'><td colspan='7' style='text-align:center;'>暂无数据</td></tr>");
            }

            //警报设置
            if (Body.Data.Alarm != null && Body.Data.Alarm != "") {
                //异常行为
                var ExcepTypeMap = new Map();
                ExcepTypeMap.set("00", "漏刷");
                ExcepTypeMap.set("01", "进站失败");
                ExcepTypeMap.set("02", "上机失败");
                ExcepTypeMap.set("03", "下机失败");
                ExcepTypeMap.set("04", "出站失败");
                ExcepTypeMap.set("05", "上料失败");
                ExcepTypeMap.set("06", "接口集成失败");

                var A = Body.Data.Alarm;
                //情况异常列表
                $("#li1").html("");
                A.forEach(function (i) {
                    //异常行为
                    var ExcepType = i.ExcepType;
                    var ExcepVal = ExcepTypeMap.get(ExcepType);

                    //警报方式
                    var AlarmType = i.AlarmType;
                    //警报接受者
                    var AlarmRev = i.AlarmRev;
                    //用于接收指定人员

                    var AlarmRevDtl = [];
                    var A = i.AlarmRevDtl;
                    if (A != null) {
                        A.forEach(function (e) {
                            AlarmRevDtl.push({"UserRd": e.UserRd})
                        });
                    }
                    $("#li1").append("<li name='ycxw'>" + ExcepVal + "</li>");
                    //添加上下料配置信息
                    var u = {
                        "ExcepType": ExcepType,
                        "AlarmType": AlarmType,
                        "AlarmRev": AlarmRev,
                        "AlarmRevDtl": AlarmRevDtl
                    };
                    Alarm.push(u);
                });
            } else {
                $("#li1").html("");
            }

            //接口集成
            if (Body.Data.StationAPI != null && Body.Data.StationAPI != "") {

                var StationAPI = Body.Data.StationAPI;
                var ArrTable = [];
                StationAPI.forEach(function (e) {
                    var Arrby = {
                        "APIUrl": e.APIUrl,
                        "TriggerType": e.TriggerType,
                        "TriggerTypeTest": jkMap.get(e.TriggerType),
                        "SysVal": e.SysVal,
                        "SysValTest": SysMap.get(e.SysVal)
                    };
                    ArrTable.push(Arrby)


                })
                var config4 = {
                    tableId: 'list4',
                    data: ArrTable,
                    colArr: colNamesArr4,
                    width: 0.5,
                    multiselect: true,
                    height: 0.28
                };
                fullTable(config4);

            } else {
                var config4 = {
                    tableId: 'list4',
                    data: [],
                    colArr: colNamesArr4,
                    width: 0.5,
                    multiselect: true,
                    height: 0.28
                };
                fullTable(config4);//加载空表格
            }


            $("#status").val(Body.Data.Status);
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
        currentPage = treeSearchs("/Station/GetAllStationInfo", "StationRd", "StationName", "StationName", condition, currentPage, config);
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
            currentPage = treeSearchs("/Station/GetAllStationInfo", "StationRd", "StationName", "StationName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/Station/GetAllStationInfo", "StationRd", "StationName", "StationName", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/Station/GetAllStationInfo", "StationRd", "StationName", "StationName", condition, currentPage, config);
    });

    $("#_right").hide();
    var treeID = null;//树的id


    $("#shebei").change(function () {
        if ($(this).is(":checked")) {
            $("#show").show();
            $("#hide").hide();
            $("#hide1").hide();
        }
    });
    $("#cxzyr").change(function () {
        if ($(this).is(":checked")) {
            $("#hide").show();
            $("#show").hide();
            $("#hide1").hide();
        }
    });
    $("#caidan").change(function () {
        if ($(this).is(":checked")) {
            $("#hide1").show();
            $("#hide").hide();
            $("#show").hide();
        }
    });
    //点击指令类型
    $("#s2").change(function () {
        var CmdType = $("#s2 option:selected").val();
        if (CmdType == '00') {
            $("#k").show();
            $("#g").show();
            $("#c").hide();
            document.getElementById("s3").options[0].selected = true;
            $("#s3").css({"width": "50px"});
        } else if (CmdType == '01') {
            $("#k").hide();
            $("#g").hide();
            $("#c").show();
            document.getElementById("s3").options[2].selected = true;
            $("#s3").css({"width": "80px"});
        }

    });

    //新加功能
    //设备集成
    //点击添加事件

    $("#save").click(function () {
        var SLineRd = $("#slinameRd").val();
        if (SLineRd == null || SLineRd == "") {
            toastr.warning("请选择复制线体！");
            return false;
        }
        var LineRdArr = $("#tlinameRd").val();
        if (LineRdArr == null || LineRdArr.length <= 0) {
            toastr.warning("请选择目标线体！");
            return false;
        }
        var TLineRd = LineRdArr[0];
        var newData = {"SLineRd": SLineRd, "TLineRd": TLineRd};

        var stationSize;
        var Linename = "";
        request({
            url: "/Station/GetAllStationInfoByLineRd",
            data: {"busData": TLineRd}
        }, function (Body) {
            stationSize = Body.Data.id;
            Linename = Body.Data.name;
        });

        if (stationSize != undefined && stationSize != null && stationSize > 0) {
            layer.confirm('目标线体:【' + Linename + '】已存在工位信息，是否继续复制？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/Station/SaveStationInfo",
                        data: {"ExecType": "04", "busData": JSON.stringify(newData)}
                    }, function (Body) {
                        toastr.success(Body.MsgDes);
                        $("#sliname").val('');
                        $("#tlinameRd").selectpicker('val', "");
                        loaddata();
                    });
                    layer.closeAll("dialog");
                }
            );
        } else {
            request({
                url: "/Station/SaveStationInfo",
                data: {"ExecType": "04", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#sliname").val('');
                $("#tlinameRd").selectpicker('val', "");
                loaddata();
            });
        }
        $("#myModal").modal("hide");
    });

    /*------------------点击复制显示页面------------------*/
    $("#copy").click(function () {
        var SLineRd = $("#slinameRd").val();
        if (SLineRd == null || SLineRd == "") {
            toastr.warning("请点击左侧选择需要复制的产线工位信息!");
            return false;
        }
        $("#tlinameRd").selectpicker('val', "");
        $("#myModal").modal("show");
    });

    var checkNumber = 0;
    $("#add").click(function () {
        //获取设备值
        var DevRd = $("#devs").val();
        var DevRd = DevRd[0];
        var dev = $("#devs option:selected").text();
        if (dev == null || dev == undefined || dev == "") {
            toastr.warning("设备不能为空;");
            return
        }
        //获取映射设备
        var ysdev = $("#sys option:selected").text();
        var DevMapRd = $("#sys option:selected").attr("id");
        if (ysdev == null || ysdev == undefined || ysdev == "") {
            toastr.warning("设备不能为空;");
            return
        }
        //获取触发点
        var cfd = $("#cfd option:selected").text();
        var TriggerType = $("#cfd option:selected").val();

        //获取执行指令
        var zxzl = $("#zxzl").val();
        if (zxzl == null || zxzl == undefined || zxzl == "") {
            toastr.warning("执行指令不能为空;");
            return
        }
        //获取执行类型
        var zlType = $("#s2 option:selected").text();
        var CmdType = $("#s2 option:selected").val();
        if (zlType == null || zlType == undefined || zlType == "") {
            toastr.warning("执行类型不能为空;");
            return
        }
        //获取指令值
        var zlnum = $("#s3 option:selected").text();
        var CmdVal = $("#s3 option:selected").val();
        if (zlnum == null || zlnum == undefined || zlnum == "") {
            toastr.warning("指令值不能为空;");
            return
        }
        checkNumber = 0
        //定义变量防止添加重复数据
        var fals = false;
        var data = {
            "id": id++,
            "DevRd": DevRd, "dev": dev,
            "DevMapRd": DevMapRd, "ysdev": ysdev,
            "TriggerType": TriggerType, "cfd": cfd,
            "zxzl": zxzl,
            "CmdType": CmdType, "zlType": zlType,
            "CmdVal": CmdVal, "zlnum": zlnum
        };

        tableData.push(data);

        //调用合并单元格方法
        var table = rowspan(tableData);
        //清空表格
        $("#list").empty();
        $("#list").append(" <tr> <td  style='width: 30px;text-align:center;'><input type='checkbox' id='zd'></td> <td style='width: 150px;text-align:center;'>设备</td> <td style='width: 150px;text-align:center;'>映射设备</td><td style='width: 100px;text-align:center;'>触发点</td><td style='width: 100px;text-align:center;'>执行指令</td> <td style='width: 100px;text-align:center;'>指令类型</td> <td style='width: 100px;text-align:center;'>指令值</td> </tr>")
        $("#zwsj").empty();
        if (table == "") {
            $("#list").append("  <tr id='zwsj'><td colspan='7' style='text-align:center;'>暂无数据</td></tr>");
        } else {
            $("#list").append(table);
        }
        table = "";
    });

    //获取选择的id
    function aa() {
        var checkID = [];
        $("#list input:checkbox").each(function () {
            if ($(this).is(":checked") == true) {
                var id = $(this).attr("id");
                checkID.push(id);
            }
        })
        return checkID;

    }

    //表格删除
    $("#del1").click(function () {
        var checkID = aa();
        if (checkID.length == 0) {
            toastr.warning("请选择数据!");
            return false
        }

        checkID.forEach(function (i) {
            if (i == "zd" && checkID.length == 1) {
                toastr.warning("无数据!");
                return
            } else {
                checkNumber = 0;
                var tableData1 = [];
                tableData.forEach(function (e) {
                    if (e.id != i) {
                        tableData1.push(e);
                    }
                })
                tableData = tableData1;
            }
        })
        //调用合并单元格方法
        var table = rowspan(tableData);
        //清空表格
        $("#list").empty();
        $("#list").append(" <tr> <td  style='width: 30px;text-align:center;'><input type='checkbox' id='zd'></td> <td style='width: 150px;text-align:center;'>设备</td> <td style='width: 150px;text-align:center;'>映射设备</td> <td style='width: 100px;text-align:center;'>触发点</td><td style='width: 100px;text-align:center;'>执行指令</td> <td style='width: 100px;text-align:center;'>指令类型</td> <td style='width: 100px;text-align:center;'>指令值</td> </tr>")
        $("#zwsj").empty();
        if (table == "") {
            $("#list").append("  <tr id='zwsj'><td colspan='7' style='text-align:center;'>暂无数据</td></tr>");
        } else {
            $("#list").append(table);
        }
        table = "";
        bb(false);
    });

    function bb(flag) {
        if (flag) {
            $("#list tbody input[type='checkbox']").prop("checked", "true");
        } else {
            $("#list tbody input[type='checkbox']").removeAttr("checked");
        }

    }

    //表格全选
    $(document).on("click", "#zd", function () {
        if ($(this).is(":checked")) {
            bb(true);
        } else {
            bb(false);
        }
    });
    //表格选中事件
    $(document).on("click", "#list tbody input[type='checkbox']", function () {
        if ($("#zd").is(":checked")) {
            checkNumber = tableData.length;
        }
        if ($(this).is(":checked")) {
            checkNumber++
        } else {
            checkNumber--
        }

        if (tableData.length == parseInt(checkNumber)) {
            $("#zd").prop("checked", "true")
        } else {
            $("#zd").removeAttr("checked");
        }

    });
    //设备选择事件
    $("#devs").change(function () {
        var dev = $("#devs").val();

        if (dev == null) {
            $("#sys").empty();
        } else {
            request({
                data: {"ExecType": "00", "BusData": JSON.stringify({"DevRd": dev[0]})},
                url: "/Device/GetDevInfo"
            }, function (Body) {
                var data = Body.Data.DevMapInfo;
                $("#sys").empty();
                data.forEach(function (e) {
                    $("#sys").append("<option id=" + e.DevMapRd + " value=" + e.DevMapRd + ">" + e.DevMapName + "</option>")
                })

            })
        }
    });

    //接口集成添加
    //存放接口集成数据
    $("#add2").click(function () {
        var url = $("#jkdz").val().trim();
        if (url == null || url == "") {
            toastr.warning("请输入接口地址!");
            return false;
        }
        var fals = false;
        //获取表格数据
        /* var tableData=getRowData("list4");
         //判断接口地址是否重复"
         tableData.forEach(function (e) {

             if(url==e.APIUrl){
                 fals=true;
             }
         });

         if(fals){
             toastr.warning("请勿重复输入接口地址!");
             return false;
         }*/

        //获得触发点信息
        var TriggerTypejc = $("#cfd1 option:selected").val();
        var TriggerText = $("#cfd1 option:selected").text();


        //获取内置参数
        var SysVal = $("#nzcs option:selected").val()
        var SysValTest = $("#nzcs option:selected").text();

        var data = {
            "APIUrl": url,
            "TriggerType": TriggerTypejc,
            "TriggerTypeTest": TriggerText,
            "SysVal": SysVal,
            "SysValTest": SysValTest
        }
        addSrow("list4", data);


    });

    //接口集成删除
    $("#del2").click(function () {
        //删除选中的数据
        derow("list4")
    });

    /*-----------------加载树------------*/
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        request({
            url: '/Station/GetAllStationInfoTree',
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "LineName",
                            "FieldOpt": "Order BY"
                        }]
                })
            }
        }, function (Body) {

            var busData = [];
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var data = {"id": "", "name": "", "Info": []};
                data.id = "00" + treeData[i].LineRd;
                data.name = treeData[i].LineName;
                data.Info = treeData[i].StationList;
                busData.push(data);
            }
            var rules = [{
                id: "id",
                text: "name",
                children: "Info"
            },
                {
                    id: "id",
                    text: "name",
                    children: "Info"
                }
            ];
            var config = {
                id: "jstree_demo1",
                data: {
                    source: busData,
                    rule: rules
                },
                event: {
                    onClick: onClicks
                }
            };
            //config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    // 首次加载数据
    loaddata();


    /*--------------点击新增按钮顶部菜单----------*/
    var formData = {};
    $(".cAdd").click(function () {
        var LineRd = $("#saveLineRd").val();
        if (LineRd == null || LineRd == "") {
            toastr.warning("请点击左侧选择产线在进行工位新增操作!");
            return false;
        }

        //定位到过站配置页签
        $(".gzpz").tab("show");
        id = 1;
        tableData = [];
        //清空表格
        $("#list").empty();
        $("#list").append(" <tr> <td  style='width: 30px;text-align:center;'><input type='checkbox' id='zd'></td> <td style='width: 150px;text-align:center;'>设备</td> <td style='width: 150px;text-align:center;'>映射设备</td> <td style='width: 100px;text-align:center;'>触发点</td><td style='width: 100px;text-align:center;'>执行指令</td> <td style='width: 100px;text-align:center;'>指令类型</td> <td style='width: 100px;text-align:center;'>指令值</td> </tr>")
        $("#list").append("  <tr id='zwsj'><td colspan='7' style='text-align:center;'>暂无数据</td></tr>");
        UDM = [];//上下料配置值初始化
        $("#UDMType0").prop("checked", true);
        $("#UDMCarrierType0").prop("checked", false);
        $("#UDMCarrierType0").prop("checked", false);
        $("#DMArea0").prop("checked", false)
        $("#DMArea1").prop("checked", true)
        $("#sl").show();
        $("#xl").hide();
        $("#xlfw").hide();
        //设备集成初始化
        $("#devs").selectpicker('val', "");
        $("#sys").empty();
        document.getElementById("cfd").options[0].selected = true;
        document.getElementById("s2").options[0].selected = true;
        document.getElementById("s3").options[0].selected = true;
        $("#s3").css({"width": "50px"});
        $("#k").show();
        $("#g").show();
        $("#c").hide();
        $("#zxzl").val("")

        $("#li").html("");
        //警报配置初始化
        Alarm = []
        document.getElementById("ExcepType").options[0].selected = true;
        $("#AlarmRev0").prop("checked", true);
        $("#d1").hide();
        $("#user").selectpicker('val', "");
        $("#li1").html("");
        //接口集成初始化
        $("#jkdz").val("");
        $("#cfd1").val("00");
        $("#nzcs").val("00");
        var config4 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr4,
            width: 0.5,
            multiselect: true,
            height: 0.28
        };
        fullTable(config4);//加载空表格

        reasultFlag = true;
        $("#shebei").prop("checked", true);
        $("#_right").show();
        $("#show").show();
        $("#hide").hide();
        $("#hide1").hide();
        treeID = null;
        $("#ExecType").val("00");
        $("#beizhu").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#StationName").val("");
        $("#sliname").val("");
        $("#slinameRd").val("");
        //  $("#glsb").clearseldata("DevRd");
        $("#gx").selectpicker('val', "");
        //$("#print").clearseldata("PrRd");
        //$("#PrintT").clearseldata("PtRd");
        $("#prints").selectpicker('val', "");
        $("#PrintTs").selectpicker('val', "");
        $("#status").val("00");
        $("#scsb").selectpicker('val', "");
        $("#xtcaidanDatas").selectpicker('val', "");
        $("#sbsbz").selectpicker('val', "");
        $("#cxzyrdatas").selectpicker('val', "");
        $("#PrintCopy").val("1");
        $("#Down").prop("checked", false);
        $("#Up").prop("checked", false);
        $("#Start").prop("checked", false);
        $("#End").prop("checked", false);
        $("#PrintLabel").prop("checked", false);
        $("#CarrierType00").prop("checked", false);
        $("#CarrierType01").prop("checked", false);
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/Station/SaveStationInfo",
                        data: {"ExecType": "01", "busData": "{\"StationRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        $("#_right").hide();
                        currentPage = 0;
                        condition = '';
                        loaddata();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧产线下要删除的工位再进行删除操作!");
        }
    });

    //双击作业载体取消选中
    $("#CarrierType00").dblclick(function () {
        $("#CarrierType00").prop("checked", false);
    })

    $("#CarrierType01").dblclick(function () {
        $("#CarrierType01").prop("checked", false);
    })

    //上料点击事件
    $("#UDMType0").click(function () {
        $("#sl").show();
        $("#xl").hide();
        $("#xlfw").hide();

    });
    $("#UDMType1").click(function () {
        $("#xl").show();
        $("#xlfw").show();
        $("#sl").hide();
    });

    //上下料添加
    $("#addUDMType").click(function () {
        //上下料取值
        var UDMType = "";
        var liname = "";
        if ($("#UDMType0").prop("checked") == true) {
            //00#上料
            UDMType = "00";
        } else if ($("#UDMType1").prop("checked") == true) {
            //01#下料
            UDMType = "01";
        }
        //上下载体取值
        var UDMCarrierType = "";
        if ($("#UDMCarrierType0").prop("checked") == true) {
            //00#载具
            UDMCarrierType = "00";
        } else if ($("#UDMCarrierType1").prop("checked") == true) {
            //01#材料实体
            UDMCarrierType = "01";
        }
        if (UDMCarrierType == '') {
            toastr.warning("请选择上下载体信息!");
            return false;
        }
        //下料操作范围
        var DMArea = null;
        if (UDMType == "00") {
            DMArea = null;
            liname = "上料";
        } else if (UDMType == "01") {
            liname = "下料";
            if ($("#DMArea0").prop("checked") == true) {
                //00#载具
                DMArea = "00";
            } else if ($("#DMArea1").prop("checked") == true) {
                //01#材料实体
                DMArea = "01";
            }
        }

        //获取列表内的值
        var a = false;
        $("#ul li").each(function () {
            var y = $(this).text();
            if (liname == y) {
                a = true;
            }
        });

        if (a) {
            a = false;
            toastr.warning("请勿重复添加上下料信息!");
            return false;
        }
        $("#li").append("<li name='sxl'>" + liname + "</li>");
        //添加上下料配置信息
        var u = {
            "UDMType": UDMType,
            "UDMCarrierType": UDMCarrierType,
            "DMArea": DMArea
        };
        UDM.push(u);

    });

    //上下料点击列表信息
    var litext = null;
    $(document).on("click", "#li li", function () {
        $("#li li").css("background-color", '')
        $(this).css("background-color", "#D3D6DA");
        litext = $(this).text();
    });
    //上下料双击取消选中
    $(document).on("dblclick", "#li li", function () {
        $(this).css("background-color", "");
        litext = null;
    });

    //上下料上移
    $("#sy").click(function () {
        if (litext == null) {
            toastr.warning("请选择信息再移动");
            return false;
        }
        var UDMType = null;
        if (litext == "上料") {
            UDMType = "00"
        }
        if (litext == "下料") {
            UDMType = "01"
        }
        for (var i = 0; i < UDM.length; i++) {
            if (UDM[i].UDMType == UDMType && i == 0) {
                toastr.warning("到顶了,移不动了");
                return false;
            } else if (UDM[i].UDMType == UDMType && i != 0) {
                var u = UDM[i - 1];
                UDM[i - 1] = UDM[i];
                UDM[i] = u;

            }
        }
        $("#li").html("");
        UDM.forEach(function (e) {
            var y = e.UDMType == "00" ? "上料" : "下料";
            $("#li").append("<li name='sxl'>" + y + "</li>");
        })
        litext = null;

    });

    //上下料下移
    $("#xy").click(function () {
        if (litext == null) {
            toastr.warning("请选择信息再移动");
            return false;
        }
        var UDMType = null;
        if (litext == "上料") {
            UDMType = "00"
        }
        if (litext == "下料") {
            UDMType = "01"
        }
        var u = {};
        var j = null;
        for (var i = 0; i < UDM.length; i++) {
            if (UDM[i].UDMType == UDMType && i == (UDM.length - 1)) {
                toastr.warning("到底了,移不动了");
                return false;
            } else if (UDM[i].UDMType == UDMType && i != (UDM.length - 1)) {
                u = UDM[i];
                j = i;
            }

        }
        if (j != null) {
            UDM[j] = UDM[j + 1];
            UDM[j + 1] = u;
        }
        $("#li").html("");
        UDM.forEach(function (e) {
            var y = e.UDMType == "00" ? "上料" : "下料";
            $("#li").append("<li name='sxl'>" + y + "</li>");
        })
        litext = null;
    });

    //上下料删除
    $("#del").click(function () {
        var udm1 = [];
        if (litext == null) {
            toastr.warning("请选择要删除的信息!");
            return false;
        }
        var UDMType = null;
        if (litext == "上料") {
            UDMType = "00"
        }
        if (litext == "下料") {
            UDMType = "01"
        }
        UDM.forEach(function (e) {
            if (e.UDMType != UDMType) {
                udm1.push(e)
            }
        })
        UDM = udm1;
        $("#li").html("");
        UDM.forEach(function (e) {
            var y = e.UDMType == "00" ? "上料" : "下料";
            $("#li").append("<li name='sxl'>" + y + "</li>");
        })
        litext = null;
    });

    //警报添加事件
    $("#adds").click(function () {
        //异常行为
        var ExcepVal = $("#ExcepType option:selected").text();
        var ExcepType = $("#ExcepType option:selected").val();
        //警报方式
        var AlarmType = null;
        if ($("#AlarmType0").prop("checked") == true) {
            //00#弹屏
            AlarmType = "00";
        }

        if (AlarmType == null) {
            toastr.warning("请选择警报方式!");
            return false;
        }
        //警报接受者
        var AlarmRev = null;
        //用于接收指定人员
        var AlarmRevDtl = [];
        if ($("#AlarmRev0").prop("checked") == true) {
            //00#全部
            AlarmRev = "00";
        } else if ($("#AlarmRev1").prop("checked") == true) {
            //01#指定人员
            // AlarmRevDtl.push({"UserRd":"UserRd"})
            AlarmRev = "01";
        }
        if (AlarmRev == null) {
            toastr.warning("请选择警报接受者!");
            return false;
        }
        if (AlarmRev == "01") {
            var data = $("#user").val();
            if (data == null || data.length == 0) {
                toastr.warning("请选择指定接受人员!");
                return false;
            }
            for (var i in data) {
                AlarmRevDtl.push({"UserRd": data[i]})
            }
        }
        //获取列表内的值
        var a = false;
        $("#ul1 li").each(function () {
            var y = $(this).text();
            if (ExcepVal == y) {
                a = true;
            }
        });

        if (a) {
            a = false;
            toastr.warning("请勿重复添加异常行为!");
            return false;
        }
        $("#li1").append("<li name='ycxw'>" + ExcepVal + "</li>");
        //添加上下料配置信息
        var u = {
            "ExcepType": ExcepType,
            "AlarmType": AlarmType,
            "AlarmRev": AlarmRev,
            "AlarmRevDtl": AlarmRevDtl
        };

        Alarm.push(u);

    });
    //警报异常行为点击列表信息
    var li1text = null;
    $(document).on("click", "#li1 li", function () {
        $("#li1 li").css("background-color", '')
        $(this).css("background-color", "#D3D6DA");
        li1text = $(this).text();
    });
    //警报异常行为
    $(document).on("dblclick", "#li1 li", function () {
        $(this).css("background-color", "");
        li1text = null;
    });
    //异常行为删除
    $("#dels").click(function () {
        var Alarm1 = [];
        if (li1text == null) {
            toastr.warning("请选择要删除的信息!");
            return false;
        }
        var ExcepType = null;
        var m = new Map();
        m.set("00", "漏刷");
        m.set("01", "进站失败");
        m.set("02", "上机失败");
        m.set("03", "下机失败");
        m.set("04", "出站失败");
        m.set("05", "上料失败");
        m.set("06", "接口集成失败");

        if (li1text == "漏刷") {
            ExcepType = "00"
        }
        if (li1text == "进站失败") {
            ExcepType = "01"
        }
        if (li1text == "上机失败") {
            ExcepType = "02"
        }
        if (li1text == "下机失败") {
            ExcepType = "03"
        }
        if (li1text == "出站失败") {
            ExcepType = "04"
        }
        if (li1text == "上料失败") {
            ExcepType = "05"
        }
        if (li1text == "接口集成失败") {
            ExcepType = "06"
        }

        Alarm.forEach(function (e) {
            if (e.ExcepType != ExcepType) {
                Alarm1.push(e)
            }
        })
        Alarm = Alarm1;
        $("#li1").html("");
        Alarm.forEach(function (e) {
            var y = m.get(e.ExcepType);
            $("#li1").append("<li name='sxl'>" + y + "</li>");
        })
        li1text = null;
    });
    //点击警报接受者
    //1.全部
    $("#AlarmRev0").click(function () {
        $("#d1").hide();
    })

    //2.指定人员
    $("#AlarmRev1").click(function () {
        $("#d1").show();
    })


    /*--------------点击保存按钮顶部菜单----------*/
    var newData = {};
    $(".cSave").click(function () {

        if (!reasultFlag) {
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }

        var StationName = $("#StationName").val().trim();
        if (StationName == null || StationName == "") {
            toastr.warning("保存失败，工位名称不能为空");
            return false;
        }
        var LineRd = $("#saveLineRd").val();
        if (LineRd == null) {
            toastr.warning("保存失败，线体不能为空");
            return false;
        }
        /* if (LineRd == null || LineRd == "") {

         }*/
        var SpecRdArr = $("#gx").val();
        if (SpecRdArr == null || SpecRdArr.length <= 0) {
            toastr.warning("保存失败，工序不能为空");
            return false;
        }

        var SpecRd = SpecRdArr[0];
        /* if (SpecRd == null || SpecRd == "") {
             toastr.warning("保存失败，工序不能为空");
             return false;
         }*/
        //生产设备
        var AssDevInfo = [];
        var devData = $("#scsb").val();
        for (var i in devData) {
            // alert(JSON.stringify(devData[i]));
            AssDevInfo.push({"DevRd": devData[i]})
        }
        //var DevRd = $("#glsb").getseldata().DevRd;
        var ExecType = null;
        if ($("#shebei").is(":checked")) {
            ExecType = "00"
            if ($("#sbsbz").val() == null || $("#sbsbz").val() == "") {
                toastr.warning("保存失败，设备设备组不能为空");
                return false;
            }
            //设备是0 设备组是1
            var data = $("#sbsbz").val();
            var ExDetail = [];
            for (var i in data) {
                if (data[i].indexOf(",0") >= 0) {
                    var split = data[i].split(",0");
                    var ExDetails = {
                        "ExecRd": split[0],
                        "ExecGRd": ""
                    }
                    ExDetail.push(ExDetails)
                }
                if (data[i].indexOf(",1") >= 0) {
                    var split = data[i].split(",1");
                    var ExDetails = {
                        "ExecRd": "",
                        "ExecGRd": split[0]
                    }
                    ExDetail.push(ExDetails)
                }
            }

        }


        if ($("#cxzyr").is(":checked")) {
            ExecType = "01";
            if ($("#cxzyrdatas").val() == null || $("#cxzyrdatas").val() == "") {
                toastr.warning("保存失败，产线作业人不能为空");
                return false;
            }

            var UserSources = $("#cxzyrdatas").val();
            var ExDetail = [];
            for (var obj in UserSources) {
                var LineInfos = {
                    "ExecRd": UserSources[obj],
                    "ExecGRd": ""
                };
                ExDetail.push(LineInfos);
            }

        }

        if ($("#caidan").is(":checked")) {
            ExecType = "02";
            if ($("#xtcaidanDatas").val() == null || $("#xtcaidanDatas").val() == "") {
                toastr.warning("保存失败，菜单不能为空");
                return false;
            }

            var RelSources = $("#xtcaidanDatas").val();
            var ExDetail = [];
            for (var obj in RelSources) {
                var LineInfos = {
                    "ExecRd": RelSources[obj],
                    "ExecGRd": ""
                };
                ExDetail.push(LineInfos);
            }

        }

        if (ExecType == null) {
            toastr.warning("保存失败，执行实体类型不能为空");
            return false;
        }

        var Status = $("#status option:selected").val();


        var Remark = $("#beizhu").val().trim();

        var Up = null;
        if ($("#Up").prop("checked") == true) {
            Up = "00";
        } else {
            Up = "01";
        }

        var Start = null;
        if ($("#Start").prop("checked") == true) {
            Start = "00";
        } else {
            Start = "01";
        }

        var Down = null;
        if ($("#Down").prop("checked") == true) {
            Down = "00";
        } else {
            Down = "01";
        }

        var End = null;
        if ($("#End").prop("checked") == true) {
            End = "00";
        } else {
            End = "01";
        }


        //载具解绑
        var CarrierUnBind = null;
        if ($("#CarrierUnBind").prop("checked") == true) {
            CarrierUnBind = "00";
        } else {
            CarrierUnBind = "01";
        }
        //打印标签
        var PrintLabel = null;
        if ($("#PrintLabel").prop("checked") == true) {
            PrintLabel = "00";
        } else {
            PrintLabel = "01";
        }

        //作业载体取值
        var CarrierType = null;
        if ($("#CarrierType00").prop("checked") == true) {
            //载具
            CarrierType = "01";
        } else if (($("#CarrierType01").prop("checked") == true)) {
            //产品实体
            CarrierType = "00";
        } else {
            //作业载体未设置
            CarrierType = "02";
        }

        //打印机
        var PrintRd = undefined;//$("#print").getseldata().PrRd;
        if ($("#prints").val() != undefined) {
            PrintRd = $("#prints").val()[0];
        }
        var PrintTRd = undefined;//$("#PrintT").getseldata().PtRd;
        if ($("#PrintTs").val() != undefined) {
            PrintTRd = $("#PrintTs").val()[0];
        }

        if (PrintTRd != undefined && PrintTRd != "" && PrintTRd != "null") {
            if (PrintRd == undefined || PrintRd == "" || PrintRd == "null") {
                toastr.warning("保存失败，请选择打印机之后在选择模板进行保存！");
                return
            }
        }
        var PrintCopy = $("#PrintCopy").val().trim();
        var OStation = {
            "Start": Start,
            "Up": Up,
            "Down": Down,
            "End": End,
            "CarrierUnBind": CarrierUnBind,
            "PrintLabel": PrintLabel,
            "CarrierType": CarrierType,
        };
        var Integrated = [];

        tableData.forEach(function (e) {
            var Integrated1 = {
                "DevRd": e.DevRd,
                "DevMapRd": e.DevMapRd,
                "TriggerType": e.TriggerType,
                "CmdText": e.zxzl,
                "CmdType": e.CmdType,
                "CmdVal": e.CmdVal,
            }
            Integrated.push(Integrated1);

        });

        //存放接口集成数据
        var StationAPI = [];
        var API = getRowData("list4");
        API.forEach(function (e) {
            StationAPI.push({"APIUrl": e.APIUrl, "TriggerType": e.TriggerType, "SysVal": e.SysVal})
        });

        if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
            newData = {
                "StationName": StationName,
                "AssDevInfo": AssDevInfo,
                "LineRd": LineRd,
                "SpecRd": SpecRd,
                "PrintRd": PrintRd == "null" ? "" : PrintRd,
                "PrintTRd": PrintTRd == "null" ? "" : PrintTRd,
                "PrintCopy": PrintCopy == "null" ? "" : PrintCopy,
                "ExecType": ExecType,
                "ExDetail": ExDetail,
                "OStation": OStation,
                "UDM": UDM,
                "Integrated": Integrated,
                "Alarm": Alarm,
                "StationAPI": StationAPI,
                "Status": Status,
                "Remark": Remark,
            };
            request({
                url: "/Station/SaveStationInfo",
                data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val('');
                currentPage = 0;
                treeID == null
                reasultFlag = false;
                loaddata();
            });
        } else if (treeID != null && treeID != "") {
            newData = {
                "StationRd": treeID,
                "StationName": StationName,
                "AssDevInfo": AssDevInfo,
                "LineRd": LineRd,
                "SpecRd": SpecRd,
                "PrintRd": PrintRd == "null" ? "" : PrintRd,
                "PrintTRd": PrintTRd == "null" ? "" : PrintTRd,
                "PrintCopy": PrintCopy == "null" ? "" : PrintCopy,
                "ExecType": ExecType,
                "ExDetail": ExDetail,
                "OStation": OStation,
                "UDM": UDM,
                "Integrated": Integrated,
                "Alarm": Alarm,
                "StationAPI": StationAPI,
                "Status": Status,
                "Remark": Remark,
            };
            request({
                url: "/Station/SaveStationInfo",
                data: {"ExecType": "02", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val('');
                treeID = null;
                currentPage = 0;
                condition = '';
                reasultFlag = false;
                loaddata();
            });
        }
    });
});
