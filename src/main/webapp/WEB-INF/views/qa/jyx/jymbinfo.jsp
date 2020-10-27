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
    <title>检验模板清单</title>
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
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class="zsyLeft" >
        <div class="left left1 zsyZi" id="left2" >
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索检验模板清单">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　>>　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right">
        <form id="factoryinfo">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">检验模板清单信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <%--            <div class="box-body form-inline">
                                                <div class="printTInfo">
                                                    <div class=" formgroup disblock">
                                                        <label for="djpdmc">等级判断名称&nbsp;&nbsp;</label>
                                                        <input type="text" class="form-control check" id="djpdmc"
                                                               name="djpdmc" placeholder="" maxlength="50">
                                                        &lt;%&ndash;<label style="margin-left: -65px;"><input type="checkbox" id="default"/>&nbsp;默认</label>&ndash;%&gt;
                                                    </div>
                                                    <div class=" formgroup disblock">
                                                        <label for="material">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产品&nbsp;&nbsp;</label>
                                                        <div id="material">

                                                        </div>
                                                        &lt;%&ndash;<label for="djpdlx">等级判断类型&nbsp;&nbsp;</label>
                                                        <select id="djpdlx" style="width: 200px;">
                                                            <option value="00">外观检验判断</option>
                                                            <option value="01">EL检验判断</option>
                                                            <option value="02">外观与EL</option>
                                                        </select>&ndash;%&gt;
                                                    </div>
                                                </div>
                                            </div>--%>
                                <div class="box-body form-inline" style="margin-bottom: 10px;padding-right: 50px">
                                    <div class="printTInfo disblock">
                                        <div class="form-group formgroup">
                                            <label for="CheckTempName">检验模板名称</label>
                                            <input type="text" class="form-control read check" id="CheckTempName"
                                                   name="CheckTempName" placeholder="" maxlength="50">
                                        </div>
                                        <%--        <div class="form-group">
                                                    <label>检验类型名称</label>
                                                    <input type="text" class="form-control rightMargin check version" id="name"
                                                           name="name" placeholder="" maxlength="30">
                                                    <label for="IsDef" class="checkbox" id="ddd">
                                                        <input type="checkbox" value="" class="baoliu7 check" id="IsDef"
                                                               name="IsDef">
                                                        设置为默认版本
                                                    </label>
                                                </div>--%>
                                      <%--  <div class="form-group formgroup">
                                            <label for="DCName">检验类型名称</label>
                                            <input type="text" class="form-control read check" id="Name"
                                                   name="DCName" placeholder="" maxlength="50">
                                        </div>--%>
                                        <div class="form-group formgroup">
                                        <label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态&nbsp;</label>
                                        <select id="Status" style="width: 135px;">
                                            <option value="00">启用</option>
                                            <option value="01">禁用</option>
                                        </select>
                                    </div>
                                    </div>


                                </div>

                                <%--             <ul id="myTab" class="nav nav-tabs">
                                                 <li class="active">
                                                     <a href="#home" data-toggle="tab">
                                                         外观检验等级
                                                     </a>
                                                 </li>
                                                 <li><a href="#ios" data-toggle="tab">EL检验等级</a></li>
                                                 <li>
                                                     <a href="#jmeter" data-toggle="tab">外观与EL</a>


                                                 </li>
                                             </ul>--%>
                                <div id="myTabContent" class="tab-content" style="padding-top:50px">
                                    <div class="tab-pane fade in active" id="home">
                                        <div class="box box-primary  clearfix">
                                            <div class="f2_4 btn-group">
                                                <div class="btn btn-primary add1">新增</div>
                                                <%--<div class="btn btn-primary edit1">编辑</div>--%>
                                                <div class="btn btn-primary del1">删除</div>
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
                                    <div class="tab-pane fade" id="ios">
                                        <div class="box box-primary  clearfix">
                                            <div class="f2_4 btn-group">
                                                <div class="btn btn-primary add2">新增</div>
                                                <%--<div class="btn btn-primary edit1">编辑</div>--%>
                                                <div class="btn btn-primary del2">删除</div>
                                            </div>
                                            <section>
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
                                    </div>
                                    <div class="tab-pane fade" id="jmeter">
                                        <div class="box box-primary  clearfix">
                                            <div class="f2_4 btn-group">
                                                <div class="btn btn-primary add3">新增</div>
                                                <%--<div class="btn btn-primary edit1">编辑</div>--%>
                                                <div class="btn btn-primary del3">删除</div>
                                            </div>
                                            <section>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="box-body tablecontent">
                                                            <div class="gridPanel">
                                                                <table id="list6"></table>
                                                                <div id="pager6"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的系统信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
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
            模板名称&nbsp;&nbsp;&nbsp;<input type="text" name="" id="sliname"  autocomplete="off"  style="width: 200px;"/>
            <input type="text" name="" id="slinameRd" style="display: none" autocomplete="off" />
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<%--<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/djpd/djpdinfo.js?v=1'></script>--%>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/qa/jyx/jymbinfo.js?v=2'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
