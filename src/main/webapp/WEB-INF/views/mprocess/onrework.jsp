<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>在线重工</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button type="button" class="btn btn-primary" id="search_">查询</button>
    <button type="button" class="btn btn-primary" id="save_">保存</button>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd ">
    <form id="setupspForm">
        <div class="col-md-12">
            <div class="tab-pane  clearfix" id="tab_1">
                <div class="  form-inline">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">在线重工</a></li>
                        </ul>
                        <div class='f1 bBottom'>
                            <label>批&emsp;&emsp;次</label>
                            <input type="text" class="form-control" id="batch" name="batch" maxlength="50"/>
                        </div>
                        <div class="bBottom">
                            <label for="woCode">工&ensp;单&ensp;号</label>
                            <input type="text" class="form-control" id="woCode" name="woCode" readonly/>
                        </div>
                        <div class="bBottom">
                            <label for="procedureName">产品名称</label>
                            <input type="text" class="form-control" id="procedureName" name="procedureName"
                                   readonly/>
                        </div>
                        <div class="bBottom">
                            <label for="workflow">工艺流程</label>
                            <input type="text" class="form-control" id="workflow" name="workflow" readonly/>
                        </div>
                        <div class="bBottom">
                            <label for="specName">工&emsp;&emsp;序</label>
                            <input type="text" class="form-control" id="specName" name="specName" readonly/>
                        </div>
                        <div class="bBottom">
                            <label for="reSpec">重工路径</label>
                            <select class="form-control" id="reSpec" style="width: 196px;height: 31px;">

                            </select>
                        </div>
                        <div class="">
                            <label calss="">重工原因</label>
                            <div id="defaultSelect" data-id=""></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/onrework.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

</body>
</html>


