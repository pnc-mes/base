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
    <title>过站异常追踪</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/ui/global/layer/skin/default/layer.css">
</head>
<body id="aaa">
<div class="contation">
    <div class="btn-group table td1">
        <button class="btn btn-primary cSelect">查询</button>
        <button class="btn btn-primary _close">关闭</button>
    </div>

    <div style="width: 1200px;height: 60px;">
        <span for="zjtm">组件条码&nbsp;&nbsp;</span><input type="text" id="batch" style="width: 150px;"/>
        <span for="test5" style="">&nbsp;&nbsp;&nbsp;开始时间&nbsp;&nbsp;</span><input type="date" id="StartTime" style="width: 150px;">
        <span for="test6" style="margin-left: 23px;">结束时间&nbsp;&nbsp;</span><input type="date" id="EndTim" style="width: 150px;">
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


<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/search/gzyc/gzyc.js?v=1'></script>
</body>
</html>

