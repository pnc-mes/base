/**
 * Created by PNC on 2017/6/29.
 */
$(function () {

//树的click事件
    var onClicks = function (id, text) {

        if (!id.hasChildren) {
            $("#add").attr("a", "");
            $("#hidden").attr("a", "00")
            $(".right").show();
            var treeID = id.nodeID;
            /*$("#edit").attr("b",id.nodeID);*/
            $("#remove").attr("d", id.nodeID)
            $("#recopy").attr("e", id.nodeID)
            var objData = {
                "OpertRd": treeID
            };
            request({url: "/Opert/GetOpertInfo", data: {"ExecType": "00", "busData": JSON.stringify(objData)}},function (Body) {
                //加载工作中心数据
                $("#defaultSelect").showData({
                    id:Body.Data.WCRd,
                    name:Body.Data.WCName,
                    keyfield:"WCRd",
                    fields:[
                        {"name":"WCRd"},
                        {"name":"WCName"}
                    ]
                });
                //加载批次等级
                $("#defaultSelect1").showData({
                    id:Body.Data.BLRd,
                    name:Body.Data.BLName,
                    keyfield:"BLRd",
                    fields:[
                        {"name":"BLRd"},
                        {"name":"BLName"}
                    ]
                });
                //加载原因代码组
                $("#defaultSelect2").showData({
                    id:Body.Data.ReaCGRd,
                    name:Body.Data.ReaCGName,
                    keyfield:"RCGRd",
                    fields:[
                        {"name":"RCGRd"},
                        {"name":"RCGpName"}
                    ]
                });

                //首次加载可以操作
                $("#remove").attr("d", Body.Data.OpertRd);
                $("#recopy").attr("e", Body.Data.OpertRd);
                $("#remove").attr("d", Body.Data.OpertRd);

                $("#OptName").val(Body.Data.OptName)
                $("#creatPeople").val(Body.Data.Creator)
                $("#creatTime").val(Body.Data.CreateTime)
                $("#modifyPeople").val(Body.Data.LastModifyMan)
                $("#modifyTime").val(Body.Data.LastModifyTime)
                $("#beizhu").val(Body.Data.Remark)

                if (Body.Data.BadOutSpec == "01") {
                    $("#BadOutSpec").prop("checked", true);
                } else {
                    $("#BadOutSpec").prop("checked", false);
                }

                if (Body.Data.PackInfo.PackOpt == "00") {
                    $(".dabao").prop("checked", true);
                    if (Body.Data.PackInfo.CkWeight == "00") {

                        $("#CkWeight").prop("checked", true);
                    } else {
                        $("#CkWeight").prop("checked", false);
                    }
                    $("input[name='PackType']").each(function () {
                        if ($(this).attr("value") == Body.Data.PackInfo.PackType) {
                            $(this).prop("checked", "checked");
                        } else {
                            $(this).removeAttr("checked");
                        }
                    });
                } else {
                    $(".dabao").prop("checked", false);
                    $(".CkWeight").prop("checked", false);
                    $("input[name='PackType']").each(function () {
                        $(this).prop("checked", false);
                    });
                }

                $("input[name='SpecOption']").each(function () {
                    if ($(this).attr("value") == Body.Data.SpecOption) {
                        $(this).prop("checked", "checked");
                    } else {
                        $(this).removeAttr("checked");
                    }
                });

                if ($("#optionsRadios3").is(":checked") == true || $("#optionsRadios4").is(":checked") == true) {
                    $("#isout").attr("checked", true)
                } else {
                    $("#isout").attr("checked", false)
                }

                if ($("#optionsRadios5").is(":checked") == true || $("#optionsRadios6").is(":checked") == true || $("#CkWeight").attr("checked") == true) {
                    $("#dabao").attr("checked", true)
                } else {
                    $("#dabao").attr("checked", false)
                }
            });
        }
    }
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
        currentPage = treeSearchs("/Opert/GetAllOpertInfo","OpertRd","OptName","OptName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Opert/GetAllOpertInfo","OpertRd","OptName","OptName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })




    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Opert/GetAllOpertInfo","OpertRd","OptName","OptName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Opert/GetAllOpertInfo","OpertRd","OptName","OptName",condition,currentPage,config);
    });


    //查询工作中心
    var params = {
        "displaymode": "0",
        "title": "所属工作中心",
        "binddata": {
            "keyfield": "WCRd",
            "fields": [
                {
                    "caption": "工作中心id",
                    "name": "WCRd"
                }, {
                    "caption": "所属工作中心",
                    "name": "WCName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"CenterName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"CenterName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/WorkC/GetAllWCInfo",data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WCRd": datas[i].WCRd,
                            "WCName": datas[i].WCName,
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
    }

    $("#defaultSelect").zc_select(params);

    var params1 = {
        "displaymode": "0",
        "title": "批次等级",
        "binddata": {
            "keyfield": "BLRd",
            "fields": [
                {
                    "caption": "批次等级id",
                    "name": "BLRd"
                }, {
                    "caption": "批次等级",
                    "name": "BLName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {

                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"BLName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"BLName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //查询批次等级
                request({url: "/BatchL/GetAllBLInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "BLRd": datas[i].BLRd,
                            "BLName": datas[i].BLName,
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
    }

    $("#defaultSelect1").zc_select(params1);

    var params2 = {
        "displaymode": "0",
        "title": "原因代码组",
        "binddata": {
            "keyfield": "RCGRd",
            "fields": [
                {
                    "caption": "原因代码组id",
                    "name": "RCGRd"
                }, {
                    "caption": "原因代码组",
                    "name": "RCGpName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {

                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ReaCGName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"ReaCGName",
                            "FieldOpt":"Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //查询原因代码组
                request({url: "/ReasonG/GetAllRCGInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "RCGRd": datas[i].RCGRd,
                            "RCGpName": datas[i].RCGpName,
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
    }

    $("#defaultSelect2").zc_select(params2);
    //新增
    $("#add").click(function () {
        $(".right").show();
        /*clearForm("opertForm");*/
        $("#optionsRadios3").prop("checked",true);
        $("#defaultSelect").clearseldata("WCRd");
        $("#defaultSelect1").clearseldata("BLRd");
        $("#defaultSelect2").clearseldata("RCGRd");

        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $("#hidden").attr("a","")
        $("#add").attr("a", "00");
        $("#remove").attr("d", "")
        $("#recopy").attr("e", "")
        readOnly("opertForm", false);
        $("#OptName").val("");
        $("#beizhu").val("");
        $("#OptName").val("");
      /*  $("#DCVerRd").val("")*/
      /*  $("input[name='SpecOption']").attr("checked", true)*/
        $("#optionsRadios3").attr("checked", true)
        $("#dabao").prop("checked", false);
        $("#CkWeight").prop("checked", false);
        $("input[name='PackType']").attr("checked", false);
        $("#BadOutSpec").prop("checked", false);

    });

    // 刷新树

    var loadTree = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"OptName",
                    "FieldOpt":"Order BY",
                }

            ]
        }
        var treedataList = [];
        request({url:"/Opert/GetAllOpertInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData)},"PageInfo":JSON.stringify(pageInfo)},function (Body) {
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
                var tree = {
                    id: treeData[i].OpertRd == undefined ? "" : treeData[i].OpertRd,
                    name: treeData[i].OptName == undefined ? "" : treeData[i].OptName
                }
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadTree();



    //为了点击保存时获取想要的值
    $("input[name=SpecOption]").click(function () {
        if ($(this).is(":checked")) {
            $("#hidden").val($(this).attr("value"));
        }
    });
    $("#hidden").val("02");


    $("input[name=PackType]").click(function () {
        if ($(this).is(":checked")) {
            $("#hidden").attr("hi", $(this).attr("value"));
        }
    });
    $("#hidden").attr("hi", "00")

    $("#isout").click(function () {

        if ($(this).is(':checked') == true) {

            $("input[name='SpecOption']").each(function () {
                $(this).attr("disabled", false)
            });
        } else {
            $("input[name='SpecOption']").each(function () {
                $(this).removeAttr("checked");
                $(this).attr("disabled", true)
            });
        }
    });

    $("#dabao").click(function () {
        if ($(this).is(":checked") == true) {
            $("input[name='PackType']").each(function () {
                $(this).attr("disabled", false)
            })
            $("#CkWeight").attr("disabled", false)

        } else {
            $("input[name='PackType']").each(function () {
                $(this).removeAttr("checked");
                $(this).attr("disabled", true)
            })
            $("#CkWeight").attr("checked", false)
            $("#CkWeight").attr("disabled", true)
        }
    });


    $("#save").click(function () {
        var SpecOption = $("#hidden").val();
        //是否作业打包 01
        if ($("input[name='PackOpt']").is(':checked') == true) {
            var PackOpt = "00";
        } else {
            var PackOpt = "01"
        }
        //是否检验 01
        if ($("input[name='CkWeight']").is(':checked') == true) {
            var CkWeight = "00"
        } else {
            var CkWeight = "01"
        }
        //检验不良是否出站 默认00
        if ($("input[name='BadOutSpec']").is(':checked') == true) {
            $("#BadOutSpec").val("01")
        } else {
            $("#BadOutSpec").val("00")
        }
        var aa = $("#OptName").val();
        var aa4 = $("#beizhu").val()
        var BadOutSpec = $("#BadOutSpec").val();
        var PackType = $("#hidden").attr("hi");

        var ReaCGRd = $("#defaultSelect2").getseldata().RCGRd;

        var WCRd=$("#defaultSelect").getseldata().WCRd;

        var BLRd=$("#defaultSelect1").getseldata().BLRd;

        var data = {
            "OptName": aa,
            "WCRd": WCRd,
            "BLRd": BLRd,
            "Remark": aa4,
            "SpecOption": SpecOption,
            "BadOutSpec": BadOutSpec,
            "ReaCGRd":ReaCGRd,
            "PackInfo": {
                "PackOpt": PackOpt,
                "CkWeight": CkWeight,
                "PackType": PackType
            }
        };

        //新增保存
        if ($("#add").attr("a") == "00") {
            if (aa != null && aa != "" && WCRd != "" && BLRd!="") {
                request({url:"/Opert/SaveOpertInfo", data: {"ExecType": "00", "BusData": JSON.stringify(data)}},function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#add").attr("a", "");
                    condition = "";
                    currentPage = 0;
                    loadTree();
                });
            }else{
                if (WCRd == "") {
                    $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect").find("input:eq(0)").val("");
                    $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
                if (BLRd == "") {
                    $("#defaultSelect1").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect1").find("input:eq(0)").val("");
                    $("#defaultSelect1").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }
        }
        var OpertRd = $("#remove").attr("d");
        var data1 = {
            "OpertRd": OpertRd,
            "OptName": aa,
            "WCRd": WCRd,
            "BLRd": BLRd,
            "Remark": aa4,
            "ReaCGRd":ReaCGRd,
            "SpecOption": SpecOption,
            "BadOutSpec": $("#BadOutSpec").val(),
            "PackInfo": {
                "PackOpt": PackOpt,
                "CkWeight": CkWeight,
                "PackType": $("#hidden").attr("hi")
            }
        }
        //更新保存
        if ($("#hidden").attr("a") == "00") {
            if (aa != null && aa != "" && WCRd != "" && BLRd != "") {
                request({url:"/Opert/SaveOpertInfo", data: {"ExecType": "02", "BusData": JSON.stringify(data1)}},function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#remove").attr("d", "");
                    $("#hidden").attr("a", "");
                    $("#recopy").attr("e", "");
                    condition = "";
                    currentPage = 0;
                    loadTree();
                });
            }else{
                if (WCRd == "") {
                    $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect").find("input:eq(0)").val("");
                    $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
                if (BLRd == "") {
                    $("#defaultSelect1").find("input:eq(0)").css("border-color", "red");
                    $("#defaultSelect1").find("input:eq(0)").val("");
                    $("#defaultSelect1").find("input:eq(0)").prop("placeholder", "不能为空!");
                }
            }
        }
    });
    //删除
    $("#remove").click(function () {
        if ($("#jstree_demo1 ul").has("li").length <= 0) {
            toastr.warning("请先新增数据!");
        }
        else {
            var data1 = $(this).attr("d");
            var data = {
                "OpertRd": data1
            }
            if (data1 == null || data1 == "") {
                toastr.warning("请选择左侧要删除的一项再进行删除!");
            }
            else {
                layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Opert/SaveOpertInfo",data: {"ExecType": "01", "BusData": JSON.stringify(data)}},function (Body) {
                        toastr.success(Body.MsgDes);
                       $(".right").hide();
                        layer.closeAll("dialog");
                        condition = "";
                        currentPage = 0;
                        loadTree();
                        $("#remove").attr("d", "");
                        $("#recopy").attr("e", "");
                    });
                })
            }
        }
    });

    //复制
    $("#recopy").click(function () {
        if ($("#jstree_demo1 ul").has("li").length <= 0) {
            toastr.warning("请先新增数据!");
        }
        else {
            var data1 = $(this).attr("e");
            var data = {
                "OpertRd": data1
            }
            if (data1 == null || data1 == "") {
                toastr.warning("请选择左侧要复制的一项再进行复制!");
            }
            else {
                request({url:"/Opert/SaveOpertInfo",data: {"ExecType": "03", "BusData": JSON.stringify(data)}},function (Body) {
                    $("#recopy").attr("e", "")
                    $("#remove").attr("d", "")
                    condition = "";
                    currentPage = 0;
                    loadTree();
                    toastr.success(Body.MsgDes);
                });
            }
        }
    });
});