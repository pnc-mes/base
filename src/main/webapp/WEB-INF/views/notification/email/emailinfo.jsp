<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>邮件内容</title>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ui/global/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/min/css/supershopui.common.min.css" rel="stylesheet"/>
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
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/base/serialnum/serialnum.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/pnsadmin/Base/css/plan/pick/pickinfo.css">

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/plugins/bootstrap-submenu-2.0.0-dist/css/bootstrap-submenu.css">

</head>
<body>

<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/email/ueditor.config.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/email/ueditor.all.min.js'></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/email/lang/zh-cn/zh-cn.js'></script>

<div class="btn-group common table tabTop td1">
    <div class="btn btn-primary cAdd">新增</div>
    <div class="btn btn-primary cSave">保存</div>

    <div class="btn-group">
        <div class="btn btn-primary dropdown-toggle"  data-toggle="dropdown" data-submenu>操作
            <span class="caret"></span>
        </div>
        <ul class="dropdown-menu">
            <li class="dropdown-submenu">
                <a  class="text-center datelabel" tabindex="-1">数据标签</a>
                <ul class="dropdown-menu" z-index:9999 >
                    <li role="presentation"><a  class="text-center ma_batch" role="menuitem" tabindex="-1">物料批次</a></li>
                    <li role="presentation"><a class="text-center ma_code" role="menuitem" tabindex="-1">物料代码</a></li>
                    <li role="presentation"><a  class="text-center ma_name" role="menuitem" tabindex="-1">物料名称</a></li>
                    <li role="presentation"><a class="text-center ma_des" role="menuitem" tabindex="-1">物料描述</a></li>
                    <li role="presentation"><a  class="text-center ma_uptime" role="menuitem" tabindex="-1">物料上料时间</a></li>

                    <li role="presentation"><a  class="text-center dev_name" role="menuitem" tabindex="-1">设备名称</a></li>
                    <li role="presentation"><a class="text-center tool_name" role="menuitem" tabindex="-1">工具名称</a></li>
                    <li role="presentation"><a class="text-center carrier_name" role="menuitem" tabindex="-1">载具名称</a></li>

                    <li role="presentation"><a class="text-center dev_totalnum" role="menuitem" tabindex="-1">设备已累计使用次数</a></li>
                    <li role="presentation"><a  class="text-center tool_totalnum" role="menuitem" tabindex="-1">工具已累计使用次数</a></li>
                    <li role="presentation"><a class="text-center carrier_totalnum" role="menuitem" tabindex="-1">载具已累计使用次数</a></li>

                    <li role="presentation"><a  class="text-center dev_pmtimer" role="menuitem" tabindex="-1">设备上次保养时间</a></li>
                    <li role="presentation"><a class="text-center tool_pmtimer" role="menuitem" tabindex="-1">工具上次保养时间</a></li>
                    <li role="presentation"><a class="text-center carrier_pmtimer" role="menuitem" tabindex="-1">载具上次保养时间</a></li>

                    <li role="presentation"><a  class="text-center dev_checktimer" role="menuitem" tabindex="-1">设备上次点检时间</a></li>
                    <li role="presentation"><a class="text-center tool_checktimer" role="menuitem" tabindex="-1">工具上次点检时间</a></li>
                    <li role="presentation"><a class="text-center carrier_checktimer" role="menuitem" tabindex="-1">载具上次点检时间</a></li>


                </ul>
            </li>
            <li class="divider"></li>
            <li role="presentation"><a class="text-center cDel" role="menuitem" tabindex="-1">删除</a></li>
        </ul>
    </div>
    <div class="btn btn-primary _close">关闭</div>
    <%-- 引入页面中的系统信息--%>
    <%--   <%@include file="/WEB-INF/views/sysInfo.jspf"%>--%>
