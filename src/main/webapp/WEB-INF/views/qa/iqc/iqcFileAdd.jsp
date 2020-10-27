<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../../../static/ui/global/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../../../static/pnsadmin/Base/css/base/printT/printTInfo.css">
    <style>
        input{
            width: 200px;
            display: block;
            top: 120px;
            margin-top: 30px;
        }
        span{
            position: absolute;
            left: 255px;
            top: 27px;
        }
    </style>
</head>
<body>
<div class="btn-group">
    <button class="btn btn-primary aSubmit">确认</button>
    <button class="btn btn-primary aClose _close" >关闭</button>
<form id="iqcAdd">
    <input type="text" class="form-control" id="fileName" readonly
           name="fileName" placeholder="">
     <span class="btn btn-success start fileinput-button" style="position: relative;">
         <i class="fa fa-upload"></i>
         <span> 开始上传 </span>
         <input type="file" readonly name="files" id="files" accept="application/pdf" multiple="">
     </span>
</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/mainPage.js"></script>
<script src="${pageContext.request.contextPath}/static/pnsadmin/Base/js/confirm.js"></script>
<script src='${pageContext.request.contextPath}/static/min/js/supershopui.common.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/ui/global/jQuery/jquery.min.js"></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery-file-upload/js/jquery.fileupload.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/commons.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Module/qa/iqc/iqcFileAdd.js?v=1'></script>

</body>
</html>