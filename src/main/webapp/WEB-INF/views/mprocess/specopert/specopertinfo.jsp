<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/7/2
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>工序操作</title>
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
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/plugins/searchableDropdown/jquery.searchableDropdown.css">

</head>
<body id="aaa">

<div class="btn-group table tabTop common td1">
    <button class="btn btn-primary " id="_query">查询</button>
    <button class="btn btn-primary " id="_sure">确定</button>
    <button class="btn btn-primary " id="_save">保存</button>
    <button class="btn btn-primary " id="_in">进站</button>
    <button class="btn btn-primary " id="_out">出站</button>
    <button class="btn btn-primary " id="_up">上机</button>
    <button class="btn btn-primary " id="_down">下机</button>
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2 zsyLeft1 clearfix lRight">
    <div class="left left1 zsyZi1 left2 leftWidth" style="border-top: 0px;">
            <input type="hidden" id="SpecVerRd"/>
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom" style="margin-left:10px;">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup hang">
                                            <label for="Batch">生产批次</label>
                                            <input type="text" class="form-control mright" id="Batch"
                                                   name="Batch" placeholder="" maxlength="50">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="Num">批次数量</label>
                                            <input type="number" class="form-control mright" id="Num"
                                                   name="Num" placeholder="" style="width: 160px;">
                                            <label id="UnitName" name="UnitName"></label>
                                        </div>
                                        <div class="form-group formgroup hang" id="isDev" style="display: none;">
                                            <label class="">设&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备
                                                <select class="form-control selectControl mright" id="devg" style="width: 196px;">

                                                </select>
                                            </label>
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="MaCode">物料代码</label>
                                            <input type="text" class="form-control mright" id="MaCode" readonly
                                                   name="MaCode" placeholder="">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="MaName">物料名称</label>
                                            <input type="text" class="form-control mright" id="MaName" readonly
                                                   name="MaName" placeholder="">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="MaDes">物料描述</label>
                                            <input type="text" class="form-control mright" id="MaDes" readonly
                                                   name="MaDes" placeholder="">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="WFName">工艺流程</label>
                                            <input type="text" class="form-control mright" id="WFName" readonly
                                                   name="WFName" placeholder="">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="SpecName">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序</label>
                                            <input type="text" class="form-control mright" id="SpecName" readonly
                                                   name="SpecName" placeholder="">
                                        </div>
                                        <div class="form-group formgroup hang" style="display: none;">
                                            <label for="OptName">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业</label>
                                            <input type="text" class="form-control mright" id="OptName" readonly
                                                   name="OptName" placeholder="">
                                        </div>
                                        <!--***************************************工艺指导书***********************************************************-->
                                        <div class="gyzd" >
                                            <label>工艺指导书</label>
                                            <form id="printForm" action="${pageContext.request.contextPath}/Opert/FileViewPG" method="get" target="_blank">
                                                <input type="hidden" id="txtUrl" name="Url"/>
                                                <input type="hidden" id="txtFileName" name="FileName"/>
                                                <ul id="sop" >
                                                </ul>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
    <div class="right1 rightMLeft " style="overflow: auto;height:100%;">
<%--************************************************物料清单和数据采集***************************************************************************--%>

        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom" style="margin-left:10px;margin-top: 10px;">
                    <ul class="nav nav-tabs">
                        <li class="active" id="nav_tab3"><a href="#tab_3" data-toggle="tab" class="dymb">物料消耗</a></li>
                        <li id="nav_tab4"><a href="#tab_4" data-toggle="tab">数据采集</a></li>
                        <li id="nav_tab5"><a href="#tab_5" data-toggle="tab">标记不良</a></li>
                    </ul>
                    <div class="tab-content">

                        <div class="tab-pane active clearfix" id="tab_3">

                            <div class="box-body form-inline">
				<%--<div class="f2_4 form-group">
                                    <input type="text" placeholder="物料代码/批号"  class="form-control" />
                                </div>--%>
                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body tablecontent">
                                                <div class="gridPanel" style="padding-bottom: 0px;">
                                                    <div style="margin-top:-11px;margin-left:6px">
                                                        <span style="background-color:#00CCCC;width:20px;height:20px;display:block;margin-bottom:8px;"></span><span style="float:left;margin-top:-28px;margin-left:25px;">未完成</span>
                                                        <span style="float:left;margin-left:76px;margin-top:-28px;background-color:#CCCC00;width:20px;height:20px;display:block;"></span><span style="float:left;margin-top:-28px;margin-left:103px;">已完成</span>
                                                    </div>
                                                    <table id="list3"></table>
                                                    <div id="pager3"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="tab_4">
                            <div class="box-body form-inline">

                                <div class="f2_4 btn-group">
                                    <input type="button" class="btn btn-primary del1" id="readData" value="读取数据"/>
                                </div>

                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body tablecontent">
                                                <div class="gridPanel" style="padding-bottom: 0px;">
                                                    <table id="list4"></table>
                                                    <div id="pager4"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>

                        <div class="tab-pane" id="tab_5">
                            <div class="box-body form-inline">

                                <select id="cSearch" name="CkResult" style="width: 150px;margin-top: 5px" class="form-control selectControl" >
                                </select>

                                <div class="f2_4 btn-group">
                                    <input type="button" class="btn btn-primary del1" id="deleteReaCode" value="删除"/>
                                </div>

                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body tablecontent">
                                                <div class="gridPanel" style="padding-bottom: 0px;">
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
            </div>
        </div>
<%--********************************************等待进站批次和等待出站批次*****************************************************************************--%>
           <div class="row rightTop">
               <div class="col-md-12">
                   <div class="nav-tabs-custom" style="margin-left:10px;margin-top: 10px;">
                       <ul class="nav nav-tabs">
                           <li class="active" id="nav_tab1"><a href="#tab_1" data-toggle="tab" class="dymb">等待进站批次</a></li>
                           <li id="nav_tab2"><a href="#tab_2" data-toggle="tab">等待出站批次</a></li>
                       </ul>
                       <div class="tab-content">
<%--等待进站批次--%>
                           <div class="tab-pane active clearfix" id="tab_1">
                               <div class="box-body form-inline">
                                   <section>
                                       <div class="row">
                                           <div class="col-md-12">
                                               <div class="box-body tablecontent">
                                                   <div class="gridPanel" style="padding-bottom: 0px;">
                                                       <table id="list1"></table>
                                                       <div id="pager1"></div>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </section>
                               </div>
                           </div>
                           <!-- /.tab-pane -->
    <%--等待出站批次--%>
                           <div class="tab-pane" id="tab_2">
                               <div class="box-body form-inline">
                                   <section>
                                       <div class="row">
                                           <div class="col-md-12">
                                               <div class="box-body tablecontent">
                                                   <div class="gridPanel" style="padding-bottom: 0px;">
                                                       <table id="list2"></table>
                                                       <div id="pager2"></div>
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
       <%--***************************************************结束*****************************************************************************************--%>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/jquery-cookie/jquery.cookie.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/searchableDropdown/jquery.searchableDropdown.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/specopert/specopertinfo.js?v=1'></script>
</body>
</html>