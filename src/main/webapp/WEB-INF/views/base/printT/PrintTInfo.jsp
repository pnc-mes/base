<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>打印模板</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group td1  table common" id="header">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span> <%--dropdownMenu1--%>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">复制</a>--%>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a>
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

<input type="hidden" id="ExecType" name="ExecType"/>

<div class="f2  clearfix zsyLeft1  clearfix">
    <div class="left zsyZi1 " id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索打印模板">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>

    <div class="right" id="_right">
        <form id="printForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">打印模板信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <label for="printModelName">打印模板名称</label>
                                            <input type="text" class="form-control check" id="printModelName"
                                                   name="PtName" placeholder="" maxlength="60">
                                        </div>
                                        <div class="form-group">
                                            <label for="fileName">模板文件</label>
                                            <input type="hidden" class="form-control read rightMargin" id="fileName"
                                                   readonly
                                                   name="FileName" accept=".btw" placeholder="">
                                            <span class="btn btn-success start fileinput-button"
                                                  style="position: relative;">
                                                <i class="fa fa-upload"></i>
                                                <span> 上传 </span>
                                            <input type="file" id="file" readonly name="file" accept=".btw,.xlsx" multiple="" >
                                            </span>
                                            <span class="btn btn-success start fileinput-button"
                                                  style="position: relative;">
                                                <i class="glyphicon glyphicon-download-alt" style="color:white;"></i>
                                                <a id="filedown" style="color:#fff;"><span class="filedown"> 开始下载 </span></a>
                                            </span>
                                            (只能上传.btw/.xlsx文件)
                                        </div>

                                    </div>
                                </div>

                                <ul id="myTab" class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#home" data-toggle="tab" id="atthome">
                                            绑定字段
                                        </a>
                                    </li>
                                    <li><a href="#ios" data-toggle="tab" id="anonthertab">自定义模板字段</a></li>
                                </ul>

                                <div id="myTabContent" class="tab-content">
                                    <div class="tab-pane fade in active" id="home">
                                        <div class="f2_4 btn-group">
                                            <div class="btn btn-primary add1">新增</div>
                                            <%--<div class="btn btn-primary edit1">编辑</div>--%>
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
                                    <div class="tab-pane fade" id="ios">
                                        <div class="box-body form-inline" style="margin-bottom: 10px;">
                                            <div class="printTInfo">
                                                <div class="form-group">
                                                    <label for="jbmc">脚本名称&nbsp;&nbsp;</label>
                                                    <input type="text" class="form-control" id="jbmc"
                                                         placeholder="" maxlength="60">
                                                    <label><input type="checkbox" id="isdefault"/><span style="margin-left: -15px;">启用脚本实现</span></label>
                                                </div>
                                            </div>
                                            <div style="width: 800px;height: 400px;margin-top: 13px;">
                                                <div class="printTInfo" style="float: left;width: 550px;height: 400px;">
                                                    <div class="form-group">
                                                        <label>定义输入</label>
                                                        <div class="tab-pane fade in active" style="margin-top: 8px;">
                                                            <div class="f2_4 btn-group">
                                                                <div class="btn btn-primary add2">新增</div>
                                                                <div class="btn btn-primary del2">删除</div>
                                                            </div>
                                                            <section>
                                                                <div class="row">
                                                                    <div class="col-md-12">
                                                                        <div class="box-body tablecontent">
                                                                            <div class="gridPanel">
                                                                                <table id="list2"></table>
                                                                                <div id="pager2"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </section>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="printTInfo"style="float: right;width: 250px;height: 400px;">
                                                    <div class="form-group">
                                                        <div class="f2_4 btn-group">
                                                            <label>定义输出</label>
                                                            <div class="tab-pane fade in active" style="margin-top: 8px;">
                                                                <div class="btn btn-primary add3">新增</div>
                                                                <div class="btn btn-primary del3">删除</div>
                                                                <section>
                                                                    <div class="row">
                                                                        <div class="col-md-12">
                                                                            <div class="box-body tablecontent">
                                                                                <div class="gridPanel">
                                                                                    <table id="list3"></table>
                                                                                    <div id="pager3"></div>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/printTInfo/printTInfo.js?v=1'></script>
</body>
</html>
