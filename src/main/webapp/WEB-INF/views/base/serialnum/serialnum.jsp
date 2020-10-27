<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/6/9
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>序号管理</title>
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
</head>
<body>
<div class="btn-group table tabTop aa td1 ">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索序号规则">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <form class="form-inline" id="serialnum">
        <div class="right" id="_right">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">规则信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body formBottom">
                                    <div class="printTInfo">
                                        <div class=" formgroup biaodan1 hangji">
                                            <label for="sNName">规&nbsp;则&nbsp;名&nbsp;称</label>
                                            <input type="text" class="form-control check" id="sNName" name="sNName"
                                                   placeholder="" maxlength="40">
                                            <label for="IsScript" class="checkbox" id="ck" style="">
                                                <input type="checkbox" name="" value="" class="baoliu6" id="IsScript"
                                                       name="IsScript">
                                                脚本实现
                                            </label>
                                        </div>
                                            <div id="ta1">
                                                <div class=" biaodan hangji">
                                                <label for="prefix">前&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缀</label>
                                                <input type="text" class="form-control" id="prefix" name="prefix"
                                                       placeholder="" maxlength="500">
                                                <label for="suffix">后&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缀</label>
                                                <input type="text" class="form-control" id="suffix" name="suffix"
                                                       placeholder="" maxlength="500">
                                                </div>
                                                <div class=" biaodan hangji">
                                                    <label for="sNLength">流水号长度</label>
                                                    <input type="number" class="form-control" id="sNLength"
                                                           name="SNLength" placeholder="">
                                                    <label for="start">起&nbsp;始&nbsp;位</label>
                                                    <input type="number" class="form-control" id="start" name="Start"
                                                           placeholder="">
                                                    <label for="step">步&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长</label>
                                                    <input type="number" class="form-control" id="step" name="Step"
                                                           placeholder="">
                                                </div>
                                                <div class=" biaodan hangji">
                                                    <label for="lastLevel">最后流水号</label>
                                                    <input type="text" readonly="readonly" class="form-control"
                                                           id="lastLevel" name="lastLevel" placeholder="">
                                                    <label for="reset" class="checkbox" id="dd" style="">
                                                        <input type="checkbox" name="" value="" class="baoliu7" id="reset"
                                                               name="reset">
                                                        达到最大流水号的时候重置
                                                    </label>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="ta2" style="display: none">
                                    <div id="jb">
                                        <label for="jname">脚&nbsp;本&nbsp;名&nbsp;称</label>
                                        <input type="text" class="form-control"
                                               id="jname" name="jname" placeholder="">
                                    </div>
                                    <div class="box box-primary  clearfix" style=" margin-top: 20px;">
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
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/base/serialnum/serialnum.js?v=1"></script>
</body>
</html>
