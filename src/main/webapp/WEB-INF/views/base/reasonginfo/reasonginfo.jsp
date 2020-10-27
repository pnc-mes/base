<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/29
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>原因代码组管理</title>

    <meta content="width=device-width, initial-scale=1" name="viewport"/>

    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/reasonginfo/reasonginfo.css">
</head>
<body>
<div class="btn-group td1 table tabTop common">
   <%-- <div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1" id="remove">删除</a></li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">打印</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix ">
    <div class="left left1 " id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索原因代码组">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>
    <input type="hidden" id="ExecType" name="ExecType"/>
    <div class="right" id="_right">
        <form id="resongForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">原因代码组信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="block bBottom">
                                            <div class="form-group formgroup">
                                                <label for="RCGpName">原因代码组名称</label>
                                                <input type="text" class="form-control check" id="RCGpName"
                                                       name="RCGpName" placeholder="" maxlength="50">
                                            </div>
                                        </div>
                                        <!--****************************************************************************************************************-->
                                        <div class="nav-tabs-custom _shade">
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">原因代码信息</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active clearfix" id="tab_3">
                                                    <%--上边--%>
                                                    <div class="f2_4 btn-group">
                                                        <button type="button" class="btn btn-primary add1">新增</button>
                                                        <button type="button" class="btn btn-primary del1">删除</button>
                                                    </div>
                                                    <%--下边--%>
                                                    <div class="box-body form-inline ">
                                                        <div id="rheight">
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
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--*********************************************************************************************************************-->
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/reasonginfo/reasonginfo.js?v=1'></script>

</body>
</html>
