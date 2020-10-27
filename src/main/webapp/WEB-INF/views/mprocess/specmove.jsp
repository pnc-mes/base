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
    <button type="button" class="btn btn-primary" id="search_" >查询</button>
    <button type="button" class="btn btn-primary" id="save_">保存</button>
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
                            <div>
                                <div class="tab-pane  clearfix" id="tab_1">
                                    <div class="form-inline">
                                        <div class="nav-tabs-custom">
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">批次移动</a></li>
                                            </ul>
                                            <div class='f1'>
                                                <div class="bBottom">
                                                    <label>批&emsp;&emsp;次</label>
                                                        <input type="text" class="form-control" id="batch" name="batch"  maxlength="50"/>
                                                    </div>
                                                    <div class="bBottom">
                                                        <label for="woCode">工&ensp;单&ensp;号</label>
                                                        <input type="text" class="form-control" id="woCode" name="woCode" readonly />
                                                    </div>
                                                    <div class="bBottom">
                                                        <label for="procedureCode">产品代码</label>
                                                        <input type="text" class="form-control" id="procedureCode" name="procedureCode" readonly />
                                                    </div>
                                                    <div class="bBottom">
                                                        <label for="procedureName">产品名称</label>
                                                        <input type="text" class="form-control" id="procedureName" name="procedureName" readonly />
                                                    </div>
                                                    <div class="bBottom">
                                                        <label for="workflow">工艺流程</label>
                                                        <input type="text" class="form-control" id="workflow" name="workflow" readonly />
                                                    </div>
                                                </div>
                                        </div>
                                    </div>

                                    <!--处理表格-->
                                    <div class="tab-pane" id="tab_4">
                                        <div class="boxx">
                                            <div class="nav-tabs-custom" style="margin-bottom: 0px;">
                                                <ul class="nav nav-tabs">
                                                    <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">移动列表</a></li>
                                                </ul>
                                                <button type="button" class="btn btn-primary mtop" id="del_">删除</button>
                                                <section class="clearfix">
                                                    <div class=" tablecontent">
                                                        <div class="gridPanel">
                                                            <table id="list1"></table>
                                                            <div id="pager1"></div>
                                                        </div>
                                                    </div>
                                                </section>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/specmove.js?v=1'></script>
</body>
</html>


