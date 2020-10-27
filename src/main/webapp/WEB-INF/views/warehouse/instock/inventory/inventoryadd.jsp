<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/6/29
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>

    <!-- 树开始 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>
<body>
<table class="table tabTop" style="position: fixed;top: 0; right: 0;z-index: 100;">
    <tbody>
    <tr class="biaoqian">
        <td colspan="10" class="td1">
            <div class="btn-group">
                <button type="button" class="btn btn-primary cSave">保存</button>
                <button type="button" class="btn btn-primary cLose">关闭</button>
            </div>
        </td>
    </tr>
    </tbody>
</table>
<div class="f2 clearfix" style="margin-top:30px;margin-left: 10px;">
    <form class="form-inline">
        <div class="form-group formgroup">

            <label class="mright3">仓&nbsp;&nbsp;&nbsp;&nbsp;库</label>
            <div id="defaultSelect" data-id=""></div>
            <div class="form-group beizhu">
                <label>
                    <span class="beizhuspan">备&nbsp;&nbsp;&nbsp;&nbsp;注</span></label>
                <textarea id="beizhu" name="remark" style="left:50px"></textarea>
            </div>
        </div>
    </form>
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/instock/inventory/inventoryadd.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>

</html>
