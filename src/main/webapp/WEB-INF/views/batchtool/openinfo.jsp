
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>打开批次</title>
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
    <button type="button" class="btn btn-primary" id="save">确认</button>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <form id="purchForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="form-inline">
                    <%--floor1--%>
                    <div class="printTInfo mb">
                        <div class="nav-tabs-custom">
                            <div>
                                <div class="tab-pane active clearfix" id="tab_1">
                                    <div class="box-body  form-inline">
                                        <div class='f1'>
                                            <div class="form-group formgroup">
                                                <label>批次号</label>
                                                <input type="text" class="form-control check" id="batchNum" name="batchNum">
                                            </div>
                                            <div class="form-group formgroup ">
                                                <label class="reason">打开原因</label>
                                                <div id="defaultSelect" data-id=""></div>
                                            </div>

                                        </div>
                                    </div>

                                    <!--处理表格-->
                                    <div class="tab-pane" id="tab_4">
                                        <div class="boxx">
                                            <div class="f2_4 btn-group">
                                                <div class="btn btn-primary del1">删除</div>
                                            </div>
                                            <section class="clearfix">
                                                <div class=" tablecontent">
                                                    <div class="gridPanel">
                                                        <table id="list4"></table>
                                                        <div id="pager4"></div>
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
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/batchTool/openinfo.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>


