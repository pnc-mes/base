<%--
  Created by IntelliJ IDEA.
  User: lijingjing
  Date: 2017/11/3
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>产线余料调拨</title>
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
    <button class="btn btn-primary cCheck">查询</button>
    <button class="btn btn-primary cbb" id="save">保存</button>
    <button class="btn btn-primary cClose _close">关闭</button>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <form id="purchForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <%--floor1--%>
                <div class="printTInfo mb">
                    <div class="nav-tabs-custom">

                        <div class="tab-pane active clearfix" id="tab_1">
                            <div class='f1'>
                                <div class='width100'>
                                    <label class="">
                                        <input type="checkbox" id="haveWorkOrder" checked name="haveWorkOrder"/>有工单调拨
                                    </label>
                                </div>
                                <div class='width100 mtop' id="in_WorkOrder">
                                    <label class="">调出工单号</label>
                                    <div id="outWorkNum" data-id=""></div>
                                </div>
                                <div class='width100 mtop' style="display: none" id="batchNum">
                                    <label class="">物料批次号 <input id="materBatch" name="batch" style="width: 196px;height: 28px;margin-bottom: -4px;"/></label>
                                </div>
                                <div class='width100 mtop' id="out_WorkOrder">
                                    <label class="PrintT">调入工单号</label>
                                    <div id="inWorkNum" data-id=""></div>
                                </div>
                            </div>


                            <!--处理表格-->
                            <div class="tab-pane" id="tab_4">
                                <div class="boxx">
                                    <div class="f2_4 btn-group">
                                        <div class="btn btn-primary cDel">删除</div>
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
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/onlinemad.js?v=1"></script>
</body>
</html>

