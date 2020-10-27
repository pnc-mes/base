<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>装卸物料</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <!--jqgrid表格样式 -->
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/currency/splitBatch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">
    <style type="text/css">
        #ulWo{
            height: 200px;
            border: 1px solid #C1C1C1;
            overflow:auto;
        }
        #ulWo li{
            list-style: none;
            border-bottom: 1px dotted #C1C1C1;
            margin: 5px 20px 0 -20px;
            text-align: center;
        }
        #ulWo li:hover{
            background-color: gainsboro;
        }
        input {
            margin-right: 0px;
        }
        table tr{
            font-size: 13px;

        }

    </style>
</head>
<body>
<div class="btn-group table tabTop fix td1">
    <button type="button" class="btn btn-primary" id="search_">查询</button>
    <div class="btn btn-primary _close">关闭</div>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="formbd col-sm-12">
    <form id="setupspForm">
        <div class="row rightTop">
            <div class="form-inline">
                <%--floor1--%>
                <div class="printTInfo mb">
                    <div class="nav-tabs-custom">
                        <div class='nav nav-tabs'>
                            <div class="tab-pane  clearfix" id="tab_1">
                                <div class="box-body  form-inline">
                                    <div class='f1'>
                                        <div class="mtop width100">
                                            <label>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;装卸方式&ensp;</label>

                                            <select id="findModel" class="form-control"
                                                    style="width: 195px;height: 32px;">
                                                <option value="00">工序</option>
                                                <option value="01">设备</option>
                                            </select>

                                            <label for="WoCode">&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;工单号&ensp;</label>
                                            <input type="hidden" id="WoRd" />
                                            <input type="text" class="form-control" id="WoCode" name="WoCode" autocomplete="off" readonly />
                                            <input type="button" value="..." id="sltWo" />
                                            <div class="form-group " style="width: 25%; margin-left: 44px;">
                                                <label for="workflow">工艺流程&ensp;</label>
                                                <input type="text" class="form-control mright" id="workflow"
                                                       name="workflow" readonly style="width: 195px;"/>
                                            </div>
                                        </div>
                                        <div class="mtop width100">
                                            <div class="form-group " style="width: 24%; margin-left: 44px;">
                                                <label for="CZPCode">物料消耗预警值&ensp;</label>
                                                <input type="number" class="form-control" id="number" name="number" autocomplete="off" style="width: 150px" />
                                            </div>
                                            <div class="form-group width22">
                                                <label for="woCode">工&ensp;&ensp;序&ensp;</label>

                                                <select id="workProcedure" class="form-control"
                                                        style="width: 200px;height: 31px;">

                                                </select>
                                            </div>
                                            <div class="form-group width22" style="margin-left: 68px;">
                                                <label for="equipment" class="shebei" style="display: none;">设&ensp;&ensp;备&ensp;</label>

                                                <select id="equipment" class="form-control shebei"
                                                        style="display: none;width: 196px;height: 31px;">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--处理表格-->

                            <div class="form-inline mtop">
                                <div class="nav-tabs-custom" style="margin-bottom: 0px;">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#tab_2" data-toggle="tab">装料</a></li>
                                        <li><a href="#tab_3" data-toggle="tab">卸料</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="tab_2">
                                            <div class="mtop width100" style="margin-left: -15px;">
                                                <div class="bBottom">
                                                    <label for="CZPCode">&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;物料批次&ensp;</label>
                                                    <input type="text" class="form-control" id="CZPCode" name="CZPCode" autocomplete="off" style="width: 300px" />
                                                    <label for="SRemark">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注&ensp;</label>
                                                    <input type="text" class="form-control" id="SRemark" name="Remark" autocomplete="off" style="width: 100px" />
                                                </div>
                                            </div>
                                            <div class="tab-pane fade in active" style="margin-left: 25px;" id="home">
                                                <span style="margin-left: 11px;">
                                                    <button type="button" class="btn btn-primary" id="addMaterial">上料</button>
                                                </span>
                                                <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;上料操作记录</span>
                                                <div class=" box-primary  clearfix">
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
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="tab_3">
                                            <div class="mtop width100">
                                                <div class="bBottom">
                                                    <label for="XCZPCode">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;物料批次&ensp;</label>
                                                    <input type="text" class="form-control" id="XCZPCode" name="XCZPCode" autocomplete="off" style="width: 300px" />
                                                    <label for="ZSnumber">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;物料总数&ensp;</label>
                                                    <input type="number" class="form-control" id="ZSnumber" name="ZSnumber" autocomplete="off"  disabled style="width: 100px;" />
                                                    <label for="KYnumber">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可用数量&ensp;</label>
                                                    <input type="number" class="form-control" id="KYnumber" name="KYnumber" autocomplete="off"  disabled style="width: 100px;" />
                                                    <label id="UnitName"></label>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade in active" style="margin-left: 25px;" id="home1">
                                                <span style="margin-left: 11px;">
                                                    <button type="button" class="btn btn-primary" id="removeMaterial">下料</button>
                                                </span>
                                                <span>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;下料操作记录</span>
                                                <div class=" box-primary  clearfix">
                                                    <section>
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
    </form>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 100%">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    选择工单
                </h4>
            </div>
            <div class="modal-body">
                <div style="margin: 0 auto;">
                    <input type="text" id="searchWo" style="width: 100%;" />
                    <div style="border-left: 1px solid #C1C1C1;border-right: 1px solid #C1C1C1;text-align: center;">工单号</div>
                    <ul id="ulWo">
                    </ul>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/setupm.js?v=1"></script>
</body>
</html>


