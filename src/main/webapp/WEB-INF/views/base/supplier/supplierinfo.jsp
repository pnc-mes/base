<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/6/9
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>供应商</title>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
</head>

<body>

<div class="btn-group table tabTop aa td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span> <%--dropdownMenu1--%>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
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
<input type="hidden" id="hidden" name="hidden" h=""/>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<div class="f2 clearfix">
    <div class="zsyLeft1">
        <div class="left zsyZi1">
            <input class="input1 form-control" type="search" name="" value="" placeholder="供应商名称">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <form id="supplierForm">
        <div class="right">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">供应商信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline formBottom" style="margin-bottom: 10px;">
                                    <div class="printTInfo clearfix">
                                        <div style="float: left;width: 50%;">
                                            <div class="form-group ">
                                                <label for="supCode">供应商代码</label>
                                                <input type="text" class="form-control" id="supCode"
                                                       name="supCode" style="width:252px;" placeholder="供应商代码可自动生成" readonly maxlength="50">
                                            </div>
                                            <div class="form-group mtop">
                                                <label for="supName">供应商名称</label>
                                                <input type="text" class="form-control check" id="supName"
                                                       name="supName" style="width:252px;" placeholder="" maxlength="50">
                                            </div>
                                            <div class="form-group mtop">
                                                <label for="supFName">供应商全称</label>
                                                <input type="text" class="form-control check" id="supFName"
                                                       name="supFName" placeholder="" style="width:252px;" title="" maxlength="50">
                                            </div>
                                            <div class="form-group mtop">
                                                <label for="status">状&emsp;&emsp;&emsp;态
                                                    <select id="Status" name="Statuss" class="form-control" style="width: 252px;padding: 1px 6px;">
                                                        <option id="00" value="00">可用</option>
                                                        <option id="01" value="01">不可用</option>
                                                    </select>
                                                </label>
                                            </div>
                                        </div>
                                        <div style="float: left;width: 50%;">
                                            <div class="form-group ">
                                                <label for="contactor">联&ensp;系&ensp;人</label>
                                                <input type="text" class="form-control" id="contactor"
                                                       name="contactor" placeholder="" maxlength="30">
                                            </div>
                                            <div class="form-group mtop">
                                                <label for="mobile">联系方式</label>
                                                <input type="text" class="form-control" id="mobile"  name="mobile"
                                                       placeholder="" maxlength="25">
                                            </div>
                                            <div class="form-group mtop">
                                                <label for="address">联系地址</label>
                                                <input type="text" class="form-control" id="address"  name="address"
                                                       placeholder="" maxlength="150">
                                            </div>
                                        </div>
                                    </div>
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
        </div>
    </form>
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/supplierinfo/supplierinfo.js?v=1'></script>
</body>
</html>
