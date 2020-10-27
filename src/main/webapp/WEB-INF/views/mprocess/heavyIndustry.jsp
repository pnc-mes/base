<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>非标准移动</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button type="button" class="btn btn-primary" >查询</button>
    <button type="button" class="btn btn-primary" id="save">保存</button>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <form id="setupspForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="form-inline">
                    <%--floor1--%>
                    <div class="printTInfo mb">
                        <div class="nav-tabs-custom">
                                <div class="tab-pane  clearfix" id="tab_1">
                                    <div class="box-body  form-inline">
                                        <div class="nav-tabs-custom">
                                        <ul class="nav nav-tabs">
                                                    <li class="active"><a href="#tab_1" data-toggle="tab" class="">在线重工</a></li>
                                                </ul>
                                        <div class='f1'>
                                            <div class="mBottom">
                                                <label>批&emsp;&emsp;次</label>
                                                <input type="text" class="form-control check" id="batch" name="batch" />
                                                </div>
                                                <div class="mBottom">
                                                <label for="workCode">工&ensp;单&ensp;号</label>
                                                <input type="text" class="form-control" id="workCode" name="workCode" placeholder="" >
                                                </div>
                                                <div class="mBottom">
                                                <label for="product">产&emsp;&emsp;品</label>
                                                <input type="text" class="form-control" id="product" name="product" placeholder="" >
                                                </div>
                                               
                                                <div class="mBottom">
                                                <label for="workflow">工艺流程</label>
                                                <input type="text" class="form-control" id="workflow" name="workflow" placeholder="">
                                                </div>
                                                 <div class="mBottom">
                                                <label for="procedureName">工&emsp;&emsp;序</label>
                                                <input type="text" class="form-control" id="procedureName" name="procedureName" placeholder="">
                                                </div>
                                                 <div class="mBottom">
                                                <label for="reworkPath">返工路径</label>
                                                <input type="text" class="form-control" id="reworkPath" name="reworkPath" placeholder="" list="reworkPathList">
                                                <datalist id="reworkPathList">
                                                </datalist>
                                                </div>
                                                 <div class="mBottom">
                                                <label for="reworkReason">返工原因</label>
                                                <input type="text" class="form-control" id="reworkReason" name="reworkReason" placeholder="" list="reworkReasonList">
                                                <datalist id="reworkReasonList">
                                                </datalist>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
</body>
</html>


