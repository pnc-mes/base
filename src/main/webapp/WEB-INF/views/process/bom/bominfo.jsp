<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/30
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport content=" width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>BOM管理</title>

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
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/workflow.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/bom/bominfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/bom/subbom.css">
</head>
<body>
<div class="btn-group table tabTop td1 common">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1"
                                       id="bomChange">BOM变更</a>
            </li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="historyChange">历史变更记录</a>
            </li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="switch">显示全部</a>
            </li>
        </ul>
    </div>
    <div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="import">导入</a>
            </li>
            <li role="presentation"><a class="text-center" role="menuitem" tabindex="-1" id="export">导出</a>
            </li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1">打印</a></li>
        </ul>
    </div>
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 clearfix zsyLeft1">
    <div class="left left1 zsyZi1" style="width:226px;" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索BOM">
        <img class="searchTree"
             src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png"/>
        <!--树的处理-->
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <div id="jstree_demo1"></div>
    </div>
    <input type="hidden" id="hidden" value=""/>
    <input type="hidden" id="ExecType" value=""/>
    <input type="hidden" id="BomRd" name="BomRd" editid=""/>
    <input type="hidden" id="BomVerRd" name="BomVerRd" />
    <div class="bom_right" style="margin-left: 230px;">
        <form id="printForm" style="display: none;">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">BOM信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab_1">
                                <div class="box-body form-inline clearfix width100">
                                    <div class="left50">
                                        <div class="bBottom block">
                                            <div class="form-group ">
                                                <label for="BomCode">BOM&emsp;代&ensp;&nbsp;码</label>
                                                <input type="text" class="form-control" id="BomCode" style="width: 350px;margin: 0px;"
                                                       name="BomCode" placeholder="BOM代码可自动生成" maxlength="30">
                                            </div>
                                        </div>
                                        <div class="bBottom block">
                                            <div class="form-group ">
                                                <label for="BomName">BOM&emsp;名&ensp;&nbsp;称</label>
                                                <input type="text" class="form-control check" id="BomName" style="width: 350px;margin: 0px;"
                                                       name="BomName" placeholder="" maxlength="50">
                                            </div>
                                        </div>
                                        <div class="bBottom block">
                                            <div class="form-group ">
                                                <label for="BomCode">版&emsp;&ensp;本&ensp;&ensp;&ensp;号</label>
                                                <input type="text" class="form-control" id="VersionNo" style="width: 350px;margin: 0px;"
                                                       name="BomCode" maxlength="30">
                                            </div>
                                        </div>
                                        <div class="bBottom block">
                                            <div class="form-group ">
                                                <label for="BomName">BOM&emsp;编&ensp;&nbsp;号</label>
                                                <input type="text" class="form-control" id="BomNo" style="width: 350px;margin: 0px;"
                                                       name="BomName" placeholder="" maxlength="50">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="left50">
                                        <div class="bBottom block">
                                            <div class="form-group ">
                                                <label>状&emsp;&emsp;&nbsp;&emsp;&ensp;&nbsp;态</label>
                                                <select id="Status" name="" style="width: 196px;">
                                                    <option value="00">可用</option>
                                                    <option value="01">不可用</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="bBottom block">
                                            <div class="form-group ">
                                                <label>工程变更单号</label>
                                                <select id="Version"
                                                        style="width: 196px; display: inline-block;"></select>
                                                <input type="text" id="addVersion" name="Version"
                                                       style="display: none;width: 196px;" maxlength="30"/>
                                            </div>
                                        </div>
                                        <div class="bBottom block" style="display: none;" id="reasonDiv">
                                            <div class="form-group " style="position: relative;width: 100%;">
                                                <label style="">工程变更原因</label>
                                                <textarea id="reason"
                                                          style="width: 196px;"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--*********************************tab标签开始***********************************************************************-->
                                <div class="nav-tabs-custom" style="margin-top: 10px;">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">用料清单</a></li>
                                        <li class="" id="tab_4_li" style="position: relative;">
                                            <a href="#tab_4" data-toggle="tab" class=" lr-delete wlxh">物料消耗&emsp;</a>
                                            <i class="fa fa-remove XH" style="position: absolute;top: 2px;right: 4px;"></i>
                                        </li>
                                        <li class="" id="tab_5_li" style="position: relative;">
                                            <a href="#tab_5" data-toggle="tab" class="sztdl">设置替代料&emsp;</a>
                                            <i class="fa fa-remove TDL" style="position: absolute;top: 2px;right: 4px;"></i>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active clearfix" id="tab_3">
                                            <div class="f2_4 btn-group" id="maInfoButton">
                                                <div class="btn btn-primary add1">新增</div>
                                                <div class="btn btn-primary del1">删除</div>
                                                <div class="btn btn-primary substitution">设置替代料</div>
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
                                                <div class="btn btn-primary sure">确认</div>
                                            </div>
                                            <div class="bBottom block">
                                                <div class="form-group ">
                                                    <label>物料名称</label>
                                                    <div id="maName" data-id=""></div>

                                                    <label>工序</label>
                                                    <div id="specName" data-id=""></div>

                                                    <label>消耗方式</label>
                                                    <select id="consume">
                                                        <option value="00">仅显示</option>
                                                        <option value="01">按批次消耗</option>
                                                        <option value="02">按序列号消耗</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="bBottom block">
                                                <label for="BomName">消耗数量
                                                    <input type="number" id="consumeNum"
                                                           style="margin-right: 0;width: 180px;" name="consumeNum"
                                                           placeholder="" maxlength="50">
                                                </label><label class="unit"></label>
                                                <label for="ARate">损耗率
                                                    <input type="text" id="ARate" class="checkFloat" value="0"
                                                           style="margin-right: 0;width: 50px;" name="ARate"
                                                           placeholder="" maxlength="50">%
                                                </label>

                                                <label for="check" class="checkbox" id="d2d" style="width: 120px">
                                                    <input type="checkbox" class="baoliu7" id="check"
                                                           name="">
                                                    检验序列号合法性
                                                </label>
                                            </div>
                                        </div>
                                        <div class="tab-pane clearfix" id="tab_5">
                                            <div class="f2_4 btn-group">
                                                <div class="btn btn-primary sure1">确认</div>
                                            </div>
                                            <div class="f2_4 block" id="tidailiao">
                                                <div class="form-group ">
                                                        <label>替代料</label>
                                                        <div id="replaceMaInfo" data-id="" style="display: none;"></div>

                                                        <span style="display: none;" id="replaceMaInfoselect">
                                                            <%--<select class="selectpicker" multiple data-live-search="true" id="replaceMaInfoselectdatas" data-max-options="1" title="请选择物料">
                                                            </select>--%>
                                                            <select id="replaceMaInfoselectdatas" >

                                                            </select>
                                                        </span>
                                                    <label>&nbsp;&nbsp;消耗数量</label>
                                                    <input type="text" id="xhsl" class="checkFloat" style="width: 50px;"/> <span style="margin-left: -95px;" id="dw"></span>
                                                    <label>&nbsp;&nbsp;损耗率</label>
                                                    <input type="text" id="shl" class="checkFloat" style="width: 50px;"/><span style="margin-left: -95px;">%</span>


                                                    </div>

                                                <div>

                                                </div>
                                            </div>
                                            <div class="btn-group">
                                                <div class="btn btn-primary add2">新增</div>
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
                                <!--*********************************tab标签结束***********************************************************************-->
                            </div>
                            <!-- /.tab-pane -->
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/bom/bominfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
