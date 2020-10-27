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
    <title>通用查询</title>
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
            <li role="presentation"><a  class="text-center sccd" data-target="#myModal3" data-toggle="modal" role="menuitem" tabindex="-1">生成菜单</a></li>

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
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索SQL配置">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class=" formgroup disblock">
                                            <label for="pzname">配置SQL名称</label>
                                            <input type="text" id="pzname"/><br/>
                                            <%----%>
                                        </div>

                                        <ul id="myTab" class="nav nav-tabs">
                                            <li class="active">
                                                <a href="#home" data-toggle="tab" id="chaxun">
                                                    查询设置
                                                </a>
                                            </li>
                                            <li><a href="#bgsz" data-toggle="tab" id="bgcx">表格设置</a></li>
                                            <li id="gg"><a href="#tjsz" data-toggle="tab" id="tjszs">统计设置</a></li>
                                            <li id="gg1"><a href="#tjmx" data-toggle="tab" style="display: none;" id="tjmxs">统计明细</a><i class="glyphicon glyphicon-remove-sign shutdown" id="endicon" style="margin-left: 70px;cursor: pointer;margin-top: -40px;display: none;"></i></li>


                                        </ul>
                                        <div id="myTabContent" class="tab-content">
                                            <div class="tab-pane fade in active" id="home">

                                                <p style="margin-top: 10px;">基本配置</p>
                                                <spna style="margin-left: 10px;">一行<input type="text" class="checkNumber" id="row1" style="width: 35px;margin-left: 10px;"/><span style="margin-left: -90px;">列&nbsp;&nbsp;&nbsp;当</span><input type="text" class="checkNumber" id="clum" style="width: 35px;margin-left: 10px;"/><span style="margin-left: -90px;">行出现伸缩面板</span></spna>

                                                <p style="margin-top: 10px;">查询条件字段</p>
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
                                                                <th>字段名称</th>
                                                                <th>字段别名</th>
                                                                <th>字段类型</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="tbody"></tbody>
                                                    </table>
                                                </div>


                                                <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-hidden="true">×
                                                                </button>
                                                                <h4 class="modal-title" id="myModalLabel1">

                                                                </h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="printTInfo">
                                                                    <div class="form-group">
                                                                            <div class="form-group formgroup">
                                                                                <label for="liemingcheng1">字段名称</label>
                                                                                <input type="text" id="liemingcheng1"/>
                                                                            </div>
                                                                            <div class="form-group formgroup " id="pwdhidden" style="float: right;margin-top: -30px;">
                                                                                <label for="liebieming1">字段别名</label>
                                                                                <input type="text" id="liebieming1" style="width: 160px;"/>
                                                                            </div>
                                                                    </div>
                                                                </div>
                                                                <label>字段类型</label>
                                                                <select id="select1" style="margin-top: 10px;width: 160px;">
                                                                    <option value="00">下拉框</option>
                                                                    <option value="01">文本框</option>
                                                                    <option value="02">日期段</option>
                                                                    <option value="03">时间段</option>
                                                                    <option value="04">多行文本</option>
                                                                </select>
                                                                <div id="aa" style="display: none;">
                                                                    <p style="color: red;">SQL脚本不能出现*，必须设置具体字段名称，而且只能设置两个输出字段</p>
                                                                    <div class="printTInfo">
                                                                        <div class="form-group">
                                                                            <div class="form-group formgroup" style="margin-left: -14px;">
                                                                                <label for="xsnezd">显示内容字段</label>
                                                                                <input type="text" id="xsnezd" style="width: 154px;"/>
                                                                            </div>
                                                                            <div class="form-group formgroup " style="float: right;margin-top: -30px;">
                                                                                <label for="liebieming1">字段值</label>
                                                                                <input type="text" id="zdz"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <p>SQL脚本</p>
                                                                    <textarea id="txtRea" style="margin: 0px; width:500px; height: 158px;"></textarea><br/>
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
                                            <div class="tab-pane fade" id="bgsz" style="margin-bottom: 100px;">
                                                <label for="pzsql" style="margin-top: 10px;">SQL脚本</label><br/>
                                                <textarea id="pzsql" style="margin: 0px; width:800px; height: 158px;"></textarea><br/>


                                                <ul id="myTab2" class="nav nav-tabs" style="margin-top: 20px">
                                                    <li class="active">
                                                        <a href="#c1" data-toggle="tab">
                                                            输出字段配置
                                                        </a>
                                                    </li>
                                                    <li><a href="#c2" data-toggle="tab">查询条件配置</a></li>

                                                </ul>


                                                <div id="myTabContent3" class="tab-content">
                                                    <div class="tab-pane fade in active" id="c1" style="margin-top: 10px;margin-bottom: 100px;">
                                                        <div class="btn btn-primary add2" data-target="#myModal5" data-toggle="modal">新增</div>
                                                        <div class="btn btn-primary edit2" data-target="#myModal5" data-toggle="modal">编辑</div>
                                                        <div class="btn btn-primary del2">删除</div>
                                                        <div class="btn btn-primary up2">上移</div>
                                                        <div class="btn btn-primary down2">下移</div>
                                                        <div style="margin-top: 10px;width: 900px;height: 300px;margin-bottom: 20px;overflow: auto;border: 1px solid #A9A9A9">
                                                            <table class="table table-striped table-bordered table-hover table-condensed">
                                                                <thead>
                                                                <tr>
                                                                    <th style="width: 35px;"><input type="checkbox"  id="check2"/></th>
                                                                    <th style="width: 35px;">序号</th>
                                                                    <th>字段名称</th>
                                                                    <th>字段别名</th>
                                                                    <th>内容类型</th>
                                                                    <th>是否显示</th>
                                                                    <th>宽度</th>
                                                                    <th>超过宽度截断提示</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody id="tbody5"></tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="c2" style="margin-top: 10px;margin-bottom: 100px;">
                                                        <div class="btn btn-primary add3" data-target="#myModal6" data-toggle="modal">新增</div>
                                                        <div class="btn btn-primary del3">删除</div>
                                                        <div style="margin-top: 10px;width: 900px;height: 300px;margin-bottom: 100px;overflow: auto;border: 1px solid #A9A9A9">
                                                            <table class="table table-striped table-bordered table-hover table-condensed">
                                                                <thead>
                                                                <tr>
                                                                    <th style="width: 35px;"><input type="checkbox" id="check3" /></th>
                                                                    <th>字段名称</th>
                                                                    <th>字段别名</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody id="tbody5s"></tbody>
                                                            </table>
                                                        </div>
                                                    </div>

                                                </div>



                                               <%-- <label for="pzsql" style="margin-top: 10px;">输出字段配置</label><br/>

                                                <p style="margin-top: 10px;">查询条件配置</p>--%>

                                                <!---->
                                                <div class="modal fade" id="myModal5" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-hidden="true">×
                                                                </button>
                                                                <h4 class="modal-title" id="myModalLabel5">

                                                                </h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="printTInfo">
                                                                    <label for="liemingcheng5" style="margin-left: 58px;">字段名称&nbsp;&nbsp;<input type="text" id="liemingcheng5"/></label>
                                                                    <label for="liebieming5" style="margin-left: 58px;margin-top: 6px;">字段别名&nbsp;&nbsp;<input type="text" id="liebieming5"/></label>
                                                                    <label for="select5" style="margin-left: 56px;">内容类型
                                                                        <select id="select5" style="margin-left: 5px;margin-top: 5px;width: 180px;">
                                                                            <option value="00">文本</option>
                                                                            <option value="01">链接</option>
                                                                        </select>
                                                                    </label><br/>
                                                                    <label for="isdefault" style="margin-left: 53px;">是否显示&nbsp;&nbsp;
                                                                        <input type="checkbox" id="isdefault"/> <span>显示</span>
                                                                    </label><br/>
                                                                    <label for="wight" style="margin-left: 80px;">宽度&nbsp;&nbsp;
                                                                        <input type="text" class="checkNumber" id="wight"/>
                                                                    </label><br/>
                                                                    <label for="tip" style="margin-left: -4px;">超过宽度截断提示&nbsp;&nbsp;
                                                                        <input type="checkbox" id="tip"/>是
                                                                    </label><br/>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-primary" id="savadetail5">
                                                                    确认
                                                                </button>
                                                                <button type="button" class="btn btn-default"
                                                                        data-dismiss="modal">关闭
                                                                </button>
                                                            </div>
                                                        </div><!-- /.modal-content -->
                                                    </div><!-- /.modal-dialog -->
                                                </div>



                                                <div class="modal fade" id="myModal6" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-hidden="true">×
                                                                </button>
                                                                <h4 class="modal-title" id="myModalLabel6">

                                                                </h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div style="margin-top: 10px;width: 503px;height: 300px;margin-bottom: 100px;overflow: auto;border: 1px solid #A9A9A9">
                                                                    <table class="table table-striped table-bordered table-hover table-condensed" style="width: 500px;">
                                                                        <thead>
                                                                        <tr>
                                                                            <th style="width: 35px;"><input type="checkbox" id="check4"/></th>
                                                                            <th>字段名称</th>
                                                                            <th>字段别名</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody id="tbody5s1"></tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-primary" id="savadetail6">
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
                                            <div class="tab-pane fade" id="tjsz">
                                                <div style="margin-top: 10px;">
                                                    <div class="btn btn-primary addjia">添加</div>
                                                    <div class="btn btn-primary addbianji">编辑</div>
                                                    <div class="btn btn-primary adddel">删除</div>
                                                </div>


                                                <div style="margin-top: 10px;width: 303px;height: 300px;margin-bottom: 100px;overflow: auto;border: 1px solid #A9A9A9">
                                                    <table class="table table-striped table-bordered table-hover table-condensed" style="width: 300px;">
                                                        <thead>
                                                        <tr>
                                                            <th style="width: 35px;"><input type="checkbox" id="check5"/></th>
                                                            <th>统计脚本名称</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="tbody5add"></tbody>
                                                    </table>
                                                </div>



                                            </div>
                                            <div class="tab-pane fade" id="tjmx" >
                                                <div style="margin-top: 10px;">
                                                    <div class="btn btn-primary saveend" >保存</div> <div  style="margin-left: 10px;" class="btn btn-primary shutdown" >关闭</div>
                                                </div>
                                                <p style="color: red;margin-top: 10px;">查询脚本配置确保输出的数据是单行数据，如果输出多行数据，查询页面将无法显示统计数据</p>
                                                <div class="printTInfo">
                                                    <label for="liemingcheng5" >配置名称&nbsp;&nbsp;<input type="text" id="pzmc"/></label><br/>
                                                    <label>SQL脚本</label><br/>
                                                    <textarea id="sqltext" style="margin: 0px; width:500px; height: 158px;"></textarea><br/>

                                                    <ul id="myTab1" class="nav nav-tabs" style="margin-top: 20px;">
                                                        <li class="active">
                                                            <a href="#b1" data-toggle="tab">
                                                                输出字段配置
                                                            </a>
                                                        </li>
                                                        <li><a href="#b2" data-toggle="tab">
                                                            查询条件配置
                                                        </a></li>

                                                    </ul>

                                                    <div id="myTabContent1" class="tab-content">
                                                        <div class="tab-pane fade in active" id="b1" style="margin-top: 10px;margin-bottom: 100px;">
                                                            <div class="btn btn-primary add7" data-target="#myModal7" data-toggle="modal">新增</div>
                                                            <div class="btn btn-primary edit7" data-target="#myModal7" data-toggle="modal">编辑</div>
                                                            <div class="btn btn-primary del7">删除</div>
                                                            <div class="btn btn-primary up7">上移</div>
                                                            <div class="btn btn-primary down7">下移</div>
                                                            <div style="margin-top: 10px;width: 900px;height: 300px;margin-bottom: 20px;overflow: auto;border: 1px solid #A9A9A9">
                                                                <table class="table table-striped table-bordered table-hover table-condensed">
                                                                    <thead>
                                                                    <tr>
                                                                        <th style="width: 35px;"><input type="checkbox" id="check6"/></th>
                                                                        <th style="width: 35px;">序号</th>
                                                                        <th>字段名称</th>
                                                                        <th>输出内容</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody id="tbody7"></tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                        <div class="tab-pane fade" id="b2" style="margin-top: 10px;">
                                                            <div class="btn btn-primary add8" data-target="#myModal8" data-toggle="modal">新增</div>
                                                            <div class="btn btn-primary del8">删除</div>
                                                            <div style="margin-top: 10px;width: 900px;height: 300px;margin-bottom: 100px;overflow: auto;border: 1px solid #A9A9A9">
                                                                <table class="table table-striped table-bordered table-hover table-condensed">
                                                                    <thead>
                                                                    <tr>
                                                                        <th style="width: 35px;"><input type="checkbox" id="check7"/></th>
                                                                        <th>字段名称</th>
                                                                        <th>字段别名</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody id="tbody8"></tbody>
                                                                </table>


                                                            </div>
                                                        </div>

                                                    </div>



