<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>无工单领料</title>
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
    <style type="text/css">
        li:hover #b{
            display: block;
        }
        #b{
            display: none;
        }
    </style>
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn btn-primary xd" a>下达</div>
    <div class="btn btn-primary cSave" a>保存</div>
    <div class="btn btn-primary cadd" a>新增</div>
    <div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a   class="text-center" id="delete" role="menuitem" tabindex="-1">删除</a></li>
            <li role="presentation"><a   class="text-center" id="cancel" role="menuitem" tabindex="-1" a>取消</a></li>
        </ul>
    </div>
    <div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu " role="menu" aria-labelledby="dropdownMenu1" id="impo">
            <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1" id="import">导入</a></li>
            <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1" id="export3">导出</a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" id="b" style="margin-left: 160px;margin-top: -57px;">
                    <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1" id="export">导出备货清单</a></li>
                    <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1" id="export2">导出领料确认单</a></li>
                </ul>
            </li>
            <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1" id="export1">下载推荐库位单</a></li>
        </ul>
    </div>
    <button class="btn btn-primary _close">关闭</button>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<div class="f2  clearfix zsyLeft1">
    <div class="left zsyZi1 left1" id="left2" style="height: 95.5%;width:226px;">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索无工单领料">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" style="height: 93%;margin-left: 230px;" id="_right">
      <%--  <form id="purchForm">--%>
            <div class="row rightTop">
                <div class="col-md-12">
                    <%--floor1--%>
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab">领料单信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="form-inline">
                                    <div class='f1'>
                                        <div class="form-group formgroup">
                                            <label>领料单号&nbsp;</label>
                                            <input type="text" class="form-control" id="PurChCode" name="PurChCode" placeholder="领料单号可自动生成" maxlength="50">
                                        </div>
                                        <div class="form-group formgroup">
                                            <label>&nbsp;&nbsp;领用人</label>
                                            <div id="Buyers" data-id=""></div>
                                        </div>
                                        <div class="form-group formgroup ">
                                            <label>&nbsp;&nbsp;&nbsp;&nbsp;预领料日期</label>
                                            <input type="date" class="form-control" id="ArrivalTime" name="" placeholder="">
                                        </div>
                                    </div>
                                    <%--floor2--%>
                                    <div class="formgroup mb mtop">
                                        <label id="yt">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;途</label>
                                        <input id="use" type="text" class="form-control" maxlength="160"/>

                                        <label>领料仓库</label>
                                        <div id="Store2" data-id=""></div>

                                        <label for="bom">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BOM清单
                                        </label>
                                        <div id="bom" data-id=""></div>
                                    </div>

                                    <div class="formgroup mb mtop">
                                        <label for="status" id="stat">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态&nbsp;&nbsp;</label>
                                        <label id="status"></label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label for="shztss" id="shzt">审核状态&nbsp;&nbsp;</label>
                                        <label id="shztss"></label>
                                    </div>
                                </div>
                                <div class="nav-tabs-custom" style="margin-top: 30px;margin-bottom:0px;">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_3" data-toggle="tab">领料明细</a></li>
                                        <li class="" id="tab_4_li" style="position: relative;display: none;">
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
                                                    <input type="text" id="ccsl"  placeholder="">
                                                </div>
                                            </div>
                                            <div class="bBottom block">
                                                <div class="form-group formgroup">
                                                    <label>领用数量</label>
                                                    <input type="text" class="checkFloat" style="width: 70px;" id="lysl" placeholder="">


                                                    <input type="hidden" id="ycyid" value=""/>
                                                    <label>备注</label>
                                                    <textarea id="Remark"></textarea>
                                                </div>
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
                            </div>
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
      <%--  </form>--%>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi1.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/plan/pick/newpick.js?v=2"></script>
</body>
</html>



