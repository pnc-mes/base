/**
 * Created by test on 2017/7/14.
 */
$(function () {

    //表格定义
    var colNamesArr = [
        {"Caption": "条码", "Name": "BarCode", "CType": "text",Width:100},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text",Width:100},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text"},
        {"Caption": "单号", "Name": "ReCode", "CType": "text"},
        {"Caption": "单据类型", "Name": "OrderType", "CType": "text",Width:60},
        {"Caption": "条码类型", "Name": "BarType", "CType": "text",Width:60,"Hidden": true},
        {"Caption": "打印数量", "Name": "PrintCount", "CType": "text",Width:60},
        {"Caption": "打印份数", "Name": "PrintCopy", "CType": "text",Width:60},
        {"Caption": "最近打印时间", "Name": "LastPTime", "CType": "text",Width:120},
        {"Caption": "PTCode", "Name": "PTCode", "CType": "text", "Hidden": true}
    ];

    //生产线别
    var scxb = {
        "displaymode": "0",
        "title": "生产线别",
        "binddata": {
            "keyfield": "LineRd",
            "fields": [
                {
                    "caption": "生产线别Rd",
                    "name": "LineRd"
                }, {
                    "caption": "生产线别名称",
                    "name": "LineName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"LineName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "LineName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Line/GetAllLineInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "LineRd": datas[i].LineRd,
                            "LineName": datas[i].LineName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#scxb").zc_select(scxb);

    //今天的时间
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var s2 = day2.getFullYear() + "-" + (day2.getMonth() + 1) + "-" + day2.getDate();

    //明天的时间
    var day3 = new Date();
    day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000);
    var s3 = day3.getFullYear() + "-" + (day3.getMonth() + 1) + "-" + day3.getDate();

    var dateTime= s2 + " 00:00:00 ~ "+ s3 + " 00:00:00";
    laydate.render({
        elem: '#date',
        range: "~",
        type: 'datetime',
        value: dateTime
    });

    var FiledList1 = [];
    var Filed = {
        "FieldName": "CreateTime",
        "FieldOpt": ">=",
        "FieldVal": s2.split("|")[0]
    };
    FiledList1.push(Filed);
    Filed = {
        "FieldName": "CreateTime",
        "FieldOpt": "<=",
        "FieldVal": s3.split("|")[1]

    };
    FiledList1.push(Filed);
    Filed =  {
        "FieldName": "CreateTime DESC",
        "FieldOpt": "Order BY"
    };
    FiledList1.push(Filed);
    var InitData = {};

    //获取打印记录
    var PrintserchData=function() {
        InitData = {
            "FiledList": FiledList1
        };

        request({url:"/HisPrintLog/GetAllHPLInfo", data: {"ExecType": "00", "InitData": JSON.stringify(InitData)} ,id:"aaa"},function(Body){
            if (Body.MsgCode == "0x00000") {
                var data = Body.Data;
                data.forEach(function (obj) {
                    var barType = obj.BarType;
                    if (barType == "00") {
                        obj.BarType = "砧板"
                    } else if (barType == "01") {
                        obj.BarType = "箱包"
                    } else if (barType == "02") {
                        obj.BarType = "批次号"
                    }
                    var OrderType = obj.OrderType;
                    if (OrderType == "00") {
                        obj.OrderType = "采购单"
                    } else if (OrderType == "01") {
                        obj.OrderType = "来料收货通知单"
                    } else if (OrderType == "02") {
                        obj.OrderType = "生产工单"
                    } else if (OrderType == "03") {
                        obj.OrderType = "盘点单"
                    } else if (OrderType == "04") {
                        obj.OrderType = "无源单"
                    }else if (OrderType == "05") {
                        obj.OrderType = "领料单"
                    }
                });

                var config = {
                    tableId: "list4",
                    data: data,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.99,
                    height: 0.88
                };
                fullTable(config);
            } else {
                toastr.warning(Body.MsgDes);
                return;
            }
        });
    };
    //PrintserchData();
    var config = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.99,
        height: 0.78
    };
    fullTable(config);

    $(".cSelect").zc_filter({
        "params": [{
            "caption": "单号", //显示名
            "name": "ReCode",//字段名
            "valtype": "00",//字段值类 00#文本 01#下拉框 02#日期段
        },
            {
                "caption": "条码", //显示名
                "name": "BarCode",//字段名
                "valtype": "00",//字段值类 00#文本 01#下拉框 02#日期段
            },
           /* {
                "caption": "条码类型", //显示名
                "name": "BarType",//字段名
                "valtype": "01",//字段值类 00#文本 01#下拉框 02#日期段
                "data": "\"02\":\"批次号\"|\"01\":\"箱包\"|\"00\":\"砧板\"", //当 valtype=01 有效格式
            },*/
            {
                "caption": "物料代码", //显示名
                "name": "MaterialCode",//字段名
                "valtype": "00"//字段值类 00#文本 01#下拉框 02#日期段
            },
            {
                "caption": "物料名称", //显示名
                "name": "MaterialName",//字段名
                "valtype": "00"//字段值类 00#文本 01#下拉框 02#日期段
            },
            {
                "caption": "打印时间段", //显示名
                "name": "CreateTime",//字段名
                "valtype": "02"//字段值类 00#文本 01#下拉框 02#日期段
            }
        ],
        "event": {
            onsure: function (res) {
                var FiledList = [];
                for (var i in res) {
                    for (var j in res[i]) {
                        if (res[i][j].trim() != "" && res[i][j] != null) {
                            if (j == "CreateTime") {
                                var Filed = {
                                    "FieldName": j,
                                    "FieldOpt": ">=",
                                    "FieldVal": res[i][j].split("|")[0]
                                };
                                FiledList.push(Filed);
                                Filed = {
                                    "FieldName": j,
                                    "FieldOpt": "<=",
                                    "FieldVal": res[i][j].split("|")[1] + " 23:59:59"

                                };
                                FiledList.push(Filed);
                                break;
                            }
                            var Filed = {
                                "FieldName": j,
                                "FieldOpt": "like",
                                "FieldVal": "%" + res[i][j] + "%"
                            };

                            FiledList.push(Filed);
                        }
                    }
                }
                var  Filed =  {
                    "FieldName": "CreateTime DESC",
                    "FieldOpt": "Order BY"
                };
                FiledList.push(Filed);
                InitData = {
                    "FiledList": FiledList
                };

                //console.log(JSON.stringify(InitData));

                request({url:"/HisPrintLog/GetAllHPLInfo", data: {"ExecType": "00", "InitData": JSON.stringify(InitData)} ,id:"aaa"},function(Body){
                    if (Body.MsgCode == "0x00000") {
                        var data = Body.Data;
                        data.forEach(function (obj) {
                            var barType = obj.BarType;
                            if (barType == "00") {
                                obj.BarType = "砧板"
                            } else if (barType == "01") {
                                obj.BarType = "箱包"
                            } else if (barType == "02") {
                                obj.BarType = "批次号"
                            }
                            var OrderType = obj.OrderType;
                            if (OrderType == "00") {
                                obj.OrderType = "采购单"
                            } else if (OrderType == "01") {
                                obj.OrderType = "来料收货通知单"
                            } else if (OrderType == "02") {
                                obj.OrderType = "生产工单"
                            } else if (OrderType == "03") {
                                obj.OrderType = "盘点单"
                            } else if (OrderType == "04") {
                                obj.OrderType = "无源单"
                            }else if (OrderType == "05") {
                                obj.OrderType = "领料单"
                            }
                        });
                        var config = {
                            tableId: "list4",
                            data: Body.Data,
                            colArr: colNamesArr,
                            multiselect: true,
                            width: 0.99,
                            height: 0.88
                        };
                        fullTable(config);
                    } else {
                        toastr.warning(Body.MsgDes);
                        return;
                    }
                });
            }
        }
    });


    /*---------重打---------------------------------*/
    $('.rePrint').on('click', function () {

        var ckl = $("#list4 .cbox:checked").length;
        if (ckl > 0) {

            var data = getSeRowData("list4");
            var rePrInfo = [], rePrInfo2 = [], rePrInfo3 = [];
            data.forEach(function (obj) {

                var _rePrInfo = {
                    PTCode: obj.PTCode
                };
                rePrInfo.push(_rePrInfo);

                var _rePrInfo2 = {
                    ReCode: obj.ReCode
                };
                rePrInfo2.push(_rePrInfo2);

                var _rePrInfo3 = {
                    BarCode: obj.BarCode
                };
                rePrInfo3.push(_rePrInfo3);
            });

            sessionStorage.setItem("data", JSON.stringify(rePrInfo));
            sessionStorage.setItem("data2", JSON.stringify(rePrInfo2));
            sessionStorage.setItem("data3", JSON.stringify(rePrInfo3));

            layer.alert('', {
                type: 2,
                title: '重打',
                shadeClose: true,
                shade: 0.7,
                area: ['45%', '50%'],
                content: getBasePath() + "/HisPrintLog/RePrintPG",
                end: function () {
                    if (storage.getItem("sure") == "0") {
                        //PrintserchData();
                        $("#cx").click();
                        storage.removeItem("sure");
                    }
                }
            });
        } else {
            toastr.warning("请选择要打印的信息");
        }
    });

    $("#export").on('click', function () {
        //alert(JSON.stringify(InitData))
        layer.confirm('确认要打印记录查询信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {

            layer.closeAll("dialog");

            var url = getBasePath() + "/HisPrintLog/ExportHPLInfo"; // "FiledList":FiledList
            //var data_ = "ExecType=00/*&InitData="+"FiledList"+FiledList+""*//*+JSON.stringify(InitData)+""*/;/*"ExecType=00&InitData="+FiledList+"";*/
            var data_ = "ExecType=00";
            //  data_+="InitData:"+JSON.stringify(InitData);
            //alert(JSON.stringify(data_))
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

                        filename = "打印记录查询表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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

            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=UTF-8');
            xhr.send("ExecType=00" + "&InitData=" + escape(JSON.stringify(InitData)));
        });
    });

    //查询事件
    $("#cx").click(function(){
        var Batch=$("#tjtm").val().trim();
        var LineRd=$("#scxb").getseldata().LineRd;

        var dates = $("#date").val().split("~");
        var startDate = '', endDate = '';
        if(dates.length == 2){
            startDate = dates[0];
            endDate = dates[1];
        }

        if(startDate == '' || endDate == ''){
            toastr.warning("日期不能为空");
            return false;
        }

        var data={
            "Batch": Batch,
            "LineRd": LineRd,
            "StartDate": startDate,
            "EndDate": endDate
        };
        var objData = {
            "ExecType": "00",
            "BusData": JSON.stringify(data),
        };
        request({url: "/HisPrintLog/GetAllHPLNewInfo", data: objData}, function (Body) {
            var data = Body.Data;
            data.forEach(function (obj) {
                var barType = obj.BarType;
                if (barType == "00") {
                    obj.BarType = "砧板"
                } else if (barType == "01") {
                    obj.BarType = "箱包"
                } else if (barType == "02") {
                    obj.BarType = "批次号"
                }
                var OrderType = obj.OrderType;
                if (OrderType == "00") {
                    obj.OrderType = "采购单"
                } else if (OrderType == "01") {
                    obj.OrderType = "来料收货通知单"
                } else if (OrderType == "02") {
                    obj.OrderType = "生产工单"
                } else if (OrderType == "03") {
                    obj.OrderType = "盘点单"
                } else if (OrderType == "04") {
                    obj.OrderType = "无源单"
                }else if (OrderType == "05") {
                    obj.OrderType = "领料单"
                }
            });

            var config = {
                tableId: "list4",
                data: data,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.99,
                height: 0.78
            };
            fullTable(config);
        });
    });
});
