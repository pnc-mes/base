/**
 * Created by 乔帅阳 2018-7-6
 */

$(function () {
    //多级目录
    $('[data-submenu]').submenupicker();

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
    var selectValue=0;
    $("#MessageFormat").change(function(e){
       selectValue=parseInt(e.target.value);
    });
    /*    $("#date01").datepicker({
     language: "zh-CN",
     format: "hh:mm"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
     });
     $("#date02").datepicker({
     language: "zh-CN",
     format: "mm/dd/yyyy"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
     });*/
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //名称
        "ctlid": "EmailName", //自定义名字：标签id名字
        "param": "EmailName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //描述
        "ctlid": "Description",
        "param": "Description"
    },{
        "ctlid": "MessageFormat",
        "param": "MessageFormat"
    },{

        "ctlid": "Sender",
        "param": "Sender"
    },{

        "ctlid": "Subject",
        "param": "Subject"
    },{

        "ctlid": "Message",
        "param": "Message"
    },{
        //创建人
        "ctlid": "creatPeople",
        "param": "Creator"
    }, {
        //创建时间
        "ctlid": "creatTime",
        "param": "CreateTime"
    }, {
        //修改人
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    }, {
        //修改时间
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    }, {
        //备注
        "ctlid": "beizhu",
        "param": "Remark"
    }];

    //加载邮件服务
