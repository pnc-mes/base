<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>IQC管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
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
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/qa/IQC/iqcinfo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">

    <style>
        .search{
            margin-bottom:20px;
            -webkit-border-radius:5px;
            -moz-border-radius:5px;
            border-radius:5px;
            height:30px;
            outline: none;
            border:1px solid #C3C3C3;
        }

    </style>
</head>
<body>

<div class="btn-group table tabTop common td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave" id="save" >保存</div>
    <div class="btn btn-primary cDel" id="remove" a="">删除</div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>


<input type="hidden" id="ExecType" name="ExecType"/>
<input type="hidden" id="fileName" name="fileName"/>

<div class="f2 clearfix zsyLeft1">
        <div class="left left1 left2 zsyZi1">
            <input class="input1 form-control" type="search" id="" name="" value="" placeholder="搜索批次号">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    <div class="right">
        <form id="iqcForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">质检信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class="block">
                                            <div class="form-group formgroup">
                                                <label for="MaCode">物料代码</label>
                                                <input type="text" class="form-control" id="MaCode" readonly
                                                       name="MaCode">
                                            </div>
                                            <div class="form-group">
                                                <label for="Num">数&emsp;&emsp;量</label>
                                                <input type="text" class="form-control" id="Num" readonly
                                                       name="Num">
                                            </div>
                                        </div>
                                        <div class="block blockf2">
                                            <div class="form-group formgroup">
                                                <label for="MaName">物料名称</label>
                                                <input type="text" class="form-control" id="MaName" readonly
                                                       name="MaName">
                                            </div>
                                            <div class="form-group">
                                                <label class="mright3">检验结果
                                                    <select id="CkResult" name="CkResult"
                                                            class="form-control selectControl" style="height: 31px;">
                                                        <option value="00">合格</option>
                                                        <option value="01">不合格</option>
                                                        <option value="02">降级使用</option>
                                                    </select>
                                                </label>
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
        <!--************************************************处理tab切换表格***********************************************************************8-->
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">不良/降级代码</a></li>
                        <!--<li><a href="#tab_4" data-toggle="tab">质检报告</a></li>-->
                    </ul>
                    <div class="tab-content f2_4">
                        <div class="tab-pane active" id="tab_3">
                            <div class="boxx ">
                                <div id="defaultSelect" data-id="" disabled></div>
                                <div class=" btn-group">
                                    <input type="button" class="btn btn-primary del1" id="deleteReaCode" value="删除"/>
                                </div>

                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body tablecontent">
                                                <div class="gridPanel">
                                                    <table id="list9"></table>
                                                    <div id="pager9"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="tab_4">
                            <div class="boxx">
                                <div class="btn-group">
                                    <div class="btn btn-primary add1">新增</div>
                                    <div class="btn btn-primary del1">删除</div>
                                </div>
                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body tablecontent">
                                                <div class="gridPanel">
                                                    <table id="list10"></table>
                                                    <div id="pager10"></div>
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
    </div>

</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/qa/iqc/iqcinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

</body>

</html>
