<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>批次查询</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
</head>
<body>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body" style="margin-top: -50px;">
                <form id="factoryinfo">
                    <div class="row rightTop">
                        <div class="col-md-12">
                            <div class="nav-tabs-custom">
                                <div class="tab-content">
                                    <div class="tab-pane active clearfix" id="tab_1">
                                        <div class="box-body form-inline">
                                            <div class="printTInfo" style="margin-top: 30px;">

                                                <div class=" formgroup disblock">
                                                    <label for="xt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线体&nbsp;&nbsp;</label>
                                                    <div id="xt"></div>

                                                </div>
                                                <div class=" formgroup disblock" style="margin-left: 80px;margin-top: -8px;">
                                                    <label><input type="radio" id="up" name="postion" value="00"/>往后道</label>
                                                    <label style="margin-left: 15px;"><input type="radio" id="down" name="postion" value="01"/>往前道</label>
                                                </div>

                                                <div class=" formgroup disblock" id="wfdata" style="margin-top: -14px;">
                                                    <label for="mbgx">&nbsp;&nbsp;&nbsp;工艺流程&nbsp;&nbsp;</label>

                                                    <select class="selectpicker" multiple data-live-search="true" data-max-options="1"  id="wf"  style="width: 198px;" title="请选择工艺流程">

                                                    </select>

                                                </div>



                                                <div class=" formgroup disblock" style="margin-top: -14px;">
                                                    <label for="mbgx">&nbsp;&nbsp;&nbsp;目标工序&nbsp;&nbsp;</label>

                                                    <select class="selectpicker" multiple data-live-search="true" data-max-options="1"  id="mbgx"  style="width: 198px;" title="请选择目标工序">

                                                    </select>

                                                </div>
                                                <div class=" formgroup disblock">
                                                    <label>&nbsp;&nbsp;&nbsp;目标工序行为&nbsp;&nbsp;</label><br/>
                                                    <div style="margin-left: 80px;">
                                                        <label><input type="radio" id="in" name="action" value="00"/>进站</label>
                                                        <label style="margin-left: 15px;"><input type="radio" id="out" name="action" value="01"/>过站</label>
                                                    </div>

                                                </div>
                                                <div class=" formgroup disblock">
                                                    <div style="margin-left: 17px;">
                                                        <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说明&nbsp;&nbsp;</label>
                                                        <select id="show" style="width: 200px;">
                                                            <option>其他说明</option>
                                                            <option>产线异常快速跳站</option>
                                                            <option>产品重用</option>
                                                        </select>
                                                    </div>
                                                    <div style="margin-left: 75px;margin-top: 10px;">
                                                <textarea id="textarea" style="width: 200px;height: 100px;">

                                                </textarea>
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
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="confirm">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="contation">
    <div class="btn-group table td1">
        <button class="btn btn-primary cSelect">筛选</button>
        <div class="btn-group">
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="true" id="option1">选项
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                <li role="presentation" id="export"><a  class="text-center" role="menuitem" tabindex="-1">导出</a></li>
                <li role="presentation" id="error"><a  class="text-center" role="menuitem" tabindex="-1">异常调站</a></li>

            </ul>
        </div>
    </div>
    <!--*******************************************下拉菜单**************************************************************************-->

    <!--*********************************处理带有CheckBox的表格***************************************************************************-->
    <section class="content box box-primary margintop">
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
        <!--*********************************处理非CheckBox的表格***************************************************************************-->
    </section>
</div>
<form id="SWareInfoForm" style="display: none;"><%--style="display: none;"--%>
    <div class="col-md-12" >
        <div class="box-body form-inline">
            <div class="printTInfo">
                <h3 class="h3" >批次查询表</h3>
                <div class="table table-responsive  mtop">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th class="text-center" rowspan="2" colspan="1">物料代码</th>
                            <th class="text-center" rowspan="2" colspan="1">物料名称</th>
                            <th class="text-center" rowspan="2" colspan="1">批次号</th>
                            <th class="text-center" rowspan="2" colspan="1">数量</th>
                            <th class="text-center" rowspan="2" colspan="1">单位</th>
                            <th class="text-center" rowspan="2" colspan="1">单据类型</th>
                            <th class="text-center" rowspan="2" colspan="1">创建时间</th>
                        </tr>
                        </thead>
                        <tbody id="CyContent">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

<%@include file="/WEB-INF/views/commonJs.jspf"%>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/search/batch/batch.js?v=1'></script>
</body>
</html>
