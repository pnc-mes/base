<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/7/5
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
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
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <style>
        .ui-jqgrid-bdiv{
            height:600px;
        }
    </style>
</head>
<body>

<div class="btn-group td1 table tabTop common">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd" b="">新增</div>
    <div class="btn btn-primary cSave" a="">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1" a="">删除</a>
            </li>
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="copy" co="">复制</a>
            </li>--%>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="repwd"
                                       r="">重置密码</a>
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
    <div class="btn btn-primary _close" id="close" onclick="closeTab(this)">关闭</div>
</div>
<div class="f2 clearfix zsyLeft1">
    <div class="left left1 zsyZi1" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索用户">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <!--树的处理-->
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>
    <div class="right">
        <form id="printForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">用户信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class="bBottom">
                                            <div class="form-group formgroup">
                                                <label for="printModelName">用户名称</label>
                                                <input type="text" class="form-control check" id="printModelName"
                                                       name="ptName" maxlength="40">
                                            </div>
                                            <div class="form-group formgroup check" id="pwdhidden" style="display: none;">
                                                <label for="mima">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                                                <input type="text" class="form-control check" id="mima"
                                                       name="ptName" maxlength="30">
                                            </div>
                                        </div>
                                        <div class="bBottom">
                                            <div  class="form-group formgroup">
                                                <label for="name">真实姓名</label>
                                                <input type="text" class="form-control check" id="name"
                                                       name="ptName" maxlength="30" placeholder="">
                                            </div>
                                            <div class="form-group formgroup check">
                                                <label for="MobileNo">电话号码</label>
                                                <input type="text" class="form-control" id="MobileNo"
                                                       name="MobileNo" maxlength="20" placeholder="">
                                            </div>
                                        </div>

                                        <div class="bBottom">
                                            <div class="form-group formgroup">
                                                <label for="EmailAddress">邮件地址</label>
                                                <input type="text" class="form-control" id="EmailAddress"
                                                       name="EmailAddress" maxlength="50">
                                            </div>
                                            <div class="form-group formgroup" style="display: none;">
                                                <label for="banbie">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
                                                <div id="banbie" data-id=""></div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="nav-tabs-custom">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">所属角色</a></li>
                                    </ul>
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
<%@ include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/sys/user/userInfo.js?v=3'></script>
</body>
</html>
