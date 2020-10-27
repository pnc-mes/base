<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/30
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>装料</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group td1  table common">
    <div class="btn btn-primary _cSave">确认</div>
    <div class="btn btn-primary _close">关闭</div>
    <%-- 引入页面中的其他信息--%>
    <%@include file="/WEB-INF/views/sysInfo.jspf" %>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2  clearfix zsyLeft1  clearfix">
    <div class="f2 col-sm-12 clearfix">
        <div class="mtop1" id="_right">
            <div class="row rightTop">
                <div class="row rightTop">
                    <div class="col-md-12">
                        <div class="tab-content">

                            <section class='col-sm-12'>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="box-body tablecontent">
                                            <div class="gridPanel">
                                                <table id="list1"></table>
                                                <div id="pager1"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                            <article class="col-lg-2" style="width: 30%;">
                                <input class="input1 form-control" type="text" id="addBatch" placeholder="批次号">
                            </article>
                            <section class="col-sm-12">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="box-body tablecontent">
                                            <div class="gridPanel">
                                                <table id="list2"></table>
                                                <div id="pager2"></div>
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
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/specopert/loadmainfo.js?v=1'></script>
</body>
</html>

