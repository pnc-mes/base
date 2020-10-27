<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/6/29
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>库存管理</title>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/company/companyinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/warehouse/instock/instock/instock.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group table tabTop aa td1">
    <button class="btn btn-primary cSelect">筛选</button>
    <div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option1">选项
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" id="export"><a class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>
    <button class="btn btn-primary _close">关闭</button>
</div>

<!--*********************************处理带有CheckBox的表格***************************************************************************-->

<div class="zsyLeft1">
    <div class="left leftr zsyZi1">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索仓库名称">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
    </div>
    <div class="right mrop rightl">
        <section class="content">
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
        <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">库存明细</a>
                </li>
                <li><a href="#tab_4" data-toggle="tab">在库批次</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active clearfix" id="tab_3">
                    <section class="content2">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-body tablecontent">
                                    <div class="gridPanel">
                                        <table id="list6"></table>
                                        <div id="pager6"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="tab-pane" id="tab_4">
                    <section class="content1">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box-body tablecontent">
                                    <div class="gridPanel">
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
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/instock/instock/instock.js?v=1'></script>
</body>
</html>
