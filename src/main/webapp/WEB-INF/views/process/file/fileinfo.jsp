<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/7/4
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件管理</title>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/warehouse/store/storeinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/res/dev/devinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
</head>
<body>
<div class="btn-group table tabTop td1 common">
    <input type="hidden" id="hidden" value="" h=""/>
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd" id="add" e="">新增</div>
    <div class="btn btn-primary cSave" id="save">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
           <%-- <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="copy" d="">复制</a>
            </li>--%>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1"
                                       id="addVersion">新增版本</a></li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="removeVersion"
                                       b="">删除版本</a></li>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1" id="remove" a="">删除</a>
            </li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a>
            </li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>

<div class="f2 clearfix">

    <div class="zsyLeft">
        <div class="left zsyZi " id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索文件">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <input type="hidden" id="ExecType" name="ExecType"/>
    <form id="fileForm">
        <div class="right" style="display: none;">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">文件信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class=" marbottom">
                                            <label for="fileName">文件名称</label>
                                            <input type="text" class="form-control check" id="fileName" name="fileName"
                                                   placeholder="" maxlength="50">
                                            <label for="version">版本</label>
                                            <input type="text" class="form-control check" id="version" name="version"
                                                   placeholder="" maxlength="50">
                                            <label for="isDef" class="checkbox" id="dd" style="">
                                                <input type="checkbox" name="isDef" class="isDef" id="isDef"
                                                       name="isDef">
                                                设置为默认版本
                                            </label>
                                        </div>
                                        <div class="marbottom">
                                            <label for="filePath">文件上传</label>
                                            <input type="hidden" class="form-control rightMargin check" id="filePath"
                                                   name="filePath" accept=".pdf" placeholder="">
                                            <span class="btn btn-success start fileinput-button"
                                                  style="position: relative;">
                                                <i class="fa fa-upload"></i>
                                                <span>  <input type="file" id="file" name="file" value="upload"
                                                               title="选好文件之后点击保存上传成功"/>选择文件 </span>
                                            </span>
                                            <span id="filedowns" class="btn btn-success start fileinput-button"
                                                  style="position: relative;">
                                                <i class="glyphicon glyphicon-download-alt" style="color:white;"></i>
                                                <a id="filedown" download="" type="application/pdf" target="_blank"
                                                   style="color:#fff;"><span class="filedown"> 开始下载 </span></a>
                                            </span>
                                            <a id="zxck" href="" target="_blank">
                                                <span id="sop" class="btn btn-success fileinput-button"
                                                      style="position: relative;">
                                                    在线查看
                                                </span>
                                            </a>
                                            (只能上传.pdf文件)
                                        </div>
                                        <div>
                                            <label for="status">状&emsp;&emsp;态
                                                <select id="Status" name="Statuss" class="form-control" style="width: 196px;padding: 1px 6px;">
                                                    <option id="00" value="00">可用</option>
                                                    <option id="01" value="01">不可用</option>
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/file/fileinfo.js?v=2'></script>
</body>
</html>
