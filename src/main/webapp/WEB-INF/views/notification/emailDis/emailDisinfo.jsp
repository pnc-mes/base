<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>邮件分发组</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
</head>
<body>

<div class="btn-group table tabTop aa td1 ">
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>

    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<input type="hidden" id="hidden" name="hidden" h=""/>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<div class="f2 clearfix">
    <div class="zsyLeft">
        <div class="left zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索邮件分发组名称">
             <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <form class="form-inline" id="storeInfo">
        <div class="right" id="_right" style="display: none;">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">基本信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="form-inline">
                                    <div class="printTInfo">
                                        <div class="manWidth f1">
                                            <div class="form-group " style="margin-top: 15px">
                                                <label>邮件分发组名称</label>
                                                <input type="text" class="form-control" id="EmailDisName"
                                                       name="EmailDisName"  maxlength="60" >
                                            </div><br/>
                                            <div class="form-group formgroup biaodan1 hangji" style="margin-top: 15px;">
                                                <label >描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;述</label>
                                                <textarea style="width: 300px;height: 40px;" id="deception" maxlength="255" onchange="this.value=this.value.substring(0, 255)" onkeydown="this.value=this.value.substring(0, 255)" onkeyup="this.value=this.value.substring(0, 255)">

                                            </textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>

                                    <div class="form-group formgroup biaodan1 hangji" style="margin-top: 15px;">
                                        <div class="boxx">
                                            <div class="tab-pane" id="tab_4">
                                                <div id="Buyers" data-id=""></div>
                                                <label>收件人-员工清单

                                                    <div class="f2_4 btn-group" >
                                                        <div class="btn btn-primary adduser">添加</div>
                                                    </div>
                                                    <div class="f2_4 btn-group">
                                                        <div class="btn btn-primary deluser">删除</div>
                                                    </div>
                                                </label>
                                            <section style="margin-top: -10px;">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="box-body tablecontent">
                                                            <div>
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
                                <div>
                                    <div class="form-group formgroup biaodan1 hangji" style="margin-top: 15px;">
                                        <div class="boxx">
                                            <div id="Roles" data-id=""></div>
                                            <label>收件人-角色清单


                                                <div class="f2_4 btn-group" >
                                                    <div class="btn btn-primary addrole" >添加</div>
                                                </div>
                                                <div class="f2_4 btn-group" >
                                                    <div class="btn btn-primary delrole">删除</div>
                                                </div>
                                            </label>
                                            <section style="margin-top: -10px;">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="box-body tablecontent">
                                                            <div>
                                                                <table id="list5"></table>
                                                                <div id="pager5"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>

                                        </div>
                                    </div>
                                    <div class="form-group formgroup biaodan1 hangji" style="margin-top: 15px;">

                                        <div class="boxx">
                                            <div class="tab-pane" id="tab_6">
                                                <label>外部人员邮箱地址清单
                                                    <div class="f2_4 btn-group" >
                                                        <div class="btn btn-primary otheradds">添加</div>
                                                    </div>
                                                    <div class="f2_4 btn-group" >
                                                        <div class="btn btn-primary otherdel">删除</div>
                                                    </div>
                                                </label>
                                            <section style="margin-top: -10px;">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="box-body tablecontent">
                                                            <div>
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

                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                    <%@include file="/WEB-INF/views/otherAll.jspf" %>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/base/emailDis/emailDisinfo.js?v=1"></script>
</body>
</html>
