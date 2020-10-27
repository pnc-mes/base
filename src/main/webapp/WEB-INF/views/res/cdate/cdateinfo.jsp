<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>按次保养计划</title>
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
            <li role="presentation"><a   class="text-center cDel" role="menuitem" tabindex="-1">删除</a>
            </li>
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
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索按次保养">
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
                                        <div class="manWidth f1"  style="margin-top:10px;">
                                            <div class="form-group width30">
                                                <label>&nbsp;&nbsp;&nbsp;&nbsp;名称&nbsp;&nbsp;</label>
                                                <input type="text" class="form-control" id="FrePlanName"
                                                       name="FrePlanName" >
                                            </div>
                                            <div class="form-group width30">
                                                <label>&nbsp;&nbsp;状态&nbsp;&nbsp;</label>
                                                <select id="status" class="form-control check" style="width: 150px;padding: 1px 6px;">
                                                    <option  value="00">可用</option>
                                                    <option  value="01">不可用</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group width180" style="margin-top: 20px">
                                            <label>&nbsp;&nbsp;&nbsp;&nbsp;描述&nbsp;&nbsp;</label>
                                            <input style="width: 700px" type="text" class="form-control" id="Description"
                                                   name="Description" >
                                        </div>
                                        <div class="form-group"  style="margin-top:20px;width: 100%;">
                                            <div class="form-group width30">
                                                <label>保养原因&nbsp;&nbsp;</label>
                                                <div id="byyy" data-id=""></div>
                                            </div>
                                            <div class="form-group" >
                                                <label>保养标准作业书&nbsp;&nbsp;</label>
                                                <div id="bybzzys" data-id=""></div>
                                            </div>
                                        </div>
                                        <div class="manWidth f1"  style="margin-top:10px;">
                                            <div class="form-group width30">
                                                <label>使用量&nbsp;&nbsp;</label>
                                                <input type="number" style="width: 150px" class="form-control" id="UseNum"
                                                       onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                            </div>
                                            <div class="form-group width30">
                                                <label >单位&nbsp;&nbsp;</label>
                                                <select style="width: 150px;padding: 1px 6px;" class="form-control check" id="unit" >
                                                    <option value="00">次</option>
                                                </select>

                                            </div>
                                        </div>

                                        <div class="manWidth f1"  style="margin-top:10px;">
                                      <%--      <div class="manWidth f1"  style="margin-top:20px; margin-bottom:20px; ">--%>
                                          <div class="form-group width30">
                                                <label >&nbsp;&nbsp;提醒&nbsp;&nbsp;</label>
                                                <input  style="width: 150px"  class="form-control" id="xtsj" name="Description"  type="number"
                                                        onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                          </div>
                                           <%-- </div>--%>
                                        <%--    <div class="form-group width30">
                                                <label >容许上限公差&nbsp;&nbsp;</label>
                                                <input type="number" style="width: 150px" type="text"class="form-control checkFloat" id="rxsxgc"
                                                       name="Description"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                                            </div>--%>
                                            <div class="form-group width30">
                                                <label >&ensp;超过&nbsp;&nbsp;</label>
                                                <input type="number" style="width: 150px" type="number"type="text" class="form-control checkFloat" id="rxxxgc"
                                                       name="Description" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" >
                                            </div>

                                        </div>
                                        <div class="form-group width30" style="padding-top: 20px">
                                            <label style="padding-left: 21px;">&ensp;参考信息</label>
                                            <div style="margin-top: -23px;padding-left: 96px">
                                                <textarea style="width: 330px;  height: 80px;margin-top: -7px;"
                                                          id="Reference" name="Reference"></textarea>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div>

                                    <div class="tab-pane" id="tab_4" style="margin-top: 10px">

                                        <div class="boxx">
                                            保养内容清单&nbsp;&nbsp;

                                            <select class="selectpicker" multiple data-live-search="true" id="bynrqd" title="请选择保养内容清单">

                                            </select>

                                            <div class="f2_4 btn-group" style="margin-bottom: 17px;">
                                                <div class="btn btn-primary adduser">添加&nbsp;&nbsp;</div>
                                            </div>
                                            <div class="f2_4 btn-group" style="margin-bottom: 17px;">
                                                <div class="btn btn-primary deluser">删除&nbsp;&nbsp;</div>
                                            </div>
                                            <section style="margin-top: -10px">
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
                                <div style="margin-bottom: 200px">
                                    <div class="manWidth f1"  style="margin-top:10px;">
                                        <div>
                                            <label style="font-size:15px;"><strong>提前提醒</strong></label>
                                        </div>
                                        <div class="form-group width30">
                                            <label>&ensp;&ensp;邮件通知组&nbsp;&nbsp;</label>
                                            </label><div id="goaheademail"></div>
                                        </div>
                                        <div class="form-group width30">
                                            <label >邮件内容&nbsp;&nbsp;</label>
                                            <div  id="goaheademailcontent"></div>
                                        </div>
                                    </div>
                                    <div class="manWidth f1"  style="margin-top:10px;">
                                        <div>
                                            <label style="font-size:15px;"><strong>到期提醒</strong></label>
                                        </div>
                                        <div class="form-group width30">
                                            <label>&ensp;&ensp;邮件通知组&nbsp;&nbsp;</label>
                                            <div id="daoqiGroup"></div>
                                        </div>
                                        <div class="form-group width30">
                                            <label >邮件内容&nbsp;&nbsp;</label>
                                            <div id="daoqiContent"></div>
                                        </div>
                                    </div>
                                    <div class="manWidth f1"  style="margin-top:10px;"style="width: 100%">
                                        <div>
                                            <label style="font-size:15px;"><strong>超过提醒</strong></label>
                                        </div>
                                        <div class="form-group " >
                                            <label>&ensp;&ensp;邮件通知组&nbsp;&nbsp;</label>
                                            <div id="expiremailgroup"></div>
                                        </div>
                                        <div class="form-group width30">
                                            <label >&ensp;邮件内容&nbsp;&nbsp;</label>
                                            <div  id="expiremailcontent"></div>
                                        </div>
                                        <%--<div class="form-group">
                                            <label >过期采取行动&nbsp;&nbsp;</label>
                                            <select id="gqcqxd" style="width:80px;">
                                                <option value="00">Down</option>
                                                <option value="01">No Event</option>
                                            </select>
                                        </div>--%>
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
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/res/cdate/cdateinfo.js?v=2"></script>
</body>
</html>
