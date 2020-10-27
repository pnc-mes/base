<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/7/3
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>替代料</title>

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
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
</head>
<body>

<div class="btn-group table tabTop td1" style="position: fixed;top: 0; right: 0;z-index: 100;">
    <button type="button" class="btn btn-primary cCerten">确认</button>
    <button type="button" class="btn btn-primary cClose1 _close">关闭</button>
</div>
<div class="f2 clearfix">
    <div class="left left1" id="left2">
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="tab-content">
                    <div class="box box-primary last clearfix">
                        <section class="content">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body tablecontent">
                                        <div class="gridPanel">
                                            <table id="list4"></table>
                                            <div id="pager4"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/mainPage.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/confirm.js"></script>
<script src='${pageContext.request.contextPath}/static/min/js/supershopui.common.js'></script>
<!-- 树开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/JstreeEx.js"></script>
<script type="" src='${pageContext.request.contextPath}/static/plugins/jstree/dist/jstree.min.js'></script>
<!-- 树结束 -->
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/printTInfo/printTInfo.js'></script>
<!-- jqgrid表格开始 -->
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.min.js"></script>
<!-- jqgrid表格结束 -->
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/material/remaset.js?v=1'></script>

</body>
</html>
