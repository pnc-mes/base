<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上下载具</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css"
          rel="stylesheet"/>
    <!-- 树开始 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>
<body>
<div class="btn-group common table tabTop td1">
    <div class="btn btn-primary _zhuangzai" a>保存</div>
    <div class="btn btn-primary saves" style="display: none">保存</div>
    <div class="btn btn-primary _close">关闭</div>
</div>

<div class="f2 zsyLeft1 clearfix lRight">
    <div class="right1 rightMLeft " style="overflow: auto;height:100%;margin-top: 17px">
     <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom" style="margin-left:10px;margin-top: 10px;">
                    <ul class="nav nav-tabs">
                        <li class="active" id="nav_tab3"><a href="#tab_3" data-toggle="tab" class="dymb">载具装载</a></li>
                        <li id="nav_tab4"><a href="#tab_4" data-toggle="tab">载具卸载</a></li>
                    </ul>
                    <div class="tab-content">

                        <div class="tab-pane active clearfix" id="tab_3">
                           <div style="border: 1px;padding-top: 10px;">
                               <input type="radio" name="commom" id="sing" checked><label for="sing">一载具装多批</label>
                               <input type="radio" name="commom" id="double"><label for="double">一批装多载具</label>
                           </div>
                            <div class="box-body form-inline">
                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body ">
                                                <div class="gridPanel" style="padding-bottom: 0px;">
                                                    <div id="a1" style="margin-top:-11px;margin-left:6px">
                                                        <label >载具名称</label><br/>
                                                        <input type="text" class="form-control" id='zjbm'
                                                               placeholder="" maxlength="50">

                                                    </div>
                                                    <div id="b1" style="margin-top:-11px;margin-left:6px;display: none">
                                                        <label >批次ID</label><br/>
                                                        <input type="text" class="form-control" id='pc1'
                                                               placeholder="" maxlength="50">

                                                    </div>

                                                    <br/>



                                                    <div id="a2" style="margin-top:-11px;margin-left:6px">
                                                        <label >批次ID</label><br/>
                                                        <input type="text" class="form-control" id='pc'
                                                               placeholder="">
                                                    </div>

                                                    <div id="b2" style="margin-top:-11px;margin-left:6px;display: none ">
                                                        <label >载具编码</label><br/>
                                                        <input type="text" class="form-control" id='zjbm2'
                                                               placeholder="">
                                                    </div>
                                                    <div id="cke" style="margin-left: 260px;margin-top: -22px;">
                                                        <input type="checkbox" id="check"/><label for="check">自动装载(一载具装一批的时候勾选，每次扫描可不用点保存按钮)</label>
                                                    </div>
                                                    <br/>
                                                    <div id="pcxx" style="margin-top:-11px;margin-left:6px">
                                                        <label>批次信息</label>  <br/>
                                                        <table id="list3"></table>
                                                    </div>
                                                    <div id="zjxx" style="margin-top:-11px;margin-left:6px;display: none;">
                                                        <label>载具信息</label><br/>
                                                        <table id="list4"></table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="tab_4">
                            <div class="box-body form-inline">
                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body ">
                                                <div style="margin-top:-11px;margin-left:6px">
                                                    <label id="xzzj">载具名称</label><br/>
                                                    <input type="text" class="form-control" id='xzzjname'
                                                           placeholder=""><input type="checkbox" id="autoxzzj"/><label for="autoxzzj">自动卸载(勾选后，扫描可不用点卸载按钮)</label>
                                                </div>

                                                <br/>
                                                <div id="xzpxxx" style="margin-top:-11px;margin-left:6px">
                                                    <label>批次信息</label><div class="f2_4 btn-group" style="    margin-left: 20px;
    margin-bottom: 13px;">
                                                    <div class="btn btn-primary del1">删除</div>
                                                </div> <br/>
                                                    <table id="list5"></table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>

                            <%----%>
                        </div>


                    </div>
                </div>
            </div>
        </div>
      </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/carrierrelation/carrierrelation.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>


</body>

</html>

