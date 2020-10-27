<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/9/20
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>快速退料单管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- bootstrap -->
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!-- 树开始 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/mprocess/pack/pack.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/demo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/selectpage/selectpage.bootstrap3.css">
</head>
<div class="btn-group table tabTop common td1">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn btn-primary cSave" a="" b="">保存</div>
    <div class="btn btn-primary cAdd" a="">新增</div>
    <input type="hidden" id="hidden" a="" editid=""/>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
    </div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="import">导入</a></li>
            <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1" id="export">导出</a></li>
            <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1">打印</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix zsyLeft1">
    <div class="left zsyZi1 left1" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索退料单">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" id="_right" style="display: none;">
        <input type="hidden" id="ExecType" />
        <input type="hidden" id="hidden1"  editid=""/>
        <form id="returnForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">退料单信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix mbottom" id="tab_1">
                                <div class="box-body  form-inline">
                                    <div class="printTInfo">
                                        <div class="manWidth">
                                            <label for="tuiLOrder">退料单号</label>
                                            <input type="text" class="form-control" id="tuiLOrder"
                                                   name="tuiLOrder" placeholder="退料单号可自动生成" maxlength="50">
                                            <label>退料仓库</label>
                                            <select id="cangku" style="padding: 0px;width: 196px" name="cangku" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <!--*********************************Tab关联批次**************************************-->
                                <div class="nav-tabs-custom mtop ">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">退料明细</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active clearfix" id="tab_3">
                                            <div class='mtop mbottom' id="sb">
                                                  <label>物料</label>
                                                <div id="wuliao" data-id=""></div>
                                                <div class="checkbox" style="display: none;">
                                                    <label>
                                                        <input type="checkbox" id="zaiku" checked> 在库批次
                                                    </label>
                                                </div>
                                                 <label>批次</label>
                                                <div id="pici" data-id=""></div>
                                                <label>库位</label>
                                                    <div id="kuwei" data-id="" style="display: none"></div>
                                            </div>
                                            <button class="button btn btn-primary tladd"  type="button">新增</button>
                                            <button class="button btn btn-primary tldel"  type="button">删除</button>
                                            <section class="content">
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
<!-- jqgrid表格结束 -->
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/plan/returnmainfo/nretmainfo.js?v=1'></script>
</body>
</html>

