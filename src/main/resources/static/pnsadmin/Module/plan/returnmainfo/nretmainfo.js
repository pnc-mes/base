$(function () {
    var treeID = null;
    var kuwei;//定义库位信息
    var trObj = null;//定义表格行数据

    //树的点击事件
    var onClicks = function (nodeinfo, handle) {
        $(".right").show();
        treeID = nodeinfo.nodeID;
        $("#tuiLOrder").attr("readonly", true);
        $("#sb").css("display","none");
        $(".tladd").hide();
        $(".tldel").hide();
        $("#cangku").attr("disabled",true);
        var BusData = {"RetRd": nodeinfo.nodeID};
        request({
            url: "/RetMa/GetNRMInfo",
            data: {"ExecType": "00", "BusData": JSON.stringify(BusData)}
        }, function (Body) {
            $("#tuiLOrder").val(Body.Data.RetCode);
            $cangku.find("option").each(function () {
                if ($(this).attr("id") == Body.Data.StoreInfo.StoreRd) {
                    $(this).prop("selected", true);
                    return false;
                }
            });

            $("#ExecType").val("");
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            for (var i in Body.Data.RetDlInfo) {
                if (Body.Data.RetDlInfo[i].LName == null)
                    Body.Data.RetDlInfo[i].LName = "";
            }
            config2.data = Body.Data.RetDlInfo;

            fullTable(config2);
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
        //定义事件获取点击的值
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
    var list = [];
    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/RetMa/GetAllNRMInfo", "RetRd", "RetCode", "RetCode", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function(event) {

        if (event.keyCode == 13) {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/RetMa/GetAllNRMInfo", "RetRd", "RetCode", "RetCode", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/RetMa/GetAllNRMInfo", "RetRd", "RetCode", "RetCode", condition, currentPage, config, list);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/RetMa/GetAllNRMInfo", "RetRd", "RetCode", "RetCode", condition, currentPage, config, list);
    });
    $("#_right").hide();
    //默认加载表头
    var colNamesArr = [
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false, Width: 100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
        {"Caption": "批次号", "Name": "Batch", "Editable": false},
        {"Caption": "批次数量", "Name": "Num", "Editable": false, Width: 80},
        {"Caption": "仓库", "Name": "StoreName", "Editable": false, Width: 80},
        {"Caption": "LRd", "Name": "LRd", "Hidden": true},
        {"Caption": "InSStatus", "Name": "InSStatus", "Hidden": true},
        {"Caption": "库位", "Name": "LName", "Editable": false, Width: 80},
        {"Caption": "退料数量", "Name": "SRetNum", "Editable": true, "CType": "text", Width: 80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false, Width: 50}
    ];
    var config2 = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height: 0.45
    };
    fullTable(config2);

    var $cangku = $("#cangku");
    request({url: '/Store/GetAllStoreInfo', data: {"ExecType": "00"}}, function (Body) {
        for (var i in Body.Data) {
            $cangku.append("<option id='" + Body.Data[i].StoreRd + "' value='" + Body.Data[i].StoreName + "'>" + Body.Data[i].StoreName + "</option>")
        }
    });
    $("#cangku").on("change", function () {
        $("#wuliao").clearseldata("MaVerRd");
        $("#kuwei").clearseldata("LRd");
        $("#pici").clearseldata("Batch");
        /*$("#kuwei").css("display", "none").prev().css("display", "none");*/
        config2.data = [];
        fullTable(config2);
        trObj = null;
    });
    var lrd;
    var lname;
    //库位
    var params3 = {
        "displaymode": "0",
        "title": "库位",
        "binddata": {
            "keyfield": "LRd",
            "fields": [
                {
                    "caption": "id",
                    "name": "LRd"
                }, {
                    "caption": "名称",
                    "name": "LName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                lrd=res.LRd;
                lname=res.LName;
                trObj.LName = res.LName;
                trObj.LRd = res.LRd;
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "LName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },
                        {
                            "FieldName": "StoreGd",
                            "FieldOpt": "like",
                            "FieldVal": $cangku.find("option:selected").attr("id")
                        },
                        {
                            "FieldName": "LName",
                            "FieldOpt": "order by",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url: "/Store/GetAllLInfo"
                };
                var xldata = [];
                request(obj, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "LRd": datas[i].LRd,
                            "LName": datas[i].LName
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
    $("#kuwei").zc_select(params3);
    //$("#kuwei").css("display","none").prev().css("display","none");
    var zaiku = "00";//00:在库
    $("#zaiku").on("click", function () {
        if ($(this).prop("checked")) {
            zaiku = "00";
        } else {
            zaiku = "";
        }
        $("#pici").clearseldata("rd");
    });
    var datass = "";
    var tuiliaoTrList = [];
    //定义物料的加载
    var params = {
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
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (red) {
                var unitname="";
                request({
                    url: "/Material/GetMaInfo",
                    data: {"ExecType": "01", "BusData": JSON.stringify({"MaVerRd": red.MaVerRd})}
                }, function (Body) {
                    datass = Body.Data.IsBatch;
                    unitname=Body.Data.UnitInfo.UnitName;
                });
                if (datass == "01") {
                    $("#pici").css("display", "none").prev().css("display", "none");
                    var busData = {
                        "MaVerRd": red.MaVerRd,
                        "StoreRd": $cangku.find("option:selected").attr("id"),
                        "IsStore": zaiku
                    };
                    var obj = {
                        data: {
                            "ExecType": "00",
                            "BusData": JSON.stringify(busData)
                        },
                        url: "/RetMa/GetNRMTotalInfo"
                    };
                    var datas=[];
                    request(obj, function (Body) {
                        datas = Body.Data.Bnfo;
                        kuwei=Body.Data;
                        tuiliaoTrList = datas;
                    });

                    if(datas.length>0){
                        for (var i in datas) {
                            trObj = {
                                "MaVerRd": kuwei.MaVerRd,
                                "MaCode": kuwei.MaCode,
                                "MaName": kuwei.MaName,
                                "MaDes": kuwei.MaDes,
                                "Batch":"",
                                "Num":"",
                                "StoreName": datas[i].StoreInfo.StoreName == null ? $cangku.val() : datas[i].StoreInfo.StoreName,
                                "InSStatus":"00",
                                "LName": datas[i].LInfo.LName == null ? "" : datas[i].LInfo.LName,
                                "LRd": datas[i].LInfo.LRd,
                                "SRetNum": datas[i].Num,
                                "UnitName": datas[i].UnitName
                            }
                        }
                    }else{
                            trObj = {
                                "MaVerRd": kuwei.MaVerRd,
                                "MaCode": kuwei.MaCode,
                                "MaName": kuwei.MaName,
                                "MaDes": kuwei.MaDes,
                                "Batch": "",
                                "Num": "",
                                "StoreName": $cangku.val(),
                                "InSStatus": "02",
                                "LName": lname == undefined ? "" : lname,
                                "LRd": lrd== undefined ? "" : lrd,
                                "SRetNum": "",
                                "UnitName": unitname
                            }
                    }

                } else {
                    $("#pici").css("display", "inline-block").prev().css("display", "inline-block");
                }
               /* $("#pici").clearseldata("rd");
                $("#kuwei").clearseldata("LRd");*/
            },
            "onformatval": function (data) {
                console.log(data);
                return data.MaCode + "-" + data.MaName;
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        }, {
                            "FieldName": "Status",
                            "FieldOpt": "=",
                            "FieldVal": "00"
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

            }
        }
    };
    $("#wuliao").zc_select(params);

    //定义批次的加载
    var params1 = {
        "displaymode": "0",
        "title": "批次",
        "binddata": {
            "keyfield": "rd",
            "fields": [
                {
                    "caption": "",
                    "name": "rd"
                }, {
                    "caption": "批次",
                    "name": "Batch"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                var batch = $("#pici").getseldata().Batch;
                for (var i in kuwei.Bnfo) {
                    if (kuwei.Bnfo[i].Batch == batch) {
                        if (kuwei.Bnfo[i].InSStatus == "00") {//在库，直接隐藏
                            $("#kuwei").css("display", "none").prev().css("display", "none");
                            $("#kuwei").clearseldata("LRd");
                        } else {
                            $("#kuwei").css("display", "inline-block").prev().css("display", "inline-block");
                        }
                        trObj = {
                            "MaVerRd": kuwei.MaVerRd,
                            "MaCode": kuwei.MaCode,
                            "MaDes": kuwei.MaDes,
                            "MaName": kuwei.MaName,
                            "Batch": kuwei.Bnfo[i].Batch,
                            "Num": kuwei.Bnfo[i].Num,
                            "StoreName": kuwei.Bnfo[i].StoreInfo.StoreName == null ? $cangku.val() : kuwei.Bnfo[i].StoreInfo.StoreName,
                            "InSStatus": kuwei.Bnfo[i].InSStatus,
                            "LName": kuwei.Bnfo[i].LInfo.LName == null ? "" : kuwei.Bnfo[i].LInfo.LName,
                            "LRd": kuwei.Bnfo[i].LInfo.LRd,
                            "SRetNum": kuwei.Bnfo[i].Num,
                            "UnitName": kuwei.Bnfo[i].UnitName
                        }
                    }
                }
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "Batch",
                            "FieldOpt": "like",
                            "FieldVal": o.condition
                        },
                        {
                            "FieldName": "Batch",
                            "FieldOpt": "order by",
                        }
                    ]
                };
                var busData = {
                    "MaVerRd": $("#wuliao").getseldata().MaVerRd,
                    "StoreRd": $cangku.find("option:selected").attr("id"),
                    "IsStore": zaiku
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data: {
                        "ExecType": "00", "InitData": JSON.stringify(InitData),
                        "PageInfo": JSON.stringify(page), "BusData": JSON.stringify(busData)
                    },
                    url: "/RetMa/GetNRMTotalInfo"
                };
                var xldata = [];
                request(obj, function (Body) {
                    var datas = Body.Data.Bnfo;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "rd": datas[i].Batch,
                            "Batch": datas[i].Batch
                        };
                        xldata.push(data);
                    }
                    kuwei = Body.Data;
                    tuiliaoTrList = datas;
                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    };
    $("#pici").zc_select(params1);
    //点击新增
    $(".tladd").on("click", function () {
        var tabData = getTableData("list4");
        var flag = true;
        if (trObj == null && datass == "00") {
            toastr.warning("没有选择物料和批次，无法添加");
            return;
        }

        if (datass == "00") {
            for (var i in tabData) {
                if (tabData[i].MaVerRd == trObj.MaVerRd && tabData[i].Batch == trObj.Batch) {
                    flag = false;
                    break;
                }
            }
        } else {
            for (var i in tabData) {
                if (tabData[i].MaVerRd == trObj.MaVerRd) {
                    flag = false;
                    break;
                }
            }
        }

        if (flag && trObj != null) {
            addSrow("list4", trObj);
        }
        if (!flag) {
            toastr.warning("该物料已经添加");
            return;
        }
    });

    //获取生产退料列表信息
    var loadPage = function () {
        var trees = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        request({
            url: '/RetMa/GetAllNRMInfo',
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CreateTime DESC",
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
            if (treeData.length <= 0) {
                return false;
            }

            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].RetRd == undefined ? "" : treeData[i].RetRd,
                    name: treeData[i].RetCode == undefined ? "" : treeData[i].RetCode
                };
                trees.push(tree);
            }

            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载

        })
    };
    loadPage();

    //新增
    $(".cAdd").click(function () {
        treeID=null;
        $(".right").show();
        $("#tuiLOrder").attr("readonly", false);
        $("#ExecType").val("00");
        $("#tuiLOrder").val("");
        /*$(".status").text("待执行");*/
        $("#wuliao").clearseldata("MaVerRd");
        $("#kuwei").clearseldata("LRd");
        $("#pici").clearseldata("rd");
        $("#beizhu").val("");
        config2.data = [];
        fullTable(config2);
        $("#sb").show();
        $(".tladd").show();
        $(".tldel").show();
        $("#cangku").attr("disabled",false);
        $("#pici").css("display", "none").prev().css("display", "none");
    });
    //保存
    $(".cSave").click(function () {

        var RetCode = $("#tuiLOrder").val();
        var StoreRd = $cangku.find("option:selected").attr("id");

        if(StoreRd==null||""==StoreRd){
            toastr.warning("保存失败，仓库不能为空");
            return false;
        }
        var RetDlInfo = getTableData("list4");
        if(RetDlInfo.length<=0||""==RetDlInfo||RetDlInfo==null){
            toastr.warning("保存失败，退料明细不能为空");
            return false;
        }
        //新增保存
        if ($("#ExecType").val() == "00") {


            for (var j in RetDlInfo) {
                var r = /^\d+$/;
                if(!r.test(RetDlInfo[j].SRetNum) || RetDlInfo[j].SRetNum==0){
                    toastr.warning("退料数量为大于0正整数");
                    return;
                }

                if(RetDlInfo[j].InSStatus != "00"){
                    RetDlInfo[j].InSStatus = "02";
                }

                for (var i in tuiliaoTrList) {
                    if (tuiliaoTrList[i].Batch == RetDlInfo[j].Batch) {
                        if (RetDlInfo[j].SRetNum <= 0) {
                            toastr.warning("第" + (j + 1) + "行，退料数量不能为空");
                            return false;
                        }
                        if (RetDlInfo[j].SRetNum > tuiliaoTrList[i].Num && RetDlInfo[j].InSStatus == "02") {
                            toastr.warning("第" + (j + 1) + "行，退料数量不能大于批次数量");
                            return false;
                        }
                    }
                }

                delete RetDlInfo[j].MaDes;
                delete RetDlInfo[j].StoreName;
                delete RetDlInfo[j].LName;
                delete RetDlInfo[j].Num;
            }
            var data = {
                "RetCode": RetCode,
                "StoreRd": StoreRd,
                "RetDlInfo": RetDlInfo
            };
            request({
                url: "/RetMa/SaveNRMInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(data)}
            }, function (Body) {
                $("#ExecType").val("");
                toastr.success("退料单信息新增成功,退料单号{" + Body.Data.RetCode + "}");
                loadPage();
                $("#sb").css("display","none");
                $(".tladd").hide();
                $(".tldel").hide();
                $("#cangku").attr("disabled",true);
                $("#hidden1").attr("editid", Body.Data.RetRd);
                $("#tuiLOrder").attr("readonly", true);
                treeID = $("#hidden1").attr("editid");
                var BusData = {"RetRd": treeID};
                request({
                    url: "/RetMa/GetNRMInfo",
                    data: {"ExecType": "00", "BusData": JSON.stringify(BusData)}
                }, function (Body) {
                    $("#tuiLOrder").val(Body.Data.RetCode);
                    $cangku.find("option").each(function () {
                        if ($(this).attr("id") == Body.Data.StoreInfo.StoreRd) {
                            $(this).prop("selected", true);
                            return false;
                        }
                    });

                    $("#ExecType").val("");
                    $("#creatPeople").val(Body.Data.Creator);
                    $("#creatTime").val(Body.Data.CreateTime);
                    $("#modifyPeople").val(Body.Data.LastModifyMan);
                    $("#modifyTime").val(Body.Data.LastModifyTime);
                    $("#beizhu").val(Body.Data.Remark);
                    for (var i in Body.Data.RetDlInfo) {
                        if (Body.Data.RetDlInfo[i].LName == null)
                            Body.Data.RetDlInfo[i].LName = "";
                    }
                    config2.data = Body.Data.RetDlInfo;
                    fullTable(config2);
                    trObj={};
                });
            });
        }
    });

    //表格的删除
    $(".tldel").click(function () {
        delTr("list4");
    });


    $("#import").on("click", function () {
        //弹窗
        layer.open({
            type: 2,
            title: '快速退料单导入',
            shadeClose: true,
            area: ['60%', '50%'],
            content: getBasePath() + "/RetMa/importPG"
        });
    });
    //导出
    $("#export").on('click', function () {
        if (treeID != "" && treeID != null) {
            layer.confirm('确认要导出该退料单信息？', {
                btn: ['确认', '取消']
            }, function () {
                layer.closeAll("dialog");
                var url = getBasePath() + "/RetMa/exportExcel";
                var data_ = "ExecType=00&BusData=" + treeID;
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

                            filename = "快速退料单清单表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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
        } else {
            toastr.warning("请选择要导出的退料单再进行导出操作");
        }
    });

    //筛选
    var params = [{
        "caption": "退料单号",
        "name": "RetCode",
        "valtype": "00"
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
            }
            PageInfo.PageIndex = currentPage;
            var trees = [];
            request({
                url: '/RetMa/GetAllNRMInfo',
                data: {
                    "ExecType": "00",
                    "InitData": JSON.stringify(InitData1)
                }
            }, function (Body) {
                currentPage = 0;
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20 : treeData.length;
                if (treeData.length <= 0) {
                    return false;
                }
                $(".right").show();

                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].RetRd == undefined ? "" : treeData[i].RetRd,
                        name: treeData[i].RetCode == undefined ? "" : treeData[i].RetCode
                    };
                    trees.push(tree);
                }

                config.data.source = trees;
                $.JstreeEx.init(config);//先调用后加载
            })
            delete InitData1.FiledList['RetCode'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);
});
