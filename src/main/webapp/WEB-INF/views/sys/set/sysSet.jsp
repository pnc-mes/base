<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/7/11
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>应用设置</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
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
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button class="btn btn-primary cSplit">确定</button>
    <button class="btn btn-primary cClose _close">关闭</button>
</div>
<div class="formbd col-sm-12">
    <form id="printForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="box-body form-inline clearfix">
                    <div class="printTInfo mb contentLeft" style="margin-top: 30px;">
                        <div class=" formgroup ">
                            <label for="AppName">应&ensp;用&ensp;名&ensp;称&ensp;</label>
                            <input type="text" class="form-control check" id="AppName" name="AppName" maxlength="30"/>
                        </div>
                        <div class=" formgroup biaoqian">
                            <span>
                                <label>应用LOGO：</label>
                                <img id="imags" style=" width: 187px;">
                            </span>
                            <span>
                                <input type="file" style="margin-left: 50px; margin-top: 50px;"  id="imageFile"/>
                                <input type="button" style="float: left; margin-left: 280px; margin-top: -25px;" class="file_1" value="上传" id="pushImg"/>
                                <input style="display: none;" id="AppLogo"/>
                            </span>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </form>
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/sys/set/sysSet.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/AppSet/GetAppSetInfo",
            type: "POST",
            data: {"ExecType": "00"},
            success: function (res) {
                if (res.Body.Data != null && res.Body.Data != undefined) {
                    $("#AppLogo").val(res.Body.Data.appLogo);
                    $("#AppName").val(res.Body.Data.appName);
                    if (res.Body.Data.appLogo != undefined && res.Body.Data.appLogo != "") {
                        $("#imags").attr("src", res.Body.Data.appLogo);
                    } else {
                        $("#imags").attr("src","${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/logo.png");
                    }
                }
            }
        })
    });
</script>
</body>
</html>
