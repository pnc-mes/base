<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" href="../../../../mesadmin/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="../../../../mesadmin/static/min/css/supershopui.common.min.css" rel="stylesheet" />
    <style type="text/css" media="screen">
        .zzsc {
            width: 150px;
            margin: 0px auto 0;
        }

        .biaoqian td {
            cursor: pointer;
            padding-right: 20px;
            padding-bottom: 20px;
            padding-top: 10px;
        }
        a{
            text-decoration: none;
        }
        ul {
            width: 400px;
        }

        li {
            width: 15%;
            float: left;
            list-style: none;
            line-height: 28px;
            text-align: center;
            border-right: 1px solid white;
            /**/
        }
        .ul1>li{
            color: #fff;
        }
        .table {
            margin-bottom: 0;
        }

        .opera {
            position: absolute;
            top: 28px;
            left: -1px;
        }

        .opera p {
            text-align: center;
            width: 61px;
            background:  #337AB7;
            margin-bottom: 0;
            border: 1px solid white;

        }
        .opera p>a{
            color: #fff;
        }
        .opera>.p1{
            border-bottom:0;
        }
        li:hover {
            background: #0F0FF7;
        }
        .opera p:hover{
            background: #0F0FF7;
        }
        .table11>tbody>tr>td {
            padding: 0px;
        }

        .biaoqian {
            margin-top: 20px;
        }

        ol {
            margin-bottom: 0px;
        }

        ol>li {
            line-height: 28px;
        }
        .fixed-table-loading{
            display: none;
        }
    </style>
</head>
<body>
<table class="table table11">
    <tbody>
    <tr class="biaoqian">
        <td colspan="10" style="background: #337AB7">
            <ul class="nav ul1 clearfix ">
                <li class="active ">筛选</li>
                <li id="add">新增</li>
                <li class="ed" id="edit">编辑</li>
                <li id="delete">删除</li>
                <li class="caozuo">操作
                    <div class="opera hide">
                        <p class="p1"><a href="#" title="" >导出</a></p>
                        <p><a href="#" title="" >打印</a></p>
                    </div>
                </li>
                <li>关闭</li>

            </ul>
        </td>
    </tr>
    <tr class="biaoqian">
        <td style="padding-top: 20px;line-height: 1px;">
            <ol>
                <li style="width: 80px;margin-left: -40px;">快速过滤</li>
                <li style="width: 80px; background: #A3E7FB;">缺省方案</li>
                <li style="width: 80px;">搜索条件1</li>
            </ol>
        </td>
    </tr>
    </tbody>
</table>
<div class="table-responsive table1" style="margin-top: 14px;">
    <table id="myTable" border="1" cellspacing="0" cellpadding="0" class=" table-hover table-responsive">
        <tr>
            <td>
                <select onchange="load(this)" class="form-control input-sm">
                    <option selected="">查询条件</option>
                    <option class="0">下拉框</option>
                    <option class="1">文本框</option>
                    <option>查询条件 4</option>
                    <option>查询条件 5</option>
                </select>
            </td>
            <td>
                <select class="form-control input-sm" name="select">
                    <option selected="">等于</option>
                    <option>大于</option>
                    <option>小于</option>
                    <option>不等于</option>
                    <option>不小于</option>
                    <option>不大于</option>
                    <option>包括</optiosn>
                </select>
            </td>
            <td>
                <select class="form-control input-sm inputs" name="select">
                    <option selected="">值</option>
                    <option>option 2</option>
                    <option>option 3</option>
                    <option>option 4</option>
                    <option>option 5</option>
                </select>
                <input type="text" class='inputtext' style="display: none;" />
                <!-- 如果是文本状态 -->
                <!-- <input type="text" class="form-control"> -->
            </td>
            <td>

                <div class="col-sm-1 ">
                    <span class="glyphicon glyphicon-chevron-up up" style="cursor: pointer;font-size:14px;display: block;color: #337AB7;"></span>
                    <span class="glyphicon glyphicon-chevron-down down" style="cursor: pointer;font-size:14px;display:none;color: green;"></span>
                </div>
                <div class="col-sm-1 ">
                    <span class="glyphicon glyphicon-plus add downMenu " style="cursor: pointer;font-size:14px;color: #337AB7;display: none;"></span>
                </div>
            </td>

            <td>
                <button type="button" class="btn btn-primary btn-sm" name="sub">搜索</button>
            </td>
            <td>
                <button type="button" class="btn  btn-success btn-sm" onclick="modalConfirm()" name="sub">保存</button>
            </td>
            <td>
                <button type="button" class="btn btn-primary btn-sm" name="" id="reset">重置</button>
            </td>
            <td>
                <span id="msg" style="color: red;"></span>
            </td>
        </tr>
    </table>
</div>


<%--<table id="tab">
    <thead>

    </thead>
</table>--%>

<form id="form" style="display: block;">
    <input type="hidden" name="ExceType" id="ExceType"/>
    <input type="hidden" name="guid" />
<div class="table-scrollable table1" style="margin-top: 20px;display: none">
    <table class="table-striped table-hover" id="table" data-toggle="table" data-url="" data-toolbar="#toolbar">
        <thead>
        <tr style="cursor: pointer;">
            <th>全选<input id="checkall" type="checkbox"/></th>
            <th>用户名称</th>
            <th>真实姓名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <core:forEach var="ele" items="${UserInfoList}">
            <tr>
                <td><input type="checkbox" name="guids" class="ck" value="${ele.ruid}"/></td>
                <td id="userName">${ele.userName}</td>
                <td>${ele.realName}</td>
                <td><a href="#" title="" id="${ele.ruid}" class="reSetPassword">重置密码</a></td>
            </tr>
            </core:forEach>
        </tbody>
    </table>
