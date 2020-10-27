<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>最小时间管控</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
</head>
<body>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/email/ueditor.config.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/email/ueditor.all.min.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/email/lang/zh-cn/zh-cn.js'></script>
<div class="btn-group common table tabTop td1">
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>


    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--  <li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
    <%-- 引入页面中的系统信息--%>
    <%--   <%@include file="/WEB-INF/views/sysInfo.jspf"%>--%>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class='zsyLeft'>
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索最小时间名称">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right"  style="display: none;">
        <form id="devform">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">基本信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo clearfix disblock">
                                        <div style="float: left;">
                                            <div class="form-group ">
                                                <label for="supCode" style="margin-left: 5px;">最小时间名称&nbsp;&nbsp;</label>
                                                <input type="text" class="form-control" id="supCode"
                                                       name="supCode" style="width:252px;" maxlength="60">
                                            </div><br/>
                                            <div class="form-group">
                                                <label for="textr" style="margin-left: 61px;padding-top: 22px;">描述&nbsp;&nbsp;</label>
                                                <div style="margin-top: -29px;">
                                                    <textarea id="textr"  style="width: 542px;height: 30px;margin-left: 100px;" type="text" maxlength="255"/></textarea>
                                                </div>
                                            </div><br/>

                                            <table id="table"  style="width: 1000px;margin-left: 64px;margin-top: 15px;">
                                                <tr>
                                                    <td><label for="material">产品&nbsp;&nbsp;</label> <div id="material" data-id=""></div></td><td><label style="margin-left: -50px;">工艺流程&nbsp;&nbsp;</label><select id="gylc" style="width: 196px;height: 28px;"> </select></td><td><label>开始工序&nbsp;&nbsp;</label><select id="startSpec" style="width: 196px;height: 28px;"> </select></td>
                                                </tr>

                                            </table><br/>

                                            <table  style="width: 1000px;margin-left: 40px;margin-top: -2px;">
                                                <tr>
                                                    <td style="width: 318px;"><label style="margin-left: 24px">设备&nbsp;&nbsp;&nbsp;</label><select id="DevGD" style="width: 197px;"> </select></td><td><label style="margin-left:5px">最小时间&nbsp;&nbsp;</label><input class="checkFloat" id="maxtime" maxlength="10" style="width: 77px;"/><span style="margin-left: -14px;">小时</span></td>
                                                </tr>

                                            </table>
                                        </div>
                                    </div>
                                    <div class="nav-tabs-custom" style="margin-top: -10px;margin-bottom:0px;">
                                        <ul style="margin-left: -36px;">
                                            <%--<li class="active"><a href="#tab_5" data-toggle="tab"></a></li>--%>
                                            <strong>超时设置</strong>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane active clearfix" id="tab_5">
                                                <table  style="width: 1000px;margin-left: 7px;margin-top: 2px;">
                                                    <tr>
                                                        <td style="width: 380px;"><label>超时采取行动&nbsp;&nbsp;</label>
                                                            <select id="csxd"  style="width: 196px;">
                                                                <option value="00">自动下机</option>
                                                                <option value="01">邮件提醒</option>
                                                            </select></td><td><label style="margin-left: -34px;">到期提醒(邮件通知组)&nbsp;&nbsp;</label><div id="expiremailgroup"></div></td>
                                                    </tr>

                                                </table>

                                                <table  style="width: 1000px;margin-left: 2px;margin-top: 15px;">
                                                    <tr>
                                                        <td sstyle="width: 380px;"><label>提醒(邮件内容)&nbsp;&nbsp;</label><div  id="expiremailcontent"></div></td>
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
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/qa/MinTimeWindowInfo.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
