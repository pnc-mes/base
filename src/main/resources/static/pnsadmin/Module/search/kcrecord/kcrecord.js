/**
 * Created by liufuzhi on 2017/10/25.
 */
$(function () {
    var colNamesArr = [
        {"Caption": "类型", "Name": "OptType", 'Editable': false, Width: 80},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false, Width: 120},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "单号", "Name": "OrderCode", "Editable": false},
        {"Caption": "批号", "Name": "Batch", "Editable": false, Width: 150},
        {"Caption": "数量", "Name": "Num", "Editable": false, Width: 80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false, Width: 50},
        {"Caption": "制单人", "Name": "Createor", "Editable": false, Width: 80},
        {"Caption": "制单时间", "Name": "CreateTime", "Editable": false, Width: 180},
        {"Caption": "处理人", "Name": "Dealor", "Editable": false, Width: 80},
        {"Caption": "处理时间", "Name": "DealTime", "Editable": false, Width: 180}
    ];

    var config = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: false,
        width: 0.996,
        height: 0.85

    };
    fullTable(config);//加载表格

    //今天的时间
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var s2 = day2.getFullYear() + "-" + (day2.getMonth() + 1) + "-" + day2.getDate();

    //明天的时间
    var day3 = new Date();
    day3.setTime(day3.getTime() + 24 * 60 * 60 * 1000);
    var s3 = day3.getFullYear() + "-" + (day3.getMonth() + 1) + "-" + day3.getDate();

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
    var InitData = {};

    function RecInfo() {
        InitData = {
            "FiledList": FiledList1
        }
        request({
            id: "list4",
            url: "/Report/GetAllKCRecInfo",
            async: true,
            data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
        }, function (Body) {

            var config = {
                tableId: 'list4',
                data: Body.Data,
                colArr: colNamesArr,
                multiselect: false,
                width: 0.996,
                height: 0.85
            };
            fullTable(config);

            for (var i = 0; i < Body.Data.length; i++) {
                var str = "<tr>"
                    + "<td>" + Body.Data[i].OptType + "</td>"
                    + "<td>" + Body.Data[i].MaCode + "</td>"
                    + "<td>" + Body.Data[i].MaName + "</td>"
                    + "<td>" + Body.Data[i].OrderCode + "</td>"
                    + "<td>" + Body.Data[i].Batch + "</td>"
                    + "<td>" + Body.Data[i].Num + "</td>"
                    + "<td>" + Body.Data[i].UnitName + "</td>"
                    + "<td>" + Body.Data[i].Createor + "</td>"
                    + "<td>" + Body.Data[i].CreateTime + "</td>"
                    + "<td>" + Body.Data[i].Dealor + "</td>"
                    + "<td>" + Body.Data[i].DealTime + "</td>"
                    + "</tr>";
                $("#CyContent").append(str);
            }
        });
    }

    RecInfo();

    var params = [{
        "caption": "物料代码",
        "name": "MaterialCode",
        "valtype": "00"
    }, {
        "caption": "物料名称",
        "name": "MaterialName",
        "valtype": "00"
    }, {
        "caption": "时间段",
        "name": "CreateTime",
        "valtype": "02"
    }];

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
            InitData = {
                "FiledList": FiledList
            }
            request({
                id: "list4",
                url: "/Report/GetAllKCRecInfo",
                async: true,
                data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
            }, function (Body) {
                var config = {
                    tableId: 'list4',
                    data: Body.Data,
                    colArr: colNamesArr,
                    multiselect: false,
                    width: 0.996,
                    height: 0.85
                };
                fullTable(config);
            });
        }
    };

    //筛选
    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);

    //导出
    $("#export").on('click', function () {
        layer.confirm('确认要导出该库存变动信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            var url = getBasePath() + "/Report/ExportGetAllKCRecInfo";
            var data_ = "ExecType=00";
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

                        filename = "库存变动表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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
            xhr.send("ExecType=00" + "&InitData=" + escape(JSON.stringify(InitData)));
        });
    });

    //打印
    $("#print").on('click', function () {

        var obj = $("#SWareInfoForm").clone();
        obj.removeAttr("style");
        obj.jqprint();
    });

    //查询点击事件
    $("#Big_").click(function () {
        var aa = $("#path").val();
        window.open(aa + "/ReportServer?reportlet=入库历史.cpt");
    });
});
