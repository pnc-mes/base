$(function () {
    var user = JSON.parse(localStorage.user);
    var colNamesArr4 = [
        {"Caption": "StartOpt", "Name": "StartOpt", "CType": "text", "Hidden": true},
        {"Caption": "物料批次", "Name": "Batch", "CType": "text", "Editable": false},
        {"Caption": "上/下料", "Name": "Type", "CType": "text", "Editable": false},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text", "Editable": false},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "CType": "text", "Editable": false},
        {"Caption": "记录数量", "Name": "CanNum", "CType": "text", "Editable": false},
        {"Caption": "备注", "Name": "Remark", "CType": "text", "Editable": false},
        {"Caption": "时间", "Name": "CurrentTime", "CType": "text", "Editable": false},
    ];
    var config4 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr4,
        width: 0.9,
        height: 0.415
    };
    config4.data.length = 0;
    fullTable(config4);//加载空表格


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
           /* //根据当前作业人所在线体去查询所属工单
            //1：先把线体RD转换成GD
            //2:在用线体GD去关联工单绑定线体表
            //3：查出工单绑定信息后在根据工单的GD去查询工单信息表
            var lineGd = "(";
            request({
                url: "/Line/GetLineInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify({LineRd: user.LineInfo.LineRd})}
            }, function (Body) {
                lineGd = lineGd + "'" + Body.Data.LineGd + "'";
            });

            if (user.LineInfo.OEMLine != undefined && user.LineInfo.OEMLine != null) {
                if (user.LineInfo.OEMLine.length > 0) {
                    for (var i = 0; i < user.LineInfo.OEMLine.length; i++) {
                        request({
                            url: "/Line/GetLineInfo",
                            data: {
                                "ExecType": "00",
                                "BusData": JSON.stringify({LineRd: user.LineInfo.OEMLine[i].LineRd})
                            }
                        }, function (Body) {
                            lineGd = lineGd + "," + "'" + Body.Data.LineGd + "'";
                        });

                    }
                }
            }
            lineGd = lineGd + ")";

            var WoLineData = "(";
            request({
                url: "/WO/GetAllWoLine",
                data: {
                    "ExecType": "00",
                    "InitData": JSON.stringify({
                        "FiledList": [{
                            "FieldName": "LineGd",
                            "FieldOpt": "in",
                            "FieldVal": lineGd
                        }]
                    })
                }
            }, function (Body) {
                WoLineData = WoLineData + Body.Data
            });
            WoLineData = WoLineData + ")";

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
                            "FieldName": "Guid",
                            "FieldOpt": "in",
                            "FieldVal": WoLineData
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
            });*/

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
            //工单类型 d.WOTInfo.WTName
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
            $("#MaDes").text(d.MaInfo.MaDes == undefined ? "" : d.MaInfo.MaDes);
            $("#myModal").modal("hide");
        });
    }

    //清空
    function reClear() {
        $("#WoRd").val("");
        //工单号
        $("#WoCode").val("");
        //工单类型 d.WOTInfo.WTName
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
        $("#MaDes").text("");
        var config4 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr4,
            width: 0.8,
            height: 0.415
        };
        config4.data.length = 0;
        fullTable(config4);//加载空表格
    }


    $('#CZPCode').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            if ($(this).val().trim() == "") {
                toastr.warning("物料批次不能为空");
                return false;
            }

            var Batch = $(this).val().trim();

            var config4 = {
                tableId: 'list4',
                data: [],
                colArr: colNamesArr4,
                width: 0.8,
                height: 0.415
            };
            config4.data.length = 0;
            fullTable(config4);//加载空表格
            var datas = {
                "Batch": Batch
            };

            request({
                url: "/SunPort/UDMaterialFP/GetAllByWoCode",
                data: {"ExecType": "01", "BusData": JSON.stringify(datas)}
            }, function (Body) {
                $("#ZSnumber").val(Body.Data.binfoNum);
                $("#KYnumber").val(Body.Data.binfoCanNum);
                Body.Data.udMaterialFoolProofInfoList.forEach(function (val, index) {
                    var MaCode = val.materialCode;
                    var MaName = val.materialName;
                    var MaDes = val.materialDes;
                    var CanNum = val.num + val.unitName;
                    var Batch = val.batch;
                    var CurrentTime = val.screateTime;
                    var Remark = val.remark;
                    var Type = val.udType == "00" ? "上料" : "下料";
                    var data = {
                        "Batch": Batch,
                        "MaCode": MaCode,
                        "MaName": MaName,
                        "CanNum": CanNum,
                        "MaDes": MaDes,
                        "Type": Type,
                        "Remark": Remark,
                        "CurrentTime": CurrentTime
                    };
                    addSrow("list4", data);
                });

            });

        }
    })

    $("._Save").click(function () {
        if ($('#CZPCode').val().trim() == "") {
            toastr.warning("物料批次不能为空");
            return false;
        }

        var Batch = $('#CZPCode').val().trim();
        var number = $('#number').val();
        var Remark = $('#Remark').val();
        /* var WoRd = WoRds;
         if (WoRd == null || WoRd == "") {
             toastr.warning("工单不能为空");
             return false;
         }*/

        if (number == null || number == undefined || number <= 0) {
            toastr.warning("请输入正确下料数量！");
            return false;
        }
        var datas = {
            "Number": number,
            "Batch": Batch,
            "Remark": Remark,
            "Type": "01"
        };

        request({
            url: "/SunPort/UDMaterialFP/SaveUDMaterial",
            data: {"ExecType": "00", "BusData": JSON.stringify(datas)}
        }, function (Body) {
            toastr.success(Body.MsgDes);

            var objBusData = JSON.stringify({"Batch": Batch});
            var objData = {
                "ExecType": "00",
                "BusData": objBusData,
            };
            request({url: "/Commom/GetCMBInfo", data: objData}, function (Body) {
                var MaCode = Body.Data.MaCode;
                var MaName = Body.Data.MaName;
                var MaDes = Body.Data.MaDes;
                var CanNum = $('#number').val() + Body.Data.UnitInfo.UnitName;
                var Batch = Body.Data.Batch;
                var Remark = $('#Remark').val();
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
                    "Batch": Batch,
                    "MaCode": MaCode,
                    "MaName": MaName,
                    "CanNum": CanNum,
                    "Type": "下料",
                    "MaDes": MaDes,
                    "Remark": Remark,
                    "CurrentTime": CurrentTime
                };
                addSrow("list4", data);
            });
        });
        $('#number').val("");
    });

    //查询工单对应物料操作记录
    $("._Select").click(function () {
        var config4 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr4,
            width: 0.8,
            height: 0.415
        };
        config4.data.length = 0;
        fullTable(config4);//加载空表格
        var WoRd = WoRds;
        if (WoRd == null || WoRd == "") {
            toastr.warning("工单不能为空");
            return false;
        }

        var datas = {
            "WoRd": WoRd
        };

        request({
            url: "/SunPort/UDMaterialFP/GetAllByWoCode",
            data: {"ExecType": "00", "BusData": JSON.stringify(datas)}
        }, function (Body) {
            Body.Data.forEach(function (val, index) {
                var MaCode = val.materialCode;
                var MaName = val.materialName;
                var MaDes = val.materialDes;
                var CanNum = val.num + val.unitName;
                var Batch = val.batch;
                var CurrentTime = val.screateTime;
                var Remark = val.remark;
                var Type = val.udType == "00" ? "上料" : "下料";
                var data = {
                    "Batch": Batch,
                    "MaCode": MaCode,
                    "MaName": MaName,
                    "CanNum": CanNum,
                    "MaDes": MaDes,
                    "Type": Type,
                    "Remark": Remark,
                    "CurrentTime": CurrentTime
                };
                addSrow("list4", data);
            });

            toastr.success(Body.MsgDes);
        });
    })


});