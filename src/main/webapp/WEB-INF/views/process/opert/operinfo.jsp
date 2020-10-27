<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>作业管理</title>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/spec/specinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/oper/operinfo.css">
</head>
<body>

<div class="btn-group table tabTop common td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd" id="add" a="">新增</div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="recopy"
                                       e="">复制</a></li>--%>
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1" id="remove" d="">删除</a>
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
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索作业">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right">
        <form id="opertForm" >
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">作业信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="disblock">

                                        <label for="OptName">作业名称</label>
                                        <input type="text" class="form-control check" id="OptName"
                                               name="OptName" placeholder="" maxlength="40">


                                        <label>所属工作中心</label>
                                        <div id="defaultSelect" data-id=""></div>
                                    </div>
                                    <div class="disblock">
                                        <label>批次等级</label>
                                        <div id="defaultSelect1" data-id="" style="margin-right: 114px"></div>
                                        <label>原因代码组</label>
                                        <div id="defaultSelect2" data-id=""></div>
                                    </div>
                                    <div class="disblock">

                                    </div>

                                    <!--*******************************表单下面的内容****************************************-->
                                    <div class="container clearfix border">
                                        <input type="hidden" id="hidden" value="" hi="" a="" b=""/>
                                        <!--****************************左侧********************************-->
                                        <div class="leftt">
                                            <div class="margina">
                                                <label>出入站操作</label>
                                            </div>
                                            <ul>
                                                <li class="margina">
                                                    <label>
                                                        <input type="radio" name="SpecOption"
                                                               id="optionsRadios3" value="00">执行接收操作
                                                    </label>
                                                </li>
                                                <li class="margina">
                                                    <label>
                                                        <input type="radio" name="SpecOption"
                                                               id="optionsRadios4" value="01">系统自动过站
                                                    </label>
                                                </li>
                                                <li class="margina">
                                                    <label>
                                                        <input type="radio" name="SpecOption"
                                                               id="optionsRadios7" value="02">自动入站
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                        <!--****************************中间********************************-->
                                        <div class="leftt">
                                            <div class="margina ">
                                                <label for="dabao" id="_db">
                                                    <input type="checkbox" id="dabao" name="PackOpt" class="dabao"
                                                           value="1">打包作业
                                                </label>
                                            </div>
                                            <ul>
                                                <li class="margina a disabled">
                                                    <label>
                                                        <input type="checkbox" id="CkWeight" name="CkWeight"
                                                               value="">是否检验重量
                                                    </label>
                                                </li>
                                                <li class="margina a disabled">
                                                    <label>
                                                        <input type="radio" name="PackType" id="optionsRadios5"
                                                               value="00">
                                                        同产品，不同工单
                                                    </label>
                                                </li>
                                                <li class="margina a disabled">
                                                    <label>
                                                        <input type="radio" name="PackType" id="optionsRadios6"
                                                               value="01">
                                                        同产品同工单
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                        <!--****************************右侧********************************-->
                                        <div class="leftt">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="BadOutSpec" name="BadOutSpec"
                                                           value="">检验不良不出站
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <label class="operColor">*当前工序出站时检验到下道工序是否系统自动过站的情况则直接出入站</label>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的系统信息--%>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/opert/opert.js?v=2'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>

