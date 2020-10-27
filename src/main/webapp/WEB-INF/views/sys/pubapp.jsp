<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>发布App</title>
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
</head>
<body>
<div class="btn-group td1  table common">
    <div class="btn btn-primary _save">保存</div>
    <div class="btn btn-primary _close">关闭</div>
</div>

<input type="hidden" id="ExecType" name="ExecType"/>

<div class="f2  clearfix zsyLeft1  clearfix">
    <div class="mtop" id="_right" style="margin-left: 10px;">
        <form id="pubAddForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="form-group">
                                            <label>应&nbsp;用&nbsp;标&nbsp;识</label>
                                            <input type="text" class="form-control" id="AppID" name="AppID" placeholder="" maxlength="150">
                                            <label>应用名称</label>
                                            <input type="text" class="form-control" id="AppName" name="AppName" placeholder="" maxlength="100">
                                            <label>显示版本号</label>
                                            <input type="text" class="form-control" id="SAppVer" name="SAppVer" placeholder="" maxlength="50">
                                        </div>
                                        <div class="mtop">
                                            <label>内部版本号</label>
                                            <input type="text" class="form-control" id="AppVer" name="AppVer" placeholder="" maxlength="30">

                                            <label>上传文件</label>
                                            <input type="text" class="form-control check" id="fileName" name="fileName" readonly placeholder="">
                                            <span class="btn btn-success start fileinput-button" style="position: relative;">
                                                <i class="fa fa-upload"></i>
                                                <span> 上传 </span>
                                            <input type="file" id="file" readonly name="file"  multiple="">
                                            </span>

                                        </div>
                                            <div class="mtop">
                                                <label>是否强制更新</label>
                                                <div class="radio">
                                                    <label><input type="radio" name="ForUpApp"  value="" >是</label>
                                                    <label><input type="radio" name="ForUpApp"  value="">否</label>
                                                </div>
                                            </div>
                                            <div class="form-group mtop" style="position: relative;width: 100%;height: 70px;">
                                                <label style="position: absolute;top: 10px;">更&ensp;新&ensp;内&nbsp;容</label>
                                                <textarea id="UpDes" name="UpDes" style="position: absolute;left: 75px;padding-left: 10px;">
                                                    1.升级
                                                </textarea>
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
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/sys/pubapp.js?v=1'></script>
</body>
</html>
