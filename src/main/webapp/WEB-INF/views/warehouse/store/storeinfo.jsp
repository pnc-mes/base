<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>仓库管理</title>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
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
<input type="hidden" id="hidden" name="hidden" h=""/>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索仓库名称">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <form class="form-inline" id="storeInfo">
        <div class="right" id="_right" style="display: none;">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">仓库信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="form-inline">
                                    <div class="printTInfo">
                                        <div class="manWidth f1">
                                            <div class="form-group width30">
                                                <label for="storeCode">仓库代码</label>
                                                <input type="text" class="form-control" id="storeCode"
                                                       name="storeCode" placeholder="仓库代码可自动生成" maxlength="30">
                                            </div>
                                            <div class="form-group width30">
                                                <label>仓库名称</label>
                                                <input type="text" class="form-control check" id="storeName"
                                                       name="storeName" placeholder="" maxlength="40">
                                            </div>
                                            <div class="form-group width30">
                                                <label class="">仓库类别
                                                    <select id="StoreType" name="StoreType" class="form-control" style="padding: 1px 6px;width:196px;">
                                                        <option id="" value=""></option>
                                                        <option id="00" value="00">原料</option>
                                                        <option id="01" value="01">成品仓库</option>
                                                        <option id="02" value="02">不良仓</option>
                                                        <option id="03" value="03">废品仓</option>
                                                    </select>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="cpxx2 cpmcx manWidth " style="margin-top: 20px;">
                                            <div class="form-group width30">
                                                <label class="status num">仓库状态
                                                    <select id="Status" name="Statuss" class="form-control check" style="width: 196px;padding: 1px 6px;">
                                                        <option id="0" value="00">可用</option>
                                                        <option id="1" value="01">不可用</option>
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
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">仓库权限</a>
                            </li>
                            <li><a href="#tab_4" data-toggle="tab">附件字段</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_3">
                                <div class="f1">
                                    <div class="btn btn-primary add1">新增</div>
                                    <div class="btn btn-primary del1">删除</div>
                                </div>
                                <div class="form-inline">
                                    <div class='f1'>
                                        <div class="form-group formgroup">
                                            <label>用户/角色信息</label>
                                            <div id="lymater" data-id=""></div>
                                        </div>
                                    </div>
                                </div>
                                <section class="clearfix">
                                    <div class=" tablecontent">
                                        <div class="gridPanel">
                                            <table id="list4"></table>
                                            <div id="pager4"></div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                            <div class="tab-pane" id="tab_4">
                                <div class='f1' style="float: left;width: 50%;">
                                    <div class="form-group ">
                                        <label for="F1">附加字段一</label>
                                        <input type="text" class="form-control " id="F1"
                                               name="F1" style="width:252px;" placeholder="" maxlength="255">
                                    </div>
                                    <div class="form-group mtop">
                                        <label for="F2">附加字段二</label>
                                        <input type="text" class="form-control " id="F2"
                                               name="F2" style="width:252px;" placeholder="" maxlength="255">
                                    </div>
                                    <div class="form-group mtop">
                                        <label for="F3">附加字段三</label>
                                        <input type="text" class="form-control " id="F3"
                                               name="F3" placeholder="" style="width:252px;" title="" maxlength="255">
                                    </div>
                                    <div class="form-group mtop">
                                        <label for="F4">附加字段四</label>
                                        <input type="text" class="form-control " id="F4"
                                               name="F4" placeholder="" style="width:252px;" title="" maxlength="255">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/store/storeinfo.js?v=1"></script>
</body>
</html>
