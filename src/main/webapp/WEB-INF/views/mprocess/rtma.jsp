<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>物料移除与替换</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button type="button" class="btn btn-primary" id="search_" >查询</button>
    <button type="button" class="btn btn-primary" id="save_" >保存</button>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <form id="setupspForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="form-inline">
                    <%--floor1--%>
                    <div class="printTInfo mb">
                        <div class="nav-tabs-custom">
                            <div>
                                <div class="tab-pane  clearfix" id="tab_1">
                                    <div class="box-body  form-inline">
                                        <div class="nav-tabs-custom">
                                       <ul class="nav nav-tabs">
                                                   <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">材料移除与替换</a></li>
                                               </ul>
                                        <div class='f1'>
                                                <div class="bBottom">
                                                     <label for="batch">批&emsp;次</label><input type="text" class="form-control" id="batch" name="batch"  " />
                                                </div>
                                                <div class="bBottom">
                                                    <label for="woCode">工单号</label>
                                                    <input type="text" class="form-control" id="woCode" name="woCode"  />
                                                    </div>
                                                    <div class="bBottom">
                                                         <label for="product">产&emsp;品</label>
                                                            <input type="text" class="form-control" id="product" name="product"  />
                                                    </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                    <!--处理表格-->

                              <div class="box-body  form-inline">
                                <div class="nav-tabs-custom">
                                    <ul class="nav nav-tabs">
                                      <li class="active"><a href="#tab_2" data-toggle="tab" class="dymb">消耗列表</a></li>
                                    </ul>
                                    <div class="tab-pane" id="tab_2">
                                        <div class="boxx">
                                            <div class="nav-tabs-custom">
                                                <section class="clearfix">
                                                    <div class=" tablecontent">
                                                        <div class="gridPanel">
                                                            <table id="list1"></table>
                                                            <div id="pager1"></div>
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>
                                        </div>
                                    </div>

                                    <ul class="nav nav-tabs">
                                      <li class="active"><a href="#tab_3" data-toggle="tab" class="dymb">明细</a></li>
                                    </ul>
                                    <div class="tab-pane" id="tab_3">
                                        <div class="boxx">
                                            <div class="nav-tabs-custom clearfix rtmaF">
                                               <div class="rtmaLeft" >
                                                   <div class="bBottom">
                                                       <label for="">批&emsp;&emsp;次</label>
                                                        <input class="form-control" type="text" name="" />
                                                   </div>
                                                   <div class="bBottom">
                                                       <label for="">物料代码</label>
                                                        <input class="form-control" type="text" name="" />
                                                   </div>
                                                   <div class="bBottom">
                                                       <label for="">物料名称</label>
                                                        <input class="form-control" type="text" name="" />
                                                   </div>
                                                   <div class="bBottom">
                                                       <label for="">消耗数量</label>
                                                        <input class="form-control" type="text" name="" />
                                                   </div>
                                                   <div class="bBottom">
                                                       <label for="">单&emsp;&emsp;位</label>
                                                        <input class="form-control" type="text" name="" />
                                                   </div>
                                               </div>
                                               <div class="rtmaRight">
                                                  <div class="bBottom">
                                                       <label for="">操&emsp;&emsp;作</label>
                                                        <input class="form-control" type="text" name="operation" list="operationList" />
                                                        <datalist id="operationList">
                                                            <option value="移除">
                                                            <option value="替换">
                                                        </datalist>
                                                   </div>
                                                   <div class="bBottom">
                                                       <label for="">替换批次</label>
                                                        <input class="form-control" type="text" name="replaceBatch" list="replaceBatchList" />
                                                        <datalist id="replaceBatchList"></datalist>
                                                   </div>
                                               </div>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
</body>
</html>


