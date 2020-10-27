<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/27
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<media name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"></media>
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<html>
<head>
    <title>工艺流程管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/workflow.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/jsplumbtoolkit-defaults.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/jsplumbtoolkit-demo.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/demo.css">

</head>
<body>
<div class="btn-group table tabTop td1">
    <input type="hidden" id="ExecType" value=""/>
    <%--<button class="btn btn-primary cSelect">筛选</button>--%>
    <button class="btn btn-primary cAdd" id="add">新增</button>
    <button class="btn btn-primary cSave" id="save">保存</button>
    <div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="copy">复制</a>
            </li>--%>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1"
                                       id="addVersion">新增版本</a>
            </li>
            <li role="presentation"><a class="text-center" role="menuitem" tabindex="-1" id="remove">删除</a>
            </li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1"
                                       id="rmVersion">删除版本</a>
            </li>
        </ul>
    </div>

    <%--<div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a>
            </li>
        </ul>
    </div>--%>
    <button class="btn btn-primary _close" id="close">关闭</button>
</div>
<div class="f2 clearfix">
    <div class="zsyLeft1">
        <div class="left zsyZi1">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索流程">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div id="_right" class="right" style="display: none;">
        <form id="printForm">

            <input type="hidden" id="WfRd" name="WfRd"/>
            <input type="hidden" id="WfVerRd" name="WfVerRd"/>

            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">流程信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <label for="WfName">流程名称</label>
                                            <input type="text" class="form-control" id="WfName"
                                                   name="WfName" placeholder="" maxlength="40">
                                        </div>
                                        <div class="form-group">
                                            <label for="Version">版本</label>
                                            <input type="text" class="form-control" id="Version"
                                                   name="Version" placeholder="" maxlength="30">
                                            <label for="IsDef" class="checkbox" id="dd" style="">
                                                <input type="checkbox" value="" class="baoliu7" id="IsDef" name="IsDef">
                                                设置为默认版本
                                            </label>
                                        </div>

                                    </div>
                                    <div class="form-group aa">

                                        <div class="form-group formgroup">
                                            <label>状态</label>
                                            <select id="status_" style="width: 179px;">
                                                <option>可用</option>
                                                <option>不可用</option>
                                            </select>
                                        </div>
                                        <div class="form-group" style="margin-left: 100px;">
                                            <label>流程类型</label>
                                            <select id="lclx" style="width: 179px;">
                                                <option value="00">正常流程</option>
                                                <option value="01">返工流程</option>
                                            </select>
                                        </div>
                                        <%--<input list="status" name="Status" id="status_"
                                               class="form-control selectControl check"/>
                                        <datalist id="status">
                                            <option id="use" label="00" value="可用"></option>
                                            <option id="unuse" label="01" value="不可用"></option>
                                        </datalist>--%>
                                    </div>
                                </div>
                                <!--右侧所有-->
                                <div class="clearfix rightcontent">
                                    <!--右侧的左侧（树）-->
                                    <div class="left left1 " id="kzZsy">
                                        <!--右侧的搜索栏-->
                                        <input class="input2 form-control" type="search" name="" value=""
                                               placeholder="搜索工序">
                                        <img class="searchTree2" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
                                        <div class="pages1"><a title="上一页" id="prev1" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next1" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
                                        </div>
                                        <div id="jstree_demo3"></div>
                                    </div>
                                    <!--右侧的右侧-->
                                    <div class=" right1" style="overflow-x:hidden;margin-left:170px;width:89%;height:100%">
                                        <div id="main">
                                            <div class="_top">
                                                <span class="lbl">返工路线</span>
                                            </div>
                                            <!-- demo -->
                                            <div class="jtk-demo-canvas canvas-wide statemachine-demo jtk-surface jtk-surface-nopan"
                                                 id="canvas">
                                            </div>
                                            <!-- /demo -->
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="rightkey" id="rightkey" style="display: none;">
        <span id="defElement">开始元素</span>
        <span id="delElement">删除元素</span>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/jquery-ui.min.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/jsplumb.min.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/demo.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/workflowinfo.js?v=1"></script>
</body>
</html>
