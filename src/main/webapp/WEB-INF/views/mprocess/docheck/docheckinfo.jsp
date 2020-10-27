<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/9/14
  Time: 8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>点检计划执行</title>
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
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <%--<div class="btn btn-primary cAdd">新增</div>--%>
    <div class="btn btn-primary cSave">保存</div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            &lt;%&ndash;<li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>&ndash;%&gt;
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>--%>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
                id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div>
        <form id="factoryinfo">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class=" formgroup disblock" style="margin-top: 38px;margin-left: 12px;">
                            <label>点检对象&nbsp;&nbsp;</label>
                            <div id="shebei" data-id=""></div>

                            <label  style="margin-left: 50px;">状态&nbsp;&nbsp;</label><select id="status"  style="width: 115px">
                            <option value="00">未完成</option>
                            <option value="01">已完成</option>
                        </select>
                            <input type="button" value="查询" id="chaxun" style="margin-left: 50px;"/>
                        </div>
                        <div class=" formgroup"  style="margin-left: 12px;">
                            <label>任务清单&nbsp;&nbsp;</label>
                            <select id="rwqd"  style="width: 190px">
                            </select>
                        </div>

                        <div class="nav-tabs-custom " style="margin-top: 10px;margin-left: 12px;">
                            <table border="1" style="width: 90%;border:1px solid  #CFCFCF; margin-left: 12px;">
                                <thead>
                                <tr><td style="text-align:center;vertical-align:middle;width: 45%;" id="rwms">任务描述</td><td style="text-align:center;vertical-align:middle;width: 45%">确认方式</td></tr>
                                </thead>
                                <tbody id="tbody">

                                </tbody>
                            </table>
                           <%-- <div class="tab-content">
                                <div class="tab-pane active clearfix" id="tab_3">
                                    <section >
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
                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>


        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/docheck/docheckinfo.js?v=1'></script>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
