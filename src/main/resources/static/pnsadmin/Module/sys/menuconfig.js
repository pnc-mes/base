/**
 * Created by pjf on 2017/12/28.
 */
$(function () {
    //定义全局变量,针对于表格本身
    var addid = 1;

    //定义双击编辑事件
    var resultflag = false;

    //定义修改表格的id
    var modifytableid = "";

    //加载数据存储ResRd
    var tablemainid = "";


    var $tab_4_li = $("#tab_4_li");
    var $tab_4 = $("#tab_4");//
    var flag = true;

    var colNamesArr = [
        {"Caption": "隐藏数据", "Name": "OptInfo", 'Hidden': true, Width: 100},
        {"Caption": "ResRd", "Name": "ResRd", "Hidden": true},
        {"Caption": "资源名称", "Name": "ResName", "Hidden": false},
        {"Caption": "链接类型", "Name": "LinkType", "Hidden": false},
        {"Caption": "Url路径", "Name": "ResUrl", 'Editable': false, Width: 100},
        {"Caption": "排序号", "Name": "Pos", 'Editable': false, Width: 20},
        {"Caption": "表格的唯一一个数据", "Name": "addid", 'Hidden': true, Width: 100},
        {"Caption": "是否关闭", "Name": "IsClose", 'Hidden': true, Width: 100},
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height: 0.415
    };
    fullTable(config1);//加载空表格

    var onClicks = function (nodeinfo, handle) {
        //   addid=1;
        $("#modal").attr("a", "01");
        if (nodeinfo.isRoot) {  //父节点
            $("#modalChildren").attr("a", nodeinfo.nodeID);
            $("#modalChildren").attr("b", nodeinfo.nodeText);
            $("#topModalName").attr("readonly", true);
            $tab_4_li.css("display", "none").prev().addClass("active");
            $tab_4.removeClass("active").prev().addClass("active");
            $("#_right").show();
            $("#delete").attr("a", nodeinfo.nodeID)
            var data = {
                "ModuleRd": nodeinfo.nodeID
            }
            request({
                url: "/Module/GetMdInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(data)}
            }, function (Body) {
                $("#modalName").attr("a", Body.Data.MInfo.ModuleRd)
                $("#modalName").val(Body.Data.MInfo.ModuleName);
                $("#resUrl").val(Body.Data.MInfo.ResUrl);
                $("#linkType").val(Body.Data.MInfo.LinkType);
                $("#ppos").val(Body.Data.MInfo.Pos);
                $("#topModalName").attr("a", Body.Data.PMInfo.PModuleRd)
                $("#topModalName").val(Body.Data.PMInfo.PModuleName);
                $("#creatPeople").val(Body.Data.Creator);
                $("#creatTime").val(Body.Data.CreateTime);
                $("#modifyPeople").val(Body.Data.LastModifyMan);
                $("#modifyTime").val(Body.Data.LastModifyTime);
                $("#beizhu").val(Body.Data.Remark);
                Body.Data.ResInfo.forEach(function (obj) {
                    obj.LinkType = obj.LinkType == "01" ? "外链" : "内链";
                    obj.OptInfo = JSON.stringify(obj.OptInfo);
                    obj.addid = addid++;
                });
                if (Body.Data.MInfo.IsClose == "00") {
                    $("#shutdown").prop("checked", true);
                } else {
                    $("#shutdown").prop("checked", false);
                }
                var config1 = {
                    tableId: 'list4',
                    data: Body.Data.ResInfo,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config1);

                trDBLClick();
            });
        } else {
            $("#modalChildren").attr("a", nodeinfo.nodeID);
            $("#modalChildren").attr("b", nodeinfo.nodeText);
            $("#delete").attr("a", nodeinfo.nodeID)
            $tab_4_li.css("display", "none").prev().addClass("active");
            $tab_4.removeClass("active").prev().addClass("active");
            $("#_right").show();
            var data = {
                "ModuleRd": nodeinfo.nodeID
            }
            request({
                url: "/Module/GetMdInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(data)}
            }, function (Body) {
                $("#modalName").attr("a", Body.Data.MInfo.ModuleRd)
                $("#modalName").val(Body.Data.MInfo.ModuleName);
                $("#resUrl").val(Body.Data.MInfo.ResUrl);
                $("#linkType").val(Body.Data.MInfo.LinkType);
                $("#ppos").val(Body.Data.MInfo.Pos);

                $("#topModalName").attr("a", Body.Data.PMInfo.PModuleRd)
                $("#topModalName").val(Body.Data.PMInfo.PModuleName);

                if (Body.Data.MInfo.IsClose == "00") {
                    $("#shutdown").prop("checked", true);
                } else {
                    $("#shutdown").prop("checked", false);
                }


                $("#creatPeople").val(Body.Data.Creator);
                $("#creatTime").val(Body.Data.CreateTime);
                $("#modifyPeople").val(Body.Data.LastModifyMan);
                $("#modifyTime").val(Body.Data.LastModifyTime);
                $("#beizhu").val(Body.Data.Remark);
                Body.Data.ResInfo.forEach(function (obj) {
                    obj.LinkType = obj.LinkType == "01" ? "外链" : "内链";
                    obj.OptInfo = JSON.stringify(obj.OptInfo);
                    obj.addid = addid++;
                });

                var config1 = {
                    tableId: 'list4',
                    data: Body.Data.ResInfo,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config1);
                trDBLClick();
            });
        }
    }


    $("#modalChildren").click(function () {
        $("#shutdown").attr("checked", false);
        addid = 1;
        $("#modal").attr("a", "02");
        if ($(this).attr("a") == null || $(this).attr("a") == "") {
            toastr.warning("请选择左侧信息，再点击该信息");
            return false;
        }
        $("#resUrl").val("");
        $("#linkType").val("00");
        $("#ppos").val("1");
        $("#topModalName").attr("readonly", true);
        $("#topModalName").val($(this).attr("b"));
        $("#modalName").val("");
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height: 0.415
        };
        fullTable(config1);//加载空表格
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
    });

    //-------------------------------------------START加入系统版本操作------------------------------------------------

    //加载所有系统版本
    request({url: "/Module/GetAllSysVerInfo", data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].SysVerRd + '>' + data[i].SysVerName + '</option>';
        }
        $("#sysVer").html(aa);
        $("#sysVer").selectpicker({
            width: 150
        });
        $("#CopySysVer").html(aa);
        $("#CopySysVer").selectpicker({
            width: 200
        });
    });

    //定义加载激活版本方法
    var JHSysVer = function () {
        request({
            url: "/Module/GetAllSysVerInfo",
            data: {"ExecType": "00", "BusData": JSON.stringify({"Status": "00"})}
        }, function (Body) {
            var data = Body.Data;
            $("#sysVer").selectpicker('val', data[0].ruid);
        });
    };

    //定义加载所有版本方法
    var AllSysVer = function () {
        request({url: "/Module/GetAllSysVerInfo", data: {"ExecType": "00"}}, function (Body) {
            var data = Body.Data;
            var aa = "";
            for (var i in data) {
                aa += '<option  value=' + data[i].SysVerRd + '>' + data[i].SysVerName + '</option>';
            }
            $("#sysVer").html(aa);
            $("#sysVer").selectpicker({
                width: 150
            });
            $("#CopySysVer").html(aa);
            $("#CopySysVer").selectpicker({
                width: 200
            });
        });
    };


    $("#sysVer").change(function () {
        $("#_right").hide();
        loadtree();
    });

    //新增系统版本
    $("#AddSysVer").click(function () {
        $("#myModal").modal("show");
    });

    $("#saveSysVer").click(function () {
        var SysVerName = $("#SysVerName").val();
        if (SysVerName == undefined || SysVerName == null || SysVerName == "") {
            toastr.warning("版本名称不能为空");
        }
        var SourceSysVRd = $("#CopySysVer").val();
        var ssysRd = "";
        if (SourceSysVRd != undefined && SourceSysVRd != null && SourceSysVRd != "") {
            ssysRd = SourceSysVRd[0];
        }

        request({
            url: "/Module/SaveSysVerInfo",
            data: {
                "ExecType": "00",
                "BusData": JSON.stringify({"SysVerName": SysVerName, "SourceSysVRd": ssysRd})
            }
        }, function (Body) {
            toastr.success(Body.MsgDes);
            location.reload();
        });
        $("#myModal").modal("hide");
    });

    //激活系统版本
    $("#JHSysVer").click(function () {
        var SysVerRd = $("#sysVer").val();
        request({
            url: "/Module/SaveSysVerInfo",
            data: {
                "ExecType": "03",
                "BusData": JSON.stringify({"SysVerRd": SysVerRd[0]})
            }
        }, function (Body) {
            toastr.success(Body.MsgDes);
            location.reload();
        });
    });

    //删除系统版本
    $("#DelSysVer").click(function () {
        var SysVerRd = $("#sysVer").val();
        if (SysVerRd == null || SysVerRd == "") {
            toastr.warning("请选择你要删除的系统版本！");
            return false;
        } else {
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                    url: "/Module/SaveSysVerInfo",
                    data: {
                        "ExecType": "01",
                        "BusData": JSON.stringify({"SysVerRd": SysVerRd[0]})
                    }
                }, function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    location.reload();
                });
            })
        }

    });

    //-------------------------------------------END加入系统版本操作------------------------------------------------

    var loadtree = function () {
        var sysVerRd = $("#sysVer").val();
        request({
            url: '/Module/GetAllMdInfo',
            data: {"ExecType": "00", "BusData": JSON.stringify({"SysVerRd": sysVerRd[0]})}
        }, function (Body) {
            var treeData = Body.Data;
            var rules = [{
                id: "ModuleRd",
                text: "ModuleName",
                children: "MenuInfo"
            },
                {
                    id: "ModuleRd",
                    text: "ModuleName",
                    children: "MenuInfo"
                },
                {
                    id: "ModuleRd",
                    text: "ModuleName",
                    children: "MenuInfo"
                },
                {
                    id: "ModuleRd",
                    text: "ModuleName",
                    children: "MenuInfo"
                },
                {
                    id: "ModuleRd",
                    text: "ModuleName",
                    children: "MenuInfo"
                }
            ]
            var config = {
                id: "jstree_demo1",
                data: {
                    source: treeData,
                    rule: rules
                },
                event: {
                    onClick: onClicks
                }
            };
            $.JstreeEx.init(config);//先调用后加载
        });
    };

    JHSysVer();
    loadtree();
    //保存
    $(".cSave").click(function () {

        var tableData = getTableData("list4");
        var IsClose = "";
        if ($("#shutdown").is(":checked")) {
            IsClose = "00";
        } else {
            IsClose = "01";
        }


        var ModuleName = $("#modalName").val().trim();
        var PModuleRd = $("#topModalName").attr("a");
        var resUrl = $("#resUrl").val().trim();
        var linkType = $("#linkType").val();
        var pos = $("#ppos").val();

        if (ModuleName == null || "" == ModuleName) {
            toastr.warning("模块名称不能为空");
            return false;
        }

        if (linkType != "00" && linkType != "01") {
            toastr.warning("链接类型不能为空");
            return false;
        }

        var str = /^\d+(\.\d+)?$/;
        if (!pos) {
            toastr.warning("排序号不能为空");
            return false;
        }
        var flag = str.test(pos);
        if (flag == false) {
            toastr.warning("输入有误，数量为正整数！");
            return false;
        }
        if (pos == 0) {
            toastr.warning("输入有误，排序后不能为0！");
            return;
        }

        var sysVerRdArrs = $("#sysVer").val();
        if (sysVerRdArrs == undefined || sysVerRdArrs == null) {
            toastr.warning("请选择要操作的系统版本！");
        }
        var sysverRd = sysVerRdArrs[0];

        //保存新增
        if ($("#modal").attr("a") == "00") {
            for (var i in tableData) {
                if (tableData[i].LinkType == "内链") {
                    tableData[i].LinkType = "00";
                } else {
                    tableData[i].LinkType = "01"
                }
                tableData[i].OptInfo = JSON.parse(tableData[i].OptInfo);
                delete tableData[i].ResRd;
                delete tableData[i].addid;
            }
            var dataSource = {
                "ModuleName": ModuleName,
                "PModuleRd": PModuleRd,
                "ResInfo": tableData,
                "ResUrl": resUrl,
                "LinkType": linkType,
                "Pos": pos,
                "SysVerRd": sysverRd,
                "IsClose": IsClose
            }

            request({
                url: "/Module/SaveMdInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(dataSource)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loadtree();
                addid = 1;
            });
        }
        if ($("#modal").attr("a") == "02") {

            for (var i in tableData) {
                if (tableData[i].LinkType == "内链") {
                    tableData[i].LinkType = "00";
                } else {
                    tableData[i].LinkType = "01"
                }
                tableData[i].OptInfo = JSON.parse(tableData[i].OptInfo);
                delete tableData[i].ResRd;
                delete tableData[i].addid;
            }
            var dataSource = {
                "ModuleName": $("#modalName").val().trim(),
                "PModuleRd": $("#modalChildren").attr("a"),
                "ResInfo": tableData,
                "ResUrl": resUrl,
                "LinkType": linkType,
                "Pos": pos,
                "SysVerRd": sysverRd,
                "IsClose": IsClose
            }
            request({
                url: "/Module/SaveMdInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(dataSource)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loadtree();
                addid = 1;
            });
        }
        if ($("#modal").attr("a") == "01") {
            var ModuleRd = $("#modalName").attr("a");
            if (ModuleRd == null || ModuleRd == "") {
                toastr.warning("请选择你要编辑的数据");
                return false;
            }
            for (var i in tableData) {
                tableData[i].OptInfo = JSON.parse(tableData[i].OptInfo);
            }

            for (var i in tableData) {
                if (tableData[i].LinkType == "内链") {
                    tableData[i].LinkType = "00";
                } else {
                    tableData[i].LinkType = "01"
                }
                for (var j in tableData[i].OptInfo) {
                    if (tableData[i].OptInfo[j].OptName != undefined) {
                        delete tableData[i].OptInfo[j].OptName;
                        delete tableData[i].OptInfo[j].Checked;
                    }
                    delete tableData[i].addid;
                }

            }

            var dataSource = {
                "ModuleRd": ModuleRd,
                "ModuleName": ModuleName,
                "PModuleRd": PModuleRd,
                "ResInfo": tableData,
                "ResUrl": resUrl,
                "LinkType": linkType,
                "SysVerRd": sysverRd,
                "Pos": pos,
                "IsClose": IsClose
            }

            request({
                url: "/Module/SaveMdInfo",
                data: {"ExecType": "02", "BusData": JSON.stringify(dataSource)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loadtree();
                addid = 1;
            });
        }
    });


    //刚开始的时候右边是木有的
    $tab_4_li.css("display", "none").prev().addClass("active");
    $tab_4.removeClass("active").prev().addClass("active");


    $(".XH").on("click", function () {
        $tab_4_li.css("display", "none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
    });
    trDBLClick();

    function trDBLClick() {
        $("#list4").find("tbody").on("dblclick", "tr", function () {
            resultflag = true;
            //获取当前双击表格的数据

            _PurChDlRd = $(this).find(".OptInfo").text();
            modifytableid = $(this).find(".addid").text();

            /*alert(_PurChDlRd)
            if(_PurChDlRd == ""){
                flag = false;
            }else{
                flag = true;
            }
*/
            $tab_4_li.css("display", "block").addClass("active").siblings().removeClass("active");
            $tab_4.addClass("active").siblings().removeClass("active");
            var data = JSON.parse($(this).find(".OptInfo").text());
            for (var i in data) {
                if (data[i].OptName == "增加") {
                    $(".add1").attr("a", data[i].OptRd)
                    if ($(".add1").attr("value") == data[i].Checked) {
                        $(".add1").attr("checked", true);
                    } else {
                        $(".add1").attr("checked", false);
                    }
                }
                if (data[i].OptName == "删除") {
                    $(".rem").attr("a", data[i].OptRd)
                    if ($(".rem").attr("value") == data[i].Checked) {
                        $(".rem").attr("checked", true);
                    } else {
                        $(".rem").attr("checked", false);
                    }
                }
                if (data[i].OptName == "修改") {
                    $(".mod").attr("a", data[i].OptRd)
                    if ($(".mod").attr("value") == data[i].Checked) {

                        $(".mod").attr("checked", true);
                    } else {
                        $(".mod").attr("checked", false);
                    }
                }
                if (data[i].OptName == "查询") {
                    $(".sel").attr("a", data[i].OptRd)
                    if ($(".sel").attr("value") == data[i].Checked) {
                        $(".sel").attr("checked", true);
                    } else {
                        $(".sel").attr("checked", false);
                    }
                }
            }

            var data1 = $(this).find(".ResName").text();
            var data2 = $(this).find(".ResUrl").text();
            var data3 = $(this).find(".ResRd").text();
            var data4 = $(this).find(".Pos").text();
            var data5 = $(this).find(".LinkType").text();
            var data6 = $(this).find(".IsClose").text();
            if (data3 != null || "" != data3) {
                tablemainid = data3;
            }
            if (data6 == "00") {
                $("#detailshutdown").prop("checked", true);
            } else {
                $("#detailshutdown").prop("checked", false);
            }


            $(".name").val(data1);
            $(".url").val(data2);
            $("#pos").val(data4);
            $("#linkType2").val(data5);
        });
    }


    //新增
    $("#modal").click(function () {
        $("#shutdown").attr("checked", false);
        addid = 1;
        $("#topModalName").attr("readonly", true);
        $(".name").val("")
        $(".url").val("")
        $(".add1").attr("checked", false);
        $(".rem").attr("checked", false);
        $(".mod").attr("checked", false);
        $(".sel").attr("checked", false);
        flag = false;
        $("#modal").attr("a", "00");
        $("#modalName").val("");
        $("#resUrl").val("");
        $("#linkType").val("00");
        $("#ppos").val("1");
        $("#topModalName").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $tab_4_li.css("display", "none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
        $("#_right").show();
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height: 0.415
        };
        fullTable(config1);
    });

    var ResInfos = [];

    var ResInfo = {};
    var cofirmData = [];


    //删除
    $("#delete").click(function () {
        var ModuleRd = $(this).attr("a");
        var dataSource = {
            "ModuleRd": ModuleRd
        }
        if (ModuleRd == null || ModuleRd == "") {
            toastr.warning("请选择你要删除的数据");
            return false;
        } else {
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                    url: "/Module/SaveMdInfo",
                    data: {"ExecType": "01", "BusData": JSON.stringify(dataSource)}
                }, function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    loadtree();
                    $("#_right").hide();
                });
            })
        }
    });


    //表格的新增
    $('.add2').on('click', function () {
        resultflag = false;
        $("#detailshutdown").attr("checked", false);
        $(".name").val("");
        $(".url").val("");
        $(".pos").val("");
        $(".add1").attr("checked", false);
        $(".rem").attr("checked", false);
        $(".mod").attr("checked", false);
        $(".sel").attr("checked", false);
        //把物料消耗的tab显示出来,并设置li的class为active,而其他的li移除active
        $tab_4_li.css("display", "block").addClass("active").siblings().removeClass("active");
        $tab_4.addClass("active").siblings().removeClass("active");

        ResInfos = [];
        OptInfos = [];
        OptInfo = {};
        ResInfo = {};
        //显示明细设置
        flag = false;

        //所有内容都可编辑
        $(".sure2").css("display", "-webkit-box");
        $("#tab_4").find("input").each(function () {
            $(this).prop("readonly", false);
        });
        $("#tab_4").find("select").prop("disabled", false);
        $("#tab_4").find("img").css("display", "inline-block");

    });

    //表格的确认
    $(".sure").on("click", function () {
        var OptInfos = [];
        var OptInfo = {};
        var tableData = getTableData("list4");
        var ResName = $(".name").val().trim();
        var ResUrl = $(".url").val().trim();
        var Pos = $(".pos").val().trim();
        var isclose = "";
        if ($("#detailshutdown").is(":checked")) {
            isclose = "00";
        } else {
            isclose = "01";
        }
        // alert(isclose)
        var linkType = $("#linkType2").val();
        var str = /^\d+(\.\d+)?$/;
        if (!Pos) {
            toastr.warning("排序号不能为空");
            return false;
        }
        var flag = str.test(Pos);
        if (flag == false) {
            toastr.warning("输入有误，数量为正整数！");
            return false;
        }
        if (Pos == 0) {
            toastr.warning("输入有误，排序后不能为0！");
            return;
        }
        //获取里面所有的数据，添加到用料清单的表格中
        var $id1 = "";
        var $id2 = "";
        var $id3 = "";
        var $id4 = "";

        if ($(".add1").is(':checked')) {
            $id1 = 1;
            OptInfo = {
                "OptRd": $id1
            }
            OptInfos.push(OptInfo);
        }
        if ($(".rem").is(':checked')) {
            $id2 = 2;
            OptInfo = {
                "OptRd": $id2
            }
            OptInfos.push(OptInfo);
        }
        if ($(".mod").is(':checked')) {
            $id3 = 3;
            OptInfo = {
                "OptRd": $id3
            }
            OptInfos.push(OptInfo);
        }
        if ($(".sel").is(':checked')) {
            $id4 = 4;
            OptInfo = {
                "OptRd": $id4
            }
            OptInfos.push(OptInfo);
        }

        var tab_TR = {
            ResRd: "",
            ResName: ResName,
            ResUrl: ResUrl,
            Pos: Pos,
            LinkType: linkType,
            addid: addid++,
            IsClose: isclose,
            OptInfo: JSON.stringify(OptInfos)
        };
        if (OptInfos.length <= 0) {
            toastr.warning("操作不能为空");
            return false;
        }

        for (var i in tab_TR) {
            if (tab_TR.ResName == "" || tab_TR.ResName == null) {
                toastr.warning("名称不能为空");
                return false;
            } else if (tab_TR.ResUrl == "" || tab_TR.ResUrl == null) {
                toastr.warning("URL不能为空");
                return false;
            } else if (tab_TR.LinkType == null || tab_TR.LinkType == "") {
                toastr.warning("链接类型不能为空");
                return false;
            }
        }

        if (resultflag) {
            if (tableData.length > 0) {
                for (var i in tableData) {
                    if (tableData[i].addid == modifytableid) {
                        tableData[i].ResRd = tablemainid;
                        tableData[i].ResName = ResName;
                        tableData[i].ResUrl = ResUrl;
                        tableData[i].Pos = Pos;
                        tableData[i].IsClose = isclose;
                        tableData[i].LinkType = linkType;
                        tableData[i].OptInfo = JSON.stringify(OptInfos);
                    }
                }

                var config1 = {
                    tableId: 'list4',
                    data: tableData,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config1);
            }
        } else {

            addSrow("list4", tab_TR);
        }


        $tab_4_li.css("display", "none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
        trDBLClick();
        /*if(tableData.length<=0){

        }else {

        }*/

        /*  if(!flag) {


              //

              var _flag = true;
              if(_flag)

          }else{
              //获取里面所有的数据，添加到用料清单的表格中
              var tab_TR = {
                  ResRd: "",
                  ResName:ResName,
                  ResUrl: ResUrl,
                  OptInfo:JSON.stringify(OptInfos)
              };
              var tab_list4 = getTableData("list4");
  //
              /!*  for(var i in tab_TR){
                    if(tab_TR.ResName==""||tab_TR.ResName==null){
                        toastr.warning("名称不能为空");
                        return false;
                    }else if(tab_TR.ResUrl==""||tab_TR.ResUrl==null){
                        toastr.warning("URL不能为空");
                        return false;
                    }else if(tab_TR.OptInfo==null||tab_TR.OptInfo==""){
                        toastr.warning("操作不能为空");
                        return false;
                    }
                }*!/
              $("#list4").find("tbody tr").each(function () {
                  //  if($(this).find(".ResRd").text() == _PurChDlRd){
                  $(this).find(".OptInfo").html(tab_TR.OptInfo);
                  $(this).find(".ResRd").html(tab_TR.ResRd);
                  $(this).find(".ResName").html(tab_TR.ResName);
                  $(this).find(".ResUrl").html(tab_TR.ResUrl);
                  //  return false;
                  //   }
              });
          }*/
        //隐藏当前tab

    });

    $(".ontherHide").click(function () {
        $(".divhide").hide();
    });
    $(".ontherModel").click(function () {
        $(".divhide").show();
    });

    //表格删除
    $('.del1').on('click', function () {
        derow('list4');
    });
});