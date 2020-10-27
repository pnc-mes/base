<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/29
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>物料管理</title>

    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!--下拉框css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.css">
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/material/material.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/datacj/datacj.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/bom/bominfo.css">
</head>
<style>
  /*  .treeSelector-container{
        top: 5px;
    }*/
</style>
<body>
<div class="btn-group td1 table tabTop common">
    <div class="btn btn-primary cSelect">筛选</div>
    <%--<div class="btn btn-primary cAdd">新增</div>--%>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option22">新增
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a   class="text-center" role="menuitem" tabindex="-1" id="addRoot">新增物料</a></li>
            <%--<li role="presentation"><a href="#" class="text-center" role="menuitem" tabindex="-1"
                                       id="addChild">新增物料版本</a></li>--%>
        </ul>
    </div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1"
                                       id="remove">删除</a></li>
        </ul>
    </div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true" id="option2">选项
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" id="import" a><a class="text-center" role="menuitem" tabindex="-1">导入</a></li>
            <li role="presentation" id="export"><a  class="text-center" role="menuitem" tabindex="-1">导出</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="hidden1"  editid=""/>
<input type="hidden" id="hidden2"  editcode=""/>
<input type="hidden" id="hidden3"  editid=""/>
<div class="f2 clearfix zsyLeft1">
    <div class="left left1 zsyZi1" id="left2">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索物料类别">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div class="right" id="_right" style="display: none">
        <input type="hidden" id="ExecType"/>
        <form id="printForm">
            <input type="hidden" id="MaRd"/>
            <input type="hidden" id="MaVerRd"/>
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <%--处理表格--%>
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
                        <%--表格结束--%>
                        <ul class="nav nav-tabs">
                            <li class="active"  id="a_tab_1"><a href="#tab_1" data-toggle="tab" class="dymb">基本资料</a></li>
                            <li><a href="#tab_7" data-toggle="tab" id="MType" style="display: none">物料特性</a></li>
                            <li><a href="#tab_3" data-toggle="tab">物流信息</a></li>
                            <li id="a_tab_4"><a href="#tab_4" data-toggle="tab">工艺信息</a></li>
                            <li><a href="#tab_5" data-toggle="tab">替代料</a></li>
                            <li><a href="#tab_6" data-toggle="tab">包装规格</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo clearfix">
                                        <div class="block bBottom maLeft" style="float: left;width: 50%;">
                                            <div class=" formgroup maBottom">
                                                <label for="MaCode">物料代码</label>
                                                <input type="text" class="form-control check" id="MaCode"
                                                       name="MaCode" placeholder="" maxlength="30">
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="MaName">物料名称</label>
                                                <input type="text" class="form-control check" id="MaName"
                                                       name="MaName" placeholder="" maxlength="50">
                                            </div>
                                            <%--物料版本--%>
                                            <div class=" formgroup maBottom">
                                                <label for="materVersion">物料版本
                                                    <select id="materVersion" class="form-control" style="width: 196px;">
                                                    </select>
                                                    <input type="text" class="form-control check version" id="version" style="display: none" maxlength="30" />
                                                </label>
                                            </div>

                                            <div class=" formgroup maBottom">
                                                <label style="position:absolute;">物料描述</label>
                                                <textarea class="maTextarea"
                                                          style="width: 400px;height: 60px;position: absolute;left: 76px;"
                                                          id="MaDes" name="MaDes"  maxlength="255" onchange="this.value=this.value.substring(0, 255)" onkeydown="this.value=this.value.substring(0, 255)" onkeyup="this.value=this.value.substring(0, 255)"></textarea>
                                            </div>

                                            <%--物料家族--%>
                                            <div class=" formgroup maBottom" style="margin-top: 83px;">
                                                <label for="materFamily">物料家族
                                                </label>
                                                <div id="materFamily" data-id=""></div>
                                            </div>

                                            <%--物料家族--%>
                                            <div class=" formgroup maBottom" style="margin-top: 12px;">
                                                <label>企&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业</label>
                                                <div id="defaultSelect" data-id=""></div>
                                            </div>
                                        </div>
                                        <div style="float: right;width: 50%;">
                                            <div class="maBottom">
                                                <label class="maBottom">物料类别
                                                    <input type="hidden" id="MaTypeRd" name="MaTypeRd" />
                                                    <input id="type" name="type" autocomplete="off" style="width:196px;" maxlength="80"/>
                                                </label>
                                                <div id="t" style="position: absolute;z-index: 99999;width:197px;display:none;
                                                background: #FFF;margin-left: 60px;margin-top: -10px;border: 1px solid darkgray;
                                                height:150px;overflow: auto;"></div>
                                                <div id="MaType" data-id="" style="display: none;"></div>
                                            </div>
                                            <div class=" formgroup maBottom" style="margin-top: -9px;">
                                                <label for="MaBarCode">物料条码</label>
                                                <input type="text" class="form-control" id="MaBarCode"
                                                       name="MaBarCode" placeholder="" disabled>
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="UnitName">单&emsp;&emsp;位
                                                </label>
                                                <div id="UnitName" data-id=""></div>
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="SRName">序号规则</label>
                                                <div id="SRName" data-id=""></div>
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="wlbq">物料标签</label>
                                                <div id="wlbq" data-id=""></div>
                                            </div>
                                            <div class="maBottom">
                                                <label>状&emsp;&emsp;态</label>
                                                <select class="form-control selectControl" id="Status" name="Status" style="padding-left: 10px;width: 196px;">
                                                    <option value="00">可用</option>
                                                    <option value="01">不可用</option>
                                                </select>
                                            </div>
                                            <div class=" formgroup">
                                                <label for="maBatch" class="checkbox">
                                                    <input type="checkbox" class="baoliu7" id="maBatch"
                                                           name="maBatch">
                                                    启用批次
                                                </label>
                                                <label for="mianjian" class="checkbox" id="dd3" style="display: none;">
                                                    <input type="checkbox" class="baoliu7" id="mianjian"
                                                           name="IsExem">
                                                    免检
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_7">
                                <div class="box-body form-inline">
                                    <div class="printTInfo clearfix">
                                        <form id="ssform">
                                            <div class="block bBottom maLeft formdata">

                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_3">
                                <div class="box-body form-inline">
                                    <div class="printTInfo clearfix">
                                        <div class="block bBottom maLeft" style="float: left;width: 50%;">
                                            <div class=" formgroup maBottom">
                                                <label for="MaCode">最低库存</label>
                                                <input type="text" class="form-control  check num" id="MinSNum"
                                                           name="MinSNum" maxlength="10">

                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="MaCode">最大库存</label>
                                                <input type="text" class="form-control  check num" id="MaxSNum"
                                                       name="MaxSNum" maxlength="10">
                                            </div>

                                            <div class="block maBottom">
                                                <label for="Shelflife" class="checkbox tdl1" id="dd2">
                                                    <input type="checkbox" name="" value="" class="baoliu7"
                                                           id="Shelflife" name="Shelflife">
                                                    启用保质期管理
                                                </label>
                                                <input type="text" min="1" step="1" class="form-control" id="Interval"
                                                       name="Interval" placeholder="">
                                                <select class="form-control selectControl" id="SUnit">
                                                    <option value="00">年</option>
                                                    <option value="01">月</option>
                                                    <option value="02">周</option>
                                                    <option value="03">天</option>
                                                </select>
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="ReMaterial" class="checkbox tdl" id="dd4">
                                                    <input type="checkbox" class="baoliu7" id="ReMaterial"
                                                           name="ReMaterial">
                                                    是否允许被设置为替代料
                                                </label>
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="OrderNum">　订货号</label>
                                                <input type="text" class="form-control" id="OrderNum" name="OrderNum" placeholder="" maxlength="100">
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="Brand">品　　牌</label>
                                                <input type="text" class="form-control" id="Brand" name="Brand" placeholder="" maxlength="100">
                                            </div>
                                        </div>
                                        <div style="float: left;width: 50%;">
                                            <%--客户产品代码--%>
                                            <div class=" formgroup maBottom">
                                                <label for="customerPcode">客户产品代码
                                                    <input type="text" class="form-control" name="customerPcode" id="customerPcode" maxlength="30"/>
                                                </label>
                                            </div>
                                            <div class=" formgroup maBottom">
                                                <label for="SuName">　　制&ensp;造&ensp;商
                                                   <%-- <input type="text" class="form-control" name="SuName"
                                                           id="SuName" maxlength="80"/>--%>
                                                </label>
                                                <div id="SuName"  data-id=""></div>
                                            </div>
                                            <div class=" formgroup">
                                                <label for="SuMaCode">　　原厂代码
                                                    <input type="text" class="form-control" name="SuMaCode"
                                                           id="SuMaCode" maxlength="30"/>
                                                </label>
                                            </div>

                                            <ul>
                                                <%--<li>
                                                    <label for="distribution" class="checkbox tdl">　　
                                                        <input type="radio" name="DisRule" id="distribution" checked value="00">
                                                        按需配送
                                                    </label>
                                                </li>--%>
                                                <li>配送规则
                                                    <label for="minDistribution" class="checkbox tdl">　　
                                                        <input type="checkbox" name="DisRule" id="minDistribution">
                                                        按最小包装配送
                                                    </label>
                                                </li>
                                            </ul>

                                            <div class=" formgroup maBottom" id="zxbzsl" style="display: none;">
                                                <label for="MixPQ" class="checkbox tdl ">　　
                                                    最小包装数量
                                                    <input type="text" id="MixPQ" class="">
                                                </label>
                                            </div>


                                            <%--<label for="ReMaterial">　　最小包装
                                                <input type="text" class="form-control" id="MinPack" name="MinPack" disabled>
                                            </label>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_4" style="margin-top: 10px;">
                                <div class=" formgroup maBottom" style="display: inline-block">
                                    <label for="workflows" style="">工艺流程&nbsp</label>
                                    <div class="treeSelector"  id="workflows" style="display:inline-block;" ></div>
                                    <label for="bom">BOM清单
                                    </label>
                                    <div id="bom" data-id=""></div>
                                    <label id="bomCJ" style="display: none"><a href="javascript:void(0);">BOM层级</a></label>
                                </div>
                                <div class=" formgroup maBottom">

                                </div>
                                <div class=" formgroup maBottom">
                                    <label for="wlgkTime" style="">在线物料有效期管控&nbsp</label>
                                    <div  id="wlgkTime" style="display:inline-block;" ></div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_5">
                                <div class="f2_4 block" id="tidailiao">
                                    <div class="form-group ">
                                        <label>替代料</label>
                                        <div id="replaceMaInfo" data-id=""></div>
                                    </div>
                                </div>
                                <div class="f2_4 btn-group">
                                   <%-- <div class="btn btn-primary add2">新增</div>--%>
                                    <div class="btn btn-primary del2">删除</div>
                                </div>
                                <section>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="box-body tablecontent">
                                                <div class="gridPanel" style="padding-bottom: 0px;">
                                                    <table id="list5"></table>
                                                    <div id="pager5"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                            <div class="tab-pane" id="tab_6" style="margin-top: 10px;">
                                <div class=" formgroup maBottom">
                                    <label for="zhanban">栈板包装规格
                                    </label>
                                    <div id="zhanban" data-id=""></div>
                                </div>
                                <div class=" formgroup maBottom">
                                    <label for="bom">箱包包装规格
                                    </label>
                                    <div id="xiangbao" data-id=""></div>
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
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/material/materialinfo.js?v=5'></script>
<!--下拉框js-->
<script src="${pageContext.request.contextPath}/static/plugins/jstree/tree/jquery.treeSelector.js"></script>
</body>
</html>
