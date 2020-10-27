<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/7/5
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户角色</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
</head>
<body>
<table class="table tabTop" style="position: fixed;top: 0; right: 0;z-index: 100;">
    <tbody>
    <tr class="biaoqian">
        <td colspan="10" class="td1">
            <div class="btn-group">
                <button class="btn btn-primary " id="confirm">确认</button>
                <button class="btn btn-primary _close">关闭</button>
            </div>
        </td>
    </tr>
    </tbody>
</table>
<div class="f2 clearfix" style="margin-top:30px;">
    <form class="form-inline" id="user">
        <div class="col-md-12 ">
            <div class="nav-tabs-custom">
                <div class="tab-content">
                    <div class="box-body">
                        <select class="form-control selectControl" multiple id="data" name="RoleRd">
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script>
    $(function(){
        //查询所有角色
        $.ajax({
            url:getBasePath() +"/Role/GetAllRoleInfo",
            type:"POST",
            data: {
                "ExecType": "00"
            },
            success: function (data) {
                if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                    for (var i = 0; i < data.Body.Data.length; i++) {
                        $("#data").append("<option value='" + data.Body.Data[i].RoleRd + "'>" + data.Body.Data[i].RoleName + "</option>")
                    }
                }
            }
        })

    var data="";

    $("#data").change(function(){
        var select = [];
        $(this).find("option:checked").each(function () {
            var id=$(this).attr("value");
            var text=$(this).text();
            var datasource={
                "RoleRd":id,
                "RoleName":text
            }
            select.push(datasource);
        })
        data = JSON.stringify(select);
    });

    $("#confirm").click(function(){
        window.sessionStorage.setItem("data",data);
        parent.layer.closeAll()
    });

    })
</script>
</body>
</html>
