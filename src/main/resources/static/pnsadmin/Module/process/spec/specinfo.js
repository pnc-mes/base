//@ sourceURL=msgprompt.js
$(function () {
    var treeID=null;
    var rule = [{
        "ctlid": "specName",
        "param": "SpecName"
    },
        {
            "ctlid": "version",
            "param": "Version"
        },
        {
            "ctlid": "isDef",
            "param": "IsDef"
        }, {
            "ctlid": "address",
            "param": "Address"
        }, {
            //创建人
            "ctlid": "creatPeople",
            "param": "Creator"
        }, {
            //创建时间
            "ctlid": "creatTime",
            "param": "CreateTime"
        }, {
            //修改人
            "ctlid": "modifyPeople",
            "param": "LastModifyMan"
        }, {
            //修改时间
            "ctlid": "modifyTime",
            "param": "LastModifyTime"
        }, {
            //备注
            "ctlid": "beizhu",
            "param": "Remark"
        }];
    /*------------------点击事件的处理----------------*/
    var onClicks = function (nodeinfo, handle) {
        //跟节点
        if (nodeinfo.isRoot) {
            $("#add").attr("e", "");
            $(".right").show();
            // $("#addVersion").attr("a","00")
            $("#addVersion").attr("c", nodeinfo.nodeID);
            $("#isDef").attr("disabled", true);
            $("#remove").attr("a", nodeinfo.nodeID);
            //更新保存
            $("#hidden").attr("r", "00");
            $("#copy").attr("d", nodeinfo.nodeID);
            $("#addVersion").attr("ad", "00");
            treeID=nodeinfo.nodeID;
            var datasource = {
                "SpecRd": nodeinfo.nodeID
            };
            request({url:"/Spec/GetSVTreeInfo", data: {
                    "ExecType": "00",
                    "busData": JSON.stringify(datasource)
                }},function (Body) {
                if (Body.MsgCode == "0x00000") {
                    var rule = [{
                        id: "SpecVerRd",
                        text: "Version"
                    }];
                    handle.ckAddChild(rule,Body.Data);
                }
            });
           /* $.ajax({
                url: getBasePath() + "/Spec/GetSVTreeInfo",
                type: "POST",
                data: {
                    "ExecType": "00",
                    "busData": JSON.stringify(datasource)
                },
                success: function (res) {
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        var rule = [{
                            id: "SpecVerRd",
                            text: "Version"
                        }];
                        handle.ckAddChild(rule, res.Body.Data);
                    }
                }
            });*/

            var data = {
                "SpecRd": nodeinfo.nodeID
            };

            request({url:"/Spec/GetSVInfo", data: {
                    "ExecType": "00",
                    "busData": JSON.stringify(data)
                }},function (Body) {
                if (Body.MsgCode == "0x00000") {
                    fillform("aa", rule, Body.Data);
                    $("#hidden").val(Body.Data.SpecRd);
                    $("#hidden").attr("h", Body.Data.SpecVerRd);
                    $("#specName").val(Body.Data.SpecName);
                    $("#version").val(Body.Data.Version);
                    if ("00" == Body.Data.IsDef) {
                        $("#isDef").prop("checked", true);
                        $("#isDef").prop("disabled", true);
                        $("#specName").prop("readonly", false)
                    }
                    if ("01" == Body.Data.IsDef) {
                        $("#isDef").prop("checked", false);
                        $("#isDef").prop("disabled", false);
                        $("#specName").prop("readonly", true)
                    }
                    /*if(Body.Data.PTInfo!=null){
                        //打印机
                        $("#Print").showData({
                            id: Body.Data.PTInfo.PrintTRd,
                            name: Body.Data.PTInfo.PtName,
                            keyfield: "PrintTRd",
                            fields: [
                                {"name": "PrintTRd"},
                                {"name": "PtName"}
                            ]
                        });
                    }*/

                    /*if(Body.Data.PrinterInfo!=null){
                        //打印模板
                        $("#PrintT").showData({
                            id: Body.Data.PrinterInfo.PrRd,
                            name: Body.Data.PrinterInfo.PrName,
                            keyfield: "PrRd",
                            fields: [
                                {"name": "PrRd"},
                                {"name": "PrName"}
                            ]
                        });
                    }*/

                    $("#status").find("option").each(function () {
                        if($(this).val() == Body.Data.Status){
                            $(this).prop("selected",true);
                            $(this).siblings().prop("selected",false);
                            return false;
                        }
                    });

                    //作业
                    $("#defaultSelect").showData({
                        id: Body.Data.Opert.OptRd,
                        name: Body.Data.Opert.OptName,
                        keyfield: "OpertRd",
                        fields: [
                            {"name": "OpertRd"},
                            {"name": "OptName"}
                        ]
                    });
                    //设备组
                    $("#defaultSelect1").showData({
                        id: Body.Data.Device.DevGpRd,
                        name: Body.Data.Device.DevGpName,
                        keyfield: "DevGRd",
                        fields: [
                            {"name": "DevGRd"},
                            {"name": "DevGpName"}
                        ]
                    });
                    //数据采集
                    $("#defaultSelect2").showData({
                        id: Body.Data.DC.DCVerRd,
                        name: Body.Data.DC.DCName,
                        keyfield: "DCVerRd",
                        fields: [
                            {"name": "DCVerRd"},
                            {"name": "DcName"}
                        ]
                    });
                    //技能组
                    $("#defaultSelect3").showData({
                        id: Body.Data.SkillG.SkillGRd,
                        name: Body.Data.SkillG.SkillGName,
                        keyfield: "SGRd",
                        fields: [
                            {"name": "SGRd"},
                            {"name": "SGName"}
                        ]
                    });
                    //文件组
                    $("#defaultSelect4").showData({
                        id: Body.Data.FileGrInfo.FileGRd,
                        name: Body.Data.FileGrInfo.FileGpName,
                        keyfield: "FileGRd",
                        fields: [
                            {"name": "FileGRd"},
                            {"name": "FileGpName"}
                        ]
                    });
                }
            });


         /*   $.ajax({
                url: getBasePath() + "/Spec/GetSVInfo",
                type: "POST",
                data: {
                    "ExecType": "00",
                    "busData": JSON.stringify(data)
                },
                success: function (data) {
                    if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                        fillform("aa", rule, data.Body.Data);
                        $("#hidden").val(data.Body.Data.SpecRd);
                        $("#hidden").attr("h", data.Body.Data.SpecVerRd);
                        $("#specName").val(data.Body.Data.SpecName);
                        $("#version").val(data.Body.Data.Version);
                        if ("00" == data.Body.Data.IsDef) {
                            $("#isDef").prop("checked", true);
                            $("#isDef").prop("disabled", true);
                            $("#specName").prop("readonly", false)
                        }
                        if ("01" == data.Body.Data.IsDef) {
                            $("#isDef").prop("checked", false);
                            $("#isDef").prop("disabled", false);
                            $("#specName").prop("readonly", true)
                        }
                        if(data.Body.Data.PTInfo!=null){
                            //打印机
                            $("#Print").showData({
                                id: data.Body.Data.PTInfo.PrintTRd,
                                name: data.Body.Data.PTInfo.PtName,
                                keyfield: "PrintTRd",
                                fields: [
                                    {"name": "PrintTRd"},
                                    {"name": "PtName"}
                                ]
                            });
                        }

                        if(data.Body.Data.PrinterInfo!=null){
                            //打印模板
                            $("#PrintT").showData({
                                id: data.Body.Data.PrinterInfo.PrRd,
                                name: data.Body.Data.PrinterInfo.PrName,
                                keyfield: "PrRd",
                                fields: [
                                    {"name": "PrRd"},
                                    {"name": "PrName"}
                                ]
                            });
                        }

                        $("#status").find("option").each(function () {
                            if($(this).val() == data.Body.Data.Status){
                                $(this).prop("selected",true);
                                $(this).siblings().prop("selected",false);
                                return false;
                            }
                        });

                        //作业
                        $("#defaultSelect").showData({
                            id: data.Body.Data.Opert.OptRd,
                            name: data.Body.Data.Opert.OptName,
                            keyfield: "OpertRd",
                            fields: [
                                {"name": "OpertRd"},
                                {"name": "OptName"}
                            ]
                        });
                        //设备组
                        $("#defaultSelect1").showData({
                            id: data.Body.Data.Device.DevGpRd,
                            name: data.Body.Data.Device.DevGpName,
                            keyfield: "DevGRd",
                            fields: [
                                {"name": "DevGRd"},
                                {"name": "DevGpName"}
                            ]
                        });
                        //数据采集
                        $("#defaultSelect2").showData({
                            id: data.Body.Data.DC.DCVerRd,
                            name: data.Body.Data.DC.DCName,
                            keyfield: "DCVerRd",
                            fields: [
                                {"name": "DCVerRd"},
                                {"name": "DcName"}
                            ]
                        });
                        //技能组
                        $("#defaultSelect3").showData({
                            id: data.Body.Data.SkillG.SkillGRd,
                            name: data.Body.Data.SkillG.SkillGName,
                            keyfield: "SGRd",
                            fields: [
                                {"name": "SGRd"},
                                {"name": "SGName"}
                            ]
                        });
                        //文件组
                        $("#defaultSelect4").showData({
                            id: data.Body.Data.FileGrInfo.FileGRd,
                            name: data.Body.Data.FileGrInfo.FileGpName,
                            keyfield: "FileGRd",
                            fields: [
                                {"name": "FileGRd"},
                                {"name": "FileGpName"}
                            ]
                        });
                    }
                }
            });*/

        }
        //子节点
        else {
            $("#add").attr("e", "");
            $(".right").show();
            $("#hidden").attr("r", "00");
            $("#addVersion").attr("ad", "01");
            $("#removeVersion").attr("b", nodeinfo.nodeID);
            treeID=nodeinfo.nodeID;
            var data = {
                "SpecVerRd": nodeinfo.nodeID
            };

            request({url:"/Spec/GetSVInfo",data: {"ExecType": "01", "busData": JSON.stringify(data)}},function (Body) {
                if (Body.MsgCode == "0x00000") {
                    fillform("aa", rule, Body.Data);
                    $("#hidden").val(Body.Data.SpecRd);
                    $("#hidden").attr("h", Body.Data.SpecVerRd);
                    $("#specName").val(Body.Data.SpecName);
                    $("#version").val(Body.Data.Version);
                    if ("00" == Body.Data.IsDef) {
                        $("#isDef").prop("checked", true);
                        $("#isDef").prop("disabled", true);
                        $("#specName").prop("readonly", false)
                    }
                    if ("01" == Body.Data.IsDef) {
                        $("#isDef").prop("checked", false);
                        $("#isDef").prop("disabled", false);
                        $("#specName").prop("readonly", true)
                    }

                    $("#status").find("option").each(function () {
                        if($(this).val() == Body.Data.Status){
                            $(this).prop("selected",true);
                            $(this).siblings().prop("selected",false);
                            return false;
                        }
                    });

                    //作业
                    $("#defaultSelect").showData({
                        id: Body.Data.Opert.OptRd,
                        name: Body.Data.Opert.OptName,
                        keyfield: "OpertRd",
                        fields: [
                            {"name": "OpertRd"},
                            {"name": "OptName"}
                        ]
                    });
                    //设备组
                    $("#defaultSelect1").showData({
                        id: Body.Data.Device.DevGpRd,
                        name: Body.Data.Device.DevGpName,
                        keyfield: "DevGRd",
                        fields: [
                            {"name": "DevGRd"},
                            {"name": "DevGpName"}
                        ]
                    });
                    //数据采集
                    $("#defaultSelect2").showData({
                        id: Body.Data.DC.DCVerRd,
                        name: Body.Data.DC.DCName,
                        keyfield: "DCVerRd",
                        fields: [
                            {"name": "DCVerRd"},
                            {"name": "DcName"}
                        ]
                    });
                    //技能组
                    $("#defaultSelect3").showData({
                        id: Body.Data.SkillG.SkillGRd,
                        name: Body.Data.SkillG.SkillGName,
                        keyfield: "SGRd",
                        fields: [
                            {"name": "SGRd"},
                            {"name": "SGName"}
                        ]
                    });
                    //文件组
                    $("#defaultSelect4").showData({
                        id: Body.Data.FileGrInfo.FileGRd,
                        name: Body.Data.FileGrInfo.FileGpName,
                        keyfield: "FileGRd",
                        fields: [
                            {"name": "FileGRd"},
                            {"name": "FileGpName"}
                        ]
                    });
                    /*if(Body.Data.PTInfo!=null){
                        //打印机
                        $("#Print").showData({
                            id: Body.Data.PTInfo.PrintTRd,
                            name: Body.Data.PTInfo.PtName,
                            keyfield: "PrintTRd",
                            fields: [
                                {"name": "PrintTRd"},
                                {"name": "PtName"}
                            ]
                        });
                    }*/

                    /*if(Body.Data.PrinterInfo!=null){
                        //打印模板
                        $("#PrintT").showData({
                            id: Body.Data.PrinterInfo.PrRd,
                            name:Body.Data.PrinterInfo.PrName,
                            keyfield: "PrRd",
                            fields: [
                                {"name": "PrRd"},
                                {"name": "PrName"}
                            ]
                        });
                    }*/


                }
            });





           /* $.ajax({
                url: getBasePath() + "/Spec/GetSVInfo",
                type: "POST",
                data: {
                    "ExecType": "01",
                    "busData": JSON.stringify(data)
                },
                success: function (data) {

                    if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                        fillform("aa", rule, data.Body.Data);
                        $("#hidden").val(data.Body.Data.SpecRd);
                        $("#hidden").attr("h", data.Body.Data.SpecVerRd);
                        $("#specName").val(data.Body.Data.SpecName);
                        $("#version").val(data.Body.Data.Version);
                        if ("00" == data.Body.Data.IsDef) {
                            $("#isDef").prop("checked", true);
                            $("#isDef").prop("disabled", true);
                            $("#specName").prop("readonly", false)
                        }
                        if ("01" == data.Body.Data.IsDef) {
                            $("#isDef").prop("checked", false);
                            $("#isDef").prop("disabled", false);
                            $("#specName").prop("readonly", true)
                        }

                        $("#status").find("option").each(function () {
                            if($(this).val() == data.Body.Data.Status){
                                $(this).prop("selected",true);
                                $(this).siblings().prop("selected",false);
                                return false;
                            }
                        });

                        //作业
                        $("#defaultSelect").showData({
                            id: data.Body.Data.Opert.OptRd,
                            name: data.Body.Data.Opert.OptName,
                            keyfield: "OpertRd",
                            fields: [
                                {"name": "OpertRd"},
                                {"name": "OptName"}
                            ]
                        });
                        //设备组
                        $("#defaultSelect1").showData({
                            id: data.Body.Data.Device.DevGpRd,
                            name: data.Body.Data.Device.DevGpName,
                            keyfield: "DevGRd",
                            fields: [
                                {"name": "DevGRd"},
                                {"name": "DevGpName"}
                            ]
                        });
                        //数据采集
                        $("#defaultSelect2").showData({
                            id: data.Body.Data.DC.DCVerRd,
                            name: data.Body.Data.DC.DCName,
                            keyfield: "DCVerRd",
                            fields: [
                                {"name": "DCVerRd"},
                                {"name": "DcName"}
                            ]
                        });
                        //技能组
                        $("#defaultSelect3").showData({
                            id: data.Body.Data.SkillG.SkillGRd,
                            name: data.Body.Data.SkillG.SkillGName,
                            keyfield: "SGRd",
                            fields: [
                                {"name": "SGRd"},
                                {"name": "SGName"}
                            ]
                        });
                        //文件组
                        $("#defaultSelect4").showData({
                            id: data.Body.Data.FileGrInfo.FileGRd,
                            name: data.Body.Data.FileGrInfo.FileGpName,
                            keyfield: "FileGRd",
                            fields: [
                                {"name": "FileGRd"},
                                {"name": "FileGpName"}
                            ]
                        });
                        if(data.Body.Data.PTInfo!=null){
                            //打印机
                            $("#Print").showData({
                                id: data.Body.Data.PTInfo.PrintTRd,
                                name: data.Body.Data.PTInfo.PtName,
                                keyfield: "PrintTRd",
                                fields: [
                                    {"name": "PrintTRd"},
                                    {"name": "PtName"}
                                ]
                            });
                        }

                        if(data.Body.Data.PrinterInfo!=null){
                            //打印模板
                            $("#PrintT").showData({
                                id: data.Body.Data.PrinterInfo.PrRd,
                                name: data.Body.Data.PrinterInfo.PrName,
                                keyfield: "PrRd",
                                fields: [
                                    {"name": "PrRd"},
                                    {"name": "PrName"}
                                ]
                            });
                        }


                    }
                }
            });*/
        }
    };

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
        "PageSize":20,
        "PageIndex":currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function(event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config);
    });

    //加载打印模板
   /* var params_printT = {
        "displaymode": "0",
        "title": "选择打印模板",
        "binddata": {
            "keyfield": "PrintTRd",
            "fields": [
                {
                    "caption": "打印模板id",
                    "name": "PrintTRd"
                }, {
                    "caption": "打印模板名称",
                    "name": "PtName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var obj = {
                    data: {"ExecType": "00"},
                    url: "/PrintT/GetAllPtInfo"
                };
                var xldata = [];
                request(obj, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PrintTRd": datas[i].PtRd,
                            "PtName": datas[i].PtName,
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
    $("#PrintT").zc_select(params_printT);*/

    //加载打印机
    /*var params_print = {
        "displaymode": "0",
        "title": "选择打印机",
        "binddata": {
            "keyfield": "PrRd",
            "fields": [
                {
                    "caption": "打印机id",
                    "name": "PrRd"
                }, {
                    "caption": "打印机名称",
                    "name": "PrName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var obj = {
                    data: {"ExecType": "00"},
                    url: "/Printer/GetAllPrInfo"
                };
                var xldata = [];
                request(obj, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PrRd": datas[i].PrRd,
                            "PrName": datas[i].PrName,
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
    $("#Print").zc_select(params_print);*/



    //查询所有的作业
    var params = {
        "displaymode": "0",
        "title": "作业",
        "binddata": {
            "keyfield": "OpertRd",
            "fields": [
                {
                    "caption": "作业id",
                    "name": "OpertRd"
                }, {
                    "caption": "作业名称",
                    "name": "OptName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"OptName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"OptName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                        url: "/Opert/GetAllOpertInfo",
                        data: {
                            "ExecType": "00",
                            "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)
                        }
                    },
                    function (Body) {
                        var datas = Body.Data;

                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "OpertRd": datas[i].OpertRd,
                                "OptName": datas[i].OptName
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

    $("#defaultSelect").zc_select(params);

    //查询所有的设备组
    var params1 = {
        "displaymode": "0",
        "title": "设备组",
        "binddata": {
            "keyfield": "DevGRd",
            "fields": [
                {
                    "caption": "设备组id",
                    "name": "DevGRd"
                }, {
                    "caption": "设备组名称",
                    "name": "DevGpName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DevGpName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"DevGpName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                        url: "/DeviceG/GetAllDevGInfo",
                        data: {
                            "ExecType": "00",
                            "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)
                        }
                    },
                    function (Body) {

                        var datas = Body.Data;

                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "DevGRd": datas[i].DevGRd,
                                "DevGpName": datas[i].DevGpName,
                            }
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

    $("#defaultSelect1").zc_select(params1);
    //查询数据版本采集
    var params2 = {
        "displaymode": "0",
        "title": "数据采集",
        "binddata": {
            "keyfield": "DCVerRd",
            "fields": [
                {
                    "caption": "数据采集id",
                    "name": "DCVerRd"
                }, {
                    "caption": "数据采集名称",
                    "name": "DcName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DCName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"DCName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/DC/GetAllDcInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)
                }}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DCVerRd": datas[i].DCVerRd,
                            "DcName": datas[i].DcName
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
    $("#defaultSelect2").zc_select(params2);

    //获取技能组信息
    var params3 = {
        "displaymode": "0",
        "title": "技能组",
        "binddata": {
            "keyfield": "SGRd",
            "fields": [
                {
                    "caption": "技能组id",
                    "name": "SGRd"
                }, {
                    "caption": "技能组名称",
                    "name": "SGName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"SkillGName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"SkillGName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/SkillG/GetAllSGInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SGRd": datas[i].SGRd,
                            "SGName": datas[i].SGName
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

    $("#defaultSelect3").zc_select(params3);

    //获取文件组信息
    var params4 = {
        "displaymode": "0",
        "title": "文件组",
        "binddata": {
            "keyfield": "FileGRd",
            "fields": [
                {
                    "caption": "文件组id",
                    "name": "FileGRd"
                }, {
                    "caption": "文件组名称",
                    "name": "FileGpName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"FileGpName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"FileGpName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/FileGroup/GetAllFileGInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "FileGRd": datas[i].FileGRd,
                            "FileGpName": datas[i].FileGpName
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

    $("#defaultSelect4").zc_select(params4);


    /*--------默认加载第一个树节点信息---------*/



    //局部刷新
    var loadPage = function () {
        var specInfoArr = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        request({url:"/Spec/GetAllSpecInfo",  data: {
                "ExecType": "00",
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "SpecName",
                            "FieldOpt": "Order BY"
                        }]}),
                "PageInfo":JSON.stringify(pageInfo)
            }},function (Body) {
            if (Body.MsgCode == "0x00000") {
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                if(treeData.length<=0){
                    return false;
                }

                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var specInfo = {
                        "id": treeData[i].SpecRd,
                        "name": treeData[i].SpecName
                    };
                    specInfoArr.push(specInfo);
                }
                config.data.source = specInfoArr;
                $.JstreeEx.init(config);
            }
        });


        //加载树形
        /*$.ajax({
            url: getBasePath() + '/Spec/GetAllSpecInfo',
            type: 'POST',
            data: {
                "ExecType": "00",
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "SpecName",
                            "FieldOpt": "Order BY"
                        }]}),
                "PageInfo":JSON.stringify(pageInfo)
            },
            success: function (data) {
                if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                    var treeData = data.Body.Data;
                    if (treeData.length > PageInfo.PageSize) {
                        $pages.css('display', 'block');
                    } else {
                        $pages.css('display', 'none');
                    }
                    if(treeData.length<=0){
                        return false;
                    }

                    var len = treeData.length >= 20 ? 20:treeData.length;
                    for (var i = 0; i < len; i++) {
                        var specInfo = {
                            "id": treeData[i].SpecRd,
                            "name": treeData[i].SpecName
                        };
                        specInfoArr.push(specInfo);
                    }
                    config.data.source = specInfoArr;
                    $.JstreeEx.init(config);
                }
            }
        })*/
    };
    loadPage();

    //新增
    $("#add").click(function () {
        clearForm("aa");
        defaultValueForNew();
        $("#defaultSelect").clearseldata("OpertRd");
        $("#defaultSelect1").clearseldata("DevGRd");
        $("#defaultSelect2").clearseldata("DCVerRd");
        $("#defaultSelect3").clearseldata("SGRd");
        $("#defaultSelect4").clearseldata("FileGRd");
        //$("#Print").clearseldata("PrRd");
        //$("#PrintT").clearseldata("PrintTRd");
        $("#hidden").attr("r", "");
        $(".right").show();
        $("#remove").attr("a", "");
        $(this).attr("e", "00");
        //更新根版本
        $("#modify").attr("c", "");
        $("#copy").attr("d", "");
        $("#removeVersion").attr("b", "");
        $("#addVersion").attr("ca", "");
        $("#isDef").attr("checked", true);
        $("#specName").attr("readonly", false);
        $("#version").attr("readonly", false);
        $("#isDef").attr("disabled", false);
        $("#opert").attr("disabled", false);
        $("#specName").val("");
        $("#device").attr("disabled", false);
        $("#status").attr("disabled", false);
        $("#beizhu").attr("readonly", false);
        $("#isstatus").val("可用");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
    });

    //删除
    $("#remove").click(function () {

        var tt = $(this).attr("a");
        if(tt!=treeID){
           toastr.warning("删除只针对主项进行操作,若要删除版本信息,请点击删除版本");
           return;
        }

        if (tt == null || tt == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            var bus = {
                "SpecRd": tt
            }

            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'] //按钮
            }, function () {

                request({url:"/Spec/SaveSpecInfo",data: {
                        "ExecType": "01",
                        "BusData": JSON.stringify(bus)
                    }},function (Body) {
                    if (Body.MsgCode == "0x00000") {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        currentPage = 0;
                        condition = ""
                        loadPage();  //局部刷新
                        $(".right").hide();
                        $("#remove").attr("a", "");
                        $("#copy").attr("d", "")
                    }
                });


             /*   $.ajax({
                    url: getBasePath() + "/Spec/SaveSpecInfo",
                    type: "POST",
                    data: {
                        "ExecType": "01",
                        "BusData": JSON.stringify(bus)
                    },
                    success: function (res) {
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            layer.closeAll("dialog");
                            toastr.success(res.Body.MsgDes);
                            currentPage = 0;
                            condition = ""
                            loadPage();  //局部刷新
                            $(".right").hide();
                            $("#remove").attr("a", "");
                            $("#copy").attr("d", "")
                        }
                    }
                });*/
            })
        }
    });

    //删除子版本
    $("#removeVersion").click(function () {

        var tt = $(this).attr("b");
        if (tt == null || tt == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                var bus = {
                    "SpecVerRd": tt
                };

                request({url:"/Spec/SaveSpecInfo",data: {
                        "ExecType": "05",
                        "BusData": JSON.stringify(bus)
                    }},function (Body) {
                    if (Body.MsgCode == "0x00000") {
                        layer.closeAll("dialog");
                        currentPage = 0;
                        condition = ""
                        toastr.success(Body.MsgDes);
                        loadPage();  //局部刷新
                        $(".right").hide();
                        $("#removeVersion").attr("b", "");
                    }
                });


               /* $.ajax({
                    url: getBasePath() + "/Spec/SaveSpecInfo",
                    type: "POST",
                    data: {
                        "ExecType": "05",
                        "BusData": JSON.stringify(bus)
                    },
                    success: function (res) {
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            layer.closeAll("dialog");
                            currentPage = 0;
                            condition = ""
                            toastr.success(res.Body.MsgDes);
                            loadPage();  //局部刷新
                            $(".right").hide();
                            $("#removeVersion").attr("b", "");
                        }
                    }
                });*/
            })
        }
    });

    //复制
    $("#copy").click(function () {
        var tt = $(this).attr("d");
        if (tt == null || tt == "") {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
        else {
            var data = {
                "SpecRd": tt
            };

            request({url:"/Spec/SaveSpecInfo",data: {
                    "ExecType": "03",
                    "busData": JSON.stringify(data)
                }},function (Body) {
                if (Body.MsgCode == "0x00000") {
                    toastr.success(Body.MsgDes);
                    currentPage = 0;
                    condition = ""
                    loadPage();//局部刷新
                    $("#copy").attr("d", "");
                    $("#remove").attr("a", "");
                }
            });



            /*$.ajax({
                url: getBasePath() + "/Spec/SaveSpecInfo",
                type: "POST",
                data: {
                    "ExecType": "03",
                    "busData": JSON.stringify(data)
                },
                success: function (res) {
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        toastr.success(res.Body.MsgDes);
                        currentPage = 0;
                        condition = ""
                        loadPage();//局部刷新
                        $("#copy").attr("d", "");
                        $("#remove").attr("a", "");
                    }
                }
            });*/
        }
    });

    //新增版本
    $("#addVersion").click(function () {
        var id = $(this).attr("c");
        if (id == null || id == "" || $(this).attr("ad") == "01") {
            toastr.warning("请选择左侧要新增版本的一项再进行新增!");
        }
        else {
            $("#defaultSelect").clearseldata("OpertRd");
            $("#defaultSelect1").clearseldata("DevGRd");
            $("#defaultSelect2").clearseldata("DCVerRd");
            $("#defaultSelect3").clearseldata("SGRd");
            $("#defaultSelect4").clearseldata("FileGRd");
            //$("#Print").clearseldata("PrRd");
            //$("#PrintT").clearseldata("PrintTRd");
            $("#version").val("");
            $("#addVersion").attr("a", "00");
            $("#hidden").attr("r", "01");
            $(".right").show();
            $("#modify").attr("c", "");
            $("#specName").attr("readonly", true);
            $("#version").attr("readonly", false);
            $("#isDef").attr("disabled", false);
            $("#opert").attr("disabled", false);
            $("#device").attr("disabled", false);
            $("#status").attr("disabled", false);
            $("#beizhu").attr("readonly", false);
            $("#isDef").attr("checked", false);
            $("#creatPeople").val("");
            $("#creatTime").val("");
            $("#modifyPeople").val("");
            $("#modifyTime").val("");
            $("#beizhu").val("");
        }
    });

    //保存
    $("#save").click(function () {
        //var PrintRd = $("#Print").getseldata().PrRd;
        //var PrintTRd = $("#PrintT").getseldata().PrintTRd;

        var specName = $("#specName").val();
        var version = $("#version").val();
        var remark = $("#beizhu").val();


        var opert = $("#defaultSelect").getseldata().OpertRd;
        var device = $("#defaultSelect1").getseldata().DevGRd;

        var status = $("#status").val();

        var DCVerRd = $("#defaultSelect2").getseldata().DCVerRd;

        var SkillGRd = $("#defaultSelect3").getseldata().SGRd;

        var FileGRd = $("#defaultSelect4").getseldata().FileGRd;
        var checked = $("input[type='checkbox']").prop("checked");
        if (checked == true) {
            checked = "00";
        } else {
            checked = "01";
        }
        newData = {
            "SpecName": specName,
            "Version": version,
            "IsDef": checked,
            "Status": status,
            "OptRd": opert,
            "DevGpRd": device,
            "Remark": remark,
            "DCVerRd": DCVerRd,
            "SkillGRd": SkillGRd,
            "FileGRd": FileGRd,
            //"PrintRd":PrintRd,
            //"PrintTRd":PrintTRd
        };
        //直接新增
        if ($("#add").attr("e") == "00") {
            if (specName != "" && specName != null && version != null
                && version != "" ) {

                request({url:"/Spec/SaveSpecInfo",data: {
                        "ExecType": "00",
                        "busData": JSON.stringify(newData)
                    }},function (Body) {
                    if (Body.MsgCode == "0x00000") {
                        toastr.success(Body.MsgDes);
                        condition = ""
                        currentPage = 0;
                        loadPage(); //局部刷新
                        $("#add").attr("e", "");
                    }
                    if (Body.MsgCode == "MG0006F") {
                        toastr.warning(res.Body.MsgDes);
                    }
                });


               /* $.ajax({
                    url: getBasePath() + "/Spec/SaveSpecInfo",
                    type: "POST",
                    data: {
                        "ExecType": "00",
                        "busData": JSON.stringify(newData)
                    },
                    success: function (res) {
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            toastr.success(res.Body.MsgDes);
                            condition = ""
                            currentPage = 0;
                            loadPage(); //局部刷新
                            $("#add").attr("e", "");
                        }
                        if (res.Status == "00" && res.Body.MsgCode == "MG0006F") {
                            toastr.warning(res.Body.MsgDes);
                        }
                    }
                });*/
            }/* else {
                if (opert == "") {
                    $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect").find("input:eq(0)").val("");
                    $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }*/
        }
        //更新保存
        if ($("#hidden").attr("r") == "00") {
            var specRd = $("#hidden").val();
            var specVerRd = $("#hidden").attr("h");
            newData1 = {
                "SpecRd": specRd,
                "SpecVerRd": specVerRd,
                "SpecName": specName,
                "Version": version,
                "IsDef": checked,
                "Status": status,
                "OptRd": opert,
                "DevGpRd": device,
                "Remark": remark,
                "DCVerRd": DCVerRd,
                "SkillGRd": SkillGRd,
                "FileGRd": FileGRd,
                //"PrintRd":PrintRd,
                //"PrintTRd":PrintTRd
            };
            //更新信息
            if (specName != "" && specName != null && version != null
                && version != "" ) {

                request({url:"/Spec/SaveSpecInfo",data: {
                        "ExecType": "02",
                        "busData": JSON.stringify(newData1)
                    }},function (Body) {
                    if (Body.MsgCode == "0x00000") {
                        toastr.success(Body.MsgDes);
                        condition = ""
                        currentPage = 0;
                        loadPage()//局部刷新
                        $("#hidden").val("");
                        $("#hidden").attr("h", "");
                        $("#modify").attr("c", "");
                        $("#add").attr("e", "");
                        $("#hidden").attr("r", "");
                        $("#specName").prop("readonly", false)
                    }
                    if (Body.MsgCode == "MG0006F") {
                        toastr.success(Body.MsgDes)
                    }
                    if (Body.MsgCode == "MG_MES4001811020007F") {
                        toastr.warning(Body.MsgDes);
                    }
                });


               /* $.ajax({
                    url: getBasePath() + "/Spec/SaveSpecInfo",
                    type: "POST",
                    data: {
                        "ExecType": "02",
                        "busData": JSON.stringify(newData1)
                    },
                    success: function (res) {
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            toastr.success(res.Body.MsgDes);
                            condition = ""
                            currentPage = 0;
                            loadPage()//局部刷新
                            $("#hidden").val("");
                            $("#hidden").attr("h", "");
                            $("#modify").attr("c", "");
                            $("#add").attr("e", "");
                            $("#hidden").attr("r", "");
                            $("#specName").prop("readonly", false)
                        }
                        if (res.Status == "00" && res.Body.MsgCode == "MG0006F") {
                            toastr.success(res.Body.MsgDes)
                        }
                        if (res.Status == "00" && res.Body.MsgCode == "MG_MES4001811020007F") {
                            toastr.warning(res.Body.MsgDes);
                        }
                    }
                });*/
            }/* else {
                if (opert == "") {
                    $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect").find("input:eq(0)").val("");
                    $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }*/
        }

        //新增版本
        if ($("#addVersion").attr("ad") == "00" && $("#addVersion").attr("a") == "00") {
            var opert = $("#defaultSelect").getseldata().OpertRd;
            var device = $("#defaultSelect1").getseldata().DevGRd;
            var DCVerRd = $("#defaultSelect2").getseldata().DCVerRd;
            var SkillGRd = $("#defaultSelect3").getseldata().SGRd;
            var FileGRd = $("#defaultSelect4").getseldata().FileGRd;

            var specName = $("#specName").val();
            var version = $("#version").val();
            var remark = $("#beizhu").val();

            var status = $("#status").val();

            if ($("input[type='checkbox']").is(':checked') == true) {
                var ck = "00";
            } else {
                var ck = "01";
            }
            var id = $("#remove").attr("a");
            newData3 = {
                "SpecRd": id,
                "SpecName": specName,
                "Version": version,
                "IsDef": ck,
                "Status": status,
                "OptRd": opert,
                "DevGpRd": device,
                "Remark": remark,
                "DCVerRd": DCVerRd,
                "SkillGRd": SkillGRd,
                "FileGRd": FileGRd,
                //"PrintRd":PrintRd,
                //"PrintTRd":PrintTRd
            };
            if (specName != "" && specName != null && version != null
                && version != "") {

                request({url:"/Spec/SaveSpecInfo",data: {
                        "ExecType": "04",
                        "busData": JSON.stringify(newData3)
                    }},function (Body) {
                    if (Body.MsgCode == "0x00000") {
                        toastr.success(Body.MsgDes);
                        condition = ""
                        currentPage = 0;
                        loadPage();//局部刷新
                        $("#addVersion").attr("c", "");
                        $("#addVersion").attr("ad", "");
                        $("#addVersion").attr("a", "");
                        $("#hidden").attr("r", "");

                    }
                    if (Body.MsgCode == "MG_MES4001811020007F") {
                        toastr.warning(res.Body.MsgDes);
                    }
                });


                /*$.ajax({
                    url: getBasePath() + "/Spec/SaveSpecInfo",
                    type: "POST",
                    data: {
                        "ExecType": "04",
                        "busData": JSON.stringify(newData3)
                    },
                    success: function (res) {
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            toastr.success(res.Body.MsgDes);
                            condition = ""
                            currentPage = 0;
                            loadPage();//局部刷新
                            $("#addVersion").attr("c", "");
                            $("#addVersion").attr("ad", "");
                            $("#addVersion").attr("a", "");
                            $("#hidden").attr("r", "");

                        }
                        if (res.Status == "00" && res.Body.MsgCode == "MG_MES4001811020007F") {
                            toastr.warning(res.Body.MsgDes);
                        }
                    }
                });*/
            } /*else {PrintTRd
                if (opert == "") {
                    $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect").find("input:eq(0)").val("");
                    $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }*/
        }
    });
})