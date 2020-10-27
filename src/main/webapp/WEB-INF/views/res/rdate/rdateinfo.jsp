<%--
  Created by IntelliJ IDEA.
  User: 乔
  Date: 2018/7/23
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>周期性保养</title>
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
    <style>
        .inpSearch{
            width: 150px  !important;

        }
        .floor1Top{
            width: 150px !important;
        }
    </style>
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
            <li role="presentation"><a  class="text-center cDel" role="menuitem" tabindex="-1">删除</a>
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
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索周期性保养">
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
                                            <div class="form-group width20">
                                                <label>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;名称&nbsp;&nbsp;</label>
                                                <input style="width: 150px;" type="text" class="form-control" id="CyclePlanName"
                                                       name="CyclePlanName" >
                                            </div>
                                            <div class="form-group width40">
                                                <label>状态&nbsp;&nbsp;</label>
                                                <select id="status" class="form-control check" style="width: 150px;padding: 1px 6px;">
                                                    <option  value="00">可用</option>
                                                    <option  value="01">不可用</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group width180" style="margin-top: 20px">
                                            <label>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;描述&nbsp;&nbsp;</label>
                                            <input style="width: 700px" type="text" class="form-control" id="Description"
                                                   name="Description" >
                                        </div>
                                        <div class="manWidth f1"  style="margin-top:20px;">
                                            <div class="form-group width20">
                                                <label>&ensp;&ensp;&ensp;&ensp;&ensp;保养原因&nbsp;&nbsp;</label>
                                                <div id="byyy" data-id=""></div>
                                            </div>
                                            <div class="form-group width20" style=" margin-left: 45px;">
                                                <label >保养标准作业书&nbsp;&nbsp;</label>
                                                <div id="bybzzys" data-id=""></div>
                                            </div>
                                        </div>
                                        <div class="manWidth f1"  style="margin-top:10px;">
                                            <div class="form-group width30">
                                                <label>&ensp;&ensp;&ensp;&ensp;&ensp;起算日期&nbsp;&nbsp;</label>
                                                <input style="width: 150px" type="date" class="form-control" id="startdata"
                                                      >
                                            </div>
                                            <div class="form-group width30">
                                                <label >&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;截止日期&nbsp;&nbsp;</label>
                                                <input style="width: 150px" type="date" class="form-control" id="enddata"
                                                     >
                                            </div>
                                        </div>
                                        <div>
                                        <div class="manWidth f1"  style="margin-top:10px;">
                                            <div class="form-group width30">
                                                <label>&ensp;&ensp;&ensp;&ensp;&ensp;保养周期&nbsp;&nbsp;</label>
                                                <select id="byzq" name="MessageFormat" class="form-control check" style="width: 150px;padding: 1px 6px;">
                                                    <option  value="00">每日</option>
                                             <%--       <option  value="01">季度</option>
                                                    <option  value="02">每周</option>
                                                    <option  value="03">每月</option>
                                                    <option  value="04">每年</option>
                                                    <option  value="05">指定日期</option>--%>
                                                </select>
                                            </div>
                                            <div class="form-group" id="meiridiv" style="width: 40%;">
                                                <div class="form-group " id="zdsjdiv" style="margin-left: 30px">
                                                    <label for="zdsj"><input id="zdsj" type="radio"  value="00"  name="radio" /><span style="margin-left: -16px;">指定时间</span></label>
                                                </div>

                                                <div class="form-group" id="jgsjdiv" style="margin-left: 20px;">
                                                    <label for="jgsj"><input id="jgsj" type="radio" value="01"  name="radio" /><span style="margin-left: -16px;">间隔时间</span></label>
                                                </div>
                                                <div class="form-group width20" id="iddiv" style="margin-left: 8px;">
                                                    <label>时间</label>
                                                    <input type="time" id="TimeContent" name="StartTime">
                                                </div>
                                                <div class="form-group width20" id="jgsjdivs">
                                                    <label>时间</label>
                                                    <input id="jgsjvalue" type="text" style="width: 77px" value="1"/><span style="margin-left: -14px;">小时</span>
                                                </div>

                                                <div class="form-group width20" id="xqjdiv" style="display: none">
                                                    <select id="xqj">
                                                        <option value="00">星期一</option>
                                                        <option value="01">星期二</option>
                                                        <option value="02">星期三</option>
                                                        <option value="03">星期四</option>
                                                        <option value="04">星期五</option>
                                                        <option value="05">星期六</option>
                                                        <option value="06">星期天</option>
                                                    </select>
                                                </div><br/>
                                               <%-- <div class="form-group width20" id="iddiv" style="margin-top: 10px">

                                                    &lt;%&ndash;<select id="" name="MessageFormat" class="form-control check" style="width: 150px;padding: 1px 6px;">
                                                        <option  value="0">09:00</option>
                                                        <option  value="1">09:00</option>
                                                    </select>&ndash;%&gt;

                                                    &lt;%&ndash;<input id="TimeContent" type="text" width="100px"/>&ndash;%&gt;
                                                </div>--%>



                                            </div>

                                            <div class="form-group width30" id="jidudiv" style="display: none; width: 600px;margin-left: 70px;">
                                                <div class="form-group width20">
                                                    <label>季度&nbsp;&nbsp;</label>
                                                    <select id="jidu" style="width: 100px;height: 28px;">
                                                        <option value="1">第一季度</option>
                                                        <option value="2">第二季度</option>
                                                        <option value="3">第三季度</option>
                                                        <option value="4">第四季度</option>
                                                    </select>
                                                </div>
                                                <div class="form-group width20">
                                                    <label>月份</label>
                                                    <select id="yuefen"  style="width: 100px;height: 28px;">

                                                    </select>
                                                </div>
                                                <div class="form-group width20">

                                                    <select id="hao" style="width: 100px;height: 28px;">
                                                    </select>号
                                                </div>
                                            </div>
                                            <div class="form-group width30" id="meizhoudiv" style="display: none;">
                                                <div class="form-group width20" style="margin-left: 60px;">
                                                    <label>星期几&nbsp;</label>
                                                    <select id="meizhou" style="width: 100px;">
                                                        <option value="00">星期一</option>
                                                        <option value="01">星期二</option>
                                                        <option value="02">星期三</option>
                                                        <option value="03">星期四</option>
                                                        <option value="04">星期五</option>
                                                        <option value="05">星期六</option>
                                                        <option value="06">星期天</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group width30" id="meiyuediv" style="display: none;margin-left: 110px;">
                                                <select id="yuehao" style="width: 78px;">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                    <option value="6">6</option>
                                                    <option value="7">7</option>
                                                    <option value="8">8</option>
                                                    <option value="9">9</option>
                                                    <option value="10">10</option>
                                                    <option value="11">11</option>
                                                    <option value="12">12</option>
                                                </select>&nbsp;&nbsp;月
                                            </div>
                                            <div class="form-group width30" id="meiniandiv" style="display: none;margin-left: 109px;">
                                                <select id="meiniandivyue" style="width: 100px;height: 28px;">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                    <option value="6">6</option>
                                                    <option value="7">7</option>
                                                    <option value="8">8</option>
                                                    <option value="9">9</option>
                                                    <option value="10">10</option>
                                                    <option value="11">11</option>
                                                    <option value="12">12</option>
                                                </select>月
                                                <select id="meiniandivhao" style="width: 100px;height: 28px;">

                                                </select>号
                                            </div>
                                            <div class="form-group width30" id="zhidingriqi" style="display: none;margin-left: 46px;">
                                                <label>保养日期&nbsp;</label>
                                                <input style="width: 150px" type="date" class="form-control" id="zdri">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="manWidth f1"  style="margin-top:10px;">

                                            <div class="form-group width30" id="tixingshijian">
                                            <%--    <label >&ensp;容许下限公差&nbsp;&nbsp;</label>
                                                <input style="width: 77px" type="text"  class="form-control checkFloat" id="rxxxgc"
                                                       name="Description" ><span style="margin-left: -14px;">小时</span>--%>
                                                <label >&ensp;&ensp;&ensp;&ensp;&ensp;提醒时间&nbsp;&nbsp;</label>
                                                <input style="width: 77px" type="type" class="form-control checkFloat" id="xtsj"
                                                       name="Description" ><span style="margin-left: -14px;">小时</span>
                                            </div>
                                            <div class="form-group width30" style="margin-left: 14px;" id="yanqishijian">
                                                <label >延期时间&nbsp;&nbsp;</label>
                                                <input style="width: 77px" type="text" class="form-control checkFloat" id="rxsxgc"
                                                       name="Description" ><span style="margin-left: -14px;">小时</span>
                                            </div>
                                        </div>
                                     <%--   <div class="manWidth f1"  style="margin-top:20px;">

                                                <label >&ensp;&ensp;&ensp;&ensp;&ensp;提醒时间&nbsp;&nbsp;</label>
                                                <input style="width: 77px" type="type" class="form-control checkFloat" id="xtsj"
                                                       name="Description" ><span style="margin-left: -14px;">小时</span>

                                        </div>--%>
                                    </div>
                                    <div class="form-group width30" style="padding-top: 20px">
                                        <label style="padding-left: 21px;">&ensp;参考信息</label>
                                        <div style="margin-top: -23px;padding-left: 96px">
                                                <textarea style="width: 330px;  height: 80px;margin-top: -7px;"
                                                          id="Reference" name="Reference"></textarea>
                                        </div>

                                    </div>
                                </div>
                                <div>

                                    <div class="tab-pane" id="tab_4" style="margin-top: 20px">

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
                                            <section style="margin-top: -18px;">
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
                                    <div class="manWidth f1"  style="margin-top:10px;">
                                        <div>
                                            <label style="font-size:15px;"><strong>超过提醒</strong></label>
                                        </div>
                                        <div class="form-group width30">
                                            <label>&ensp;&ensp;邮件通知组&nbsp;&nbsp;</label>
                                            <div id="expiremailgroup"></div>
                                        </div>
                                        <div class="form-group width30">
                                            <label >邮件内容&nbsp;&nbsp;</label>
                                            <div  id="expiremailcontent"></div>
                                        </div>
                                        <%--<div class="form-group width30">
                                            <label >过期采取行动&nbsp;&nbsp;</label>
                                            <select id="gqcqxd" style="width: 107px;">
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
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/res/rdate/rdateinfo.js?v=2"></script>
</body>
</html>
