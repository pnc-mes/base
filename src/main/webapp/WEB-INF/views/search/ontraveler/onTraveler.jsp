<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/6/29
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>在线追踪</title>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/mprocess/specOpert/specOpert.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/mprocess/ontraveler/ontraveler.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group td1 table tabTop zaixianzz">
    <button class="btn btn-primary ocheck">查询</button>
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 clearfix">
    <div class="left left1 left2 zsyLeft1">
        <form id="printForm" class="zsyZi1">
            <div class="row rightTop">
                <div class="col-md-12 _height">
                    <div class="nav-tabs-custom aa" style="margin-bottom: 0px;">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix">
                                <div class="box-body form-inline bb">
                                    <div class="printTInfo">
                                        <div class="form-group  hang">
                                            <label class="mright3">批&emsp;次&emsp;号
                                                <input type="text" class="form-control" id="batch"/>
                                            </label>
                                        </div>
                                        <div class="form-group  hang">
                                            <label for="q2">来源批次号</label>
                                            <input type="text" class="form-control mright" id="q2" readonly
                                                   name="ptName">
                                        </div>
                                        <%--<div class="form-group  hang">
                                            <label for="q3">生&nbsp;产&nbsp;数&nbsp;量</label>
                                            <input type="text" class="form-control mright" id="q3" readonly
                                                   name="ptName">
                                        </div>--%>
                                    </div>
                                </div>
                            </div>
                            <!--***************************************拆分后批次***********************************************************-->
                            <div class="gyzd">
                                <div class="gridPanel" style="padding-bottom: 0px;">
                                    <table id="list9"></table>
                                    <div id="pager9"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class=" right1 rightTopp cc">
        <div class="row rightTop">
            <div class="col-md-12 _height">
                <div class="nav-tabs-custom kuan">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" >批次信息</a></li>
                        <li><a href="#tab_5" data-toggle="tab" >事务操作</a></li>
                        <li><a href="#tab_2" data-toggle="tab">消耗物料</a></li>
                        <li><a href="#tab_3" data-toggle="tab">采集数据</a></li>
                        <li><a href="#tab_4" data-toggle="tab">不良原因</a></li>
                        <li><a href="#tab_9" data-toggle="tab">工序异常</a></li>
                        <li><a href="#tab_10" data-toggle="tab">后道信息</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active clearfix" id="tab_1">
                            <!--**************批次信息处理**************-->
                            <div id="lefttt" class='mtop' >
                                <div class="form-group ">
                                    <label class="mright3">批&emsp;&emsp;次
                                        <input class="form-control" type="text" id="batch1" readonly/>
                                    </label>
                                </div>
                                <div class="form-group ">
                                    <label class="mright3">线体名称
                                        <input class="form-control" type="text" id="LineName" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">物料代码
                                        <input class="form-control" type="text" id="materCode" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">物料名称
                                        <input class="form-control" type="text" id="materName" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">物料类型
                                        <input class="form-control" type="text" id="materType" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">数&emsp;&emsp;量
                                        <input class="form-control" type="text" id="num" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">工&ensp;单&ensp;号
                                        <input class="form-control" type="text" id="wordCard" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">工艺流程
                                        <input class="form-control" type="text" id="workFlow" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">BOM信息
                                        <input class="form-control" type="text" id="bomInfo" readonly/>
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label class="mright3">批次状态
                                        <input class="form-control" type="text" id="batchStatus" readonly/>
                                    </label>
                                </div>
                            </div>
                            <div class='mtop' id="righttt" >
                                <div class="form-group">
                                    <label class="mright3">是否包装
                                        <input class="form-control"  type="text" id="package" readonly/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane clearfix" id="tab_5">
                            <!--**************事务操作的表格**************-->
                            <section class="content mtop">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="tablecontent">
                                            <div class="gridPanel">
                                                <table id="list6"></table>
                                                <div id="pager6"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="tab_2">
                            <section class="content mtop">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="tablecontent">
                                            <div class="gridPanel">
                                                <table id="list7"></table>
                                                <div id="pager7"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="tab-pane" id="tab_3">
                            <section class="content mtop">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="tablecontent">
                                            <div class="gridPanel">
                                                <table id="list8"></table>
                                                <div id="pager8"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="tab-pane" id="tab_4">
                            <section class="content mtop">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="tablecontent">
                                            <div class="gridPanel">
                                                <table id="list11"></table>
                                                <div id="pager11"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="tab-pane" id="tab_9">
                            <section class="content mtop">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="tablecontent">
                                            <div class="gridPanel">
                                                <table id="list12"></table>
                                                <div id="pager12"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="tab-pane" id="tab_10">
                            <section class="content mtop">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="tablecontent">
                                            <div class="gridPanel">
                                                <table id="list13"></table>
                                                <div id="pager13"></div>
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
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/ontraveler/onTraveler.js?v=1"></script>
</body>
</html>
