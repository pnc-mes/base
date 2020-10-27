<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/6/27
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<media name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"></media>
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<html>
<head>
    <title>出库确认单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/workflow.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/jsplumbtoolkit-defaults.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/jsplumbtoolkit-demo.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/workflow/demo.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/zTreeFuzzySearch-master/css/metro.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
    <style type="text/css">
        ul.ztree {
            margin-top: 10px;
            border: 1px solid #617775;
            width: 160px;
            height: 450px;
            overflow-y: scroll;
            overflow-x: auto;
        }
        div.zTreeDemoBackground {
            width: 160px;
            height: 450px;
            text-align: left;
        }

        span.search_highlight {
            color: whitesmoke;
            background-color: darkred;
        }
    </style>


    <!-- 引入ztree插件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/zTreeFuzzySearch-master/js/jquery.ztree.all.min.js"></script>
    <!-- 引入ztree插件扩展功能 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/zTreeFuzzySearch-master/js/jquery.ztree.exhide.min.js"></script>
    <!-- 引入自定义方法 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/zTreeFuzzySearch-master/js/fuzzysearch.js"></script>
</head>
<body>
<div class="btn-group table tabTop td1">
    <input type="hidden" id="ExecType" value=""/>

    <div class="btn btn-primary cSelect">筛选</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
             aria-expanded="true" id="option1">操作

        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li role="presentation" ><a  class="text-center" role="addOff" id="export" tabindex="-1">导出</a>
            <li role="presentation" ><a  class="text-center" role="addOff" id="addOff" tabindex="-1">取消</a>
            </li>
        </ul>
    </div>
    <div class="btn btn-primary cSave00">换货</div>
    <div class="btn btn-primary cSave01">确定</div>
    <button class="btn btn-primary _close" id="close">关闭</button>

</div>
<div class="f2 clearfix">
    <div class="left zsyZi1 left1" id="left2" style="width: 200px;">
        <input class="input1 form-control" type="search" name="" value="" placeholder="搜索出库确认单号">
        <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
        <div><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　<<　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　>>　</a></div>
        <!--树的处理-->
        <div id="jstree_demo1"></div>
    </div>
    <div id="_right" class="right" style="margin-left: 230px;">
        <form id="printForm">

            <input type="hidden" id="WfRd" name="WfRd"/>
            <input type="hidden" id="WfVerRd" name="WfVerRd"/>

            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">出库确认单信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline" style="margin-bottom: 10px;">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <label for="QCheckMaCode">出库确认单号</label>
                                            <input type="text" class="form-control" id="QCheckMaCode"
                                                   name="QCheckMaCode" placeholder="" maxlength="40" readonly>
                                        </div>

                                        <div class="form-group formgroup" style="margin-left: -20px;">
                                            <label for="StockMaCode">备货单号</label>
                                            <input type="text" class="form-control" id="StockMaCode"
                                                   name="StockMaCode" placeholder="" maxlength="40" readonly>
                                        </div>

                                        <div class="form-group" style="margin-left: -15px" readonly>
                                            <label>状态</label>
                                            <select id="Status" style="width: 119px;"  disabled="disabled" >
                                                <option value="00">待检验</option>
                                                <option value="01">取消</option>
                                                <option value="02">完成</option>
                                                <option value="03">批退</option>
                                                <option value="04">已出库</option>
                                            </select>
                                        </div>
                                        <%--<div class="form-group">
                                            <label for="QCheckMaType">请检单类型</label>
                                            <select id="QCheckMaType" style="width: 139px;"  disabled="disabled" >
                                                <option value="00">生产入库请检</option>
                                                <option value="01">销售出库请检</option>
                                            </select>
                                        </div>
