$(function () {

    //外观等级
    var wgBadInfos = [];
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
        wgBadInfos = Body.Data;
    });

    //EL等级
    var elBadInfos = [];
    request({
        url: "/SunPort/Grade/GetAllGradeInfo",
        data: {
            "ExecType": "00",
            "InitData": JSON.stringify({
                "FiledList": [{
                    "FieldName": "GradeType",
                    "FieldOpt": "=",
                    "FieldVal": "01"
                }]
            })
        }
    }, function (Body) {
        elBadInfos = Body.Data;
    });

    //所有等级
    var BadInfos = [];
    request({url: "/CI/GetAllCIInfo", data: {"ExecType": "00"}}, function (Body) {
        var resp = Body.Data;
        for (var i = 0; i < resp.length; i++) {
            var item = resp[i];
            for (var j = 0, jlen = BadInfos.length; j <= jlen; j++) {
                if (BadInfos[j] != undefined && BadInfos[j] != "") {
                    if (item.CheckItemName == BadInfos[j].CheckItemName) {
                        break;
                    }
                }
                if (j == jlen) {
                    BadInfos.push(item);
                }
            }
        }

    });
    var colNamesArr = [
        {"Caption": "id", "Name": "CheckTypeDtlRd", "Hidden": true},
       // {"Caption": "检验项代码", "Name": "CheckItemCode", "Hidden": true},
        {"Caption": "检验项", "Name": "CheckItemName", "CType": "text", "Editable": false,"Width":60},
        {"Caption": "检验项内容", "Name": "CheckItemC", "Editable": true, "CType": "text","Width":300},
        {"Caption": "确认方式", "Name": "SureType", "Editable": false, "CType": "text"},
    ]
    // 定义表格中下拉框“数据类型”的数组
    var ItemTypes1 = [
        {TypeText: "打勾", TypeVal: "00"},
        {TypeText: "记录结果", TypeVal: "01"}
    ];
    // 定义表格中下拉框的“数据类型”的事件
    var select1 = function () {
        $(".SureType").each(function () {
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < ItemTypes1.length; i++) {
                if (current_Td_Val == ItemTypes1[i].TypeVal)
                    str += "<option selected value='" + ItemTypes1[i].TypeVal + "'>" + ItemTypes1[i].TypeText + "</option>";
                else
                    str += "<option value='" + ItemTypes1[i].TypeVal + "'>" + ItemTypes1[i].TypeText + "</option>";
            }
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
        });
    };

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


    var colNamesArr5 = [
        {"Caption": "id", "Name": "LRDtlRd", "Hidden": true},
        {"Caption": "前EL", "Name": "PreGradeName2", "CType": "text", "Editable": false},
        {"Caption": "后EL", "Name": "TPreGradeName2", "CType": "text", "Editable": false},
        {"Caption": "判断等级", "Name": "TargetGradeName2", "CType": "text", "Editable": false},
        {"Caption": "进入待评审", "Name": "IsReview", "CType": "text", "Editable": false},
    ];
    var config5 = {
        tableId: 'list5',
        data: [],
        colArr: colNamesArr5,
        multiselect: true,
        width: 0.6,
        height: 0.415
    };
    config5.data.length = 0;
    fullTable(config5);//加载空表格


    var colNamesArr6 = [
        {"Caption": "id", "Name": "LRDtlRd", "Hidden": true},
        {"Caption": "外观检验等级", "Name": "PreGradeName", "CType": "text", "Editable": false},
        {"Caption": "EL检验等级", "Name": "TPreGradeName", "CType": "text", "Editable": false},
        {"Caption": "判断等级", "Name": "TargetGradeName", "CType": "text", "Editable": false},
        {"Caption": "进入待评审", "Name": "IsReview", "CType": "text", "Editable": false},
    ];
    var config6 = {
        tableId: 'list6',
        data: [],
        colArr: colNamesArr6,
        multiselect: true,
        width: 0.6,
        height: 0.415
    };
    config6.data.length = 0;
    fullTable(config6);//加载空表格

    //外观检验等级
    var clickSelect_1 = function () {
        $(".CheckItemName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos[0].CheckItemCode);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos.length; i++) {
                if (current_Td_Val == wgBadInfos[i].CheckItemName)
                    str += "<option selected>" + wgBadInfos[i].CheckItemName + "</option>";
                else
                    str += "<option >" + wgBadInfos[i].CheckItemName + "</option>";
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
                    if (wgBadInfos[i].CheckItemName == $(this).find("option:selected").text()){
                  //  alert(wgBadInfos[i].CheckItemName+"----------------"+wgBadInfos[i].CheckItemCode)
                    }
                        //this).parent().prev().text(wgBadInfos[i].BadRd);
                }
            })
        });
    };

    var clickSelect1_1 = function () {
        $(".TPreGradeName1").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos.length; i++) {
                if (current_Td_Val == wgBadInfos[i].GradeName)
                    str += "<option selected>" + wgBadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + wgBadInfos[i].GradeName + "</option>";
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
                    if (wgBadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(wgBadInfos[i].BadRd);
                }
            })
        });
    };

    var clickSelect2_1 = function () {
        $(".TargetGradeName1").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos.length; i++) {
                if (current_Td_Val == wgBadInfos[i].GradeName)
                    str += "<option selected>" + wgBadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + wgBadInfos[i].GradeName + "</option>";
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
                    if (wgBadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(wgBadInfos[i].BadRd);
                }
            })
        });
    };

    //EL检验等级
    var clickSelect_2 = function () {
        $(".PreGradeName2").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(elBadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < elBadInfos.length; i++) {
                if (current_Td_Val == elBadInfos[i].GradeName)
                    str += "<option selected>" + elBadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + elBadInfos[i].GradeName + "</option>";
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
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (elBadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(elBadInfos[i].BadRd);
                }
            })
        });
    };

    var clickSelect1_2 = function () {
        $(".TPreGradeName2").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(elBadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < elBadInfos.length; i++) {
                if (current_Td_Val == elBadInfos[i].GradeName)
                    str += "<option selected>" + elBadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + elBadInfos[i].GradeName + "</option>";
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
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (elBadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(elBadInfos[i].BadRd);
                }
            })
        });
    };

    var clickSelect2_2 = function () {
        $(".TargetGradeName2").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(elBadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < elBadInfos.length; i++) {
                if (current_Td_Val == elBadInfos[i].GradeName)
                    str += "<option selected>" + elBadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + elBadInfos[i].GradeName + "</option>";
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
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (elBadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(elBadInfos[i].BadRd);
                }
            })
        });
    };

    //外观与EL
    //外观
    var clickSelect = function () {
        $(".PreGradeName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(wgBadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < wgBadInfos.length; i++) {
                if (current_Td_Val == wgBadInfos[i].GradeName)
                    str += "<option selected>" + wgBadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + wgBadInfos[i].GradeName + "</option>";
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
                    if (wgBadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(wgBadInfos[i].BadRd);
                }
            })
        });
    }

    //EL
    var clickSelect1 = function () {
        $(".TPreGradeName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(elBadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < elBadInfos.length; i++) {
                if (current_Td_Val == elBadInfos[i].GradeName)
                    str += "<option selected>" + elBadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + elBadInfos[i].GradeName + "</option>";
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
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (elBadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(elBadInfos[i].BadRd);
                }
            })
        });
    }

    var clickSelect2 = function () {
        $(".TargetGradeName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).prev().text(BadInfos[0].BadRd);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < BadInfos.length; i++) {
                if (current_Td_Val == BadInfos[i].GradeName)
                    str += "<option selected>" + BadInfos[i].GradeName + "</option>";
                else
                    str += "<option >" + BadInfos[i].GradeName + "</option>";
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
                for (var i = 0; i < BadInfos.length; i++) {
                    if (BadInfos[i].GradeName == $(this).find("option:selected").text())
                        $(this).parent().prev().text(BadInfos[i].BadRd);
                }
            })
        });
    };

    var clickSelect3 = function () {
        $(".IsReview").each(function () {
            var current_Td_Val = $(this).text();
            var str = "";
            if (current_Td_Val == "00")
                str += "<input type='checkbox' name='Checked' checked value='00'/>";
            else
                str += "<input type='checkbox' name='Checked' value='01'/>";
            $(this).html(str);
        });
        $("input[name='Checked']").on("click", function () {
            if ($(this).prop("checked"))
                $(this).val("00");
            else
                $(this).val("01");
        });
    }


    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {

        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CTRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/CT/GetCTInfo", data: objData,}, function (Body) {
            $("#CheckTypeName").val(Body.Data.CheckTypeName);
            $("#CheckTypeCode").val(Body.Data.CheckTypeCode);
            //点击左侧列表获取外观检验数据
            if (Body.Data.CTDtlInfo.length > 0) {
                var busData = Body.Data.CTDtlInfo;
                var CTDtlInfo = [];
                for (var i = 0; i < busData.length; i++) {
                    var wgData = {
                        "CheckTypeDtlRd": busData[i].CheckTypeDtlRd,
                        "CheckItemName": busData[i].CheckItemName,
                        "CheckItemC": busData[i].CheckItemC,
                        "SureType": busData[i].SureType
                    };
                    CTDtlInfo.push(wgData);
                }
                var config1 = {
                    tableId: 'list4',
                    data: CTDtlInfo,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.6,
                    height: 0.415
                };
                fullTable(config1);//加载空表格
            }
            //点击左侧列表获取EL检验数据
       /*     if (Body.Data.LRDtlInfo01.length > 0) {
                var busData = Body.Data.LRDtlInfo01;
                var LRDtlInfo01 = [];
                for (var i = 0; i < busData.length; i++) {
                    var elData = {
                        "IsReview": busData[i].IsReview,
                        "LRDtlRd": busData[i].LRDtlRd,
                        "PreGradeName2": busData[i].PreGradeName,
                        "TPreGradeName2": busData[i].TPreGradeName,
                        "TargetGradeName2": busData[i].TargetGradeName
                    };
                    LRDtlInfo01.push(elData);
                }
                var config5 = {
                    tableId: 'list5',
                    data: LRDtlInfo01,
                    colArr: colNamesArr5,
                    multiselect: true,
                    width: 0.6,
                    height: 0.415
                };
                fullTable(config5);//加载空表格
            }*/
      /*      if (Body.Data.LRDtlInfo02.length > 0) {
                var config6 = {
                    tableId: 'list6',
                    data: Body.Data.LRDtlInfo02,
                    colArr: colNamesArr6,
                    multiselect: true,
                    width: 0.6,
                    height: 0.415
                };
                fullTable(config6);//加载空表格
            }
            MaVerRd = Body.Data.MaVerRd;
            $("#material").showData({
                id: Body.Data.MaVerRd,
                name: Body.Data.MaName,
                keyfield: "MaVerRd",
                fields: [
                    {"name": "MaVerRd"},
                    {"name": "MaName"}
                ]
            });*/
            //外观
            clickSelect_1();
            select1();

 /*           clickSelect1_1();
            clickSelect2_1();

            //EL
            clickSelect_2();
            clickSelect1_2();
            clickSelect2_2();

            //外观与EL
            clickSelect();
            clickSelect1();
            clickSelect2();

            clickSelect3();*/

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
        currentPage = treeSearchs("/CT/GetAllCTInfo", "CTRd", "CheckTypeName", "CheckTypeName", condition, currentPage, config);
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
            currentPage = treeSearchs("/CT/GetAllCTInfo", "CTRd", "CheckTypeName", "CheckTypeName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/CT/GetAllCTInfo", "CTRd", "CheckTypeName", "CheckTypeName", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/CT/GetAllCTInfo", "CTRd", "CheckTypeName", "CheckTypeName", condition, currentPage, config);
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

    var treeID = null;
    $(".add1").click(function () {
        addErow("list4");
        var trObj = $("#list4 tbody>tr:eq(1)");
        trObj.find("td").each(function () {
            if ($(this).attr("class") == "CheckItemName") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < wgBadInfos.length; i++) {
                    if (current_Td_Val == wgBadInfos[i].CheckItemName)
                        str += "<option selected value='" + wgBadInfos[i].CheckItemName + "'>" + wgBadInfos[i].CheckItemName + "</option>";
                    else
                        str += "<option value='" + wgBadInfos[i].CheckItemName + "'>" + wgBadInfos[i].CheckItemName + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
        });

        select1();
    });
    $(".del1").click(function () {
        derow("list4");
    });

    $(".add2").click(function () {
        addErow("list5");
        var trObj = $("#list5 tbody>tr:eq(1)");
        trObj.find("td").each(function () {
            if ($(this).attr("class") == "PreGradeName2") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (current_Td_Val == elBadInfos[i].TypeText)
                        str += "<option selected value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                    else
                        str += "<option value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
            if ($(this).attr("class") == "TPreGradeName2") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (current_Td_Val == elBadInfos[i].TypeText)
                        str += "<option selected value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                    else
                        str += "<option value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
            if ($(this).attr("class") == "TargetGradeName2") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (current_Td_Val == elBadInfos[i].TypeText)
                        str += "<option selected value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                    else
                        str += "<option value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
            if ($(this).attr("class") == "IsReview") {
                var current_Td_Val = $(this).text();
                var str = "";
                if (current_Td_Val == "00")
                    str += "<input type='checkbox' name='Checked' checked value='00'/>";
                else
                    str += "<input type='checkbox' name='Checked' value='01'/>";
                $(this).html(str);
                $("input[name='Checked']").on("click", function () {
                    if ($(this).prop("checked"))
                        $(this).val("00");
                    else
                        $(this).val("01");
                });
            }
        });


        /*clickSelect();
         clickSelect1();
         clickSelect2();
         clickSelect3();*/

    });
    $(".del2").click(function () {
        derow("list5");
    });

    $(".add3").click(function () {
        addErow("list6");
        var trObj = $("#list6 tbody>tr:eq(1)");
        trObj.find("td").each(function () {
            if ($(this).attr("class") == "PreGradeName") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < wgBadInfos.length; i++) {
                    if (current_Td_Val == wgBadInfos[i].TypeText)
                        str += "<option selected value='" + wgBadInfos[i].GradeName + "'>" + wgBadInfos[i].GradeName + "</option>";
                    else
                        str += "<option value='" + wgBadInfos[i].GradeName + "'>" + wgBadInfos[i].GradeName + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
            if ($(this).attr("class") == "TPreGradeName") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < elBadInfos.length; i++) {
                    if (current_Td_Val == elBadInfos[i].TypeText)
                        str += "<option selected value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                    else
                        str += "<option value='" + elBadInfos[i].GradeName + "'>" + elBadInfos[i].GradeName + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
            if ($(this).attr("class") == "TargetGradeName") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < BadInfos.length; i++) {
                    if (current_Td_Val == BadInfos[i].TypeText)
                        str += "<option selected value='" + BadInfos[i].GradeName + "'>" + BadInfos[i].GradeName + "</option>";
                    else
                        str += "<option value='" + BadInfos[i].GradeName + "'>" + BadInfos[i].GradeName + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
            if ($(this).attr("class") == "IsReview") {
                var current_Td_Val = $(this).text();
                var str = "";
                if (current_Td_Val == "00")
                    str += "<input type='checkbox' name='Checked' checked value='00'/>";
                else
                    str += "<input type='checkbox' name='Checked' value='01'/>";
                $(this).html(str);
                $("input[name='Checked']").on("click", function () {
                    if ($(this).prop("checked"))
                        $(this).val("00");
                    else
                        $(this).val("01");
                });
            }
        });


        /*clickSelect();
         clickSelect1();
         clickSelect2();
         clickSelect3();*/

    });
    $(".del3").click(function () {
        derow("list6");
    });

    //等级判断类型
    $("#djpdlx").change(function () {
        var type = $(this).find("option:selected").val();

        if (type == "00") {
            var colNamesArr = [
                {"Caption": "id", "Name": "LRDtlRd", "Hidden": true},
                {"Caption": "压后检", "Name": "PreGradeName1", "CType": "text", "Editable": false},
                {"Caption": "终检", "Name": "TPreGradeName1", "CType": "text", "Editable": false},
                {"Caption": "判断等级", "Name": "TargetGradeName1", "CType": "text", "Editable": false},
                {"Caption": "进入待评审", "Name": "IsReview", "CType": "text", "Editable": false},
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
        }
    });


    //加载
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        request({
            url: '/CT/GetAllCTInfo',
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CheckTypeName",
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
                    id: treeData[i].CTRd == undefined ? "" : treeData[i].CTRd,
                    name: treeData[i].CheckTypeName == undefined ? "" : treeData[i].CheckTypeName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
        //外观
        clickSelect_1();
        clickSelect1_1();
        clickSelect2_1();
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        //外观
        clickSelect_1();
        clickSelect1_1();
        clickSelect2_1();
        $("#ExecType").val("00");
        $("#djpdlx").val("00");
        treeID = null;
        $("#_right").show();
        $("#CheckTypeName").val("");
        $("#CheckTypeCode").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $("#CheckTypeCode").clearseldata("CheckTypeCode");
        $("#CheckTypeName").clearseldata("CheckTypeName");
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

        var config5 = {
            tableId: 'list5',
            data: [],
            colArr: colNamesArr5,
            multiselect: true,
            width: 0.6,
            height: 0.415
        };
        config5.data.length = 0;
        fullTable(config5);//加载空表格

        var config6 = {
            tableId: 'list6',
            data: [],
            colArr: colNamesArr6,
            multiselect: true,
            width: 0.6,
            height: 0.415
        };
        config6.data.length = 0;
        fullTable(config6);//加载空表格

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
                        url: "/CT/SaveCTInfo",
                        data: {"ExecType": "01", "busData": "{\"CTRd\":" + treeID + "}"}
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


    /*--------------顶部菜单点击保存按钮----------*/

    $(".cSave").click(function () {

        var CheckTypeName = $("#CheckTypeName").val().trim();
        if (CheckTypeName == null || CheckTypeName == "") {
            toastr.warning("检验类型名称不能为空");
            return false;
        }
        var CheckTypeCode = $("#CheckTypeCode").val().trim();
        if (CheckTypeCode == null || CheckTypeCode == "") {
            toastr.warning("检验类型代码不能为空");
            return false;
        }
        var list4Data = getTableData("list4");
    /*    var list5Data = getTableData("list5");
        var list6Data = getTableData("list6");*/

        /*if (list4Data.length <= 0) {
         toastr.warning("外观检验等级不能为空");
         return false;
         }
         if (list5Data.length <= 0) {
         toastr.warning("EL检验等级不能为空");
         return false;
         }*/

        var Remark = $("#beizhu").val().trim();


        var newData = {};

        console.log(list4Data)       /*if (tableData.length <= 0) {
         toastr.warning("等级明细不能为空");
         return false;
         }*/
        //新增
        if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
            var wglist4Data = [];
          /*  var ellist5Data = [];*/
         if (list4Data.length > 0) {
                var k = 1;
                for (var i  in  list4Data) {
                    if (list4Data[i].CheckItemName == "" || list4Data[i].CheckItemName == null
                    ) {
                        toastr.warning("第" + k + "行的数据为空，不能保存");
                        return false;
                    }

                   // delete list4Data[i].CheckTypeDtlRd;
                    var wgData = {
                        "CheckTypeDtlRd": wgBadInfos[i].CIRd,
                        "CheckItemName": list4Data[i].CheckItemName,
                        "CheckItemC": list4Data[i].CheckItemC,
                        "SureType": list4Data[i].SureType,
                    };
                    wglist4Data.push(wgData);
                    k++;
                }
            }
/*            if (list5Data.length > 0) {
                var l = 1;
                for (var i  in  list5Data) {
                    if (list5Data[i].PreGradeName2 == "" || list5Data[i].PreGradeName2 == null
                        || list5Data[i].TPreGradeName2 == "" || list5Data[i].PreGradeName2 == null
                        || list5Data[i].TargetGradeName2 == "" || list5Data[i].PreGradeName2 == null
                    ) {
                        toastr.warning("第" + l + "行的数据为空，不能保存");
                        return false;
                    }
                    delete list5Data[i].LRDtlRd;
                    var elData = {
                        "IsReview": list5Data[i].IsReview,
                        "PreGradeName": list5Data[i].PreGradeName2,
                        "TPreGradeName": list5Data[i].TPreGradeName2,
                        "TargetGradeName": list5Data[i].TargetGradeName2
                    };
                    ellist5Data.push(elData);
                    l++;
                }
            }*/
/*            if (list6Data.length > 0) {
                var kl = 1;
                for (var i  in  list6Data) {
                    if (list6Data[i].PreGradeName == "" || list6Data[i].PreGradeName == null
                        || list6Data[i].TPreGradeName == "" || list6Data[i].PreGradeName == null
                        || list6Data[i].TargetGradeName == "" || list6Data[i].PreGradeName == null
                    ) {
                        toastr.warning("第" + k + "行的数据为空，不能保存");
                        return false;
                    }
                    delete list6Data[i].LRDtlRd;
                    kl++;
                }
            }*/
            newData = {
                "CheckTypeCode": CheckTypeCode,
                "CheckTypeName": CheckTypeName,
                "CTDtlInfo": wglist4Data,
        /*        "LRDtlInfo01": ellist5Data,
                "LRDtlInfo02": list6Data,*/
                "Remark": Remark
            };
            console.log(newData);
            request({
                url: "/CT/SaveCTInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        }
        //更新工厂信息
        else if (treeID != null && treeID != "") {
            var wglist4Data = [];
    /*        var ellist5Data = [];*/
            if (list4Data.length > 0) {
                var k = 1;
                for (var i  in  list4Data) {
               /*     if (list4Data[i].PreGradeName1 == "" || list4Data[i].PreGradeName1 == null
                        || list4Data[i].TPreGradeName1 == "" || list4Data[i].PreGradeName1 == null
                        || list4Data[i].TargetGradeName1 == "" || list4Data[i].PreGradeName1 == null
                    ) {
                        toastr.warning("第" + k + "行的数据为空，不能保存");
                        return false;
                    }*/
                    var wgData = {
                        "CheckTypeDtlRd": list4Data[i].CheckTypeDtlRd,
                        "CheckItemName": list4Data[i].CheckItemName,
                        "CheckItemC": list4Data[i].CheckItemC,
                        "SureType": list4Data[i].SureType
                    };


                    wglist4Data.push(wgData);
                    k++;
                }
            }
/*            if (list5Data.length > 0) {
                var l = 1;
                for (var i  in  list5Data) {
                    if (list5Data[i].PreGradeName2 == "" || list5Data[i].PreGradeName2 == null
                        || list5Data[i].TPreGradeName2 == "" || list5Data[i].PreGradeName2 == null
                        || list5Data[i].TargetGradeName2 == "" || list5Data[i].PreGradeName2 == null
                    ) {
                        toastr.warning("第" + l + "行的数据为空，不能保存");
                        return false;
                    }
                    var elData = {
                        "IsReview": list5Data[i].IsReview,
                        "LRDtlRd": list5Data[i].LRDtlRd,
                        "PreGradeName": list5Data[i].PreGradeName2,
                        "TPreGradeName": list5Data[i].TPreGradeName2,
                        "TargetGradeName": list5Data[i].TargetGradeName2
                    };
                    ellist5Data.push(elData);
                    l++;
                }
            }*/
/*            if (list6Data.length > 0) {
                var kl = 1;
                for (var i  in  list6Data) {
                    if (list6Data[i].PreGradeName == "" || list6Data[i].PreGradeName == null
                        || list6Data[i].TPreGradeName == "" || list6Data[i].PreGradeName == null
                        || list6Data[i].TargetGradeName == "" || list6Data[i].PreGradeName == null
                    ) {
                        toastr.warning("第" + k + "行的数据为空，不能保存");
                        return false;
                    }
                    kl++;
                }
            }*/
            newData = {
                "CTRd": treeID,
                "CheckTypeCode": CheckTypeCode,
                "CheckTypeName": CheckTypeName,
                "CTDtlInfo": wglist4Data,
            /*    "LRDtlInfo01": ellist5Data,
                "LRDtlInfo02": list6Data,*/
                "Remark": Remark
            };
            console.log(newData);
            request({
                url: "/CT/SaveCTInfo",
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
