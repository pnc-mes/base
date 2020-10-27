
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>创建来料收货通知单</title>
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
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn btn-primary " id="addOn">下达</div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn btn-primary " id="addPurch">新增</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a   class="text-center " id="delete" role="menuitem" tabindex="-1">删除</a>
            </li>
            <li role="presentation"><a  class="text-center " id="addOff" role="menuitem" tabindex="-1">取消</a>
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
<div class="f2  clearfix zsyLeft1">
    <div class="left zsyZi1 left1" id="left2" style="height: 95.5%">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索收料通知单">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div> <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div  class="right" style="height: 93%" id="_right">
    <form id="purchForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="form-inline">
                    <%--floor1--%>
                    <div class="printTInfo">
                        <div class="nav-tabs-custom"  style="margin-bottom: 0px;">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#tab_1" data-toggle="tab">来料通知单信息</a></li>
                                <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active clearfix" id="tab_1">
                                    <div class="box-body  form-inline">
                                        <div class='f1'>
                                            <div class="form-group formgroup ">
                                                <label>来料通知单号</label>
                                                <input type="text" class="form-control" id="InCCode" name="InCCode"
                                                       placeholder="单号可自动生成" maxlength="50">
                                            </div>
                                            <div class="form-group formgroup">
                                                <label>来源单据</label>
                                                <select id="SourceType" style="width: 80px; margin-right: 20px;">
                                                    <option value="00">采购单</option>
                                                    <option value="01">自建</option>
                                                </select>
                                            </div>
                                            <div class="form-group formgroup" id="defaultSelect_">
                                                <label class="">采购单号</label>
                                                <div id="defaultSelect" data-id=""></div>
                                            </div>
                                        </div>
                                        <div class="f1">
                                            <div class="form-group formgroup" style="margin-right: 50px;">
                                                <label for="status" id="stat">执行状态</label>
                                                <label id="status"></label>
                                            </div>
                                            <div class="form-group formgroup">
                                                <label for="sStatus" id="sStat">审核状态</label>
                                                <label id="sStatus"></label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="nav-tabs-custom" style="margin-top: 30px;margin-bottom:0px;">
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a href="#tab_3" data-toggle="tab">来料收货</a></li>
                                            <li class="" id="tab_4_li" style="position: relative;">
                                                <a href="#tab_4" data-toggle="tab" class=" lr-delete wlxh">明细设置&emsp;</a>
                                                <i class="fa fa-remove XH" style="position: absolute;top: 2px;right: 4px;"></i>
                                            </li>
                                            <li class="" id="tab_5_li" style="position: relative;">
                                                <a href="#tab_5" data-toggle="tab" class=" lr-delete wlxh">明细设置&emsp;</a>
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
                                                    <!--处理表格-->
                                                    <div class="boxx">
                                                        <section class="clearfix">
                                                            <div class=" tablecontent">
                                                                <div class="gridPanel">
                                                                    <table id="list4"></table>
                                                                    <div id="pager4"></div>
                                                                </div>
                                                            </div>
                                                        </section>
                                                    </div>
                                                </section>
                                            </div>
                                            <div class="tab-pane active clearfix" id="tab_4">
                                                <div class="f2_4 btn-group" id="maInfoButton_1">
                                                    <div class="btn btn-primary" id="add_1">确认</div>
                                                </div>
                                                <section>
                                                    <div class='f1'>
                                                        <div class="form-group formgroup ">
                                                            <label>收货物料</label>
                                                            <div id="maName_1" data-id=""></div>

                                                            <label>收料仓库</label>
                                                            <div id="storeName" data-id=""></div>

                                                            <label>收货总量</label>
                                                            <input type="text" id="allNum_1" style="width: 70px;" disabled />
                                                        </div>
                                                    </div>
                                                    <div class='f1'>
                                                        <div class="form-group formgroup ">
                                                            <label>已收货量</label>
                                                            <input type="text" id="totalScanNum" style="width: 70px;" disabled />

                                                            <label>未开单量</label>
                                                            <input type="text" id="unCInCNum_1" style="width: 70px;" disabled />

                                                            <label>本次预收货数量</label>
                                                            <input type="number" id="num_1" style="width: 70px;"  class="checkNumber" maxlength="12" />
                                                            <label style="margin-left: -18px;">个</label>
                                                        </div>
                                                    </div>
                                                    <div class='f1'>
                                                        <div class="form-group formgroup ">
                                                            <label>本次已收数量</label>
                                                            <input type="text" id="scanNum_1" style="width: 70px;" disabled />
                                                            <label>备注</label>
                                                            <textarea id="remark_1"></textarea>
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>
                                            <div class="tab-pane active clearfix" id="tab_5">
                                                <div class="f2_4 btn-group" id="maInfoButton_2">
                                                    <div class="btn btn-primary" id="add_2">确认</div>
                                                </div>
                                                <section>
                                                    <div class='f1'>
                                                        <div class="form-group formgroup ">
                                                            <label>收货物料</label>
                                                            <div id="maName_2" data-id=""></div>

                                                            <%--<label>甲供料</label>
                                                            <select style="width: 70px;" id="afeed_">
                                                                <option value="00">是</option>
                                                                <option value="01">否</option>
                                                            </select>--%>

                                                            <label>收料仓库</label>
                                                            <div id="storeName_1" data-id=""></div>

                                                            <label>本次预收货数量</label>
                                                            <input type="text" id="num_2" style="width: 70px;" />
                                                            <label style="margin-left: -18px;">个</label>
                                                        </div>
                                                    </div>
                                                    <div class='f1'>
                                                        <div class="form-group formgroup ">
                                                            <label>本次已收数量</label>
                                                            <input type="text" id="scanNum_2" style="width: 70px;"  disabled />
                                                            <label>备注</label>
                                                            <textarea id="remark_2"></textarea>
                                                            <input type="hidden" id="unitName" />
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
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/plan/incadd/incadd.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>


