<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/7/14
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>重打</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>

    <!-- 树开始 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>

<body>
<div class="btn-group td1 table tabTop common">
    <button class="btn btn-primary " id="confirm">确认</button>
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 clearfix" style="margin-top:30px;">
    <form class="form-inline">
        <div class="col-md-12">

                    <!-- /.tab-pane -->
                    <div class="box-body">
                        <div class="form-group">

                            <div class="block bBottom1">
                                <div class="form-group">
                                    <label class="">打&nbsp;&nbsp;印&nbsp;&nbsp;机</label>
                                    <div id="defaultSelect" data-id=""></div>
                                </div>
                            </div>

                            <%--<div class="block bBottom1">
                                <div class="form-group">
                                    <label class="">打印模板</label>
                                    <div id="PrintT" data-id=""></div>
                                </div>
                            </div>--%>

                            <div class="block bBottom">
                                <div class="form-group">
                                    <label for="printCount" >打印数量</label>
                                    <input type="number" class="form-control" id="printCount"
                                           name="fileGpName" readonly value="1" style="display: inline-block;width: 197px;">
                                </div>
                            </div>
                            <div class="block">
                                <div class="form-group">
                                    <label for="printCopy">打印份数</label>
                                    <input type="number" class="form-control" id="printCopy"
                                           name="fileGpName" value="1" style="display: inline-block;width: 197px;">
                                </div>
                            </div>
                        </div>
                    </div>

        </div>
    </form>
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/currency/printsearch/RePrint.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>

