<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/7/1
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>工单管理</title>
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
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd" ca="">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <input type="hidden" id="hidden" a="" editid=""/>
    <input type="hidden" id="hidden1" name="hidden" h=""/>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" ><a  class="text-center" role="menuitem" id="remove"  tabindex="-1">删除</a>
            </li>
            <li role="presentation" ><a  class="text-center" role="menuitem" id="xd"  tabindex="-1">下达</a>
            </li>
            <li role="presentation" ><a  class="text-center" role="menuitem" id="cancel" tabindex="-1">取消</a>
            </li>
            <li role="presentation" ><a  class="text-center" role="menuitem" id="freeze"  tabindex="-1">冻结</a>
            </li>
            <li role="presentation" ><a  class="text-center" role="menuitem" id="thaw"  tabindex="-1">解冻</a>
            </li>
            <li role="presentation" ><a  class="text-center" role="menuitem" id="stop"  tabindex="-1">终止</a>
            </li>
            <li role="presentation" ><a  class="text-center" role="menuitem" id="start"  tabindex="-1">开始</a>
            </li>
            <li role="presentation" ><a  class="text-center" role="menuitem" id="close"  tabindex="-1">关闭</a>
            </li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a>
            </li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>

<div class="f2 clearfix zsyLeft1">
        <div class="left zsyZi1 left1" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索工单号">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　>>　</a></div>
            <!--树的处理-->
            <div id="jstree_demo1"></div>
        </div>
    <div class="right">
        <form id="printForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">工单基本信息</a></li>
                            <li><a href="#tab_4" data-toggle="tab">进度信息</a></li>
                            <li><a href="#tab_5" data-toggle="tab">客户定制</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1" style="height: 182px;">
                                <div class="box-body  form-inline">
                                    <div class="printTInfo">
                                        <div class="manWidth">
                                            <div class="form-group width30">
                                                <input type="hidden" id="hiddentime" value="${time}"/>
                                                <label for="WoCode">工&emsp;&ensp;单&emsp;&ensp;号</label>
                                                <input type="text" class="form-control" id="WoCode"
                                                   name="ptName" placeholder="工单号可自动生成">
                                            </div>
                                            <div class="form-group width30">
                                                <label>产&ensp;品&emsp;名&ensp;称</label>
                                                <div id="comboSelect" data-id=""></div>
                                            </div>
                                            <div class="form-group width30">
                                                <label class="mright3 num">数&emsp;&emsp;&emsp;&emsp;量</label>
                                                <input type="text" class="form-control checkNumber" id="Num" autocomplete="off" /><label id="unit" value=""></label>
                                                <label id="SpecVer" value=""></label>
                                            </div>

                                        </div>
                                        <div class="cpxx2 cpmcx manWidth">
                                            <div class="form-group width30">
                                                <label for="WoCode">工&ensp;单&emsp;类&ensp;型</label>
                                                <div id="WoType" data-id=""></div>
                                            </div>
                                            <div class="form-group width30">
                                                <label>紧&emsp;&nbsp;&nbsp;急&nbsp;&emsp;度</label>
                                                <div id="jinjidu" data-id=""></div>
                                            </div>
                                            <div class="form-group width20">
                                                <label class="">生&ensp;产&emsp;线&ensp;别</label>

                                                <%--<select class="selectpicker" multiple data-live-search="true" id="scxb" title="请选择线别">

                                                </select>--%>

                                                    <select class="selectpicker" multiple data-live-search="true" id="scxb" title="请选择生产线别">
                                                </select>
                                            </div>
                                            <%--<div class="form-group width20">
                                                <label>生&ensp;产&emsp;线&ensp;别</label>
                                                <div id="scxb" data-id=""></div>
                                            </div>--%>
                                        </div>

                                        <div class="cpxx2 manWidth">
                                            <div class="form-group width30">
                                                <label class="">工&ensp;艺&emsp;流&ensp;程</label>
                                                <select class="selectpicker" multiple data-live-search="true" id="wf" data-max-options="1" title="请选择工艺流程">
                                                </select>
                                            </div>
                                            <div class="form-group width30">
                                                <label>计划开始日期</label>
                                                <input class="form-control datainp wicon check" id="date01" type="text"
                                                       placeholder="mm/dd/yyyy" value="">
                                            </div>
                                            <div class="form-group width30">
                                                <label>计划完工日期</label>
                                                <input class="form-control datainp wicon check" id="date02" type="text"
                                                   placeholder="mm/dd/yyyy" value="">
                                            </div>
                                        </div>
                                        <div class="cpxx2 manWidth" id="printDiv" style="display: none;">
                                            <%--<div class="cpxx2 manWidth">
                                                <label><input type="checkbox" id="isPrint" />是否打印</label>
                                            </div>--%>
                                            <label>打&emsp;&ensp;印&emsp;&ensp;机</label>
                                            <div id="Print" data-id=""></div>
                                            <label>&emsp;打&ensp;印&ensp;模&ensp;板</label>
                                            <div id="PrintT" data-id=""></div>
                                            <%--<label>打&emsp;印&emsp;数&emsp;量</label>--%>
                                            <div id="PrintNum" data-id="" style="display: none;"></div>
                                            <label>&emsp;打&emsp;印&emsp;份&nbsp;&nbsp;&nbsp;&nbsp;数</label>
                                            <input type="number" style="width: 100px;" id="printCopy" value="1" autocomplete="off" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_4" style="height: 182px;">
                                <div class="box-body  form-inline">
                                    <div class="printTInfo">
                                <%-- 进度信息--%>
                                    <div class="cpxx2 manWidth" >
                                        <div class="form-group width25 hiderow"  style="display: none;">
                                            <label>已完工数量&nbsp;&nbsp;&nbsp;</label><label id="FinishNum"></label>
                                        </div>
                                        <div class="form-group width25 hiderow" style="display: none;">
                                            <label  style="margin-left: 50px">未创建批次数量  &nbsp;&nbsp;&nbsp;</label><label  id="UnCBatNum"></label>
                                        </div>
                                        <div class="form-group width25 hiderow" style="display: none;">
                                            <label style="margin-left: 50px">状态&nbsp;&nbsp;&nbsp;</label><label   id="Status"></label>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_5" style="height: 182px;">
                                <div class="box-body  form-inline">
                                    <div class="printTInfo">
                                <%-- 客户定制--%>
                                <div class="cpxx2 manWidth">
                                    <div class="form-group width30">
                                        <label for="OrderCode">订单号</label>
                                        <%--<input type="text" class="form-control" id="OrderCode"
                                               name="ptName" maxlength="40">--%>
                                        <div id="OrderCode" data-id=""> </div>
                                    </div>
                                    <div class="form-group width30">
                                        <label>栈板包装规格</label>
                                        <div id="zanban" data-id=""></div>
                                    </div>
                                    <div class="form-group width30">
                                        <label>箱包包装规格</label>
                                        <div id="xiangbao" data-id=""></div>
                                    </div>
                                </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_2" style="height: 182px;">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
                            </div>
                        </div>
                        <!--*********************************Tab关联批次**************************************-->
                        <div class="nav-tabs-custom mtop">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">关联批次</a></li>
                            </ul>
                            <div class="tab-content">
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
<!-- jqgrid表格结束 -->
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/plan/worderinfo/worderinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

</body>
</html>
