<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/7/14
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>打印记录查询</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <!-- 树开始 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
</head>
<body id="aaa">
<div class="contation">
    <div class="btn-group table td1">
        <button class="btn btn-primary cSelect">筛选</button>
        <div class="btn-group">
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="true" id="option">操作
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li role="presentation"><a href="#" class="text-center rePrint" role="menuitem" tabindex="-1">重打</a>
                </li>
            </ul>
        </div>
        <div class="btn-group">
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="true" id="option1">选项
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li role="presentation" id="export"><a class="text-center" role="menuitem" tabindex="-1">导出</a></li>
               <%-- <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">打印</a></li>
           --%> </ul>
        </div>
        <button class="btn btn-primary _close">关闭</button>
    </div>
    <!--*******************************************下拉菜单**************************************************************************-->

    <!--*********************************处理带有CheckBox的表格***************************************************************************-->
    <section class="content box box-primary">
        <div class="row">
            <div class="col-md-12">
                <div class="box-body tablecontent">
                    <div class="gridPanel">
                        <table id="list4"></table>
                        <div id="pager4"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/currency/printsearch/printsearch.js?v=1'></script>
</body>
</html>

