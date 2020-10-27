<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/6/29
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>盘点管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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
    <style>
        .martop {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="btn-group table tabTop aa td1">
    <button class="btn btn-primary cSelect">筛选</button>
    <button class="btn btn-primary cAdd">新增</button>
    <div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option2">操作
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a name="pdcy"   c="" class="text-center" role="menuitem" tabindex="-1">盘点差异</a></li>
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
            <li role="presentation"><a class="text-center" role="menuitem" tabindex="-1">审核</a></li>
            <%--<li role="presentation"><a class="text-center" id="print" role="menuitem" tabindex="-1">打印</a></li>--%>
            <li role="presentation"><a class="text-center" role="menuitem" tabindex="-1">取消</a></li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option1">选项
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" id="export"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <button class="btn btn-primary _close">关闭</button>
</div>
<!--*********************************处理带有CheckBox的表格***************************************************************************-->
<section class="content martop">
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
<form id="SWareInfoForm" style="display: none;"><%--style="display: none;"--%>
    <div class="col-md-12" >
        <div class="box-body form-inline">
            <div class="printTInfo">
                <h3 class="h3" >盘点表</h3>
                <div class="table table-responsive  mtop">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th class="text-center" rowspan="2" colspan="1">盘点任务单号</th>
                            <th class="text-center" rowspan="2" colspan="1">仓库名称</th>
                            <th class="text-center" rowspan="2" colspan="1">执行人</th>
                            <th class="text-center" rowspan="2" colspan="1">执行时间</th>
                            <th class="text-center" rowspan="2" colspan="1">取消人</th>
                            <th class="text-center" rowspan="2" colspan="1">取消时间</th>
                            <th class="text-center" rowspan="2" colspan="1">完成人</th>
                            <th class="text-center" rowspan="2" colspan="1">完成时间</th>
                            <th class="text-center" rowspan="2" colspan="1">审核人</th>
                            <th class="text-center" rowspan="2" colspan="1">审核时间</th>
                            <th class="text-center" rowspan="2" colspan="1">状态</th>
                        </tr>
                        </thead>
                        <tbody id="CyContent">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/instock/inventory/inventory.js?v=1'></script>
</body>
</html>
