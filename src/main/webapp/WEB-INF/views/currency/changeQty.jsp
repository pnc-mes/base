<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>更改数量</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/changeQty.css"/>
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <div class="btn btn-primary" id="check">确认</div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="box-body form-inline">
                    <div class="printTInfo mb">
                        <div class="form-group formgroup">
                            <label>批次号</label>
                            <input type="text" class="form-control check" id="batch" placeholder="" maxlength="30">
                        </div>
                    </div>
                </div>
                <!--处理表格-->

                <div class="tab-content">
                    <div class="tab-pane active clearfix" id="tab_1">
                        <div class="btn btn-primary cDel ">删除</div>
                        <div class=" clearfix">
                            <section class="clearfix mtop">
                                <div class="box-body tablecontent">
                                    <div class="gridPanel">
                                        <table id="list4"></table>
                                        <div id="pager4"></div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/currency/changeQty.js?v=1'></script>
</body>
</html>

