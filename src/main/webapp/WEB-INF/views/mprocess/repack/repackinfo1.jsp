<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>成品入库拆箱</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">

</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button type="button" class="btn btn-primary" id="save">确认</button>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <div class="row rightTop">
        <div>
            <div class="box-body  form-inline">
                <div class='f1'>
                    <div class="form-group formgroup">
                        &nbsp;&nbsp;
                        <input type="text" class="form-control " placeholder="扫描箱号" id="Code" name="" maxlength="50">
                        <input type="hidden" id="BarType"/>

                        <label>总数</label><span id="totalnum" style="margin-left: 10px;"></span>
                        <label style="margin-left: 10px;">已选数量</label><span id="checktotalnum" style="margin-left: 10px;"></span>
                        <label>拆箱原因</label><input type="text" id="remark" style="margin-left: 10px;"/>
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="tab_4">
                <div class="boxx">
                    <div class="f2_4 btn-group col-sm-12">
                        <div class="nav-tabs-custom">
                            <ul class="nav nav-tabs ul">
                                <li class="active"><a href="#tab_1" data-toggle="tab">拆箱信息</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active clearfix" id="tab_1">
                                   <%-- <div class="btn btn-primary cDel">删除</div>--%>
                                    <div class="clearfix">
                                        <section class="clearfix">
                                            <div class="tablecontent">
                                                <div class="gridPanel">
                                                    <table id="list5"></table>
                                                    <div id="pager5"></div>
                                                </div>
                                            </div>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<!--layui时间-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/laydate/laydate.js"></script>
<!--下拉框js-->
<script src="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/repack/repackinfo1.js"></script>
</body>
</html>


