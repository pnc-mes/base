<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>产品批次创建</title>
    <%--用户不能手动缩放/允许用户缩放到最大比例（默认为1）--%>
    <meta content="width=device-width, initial-scale=1 maximum-scale=1,user-scalable=no" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css"
          rel="stylesheet"/>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/plugins/datepicker/datepicker3.css">
</head>

<body>
<div class="btn-group able tabTop td1 common wi">
    <button  class="btn btn-primary cSave">保存</button>
    <button  class="btn btn-primary _close">关闭</button>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix" style="margin-top:30px;margin-left: 10px;overflow-y: auto;position: absolute;height: 100%;">
    <form class="form-inline">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom" style="margin-bottom: 0px;">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">批次信息</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active clearfix" id="tab_1">

                            <div class="box-body mbottom">
                                <div class="printTInfo">
                                    <div class="form-group formgroup">
                                        <div class="form-group formgroup xinx">
                                            <div class="cpxx ">工单信息</div>
                                            <div class="form-group width30">
                                                <label>工&ensp;单&ensp;号</label>
                                                <div id="WoCode"></div>
                                            </div>
                                            <div class="form-group width30">
                                                <label >数&emsp;&emsp;量
                                                    <input type="text" class="form-control" readonly id="WoCount"
                                                           placeholder="">
                                                </label>
                                            </div>
                                            <div class="form-group width30">
                                                <label >未创批次数量
                                                    <input type="text" class="form-control" readonly id="UnCBatNum"
                                                           placeholder="" style="margin-right: 0;width: 90px;">
                                                    自动生成批次
                                                    <input type="checkbox" id="IsABatch" checked />
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group formgroup xinx mtop">
                                            <div class="cpxx ">产品信息</div>
                                            <div class="form-group width30">
                                                <label>产&emsp;&emsp;品
                                                    <input type="text" class="form-control" readonly id="MaVer"/>
                                                </label>
                                            </div>
                                            <div class="form-group width30">
                                                <label for="WFVer">工艺流程
                                                    <input type="text" class="form-control" id="WfVer" readonly
                                                           placeholder="">
                                                </label>
                                            </div>
                                            <div class="form-group width30">
                                                <label>开&nbsp;&nbsp;始&nbsp;&nbsp;工&nbsp;&nbsp;&nbsp;序</label>
                                                <div id="SpecVer"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group mtop" style="display: block;">
                                        <div class="cpxx">生产信息</div>
                                        <div class="scxx">
                                            <label class="mright3">拆&nbsp;&nbsp;&nbsp;成
                                                <input type="number" class="form-control " id="AllNum" placeholder="">批
                                            </label>
                                            <label class="mright3 rL1">&nbsp;&nbsp;每批
                                                <input type="number" class="form-control" id="Num" placeholder="">
                                                <span id="Unit"></span>&nbsp;&nbsp;
                                            </label>
                                            <label class="rL11">计划开始日期
                                                <input type="text" class="form-control datainp wicon" id="startDate" data-date-format="yyyy-mm-dd">
                                            </label>
                                            <label class="mright5">计划完工日期
                                                <input type="text" class="form-control datainp wicon" id="finishDate" >
                                            </label>
                                        </div>
                                        <div class="cpxx mtop">打印信息</div>
                                        <div class="xinx">
                                            <div class="form-group width30">
                                                <label>打印模板</label>
                                                <div id="PrintT" data-id=""></div>
                                            </div>
                                            <div class="form-group width30">
                                                <label>打印机</label>
                                                <div id="Print"></div>
                                            </div>
                                        </div>
                                       <%-- <label for="ifPackage" class="checkbox rL3">
                                            <input type="checkbox" id="ifPackage">是否打印包裹批次
                                        </label>--%>
                                    </div>
                                </div>
                            </div>


                            <!--*********************************Tab关联批次**************************************-->
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">关联批次</a></li>
                                    <li class="" id="tab_4_li" style="position: relative; display: none;">
                                        <a href="#tab_4" data-toggle="tab" class=" lr-delete wlxh">手动输入批次&emsp;</a>
                                        <i class="fa fa-remove XH" style="position: absolute;top: 2px;right: 4px;"></i>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active clearfix" id="tab_3">
                                        <section>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="box-body tablecontent">
                                                        <div class="gridPanel">
                                                            <table id="list5"></table>
                                                            <div id="pager5"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>
                                    </div>
                                    <div class="tab-pane clearfix" id="tab_4">
                                        <br/>
                                        <div class="btn btn-primary cAdd">新增</div>
                                        <div class="btn btn-primary cDel">删除</div>
                                        <section>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="box-body tablecontent">
                                                        <div class="gridPanel">
                                                            <table id="list6"></table>
                                                            <div id="pager6"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </section>
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
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/bootstrap-datepicker.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/plan/batch/batchwaddyougd.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>

</html>
