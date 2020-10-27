<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset=UTF-8>
<title>PNC-MES制造执行系统-用户登录</title>
<meta name=name content=content>
<meta http-equiv=X-UA-compatible content="IE=Edge">
<meta name=viwport content="width=divice-width" initial-scale=1>
<link rel=stylesheet type=text/css href=${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.css><link rel=stylesheet type=text/css href=${pageContext.request.contextPath}/static/pnsadmin/Base/css/login.css>
<script type=text/javascript src=${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js></script>
<script src=${pageContext.request.contextPath}/static/ui/global/bootstrap/responsive/html5shiv.min.js></script>
<script src=${pageContext.request.contextPath}/static/ui/global/bootstrap/responsive/respond.min.js></script>
<script type=text/javascript src=${pageContext.request.contextPath}/static/ui/global/layer/layer.js></script>
<%--系统消息--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/toastr.min.js"></script>
<script type=text/javascript src=${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js></script>
<script>window!=top&&(top.location.href=location.href);</script>
<body><div class=content>
    <div class=content-head>
        <h2><%--<img class=logoImg src=${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/logo.png alt="">--%></h2>
    </div>
    <%--<p class=pLogo>smart manufacturing</p>--%>
    <%--<p class=pLogo ><font size="6">用户登录</font></p>--%>
    <div style="text-align: left;margin-left: 40px;">
        <img src="${pageContext.request.contextPath}/static/pnsadmin/Base/images/loginmage.png" style="width: 120px;height: 30px;margin-top: -8px;"><span style="font-size: 35px;color: #01998a;font-weight: bolder;margin-left: 12px;">PNC-MES系统</span>
    </div>

    <form class=form-content role=form accept-charset=utf-8>
        <%--<div class=loginu>
            <p class=pLogo style="margin: 0px; text-align: center;"><font size="6">用户登录</font></p>
            <br />
        </div>--%>
        <div class=loginu><img class=loginUser
                               src=${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/loginUser.png>
            <input name=userName value="${ user.userName }" type="text" class=form-control placeholder=请输入用户名 required
                   autofocus
                   autocomplete="off">
        </div>
        <div class=pwdf><img class=loginLogin
                             src=${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/loginPwd.png>
            <input type=password name=password class=form-control placeholder=请输入密码 required
                   autocomplete="off">
        </div>
        <%--<div class=zE><img class=zEImg
                           src=${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/zy.png><select>
            <option value=01>中文
            <option value=02>English
        </select>
        </div>--%>
        <input class="btn btn-group-lg btn-primary" id=login type=button value="登录">
        <input class="btn btn-group-lg btn-primary" type=reset value="重置">
    </form>
    <div class=content-footer></div>
</div>
<img class=bgImg src=${pageContext.request.contextPath}/static/pnsadmin/Base/images/admin/bgimg.png alt="">
<footer class=main-footer style="color: white;">&copy; 版权所有：驭航信息技术(上海)有限公司
    <%--<span>V0.1</span>--%></footer>
<script>
    $(function () {
        function o() {
            $.ajax({
                url: getBasePath() + "/User/UserLoginInfo",
                type: "post",
                data: $("form").serialize(),
                success: function (o) {
                    if("00" == o.Status && "0x00000" == o.Body.MsgCode) {
                        localStorage.setItem("user", JSON.stringify(o.Body.Data));
                        window.location.href = getBasePath() + "/Main";
                    }else {
                        layer.msg(o.Body.MsgDes);
                    }
                }
            })
        }

        $(document).keydown(function (n) {
            13 == n.keyCode && ($(".loginu input[name='userName']").is(":focus") || $(".pwdf input[name='password']").is(":focus")) && o()
        }), $("#login").click(function () {
            var username=$(".loginu input[name='userName']").val().trim();
            var pwd=$(".pwdf input[name='password']").val().trim();
           if(username==null||username==""||pwd==null||pwd==""){
                layer.msg("用户名或密码不能为空")
                return false;
            }
             o();
        }), $("input").on("focus", function () {
            $("input:-webkit-autofill").css("background-color", "#fff")
        })
    });
</script>