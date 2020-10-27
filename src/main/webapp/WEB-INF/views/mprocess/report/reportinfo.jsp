<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/27
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>包装</title>
<%--    <meta http-equiv="Windows-Target" contect="_top">--%>
    <%--<meta http-equiv="X-Frame-Options" content="deny">--%>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/mprocess/pack/pack.css">
  <%--  <style id="antiClickjack">body{display:none !important;}</style>--%>
</head>
<body>

<div class="btn-group table tabTop td1 common">
    <a target="reload" id="abc"  href="http://10.4.13.104:8075/webroot/decision/view/report?viewlet=Sunport+%5B524d%5DEL%5B4e0d%5D%5B826f%5D%5B660e%5D%5B7ec6%5D%5B8868%5D.cpt">异步加载页面</a>
    <button type="button" class="btn btn-primary" >


    </button>
    <button type="button" class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 ">
    <iframe  name="reload"  width="100%" height="800">

    </iframe>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
<script>
    if (self === top) {
        var antiClickjack = document.getElementById("abc");
        antiClickjack.parentNode.removeChild(antiClickjack);
    } else {
        top.location = self.location;
    }
</html>
