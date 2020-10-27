<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/11/12
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>切换工位</title>
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
    <style>
        #thead tr th{
            text-align: center;
        }
        #thead th span{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="btn-group table tabTop td1 common">

    <div class="btn btn-primary cSelect">查询</div>

    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix">
    <div style="">
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
                                            <label for="xt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员&nbsp;&nbsp;</label>
                                            <div id="person"></div>
                                            <label for="xt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设备&nbsp;&nbsp;</label>
                                            <div id="shebei"></div>
                                        </div>
                                        <div class=" formgroup disblock"  style="overflow-y:scroll;height:400px ">
                                            <table border="1" style="width: 1000px;border:  #D3D6DA;margin-left: 80px"  >
                                                <thead id="thead">
                                                    <tr><td style="text-align: center;width: 200px;"><span>线体</span></td><td style="text-align: center;width: 200px;"><span>工序</span></td><td style="text-align: center;width: 207px;"><span>工位</span></td><td style="text-align: center;width: 200px;"><span>操作</span></td></tr>
                                                </thead>
                                                <tbody id="tbody">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </form>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel2" aria-hidden="true">
            <div class="modal-dialog" style="width: 400px;height: 200px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">×
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class=" formgroup disblock">
                            &nbsp;&nbsp;&nbsp;&nbsp;原工位&nbsp;&nbsp;<span id="ygw"></span>
                        </div>
                        <div class=" formgroup disblock">
                            目标工位&nbsp;&nbsp;<div id="mbgw"></div>
                        </div>
                        <div class=" formgroup disblock">
                            &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;备注&nbsp;&nbsp;<input type="text" id="remark"/>
                        </div>
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
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/qhgw/qhgwinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>