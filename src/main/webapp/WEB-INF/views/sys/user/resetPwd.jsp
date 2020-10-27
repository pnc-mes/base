<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/7/5
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
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
                <%--<button class="btn btn-primary ">关闭</button>--%>
            </div>
        </td>
    </tr>
    </tbody>
</table>
<div class="f2 clearfix" style="margin-top:30px;">
    <form class="form-inline">
        <input type="hidden" id="hidden" value="${UserRd}"/>
        <div class="col-md-12 ">
            <div class="nav-tabs-custom">
                <div class="tab-content">
                    <div class="box-body">
                        <input type="password" class="form-control" id="rePwd" name="" placeholder="请输入新密码" maxlength="30">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@ include file="/WEB-INF/views/commonJs.jspf" %>
<script>
    $(function(){

        $("#confirm").click(function () {
            var rePwd=$("#rePwd").val().trim();
            var Hidden=$("#hidden").val();
            if(rePwd==""){
                parent.toastr.warning("密码不能为空");
                return false;
            }
            var objData={
                "UserRd":Hidden,
                "Password":rePwd
            }
            $.ajax({
                url: getBasePath()+"/User/SaveUserInfo",
                type: "POST",
                async: false,//设为同步请求
                data: {
                    "ExecType":"03",
                    "BusData":JSON.stringify(objData)
                },
                success: function (res) {

                    if(res.Status =="00" && res.Body.MsgCode == "0x00000"){
                        parent.layer.closeAll();
                        parent.toastr.success("修改成功");
                    }
                }
            });
        });



    });
</script>
</body>
</html>
