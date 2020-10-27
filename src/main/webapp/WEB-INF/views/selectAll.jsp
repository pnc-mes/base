<%--
  Created by IntelliJ IDEA.
  User: test
  Date: 2017/9/19
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/filter/jquery-ui-1.9.2.custom.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/filter/filter.css"/>
</head>
<body>
<div class="containerr" style="display: none; width: 340px;cursor: pointer;">
    <div class="selectBox" style="position: absolute;width: 340px;left: 100px;top:100px;margin: 0 auto;">
        <div class=" table-responsive" style="height: 200px;width: 340px;border: 1px solid #9E9E9E;background: #fff; position: relative;">
            <form id="filterForm" >
                <div class="clearfix" style="position: absolute;top: 0;background-color: #fff;" >
                    <div class="selectLeft" style="float: left;">过滤条件</div>
                    <div class="selectLeft" style="float: right;"><img class="imgClose" src="${pageContext.request.contextPath}/static/pnsadmin/Base/css/filter/overcast/images/close.png" width="28px" height="28px"></div>
                </div>
                <table class="table " id="filterTable" style="margin-top: 35px;overflow: auto">
                    <%-- <thead>
                     <tr style="position: fixed;top: 0;left: 0;">
                         <th class="text-left tj" >过滤条件</th>
                         <th class="text-right ">
                             <img class="imgClose" src="${pageContext.request.contextPath}/static/pnsadmin/Base/css/filter/overcast/images/close.png" width="28px" height="28px">
                         </th>
                     </tr>
                     </thead>--%>
                    <tbody >
                    </tbody>
                </table>
            </form>
            <div class="text-right" style="background: #fff;position: absolute;bottom: 0;width: 100%;">
                <button class="sure">确认</button>&emsp;<button class="unSure">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="datakj" style="display: none;line-height: 37px;">
    <input type="text" class="ui-datepicker-time" readonly value=""/>
    <div class="ui-datepicker-css">
        <div class="ui-datepicker-choose">
            <div class="ui-datepicker-date">
                <input name="startDate" id="startDate" class="startDate" readonly value="2017/08/20" type="text">—
                <input name="endDate" id="endDate" class="endDate" readonly value="2017/08/20" type="text" disabled onchange="datePickers()">
            </div>
        </div>
    </div>
</div>
</body>
</html>
