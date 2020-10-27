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
<form action="" id="form">
    <div style="margin-top: 30px;">
        <input type="hidden" name="ExceType" id="ExceType"/>
        <div class="form-group  col-xs-10">
            <label for="username" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-26px;">用户名</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" id="username" name="UserName" value="${name }" placeholder="用户名" readonly>
            </div>
            <p class="col-xs-7 col-sm-offset-5 p1"></p>
        </div>
       <%-- <div class="form-group  col-xs-10">
            <label for="oldPwd" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-25px;">原始密码</label>
            <div class="col-xs-8">
                <input type="password" class="form-control" id='oldPwd' name="OldPassword" placeholder="">
            </div>
            <p class="col-xs-7 col-sm-offset-5 p2"></p>
        </div>--%>
        <div class="form-group  col-xs-10">
            <input type="hidden" class="hidden" name="guid" value="${id}"/>
            <label for="newPwd" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-25px;">新密码</label>
            <div class="col-xs-8">
                <input type="password" id="newPwd" class="form-control" name="passWord" value="" placeholder="">
            </div>
            <p class="col-xs-7 col-sm-offset-5 p3"></p>
        </div>


        <%--<div class="form-group  col-xs-10">
            <label for="reNewPwd" class="col-xs-3 control-label" style="margin-top: 8px; margin-right:-24px;">确认密码</label>
            <div class="col-xs-8">
                <input type="password" id="reNewPwd" class="form-control" name="reNewPwd" placeholder="">
            </div>
            <p class="col-xs-7 col-sm-offset-5 p4"></p>
        </div>--%>
        <div style="padding-left: 400px;padding-top: 100px;">
           <input type="button" id="confirm" a="" value="确定"/>
      </div>
        <div style="padding-left: 450px;margin-top: -26px;">
            <input type="reset" value="重置"/>
        </div>
        <div style="padding-left: 300px;margin-top: -20px;">
                <span style="color: red;" id="msg"></span>
        </div>

    </div>
    <input type="hidden" name="UserRd" value="${ user.ruid }" />

</form>
<!-- jQuery 2.2.3 -->
<script src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
</body>
<script>
    $(function(){
        $("#confirm").click(function(){
            $("#ExceType").val("03#");
            var a=$(".hidden").val();
            var pwd=$("#newPwd").val().trim();
            if(pwd==""||pwd==null){
                    $("#msg").text("不能为空");
            }else {
                $.ajax({
                    url:"SaveUserInfoRequest",
                    type:"POST",
                    data : $('#form').serialize(),
                    success:function (data) {
                        $("#msg").text(data);
                        setTimeout(function(){
                            $("#msg").text("");
                        },1500)
                    }
                })
            }
        });

    });
</script>
</html>

