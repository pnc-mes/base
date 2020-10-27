<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/11/16
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AQL检验规则</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
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
        table tr{
            font-size: 13px;
            color: #333333;
        }
        thead th{
            font-size: 13px;
            color: #333333;
        }
        table th, .table td {
            text-align: center;
            vertical-align: middle!important;
        }
    </style>
</head>
<body>
<div class="btn-group table tabTop td1 common">
    <%--<div class="btn btn-primary sccd"  data-target="#myModal3" data-toggle="modal"></div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
           <%--  <li role="presentation"><a  class="text-center sccd" data-target="#myModal3" data-toggle="modal" role="menuitem" tabindex="-1">生成菜单</a></li>--%>

            <%--<li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1">生成菜单</a></li>--%>
            <%-- <div class="btn btn-primary sccd"  ></div>--%>
        </ul>
    </div>
    <%--<div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
                id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>--%>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class="zsyLeft" >
        <div class="left left1 zsyZi" id="left2" >
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索AQL检验规则">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　>>　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">基本信息</a></li>
                        <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active clearfix" id="tab_1">
                            <div class="box-body form-inline">
                                <div class="printTInfo">
                                    <div class=" formgroup disblock" style="padding-top: 20px">
                                        <label for="AQLRuleName">AQL 规则名称</label>
                                        <input type="text" id="AQLRuleName"/>
                                        <label>检验水平</label>
                                        <div id="defaultSelect" data-id=""></div><br/>
                                        <%----%>
                                    </div>
                                    <div id="myTabContent" class="tab-content" style="padding-top: 70px">
                                        <div class="tab-pane fade in active" id="home">

                                            <div class="btn btn-primary add1" data-target="#myModal1" data-toggle="modal">新增</div>
                                            <div class="btn btn-primary edit1" data-target="#myModal1" data-toggle="modal">编辑</div>
                                            <div class="btn btn-primary del1">删除</div>
                                            <div class="btn btn-primary up1">上移</div>
                                            <div class="btn btn-primary down1">下移</div>

                                            <div style="margin-top: 10px;width: 900px;height: 300px;margin-bottom: 100px;overflow: auto;border: 1px solid #A9A9A9">
                                                <table class="table table-striped table-bordered table-hover table-condensed">
                                                    <thead>
                                                    <tr>
                                                        <th style="width: 35px;"><input type="checkbox" id="check1"/></th>
                                                        <th style="width: 35px;">序号</th>
                                                        <th>批次最小数量</th>
                                                        <th>批次最大数量</th>
                                                        <th>抽检数量</th>
                                                        <th>合格数量</th>
                                                        <th>不合格数量</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="tbody"></tbody>
                                                </table>
                                            </div>
                                            <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="printTInfo">
                                                                <div class="form-group">
                                                                    <div style="width: 700px">
                                                                    <div class="form-group formgroup">
                                                                        <label for="liemingcheng1">批次最小数量</label>
                                                                        <input class="checkFloat" id="liemingcheng1" style="width: 100px;"/>
                                                                    </div>
                                                                    <div class="form-group formgroup " id="pwdhidden1" style="padding-right: 50px" >
                                                                        <label for="liebieming1">批次最大数量</label>
                                                                        <input type="number" id="liebieming1" style="width:100px;"/>
                                                                    </div>
                                                                    </div>
                                                                    <div style="width: 700px;padding-top: 20px">
                                                                    <div class="form-group formgroup " id="pwdhidden2" >
                                                                        <label for="SamplingNum">&ensp;&ensp;&ensp;&ensp;抽检数量</label>
                                                                        <input type="number" id="SamplingNum" style="width: 100px;"/>
                                                                    </div>
                                                                    <div class="form-group formgroup " id="pwdhidden3">
                                                                        <label for="AcceptNum">&ensp;&ensp;&ensp;&ensp;合格数量</label>
                                                                        <input type="number" id="AcceptNum" style="width: 100px;"/>
                                                                    </div>
                                                                    </div>
                                                                    <div style="width: 700px;padding-top: 20px">
                                                                    <div class="form-group formgroup " id="pwdhidden4" >
                                                                        <label for="RejectNum">&ensp;&ensp;不合格数量</label>
                                                                        <input type="number" id="RejectNum" style="width: 100px;"/>
                                                                    </div>
                                                                    </div>
                                                            </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" id="savadetail1">
                                                                确认
                                                            </button>
                                                            <button type="button" class="btn btn-default"
                                                                    data-dismiss="modal">关闭
                                                            </button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div>
                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                        <div class="tab-pane" id="tab_2">
                            <%-- 引入页面中的其他信息--%>
                            <%@include file="/WEB-INF/views/otherAll.jspf"%>
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
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/qa/jyx/aqlgzinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
