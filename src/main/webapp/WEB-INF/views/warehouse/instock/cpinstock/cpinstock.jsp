<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>成品库存管理</title>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/ui/global/layer/skin/default/layer.css">
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>
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
    </div>--%>
        <button class="btn btn-primary _export">导出</button>
    <button class="btn btn-primary _close">关闭</button>
</div>
<div class="f2  clearfix zsyLeft1">

    <div class="tab-pane active clearfix" id="tab_1">
        <div class="box-body form-inline" style="margin-bottom: 10px;margin-top: 30px;">
            <div class='printTInfo'>

                <div class="form-group formgroup">
                    <label>仓库</label>
                    <div id="cangku" data-id=""></div>
                </div>


                <div class="form-group formgroup">
                    <label>产品</label>
                    <div id="chanpin" data-id=""></div>
                    <label></label>
                </div>

                <div class="form-group formgroup">
                    <label>功率</label>
                    <select id="gonglv" style="width: 150px;">

                    </select>
                </div>

                <div class="form-group formgroup">
                    <label class="layui-form-label" >日期时间范围&nbsp;&nbsp;<input type="text" class="layui-input" id="test10" placeholder=" - " style="width: 280px;"></label>
                </div>

                <div class="form-group "  style="margin-top: 10px;">

                    </select>
                    <label>电流</label>
                    <select id="dianliu" style="width: 150px;">

                    </select>
                    <label style="margin-left: 46px;">颜色</label>
                    <select id="yanse" style="width: 150px;">

                    </select>
                    <label style="margin-left: 50px;">等级</label>
                    <select id="dengji" style="width: 150px;">

                    </select>
                    <label style="margin-left: 46px;">箱号</label>
                    <input type="text" width="width:150px" id="batch"/>
                    <button id="huizongselect">查询</button>
                </div>
            </div>

            <section>
                <div class="row">
                    <div class="col-md-12">
                        <div class="box-body tablecontent">
                            <div>
                                <table id="list6"></table>
                                <div id="pager6"></div>
                            </div>
                            <div>
                                <span>总箱数:</span><label id="total"></label><span style="margin-left: 10px;">总组件数:</span><label id="pretotal"></label>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <div>

</div>
</div>
</body>
<%@include file="/WEB-INF/views/commonJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>

<!--layui时间-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/cpinstock/cpinstock.js?v=1'></script>
</body>
</html>
