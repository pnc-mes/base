<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/28
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>技能培训</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group td1  table common">
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2  clearfix zsyLeft1 col-sm-12 clearfix">
    <div class="f2 col-sm-12 clearfix">
        <div class="mtop1" id="_right">
            <div class="row rightTop">
                    <div class="row rightTop">
                        <div class="col-md-12">
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">培训记录信息</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tab_1">
                                        <div class="box-body form-inline">
                                            <div class="devGpName">
                                                <div class="form-group formgroup people">
                                                    <label>培训人员</label>
                                                    <div id="RealName" data-id=""></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="content clearfix">
                                            <div class="nav-tabs-custom">
                                                <div class="f2_4 btn-group">
                                                    <button type="button" class="btn btn-primary " id="add1">新增</button>
                                                    <button type="button" class="btn btn-primary " id="del1">删除</button>
                                                </div>
                                                <section>
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
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/res/usrskill/usrskillinfo.js?v=1'></script>
</body>
</html>

