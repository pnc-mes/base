
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>包装信息查询</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <!-- 树开始 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/ui/global/layer/skin/default/layer.css">

    <%--<script>
        //获取浏览器页面可见高度和宽度
        var _PageHeight = document.documentElement.clientHeight,
            _PageWidth = document.documentElement.clientWidth;
        //计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
        var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
            _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
        //在页面未加载完毕之前显示的loading Html自定义内容
        var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:0.8;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(/Content/loading.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">页面加载中，请等待...</div></div>';
        //呈现loading效果
        document.write(_LoadingHtml);

        //window.onload = function () {
        //    var loadingMask = document.getElementById('loadingDiv');
        //    loadingMask.parentNode.removeChild(loadingMask);
        //};

        //监听加载状态改变
        document.onreadystatechange = completeLoading;

        //加载状态为complete时移除loading效果
        function completeLoading() {
            if (document.readyState == "complete") {
                var loadingMask = document.getElementById('loadingDiv');
                loadingMask.parentNode.removeChild(loadingMask);
            }
        }

    </script>--%>
</head>
<body id="aaa">
<div id="loading">
</div>
<div class="contation">
    <div class="btn-group table td1">
        <button class="btn btn-primary repack">重打唛头</button>
        <button class="btn btn-primary addQc">生成请检单</button>
        <%--<button class="btn btn-primary addHP">换片</button>--%>
       <%-- <div class="btn-group">
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="true" id="option">操作
                <span class="caret"></span>
            </button>
            &lt;%&ndash;<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li role="presentation"><a   class="text-center rePrint" role="menuitem" tabindex="-1">重打</a>
                </li>
            </ul>&ndash;%&gt;
        </div>
        <div class="btn-group">
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="true" id="option1">选项
                <span class="caret"></span>
            </button>
           &lt;%&ndash; <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li role="presentation" id="export"><a class="text-center" role="menuitem" tabindex="-1">导出</a></li>
               &lt;%&ndash; <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">打印</a></li>
           &ndash;%&gt; </ul>&ndash;%&gt;
        </div>--%>
        <button class="btn btn-primary _close">关闭</button>
    </div>


    <div style="width: 100%;height: 50px;margin-left: 20px;margin-top: 10px;">
        <label>箱<%--/组件--%>&nbsp;&nbsp;<input type="text" id="xiangandbatch" placeholder="请输入箱号"/></label><%--<input type="checkbox" id="checkBarCode" style="margin-left: 8px;"/>--%>

        <label class="layui-form-label" style="margin-left: 20px;">日期时间范围&nbsp;&nbsp;<input type="text" class="layui-input" id="test10" placeholder=" - " style="width: 280px;"></label>

        <label>&nbsp;&nbsp;&nbsp;&nbsp;查询入库前未检验箱号&nbsp;&nbsp;<input type="checkbox" id="isverify"/></label>
        <label>&nbsp;&nbsp;&nbsp;&nbsp;是否满箱&nbsp;&nbsp;<input type="checkbox" id="siFull"/></label>

        <label>包装规格&ensp;<select id="packs" style="width: 150px;"></select></label>


        <input type="button" id="select" value="查询" style="margin-left: 40px;"/>
        <br/>

        <label>发货地址&nbsp;&nbsp;<input id="fahuoaddress" style="width: 300px;" type="text"/></label>
        <label style="margin-left: 10px;">收货地址&nbsp;&nbsp;<input id="shouhuoaddress" style="width: 300px;" type="text"/></label>
    </div>


    <section class="content box box-primary" style="margin-top:13px;">
        <div class="row">
            <div class="col-md-12">
                <div class="box-body tablecontent">
                    <div class="gridPanel">
                        <table id="list4"></table>
                        <div id="pager4"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section style="margin-top:13px;margin-left: 15px;">
        <div class="row">
           <label>当前总数</label><span id="totalnum"></span> <label style="margin-left: 10px;">已勾选总数</label><span id="checktotalnum"></span>
        </div>
    </section>

    <section class="content box box-primary" style="margin-top:13px;">
        <div class="row">
            <div class="col-md-12">
                <div class="box-body tablecontent">
                    <div class="gridPanel">
                        <table id="list5"></table>
                        <div id="pager5"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 400px;height: 500px;">
        <div class="modal-content" >
            <div class="modal-header">

            </div>
            <div class="form-group " style="width: 480px;padding-left: 35px" >
                <label style="padding-right: 20px">备注&nbsp;&nbsp;</label>
                <div style="margin-top: -29px;padding-left: 45px">
                   <textarea style="width: 220px;height: 68px;"
                             id="remark" name="remark"  >
                     </textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save2">
                    确认
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<%--<script type="text/javascript">
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
        laydate.render({
            elem: '#test10'
            , type: 'datetime'
            , range: true
            ,value:dateTime
        });

    });
</script>--%>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<!--layui时间-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/laydate/laydate.js"></script>
<!--下拉框js-->
<script src="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/search/printsearch/selectxiangandbatch.js?v=1'></script>




</body>
</html>

