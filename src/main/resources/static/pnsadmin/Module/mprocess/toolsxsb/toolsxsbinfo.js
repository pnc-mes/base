$(function(){
    var DevRd="";
    var colNamesArr = [
        {"Caption": "ToolRd", "Name": "ToolRd", 'Hidden': true,Width:100},
        {"Caption": "工具序列号", "Name": "VenderSN", "Hidden": false},
        {"Caption": "工具名称", "Name": "ToolName", "Hidden": false},
        {"Caption": "使用次数", "Name": "UsrNum", 'Editable': false,Width:100},
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


    var tool = {
        "displaymode": "0",
        "title": "设备",
        "binddata": {
            "keyfield": "DevRd",
            "fields": [
                {
                    "caption": "设备id",
                    "name": "DevRd"
                }, {
                    "caption": "设备名称",
                    "name": "DevName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DevName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"DevName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                // obj : 业务数据
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Device/GetAllDevInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevRd": datas[i].DevRd,
                            "DevName": datas[i].DevName,
                        }
                        xldata.push(data);
                    }
                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            },
            "onclick":function (data) {
                DevRd= data.DevRd;
                var objBusData = JSON.stringify({"DevRd": data.DevRd});
                var objData = {
                    "ExecType": "00",
                    "BusData": objBusData,
                };
                request({url:"/ToolDev/GetToolDevRelInfo",data: objData},function(Body) {
                        if(Body.Data.length>0){
                            var listData=[];
                            for(var i  in  Body.Data){
                                var listDatas={
                                    "ToolRd":Body.Data[i].ToolRd,
                                    "VenderSN":Body.Data[i].VenderSN,
                                    "ToolName":Body.Data[i].ToolName,
                                    "UsrNum":Body.Data[i].UsrNum,
                                }
                                listData.push(listDatas);
                            }

                            var config1 = {
                                tableId: 'list4',
                                data: listData,
                                colArr: colNamesArr,
                                multiselect: true,
                                width: 0.84,
                                height: 0.415
                            };
                            fullTable(config1);
                        }
                });
            }
        }
    }
    $("#shebei").zc_select(tool);

    //上设备
    $(".upshebei").click(function(){
        var DevRd =$("#shebei").getseldata().DevRd;
        if(DevRd==null||DevRd==""){
            toastr.warning("设备不能为空");
            return false;
        }

        var VenderSN=$("#gjxlh").val().trim();
        if(VenderSN==null||VenderSN==""){
            toastr.warning("序列号不能为空");
            return false;
        }

       var  newData = {
            "DevRd": DevRd,
            "VenderSN":VenderSN
        };
        request({url:"/ToolDev/SaveToolDevRel", data: {"ExecType": "00", "busData": JSON.stringify(newData)}},function(Body){
            toastr.success(Body.MsgDes);
            var objBusData = JSON.stringify({"DevRd": DevRd});
            var objData = {
                "ExecType": "00",
                "BusData": objBusData,
            };
            request({url:"/ToolDev/GetToolDevRelInfo",data: objData},function(Body) {
                if(Body.Data.length>0){
                    var listData=[];
                    for(var i  in  Body.Data){
                        var listDatas={
                            "ToolRd":Body.Data[i].ToolRd,
                            "VenderSN":Body.Data[i].VenderSN,
                            "ToolName":Body.Data[i].ToolName,
                            "UsrNum":Body.Data[i].UsrNum,
                        }
                        listData.push(listDatas);
                    }

                    var config1 = {
                        tableId: 'list4',
                        data: listData,
                        colArr: colNamesArr,
                        multiselect: true,
                        width: 0.84,
                        height: 0.415
                    };
                    fullTable(config1);
                }
            });
        });

    });
    //下设备
    $(".downshebei").click(function(){
            var busData= getSeRowData("list4");
            if(busData.length<=0){
                toastr.warning("序列号不能为空");
                return false;
            }

            var aa=[];
            for(var i  in  busData){
                var a={
                    "ToolRd":busData[i].ToolRd
                }
                aa.push(a);
            }
        request({url:"/ToolDev/SaveToolDevRel", data: {"ExecType": "01", "BusData": JSON.stringify(aa)}},function(Body){
            toastr.success(Body.MsgDes);
            derow("list4");
            $("#shebei").clearseldata("DevRd");
            $("#gjxlh").val("");

        });


    });


});