<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/6/26
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>工序管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>

    <!-- 树开始 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"
          rel="stylesheet"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/spec/specinfo.css">
</head>
<body>
<div class="btn-group table tabTop common td1">
    <input type="hidden" id="hidden" value="" h="" r=""/>
    <%--<div  class="btn btn-primary cSelect">筛选</div>--%>
    <div  class="btn btn-primary cAdd" id="add" e="">新增</div>
    <div  class="btn btn-primary cSave" id="save">保存</div>

    <div class="btn-group">
        <div  class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
                id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1" id="remove" a="">删除</a>
            </li>
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="copy" d="">复制</a>
            </li>--%>
            <li role="presentation"><a class="text-center" role="menuitem" tabindex="-1"
                                       id="addVersion" ad="" a="" b="" c="">新增版本</a></li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="removeVersion"
                                       b="">删除版本</a></li>
        </ul>
    </div>
    <%--<div class="btn-group">--%>
        <%--<div  class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"--%>
                <%--id="option2">选项--%>
            <%--<span class="caret"></span>--%>
        <%--</div>--%>
        <%--<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">--%>
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>--%>
        <%--</ul>--%>
    <%--</div>--%>
    <div  class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索工序">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <!--树的处理-->
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" >
        <input type="hidden" id="hidde" value=""/>
        <form class="form-inline" id="aa">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">工序管理</a></li>
                            <%--<li><a href="#tab_3" data-toggle="tab" class="dyxx">打印信息</a></li>--%>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <form id="full">
                                    <div class="box-body">
                                        <div class="printTInfo">
                                            <div class=" flo2">
                                                <div class="form-group f2label1">
                                                    <label for="specName">工序名称</label>
                                                    <input type="text" class="form-control check m0" id="specName"
                                                           name="specName" style="margin-right: 0;" maxlength="40">
                                                </div>
                                                <div class="form-group f2label2">
                                                    <label for="version">版&emsp;本</label>
                                                    <input type="text" class="form-control check version" id="version"
                                                           name="version" style="margin-right: 0;" maxlength="30">
                                                </div>
                                                <div class="form-group f2label3">
                                                    <label for="isDef" class="checkbox" id="dd" style="">
                                                        <input type="checkbox" value="" class="baoliu7 check" id="isDef"
                                                               name="isDef" style="margin-right: 0;">
                                                        设置为默认版本
                                                    </label>
                                                </div>
                                            </div>
                                            <div class=" disblock  mtop flo2" >
                                                <div class="form-group f2label1">
                                                    <label>作&emsp;&emsp;业</label>
                                                    <div id="defaultSelect" data-id=""></div>
                                                </div>
                                                <div class="form-group f2label2">
                                                    <label >设备组</label>
                                                    <div id="defaultSelect1" data-id=""></div>
                                                </div>
                                                <div class="form-group f2label3">
                                                    <label id="posion test2">数据采集</label>
                                                    <div id="defaultSelect2" data-id=""></div>
                                                </div>
                                            </div>
                                            <div class="flo2">
                                                <div class="form-group f2label1">
                                                    <label class="" id="posion test3" >技&ensp;能&ensp;组</label>
                                                    <div id="defaultSelect3" data-id=""></div>
                                                </div>
                                                <div class="form-group f2label2">
                                                    <label class="">文件组</label>
                                                    <div id="defaultSelect4" data-id="" ></div>
                                                </div>
                                                <div class="form-group f2label3">
                                                    <label  for="specName">状&emsp;&emsp;态
                                                        <select id="status" name="Statuss" class="form-control" style="padding: 1px 6px;width:196px;">
                                                            <option id="00" value="00">可用</option>
                                                            <option id="01" value="01">不可用</option>
                                                        </select>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- /.tab-pane -->
                            <%--<div class="tab-pane" id="tab_3">
                                <label>打&emsp;&ensp;印&emsp;&ensp;机</label>
                                <div id="Print" data-id=""></div>
                                <label>&emsp;打&ensp;印&ensp;模&ensp;板</label>
                                <div id="PrintT" data-id=""></div>
                            </div>--%>


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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/spec/specinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

<!-- jqgrid表格结束 -->
</body>
</html>
