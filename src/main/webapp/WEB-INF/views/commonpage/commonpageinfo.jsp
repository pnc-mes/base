<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>站点记录查询页面</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>

    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/ui/global/layer/skin/default/layer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layuiadmin/style/admin.css" media="all">
</head>
<body id="aaa">
<div class="contation">
    <div class="btn-group table td1">
        <button class="btn btn-primary _export">导出</button>
        <button class="btn btn-primary _close">关闭</button>
    </div>
</div>
<div>
    <div class="row"  style="margin-top: 8px;margin-left: 2px;">
        <div class="col-md-3">
            <label>站点&nbsp;&nbsp;</label>
            <select style="width: 90px;" id="station">
                <option value="0" selected>全部</option>
                <option value="1">背板上料</option>
                <option value="2">电池片分档</option>
                <option value="3">层压机记录</option>
                <option value="4">压后检</option>
                <option value="5">前EL</option>
                <option value="6">IV</option>
                <option value="7">后EL</option>
                <option value="8">终检</option>
            </select>
        </div>
        <div class="col-md-3" style="margin-left: -150px;">
            <label>线别&nbsp;&nbsp;</label>
            <select style="width: 150px;" id="lineinfo">
            </select>
        </div>
        <div class="col-md-3" style="margin-left: -49px;">
            <label style="margin-left: -40px;">日期&nbsp;&nbsp;</label>
            <input type="text" class="layui-input" id="test10" placeholder=" - " style="width: 290px;margin-top: -30px;">
        </div>
        <div class="col-md-3" style="margin-left: 12px;">
            <label>工单&nbsp;&nbsp;</label>
            <select  class="selectpicker" style="width: 115px;" multiple data-live-search="true" id="scxb" title="请选择工单">
            </select>
        </div>
        <label style="margin-left: -115px;">等级名称</label>
        <select style="width: 55px;">
            <option>全部</option>
            <option>A</option>
            <option>A2</option>
        </select>
        <input type="checkbox" id="isChecked"/><label for="isChecked">是否去重</label><button id="select" style="margin-left: 5px;">查询</button>
    </div>
</div>
<div class="layui-fluid" style="width: 100%;height: 600px;overflow: auto;">
    <table class="layui-hide" id="test-table-data" lay-filter="test-table-data"></table>
</div>


<%--<div class="row" style="margin-left: 2px;">
    <div class="col-md-12">
        <div class="box-body tablecontent">
            <div class="gridPanel">
                <table id="list4"></table>
                <div id="pager4"></div>
            </div>
        </div>
    </div>
