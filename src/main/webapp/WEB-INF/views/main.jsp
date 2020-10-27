<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html style="position: relative;">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>正在加载中...<%--PNC-MES制造执行系统--%></title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <style type="text/css">
        html {
            overflow: hidden;
        }

        .navbar-nav > li > a {
            padding-top: 9px;
            padding-bottom: 7px;
        }

        #textSeach {
            height: 34px;
        }
    </style>
    <!-- IE -->
    <script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/responsive/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/responsive/respond.min.js"></script>
</head>

<body class="hold-transition skin-blue sidebar-mini fixed">
<div class="wrapper">
    <!-- Main Header -->
    <header class="main-header">
        <!-- Logo -->
        <a href="" class="logo">
            <span class="logo-lg pull-left"><img id="imags" src="" alt="" style="margin-top: -5px; width: 122px; "></span>
            <%--<span class="logo-lg pull-left" style="display: none">
            <img id="imags" src="${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/logo.png"
            alt="" style="margin-top: -5px; width: 187px; "></span>
            --%>
        </a>
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">切换导航</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <%--<li class="caiMenu"><a href="javascript:void(0);" data-toggle="dropdown">&copy; V0.1</a></li>--%>
                    <!--   <li class="caiMenu" data-toggle="tooltip"  data-placement="bottom" title="帮助文档" style="cursor: pointer;"><a target="_blank" href="http://10.4.1.252/PNC-MES制造执行管理系统-操作手册.pdf"><span class="glyphicon glyphicon-question-sign"></span></a></li>
                        <li class="dropdown messages-menu">-->
                    <!-- 消息图标-->
                    <!--  <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-envelope-o"></i>
                        <span class="label label-success">0</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有0条提醒</li>
                        <li class="footer"><a href="javascript:void(0);">查看所有提醒</a></li>
                    </ul>
                </li>-->
                    <!-- 用户个人信息开始 -->
                    <li class="dropdown user user-menu">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user-circle" style="font-size: 18px;"></i>
                            <span class="hidden-xs">${user.realName}</span>
                        </a>
                        <!-- 用户信息便签-->
                        <input type="hidden" id="userRd" value="${user.ruid}"/>
                        <input type="hidden" id="roleRd" value="${roleRd}"/>
                        <ul class="dropdown-menu">
                            <li class="user-header">
                                <i class="fa fa-user-circle" style="color: white;font-size: 100px;"></i>
                                <p>${user.realName}</p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="javascript:void(0);" class="btn btn-default btn-flat modify"
                                       onclick="modalOpen()">修改密码</a>
                                </div>
                                <div class="pull-right">
                                    <a href="javascript:void(0);" id="logout" class="btn btn-default btn-flat">退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <%--<li class="shik1">
                        <a href="javascript:void(0);" data-toggle="control-sidebar">
                            <!-- <i class="fa fa-user-o fa-1x"></i> -->
                            <img class="pf1" src="${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/pf.png" alt="" style="display: block;width: 20px;">
                        </a>
                    </li>--%>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar">
        <section class="sidebar">
            <!-- <ul class="nav nav-tabs nav-justified control-sidebar-tabs" id="tabLeft">
                 <li id="li1"><a href="javascript:void(0);" id="biaozhun" data-toggle="tab">标准</a></li>
                 <li id="li2"><a href="javascript:void(0);" id='changyong' data-toggle="tab">常用</a></li>
             </ul>-->
            <div class="input-group">
                <input type="text" name="q" class="form-control" id="textSeach" placeholder="搜索...">
                <span class="input-group-btn">
                            <button name="search" id="search-btn" class="btn btn-flat">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
            </div>
            <ul class="sidebar-menu">
                <li class="menus"></li>
            </ul>
        </section>
    </aside>
    <div class="content-wrapper" id="content-wrapper">
        <div class="content-tabs">
            <button class="roll-nav roll-left tabLeft" onclick="scrollTabLeft()">
                <i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs menuTabs tab-ui-menu" id="tab-menu">
                <div class="page-tabs-content" style="margin-left: 0px;">
                </div>
            </nav>
            <button class="roll-nav roll-right tabRight" onclick="scrollTabRight()">
                <i class="fa fa-forward" style="margin-left: 3px;"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown tabClose" data-toggle="dropdown">
                    页签操作<i class="fa fa-caret-down" style="padding-left: 3px;"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-right" style="min-width: 128px;z-index: 2100;">
                    <li><a class="tabReload" href="javascript:refreshTab();" style="z-index: 2100;">刷新当前</a></li>
                    <li><a class="tabCloseCurrent" href="javascript:closeCurrentTab();">关闭当前</a></li>
                    <li><a class="tabCloseAll" href="javascript:closeOtherTabs(true);">全部关闭</a></li>
                    <li><a class="tabCloseOther" href="javascript:closeOtherTabs();">除此之外全部关闭</a></li>
                </ul>
            </div>
            <button class="roll-nav roll-right fullscreen"><i class="fa fa-arrows-alt"></i></button>
        </div>
        <div class="content-iframe " style="background-color: #ffffff; ">
            <div class="tab-content " id="tab-content">
            </div>
        </div>
    </div>
    <aside class="control-sidebar control-sidebar-dark">
        <div class="tab-content">
            <div class="tab-pane" id="control-sidebar-home-tab">
            </div>
        </div>
    </aside>
    <div class="control-sidebar-bg"></div>
