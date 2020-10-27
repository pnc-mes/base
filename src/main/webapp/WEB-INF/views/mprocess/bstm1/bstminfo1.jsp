<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/11/12
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>补刷</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css"
          rel="stylesheet"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>
<body>
<div class="btn-group table tabTop td1 common">

    <div class="btn btn-primary cSave">补刷</div>

    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix">
    <div>
        <form id="factoryinfo">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo" style="margin-top: 30px;">
                                        <div class=" formgroup disblock">
                                            <label for="ID">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID&nbsp;&nbsp;</label>
                                            <input type="text" class="form-control" id="ID"  style="width: 195px;"
                                               name="ID" placeholder="" maxlength="50">
                                        </div>
                                        <div class=" formgroup disblock">
                                            <label for="xt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线体&nbsp;&nbsp;</label>
                                            <div id="xt"></div>

                                        </div>
                                        <div class=" formgroup disblock" style="margin-left: 80px;margin-top: -8px;">
                                            <label><input type="radio" id="up" name="postion" value="00"/>往后道</label>
                                            <label style="margin-left: 15px;"><input type="radio" id="down" name="postion" value="01"/>往前道</label>
                                        </div>

                                        <div class=" formgroup disblock" style="margin-top: -14px;">
                                            <label for="mbgx">&nbsp;&nbsp;&nbsp;目标工序&nbsp;&nbsp;</label>

                                            <select class="selectpicker" multiple data-live-search="true" data-max-options="1"  id="mbgx"  style="width: 198px;" title="请选择目标工序">

                                            </select>

                                        </div>
                                        <div class=" formgroup disblock">
                                            <label>&nbsp;&nbsp;&nbsp;目标工序行为&nbsp;&nbsp;</label><br/>
                                            <div style="margin-left: 80px;">
                                                <label><input type="radio" id="in" name="action" value="00"/>进站</label>
                                                <label style="margin-left: 15px;"><input type="radio" id="out" name="action" value="01"/>过站</label>
                                            </div>

                                        </div>
                                        <div class=" formgroup disblock">
                                            <div style="margin-left: 17px;">
                                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说明&nbsp;&nbsp;</label>
                                               <select id="show" style="width: 200px;">
                                                   <option>其他说明</option>
                                                   <option>产线异常快速跳站</option>
                                                   <option>产品重用</option>
                                               </select>
                                            </div>
                                            <div style="margin-left: 75px;margin-top: 10px;">
                                                <textarea id="textarea" style="width: 200px;height: 100px;">

                                                </textarea>
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
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/bootstrap-datepicker.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/bstm1/bstm1.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>