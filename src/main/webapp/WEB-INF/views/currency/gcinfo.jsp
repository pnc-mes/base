<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/8/24
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全局配置</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <!-- 树开始 -->
<%--    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>--%>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/mprocess/specOpert/specOpert.css">
    <!--jqgrid表格样式 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
   <%-- <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">--%>
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <style>
        table{
            font-size: 12px;
        }
       .table>thead>tr>th,.table>tbody>tr>td{
           padding: 0px;
           padding-left:10px;
           font-size: 14px;
           padding-top:10px;
       }
       .table-bordered>thead>tr>th{
           border-bottom:1px;
       }
       tr{
           border:1px solid #f4f4f4;
       }
       .message{
           margin-left:10px;
       }
       .contents{
           margin-top:6px;
       }
   </style>
</head>
<body>
<div class="btn-group td1 table tabTop common">
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="nav-tabs-custom mtop30 col-sm-12">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#tab_3" data-toggle="tab" >参数配置</a></li>
        <li><a href="#tab_4" data-toggle="tab" >代码生成规则</a></li>
        <li><a href="#tab_5" data-toggle="tab" >数据同步控制</a></li>
        <li><a href="#tab_6" data-toggle="tab" >通知服务</a></li>
        <li><a href="#tab_7" data-toggle="tab" >邮件服务器</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active clearfix" id="tab_3">
            <div class="col-sm-12 contents">
                <div class="bBottom1">模式</div>
                <table class="table table-bordered table-hover" id="list4">
            <thead>
                <tr>
                    <th>模式项</th>
                    <th colspan="2"></th>
                </tr>
            </thead>
            <tbody>
                <tr></tr>
                <tr>
                    <td class="ModeName" id="M1">原材料入库依据</td>
                    <td colspan="2" class="ModeValue" id="M1Val">
                        <label><input type="radio" class="radio1" name="standard" value="M1_00" disabled/>以采购单为标准</label>
                        <label><input type="radio" class="radio2" name="standard" value="M1_01" checked disabled/>以来料收货通知单为准</label>
                    </td>
                </tr>
                <tr>
                    <td class="ModeName" id="M2">工序扣料方式</td>
                    <td colspan="2" class="ModeValue" id="M2Val">
                        <label><input type="radio" class="radio3" name="standard1" value="M2_00"/>以工单扣减</label>
                        <label><input type="radio" class="radio4" name="standard1" value="M2_01"/>以批次扣除</label>
                        <label id="m2l" style="display: none;"><input type="radio" class="radio5" name="standard1" value="M2_02"/>关闭扣料</label>
                    </td>
                </tr>
                <tr>
                    <td class="ModeName" id="M3">上机装料模式</td>
                    <td colspan="2" class="ModeValue" id="M3Val">
                        <label><input type="radio" class="radio5" name="standard2" value="M3_00"/>足量上机</label>
                        <label><input type="radio" class="radio6" name="standard2" value="M3_01"/>缺料上机</label>
                    </td>
                </tr>
                <tr>
                    <td class="ModeName" id="M4">产品创批</td>
                    <td colspan="2" class="ModeValue" id="M4Val">
                        <label><input type="radio" class="radio7" name="standard3" value="M4_00"/>工单下达创批</label>
                        <label><input type="radio" class="radio8" name="standard3" value="M4_01"/>手动创批</label>
                    </td>
                </tr>
                <tr>
                    <td class="ModeName" id="M5">在制品结束点</td>
                    <td colspan="2" class="ModeValue" id="M5Val">
                        <label><input type="radio" class="radio9" name="standard5" value="M5_00"/>工艺流程结束</label>
                        <label><input type="radio" class="radio10" name="standard5" value="M5_01"/>入库完成</label>
                    </td>
                </tr>
                <tr id="M6_" style="display: none;">
                    <td class="ModeName" id="M6">工艺流程验证</td>
                    <td colspan="2" class="ModeValue" id="M6Val">
                        <label><input type="radio" class="radio11" name="standard6" value="M6_00"/>打开</label>
                        <label><input type="radio" class="radio12" name="standard6" value="M6_01"/>关闭</label>
                    </td>
                </tr>
            </tbody>
        </table>
                <div class="biaoqian">消息</div>
                <label class="message"><input id="C1" type="checkbox" class="Msg" name="MsgValue"/>物料创建批次成功通知执行人员</label>
            </div>
        </div>
        <div class="tab-pane  clearfix" id="tab_4">
            <div class="f2_4 btn-group">代码自动生成规则：代码前缀+时间+序号</div>
                    <div class="box-body form-inline">
                        <section class="content">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body tablecontent">
                                        <div class="gridPanel">
                                            <table id="list3"></table>
                                            <div id="pager3"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
        </div>
        <div class="tab-pane  clearfix" id="tab_5">
            <div class="col-sm-12 contents">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th style="width: 300px;">控制项</th>
                        <th colspan="2">启用</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr></tr>
                    <tr>
                        <td class="SynchName" id="RawM_Storage">原材料入库数据同步</td>
                        <td colspan="2" class="ModeValue" id="RawM_StorageVal">
                            <label><input type="CheckBox" class="CheckBox1" name="standard" value="RawM_Storage" /></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="SynchName" id="PickOut_Storage">领料出库数据同步</td>
                        <td colspan="2" class="ModeValue" id="PickOut_StorageVal">
                            <label><input type="CheckBox" class="CheckBox2" name="standard1" value="PickOut_Storage"/></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="SynchName" id="RetMa_Storage">退料数据同步</td>
                        <td colspan="2" class="ModeValue" id="RetMa_StorageVal">
                            <label><input type="CheckBox" class="CheckBox3" name="standard2" value="RetMa_Storage"/></label>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="tab-pane  clearfix" id="tab_6">
            <div class="col-sm-12 contents">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th style="width: 300px;">通知服务</th>
                        <th colspan="2">启用</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr></tr>
                    <tr>
                        <td class="SynchName">保养通知</td>
                        <td colspan="2" class="ModeValue">
                            <label><input type="CheckBox"  id="SERVICE_PM" class="CheckBox1" name="standard" /></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="SynchName">点检通知</td>
                        <td colspan="2" class="ModeValue">
                            <label><input type="CheckBox"  id="SERVICE_DJ" class="CheckBox2" name="standard1"/></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="SynchName">在线物料过期通知</td>
                        <td colspan="2" class="ModeValue">
                            <label><input type="CheckBox" class="CheckBox3" name="standard2" id="SERVICE_WLGQ"/></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="SynchName">最小时间管控通知</td>
                        <td colspan="2" class="ModeValue">
                            <label><input type="CheckBox" class="CheckBox3" name="standard2"  id="SERVICE_ZXSJ"/></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="SynchName">最大时间管控通知</td>
                        <td colspan="2" class="ModeValue">
                            <label><input type="CheckBox" class="CheckBox3" name="standard2" id="SERVICE_ZDSJ"/></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="SynchName">数据采集异常通知</td>
                        <td colspan="2" class="ModeValue">
                            <label><input type="CheckBox" class="CheckBox3" name="standard2"  id="SERVICE_CJYC"/></label>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <%--熊伟--%>

        <div class="tab-pane  clearfix" id="tab_7" style="height: 182px;">
            <div class="box-body form-inline">
                <div class="printTInfo">
                    <div class="formgroup disblock" >

                        <label>SMTP服务器地址&nbsp;&nbsp;</label>
                        <input type="text"  style="width: 200px" class="form-control" id="SMTPURL"
                               name="SMTPURL" >

                        <label>端口&nbsp;&nbsp;</label>
                        <input type="text"  style="width: 200px" class="form-control checkNumber" id="port"
                               name="PORT" >
                    </div>

                    <div class="formgroup disblock"  style="margin-left: 32px;">
                        <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名&nbsp;&nbsp;</label>
                        <input type="text"  class="form-control" id="UserName"
                               name="UserName" >
                        <label >&nbsp;密码&nbsp;&nbsp;</label>
                        <input type="text" class="form-control" id="Password"
                               name="Password">
                    </div>
                    <div class="formgroup disblock" style="margin-left: 113px;" >
                        &ensp;<label><input type="checkbox" id="UseSSL" name="UseSSL">启用SSL</label>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/currency/gcinfo.js?v=1'></script>
</body>
</html>
