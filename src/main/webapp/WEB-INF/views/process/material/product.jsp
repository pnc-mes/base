<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/29
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>产品管理</title>

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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/bom/bominfo.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/demo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/selectpage/selectpage.bootstrap3.css">
</head>
<style>
    .vertical {
        vertical-align: top;
    }
    #MaDes {
        height: 28px;
        max-height: 28px;
        width: 196px;
        border-color: rgb(210, 214, 222);
    }
</style>
<body>
<div class="btn-group td1 table tabTop common">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="copy">复制</a></li>
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="addVer">新增版本</a>
            </li>--%>
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1"
                                       id="removeVer">删除规格</a></li>
            <li role="presentation"><a href="#" class="text-center cDel" role="menuitem" tabindex="-1"
                                       id="remove">删除</a></li>
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1"
                                       id="readBOM">查看BOM层次</a></li>
        </ul>
    </div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close" >关闭</div>
</div>
<div class="f2 clearfix zsyLeft1">
    <div class="left left1 zsyZi1" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索产品">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div><!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" id="_right" style="display: none;">
        <form id="printForm">
            <input type="hidden" id="MaRd"/>
            <input type="hidden" id="MaVerRd"/>
            <input type="hidden" id="ExecType"/>
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">产品信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div style="width: 50%;float: left;">
                                            <ul class='ulLeft' style="padding-left: 0px;">
                                                <li>
                                                    <label for="MaCode">产品代码
                                                        <input type="text" class="form-control check" id="MaCode" name="MaCode">
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="MaName">产品名称
                                                        <input type="text" class="form-control check" id="MaName" name="MaName" >
                                                    </label>
                                                </li>
                                                <li>
                                                    <label class="vertical"><span>产品描述</span>
                                                        <textarea id="MaDes" name="MaDes"></textarea>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="Version">产品规格
                                                        <input type="text" class="form-control check" id="Version" name="Version">
                                                    </label>
                                                </li>
                                                <li>
                                                    <label class="">产&ensp;品&ensp;族</label>
                                                    <div id="defaultSelect" data-id=""></div>
                                                </li>
                                                <li>
                                                    <label class="">单&emsp;&emsp;位</label>
                                                    <div id="defaultSelect1" data-id=""></div>
                                                </li>
                                                <li>
                                                    <label for="MaCode">最低库存</label>
                                                    <input type="text" class="form-control check" id="MinSNum"
                                                           name="MinSNum" placeholder="">
                                                </li>
                                                <li>
                                                    <label for="MaCode">最大库存</label>
                                                    <input type="text" class="form-control check" id="MaxSNum"
                                                           name="MaxSNum" placeholder="">
                                                </li>
                                            </ul>
                                        </div>

                                        <div style="width: 50%;display: inline-block">
                                            <ul class='ulLeft'>
                                                <li>
                                                    <label for="Status">状&emsp;&emsp;&emsp;&emsp;态
                                                    <select class="form-control selectControl" id="Status" name="Status">
                                                        <option value="00">可用</option>
                                                        <option value="01">不可用</option>
                                                    </select>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label class="">序&ensp;号&emsp;规&ensp;则</label>
                                                    <div id="defaultSelect2" data-id=""></div>
                                                </li>
                                                <li>
                                                    <label class="">工&ensp;艺&emsp;流&ensp;程</label>
                                                    <div id="defaultSelect3" data-id=""></div>
                                                </li>
                                                <li>
                                                    <label class="">BOM&ensp;清&emsp;单&nbsp;</label>
                                                    <div id="defaultSelect4" data-id=""></div>
                                                </li>
                                                <li>
                                                    <label >客户产品代码
                                                        <input type="text" class="form-control " id="CusMaCode" name="CusMaCode" />
                                                    </label>
                                                </li>
                                            </ul>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/plugins/selectpage/selectpage.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/bootstrap-datepicker.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/material/productinfo.js?v=2'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
