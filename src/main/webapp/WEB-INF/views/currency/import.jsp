<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>导入库存</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <style>
        .fileinput-button input {
            position: absolute;
            top: -2px;
            right: -45px;
        }
    </style>
</head>
<body id="aaa">
<div class="btn-group td1  table common">
    <div class="btn btn-primary _save">确认</div>
    <div class="btn btn-primary  start fileinput-button" id="filedowns">
        <a id="filedown" download="" style="color:#fff;"><span class="filedown"> 下载模板 </span></a>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>

<input type="hidden" id="ExecType" name="ExecType"/>

<div class="f2  clearfix zsyLeft1  clearfix">
    <div class="" id="_right" style="margin-left: 10px;margin-top: 26px;">
        <form id="printForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <input type="text" class="form-control check" id="fileName" name="fileName" readonly placeholder="">
                                        </div>
                                        <div class="form-group" style="position: absolute;">
                                            <span class="btn btn-success start fileinput-button" style="position: relative;">
                                                <i class="fa fa-upload"></i>
                                                <span> 上传 </span>
                                            <input type="file" id="file" readonly name="file"  multiple="">
                                            </span>
                                            <span id="pCode" style="color: red;margin-left: 10px;"></span>
                                        </div>
                                    </div>
                                </div>
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
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/currency/import.js?v=1'></script>
</body>
</html>
