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
    <title>设备管理</title>
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
    <div class="btn btn-primary _Save">保存</div>
        <div class="btn btn-primary _close">关闭</div>
</div>

<div class="f2 clearfix">
    <input name="id" id="id" value="${id}"/>
    <div style="margin-left: 10px;margin-top: 20px;" >
        <div class=" formgroup disblock">
            <label for="ysshbh">映射设备编号</label>
            <input class="form-control " type="text"  style="width: 200px;margin-left: 100px;margin-top: -30px;"   id="ysshbh" maxlength="40"/>
        </div>
        <div class=" formgroup disblock">
            <label for="ysshmc">设备映射名称</label>
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
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
<script>
    $(function () {
        var colNamesArr = [
            {"Caption": "映射设备编号", "Name": "DevMapCode"},
            {"Caption": "设备映射名称", "Name": "DevMapName"}
        ];
        var config={
            tableId:"list4",
            data: [],
            colArr:colNamesArr,
            multiselect:true,
            width:0.84,
            height:0.35
        };
        fullTable(config);


        var objBusData = JSON.stringify({"DevRd": $("#id").val()});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/Device/GetDevInfo",data: objData},function(Body){
                if(Body.Data.DevMapInfo.length>0){
                    var config={
                        tableId:"list4",
                        data: Body.Data.DevMapInfo,
                        colArr:colNamesArr,
                        multiselect:true,
                        width:0.84,
                        height:0.35
                    };
                    fullTable(config);
                }
        })





    $("#changeadd").click(function () {
        var DevMapCode=$("#ysshbh").val().trim();
        var DevMapName=$("#ysshmc").val().trim();
        if(DevMapCode==null||DevMapCode==""){
            toastr.warning("映射设备编号不能为空");
            return false;
        }
        if(DevMapName==null||DevMapName==""){
            toastr.warning("映射设备名称不能为空");
            return false;
        }
        var DevMapInfo={
            "DevMapCode":DevMapCode,
            "DevMapName":DevMapName
        };
        addSrow("list4",DevMapInfo);
        $("#ysshbh").val("");
        $("#ysshmc").val("");
    });

    $('.del1').on('click',function(){
        derow('list4');
    });

    $("._Save").click(function () {
    var list4Data= getTableData("list4");
   /* if(list4Data.length<=0){
        toastr.warning("映射设备明细不能为空");
        return false;
    }*/

    var  DevRd=$("#id").val();
    var BusData={
        "DevRd":DevRd,
        "DevMapCode":list4Data
    };


    request({url:"/Device/SaveDevInfo", data: {"ExecType":"04", "busData": JSON.stringify(BusData)}},function(Body){
        parent.layer.closeAll();
        parent.toastr.success("操作成功!");
    });




});



    $("._close").click(function () {
        parent.layer.closeAll();
    });

        // alert($("#id").attr("value"))
    });
</script>
</html>
