<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/11/13
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载具</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
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
    <style type="text/css">
        ul{
            height: 200px;
            border: 1px solid #C1C1C1;
            overflow:auto;
        }
        ul li{
            list-style: none;
            border-bottom: 1px dotted #C1C1C1;
            margin: 5px 20px 0 -20px;
            text-align: center;
        }
        ul li:hover{
            background-color: gainsboro;
        }
        input {
            margin-right: 0px;
        }
         table tr{
             font-size: 13px;

         }

    </style>
</head>
<body>
<div class="btn-group table tabTop td1 common">

    <div class="btn btn-primary _Save">确认</div>

    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix">
    <div>
        <form id="factoryinfo">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1" style="margin-top: 30px;">
                                <div class=" form-inline">
                                    <div class="nav-tabs-custom">
                                        <div class="bBottom" style="margin-top: 10px">
                                            &emsp;<label for="carrierSN">载具序列号&ensp;</label>
                                            <input  id="carrierSN" />
                                            <input hidden />
                                        </div>
                                        <div class="formgroup disblock" style="margin-left: 20px;">
                                            <span>记录</span>
                                            <div class="formgroup disblock" style="margin-top: 10px">
                                                <table id="table">

                                                </table>
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
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/carrierunload/carrierunloadInfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>