/*
    var smtp = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "SMTPRd",
            "fields": [
                {
                    "caption": "SMTPRd",
                    "name": "SMTPRd"
                }, {
                    "caption": "内容名称",
                    "name": "SMTPName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"SMTPName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "SMTPName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/SMTP/GetAllSMTPInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SMTPRd": datas[i].SMTPRd,
                            "SMTPName": datas[i].SMTPName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            },
        }
    };

    $("#smtp").zc_select(smtp);
*/

    var Sender = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "UserRd",
            "fields": [
                {
                    "caption": "UserRd",
                    "name": "UserRd"
                }, {
                    "caption": "内容名称",
                    "name": "UserName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"UserName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "UserName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/User/GetAllUserInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UserRd": datas[i].UserRd,
                            "UserName": datas[i].UserName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            },
        }
    };

    $("#Sender").zc_select(Sender);



    /*-------------------点击树节点------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"EmailRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/Email/GetEmailInfo",data: objData},function(Body){
            //$("#Message").val( Body.Data.Message);
            UE.getEditor('editor').setContent(Body.Data.Message);
            var defaultSelect=Body.Data.MessageFormat;
            if(defaultSelect){
                $("#MessageFormat").val(1)
            } else {
                $("#MessageFormat").val(0)
            }



            fillform("devform", rule, Body.Data);
            if(Body.Data.SMTP!=null){

                $("#smtp").showData({
                    id:Body.Data.SMTP.SMTPRd,
                    name:Body.Data.SMTP.SMTPName,
                    keyfield:"SMTPRd",
                    fields:[
                        {"name":"SMTPRd"},
                        {"name":"SMTPName"}
                    ]
                });
            }

            if(Body.Data.Sender!=null){
                $("#Sender").showData({
                    id:Body.Data.Sender.SendRd,
                    name:Body.Data.Sender.SendName,
                    keyfield:"UserRd",
                    fields:[
                        {"name":"UserRd"},
                        {"name":"UserName"}
                    ]
                });
            }

        })
    }
    /*----------------------定义控件规则-------------------*/
    var config = {
        id: "jstree_demo1",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        //定义事件获取点击的值
        event: {
            onClick: onClicks
        }
    };
    var $pages = $('.pages');
    var condition = "";
    var currentPage = 0;
    var PageInfo = {
        "PageSize":20,
        "PageIndex":currentPage
    };
    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;                                              //ShiftName一个前端传的值，一个数据库值
        currentPage = treeSearchs("/Email/GetAllEmailInfo","EmailRd","EmailName","EmailName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function(event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;                                              //ShiftName一个前端传的值，一个数据库值
            currentPage = treeSearchs("/Email/GetAllEmailInfo","EmailRd","EmailName","EmailName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;                                          //ShiftName一个前端传的值，一个数据库值
            currentPage = treeSearchs("/Email/GetAllEmailInfo","EmailRd","EmailName","EmailName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Email/GetAllEmailInfo","EmailRd","EmailName","EmailName",condition,currentPage,config);
    });
    /*-----------------加载树------------*/
    var loaddata = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"EmailName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var treedataList = [];
        request({url:'/Email/GetAllEmailInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            if(treeData.length<=0){
                return false;
            }
            var len = treeData.length >= 20 ? 20:treeData.length;

            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].EmailRd == undefined ? "" : treeData[i].EmailRd,
                    name: treeData[i].EmailName == undefined ? "" : treeData[i].EmailName
                }
                treedataList.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载

        });
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        UE.getEditor('editor').setContent("");
        $("#_right").show();
        clearForm("devform");
        treeID = null;
        $("#ExecType").val("00");
        $("#Sender").clearseldata("UserRd");
        $("#smtp").clearseldata("SMTPRd");
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null) {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Email/SaveEmailInfo", data: {"ExecType": "01", "busData": "{\"EmailRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        condition = "";
                        currentPage = 0;
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        loaddata();
                        $("#_right").hide();

                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    /*--------------顶部菜单点击操作中的复制按钮----------*/
  /*  $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {

            request({
                    url:"/Email/SaveEmailInfo",
                    data: {
                        "ExecType": "03",
                        "busData": "{\"EmailRd\":" + treeID + "}"
                    }},
                function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    treeID = null;
                    condition = "";
                    currentPage = 0;
                    loaddata();
                });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });*/



    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("devform");
       var aa = UE.getEditor('editor').getContent()
        var SMTPRd=$("#smtp").getseldata().SMTPRd;
        var SendRd=$("#Sender").getseldata().UserRd;

        if(aa==null||aa==""){
            toastr.warning("邮件内容不能为空");
            return false;
        }



        if ($("#CarrierName").val()!= "" ) {
            //新增设备信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "SMTPRd":SMTPRd,
                    "EmailName": formData["EmailName"],
                  /*  "Description": formData["Description"],*/
                    "Subject": formData["Subject"],
                    "Message": UE.getEditor('editor').getContent(),
                 /*   "SendRd": SendRd,
                    "MessageFormat": selectValue,*/
                  //  "MessageFormat": true,
                    "Remark": formData["Remark"]
                };
                request({url:"/Email/SaveEmailInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loaddata();
                });
            }
            //编辑设备信息
            else if (treeID != null && treeID != "") {
                newData = {
                    /*"SMTPRd":SMTPRd,*/
                    "EmailRd": treeID,
                    "EmailName": formData["EmailName"],
                 /*   "Description": formData["Description"],*/
                    "Subject": formData["Subject"],
                    "Message": UE.getEditor('editor').getContent(),/*
                    "SendRd": SendRd,
                    "MessageFormat":selectValue ,*/
                    "Remark": formData["remark"]
                };
                request({url:"/Email/SaveEmailInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loaddata();
                    treeID = null;
                });
            }
        } else {
            if ($("#EmailName").val().trim() == "") {
                $("#EmailName").css("border-color", "red");
                $("#EmailName").prop("placeholder", "不能为空！");
            }
        }
    });

   //悬停
   //  $(".DataLabel").click(function () {
   //      $(".dtag").show()}
   //      );
 // 物料批次
    $(".ma_batch").click(function () {
        addContent("%%ma_batch%%");
    });

    $(".ma_code").click(function () {
        addContent("%%ma_code%%");
    });
    $(".ma_name").click(function () {
        addContent("%%ma_name%%");
    });
    $(".ma_des").click(function () {
        addContent("%%ma_des%%");
    });
    $(".dev_name").click(function () {
        addContent("%%dev_name%%");
    });
    $(".tool_name").click(function () {
        addContent("%%tool_name%%");
    });
    $(".carrier_name").click(function () {
        addContent("%%carrier_name%%");
    });
    $(".ma_uptime").click(function () {
        addContent("%%ma_uptime%%");
    });
    $(".dev_totalnum").click(function () {
        addContent("%%dev_totalnum%%");
    });
    $(".tool_totalnum").click(function () {
        addContent("%%tool_totalnum%%");
    });
    $(".carrier_totalnum").click(function () {
        addContent("%%carrier_totalnum%%");
    });
    $(".dev_pmtimer").click(function () {
        addContent("%%dev_pmtimer%%");
    });
    $(".tool_pmtimer").click(function () {
        addContent("%%tool_pmtimer%%");
    });
    $(".carrier_pmtimer").click(function () {
        addContent("%%carrier_pmtimer%%");
    });

    $(".dev_checktimer").click(function () {
        addContent("%%dev_checktimer%%");
    });
    $(".tool_checktimer").click(function () {
        addContent("%%tool_checktimer%%");
    });
    $(".carrier_checktimer").click(function () {
        addContent("%%carrier_checktimer%%");
    });




    function addContent(placeholder) {
        var placeholder  =placeholder.toString();
        /*var aa = UE.getEditor('editor').getContent();
        if(aa ==''){
            aa=aa+placeholder;
        }else if(aa !='' && aa.lastIndexOf("</p>")!=-1
       ){
        aa=aa.slice(0,aa.lastIndexOf("</p>"))+placeholder+"</p>";
        }*/


     //   UE.getEditor('editor').setContent(aa);

        UE.getEditor('editor').focus();
        UE.getEditor('editor').execCommand('inserthtml',placeholder);

    }




});
