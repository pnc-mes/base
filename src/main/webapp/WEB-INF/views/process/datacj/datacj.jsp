<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>数据采集管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/spec/specinfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
</head>
<body>
<div class="btn-group td1 table tabTop common">
    <input type="hidden" id="hidden" value="" h="" hi="" editdj="" editid="" editvid=""/>
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd" a="">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1" id="remove" b="">删除</a>
            </li>
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1" id="recopy"
                                       re="">复制</a></li>--%>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="addver"
                                       d="" a="" >新增版本</a></li>
            <li role="presentation"><a  class="text-center" role="menuitem" tabindex="-1" id="removeVer" rm="" d="">删除版本</a>
            </li>
        </ul>
    </div>
    <%--<div class="btn-group">
        <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 left clearfix zsyLeft8">
    <div class="left1 zsyZi1" style="position: relative;">
        <input class="input1 form-control"  type="search" name="" value="" placeholder="数据采集名">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
        </div>
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
</div>
<div class="right" id="_right">
    <form id="datacjForm" style="display: none;">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">采集信息</a></li>
                        <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">
                            <div class="box-body form-inline" style="margin-bottom: 10px;">
                                <div class="printTInfo disblock">
                                    <div class="form-group formgroup">
                                        <label for="DCName">采集数据名称</label>
                                        <input type="text" class="form-control read check" id="DCName"
                                               name="DCName" placeholder="" maxlength="50">
                                    </div>
                                    <div class="form-group">
                                        <label for="Version">版本</label>
                                        <input type="text" class="form-control rightMargin check version" id="Version"
                                               name="Version" placeholder="" maxlength="30">
                                        <label for="IsDef" class="checkbox" id="ddd">
                                            <input type="checkbox" value="" class="baoliu7 check" id="IsDef"
                                                   name="IsDef">
                                            设置为默认版本
                                        </label>
                                    </div>
                                </div>
                                <div class="printTInfo disblock">
                                    <label class="labell">&emsp;&emsp;&emsp;&emsp;状态
                                        <select id="Status" name="Statuss" class="form-control" style="width:196px;padding: 1px 6px;">
                                            <option id="00" value="00">可用</option>
                                            <option id="01" value="01">不可用</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="printTInfo disblock">
                                    <strong>告警设置</strong>
                                </div>
                                <div class="printTInfo disblock">
                                    <label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;到期提醒(邮件通知组)&nbsp;&nbsp;</label>
                                    <div id="expiremailgroup"></div>

                                    <label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提醒(邮件内容)&nbsp;&nbsp;</label>
                                    <div id="expiremailcontent"></div>
                                </div>
                            </div>
                            <div class="box box-primary  clearfix">
                                <div class="f2_4 btn-group">
                                    <div class="btn btn-primary add1">新增</div>
                                    <div class="btn btn-primary del1">删除</div>
                                </div>
                                <section >
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
                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="tab_2">
                            <%-- 引入页面中的其他信息--%>
                            <%@include file="/WEB-INF/views/otherAll.jspf"%>
                        </div>
                    </div>
                </div>

                <tr>
                    <td>
                        <input type='checkbox' class='layui-form'  lay-skin='primary' lay-filter='selectOne' class='selectPre'/></td>
                    <td><input type='checkbox' class='layui-form'  lay-skin='primary' lay-filter='selectOne' class='selectPre'/></td><td><input type='hidden' value=''/>"</td><td>"+ItemName+"</td><td>"+DataAlias+"</td><td>"+SValue+"</td><td>"+UpLimit+"</td><td>"+DwLimit+"</td><td>"+DefValue+"</td><td>"+Checked==true?'是':'否'+"</td><td>"+Action+"</td><td><a id='detailDeletes'>删除</a></td>
            </div>
        </div>
    </form>
</div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/datacj/datacj.js?v=2'></script>
</body>
</html>
