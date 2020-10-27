<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/8/28
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>工位管理</title>
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
   <style>
       #cc ul{
           height: 150px;
       }
       #ul ul{
           width: 100px;
           height: 180px;
           border: 1px solid #D3D6DA;
           overflow:auto;
           margin-left: 20px;
       }
       #ul ul li{
           list-style: none;
           width: 70px;
           border-bottom: 1px dotted #D3D6DA;
           margin: 5px 0 0 -25px;
       }
       #ul1 ul{
           width: 140px;
           height: 210px;
           border: 1px solid #D3D6DA;
           overflow:auto;
           margin-left: 20px;
       }
       #ul1 ul li{
           list-style: none;
           width: 100px;
           border-bottom: 1px dotted #D3D6DA;
           margin: 5px 0 0 -25px;
       }
       #d{
           width: 300px !important;
       }
      /* #user{
            width: 0px !important;
       }*/
       .disblock{
           margin-top: -5px;
       }
       #xt .floor1Top{
           width: 100px !important;
       }
       #xt .inpSearch{
           width: 100px !important;
       }
       #devs .floor1Top{
           width: 150px !important;
       }
       #devs .inpSearch{
           width: 150px !important;
       }
       #dy .floor1Top{
           width: 200px !important;
       }
       #dy .inpSearch{
           width: 200px !important;
       }
       #sc .form-control{
           width: 196px;
       }
       #tab_sb .form-control{
           width: 150px;
       }
       #b .form-control{
           width: 100px;
       }
       #bb .form-control{
           width: 196px;
       }
   </style>
