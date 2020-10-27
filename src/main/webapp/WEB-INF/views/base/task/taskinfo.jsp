<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>任务列表</title>
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
<div class="btn-group common table tabTop td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a name="copy" class="text-center copy" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
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
    <%-- 引入页面中的系统信息--%>
    <%--  <%@include file="/WEB-INF/views/sysInfo.jspf"%>--%>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class='zsyLeft'>
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索任务列表">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right" style="display: none;">
        <form id="skillform">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">技能信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="formgroup disblock">
                                            <label for="taskName" style="margin-left: 37px;">名称&nbsp;&nbsp;</label><input type="text" class="form-control check" id="taskName"
                                                   name="taskName" placeholder="">
                                            <label class="labell">状&emsp;&emsp;态&nbsp;
                                                <select id="status">
                                                    <option value="00">可用</option>
                                                    <option value="01">不可用</option>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="formgroup disblock" style="margin-top: -12px;">
                                            <label class="decription" style="margin-left: 37px;">描述&nbsp;&nbsp;</label><input id="decription" style="width: 300px;"/>
                                        </div>
                                        <div class="formgroup disblock" style="margin-top: -6px;">
                                            <label class="decription" style=" margin-left: 5px;">任务类型&nbsp;&nbsp;</label>
                                            <select id="rwlx">
                                                <option value="00">保养任务</option>
                                                <option  value="01">点检任务</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="nav-tabs-custom" style="margin-top: 10px;margin-bottom:0px;">
                                    <ul class="nav nav-tabs">
                                        <%--<li class="active"><a href="#tab_4" data-toggle="tab">任务明细</a></li>--%>

                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active clearfix" id="tab_4">
                                            <div class="f2_4 btn-group" id="maInfoButton">
                                                <div class="btn btn-primary add1" data-target="#myModal" data-toggle="modal">新增</div>
                                                <div class="btn btn-primary del1">删除</div>
                                            </div>
                                            <section>
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
                            <!--data-backdrop="static" data-keyboard="false"  bootstrap 空白处禁止关闭-->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">×
                                            </button>
                                            <h4 class="modal-title" id="myModalLabel">
                                                明细
                                            </h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="printTInfo">
                                                <input type="hidden" id="hidden"/>
                                                <label for="TaskItemName" style="margin-left: 27px;">任务描述</label>
                                                <div class=" formgroup " style="margin-left: 86px;margin-top: -24px;">
                                                    <textarea id="TaskItemName" style="margin: 0px;width: 450px;height: 84px;" >
                                                    </textarea>
                                                </div>
                                                <div class=" formgroup disblock" style="margin-top: 13px;">
                                                    <label for="taskName" style="margin-left: 27px;">确认方式</label>
                                                    <select id="qrfs" style="width: 123px;height: 28px;">
                                                        <option value="00">打勾</option>
                                                        <option  value="01">记录结果</option>
                                                    </select>
                                                </div>
                                                <div class=" formgroup disblock">
                                                    <label for="max" style="">最大执行次数</label>
                                                    <input type="number" class="checkNumber" id="max" style="width: 80px"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" id="savadetail">
                                                保存
                                            </button>
                                            <button type="button" class="btn btn-default"
                                                    data-dismiss="modal">取消
                                            </button>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/task/taskinfo.js?v=1'></script>
</body>
s
</html>