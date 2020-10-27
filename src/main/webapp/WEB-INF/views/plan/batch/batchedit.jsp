<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增有工单</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Module/plan/worderinfo/skin/jedate.css">
    <!-- 树开始 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plugins/jstree/dist/themes/default/style.min.css"/>
    <!-- 树结束 -->
    <!-- 有关tab切换的样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/common1.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
</head>

<body>

<div class="btn-group table tabTop td1 common ">
    <div class="btn btn-primary cSave" id="cSave">保存</div>
    <div class="btn btn-primary _close">关闭</div>
</div>
<div class="f2 clearfix content margintop1">
    <form class="form-inline">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">批次信息</a></li>
                        <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active clearfix" id="tab_1">

                            <div class="box-body">
                                <div class="printTInfo">
                                    <div class="form-group formgroup">
                                        <div class="cpxx">工单信息</div>
                                        <label class="mright3">工&nbsp;&nbsp;单&nbsp;&nbsp;号
                                            <input type="text" class="form-control"  id="woCode" placeholder="">
                                        </label>
                                        <label class="mright2">计划生产数量
                                            <input type="text" class="form-control"  id="woNum" placeholder="">
                                        </label>
                                        <div class="cpxx cpxx1">产品信息</div>
                                        <label class="mright3">产&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品
                                            <input type="text" class="form-control"  id="maName"
                                                   placeholder="产品A(001)-版本:0001">
                                        </label>
                                        <label class="mright3">工&nbsp;&nbsp;艺&nbsp;&nbsp;流&nbsp;&nbsp;程
                                            <input type="text" class="form-control"  id="gylc"
                                                   placeholder="工艺流程-01">
                                        </label>
                                        <label class="mright1">开始工序
                                            <select class="form-control selectControl" id="gx">
                                            </select>
                                        </label>
                                    </div>
                                    <div class="form-group " style="display: block; margin-top:15px;">
                                        <div class="cpxx">生产信息</div>
                                        <label class="mright3">计划开始日期
                                            <input class="datainp wicon" id="jsdate" type="text"
                                                   placeholder="YYYY-MM-DD hh:mm:ss" value="" >
                                        </label>
                                        <label class="mright3">计划完工日期
                                            <input class="datainp wicon" id="jfdate" type="text"
                                                   placeholder="YYYY-MM-DD hh:mm:ss" value="" >
                                        </label>
                                        <label class="mright3">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量
                                            <input type="text" class="form-control" id="bcount" placeholder="">
                                        </label>
                                        <label class="mright3">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位
                                            <input type="text" class="form-control danwei"  id="smallText7" readonly
                                                   placeholder="个">
                                        </label>

                                        <div class="cpxx cpxx1">打印信息</div>
                                        <label class=" ">打印模板
                                            <input type="text" id="dymb" name="" value="">
                                        </label>
                                        <button class="btn btn-primary tianj">+</button>
                                        <label class="">打印机
                                            <select class="form-control selectControl" id="dyj">
                                            </select>
                                        </label>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="tab_2">
                            <div class="box-body">
                                <div class="form-group">
                                    <div class="form-group">
                                        <label for="creatPeople">创建人</label>
                                        <input type="text" class="form-control" readonly id="creatPeople"
                                               placeholder=" ">
                                    </div>
                                    <div class="form-group">
                                        <label for="creatData">创建时间</label>
                                        <input type="text" class="form-control" readonly id="" placeholder=" ">
                                    </div>
                                    <br>
                                    <br>
                                    <div class="form-group">
                                        <label for="ss">修改人</label>
                                        <input type="text" class="form-control" readonly id="ss" placeholder=" ">
                                    </div>
                                    <div class="form-group">
                                        <label for="">修改时间</label>
                                        <input type="text" class="form-control" readonly id="creatData" placeholder=" ">
                                    </div>
                                    <div class="form-group beizhu">
                                        <label>
                                            <span class="beizhuspan">备&nbsp;&nbsp;&nbsp;&nbsp;注</span></label>
                                        <textarea id="beizhu" readonly name="remark"></textarea>
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
<input type="hidden" id="BRd" value="${BRd}"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/mainPage.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/confirm.js"></script>
<script src='${pageContext.request.contextPath}/static/min/js/supershopui.common.js'></script>
<!-- 树开始 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/JstreeEx.js"></script>
<script type="" src='${pageContext.request.contextPath}/static/plugins/jstree/dist/jstree.min.js'></script>
<!-- 树结束 -->
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/base/printTInfo/printTInfo.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/plan/worderinfo/jquery.jedate.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/plan/batch/batchedit.js?v=1'></script>

</body>
</html>
