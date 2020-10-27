<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/7/1
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>检验文档管理</title>
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
<div class="btn-group table tabTop td1 common">
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>

    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索检验文档管理">
            <img class="searchTree"
                 src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png"/>
            <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页"
                                                                                                         id="next"
                                                                                                         style="display: block;float: right;cursor: pointer">　>>　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right">
        <form id="factoryinfo">
            <input type="hidden" id="MaRd"/>
            <input type="hidden" id="MaVerRd"/>
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">检验文档信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;padding-right: 50px">
                                    <div class="printTInfo disblock">
                                        <div class="form-group formgroup">
                                            <label for="CheckDocName">检验文档名称</label>
                                            <input type="text" class="form-control read check" id="CheckDocName"
                                                   name="CheckDocName" placeholder="" maxlength="50">
                                        </div>

                                        <%--<div class="form-group formgroup">
                                            <label>关联类型</label>
                                            <select id="correlationtype" style="width: 105px;">
                                                <option value=""></option>
                                                <option value="00">物料</option>
                                                <option value="01">物料类别</option>
                                            </select>

                                            <div class="form-group " id="guanlian1">
                                                <label>物料编码</label>
                                                <div id="material" data-id=""></div>
                                            </div>
                                            <div class="form-group " id="guanlian2" style="display: none">
                                                <div class="maBottom">
                                                    <label class="maBottom">物料类别
                                                        <input type="hidden" id="MaTypeRd" name="MaTypeRd"/>
                                                        <input id="type" name="type" autocomplete="off"
                                                               style="width:196px;" maxlength="80"/>
                                                    </label>
                                                    <div id="t" style="position: absolute;z-index: 99999;width:197px;display:none;
                                                background: #FFF;margin-left: 60px;margin-top: -10px;border: 1px solid darkgray;
                                                height:150px;overflow: auto;"></div>
                                                    <div id="MaType" data-id="" style="display: none;"></div>
                                                </div>
                                            </div>
                                        </div>--%>
                                    </div>
                                </div>
                            </div>

                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的系统信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <form id="uploadForm" action="" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td id="exit">上传文件： <input type="file" name="file[]" id="file"/><a href="javascript:;"
                                                                                       id="add">[+]</a></td>
                    <%--   <td id="exit1">上传文件：<label id="exit2"></label></td>--%>
                </tr>
                <tr>
                </tr>
            </table>
        </form>
        <td>
            <input type="button" value="上传" id="but"/>
        </td>

    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 400px;height: 200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    复制检验模板信息
                </h4>
            </div>
            <div class="modal-body">
                模板名称&nbsp;&nbsp;&nbsp;<input type="text" name="" id="sliname" autocomplete="off" style="width: 200px;"/>
                <input type="text" name="" id="slinameRd" style="display: none" autocomplete="off"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<!--下拉框js-->
<script src="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/qa/jyx/jywdinfo.js?v=2'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>

</body>
</html>
