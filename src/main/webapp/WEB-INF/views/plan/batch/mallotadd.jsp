<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/7/14
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>物料批次创建</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!-- 树开始 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/mallotadd.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/demo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/selectpage/selectpage.bootstrap3.css">
</head>
<body style="overflow-y: auto">
<div class="btn-group table tabTop td1 common">
    <div class="btn btn-primary cSave" id="add">新增</div>
    <div class="btn btn-primary cSave" id="batchPrint">生成批次打印</div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix contentt" >
    <form id="mallota" class="form-inline">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">批次信息</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active clearfix" id="tab_1">
                            <div class="box-body">
                                <div class="printTInfo bBottom">
                                    <div class="cpxx">物料信息</div>
                                    <div class="disblock form-group formgroup">

                                        <div class="form-group formgroup">
                                            <label for="Supplier">供应商</label>
                                            <div id="Supplier"></div>
                                        </div>

                                        <div class="form-group formgroup">
                                            <label for="PurChCode">采购单</label>
                                            <div id="PurChCode"></div>
                                        </div>

                                        <div class="form-group formgroup" id="Ichs" style="display: none;">
                                            <label for="Ich">来料收货通知单</label>
                                            <div id="Ich"></div>
                                        </div>

                                    </div>

                                    <div class="disblock mtop">
                                        <div class="form-group formgroup">
                                            <input class="form-control" type="text" id="search" placeholder="物料代码/名称/描述" maxlength="80" />
                                        </div>
                                    </div>

                                    <div class="box box-primary last clearfix">

                                        <!-- 显示所有物料信息　-->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="box-body tablecontent">
                                                    <div class="gridPanel">
                                                        <table id="list5"></table>
                                                        <div id="pager5"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group disblock bBottom">
                                        <div class="cpxx cpxx1">打印信息</div>
                                        <label class="">打印模板
                                            <%--<input type="text" id="PrintT" class="form-control check" name="" value=""
                                                   list="list3">
                                            <datalist id="list3">

                                            </datalist>--%>
                                        </label>
                                        <div id="PrintT" data-id=""></div>
                                        <label class="">打&emsp;印&emsp;机
                                            <%--<input type="text" id="Print" name="" value="" class="check form-control" list="list4">
                                            <datalist id="list4">
                                            </datalist>--%>
                                        </label>
                                        <div id="Print" data-id=""></div>
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
<input type="hidden" id="BRd" value="${BRd}"/>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/bootstrap-datepicker.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/plan/batch/mallotadd.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

</body>
</html>
