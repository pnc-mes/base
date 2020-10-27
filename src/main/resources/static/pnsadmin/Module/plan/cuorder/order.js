$(function () {
    //增删改查树的局部刷新
    var onClicks = function (nodeinfo, handle) {
        $(".right").show();
        $(".cAdd").attr("ca", "");
        $("#WoCode").attr("readonly", true);
        $(".hiderow").show();
        //默认加载直接保存
        $("#hidden").attr("a", "00");
        //获取默认加载的id
        $("#hidden").attr("editid", nodeinfo.nodeID);
        //默认删除
        $("#remove").attr("a", nodeinfo.nodeID);
        var CoRd = $("#hidden").attr("editid");
        var test1 = {
            "CoRd": CoRd
        };
        $("#OrderCode").attr("readonly", true);

        //点击获取关联工单列表信息
        request({
            url: "/CO/GetAllCOBInfo",
            data: {
                "ExecType": "00",
                "busData": JSON.stringify(test1)
            }, async: false
        }, function (Body) {
            if (Body.MsgCode == "0x00000") {
                if (Body.Data == null || Body.Data.length <= 0) {
                    var config2 = {
                        tableId: "list4",
                        data: [],
                        colArr: colNamesArr,
                        width: 0.84,
                        height: 0.39
                    };
                    fullTable(config2);
                    return false;
                }


                var config2 = {
                    tableId: "list4",
                    data: Body.Data,
                    colArr: colNamesArr,
                    width: 0.84,
                    height: 0.39
                };
                fullTable(config2);
            }
        });

        //点击获取获取订单详情
        var test = {
            "CoRd": CoRd
        };
        request({
            url: "/CO/GetCOInfo",
            data: {"ExecType": "00", "busData": JSON.stringify(test)}
        }, function (Body) {
            if (Body.MsgCode == "0x00000") {
                $("#hidden1").attr("h", Body.Data.DSource);
                var DSource = $("#hidden1").attr("h");

                $("#OrderCode").val(Body.Data.OrderCode);
                $("#Num").val(Body.Data.Num);

                $("#OrderType").showData({
                    id: Body.Data.COTInfo.CTRd,
                    name: Body.Data.COTInfo.CTName,
                    keyfield: "CTRd",
                    fields: [
                        {"name": "CTRd"},
                        {"name": "CTName"}
                    ]
                });

                $("#comboSelect").showData({
                    id: Body.Data.MaInfo.MaVerRd,
                    name: Body.Data.MaInfo.MaCode + "-" + Body.Data.MaInfo.MaName,
                    keyfield: "MaVerRd",
                    fields: [
                        {"name": "MaVerRd"},
                        {"name": "MaName"}
                    ]
                });

                $("#Customer").showData({
                    id: Body.Data.CustomerInfo.CustomerRd,
                    name: Body.Data.CustomerInfo.CustomerName,
                    keyfield: "CusRd",
                    fields: [
                        {"name": "CusRd"},
                        {"name": "CusName"}
                    ]
                });

            }
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

    // 树的分页
    var _currentPage = 0;
    var _PageInfo = {
        "PageSize": 20,
        "PageIndex": _currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = _currentPage;
        _currentPage = 0;
        _currentPage = treeSearchs("/CO/GetAllCOInfo", "CoRd", "OrderCode", "OrderCode", condition, _currentPage, config);
        if (_currentPage < 0) {
            condition = "";
            _currentPage = temp;
        }
    });

    $(".input1").keydown(function (event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = _currentPage;
            _currentPage = 0;
            _currentPage = treeSearchs("/CO/GetAllCOInfo", "CoRd", "OrderCode", "OrderCode", condition, _currentPage, config);
            if (_currentPage < 0) {
                condition = "";
                _currentPage = temp;
            }
        }
    })

    $("#prev").on("click", function () {
        if (_currentPage > 0) {
            _currentPage--;
            _currentPage = treeSearchs("/CO/GetAllCOInfo", "CoRd", "OrderCode", "OrderCode", condition, _currentPage, config);
        }
    });
    $("#next").on("click", function () {
        _currentPage++;
        _currentPage = treeSearchs("/CO/GetAllCOInfo", "CoRd", "OrderCode", "OrderCode", condition, _currentPage, config);
    });

    $("#_right").hide();


    //判断是否工单打印批次
    var f = true; //true打印，false不打印

    request({
        url: "/Commom/GetCMGCInfo",
        data: {"ExecType": "00", "BusData": "{\"ModeName\": \"M4\"}"}
    }, function (Body) {
        var data = Body.Data;
        if (data != undefined && data.length > 0 && data[0].Value == "M4_00") {
            f = false;
        }
    });

    if (!f) {
        $("#printDiv").css("display", "");
    }

    var newTree = [];
    // 产品名称
    var currentPage = 0;
    var PageInfo = {
        "PageSize": 100,
        "PageIndex": currentPage,
    };

    var MaVerRd = "";

    //默认加载表头
    var colNamesArr = [
        {"Caption": "id", "Name": "WoRd", "Hidden": true},
        {"Caption": "工单号", "Name": "WoCode", "Editable": false, "Width": 150},
        {"Caption": "生产数量", "Name": "Num", "Editable": false, "Width": 100},
        {"Caption": "单位", "Name": "UnitName", "Editable": false, "Width": 70},
        {"Caption": "计划完成时间", "Name": "JFDate", "Editable": false, "Width": 180},
        {"Caption": "实际完成日期", "Name": "SFDate", "Editable": false, "Width": 180},
        {"Caption": "状态", "Name": "Status", "Editable": false, "Width": 40}
    ];
    var config2 = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        width: 0.84,
        height: 0.39
    };
    fullTable(config2);
    PageInfo.PageIndex = currentPage;

    //---------------------定义下拉控件-----------------
    //加载物料
    var params = {
        "displaymode": "1",
        "title": "产品名称",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "产品名称id",
                    "name": "MaVerRd"
                }, {
                    "caption": "物料代码",
                    "name": "MaCode"
                }, {
                    "caption": "产品名称",
                    "name": "MaName"
                }, {
                    "caption": "产品描述",
                    "name": "MaDes"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                MaVerRd = res.MaVerRd;
                var data = {
                    "MaVerRd": MaVerRd
                }
                request({
                    url: "/Material/GetMaInfo",
                    data: {"ExecType": "01", "busData": JSON.stringify(data)}
                }, function (data) {
                    $("#unit").attr("value", data.Data.UnitInfo.UnitRd);
                    $("#unit").text(data.Data.UnitInfo.UnitName);
                    $("#zanban").showData({
                        id: data.Data.TrayPackInfo.PackSpRd,
                        name: data.Data.TrayPackInfo.PackName,
                        keyfield: "MPRd_",
                        fields: [
                            {"name": "MPRd_"},
                            {"name": "MPName_"}
                        ]
                    });
                    $("#xiangbao").showData({
                        id: data.Data.BoxPackInfo.PackSpRd,
                        name: data.Data.BoxPackInfo.PackName,
                        keyfield: "MPRd",
                        fields: [
                            {"name": "MPRd"},
                            {"name": "MPName"}
                        ]
                    });
                });
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },
                        {
                            "FieldName": "MaterialType",
                            "FieldOpt": "in",
                            "FieldVal": "('00','01')"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "order by",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                Materialinfo(InitData, page, xldata);
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            },
            "onformatval": function (seldata) {
                return seldata.MaCode + "-" + seldata.MaName;
            }
        }
    };
    $("#comboSelect").zc_select(params);

    //加载订单类型下拉框
    var params2 = {
        "displaymode": "0",
        "title": "订单类型数据",
        "binddata": {
            "keyfield": "CTRd",
            "fields": [
                {
                    "caption": "工单类型id",
                    "name": "CTRd"
                }, {
                    "caption": "工单类型名称",
                    "name": "CTName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CoTName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        }, {
                            "FieldName": "CreateTime",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/CoType/GetAllCTInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CTRd": datas[i].CTRd,
                            "CTName": datas[i].CTName,
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
    $("#OrderType").zc_select(params2);

//加载客户信息下拉框
    var params3 = {
        "displaymode": "0",
        "title": "客户数据",
        "binddata": {
            "keyfield": "CusRd",
            "fields": [
                {
                    "caption": "客户id",
                    "name": "CusRd"
                }, {
                    "caption": "客户名称",
                    "name": "CusName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CustomerName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        }, {
                            "FieldName": "CreateTime",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/Customer/GetAllCusInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CusRd": datas[i].CusRd,
                            "CusName": datas[i].CusName,
                        };
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
    $("#Customer").zc_select(params3);

    //新增或者删除时刷新加载树
    var loadTree = function () {
        //加载树
        var treedataList = [];

        request({
            url: "/CO/GetAllCOInfo",
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(_PageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CreateTime DESC",
                            "FieldOpt": "Order BY"
                        }]
                })
            }, async: false
        }, function (Body) {
            if (Body.MsgCode == "0x00000") {

                var treeData = Body.Data;
                for (var i = 0; i < treeData.length; i++) {
                    var tree = {
                        id: treeData[i].CoRd == undefined ? "" : treeData[i].CoRd,
                        name: treeData[i].OrderCode == undefined ? "" : treeData[i].OrderCode
                    }
                    treedataList.push(tree);
                    newTree.push(tree)
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }
        });

    };
    loadTree();

    //保存更新保存
    $(".cSave").click(function () {
        var str = /^\d+(\.\d+)?$/;　//非负浮点数（正浮点数 + 0）

        var OrderCode = $("#OrderCode").val();
        var Num = $("#Num").val();
        if (Num == "") {
            toastr.warning("数量不能为空！");
            return false;
        }
        var flag = str.test(Num);
        if (flag == false) {
            toastr.warning("输入有误，数量为正整数！");
            return false;
        }

        var MaVerRd = $("#comboSelect").getseldata().MaVerRd;
        if (MaVerRd == "") {
            toastr.warning("产品名称不能为空!");
            return;
        }
        var CTRd = $("#OrderType").getseldata().CTRd;
        if (CTRd == "") {
            toastr.warning("订单类型不能为空");
            return;
        }

        var CustomerRd = $("#Customer").getseldata().CusRd;
        if (CustomerRd == "") {
            toastr.warning("客户信息不能为空");
            return;
        }

        //更新保存
        if ($("#hidden").attr("a") == "00") {
            var CoRd = $("#hidden").attr("editid");
            var newData1 = {
                "CoRd": CoRd,
                "OrderCode": OrderCode,
                "CTRd": CTRd,
                "Num": Num,
                "MaVerRd": MaVerRd,
                "OrderCode": OrderCode,
                //"Remark": Remark,
                "CustomerRd": CustomerRd

            };
            if (CoRd != null && CoRd != "" && Num != null && Num != "" && MaVerRd != null && MaVerRd != "" && CTRd != null && CTRd != "" && CustomerRd != null && CustomerRd != "") {
                request({
                    url: "/CO/SaveCOInfo",
                    data: {"ExecType": "02", "BusData": JSON.stringify(newData1)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    _currentPage = 0;
                    condition = "";
                    $("#hidden").attr("editid", CoRd);
                    loadTree()  //局部刷新
                });
            }
        }

        //新增
        if ($(".cAdd").attr("ca") == "00") {
            var newData = {
                "OrderCode": OrderCode,
                "CTRd": CTRd,
                "Num": Num,
                "MaVerRd": MaVerRd,
                "OrderCode": OrderCode,
                //"Remark": Remark,
                "CustomerRd": CustomerRd
            };
            if (Num != null && Num != "" && MaVerRd != null && MaVerRd != "" && CTRd != null && CTRd != "" && CustomerRd != null && CustomerRd != "") {
                request({
                    url: "/CO/SaveCOInfo",
                    data: {"ExecType": "00", "BusData": JSON.stringify(newData)}
                }, function (Body) {
                    if (Body.MsgCode == "0x00000") {
                        toastr.success("订单信息新增成功,工单号{" + Body.Data.orderCode + "}");
                        condition = "";
                        _currentPage = 0;
                        loadTree(); //局部刷新
                        $("#hidden").attr("a", "00");
                        $('.cAdd').attr("ca", "");
                        $("#hidden").attr("editid", Body.Data.ruid);
                        $(".right").show();
                        $(".cAdd").attr("ca", "");
                        $("#OrderCode").attr("readonly", true);
                        $(".hiderow").show();
                        var CoRd = $("#hidden").attr("editid");
                        var test1 = {
                            "CoRd": CoRd
                        };

                        request({
                            url: "/CO/GetAllCOBInfo", async: false,
                            data: {
                                "ExecType": "00",
                                "busData": JSON.stringify(test1)
                            }
                        }, function (Body) {
                            if (Body.MsgCode == "0x00000") {
                                if (Body.Data == null || Body.Data.length <= 0) {
                                    var config2 = {
                                        tableId: "list4",
                                        data: [],
                                        colArr: colNamesArr,
                                        width: 0.84,
                                        height: 0.39
                                    };
                                    fullTable(config2);
                                    return false;
                                }
                                var config2 = {
                                    tableId: "list4",
                                    data: Body.Data,
                                    colArr: colNamesArr,
                                    width: 0.84,
                                    height: 0.39
                                };
                                fullTable(config2);
                            }
                        });


                        var test = {
                            "CoRd": CoRd
                        };
                        //详情页面
                        request({
                            url: "/CO/GetCOInfo",
                            data: {
                                "ExecType": "00",
                                "busData": JSON.stringify(test)
                            }
                        }, function (Body) {
                            if (Body.MsgCode == "0x00000") {
                                $("#OrderCode").val(Body.Data.orderCode);
                            }
                        });
                    } else {
                        toastr.success(Body.MsgCode);
                    }
                });
            }
        }
    });

    //新增
    $(".cAdd").click(function () {
        $("#scxb").selectpicker('val', "");
        $("#OrderType").clearseldata("CTRd");
        $("#Customer").clearseldata("CusRd");
        $("#zanban").clearseldata("MPRd_");
        $("#xiangbao").clearseldata("MPRd");
        $("#comboSelect").clearseldata("MaVerRd");
        $("#jinjidu").clearseldata("UrcyRd");
        $("#creatPeople").val("");

        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $("#hidden").attr("a", "")
        $(".right").show();
        $(".hiderow").hide();
        //直接新增做的判断
        $(this).attr("ca", "00")

        $("#WoCode").val("");
        $("#OrderCode").attr("readonly", false);
        $("#comboSelect").prop("disabled", false);
        $("#Num").attr("readonly", false);
        $("#WoType").prop("disabled", false);
        $("#jinjidu").prop("disabled", false);
        $("#date01").attr("readonly", false);
        $("#date02").attr("readonly", false);
        $("#OrderCode").attr("readonly", false);
        $("#zanban").prop("disabled", false);
        $("#xiangbao").prop("disabled", false);
        $("#mater").val("")
        $("#Num").val("")
        $("#unit").text("")
        $("#OrderCode").val("")
        $("#FinishNum").val("")
        $("#UnCBatNum").val("")
        $("#Status").val("")
        $("#beizhu").val("")

        var aaaa = $("#hiddentime").val();
        var a11 = aaaa.split("/");
        var a110 = a11[0] + "/" + a11[1] + "/" + a11[2];
        $("#date01").val(a110);
        $("#date02").val(a110);


        var config2 = {
            tableId: "list4",
            data: [],
            colArr: colNamesArr,
            width: 0.84,
            height: 0.39
        };
        fullTable(config2);
    });

    //删除
    $("#remove").click(function () {
        var CoRd = $(this).attr("a");
        var data = {
            "CoRd": CoRd
        }
        if (CoRd == null || CoRd == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            var DSource = $("#hidden1").attr("h");
            if (DSource == "00") {
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                layer.closeAll("dialog");
                request({
                    url: "/CO/SaveCOInfo",
                    data: {"ExecType": "01", "BusData": JSON.stringify(data)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    _currentPage = 0;
                    condition = "";
                    loadTree();//局部刷新
                    $("#remove").attr("a", "");
                    $(".right").hide();
                });
            })
        }
    });

    //取消
    $("#cancel").click(function () {
        saveWo("04");
    });

    //冻结
    $("#freeze").click(function () {
        saveWo("05");
    });

    //解冻
    $("#thaw").click(function () {
        saveWo("06");
    });

    //终止
    $("#stop").click(function () {
        saveWo("07");
    });

    //开始
    $("#start").click(function () {
        saveWo("08");
    });

    //关闭
    $("#close").click(function () {
        saveWo("09");
    });

});