</div>
</form>
</div>
<!-- END SAMPLE TABLE PORTLET-->
<form class="saveModal col-sm-6" style="display: none">
    <div id="modal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Id</label>
                        <input type="number" class="form-control" name="id" placeholder="id">
                    </div>
                    <div class="form-group">
                        <label>Stars</label>
                        <input type="text" class="form-control" name="name" placeholder="Name">
                    </div>
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" name="forks_count" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label>状态</label>
                        <input type="text" class="form-control" name="状态" placeholder="状态">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary submit">保存</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
</form>

<table id="table">
    <tbody>111</tbody>
</table>
<!-- /.modal -->
</div>
<script type="text/javascript" src="../../../../mesadmin/static/ui/global/jQuery/jquery.min.js"></script>
<script src="../../../../mesadmin/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../../../mesadmin/static/ui/global/layer/layer.js"></script>
<script src="../../../../mesadmin/static/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="../../../../mesadmin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<!-- 弹出层 -->
<script type="text/javascript" src="../../../../mesadmin/static/pnsadmin/Base/js/user.js?v=1"></script>
<script src="../../../../mesadmin/static/pnsadmin/Base/js/mainPage.js"></script>
<script src="../../../../mesadmin/static/pnsadmin/Base/js/confirm.js"></script>
<script type="text/javascript">



    var User = {
        init: function() {
            this.dealCaidan();
            this.dealReset();
        },
        dealCaidan: function() {/*处理下拉菜单*/
            $('.caozuo').mouseover(function() {
                $('.opera').addClass('show').end().find('.opera p').addClass('show');
            }, function() {
                $('.opera').addClass('show')
            })
            $('.caozuo').mouseout(function() {
                $('.opera').removeClass('show').end().find('.opera p').removeClass('show');
            })
        },
        dealReset: function() {/*重置文本框内容*/
            $('#reset').on('click',function(){
                $('.inputtext').val('').focus();
            })
        },

    }
    User.init();
    $(function(){
       /* var tab=$("#tab");
        alert(tab)

        $.post("/mesadmin/user/tableDate",function(data){
            var data=data;
            alert(data)
        });*/


        $.ajax({
            url: "/mesadmin/user/GetAllUserInfoRequest",
            type: "GET",
            success: function(json){
                if(json.status == "00"){
                    alert(JSON.stringify(json.body.data))
                    var data = json.body.data;
                    for(var i=0; i<data.length; i++){

                        $("#table tbody").append("<tr id='tr1'><td>"
                            +  data[i].userName  +"</td></tr>");
                    }
                }
            }
        });



        // 增加事件
        $("#add").click(function(){
            layer.open({
                type: 2,
                title: '添加页面',
                maxmin: true,//是否显示缩小扩大样式
                shadeClose: true, //点击遮罩关闭层
                area : ['900px' , '550px'],
                content: 'addUserInfo'
            });
        });
        //重置密码
        $(".reSetPassword").each(function () {
            $(this).click(function(){
                //点击时获取自己的id属性
                var value=$(this).attr("id");
                layer.open({
                    type: 2,
                    title: '重置密码',
                    maxmin: true,//是否显示缩小扩大样式
                    shadeClose: true, //点击遮罩关闭层
                    area : ['600px' , '350px'],
                    content: 'reSetPassword?id=' + value
                });
            });
        });


        $(document).on("click", ":input[class='ck']", function() {
            if($(this).attr("checked").length >1){
                $("#edit").css("display","none");
            }
            else{
                $("#edit").css("display","block");
            }
        })



        $(document).on("click", ":input[type='checkbox']", function() {
            if($("input[name='guids']:checked").length ==  $('input[name="guids"]').length) {
                $("#checkall").prop("checked", true);
            }
           else {
                $("#checkall").prop("checked", false);
            }

       } )
        //全选和全不选
        $("#checkall").click(function(){
            if ($(this).is(':checked')){
                $('input[name="guids"]').prop("checked", true);
            }else {
                $('input[name="guids"]').prop("checked", false);
            }
        });
        //单选删除
        $("#delete").click(function(){
            $("#ExceType").val("01#");
            var $this = $("input[name='guids']:checked");
            if($this.is(':checked')){
                var r = confirm("确定删除吗");
                if (r == true) {
                    $.ajax({
                        url : "/mesadmin/user/SaveUserInfoRequest",
                        type : "POST",
                        data : $('#form').serialize(),
                        success : function(data) {
                            $this.parent().parent().remove();
                          $("#msg").text(data);
                            setTimeout(function () {
                                $("#msg").text("");
                            },1500)
                        }
                    });
                } else {
                    return;
                }
            }
        });

        //编辑
        $("#edit").click(function(){
            $("#ExceType").val("02#");
            var $this = $("input[name='guids']:checked").val();
            layer.open({
                type: 2,
                title: '编辑页面',
                maxmin: true,//是否显示缩小扩大样式
                shadeClose: true, //点击遮罩关闭层
                area : ['900px' , '550px'],
                content: 'editUserInfo?id='+$this
            });
        });



    });
</script>
</body>
</html>
