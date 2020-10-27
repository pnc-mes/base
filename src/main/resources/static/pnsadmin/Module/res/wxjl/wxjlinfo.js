$(function(){
    var shebei = {
        "displaymode": "0",
        "title": "shebei",
        "binddata": {
            "keyfield": "DevRd",
            "fields": [
                {
                    "caption": "DevRd",
                    "name": "DevRd"
                }, {
                    "caption": "DevName",
                    "name": "DevName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DevName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DevName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Device/GetAllDevInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevRd": datas[i].DevRd,
                            "DevName": datas[i].DevName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            },
        }
    };

    $("#shebei").zc_select(shebei);

    var gzlx  = {
        "displaymode": "0",
        "title": "shebei",
        "binddata": {
            "keyfield": "DevMalfRd",
            "fields": [
                {
                    "caption": "DevMalfRd",
                    "name": "DevMalfRd"
                }, {
                    "caption": "DevMalfName",
                    "name": "DevMalfName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DevMalfName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DevMalfName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/DeviceMalf/GetAllDevMalfInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevMalfRd": datas[i].DevMalfRd,
                            "DevMalfName": datas[i].DevMalfName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            },
        }
    };

    $("#gzlx ").zc_select(gzlx);

    var ztqh = {
        "displaymode": "0",
        "title": "shebei",
        "binddata": {
            "keyfield": "DevSRd",
            "fields": [
                {
                    "caption": "DevSRd",
                    "name": "DevSRd"
                }, {
                    "caption": "DevSName",
                    "name": "DevSName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DevSName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DevSName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/DevState/GetAllDevSInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevSRd": datas[i].DevSRd,
                            "DevSName": datas[i].DevSName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            },
        }
    };

    $("#ztqh").zc_select(ztqh);


    $("#save").click(function () {
        var DevRd=$("#shebei").getseldata().DevRd;
        var DevMalfRd=$("#ztqh").getseldata().DevMalfRd;
        var DevSRd=$("#gzlx").getseldata().DevSRd;

        if(DevRd==""||DevRd==null){
            toastr.warning("设备不能为空");
            return false;
        }
        if(DevMalfRd==""||DevMalfRd==null){
            toastr.warning("状态切换不能为空");
            return false;
        }
        if(DevSRd==""||DevSRd==null){
            toastr.warning("状态切换不能为空");
            return false;
        }

        var Remark=$("#remark").val().trim();

        var busData={
                "DevRd":DevRd,
                "DevMalfRd":DevMalfRd,
                "DevSRd":DevSRd,
                "Remark":Remark,
        }

        request({
            url: "/DevMaTain/SaveDevOpt",
            data: {"ExecType": "00", "BusData": JSON.stringify(busData)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
            $("#remark").val("");
            $("#shebei").clearseldata("DevRd");
            $("#ztqh").clearseldata("DevMalfRd");
            $("#gzlx").clearseldata("DevSRd");

        });
    });


});