<%--
  Created by IntelliJ IDEA.
  User: lijingjing
  Date: 2017/11/2
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>打包</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button class="btn btn-primary cbb" id="_save">确认</button>
    <button class="btn btn-primary cClose _close">关闭</button>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <form id="purchForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="form-inline">
                    <div class="tab-pane active clearfix" id="tab_1">
                        <div class="box-body  form-inline bBottom">
                            <div class="width100">
                                <div class="form-group width30">
                                    <label for="packType">打包类型</label>
                                    <select id="packType" style="width: 100px;">
                                        <option value="00">栈板</option>
                                        <option value="01">箱</option>
                                    </select>
                                    <div class="form-group width30" style="margin-left: 0px;">
                                        <input type="text" class="form-control" id="barCode" placeholder="栈板/箱/批次号" maxlength="50"/>
                                    </div>
                                </div>
                            </div>
                            <div class="width100" style="margin-top: 20px;">
                                <div class="form-group width30">
                                    <label for="isPrint">
                                        <input type="checkbox" id="isPrint"/>打包标签
                                    </label>
                                </div>
                            </div>
                            <div class="width100">
                                <div class="form-group width30">
                                    <label>打印模板</label>
                                    <div id="PrintT" data-id=""></div>
                                </div>
                                <div class="form-group width30">
                                    <label>打印机</label>
                                    <div id="Print" data-id=""></div>
                                </div>
                                <div class="form-group width30">
                                    <label>打印份数</label>
                                    <input type="number" style="width: 100px;" id="printCopy" value="1" />
                                </div>

                                <%--<label>打印数量</label>
                                <input type="number" style="width: 100px;" id="printCount" value="1" />--%>

                            </div>
                        </div>

                        <!--处理表格-->
                        <div class="tab-pane" id="tab_4">
                            <div class="boxx">
                                <div class="nav-tabs-custom">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_1" data-toggle="tab">打包信息</a></li>
                                    </ul>
                                    <section class="clearfix">
                                        <div class=" tablecontent">
                                            <div class="gridPanel">
                                                <table id="list"></table>
                                                <div id="pager"></div>
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
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/assoc.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
