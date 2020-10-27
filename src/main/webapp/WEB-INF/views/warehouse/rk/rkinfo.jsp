<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/6/29
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>入库管理</title>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/warehouse/rk/rkinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group table tabTop common td1">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" id="export"><a class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>


<div class="f2 clearfix  zsyLeft1" >
    <div class="left zsyZi1">
        <%--<input class="input1 form-control" type="search" name="" value="" placeholder="搜索入库单">--%>
            <input class="input1 form-control" type="search" name="" value="" placeholder="">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div id="jstree_demo1"></div>
    </div>

    <div class="right rightTop box box-primary" id="formContent" style="display: none">
        <!--************************************************************表格1******************************************************************-->
        <section >
            <div class="row">
                <div class="col-md-12">
                    <div class="box-body tablecontent">
                        <div class="gridPanel">
                            <table id="list1"></table>
                            <div id="pager1"></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="boxx clearfix box box-primary">
            <!--**********************************右边左侧表格************************************************-->
            <section class="content1 leftt1 leftTable">
                <div class="row">
                    <div class="col-md-12">
                        <div class="box-body tablecontent">
                            <div class="gridPanel">
                                <table id="list2"></table>
                                <div id="pager2"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!--**********************************右边右侧表格************************************************-->
            <section class="content2 rightt1 rightTable" >
                <div class="row">
                    <div class="col-md-12">
                        <div class="box-body tablecontent">
                            <div class="gridPanel">
                                <table id="list3"></table>
                                <div id="pager3"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/rk/rk.js?v=1'></script>
</body>
</html>
