$(function () {
    //处理页面加载时，直接点击保存事件
    var isEdit = false;
    //加载树点击事件
    var onClicks = function (id, text) {
        $(".cAdd").attr("a", "01");
        isEdit = true;
        $(".right").show();
        $("#table5").empty().html(
            "<tbody id='tbody5'>"
            + "    <tr style='width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><td style='width: 120px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><a style='margin-right: 5px; margin-left: 5px' class='Icons''><i class='fa fa-file-text fa-fw'></i></a><input style='margin-right: 5px;' type='checkbox' id='churuku'/><strong>出入库作业</strong></td>"
            + "    <td style='width: 600px;height: 30px;border:0;cellspacing:0;cellpadding:0;'  class='aa'><label for='yclrk'><input type='checkbox' id='yclrk' value='00'/>原材料入库</label>"
            + "     <label for='ccprk'><input type='checkbox' id='ccprk' value='01'/>产成品入库</label>"
            + "    <label for='otherrk'><input type='checkbox' id='otherrk' value='02'/>其他入库</label>"
            + "    <label for='llck'><input type='checkbox' id='llck' value='03'/>领料出库</label>"
            + "    <label for='xsck'><input type='checkbox' id='xsck' value='04'/>销售出库</label>"
            + "     <label for='otherck'><input type='checkbox' id='otherck' value='05'/>其他出库</label>"
            + "  <label for='sctl'><input type='checkbox' id='sctl' value='11'/>生产退料</label>"
            + "  <label for='lldsq'><input type='checkbox' id='lldsq' value='12'/>领料单申请</label><br/><label for='ztrk'><input type='checkbox' id='ztrk' value='14'/>组件入库</label><label for='zjchbh'><input type='checkbox' id='zjchbh' value='15' style='margin-left: 9px;'/>组件出货备货</label><label for='cpqj'><input type='checkbox' id='cpqj' value='16'/>成品请检</label><label for='cpck'><input type='checkbox' id='cpck' value='17'/>成品出库</label><label for='yiku'><input type='checkbox' id='yiku' value='18'/>移库</label></td>" +
            "</tr>"

            + "<tr style='width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><td style='width: 120px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><a style='margin-right: 5px; margin-left: 5px' class='Icons''><i class='fa fa-file-text fa-fw'></i></a><input style='margin-right: 5px;' type='checkbox' id='kuncun'/><strong>库存作业</strong></td>"
            + "     <td style='width: 600px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='aa'><label for='sj'><input type='checkbox' id='sj' value='06'/>上架</label>"
            + "     <label for='pd' style='margin-left: 51px;'><input type='checkbox' id='pd' value='07'/>盘点</label>"
            + "   <label for='yk' style='margin-left: 51px;'><input type='checkbox' id='yk' value='08'/>调拨</label>"
            + "     <label for='tzkw' style='margin-left: 39px;'><input type='checkbox' id='tzkw' value='09'/>调整库位</label></td></tr>"

            + "     <tr style='width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><td style='width: 120px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><a style='margin-right: 5px; margin-left: 5px' class='Icons''><i class='fa fa-file-text fa-fw'></i></a><input style='margin-right: 5px;' type='checkbox' id='zhijian'/><strong>质检作业</strong></td>"
            + "   <td style='width: 600px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='aa'><label for='iqc'><input type='checkbox' id='iqc' value='10'/>IQC</label></td></tr>"

            + "     <tr style='width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><td style='width: 120px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><a style='margin-right: 5px; margin-left: 5px' class='Icons''><i class='fa fa-file-text fa-fw'></i></a><input style='margin-right: 5px;' type='checkbox' id='shengchan'/><strong>生产作业</strong></td>"
            + "   <td style='width: 600px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='aa'><label for='sl'><input type='checkbox' id='sl' value='13'/>上料</label></td></tr>"
            + "   </tbody>"
        );

        //value值从00开始 class 变换为bb
        $("#table6").empty().html(
            "<tbody id='tbody6'>"
            + "     <tr style='width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><!--<td style='width: 120px;height: 30px;border:0;cellspacing:0;cellpadding:0;'><a style='margin-right: 5px; margin-left: 5px' class='Icons''><i class='fa fa-file-text fa-fw'></i></a><input style='margin-right: 5px;' type='checkbox' id='renwu'/><strong>任务</strong></td>-->"
            + "     <td style='width: 600px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='bb'><label for='dj'><input type='checkbox' id='dj' value='00'/>点检保养系统</label>"
            + "    <!-- <label for='by' style='margin-left: 39px;'><input type='checkbox' id='by' value='01'/>保养</label>--></td></tr>"
            + "   </tbody>"
        );

        $(".cDel").attr("b", id.nodeID);
        var objBusData = {"RoleRd": id.nodeID};
        request({
            url: "/Role/GetRoleInfo", async: false,//设为同步请求
            data: {
                "ExecType": "00",
                "BusData": JSON.stringify(objBusData),
            }
        }, function (Body) {

            $("#table>#tbody").empty();
            if (Body.MsgCode == "0x00000") {

                $(".cDel").attr("b", Body.Data.RoleRd);

                $("#printModelName").val(Body.Data.RoleName);
                $("#creatPeople").val(Body.Data.Creator);
                $("#creatTime").val(Body.Data.CreateTime);
                $("#modifyPeople").val(Body.Data.LastModifyMan);
                $("#modifyTime").val(Body.Data.LastModifyTime);
                $("#beizhu").val(Body.Data.Remark);

                var MenuInfoList = Body.Data.MenuInfo;
                if (MenuInfoList == null || MenuInfoList.length == 0) {
                    return;
                }
                MenuInfos(MenuInfoList);


                var scan = Body.Data.ScanPVInfo;

                if (scan == null || scan == "") {
                    $(".aa>label>input").removeAttr("checked")
                }
                if (scan != null && scan != "") {
                    $(".aa>label>input").each(function () {
                        for (var i in scan) {
                            for (var j in scan[i].PVIInfo) {
                                if (scan[i].PVIInfo[j].PVFlag == $(this).attr("value")) {
                                    $(this).attr("checked", true)
                                }
                            }
                        }
                    });
                }
                //开始勾选车间权限
                var Exec = Body.Data.ExecPVInfo;
                if (Exec == null || Exec == "") {
                    $(".bb>label>input").removeAttr("checked");
                    return false;
                }

                $(".bb>label>input").each(function () {
                    for (var i in Exec) {
                        for (var j in Exec[i].PVIInfo) {
                            if (Exec[i].PVIInfo[j].PVFlag == $(this).attr("value")) {
                                $(this).attr("checked", true)
                            }
                        }
                    }
                });
            }
        });

        //处理点击左侧列表实现渲染权限页面
        var strModule = "";
        var strRes = "";
        var i = 0;
        var idSize = "";
        var MenuInfos = function (MenuInfoList) {
            $.each(MenuInfoList, function (key, MenuInfo) {
                strModule = "<tr style='display:block;width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='modulerow'>" +
                    "<td style='width: 300px; border:0;cellspacing:0;cellpadding:0;' class='guanli' id='" + MenuInfo.ModuleRd + "'>" + "<a class='Icons'  id='icon" + i + "' style='margin-right: 5px;'><i class='fa fa-clipboard fa-fw'></i></a>" + "<input style='margin-right: 5px; margin-top: 10px;' class = 'Taisyo' type='checkbox' id='checkname" + i + "'/>" + MenuInfo.ModuleName + "</td>" +
                    "<td style='width: 500px; border:0;cellspacing:0;cellpadding:0;'></td>" +
                    "</tr>";
                $("#table>#tbody").append(strModule);
                var j = 1;
                if (MenuInfo.ResInfo != undefined && MenuInfo.ResInfo.length > 0) {
                    $.each(MenuInfo.ResInfo, function (key, ResInfo) {
                        strRes = "<tr class='resrow' id='trRow" + i + "win" + j + "' style='display:none;width: 800px;height: 30px;'><td style='width: 300px;' class='xinxiushan' pid='" + MenuInfo.ModuleRd + "' id='" + ResInfo.ResRd + "'>" + "<span class='resname' style='margin-left: 30px'>" + "<a style='margin-right: 5px; margin-left: 5px' class='Icons' id='icon_file" + i + "'><i class='fa fa-file-text fa-fw'></i></a>" + "<input  style='margin-right: 5px;'class = 'Taisyo' type='checkbox' id='checkname" + i + "win" + j + "'/>" + ResInfo.ResName + "</span>" + "</td><td style='width: 500px;'>";
                        if (ResInfo.OptInfo != null && ResInfo.OptInfo.length > 0) {
                            $.each(ResInfo.OptInfo, function (key, OptInfo) {
                                strRes += "&nbsp;&nbsp;&nbsp;" + "<input type='checkbox' id='checkname" + i + "win" + j + "' value='" + OptInfo.OptRd + "' " + (OptInfo.Checked == "00" ? "checked" : "") + "/>" + "&nbsp;" + "<span>" + OptInfo.OptName + "</span>" + "&nbsp;&nbsp;";
                            });
                        }
                        strRes += "</td></tr>";
                        $("#table>#tbody").append(strRes);
                        j++;
                    });
                }
                if (MenuInfo.MenuInfo.length > 0) {
                    $.each(MenuInfo.MenuInfo, function (key, MenuInfo_for) {
                        var reqs = i + "win" + j;
                        var menuInfo_f = [];
                        menuInfo_f = MenuInfo_for;
                        MenuInfosFun(menuInfo_f, reqs, menuInfo_f.ModuleName, 0);
                        j++;
                    });
                }
                ;
                i++;
            });

        };

        var MenuInfosFun = function (MenuInfo, i, lastMenuInfo, leftLenght) {
            lastMenuInfo = lastMenuInfo + ">" + MenuInfo.ModuleName;
            leftLenght = leftLenght + 20;
            var leftRowLenght = leftLenght + 30;
            strModule = "<tr style='display:none;width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='modulerow' id='trRow" + i + "'>"
                + "<td style='width: 300px; border:0;cellspacing:0;cellpadding:0;' class='guanli' id='" + MenuInfo.ModuleRd + "'>"
                + "<span style='margin-left:" + leftLenght + "px;'><a class='Icons'  style='margin-right: 5px;' id='icon" + i + "'><i class='fa fa-clipboard fa-fw'></i></a><input style='margin-right: 5px; margin-top: 10px;' class = 'Taisyo' type='checkbox' id='checkname" + i + "'/>" + MenuInfo.ModuleName + "</span>"
                + "</td><td style='width: 500px; border:0;cellspacing:0;cellpadding:0;'></td></tr>";
            $("#table>#tbody").append(strModule);
            if (MenuInfo.ResInfo == null || MenuInfo.ResInfo.length == 0) {
                return false;
            }

            var z = 1;
            $.each(MenuInfo.ResInfo, function (key, ResInfo) {
                strRes = "<tr class='resrow' id='trRow" + i + "_" + z + "' style='display:none;width: 800px;height: 30px;'><td style='width: 300px;' class='xinxiushan' pid='" + MenuInfo.ModuleRd + "' id='" + ResInfo.ResRd + "'>" + "<span class='resname' style='margin-left: " + leftRowLenght + "px'>" + "<a style='margin-right: 5px; margin-left: 5px' class='Icons' id='icon_file" + i + "'><i class='fa fa-file-text fa-fw'></i></a>" + "<input style='margin-right: 5px;' class = 'Taisyo' type='checkbox' id='checkname" + i + "_" + z + "'/>" + ResInfo.ResName + "</span>" + "</td><td style='width: 500px;'>";
                if (ResInfo.OptInfo != null && ResInfo.OptInfo.length > 0) {
                    $.each(ResInfo.OptInfo, function (key, OptInfo) {
                        strRes += "&nbsp;&nbsp;&nbsp;" + "<input type='checkbox' id='checkname" + i + "_" + z + "' value='" + OptInfo.OptRd + "' " + (OptInfo.Checked == "00" ? "checked" : "") + "/>" + "&nbsp;" + "<span>" + OptInfo.OptName + "</span>" + "&nbsp;&nbsp;";
                    });
                }
                strRes += "</td></tr>";
                $("#table>#tbody").append(strRes);
                z++;
            });
            if (MenuInfo.MenuInfo.length > 0) {
                $.each(MenuInfo.MenuInfo, function (key, MenuInfo_funFor) {
                    idSize = i + "_" + z;
                    var menuInfo_fun = [];
                    menuInfo_fun = MenuInfo_funFor;
                    MenuInfosFun(menuInfo_fun, idSize, lastMenuInfo, leftLenght);
                    z++;
                });

            }
        };
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
        "PageSize": 20,
        "PageIndex": currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/Role/GetAllRoleInfo", "RoleRd", "RoleName", "RoleName", condition, currentPage, config);
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
            currentPage = treeSearchs("/Role/GetAllRoleInfo", "RoleRd", "RoleName", "RoleName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/Role/GetAllRoleInfo", "RoleRd", "RoleName", "RoleName", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/Role/GetAllRoleInfo", "RoleRd", "RoleName", "RoleName", condition, currentPage, config);
    });


    var loadPage = function () {
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        var treedataList = [];
        request({
            url: "/Role/GetAllRoleInfo", async: false,
            data: {"ExecType": "00", "PageInfo": JSON.stringify(pageInfo)}
        }, function (Body) {
            if (Body.MsgCode == "0x00000") {
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                if (treeData.length <= 0) {
                    return false;
                }
                var len = treeData.length >= 20 ? 20 : treeData.length;

                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].RoleRd == undefined ? "" : treeData[i].RoleRd,
                        name: treeData[i].RoleName == undefined ? "" : treeData[i].RoleName
                    }

                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }

        });

    };
    loadPage();

    var idSizeAdd = 0;
    //新增
    $(".cAdd").click(function () {
        isEdit = true;
        var objBusData = {"RoleRd": ""};


        request({
            url: "/Role/GetRoleInfo", async: false,//设为同步请求
            data: {
                "ExecType": "00",
                "BusData": JSON.stringify(objBusData),
            }
        }, function (Body) {
            $("#table>#tbody").empty();
            if (Body.MsgCode == "0x00000") {

                var MenuInfoList = Body.Data.MenuInfo;
                if (MenuInfoList == null || MenuInfoList.length == 0) {
                    return;
                }
                var strModule = "";
                var strModule1 = "";
                var strModule2 = "";
                var strRes = "";
                var i = 0;
                $.each(MenuInfoList, function (key, MenuInfo) {
                    strModule = "<tr style='display:block;width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='modulerow'>" +
                        "<td style='width: 300px; border:0;cellspacing:0;cellpadding:0;' class='guanli' id='" + MenuInfo.ModuleRd + "'>" + "<a class='Icons'  id='icon" + i + "' style='margin-right: 5px;'><i class='fa fa-clipboard fa-fw'></i></a>" + "<input style='margin-right: 5px; margin-top: 10px;' class = 'Taisyo' type='checkbox' id='checkname" + i + "'/>" + MenuInfo.ModuleName + "</td>" +
                        "<td style='width: 500px; border:0;cellspacing:0;cellpadding:0;'></td>" +
                        "</tr>";
                    $("#table>#tbody").append(strModule);
                    var j = 1;
                    if (MenuInfo.ResInfo != undefined && MenuInfo.ResInfo.length > 0) {
                        $.each(MenuInfo.ResInfo, function (key, ResInfo) {
                            strRes = "<tr class='resrow' id='trRow" + i + "win" + j + "' style='display:none;width: 800px;height: 30px;'><td style='width: 300px;' class='xinxiushan' pid='" + MenuInfo.ModuleRd + "' id='" + ResInfo.ResRd + "'>" + "<span class='resname' style='margin-left: 30px'>" + "<a style='margin-right: 5px; margin-left: 5px' class='Icons' id='icon_file" + i + "'><i class='fa fa-file-text fa-fw'></i></a>" + "<input  style='margin-right: 5px;'class = 'Taisyo' type='checkbox' id='checkname" + i + "win" + j + "'/>" + ResInfo.ResName + "</span>" + "</td><td style='width: 500px;'>";
                            if (ResInfo.OptInfo != null && ResInfo.OptInfo.length > 0) {
                                $.each(ResInfo.OptInfo, function (key, OptInfo) {
                                    strRes += "&nbsp;&nbsp;&nbsp;" + "<input type='checkbox' id='checkname" + i + "win" + j + "' value='" + OptInfo.OptRd + "' " + (OptInfo.Checked == "00" ? "checked" : "") + "/>" + "&nbsp;" + "<span>" + OptInfo.OptName + "</span>" + "&nbsp;&nbsp;";
                                });
                            }
                            strRes += "</td></tr>";
                            $("#table>#tbody").append(strRes);
                            j++;
                        });
                    }
                    if (MenuInfo.MenuInfo.length > 0) {
                        $.each(MenuInfo.MenuInfo, function (key, MenuInfo_for) {
                            var reqs = i + "win" + j;
                            var menuInfo_f = [];
                            menuInfo_f = MenuInfo_for;
                            MenuInfosFun_Add(menuInfo_f, reqs, menuInfo_f.ModuleName, 0);
                            j++;
                        });
                    }
                    ;
                    i++;
                });
            }
        });

        var MenuInfosFun_Add = function (MenuInfo, i, lastMenuInfo, leftLenght) {
            lastMenuInfo = lastMenuInfo + ">" + MenuInfo.ModuleName;
            leftLenght = leftLenght + 20;
            var leftRowLenght = leftLenght + 30;
            strModule = "<tr style='display:none;width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='modulerow' id='trRow" + i + "'>"
                + "<td style='width: 300px; border:0;cellspacing:0;cellpadding:0;' class='guanli' id='" + MenuInfo.ModuleRd + "'>"
                + "<span style='margin-left:" + leftLenght + "px;'><a class='Icons'  style='margin-right: 5px;' id='icon" + i + "'><i class='fa fa-clipboard fa-fw'></i></a><input style='margin-right: 5px; margin-top: 10px;' class = 'Taisyo' type='checkbox' id='checkname" + i + "'/>" + MenuInfo.ModuleName + "</span>"
                + "</td><td style='width: 500px; border:0;cellspacing:0;cellpadding:0;'></td></tr>";
            $("#table>#tbody").append(strModule);
            if (MenuInfo.ResInfo == null || MenuInfo.ResInfo.length == 0) {
                return false;
            }

            var z = 1;
            $.each(MenuInfo.ResInfo, function (key, ResInfo) {
                strRes = "<tr class='resrow' id='trRow" + i + "_" + z + "' style='display:none;width: 800px;height: 30px;'><td style='width: 300px;' class='xinxiushan' pid='" + MenuInfo.ModuleRd + "' id='" + ResInfo.ResRd + "'>" + "<span class='resname' style='margin-left: " + leftRowLenght + "px'>" + "<a style='margin-right: 5px; margin-left: 5px' class='Icons' id='icon_file" + i + "'><i class='fa fa-file-text fa-fw'></i></a>" + "<input style='margin-right: 5px;' class = 'Taisyo' type='checkbox' id='checkname" + i + "_" + z + "'/>" + ResInfo.ResName + "</span>" + "</td><td style='width: 500px;'>";
                if (ResInfo.OptInfo != null && ResInfo.OptInfo.length > 0) {
                    $.each(ResInfo.OptInfo, function (key, OptInfo) {
                        strRes += "&nbsp;&nbsp;&nbsp;" + "<input type='checkbox' id='checkname" + i + "_" + z + "' value='" + OptInfo.OptRd + "' " + (OptInfo.Checked == "00" ? "checked" : "") + "/>" + "&nbsp;" + "<span>" + OptInfo.OptName + "</span>" + "&nbsp;&nbsp;";
                    });
                }
                strRes += "</td></tr>";
                $("#table>#tbody").append(strRes);
                z++;
            });
            if (MenuInfo.MenuInfo.length > 0) {
                $.each(MenuInfo.MenuInfo, function (key, MenuInfo_funFor) {
                    idSize = i + "_" + z;
                    var menuInfo_fun = [];
                    menuInfo_fun = MenuInfo_funFor;
                    MenuInfosFun_Add(menuInfo_fun, idSize, lastMenuInfo, leftLenght);
                    z++;
                });

            }
        };

        /* $.ajax({
             url: getBasePath()+"/Role/GetRoleInfo",
             type: "POST",
             async: false,//设为同步请求
             data: {
                 "ExecType": "00",
                 "BusData": JSON.stringify(objBusData),
             },
             success: function (res) {
                 $("#table>#tbody").empty();
                 if(res.Status =="00" && res.Body.MsgCode == "0x00000"){

                     var MenuInfoList=res.Body.Data.MenuInfo;
                     if(MenuInfoList==null || MenuInfoList.length==0) {
                         return;
                     }
                     var strModule="";
                     var strModule1="";
                     var strModule2="";
                     var strRes="";
                     var i=0;
                     $.each(MenuInfoList,function(key,MenuInfo) {
                         strModule = "<tr class='modulerow'><td class='guanli' id='" + MenuInfo.ModuleRd + "'>"+"<input type='checkbox' id='checkname"+i+"'/>" + MenuInfo.ModuleName + "</span>"+"</td><td></td><ul></ul></tr>";
                         $("#table>#tbody").append(strModule);
                         $.each(MenuInfo.MenuInfo,function(key,ResInfo) {
                             strModule1 = "<tr class='resrow1'><td class='xinxiushan' a='"+ResInfo.ModuleRd+"'>" +"<span class='spann' style='margin-left: 25px;'>"+"--"+"<input type='checkbox' id='checkname"+"'/>"  +"<span class='resname'>"+ResInfo.ModuleName+"</span>" + "</td>";
                             if(ResInfo.ResInfo!=null && ResInfo.ResInfo.length>0) {
                                 $("#table>#tbody").append(strModule1);
                                 $.each(ResInfo.ResInfo, function (key, OptInfo) {
                                     strModule2 = "<tr class='resrow'><td class='xinxiushan' a='"+OptInfo.ResRd+"'>" +"<span class='spann' style='margin-left: 25px;'>"+"--"  +"<span class='resname'>"+OptInfo.ResName+"</span>" + "</td>";
                                     for(var i in OptInfo.OptInfo){
                                         strModule2 += "<td>&nbsp;&nbsp;&nbsp;"+"<input type='checkbox' id='add"+i+"' value='" + OptInfo.OptInfo[i].OptRd + "' " + (OptInfo.OptInfo[i].Checked == "00" ? "checked": "") + "/>" + "&nbsp;"+"<span>"+OptInfo.OptInfo[i].OptName+"</span>"+"&nbsp;&nbsp;";
                                     }
                                 });
                                 strModule2+="</td></tr>";
                             }
                             $("#table>#tbody").append(strModule2);
                         });

                         if(MenuInfo.ResInfo==null || MenuInfo.ResInfo.length==0){
                             return false;
                         }
                         $.each(MenuInfo.ResInfo,function(key,ResInfo) {
                             strRes = "<tr class='resrow'><td class='xinxiushan' pid='" + MenuInfo.ModuleRd + "' id='" + ResInfo.ResRd + "'>" + "<span class='spann'>--&nbsp;" +"<span class='resname'>"+ResInfo.ResName+"</span>" + "</td><td>";
                             if(ResInfo.OptInfo!=null && ResInfo.OptInfo.length>0) {
                                 $.each(ResInfo.OptInfo, function (key, OptInfo) {
                                     strRes += "&nbsp;&nbsp;&nbsp;"+"<input type='checkbox' id='add"+i+"' value='" + OptInfo.OptRd + "' " + (OptInfo.Checked == "00" ? "checked": "") + "/>" + "&nbsp;"+"<span>"+OptInfo.OptName+"</span>"+"&nbsp;&nbsp;";
                                 });
                             }
                             strRes+="</td></tr>";
                             $("#table>#tbody").append(strRes);
                         });
                         i++
                     });
                 }
             }
         });*/


        $(".right").show();
        //为防止错误操作
        $(".cDel").attr("b", "");

        $($("input[type='checkbox']")).each(function () {
            $(this).attr("checked", false);
        });
        $(this).attr("a", "00");

        $("#printModelName").attr("readonly", false);
        $("#printModelName").val("");

        $("#beizhu").attr("readonly", false);
        $("#beizhu").val("");
        $("#creatTime").val("");
        $("#modifyTime").val("");
        $("#modifyPeople").val("");
    });

    var ResInfo = []
    var OptInfo = []
    $("#tbody>tr>td>input").each(function () {
        $(this).click(function () {
            if ($(this).is(':checked') == true) {
                if ($(this).attr("value") == "1") {
                    var OptInfo = {
                        "OptRd": $(this).attr("value"),
                        "OptName": "增加"
                    }
                    var ResInfo = {
                        "ResRd": 1,
                        "ResName": "用户管理",
                        "OptInfoData": OptInfo
                    }

                }
                if ($(this).attr("value") == "2") {
                    var OptInfo = {
                        "OptRd": $(this).attr("value"),
                        "OptName": "删除"
                    }
                    var ResInfo = {
                        "ResRd": 1,
                        "ResName": "用户管理",
                        "OptInfoData": OptInfo
                    }

                }
                if ($(this).attr("value") == "3") {
                    var OptInfo = {
                        "OptRd": $(this).attr("value"),
                        "OptName": "修改"
                    }
                    var ResInfo = {
                        "ResRd": 1,
                        "ResName": "用户管理",
                        "OptInfoData": OptInfo
                    }
                }
            }
            else {
                ResInfo = null;
            }

        });
    });

    //获取表格里面的增删改查数据
    var getCheckedInfo = function () {
        var objModuleInfoList = [];
        var objResInfoList = [];
        var objOptInfoList = [];
        var ModuleRd, ModuleName, ResRd, ResName;
        $("tbody tr[class='modulerow']", $("#table")).each(function (key, modulerow) {
            ModuleRd = $(modulerow).find("td:first").attr("id");
            ModuleName = $(modulerow).find("td:first").text();
            objResInfoList = [];
            $("tbody tr", $("#table")).each(function (key, tr) {
                objOptInfoList = [];
                if ($(tr).attr("class") == "resrow" && $(tr).find("td:first").attr("pid") == ModuleRd) {
                    $.each($(tr).find("td:last").children(), function (key, ckBox) {
                        if ($(ckBox).is(':checked')) {
                            objOptInfoList.push({"OptRd": $(ckBox).val(), "OptName": $(ckBox).next().html()});
                        }
                    });
                    if (objOptInfoList.length > 0) {
                        ResRd = $(tr).find("td:first").attr("id");
                        ResName = $(tr).find("td:first span[class='resname']").html();
                        objResInfoList.push({"ResRd": ResRd, "ResName": ResName, "OptInfo": objOptInfoList});
                    }
                }
            });
            if (objResInfoList.length > 0) {
                objModuleInfoList.push({"ModuleRd": ModuleRd, "ModuleName": ModuleName, "ResInfo": objResInfoList})
            }
        });

        return objModuleInfoList;
    };

    //保存
    $(".cSave").click(function () {
        if (!isEdit) {
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }
        var RoleRd = $(".cDel").attr("b");
        var RoleName = $("#printModelName").val();
        var Remark = $("#beizhu").val();

        var MenuInfo = getCheckedInfo();

        var ScanPVInfo = [];
        var ScanPVInfo1 = [];
        var ScanPVInfo2 = [];
        var OpertMFlag = "";
        var OpertMFlag1 = "";
        var OpertMFlag2 = "";
        var OpertMFlag3 = "";
        var PVIInfo = [];
        var PVIInfo1 = [];
        var PVIInfo2 = [];
        var PVIInfo3 = [];

        //定义app车间系统
        var ExecPVInfo = [];
        var Ex_PVIInfo = [];
        var Ex_OpertMFlag = "";

        $(".aa>label>input").each(function () {
            if (this.checked == true) {
                if ($(this).attr("value") == "00" || $(this).attr("value") == "01" || $(this).attr("value") == "02" || $(this).attr("value") == "03" || $(this).attr("value") == "04" || $(this).attr("value") == "05" || $(this).attr("value") == "11" || $(this).attr("value") == "12"|| $(this).attr("value") == "14" || $(this).attr("value") == "15" || $(this).attr("value") == "16"|| $(this).attr("value") == "17"||$(this).attr("value")=="18" ){
                    OpertMFlag = "00";
                    var PVIInfos = {
                        "PVFlag": $(this).attr("value")
                    }
                    PVIInfo.push(PVIInfos)
                }

                if ($(this).attr("value") == "06" || $(this).attr("value") == "07" || $(this).attr("value") == "08" || $(this).attr("value") == "09") {
                    OpertMFlag1 = "01";
                    var PVIInfos = {
                        "PVFlag": $(this).attr("value")
                    }
                    PVIInfo1.push(PVIInfos)
                }
                if ($(this).attr("value") == "10") {
                    OpertMFlag2 = "02";
                    var PVIInfos = {
                        "PVFlag": $(this).attr("value")
                    }
                    PVIInfo2.push(PVIInfos)
                }
                if ($(this).attr("value") == "13") {
                    OpertMFlag3 = "03";
                    var PVIInfos3 = {
                        "PVFlag": $(this).attr("value")
                    }
                    PVIInfo3.push(PVIInfos3)
                }
            }

        });

        $(".bb>label>input").each(function () {
            if (this.checked == true) {
                if ($(this).attr("value") == "00" || $(this).attr("value") == "01") {
                    Ex_OpertMFlag = "00";
                    var EX_PVIInfos = {"PVFlag": $(this).attr("value")};
                    Ex_PVIInfo.push(EX_PVIInfos)
                }
            }

        });

        var ScanPVInfos = {
            "OpertMFlag": OpertMFlag,
            "PVIInfo": PVIInfo
        }
        if (ScanPVInfos.OpertMFlag != "") {
            ScanPVInfo.push(ScanPVInfos)
        }

        var ScanPVInfos1 = {
            "OpertMFlag": OpertMFlag1,
            "PVIInfo": PVIInfo1
        }
        if (ScanPVInfos1.OpertMFlag != "") {
            ScanPVInfo.push(ScanPVInfos1)
        }

        var ScanPVInfos2 = {
            "OpertMFlag": OpertMFlag2,
            "PVIInfo": PVIInfo2
        }
        if (ScanPVInfos2.OpertMFlag != "") {
            ScanPVInfo.push(ScanPVInfos2)
        }
        var ScanPVInfos3 = {
            "OpertMFlag": OpertMFlag3,
            "PVIInfo": PVIInfo3
        }
        if (ScanPVInfos3.OpertMFlag != "") {
            ScanPVInfo.push(ScanPVInfos3)
        }


        var ExecPVInfo_1 = {
            "OpertMFlag": Ex_OpertMFlag,
            "PVIInfo": Ex_PVIInfo
        };
        if (ExecPVInfo_1.OpertMFlag != "") {
            ExecPVInfo.push(ExecPVInfo_1);
        }

        var data = {
            "RoleName": RoleName,
            "Remark": Remark,
            "MenuInfo": MenuInfo,
            "ScanPVInfo": ScanPVInfo,
            "ExecPVInfo": ExecPVInfo
        }

        //新增保存
        if ($(".cAdd").attr("a") == "00") {
            request({
                url: '/Role/SaveRoleInfo',
                data: {"ExecType": "00", "BusData": JSON.stringify(data)}
            }, function (res) {
                currentPage = 0;
                condition = "";
                toastr.success(res.MsgDes);
                loadPage();
                $(".cAdd").attr("a", "");
            });
        }
        else {

            var datasources = {
                "RoleRd": RoleRd,
                "RoleName": RoleName,
                "Remark": Remark,
                "MenuInfo": MenuInfo,
                "ScanPVInfo": ScanPVInfo,
                "ExecPVInfo": ExecPVInfo
            };

            request({
                url: '/Role/SaveRoleInfo',
                data: {"ExecType": "02", "BusData": JSON.stringify(datasources)}
            }, function (res) {
                toastr.success(res.MsgDes);
                currentPage = 0;
                condition = "";
                loadPage();
                $(".cExit").attr("ce", "")
                $(".cDel").attr("b", "")
            })
        }
    });

    //删除
    $(".cDel").click(function () {
        var RoleRd = $(this).attr("b");
        if (RoleRd == null || RoleRd == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            var objData = {
                "RoleRd": RoleRd
            }
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {


                request({
                    url: "/Role/SaveRoleInfo", async: false,//设为同步请求
                    data: {
                        "ExecType": "01",
                        "BusData": JSON.stringify(objData)
                    }
                }, function (Body) {
                    layer.closeAll("dialog");
                    if (Body.MsgCode == "0x00000") {
                        currentPage = 0;
                        condition = "";
                        toastr.success(Body.MsgDes);
                        loadPage();
                        $(".cDel").attr("b", "")
                        $(".right").hide();
                    }

                });
                /*$.ajax({
                    url: getBasePath() + "/Role/SaveRoleInfo",
                    type: "POST",
                    async: false,//设为同步请求
                    data: {
                        "ExecType": "01",
                        "BusData": JSON.stringify(objData)
                    },
                    success: function (res) {
                        layer.closeAll("dialog");
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            currentPage=0;
                            condition = "";
                            toastr.success(res.Body.MsgDes);
                            loadPage();
                            $(".cDel").attr("b", "")
                            $(".right").hide();
                        }
                    }
                });*/
            })
        }

    });


    //手机端全选和全不选
    $(document).on("click", "#churuku", function () {
        if ($(this).is(":checked") == true) {
            $("#yclrk").prop("checked", true);
            $("#ccprk").prop("checked", true);
            $("#otherrk").prop("checked", true);
            $("#llck").prop("checked", true);
            $("#xsck").prop("checked", true);
            $("#otherck").prop("checked", true);
            $("#sctl").prop("checked", true);
            $("#lldsq").prop("checked", true);

            $("#ztrk").prop("checked", true);
            $("#zjchbh").prop("checked", true);
            $("#cpqj").prop("checked", true);
        } else {
            $("#yclrk").prop("checked", false);
            $("#ccprk").prop("checked", false);
            $("#otherrk").prop("checked", false);
            $("#llck").prop("checked", false);
            $("#xsck").prop("checked", false);
            $("#otherck").prop("checked", false);
            $("#sctl").prop("checked", false);
            $("#lldsq").prop("checked", false);

            $("#ztrk").prop("checked", false);
            $("#zjchbh").prop("checked", false);
            $("#cpqj").prop("checked", false);
        }
    })
    $(document).on("click", "#kuncun", function () {
        if ($(this).is(":checked") == true) {
            $("#sj").prop("checked", true);
            $("#pd").prop("checked", true);
            $("#yk").prop("checked", true);
            $("#tzkw").prop("checked", true);
        } else {
            $("#sj").prop("checked", false);
            $("#pd").prop("checked", false);
            $("#yk").prop("checked", false);
            $("#tzkw").prop("checked", false);
        }
    })
    $(document).on("click", "#zhijian", function () {
        if ($(this).is(":checked") == true) {
            $("#iqc").prop("checked", true);
        } else {
            $("#iqc").prop("checked", false);
        }
    })
    $(document).on("click", "#shengchan", function () {
        if ($(this).is(":checked") == true) {
            $("#sl").prop("checked", true);
        } else {
            $("#sl").prop("checked", false);
        }
    })

    $(document).on("click", "#renwu", function () {
        if ($(this).is(":checked") == true) {
            $("#dj").prop("checked", true);
            $("#by").prop("checked", true);

        } else {
            $("#dj").prop("checked", false);
            $("#by").prop("checked", false);

        }
    })
    //Web端全选和全不选

    /* var istrue=true;
     setInterval(function(){
         for(var i = 0; i<$(".Taisyo").length; i++){
             var checkbox = $(".Taisyo")[i];
             var idNumber="#add"+checkbox.id.substring(9,checkbox.id.length);
             if(checkbox.checked){
                 for (var j =0; j<$(""+idNumber+"").length; j++){
                     var checkboxj = $(""+idNumber+"")[j];
                     if (!checkboxj.checked) {
                         istrue=false;
                         return;
                     }
                 }
                 if (istrue){
                     $("tbody td "+idNumber+"").each(function () {
                         $(this).prop("checked", true);
                     });
                 }

             }else{

             }
         }
     },200);*/

    /*$(document).on("click", ".Taisyo", function () {
        var checkbox = $(this)[0];
        var idNumber = checkbox.id;
        if ($(this).is(":checked") == true) {
            $("tbody td " + idNumber + "").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td " + idNumber + "").each(function () {
                $(this).prop("checked", false);
            });
        }
    });*/

    $(document).on("click", ".Taisyo", function () {
        var checkbox = $(this)[0];
        var idNumber = "#" + checkbox.id;
        if ($(idNumber).is(":checked") == true) {
            $(idNumber).prop("checked", true);
            $("tbody td " + idNumber + "").each(function () {
                $(this).prop("checked", true);
            });
            for (var i = 1; i < 10000; i++) {
                var idNumberF = idNumber + "win" + i;
                var idNumberF_ = idNumber + "_" + i;
                if ($(idNumberF)[0] != undefined) {
                    $(idNumberF).prop("checked", true);
                    $("tbody td " + idNumberF + "").each(function () {
                        $(this).prop("checked", true);
                    });
                    var idNumberF_A = idNumberF + "_1";
                    if ($(idNumberF_A)[0] != undefined) {
                        var isTrue = true;
                        clickChecked(idNumberF, isTrue);
                    }
                } else if ($(idNumberF_)[0] != undefined) {
                    $(idNumberF_).prop("checked", true);
                    $("tbody td " + idNumberF_ + "").each(function () {
                        $(this).prop("checked", true);
                    });
                    var idNumberF_A = idNumberF_ + "_1";
                    if ($(idNumberF_A)[0] != undefined) {
                        var isTrue = true;
                        clickChecked(idNumberF_, isTrue);
                    }
                } else {
                    return;
                }

            }
        } else {
            $(idNumber).prop("checked", false);
            $("tbody td " + idNumber + "").each(function () {
                $(this).prop("checked", false);
            });
            for (var i = 1; i < 10000; i++) {
                var idNumberF = idNumber + "win" + i;
                var idNumberF_ = idNumber + "_" + i;
                if ($(idNumberF)[0] != undefined) {
                    $(idNumberF).prop("checked", false);
                    $("tbody td " + idNumberF + "").each(function () {
                        $(this).prop("checked", false);
                    });
                    var idNumberF_A = idNumberF + "_1";
                    if ($(idNumberF_A)[0] != undefined) {
                        var isTrue = false;
                        clickChecked(idNumberF, isTrue);
                    }
                } else if ($(idNumberF_)[0] != undefined) {
                    $(idNumberF_).prop("checked", false);
                    $("tbody td " + idNumberF_ + "").each(function () {
                        $(this).prop("checked", false);
                    });
                    var idNumberF_A = idNumberF_ + "_1";
                    if ($(idNumberF_A)[0] != undefined) {
                        var isTrue = false;
                        clickChecked(idNumberF_, isTrue);
                    }
                } else {
                    return;
                }

            }
        }
    });

    var clickChecked = function (idNumber, istrue) {
        for (var i = 1; i < 10000; i++) {
            var idNumberA = idNumber + "_" + i;
            if ($(idNumberA)[0] != undefined) {
                $(idNumberA).prop("checked", istrue);
                $("tbody td " + idNumberA + "").each(function () {
                    $(this).prop("checked", istrue);
                });
                var idNumberB = idNumberA + "_1";
                if ($(idNumberB)[0] != undefined) {
                    clickChecked(idNumberA, istrue);
                }
            } else {
                return;
            }
        }
    };

    $(document).on("click", ".Icons", function () {
        var icon = $(this)[0];
        var ids = "#trRow";
        var Number = icon.id.substring(4, icon.id.length);
        for (var i = 1; i < 10000; i++) {
            var idNumber = ids + Number + "win" + i;
            var idNumber_ = ids + Number + "_" + i;
            if ($(idNumber)[0] != undefined) {
                var style_ = $(idNumber).attr("style").substring(0, 14);
                var style_ours = $(idNumber).attr("style").substring(14, $(idNumber).attr("style").length);
                var style_block = "display:block;";
                var style_none = "display: none;";
                var istrue;
                if (style_ == style_block) {
                    $(idNumber).attr("style", style_none + style_ours);
                    istrue = false;
                } else {
                    $(idNumber).attr("style", style_block + style_ours);
                    istrue = true;
                }
                var idNumberA = idNumber + "_1";
                if ($(idNumberA)[0] != undefined) {
                    clickFuns(idNumber, istrue);
                }
            } else if ($(idNumber_)[0] != undefined) {
                var istrue;
                var style_ = $(idNumber_).attr("style").substring(0, 14);
                var style_ours = $(idNumber_).attr("style").substring(14, $(idNumber_).attr("style").length);
                var style_block = "display:block;";
                var style_none = "display: none;";
                if (style_ == style_block) {
                    $(idNumber_).attr("style", style_none + style_ours);
                    istrue = false;
                } else {
                    $(idNumber_).attr("style", style_block + style_ours);
                    istrue = true;
                }
                var idNumberA = idNumber_ + "_1";
                if ($(idNumberA)[0] != undefined) {
                    clickFuns(idNumber_, istrue);
                }
            } else {
                return;
            }
        }
    });

    var clickFuns = function (idNumber, istrue) {
        for (var i = 1; i < 10000; i++) {
            var idNumberA = idNumber + "_" + i;
            if ($(idNumberA)[0] != undefined) {
                var style_ours = $(idNumberA).attr("style").substring(14, $(idNumberA).attr("style").length);
                var style_block = "display:block;";
                var style_none = "display: none;";
                if (istrue) {
                    $(idNumberA).attr("style", style_block + style_ours);
                } else {
                    $(idNumberA).attr("style", style_none + style_ours);
                }
                var idNumberB = idNumberA + "_1";
                if ($(idNumberB)[0] != undefined) {
                    clickFuns(idNumberA, istrue);
                }
            } else {
                return;
            }
        }
    }


    /*$(document).on("click", "#checkname1", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add1").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add1").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
    $(document).on("click", "#checkname2", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add2").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add2").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
    $(document).on("click", "#checkname3", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add3").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add3").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
    $(document).on("click", "#checkname4", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add4").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add4").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
    $(document).on("click", "#checkname5", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add5").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add5").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
    $(document).on("click", "#checkname6", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add6").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add6").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
    $(document).on("click", "#checkname7", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add7").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add7").each(function () {
                $(this).prop("checked", false);
            });
        }
    })


    $(document).on("click", "#checkname8", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add8").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add8").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
    $(document).on("click", "#checkname9", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add9").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add9").each(function () {
                $(this).prop("checked", false);
            });
        }
    })

    $(document).on("click", "#checkname10", function () {
        if ($(this).is(":checked") == true) {
            $("tbody td #add10").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("tbody td #add10").each(function () {
                $(this).prop("checked", false);
            });
        }
    })
*/

    /*    var Data={
            "MenuInfo": [
                        {
                            "ModuleRd": 1,
                            "ModuleName": "系统管理",
                            "ResInfo": [
                                {
                                    "ResRd": 1,
                                    "ResName": "用户管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 1,
                                            "OptName": "查询",
                                            "Checked": "00"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 2,
                                    "ResName": "角色管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 4,
                                            "OptName": "查询",
                                            "Checked": "00"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 3,
                                    "ResName": "字典管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 5,
                                            "OptName": "查询",
                                            "Checked": "00"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 73,
                                    "ResName": "发布App",
                                    "OptInfo": [
                                        {
                                            "OptRd": 76,
                                            "OptName": "查询",
                                            "Checked": "00"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": [
                                {
                                    "ModuleRd": 12,
                                    "ModuleName": "系统管理-1",
                                    "ResInfo": [
                                        {
                                            "ResRd": 76,
                                            "ResName": "test",
                                            "OptInfo": [
                                                {
                                                    "OptRd": 79,
                                                    "OptName": "查询",
                                                    "Checked": "01"
                                                }
                                            ]
                                        }
                                    ],
                                    "MenuInfo": []
                                }
                            ]
                        },
                        {
                            "ModuleRd": 2,
                            "ModuleName": "资源管理",
                            "ResInfo": [
                                {
                                    "ResRd": 41,
                                    "ResName": "设备管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 44,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 42,
                                    "ResName": "设备组管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 45,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 50,
                                    "ResName": "设备家族",
                                    "OptInfo": [
                                        {
                                            "OptRd": 53,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 52,
                                    "ResName": "技能信息",
                                    "OptInfo": [
                                        {
                                            "OptRd": 55,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 53,
                                    "ResName": "技能组信息",
                                    "OptInfo": [
                                        {
                                            "OptRd": 56,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 75,
                                    "ResName": "设备状态",
                                    "OptInfo": [
                                        {
                                            "OptRd": 78,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 54,
                                    "ResName": "设备状态变更原因",
                                    "OptInfo": [
                                        {
                                            "OptRd": 57,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 74,
                                    "ResName": "设备状态模型",
                                    "OptInfo": [
                                        {
                                            "OptRd": 77,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 55,
                                    "ResName": "保养原因",
                                    "OptInfo": [
                                        {
                                            "OptRd": 58,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 64,
                                    "ResName": "技能培训记录",
                                    "OptInfo": [
                                        {
                                            "OptRd": 67,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 3,
                            "ModuleName": "基础信息管理",
                            "ResInfo": [
                                {
                                    "ResRd": 4,
                                    "ResName": "企业管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 6,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 5,
                                    "ResName": "工厂管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 7,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 6,
                                    "ResName": "工作中心",
                                    "OptInfo": [
                                        {
                                            "OptRd": 8,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 7,
                                    "ResName": "客户信息",
                                    "OptInfo": [
                                        {
                                            "OptRd": 9,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 8,
                                    "ResName": "序号管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 13,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 10,
                                    "ResName": "批次等级管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 14,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 11,
                                    "ResName": "原因代码管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 15,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 12,
                                    "ResName": "计量单位管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 16,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 13,
                                    "ResName": "打印机管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 17,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 14,
                                    "ResName": "打印模板管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 18,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 47,
                                    "ResName": "原因代码组管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 50,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 51,
                                    "ResName": "紧急度代码",
                                    "OptInfo": [
                                        {
                                            "OptRd": 54,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 58,
                                    "ResName": "包装规格管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 61,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 72,
                                    "ResName": "工单类型",
                                    "OptInfo": [
                                        {
                                            "OptRd": 75,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 4,
                            "ModuleName": "产品工艺管理",
                            "ResInfo": [
                                {
                                    "ResRd": 15,
                                    "ResName": "数据采集管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 19,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 16,
                                    "ResName": "作业管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 20,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 17,
                                    "ResName": "文件组管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 21,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 18,
                                    "ResName": "文件管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 22,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 19,
                                    "ResName": "工艺文件管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 23,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 59,
                                    "ResName": "物料类别管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 62,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 20,
                                    "ResName": "物料管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 24,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 60,
                                    "ResName": "产品管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 63,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 21,
                                    "ResName": "产品族管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 25,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 22,
                                    "ResName": "BOM管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 26,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 23,
                                    "ResName": "工序管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 27,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 24,
                                    "ResName": "工艺流程管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 28,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 5,
                            "ModuleName": "计划管理",
                            "ResInfo": [
                                {
                                    "ResRd": 25,
                                    "ResName": "工单管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 29,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 38,
                                    "ResName": "产品批次创建",
                                    "OptInfo": [
                                        {
                                            "OptRd": 41,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 39,
                                    "ResName": "无源批次创建",
                                    "OptInfo": [
                                        {
                                            "OptRd": 42,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 40,
                                    "ResName": "物料批次创建",
                                    "OptInfo": [
                                        {
                                            "OptRd": 43,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 62,
                                    "ResName": "采购单管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 65,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 61,
                                    "ResName": "来料收货通知单管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 64,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 44,
                                    "ResName": "领料单管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 47,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 6,
                            "ModuleName": "车间管理",
                            "ResInfo": [
                                {
                                    "ResRd": 27,
                                    "ResName": "工序操作",
                                    "OptInfo": [
                                        {
                                            "OptRd": 30,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 28,
                                    "ResName": "包装",
                                    "OptInfo": [
                                        {
                                            "OptRd": 31,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 7,
                            "ModuleName": "质量管理",
                            "ResInfo": [
                                {
                                    "ResRd": 30,
                                    "ResName": "供应商管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 33,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 31,
                                    "ResName": "IQC管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 34,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 56,
                                    "ResName": "冻结批次原因",
                                    "OptInfo": [
                                        {
                                            "OptRd": 59,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 57,
                                    "ResName": "解冻批次原因",
                                    "OptInfo": [
                                        {
                                            "OptRd": 60,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 65,
                                    "ResName": "批次冻结",
                                    "OptInfo": [
                                        {
                                            "OptRd": 68,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 66,
                                    "ResName": "批次解冻",
                                    "OptInfo": [
                                        {
                                            "OptRd": 69,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 67,
                                    "ResName": "标记不良",
                                    "OptInfo": [
                                        {
                                            "OptRd": 70,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 8,
                            "ModuleName": "仓储管理",
                            "ResInfo": [
                                {
                                    "ResRd": 32,
                                    "ResName": "仓库信息",
                                    "OptInfo": [
                                        {
                                            "OptRd": 35,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 49,
                                    "ResName": "库位信息",
                                    "OptInfo": [
                                        {
                                            "OptRd": 52,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 33,
                                    "ResName": "入库单管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 36,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 34,
                                    "ResName": "出库单管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 37,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 35,
                                    "ResName": "库存管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 38,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 43,
                                    "ResName": "盘点管理",
                                    "OptInfo": [
                                        {
                                            "OptRd": 46,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 9,
                            "ModuleName": "通用管理",
                            "ResInfo": [
                                {
                                    "ResRd": 36,
                                    "ResName": "拆批",
                                    "OptInfo": [
                                        {
                                            "OptRd": 39,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 37,
                                    "ResName": "打印记录查询",
                                    "OptInfo": [
                                        {
                                            "OptRd": 40,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 45,
                                    "ResName": "更改数量",
                                    "OptInfo": [
                                        {
                                            "OptRd": 48,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 46,
                                    "ResName": "拆包",
                                    "OptInfo": [
                                        {
                                            "OptRd": 49,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 63,
                                    "ResName": "全局配置",
                                    "OptInfo": [
                                        {
                                            "OptRd": 66,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 68,
                                    "ResName": "报废",
                                    "OptInfo": [
                                        {
                                            "OptRd": 71,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 69,
                                    "ResName": "打开",
                                    "OptInfo": [
                                        {
                                            "OptRd": 72,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 70,
                                    "ResName": "关闭",
                                    "OptInfo": [
                                        {
                                            "OptRd": 73,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 71,
                                    "ResName": "导入库存",
                                    "OptInfo": [
                                        {
                                            "OptRd": 74,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        },
                        {
                            "ModuleRd": 11,
                            "ModuleName": "报表管理",
                            "ResInfo": [
                                {
                                    "ResRd": 48,
                                    "ResName": "批次查询",
                                    "OptInfo": [
                                        {
                                            "OptRd": 51,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                },
                                {
                                    "ResRd": 29,
                                    "ResName": "在线跟踪",
                                    "OptInfo": [
                                        {
                                            "OptRd": 32,
                                            "OptName": "查询",
                                            "Checked": "01"
                                        }
                                    ]
                                }
                            ],
                            "MenuInfo": []
                        }
                    ]
        };*/

    /*alert(JSON.stringify(Data.MenuInfo))*/


    /*
    for(var i in Data.MenuInfo){
        $("#tbody2 >ul").append("<li><input type='checkbox' value='Data.MenuInfo[i].ModuleRd'/>"+Data.MenuInfo[i].ModuleName+"</li>");

        for(var i1 in Data.MenuInfo[i].ResInfo){
            $("#tbody3 >ul").append("<li><input type='hidden' value='Data.MenuInfo[i].ResInfo.[i1].ResRd'/>"+Data.MenuInfo[i].ResInfo[i1].ResName+"</li>");
        }
    }
    */


});