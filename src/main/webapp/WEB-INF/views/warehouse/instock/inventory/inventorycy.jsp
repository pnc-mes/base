<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/6/29
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>盘点差异</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/company/companyinfo.css">
    <style>
        .martop {
            margin-top: 30px;
        }

        .leftWidth, .rightWidth {
            float: left;
            width: 50%;
        }
        .pdjg .nav-tabs-custom{
            padding-left:10px;
        }
    </style>
</head>
<body>
<div class="btn-group table tabTop aa td1">
    <button class="btn btn-primary" id="cSave">保存</button>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" id="export"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
            <li role="presentation" id="print"><a class="text-center" role="menuitem" tabindex="-1">打印</a></li>
        </ul>
    </div>
    <button class="btn btn-primary cLose">关闭</button>
</div>
<!--*********************************处理带有CheckBox的表格***************************************************************************-->

<section class="content martop leftWidth">
    <div class="row">
        <div class="col-md-12 pdjg">
            <div class="box-body tablecontent">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_4" data-toggle="tab" class="dymb">盘点结果</a></li>
                    </ul>
                    <div class="gridPanel">
                        <input id="hidden" type="hidden" value="${PDRd}"/>
                        <table id="list4"></table>
                        <div id="pager4"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="content martop rightWidth">
    <div class="row">
        <div class="col-md-12">
            <div class="box-body tablecontent">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">库存</a></li>
                    </ul>
                    <div class="gridPanel">
                        <table id="list5"></table>
                        <div id="pager5"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<form id="panDianaForm" style="display: none;"><%--style="display: none;"--%>
    <div class="col-md-12" >
        <div class="box-body form-inline">
            <div class="printTInfo">
                <h3 class="h3" >盘点差异表</h3>
                <div class="width100">
                        <label>盘点单号：</label><label class="mright" name="danhao" id="PDCode"></label>
                        <label>盘点仓库：</label><label class="mright" name="cangku" id="StoreName"></label>
                        <label>盘点开始日期：</label><label class="mright" name="startDate" id="StartTime"></label>
                        <label>盘点结束日期：</label><label class="mright" name="endDate" id="FinishTime"></label>
                </div>
                <div class="mtop">
                        <label>盘点执行人：</label><label class="mright" name="controlPeople" id="Execor"></label>
                        <label>盘点差异复核人：</label><label class="mright" name="reCheckPeople" id="ExecTime"></label>
                </div>
                <div class="table table-responsive  mtop">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th class="text-center" rowspan="2" colspan="1">物料代码</th>
                            <th class="text-center" rowspan="2" colspan="1">物料名称</th>
                            <th class="text-center" rowspan="2" colspan="1">物料描述</th>
                            <th class="text-center" colspan="3">变更前</th>
                            <th class="text-center" colspan="3">变更后</th>
                            <th class="text-center" rowspan="2">单位</th>
                            <th class="text-center" rowspan="2">变更原因</th>
                        </tr>
                        <tr>
                            <th class="text-center">仓库</th>
                            <th class="text-center">库位</th>
                            <th class="text-center">数量</th>
                            <th class="text-center">仓库</th>
                            <th class="text-center">库位</th>
                            <th class="text-center">数量</th>
                        </tr>
                        </thead>
                        <tbody id="CyContent">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<%@ include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/instock/inventory/inventorycy.js?v=1'></script>
</body>
</html>
