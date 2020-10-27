<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/10/25
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>库存变动</title>
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
</head>
<body>
<div class="contation">
    <div class="btn-group table td1">
        <button class="btn btn-primary cSelect">筛选</button>
        <div class="btn-group">
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="true" id="option1">选项
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li role="presentation" id="export"><a class="text-center" role="menuitem" tabindex="-1">导出</a></li>
                <li role="presentation" id="print"><a class="text-center" role="menuitem" tabindex="-1">打印</a></li>
            </ul>
        </div>
        <button class="btn btn-primary _close">关闭</button>
    </div>
    <!--*******************************************下拉菜单**************************************************************************-->

    <!--*********************************处理带有CheckBox的表格***************************************************************************-->
    <section class="content box box-primary margintop">
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
        <!--*********************************处理非CheckBox的表格***************************************************************************-->
    </section>
</div>
<form id="SWareInfoForm" style="display: none;"><%--style="display: none;"--%>
    <div class="col-md-12" >
        <div class="box-body form-inline">
            <div class="printTInfo">
                <h3 class="h3" >库存变动表</h3>
                <div class="table table-responsive  mtop">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th class="text-center" rowspan="2" colspan="1">类型</th>
                            <th class="text-center" rowspan="2" colspan="1">物料代码</th>
                            <th class="text-center" rowspan="2" colspan="1">物料名称</th>
                            <th class="text-center" rowspan="2" colspan="1">单号</th>
                            <th class="text-center" rowspan="2" colspan="1">批号</th>
                            <th class="text-center" rowspan="2" colspan="1">数量</th>
                            <th class="text-center" rowspan="2" colspan="1">单位</th>
                            <th class="text-center" rowspan="2" colspan="1">制单人</th>
                            <th class="text-center" rowspan="2" colspan="1">制单时间</th>
                            <th class="text-center" rowspan="2" colspan="1">处理人</th>
                            <th class="text-center" rowspan="2" colspan="1">处理时间</th>
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
<%@include file="/WEB-INF/views/commonJs.jspf"%>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/search/kcrecord/kcrecord.js?v=1'></script>
</body>
<%--<body>
<div class="btn-group table td1">
    <button type="button" class="btn btn-primary" id="Big_">最大化</button>
    <input id="path" style="display: none" value='${pageContext.request.contextPath}'/>
</div>
<iframe id="reportFrame" width="1400" height="600" src='${pageContext.request.contextPath}/ReportServer?reportlet=入库历史.cpt'></iframe>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/search/kcrecord/kcrecord.js?v=1'></script>
</body>--%>
</html>

