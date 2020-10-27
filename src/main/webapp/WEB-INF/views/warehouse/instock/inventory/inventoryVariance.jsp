<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>盘点差异</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/mprocess/pack/pack.css">

</head>
<body>
<input id="hidden" type="hidden" value="${PDRd}"/>
<form id="panDianaForm">
    <div class="col-md-12">
        <div class="box-body box box-primary form-inline">
            <div class="printTInfo">
                <h3 class="h3" >盘点差异表</h3>
                <div class="width100">
                    <div class="form-group formgroup">
                        <label>盘点单号</label><label name="danhao"></label>
                    </div>
                    <div class="form-group formgroup">
                        <label>盘点仓库</label><label name="cangku"></label>
                    </div>
                    <div class="form-group formgroup">
                        <label>盘点开始日期:</label><label name="startDate"></label>
                    </div>
                    <div class="form-group formgroup">
                        <label>盘点结束日期:</label><label name="endDate"></label>
                    </div>
                </div>
                <div class="mtop">
                    <div class="form-group formgroup">
                        <label>盘点执行人:</label><label name="controlPeople"></label>
                    </div>
                    <div class="form-group formgroup">
                        <label>盘点差异复核人:</label><label name="reCheckPeople"></label>
                    </div>
                </div>
                <div class="table table-responsive  mtop">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th class="text-center" rowspan="2" colspan="1">物料代码</th>
                            <th class="text-center" rowspan="2" colspan="1">物料名称</th>
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
                        <tbody id="content">
                        <tr>
                            <td>1000001</td>
                            <td>测试物料</td>
                            <td>仓库1</td>
                            <td>001</td>
                            <td>12</td>
                            <td>仓库1</td>
                            <td>001</td>
                            <td>1</td>
                            <td>个</td>
                            <td>移动</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

<%@include file="/WEB-INF/views/commonJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/instock/inventory/inventoryvariance.js?v=1'></script>

</body>
</html>
