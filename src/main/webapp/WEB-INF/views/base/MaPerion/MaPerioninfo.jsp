<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>物料用料管控时间</title>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
</head>

<body>

<div class="btn-group table tabTop aa td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd" a>新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span> <%--dropdownMenu1--%>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a class="text-center cDel"  role="menuitem" tabindex="-1">删除</a>
            </li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>

<input type="hidden" id="ExecType" name="ExecType"/>
<input type="hidden" id="hidden" name="hidden" h=""/>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<div class="f2 clearfix">
    <div class="zsyLeft1">
        <div class="left zsyZi1">
            <input class="input1 form-control" type="search" name="" value="" placeholder="物料用料管控时间">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <form id="supplierForm">
        <div class="right">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">基本信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline formBottom" style="margin-bottom: 10px;">
                                    <div class="printTInfo clearfix disblock">
                                        <div style="float: left;width: 50%;">
                                            <div class="form-group ">
                                                <label for="supCode">有效期管控名称&nbsp&nbsp&nbsp</label>
                                                <input type="text" class="form-control" id="supCode"
                                                       name="supCode" style="width:252px;" maxlength="60">
                                            </div>
                                            <div class="form-group" style="width: 1000px">
                                                <label for="textr" style="padding-top: 20px;">描述&nbsp&nbsp&nbsp</label>
                                                <input id="textr"  style="width: 650px;" type="text" maxlength="255"/>
                                            </div>
                                            <div class="form-group" style="width: 100%">
                                                  <label for="maxtime" style="padding-top: 20px;">开箱使用有效&nbsp&nbsp&nbsp</label><input type="number" class="checkFloat" id="maxtime" maxlength="6"  style="width: 15%;"/>小时
                                            </div>
                                        </div>
                                    </div>
                                    <div style="margin-top: 20px;margin-bottom:0px;">
                                            <strong>&nbsp&nbsp提前提醒设置</strong>
                                        <div class="tab-content">
                                            <div class="tab-pane active clearfix" >
                                                <div class="block bBottom">
                                                    <div class="form-group" style="width: 35%">
                                                        <label for="expricetime">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp提前时间&nbsp&nbsp&nbsp</label>
                                                        <input type="number" class="checkFloat" id="expricetime" maxlength="6"  style="width: 20%;"/><span style="margin-left:-85px">小时</span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label >提前提醒(邮件通知组)&nbsp&nbsp&nbsp</label><div id="goaheademail"></div>
                                                    </div>
                                                </div>


                                                <table  style="width: 1000px;margin-left: 2px;margin-top: 8px;">
                                                    <tr>
                                                        <td sstyle="width: 380px;"><label>提醒(邮件内容)&nbsp&nbsp&nbsp</label><div  id="goaheademailcontent"></div></td>
                                                    </tr>
                                                </table>
                                            </div>

                                        </div>
                                    </div>

                                    <div  style="margin-top: 20px;margin-bottom:0px;">
                                            <strong>&nbsp&nbsp超时采取设置</strong>
                                        <div class="tab-content">
                                            <div class="tab-pane active clearfix" id="tab_5">
                                                <table  style="width: 1000px;margin-left: 7px;margin-top: 8px;">
                                                    <tr>
                                                        <td style="width: 380px;"><label>超时采取行动&nbsp&nbsp&nbsp</label>
                                                            <select id="csxd" style="width: 40%">
                                                                <option value="00">邮件报警&nbsp&nbsp&nbsp</option>
                                                            </select></td><td><label style="margin-left: -34px;">过期提醒(邮件通知组)&nbsp&nbsp&nbsp</label><div id="expiremailgroup"></div></td>
                                                    </tr>

                                                </table>

                                                <table  style="width: 1000px;margin-left: 2px;margin-top: 8px;">
                                                    <tr>
                                                        <td sstyle="width: 380px;"><label>提醒(邮件内容)&nbsp&nbsp&nbsp</label><div  id="expiremailcontent"></div></td>
                                                    </tr>
                                                </table>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/MaPerion/MaPerioninfo.js?v=1'></script>
</body>
</html>
