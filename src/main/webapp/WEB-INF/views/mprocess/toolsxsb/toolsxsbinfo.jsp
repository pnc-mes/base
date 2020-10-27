<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/9/17
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工具上下设备</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css"
          rel="stylesheet"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <style>
        table th{
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class="btn-group table tabTop td1 common">
    <div class="btn btn-primary upshebei">上设备</div>
    <div class="btn btn-primary downshebei">下设备</div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix">
    <div>
        <form id="factoryinfo">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class=" formgroup disblock formgroup" style="margin-top: 38px;margin-left: 12px;">
                            <label for="shebei">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设备&nbsp;&nbsp;</label>
                            <div id="shebei"></div><br/>
                            <label for="gjxlh" style="margin-top: 15px; ">工具序列号&nbsp;&nbsp;</label>
                            <input type="text" id="gjxlh" style="width: 250px;"/>
                        </div>
                    </div>

                    <div>
                        <section>
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
            <br/>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/toolsxsb/toolsxsbinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>