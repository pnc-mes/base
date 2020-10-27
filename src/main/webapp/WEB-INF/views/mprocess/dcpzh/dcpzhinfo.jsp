<%--
  Created by IntelliJ IDEA.
  User: zhangliangliang
  Date: 2018/12/14
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>电池片转化关系信息</title>
        <meta content="width=device-width, initial-scale=1" name="viewport"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
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
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/worder/worder.css">
        <style type="text/css">
            input {
                margin-right: 0px;
            }
            a{
                cursor: pointer;
            }
            table tr,th{
                font-size: 12px;
                color: #333333;
            }
            #table th{
                border: 1px solid #E0E4EB;
            }


            #table td{
                border: 1px solid #E0E4EB;
            }
            #table input{
                border:0;
                background:transparent;
            }
        </style>
    </head>
<body>
<div class="btn-group table tabTop fix td1">
    <button class="btn btn-primary cSplit" id="save_">保存</button>
    <button class="btn btn-primary cClose _close">关闭</button>
</div>
<div class="formbd col-sm-12">
    <form id="printForm">
        <div class="row rightTop">
            <div class="col-md-12">
                <div class="box-body form-inline clearfix">
                    <div class="nav-tabs-custom">
                        <br/>
                        <div class="bBottom" style="margin-top: 15px">
                           <select id="select">
                               <option value="00">工厂</option>
                               <option value="01">单/多晶</option>
                               <option value="02">电池片尺寸</option>
                               <option value="03">外观图形</option>
                               <option value="04">技术信息</option>
                               <option value="05">电池片效率</option>
                               <option value="06">电池片颜色</option>
                               <option value="07">电池片等级</option>
                           </select>
                            <br/>
                            <p id="pp" style="color: red;margin-top: 10px;display: none;">电池片效率档高，使用【上移】功能，电池片效率档低，使用【下移】功能</p>
                        </div>
                        <div class="f2_4 btn-group" id="maInfoButton" style="margin-top: -10px;">
                            <div class="btn btn-primary add5">新增</div>
                            <div class="btn btn-primary del5">删除</div>
                        </div>
                        <div class="bBottom" style="margin-top: 8px;width: 1100px;height: 600px;overflow: auto;">

                            <section id="section">
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

                            <table id="table" border="1" cellspacing="0" cellpadding="0" style="display: none;width: 1000px;height: 600px;margin-bottom: 200px;">
                                <thead id="thead">
                                    <tr><td><input type="checkbox"/></td><td>工厂代码</td><td>工厂描述</td><td>操作</td></tr>
                                </thead>
                                <tbody id="tbody">
                                    <%--<tr><td><input type="checkbox"/></td><td>工厂代码</td><td>工厂描述</td><td><a onclick="check(this,'MoveUp')">上移</a><a  onclick="check(this,'MoveDown')"> 下移</a></td></tr>
                                    <tr><td><input type="checkbox"/></td><td>工厂代码1</td><td>工厂描述1</td><td><a onclick="check(this,'MoveUp')">上移</a><a  onclick="check(this,'MoveDown')">下移</a></td></tr>
                                    <tr><td><input type="checkbox"/></td><td>工厂代码2</td><td>工厂描述2</td><td><a onclick="check(this,'MoveUp')">上移</a><a  onclick="check(this,'MoveDown')">下移</a></td></tr>--%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>


<%@include file="/WEB-INF/views/commonJs.jspf" %>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/ceshi.js?v=5'></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/mprocess/dcpzh/dcpzh.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
</body>
</html>
