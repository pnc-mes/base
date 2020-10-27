<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/7
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>替代料</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!-- 有关tab切换的样式 -->
    <%--<link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">--%>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <div class="btn btn-primary ">确认</div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="formbd">
        <div class="row rightTop">
            <div class="col-md-12">

                <!--处理表格-->
                <div class="tab-content">
                    <div class="tab-pane active clearfix" id="tab_5">
                        <div class=" clearfix">
                            <section class="clearfix">
                                <div class="col-md-12">
                                    <div class="box-body tablecontent">
                                        <div class="gridPanel">
                                            <table id="list5"></table>
                                            <div id="pager5"></div>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script><%--
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/currency/splitbatch.js"></script>--%>

</body>
</html>