--%>
                                    </div>
                                    <div class="form-group aa">

                                        <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认结果</label>
                                        <label for="hege">
                                            <input style="margin-left: 10px;" id="hege" type="radio"  value="00"  name="radio" />
                                            <span style="margin-left: 2px;">合格</span>
                                        </label>
                                        <label for="buhege">
                                            <input style="margin-left: 15px;" id="buhege" type="radio" value="01"  name="radio" />
                                            <span  style="margin-left: 2px;">不合格</span>
                                        </label>

                                        <div class="form-group formgroup" style="margin-left: 125px">
                                            <label for="creatTime">生成时间</label>
                                            <input type="text" class="form-control" id="creatTime"
                                                   name="creatTime" placeholder="" style="width: 180px;" readonly>
                                        </div>

                                        <label for="creatPeople" style="margin-left: -28px">创建人</label>
                                        <input type="text" class="form-control" id="creatPeople"
                                               name="creatPeople" placeholder="" style="width: 119px;" readonly>

                                        <%--<input list="status" name="Status" id="status_"
                                               class="form-control selectControl check"/>
                                        <datalist id="status">
                                            <option id="use" label="00" value="可用"></option>
                                            <option id="unuse" label="01" value="不可用"></option>
                                        </datalist>--%>
                                    </div>
                                    <div class="printTInfo">
                                        <div class="form-group formgroup" style="padding-left: 45px;padding-top: 20px">
                                            <label for="Remark">备注</label>
                                            <div style="margin-top: -23px; padding-left: 37px">
                                            <textarea rows="2" cols="40" type="text" class="form-control" id="Remark"
                                                   name="Remark" placeholder="" maxlength="40" readonly>
                                                </textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--右侧所有-->
                                <div class="clearfix rightcontent" style="height: 1200px;">
                                    <!--右侧的左侧（树）-->
                              <%--      <div class="left left1 " id="kzZsy">
                                        <!--右侧的搜索栏-->
                                        <input class="input2 form-control" type="search" name="" value=""
                                               placeholder="请输入箱号/组件号">
                                        <img class="searchTree2" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
                                        <div class="pages1"><a title="上一页" id="prev1" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next1" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
                                        </div>
                                        <div id="jstree_demo3"></div>
                                    </div>--%>
                                    <div style="width: 600px">
                                        <input id="keyword" type="search" placeHolder="请输入箱号/组件号"/>
                                    </div>
                                    <div class="content_wrap">
                                        <div class="zTreeDemoBackground left" style="height: 1090px;">
                                            <ul id="book" class="ztree" style="height: 1090px;"></ul>
                                        </div>
                                    </div>
                                    <br />
                                    <div><small></small></div>
                                    <!--右侧的右侧-->
                                    <div class=" right1" style="overflow-x:hidden;margin-left:170px;width:89%;height:100%">
                                        <div id="main">
                                            <div class="manWidth f1" >
                                                <%-- <div class="">--%>
                                                <span>
                                                <label calss="no3" >箱号/组件号</label>
                                                    <label id="xianghao" style="width: 80px"></label>
                                                    <label id="barType" style="width: 80px; display: none"></label>
                                                    <label calss="no3" style="padding-left: 20px">&ensp;&ensp;&emsp;功率&ensp;</label>
                                                    <label id="gonglv" style="width: 110px"></label>
                                                    <label calss="no3" style="padding-left: 10px">&ensp;&ensp;&emsp;电流&ensp;</label>
                                                    <label id="dianliu" style="width: 40px"></label>
                                                    <label calss="no3" style="padding-left: 10px">&ensp;&ensp;&emsp;颜色&ensp;</label>
                                                    <label id="yanse" style="width: 40px"></label>
                                                    <label calss="no3" style="padding-left: 10px">&ensp;&ensp;&emsp;综合等级&ensp;</label>
                                                    <label id="zonghedengji" style="width: 30px"></label>
                                                    </span>
                                                <br>
                                                <span>
                                                    <label calss="no3" style="padding-left: 30px">&ensp;&ensp;数量</label>
                                                    <label id="num" style="width: 18px"></label>
                                                    <label calss="no3" style="padding-left: 100px">&ensp;&ensp;装箱类型</label>
                                                    <label id="MixedBag" style="width: 50px"></label>
                                                    <label calss="no3" style="padding-left: 60px">&ensp;&ensp;识别码</label>
                                                    <label id="ShbCode" style="width: 18px"></label>
                                                    </span>
                                            </div>
                                            <ul id="myTab2" class="nav nav-tabs" style="margin-top: 20px">
                                                <li class="active">
                                                    <a href="#c1" data-toggle="tab">
                                                        IV数据
                                                    </a>
                                                </li>
                                                <li><a href="#c2" data-toggle="tab">电池片信息</a></li>

                                            </ul>

                                            <div id="myTabContent3" class="tab-content">
                                                <div class="tab-pane fade in active" id="c1" style="margin-top: 10px;margin-bottom: 100px;">
                                                    <div style="margin-top: 10px;width: 800px;height: 1000px;margin-bottom: 100px;overflow: auto;border: 1px solid #A9A9A9">
                                                        <table class="table table-striped table-bordered table-hover table-condensed" style="width: 800px">
                                                            <thead>
                                                            <tr>
                                                                <th style="width: 50px;">组件条码</th>
                                                                <%-- <th width="100">Eta</th>--%>
                                                                <th style="width: 50px;">Isc</th>
                                                                <th width="100">Uoc</th>
                                                                <th style="width: 50px;">Impp</th>
                                                                <th style="width: 50px;">Umpp</th>
                                                                <th style="width: 50px;">Pmpp</th>
                                                                <th style="width: 50px;">FF</th>
                                                                <%--<th width="100">Tcell</th>
                                                                <th width="100">Tmonicell</th>
                                                                <th width="100">Rser</th>
                                                                <th width="100">Rshunt</th>
                                                                <th width="100">Insol</th>--%>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="tbody1"></tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="tab-pane fade" id="c2" style="margin-top: 10px;margin-bottom: 100px;">
                                                 <%--   <div class="btn btn-primary add3" data-target="#myModal6" data-toggle="modal">新增</div>
                                                    <div class="btn btn-primary del3">删除</div>--%>
                                                     <div style="margin-top: 10px;width: 900px;height: 1000px;margin-bottom: 100px;overflow: auto;border: 1px solid #A9A9A9">
                                                        <table class="table table-striped table-bordered table-hover table-condensed" style="width: 900px">
                                                            <thead>
                                                            <tr>
                                                               <%-- <th style="width: 35px;"><input type="checkbox" id="check3" /></th>--%>
                                                                   <th>组件条码</th>
                                                                   <th>颜色</th>
                                                                   <th>单多晶</th>
                                                                   <th>电池片效率</th>
                                                                   <th>工厂</th>
                                                                   <th>电池等级</th>
                                                                   <th>终检等级</th>
                                                                   <th>外观不良</th>
                                                                   <th>EL不良</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="tbody"></tbody>
                                                        </table>
                                                    </div>
                                                </div>

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
        </form>
    </div>

    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 400px;height: 500px;">
            <div class="modal-content" >
                <div class="modal-header">

                </div>
                <div class="modal-body">
                    确认单号&nbsp;&nbsp;&nbsp;<input type="text" name="" id="sliname2" disabled  autocomplete="off"  style="width: 200px;"/>
                    <input type="text" name="" id="slinameRd2" style="display: none" autocomplete="off" readonly />
                </div>
                <div class="form-group " style="width: 480px;padding-left: 35px" >
                    <label style="padding-right: 20px">备注&nbsp;&nbsp;</label>
                    <div style="margin-top: -29px;padding-left: 45px">
                   <textarea style="width: 220px;height: 68px;"
                             id="beizhuaaaa2" name="beizhu"  >
                     </textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="save2">
                        确认
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