</div>--%>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<!--layui时间-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/laydate/laydate.js"></script>
<%--<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/commonpage/commonpageinfo.js'></script>--%>
<script src="${pageContext.request.contextPath}/static/layuiadmin/layui/layui.js"  charset="utf-8"></script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>
<script>
    $("#scxb").selectpicker({
        width:115
    });
    layui.config({
        base: '${pageContext.request.contextPath}/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function(){

        var table = layui.table;
        //展示已知数据
        table.render({
           // toolbar:true,
            skin: 'line'
            //title:'用户信息表'
            //,defaultToolbar: ['filter', 'print', 'exports']
            ,elem: '#test-table-data'
            ,cols: [[ //标题栏
                {field: 'woCode', title: '工单号', minWidth: 10 ,fixed: 'left'}
                ,{field: 'batch', title: '组件条码', minWidth: 10,fixed: 'left'}
                ,{field: 'czpcode', title: '导电芯板编号', minWidth: 10,fixed: 'left'}
                ,{field: 'maCode', title: '产品代码',minWidth: 10,fixed: 'left'}
                ,{field: 'maName', title: '产品名称',minWidth: 10,fixed: 'left'}
                ,{field: 'eff', title: '电池产率档', minWidth: 10}
                ,{field: 'grade', title: '电池片等级', minWidth: 10}
                ,{field: 'color', title: '电池片颜色', minWidth: 10}
                ,{field: 'gradeName', title: '等级名称', minWidth: 10}
                ,{field: 'badName', title: '不良明细', minWidth: 10}
                ,{field: 'locationName', title: '不良位置', minWidth: 10}
                ,{field: 'maCode1', title: '物料代码', minWidth: 10}
                ,{field: 'maName1', title: '物料代码', minWidth: 10}
                ,{field: 'bomName', title: 'BOM信息', minWidth: 10}
                ,{field: 'createTime', title: '创建时间', minWidth: 10, fixed: 'right'}
                ,{field: 'creator', title: '创建者', minWidth: 10, fixed: 'right'}
                ,{field: 'remark', title: '备注', minWidth: 10, fixed: 'right'}
            ]]
            ,data: []
            ,even: true
            ,page: true //是否显示分页
            ,limits: [5, 10, 20]
            ,limit: 10 //每页默认显示的数量
        });
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

        //默认加载当前的时间，范围为1天
        laydate.render({
            elem: '#test10'
            ,type: 'datetime'
            ,range: true
            ,value:dateTime
        });

        //加载线别，由于线别就几个因此这边的控件就用原生的select即可
        request({
            url: '/Line/GetAllLineInfo',
            data: {
                "ExecType": "00",
            }}, function (Body) {
            var resultData = Body.Data;
            if(resultData.length>0){
                //定义下拉框的option进行拼接
                var option_="";
                for(var i  in  resultData){
                    option_+="<option value='"+resultData[i].LineRd+"'>"+resultData[i].LineName+"</option>"
                }
                $("#lineinfo").empty().html(option_);

            }
        });


        //加载工单
        request({
            url: "/WO/GetAllWOInfo",
            data: {
                "ExecType": "00",
                "InitData": JSON.stringify({
                    "FiledList": [{
                        "FieldName": "Status",
                        "FieldOpt": "in",
                        "FieldVal": "(01,03)"
                    }, {
                        "FieldName": "CreateTime DESC",
                        "FieldOpt": "Order BY"
                    }]
                })
            }
        }, function (Body) {
            var data=Body.Data;
            var aa="<option selected></option>";
            for(var i in data){
                aa+='<option value='+data[i].WoRd+'>'+data[i].WoCode +'</option>';
            }
            $("#scxb").html(aa);
            $('#scxb').selectpicker('refresh');
            $("#scxb").selectpicker({
                width:115
            });
        });
        //点击加载工单
        $('#scxb').on('shown.bs.select', function (e, clickedIndex, isSelected, previousValue) {

            request({
                url: "/WO/GetAllWOInfo",
                data: {
                    "ExecType": "00",
                    "InitData": JSON.stringify({
                        "FiledList": [{
                            "FieldName": "Status",
                            "FieldOpt": "in",
                            "FieldVal": "(01,03)"
                        }, {
                            "FieldName": "CreateTime DESC",
                            "FieldOpt": "Order BY"
                        }]
                    })
                }
            }, function (Body) {
                var data=Body.Data;
                var aa="<option selected></option>";
                for(var i in data){
                    aa+='<option value='+data[i].WoRd+'>'+data[i].WoCode +'</option>';
                }
                $("#scxb").html(aa);
                $("#scxb").selectpicker({
                    width:115
                });
               // $('#scxb').selectpicker('refresh');
            });
        });

        $("#select").click(function(){
            //获取站点的信息
            var id=$("#station").find("option:selected").val();
            if(parseInt(id)==0){
                toastr.warning("站点不能为全部，选择其他站点");
                return false;
            }
            if(id==null||id==""){
                toastr.warning("站点不能为空");
                return false;
            }
            var lineGd=$("#lineinfo").find("option:selected").val();
            if(lineGd==null||lineGd==""){
                toastr.warning("线别不能为空");
                return false;
            }
            var data=$("#test10").val();
            if(data==""||data==null){
                toastr.warning("时间不能为空");
                return false;
            }
            var createTimeStart=data.split(" - ")[1];
            var createTimeEnd=data.split(" - ")[0];
            //后端ajax 拼接的参数，json形式的
            var LineInfoSources=$("#scxb").val();

            var LineInfo=[];
            if(LineInfoSources==""||LineInfoSources==null){
                LineInfo=[];
            }else {
                for(var  obj in LineInfoSources){
                    LineInfo.push(LineInfoSources[obj])
                }
            }
            var isChecked="00";
            var flag=$("#isChecked").is(":checked");
            if(flag){
                isChecked="00";
            }else {
                isChecked="01";
            }
            var data_={
                "id":id,
                "lineGd":lineGd,
                "createTimeStart":createTimeStart,
                "createTimeEnd":createTimeEnd,
                "woGd":LineInfo,//
                "isChecked":isChecked
            }
            $.ajax({
                "url": getBasePath() +"/Zdjlcx/getAllCommonPageInfo",
                "type": "post",
                "data": JSON.stringify(data_),
                "contentType": "application/json",
                "dataType": "json",
                "success": function(result){
                    var maxSize=result.length;
                    table.render({
                        //toolbar:true
                        skin: 'line'
                       // title:'用户信息表'
                      // , limit:max()
                       // ,defaultToolbar: ['filter', 'print', 'exports']
                        ,elem: '#test-table-data'
                        ,cols: [[ //标题栏
                            {field: 'woCode', title: '工单号', minWidth: 10 ,fixed: 'left'}
                            ,{field: 'batch', title: '组件条码', minWidth: 10,fixed: 'left'}
                            ,{field: 'czpcode', title: '导电芯板编号', minWidth: 10,fixed: 'left'}
                            ,{field: 'maCode', title: '产品代码',minWidth: 10,fixed: 'left'}
                            ,{field: 'maName', title: '产品名称',minWidth: 10,fixed: 'left'}
                            ,{field: 'eff', title: '电池产率档', minWidth: 10}
                            ,{field: 'grade', title: '电池片等级', minWidth: 10}
                            ,{field: 'color', title: '电池片颜色', minWidth: 10}
                            ,{field: 'gradeName', title: '等级名称', minWidth: 10}
                            ,{field: 'badName', title: '不良明细', minWidth: 10}
                            ,{field: 'locationName', title: '不良位置', minWidth: 10}
                            ,{field: 'maCode1', title: '物料代码', minWidth: 10}
                            ,{field: 'maName1', title: '物料代码', minWidth: 10}
                            ,{field: 'bomName', title: 'BOM信息', minWidth: 10}
                            ,{field: 'createTime', title: '创建时间', minWidth: 10, fixed: 'right'}
                            ,{field: 'creator', title: '创建者', minWidth: 10, fixed: 'right'}
                        ]]
                        ,data: result
                        ,even: true
                        ,page: true //是否显示分页
                        ,limits: [5, 10, 20]
                        ,limit: 10 //每页默认显示的数量
                    });
                }
            });

        });


    });
</script>
</body>
</html>

