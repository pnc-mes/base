<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/11/19
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通用查询</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!--下拉框css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/ui/global/layer/skin/default/layer.css">

    <style>
        #myModal{
            background: 0px;
        }
    </style>
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button class="btn btn-primary cSplit"  type="submit" id="cx" >查询</button>
    <button class="btn btn-primary cSplit" id="dc">导出</button>
    <iframe id="ifile" style="display:none"></iframe>
    <button class="btn btn-primary cClose _close">关闭</button>
</div>
<div class="formbd col-sm-12">

    <form id="addForm" action="${pageContext.request.contextPath}/Sys/tycxzxPage">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="box-body form-inline clearfix">


                    <div class="printTInfo mb contentLeft">
                        <div class=" formgroup disblock" style="min-width: 1200px">
                            ${newsId}
                        </div>
                        <div  class=""   style="display: none;">
                            <table  border='1' style='border:#1b56a5;margin-top:10px;font-size: 15px;width:max-content'>
                                <thead id="tableHead1" style="">

                                </thead>
                                <tbody id='tableBody1'></tbody>
                                <div >

                                </div>
                            </table>
                            <table  border='1' style='border:#1b56a5;margin-top:10px;width:max-content;font-size: 15px;'></table>
                        </div>
                        <!--<div id="tableBody" class=" formgroup disblock">
                        </div>-->
                    </div>
                </div>
            </div>


            <!--遮罩层-->
            <div style="align-content: center;margin-top: 20%;margin-left: 45%;width:20%;height: 20%;" class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel2" aria-hidden="true">
                <div class="circleChart" id="circle1" ></div>
            </div><!-- /.modal -->

        </div>
    </form>
    <!--补录组件条码-->
    <div class="modal fade" id="Modals" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel2" aria-hidden="true">
        <div class="modal-dialog" style="width: 400px;height: 200px;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel2">
                        请每行输入一条数据
                    </h4>
                </div>
                <div class="modal-body">
                    <textarea id="mo" autocomplete="off" rows="5" style="width: 100%;"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="save">
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>


<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<!--layui时间-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/laydate/laydate.js"></script>
<!--下拉框js-->
<script src="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.js"></script>
<script  src="${pageContext.request.contextPath}/static/pnsadmin/Module/sys/tycxzx/tycxzx.js"></script>

<!--进度条-->
<script  src="${pageContext.request.contextPath}/static/plugins/circleChart/circleChart.js"></script>
<script type="text/javascript">
    $(function () {
        var date = new Date();
        this.year = date.getFullYear();
        this.month = date.getMonth() + 1;
        this.date = date.getDate();
        this.date1 = date.getDate()-1;
        this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        //时间段
        var dateTime=this.year+"-"+this.month+"-"+this.date1+" "+this.hour+":"+this.minute+":"+this.second+" - "+this.year+"-"+this.month+"-"+this.date+" "+this.hour+":"+this.minute+":"+this.second;
        //日期段
        var data=this.year+"-"+this.month+"-"+this.date1+" - "+this.year+"-"+this.month+"-"+this.date;

        //加载时间控件样式
        var timeArr='<%=session.getAttribute("time")%>';
        timeArr=timeArr.split("|");
        timeArr.forEach(function (e) {
            if(e!=null&&e!=''){
                var s='#'+e;
                laydate.render({
                    elem:s
                    ,type: 'datetime'
                    ,value:""+dateTime+""
                    ,range: true
                });

            }
        })
        //加载日期段样式
        var dateArr='<%=session.getAttribute("date")%>';
        dateArr=dateArr.split("|");
        dateArr.forEach(function (ee) {
            if(ee!=null&&ee!=''){
             laydate.render({
                 elem: '#'+ee
                // ,type: 'date'
                 ,range: true
                 ,value:""+data+""
             });
            }
        });
        $(".inputs").on('click', function(){
            $("#Modals").modal("show");
        });
        $("#save").click(function () {
            aa();
        });
        function aa(){
            if($("#mo").val() == undefined || $("#mo").val().trim() == ''){
                $(".inputs").prev().val("'~'");
                $("#Modals").modal("hide");
            }else{
                var arr = $("#mo").val().split("\n");
                for(var i=0;i<arr.length;i++){
                    arr[i] = "'" + arr[i] + "'";
                }
                var strs = arr.join(',');
                $(".inputs").prev().val(strs);
                $('#mo').focus();
                $("#Modals").modal("hide");
            }
        }
    });
</script>
</body>
</html>
