<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/7/6
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css"
          rel="stylesheet"/>
    <!-- 树开始 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/sys/role/role.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group td1 table tabTop common">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd" a="">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" b="" 　role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <%-- <div class="btn-group">
         <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
              aria-expanded="true" id="option2">选项
             <span class="caret"></span>
         </div>
         <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
             <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a>
             </li>
         </ul>
     </div>--%>
    <div class="btn btn-primary _close">关闭</div>
    <%-- 引入页面中的系统信息
    <%@include file="/WEB-INF/views/sysInfo.jspf" %>--%>
</div>
<div class="f2 clearfix zsyLeft1">
    <div class="left left1 zsyZi1" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索角色">
        <img class="searchTree"
             src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png"/>
        <!--树的处理-->
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a
                title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>
    <div class="right">
        <form id="printForm">
            <div class="row rightTop">
                <div class="col-md-11">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">角色信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <label for="printModelName">角色名称</label>
                                            <input type="text" class="form-control" id="printModelName"
                                                   name="ptName" maxlength="40" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="nav-tabs-custom">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">后台管理系统权限</a>
                                        </li>
                                        <li><a href="#tab_4" data-toggle="tab" class="dymb">APP权限</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active " id="tab_3">
                                            <section class="content">
                                                <%--<div class="row">--%>
                                                <%--<div class="col-md-11">--%>
                                                <%--<div class="box-body tablecontent">--%>
                                                <%--<div class="gridPanel">--%>
                                                <%-- <div id="table4" style="height: 13200px;">--%>
                                                <table id="table"
                                                       style="border:0;cellspacing:0;cellpadding:0;font-size: 15px"
                                                       class="table2 table-hover">
                                                    <tbody id="tbody">
                                                    </tbody>
                                                </table>


                                            </section>
                                        </div>
                                        <div class="tab-pane  " id="tab_4">
                                            <section class="content">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="box-body ">
                                                            <div class="gridPane2">
                                                                <span>仓库App权限设置</span>
                                                                <div>
                                                                    <table id="table5"
                                                                           style="border:0;cellspacing:0;cellpadding:0;font-size: 15px"
                                                                           class="table2 table-hover">
                                                                        <tbody id="tbody5">
                                                                        <%--<tr class="aa"
                                                                            style="width: 800px;height: 30px;border:0;cellspacing:0;cellpadding:0;">
                                                                            <td style="width: 300px;height: 30px;border:0;cellspacing:0;cellpadding:0;">
                                                                                <a style='margin-right: 5px; margin-left: 5px'
                                                                                   class='Icons'
                                                                                   id='icon_file" + i + "'><i
                                                                                        class='fa fa-file-text fa-fw'></i></a>
                                                                                <input style="margin-right: 5px;"
                                                                                       type="checkbox" id="churuku"/>
                                                                                <strong>出入库作业</strong>
                                                                            </td>
                                                                            <td style="width: 500px;height: 30px;border:0;cellspacing:0;cellpadding:0;">
                                                                                <label for="yclrk"><input
                                                                                        type="checkbox" id="yclrk"
                                                                                        value="00"/>原材料入库</label>
                                                                                <label for="ccprk"><input
                                                                                        type="checkbox" id="ccprk"
                                                                                        value="01"/>产成品入库</label>
                                                                                <label for="otherrk"><input
                                                                                        type="checkbox" id="otherrk"
                                                                                        value="02"/>其他入库</label>
                                                                                <label for="llck"><input type="checkbox"
                                                                                                         id="llck"
                                                                                                         value="03"/>领料出库</label>
                                                                                <label for="xsck"><input type="checkbox"
                                                                                                         id="xsck"
                                                                                                         value="04"/>销售出库</label>
                                                                                <label for="otherck"><input
                                                                                        type="checkbox" id="otherck"
                                                                                        value="05"/>其他出库</label>
                                                                                <label for="sctl"><input type="checkbox"
                                                                                                         id="sctl"
                                                                                                         value="11"/>生产退料</label>
                                                                                <label for="lldsq"><input
                                                                                        type="checkbox" id="lldsq"
                                                                                        value="12"/>领料单申请</label>
                                                                        </tr>
                                                                        <tr class="aa">
                                                                            <td><input type="checkbox"
                                                                                       id="kuncun"/><strong>库存作业</strong>
                                                                            </td>
                                                                            <td><label for="sj"><input type="checkbox"
                                                                                                       id="sj"
                                                                                                       value="06"/>上架</label>
                                                                                <label for="pd"
                                                                                       style="margin-left: 51px;"><input
                                                                                        type="checkbox" id="pd"
                                                                                        value="07"/>盘点</label>
                                                                                <label for="yk"
                                                                                       style="margin-left: 51px;"><input
                                                                                        type="checkbox" id="yk"
                                                                                        value="08"/>调拨</label>
                                                                                <label for="tzkw"
                                                                                       style="margin-left: 39px;"><input
                                                                                        type="checkbox" id="tzkw"
                                                                                        value="09"/>调整库位</label></td>
                                                                        </tr>
                                                                        <tr class="aa">
                                                                            <td><input type="checkbox"
                                                                                       id="zhijian"/><strong>质检作业</strong>
                                                                            </td>
                                                                            <td><label for="iqc"><input type="checkbox"
                                                                                                        id='iqc'
                                                                                                        value="10"/>IQC</label>
                                                                            </td>
                                                                        </tr>--%>
                                                                        <%-- <tr class="aa">
                                                                             <td><input type="checkbox"
                                                                                        id="shengchan"/><strong>生产作业</strong>
                                                                             </td>
                                                                             <td><label for="sl"><input type="checkbox"
                                                                                                        id='sl'
                                                                                                        value="13"/>上料</label>
                                                                             </td>
                                                                         </tr>--%>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                                <div style="margin-top: 50px;">
                                                                    <span>车间现场执行系统</span>
                                                                </div>

                                                                <div style="margin-top: 20px;">
                                                                    <table id="table6" style="border:0;cellspacing:0;cellpadding:0;font-size: 15px" class="table2 table-hover">
                                                                        <tbody id="tbody6">
                                                                        </tbody>
                                                                    </table>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>

                                        <div id="other"></div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/sys/role/roleInfo.js?v=1'></script>
</body>
</html>
