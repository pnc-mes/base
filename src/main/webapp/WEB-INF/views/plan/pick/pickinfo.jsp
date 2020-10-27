<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/7
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>领料单管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/selectpage/selectpage.bootstrap3.css">
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn btn-primary " id="onPick">下达</div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn btn-primary " id="addPick">新增</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center " id="delete" role="menuitem" tabindex="-1">删除</a>
            </li>
            <li role="presentation"><a  class="text-center " id="offPick" role="menuitem" tabindex="-1">取消</a>
            </li>
        </ul>
    </div>
    <div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="import">导入</a></li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="export">导出</a></li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="export1">下载推荐库位单</a></li>
        </ul>
    </div>
    <button class="btn btn-primary _close">关闭</button>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<div class="f2  clearfix zsyLeft1">
    <div class="left zsyZi1 left1" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索领料单">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" id="_right">
        <form id="pickForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom"  style="margin-bottom: 250px">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab">领料单信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class='printTInfo'>
                                        <div class="form-group formgroup">
                                            <label>领料单号</label>
                                            <input type="text" class="form-control" id="PickCode" name="PickCode" placeholder="领料单号可自动生成" maxlength="50"/>
                                        </div>
                                        <div class="form-group formgroup">
                                            <label>　工单号</label>

                                            <div id="AssCode" data-id="" style="margin-right: 20px;"></div>
                                            <input type="hidden" id="AssSource" name="AssSource">
                                        </div>
                                        <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产品代码</label>
                                        <input type="text" class="form-control" id="MaCode" name="MaCode" readonly/>
                                    </div>
                                    <%--floor2--%>
                                    <div class=" formgroup mb cpmc mtop " >
                                        <label>产品名称</label>
                                        <input type="hidden" class="form-control" id="MaVerRd" name="MaVerRd"/>
                                        <input type="text" class="form-control" id="MaName" name="MaName" readonly/>
                                        <label for="Num">生产总量</label>
                                        <input type="text" class="form-control" id="Num" readonly name="Num"/>
                                        <label id="UnitName" style="width: 78px;"></label>

                                        <label>预领料日期</label>
                                        <input type="date" class="form-control" id="PrePickDate" name="PrePickDate">

                                    </div>
                                    <div class=" formgroup mb cpmc mtop " >

                                        <label>领料仓库</label>
                                        <div id="material_warehouse" data-id=""></div>

                                        <label style="margin-left: 20px">状　　态</label>
                                        <input type="text" class="form-control" id="ExStatus" name="ExStatus"
                                               data-status="00" readonly/>

                                        <label style="margin-left: 20px">审核状态</label>
                                        <input type="text" class="form-control" id="SStatus" name="SStatus"
                                               data-status="00" readonly/>
                                    </div>
                                </div>
                                <!--处理表格-->
                                <div class="nav-tabs-custom" style="margin-top: 30px;margin-bottom:0px;">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_4" data-toggle="tab">领料明细</a></li>
                                        <li class="" id="tab_4_li" style="position: relative;display: none;">
                                            <a href="#tab_44" data-toggle="tab" class=" lr-delete wlxh">明细设置&emsp;</a>
                                            <i class="fa fa-remove XH" style="position: absolute;top: 2px;right: 4px;"></i>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active clearfix" id="tab_4">
                                            <div class="f2_4 btn-group" id="maInfoButton">
                                                <div class="btn btn-primary add1">新增</div>
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
                                        <!-- 明细 -->
                                        <div class="tab-pane clearfix" id="tab_44" style="margin-bottom: 250px;">
                                            <div class="f2_4 btn-group bBottom" id="sure2">
                                                <div class="btn btn-primary sure2">确认</div>
                                            </div>
                                            <div class="bBottom block">
                                                <div class="form-group ">
                                                    <label>领料仓库</label>
                                                    <div id="Store" data-id=""></div>
                                                    <label>领用物料</label>
                                                    <div id="lymater" data-id=""></div>
                                                    <label>库存数量</label>
                                                    <input type="text" id="ccsl" disabled="disabled" />
                                                </div>
                                            </div>
                                            <div class="bBottom block">
                                                <div class="form-group formgroup">
                                                    <label>应领数量</label>
                                                    <input type="text" id="ccs2" name="=ccs2" disabled="disabled" />
                                                    <label>领用数量</label>
                                                    <input type="text" class="checkFloat" style="width: 70px;" id="lysl" placeholder=""><input type="hidden" id="ycyid" value=""/>
                                                    <label>备注</label>
                                                    <textarea id="Remark"></textarea>
                                                </div>
                                            </div>

                                            <div class="bBottom block">
                                                <div class="form-group formgroup">
                                                   <label>替代料信息</label><br/>
                                                </div>

                                                <div class="gridPanel">
                                                    <table id="list7"></table>
                                                    <div id="pager7"></div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
    <%--处理替代料弹出层--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <button type="button" class="btn btn-primary confirm">确认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
                <div class="modal-body"><%--模态框加载页面--%>
                    <div>
                        <div class="row rightTop">
                            <div class="col-md-12">
                                <!--处理表格-->
                                <div class="tab-content">
                                    <div class="tab-pane active clearfix" id="tab_5">
                                        <div class=" clearfix">
                                            <section class="clearfix">
                                                <div class="col-md-12">
                                                    <div class="box-body tablecontent">
                                                        <div class="gridPanel">
                                                            <table id="list5"></table>
                                                            <div id="pager5"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                                <div id="replace">
                                    <label>物料代码
                                        <input type="text" id="code" name="MaCode" readonly/>
                                    </label>
                                    <label>物料名称
                                        <input type="text" id="name" name="MaName" readonly/>
                                    </label>
                                    <label>替换数量
                                        <input type="number" id="reNum" name="Num" />
                                        <span id="UnitName_2"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /.modal-content -->领料仓库
        </div><!-- /.modal -->
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi1.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/plan/pick/pickinfo.js?v=4"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>