</head>
<body>
<div class="btn-group common table tabTop td1">
    <%--<div  class="btn btn-primary cSelect">筛选</div>--%>
    <div  class="btn btn-primary cAdd">新增</div>
    <div  class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a id="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class='zsyLeft'>
        <div class="left left1 zsyZi" id="left2" >
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索工位">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right">
        <form id="customerinfo">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">工位信息</a></li>
                            <li><a href="#tab_3" data-toggle="tab">打印机信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content" >
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class=" formgroup disblock" id="sc" style="margin-top: 0px">
                                            <label for="StationName">&emsp;&emsp;&nbsp;&nbsp;&nbsp;工位名称&nbsp;&nbsp;</label>
                                            <input type="text" class="form-control check" id="StationName"
                                                   name="StationName" placeholder="" maxlength="40">
                                            <label for="scsb" style="margin-left: -80px">生产设备&nbsp;&nbsp;</label>
                                            <select  class="form-control selectpicker" id="scsb" title="请选择生产设备" data-live-search="true" multiple style="width: 150px">
                                            </select>
                                            <!--<div id="glsb" data-id="" id="glsb"></div>-->
                                        </div>
                                        <div class=" formgroup disblock" id="b">
                                            <label for="xt">&emsp;&emsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线体&nbsp;&nbsp;</label>
                                            <%--<select  class="form-control selectpicker" id="xt" title="请选择线体" data-live-search="true" data-max-options="1" multiple style="width: 250px">

                                            </select>--%>
                                            <input type="text" name="" id="xt" disabled autocomplete="off"  style="width: 200px;"/>
                                            <input id="saveLineRd" style="display: none">
                                            <!--<div id="xt" data-id="" id="xt"></div>-->
                                            <div style="display: inline" id="bb">
                                            <label for="gx" style="margin-left: 28px">&emsp;&nbsp;工序&nbsp;&nbsp;</label>
                                            <select  class="form-control selectpicker" id="gx" title="请选择工序" data-live-search="true" data-max-options="1" multiple style="width: 150px">

                                            </select>
                                            </div>
                                            <!--<div id="gx" data-id="" id="gx"></div>-->
                                            <label for="status">&emsp;&nbsp;&nbsp;状态&nbsp;&nbsp;</label>
                                            <select id="status" style="width: 60px;">
                                                <option value="00">启用</option>
                                                <option value="01">禁用</option>
                                            </select>
                                        </div>
                                        <div class=" formgroup disblock" >
                                            <label for="status">&emsp;&emsp;执行实体类型&nbsp;&nbsp;</label>
                                            <input type="radio"  name="sb" id="shebei" style="margin-left: 13px;"/><label for="shebei">设备</label>
                                            <input type="radio"  name="sb" id="cxzyr" style="margin-left: 13px;"/><label for="cxzyr">产线作业人</label>
                                            <input type="radio"  name="sb" id="caidan" style="margin-left: 13px;"/><label for="caidan">绑定菜单</label>
                                        </div>
                                        <div class=" formgroup disblock" style="width: 350px" id="cc">
                                            <div class="form-group disblock" id="show" >
                                                <label for="sbsbz">&emsp;&emsp;&nbsp;&nbsp;&nbsp;设备/设备组&nbsp;&nbsp;</label>
                                                <div id="d" style="margin-left: 120px;margin-top: -26px;">
                                                    <select  class="form-control selectpicker" id="sbsbz" title="请选择设备设备组" data-live-search="true" multiple >
                                                        <optgroup label="设备" id="sb"></optgroup>
                                                        <optgroup label="设备组" id="sbz"></optgroup>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group disblock" style="display: none" id="hide">
                                                <label for="cxzyrdatas">&emsp;&emsp;产线作业人</label>&emsp;
                                                <%--<div id="cxzyrdata" data-id="" id="cxzyrdata"></div>--%>
                                                <div style="margin-left: 120px;margin-top: -26px;">
                                                    <select class="form-control selectpicker" id="cxzyrdatas" title="请选择产线作业人" data-live-search="true" multiple >
                                                        <optgroup label="产线作业人" id="sncxzyrdatas"></optgroup>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group disblock" style="display: none" id="hide1">
                                                <label for="xtcaidanDatas">&emsp;&emsp;系统菜单</label>&emsp;
                                                <div style="margin-left: 120px;margin-top: -26px;">
                                                    <select class="form-control selectpicker" id="xtcaidanDatas" title="请选择系统菜单" data-live-search="true" multiple >
                                                        <optgroup label="产线作业人" id="snxtcaidanDatas"></optgroup>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--页签-->
                                <div class="nav-tabs-custom">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_gz" data-toggle="tab" class="gzpz">过站配置</a></li>
                                        <li class="" id="tab_sxl1" style="position: relative;">
                                            <a href="#tab_sxl" data-toggle="tab" class="sxl">上下料配置</a>
                                        </li>
                                        <li class="" id="tab_sb1" style="position: relative;">
                                            <a href="#tab_sb" data-toggle="tab" class="sztdl">设备集成</a>
                                        </li>
                                        <li class="" id="tab_jb1" style="position: relative;">
                                            <a href="#tab_jb" data-toggle="tab" class="jb">警报配置</a>
                                        </li>
                                        <li class="" id="tab_jk1" style="position: relative;">
                                            <a href="#tab_jk" data-toggle="tab" class="jb">接口集成</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content" style="margin-top: 10px;height: 300px">
                                        <!--过站配置-->
                                        <div class="tab-pane active clearfix" id="tab_gz">

                                            <div class=" formgroup disblock" style="margin-bottom: 0;">
                                                <label for="status">&emsp;&emsp;作业行为&nbsp;&nbsp;</label>
                                                <input type="checkbox" id="Start" style="margin-left: 20px;"/><label for="Start">进站</label>
                                                <input type="checkbox" id="Up" style="margin-left: 20px;"/><label for="Up">上机</label>
                                                <input type="checkbox" id="Down" style="margin-left: 20px;"/><label for="Down">下机</label>
                                                <input type="checkbox" id="End" style="margin-left: 20px;"/><label for="End">出站</label>
                                                <input type="checkbox" id="CarrierUnBind" style="margin-left: 20px;"/><label for="CarrierUnBind">载具解绑</label>
                                                <input type="checkbox" id="PrintLabel" style="margin-left: 20px;"/><label for="PrintLabel">打印标签</label>
                                            </div>

                                            <div class=" formgroup disblock"  style="margin-top: 5px" >
                                                <label for="status">&emsp;&emsp;作业载体&nbsp;&nbsp;</label>
                                                <input type="radio" name="123" id="CarrierType00" style="margin-left: 20px" value="01"/>载具
                                                <input type="radio" name="123" id="CarrierType01" style="margin-left: 20px" value="00"/>产品实体
                                            </div>

                                        </div>
                                        <!--上下料配置-->
                                        <div class="tab-pane clearfix" id="tab_sxl">
                                            <div class=" formgroup disblock">
                                                <input type="radio" name="UDMType" id="UDMType0" checked="checked"  style="margin-left: 50px;" value="00"/>上料
                                                <input type="radio" name="UDMType" id="UDMType1" style="margin-left: 20px;" value="01"/>下料
                                                <button type="button" id="addUDMType" style="margin-left: 20px">添加</button>
                                            </div>
                                            <div class=" formgroup disblock" style="margin-top: -10px;height: 70px">
                                                <label id="sl" style="margin-left: 0px">&emsp;上料载体&nbsp;</label>
                                                <label id="xl" style="display: none">&emsp;下料载体&nbsp;</label>
                                                <input type="radio" name="UDMCarrierType" id="UDMCarrierType0" style="margin-left: 0px" value="00"/>载具
                                                <input type="radio" name="UDMCarrierType" id="UDMCarrierType1" style="margin-left: 20px;" value="01"/>材料实体
                                                <div id="xlfw" style="display: none">
                                                    <div style="float: left">&emsp;下料范围&nbsp;</div>
                                                    <div style="margin-left: 78px;">
                                                        <input type="radio" name="DMArea" id="DMArea0" style="margin-left: 0px" value="00"/>全部下料<br/>
                                                        <input type="radio" name="DMArea" id="DMArea1" checked="checked"  style="margin-left: 0px;margin-top: 5px" value="01"/>只下料当前作业人或设备上的料
                                                    </div>
                                                </div>
                                            </div>
                                            <div class=" formgroup disblock" style="">
                                                <div  id="ul" style="float: left;">
                                                    <ul id="li">
                                                    </ul>
                                                </div>
                                                <div style="margin-left: 130px">
                                                    <button type="button" id="sy" style="margin-top: 0px;">上移</button> <br>
                                                    <button type="button" id="xy" style="margin-top: 15px">下移</button> <br>
                                                    <button type="button" id="del" style="margin-top: 15px">删除</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--设备集成-->
                                        <div class="tab-pane clearfix" id="tab_sb">
                                            <div class=" formgroup disblock"  >
                                                <label  for="devs" style="margin-left: 5px">&emsp;&emsp;设备</label>&emsp;
                                                <select  class="form-control selectpicker" id="devs" title="请选择设备" data-live-search="true" data-max-options="1" multiple style="width: 150px">

                                                </select>
                                                <!--<div id="devs" data-id="" style="margin-left: -10px"></div>  -->
                                                <label >&emsp;&emsp;映射设备</label>&emsp;
                                                <select id="sys"  style="width: 150px;margin-left: -10px">
                                                </select>
                                                <label >&emsp;触发点</label>&emsp;
                                                <select id="cfd"  style="width: 80px;margin-left: -10px">
                                                    <option value="00">进站成功</option>
                                                    <option value="01">上机成功</option>
                                                    <option value="02">下机成功</option>
                                                    <option value="03">出站成功</option>
                                                    <option value="04">进站失败</option>
                                                    <option value="05">上机失败</option>
                                                    <option value="06">下机失败</option>
                                                    <option value="07">出站失败</option>
                                                    <option value="08">漏刷</option>
                                                </select>
                                            </div>
                                            <div class=" formgroup disblock" style="margin-top: -10px">
                                                <div style="float: left">
                                                    <label for="zxzl">&emsp;执行指令</label>
                                                    <input id="zxzl" maxlength="40" style="width: 80px;margin-left: 5px">
                                                </div>
                                                <div style="float: left;margin-left: 68px;width: 150px">
                                                    <label style="margin-left: -80px">&emsp;指令类型</label>&emsp;
                                                    <select id="s2" style="width: 150px;margin-left: -10px">
                                                        <option value="00">开关常量</option>
                                                        <option value="01">开关变量</option>
                                                    </select>
                                                </div>
                                                <div style="width: 800px;">
                                                    <label style="margin-left: 10px">指令值&ensp;</label>
                                                    <select id="s3" style="width: 50px">
                                                        <option id="k" value="00">开</option>
                                                        <option id="g" value="01">关</option>
                                                        <option id="c" style="display: none" value="02">产品实体</option>
                                                    </select>
                                                </div>
                                                <button type="button" id="add" style="margin-left: 650px;margin-top: -30px;float: left">添加</button>


                                            </div>
                                            <div class=" formgroup disblock" style="margin-top: -10px;margin-left: 20px;">
                                                <button class="btn btn-primary" type="button" id="del1" style="margin-bottom: 10px;" >删除</button>
                                                <div style="height: 150px;overflow-y: scroll">
                                                    <table id="list" style=" border:#D3D6DA;overflow-y: scroll" border='1' >
                                                        <tr>
                                                            <td style='width: 30px;text-align:center;'><input type="checkbox" id="zd"></td>
                                                            <td style='width: 150px;text-align:center;'>设备</td>
                                                            <td style='width: 150px;text-align:center;'>映射设备</td>
                                                            <td style='width: 100px;text-align:center;'>触发点</td>
                                                            <td style='width: 100px;text-align:center;'>执行指令</td>
                                                            <td style='width: 100px;text-align:center;'>指令类型</td>
                                                            <td style='width: 100px;text-align:center;'>指令值</td>
                                                        </tr>
                                                        <tr id="zwsj"><td colspan="7" style="text-align:center;">暂无数据</td></tr>
                                                    </table>
                                                </div>
                                                <div id="pager"></div>
                                            </div>
                                        </div>
                                            <!--警报配置-->
                                        <div class="tab-pane clearfix" id="tab_jb">
                                            <div style="float: left">
                                                <div class=" formgroup disblock" >
                                                    <label >&emsp;&emsp;异常行为</label>&emsp;
                                                    <select id="ExcepType"  style="width: 80px">
                                                        <option value="00">漏刷</option>
                                                        <option value="01">进站失败</option>
                                                        <option value="02">上机失败</option>
                                                        <option value="03">下机失败</option>
                                                        <option value="04">出站失败</option>
                                                        <option value="05">上料失败</option>
                                                        <option value="06">接口集成失败</option>
                                                    </select>
                                                </div>
                                                <div class=" formgroup disblock" style="margin-top: -15px" >
                                                    <label>&emsp;&emsp;警报方式</label>
                                                    <input type="radio" name="AlarmType" id="AlarmType0" checked="checked" style="margin-left: 20px" value="00"/>弹屏
                                                </div>
                                                <div class=" formgroup disblock" style="margin-top: -20px;width: 350px" >
                                                    <label>&emsp;警报接收者</label>
                                                    <input type="radio" name="AlarmRev" id="AlarmRev0" checked="checked" style="margin-left: 20px" value="00"/>全部
                                                    <input type="radio" name="AlarmRev" id="AlarmRev1" style="margin-left: 20px;" value="01"/>指定人员
                                                    <div id="d1" style="margin-left: 180px;margin-top: 10px;display: none" >
                                                        <select class="form-control selectpicker" id="user" title="请选择指定人员" data-live-search="true" multiple >

                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="formgroup disblock" style="float: left;width: 400px;margin-left: 35px">

                                                <div  id="ul1" style="float: left">
                                                    <button type="button" id="adds" style="margin-top: 80px;width:40px;" >>></button>
                                                    <ul id="li1" style="margin-left: 60px;margin-top: -100px">
                                                    </ul>
                                                </div>
                                                <div style="margin-left: 250px;">
                                                    <button type="button" id="dels" style="margin-left: -40px;margin-top: 6px">删除</button>
                                                </div>
                                            </div>
                                        </div>

                                        <!--接口集成-->
                                        <div class="tab-pane clearfix" id="tab_jk">
                                            <div class=" formgroup disblock"  >
                                                <label  for="jkdz" style="margin-left: 5px">接口地址</label>&emsp;
                                                <input id="jkdz" type="text" style="width: 250px;margin-right: 0px">
                                                <label >&emsp;触发点</label>&emsp;
                                                <select id="cfd1"  style="width: 80px;margin-left: -10px">
                                                    <option value="00">进站成功</option>
                                                    <option value="01">上机成功</option>
                                                    <option value="02">下机成功</option>
                                                    <option value="03">出站成功</option>
                                                    <option value="04">进站失败</option>
                                                    <option value="05">上机失败</option>
                                                    <option value="06">下机失败</option>
                                                    <option value="07">出站失败</option>
                                                    <option value="08">上料成功</option>
                                                    <option value="09">下料成功</option>
                                                    <option value="10">上料失败</option>
                                                    <option value="11">下料失败</option>
                                                    <option value="12">进站前</option>
                                                    <option value="13">上机前</option>
                                                    <option value="14">下机前</option>
                                                    <option value="15">出站前</option>
                                                </select>
                                            </div>

                                            <div class=" formgroup disblock" style="margin-top: -10px">
                                                <div style="float: left;margin-left: 68px;width: 180px">
                                                    <label style="margin-left: -75px">&emsp;内置参数</label>&emsp;
                                                    <select id="nzcs" style="width: 150px;margin-left: 0px">
                                                        <option value="00">产品实体</option>
                                                        <option value="01">工位ID</option>
                                                        <option value="02">两者都有</option>
                                                    </select>

                                                </div>
                                            </div>
                                            <div class=" formgroup disblock" style="margin-top: -10px;margin-left: 20px;">
                                                <button class="btn btn-primary" type="button" id="add2" style="margin-bottom: 10px;">添加</button>

                                                <div  >
                                                    <button class="btn btn-primary" type="button" id="del2" style="margin-bottom: 10px;" >删除</button>
                                                    <div class="gridPanel">
                                                        <table id="list4"></table>
                                                        <div id="pager4"></div>
                                                    </div>
                                                </div>
                                                <div id="pager2"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_3">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class=" formgroup disblock" id="dy">
                                            <label for="prints">打印机</label>
                                            <%--<div id="print" data-id="" id="print"></div>--%>
                                            <select  class="form-control selectpicker" id="prints" title="请选择打印机" data-live-search="true" data-max-options="1" multiple style="width: 300px">

                                            </select>

                                            <label for="PrintTs">&emsp;&emsp;打印模板</label>
                                            <%--<div id="PrintT" data-id="" id="PrintT"></div>--%>
                                            <select  class="form-control selectpicker" id="PrintTs" title="请选择打印模板" data-live-search="true" data-max-options="1" multiple style="width: 300px">

                                            </select>

                                            <label for="PrintCopy">&emsp;&emsp;打印份数</label>
                                            <input id="PrintCopy" type="number" style="width: 50px" value="1">
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
        </form>
    </div>

</div>
<!--data-backdrop="static" data-keyboard="false"  bootstrap 空白处禁止关闭-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 400px;height: 200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    复制工位信息
                </h4>
            </div>
            <div class="modal-body">
                拷贝线体&nbsp;&nbsp;&nbsp;<input type="text" name="" id="sliname" disabled autocomplete="off"  style="width: 200px;"/>
                <input type="text" name="" id="slinameRd" style="display: none" autocomplete="off" />
            </div>
            <div class="modal-body">
                目标线体&nbsp;&nbsp;
                <select  class="form-control selectpicker" id="tlinameRd" title="请选择线体" data-live-search="true" data-max-options="1" multiple style="width: 200px">
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/gw/gwinfo.js?v=1'></script>
</body>
</html>
