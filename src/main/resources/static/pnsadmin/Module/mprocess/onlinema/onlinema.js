/**
 * Created by liufuzhi on 2017/11/3.
 */
$(function(){
// 2.处理表格
    var colNamesArr = [
        {"Caption": "工单号", "Name": "WoCode",  "Editable": false,Width:100},
        {"Caption": "批次号", "Name": "Batch",  "Editable": false,Width:100},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "数量", "Name": "Num", "Editable": false,Width:50},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
        {"Caption": "状态", "Name": "Status",  "Editable": false,Width:50},
        {"Caption": "来源", "Name": "Source", "Editable": false,Width:50}
    ];

    var config1 = {
        tableId: 'list4',
        data:[],
        colArr: colNamesArr,
        multiselect: false,
        width: 0.995,
        height:0.85
    };
    fullTable(config1);
    /*********************************************请求批次列表信息*********************************************/
    //今天的时间
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();

    //明天的时间
    var day3 = new Date();
    day3.setTime(day3.getTime()+24*60*60*1000);
    var s3 = day3.getFullYear()+"-" + (day3.getMonth()+1) + "-" + day3.getDate();

    var  datasources={};
    datasources={
        "Batch":"",
        "WoCode":"",
        "MaCode":"",
        "MaName":"",
        "CreateTime":s2,
        "CreateTime1":s3,
        "Status":""
    }
    request({
        id:"list4",
        url: "/Report/GetAllLYMaInfo",
        async: true,
        data: {"ExecType": "00","BusData":JSON.stringify(datasources)}
    }, function (Body) {
        var config1 = {
            tableId: 'list4',
            data: Body.Data,
            colArr: colNamesArr,
            multiselect: false,
            width: 0.995,
            height:0.85
        };
        fullTable(config1);
    });



    var params = [{
        "caption": "批次号",
        "name": "Batch",
        "valtype": "00"
    },{
        "caption": "工单号",
        "name": "AssCode",
        "valtype": "00"
    },{
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
    },{
        "caption": "状态",
        "name": "Status",
        "valtype": "01",
        "data":"\"00\":\"待消耗\"|\"01\":\"消耗中\"|\"02\":\"冻结\"|\"03\":\"报废\""
    }];


    var event = {
        onsure: function (result) {
            var data=result[4].CreateTime.split("|");
            var data1=data[0];
            var data2=data[1];
            datasources={
                "Batch":result[0].Batch,
                "WoCode":result[1].AssCode,
                "MaCode":result[2].MaterialCode,
                "MaName":result[3].MaterialName,
                "CreateTime":data1,
                "CreateTime1":data2,
                "Status":result[5].Status,
            }
            $("#list4").html("");
            request({
                id:"list4",
                url: "/Report/GetAllLYMaInfo",
                async: true,
                data: {"ExecType": "00", "BusData": JSON.stringify(datasources)}
            }, function (Body) {
                var config1 = {
                    tableId: 'list4',
                    data: Body.Data,
                    colArr: colNamesArr,
                    multiselect: false,
                    width: 0.995,
                    height:0.85
                };
                fullTable(config1);
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
        layer.confirm('确认要导出该产线余料信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            var url = getBasePath() + "/Report/ExportYMaInfo";
            var data_ = "ExecType=00&BusData="+JSON.stringify(datasources);
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

                        filename = "产线余料表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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