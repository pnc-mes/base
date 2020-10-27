/**
 * Created by 乔帅阳 2018-6-17
 */
$(function () {
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
            var defaultSelect=Body.Data.MessageFormat;
            if(defaultSelect){
                $("#MessageFormat").val(1)
            } else {
                $("#MessageFormat").val(0)
            }


            fillform("devform", rule, Body.Data);
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
        $("#_right").show();
        clearForm("devform");
        treeID = null;
        $("#ExecType").val("00");
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
    $("a[name='copy']").click(function () {
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
    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("devform");

        if ($("#CarrierName").val()!= "" ) {
            //新增设备信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "EmailName": formData["EmailName"],
                    "Description": formData["Description"],
                    "Subject": formData["Subject"],
                    "Message": formData["Message"],
                    "Sender": formData["Sender"],
                    "MessageFormat": selectValue,
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
                    "EmailRd": treeID,
                    "EmailName": formData["EmailName"],
                    "Description": formData["Description"],
                    "Subject": formData["Subject"],
                    "Message": formData["Message"],
                    "Sender": formData["Sender"],
                    "MessageFormat":selectValue ,
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

});
