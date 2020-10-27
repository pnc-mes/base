<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>useEdit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet" />
    <style type="text/css">
        td {
            cursor: pointer;
        }

        .a {
            /*background: #f5f5f5;*/
        }

        .b {
            display: none;
        }

        input {
            background: #f5f5f5;
            outline: none;
            border: none;
            border: 1px solid #DDDDDD;
        }

        td ,th{
            outline: none;
        }

        li {
            width: 50%;
        }

        .nav-tabs>li>a {
            margin-right: -1px;
        }
    </style>
</head>
<body>
<form id="form">
    <input type="hidden" name="ExceType" id="ExceType"/>
    <input type="hidden" name="guid" value="${guid}"/>　
<table class="" cellspacing="50%" cellpadding="80" style="margin-left: 20px;">
    <tbody>
    <tr>
        <td colspan="4" style="line-height: 50px;padding-bottom: 30px;">
            <input type="button" class="btn btn-sm" id="updata" value="保存"/>
            <input type="button" class="btn btn-sm" id="" value="新增"/>
            <input type="button" class="btn btn-sm" id="" value="关闭"/>
            <span id="msg" style="color: red;"></span>
        </td>
    </tr>
    <tr>
        <td colspan="8">
            <ul class="nav nav-tabs">
                <li class="active text-center c" id="userinfo"><a href="#tab_1" data-toggle="tab" aria-expanded="true">用户信息</a></li>
                <li class="text-center d" id="other"><a href="#tab_2" data-toggle="tab" aria-expanded="false">其他</a></li>
            </ul>
        </td>
    </tr>
    <tr class="a">
        <td style="padding:0 20px;border-left: 1px solid #DDDDDD;">用户名称</td>
        <td style="padding:30px 0;">
            <input type="text" name="userName" value="${userInfo.userName}">
        </td>
    </tr>
    <tr class="a">
        <td style="padding:0 20px 30px 20px;border-left: 1px solid #DDDDDD;border-bottom: 1px solid #DDDDDD;">真实姓名</td>
        <td colspan="3" style="padding-bottom:30px;border-right: 1px solid #DDDDDD;">
            <input type="text" name="realName" value="${userInfo.realName}" placeholder="">
        </td>
    </tr>
    <tr class="b">
        <td style="padding:0 20px;border-left: 1px solid #DDDDDD;">修改人</td>
        <td>
            <input type="text" name="lastModifyMan" value="" placeholder="">
        </td>
        <td style="padding:0 20px;border-right: 1px solid #DDDDDD;">修改时间</td>
        <td style="border-right: 1px solid #DDDDDD; ">
            <input type="text" name="lastModifyTime" value="${userInfo.lastModifyTime}" placeholder="">
        </td>
    </tr>
    <tr class="b">
        <td style="padding:0 20px;border-left: 1px solid #DDDDDD;border-bottom: 1px solid #DDDDDD;">备注</td>
        <td style="padding:20px 20px 20px 0;border-right: 1px solid #DDDDDD;" colspan="3">
            <textarea name="remark" value="${userInfo.remark}" ></textarea>
        </td>
    </tr>
    <tr style="border:none;">
        <td colspan="4" style="line-height: 50px;border:none;">&nbsp;</td>
    </tr>
    <tr>
        <!-- <td colspan="2" class="text-center" style="line-height: 30px;padding:0 20px;border-top:1px solid #000;background:#D1CACA; ">所属角色</td> -->
        <td colspan="8">
            <ul class="nav nav-tabs" style="margin-bottom: -20px;">
                <li class="active text-center" id="userinfo"><a href="#tab_1" data-toggle="tab" aria-expanded="true">所属角色</a></li>
                <li class=" text-center" id="userinfo"><a href="#tab_1" data-toggle="tab" aria-expanded="true"></a></li>
            </ul>
        </td>
    </tr>
    <tr>
        <td colspan="4" >
            <div class="table-scrollable">
                <table class="table-bordered table-striped table-hover" id="table" data-toggle="table" data-pagination="true" data-url="" data-toolbar="#toolbar" data-row-style="rowStyle">
                    <thead>
                    <tr data-checkbox="true" style="cursor: pointer;">
                        <th data-checkbox="true" class="checkSelect"></th>
                        <th data-field="id" data-sortable="true" style="padding:0 20px;">角色名称</th>
                        <th data-field="lastname" data-sortable="true" style="padding:0 20px;">备注</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="tr">
                        <td></td>
                        <td>1</td>
                        <td>1</td>
                    </tr>
                    <tr id="tr">
                        <td></td>
                        <td>1</td>
                        <td>1</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </td>
    </tr>
    </tbody>

</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
</body>
</form>
<script type="text/javascript">
    $(function() {
        $('#userinfo').on('click', function() {
            $('.a').show();
            $('.b').hide();
        })
        $('#other').on('click', function() {
            $('.a').hide();
            $('.b').show();
        })
        $("#updata").click(function(){
                $("#ExceType").val("02#");
            $.ajax({
                url:"/mesadmin/user/SaveUserInfoRequest",
                type:"POST",
                data : $("#form").serialize(),
                success:function(data){
                    $("#msg").text(data);
                    setTimeout(function(){
                        $("#msg").text("");
                    },2000)
                }
            })
        });

    })
</script>
</html>
