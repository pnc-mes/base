<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/29
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工序设置</title>

    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css"
          rel="stylesheet"/>
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

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
</head>
<body>

<table class="table tabTop col-sm-12" style="position: fixed;top: 0; right: 0;z-index: 100;">
    <tbody>
    <tr class="biaoqian">
        <td colspan="10" class="td1">
            <div class="btn-group">
                <button class="btn btn-primary" id="_finish">确认</button>
                <button class="btn btn-primary _close" id="_close">关闭</button>
            </div>
        </td>
    </tr>
    </tbody>
</table>
<!--*****************************************form表单******************************************************88-->
<form id="specbox" class="form-inline box  box-primary col-sm-12" style="margin-top: 35px;">
    <div class="box-body">
        <div class="printTInfo">

            <div class="form-group formgroup">
                <label for="SpecName">工&nbsp;&nbsp;&nbsp;&nbsp;序</label>
                <input type="text" class="form-control" id="SpecName" readonly placeholder="">
            </div>
            <div class="form-group beizhu">
                <label>
                    <span class="beizhuspan">描&nbsp;&nbsp;&nbsp;&nbsp;述</span></label>
                <textarea id="beizhu" id="Remark" name="Remark"></textarea>
            </div>

        </div>
    </div>
</form>
<!--*********************************处理带有CheckBox的表格***************************************************************************-->

<div class="box box-primary last clearfix col-sm-12 ">
    <div class="f2_4 btn-group">
        <%--<div class="btn btn-primary add1">新增</div>--%>
        <%--<div class="btn btn-primary del1">删除</div>--%>
    </div>
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
<!-- tab切换区开始 -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/user.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/mainPage.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/confirm.js"></script>
<script src='${pageContext.request.contextPath}/static/min/js/supershopui.common.js'></script>
<!-- 树开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/JstreeEx.js"></script>
<script type="" src='${pageContext.request.contextPath}/static/plugins/jstree/dist/jstree.min.js'></script>
<!-- 树结束 -->
<!-- jqgrid表格开始 -->
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.min.js"></script>
<!-- jqgrid表格结束 -->
<script src='${pageContext.request.contextPath}/static/plugins/jquery-file-upload/js/jquery.fileupload.js'></script>
<script src='${pageContext.request.contextPath}/static/ui/global/json2.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/specattr.js?v=1'></script>

</body>
</html>
