<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/7/1
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>载具信息</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet"/>
    <!-- 树开始 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>
<body>
<div class="btn-group common table tabTop td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
    <%-- 引入页面中的系统信息--%>
 <%--   <%@include file="/WEB-INF/views/sysInfo.jspf"%>--%>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class='zsyLeft'>
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索载具">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right"  style="display: none;">
        <form id="devform">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">基本信息</a></li>
                            <li id="sbtxx"><a href="#tab_4" data-toggle="tab">载具标准值</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class=" formgroup disblock">
                                            <label for="CarrierName">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称&nbsp;&nbsp;</label>
                                            <input type="text" class="form-control check" id="CarrierName"
                                                   name="CarrierName" placeholder="" maxlength="40">
                                           <%-- <input type="text" class="form-control check" id="CarrierName"
                                                   name="CarrierName" placeholder="">--%>

                                            <label class="">供&nbsp;&nbsp;应&nbsp;&nbsp;商&nbsp;&nbsp;</label>
                                            <div id="defaultSelect" data-id="" ></div>

                                        </div>
                                       <div class=" formgroup disblock">
                                           <label for="SN" >序&nbsp;&nbsp;列&nbsp;&nbsp;号&nbsp;&nbsp;</label>
                                           <input type="text" class="form-control"  id="SN"
                                                  name="SN" >
                                          <%-- <input type="text" class="form-control check" id="SN"
                                                  name="SN" >--%>
                                           <label class="" >状态模型&nbsp;&nbsp;</label>
                                           <div id="defaultSelect3"  data-id="" ></div>
                                        </div>
                                        <div class=" formgroup disblock">
                                            <label class="">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;厂&nbsp;&nbsp;</label>
                                            <div id="defaultSelect2" data-id="" ></div>
                                            <label class="" style="margin-left: 9%">载具家族&nbsp;&nbsp;</label>
                                            <div id="defaultSelect1" data-id="" ></div>


                                            <%----%>

                                        </div>
                                        <div class=" formgroup disblock">
                                                <label class="">保养计划&nbsp;&nbsp;</label>
                                            <select class="selectpicker" multiple data-live-search="true" id="byjh" title="请选择保养计划">
                                                <optgroup label="周期性保养计划" id="zqxbyjh"></optgroup>
                                                <optgroup label="次数保养计划" id="csbyjh"></optgroup>
                                            </select>
                                            <label class="" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;点检计划</label>
                                            <select class="selectpicker" multiple data-live-search="true" id="jyffSelect" title="请选择点检计划">
                                                <optgroup label="点检计划" id="djjh"></optgroup>
                                            </select>
                                        </div>


                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
                            </div>


                            <%--     <!--载具标准值-->
                          </div>--%>
                            <div class="tab-pane" id="tab_4">
                                <div class="f2 clearfix">
                                    <%-- <input name="id" id="id" value="${id}"/>--%>
                                    <div style="margin-left: 10px;margin-top: 20px;" >

                                        <div class=" formgroup disblock">
                                            <label style="padding-left: 21px;">&ensp;参考信息&ensp;</label>
                                            <div style="margin-top: -23px;padding-left: 96px">
                                                <textarea style="width: 330px;  height: 80px;margin-top: -7px;"
                                                          id="Reference" class="Reference" name="Reference"></textarea>
                                            </div>
                                        </div>

                                        <div class=" formgroup disblock">
                                            <label for="ysshbh">标准值名称</label>
                                            <input class="form-control " type="text"  style="width: 200px;margin-left: 100px;margin-top: -30px;"   id="ysshbh" maxlength="40"/>
                                        </div>
                                        <div class=" formgroup disblock">
                                            <label for="ysshmc">标准值</label>
                                            <input class="form-control " type="text"  style="width: 200px;margin-left: 100px;margin-top: -30px;"  id="ysshmc" maxlength="40"/>
                                        </div>
                                        <div class=" formgroup disblock" style="margin-left: 150px;">
                                            <button type="button" class="btn btn-primary" id="changeadd"
                                                    data-toggle="button"> 新增
                                            </button>
                                        </div>

                                        <div class="content clearfix">
                                            <div class="nav-tabs-custom">

                                                <div class="f2_4 btn-group">
                                                    <%-- <div class="btn btn-primary add1">新增</div>--%>
                                                    <div class="btn btn-primary del1">删除</div>
                                                </div>
                                                <section >
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/res/carrier/carrierinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>

</html>
