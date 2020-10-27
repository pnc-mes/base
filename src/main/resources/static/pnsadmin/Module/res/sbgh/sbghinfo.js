$(function () {
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

    var spbj = {
        "displaymode": "0",
        "title": "beipingbeijian",
        "binddata": {
            "keyfield": "SPartRd",
            "fields": [
                {
                    "caption": "SPartRd",
                    "name": "SPartRd"
                }, {
                    "caption": "SPartName",
                    "name": "SPartName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"PartName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "PartName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/SPart/GetAllSPartInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SPartRd": datas[i].SPartRd,
                            "SPartName": datas[i].SPartName,
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

    $("#spbj").zc_select(spbj);


    var colNamesArr = [
        {"Caption": "设备id", "Name": "DevRd", 'Hidden': true,},
        {"Caption": "设备", "Name": "DevName", "Hidden": false},
        {"Caption": "备品备件id", "Name": "SPartRd", "Hidden": true},
        {"Caption": "备品备件", "Name": "SPartName", 'Editable': false,},
        {"Caption": "数量", "Name": "Num", "CType": "text", "Hidden": false,"Editable": true,},
        {"Caption": "备注", "Name": "Remark",  },
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height: 0.415
    };
    config1.data.length = 0;
    fullTable(config1);//加载空表格

    $(".tladd").click(function(){
        var DevRd=$("#shebei").getseldata().DevRd;
        if(DevRd==""||DevRd==null){
            toastr.warning("设备不能为空");
            return false;
        }

        var SPartRd=$("#spbj").getseldata().SPartRd;
        if(SPartRd==""||SPartRd==null){
            toastr.warning("备品备件不能为空");
            return false;
        }

        var Num=$("#numb").val().trim();
        if(Num==""||Num==null){
            toastr.warning("数量不能为空");
            return false;
        }
        var Remark=$("#remark").val();


        var tab_TR = {
            "DevRd":DevRd,
            "SPartRd":SPartRd,
            "Num":Num,
            "Remark": Remark,
            "DevName":$("#shebei").getseldata().DevName,
            "SPartName":$("#spbj").getseldata().SPartName,
        };

        addSrow("list4", tab_TR);
    });


    $("#save").click(function () {
        var list4Data = getTableData("list4");
        if(list4Data.length<=0){
            toastr.warning("保存失败，明细不能为空");
            return false;
        }
        for(var i in  list4Data){
            delete list4Data[i].DevName;
            delete list4Data[i].SPartName;
        }

        request({
            url: "/DevMaTain/SaveDevOpt",
            data: {"ExecType": "01", "BusData": JSON.stringify(list4Data)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
            $("#remark").val("");
            $("#shebei").clearseldata("DevRd");
            $("#spbj").clearseldata("SPartRd");
            $("#numb").val("");
            var config1 = {
                tableId: 'list4',
                data: [],
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height: 0.415
            };
            config1.data.length = 0;
            fullTable(config1);//加载空表格
        });
    });

    $(".tldel").click(function(){
        derow("list4");
    });

});