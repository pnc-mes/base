<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>检验模板关联</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>
<body>
<div class="btn-group table tabTop td1 common">
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>

    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
   <%--         <li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class="zsyLeft" >
        <div class="left left1 zsyZi" id="left2" style="width: 250px;" >
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索模板关联名称">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　>>　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right" style="margin-left: 260px;">
        <form id="factoryinfo">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">模板关联信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class=" formgroup disblock">
                                            <label for="TempRelName">检验模板关联名称&nbsp;</label>
                                            <input type="text" class="form-control check" id="TempRelName"
                                                   name="TempRelName" placeholder="" maxlength="50">
                                            <%--<label style="margin-left: -65px;"><input type="checkbox" id="default"/>&nbsp;默认</label>--%>
                                            <div class="form-group formgroup" style="padding-left: -30px">
                                                <label>模&nbsp;板&nbsp;类&nbsp;型&nbsp;</label>
                                                <select id="TempRelType" style="width: 135px;">
                                                    <option value="00">来料检验</option>
                                                    <option value="01">销售出库检验</option>
                                                    <option value="02">生产入库检验</option>
                                                </select>
                                            </div>
                                            <div class="form-group " id="guanlian1">
                                                <label>供&nbsp;&nbsp;应&nbsp;&nbsp;商</label>
                                                <div id="SupplierSelect" data-id=""></div>
                                            </div>
                                            <div class="form-group " id="guanlian2" style="display: none;" >
                                                <label>&nbsp;客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户</label>
                                                <div id="CustomerSelect" data-id=""></div>
                                            </div>
                                        </div>

                                        <div class="form-group formgroup" >
                                            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关联类型&nbsp;</label>
                                            <select id="correlationtype" style="width: 135px;">
                                                <option value="00">物料</option>
                                                <option value="01">物料类别</option>
                                            </select>

                                            <div class="form-group " id="guanlian3" style="padding-left: 100px" >
                                                <label>物&nbsp;料&nbsp;编&nbsp;码&nbsp;</label>
                                                <div id="material" data-id=""></div>
                                            </div>
                                            <div class="form-group " id="guanlian4"style="display: none;padding-left: 57px" >
                                                <div class="maBottom">
                                                    <label class="maBottom">物&nbsp;料&nbsp;类&nbsp;别&nbsp;
                                                        <input type="hidden" id="MaTypeRd" name="MaTypeRd" />
                                                        <input id="type" name="type" autocomplete="off" style="width:136px;" maxlength="80"/>
                                                    </label>
                                                    <div id="t" style="position: absolute;z-index: 99999;width:136px;display:none;
                                                background: #FFF;margin-top: -10px;border: 1px solid darkgray;
                                                height:150px;overflow: auto;"></div>
                                                    <div id="MaType" data-id="" style="display: none;"></div>
                                                </div>
                                            </div>

                                                <label>检验文档</label>
                                                <div id="defaultSelect" data-id=""></div>

                                        </div>

                                    </div>
                                </div>

                                <div style="padding-top: 30px">
                                <ul id="myTab" class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#home" data-toggle="tab">
                                            关联模板明细
                                        </a>
                                    </li>
                                </ul>
                                </div>
                                <div id="myTabContent" class="tab-content">
                                    <div class="tab-pane fade in active" id="home">
                                        <div class="box box-primary  clearfix">
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
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的系统信息--%>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/qa/jyx/jymbbinfo.js?v=1'></script>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<script src="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.js"></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
</body>
</html>
