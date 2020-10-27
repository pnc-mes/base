$(function () {

    var date = new Date();
    this.year = date.getFullYear();
    this.month = parseInt(date.getMonth())   + parseInt(1) < 10 ? "0" + (date.getMonth() +  parseInt(1)) :parseInt(date.getMonth()) +  parseInt(1);
    this.date = parseInt(date.getDate()) <10 ?"0"+  parseInt(date.getDate()):  parseInt(date.getDate()) ;
    this.date1 =  parseInt(date.getDate()) -parseInt(1) <10 ? "0"+ (parseInt(date.getDate()) -  parseInt(1)) :parseInt(date.getDate()) -  parseInt(1);
    this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();


    //时间段
    var dateTime = this.year + "-" + this.month + "-" + this.date1 + " " + this.hour + ":" + this.minute + ":" + this.second + " - " + this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;


    var addid = 0;
    var totalnum = 0;
    //日期时间范围
    laydate.render({
        elem: '#test10'
        , type: 'datetime'
        , range: true
        , value: dateTime
    });

    //表格定义
    var colNamesArr = [
        {"Caption": "是否满箱", "Name": "isFull", "CType": "text", Width: 60},
        {"Caption": "是否混包", "Name": "mixedBag", "CType": "text", Width: 60},
        {"Caption": "流转状态", "Name": "torsionStatus", "CType": "text", Width: 100},
        {"Caption": "箱的guid", "Name": "packGd", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "xxx", "Name": "gradeName1", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "箱号", "Name": "packCode", "CType": "text", Width: 100},
        {"Caption": "物料代码", "Name": "maCode", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "物料名称", "Name": "maName", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "模板地址", "Name": "fileName", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "包装规则", "Name": "packName", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "功率", "Name": "powerGear", "CType": "text", Width: 70},
        {"Caption": "电流", "Name": "elgear", "CType": "text", Width: 40},
        {"Caption": "颜色", "Name": "colorGear", "CType": "text", Width: 50},
        {"Caption": "等级", "Name": "gradeName", "CType": "text", Width: 50},
        {"Caption": "包装数量", "Name": "num", "CType": "text", Width: 60},
        {"Caption": "备注", "Name": "remark", "CType": "text", Width: 110},
        {"Caption": "打包时间", "Name": "createTime", "CType": "text", Width: 150},
        {"Caption": "打包人", "Name": "creator", "CType": "text", Width: 60}
    ];
    var config = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.99,
        height: 0.3
    };
    fullTable(config);

    //加载包装规格
    request({url: "/PackSp/GetAllMPKInfo", data: {"ExecType": "00"}}, function (Body) {
        var packs = $("#packs");

        var option_ = "";
        Body.Data.forEach(function (obj) {
            option_ += "<option value='" + obj.MPRd + "'>" + obj.MPName + "</option>";
        });
        packs.empty().html("<option></option>" + option_);
    });

    //表格定义
    var colNamesArr5 = [
        {"Caption": "组件", "Name": "packCode", "CType": "text", Width: 100},
        {"Caption": "功率", "Name": "powerGear", "CType": "text", Width: 100},
        {"Caption": "电流", "Name": "elgear", "CType": "text"},
        {"Caption": "颜色", "Name": "colorGear", "CType": "text", Width: 50},
        {"Caption": "等级", "Name": "gradeName", "CType": "text"},

    ];
    var config5 = {
        tableId: "list5",
        data: [],
        colArr: colNamesArr5,
        width: 0.99,
        height: 0.3
    };
    fullTable(config5);
    var commonData = [];

    $.get(getBasePath() + "/HisPrintLog/SelectAllXiang", function (data) {
        App.unblockUI("body");
        commonData = data;

        $("#totalnum").text(commonData.length);
        $("#checktotalnum").text(0);
        var config = {
            tableId: "list4",
            data: data,
            colArr: colNamesArr,
            multiselect: true,
            width: 0.99,
            height: 0.3,
            event: {
                onrowselect: function (rowdatas) {
                    //console.log(rowdatas)
                    //alert(rowdatas.packGd)
                    for (var j in rowdatas) {
                        var packGd = rowdatas[j].packGd;
                        var data = {packGd: packGd};
                        $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {
                            var config5 = {
                                tableId: "list5",
                                data: data,
                                colArr: colNamesArr5,
                                width: 0.99,
                                height: 0.3
                            };
                            fullTable(config5);
                        })

                    }


                }
            }
        };
        fullTable(config);
    });

    function aaa() {

        $.get(getBasePath() + "/HisPrintLog/SelectAllXiang", function (data) {
            App.unblockUI("body");
            var config = {
                tableId: "list4",
                data: data,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.99,
                height: 0.3,
                event: {
                    onrowselect: function (rowdatas) {
                        //console.log(rowdatas)
                        //alert(rowdatas.packGd)
                        for (var j in rowdatas) {
                            var packGd = rowdatas[j].packGd;
                            var data = {packGd: packGd};
                            $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {

                                var config5 = {
                                    tableId: "list5",
                                    data: data,
                                    colArr: colNamesArr5,
                                    width: 0.99,
                                    height: 0.3
                                };
                                fullTable(config5);

                            })

                        }


                    }
                }
            };
            fullTable(config);
        });
    }

    $("#select").click(function () {


        var batch = $("#xiangandbatch").val().trim();
        var ckeckBarCode = $("#checkBarCode").is(":checked");
        if (ckeckBarCode) {
            var data = {};
            if (batch == "") {
                toastr.warning("查询组件不能为空");
            }
            data.BarCode = batch;
            $.get(getBasePath() + "/HisPrintLog/SelectBatchDetail", data, function (data) {
                var datas = {};
                if (data == null) {
                    toastr.warning("该组件不存在");
                }
                datas.batch = data.barCode;

                $.get(getBasePath() + "/HisPrintLog/SelectAllXiang", datas, function (data) {

                    var config = {
                        tableId: "list4",
                        data: data,
                        colArr: colNamesArr,
                        multiselect: true,
                        width: 0.99,
                        height: 0.3,
                        event: {
                            onrowselect: function (rowdatas) {
                                //console.log(rowdatas)
                                //alert(rowdatas.packGd)
                                for (var j in rowdatas) {
                                    var packGd = rowdatas[j].packGd;
                                    var data = {packGd: packGd};
                                    $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {
                                        if (data.length > 0) {
                                            var config5 = {
                                                tableId: "list5",
                                                data: data,
                                                colArr: colNamesArr5,
                                                width: 0.99,
                                                height: 0.3
                                            };
                                            fullTable(config5);
                                        } else {
                                            var config5 = {
                                                tableId: "list5",
                                                data: [],
                                                colArr: colNamesArr5,
                                                width: 0.99,
                                                height: 0.3
                                            };
                                            fullTable(config5);
                                        }

                                    })

                                }


                            }
                        }
                    };
                    fullTable(config);
                });

                var config5 = {
                    tableId: "list5",
                    data: [],
                    colArr: colNamesArr5,
                    width: 0.99,
                    height: 0.3
                };
                fullTable(config5);

            });


        } else {
            var time = $("#test10").val();
            var createtime1 = null;
            var createtime2 = null;
            var data = {};
            if (batch != "") {
                data.batch = batch;
            }

            if (time != "") {
                data.createtime2 = time.split(" - ")[0];
                data.createtime1 = time.split(" - ")[1];
            }
            var isverify = "01";
            if (document.getElementById("isverify").checked) {
                isverify = "00";
            }
            var pack = $("#packs").find("option:selected").text();
            if (pack != "") {
                data.pack = pack;
            }

            data.isverify = isverify;
            //var data={batch:batch==""||batch==null?null:batch,createtime1:createtime1,createtime2:createtime2}

            $.get(getBasePath() + "/HisPrintLog/SelectAllXiang", data, function (data) {
                $("#totalnum").text(data.length);
                var config = {
                    tableId: "list4",
                    data: data,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.99,
                    height: 0.3,
                    event: {
                        onrowselect: function (rowdatas) {
                            //console.log(rowdatas)
                            //alert(rowdatas.packGd)
                            for (var j in rowdatas) {
                                var packGd = rowdatas[j].packGd;
                                var data = {packGd: packGd};
                                $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {
                                    if (data.length > 0) {
                                        var config5 = {
                                            tableId: "list5",
                                            data: data,
                                            colArr: colNamesArr5,
                                            width: 0.99,
                                            height: 0.3
                                        };
                                        fullTable(config5);
                                    } else {
                                        var config5 = {
                                            tableId: "list5",
                                            data: [],
                                            colArr: colNamesArr5,
                                            width: 0.99,
                                            height: 0.3
                                        };
                                        fullTable(config5);
                                    }

                                })

                            }


                        }
                    }
                };
                fullTable(config);
            });

            var config5 = {
                tableId: "list5",
                data: [],
                colArr: colNamesArr5,
                width: 0.99,
                height: 0.3
            };
            fullTable(config5);
            $("#checktotalnum").text(0);

        }
    });

    $(".repack").click(function () {

        var tableData = getSeRowData('list4');
        if (tableData.length < 1) {
            toastr.warning('打印失败，请选择你要重打的数据！');
            return;
        }


        for (var i in tableData) {
            var arr = [];
            var data = {packGd: tableData[i].packGd};
            $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (result) {
                for (var j in result) {
                    var arrs = {
                        BarCode: result[j].packCode,
                        BarType: "02"
                    }
                    arr.push(arrs);
                }
                var wode = tableData[i].gradeName1;
                var pg_ = tableData[i].powerGear == '' ? '***' : tableData[i].powerGear;
                var aa = pg_.substr(0, 3);
                var json = {
                    PackCode: tableData[i].packCode,
                    MaCode: tableData[i].maCode.split('-')[0].toUpperCase().replace('XXX', aa),//tableData[i].maCode,
                    MaName: tableData[i].maName,
                    PowerGear: tableData[i].powerGear == '' ? '***' : tableData[i].powerGear,
                    ColorGear: tableData[i].colorGear == '' ? '***' : tableData[i].colorGear,
                    ELGear: tableData[i].elgear == '' ? '***' : tableData[i].elgear,
                    Num: parseInt(tableData[i].num),
                    GradeName: wode,
                    FileName: "http://192.168.20.230:8087/" + tableData[i].fileName,
                    ModulesInfo: arr,
                }
                var HarvestUnit = $("#fahuoaddress").val().trim();
                var IncomingUnit = $("#shouhuoaddress").val().trim();
                if (HarvestUnit != "") {
                    json.HarvestUnit = HarvestUnit;
                }
                if (IncomingUnit != "") {
                    json.IncomingUnit = IncomingUnit;
                }
                try {
                    var ws = new WebSocket("ws://127.0.0.1:8001");
                    ws.onopen = function () {
                        toastr.success("正在打印...");

                        //ws.length=1200;
                        ws.send(JSON.stringify(json));
                        App.blockUI({
                            target: "body",
                            message: "正在处理中...",
                            boxed: true
                        });
                    };
                    ws.onmessage = function (evt) {
                        App.unblockUI('body')

                        if (evt.data == 1) {
                            toastr.success("打印成功!");
                        } else {
                            toastr.warning("打印失败!");
                        }
                    };

                    ws.onclose = function () {
                        App.unblockUI('body');
                        toastr.warning("打印失败!");
                    };
                } catch (e) {
                    toastr.warning("打印失败!");
                }
            })


        }


    });


    //生成请检单

    $(".addQc").click(function () {
        var tableData = getSeRowData('list4');
        if (tableData.length < 1) {
            toastr.warning('请选择你要生成请检单的箱号！');
            return;
        }
        $("#myModal2").modal("show");
    });

    $("#save2").click(function () {
        var tableData = getSeRowData('list4');
        if (tableData.length < 1) {
            toastr.warning('请选择你要生成请检单的箱号！');
            return;
        }
        var BarInfo = [];
        for (var i in tableData) {
            var data = {"BarType": "00", "QCheckReason": "包装查询生成请检", "BarCode": tableData[i].packCode};
            BarInfo.push(data);
        }

        App.blockUI({
            target: "body",
            message: "正在处理中...",
            boxed: true
        });

        var data = {"QCheckMaType": "00", "BarInfo": BarInfo, "Remark": $("#remark").val()};
        layer.confirm('确定生成请检单！', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            $.ajax({
                url: getBasePath() + "/SunPort/MQC/QCheckMaInfo",
                type: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                beforeSend: function () {
                    /*//获取浏览器页面可见高度和宽度
                    var _PageHeight = document.documentElement.clientHeight,
                        _PageWidth = document.documentElement.clientWidth;
                    //计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
                    var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
                        _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
                    //在页面未加载完毕之前显示的loading Html自定义内容
                    var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#0a0b0c;opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; font-family:\'Microsoft YaHei\';"><img style="height: 60px;width: 160px;" src="${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/sunprotlogo.png"></div></div>';
                    //呈现loading效果
                    $("#loading").html(_LoadingHtml);*/
                },
                complete: function () {
                    App.unblockUI("body");
                },
                success: function (Body) {
                    setTimeout("App.unblockUI('body')", 3000);
                    /*var loadingMask = document.getElementById('loadingDiv');
                    loadingMask.parentNode.removeChild(loadingMask);
                    $("#loading").empty();*/
                    if (Body.Body.MsgCode == "EEEE") {
                        toastr.warning(Body.Body.MsgDes);
                    } else {
                        toastr.success(Body.Body.MsgDes);
                    }
                },
                error: function () { //请求数据失败
                    toastr.warning("服务器繁忙!");
                }
            });
            layer.closeAll("dialog");
        });
        $("#myModal2").modal("hide");
    });

    //换片
    $(".addHP").click(function () {
        alert("换片！");
    });

    $("#siFull").click(function () {
        if ($(this).is(":checked")) {
            if (commonData.length > 0) {
                var aa = [];
                for (var i in commonData) {
                    if ("满箱" == commonData[i].isFull) {
                        var aas = {
                            mixedBag: commonData[i].mixedBag,
                            torsionStatus: commonData[i].torsionStatus,
                            remark: commonData[i].remark,
                            isFull: commonData[i].isFull,
                            packGd: commonData[i].packGd,
                            packCode: commonData[i].packCode,
                            maCode: commonData[i].maCode,
                            maName: commonData[i].maName,
                            fileName: commonData[i].fileName,
                            powerGear: commonData[i].powerGear,
                            elgear: commonData[i].elgear,
                            colorGear: commonData[i].colorGear,
                            gradeName: commonData[i].gradeName,
                            num: commonData[i].num,
                            createTime: commonData[i].createTime,
                            creator: commonData[i].creator

                        }
                        aa.push(aas);
                    }
                }
                var config = {
                    tableId: "list4",
                    data: aa,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.99,
                    height: 0.3,
                    event: {
                        onrowselect: function (rowdatas) {
                            //console.log(rowdatas)
                            //alert(rowdatas.packGd)
                            for (var j in rowdatas) {
                                var packGd = rowdatas[j].packGd;
                                var data = {packGd: packGd};
                                $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {
                                    var config5 = {
                                        tableId: "list5",
                                        data: data,
                                        colArr: colNamesArr5,
                                        width: 0.99,
                                        height: 0.3
                                    };
                                    fullTable(config5);
                                })

                            }


                        }
                    }
                };
                fullTable(config);
            }
        } else {
            aaa();
        }
    });

    $("#isverify").click(function () {
        var data = {};
        var isverify = "01";
        if ($(this).is(":checked")) {
            data.isverify = "00";

        } else {
            data.isverify = "01";
        }
        $.get(getBasePath() + "/HisPrintLog/SelectAllXiang", data, function (data) {
            var config = {
                tableId: "list4",
                data: data,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.99,
                height: 0.3,
                event: {
                    onrowselect: function (rowdatas) {
                        //console.log(rowdatas)
                        //alert(rowdatas.packGd)
                        for (var j in rowdatas) {
                            var packGd = rowdatas[j].packGd;
                            var data = {packGd: packGd};
                            $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {
                                if (data.length > 0) {
                                    var config5 = {
                                        tableId: "list5",
                                        data: data,
                                        colArr: colNamesArr5,
                                        width: 0.99,
                                        height: 0.3
                                    };
                                    fullTable(config5);
                                } else {
                                    var config5 = {
                                        tableId: "list5",
                                        data: [],
                                        colArr: colNamesArr5,
                                        width: 0.99,
                                        height: 0.3
                                    };
                                    fullTable(config5);
                                }

                            })

                        }


                    }
                }
            };
            fullTable(config);
        });
    });

    $(document).on("click", "#list4 tbody tr", function () {
        if ($(this).attr("aria-selected") == "true") {
            ++addid;
        } else {
            --addid;
        }
        $("#checktotalnum").text(addid);
    });

    $(document).on("click", "#cb_list4", function () {
        if ($(this).is(":checked")) {
            var list5Data = getSeRowData('list4');
            addid = list5Data.length;
            $("#checktotalnum").text(list5Data.length);
        } else {
            addid = 0;
            $("#checktotalnum").text(0);
        }
    });
    $("#checkBarCode").click(function () {
        if (!$(this).is(":checked")) {
            $("#xiangandbatch").val("");
        }
    });

});