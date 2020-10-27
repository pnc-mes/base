<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2018/1/30
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>收料策略</title>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/warehouse/syg/syg.css">
</head>
<body>

<div class="btn-group table tabTop common td1">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn btn-primary cAdd" id="add">新增</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center cDel" role="menuitem" tabindex="-1" id="remove" d="">删除</a>
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
<input type="hidden" id="hidden1" name="hidden1" h=""/>
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索策略名称">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right" style="display: none;">
        <form id="syginfoForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">策略信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="manWidth f1">
                                            <div class="form-group">
                                                <label class="">策略名称</label>
                                                <input type="text" class="form-control check" id="SgyName"
                                                       name="SgyName" placeholder="" maxlength="40">
                                            </div>
                                            <div class="form-group che" style="margin-left: 31px;">
                                                <label for="IsDef">
                                                    <input type="checkbox" id="IsDef" name="IsDef" class="IsDef" style="vertical-align:top;margin-right:1px;">默认策略
                                                </label>
                                                <label for="SInCome" style="margin-left: 20px;">
                                                    <input type="checkbox" id="SInCome" name="SInCome" class="SInCome" style="vertical-align:top;margin-right:1px;">允许超收
                                                </label>
                                                <label for="PartRev" style="margin-left: 20px;">
                                                    <input type="checkbox" id="PartRev" name="PartRev" class="PartRev" style="vertical-align:top;margin-right:1px;">允许部分收料
                                                </label>
                                            </div>
                                        </div>
                                        <div class="cpxx2 cpmcx manWidth " style="margin-top: 30px;">
                                            <label class="">作&nbsp;&nbsp;用&nbsp;&nbsp;于
                                                <select id="ActOn" name="ActOn" class="form-control"
                                                        style="padding: 1px 6px;width:196px;">
                                                    <option id="00" value="00">采购单</option>
                                                    <option id="01" value="01">来料收货通知单</option>
                                                </select>
                                            </label>
                                            <label class="status num" style="margin-left: 50px;">状态
                                                <select id="Status" name="Status" class="form-control check"
                                                        style="width: 196px;padding: 1px 6px;">
                                                    <option id="0" value="00">可用</option>
                                                    <option id="1" value="01">不可用</option>
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的系统信息--%>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/syg/syginfo.js?v=2'></script>
</body>
</html>


