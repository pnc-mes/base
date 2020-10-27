<%--
  Created by IntelliJ IDEA.
  User: PNC
  Date: 2017/6/30
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
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
    <link href="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="${pageContext.request.contextPath}/static/ui/global/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/batch/batch.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/warehouse/rk/rkadd.css">
</head>
<body>
<div class="btn-group table tabTop aa td1">
    <button type="button" class="btn btn-primary " id="save">保存</button>
    <button type="button" class="btn btn-primary ">关闭</button>
</div>
<div class="f2 ">
    <div class="packcontent">
        <form id="printForm">
            <div class="row rightTop">
                <div class="col-md-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">入库信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline rukadd">
                                    <div class="printTInfo">
                                        <div class="form-group formgroup">
                                            <label class="mright3">入库仓库
                                                <select class="form-control selectControl" id="cangku">
                                                </select>
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label>关联单号
                                                <input type="text" id="AssCode"/>
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label class="mright3">入库类型
                                                <select class="form-control selectControl" id="type">
                                                    <option id="00">产成品入库</option>
                                                    <option id="01">半成品入库</option>
                                                    <option id="02">原材料入库</option>
                                                    <option id="03">其他入库</option>
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="box box-primary last clearfix">
                                    <div class="f2_4 btn-group">
                                        <div class="btn btn-primary add1" id="addMve">新增</div>
                                        <div class="btn btn-primary del1">删除</div>
                                    </div>
                                    <section class="content">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="box-body tablecontent">
                                                    <div class="gridPanel">
                                                        <table id="list1"></table>
                                                        <div id="pager1"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <div class="box-body form-inline">
                                    <div class="form-group disblock">
                                        <div class="form-group">
                                            <label for="creatPeople">创建人</label>
                                            <input type="text" class="form-control" id="creatPeople" readonly
                                                   name="creator" placeholder=" ">
                                        </div>
                                        <div class="form-group">
                                            <label for="creatTime">创建时间</label>
                                            <input type="text" class="form-control" id="creatTime" readonly
                                                   name="createTime" placeholder=" ">
                                        </div>
                                    </div>
                                    <div class="disblock mtop">
                                        <div class="form-group">
                                            <label for="modifyPeople">修改人</label>
                                            <input type="text" class="form-control" id="modifyPeople" readonly
                                                   name="lastModifyMan" placeholder=" ">
                                        </div>
                                        <div class="form-group">
                                            <label for="modifyTime">修改时间</label>
                                            <input type="text" class="form-control" id="modifyTime" readonly
                                                   name="lastModifyTime" placeholder=" ">
                                        </div>
                                    </div>s
                                        <div class="form-group beizhu disblock">
                                            <label>
                                                <span class="beizhuspan">备&nbsp;&nbsp;&nbsp;&nbsp;注</span></label>
                                            <textarea id="beizhu" name="remark"></textarea>
                                        </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </form>
    </div>
    <div id="addContent" style="display: none;">
        <table id="list5">
            这里显示的是物料表格信息加载
        </table>
        <div id="pager5"></div>
    </div>
</div>
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
<!-- jqgrid表格开始 -->
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="${pageContext.request.contextPath}/static/plugins/jqgrid/jqgrid.min.js"></script>
<!-- jqgrid表格结束 -->
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/warehouse/rk/rkadd.js?v=1'></script>

</body>
</html>
