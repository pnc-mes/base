<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/30
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>更改数量</title>
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
    <button type="button" class="btn btn-primary" id="chagNum">更改数量</button>
    <div class="btn btn-primary close">关闭</div>
    <%-- 引入页面中的系统信息--%>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <form id="purchForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="form-inline">
                    <%--floor1--%>
                    <div class="printTInfo mb">
                        <div class="nav-tabs-custom">
                            <div>
                                <div class="tab-pane active clearfix" id="tab_1">
                                    <div class="box-body  form-inline">
                                        <div class='f1'>
                                            <div class="form-group formgroup">
                                                <label>批次数量</label>
                                                <input type="text" class="form-control check" id="InCCode" name="InCCode"
                                                       placeholder="">
                                            </div>

                                        </div>
                                    </div>

                                    <!--处理表格-->
                                    <div class="tab-pane" id="tab_4">
                                        <div class="boxx">
                                            <section class="clearfix">
                                                <div class=" tablecontent">
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
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/mainPage.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/confirm.js"></script>
<script src='${pageContext.request.contextPath}/static/min/js/supershopui.common.js'></script>
<!-- jqgrid表格开始 -->
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.min.js"></script>
<!-- jqgrid表格结束 -->
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/batchTool/chgqty.js?v=1"></script>
</body>
</html>