</div>
<div id="id" style="width: 600px;">
    <%--<p>倒计时:<span id="time"></span></p>--%>
</div>
<!-- jQuery 2.2.3 -->
<script src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/min/js/supershopui.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/mainPage.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/user.js?v=1"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/toastr.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/socket.io.js'></script>
<script type="text/javascript">
    $(function () {
        /*
        var socket = io("http://192.168.20.230:3000");
        socket.on('chat message', yqfunction (msg) {
            console.log('message: ' + msg);
            var oldd = msg.split('_(');
            if (oldd.length == 2) {
                var d = oldd[0].split('_');
                if (d.length == 4) {
                    var user = JSON.parse(localStorage.user);
                    if (user.UserRd == d[3]) {
                        var timer = null;
                        timer = setInterval(function () {
                            if (!flag) {
                                modalMsgOpen2(oldd[1].substr(0, oldd[1].length - 1));
                                clearInterval(timer);
                            }
                        }, 1000);
                    }
                }
            }
        });
        */

        $("[data-toggle='tooltip']").tooltip();
        //换肤之后菜单中的版本的颜色的改变
        $('.list-unstyled>li:nth-child(2)').on('click', function () {
            if ($('.skin-black ').css('background-color', '#fff')) {
                $('.caiMenu').css('color', '#000');
            } else {
                $('.caiMenu').css('color', '#fff');
            }
        })
        // 改变选中的背景颜色
        var changeBackGround = function () {
            $('.treeview-menu li').each(function () {
                if ($(this).find("ul").length <= 0) {
                    $(this).on("click", function () {
                        $('.treeview-menu li').each(function () {
                            $(this).css("backgroundColor", "");
                        });
                        $(this).css("backgroundColor", "#3C8DBC");
                    })
                }
            })
        };
        addTabs(({
            id: '10001',
            title: '欢迎页',
            close: false,
            url: "${pageContext.request.contextPath}/WelcomePG"
        }));
        App.fixIframeCotent();
        var menulist = [];

        /*---加载左侧菜单的时候，url+targetType报文上面没有给----*/
 /*       $.ajax({
            url: "${pageContext.request.contextPath}/Sys/GetSysInfo",
            type: "post",
            data: {"ExecType": "00", "BusData": "{'UserRd':${ user.ruid }}"},
            success: function (res) {
                var aaa = res.Body.Data.MenuInfo;
                menulist = eachRes(aaa);
                document.title = res.Body.Data.AppName == undefined ? 'PNC-MES制造执行系统' : res.Body.Data.AppName;
                if (res.Body.Data.AppLogo != undefined && res.Body.Data.AppLogo != "") {
                    $("#imags").attr("src", res.Body.Data.AppLogo);
                } else {
                    $("#imags").attr("src","${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/logo.png");
                }
                $('.sidebar-menu').sidebarMenu({
                    data: menulist,
                    param: {
                        strUser: 'admin'
                    }
                });
                changeBackGround();
            }
        });*/

        //遍历资源
        function eachRes(data) {
            var menulist = [];
            for (var i = 0; i < data.length; i++) {
                var module = {
                    id: data[i].ModuleRd == undefined ? "" : data[i].ModuleRd,
                    isOpen: data[i].IsOpen == undefined ? "" : data[i].IsOpen,
                    text: data[i].ModuleName == undefined ? "" : data[i].ModuleName,
                    icon: data[i].ModuleIcon == undefined ? "" : data[i].ModuleIcon,
                };
                var reslist = [];

                if (data[i].ResInfo == undefined || data[i].ResInfo.length < 1) {
                    //判断是否有子集
                    if (data[i].MenuInfo != undefined && data[i].MenuInfo.length > 0) {
                        reslist = reslist.concat(eachRes(data[i].MenuInfo));
                    }
                }

                for (var j = 0; j < data[i].ResInfo.length; j++) {
                    //判断是否有子集
                    if (data[i].MenuInfo != undefined && data[i].MenuInfo.length > 0) {
                        for (var mj = 0; mj < data[i].MenuInfo.length; mj++) {
                            if (data[i].MenuInfo[mj].Pos <= data[i].ResInfo[j].Pos) {
                                var dataMj = [];
                                dataMj.push(data[i].MenuInfo[mj]);
                                reslist = reslist.concat(eachRes(dataMj));
                                data[i].MenuInfo[mj].Pos = data[i].MenuInfo[mj].Pos + 10000;
                            }
                        }
                    }

                    var res = {
                        id: data[i].ResInfo[j].ResRd == undefined ? "" : data[i].ResInfo[j].ResRd,
                        text: data[i].ResInfo[j].ResName == undefined ? "" : data[i].ResInfo[j].ResName,
                        //isOpen:aaa[i].ResInfo[j].isOpen==undefined? "":aaa[i].isOpen,
                        icon: data[i].ResInfo[j].ResIcon == undefined ? "" : data[i].ResInfo[j].ResIcon,
                        url: data[i].ResInfo[j].ResUrl == undefined ? "" : getBasePath() + data[i].ResInfo[j].ResUrl,
                        /*targetType:aaa[i].ResInfo[j].OpenType==undefined? "":aaa[i].ResInfo[j].OpenType*/
                        targetType: "iframe-tab"
                    };
                    reslist.push(res);
                }

                //判断是否有子集
                if (data[i].MenuInfo != undefined && data[i].MenuInfo.length > 0) {
                    for (var mj = 0; mj < data[i].MenuInfo.length; mj++) {
                        if (data[i].ResInfo.length > 1) {
                            var lenghtf = data[i].ResInfo.length - 1;
                            if (data[i].MenuInfo[mj].Pos > data[i].ResInfo[lenghtf].Pos && data[i].MenuInfo[mj].Pos < 10000) {
                                var dataMj = [];
                                dataMj.push(data[i].MenuInfo[mj]);
                                reslist = reslist.concat(eachRes(dataMj));
                                data[i].MenuInfo[mj].Pos = data[i].MenuInfo[mj].Pos + 10000;
                            }
                        }
                    }
                }

                module["children"] = reslist;
                menulist.push(module);
            }
            return menulist;
        }

        // 搜索功能
        $('#search-btn').click(function () {
            var searchVal = $('#textSeach').val();
            //这里是每次搜索之前需要恢复原样的操作
            $('.treeview-menu li').each(function () {
                $(this).parent().hide();
                $(this).parent().parent().removeClass("active");
                $(this).parent().removeClass("menu-open");
                $(this).css("backgroundColor", "");
            })
            if (searchVal.trim() != "") {
                // 遍历侧边栏所有的菜单
                $('.treeview-menu li').each(function () {
                    var str = $(this).text() + "";
                    if (str.indexOf(searchVal) > -1) {
                        //改变颜色，和展开的状态
                        $(this).parent().parent().addClass("active");
                        $(this).parent().show();
                        $(this).parent().addClass("menu-open");
                        $(this).css("backgroundColor", "#3C8DBC");
                    }
                })
                changeBackGround();
            }
        });
        // 点击标准，加载标准的树菜单
        $("#biaozhun").on('click', function () {
            App.fixIframeCotent();
            $('.input-group').show();
            $('.sidebar-menu').html("");
            $('.sidebar-menu').sidebarMenu({
                data: menulist,
                param: {
                    strUser: 'admin'
                }
            });
        });

        //树的键盘keydown事件
        $("#textSeach").keydown(function (event) {
            if (event.keyCode == 13) {
                var searchVal = $('#textSeach').val();
                //这里是每次搜索之前需要恢复原样的操作
                $('.treeview-menu li').each(function () {
                    $(this).parent().hide();
                    $(this).parent().parent().removeClass("active");
                    $(this).parent().removeClass("menu-open");
                    $(this).css("backgroundColor", "");
                })
                if (searchVal.trim() != "") {
                    // 遍历侧边栏所有的菜单
                    $('.treeview-menu li').each(function () {
                        var str = $(this).text() + "";
                        if (str.indexOf(searchVal) > -1) {
                            //改变颜色，和展开的状态
                            $(this).parent().parent().addClass("active");
                            $(this).parent().show();
                            $(this).parent().addClass("menu-open");
                            $(this).css("backgroundColor", "#3C8DBC");
                        }
                    })
                    changeBackGround();
                }
            }
        })

        // 点击常用，加载常用的树菜单
        $("#changyong").on('click', function () {
            $('.input-group').hide();
            App.fixIframeCotent();
            var menus = [{
                id: '10005',
                text: "角色管理",
                isOpen: false,
                icon: "fa fa-circle-o",
                url: " javascript:;",
                targetType: "iframe-tab"
            }, {
                id: "10027",
                text: "工单管理",
                targetType: "iframe-tab",
                url: "mesadmin/WEB-INF/views/plan/worder/worderinfo.html",
                icon: "fa fa-table"
            }, {
                id: "10028",
                text: "数据采集",
                targetType: "iframe-tab",
                url: " ",
                icon: "fa fa-table"
            }];
            $('.sidebar-menu').html("");
            $('.sidebar-menu').sidebarMenu({
                data: menus,
                param: {
                    strUser: 'admin'
                }
            });
        });
        $("#logout").click(function () {
            layer.confirm('确认要退出登录吗？', {
                    area: ['280px', '30%'],
                    btn: ['确认', '取消'],
                }, function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/User/LoginoutInfo",
                        success: function (res) {
                            window.location.href = "${pageContext.request.contextPath}/Login";
                        }
                    });
                }, function () {
                }
            );
        })
    })

    // 修改密码弹窗
    function modalOpen() {
        $.fn.modalOpen({
            url: '${pageContext.request.contextPath}/User/ChangePwdPG', //加载的是要用模态框弹出用来编辑的内容
            width: "500px",
            height: "350px",
        });
    }

    var ss = function (msg) {
        return layer.alert(msg, {icon: 3, title: '通知', offset: 'rb', btn: '我知道', shade: 0.3}, function (index) {
            //do something

            layer.close(index);
        });
    };


    var flag = false;

    function modalMsgOpen2(msg) {
        flag = true;
        var arr = msg.split(",");
        $("#id").html("");
        arr.forEach(function (obj) {
            $("#id").append("<p>" + obj + "</p>")
        });
        layer.open({
            type: 1,
            btn: ['我知道'],
            area: ['600px', '400px']
            , yes: function (index, layero) {
                layer.close(index);
                flag = false;
            },
            content: $('#id') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        });

        var timer = null;
        var times = 15;
        timer = setInterval(function () {
            $("#time").val(times);
            times--;
            if (times <= 0) {
                flag = false;
                layer.closeAll('page');
                clearInterval(timer);
            }
        }, 1000);
    }

    function down(time, timer) {
        if (time <= 0) {
            clearInterval(timer);
        }
    }

    //通知
    function modalMsgOpen(msg) {
        layer.alert(msg, {icon: 3, title: '通知', offset: 'rb', btn: '我知道', shade: 0.3, closeBtn: 0}, function (index) {
            //do something

            layer.close(index);
        });
    }
</script>
</body>
</html>

