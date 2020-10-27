<%--
  Created by IntelliJ IDEA.
  User: xfxi
  Date: 2017/7/1
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增替代料</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/procress/bom/subbom.css">
</head>
<body>

<div class="f2 clearfix f2_top">
    <form class="form-inline">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="box-body">
                    <div class="printTInfo">
                        <div class="form-group formgroup disblock">
                            <label class="mright3 wl_select">物料名称
                                <select class="form-control selectControl" id="maver">

                                </select>
                            </label>
                            <label class="mright3">工序
                                <select class="form-control selectControl" id="specver">

                                </select>
                            </label>
                        </div>
                        <div class="sldw disblock">
                            <label class="mright3 " id="wl_text">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量
                                <input type="text" class="form-control " id="smallText" readonly
                                       placeholder="">
                            </label>
                            <label class="mright3">单位
                                <select class="form-control selectControl">
                                    <option value="">KG</option>
                                </select>
                            </label>
                        </div>
                        <div class="disblock sldw">
                            <label for="IsDef" class="checkbox" id="dd" style="">
                                <input type="checkbox" class="baoliu7 check" id="IsDef"
                                       name="IsDef">
                                设置为默认版本
                            </label>
                        </div>
                        <div class="clearfix ">
                            <div class="form-group beizhu left_one">
                                <label>
                                    <span class="beizhuspan">替代料</span>
                                </label>

                            </div>
                            <ul class="left_two">
                                <li>测试物料（2001）</li>
                                <li>测试物料（2001）</li>
                                <li>测试物料（2001）</li>
                            </ul>
                            <div class="left_three">
                                <div class="btn btn-primary disblock ">+</div>
                                <div class="btn btn-primary disblock reduce">-</div>
                            </div>
                            <div class="left_four">
                                <div class="wlxx">
                                    <label>物料信息</label>
                                    <input type="search" class="wlxx_input" placeholder="物料代码/物料名称">
                                    <span class="glyphicon glyphicon-zoom-in wlxx_span"></span>
                                    <ul class="right_child">
                                        <li>测试物料（2001）</li>
                                        <li>测试物料（2001）</li>
                                        <li>测试物料（2001）</li>
                                        <li>测试物料（2001）</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/confirm.js"></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery-file-upload/js/jquery.fileupload.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/process/bom/subbom.js?v=1'></script>

<script>
    $('.right_child>li').on('click', function () {
        var $rightlival = $(this).val();
        $('.add').on('click', function () {
            $('.left_two').append('<li>$rightlival</li>');
        })
    })
</script>

</body>
</html>
