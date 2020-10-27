/**
 * Created by test on 2017/8/23.
 */
$(function () {
    /*方法：------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#tab_4_li").css("display", "none").prev().addClass("active");
        $("#tab_4").removeClass("active").prev().addClass("active");
        $("#tab_5_li").css("display", "none").prev().prev().addClass("active");
        $("#tab_5").removeClass("active").prev().prev().addClass("active");
        $("#SourceType").attr("disabled", true);
        $(".inpSearch").attr("disabled", true);
        $("#stat").show();
        $("#status").show();
        $("#sStat").show();
        $("#sStatus").show();
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#InCCode").attr("readonly", true);
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"InCRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        loadData(objData);
    };

    function loadData(objData) {
        request({url: "/Ich/GetIncInfo", data: objData}, function (Body) {
            $("#defaultSelect").showData({
                id: Body.Data.PurChCode,
                name: Body.Data.PurChCode,
                keyfield: "PurChRd",
                fields: [
                    {"name": "PurChRd"},
                    {"name": "PurChCode"}
                ]
            });
            $("#InCCode").val(Body.Data.InCCode);
            var IncDlInfo = Body.Data.IncDlInfo;
            var str = "";
            if ("00" == Body.Data.Status) {
                str = "待处理";
            } else if ("01" == Body.Data.Status) {
                str = "进行中";
            } else if ("02" == Body.Data.Status) {
                str = "已完成";
            } else if ("03" == Body.Data.Status) {
                str = "已取消";
            }
            $("#status").text(str);
            $("#status").attr("exectype", Body.Data.Status);

            $("#SourceType").val(Body.Data.SourceType);
            if (Body.Data.SourceType == "01") {
                $("#defaultSelect_").hide();
            } else {
                $("#defaultSelect_").show();
            }
            var IncDlInfos = [];
            var b = true;
            if (Body.Data.DSource == "00") {
                b = false;
                config1.colArr = colNamesArr1;
                isEdit(false);
            } else {
                config1.colArr = colNamesArr;
                isEdit(true);
            }
            var sStr = "";
            if ("00" == Body.Data.SStatus) {
                sStr = "未下达";
                $("#beizhu").attr("disabled", false);
            } else if ("01" == Body.Data.SStatus) {
                sStr = "已下达";
                b = false;
                config1.colArr = colNamesArr1;
                isEdit(false);
                $("#beizhu").attr("disabled", true)
            } else if ("02" == Body.Data.SStatus) {
                sStr = "已取消";
                b = false;
                config1.colArr = colNamesArr1;
                isEdit(false);
                $("#beizhu").attr("disabled", true)
            }
            $("#sStatus").text(sStr);
            $("#sStatus").attr("exectype", Body.Data.SStatus);
            //其他
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);

            for (var i = 0; i < IncDlInfo.length; i++) {
                var Info = {
                    "InCDlRd": IncDlInfo[i].InCDlRd,
                    "PurChDlRd": IncDlInfo[i].PurChDlRd,
                    "MaVerRd": IncDlInfo[i].MaInfo.MaVerRd,
                    "MaCode": IncDlInfo[i].MaInfo.MaCode,
                    "MaName": IncDlInfo[i].MaInfo.MaName,
                    "MaDes": IncDlInfo[i].MaInfo.MaDes,
                    "Num": IncDlInfo[i].Num,
                    "UnitName": IncDlInfo[i].UnitName,
                    "AFeed": IncDlInfo[i].AFeed === "00" ? "是" : "否",
                    "StoreRd": IncDlInfo[i].StoreInfo == undefined ? "" : IncDlInfo[i].StoreInfo.StoreRd,
                    "StoreName": IncDlInfo[i].StoreInfo == undefined ? "" : IncDlInfo[i].StoreInfo.StoreName,
                    "Remark": IncDlInfo[i].Remark,
                    "TotalNum": IncDlInfo[i].TotalNum,
                    "TotalScanNum": IncDlInfo[i].TotalScanNum,
                    "UnCInCNum": IncDlInfo[i].UnCInCNum,
                    "ScanNum": IncDlInfo[i].ScanNum
                };
                IncDlInfos.push(Info);
            }

            config1.data = IncDlInfos;
            fullTable(config1);
            trDBLClick();
        });
        $("#ExecType").val("");
    }

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
        currentPage = treeSearchs("/Ich/GetAllIChInfo", "InCRd", "InCCode", "InCCode", condition, currentPage, config);
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
            currentPage = treeSearchs("/Ich/GetAllIChInfo", "InCRd", "InCCode", "InCCode", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })


    var list = [];
    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/Ich/GetAllIChInfo", "InCRd", "InCCode", "InCCode", condition, currentPage, config, list);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/Ich/GetAllIChInfo", "InCRd", "InCCode", "InCCode", condition, currentPage, config, list);
    });

    $("#_right").hide();

    var treeID = null;
    //主页面表格定义
    var StoreData = [];
    request({url: "/Store/GetAllStoreInfo", data: {"ExecType": "00"}}, function (Body) {
        StoreData = Body.Data;
    });

    var colNamesArr = [
        {"Caption": "InCDlRd", "Name": "InCDlRd", "Hidden": true},
        {"Caption": "PurChDlRd", "Name": "PurChDlRd", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Hidden": false, Width: 100},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", 'Editable': false},
        {"Caption": "物料描述", "Name": "MaDes", "Hidden": false},
        {"Caption": "甲供料", "Name": "AFeed", "CType": "text", "Hidden": true, 'Editable': false, Width: 50},
        {"Caption": "收料Rd", "Name": "StoreRd", "CType": "text", Width: 80, "Hidden": true},
        {"Caption": "收料仓库", "Name": "StoreName", "CType": "text", Width: 80},
        {"Caption": "收货数量", "Name": "Num", "CType": "text", 'Editable': true, Width: 80},
        {"Caption": "单位", "Name": "UnitName", 'Editable': false, Width: 80},
        {"Caption": "备注", "Name": "Remark", "CType": "text", 'Editable': true, Width: 80},
        {"Caption": "总量", "Name": "TotalNum", "Hidden": true},
        {"Caption": "已收数量", "Name": "TotalScanNum", "Hidden": true},
        {"Caption": "未开单量", "Name": "UnCInCNum", "Hidden": false},
        {"Caption": "已收货数量", "Name": "ScanNum", "Hidden": true}
    ];
    var colNamesArr1 = [
        {"Caption": "InCDlRd", "Name": "InCDlRd", "Hidden": true},
        {"Caption": "PurChDlRd", "Name": "PurChDlRd", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Hidden": false, Width: 100},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", 'Editable': false},
        {"Caption": "物料描述", "Name": "MaDes", "Hidden": false},
        {"Caption": "甲供料", "Name": "AFeed", "CType": "text", "Hidden": true, 'Editable': false, Width: 50},
        {"Caption": "收料Rd", "Name": "StoreRd", "CType": "text", Width: 80, "Hidden": true},
        {"Caption": "收料仓库", "Name": "StoreName", "CType": "text", Width: 80},
        {"Caption": "收货数量", "Name": "Num", "CType": "text", 'Editable': false, Width: 80},
        {"Caption": "单位", "Name": "UnitName", 'Editable': false, Width: 80},
        {"Caption": "备注", "Name": "Remark", 'Editable': false, Width: 80},
        {"Caption": "总量", "Name": "TotalNum", "Hidden": true},
        {"Caption": "已收数量", "Name": "TotalScanNum", "Hidden": true},
        {"Caption": "未开单量", "Name": "UnCInCNum", "Hidden": true},
        {"Caption": "已收货数量", "Name": "ScanNum", "Hidden": true}
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height: 0.64
    };
    fullTable(config1);//加载空表格

    // 查询所有的采购单号
    //下拉框
    var params = {
        "displaymode": "1",
        "title": "采购单",
        "binddata": {
            "keyfield": "PurChRd",
            "fields": [
                {
                    "caption": "采购单id",
                    "name": "PurChRd"
                }, {
                    "caption": "采购单号",
                    "name": "Remark"
                }, {
                    "caption": "采购单号",
                    "name": "PurChCode",
                    "hidden": true
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onclick": function (res) {
                var xldata = [], purChCode_ = res.PurChCode;


                //加载采购单信息
                request({
                    url: "/Ich/GetWIncMaInfo", data: {
                        "ExecType": "00", /*"InitData":JSON.stringify(InitData),*/
                        "BusData": "{\"PurChCode\":\"" + purChCode_ + "\"}"
                    }
                }, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PurChDlRd": datas[i].PurChDtlRd,
                            "MaVerRd": datas[i].MaInfo.MaVerRd,
                            "MaCode": datas[i].MaInfo.MaCode,
                            "MaName": datas[i].MaInfo.MaName,
                            "MaDes": datas[i].MaInfo.MaDes,
                            "TotalNum": datas[i].Num,
                            "ScanNum": datas[i].ScanNum,
                            "UnCInCNum": datas[i].UnCInCNum,
                            "Afeed": datas[i].Afeed,
                            "Num": datas[i].UnCInCNum,
                            "StoreName": datas[i].StoreInfo.StoreName,
                            "UnitName": datas[i].UnitName,
                            "Remark": datas[i].Remark == undefined ? "" : datas[i].Remark,
                            "StoreRd": datas[i].StoreInfo.StoreRd,
                            "TotalScanNum": 0
                        };
                        xldata.push(data);
                    }


                    var config1 = {
                        tableId: 'list4',
                        data: xldata,
                        colArr: colNamesArr,
                        multiselect: true,
                        width: 0.84,
                        height: 0.64
                    };
                    fullTable(config1);//加载空表格

                    trDBLClick();

                });

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "Remark",
                            "FieldOpt": "like",
                            "FieldVal": "%" + excludeSpecial(o.condition)+ "%"
                        }, {
                            "FieldName": "PurChCode",
                            "FieldOpt": "Order BY"
                        }, {
                            "FieldName": "CIEFlag",
                            "FieldOpt": "=",
                            "FieldVal": "00"
                        }, {
                            "FieldName": "SStatus",
                            "FieldOpt": "=",
                            "FieldVal": "01"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };

                var xldata = [];
                //加载采购单信息
                request({
                    url: "/Purch/GetAllPurchInfo",
                    data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}
                }, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PurChRd": datas[i].PurChRd,
                            "Remark": datas[i].Remark,
                            "PurChCode": datas[i].PurChCode
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

    $("#defaultSelect").zc_select(params);

    //加载页面
    var loadPage = function () {
        var trees = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        var InitData = {
            "FiledList": [
                {
                    "FieldName": "CreateTime DESC",
                    "FieldOpt": "order by"
                }
            ]
        };
        request({
            url: '/Ich/GetAllIChInfo',
            data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(pageInfo)}
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
                    id: treeData[i].InCRd == undefined ? "" : treeData[i].InCRd,
                    name: treeData[i].InCCode == undefined ? "" : treeData[i].InCCode
                };
                trees.push(tree);
            }
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        })
    }
    loadPage();

    var jiagongliao = function () {
        $(".AFeed").each(function () {
            if ($(this).text() == "00")
                $(this).html("是");
            else
                $(this).html("否");
        })
    };

    var getAllPDlInfo = function (InitData) {
        request({
            url: "/Purch/GetAllPDlInfo",
            data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
        }, function (Body) {
            // 填充表格
            var arr = [];
            var j = 0;
            for (var i in Body.Data) {
                if (Body.Data[i].UnCInCNum > 0) {
                    Body.Data[i].Num = Body.Data[i].UnCInCNum;
                    Body.Data[i].InCDlRd = Body.Data[i].PurChDlRd;
                    delete Body.Data[i].PurChDlRd;
                } else {
                    arr[j++] = i;
                }
            }
            for (var n = arr.length - 1; n >= 0; n--) {
                Body.Data.splice(arr[n], 1);
            }
            config1.data = Body.Data;
            fullTable(config1);
            jiagongliao();
        });
    }
    // 根据采购单号获取明细
    $("#PurChCode").on({
        "blur": function () {
            InitData.FiledList[0].FieldVal = $(this).val();
            getAllPDlInfo(InitData);
        },
        "keyup": function (e) {
            var code = e.keyCode;
            if (code == "13") {
                InitData.FiledList[0].FieldVal = $(this).val();
                var Body = getAllPDlInfo(InitData);
            }
        }
    })
    //菜单新增
    $('#addPurch').on('click', function () {
        $("#tab_4_li").css("display", "none").prev().addClass("active");
        $("#tab_4").removeClass("active").prev().addClass("active");
        $("#tab_5_li").css("display", "none").prev().prev().addClass("active");
        $("#tab_5").removeClass("active").prev().prev().addClass("active");
        $("#beizhu").attr("disabled", false);
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $(".inpSearch").attr("disabled", false);

        $("#maInfoButton").show();
        $("#SourceType").attr("disabled", false);
        $("#stat").hide();
        $("#status").hide();
        $("#status").text("");
        $("#sStat").hide();
        $("#sStatus").hide();
        $("#sStatus").text("");
        $("#_right").show();
        $("#defaultSelect").clearseldata("PurChCode");
        treeID = null;
        $('#InCCode').val('');
        //表格清空
        config1.data = [];
        config1.colArr = colNamesArr;
        fullTable(config1);//加载空表格
        $("#ExecType").val("00");
        $("#InCCode").attr("readonly", false);
    });

    // 删除
    $("#delete").on("click", function () {
        if (treeID == null) {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        } else {
            var busData = {
                "InCRd": treeID
            };
            request({
                url: "/Ich/SaveIChInfo",
                data: {"ExecType": "01", "BusData": JSON.stringify(busData)}
            }, function (Body) {
                layer.closeAll("dialog");
                toastr.success(Body.MsgDes);
                $("#_right").hide();
                condition = "";
                currentPage = 0;
                loadPage();
            });
        }
    });

    //保存
    $('#save').on('click', function () {
        //获取采购单号
        var PurChCode = $("#defaultSelect").getseldata().PurChCode;

        var InCCode = $('#InCCode').val();
        var PurMaData = getRowData("list4");

        if ($("#ExecType").val() === "00") {
            for (var i = 0; i < PurMaData.length; i++) {
                if (PurMaData[i].Num <= 0) {
                    toastr.warning(PurMaData[i].MaName + " 的收货数量不能小于等于0");
                    return;
                }

                //PurMaData[i].PurChDlRd = PurMaData[i].InCDlRd;
                delete PurMaData[i].InCDlRd;
                delete PurMaData[i].StoreName;
                delete PurMaData[i].MaCode;
                delete PurMaData[i].MaName;
                delete PurMaData[i].MaDes;

                delete PurMaData[i].TotalNum;
                delete PurMaData[i].TotalScanNum;
                delete PurMaData[i].UnCInCNum;
                delete PurMaData[i].ScanNum;
                PurMaData[i].AFeed = "01";
                /*=== "是" ? "00":"01"*/
                if (PurMaData[i].StoreRd == "undefined") {
                    PurMaData[i].StoreRd = "";
                }
            }

            var pucherInfos = {
                "PurChCode": PurChCode,
                "InCCode": InCCode,
                "SourceType": $("#SourceType").val(),
                "IChMaInfo": PurMaData,
                "Remark": $("#beizhu").val()
            };
            request({
                url: "/Ich/SaveIChInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(pucherInfos)}
            }, function (Body) {
                toastr.success("来料收货信息新增成功,来料收货通知单号{" + Body.Data.InCCode + "}");
                $("#ExecType").val('');
                condition = "";
                currentPage = 0;
                loadPage();
                $("#hidden1").attr("editid", Body.Data.InCRd);
                treeID = $("#hidden1").attr("editid");
                $("#hidden2").attr("editcode", Body.Data.InCCode);
                $("#stat").show();
                $("#status").show();
                $("#sStat").show();
                $("#sStatus").show();
                var objBusData = JSON.stringify({"InCRd": treeID});
                var objData = {
                    "ExecType": "00",
                    "BusData": objBusData
                };
                request({url: "/Ich/GetIncInfo", data: objData}, function (Body) {
                    $("#defaultSelect").showData({
                        id: Body.Data.PurChCode,
                        name: Body.Data.PurChCode,
                        keyfield: "PurChRd",
                        fields: [
                            {"name": "PurChRd"},
                            {"name": "PurChCode"}
                        ]
                    });
                    $("#InCCode").val(Body.Data.InCCode);
                    var IncDlInfo = Body.Data.IncDlInfo;
                    var str = "";
                    if ("00" == Body.Data.Status) {
                        str = "待处理";
                    }
                    if ("01" == Body.Data.Status) {
                        str = "进行中";
                    }
                    if ("02" == Body.Data.Status) {
                        str = "已完成";
                    }
                    if ("03" == Body.Data.Status) {
                        str = "已取消";
                    }
                    $("#status").text(str);
                    $("#status").attr("exectype", Body.Data.Status);
                    var sStr = "";
                    if ("00" == Body.Data.SStatus) {
                        sStr = "未下达";
                    } else if ("01" == Body.Data.SStatus) {
                        sStr = "已下达";
                        b = false;
                        config1.colArr = colNamesArr1;
                        isEdit(false);
                    } else if ("02" == Body.Data.SStatus) {
                        sStr = "已取消";
                        b = false;
                        config1.colArr = colNamesArr1;
                        isEdit(false);
                    }
                    $("#sStatus").text(sStr);
                    $("#sStatus").attr("exectype", Body.Data.SStatus);
                    $("#InCCode").attr("readonly", true);
                    $("#SourceType").attr("disabled", true);

                    $("#creatPeople").val(Body.Data.Creator);
                    $("#creatTime").val(Body.Data.CreateTime);
                    $("#modifyPeople").val(Body.Data.LastModifyMan);
                    $("#modifyTime").val(Body.Data.LastModifyTime);
                    $("#beizhu").val(Body.Data.Remark);

                    var IncDlInfos = [];
                    for (var i = 0; i < IncDlInfo.length; i++) {
                        var Info = {
                            "InCDlRd": IncDlInfo[i].InCDlRd,
                            "PurChDlRd": IncDlInfo[i].PurChDlRd,
                            "MaVerRd": IncDlInfo[i].MaInfo.MaVerRd,
                            "MaCode": IncDlInfo[i].MaInfo.MaCode,
                            "MaName": IncDlInfo[i].MaInfo.MaName,
                            "MaDes": IncDlInfo[i].MaInfo.MaDes,
                            "Num": IncDlInfo[i].Num,
                            "UnitName": IncDlInfo[i].UnitName,
                            "AFeed": IncDlInfo[i].AFeed === "00" ? "是" : "否",
                            "StoreRd": IncDlInfo[i].StoreInfo.StoreRd,
                            "StoreName": IncDlInfo[i].StoreInfo.StoreName,
                            "Remark": IncDlInfo[i].Remark,
                            "TotalNum": IncDlInfo[i].TotalNum,
                            "TotalScanNum": IncDlInfo[i].TotalScanNum,
                            "UnCInCNum": IncDlInfo[i].UnCInCNum,
                            "ScanNum": IncDlInfo[i].ScanNum
                        }
                        IncDlInfos.push(Info);
                    }
                    config1.data = IncDlInfos;
                    fullTable(config1);
                });
            });
        }
        else if (treeID != null) {
            for (var i = 0; i < PurMaData.length; i++) {
                if (PurMaData[i].Num <= 0) {
                    toastr.warning(PurMaData[i].MaName + " 的收货数量不能小于等于0");
                    return;
                }
                //delete PurMaData[i].AFeed;
                //delete PurMaData[i].StoreRd;
                delete PurMaData[i].StoreName;
                //delete PurMaData[i].MaVerRd;
                delete PurMaData[i].MaCode;
                delete PurMaData[i].MaName;
                delete PurMaData[i].MaDes;
                //delete PurMaData[i].UnitName;

                delete PurMaData[i].TotalNum;
                delete PurMaData[i].TotalScanNum;
                delete PurMaData[i].UnCInCNum;
                delete PurMaData[i].ScanNum;
                PurMaData[i].AFeed = "01";
            }

            var pucherInfos = {
                "InCRd": treeID,
                "IChMaInfo": PurMaData,
                "Remark": $("#beizhu").val()
            };

            request({
                url: "/Ich/SaveIChInfo",
                data: {"ExecType": "02", "BusData": JSON.stringify(pucherInfos)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val('');
                condition = "";
                currentPage = 0;
                loadPage();
            });
        }
    });

    $("#SourceType").change(function () {
        if ($(this).val() == "00") {
            $("#defaultSelect_").css("display", "");
        } else {
            $("#defaultSelect_").css("display", "none");
        }
        $("#tab_4_li").css("display", "none").prev().addClass("active");
        $("#tab_4").removeClass("active").prev().addClass("active");
        $("#tab_5_li").css("display", "none").prev().addClass("active");
        $("#tab_5").removeClass("active").prev().addClass("active");

        deallrow("list4");
    });

    //新增(表格)
    $(".add1").on("click", function () {
        $("#maInfoButton_2").css("display", "inline-block");
        $("#maInfoButton_1").css("display", "inline-block");
        editStatus = false;
        if ("00" == $("#SourceType").val()) {
            loadMa();
            $("#tab_5_li").css("display", "none").prev().addClass("active");
            $("#tab_5").removeClass("active").prev().addClass("active");
            $("#tab_4_li").css("display", "block").addClass("active").siblings().removeClass("active");
            $("#tab_4").addClass("active").siblings().removeClass("active");
            $("#allNum_1").val("");
            $("#scanNum_1").val("");
            $("#unCInCNum_1").val("");
            $("#num_1").val("");
            $("#remark_1").val("");
            $("#totalScanNum").val("");
            //$("#storeName").clearseldata();
            $("#storeName").showData({
                id: "",
                name: "",
                keyfield: "StoreRd",
                fields: [
                    {"name": "StoreRd"},
                    {"name": "StoreName"}
                ]
            });
        } else {
            $("#tab_4_li").css("display", "none").prev().addClass("active");
            $("#tab_4").removeClass("active").prev().addClass("active");
            $("#tab_5_li").css("display", "block").addClass("active").siblings().removeClass("active");
            $("#tab_5").addClass("active").siblings().removeClass("active");
            //$("#num_1").val("");
            $("#maName_2").clearseldata();
            $("#storeName_1").clearseldata();
            $("#num_2").val("");
            $("#scanNum_2").val("");
            $("#remark_2").val("");
        }
    });

    function loadMa() {
        $("#maName_1").text("");
        var params_1 = {
            "displaymode": "1",
            "title": "物料",
            "binddata": {
                "keyfield": "MaVerRd",
                "fields": [
                    {
                        "caption": "采购明细",
                        "name": "PurChDtlRd",
                        "hidden": true
                    },
                    {
                        "caption": "物料id",
                        "name": "MaVerRd"
                    }, {
                        "caption": "物料代码",
                        "name": "MaCode"
                    }, {
                        "caption": "物料名称",
                        "name": "MaName"
                    }, {
                        "caption": "物料描述",
                        "name": "MaDes"
                    }, {
                        "caption": "采购数量",
                        "name": "Num",
                        "hidden": true
                    }, {
                        "caption": "已收货数量",
                        "name": "ScanNum",
                        "hidden": true
                    }, {
                        "caption": "未开单量",
                        "name": "UnCInCNum",
                        "hidden": true
                    }, {
                        "caption": "是否甲供料",
                        "name": "Afeed",
                        "hidden": true
                    }, {
                        "caption": "收料仓库ID",
                        "name": "StoreRd",
                        "hidden": true
                    }, {
                        "caption": "收料仓库",
                        "name": "StoreName",
                        "hidden": true
                    }, {
                        "caption": "单位",
                        "name": "UnitName",
                        "hidden": true
                    }
                ]
            },
            "showresult": {
                "ishead": true
            },
            "event": {
                "onformatval": function (data) {
                    return data.MaCode + "-" + data.MaName;
                },
                "onclick": function (res) {
                    $("#allNum_1").val(res.Num);
                    $("#scanNum_1").val(res.ScanNum);
                    $("#unCInCNum_1").val(res.UnCInCNum);
                    $("#totalScanNum").val(0);
                },
                "onseardata": function (o) {
                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''))",
                                "FieldOpt": "like",
                                "FieldVal": "%" + excludeSpecial(o.condition) + "%"
                            }
                        ]
                    };
                    var page = {
                        PageIndex: "0",
                        PageSize: o.num
                    };

                    var xldata = [], purChCode_ = $("#defaultSelect").getseldata().PurChCode;
                    //加载采购单信息
                    request({
                        url: "/Ich/GetWIncMaInfo", data: {
                            "ExecType": "00", /*"InitData":JSON.stringify(InitData),*/
                            "BusData": "{\"PurChCode\":\"" + purChCode_ + "\"}", "PageInfo": JSON.stringify(page)
                        }
                    }, function (Body) {
                        var datas = Body.Data;

                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "PurChDtlRd": datas[i].PurChDtlRd,
                                "MaVerRd": datas[i].MaInfo.MaVerRd,
                                "MaCode": datas[i].MaInfo.MaCode,
                                "MaName": datas[i].MaInfo.MaName,
                                "MaDes": datas[i].MaInfo.MaDes,
                                "Num": datas[i].Num,
                                "ScanNum": datas[i].ScanNum,
                                "UnCInCNum": datas[i].UnCInCNum,
                                "Afeed": datas[i].Afeed,
                                "StoreRd": datas[i].StoreInfo.StoreRd,
                                "StoreName": datas[i].StoreInfo.StoreName,
                                "UnitName": datas[i].UnitName,
                                "Remark": datas[i].Remark
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
        $("#maName_1").zc_select(params_1);
    }

    //物料的搜索
    var params_2 = {
        "displaymode": "1",
        "title": "物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "物料id",
                    "name": "MaVerRd"
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
        "event": {
            "onformatval": function (data) {
                return data.MaCode + "-" + data.MaName;
            },
            "onseardata": function (o) {
                var _InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        }, {
                            "FieldName": "MaterialType",
                            "FieldOpt": "in",
                            "FieldVal": "(02,03)"
                        }
                    ]
                };
                var datas = [];
                Materialinfo(_InitData, "", datas);
                var obj = {
                    data: datas,
                    showrow: 500
                };
                return obj;
            },
            "onclick": function (res) {
                request({
                    url: "/Commom/GetCMWFInfo",
                    async: true,
                    data: {"ExecType": "00", "BusData": JSON.stringify({"MVerRd": res.MaVerRd})}
                }, function (Body) {
                    $("#unitName").val(Body.Data.MaInfo.UnitInfo == null ? "" : Body.Data.MaInfo.UnitInfo.UnitName);
                    $("#scanNum_2").val(0);
                })
            }
        }
    };
    $("#maName_2").zc_select(params_2);

    //收料仓的搜索
    var params_store = {
        "displaymode": "0",
        "title": "收料仓",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "收料仓id",
                    "name": "StoreRd"
                }, {
                    "caption": "收料仓名称",
                    "name": "StoreName"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var _InitData = {
                    "FiledList": [
                        {
                            "FieldName": "StoreName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var datas = [];
                request({
                    url: "/Store/GetAllStoreInfo",
                    data: {"ExecType": "00", "InitData": JSON.stringify(_InitData)}
                }, function (Body) {
                    for (var i in Body.Data) {
                        var data = {
                            "StoreRd": Body.Data[i].StoreRd,
                            "StoreName": Body.Data[i].StoreName
                        };
                        datas.push(data);
                    }
                });
                var obj = {
                    data: datas,
                    showrow: 500
                };
                return obj;
            }
        }
    };
    $("#storeName").zc_select(params_store);
    $("#storeName_1").zc_select(params_store);

    //明细上面的X
    $(".XH").on("click", function () {
        $("#tab_4_li").css("display", "none").prev().addClass("active");
        $("#tab_4").removeClass("active").prev().addClass("active");
        $("#tab_5_li").css("display", "none").prev().prev().addClass("active");
        $("#tab_5").removeClass("active").prev().prev().addClass("active");
    });

    //表格删除
    $('.del1').on('click', function () {
        derow('list4');
    });

    //是否是编辑状态
    var editStatus = false;
    var nowclickData={
        "MaVerRd":""
    };
    //明细设置确认
    $("#add_1").click(function () {
        var data_ = $("#maName_1").getseldata();
        var num_ = Number($("#num_1").val());
        if (num_ == undefined || num_ <= 0) {
            toastr.warning("收货数量不能为空或小于等于零");
            return;
        }
        /*if(num_ > Number($("#unCInCNum_1").val())){
            toastr.warning("收货数量不能大于未开单量");
            return;
        }*/

        var obj = {
            "PurChDlRd": data_.PurChDtlRd,
            "MaVerRd": data_.MaVerRd,
            "MaCode": data_.MaCode,
            "MaName": data_.MaName,
            "MaDes": data_.MaDes,
            "Num": num_,
            "UnitName": data_.UnitName,
            "AFeed": data_.AFeed === "00" ? "是" : "否",
            "StoreRd": data_.StoreRd,
            "StoreName": data_.StoreName,
            "Remark": $("#remark_1").val(),
            "TotalNum": $("#allNum_1").val(),
            "TotalScanNum": $("#totalScanNum").val(),
            "UnCInCNum": $("#unCInCNum_1").val(),
            "ScanNum": $("#scanNum_1").val()
        };

        if (!editStatus) {
            var d = getTableData("list4");
            for (var i = 0; i < d.length; i++) {
                if (d[i].MaCode == data_.MaCode) {
                    toastr.warning("物料不能重复添加");
                    return;
                }
            }
            addSrow("list4", obj, "first");
        } else {
            var d = getTableData("list4");
            for (var i = 0; i < d.length; i++) {
                if (d[i].MaCode == data_.MaCode) {
                    toastr.warning("物料不能重复添加");
                    return;
                }
            }
            $("#list4").find("tbody tr").each(function () {
                if ($(this).find(".MaVerRd").text() == nowclickData.MaVerRd) {
                    $(this).find(".MaVerRd").html(obj.MaVerRd);
                    $(this).find(".MaCode").html(obj.MaCode);
                    $(this).find(".MaName").html(obj.MaName);
                    $(this).find(".MaDes").html(obj.MaDes);
                    $(this).find("input[name='Num']").val(obj.Num);
                    //$(this).find(".AFeed").html(tab_TR.AFeed);
                    $(this).find(".UnitName").html(obj.UnitName);
                    $(this).find(".StoreRd").html(obj.StoreRd);
                    $(this).find(".StoreName").html(obj.StoreName);
                    $(this).find(".Remark").html(obj.Remark);
                    $(this).find(".TotalNum").html(obj.TotalNum);
                    $(this).find(".TotalScanNum").html(obj.TotalScanNum);
                    $(this).find(".UnCInCNum").html(obj.UnCInCNum);
                    $(this).find(".ScanNum").html(obj.ScanNum);
                    return false;
                }
            });
        }

        /* //隐藏当前tab
         $("#tab_4_li").css("display","none").prev().addClass("active");
         $("#tab_4").removeClass("active").prev().addClass("active");*/
        loadMa();
        $("#tab_5_li").css("display", "none").prev().addClass("active");
        $("#tab_5").removeClass("active").prev().addClass("active");
        $("#tab_4_li").css("display", "block").addClass("active").siblings().removeClass("active");
        $("#tab_4").addClass("active").siblings().removeClass("active");
        $("#allNum_1").val("");
        $("#scanNum_1").val("");
        $("#unCInCNum_1").val("");
        $("#num_1").val("");
        $("#remark_1").val("");
        $("#totalScanNum").val("");
        //$("#storeName").clearseldata();
        $("#storeName").showData({
            id: "",
            name: "",
            keyfield: "StoreRd",
            fields: [
                {"name": "StoreRd"},
                {"name": "StoreName"}
            ]
        });

        trDBLClick();
        editStatus = false;
    });
    $("#add_2").click(function () {
        var data_ = $("#maName_2").getseldata();

        var num_ = $("#num_2").val();
        if (num_ == undefined || num_ <= 0) {
            toastr.warning("收货数量不能为空或小于等于零");
            return;
        }

        var storeRd_ = $("#storeName").getseldata().StoreRd;
        var storeName_ = $("#storeName").getseldata().StoreRd;
        if (storeRd_ == undefined || storeName_ == undefined) {
            toastr.warning("收货仓库不能为空");
            return;
        }
        var obj = {
            "InCDlRd": "",
            "PurChDlRd": "",
            "MaVerRd": data_.MaVerRd,
            "MaCode": data_.MaCode,
            "MaName": data_.MaName,
            "MaDes": data_.MaDes,
            "Num": num_,
            "UnitName": $("#unitName").val(),
            "AFeed": $("#afeed_").val() == "00" ? "是" : "否",
            "StoreRd": $("#storeName").getseldata().StoreRd,
            "StoreName": $("#storeName").getseldata().StoreName,
            "Remark": $("#remark_2").val(),
            "TotalNum": 0,
            "TotalScanNum": 0,
            "UnCInCNum": 0,
            "ScanNum": $("#scanNum_2").val()
        };

        if (!editStatus) {
            var d = getTableData("list4");
            for (var i = 0; i < d.length; i++) {
                if (d[i].MaCode == data_.MaCode) {
                    toastr.warning("物料不能重复添加");
                    return;
                }
            }
            addSrow("list4", obj, "first");
        } else {
            $("#list4").find("tbody tr").each(function () {
                if ($(this).find(".MaVerRd").text() == obj.MaVerRd) {
                    $(this).find(".MaVerRd").html(obj.MaVerRd);
                    $(this).find(".MaCode").html(obj.MaCode);
                    $(this).find(".MaName").html(obj.MaName);
                    $(this).find(".MaDes").html(obj.MaDes);
                    $(this).find("input[name='Num']").val(obj.Num);
                    //$(this).find(".AFeed").html(tab_TR.AFeed);
                    $(this).find(".UnitName").html(obj.UnitName);
                    $(this).find(".StoreRd").html(obj.StoreRd);
                    $(this).find(".StoreName").html(obj.StoreName);
                    $(this).find(".Remark").html(obj.Remark);
                    $(this).find(".TotalNum").html(obj.TotalNum);
                    $(this).find(".TotalScanNum").html(obj.TotalScanNum);
                    $(this).find(".UnCInCNum").html(obj.UnCInCNum);
                    $(this).find(".ScanNum").html(obj.ScanNum);
                    return false;
                }
            });
        }
        /*//隐藏当前tab
        $("#tab_5_li").css("display","none").prev().prev().addClass("active");
        $("#tab_5").removeClass("active").prev().prev().addClass("active");*/
        $("#tab_4_li").css("display", "none").prev().addClass("active");
        $("#tab_4").removeClass("active").prev().addClass("active");
        $("#tab_5_li").css("display", "block").addClass("active").siblings().removeClass("active");
        $("#tab_5").addClass("active").siblings().removeClass("active");
        //$("#num_1").val("");
        $("#maName_2").clearseldata();
        $("#storeName_1").clearseldata();
        $("#num_2").val("");
        $("#scanNum_2").val("");
        $("#remark_2").val("");
        trDBLClick();
    });

    $("#addOn").click(function () {
        if (treeID == null) {
            toastr.warning("请选择左侧要下达的一项再进行下达!");
        } else {
            var busData = {
                "InCRd": treeID
            };
            saveICH("03", busData);
        }
    });
    $("#addOff").click(function () {
        var status_ = $("#status").attr("exectype");
        var sstatus_ = $("#sStatus").attr("exectype");
        var msg = "确定要取消吗?";
        if (sstatus_ == "01") {
            if (status_ == "01") {
                msg = "目前该单处于收货中，是否强制取消?";
            } else if (status_ == "02") {
                toastr.warning("来料单已完成不能取消!");
                return;
            }
        }

        if (treeID == null) {
            toastr.warning("请选择左侧要取消的一项再进行取消!");
        } else {
            layer.confirm(msg, {
                btn: ["确认", "取消"]
            }, function () {
                var busData = {
                    "InCRd": treeID
                };
                saveICH("04", busData);
            })
        }
    });

    function saveICH(execType, obj) {
        request({
            url: "/Ich/SaveIChInfo",
            data: {"ExecType": execType, "BusData": JSON.stringify(obj)}
        }, function (Body) {
            layer.closeAll("dialog");
            toastr.success(Body.MsgDes);
            condition = "";
            currentPage = 0;
            var objData = {
                "ExecType": "00",
                "BusData": JSON.stringify(obj)
            };
            loadData(objData);
        });
    }

    //筛选
    var params = [{
        "caption": "采购单号",
        "name": "PurChCode",
        "valtype": "00"
    }, {
        "caption": "来料收货通知单号",
        "name": "InCCode",
        "valtype": "00"
    }, {
        "caption": "状态",
        "name": "Status",
        "valtype": "01",
        "data": "\"00\":\"待处理\"|\"01\":\"处理中\"|\"02\":\"已完成\""
    }, {
        "caption": "时间段",
        "name": "CreateTime",
        "valtype": "02"
    }];
    var InitData1 = {};
    var event = {
        onsure: function (result) {
            var FiledList = [];
            for (var i in result) {
                for (var j in result[i]) {
                    if (result[i][j].trim() != "" && result[i][j] != null) {
                        if (j == "CreateTime") {
                            var Filed = {
                                "FieldName": j,
                                "FieldOpt": ">=",
                                "FieldVal": result[i][j].split("|")[0]
                            };
                            FiledList.push(Filed);
                            Filed = {
                                "FieldName": j,
                                "FieldOpt": "<=",
                                "FieldVal": result[i][j].split("|")[1] + " 23:59:59"
                            };
                            FiledList.push(Filed);
                            break;
                        }
                        var Filed = {
                            "FieldName": j,
                            "FieldOpt": "like",
                            "FieldVal": "%" + result[i][j] + "%"
                        };
                        FiledList.push(Filed);
                    }
                }
            }
            InitData1 = {
                "FiledList": FiledList
            };

            var trees = [];

            request({
                url: '/Ich/GetAllIChInfo',
                data: {"ExecType": "00", "InitData": JSON.stringify(InitData1)}
            }, function (Body) {
                currentPage = 0;
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20 : treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].InCRd == undefined ? "" : treeData[i].InCRd,
                        name: treeData[i].InCCode == undefined ? "" : treeData[i].InCCode
                    }
                    trees.push(tree);
                }
                config.data.source = trees;
                $.JstreeEx.init(config);//先调用后加载
            });
            delete InitData1.FiledList['InCCode'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);

    //是否允许编辑
    function isEdit(is) {
        if (is) {
            $("#maInfoButton").show();
        } else {
            $("#maInfoButton").hide();
        }
    }

    trDBLClick();

    function trDBLClick() {
        $("#list4").find("tbody").on("dblclick", "tr", function () {
            if ($("#sStatus").text() == "已取消" || $("#sStatus").text() == "已下达") {
                $("#maInfoButton_2").css("display", "none");
                $("#maInfoButton_1").css("display", "none");
            } else {
                $("#maInfoButton_2").css("display", "");
                $("#maInfoButton_1").css("display", "");

            }
            editStatus = true;
            nowclickData.MaVerRd=$(this).find(".MaVerRd").text();
            //获取当前双击表格的数据
            var _MaVerRd = $(this).find(".MaVerRd").text();
            var _MaName = $(this).find(".MaName").text();
            var _MaCode = $(this).find(".MaCode").text();
            var _MaDes = $(this).find(".MaDes").text();
            var _StoreRd = $(this).find(".StoreRd").text();
            var _StoreName = $(this).find(".StoreName").text();
            var _Num = $(this).find("input[name='Num']").val();
            var _ScanNum = $(this).find(".ScanNum").text();
            var _Remark = $(this).find(".Remark").text();

            if ("00" == $("#SourceType").val()) {
                loadMa();
                $("#tab_5_li").css("display", "none").prev().addClass("active");
                $("#tab_5").removeClass("active").prev().addClass("active");
                $("#tab_4_li").css("display", "block").addClass("active").siblings().removeClass("active");
                $("#tab_4").addClass("active").siblings().removeClass("active");

                var _TotalNum = $(this).find(".TotalNum").text();
                var _TotalScanNum = $(this).find(".TotalScanNum").text();
                var _UnCInCNum = $(this).find(".UnCInCNum").text();
                $("#allNum_1").val(_TotalNum);
                $("#totalScanNum").val(_TotalScanNum);
                $("#unCInCNum_1").val(_UnCInCNum);
                $("#num_1").val(_Num);
                $("#scanNum_1").val(_ScanNum);
                $("#remark_1").val(_Remark);

                $("#maName_1").showData({
                    id: _MaVerRd,
                    name: _MaName,
                    keyfield: "MaVerRd",
                    fields: [
                        {"name": "MaVerRd"},
                        {"name": "MaName"}
                    ]
                });
                $("#storeName").showData({
                    id: _StoreRd,
                    name: _StoreName,
                    keyfield: "StoreRd",
                    fields: [
                        {"name": "StoreRd"},
                        {"name": "StoreName"}
                    ]
                });
            } else {
                $("#tab_4_li").css("display", "none").prev().addClass("active");
                $("#tab_4").removeClass("active").prev().addClass("active");
                $("#tab_5_li").css("display", "block").addClass("active").siblings().removeClass("active");
                $("#tab_5").addClass("active").siblings().removeClass("active");
                //$("#num_1").val("");

                $("#allNum_1").val(0);
                $("#totalScanNum").val(0);
                $("#unCInCNum_1").val(0);
                $("#num_2").val(_Num);
                $("#scanNum_2").val(_ScanNum);
                $("#remark_2").val(_Remark);

                $("#maName_2").showData({
                    id: _MaVerRd,
                    name: _MaName,
                    keyfield: "MaVerRd",
                    fields: [
                        {"name": "MaVerRd"},
                        {"name": "MaName"}
                    ]
                });
                $("#storeName_1").showData({
                    id: _StoreRd,
                    name: _StoreName,
                    keyfield: "StoreRd",
                    fields: [
                        {"name": "StoreRd"},
                        {"name": "StoreName"}
                    ]
                });
            }
        });
    }
});