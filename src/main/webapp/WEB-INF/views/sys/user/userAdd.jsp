<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userAdd</title>
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <!-- bootstrap -->
    <link rel="stylesheet" href="../../../../mesadmin/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../../mesadmin/static/ui/global/layer/mobile/need/layer.css">
    <link href="../../../../mesadmin/static/min/css/supershopui.common.min.css" rel="stylesheet" />
    <link href="../../../../mesadmin/static/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <style type="text/css">
    td {
        cursor: pointer;
    }
    
    .a {
        border:1px solid #000;
    }
    
    .b {
        display: none;
    }
    li{
        width: 50%;
    }
     td ,th{
        outline: none;
    }
    input {
        outline: none;
        border: none;
        border: 1px solid #DDDDDD;
    }
    .nav-tabs>li>a{
        margin-right: -1px;
    }
    </style>
</head>

<body>
<form id="form">
    <input type="hidden" name="guids"/>
    <input type="hidden" name="guid"/>
    <table class="" cellspacing="50%" cellpadding="80" style="margin-left: 20px;">
        <tbody>
            <tr>
                <td colspan="4" style="line-height: 50px;padding-bottom: 30px;">

                    <button class="btn btn-sm"  id="saveUserInfo">保存</button>
                    <input type="button" class="btn btn-sm" id="addUserInfo" value="新增"/>
                    <button class="btn btn-sm">关闭</button><span id="msg" style="color: red;"></span>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    <ul class="nav nav-tabs">
                        <li class="active text-center c" id="userinfo"><a href="#tab_1" data-toggle="tab" aria-expanded="true">用户信息</a></li>
                        <li class="text-center d" id="other"  ><a href="#tab_2" data-toggle="tab" aria-expanded="false">其他</a></li>
                    </ul>
                </td>
            </tr>
            <tr class="a" style="border:1px solid #DDDDDD; border-bottom: none;border-top: none;">
                <td style="padding:0 20px;">用户名称</td>
                <td style="padding:30px 0;">
                    <input type="text" name="userName" value="" placeholder="">
                </td>
                <td style="padding:0 20px;">密码</td>
                <td style="padding: 20px 20px 20px 0;border-right: 1px solid #DDDDDD;">
                    <input type="text" name="passWord" value="" placeholder="">
                </td>
            </tr>
            <tr class="a" style="border:1px solid #DDDDDD; border-top: none;">
                <td style="padding:0 20px 30px 20px;">真实姓名</td>
                <td colspan="3" style="padding-bottom:30px;border-right: 1px solid #DDDDDD;">
                    <input type="text" name="realName" value="" placeholder="">
                </td>
            </tr>
            <tr class="b">
                <td style="padding:0 20px;border-left: 1px solid #DDDDDD;">创建人</td>
                <td style="padding:30px 0;">
                    <input type="text" name="creator" value="${user.userName}" placeholder="">
                </td>
                <td style="padding:0 20px;">创建时间</td>
                <td style="padding: 20px 20px 20px 0;border-right: 1px solid #DDDDDD;">
                    <input type="data" name="createTime" value="" placeholder="">
                </td>
            </tr>
            <tr class="b">
                <td style="padding:0 20px;border-left: 1px solid #DDDDDD;">修改人</td>
                <td>
                    <input type="text" name="lastModifyMan" value="" placeholder="">
                </td>
                <td style="padding:0 20px;">修改时间</td>
                <td style="border-right: 1px solid #DDDDDD;">
                    <input type="date" name="lastModifyTime" value="" placeholder="">
                </td>
            </tr>
            <tr class="b">
                <td style="padding:0 20px;border-left: 1px solid #DDDDDD;border-bottom: 1px solid #DDDDDD;">备注</td>
                <td style="padding:30px 0;border-right: 1px solid #DDDDDD;" colspan="3">
                    <textarea name="remark"></textarea>
                </td>
            </tr>
            <tr style="border:none;">
                <td colspan="4" style="line-height: 50px;border:none;">&nbsp;</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" name="ExceType" id="ExceType"/>
</form>
    <div class="table-scrollable col-sm-5">
        
            <ul class="nav nav-tabs" style="margin-bottom: -30px;">
                <li class="active text-center" id="userinfo"><a href="#tab_1" data-toggle="tab" aria-expanded="true">角色信息</a></li>
            </ul>
       
        <table class=" table-hover" id="table" data-toggle="table" data-pagination="true" data-side-pagination="server" data-id-table="advancedTable" data-url="" data-toolbar="#toolbar" data-row-style="rowStyle">
            <thead>
               
                <tr data-checkbox="true" style="cursor: pointer;">
                    <th data-checkbox="true" class="checkSelect"></th>
                    <th data-field="id" data-sortable="false" style="padding:0 20px;">角色名称</th>
                    <th data-field="lastname" data-sortable="false" style="padding:0 20px;">备注</th>
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
    <script src="../../../../mesadmin/static/ui/global/jQuery/jquery.min.js"></script>
    <script src="../../../../mesadmin/static/ui/global/layer/layer.js"></script>
    <script src="../../../../mesadmin/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../../mesadmin/static/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="../../../../mesadmin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
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

    })
$("#addUserInfo").click(function(){
    $("#ExceType").val("00#");
    var name=$("input[name='userName']")[0].value.trim();
    var pwd=$("input[name='passWord']")[0].value.trim();
    var realname=$("input[name='realName']")[0].value.trim();
    if(name==null||name==""){
      $("#msg").text("用户名称不能为空!");
    } else if(pwd==null||pwd==""){
        $("#msg").text("密码不能为空!");
    }else if(realname==null||realname==""){
        $("#msg").text("真实姓名不能为空!");
    }else {
        //新增
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
}
});
    </script>
</body>

</html>
