<%--
  Created by IntelliJ IDEA.
  User: liufuzhi
  Date: 2017/7/1
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>设备管理</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet"/>
    <!-- 树开始 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
</head>
<body>
<div class="btn-group common table tabTop td1">
    <%--<div class="btn btn-primary cSelect">筛选</div>--%>
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>
    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="true"
             id="option1">操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <%--<li role="presentation"><a name="copy" class="text-center" role="menuitem" tabindex="-1">复制</a></li>--%>
            <li role="presentation"><a class="text-center s" id="sltBatch" role="menuitem" tabindex="-1">设备映射</a></li>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>


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
    <%-- 引入页面中的系统信息--%>
 <%--   <%@include file="/WEB-INF/views/sysInfo.jspf"%>--%>
</div>
<!--设备映射-->
<!--data-backdrop="static" data-keyboard="false"  bootstrap 空白处禁止关闭-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static"
     data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 400px;height: 200px;">
        <div class="modal-content">

            <div class="modal-header">
                <div class="btn btn-primary cAdd">保存</div>
                <div class="btn btn-primary cSave">关闭</div>
            </div>
            <div class="modal-body">





                设备映射编号&nbsp;&nbsp;<input type="text" name="" id="DevMapCode" autocomplete="off"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">
                    确认
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal"
                        aria-hidden="true">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class='zsyLeft'>
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索设备">
            <img class="searchTree" src="${pageContext.request.contextPath}\static\pnsadmin\Base\images\admin\searchTrees.png" />
            <div class="pages"><a title="上一页" id="prev" style="display: block;float: left;cursor: pointer">　&lt;&lt;　</a>　<a title="下一页" id="next" style="display: block;float: right;cursor: pointer">　&gt;&gt;　</a>
            </div>
            <div id="jstree_demo1"></div>
        </div>
    </div>
    <div class="right" id="_right"  style="display: none;">
        <form id="devform">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">设备信息</a></li>
                            <li id="sbtx" style="display: none"><a href="#tab_3" data-toggle="tab">设备特性</a></li>
                            <li id="sbtxx"><a href="#tab_4" data-toggle="tab">设备标准值</a></li>
                           <%-- <li id="" style="display: none"><a href="#tab_4" data-toggle="tab"></a></li>--%>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class=" formgroup disblock">
                                            <label class="">设备编码</label>
                                            <input class="form-control check" type="text" id="sbbm" maxlength="40"/>
                                            <label class="">制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;造&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商</label>
                                            <div id="defaultSelect" data-id=""></div>
                                        </div>
                                       <div class=" formgroup disblock">
                                           <label for="devName">设备名称</label>
                                           <input type="text" class="form-control check" id="devName"
                                                  name="devName" placeholder="" maxlength="40">
                                            <label class="">设备状态模型</label>
                                           <div id="defaultSelect2" data-id=""></div>
                                        </div>

                                        <div class=" formgroup disblock">
                                            <label class="">设备家族</label>
                                            <div id="defaultSelect1" data-id="" style="margin-right: 100px"></div>
                                            <label class="">生&nbsp;&nbsp;产&nbsp;&nbsp;&nbsp;线&nbsp;&nbsp;别</label>
                                            <select class="selectpicker" multiple data-live-search="true" id="scxb" title="请选择生产线别">
                                            </select>
                                        </div>

                                        <div class=" formgroup disblock">
                                            <label class="">工具家族</label>
                                            <div id="toolfamil" data-id="" style="margin-right: 100px"></div>
                                            <label class="">保&nbsp;&nbsp;养&nbsp;&nbsp;&nbsp;计&nbsp;&nbsp;划</label>
                                            <select  style="width: 180px;" class="selectpicker" multiple data-live-search="true" id="byjh" title="请选择保养计划">
                                                <optgroup label="周期性保养计划" id="zqxbyjh"></optgroup>
                                                <optgroup label="次数保养计划" id="csbyjh"></optgroup>
                                            </select>
                                        </div>
                                        <div class=" formgroup disblock">
                                            <label class="">点检计划</label>
                                            <select class="selectpicker" multiple data-live-search="true" id="djjh" title="请选择点检计划">
                                            </select>
                                            <label class="" style="padding-left: 109px">放 置 地 点&nbsp;</label>
                                            <input class="form-control check" type="text" id="Place" maxlength="40"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
                            </div>
                            <!--设备特性-->
                            <div class="tab-pane" id="tab_3">
                                <div class="box-body form-inline">
                                    <div class="printTInfo clearfix">
                                        <form id="ssform">
                                            <div class="block bBottom maLeft formdata">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                       <%--     <!--设备标准值-->
                            </div>--%>
                            <div class="tab-pane" id="tab_4">
                            <div class="f2 clearfix">
                               <%-- <input name="id" id="id" value="${id}"/>--%>
                                <div style="margin-left: 10px;margin-top: 20px;" >

                                    <div class=" formgroup disblock">
                                        <label style="padding-left: 21px;">&ensp;参考信息&ensp;</label>
                                        <div style="margin-top: -23px;padding-left: 96px">
                                                <textarea style="width: 330px;  height: 80px;margin-top: -7px;"
                                                          id="Reference" class="Reference" name="Reference"></textarea>
                                        </div>
                                    </div>

                                    <div class=" formgroup disblock">
                                        <label for="ysshbh">标准值名称</label>
                                        <input class="form-control " type="text"  style="width: 200px;margin-left: 100px;margin-top: -30px;"   id="ysshbh" maxlength="40"/>
                                    </div>
                                    <div class=" formgroup disblock">
                                        <label for="ysshmc">标准值</label>
                                        <input class="form-control " type="text"  style="width: 200px;margin-left: 100px;margin-top: -30px;"  id="ysshmc" maxlength="40"/>
                                    </div>
                                    <div class=" formgroup disblock" style="margin-left: 150px;">
                                        <button type="button" class="btn btn-primary" id="changeadd"
                                                data-toggle="button"> 新增
                                        </button>
                                    </div>

                                    <div class="content clearfix">
                                        <div class="nav-tabs-custom">

                                            <div class="f2_4 btn-group">
                                                <%-- <div class="btn btn-primary add1">新增</div>--%>
                                                <div class="btn btn-primary del1">删除</div>
                                            </div>
                                            <section >
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
                                </div>
                            </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/res/dev/devinfo.js?v=1'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
