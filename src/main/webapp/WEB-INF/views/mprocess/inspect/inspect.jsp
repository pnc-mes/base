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
    <title>批次拆分</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
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
    <button class="btn btn-primary cSplit">确定</button>
    <button class="btn btn-primary cClose _close">关闭</button>
</div>
<div class="formbd col-sm-12">
    <form id="printForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="box-body form-inline clearfix">
                    <div class="printTInfo mb contentLeft">
                        <div class=" formgroup ">
                            <label for="batch">组件条码</label>
                            <input type="text" class="form-control check" id="batch" name="ptName" maxlength="30"/>
                        </div>
                        <div class=" formgroup biaoqian">
                            <label for="MaCode">工&ensp;单&ensp;号</label>
                            <input type="text" class="form-control" id="MaCode" readonly name="MaName" placeholder="">
                        </div>
                        <div class=" formgroup biaoqian">
                            <label for="MaName">生产线别</label>
                            <input type="hidden" id="MaVerRd" name="MaVerRd"/>
                            <input type="text" class="form-control" id="MaName" readonly name="MaName"
                                   placeholder="">
                        </div>
                        <div class=" formgroup biaoqian">
                            <label for="WoCode">班&emsp;&emsp;别</label>
                            <input type="text" class="form-control" id="WoCode" readonly name="MaName" placeholder="">
                        </div>
                        <div class="formgroup biaoqian">
                            <label for="Num">操&ensp;作&ensp;员</label>
                            <input type="text" class="form-control" id="Num" readonly name="Num" placeholder="">
                            <label><input type="checkbox" id="isPrint" checked> 是否组件条码</label>
                        </div>

                        <div class="formgroup biaoqian">
                            <button type="button" class="btn btn-primary" id="A">A</button>
                            <button type="button" class="btn btn-primary" id="BV">BV</button>
                            <button type="button" class="btn btn-primary" id="C">C</button>
                            <button type="button" class="btn btn-primary" id="scrap">报废</button>
                            <button type="button" class="btn btn-primary" id="APlus">A+</button>
                        </div>
                    </div>
                </div>
                <!--处理表格4-->
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab">当班统计</a></li>
                    </ul>

                    <div class="tab-content">
                        <div class="tab-pane active clearfix" id="tab_1">

                           <%-- <div class="btn btn-primary cAdd">新增</div>
                            <div class="btn btn-primary cDel">删除</div>--%>

                            <div class=" clearfix">
                                <section class="clearfix">
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
    </form>
</div>


<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/inspect/inspect.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

</body>
</html>
