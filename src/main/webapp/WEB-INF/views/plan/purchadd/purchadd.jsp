<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/23
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>采购单管理</title>
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
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/toastr.css"/>
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn btn-primary " id="xiada">下达</div>
    <div class="btn btn-primary " id="addPurch">新增</div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center " id="delete" role="menuitem" tabindex="-1">删除</a>
            </li>
            <li role="presentation"><a  class="text-center " id="cancel" role="menuitem" tabindex="-1">取消</a>
            </li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </button>
    </div>--%>
    <button class="btn btn-primary _close">关闭</button>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<input type="hidden" id="hidden3"  h=""/>
<div class="f2  clearfix zsyLeft1">
    <div class="left zsyZi1 left1" id="left2" style="height: 95.5%">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索采购单">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div><a title="上一页" id="prev" a style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页"
                                                                                                     id="next"
                                                                                                    b style="display: block;float: right;cursor: pointer">　>>　</a>
        </div>
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" style="height: 93%" id="_right">
        <form id="purchForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <%--floor1--%>
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" >采购单信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="form-inline">
                                    <div class='f1'>
                                        <div class="form-group formgroup">
                                            <label>采购单号　　</label>
                                            <input type="text" class="form-control" id="PurChCode"
                                                   name="PurChCode"
                                                   placeholder="采购单号可自动生成" maxlength="50">
                                        </div>
                                        <div class="form-group formgroup">
                                            <label>采购员</label>
                                            <div id="Buyers" data-id=""></div>
                                        </div>
                                        <div class="form-group formgroup ">
                                            <label>供应商</label>
                                            <div id="SupNames" data-id=""></div>
                                        </div>
                                    </div>
                                    <%--floor2--%>
                                    <div class=" formgroup mb mtop">
                                        <label>要求到货日期</label>
                                        <input type="date" class="form-control" id="ArrivalTime" name="ArrivalTime"
                                               placeholder="">
                                        <label for="status" id="stat">状态</label>
                                        <label id="status"></label>
                                        <label for="SStatus" id="SStatus" style="margin-left: 50px;">审核状态</label>
                                        <label id="sstatus1" style="margin-left: 10px;"></label>
                                    </div>
                                </div>

                                <div class="nav-tabs-custom" style="margin-top: 30px;margin-bottom:0px;">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_3" data-toggle="tab">采购明细</a></li>
                                        <li class="" id="tab_4_li" style="position: relative;">
                                            <a href="#tab_4" data-toggle="tab" class=" lr-delete wlxh">明细设置&emsp;</a>
                                            <i class="fa fa-remove XH" style="position: absolute;top: 2px;right: 4px;"></i>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active clearfix" id="tab_3">
                                            <div class="f2_4 btn-group" id="maInfoButton">
                                                <div class="btn btn-primary add1">新增</div>
                                                <div class="btn btn-primary del1">删除</div>
                                            </div>
                                            <section>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="box-body tablecontent">
                                                            <div>
                                                                <table id="list4"></table>
                                                                <div id="pager4"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                        <div class="tab-pane clearfix" id="tab_4">
                                            <div class="f2_4 btn-group bBottom">
                                                <div class="btn btn-primary sure2">确认</div>
                                            </div>
                                            <div class="bBottom block">
                                                <div class="form-group ">
                                                    <label>物料名称</label>
                                                    <div id="maName" data-id=""></div>

                                                    <label>甲供料</label>
                                                    <select name="AFeed" id="JGL">
                                                        <option value="00">是</option>
                                                        <option value="01">否</option>
                                                    </select>

                                                    <label>收料仓库</label>
                                                    <div id="storeName" data-id=""></div>
                                                </div>
                                            </div>
                                            <div class="bBottom block">
                                                <label>采购数量
                                                    <input type="text" id="CGNum" class="checkNumber" style="margin-right: 0;width: 196px;" name="CGNum">
                                                </label>
                                                <label class="unit"></label>
                                            </div>
                                        </div>
                                        <div class="tab-pane clearfix" id="tab_5">
                                            <div class="f2_4 btn-group">
                                                <div class="btn btn-primary sure1">确认</div>
                                            </div>
                                            <div class="f2_4 block" id="tidailiao">
                                                <div class="form-group ">
                                                    <label>替代料</label>
                                                    <div id="replaceMaInfo" data-id=""></div>
                                                </div>
                                            </div>
                                            <div class="btn-group">
                                                <div class="btn btn-primary del2">删除</div>
                                            </div>
                                            <section>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="box-body tablecontent">
                                                            <div>
                                                                <table id="list5"></table>
                                                                <div id="pager5"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>



                            <%--   <div class="boxx">
                                    <div class="f2_4 btn-group">
                                        <div class="btn btn-primary add1">新增</div>
                                        <div class="btn btn-primary del1">删除</div>
                                    </div>
                                    <section class="clearfix">
                                        <div class=" tablecontent">
                                            <div class="gridPanel">
                                                <table id="list4"></table>
                                                <div id="pager4"></div>
                                            </div>
                                        </div>
                                    </section>
                                </div>--%>
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
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/plan/purchadd/purchadd.js?v=1"></script>
</body>
</html>


