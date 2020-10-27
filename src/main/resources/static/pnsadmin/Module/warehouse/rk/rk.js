$(function () {
    //默认加载上
    var colNamesArr1 = [
        {"Caption": "id", "Name": "RkRd", "IsKey": true, "Hidden": true},
        {"Caption": "入库单号", "Name": "RkCode", "Editable": false, "Hidden": true},
        {"Caption": "执行状态", "Name": "ExStatus", "Editable": false,Width:80},
        {"Caption": "单号", "Name": "AssCode", "Editable": false,Width:200},
        {"Caption": "执行人", "Name": "Execor", "Editable": false,Width:80},
        {"Caption": "执行时间", "Name": "ExecTime", "Editable": false,Width:180},
        {"Caption": "取消人", "Name": "Cancelor", "Editable": false,Width:80},
        {"Caption": "取消时间", "Name": "CancelTime", "Editable": false,Width:180},
        {"Caption": "完成人", "Name": "Finishor", "Editable": false,Width:80},
        {"Caption": "完成时间", "Name": "FinishTime", "Editable": false,Width:180}
    ];

    var config1 = {
        tableId: "list1",
        data: [],
        colArr: colNamesArr1,
        multiselect: true,
        width: 0.85
    };
    fullTable(config1);

    //默认加载上下左边的
    var colNamesArr2 = [
        {"Caption": "id", "Name": "RkDtlRd", "Hidden": true},
        {"Caption": "来源", "Name": "AssSource", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:80},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false,Width:150},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false,Width:150},
        {"Caption": "数量", "Name": "Num", "Editable": false,Width:50},
        {"Caption": "已入库数量", "Name": "ScanNum", "Editable": false,Width:50},
        {"Caption": "未入库数量", "Name": "UnScanNum", "Editable": false,Width:50},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:30}
    ];
    var config2 = {
        tableId: "list2",
        data: [],
        colArr: colNamesArr2,
        multiselect: true,
        width: 0.85
    };
    fullTable(config2);

    //默认加载上下右边的
    var colNamesArr3 = [
        {"Caption": "id", "Name": "RkDtlRd", "Hidden": true},
        {"Caption": "条码", "Name": "BarCode", "Editable": false},
        {"Caption": "数量", "Name": "Num", "Editable": false,Width:50},
        {"Caption": "扫描时间", "Name": "ScannerTime", "Editable": false},
        {"Caption": "生产日期", "Name": "ProductDate", "Editable": false},
        {"Caption": "到期日期", "Name": "ExpireDate", "Editable": false},
        {"Caption": "备注", "Name": "Remark", "Editable": false}
    ];
    var config3 = {
        tableId: "list3",
        data: [],
        colArr: colNamesArr3,
        multiselect: true,
        width: 0.85
    };
    fullTable(config3);
    var RkRd = "";
    //树的点击事件
    var onClicks = function (nodeinfo, handler) {

        if (!nodeinfo.hasChildren) {
            $("#formContent").show();
            if ("原材料入库" == nodeinfo.nodeText) {
                var str = "01";
            }
            if ("生产入库" == nodeinfo.nodeText) {
                var str = "00";
            }
            if ("生产退料" == nodeinfo.nodeText) {
                var str = "02";
            }
            $("#list1").html("");
            //入库单列表信息 根据Code
            var rkTCode = {
                "RkTCode": str
            }
            var objData = {
                "ExecType": "00",
                "BusData": JSON.stringify(rkTCode)
            };
            var config2 = {
                tableId: "list2",
                data: [],
                colArr: colNamesArr2,
                multiselect: true,
                width: 0.85
            };
            fullTable(config2);
            var config3 = {
                tableId: "list3",
                data: [],
                colArr: colNamesArr3,
                multiselect: true,
                width: 0.85
            };
            fullTable(config3);

            request({url:"/Rk/GetAllRKInfo", async: true,//设为同步请求*/
                data: objData},function(Body){
                if (Body.Data.length == "" || Body.Data == null || Body.Data.length == 0) {
                    var config1 = {
                        tableId: "list1",
                        data: [],
                        colArr: colNamesArr1,
                        multiselect: true,
                        width: 0.85
                    };
                    fullTable(config1);
                    var config2 = {
                        tableId: "list2",
                        data: [],
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.85
                    };
                    fullTable(config2);
                    var config3 = {
                        tableId: "list3",
                        data: [],
                        colArr: colNamesArr3,
                        multiselect: true,
                        width: 0.85
                    };
                    fullTable(config3);
                    return false;
                }
                if (Body.MsgCode == "0x00000") {
                    var config1 = {
                        tableId: "list1",
                        data: Body.Data,
                        colArr: colNamesArr1,
                        multiselect: true,
                        width: 0.85,
                        event: {
                            onrowselect: function (rowdata) {
                                for (var i in rowdata) {
                                    RkRd = rowdata[i].RkRd;
                                    //点击的时候赋值，执行删除赋值
                                    $(".cDel").attr("a", RkRd);
                                    $(".cCancel").attr("a", RkRd);
                                    $(".cSuccess").attr("a", RkRd);
                                    var data = {
                                        "RkRd": RkRd
                                    }
                                    request({url:"/Rk/GetRKDlInfo",async: false,//设为同步请求
                                        data: {
                                            "ExecType": "00",
                                            "BusData": JSON.stringify(data)
                                        }},function(Body){
                                        if (Body.Data.length == "" || Body.Data == null || Body.Data.length == 0) {
                                            var config2 = {
                                                tableId: "list2",
                                                data: [],
                                                colArr: colNamesArr2,
                                                multiselect: true,
                                                width: 0.85
                                            };
                                            fullTable(config2);
                                            var config3 = {
                                                tableId: "list3",
                                                data: [],
                                                colArr: colNamesArr3,
                                                multiselect: true,
                                                width: 0.85
                                            };
                                            fullTable(config3);
                                            return false;
                                        }

                                        if (Body.MsgCode == "0x00000") {
                                            var config2 = {
                                                tableId: "list2",
                                                data: Body.Data,
                                                colArr: colNamesArr2,
                                                multiselect: true,
                                                width: 0.85,
                                                event: {
                                                    onrowselect: function (rowdatas) {

                                                        for (var j in rowdatas) {
                                                            var RkDtlRd = rowdatas[j].RkDtlRd;
                                                            var AssSource = rowdatas[j].AssSource;
                                                            var RkDtlRd1 = {
                                                                "RkDtlRd": RkDtlRd,
                                                                "AssSource": AssSource
                                                            }

                                                            request({url:"/Rk/GetRKBarInfo", async: false,//设为同步请求
                                                                data: {
                                                                    "ExecType": "00",
                                                                    "BusData": JSON.stringify(RkDtlRd1)
                                                                }},function(Body){
                                                                if (Body.MsgCode == "0x00000") {
                                                                    var config3 = {
                                                                        tableId: "list3",
                                                                        data: Body.Data,
                                                                        colArr: colNamesArr3,
                                                                        multiselect: true,
                                                                        width: 0.85
                                                                    };
                                                                    fullTable(config3);
                                                                }
                                                                if (Body.Data.length == "" || Body.Data == null || Body.Data.length == 0 || Body.Data.length == 0) {
                                                                    var config3 = {
                                                                        tableId: "list3",
                                                                        data: [],
                                                                        colArr: colNamesArr3,
                                                                        multiselect: true,
                                                                        width: 0.85
                                                                    };
                                                                    fullTable(config3);
                                                                }

                                                            });

                                                          /*  $.ajax({
                                                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                type: "POST",
                                                                async: false,//设为同步请求
                                                                data: {
                                                                    "ExecType": "00",
                                                                    "BusData": JSON.stringify(RkDtlRd1)
                                                                },
                                                                success: function (res) {
                                                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                        var config3 = {
                                                                            tableId: "list3",
                                                                            data: res.Body.Data,
                                                                            colArr: colNamesArr3,
                                                                            multiselect: true,
                                                                            width: 0.85
                                                                        };
                                                                        fullTable(config3);
                                                                    }
                                                                    if (res.Body.Data.length == "" || res.Body.Data == null || res.Body.Data.length == 0 || res.Body.Data.length == 0) {
                                                                        var config3 = {
                                                                            tableId: "list3",
                                                                            data: [],
                                                                            colArr: colNamesArr3,
                                                                            multiselect: true,
                                                                            width: 0.85
                                                                        };
                                                                        fullTable(config3);
                                                                    }

                                                                }
                                                            });*/

                                                        }
                                                    }
                                                }
                                            };
                                            fullTable(config2);
                                        }

                                        var RkDtlRd = Body.Data[0].RkDtlRd;
                                        var AssSource = Body.Data[0].AssSource;
                                        var RkDtlRd1 = {
                                            "RkDtlRd": RkDtlRd,
                                            "AssSource": AssSource
                                        }

                                        request({url:"/Rk/GetRKBarInfo", async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(RkDtlRd1)
                                            }},function(Body){
                                            if (Body.MsgCode == "0x00000") {
                                                var config3 = {
                                                    tableId: "list3",
                                                    data: Body.Data,
                                                    colArr: colNamesArr3,
                                                    multiselect: true,
                                                    width: 0.85
                                                };
                                                fullTable(config3);
                                            }
                                        });


                                        /*$.ajax({
                                            url: getBasePath() + "/Rk/GetRKBarInfo",
                                            type: "POST",
                                            async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(RkDtlRd1)
                                            },
                                            success: function (res) {
                                                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                    var config3 = {
                                                        tableId: "list3",
                                                        data: res.Body.Data,
                                                        colArr: colNamesArr3,
                                                        multiselect: true,
                                                        width: 0.85
                                                    };
                                                    fullTable(config3);
                                                }
                                            }
                                        });*/
                                    });


                                /*    $.ajax({
                                        url: getBasePath() + "/Rk/GetRKDlInfo",
                                        type: "POST",
                                        async: false,//设为同步请求
                                        data: {
                                            "ExecType": "00",
                                            "BusData": JSON.stringify(data)
                                        },
                                        success: function (res) {

                                            if (res.Body.Data.length == "" || res.Body.Data == null || res.Body.Data.length == 0) {
                                                var config2 = {
                                                    tableId: "list2",
                                                    data: [],
                                                    colArr: colNamesArr2,
                                                    multiselect: true,
                                                    width: 0.85
                                                };
                                                fullTable(config2);
                                                var config3 = {
                                                    tableId: "list3",
                                                    data: [],
                                                    colArr: colNamesArr3,
                                                    multiselect: true,
                                                    width: 0.85
                                                };
                                                fullTable(config3);
                                                return false;
                                            }

                                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                var config2 = {
                                                    tableId: "list2",
                                                    data: res.Body.Data,
                                                    colArr: colNamesArr2,
                                                    multiselect: true,
                                                    width: 0.85,
                                                    event: {
                                                        onrowselect: function (rowdatas) {

                                                            for (var j in rowdatas) {
                                                                var RkDtlRd = rowdatas[j].RkDtlRd;
                                                                var AssSource = rowdatas[j].AssSource;
                                                                var RkDtlRd1 = {
                                                                    "RkDtlRd": RkDtlRd,
                                                                    "AssSource": AssSource
                                                                }

                                                                $.ajax({
                                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                    type: "POST",
                                                                    async: false,//设为同步请求
                                                                    data: {
                                                                        "ExecType": "00",
                                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                                    },
                                                                    success: function (res) {
                                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                            var config3 = {
                                                                                tableId: "list3",
                                                                                data: res.Body.Data,
                                                                                colArr: colNamesArr3,
                                                                                multiselect: true,
                                                                                width: 0.85
                                                                            };
                                                                            fullTable(config3);
                                                                        }
                                                                        if (res.Body.Data.length == "" || res.Body.Data == null || res.Body.Data.length == 0 || res.Body.Data.length == 0) {
                                                                            var config3 = {
                                                                                tableId: "list3",
                                                                                data: [],
                                                                                colArr: colNamesArr3,
                                                                                multiselect: true,
                                                                                width: 0.85
                                                                            };
                                                                            fullTable(config3);
                                                                        }

                                                                    }
                                                                });

                                                            }
                                                        }
                                                    }
                                                };
                                                fullTable(config2);
                                            }

                                            var RkDtlRd = res.Body.Data[0].RkDtlRd;
                                            var AssSource = res.Body.Data[0].AssSource;
                                            var RkDtlRd1 = {
                                                "RkDtlRd": RkDtlRd,
                                                "AssSource": AssSource
                                            }
                                            $.ajax({
                                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                                type: "POST",
                                                async: false,//设为同步请求
                                                data: {
                                                    "ExecType": "00",
                                                    "BusData": JSON.stringify(RkDtlRd1)
                                                },
                                                success: function (res) {
                                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                        var config3 = {
                                                            tableId: "list3",
                                                            data: res.Body.Data,
                                                            colArr: colNamesArr3,
                                                            multiselect: true,
                                                            width: 0.85
                                                        };
                                                        fullTable(config3);
                                                    }
                                                }
                                            });
                                        }
                                    });*/
                                }
                            }
                        }
                    };
                    fullTable(config1);

                }

                RkRd = Body.Data[0].RkRd;
                //默认删除
                $(".cDel").attr("a", RkRd);
                $(".cCancel").attr("a", RkRd);
                $(".cSuccess").attr("a", RkRd);
                var data1 = {
                    "RkRd": RkRd
                }
                request({url:"/Rk/GetRKDlInfo", async: false,//设为同步请求
                    data: {
                        "ExecType": "00",
                        "BusData": JSON.stringify(data1)
                    }},function(Body){

                    if (Body.MsgCode == "0x00000") {
                        var config2 = {
                            tableId: "list2",
                            data: Body.Data,
                            colArr: colNamesArr2,
                            multiselect: true,
                            width: 0.85,
                            event: {
                                onrowselect: function (rowdatas) {
                                    for (var j in rowdatas) {
                                        var RkDtlRd = rowdatas[j].RkDtlRd;
                                        var AssSource = rowdatas[j].AssSource;
                                        var RkDtlRd1 = {
                                            "RkDtlRd": RkDtlRd,
                                            "AssSource": AssSource
                                        }

                                        request({url:"/Rk/GetRKBarInfo", async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(RkDtlRd1)
                                            }},function(Body){
                                            if (Body.MsgCode == "0x00000") {
                                                var config3 = {
                                                    tableId: "list3",
                                                    data: Body.Data,
                                                    colArr: colNamesArr3,
                                                    multiselect: true,
                                                    width: 0.85,
                                                };
                                                fullTable(config3);
                                            }
                                        });
                                      /*  $.ajax({
                                            url: getBasePath() + "/Rk/GetRKBarInfo",
                                            type: "POST",
                                            async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(RkDtlRd1)
                                            },
                                            success: function (res) {
                                                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                    var config3 = {
                                                        tableId: "list3",
                                                        data: res.Body.Data,
                                                        colArr: colNamesArr3,
                                                        multiselect: true,
                                                        width: 0.85,
                                                    };
                                                    fullTable(config3);
                                                }
                                            }
                                        });*/

                                    }
                                }
                            }
                        };

                        if (Body.Data.length <= 0) {
                            return false;
                        }
                        var RkDtlRd = Body.Data[0].RkDtlRd;
                        var AssSource = Body.Data[0].AssSource;
                        var RkDtlRd1 = {
                            "RkDtlRd": RkDtlRd,
                            "AssSource": AssSource
                        }


                        request({url:"/Rk/GetRKBarInfo",async: false,//设为同步请求
                            data: {
                                "ExecType": "00",
                                "BusData": JSON.stringify(RkDtlRd1)
                            }},function(Body){
                            if (Body.MsgCode == "0x00000") {
                                var config3 = {
                                    tableId: "list3",
                                    data: Body.Data,
                                    colArr: colNamesArr3,
                                    multiselect: true,
                                    width: 0.85
                                };
                                fullTable(config3);
                            }

                        });


                   /*     $.ajax({
                            url: getBasePath() + "/Rk/GetRKBarInfo",
                            type: "POST",
                            async: false,//设为同步请求
                            data: {
                                "ExecType": "00",
                                "BusData": JSON.stringify(RkDtlRd1)
                            },
                            success: function (res) {
                                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                    var config3 = {
                                        tableId: "list3",
                                        data: res.Body.Data,
                                        colArr: colNamesArr3,
                                        multiselect: true,
                                        width: 0.85
                                    };
                                    fullTable(config3);
                                }
                            }
                        });*/
                        fullTable(config2);
                    }
                });
              /*  $.ajax({
                    url: getBasePath() + "/Rk/GetRKDlInfo",
                    type: "POST",
                    async: false,//设为同步请求
                    data: {
                        "ExecType": "00",
                        "BusData": JSON.stringify(data1)
                    },
                    success: function (res1) {
                        if (res1.Status == "00" && res1.Body.MsgCode == "0x00000") {
                            var config2 = {
                                tableId: "list2",
                                data: res1.Body.Data,
                                colArr: colNamesArr2,
                                multiselect: true,
                                width: 0.85,
                                event: {
                                    onrowselect: function (rowdatas) {
                                        for (var j in rowdatas) {
                                            var RkDtlRd = rowdatas[j].RkDtlRd;
                                            var AssSource = rowdatas[j].AssSource;
                                            var RkDtlRd1 = {
                                                "RkDtlRd": RkDtlRd,
                                                "AssSource": AssSource
                                            }
                                            $.ajax({
                                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                                type: "POST",
                                                async: false,//设为同步请求
                                                data: {
                                                    "ExecType": "00",
                                                    "BusData": JSON.stringify(RkDtlRd1)
                                                },
                                                success: function (res) {
                                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                        var config3 = {
                                                            tableId: "list3",
                                                            data: res.Body.Data,
                                                            colArr: colNamesArr3,
                                                            multiselect: true,
                                                            width: 0.85,
                                                        };
                                                        fullTable(config3);
                                                    }
                                                }
                                            });

                                        }
                                    }
                                }
                            };

                            if (res1.Body.Data.length <= 0) {
                                return false;
                            }
                            var RkDtlRd = res1.Body.Data[0].RkDtlRd;
                            var AssSource = res1.Body.Data[0].AssSource;
                            var RkDtlRd1 = {
                                "RkDtlRd": RkDtlRd,
                                "AssSource": AssSource
                            }
                            $.ajax({
                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                type: "POST",
                                async: false,//设为同步请求
                                data: {
                                    "ExecType": "00",
                                    "BusData": JSON.stringify(RkDtlRd1)
                                },
                                success: function (res) {
                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                        var config3 = {
                                            tableId: "list3",
                                            data: res.Body.Data,
                                            colArr: colNamesArr3,
                                            multiselect: true,
                                            width: 0.85
                                        };
                                        fullTable(config3);
                                    }
                                }
                            });
                            fullTable(config2);
                        }
                    }
                });*/
                RkRd = "";

            });


            /*$.ajax({
                id: "list1",
                url: getBasePath() + "/Rk/GetAllRKInfo",
                type: "POST",
                async: true,//设为同步请求*!/
                data: objData,
                success: function (res) {
                    if (res.Body.Data.length == "" || res.Body.Data == null || res.Body.Data.length == 0) {
                        var config1 = {
                            tableId: "list1",
                            data: [],
                            colArr: colNamesArr1,
                            multiselect: true,
                            width: 0.85
                        };
                        fullTable(config1);
                        var config2 = {
                            tableId: "list2",
                            data: [],
                            colArr: colNamesArr2,
                            multiselect: true,
                            width: 0.85
                        };
                        fullTable(config2);
                        var config3 = {
                            tableId: "list3",
                            data: [],
                            colArr: colNamesArr3,
                            multiselect: true,
                            width: 0.85
                        };
                        fullTable(config3);
                        return false;
                    }
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        var config1 = {
                            tableId: "list1",
                            data: res.Body.Data,
                            colArr: colNamesArr1,
                            multiselect: true,
                            width: 0.85,
                            event: {
                                onrowselect: function (rowdata) {
                                    for (var i in rowdata) {
                                        RkRd = rowdata[i].RkRd;
                                        //点击的时候赋值，执行删除赋值
                                        $(".cDel").attr("a", RkRd);
                                        $(".cCancel").attr("a", RkRd);
                                        $(".cSuccess").attr("a", RkRd);
                                        var data = {
                                            "RkRd": RkRd
                                        }
                                        $.ajax({
                                            url: getBasePath() + "/Rk/GetRKDlInfo",
                                            type: "POST",
                                            async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(data)
                                            },
                                            success: function (res) {

                                                if (res.Body.Data.length == "" || res.Body.Data == null || res.Body.Data.length == 0) {
                                                    var config2 = {
                                                        tableId: "list2",
                                                        data: [],
                                                        colArr: colNamesArr2,
                                                        multiselect: true,
                                                        width: 0.85
                                                    };
                                                    fullTable(config2);
                                                    var config3 = {
                                                        tableId: "list3",
                                                        data: [],
                                                        colArr: colNamesArr3,
                                                        multiselect: true,
                                                        width: 0.85
                                                    };
                                                    fullTable(config3);
                                                    return false;
                                                }

                                                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                    var config2 = {
                                                        tableId: "list2",
                                                        data: res.Body.Data,
                                                        colArr: colNamesArr2,
                                                        multiselect: true,
                                                        width: 0.85,
                                                        event: {
                                                            onrowselect: function (rowdatas) {

                                                                for (var j in rowdatas) {
                                                                    var RkDtlRd = rowdatas[j].RkDtlRd;
                                                                    var AssSource = rowdatas[j].AssSource;
                                                                    var RkDtlRd1 = {
                                                                        "RkDtlRd": RkDtlRd,
                                                                        "AssSource": AssSource
                                                                    }

                                                                    $.ajax({
                                                                        url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                        type: "POST",
                                                                        async: false,//设为同步请求
                                                                        data: {
                                                                            "ExecType": "00",
                                                                            "BusData": JSON.stringify(RkDtlRd1)
                                                                        },
                                                                        success: function (res) {
                                                                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                                var config3 = {
                                                                                    tableId: "list3",
                                                                                    data: res.Body.Data,
                                                                                    colArr: colNamesArr3,
                                                                                    multiselect: true,
                                                                                    width: 0.85
                                                                                };
                                                                                fullTable(config3);
                                                                            }
                                                                            if (res.Body.Data.length == "" || res.Body.Data == null || res.Body.Data.length == 0 || res.Body.Data.length == 0) {
                                                                                var config3 = {
                                                                                    tableId: "list3",
                                                                                    data: [],
                                                                                    colArr: colNamesArr3,
                                                                                    multiselect: true,
                                                                                    width: 0.85
                                                                                };
                                                                                fullTable(config3);
                                                                            }

                                                                        }
                                                                    });

                                                                }
                                                            }
                                                        }
                                                    };
                                                    fullTable(config2);
                                                }

                                                var RkDtlRd = res.Body.Data[0].RkDtlRd;
                                                var AssSource = res.Body.Data[0].AssSource;
                                                var RkDtlRd1 = {
                                                    "RkDtlRd": RkDtlRd,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            }
                        };
                        fullTable(config1);

                    }

                    RkRd = res.Body.Data[0].RkRd;
                    //默认删除
                    $(".cDel").attr("a", RkRd);
                    $(".cCancel").attr("a", RkRd);
                    $(".cSuccess").attr("a", RkRd);
                    var data1 = {
                        "RkRd": RkRd
                    }

                    $.ajax({
                        url: getBasePath() + "/Rk/GetRKDlInfo",
                        type: "POST",
                        async: false,//设为同步请求
                        data: {
                            "ExecType": "00",
                            "BusData": JSON.stringify(data1)
                        },
                        success: function (res1) {
                            if (res1.Status == "00" && res1.Body.MsgCode == "0x00000") {
                                var config2 = {
                                    tableId: "list2",
                                    data: res1.Body.Data,
                                    colArr: colNamesArr2,
                                    multiselect: true,
                                    width: 0.85,
                                    event: {
                                        onrowselect: function (rowdatas) {
                                            for (var j in rowdatas) {
                                                var RkDtlRd = rowdatas[j].RkDtlRd;
                                                var AssSource = rowdatas[j].AssSource;
                                                var RkDtlRd1 = {
                                                    "RkDtlRd": RkDtlRd,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85,
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });

                                            }
                                        }
                                    }
                                };

                                if (res1.Body.Data.length <= 0) {
                                    return false;
                                }
                                var RkDtlRd = res1.Body.Data[0].RkDtlRd;
                                var AssSource = res1.Body.Data[0].AssSource;
                                var RkDtlRd1 = {
                                    "RkDtlRd": RkDtlRd,
                                    "AssSource": AssSource
                                }
                                $.ajax({
                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                    type: "POST",
                                    async: false,//设为同步请求
                                    data: {
                                        "ExecType": "00",
                                        "BusData": JSON.stringify(RkDtlRd1)
                                    },
                                    success: function (res) {
                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                            var config3 = {
                                                tableId: "list3",
                                                data: res.Body.Data,
                                                colArr: colNamesArr3,
                                                multiselect: true,
                                                width: 0.85
                                            };
                                            fullTable(config3);
                                        }
                                    }
                                });
                                fullTable(config2);
                            }
                        }
                    });
                    RkRd = "";
                }
            });*/
        }
    }

    var newTree = [];
    var treedataList = [];
    var treeData = [];
    var loadTree = function () {

        request({url:"/Rk/GetRKTInfo",  async: false,
            data: {
                "ExecType": "00"
            }},function(Body){
            if (Body.MsgCode == "0x00000") {
                treeData = Body.Data;
                for (var i = 0; i < Body.Data.length; i++) {
                    var tree = {
                        id: treeData[i].PkTCode == undefined ? "" : treeData[i].PkTCode,
                        name: treeData[i].PkTName == undefined ? "" : treeData[i].PkTName
                    }
                    newTree.push(tree);
                    treedataList.push(tree);
                }
                var config = {
                    id: "jstree_demo1",
                    data: {
                        source: treedataList,
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
                $.JstreeEx.init(config);
            }
        });

      /*  $.ajax({
            url: getBasePath() + "/Rk/GetRKTInfo",
            type: "POST",
            async: false,
            data: {
                "ExecType": "00"
            },
            success: function (data) {
                if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                    treeData = data.Body.Data;
                    for (var i = 0; i < data.Body.Data.length; i++) {
                        var tree = {
                            id: treeData[i].PkTCode == undefined ? "" : treeData[i].PkTCode,
                            name: treeData[i].PkTName == undefined ? "" : treeData[i].PkTName
                        }
                        newTree.push(tree);
                        treedataList.push(tree);
                    }
                    var config = {
                        id: "jstree_demo1",
                        data: {
                            source: treedataList,
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
                    $.JstreeEx.init(config);
                }
            }
        })*/
    }
    loadTree();
    //加载入库单类型
    request({url:"/Rk/GetRKTInfo",  data: {
            "ExecType": "00"
        }},function(Body){

            if (Body.MsgCode == "0x00000") {
                var treedataList = [];
                for (var i = 0; i < Body.Data.length; i++) {
                    var tree = {
                        id: Body.Data[i].PkTCode == undefined ? "" : Body.Data[i].PkTCode,
                        name: Body.Data[i].PkTName == undefined ? "" : Body.Data[i].PkTName
                    }
                    treedataList.push(tree);
                }
                var config = {
                    id: "jstree_demo1",
                    data: {
                        source: treedataList,
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
                $.JstreeEx.init(config);
            }

            if (Body.Data.length <= 0) {
                $("#formContent").hide();
                return false;
            }
            $("#formContent").show();

            //加载默认信息
            if ("原材料入库" == Body.Data[0].PkTName) {
                var str = "01";
            }
            if ("生产入库" == Body.Data[0].PkTName) {
                var str = "00";
            }
            if ("生产退料" == Body.Data[0].PkTName) {
                var str = "02";
            }
            //入库单列表信息 根据Code
            var rkTCode = {
                "RkTCode": str
            }
            $("#list1").html("");
            var config2 = {
                tableId: "list2",
                data: [],
                colArr: colNamesArr2,
                multiselect: true,
                width: 0.85
            };
            fullTable(config2);
            var config3 = {
                tableId: "list3",
                data: [],
                colArr: colNamesArr3,
                multiselect: true,
                width: 0.85
            };
            fullTable(config3);
        request({url:"/Rk/GetAllRKInfo", id: "list1", data: {
                "ExecType": "00",
                "BusData": JSON.stringify(rkTCode)
            }},function(Body){

                if (Body.Data == null && Body.MsgCode == "MES00xxx") {
                    return false;
                }
                if (Body.MsgCode == "0x00000") {
                    var config1 = {
                        tableId: "list1",
                        data: Body.Data,
                        colArr: colNamesArr1,
                        multiselect: true,
                        width: 0.85,
                        event: {
                            onrowselect: function (rowdata) {
                                for (var i in rowdata) {
                                    RkRd = rowdata[i].RkRd;
                                    $(".cDel").attr("a", RkRd);
                                    $(".cCancel").attr("a", RkRd);
                                    $(".cSuccess").attr("a", RkRd);
                                    var data = {
                                        "RkRd": RkRd
                                    }

                                    request({url:"/Rk/GetRKDlInfo",  async: false,//设为同步请求
                                        data: {
                                            "ExecType": "00",
                                            "BusData": JSON.stringify(data)
                                        }},function(Body){
                                            if (Body.MsgCode == "0x00000") {
                                                var config2 = {
                                                    tableId: "list2",
                                                    data: Body.Data,
                                                    colArr: colNamesArr2,
                                                    multiselect: true,
                                                    width: 0.85,
                                                    event: {
                                                        onrowselect: function (rowdatas) {
                                                            for (var j in rowdatas) {
                                                                var RkDtlRd = rowdatas[j].RkDtlRd;
                                                                var AssSource = rowdatas[j].AssSource;
                                                                var RkDtlRd1 = {
                                                                    "RkDtlRd": RkDtlRd,
                                                                    "AssSource": AssSource
                                                                }
                                                                request({url:"/Rk/GetRKBarInfo", async: false,//设为同步请求
                                                                    data: {
                                                                        "ExecType": "00",
                                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                                    }},function(Body){
                                                                    if (Body.MsgCode == "0x00000") {
                                                                        var config3 = {
                                                                            tableId: "list3",
                                                                            data: Body.Data,
                                                                            colArr: colNamesArr3,
                                                                            multiselect: true,
                                                                            width: 0.85
                                                                        };
                                                                        fullTable(config3);
                                                                    }
                                                                });



                                                               /* $.ajax({
                                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                    type: "POST",
                                                                    async: false,//设为同步请求
                                                                    data: {
                                                                        "ExecType": "00",
                                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                                    },
                                                                    success: function (res) {
                                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                            var config3 = {
                                                                                tableId: "list3",
                                                                                data: res.Body.Data,
                                                                                colArr: colNamesArr3,
                                                                                multiselect: true,
                                                                                width: 0.85
                                                                            };
                                                                            fullTable(config3);
                                                                        }
                                                                    }
                                                                });*/

                                                            }
                                                        }
                                                    }
                                                };
                                                fullTable(config2);
                                            }
                                            if (Body.Data <= 0) {
                                                return false;
                                            }
                                            var RkDtlRd2 =Body.Data[0].RkDtlRd;
                                            var AssSource = Body.Data[0].AssSource;
                                            var RkDtlRd3 = {
                                                "RkDtlRd": RkDtlRd2,
                                                "AssSource": AssSource
                                            }


                                        request({url:"/Rk/GetRKBarInfo",  async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(RkDtlRd3)
                                            }},function(Body){
                                            if (Body.MsgCode == "0x00000") {
                                                var config3 = {
                                                    tableId: "list3",
                                                    data: Body.Data,
                                                    colArr: colNamesArr3,
                                                    multiselect: true,
                                                    width: 0.85
                                                };
                                                fullTable(config3);
                                            }
                                        });
                                            /*$.ajax({
                                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                                type: "POST",
                                                async: false,//设为同步请求
                                                data: {
                                                    "ExecType": "00",
                                                    "BusData": JSON.stringify(RkDtlRd3)
                                                },
                                                success: function (res) {
                                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                        var config3 = {
                                                            tableId: "list3",
                                                            data: res.Body.Data,
                                                            colArr: colNamesArr3,
                                                            multiselect: true,
                                                            width: 0.85
                                                        };
                                                        fullTable(config3);
                                                    }
                                                }
                                            });*/
                                    });

                                    /*$.ajax({
                                        url: getBasePath() + "/Rk/GetRKDlInfo",
                                        type: "POST",
                                        async: false,//设为同步请求
                                        data: {
                                            "ExecType": "00",
                                            "BusData": JSON.stringify(data)
                                        },
                                        success: function (res) {
                                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                var config2 = {
                                                    tableId: "list2",
                                                    data: res.Body.Data,
                                                    colArr: colNamesArr2,
                                                    multiselect: true,
                                                    width: 0.85,
                                                    event: {
                                                        onrowselect: function (rowdatas) {
                                                            for (var j in rowdatas) {
                                                                var RkDtlRd = rowdatas[j].RkDtlRd;
                                                                var AssSource = rowdatas[j].AssSource;
                                                                var RkDtlRd1 = {
                                                                    "RkDtlRd": RkDtlRd,
                                                                    "AssSource": AssSource
                                                                }
                                                                $.ajax({
                                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                    type: "POST",
                                                                    async: false,//设为同步请求
                                                                    data: {
                                                                        "ExecType": "00",
                                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                                    },
                                                                    success: function (res) {
                                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                            var config3 = {
                                                                                tableId: "list3",
                                                                                data: res.Body.Data,
                                                                                colArr: colNamesArr3,
                                                                                multiselect: true,
                                                                                width: 0.85
                                                                            };
                                                                            fullTable(config3);
                                                                        }
                                                                    }
                                                                });

                                                            }
                                                        }
                                                    }
                                                };
                                                fullTable(config2);
                                            }
                                            if (res.Body.Data <= 0) {
                                                return false;
                                            }
                                            var RkDtlRd2 = res.Body.Data[0].RkDtlRd;
                                            var AssSource = res.Body.Data[0].AssSource;
                                            var RkDtlRd3 = {
                                                "RkDtlRd": RkDtlRd2,
                                                "AssSource": AssSource
                                            }
                                            $.ajax({
                                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                                type: "POST",
                                                async: false,//设为同步请求
                                                data: {
                                                    "ExecType": "00",
                                                    "BusData": JSON.stringify(RkDtlRd3)
                                                },
                                                success: function (res) {
                                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                        var config3 = {
                                                            tableId: "list3",
                                                            data: res.Body.Data,
                                                            colArr: colNamesArr3,
                                                            multiselect: true,
                                                            width: 0.85
                                                        };
                                                        fullTable(config3);
                                                    }
                                                }
                                            });
                                        }
                                    });*/
                                }
                            }
                        }
                    };
                    fullTable(config1);
                }
                if (Body.Data == null || Body.Data == "") {
                    return false;
                }
                RkRd = Body.Data[0].RkRd;
                $(".cDel").attr("a", RkRd);
                $(".cCancel").attr("a", RkRd);
                $(".cSuccess").attr("a", RkRd);
                var data1 = {
                    "RkRd": RkRd
                }


            request({url:"/Rk/GetRKDlInfo",   async: false,//设为同步请求
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(data1)
                }},function(Body){
                if (Body.MsgCode == "0x00000") {
                    var config2 = {
                        tableId: "list2",
                        data: Body.Data,
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.85,
                        event: {
                            onrowselect: function (rowdatas) {
                                for (var j in rowdatas) {
                                    var RkDtlRd = rowdatas[j].RkDtlRd;
                                    var AssSource = rowdatas[j].AssSource;
                                    var RkDtlRd1 = {
                                        "RkDtlRd": RkDtlRd,
                                        "AssSource": AssSource
                                    }


                                    request({url:"/Rk/GetRKBarInfo",  async: false,//设为同步请求
                                        data: {
                                            "ExecType": "00",
                                            "BusData": JSON.stringify(RkDtlRd1)
                                        }},function(Body){
                                        if (Body.MsgCode == "0x00000") {
                                            var config3 = {
                                                tableId: "list3",
                                                data:Body.Data,
                                                colArr: colNamesArr3,
                                                multiselect: true,
                                                width: 0.85
                                            };
                                            fullTable(config3);
                                        }
                                    });
                                   /* $.ajax({
                                        url: getBasePath() + "/Rk/GetRKBarInfo",
                                        type: "POST",
                                        async: false,//设为同步请求
                                        data: {
                                            "ExecType": "00",
                                            "BusData": JSON.stringify(RkDtlRd1)
                                        },
                                        success: function (res) {
                                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                var config3 = {
                                                    tableId: "list3",
                                                    data: res.Body.Data,
                                                    colArr: colNamesArr3,
                                                    multiselect: true,
                                                    width: 0.85
                                                };
                                                fullTable(config3);
                                            }
                                        }
                                    });*/

                                }
                            }
                        }
                    };

                    if (Body.Data.length <= 0) {
                        return false;
                    }
                    var RkDtlRd = Body.Data[0].RkDtlRd;
                    var AssSource = Body.Data[0].AssSource;
                    var RkDtlRd1 = {
                        "RkDtlRd": RkDtlRd,
                        "AssSource": AssSource
                    }

                    request({url:"/Rk/GetRKBarInfo",  async: false,//设为同步请求
                        data: {
                            "ExecType": "00",
                            "BusData": JSON.stringify(RkDtlRd1)
                        }},function(Body){
                        if (Body.MsgCode == "0x00000") {
                            var config3 = {
                                tableId: "list3",
                                data: Body.Data,
                                colArr: colNamesArr3,
                                multiselect: true,
                                width: 0.85
                            };
                            fullTable(config3);
                        }
                    });
                    /*$.ajax({
                        url: getBasePath() + "/Rk/GetRKBarInfo",
                        type: "POST",
                        async: false,//设为同步请求
                        data: {
                            "ExecType": "00",
                            "BusData": JSON.stringify(RkDtlRd1)
                        },
                        success: function (res) {
                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                var config3 = {
                                    tableId: "list3",
                                    data: res.Body.Data,
                                    colArr: colNamesArr3,
                                    multiselect: true,
                                    width: 0.85
                                };
                                fullTable(config3);
                            }
                        }
                    });*/
                    fullTable(config2);
                }
            });

              /*  $.ajax({
                    url: getBasePath() + "/Rk/GetRKDlInfo",
                    type: "POST",
                    async: false,//设为同步请求
                    data: {
                        "ExecType": "00",
                        "BusData": JSON.stringify(data1)
                    },
                    success: function (res1) {
                        if (res1.Status == "00" && res1.Body.MsgCode == "0x00000") {
                            var config2 = {
                                tableId: "list2",
                                data: res1.Body.Data,
                                colArr: colNamesArr2,
                                multiselect: true,
                                width: 0.85,
                                event: {
                                    onrowselect: function (rowdatas) {
                                        for (var j in rowdatas) {
                                            var RkDtlRd = rowdatas[j].RkDtlRd;
                                            var AssSource = rowdatas[j].AssSource;
                                            var RkDtlRd1 = {
                                                "RkDtlRd": RkDtlRd,
                                                "AssSource": AssSource
                                            }
                                            $.ajax({
                                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                                type: "POST",
                                                async: false,//设为同步请求
                                                data: {
                                                    "ExecType": "00",
                                                    "BusData": JSON.stringify(RkDtlRd1)
                                                },
                                                success: function (res) {
                                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                        var config3 = {
                                                            tableId: "list3",
                                                            data: res.Body.Data,
                                                            colArr: colNamesArr3,
                                                            multiselect: true,
                                                            width: 0.85
                                                        };
                                                        fullTable(config3);
                                                    }
                                                }
                                            });

                                        }
                                    }
                                }
                            };

                            if (res1.Body.Data.length <= 0) {
                                return false;
                            }
                            var RkDtlRd = res1.Body.Data[0].RkDtlRd;
                            var AssSource = res1.Body.Data[0].AssSource;
                            var RkDtlRd1 = {
                                "RkDtlRd": RkDtlRd,
                                "AssSource": AssSource
                            }
                            $.ajax({
                                url: getBasePath() + "/Rk/GetRKBarInfo",
                                type: "POST",
                                async: false,//设为同步请求
                                data: {
                                    "ExecType": "00",
                                    "BusData": JSON.stringify(RkDtlRd1)
                                },
                                success: function (res) {
                                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                        var config3 = {
                                            tableId: "list3",
                                            data: res.Body.Data,
                                            colArr: colNamesArr3,
                                            multiselect: true,
                                            width: 0.85
                                        };
                                        fullTable(config3);
                                    }
                                }
                            });
                            fullTable(config2);
                        }
                    }
                });*/
                RkRd = "";

        });


         /*   $.ajax({
                id: "list1",
                url: getBasePath() + "/Rk/GetAllRKInfo",
                type: "POST",
                async: false,//设为同步请求
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(rkTCode)
                },
                success: function (res) {
                    if (res.Body.Data == null && res.Body.MsgCode == "MES00xxx") {
                        return false;
                    }
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        var config1 = {
                            tableId: "list1",
                            data: res.Body.Data,
                            colArr: colNamesArr1,
                            multiselect: true,
                            width: 0.85,
                            event: {
                                onrowselect: function (rowdata) {
                                    for (var i in rowdata) {
                                        RkRd = rowdata[i].RkRd;
                                        $(".cDel").attr("a", RkRd);
                                        $(".cCancel").attr("a", RkRd);
                                        $(".cSuccess").attr("a", RkRd);
                                        var data = {
                                            "RkRd": RkRd
                                        }
                                        $.ajax({
                                            url: getBasePath() + "/Rk/GetRKDlInfo",
                                            type: "POST",
                                            async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(data)
                                            },
                                            success: function (res) {
                                                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                    var config2 = {
                                                        tableId: "list2",
                                                        data: res.Body.Data,
                                                        colArr: colNamesArr2,
                                                        multiselect: true,
                                                        width: 0.85,
                                                        event: {
                                                            onrowselect: function (rowdatas) {
                                                                for (var j in rowdatas) {
                                                                    var RkDtlRd = rowdatas[j].RkDtlRd;
                                                                    var AssSource = rowdatas[j].AssSource;
                                                                    var RkDtlRd1 = {
                                                                        "RkDtlRd": RkDtlRd,
                                                                        "AssSource": AssSource
                                                                    }
                                                                    $.ajax({
                                                                        url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                        type: "POST",
                                                                        async: false,//设为同步请求
                                                                        data: {
                                                                            "ExecType": "00",
                                                                            "BusData": JSON.stringify(RkDtlRd1)
                                                                        },
                                                                        success: function (res) {
                                                                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                                var config3 = {
                                                                                    tableId: "list3",
                                                                                    data: res.Body.Data,
                                                                                    colArr: colNamesArr3,
                                                                                    multiselect: true,
                                                                                    width: 0.85
                                                                                };
                                                                                fullTable(config3);
                                                                            }
                                                                        }
                                                                    });

                                                                }
                                                            }
                                                        }
                                                    };
                                                    fullTable(config2);
                                                }
                                                if (res.Body.Data <= 0) {
                                                    return false;
                                                }
                                                var RkDtlRd2 = res.Body.Data[0].RkDtlRd;
                                                var AssSource = res.Body.Data[0].AssSource;
                                                var RkDtlRd3 = {
                                                    "RkDtlRd": RkDtlRd2,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd3)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            }
                        };
                        fullTable(config1);
                    }
                    if (res.Body.Data == null || res.Body.Data == "") {
                        return false;
                    }
                    RkRd = res.Body.Data[0].RkRd;
                    $(".cDel").attr("a", RkRd);
                    $(".cCancel").attr("a", RkRd);
                    $(".cSuccess").attr("a", RkRd);
                    var data1 = {
                        "RkRd": RkRd
                    }

                    $.ajax({
                        url: getBasePath() + "/Rk/GetRKDlInfo",
                        type: "POST",
                        async: false,//设为同步请求
                        data: {
                            "ExecType": "00",
                            "BusData": JSON.stringify(data1)
                        },
                        success: function (res1) {
                            if (res1.Status == "00" && res1.Body.MsgCode == "0x00000") {
                                var config2 = {
                                    tableId: "list2",
                                    data: res1.Body.Data,
                                    colArr: colNamesArr2,
                                    multiselect: true,
                                    width: 0.85,
                                    event: {
                                        onrowselect: function (rowdatas) {
                                            for (var j in rowdatas) {
                                                var RkDtlRd = rowdatas[j].RkDtlRd;
                                                var AssSource = rowdatas[j].AssSource;
                                                var RkDtlRd1 = {
                                                    "RkDtlRd": RkDtlRd,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });

                                            }
                                        }
                                    }
                                };

                                if (res1.Body.Data.length <= 0) {
                                    return false;
                                }
                                var RkDtlRd = res1.Body.Data[0].RkDtlRd;
                                var AssSource = res1.Body.Data[0].AssSource;
                                var RkDtlRd1 = {
                                    "RkDtlRd": RkDtlRd,
                                    "AssSource": AssSource
                                }
                                $.ajax({
                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                    type: "POST",
                                    async: false,//设为同步请求
                                    data: {
                                        "ExecType": "00",
                                        "BusData": JSON.stringify(RkDtlRd1)
                                    },
                                    success: function (res) {
                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                            var config3 = {
                                                tableId: "list3",
                                                data: res.Body.Data,
                                                colArr: colNamesArr3,
                                                multiselect: true,
                                                width: 0.85
                                            };
                                            fullTable(config3);
                                        }
                                    }
                                });
                                fullTable(config2);
                            }
                        }
                    });
                    RkRd = "";
                }

            });*/
    });
    /*$.ajax({
        url: getBasePath() + "/Rk/GetRKTInfo",
        type: "POST",
        data: {
            "ExecType": "00"
        },
        success: function (data) {
            if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                var treedataList = [];
                for (var i = 0; i < data.Body.Data.length; i++) {
                    var tree = {
                        id: data.Body.Data[i].PkTCode == undefined ? "" : data.Body.Data[i].PkTCode,
                        name: data.Body.Data[i].PkTName == undefined ? "" : data.Body.Data[i].PkTName
                    }
                    treedataList.push(tree);
                }
                var config = {
                    id: "jstree_demo1",
                    data: {
                        source: treedataList,
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
                $.JstreeEx.init(config);
            }

            if (data.Body.Data.length <= 0) {
                $("#formContent").hide();
                return false;
            }
            $("#formContent").show();

            //加载默认信息
            if ("原材料入库" == data.Body.Data[0].PkTName) {
                var str = "01";
            }
            if ("生产入库" == data.Body.Data[0].PkTName) {
                var str = "00";
            }
            if ("生产退料" == data.Body.Data[0].PkTName) {
                var str = "02";
            }
            //入库单列表信息 根据Code
            var rkTCode = {
                "RkTCode": str
            }
            $("#list1").html("");
            var config2 = {
                tableId: "list2",
                data: [],
                colArr: colNamesArr2,
                multiselect: true,
                width: 0.85
            };
            fullTable(config2);
            var config3 = {
                tableId: "list3",
                data: [],
                colArr: colNamesArr3,
                multiselect: true,
                width: 0.85
            };
            fullTable(config3);
            $.ajax({
                id: "list1",
                url: getBasePath() + "/Rk/GetAllRKInfo",
                type: "POST",
                async: false,//设为同步请求
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(rkTCode)
                },
                success: function (res) {
                    if (res.Body.Data == null && res.Body.MsgCode == "MES00xxx") {
                        return false;
                    }
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        var config1 = {
                            tableId: "list1",
                            data: res.Body.Data,
                            colArr: colNamesArr1,
                            multiselect: true,
                            width: 0.85,
                            event: {
                                onrowselect: function (rowdata) {
                                    for (var i in rowdata) {
                                        RkRd = rowdata[i].RkRd;
                                        $(".cDel").attr("a", RkRd);
                                        $(".cCancel").attr("a", RkRd);
                                        $(".cSuccess").attr("a", RkRd);
                                        var data = {
                                            "RkRd": RkRd
                                        }
                                        $.ajax({
                                            url: getBasePath() + "/Rk/GetRKDlInfo",
                                            type: "POST",
                                            async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(data)
                                            },
                                            success: function (res) {
                                                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                    var config2 = {
                                                        tableId: "list2",
                                                        data: res.Body.Data,
                                                        colArr: colNamesArr2,
                                                        multiselect: true,
                                                        width: 0.85,
                                                        event: {
                                                            onrowselect: function (rowdatas) {
                                                                for (var j in rowdatas) {
                                                                    var RkDtlRd = rowdatas[j].RkDtlRd;
                                                                    var AssSource = rowdatas[j].AssSource;
                                                                    var RkDtlRd1 = {
                                                                        "RkDtlRd": RkDtlRd,
                                                                        "AssSource": AssSource
                                                                    }
                                                                    $.ajax({
                                                                        url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                        type: "POST",
                                                                        async: false,//设为同步请求
                                                                        data: {
                                                                            "ExecType": "00",
                                                                            "BusData": JSON.stringify(RkDtlRd1)
                                                                        },
                                                                        success: function (res) {
                                                                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                                var config3 = {
                                                                                    tableId: "list3",
                                                                                    data: res.Body.Data,
                                                                                    colArr: colNamesArr3,
                                                                                    multiselect: true,
                                                                                    width: 0.85
                                                                                };
                                                                                fullTable(config3);
                                                                            }
                                                                        }
                                                                    });

                                                                }
                                                            }
                                                        }
                                                    };
                                                    fullTable(config2);
                                                }
                                                if (res.Body.Data <= 0) {
                                                    return false;
                                                }
                                                var RkDtlRd2 = res.Body.Data[0].RkDtlRd;
                                                var AssSource = res.Body.Data[0].AssSource;
                                                var RkDtlRd3 = {
                                                    "RkDtlRd": RkDtlRd2,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd3)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            }
                        };
                        fullTable(config1);
                    }
                    if (res.Body.Data == null || res.Body.Data == "") {
                        return false;
                    }
                    RkRd = res.Body.Data[0].RkRd;
                    $(".cDel").attr("a", RkRd);
                    $(".cCancel").attr("a", RkRd);
                    $(".cSuccess").attr("a", RkRd);
                    var data1 = {
                        "RkRd": RkRd
                    }

                    $.ajax({
                        url: getBasePath() + "/Rk/GetRKDlInfo",
                        type: "POST",
                        async: false,//设为同步请求
                        data: {
                            "ExecType": "00",
                            "BusData": JSON.stringify(data1)
                        },
                        success: function (res1) {
                            if (res1.Status == "00" && res1.Body.MsgCode == "0x00000") {
                                var config2 = {
                                    tableId: "list2",
                                    data: res1.Body.Data,
                                    colArr: colNamesArr2,
                                    multiselect: true,
                                    width: 0.85,
                                    event: {
                                        onrowselect: function (rowdatas) {
                                            for (var j in rowdatas) {
                                                var RkDtlRd = rowdatas[j].RkDtlRd;
                                                var AssSource = rowdatas[j].AssSource;
                                                var RkDtlRd1 = {
                                                    "RkDtlRd": RkDtlRd,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });

                                            }
                                        }
                                    }
                                };

                                if (res1.Body.Data.length <= 0) {
                                    return false;
                                }
                                var RkDtlRd = res1.Body.Data[0].RkDtlRd;
                                var AssSource = res1.Body.Data[0].AssSource;
                                var RkDtlRd1 = {
                                    "RkDtlRd": RkDtlRd,
                                    "AssSource": AssSource
                                }
                                $.ajax({
                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                    type: "POST",
                                    async: false,//设为同步请求
                                    data: {
                                        "ExecType": "00",
                                        "BusData": JSON.stringify(RkDtlRd1)
                                    },
                                    success: function (res) {
                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                            var config3 = {
                                                tableId: "list3",
                                                data: res.Body.Data,
                                                colArr: colNamesArr3,
                                                multiselect: true,
                                                width: 0.85
                                            };
                                            fullTable(config3);
                                        }
                                    }
                                });
                                fullTable(config2);
                            }
                        }
                    });
                    RkRd = "";
                }

            });
        }
    });*/

    //编辑双击事件
    /* $(document).on("dblclick","#list1 tr[role='row']",function(){
     var RkRd= $(this).children(":first").next().attr("title");
     top.layer.open( {
     type: 2,
     title: '编辑',
     shadeClose: true,
     shade: 0.6,
     area: ['80%', '65%'],
     content: getBasePath()+'/Rk/ModifyRk/'+RkRd //iframe的url
     });
     });*/


    //删除
    $(".cDel").click(function () {
        var RkRd = $(".cDel").attr("a");
        var data = {
            "RkRd": RkRd
        }
        if (RkRd == null || RkRd == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                $.ajax({
                    url: getBasePath() + "/Rk/SaveRKInfo",
                    type: "POST",
                    data: {
                        "ExecType": "01",
                        "busData": JSON.stringify(data)
                    },
                    success: function (res) {
                        layer.closeAll("dialog");
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            toastr.success(res.Body.MsgDes);
                            loadTree();
                            $("#formContent").hide();
                            $(".cDel").attr("a", "")
                        }
                    }
                });
            })
        }
    });

    //取消
    $(".cCancel").click(function () {
        var rkrd = $(this).attr("a");
        var data = {
            "RkRd": rkrd
        }
        if (rkrd == null || rkrd == "") {
            toastr.warning("请选择左侧要取消的一项再进行取消!");
        }
        else {
            layer.confirm('确认要取消吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                $.ajax({
                    url: getBasePath() + "/Rk/SaveRKInfo",
                    type: "POST",
                    data: {
                        "ExecType": "03",
                        "busData": JSON.stringify(data)
                    },
                    success: function (res) {
                        layer.closeAll("dialog");
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {

                            $(".cCancel").attr("a", "");
                        }
                        toastr.warning(res.Body.MsgDes);
                    }
                });
            })
        }

    });

    //成功
    $(".cSuccess").click(function () {
        var RkRd = $(this).attr("a");
        var data3 = {
            "RkRd": RkRd
        }
        request({url: '/Rk/SaveRKInfo', data: {"ExecType": "04", "BusData": JSON.stringify(data3)}}, function (Body) {
            toastr.success(Body.MsgDes);
            $(".cSuccess").attr("a", "");
        })
    });

    var s1 = [];
    for (var i = 0; i < newTree.length; i++) {
        var news = {
            "id": newTree[i].id,
            "name": newTree[i].name
        }
        s1.push(news);
    }
    //树的自动搜索功能
    treeSearch(s1);

    //筛选
    var params = [, {
        "caption": "单据类型",
        "name": "RkType",
        "valtype": "01",
        "data": "\"00\":\"生产入库\"|\"01\":\"原材料入库\"|\"02\":\"生产退料\""
    }, {
        "caption": "单号",
        "name": "AssCode",
        "valtype": "00"
    }, {
        "caption": "时间段",
        "name": "ExecTime",
        "valtype": "02"
    }];

    var datasources = {};
    var event = {
        onsure: function (result) {
            var data = result[2].ExecTime.split("|");
            var data1 = data[0];
            var data2 = data[1];
            datasources = {
                "RkTCode": result[0].RkType,
                "AssCode": result[1].AssCode,
                "ExecTime": data1,
                "ExecTime1": data2 == "" ? "" : (data1 == data2 ? data2 + " 23:59:59" : data2)
            };
            $("#list1").html("");
            var config2 = {
                tableId: "list2",
                data: [],
                colArr: colNamesArr2,
                multiselect: true,
                width: 0.85
            };
            fullTable(config2);
            var config3 = {
                tableId: "list3",
                data: [],
                colArr: colNamesArr3,
                multiselect: true,
                width: 0.85
            };
            fullTable(config3);
            $("#formContent").show();
            $("#list1").html("");
            $.ajax({
                id: "list1",
                url: getBasePath() + "/Rk/GetAllRKInfo",
                type: "POST",
                async: true,
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(datasources)
                },
                success: function (res) {
                    if (res.Body.Data == null && res.Body.MsgCode == "MES00xxx") {
                        return false;
                    }
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        var config1 = {
                            tableId: "list1",
                            data: res.Body.Data,
                            colArr: colNamesArr1,
                            multiselect: true,
                            width: 0.85,
                            event: {
                                onrowselect: function (rowdata) {
                                    for (var i in rowdata) {
                                        RkRd = rowdata[i].RkRd;
                                        $(".cDel").attr("a", RkRd);
                                        $(".cCancel").attr("a", RkRd);
                                        $(".cSuccess").attr("a", RkRd);
                                        var data = {
                                            "RkRd": RkRd
                                        }
                                        $("#list2").html("");
                                        $.ajax({
                                            id: "list2",
                                            url: getBasePath() + "/Rk/GetRKDlInfo",
                                            type: "POST",
                                            async: false,//设为同步请求
                                            data: {
                                                "ExecType": "00",
                                                "BusData": JSON.stringify(data)
                                            },
                                            success: function (res) {
                                                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                    var config2 = {
                                                        tableId: "list2",
                                                        data: res.Body.Data,
                                                        colArr: colNamesArr2,
                                                        multiselect: true,
                                                        width: 0.85,
                                                        event: {
                                                            onrowselect: function (rowdatas) {
                                                                for (var j in rowdatas) {
                                                                    var RkDtlRd = rowdatas[j].RkDtlRd;
                                                                    var AssSource = rowdatas[j].AssSource;
                                                                    var RkDtlRd1 = {
                                                                        "RkDtlRd": RkDtlRd,
                                                                        "AssSource": AssSource
                                                                    }
                                                                    $("#list3").html("");
                                                                    $.ajax({
                                                                        id: "list3",
                                                                        url: getBasePath() + "/Rk/GetRKBarInfo",
                                                                        type: "POST",
                                                                        async: false,//设为同步请求
                                                                        data: {
                                                                            "ExecType": "00",
                                                                            "BusData": JSON.stringify(RkDtlRd1)
                                                                        },
                                                                        success: function (res) {
                                                                            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                                                var config3 = {
                                                                                    tableId: "list3",
                                                                                    data: res.Body.Data,
                                                                                    colArr: colNamesArr3,
                                                                                    multiselect: true,
                                                                                    width: 0.85
                                                                                };
                                                                                fullTable(config3);
                                                                            }
                                                                        }
                                                                    });

                                                                }
                                                            }
                                                        }
                                                    };
                                                    fullTable(config2);
                                                }
                                                if (res.Body.Data <= 0) {
                                                    return false;
                                                }
                                                var RkDtlRd2 = res.Body.Data[0].RkDtlRd;
                                                var AssSource = res.Body.Data[0].AssSource;
                                                var RkDtlRd3 = {
                                                    "RkDtlRd": RkDtlRd2,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd3)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            }
                        };
                        fullTable(config1);
                    }
                    if (res.Body.Data == null || res.Body.Data == "") {
                        return false;
                    }
                    RkRd = res.Body.Data[0].RkRd;
                    $(".cDel").attr("a", RkRd);
                    $(".cCancel").attr("a", RkRd);
                    $(".cSuccess").attr("a", RkRd);
                    var data1 = {
                        "RkRd": RkRd
                    }

                    $.ajax({
                        url: getBasePath() + "/Rk/GetRKDlInfo",
                        type: "POST",
                        async: false,//设为同步请求
                        data: {
                            "ExecType": "00",
                            "BusData": JSON.stringify(data1)
                        },
                        success: function (res1) {
                            if (res1.Status == "00" && res1.Body.MsgCode == "0x00000") {
                                var config2 = {
                                    tableId: "list2",
                                    data: res1.Body.Data,
                                    colArr: colNamesArr2,
                                    multiselect: true,
                                    width: 0.85,
                                    event: {
                                        onrowselect: function (rowdatas) {
                                            for (var j in rowdatas) {
                                                var RkDtlRd = rowdatas[j].RkDtlRd;
                                                var AssSource = rowdatas[j].AssSource;
                                                var RkDtlRd1 = {
                                                    "RkDtlRd": RkDtlRd,
                                                    "AssSource": AssSource
                                                }
                                                $.ajax({
                                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                                    type: "POST",
                                                    async: false,//设为同步请求
                                                    data: {
                                                        "ExecType": "00",
                                                        "BusData": JSON.stringify(RkDtlRd1)
                                                    },
                                                    success: function (res) {
                                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                                            var config3 = {
                                                                tableId: "list3",
                                                                data: res.Body.Data,
                                                                colArr: colNamesArr3,
                                                                multiselect: true,
                                                                width: 0.85
                                                            };
                                                            fullTable(config3);
                                                        }
                                                    }
                                                });

                                            }
                                        }
                                    }
                                };

                                if (res1.Body.Data.length <= 0) {
                                    return false;
                                }
                                var RkDtlRd = res1.Body.Data[0].RkDtlRd;
                                var AssSource = res1.Body.Data[0].AssSource;
                                var RkDtlRd1 = {
                                    "RkDtlRd": RkDtlRd,
                                    "AssSource": AssSource
                                }
                                $.ajax({
                                    url: getBasePath() + "/Rk/GetRKBarInfo",
                                    type: "POST",
                                    async: false,//设为同步请求
                                    data: {
                                        "ExecType": "00",
                                        "BusData": JSON.stringify(RkDtlRd1)
                                    },
                                    success: function (res) {
                                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                                            var config3 = {
                                                tableId: "list3",
                                                data: res.Body.Data,
                                                colArr: colNamesArr3,
                                                multiselect: true,
                                                width: 0.85
                                            };
                                            fullTable(config3);
                                        }
                                    }
                                });
                                fullTable(config2);
                            }
                        }
                    });
                    RkRd = "";
                }
            });
        }
    };

    //筛选
    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);

    //导出筛选数据
    $("#export").on('click', function () {
        var data=getSeRowData("list1");
        var objBusData=[];
        data.forEach(function (e) {
            var objBusData1 = {
                "RkRd":e.RkRd
            }
            objBusData.push(objBusData1);

        });
        if (objBusData == null || objBusData.length<=0) {
            toastr.warning("请选择一行入库单再导出！");
            return;
        }
        layer.confirm('确认要导出该库存信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            var url = getBasePath() + "/Rk/ExportGetRKInfo";


           /* var objBusData = {
                "RkRd": RkRd
            }*/


            var data_ = "ExecType=00&BusData=" + JSON.stringify(objBusData);
            var xhr = new XMLHttpRequest();
            xhr.open('POST', url, true);
            xhr.responseType = 'arraybuffer';
            xhr.onload = function () {
                if (this.status === 200) {
                    var filename = "";
                    var disposition = xhr.getResponseHeader('Content-Disposition');
                    if (disposition && disposition.indexOf('attachment') !== -1) {
                        var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                        var matches = filenameRegex.exec(disposition);
                        if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');

                        var type = xhr.getResponseHeader('Content-Type');

                        var date = new Date();

                        filename = "入库单管理表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

                        var blob = new Blob([this.response], {type: type});
                        if (typeof window.navigator.msSaveBlob !== 'undefined') {
                            window.navigator.msSaveBlob(blob, filename);
                        } else {
                            var URL = window.URL || window.webkitURL;
                            var downloadUrl = URL.createObjectURL(blob);

                            if (filename) {

                                var a = document.createElement("a");

                                if (typeof a.download === 'undefined') {
                                    window.location = downloadUrl;
                                } else {
                                    a.href = downloadUrl;
                                    a.download = filename;
                                    document.body.appendChild(a);
                                    a.click();
                                }
                            } else {
                                window.location = downloadUrl;
                            }

                            setTimeout(function () {
                                URL.revokeObjectURL(downloadUrl);
                            }, 100); // cleanup
                        }
                    } else {
                        var un = new Uint8Array(this.response);
                        var s = "";
                        for (var i = 0; i < un.length; i++) {
                            s += String.fromCharCode(un[i]);
                        }
                        var json = JSON.parse(s);
                        if (json.Body != undefined) {
                            toastr.warning(json.Body.MsgDes);
                        }
                    }
                }
            };
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xhr.send(data_);
        });
    });

})