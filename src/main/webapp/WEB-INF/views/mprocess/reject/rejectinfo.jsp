<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>标记不良</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet"/>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>
<body>
<div class="btn-group common table tabTop td1">
    <div class="btn btn-primary _save" a>保存</div>
    <div class="btn btn-primary _close">关闭</div>
</div>

<div class="f2 zsyLeft1 clearfix lRight">
    <div class="right1 rightMLeft " style="overflow: auto;height:100%;margin-top: 17px">
      <!--  <div class="row rightTop">-->
            <div class="col-md-13">
                <div class="nav-tabs-custom" style="margin-left:10px;margin-top: 10px;">
                    <ul class="nav nav-tabs">
                        <li class="active" id="nav_tab3"><a href="#tab_3" data-toggle="tab" class="dymb">标记不良</a></li>
                        <li id="nav_tab4"><a href="#tab_4" data-toggle="tab">换补片</a></li>
                    </ul>
                    <div class="tab-content">

                        <div class="tab-pane active clearfix" id="tab_3">
                            <div class="box-body form-inline">
                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body ">
                                                <div class="gridPanel" style="padding-bottom: 0px;">

                                                    <div class="form-group" style="width: 25%;" >
                                                        <label for="Batch">批次号/SN号*</label><br/>
                                                        <input type="text" class="form-control" id='Batch' name="Batch" maxlength="50">
                                                    </div>

                                                    <div class="form-group" style="width: 20%;">
                                                        <label for="WoCode">工单号</label><br/>
                                                        <input type="text" disabled="true"   id='WoCode' name="WoCode">
                                                    </div>

                                                    <div class="form-group" style="width: 20%;">
                                                        <label  for="MaName">产品名称</label><br/>
                                                        <input type="text" disabled="true"   id='MaName'  name="MaName">
                                                    </div>

                                                    <div class="form-group" style="width: 20%;">
                                                        <label for="Num">数量</label><br/>
                                                        <input type="text" disabled="true"   id='Num'  name="Num">
                                                    </div>
                                                </div><br/>
                                                <div style=" margin-bottom: 20px;">
                                                    <div class="form-group" style="width: 25%;">
                                                        <label for="SpecName">不良工序</label>
                                                        <select id="SpecName"></select>
                                                    </div>
                                                    <div  class="form-group" style="width: 25%;">
                                                        <label for="DevName">设备名称</label>
                                                        <select id="DevName"></select>
                                                    </div>
                                                </div>
                                                <div style=" margin-bottom: 20px;">
                                                    <div class="form-group" style="width: 25%;">
                                                        <label for="ReaDes">不良原因</label>
                                                        <select id="ReaDes"></select>
                                                    </div>
                                                    <div  class="form-group" style="width: 25%;">
                                                        <label for="BadNum" >不良数量</label>
                                                        <input type="text"   id='BadNum'  name="BadNum" onkeyup="this.value=this.value.replace(/\D/g, '')">
                                                    </div>
                                                </div>
                                                <div>
                                                    <div class="btn btn-primary save1" id="add">增加</div>
                                                    <div class="btn btn-primary del1" id="del">删除</div>
                                                </div> <br/>
                                                <div class="gridPanel">
                                                    <table id="list"></table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                        <!--换片-->
                        <div class="tab-pane" id="tab_4">
                            <div class="box-body form-inline">
                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body ">


                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                    <!---->

                    </div>
                </div>
            </div>
       <!-- </div>-->
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/reject/rejectinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>


</body>

</html>