</div>


<!--data-backdrop="static" data-keyboard="false"  bootstrap 空白处禁止关闭-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 600px;height: 270px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    换货
                </h4>
            </div>
            <div class="modal-body">
                条码类型&nbsp;&nbsp;&nbsp;<input type="text" name="" id="hhbarType" disabled autocomplete="off"  style="width: 200px;"/>
                <input type="text" name="" id="SbarType" style="display: none" autocomplete="off" />
            </div>
            <div class="modal-body">
                当前条码&nbsp;&nbsp;&nbsp;<input type="text" name="" id="bacth" disabled autocomplete="off"  style="width: 200px;"/>
            </div>
            <div  class="modal-body">
                <label calss="no3">功率：&ensp;</label>
                <label id="gonglv1" style="width: 90px"></label>
                <label calss="no3" style="padding-left: 10px">&ensp;&ensp;&emsp;电流：&ensp;</label>
                <label id="dianliu1" style="width: 18px"></label>
                <label calss="no3" style="padding-left: 10px">&ensp;&ensp;&emsp;颜色：&ensp;</label>
                <label id="yanse1" style="width: 18px"></label>
                <label calss="no3" style="padding-left: 10px">&ensp;&ensp;综合等级：&ensp;</label>
                <label id="zonghedengji1" style="width: 18px"></label>
            </div>
            <div class="modal-body">
                替代条码&nbsp;&nbsp;
                <input type="text" name="" id="Sbacth" autocomplete="off"  style="width: 200px;"/>
            </div>
            <div  class="modal-body">
                <label calss="no3">功率：&ensp;</label>
                <label id="gonglv2" style="width: 90px"></label>
                <label calss="no3" style="padding-left: 10px">&ensp;&ensp;&emsp;电流：&ensp;</label>
                <label id="dianliu2" style="width: 18px"></label>
                <label calss="no3" style="padding-left: 10px">&ensp;&ensp;&emsp;颜色：&ensp;</label>
                <label id="yanse2" style="width: 18px"></label>
                <label calss="no3" style="padding-left: 10px">&ensp;&ensp;综合等级：&ensp;</label>
                <label id="zonghedengji2" style="width: 18px"></label>
                <label id="hunbao" calss="no3" style=" display: none;padding-left: 10px;color: red">&ensp;&ensp;混包</label>
            </div>
            <div class="modal-body" style="width: 480px;" >
                <label style="padding-right: 20px">换货原因&nbsp;&nbsp;</label>
                <div style="margin-top: -29px;padding-left: 65px">
                   <textarea style="width: 220px;height: 68px;"
                             id="hhRemark">
                     </textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="hhsave">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%@include file="/WEB-INF/views/qjdJs.jspf" %>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/jquery-ui.min.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/jsplumb.min.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/workflow/demo.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/qa/chqrd.js?v=1'></script>
</body>
</html>