</div>
<input type="hidden" id="ExecType" name="ExecType"/>
<div class="f2 clearfix">
    <div class='zsyLeft'>
        <div class="left left1 zsyZi" id="left2">
            <input class="input1 form-control" type="search" name="" value="" placeholder="搜索邮件内容名称">
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
                            <li class="active"><a href="#tab_1" data-toggle="tab" class="dymb">基本信息</a></li>
                            <li><a href="#tab_2" data-toggle="tab">其他</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active clearfix" id="tab_1">
                                <div class="box-body form-inline">
                                    <div class="printTInfo">
                                        <div class="manWidth f1"  style="margin-top: 1px;">
                                        <div class="form-group width30" style="padding-top: 10px">
                                            <label>&nbsp;&nbsp;&nbsp;&nbsp;通知名称&nbsp;&nbsp;</label>
                                            <input type="text" class="form-control" style="width: 150px" id="EmailName"
                                                   name="EmailName"  maxlength="80">
                                        </div>
                                    <%--        <div class="form-group width30">
                                                <label class="">消息格式&nbsp;&nbsp;
                                                    <select id="MessageFormat" name="MessageFormat" class="form-control check" style="width: 150px;padding: 1px 6px;">
                                                        <option  value="0">Text</option>
                                                        <option  value="1">HTML</option>
                                                    </select>
                                                </label>
                                            </div>--%>
                                    </div>
                                   <%--     <div class="manWidth f1"  style="margin-top: 15px;">
                                            <div class="form-group ">
                                                <label>邮件服务器&nbsp;&nbsp;</label>
                                                <div id="smtp"></div>
                                            </div>
                                        </div>--%>
                                 <%--       <div class="manWidth f1"  style="margin-top: 15px;">
                                            <div class="form-group ">
                                                <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ensp;&nbsp;&nbsp;&nbsp;&nbsp;描述&nbsp;&nbsp;</label>
                                                <input type="text" class="form-control" style="width: 470px" id="Description"
                                                       name="Description" maxlength="150">
                                            </div>
                                        </div>--%>

                                        <div class="cpxx2 cpmcx manWidth " style="margin-top: 15px;">

                                    </div>
                                       <%--     <div class="manWidth f1"  style="margin-top: 15px;">
                                                <div class="form-group ">
                                                    <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发件人&nbsp;&nbsp;</label>
                                                    <div id="Sender"></div>
                                                </div>
                                            </div>--%>
                                    <div class="manWidth f1"  style="margin-top:15px;">
                                        <div class="form-group">
                                            <label hidden>&nbsp;&nbsp;&nbsp;&nbsp;邮件主题&nbsp;&nbsp;</label>
                                            <input type="hidden" class="form-control" id="Subject"
                                                   name="Subject" style="width: 430px"  maxlength="200" >
                                        </div>
                                    </div>

                                    <div class="cpxx2 cpmcx manWidth " >
                                        <div class="form-group width30">
                                            <label style="padding-left: 8px">&nbsp;&nbsp;邮件内容&nbsp;&nbsp;</label><br>
                                            <%--<textarea  rows="10" cols="70" id="Message"
                                                       name="Message">
                                                    </textarea>--%>
                                          <%--  <div>
                                                <%@include file="/WEB-INF/views/email/comonemail.jsp"%>
                                            </div>--%>

                                            <div id="Message"  name="Message">
                                                <div id="editor" style="width:1024px;height:500px;z-index: 99;" ></div>
                                               <%-- <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>--%>
                                            </div>
                                        </div>
                                    </div>

                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="tab_2">
                                <%-- 引入页面中的其他信息--%>
                                <%@include file="/WEB-INF/views/otherAll.jspf"%>
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
<script src="${pageContext.request.contextPath}/static/pnsadmin/Module/SMTP/Emailinfo.js?v=1"></script>
<script src='${pageContext.request.contextPath}/static/pnsadmin/Base/js/jquery-ui-1.9.2.custom.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/bootstrap-submenu-2.0.0-dist/js/bootstrap-submenu.js'></script>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor',{ zIndex: 99});


    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script>

</body>
</html>