<%--<script>
    $(function () {
        var colNamesArr = [
            {"Caption": "映射设备编号", "Name": "DevMapCode"},
            {"Caption": "设备映射名称", "Name": "DevMapName"}
        ];
        var config={
            tableId:"list4",
            data: [],
            colArr:colNamesArr,
            multiselect:true,
            width:0.84,
            height:0.35
        };
        fullTable(config);


        var objBusData = JSON.stringify({"DevRd": $("#id").val()});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/Device/GetDevInfo",data: objData},function(Body){
            if(Body.Data.DevMapInfo.length>0){
                var config={
                    tableId:"list4",
                    data: Body.Data.DevMapInfo,
                    colArr:colNamesArr,
                    multiselect:true,
                    width:0.84,
                    height:0.35
                };
                fullTable(config);
            }
        })





        $("#changeadd").click(function () {
            var DevMapCode=$("#ysshbh").val().trim();
            var DevMapName=$("#ysshmc").val().trim();
            if(DevMapCode==null||DevMapCode==""){
                toastr.warning("映射设备编号不能为空");
                return false;
            }
            if(DevMapName==null||DevMapName==""){
                toastr.warning("映射设备名称不能为空");
                return false;
            }
            var DevMapInfo={
                "DevMapCode":DevMapCode,
                "DevMapName":DevMapName
            };
            addSrow("list4",DevMapInfo);
            $("#ysshbh").val("");
            $("#ysshmc").val("");
        });

        $('.del1').on('click',function(){
            derow('list4');
        });

        $("._Save").click(function () {
            var list4Data= getTableData("list4");
            /* if(list4Data.length<=0){
                 toastr.warning("映射设备明细不能为空");
                 return false;
             }*/

            var  DevRd=$("#id").val();
            var BusData={
                "DevRd":DevRd,
                "DevMapCode":list4Data
            };


            request({url:"/Device/SaveDevInfo", data: {"ExecType":"04", "busData": JSON.stringify(BusData)}},function(Body){
                parent.layer.closeAll();
                parent.toastr.success("操作成功!");
            });




        });



        $("._close").click(function () {
            parent.layer.closeAll();
        });

        // alert($("#id").attr("value"))
    });
</script>--%>
</html>
