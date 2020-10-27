<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/11/8
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>设备更换记录</title>
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
<div class="btn-group table tabTop fix td1">
    <div class="btn btn-primary cSave" id="save">保存</div>
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 clearfix" style="margin-top: 40px;">

    <div>
        <form id="devform">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class=" formgroup disblock">
                                            <label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设备&nbsp;&nbsp;</label>
                                            <div id="shebei"></div>
                                        </div>
                                        <div class=" formgroup disblock">
                                            <label class="">&nbsp;&nbsp;备品备件&nbsp;&nbsp;</label>
                                            <div id="spbj"></div>
                                        </div>
                                        <div class=" formgroup disblock">
                                            <label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量&nbsp;&nbsp;</label>
                                            <input type="text" id="numb" class="checkNumber"/>
                                        </div>
                                        <div class=" formgroup disblock">
                                            <div class=" formgroup disblock">
                                                <label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注&nbsp;&nbsp;</label>
                                                <div style="margin-top: -23px;">
                                                    <textarea style="width: 250px;height: 60px;margin-left: 70px;" id="remark"></textarea>
                                                </div>
                                            </div>
                                        </div>

                                    <div class="nav-tabs-custom mtop" style="margin-left: 70px;">
                                        <div class="tab-content">
                                            <div class="tab-pane active clearfix" id="tab_3">
                                                <button class="button btn btn-primary tladd" type="button">新增</button>
                                                <button class="button btn btn-primary tldel" type="button">删除</button>
                                                <section class="content">
                                                    <div class="row">
                                                        <div class=" clearfix">
                                                            <section class="clearfix">
                                                                <div class="col-md-12">
                                                                    <div class="box-body tablecontent">
                                                                        <div class="gridPanel">
                                                                            <table id="list4"></table>
                                                                            <div id="pager4"></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </section>
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
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/res/sbgh/sbghinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
