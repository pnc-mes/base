/**
 * Created by test on 2017/8/17.
 */
$(function () {
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "reaCode", //自定义名字：标签id名字
        "param": "ReaCode" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "reaDes", //自定义名字：标签id名字
        "param": "ReaDes" //规则中自定义的名字：对应报文中的id字段
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
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"ReaRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/Reason/GetRealInfo", data: objData}, function (Body) {
            //去除边框颜色
            $("#reaCode").removeAttr("style");
            $("#reaDes").removeAttr("style");
            fillform("reasonForm", rule, Body.Data);
        });
    }
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
    var congfig1= [{
        "FieldName":"ReaType",
        "FieldOpt":"=",
        "FieldVal":"08"
    }];

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
        currentPage = 0;
        currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config,congfig1);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function(event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config,congfig1);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config,congfig1);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config,congfig1);
    });



    var InitData = {
        "FiledList":[
            {
                "FieldName":"ReaType",
                "FieldOpt":"=",
                "FieldVal":"08"
            },{
                "FieldName":"ReaCode",
                "FieldOpt":"Order BY",
            }
        ]
    }

    //只刷新树
    var loadtree = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var trees = [];
        request({url: '/Reason/GetAllReaInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}}, function (Body) {
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
                    id: treeData[i].ReaRd == undefined ? "" : treeData[i].ReaRd,
                    name: treeData[i].ReaCode == undefined ? "" : treeData[i].ReaCode
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };

    loadtree();

    /*-------------【新增】按钮----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("reasonForm");
        $("#ExecType").val("00");
        treeID = null;
    });

    /*---------【删除】----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/Reason/SaveReaInfo",
                        data: {"ExecType": "01", "busData": "{\"ReaRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        $("#ExecType").val("");
                        treeID = null;
                        condition = "";
                        currentPage = 0;
                        loadtree();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------复制按钮顶部菜单操作中----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            request({
                url: "/Reason/SaveReaInfo",
                data: {"ExecType": "03", "busData": "{\"ReaRd\":" + treeID + "}"}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                loadtree();
                condition = "";
                currentPage = 0;
                treeID = null;
            })
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------【保存】按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        if ($("#reaCode").val().trim() != "" && $("#reaDes").val().trim() != "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("reasonForm");

            //新增
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "ReaCode": formData["reaCode"],
                    "ReaDes": formData["reaDes"],
                    "ReaTID": "08",
                    "Remark": $('#beizhu').val()
                };
                request({
                    url: "/Reason/SaveReaInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loadtree();
                });
            }
            //【编辑】
            else if (treeID != null && treeID != "") {

                newData = {
                    "ReaRd": treeID,
                    "ReaCode": formData["reaCode"],
                    "ReaDes": formData["reaDes"],
                    "Remark": $('#beizhu').val(),
                    "ReaTID": "08",
                };
                request({
                    url: "/Reason/SaveReaInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    currentPage = 0;
                    condition = "";
                    loadtree();
                    $("#ExecType").val("");
                    treeID = null;
                });
            }
        } else {
            if ($("#reaCode").val().trim() == "") {
                $("#reaCode").css("border-color", "red").prop("placeholder", "不能为空！");
            }
            else if ($("#reaDes").val().trim() == "") {
                $("#reaDes").css("border-color", "red").prop("placeholder", "不能为空！");
            }
        }
    });
});
