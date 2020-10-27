<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>新增无工单</title>
    <meta content="width=device-width, initial-scale=1 maximum-scale=1,user-scalable=no" name="viewport"/>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/datepicker/datepicker3.css">
</head>

<body>
<div class="btn-group able tabTop td1 common wi">
    <button class="btn btn-primary cSave">保存</button>
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 clearfix" style="margin-top:30px;margin-left: 10px;">
    <form class="form-inline">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">批次信息</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active clearfix" id="tab_1">

                            <div class="box-body">
                                <div class="printTInfo">
                                    <div class="form-group formgroup xinx">
                                        <div class="cpxx">物料信息</div>
                                        <label>物&emsp;&emsp;料</label>
                                        <div id="MaVer"></div>
                                    </div>
                                    <div class="form-group " style="display: block; margin-top:15px;">
                                        <div class="cpxx">生产信息</div>
                                        <div class="scxx">
                                            <label class="mright3">拆&nbsp;&nbsp;&nbsp;成
                                                <input type="number" class="form-control checkNumber" id="AllNum" placeholder="">批
                                            </label>
                                            <label class="mright3 rL1">每批
                                                <input type="text" class="form-control checkFloat" style="width: 70px;" id="Num" placeholder="">
                                                <span id="Unit"></span>
                                            </label>
                                            <label class="mright5">计划开始日期
                                                <input type="text" class="form-control datainp wicon" id="startDate">
                                            </label>
                                            <label class="mright5">计划完工日期
                                                <input type="text" class="form-control datainp wicon" id="finishDate">
                                            </label>
                                        </div>
                                        <div class="cpxx cpxx1">打印信息</div>
                                        <div class="dyxx">
                                            <label>打印模板</label>
                                            <div id="PrintT"></div>
                                            <label>打印机</label>
                                            <div id="Print"></div>

                                            <label for='ifPackage' class="checkbox rL3">
                                                <input type="checkbox" id="ifPackage">是否打印包裹批次
                                            </label>
                                        </div>
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
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/bootstrap-datepicker.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/plan/batch/batchwadd.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>

</html>
