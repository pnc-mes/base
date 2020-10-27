$(function () {
    var colNamesArr = [
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false, Width: 120},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "库存数量", "Name": "StoreNum", "Editable": false, Width: 80},
        {"Caption": "最低库存数", "Name": "MinSNum", "Editable": false, Width: 80},
        {"Caption": "最大库存数", "Name": "MaxSNum", "Editable": false, Width: 80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false, Width: 80}
    ];

    var config = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: false,
        width: 0.99,
        height: 0.86
    };
    fullTable(config);//加载表格

    var params = [{
        "caption": "物料代码",
        "name": "MaCode",
        "valtype": "00"
    }, {
        "caption": "物料名称",
        "name": "MaName",
        "valtype": "00"
    }, {
        "caption": "库存数量",
        "name": "SumNum",
        "valtype": "00"
    }];
    var data = {};
    function aa() {
        data = {
            "MaCode": "",
            "MaName": "",
            "SumNum": ""
        }
        request({
            id: "list4",
            url: "/Report/GetAllSWareInfo",
            async: true,
            data: {"ExecType": "00","BusData": JSON.stringify(data)}
        }, function (Body) {

            var config = {
                tableId: 'list4',
                data: Body.Data,
                colArr: colNamesArr,
                multiselect: false,
                width: 0.99,
                height: 0.86
            };
            fullTable(config);

            for (var i = 0; i < Body.Data.length; i++) {
                var str = "<tr>"
                    + "<td>" + Body.Data[i].MaCode + "</td>"
                    + "<td>" + Body.Data[i].MaName + "</td>"
                    + "<td>" + Body.Data[i].StoreNum + "</td>"
                    + "<td>" + Body.Data[i].MinSNum + "</td>"
                    + "<td>" + Body.Data[i].MaxSNum + "</td>"
                    + "<td>" + Body.Data[i].UnitName + "</td>"
                    + "</tr>";
                $("#CyContent").append(str);
            }
        });
    }

    aa();


    var event = {
        onsure: function (result) {
            var a = parseFloat(result[2].SumNum)
            data = {
                "MaCode": result[0].MaCode,
                "MaName": result[1].MaName,
                "SumNum": a
            }
            if (data.MaName == "" && data.MaCode == "" && isNaN(data.SumNum)) {
                aa();
                return false;
            }

            $("#list4").html("");
            request({
                id: "list4",
                url: "/Report/GetAllSWareInfo1",
                async: true,
                data: {"ExecType": "00", "BusData": JSON.stringify(data)}
            }, function (Body) {
                var config = {
                    tableId: 'list4',
                    data: Body.Data,
                    colArr: colNamesArr,
                    multiselect: false,
                    width: 0.99,
                    height: 0.86
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
        layer.confirm('确认要导出该库存预警信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            var url = getBasePath() + "/Report/ExportSWareInfo";
            var data_ = "ExecType=00&BusData=" + JSON.stringify(data);
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

                        filename = "库存预警表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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

    //打印
    $("#print").on('click', function () {

        var obj = $("#SWareInfoForm").clone();
        obj.removeAttr("style");
        obj.jqprint();
    });

});
