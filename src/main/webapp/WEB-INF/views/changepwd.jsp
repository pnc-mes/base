<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.css">
    <style type="text/css">
        label {
            text-align: center;
        }
        p {
            margin-left: 44%;
            color: red;
            font-size: 12px;
        }
        .form-group{
            margin-left:42px;
            margin-bottom: 1px;
        }
    </style>
</head>

<body>
<form id="changePWD" action="">
    <div style="margin-top: 30px;">
        <div class="form-group  col-xs-10">
            <label for="username" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-26px;">用户名</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="username" name="UserName" value="${ user.userName }" placeholder="用户名" readonly>
            </div>
            <p class="col-xs-7 col-sm-offset-5 p1"></p>
        </div>
        <div class="form-group  col-xs-10">
            <label for="oldPwd" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-25px;">原始密码</label>
            <div class="col-xs-8">
                <input type="password" class="form-control" id='oldPwd' name="OldPassword" onblur="if($(this).val().trim() == ''){$('.p2').text('不能为空')}else{$('.p2').text('')}" placeholder="">
            </div>
            <p class="col-xs-7 col-sm-offset-5 p2"></p>
        </div>
        <div class="form-group  col-xs-10">
            <label for="newPwd" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-25px;">新密码</label>
            <div class="col-xs-8">
                <input type="password" id="newPwd" class="form-control" name="NewPassword" onblur="if($(this).val().trim() == ''){$('.p3').text('不能为空')}else{$('.p3').text('')}" value="" placeholder="">
            </div>
            <p class="col-xs-7 col-sm-offset-5 p3"></p>
        </div>
        <div class="form-group  col-xs-10">
            <label for="reNewPwd" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-24px;">确认密码</label>
            <div class="col-xs-8">
                <input type="password" id="reNewPwd" class="form-control" name="reNewPwd" onblur="if($(this).val().trim() == ''){$('.p4').text('不能为空');return;}if($(this).val().trim() != $('#newPwd').val().trim()){$('.p4').text('两次密码输入不一致')}else{$('.p4').text('')}" placeholder="">
            </div>
            <p class="col-xs-7 col-sm-offset-5 p4"></p>
        </div>
    </div>
    <input type="hidden" name="UserRd" value="${ user.ruid }" />
</form>
<!-- jQuery 2.2.3 -->
<script src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js"></script>
<script>
    function getFormData() {
        return $("#changePWD").serialize();
    }
</script>
</body>

</html>

