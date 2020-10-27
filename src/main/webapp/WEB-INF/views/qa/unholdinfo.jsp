
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>批次解冻</title>
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
    <button type="button" class="btn btn-primary" id="find">查询</button>
    <button type="button" class="btn btn-primary" id="save">解冻</button>
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
                                        <div class="f1">
                                            <div class="form-group formgroup">
                                                <label for="inputs">组件条码</label>
                                                <input type="button" value="请批量输入" id="inputs" />
                                                <%--<input type="text" class="form-control" style="" id="tjtm" maxlength="50">--%>
                                            </div>
                                            <div class="form-group formgroup" style="margin-left: 8px;">
                                                <label for="scxb">生产线别</label>
                                                <div id="scxb" style="width: 100px;"></div>
                                            </div>
                                            <div class="form-group formgroup" style="margin-left: 90px;">
                                                <label for="date">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期</label>
                                                <input type="text" class="layui-input" id="date" style="width: 280px">
                                            </div>
                                        </div>
                                        <div class='f1'>
                                            <%--<div class="form-group formgroup">
                                                <label>批次号</label>
                                                <input type="text" class="form-control check" id="batchNum" name="batchNum">
                                            </div>--%>
                                            <div class="form-group formgroup ">
                                                <label class="">解冻原因</label>
                                                <div id="defaultSelect" data-id=""></div>
                                            </div>

                                        </div>
                                    </div>

                                    <!--处理表格-->
                                    <div class="tab-pane" id="tab_4">
                                        <div class="boxx">
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

                                    <!--补录组件条码-->
                                    <div class="modal fade" id="Modals" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel2" aria-hidden="true">
                                        <div class="modal-dialog" style="width: 400px;height: 200px;">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 class="modal-title" id="myModalLabel2">
                                                        请每行输入一条数据
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <textarea id="mo" autocomplete="off" rows="5" style="width: 100%;"></textarea>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" id="btn_save">
                                                        确认
                                                    </button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div><!-- /.modal -->
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
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/qa/unholdinfo.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/laydate/laydate.js"></script>
</body>
</html>


