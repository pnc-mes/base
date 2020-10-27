$(function () {

    var wgBadInfos = [];
    var busData=[];
    var a=function(){

        var data=[];

        //外观等级

        var arr1 = [];
        var arr2 = [];
        request({
            url: "/CI/GetAllCIInfo",
            data: {
                "ExecType": "00",
                "InitData": JSON.stringify({
                    "FiledList": [{
                        "FieldName": "CheckItemName",
                        "FieldOpt": "Order BY",
                        /*   "FieldVal": "00"*/
                    }]
                })
            }
        }, function (Body) {
            arr1 = Body.Data;
        });
        arr1.forEach(function (obj) {
            var base={"id":"","name":""};
            base.id=obj.CIRd+"-"+"00";
            base.name=obj.CheckItemName;
            wgBadInfos.push(base);
        })

        request({
            url: "/CT/GetAllCTInfo",
            data: {
                "ExecType": "00",
                "InitData": JSON.stringify({
                    "FiledList": [{
                        "FieldName": "CheckTypeName",
                        "FieldOpt": "Order BY",
                        "FieldVal": "00"
                    }]
                })
            }
        }, function (Body) {
            arr2 = Body.Data;
        });
        arr2.forEach(function (obj) {
            var base={"id":"","name":""};
            base.id=obj.CTRd+"-"+"01";
            base.name=obj.CheckTypeName;
            wgBadInfos.push(base);
        })
        wgBadInfos =wgBadInfos;
    }

    a();

    var colNamesArr = [
        {"Caption": "id", "Name": "RelRd", "Hidden": true},
        {"Caption": "模板项目", "Name": "RelType", "CType": "text", "Editable": false,"Width":60},
        {"Caption": "id", "Name": "id", "CType": "text", "Hidden": true},
/*        {"Caption": "终检", "Name": "TPreGradeName1", "CType": "text", "Editable": false},
        {"Caption": "判断等级", "Name": "TargetGradeName1", "CType": "text", "Editable": false},
        {"Caption": "进入待评审", "Name": "IsReview", "CType": "text", "Editable": false},*/
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.6,
        height: 0.415
    };
    config1.data.length = 0;
    fullTable(config1);//加载空表格

    var clickSelect = function () {
        $(".RelType").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos[0].id);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos.length; i++) {
                if (current_Td_Val == wgBadInfos[i].name)
                    str += "<option selected value='" + wgBadInfos[i].id + "'>" + wgBadInfos[i].name + "</option>";
                else
                    str += "<option value='" + wgBadInfos[i].id + "'>" + wgBadInfos[i].name + "</option>";
            }
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            var str_Width = $(this).css("width");
            var index = str_Width.indexOf("px");
            var width = parseInt(str_Width.substring(0, index)) * 0.95;
            $(this).find("select").css("width", "150px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
            $(this).find("select").on("change", function () {
                for (var i = 0; i < wgBadInfos.length; i++) {
                    if (wgBadInfos[i].name == $(this).find("option:selected").text())
                        $(this).parent().prev().text(wgBadInfos[i].id);
                }
            })
        });
    }
    var treeID = null;
    $(".add1").click(function () {
        addErow("list4");

        var trObj = $("#list4 tbody>tr:eq(1)");
        trObj.find("td").each(function () {
            if ($(this).attr("class") == "RelType") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < wgBadInfos.length; i++) {
                    if (current_Td_Val == wgBadInfos[i].name) {
                        str += "<option selected value='" + wgBadInfos[i].id + "'>" + wgBadInfos[i].name + "</option>";
                    }

                    else {
                        str += "<option value='" + wgBadInfos[i].id + "'>" + wgBadInfos[i].name + "</option>";
                        $(this).prev().text(wgBadInfos[0].id);
                    }

                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                var str_Width = $(this).css("width");
                var index = str_Width.indexOf("px");
                var width = parseInt(str_Width.substring(0, index)) * 0.95;
                $(this).find("select").css("width", "150px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
                /*$(this).find("select").on("change", function () {
                 for (var i = 0; i < BadInfos.length; i++) {
                 if (BadInfos[i].BadTypeName == $(this).find("option:selected").text())

                 }
                 })*/
            }
        });

        clickSelect();

    });

    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {

        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CPTRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/CTP/GetCTPInfo", data: objData,}, function (Body) {
            $("#CheckTempName").val(Body.Data.CheckTempName);
            $("#Status").val(Body.Data.Status);
            $("#sliname").val(Body.Data.CheckTempName);
            $("#slinameRd").val(Body.Data.CPTRd);
            //点击左侧列表获取外观检验数据
            if (Body.Data.CPTInfo.length > 0) {
                 busData = Body.Data.CPTInfo;
                var CPTInfo = [];
                for (var i = 0; i < busData.length; i++) {
                    var wgData = {
                        "RelType": busData[i].RelName,
                        "RelRd": busData[i].RelRd+"-"+busData[i].RelType,
                        "RelName": busData[i].RelName,
                    /*    "PreGradeName1": busData[i].PreGradeName,
                        "TPreGradeName1": busData[i].TPreGradeName,
                        "TargetGradeName1": busData[i].TargetGradeName*/
                    };
                    CPTInfo.push(wgData);
                }
                var config1 = {
                    tableId: 'list4',
                    data: CPTInfo,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.6,
                    height: 0.415
                };
                fullTable(config1);//加载空表格
            }

            //外观
            clickSelect();

            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);


        });
    };
    /*----------------------定义控件规则-------------------*/
    var config = {
        id: "jstree_demo1",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        event: {
            onClick: onClicks
        }
    };
    var $pages = $('.pages');

    var condition = "";

    var currentPage = 0;
    var PageInfo = {
        "PageSize": 20,
        "PageIndex": currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/CTP/GetAllCTPInfo", "CPTRd", "CheckTempName", "CheckTempName", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function (event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/CTP/GetAllCTPInfo", "CPTRd", "CheckTempName", "CheckTempName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/CTP/GetAllCTPInfo", "CPTRd", "CheckTempName", "CheckTempName", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/CTP/GetAllCTPInfo", "CPTRd", "CheckTempName", "CheckTempName", condition, currentPage, config);
    });

    var material = {
        "displaymode": "1",
        "title": "物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "MaVerRd",
                    "name": "MaVerRd"
                }, {
                    "caption": "物料名称",
                    "name": "MaName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + excludeSpecial(o.condition) + "%"
                        }, {
                            "FieldName": "MaterialName",
                            "FieldOpt": "Order BY"
                        },
                        {
                            "FieldName": "MaterialType",
                            "FieldOpt": "in",
                            "FieldVal": "(01,00)"
                        }
                    ]
                };

                var obj = {
                    data: {"ExecType": "00", "InitData": JSON.stringify(InitData)},
                    url: "/Material/GetAllMaInfo"
                };
                var xldata = [];
                request(obj, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "MaVerRd": datas[i].MaVerRd,
                            "MaName": datas[i].MaName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data: xldata,
                    showrow: 50
                };
                return obj;
            }
        }
    };

    $("#material").zc_select(material);


    $("#_right").hide();

    $(".del1").click(function () {
        derow("list4");
    });


    //加载
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        request({
            url: '/CTP/GetAllCTPInfo',
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CheckTempName",
                            "FieldOpt": "Order BY"
                        }]
                })
            }
        }, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].CPTRd == undefined ? "" : treeData[i].CPTRd,
                    name: treeData[i].CheckTempName == undefined ? "" : treeData[i].CheckTempName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
        //外观
        clickSelect();
        /*
         //EL
         clickSelect_2();
         clickSelect1_2();
         clickSelect2_2();

         //外观与EL
         clickSelect();
         clickSelect1();
         clickSelect2();

         clickSelect3();*/
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        //外观
        clickSelect();

        $("#ExecType").val("00");
        $("#djpdlx").val("00");
        treeID = null;
        $("#_right").show();
        $("#djpdmc").val("");

        $("#CheckTempName").val("");
        $("#Status").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $("#material").clearseldata("MaVerRd");
        $("#sliname").val("");
        $("#slinameRd").val("");
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.6,
            height: 0.415
        };
        config1.data.length = 0;
        fullTable(config1);//加载空表格

    });

    /*--------------顶部菜单点击编辑按钮----------*/
    $(".cEdit").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            $("#ExecType").val("02");
            $("#cpName").attr("disabled", false);
        } else {
            toastr.warning("请选择左侧要编辑的一项再进行编辑!");
        }

    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/CTP/SaveCTPInfo",
                        data: {"ExecType": "01", "busData": "{\"CPTRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage = 0;
                        condition = '';
                        loaddata();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    $("a[name='copy']").click(function () {

        $("#myModal").modal("show");
    });
    $("#save").click(function () {
        var objBusData = $("#slinameRd").val();
        if (objBusData == null || objBusData == "") {
            toastr.warning("请选择复制内容！");
            return false;
        }
     var objBusData = JSON.stringify({"CPTRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        var stationSize;
        var CheckTempName = "";
        CheckTempName = $("#sliname").val();
        request({url: "/CTP/GetCTPInfo", data:objData}, function (Body) {
            stationSize = Body.Data.CPTRd;
        })
        var newData = {"CPTRd": stationSize, "CheckTempName": CheckTempName};
            request({
                url: "/CTP/SaveCTPInfo",
                data: {"ExecType": "03", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#sliname").val('');
                loaddata();
            });

        $("#myModal").modal("hide");
    });
/*    $("a[name='copy']").click(function () {
        $("#myModal").modal("show");
        if (treeID != undefined && treeID != null && treeID.trim() != "") {

            request({
                    url:"/CTP/SaveCTPInfo",
                    data: {"ExecType": "03","busData": "{\"CPTRd\":" + treeID + "}"}},
                function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    treeID = null;
                    condition = "";
                    currentPage = 0;
                    loaddata();
                });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });*/


    /*--------------顶部菜单点击保存按钮----------*/

    $(".cSave").click(function () {

        var CheckTempName = $("#CheckTempName").val().trim();
        if (CheckTempName == null || CheckTempName == "") {
            toastr.warning("检验模板名称不能为空");
            return false;
        }
        var Status = $("#Status").val();
        if (Status == null || Status == "") {
            toastr.warning("状态不能为空");
            return false;
        }

        var list4Data = getTableData("list4");

        var Remark = $("#beizhu").val().trim();
        var newData = {};

        //新增
        if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
            var wglist4Data = [];
            var ellist5Data = [];
            if (list4Data.length > 0) {
                var k = 1;
                for (var i  in  list4Data) {
                    if (list4Data[i].RelType == "" || list4Data[i].RelType == null) {
                        toastr.warning("第" + k + "行的数据为空，不能保存");
                        return false;
                    }

                    var wgData = {
                        "RelType": list4Data[i].RelRd.split("-")[1],
                        "RelRd":list4Data[i].RelRd.split("-")[0],
                    };
                    wglist4Data.push(wgData);
                    k++;
                }
            }

            newData = {
                "CheckTempName": CheckTempName,
                "Status": Status,
                "CPTInfo": wglist4Data,
                "Remark": Remark
            };
            console.log(newData);
            request({
                url: "/CTP/SaveCTPInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        } else if (treeID != null && treeID != "") {
            clickSelect();
            var wglist4Data = [];
            var ellist5Data = [];
            if (list4Data.length > 0) {
                var k = 1;
                for (var i  in  list4Data) {
                    if (list4Data[i].RelType == "" || list4Data[i].RelType == null) {
                        toastr.warning("第" + k + "行的数据为空，不能保存");
                        return false;
                    }
            /*        var aa=[];
                    aa=  list4Data[i].RelType*/

                    var wgData = {
                        "RelType": list4Data[i].RelRd.split("-")[1],
                        "RelRd": list4Data[i].RelRd.split("-")[0],
                    }
                 //   if(如果点击了下拉框){
                /*        var wgData = {
                            "RelType": list4Data[i].RelType.split("-")[1],
                            "RelRd": list4Data[i].RelType.split("-")[0],
                        };*/
                  //  }else{
                 /*       wgData={
                            "RelType": list4Data[i].RelType,
                            "RelRd": list4Data[i].RelRd,
                        }*/
               //     }
                    wglist4Data.push(wgData);
                    k++;
                }
            }
            newData = {
                "CPTRd": treeID,
                "CheckTempName": CheckTempName,
                "Status": Status,
                "CPTInfo": wglist4Data,
                "Remark": Remark
            };
            console.log(newData);
            request({
                url: "/CTP/SaveCTPInfo",
                data: {"ExecType": "02", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        }
    });
});