<%--
                                                    <label for="pzsql" style="margin-top: 10px;">输出字段配置</label><br/>

                                                    <p style="margin-top: 10px;">查询条件配置</p>--%>

                                                    <div class="modal fade" id="myModal7" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal"
                                                                            aria-hidden="true">×
                                                                    </button>
                                                                    <h4 class="modal-title" id="myModalLabel7">

                                                                    </h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="printTInfo">
                                                                        <label for="liemingcheng7" style="margin-left: 58px;">字段名称&nbsp;&nbsp;<input type="text" id="liemingcheng7"/></label>
                                                                        <label for="liebieming5" style="margin-left: 58px;margin-top: 6px;">输出内容&nbsp;&nbsp;<input type="text" id="liebieming7"/></label>

                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-primary" id="savadetail7">
                                                                        确认
                                                                    </button>
                                                                    <button type="button" class="btn btn-default"
                                                                            data-dismiss="modal">关闭
                                                                    </button>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div>
                                                    <div class="modal fade" id="myModal8" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal"
                                                                            aria-hidden="true">×
                                                                    </button>
                                                                    <h4 class="modal-title" id="myModalLabel8">

                                                                    </h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="printTInfo">
                                                                        <label for="liemingcheng7" style="margin-left: 58px;">列名称&nbsp;&nbsp;<input type="text" id="liemingcheng8"/></label>
                                                                        <label for="liebieming5" style="margin-left: 58px;margin-top: 6px;">列别名&nbsp;&nbsp;<input type="text" id="liebieming8"/></label>

                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-primary" id="savadetail8">
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


                                       <%-- <div class=" formgroup disblock" style="width: 1000px;height: 450px;margin-bottom: 100px;">

                                            <div style="float: left;width: 500px;height: 450px;">
                                                <div style="float: left;width: 400px;height: 450px;">
                                                    <label>表格列名称配置</label>
                                                    <div style="width: 400px;height: 450px;border: 1px solid #000000;" >
                                                        <ul id="addul">

                                                        </ul>
                                                    </div>
                                                    &lt;%&ndash;<section style="width: 400px;height: 450px;">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="box-body tablecontent">
                                                                    <div class="gridPanel">
                                                                        <table id="list4"></table>
                                                                        <div id="pager4">
                                                                            &lt;%&ndash;<ul id="list4ul"></ul>&ndash;%&gt;
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </section>&ndash;%&gt;
                                                </div>
                                                <div style="float:right;width: 100px;height: 450px; margin-left: -20px;margin-bottom: 100px;margin-top: 66px;">
                                                    <div class="f2_4 btn-group" >

                                                    </div>
                                                    <div class="f2_4 btn-group" >

                                                    </div>
                                                    <div class="f2_4 btn-group" >

                                                    </div>
                                                    <div class="f2_4 btn-group">

                                                    </div>
                                                    <div class="f2_4 btn-group">

                                                    </div>
                                                </div>

                                            </div>

                                            <div style="float: right;width: 500px;height: 450px;">
                                                <div style="float: left;width: 400px;height: 450px;">
                                                    <label>查询条件配置</label>
                                                    <section style="width: 400px;height: 450px;">
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
                                               &lt;%&ndash; <div style="float:right;width: 180px;height: 450px; margin-left: 80px;margin-top: -460px;">&ndash;%&gt;
                                                    <div style="float:right;width: 100px;height: 450px; margin-left: 80px;margin-top: -380px;">

                                                    <div class="f2_4 btn-group" >
                                                        <div class="btn btn-primary add2" data-target="#myModal2" data-toggle="modal">新增</div>
                                                    </div>

                                                    <div class="f2_4 btn-group" >
                                                        <div class="btn btn-primary edit2" data-target="#myModal2" data-toggle="modal">编辑</div>
                                                    </div>
                                                    <div class="f2_4 btn-group" >
                                                        <div class="btn btn-primary del2" >删除</div>
                                                    </div>
                                                    <div class="f2_4 btn-group">
                                                        <div class="btn btn-primary up2">上移</div>
                                                    </div>
                                                    <div class="f2_4 btn-group">
                                                        <div class="btn btn-primary down2">下移</div>
                                                    </div>
                                                    </div>
                                                </div>
                                            </div>--%>
                                        </div>



                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
    <!--list4表格-->
   <!-- /.modal -->
    <!--list5表格-->
    <div class="modal fade" id="myModal2"   tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">

                    </h4>
                </div>
                <div class="modal-body">
                    <div class="printTInfo">
                        <label for="liemingcheng2" style="margin-left: 27px;">列名称&nbsp;&nbsp;<input type="text" id="liemingcheng2"/></label>
                        <label for="liebieming2" style="margin-left: 27px;">列别名&nbsp;&nbsp;<input type="text" id="liebieming2"/></label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="savadetail2">
                        确认
                    </button>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>

                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!--生成菜单-->
    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabe3">
                           生成菜单
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="printTInfo">
                        <label for="sccd" style="margin-left: 27px;">菜单名称&nbsp;&nbsp;<input type="text" id="sccd"/></label>
                    </div>

                    <table id="table"
                           style="border:0;cellspacing:0;cellpadding:0;font-size: 15px"
                           class="table2 table-hover">
                        <tbody id="tbodynew">
                        </tbody>
                    </table>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="savadetail3">
                        确认
                    </button>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>

                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/sys/tycx/tycxinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
