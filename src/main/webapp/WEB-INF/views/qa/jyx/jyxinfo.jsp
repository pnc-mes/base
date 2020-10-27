<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>检验项清单</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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

<div class="btn-group table tabTop aa td1 ">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
    <%-- 引入页面中的系统信息--%><%--
    <%@include file="/WEB-INF/views/sysInfo.jspf"%>--%>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索检验项名称">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　>>　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <form class="form-inline" id="companyinfo">
        <div class="right" id="_right" style="display: none;">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">检验项信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="manWidth f1"  style="margin-top: 1px;">
                                            <div class="form-group width30">
                                                <label>&nbsp;&nbsp;&nbsp;&nbsp;检验项代码&nbsp;&nbsp;</label>
                                                <input type="text" class="form-control" style="width: 150px" id="CheckItemCode"
                                                       name="CheckItemCode"  maxlength="80">
                                            </div>
                                            <div class="form-group width30">
                                                <label>&nbsp;&nbsp;&nbsp;&nbsp;检验项名称&nbsp;&nbsp;</label>
                                                <input type="text" class="form-control" style="width: 150px" id="CheckItemName"
                                                       name="CheckItemName"  maxlength="80">
                                            </div>
                                        </div>
                                        <div style="padding-top: 30px;padding-left: 30px">
                                            <label>检验方法&nbsp;&nbsp;</label>
                                            <div id="jyffSelect" data-id=""></div>
                                        </div>
                                        <div class="manWidth f1"  style="margin-top: 15px;">
                                    <%--        <div class="form-group ">
                                                <label style="padding-left: 55px">描述&nbsp;&nbsp;</label>
                                                <textarea type="text" class="form-control" style="width: 470px " id="Description"
                                                          name="Description" maxlength="150" rows="6">
                                                    </textarea>
                                            </div>--%>
                                            <div class=" formgroup disblock">
                                                <label class="" style="padding-left: 9px;padding-top: 15px">&nbsp;&nbsp;检验项内容&nbsp;&nbsp;</label>
                                                <div style="margin-top: -23px;">
                                                    <textarea style="width: 300px;height: 80px;margin-left: 97px;" id="CheckItemC" name="CheckItemC"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="cpxx2 cpmcx manWidth " style="margin-top: 15px;">
                                        </div>
                                        <div class=" formgroup disblock" style="margin-left: 30px;padding-top: 1px">
                                            <label>确认方式&nbsp;&nbsp;</label>
                                            <select id="Status" style="width: 150px">
                                                <option selected value="0"></option>
                                                <option value="00">打勾</option>
                                                <option value="01">记录结果</option>
                                            </select>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/qa/jyx/jyxinfo.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

</body>
</html>

