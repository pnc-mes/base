<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件组管理</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group td1  table common">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <input type="hidden" id="hidden" value="" h=""/>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span> <%--dropdownMenu1--%>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a>
            </li>
        </ul>
    </div>
    <%--<div class="btn-group">
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
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2  clearfix zsyLeft1  clearfix">
    <div class="left zsyZi1 " id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索文件组名称">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>

    <div class="right " id="_right" style="display: none;">
        <form id="filegroupForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">文件组信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <label for="fileGpName">文件组名称</label>
                                            <input type="text" class="form-control check" id="fileGpName"
                                                   name="fileGpName" placeholder="" maxlength="50">
                                        </div>
                                    </div>
                                </div>
                                <div class="box box-primary  clearfix">
                                    <div class="f2_4 btn-group">
                                        <div class="btn btn-primary add1">新增</div>
                                       <%-- <div class="btn btn-primary edit1">编辑</div>--%>
                                        <div class="btn btn-primary del1">删除</div>
                                    </div>
                                    <section >
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
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/filegroup/filegroupinfo.js?v=1'></script>
</body>
</html>
