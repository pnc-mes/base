<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
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
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/qa/IQC/iqcinfo.css">

</head>
<body>
<div class="btn-group table  common td1">
    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
                id="option1">操作
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" id="addIQC"><a href="#" class="text-center" role="menuitem" tabindex="-1">新增</a></li>
        </ul>
    </div>
    <button class="btn btn-primary " id="cSave">保存</button>
   <!-- <button class="btn btn-primary _close">关闭</button>-->
</div>
<div class="f2 clearfix">
    <div class="left left1 left2 left3">
        <form id="iqcAddForm">
            <input type="hidden" id="hidden" value=""/>
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom" style="margin-left:10px;">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <input class="input1 form-control" type="search"  id="bSearch" name="" value="" placeholder="请输入批次号">
                                        <div class="form-group formgroup hang">
                                            <label for="MaCode">物料代码</label>
                                            <input type="text" class="form-control mright" id="MaCode" readonly
                                                   name="MaCode">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="MaName">物料名称</label>
                                            <input type="text" class="form-control mright" id="MaName" readonly
                                                   name="MaName">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label for="Num">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量</label>
                                            <input type="text" class="form-control mright" id="Num" readonly
                                                   name="Num">
                                        </div>
                                        <div class="form-group formgroup hang">
                                            <label class="">检测结果
                                                <select class="form-control selectControl mright" id="CkResult"
                                                        name="CkResult">
                                                    <option value="00">合格</option>
                                                    <option value="01">不合格</option>
                                                    <option value="02">降级使用</option>
                                                </select>
                                            </label>
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
    <div class=" right1 " style="width: 74%;margin-top:30px;">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">不良/降级代码</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_3">
                            <div class="boxx">
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="mright3"></label>
                                    <div id="defaultSelect" data-id="" readonly></div>
                                </div>
                                <div class="f2_4 btn-group">
                                    <input type="button" class="btn btn-primary del1" id="del1" disabled
                                           value="删除"></input>
                                </div>
                                <section class="content">
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
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/qa/iqc/iqcAdd.js?v=1'></script>
</body>
</html>
