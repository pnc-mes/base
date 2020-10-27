<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/21
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>包装规格</title>

    <meta content="width=device-width, initial-scale=1" name="viewport"/>

    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
</head>
<body>
<div class="btn-group td1 table tabTop common">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1"
                                       id="remove">删除</a></li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">打印</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix zsyLeft1">
    <div class="left left1 zsyZi1" id="left2" style="width:226px;">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索包装规格">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" id="_right" style="margin-left: 230px;">
        <form id="packSP">
            <input type="hidden" id="MaRd"/>
            <input type="hidden" id="MaVerRd"/>
            <input type="hidden" id="ExecType"/>
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">包装规格信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content contentt">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="block bBottom">
                                            <label for="MPName">包装规格名称</label>
                                            <input type="text" class="form-control check" id="MPName"
                                                   name="MPName" placeholder="" maxlength="40">
                                            <label class="labell">　规格类型
                                                    <select id="PackType" name="PackType" class="form-control" style="padding: 1px 6px;width: 196px; ">
                                                        <option id="00" value="00">砧板包装</option>
                                                        <option id="01" value="01">箱包包装</option>
                                                    </select>
                                            </label>
                                            <label class="">　打印模板</label>
                                            <div id="defaultSelect" data-id=""></div>
                                        </div>
                                        <div class="block bBottom">
                                            <label class="">　　序号规则</label>
                                            <div id="defaultSelect1" data-id=""></div>
                                            <label for="Num">　包装数量</label>
                                            <input type="number" class="form-control check" id="Num"
                                                   name="Num" placeholder="" maxlength="10">

                                            <label for="Weight">　标准重量</label>
                                            <input type="number" class="form-control check" id="Weight"
                                                   name="Weight" placeholder="" maxlength="10">
                                        </div>
                                        <div class="block">
                                            <label for="UpLimit">　上限浮动值</label>
                                            <input type="number" class="form-control " id="UpLimit"
                                                   name="UpLimit" placeholder="" maxlength="10">
                                            <label for="DownLimit">下限浮动值</label>
                                            <input type="number" class="form-control " id="DownLimit"
                                                   name="DownLimit" placeholder="" maxlength="10">
                                            <label for="UnitName">　重量单位
                                                <input type="text" list="UnitName_list" class="form-control "
                                                       name="UnitName"
                                                       id="UnitName" maxlength="40"/>
                                                <datalist id="UnitName_list">
                                                </datalist>
                                            </label>
                                        </div>
                                        <div class="block" style="margin-top: 10px;">
                                            　<input type="checkbox" id="isProperty" />是否混包
                                        </div>
                                        <div id="isShow" style="display: none;">
                                            <div class="block" style="margin-top: 10px;">
                                                <label>　　功率区间</label>
                                                <%--<select class="selectpicker" multiple data-live-search="true" id="power" title="请选择功率"></select>--%>
                                                <input type="number" id="power_s" class="form-control" style="margin-right: 3px;" />
                                                　　~　　
                                                <input type="number" id="power_e" class="form-control" style="margin-left: 3px;" />

                                                <label>　组件类型</label>
                                                <select class="selectpicker" multiple data-live-search="true" id="ma" title="请选择类型"></select>
                                            </div>
                                            <div class="block" style="margin-top: 10px;">
                                                <label>　　电流区间</label>
                                                <select class="selectpicker" multiple data-live-search="true" id="current" title="请选择电流"></select>
                                                <label>　颜色区间</label>
                                                <select class="selectpicker" multiple data-live-search="true" id="color" title="请选择颜色"></select>
                                                <label>　等级区间</label>
                                                <select class="selectpicker" multiple data-live-search="true" id="grade" title="请选择等级"></select>
                                            </div>
                                            <div class="block" style="margin-top: 30px;">
                                                <label>　　唛头功率</label>
                                                <select class="selectpicker" data-live-search="true" id="power_" title="请选择功率"></select>
                                                <label>　唛头类型</label>
                                                <select class="selectpicker" data-live-search="true" id="ma_" title="请选择类型"></select>
                                                <label>　唛头电流</label>
                                                <select class="selectpicker" data-live-search="true" id="current_" title="请选择电流"></select>
                                            </div>
                                            <div class="block" style="margin-top: 10px;">
                                                <label>　　唛头颜色</label>
                                                <select class="selectpicker" data-live-search="true" id="color_" title="请选择颜色"></select>
                                                <label>　唛头等级</label>
                                                <select class="selectpicker" data-live-search="true" id="grade_" title="请选择等级"></select>
                                            </div>
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
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/packsp/packspinfo.js?v=2'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>