$(function () {
    //默认加载上
    var colNamesArr1 = [
        {"Caption": "id", "Name": "CkRd", "IsKey": true, "Hidden": true},
        {"Caption": "出库单号", "Name": "CkCode", "Editable": false, "Hidden": true},
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
        width: 0.7
    };
    fullTable(config1);

    //默认加载上下左边的
    var colNamesArr2 = [
        {"Caption": "id", "Name": "CkDtlRd", "Hidden": true},
        {"Caption": "来源", "Name": "AssSource", "Editable": false, "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:80},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false,Width:150},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false,Width:150},
        {"Caption": "数量", "Name": "Num", "Editable": false,Width:50},
        {"Caption": "已出库数量", "Name": "ScanNum", "Editable": false,Width:50},
        {"Caption": "未出库数量", "Name": "UnScanNum", "Editable": false,Width:50},
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
        {"Caption": "到期日期", "Name": "ExpireDate", "Editable": false}
    ];
    var config3 = {
        tableId: "list3",
        data: [],
        colArr: colNamesArr3,
        multiselect: true,
        width: 0.85
    };
    fullTable(config3);

    var CkRd = "";
    var str="";
    var onClicks = function (nodeinfo, handle) {
        if (!nodeinfo.hasChildren) {
            $("#formContent").show();
            if ("领料出库" == nodeinfo.nodeText) {
                 str = "00";
            }
            if ("销售出库" == nodeinfo.nodeText) {
                 str = "01";
            }
            if ("其他出库" == nodeinfo.nodeText) {
                 str = "02";
            }
            $("#list1").html("");
            //入库单列表信息 根据Code
            var DataSources = {
                "CkTCode": str
            }
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
            request({
                id: "list1",
                url: '/Ck/GetAllCKInfo',
                async: true,
                data: {"ExecType": "00", "BusData": JSON.stringify(DataSources)}
            }, function (Body) {
                var config1 = {
                    tableId: "list1",
                    data: Body.Data,
                    colArr: colNamesArr1,
                    multiselect: true,
                    event: {
                        onrowselect: function (rowdatas) {
                            for (var j in rowdatas) {
                                CkRd = rowdatas[j].CkRd;
                                $(".cCancel").attr("a", CkRd);
                                $(".cDel").attr("a", CkRd)
                                var CkRd1 = {
                                    "CkRd": CkRd
                                }
                                request({
                                    url: '/Ck/GetCKDlInfo',
                                    data: {"ExecType": "00", "BusData": JSON.stringify(CkRd1)}
                                }, function (Body) {
                                    var config2 = {
                                        tableId: "list2",
                                        data: Body.Data,
                                        colArr: colNamesArr2,
                                        multiselect: true,
                                        width: 0.85,
                                        event: {
                                            onrowselect: function (rowdatas) {
                                                for (var j in rowdatas) {
                                                    var CkDtlRd = rowdatas[j].CkDtlRd;
                                                    var AssSource = rowdatas[j].AssSource;
                                                    var CkDtlRd1 = {
                                                        "AssSource": AssSource,
                                                        "CkDtlRd": CkDtlRd
                                                    }
                                                    request({
                                                        url: '/Ck/GetCKBarInfo',
                                                        data: {"ExecType": "00", "BusData": JSON.stringify(CkDtlRd1)}
                                                    }, function (Body) {
                                                        var config3 = {
                                                            tableId: "list3",
                                                            data: Body.Data,
                                                            colArr: colNamesArr3,
                                                            multiselect: true,
                                                            width: 0.85
                                                        };
                                                        fullTable(config3);
                                                    })

                                                }

                                            }
                                        }
                                    };
                                    fullTable(config2);
                                    //默认第三个
                                    if (Body.Data.length == "") {
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
                                    var data3 = {
                                        "CkDtlRd": Body.Data[0].CkDtlRd
                                        , "AssSource": Body.Data[0].AssSource
                                    }
                                    request({
                                        url: '/Ck/GetCKBarInfo',
                                        data: {"ExecType": "00", "BusData": JSON.stringify(data3)}
                                    }, function (Body) {
                                        var config3 = {
                                            tableId: "list3",
                                            data: Body.Data,
                                            colArr: colNamesArr3,
                                            multiselect: true,
                                            width: 0.85
                                        };
                                        fullTable(config3);
                                    })
                                })

                            }
                        }
                    }
                };
                fullTable(config1);
                //默认第二个
                if (Body.Data.length == "") {
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
                var data2 = {
                    "CkRd": Body.Data[0].CkRd
                }
                request({
                    url: '/Ck/GetCKDlInfo',
                    data: {"ExecType": "00", "BusData": JSON.stringify(data2)}
                }, function (Body) {
                    var config2 = {
                        tableId: "list2",
                        data: Body.Data,
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.85,
                        event: {
                            onrowselect: function (rowdatas) {
                                for (var j in rowdatas) {
                                    var CkDtlRd = rowdatas[j].CkDtlRd;
                                    var AssSource = rowdatas[j].AssSource;
                                    var CkDtlRd1 = {
                                        "AssSource": AssSource,
                                        "CkDtlRd": CkDtlRd
                                    }
                                    request({
                                        url: '/Ck/GetCKBarInfo',
                                        data: {"ExecType": "00", "BusData": JSON.stringify(CkDtlRd1)}
                                    }, function (Body) {
                                        var config3 = {
                                            tableId: "list3",
                                            data: Body.Data,
                                            colArr: colNamesArr3,
                                            multiselect: true,
                                            width: 0.85
                                        };
                                        fullTable(config3);
                                    })
                                }
                            }
                        }
                    };
                    fullTable(config2);
                    //默认第三个
                    if (Body.Data.length == "") {
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
                    var data3 = {
                        "CkDtlRd": Body.Data[0].CkDtlRd
                        , "AssSource": Body.Data[0].AssSource
                    }
                    request({
                        url: '/Ck/GetCKBarInfo',
                        data: {"ExecType": "00", "BusData": JSON.stringify(data3)}
                    }, function (Body) {
                        var config3 = {
                            tableId: "list3",
                            data: Body.Data,
                            colArr: colNamesArr3,
                            multiselect: true,
                            width: 0.85
                        };
                        fullTable(config3);
                    })
                })
                CkRd = "";
            });
        }
    }

    //加载出库单类型
    var newTree = [];
    var treedataList = [];
    var treeData = [];
    var loadTree = function () {

        request({url:"/Ck/GetCKTInfo", async: false,
            data: {
                "ExecType": "00"
            }},function(Body){
            if (Body.MsgCode == "0x00000") {
                treeData = Body.Data;
                for (var i = 0; i < Body.Data.length; i++) {
                    var tree = {
                        id: treeData[i].CkTCode == undefined ? "" : treeData[i].CkTCode,
                        name: treeData[i].CkTName == undefined ? "" : treeData[i].CkTName
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


       /* $.ajax({
            url: getBasePath() + "/Ck/GetCKTInfo",
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
                            id: treeData[i].CkTCode == undefined ? "" : treeData[i].CkTCode,
                            name: treeData[i].CkTName == undefined ? "" : treeData[i].CkTName
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
    //loadTree();


    request({url: '/Ck/GetCKTInfo', data: {"ExecType": "00"}}, function (Body) {
        var treeData = Body.Data;
        for (var i = 0; i < treeData.length; i++) {
            var tree = {
                id: treeData[i].CkTCode == undefined ? "" : treeData[i].CkTCode,
                name: treeData[i].CkTName == undefined ? "" : treeData[i].CkTName
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
            event: {
                onClick: onClicks
            }
        };
        $.JstreeEx.init(config);//先调用后加载

        if (treeData.length <= 0) {
            $("#formContent").hide();
            return false;
        }
        $("#formContent").show();
        $("#list1").html("");
        //加载默认信息
        if ("领料出库" == treeData[0].CkTName) {
            var str = "00";
        }
        if ("销售出库" == treeData[0].CkTName) {
            var str = "01";
        }
        if ("其他出库" == treeData[0].CkTName) {
            var str = "02";
        }
        var CkTCode = {
            "CkTCode": str
        }
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
        request({
            id: "list1",
            url: '/Ck/GetAllCKInfo',
            data: {"ExecType": "00", "BusData": JSON.stringify(CkTCode)}
        }, function (Body) {
            var config1 = {
                tableId: "list1",
                data: Body.Data,
                colArr: colNamesArr1,
                multiselect: true,
                event: {
                    onrowselect: function (rowdatas) {
                        for (var j in rowdatas) {
                            CkRd = rowdatas[j].CkRd;
                            $(".cCancel").attr("a", CkRd);
                            $(".cDel").attr("a", CkRd)
                            var CkRd1 = {
                                "CkRd": CkRd
                            }
                            request({
                                url: '/Ck/GetCKDlInfo',
                                data: {"ExecType": "00", "BusData": JSON.stringify(CkRd1)}
                            }, function (Body) {
                                var config2 = {
                                    tableId: "list2",
                                    data: Body.Data,
                                    colArr: colNamesArr2,
                                    multiselect: true,
                                    width: 0.85,
                                    event: {
                                        onrowselect: function (rowdatas) {
                                            for (var j in rowdatas) {
                                                var CkDtlRd = rowdatas[j].CkDtlRd;
                                                var AssSource = rowdatas[j].AssSource;
                                                var CkDtlRd1 = {
                                                    "AssSource": AssSource,
                                                    "CkDtlRd": CkDtlRd
                                                }
                                                request({
                                                    url: '/Ck/GetCKBarInfo',
                                                    data: {"ExecType": "00", "BusData": JSON.stringify(CkDtlRd1)}
                                                }, function (Body) {
                                                    var config3 = {
                                                        tableId: "list3",
                                                        data: Body.Data,
                                                        colArr: colNamesArr3,
                                                        multiselect: true,
                                                        width: 0.85
                                                    };
                                                    fullTable(config3);
                                                })

                                            }

                                        }
                                    }
                                };
                                fullTable(config2);
                                //默认第三个
                                if (Body.Data.length == "") {
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
                                var data3 = {
                                    "CkDtlRd": Body.Data[0].CkDtlRd
                                    , "AssSource": Body.Data[0].AssSource
                                }
                                request({
                                    url: '/Ck/GetCKBarInfo',
                                    data: {"ExecType": "00", "BusData": JSON.stringify(data3)}
                                }, function (Body) {
                                    var config3 = {
                                        tableId: "list3",
                                        data: Body.Data,
                                        colArr: colNamesArr3,
                                        multiselect: true,
                                        width: 0.85
                                    };
                                    fullTable(config3);
                                })
                            })

                        }
                    }
                }
            };
            fullTable(config1);
            //默认第二个
            if (Body.Data.length == "") {
                var config2 = {
                    tableId: "list2",
                    multiselect: true,
                    data: [],
                    colArr: colNamesArr2,
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
            var data2 = {
                "CkRd": Body.Data[0].CkRd
            }
            request({
                url: '/Ck/GetCKDlInfo',
                data: {"ExecType": "00", "BusData": JSON.stringify(data2)}
            }, function (Body) {
                var config2 = {
                    tableId: "list2",
                    data: Body.Data,
                    colArr: colNamesArr2,
                    multiselect: true,
                    width: 0.85,
                    event: {
                        onrowselect: function (rowdatas) {
                            for (var j in rowdatas) {
                                var CkDtlRd = rowdatas[j].CkDtlRd;
                                var AssSource = rowdatas[j].AssSource;
                                var CkDtlRd1 = {
                                    "AssSource": AssSource,
                                    "CkDtlRd": CkDtlRd
                                }
                                request({
                                    url: '/Ck/GetCKBarInfo',
                                    data: {"ExecType": "00", "BusData": JSON.stringify(CkDtlRd1)}
                                }, function (Body) {
                                    var config3 = {
                                        tableId: "list3",
                                        data: Body.Data,
                                        colArr: colNamesArr3,
                                        multiselect: true,
                                        width: 0.85
                                    };
                                    fullTable(config3);
                                })
                            }
                        }
                    }
                };
                fullTable(config2);
                //默认第三个
                if (Body.Data.length == "") {
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
                var data3 = {
                    "CkDtlRd": Body.Data[0].CkDtlRd
                    , "AssSource": Body.Data[0].AssSource
                }
                request({
                    url: '/Ck/GetCKBarInfo',
                    data: {"ExecType": "00", "BusData": JSON.stringify(data3)}
                }, function (Body) {
                    var config3 = {
                        tableId: "list3",
                        data: Body.Data,
                        colArr: colNamesArr3,
                        multiselect: true,
                        width: 0.85
                    };
                    fullTable(config3);
                });
            });
            CkRd = "";
        });
    });


    /*  //取消
     $(".cCancel").click(function(){
     var CkRd=$(this).attr("a");
     var data3={
     "CkRd":CkRd
     }
     if(CkRd==null||CkRd==""){
     toastr.warning("没有数据，取消失败");
     return false;
     }else{
     request({url: '/Ck/SaveCKInfo', data: {"ExecType": "03","BusData":JSON.stringify(data3)}}, function (Body) {
     if (Body.MsgCode=="xxx01"){
     toastr.success(Body.MsgDes);
     }
     else {
     toastr.warning(Body.MsgDes);
     $(".cCancel").attr("a","");
     }
     })
     }
     });

     //删除
     $(".cDel").click(function(){
     var CkRd=$(this).attr("a");
     var data3={
     "CkRd":CkRd
     }
     if(CkRd==null||CkRd==""){
     toastr.warning("没有数据，删除失败");
     return false;
     }else{
     request({url: '/Ck/SaveCKInfo', data: {"ExecType": "01","BusData":JSON.stringify(data3)}}, function (Body) {
     layer.closeAll("dialog");
     if (Body.MsgCode=="xxx3"){
     toastr.success(Body.MsgDes);
     }
     else {
     toastr.warning(Body.MsgDes);
     $(".cDel").attr("a","");
     $("#formContent").hide();
     }
     })
     }
     });*/

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
        "name": "CkType",
        "valtype": "01",
        "data": "\"00\":\"领料出库\"|\"01\":\"销售出库\"|\"02\":\"其它出库\""
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
    var data="";
    var asscode="";
    var event = {
        onsure: function (result) {
            data = result[2].ExecTime.split("|");
            var data1 = data[0];
            var data2 = data[1];
            asscode=result[1].AssCode;
            str=result[0].CkType;
            datasources = {
                "CkTCode":str,
                "AssCode":asscode,
                "ExecTime": data1,
                "ExecTime1": data2 == "" ? "" : (data1 == data2 ? data2 + " 23:59:59" : data2)
            };

            $("#formContent").show();
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
            request({
                id: "list1",
                url: '/Ck/GetAllCKInfo',
                async: true,
                data: {"ExecType": "00", "BusData": JSON.stringify(datasources)}
            }, function (Body) {
                var config1 = {
                    tableId: "list1",
                    data: Body.Data,
                    colArr: colNamesArr1,
                    multiselect: true,
                    event: {
                        onrowselect: function (rowdatas) {
                            for (var j in rowdatas) {
                                CkRd = rowdatas[j].CkRd;
                                $(".cCancel").attr("a", CkRd);
                                $(".cDel").attr("a", CkRd)
                                var CkRd1 = {
                                    "CkRd": CkRd
                                }
                                request({
                                    url: '/Ck/GetCKDlInfo',
                                    data: {"ExecType": "00", "BusData": JSON.stringify(CkRd1)}
                                }, function (Body) {
                                    var config2 = {
                                        tableId: "list2",
                                        data: Body.Data,
                                        colArr: colNamesArr2,
                                        multiselect: true,
                                        width: 0.85,
                                        event: {
                                            onrowselect: function (rowdatas) {
                                                for (var j in rowdatas) {
                                                    var CkDtlRd = rowdatas[j].CkDtlRd;
                                                    var AssSource = rowdatas[j].AssSource;
                                                    var CkDtlRd1 = {
                                                        "AssSource": AssSource,
                                                        "CkDtlRd": CkDtlRd
                                                    }
                                                    request({
                                                        url: '/Ck/GetCKBarInfo',
                                                        data: {"ExecType": "00", "BusData": JSON.stringify(CkDtlRd1)}
                                                    }, function (Body) {
                                                        var config3 = {
                                                            tableId: "list3",
                                                            data: Body.Data,
                                                            colArr: colNamesArr3,
                                                            multiselect: true,
                                                            width: 0.85
                                                        };
                                                        fullTable(config3);
                                                    })

                                                }

                                            }
                                        }
                                    };
                                    fullTable(config2);
                                    //默认第三个
                                    if (Body.Data.length == "") {
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
                                    var data3 = {
                                        "CkDtlRd": Body.Data[0].CkDtlRd
                                        , "AssSource": Body.Data[0].AssSource
                                    }
                                    request({
                                        url: '/Ck/GetCKBarInfo',
                                        data: {"ExecType": "00", "BusData": JSON.stringify(data3)}
                                    }, function (Body) {
                                        var config3 = {
                                            tableId: "list3",
                                            data: Body.Data,
                                            colArr: colNamesArr3,
                                            multiselect: true,
                                            width: 0.85
                                        };
                                        fullTable(config3);
                                    })
                                })

                            }
                        }
                    }
                };
                fullTable(config1);
                //默认第二个
                if (Body.Data.length == "") {
                    var config2 = {
                        tableId: "list2",
                        multiselect: true,
                        data: [],
                        colArr: colNamesArr2,
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
                var data2 = {
                    "CkRd": Body.Data[0].CkRd
                }
                request({
                    url: '/Ck/GetCKDlInfo',
                    data: {"ExecType": "00", "BusData": JSON.stringify(data2)}
                }, function (Body) {
                    var config2 = {
                        tableId: "list2",
                        data: Body.Data,
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.85,
                        event: {
                            onrowselect: function (rowdatas) {
                                for (var j in rowdatas) {
                                    var CkDtlRd = rowdatas[j].CkDtlRd;
                                    var AssSource = rowdatas[j].AssSource;
                                    var CkDtlRd1 = {
                                        "AssSource": AssSource,
                                        "CkDtlRd": CkDtlRd
                                    }
                                    request({
                                        url: '/Ck/GetCKBarInfo',
                                        data: {"ExecType": "00", "BusData": JSON.stringify(CkDtlRd1)}
                                    }, function (Body) {
                                        var config3 = {
                                            tableId: "list3",
                                            data: Body.Data,
                                            colArr: colNamesArr3,
                                            multiselect: true,
                                            width: 0.85
                                        };
                                        fullTable(config3);
                                    })
                                }
                            }
                        }
                    };
                    fullTable(config2);
                    //默认第三个
                    if (Body.Data.length == "") {
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
                    var data3 = {
                        "CkDtlRd": Body.Data[0].CkDtlRd
                        , "AssSource": Body.Data[0].AssSource
                    }
                    request({
                        url: '/Ck/GetCKBarInfo',
                        data: {"ExecType": "00", "BusData": JSON.stringify(data3)}
                    }, function (Body) {
                        var config3 = {
                            tableId: "list3",
                            data: Body.Data,
                            colArr: colNamesArr3,
                            multiselect: true,
                            width: 0.85
                        };
                        fullTable(config3);
                    })
                });
                CkRd = "";
            });
        }
    };

    //筛选
    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);


    //导出出库数据
    $("#export").on('click', function () {
        var datas=getSeRowData("list1");
        var datasources=[];
        datas.forEach(function (e) {
            var datasources1 = {
                "CkRd": e.CkRd
            }
            datasources.push(datasources1);
        });
        layer.confirm('确认要导出该库存信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            var url="";
           
            if(CkRd.trim() != "" || (CkRd.trim() != "" && str.trim() != "")) {
                url = getBasePath() + "/Ck/ExportGetCKDlInfo";
               /* datasources = {
                    "CkRd": CkRd
                }*/
            }

            if(CkRd == "" && str.trim() != ""){
                url = getBasePath() + "/Ck/ExportGetCKDlInfo1";
                if(data=="" && asscode==""){
                    datasources = {
                        "CkTCode":str,
                        "AssCode":"",
                        "ExecTime": "",
                        "ExecTime1": ""
                    }
                }
            }


            if(str=="" && datasources.length<=0){
                toastr.warning("请选择出库类型或请选择一行出库单导出！");
                return;
            }

            var data_ = "ExecType=00&BusData=" + JSON.stringify(datasources);
            datasources={};
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

                        filename = "出库单管理表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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

});