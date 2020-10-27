$(function () {
    var tureeID=null;
    /*------------------点击事件的处理----------------*/
    var onClicks = function (id, text) {
        $(".right").show();
        var mydata = [];
        /*---------首先，定义表单规则，处理表单信息加载右边所有表单相关的信息--------------------*/
        tureeID= id.nodeID;

        $("#remove").attr("a", id.nodeID)
        $("#save").attr("b", id.nodeID)

        /*---------处理表单信息调用接口时，加载表格信息和表单信息---- 没问题----------------*/
        var objBusData = {"IQCRd": id.nodeID};
        var objData = {
            "ExecType": "00",
            "BusData": JSON.stringify(objBusData),
        };


        request({url:"/IQC/GetIQCBInfo",async: false,//设为同步请求
            data: objData,},function (Body) {
            var BadInfos = Body.Data.BadInfo;
            var CentInfos =Body.Data.CentInfo;
            if (Body.MsgCode == "0x00000") {
                var maData = Body.Data;
                //填充默认质检批次信息
                $("#MaCode").val(maData.MaCode);
                $("#Num").val(maData.Num);
                $("#MaName").val(maData.MaName);
                $("#creatPeople").val(maData.Creator);
                $("#creatTime").val(maData.CreateTime);
                $("#modifyPeople").val(maData.LastModifyMan);
                $("#modifyTime").val(maData.LastModifyTime);
                $("#beizhu").val(maData.Remark);

                if (maData.CkResult == "00") {
                    $('#CkResult').get(0).selectedIndex = 0;
                    $("#defaultSelect").clearseldata("ReaCode");
                    $("#cSearch").attr("disabled", true);
                    $("#defaultSelect").attr("disabled", true);
                }
                if (maData.CkResult == "01") {
                    $('#CkResult').get(0).selectedIndex = 1;
                    $("#cSearch").attr("disabled", false);
                    $("#defaultSelect").attr("disabled", false);
                }
                if (maData.CkResult == "02") {
                    $('#CkResult').get(0).selectedIndex = 2;
                    $("#cSearch").attr("disabled", false);
                    $("#defaultSelect").attr("disabled", false);
                }

                //表格定义
                var colNamesArr = [
                    {"Caption": "IQCBadRd", "Name": "IQCBadRd", "Hidden": "Hidden", "CType": "text"},
                    {"Caption": "不良代码", "Name": "ReaCode", "IsKey": true, "CType": "text"},
                    {"Caption": "不良描述", "Name": "ReaDes", "CType": "text"},
                ];
                var data = [];
                for (var i = 0; i < BadInfos.length; i++) {
                    var _data = {
                        IQCBadRd: BadInfos[i].IQCBadRd,
                        ReaCode: BadInfos[i].ReaCode,
                        ReaDes: BadInfos[i].ReaDes,
                    };
                    data.push(_data);
                    $("#defaultSelect").showData({
                        id: BadInfos[0].ReaCode,
                        name: BadInfos[0].ReaDes,
                        keyfield: "ReaCode",
                        fields: [
                            {"name": "ReaCode"},
                            {"name": "ReaDes"}
                        ]
                    });
                }

                var config = {
                    tableId: "list9",
                    data: data,
                    colArr: colNamesArr,
                    multiselect: true,
                    width:0.84,
                    height:0.46
                };
                fullTable(config);


                //表格定义
                var colNamesArrCent = [
                    {"Caption": "IQCCentRd", "Name": "IQCCentRd", "Hidden": "Hidden", "CType": "text"},
                    {"Caption": "文件名称", "Name": "FileName", "CType": "text"},
                    {"Caption": "最后一次上传时间", "Name": "LastUpDate", "CType": "text"},
                ];
                var dataCent = [];

                for (var i = 0; i < CentInfos.length; i++) {
                    var _dataCent = {
                        IQCCentRd: CentInfos[i].IQCCentRd,
                        FileName: CentInfos[i].FileName,
                        LastUpDate: CentInfos[i].LastUpDate,
                    };
                    dataCent.push(_dataCent);
                }
                var configCent = {
                    tableId: "list10",
                    data: dataCent,
                    colArr: colNamesArrCent,
                    multiselect: true,
                    width:0.84,
                    height:0.46
                };
                fullTable(configCent);
            }
        });
        /*$.ajax({
            url: getBasePath() + "/IQC/GetIQCBInfo",
            type: "POST",
            async: false,//设为同步请求
            data: objData,
            success: function (res) {
                var BadInfos = res.Body.Data.BadInfo;
                var CentInfos = res.Body.Data.CentInfo;
                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                    var maData = res.Body.Data;
                    //填充默认质检批次信息
                    $("#MaCode").val(maData.MaCode);
                    $("#Num").val(maData.Num);
                    $("#MaName").val(maData.MaName);
                    $("#creatPeople").val(maData.Creator);
                    $("#creatTime").val(maData.CreateTime);
                    $("#modifyPeople").val(maData.LastModifyMan);
                    $("#modifyTime").val(maData.LastModifyTime);
                    $("#beizhu").val(maData.Remark);

                    if (maData.CkResult == "00") {
                        $('#CkResult').get(0).selectedIndex = 0;
                        $("#defaultSelect").clearseldata("ReaCode");
                        $("#cSearch").attr("disabled", true);
                        $("#defaultSelect").attr("disabled", true);
                    }
                    if (maData.CkResult == "01") {
                        $('#CkResult').get(0).selectedIndex = 1;
                        $("#cSearch").attr("disabled", false);
                        $("#defaultSelect").attr("disabled", false);
                    }
                    if (maData.CkResult == "02") {
                        $('#CkResult').get(0).selectedIndex = 2;
                        $("#cSearch").attr("disabled", false);
                        $("#defaultSelect").attr("disabled", false);
                    }

                    //表格定义
                    var colNamesArr = [
                        {"Caption": "IQCBadRd", "Name": "IQCBadRd", "Hidden": "Hidden", "CType": "text"},
                        {"Caption": "不良代码", "Name": "ReaCode", "IsKey": true, "CType": "text"},
                        {"Caption": "不良描述", "Name": "ReaDes", "CType": "text"},
                    ];
                    var data = [];
                    for (var i = 0; i < BadInfos.length; i++) {
                        var _data = {
                            IQCBadRd: BadInfos[i].IQCBadRd,
                            ReaCode: BadInfos[i].ReaCode,
                            ReaDes: BadInfos[i].ReaDes,
                        };
                        data.push(_data);
                        $("#defaultSelect").showData({
                            id: BadInfos[0].ReaCode,
                            name: BadInfos[0].ReaDes,
                            keyfield: "ReaCode",
                            fields: [
                                {"name": "ReaCode"},
                                {"name": "ReaDes"}
                            ]
                        });
                    }

                    var config = {
                        tableId: "list9",
                        data: data,
                        colArr: colNamesArr,
                        multiselect: true,
                        width:0.84,
                        height:0.46
                    };
                    fullTable(config);


                    //表格定义
                    var colNamesArrCent = [
                        {"Caption": "IQCCentRd", "Name": "IQCCentRd", "Hidden": "Hidden", "CType": "text"},
                        {"Caption": "文件名称", "Name": "FileName", "CType": "text"},
                        {"Caption": "最后一次上传时间", "Name": "LastUpDate", "CType": "text"},
                    ];
                    var dataCent = [];

                    for (var i = 0; i < CentInfos.length; i++) {
                        var _dataCent = {
                            IQCCentRd: CentInfos[i].IQCCentRd,
                            FileName: CentInfos[i].FileName,
                            LastUpDate: CentInfos[i].LastUpDate,
                        };
                        dataCent.push(_dataCent);
                    }
                    var configCent = {
                        tableId: "list10",
                        data: dataCent,
                        colArr: colNamesArrCent,
                        multiselect: true,
                        width:0.84,
                        height:0.46
                    };
                    fullTable(configCent);
                }
            }
        });*/
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
        currentPage = treeSearchs("/IQC/GetAllIQCBInfo","IQCRd","Batch","Batch",condition,currentPage,config);
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
            currentPage = treeSearchs("/IQC/GetAllIQCBInfo","IQCRd","Batch","Batch",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/IQC/GetAllIQCBInfo","IQCRd","Batch","Batch",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/IQC/GetAllIQCBInfo","IQCRd","Batch","Batch",condition,currentPage,config);
    });
    $(".right").hide();
    var rule = [{
        "ctlid": "MaCode", //自定义名字：标签id名字
        "param": "MaCode" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "MaName",
        "param": "MaName"
    }, {
        "ctlid": "Num",
        "param": "Num"
    }, {
        "ctlid": "Num",
        "param": "Num"
    }, {
        "ctlid": "creatPeople",
        "param": "Creator"
    }, {
        "ctlid": "creatTime",
        "param": "CreateTime"
    }, {
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    }, {
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    }, {
        "ctlid": "beizhu",
        "param": "Remark"
    }];

    //原因代码
    var dat1 = [];
    var params = {
        "displaymode": "0",
        "title": "原因代码",
        "binddata": {
            "keyfield": "ReaCode",
            "fields": [
                {
                    "caption": "原因代码id",
                    "name": "ReaCode"
                }, {
                    "caption": "原因描述",
                    "name": "ReaDes"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                var va = res.ReaCode;
                var vb = res.ReaDes;
                var newdata = {
                    "ReaCode": va,
                    "ReaDes": vb
                }
                dat1.push(newdata);

                addSrow("list4", newdata, 0, true);
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ReaDes",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                        url: "/Reason/GetAllReaInfo",
                        data: {
                            "ExecType": "00",
                            "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)
                        }
                    },
                    function (Body) {
                        var datas = Body.Data;
                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "ReaCode": datas[i].ReaCode,
                                "ReaDes": datas[i].ReaDes,
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
    $("#defaultSelect").zc_select(params);

    var newTree = [];
    var loadTree = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };


        request({url:"/IQC/GetAllIQCBInfo",async: false,
            data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo)}},function (Body) {
            if (Body.MsgCode == "0x00000") {

                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].IQCRd == undefined ? "" : treeData[i].IQCRd,
                        name: treeData[i].Batch == undefined ? "" : treeData[i].Batch
                    };
                    newTree.push(tree);
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }
        });

        /*$.ajax({
            url: getBasePath() + "/IQC/GetAllIQCBInfo",
            type: "POST",
            async: false,
            data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo)},
            success: function (res) {
                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {

                    var treeData = res.Body.Data;
                    if (treeData.length > PageInfo.PageSize) {
                        $pages.css('display', 'block');
                    } else {
                        $pages.css('display', 'none');
                    }
                    var len = treeData.length >= 20 ? 20:treeData.length;
                    for (var i = 0; i < len; i++) {
                        var tree = {
                            id: treeData[i].IQCRd == undefined ? "" : treeData[i].IQCRd,
                            name: treeData[i].Batch == undefined ? "" : treeData[i].Batch
                        };
                        newTree.push(tree);
                        treedataList.push(tree);
                    }
                    config.data.source = treedataList;
                    $.JstreeEx.init(config);//先调用后加载
                }
            }
        });*/
    }
    loadTree();


    $('#CkResult').change(function () {
        var ckresult = $(this).find("option:checked").val();

        if (ckresult == "00") {
            $("#cSearch").attr("disabled", true);
            $("#defaultSelect").attr("readonly", true);
        } else {
            $("#cSearch").attr("disabled", false);
            $("#defaultSelect").attr("readonly", false);
        }
    });


    //删除
    $("#remove").click(function () {
        layer.confirm("是否确认删除?", function () {
            var IQCRd = $("#remove").attr("a");
            var data = {
                "IQCRd": IQCRd
            }

            request({url:"/IQC/SaveIQCBInfo",data: {
                    "ExecType": "01",
                    "BusData": JSON.stringify(data)
                }},function (Body) {
                layer.closeAll('dialog');
                if (Body.MsgCode == "0x00000") {
                    toastr.success(Body.MsgDes);
                    currentPage = 0;
                    loadTree()
                    $(".right").hide();
                } else {
                    toastr.warning(Body.MsgDes);
                }

            });

            /*$.ajax({
                url: getBasePath() + "/IQC/SaveIQCBInfo",
                type: "POST",
                data: {
                    "ExecType": "01",
                    "BusData": JSON.stringify(data)
                },
                success: function (res) {
                    layer.closeAll('dialog');
                    if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                        toastr.success(res.Body.MsgDes);
                        currentPage = 0;
                        loadTree()
                    } else {
                        toastr.warning(res.Body.MsgDes);
                    }
                }
            });*/

        });

        /* */
    });

    //删除原因代码
    $("#deleteReaCode").click(function () {
        derow("list9");
    });
    //更新保存
    $("#save").click(function () {
        var badData1 = getRowData("list9");
        var badData3 = [];
        for (var i = 0; i < badData1.length; i++) {
            var badData2 = {
                "IQCBadRd": badData1[i].IQCBadRd,
                "ReaCode": badData1[i].ReaCode,
                "ReaDes": badData1[i].ReaDes
            };
            badData3.push(badData2);
        }

        var centinfo1 = getRowData("list10");
        var centinfo3 = [];
        for (var i = 0; i < centinfo1.length; i++) {
            var centinfo2 = {
                "IQCCentRd": centinfo1[i].IQCCentRd,
                "FileName": centinfo1[i].FileName
            };
            centinfo3.push(centinfo2);
        }

        if ($("#CkResult option:checked").attr("value") == "00") {
            var str = "00";
            badData3 = null;
        }
        if ($("#CkResult option:checked").attr("value") == "01") {
            var str = "01";
        }
        if ($("#CkResult option:checked").attr("value") == "02") {
            var str = "02";
        }

        var IQCRd = $("#save").attr("b");

        var newDate = {
            "IQCRd": IQCRd,
            "CkResult": str,
            "Remark": "测试",
            "BadInfo": badData3,
            "CentInfo": centinfo3
        };
        request({url:"/IQC/SaveIQCBInfo",data: {
                "ExecType": "02",
                "busData": JSON.stringify(newDate)
            }},function (Body) {
            if (Body.MsgCode == "0x00000") {
                toastr.success(Body.MsgDes);
                currentPage = 0;
                condition = '';
                loadTree()
            } else {
                toastr.warning(Body.MsgDes);

            }
        });
       /* $.ajax({
            url: getBasePath() + "/IQC/SaveIQCBInfo",
            type: "POST",
            data: {
                "ExecType": "02",
                "busData": JSON.stringify(newDate)
            },
            success: function (res) {
                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                    toastr.success(res.Body.MsgDes);
                    currentPage = 0;
                    condition = '';
                    loadTree()
                } else {

                    toastr.warning(res.Body.MsgDes);

                }
            }
        });*/

    });

    /*-----删除质检文件信息---------*/
    $(".del1").click(function () {
        derow("list10");
    });

    //新增弹出框    新增质检批次信息
    $(".cAdd").click(function () {
        top.layer.open({
            title: '新增',
            type: 2,
            area: ['80%', '80%'],
            content: getBasePath() + '/IQC/iqcAdd',
            end:function () {
                layer.closeAll();
                location.reload();
            }
        });

    });

    //新增弹出框    新增质检报告文件
    $(".add1").click(function () {
        window.sessionStorage.setItem("IQCGd",tureeID);
        top.layer.open({
            title: '新增',
            type: 2,
            area: ['80%', '80%'],
            content: getBasePath() + '/IQC/iqcFileAdd',
            end: function () {

                layer.closeAll();
                location.reload();
            }
        });
    });
});