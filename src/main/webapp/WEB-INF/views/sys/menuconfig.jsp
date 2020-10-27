<%--
  Created by IntelliJ IDEA.
  User: lijingjing
  Date: 2017/12/14
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>菜单配置</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!-- 树开始 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/toastr.css"/>
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <%--<div class="btn btn-primary c" id="sx">筛选</div>--%>

    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="addPurch">新增
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center " id="AddSysVer" a role="menuitem" tabindex="-1">系统版本</a>
            </li>
            <li role="presentation"><a class="text-center " id="modal" a role="menuitem" tabindex="-1">模块</a>
            </li>
            <li role="presentation"><a class="text-center " a b id="modalChildren" role="menuitem" tabindex="-1">子模块</a>
            </li>
        </ul>
    </div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center " a id="JHSysVer" role="menuitem" tabindex="-1">激活系统版本</a>
            </li>
            <li role="presentation"><a class="text-center " a id="DelSysVer" role="menuitem" tabindex="-1">删除系统版本</a>
            </li>
            <li role="presentation"><a class="text-center " a id="delete" role="menuitem" tabindex="-1">删除</a>
            </li>
        </ul>
    </div>
    <button class="btn btn-primary _close">关闭</button>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2  clearfix zsyLeft1">
    <div class="left zsyZi1 left1" id="left2" style="height: 95.5%">
        <div style="width: 150px;height: 35px; float: left; margin-left: 5px; margin-top: 5px;">

            <select class="form-control selectpicker" id="sysVer" title="请选择系统版本" data-live-search="true"
                    data-max-options="1"
                    multiple style="width: 150px"></select>

            <%-- <div style="width: 70px; height: 25px; background-color: #009E94; float: left"><a
                     style="color: #0C0C0C; margin-left: 25%;" href="#tab_1"
                     data-toggle="tab" class="ontherModel">标准版</a></div>
             <div style="width: 70px; height: 25px; float: left"><a
                     style="color: #0C0C0C; margin-left: 25%;" href="#tab_1"
                     data-toggle="tab" class="ontherModel">标准版</a></div>--%>
        </div>
        <div>
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索模块">
            <img class="searchTree" style="margin-top: 30px;"
                 src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png"/>
            <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页"
                                                                                                         id="next"
                                                                                                         style="display: block;float: right;cursor: pointer">　>>　</a>
            </div>
        </div>
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" style="height: 93%" id="_right">
        <form id="moduleForm" class="form-inline">
            <div class="row rightTop">
                <div class="col-md-12">
                    <%--floor1--%>
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="ontherModel">模块信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab" class="ontherHide">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="form-inline">
                                    <div class='f1'>
                                        <div class="form-group formgroup">
                                            <label>模块名称</label>
                                            <input type="text" class="form-control check" a id="modalName"
                                                   name="PurChCode"
                                                   placeholder="" maxlength="40">
                                        </div>
                                        <div class="form-group formgroup">
                                            <label>上级模块名称</label>
                                            <input type="text" class="form-control " a id="topModalName"
                                                   name="PurChCode"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class='f1'>
                                        <label>&ensp;&nbsp;Url路径</label>
                                        <input type="text" class="form-control" id="resUrl" style="width: 510px;"
                                               autocomplete="off"
                                               name="resUrl" maxlength="40">
                                    </div>
                                    <div class='f1'>
                                        <label>链接类型</label>
                                        <select id="linkType" style="width: 150px;">
                                            <option value="00" selected>内链</option>
                                            <option value="01">外链</option>
                                        </select>
                                    </div>
                                    <div class='f1'>
                                        <label>&ensp;&ensp;排序号</label>
                                        <input type="number" class="form-control" id="ppos" style="width: 100px;"
                                               autocomplete="off"
                                               name="ppos"/>
                                    </div>
                                    <div class='f1 shutdo'>
                                        <label style="margin-left: -13px;">&ensp;&ensp;关闭模块</label>
                                        <label for="shutdown"><input type="checkbox" name="shutdown " id="shutdown"/>关闭</label>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf" %>
                            </div>
                        </div>
                        <div class="nav-tabs-custom divhide" style="margin-top: 30px;margin-bottom:0px;">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#tab_3" data-toggle="tab">资源信息</a></li>
                                <li class="" id="tab_4_li" style="position: relative;">
                                    <a href="#tab_4" data-toggle="tab" class=" lr-delete ">资源设置&emsp;</a>
                                    <i class="fa fa-remove XH" style="position: absolute;top: 2px;right: 4px;"></i>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active clearfix" id="tab_3">
                                    <div class="f2_4 btn-group" id="maInfoButton">
                                        <div class="btn btn-primary add2">新增</div>
                                        <div class="btn btn-primary del1">删除</div>
                                    </div>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="box-body tablecontent">
                                                    <div>
                                                        <table id="list4"></table>
                                                        <div id="pager4"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                                <div class="tab-pane clearfix" id="tab_4">
                                    <div class="f2_4 btn-group bBottom">
                                        <div class="btn btn-primary sure">确认</div>
                                        <%--<div class="btn btn-primary close1">关闭</div>--%>
                                    </div>
                                    <div class="bBottom block">
                                        <div class="form-group formgroup block width100">
                                            <label>&ensp;&ensp;&ensp;&ensp;名称</label>
                                            <input type="text" class="form-control name" name="" placeholder=""
                                                   maxlength="30">
                                        </div>
                                        <div class="form-group formgroup block width100 mtop">
                                            <label>&nbsp;URL路径</label>
                                            <input type="text" class="form-control url" id="url" name=""
                                                   style="width: 510px;" autocomplete="off"
                                                   placeholder="" maxlength="300">
                                        </div>
                                        <div class="form-group formgroup block width100 mtop">
                                            <%--<label>排序号</label>
                                            <input type="number" class="form-control pos" id="pos" name=""
                                                   placeholder="" maxlength="5">--%>
                                            <label>链接类型</label>
                                            <select id="linkType2" style="width: 200px;">
                                                <option selected>内链</option>
                                                <option>外链</option>
                                            </select>
                                        </div>
                                        <div class="form-group formgroup block width100 mtop">
                                            <label>&ensp;&ensp;排序号</label>
                                            <input type="number" class="form-control pos" id="pos" name=""
                                                   autocomplete="off"
                                                   placeholder="" maxlength="5">
                                        </div>
                                        <div class="form-group formgroup block width100 mtop shutdo">
                                            <label style="margin-left: -13px;">&ensp;&ensp;关闭资源</label>
                                            <label for="detailshutdown"><input type="checkbox" id="detailshutdown"
                                                                               name="detailshutdown"/><span
                                                    style="margin-left: -20px;">关闭</span></label>
                                        </div>
                                        <div class="form-group formgroup block width100 mtop" id="cz1">
                                            <label class="mright">&ensp;&ensp;&ensp;&ensp;操作</label>
                                            <%--   <label class="mright">
                                                    <input type="checkbox" class="rightMargin" name="" value="">保存
                                                </label>--%>
                                            <label class="mright" style="margin-left: -10px">
                                                <input type="checkbox" class="add1" value="00">增加
                                            </label>
                                            <label class="mright">
                                                <input type="checkbox" class="rem" value="00" a>删除
                                            </label>
                                            <label class="mright" 　>
                                                <input type="checkbox" class="mod" value="00" a>编辑
                                            </label>
                                            <label class="mright">
                                                <input type="checkbox" class="sel" value="00" a>查询
                                            </label>
                                        </div>

                                        <%--   <div class="form-group formgroup block mleft48" id="cz2">
                                               <label class="mright">
                                                   <input type="checkbox" name="" value="01">打印
                                               </label>
                                               <label class="mright">
                                                   <input type="checkbox" name="" value="01">导入
                                               </label>
                                               <label>
                                                   <input type="checkbox" name="" value="01">导出
                                               </label>
                                           </div>--%>
                                    </div>
                                </div>
                                <div class="tab-pane clearfix" id="tab_5">
                                    <div class="f2_4 btn-group">
                                        <div class="btn btn-primary sure1">确认</div>
                                    </div>
                                    <div class="f2_4 block" id="tidailiao">
                                        <div class="form-group ">
                                            <label>替代料</label>
                                            <div id="replaceMaInfo" data-id=""></div>
                                        </div>
                                    </div>
                                    <div class="btn-group">
                                        <div class="btn btn-primary del2">删除</div>
                                    </div>
                                    <section>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="box-body tablecontent">
                                                    <div>
                                                        <table id="list5"></table>
                                                        <div id="pager5"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<!--data-backdrop="static" data-keyboard="false"  bootstrap 空白处禁止关闭-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 400px;height: 200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    新增系统版本
                </h4>
            </div>
            <div class="modal-body">
                系统版本名称&nbsp;&nbsp;&nbsp;<input type="text" name="" id="SysVerName" autocomplete="off"/>
            </div>
            <div class="modal-body">
                复制系统版本&nbsp;&nbsp;
                <select class="form-control selectpicker" id="CopySysVer" title="请选择系统版本" data-live-search="true"
                        data-max-options="1" multiple style="width: 200px">
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveSysVer">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/sys/menuconfig.js?v=1"></script>
</body>
</html>



