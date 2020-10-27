/**
 * Created by liufuzhi on 2017/7/5.
 */
$(function () {

    //获取所有文件名称
    var FileGpNames = [];
    request({url: "/FileGroup/GetAllFileGInfo", data: {"ExecType": "00"}}, function (Body) {
        FileGpNames.push({"FileGrName": "", "FileGrRd": ""});
        jQuery(Body.Data).each(function () {
            var FileGpName = {};
            FileGpName["FileGrName"] = this["FileGpName"];
            FileGpName["FileGrRd"] = this["FileGRd"];

            FileGpNames.push(FileGpName);
        });
    });
    //获取所有设备组名称
    var DevGpNames = [];
    request({url: "/DeviceG/GetAllDevGInfo", data: {"ExecType": "00"}}, function (Body) {
        DevGpNames.push({"DevGpName": "", "DevGpRd": ""});
        jQuery(Body.Data).each(function () {
            var DevGpName = {};
            DevGpName["DevGpName"] = this["DevGpName"];
            DevGpName["DevGpRd"] = this["DevGRd"];

            DevGpNames.push(DevGpName);
        });
    });

    //获取数据采集名称
    var DcNames = [];
    request({url: "/DC/GetAllDcInfo", data: {"ExecType": "00"}}, function (Body) {
        DcNames.push({"DCName": "", "DCVerRd": ""});
        jQuery(Body.Data).each(function () {
            var DcName = {};
            DcName["DCName"] = this["DcName"];
            DcName["DCVerRd"] = this["DCVerRd"];

            DcNames.push(DcName);
        });
    });

    //处理表格
    var colNamesArr = [
        {"Caption": "SpecVerRd", "Name": "SpecVerRd", "CType": "text", "Hidden": true},
        {"Caption": "工序版本", "Name": "SpecName", "CType": "text", "Editable": false},
        {
            "Caption": "文件组", "Name": "FileGrInfo", "CType": "select", "Editable": true,
            "SelectPr": {
                "Data": FileGpNames,
                "DisplayName": "FileGrName",
                "DataValue": "FileGrRd"
            }
        },
        {
            "Caption": "设备组", "Name": "Device", "CType": "select", "Editable": true,
            "SelectPr": {
                "Data": DevGpNames,
                "DisplayName": "DevGpName",
                "DataValue": "DevGpRd"
            }
        },
        {
            "Caption": "数据采集", "Name": "DC", "CType": "select", "Editable": true,
            "SelectPr": {
                "Data": DcNames,
                "DisplayName": "DCName",
                "DataValue": "DCVerRd"
            }
        }
    ];

    //定义表单规则
    var rule = [
        {
            "ctlid": "MaCode",
            "param": "MaCode"
        },
        {
            "ctlid": "MaName",
            "param": "MaName"
        },
        {
            "ctlid": "MaDes",
            "param": "MaDes"
        },
        {
            "ctlid": "creatPeople",
            "param": "Creator"
        }, {
            "ctlid": "createTime",
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

    var treeID = null;
    //封装调用查询子节点信息
    var Getchilds = function (MaRd, WFVerRd, WFName) {

        var test = {
            "MaRd": MaRd,
            "WFVerRd": WFVerRd
        };
        var objData = {
            "ExecType": "00",
            "BusData": JSON.stringify(test),
        };

        /*------------------获取点击之后一个节点的数据------------------*/
        request({
                url: "/SpecFile/GetMWFInfo",
                data: objData
            },
            function (Body) {
                $("#hidden2").attr("q", Body.Data.MVerRd);
                fillform("specfileForm", rule, Body.Data);
                var data = Body.Data.SpecInfo;
                var config = {
                    tableId: "list4",
                    data: data,
                    colArr: colNamesArr,
                    width: 0.84,
                    height: 0.69
                };
                fullTable(config);
            });

        $("#wofrod").showData({
            id: WFVerRd,
            name: WFName,
            keyfield: "WFVerRd",
            fields: [
                {"name": "WFVerRd"},
                {"name": "WFName"}
            ]
        });
    };

    //点击父节点
    var onClicks = function (nodeinfo, handler) {
        $(".right").show();
        treeID = nodeinfo.nodeID;
        if (nodeinfo.isRoot) {
            $("#hidden2").attr("r", "00");

            request({
                url: "/Material/GetMVTreeInfo", data: {
                    "ExecType": "00",
                    "busData": "{\"MaRd\":" + treeID + "}"
                }
            }, function (Body) {
                if (Body.Data.SpecInfo == null) {
                    var config = {
                        tableId: "list4",
                        data: [],
                        colArr: colNamesArr,
                        width: 0.84,
                        height: 0.69
                    };
                    fullTable(config);
                }

                if (Body.MsgCode == "0x00000") {
                    $("#hidden2").attr("q", Body.Data[0].MaVerRd);
                    var rule = [{
                        id: "MaVerRd",
                        text: "Version",
                    }];

                    handler.ckAddChild(rule, Body.Data);

                    fillform("specfileForm", rule, Body.Data);

                    var objData = {
                        "ExecType": "00",
                        "BusData": JSON.stringify({"MVerRd": treeID})
                    };

                    request({url: "/Commom/GetCMWFInfo", data: objData}, function (Body) {
                        if (Body.MsgCode == "0x00000") {
                            var WFInfo = Body.Data.WFInfo;
                            if (WFInfo != undefined && WFInfo.length>0&& WFInfo[0].WFVerRd != undefined && WFInfo[0].WFName != undefined) {
                                //var WFInfo = [{"WFVerRd": "1", "WFName": "工艺流程1"}, {"WFVerRd": "2", "WFName": "工艺流程2"}];
                                $("#hidden3").attr("q", WFInfo[0].WFVerRd);
                                Getchilds(treeID, WFInfo[0].WFVerRd);
                                $("#wofrod").showData({
                                    id: WFInfo[0].WFVerRd,
                                    name: WFInfo[0].WFName,
                                    keyfield: "WFVerRd",
                                    fields: [
                                        {"name": "WFVerRd"},
                                        {"name": "WFName"}
                                    ]
                                });
                            }
                        }
                    });
                }
            });

            /*$.ajax({
                url: getBasePath() + "/Material/GetMVTreeInfo",
                type: 'POST',
                data: {
                    "ExecType": "00",
                    "busData": "{\"MaRd\":" + treeID + "}"
                },
                success: function (data) {
                    if (data.Body.Data.SpecInfo == null) {
                        var config = {
                            tableId: "list4",
                            data: [],
                            colArr: colNamesArr,
                            width:0.84,
                            height:0.69
                        };
                        fullTable(config);
                    }

                    if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                        $("#hidden2").attr("q", data.Body.Data[0].MaVerRd);
                        var rule = [{
                            id: "MaVerRd",
                            text: "Version",
                        }];

                        handler.ckAddChild(rule, data.Body.Data);

                        fillform("specfileForm", rule, data.Body.Data);
                        Getchilds(treeID);
                    }
                }
            });*/

        } else {

            $("#hidden2").attr("q", treeID);
            $("#hidden2").attr("r", "00");

            //点击子节点加载数据
            var objBusData = JSON.stringify({"MVerRd": nodeinfo.nodeID});
            var objData = {
                "ExecType": "01",
                "BusData": objBusData
            };

            /*------------------获取点击之后一个节点的数据------------------*/
            request({
                    url: "/SpecFile/GetMWFInfo",
                    data: objData
                },
                function (Body) {
                    fillform("specfileForm", rule, Body.Data);
                    $("#hidden2").attr("q", Body.Data.MVerRd);
                    var data = Body.Data.SpecInfo;

                    var config = {
                        tableId: "list4",
                        data: data,
                        colArr: colNamesArr,
                        width: 0.84,
                        height: 0.69
                    };
                    fullTable(config);
                });
        }
    };


    //加载客户信息下拉框
    var params3 = {
        "displaymode": "0",
        "title": "工艺数据",
        "binddata": {
            "keyfield": "WFVerRd",
            "fields": [
                {
                    "caption": "工艺ID",
                    "name": "WFVerRd"
                }, {
                    "caption": "工艺名称",
                    "name": "WFName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                $("#hidden3").attr("q", res.WFVerRd);
                var WFVerRd = res.WFVerRd;
                var treeID = $("#hidden2").attr("q");
                Getchilds(treeID, WFVerRd, res.WFName);
            },
            "onseardata": function (o) {
                var xldata = [];
                var objData = {
                    "ExecType": "00",
                    "BusData": JSON.stringify({"MVerRd": treeID})
                };
                request({url: "/Commom/GetCMWFInfo", data: objData}, function (Body) {
                    var datas = Body.Data.WFInfo;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WFVerRd": datas[i].WFVerRd,
                            "WFName": datas[i].WFName,
                        };
                        xldata.push(data);
                    }

                });
                //var WFInfo = [{"WFVerRd": "1", "WFName": "工艺流程1"}, {"WFVerRd": "2", "WFName": "工艺流程2"}];
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    };

    $("#wofrod").zc_select(params3);
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
    var config1 = [/*{
        "FieldName": "WFGd",
        "FieldOpt": "!=",
        "FieldVal": ""
    },
        */{
            "FieldName": "MaterialType",
            "FieldOpt": "in",
            "FieldVal": "(00,01)"
        }];
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
        currentPage = treeSearchs("/Material/GetAllMaInfo", "MaRd", "MaName", "MaterialName", condition, currentPage, config, config1);
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
            currentPage = treeSearchs("/Material/GetAllMaInfo", "MaRd", "MaName", "MaterialName", condition, currentPage, config, config1);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/Material/GetAllMaInfo", "MaRd", "MaName", "MaterialName", condition, currentPage, config, config1);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/Material/GetAllMaInfo", "MaRd", "MaName", "MaterialName", condition, currentPage, config, config1);
    });


    var InitData = {
        "FiledList": [
            {
                "FieldName": "MaterialType",
                "FieldOpt": "in",
                "FieldVal": "('00','01')"
            }, {
                "FieldName": "MaterialName",
                "FieldOpt": "Order BY",
            }

        ]
    }

    //只刷新树
    var loadtree = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        request({
                url: '/Material/GetAllMaInfo',
                data: {
                    "ExecType": "00",
                    "BusData": "{}",
                    "InitData": JSON.stringify(InitData)
                    , "PageInfo": JSON.stringify(pageInfo)
                }
            },
            function (Body) {
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                if (treeData.length <= 0) {
                    return false;
                }
                var len = treeData.length >= 20 ? 20 : treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].MaRd == undefined ? "" : treeData[i].MaRd,
                        name: treeData[i].MaName == undefined ? "" : treeData[i].MaName
                    }
                    treedataList.push(tree);
                }

                config.data.source = treedataList;
                $.JstreeEx.init(config);
            });
    };
    loadtree();


    /*---------顶端删除----------*/
    $(".cDel").click(function () {
        var MaVerRd = $("#hidden2").attr("q");
        var WFVerRd = $("#hidden3").attr("q");
        newData = {
            "MaVerRd": MaVerRd,
            "WFVerRd": WFVerRd
        };
        if (MaVerRd != null && MaVerRd != "" && MaVerRd != undefined) {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                            url: "/SpecFile/SaveSOPInfo",
                            data: {
                                "ExecType": "01",
                                "busData": JSON.stringify(newData)
                            }
                        },
                        function (Body) {
                            layer.closeAll("dialog");
                            toastr.success(Body.MsgDes);
                            $("#ExecType").val("");
                            MaVerRd = null;
                            condition = "";
                            loadtree();
                            $(".right").hide();
                        }
                    );
                }
            );
        } else
            toastr.warning("请选中左边一项之后再进行删除!");
    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {

        var s_tableData = getRowData("list4");

        if (s_tableData != "" && s_tableData != undefined) {
            //var filegrRd = true;
            for (var i = 0; i < s_tableData.length; i++) {
                delete s_tableData[i]["SpecName"];
                delete s_tableData[i]["FileGrName"];
                delete s_tableData[i]["DevGpName"];
                delete s_tableData[i]["DCName"];
            }
        } else {
            toastr.warning("没有数据，不能保存");
            return;
        }


        // 获取所有表单数据封装成json对象
        formData = transfer("specfileForm");

        var MaVerRd = $("#hidden2").attr("q");
        var WFVerRd = $("#hidden3").attr("q");

        if ($("#hidden2").attr("r") == "00" && MaVerRd.trim() != "") {

            newData = {
                "MaVerRd": MaVerRd,
                "WFVerRd": WFVerRd,
                "SpecInfo": s_tableData,
                "Remark": formData["remark"]
            };

            request({
                    url: "/SpecFile/SaveSOPInfo",
                    data: {
                        "ExecType": "02",
                        "busData": JSON.stringify(newData)
                    }
                },
                function (Body) {
                    condition = "";
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    loadtree();
                }
            );
        }
    });
});