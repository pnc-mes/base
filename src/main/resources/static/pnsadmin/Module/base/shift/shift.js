/**
 * Created by 乔帅阳 2018-6-17
 */
$(function () {
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
        "ctlid": "ShiftName", //自定义名字：标签id名字
        "param": "ShiftName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //描述
        "ctlid": "ShiftDes",
        "param": "ShiftDes"
    },{
        //开始时间
        "ctlid": "StartTime",
        "param": "StartTime"
    },{
        //结束时间
        "ctlid": "EndTime",
        "param": "EndTime"
    }, {
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
        var objBusData = JSON.stringify({"ShiftRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/Shift/GetShiftInfo",data: objData},function(Body){
            fillform("devform", rule, Body.Data);
            $("#StartTime").val( Body.Data.StartTime);
            $("#EndTime").val( Body.Data.EndTime);
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
        currentPage = treeSearchs("/Shift/GetAllShiftsInfo","ShiftRd","ShiftName","ShiftName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Shift/GetAllShiftsInfo","ShiftRd","ShiftName","ShiftName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })



    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;                                          //ShiftName一个前端传的值，一个数据库值
            currentPage = treeSearchs("/Shift/GetAllShiftsInfo","ShiftRd","ShiftName","ShiftName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Shift/GetAllShiftsInfo","ShiftRd","ShiftName","ShiftName",condition,currentPage,config);
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
                    "FieldName":"ShiftName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var treedataList = [];
        request({url:'/Shift/GetAllShiftsInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){
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
                    id: treeData[i].ShiftRd == undefined ? "" : treeData[i].ShiftRd,
                    name: treeData[i].ShiftName == undefined ? "" : treeData[i].ShiftName
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
                    request({url:"/Shift/SaveShiftInfo", data: {"ExecType": "01", "busData": "{\"ShiftRd\":" + treeID + "}"}},function(Body){
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
                url:"/Shift/SaveShiftInfo",
                data: {
                    "ExecType": "03",
                    "busData": "{\"ShiftRd\":" + treeID + "}"
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
                    "ShiftName": formData["ShiftName"],
                    "ShiftDes": formData["ShiftDes"],
                    "StartTime": formData["StartTime"],
                    "EndTime": formData["EndTime"],
                    "Remark": formData["remark"]
                };
                request({url:"/Shift/SaveShiftInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
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
                    "ShiftRd": treeID,
                    "ShiftName": formData["ShiftName"],
                    "ShiftDes": formData["ShiftDes"],
                    "StartTime": formData["StartTime"],
                    "EndTime": formData["EndTime"],
                    "Remark": formData["remark"]
                };
                request({url:"/Shift/SaveShiftInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loaddata();
                    treeID = null;
                });
            }
        } else {
            if ($("#ShiftName").val().trim() == "") {
                $("#ShiftName").css("border-color", "red");
                $("#ShiftName").prop("placeholder", "不能为空！");
            }
        }
    });

});
