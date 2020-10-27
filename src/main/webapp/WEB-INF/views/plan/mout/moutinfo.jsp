<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>产品出库通知单管理</title>
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
    <div class="btn btn-primary xd" id="onPick">下达</div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn btn-primary " id="addPick">新增</div>
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
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2  clearfix zsyLeft1">
    <div class="left zsyZi1 left1" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索产品出库通知单">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" id="_right">
        <input type="hidden" id="ExecType"/>
        <form id="pickForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab">产品出库通知单信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class='printTInfo'>
                                        <div class="form-group formgroup">
                                            <label>产品出库通知单号</label>
                                            <input type="text" class="form-control" id="PickCode" name="PickCode" placeholder="系统可自动生成" maxlength="50"/>
                                        </div>
                                        <div class="form-group formgroup">
                                            <label>申请人</label>
                                            <div id="Buyers" data-id=""></div>

                                        </div>
                                        <div class="form-group formgroup" style="margin-left: 60px;">
                                            <label>预出库日期</label>
                                            <input type="date" id="ArrivalTime"/>
                                        </div>
                                    </div>
                                    <%--floor2--%>
                                    <div class=" formgroup mb cpmc mtop " style="margin-left: 83px;" >
                                        <label>用途</label>
                                        <input type="text" id="use"/>
                                        <label style="margin-left: 36px">状态</label>
                                        <input type="text" class="form-control" id="status" name="status"
                                               data-status="00" readonly/>

                                   <label for="shztss" id="shzt" style="margin-left: 55px;">审核状态&nbsp;&nbsp;</label>
                                        <label id="shztss"></label>
                                    </div>
                                </div>


                                <div class="nav-tabs-custom" style="margin-top: 30px;margin-bottom:0px;">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_3" data-toggle="tab">出库明细</a></li>
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
                                                    <label>出库仓库</label>
                                                    <div id="Store" data-id=""></div>
                                                    <label>出库物料</label>
                                                    <div id="lymater" data-id=""></div>
                                                    <label>库存数量</label>
                                                    <input type="text" id="ccsl"  placeholder="">
                                                </div>
                                            </div>
                                            <div class="bBottom block">
                                                <div class="form-group formgroup">
                                                    <label>出库数量</label>
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
        </form>
    </div>

</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/plan/mout/moutinfo.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>

