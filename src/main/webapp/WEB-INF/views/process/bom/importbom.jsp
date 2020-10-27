<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BOM导入</title>
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
<body id="aaa">
<div class="contation">
    <div class="btn-group table td1">
        <button class="btn btn-primary sure">确认</button>
        <button class="btn btn-primary download ">下载模板</button>
        <button class="btn btn-primary _close">关闭</button>
    </div>
    <!--*******************************************下拉菜单**************************************************************************-->

    <!--*********************************处理带有CheckBox的表格***************************************************************************-->
    <section class="content box box-primary">
        <div class="row">
            <div class="col-md-12">
                <div class="box-body tablecontent">
                    <div class="gridPanel">
                        <div class="form-group">
                            <div class="form-group formgroup">　
                                BOM文件<input type="text" class="check" id="fileName" style="margin-right: 0px" name="fileName" readonly placeholder="">
                                            <span class="btn btn-success start fileinput-button" style="position: relative;">
                                                <i class="fa fa-upload"></i>
                                                <span> 上传 </span>
                                            <input type="file" id="file" readonly name="file"  multiple="">
                                            </span>
                                <span id="pCode" style="color: red;margin-left: 10px;"></span>
                            </div>
                            <div class="form-group formgroup" style="color: red;font-size: 12px;">　
                                提示:支持一个或者多个BOM的同时导入操作,具体操作:只需要在Excel文件中建立一个或者多个Sheet即可实现
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/bom/importbom.js?v=1'></script>
</body>
</html>

