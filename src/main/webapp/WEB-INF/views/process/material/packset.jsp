<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/7/3
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>包装规格</title>

    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/packSet.css">
</head>
<body>

<div class="btn-group table td1 fixedd">
    <button type="button" class="btn btn-primary cCerten">确认</button>
    <button type="button" class="btn btn-primary cClose _close">关闭</button>
</div>
<div class="f2 clearfix col-sm-12">
    <form id="printForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="box-body form-inline mb">
                    <div class="printTInfo">
                        <div class="form-group block bBottom mt">
                            <label class="mright1 block bBottom">打印模板
                                <select class="form-control selectControl" id="printT">
                                </select>
                            </label>
                        </div>
                        <div class="printTInfo">
                            <div class="form-group block bBottom mt">
                                <label class="mright1 block bBottom">序号规则
                                    <select class="form-control selectControl" id="serialRuleInfo">
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="block bBottom">
                            <div class="form-group">
                                <label for="_num">包装数量</label>
                                <input type="text" class="form-control" id="_num"
                                       name="_num" placeholder="">
                            </div>
                        </div>
                        <div class="block bBottom">
                            <div class="form-group">
                                <label for="_weight">标准重量</label>
                                <input type="text" class="form-control" id="_weight"
                                       name="_weight" placeholder="">
                            </div>
                        </div>
                        <div class="block bBottom">
                            <div class="form-group">
                                <label for="_uplimit">上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限</label>
                                <input type="text" class="form-control" id="_uplimit"
                                       name="_uplimit" placeholder="">
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <label for="_downlimit">下&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限</label>
                                <input type="text" class="form-control" id="_downlimit"
                                       name="_downlimit" placeholder="">
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
<script src='${pageContext.request.contextPath}/static/min/js/supershopui.common.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/material/packset.js?v=1'></script>

</body>
</html>
