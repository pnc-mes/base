<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/27
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>包装</title>

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
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/mprocess/pack/pack.css">
</head>
<body>
<div class="btn-group table tabTop td1 common">
    <button type="button" class="btn btn-primary " id="save">打印</button>
    <button type="button" class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 ">
    <div class="packcontent">
        <form id="printForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body box box-primary form-inline"
                                     style="margin-bottom: 10px;margin-left: 10px;">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <label for="batch">条&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                                            <input type="text" class="form-control" id="batch"
                                                   name="batch" placeholder="批次号" maxlength="50">
                                        </div>

                                        <div class="cpxx cpxx2">产品包装规格</div>
                                        <div class="form-group formgroup">

                                            <label for="woCode">工单号</label>
                                            <input type="text" class="form-control" id="woCode" readonly
                                                   name="woCode" placeholder="">
                                            <input type="hidden" id="woRd"/>

                                            <label for="maName">产品</label>
                                            <input type="text" class="form-control" id="maName" readonly
                                                   name="maName" placeholder="">
                                            <input type="hidden" id="maVerRd"/>


                                            <label class="mright3">包装数量
                                                <input type="text" class="form-control" id="reNum"
                                                       placeholder=""><span id="unitName"></span>
                                            </label>

                                            &nbsp;&nbsp;&nbsp;&nbsp;

                                            <label class="mright3">重量范围
                                                <input type="text" class="form-control" readonly id="weight"
                                                       placeholder="">公斤
                                            </label>

                                            <input type="hidden" id="num" value="0"/>
                                        </div>

                                        <input type="hidden" id="ptRd" value=""/>
                                        <input type="hidden" id="ptName" value=""/>

                                        <div class="cpxx cpxx2">打印信息</div>
                                        <div class="form-group dyjxx">
                                            <label style="margin: 0px;">打印机</label>
                                            <div id="Print"></div>
                                            <label class="mright3">打印数量
                                                <input type="number" class="form-control" id="printCount" value="1" readonly placeholder=""/>
                                            </label>
                                            <label class="">打印份数
                                                <input type="number" class="form-control check" id="printCopy" value="1" placeholder=""/>
                                            </label>
                                        </div>

                                    </div>
                                </div>
                                <div class="box box-primary last clearfix">
                                    <div class="f2_4 btn-group">
                                        <div class="btn btn-primary del1">删除</div>
                                    </div>
                                    <section class="content">
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
            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/pack/packinfo.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
