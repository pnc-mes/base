/**
 * Created by test on 2017/8/18.
 */


/*-------------------树形的处理-------------------*/
$(function () {
    $("#_right").hide();
    var treeID = null;
    var rule = [
        {
            "ctlid": "DevSName",
            "param": "DevSName"
        }, {
            "ctlid": "supName",
            "param": "SupName"
        }, {
            "ctlid": "creatPeople",
            "param": "Creator"
        }, {
            "ctlid": "creatTime",
            "param": "CreateTime"
        }, {
            "ctlid": "modifyPeople",
            "param": "LastModifyMan"
        }, {
            "ctlid": "modifyTime",
            "param": "LastModifyTime"
        }, {
            "ctlid": "beizhu",
            "param": "Remark"
        }];
    //点击树节点
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"DevSRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后获取的节点的数据------------------*/
        request({url:"/DevState/GetDevSInfo",data: objData},function(Body){
            //去除边框颜色
            $("#DevSName").removeAttr("style");
            fillform("devSform", rule, Body.Data);
            for(var i = 0 ; i < Body.Data.length ; i ++){
                $('#beizhu').val(Body.Data[i].Remark);
            }

            $("#Status").find("option").each(function () {
                if($(this).val() == Body.Data.Status){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });
        })
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
        currentPage = treeSearchs("/DevState/GetAllDevSInfo","DevSRd","DevSName","DevSName",condition,currentPage,config);
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
            currentPage = treeSearchs("/DevState/GetAllDevSInfo","DevSRd","DevSName","DevSName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/DevState/GetAllDevSInfo","DevSRd","DevSName","DevSName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/DevState/GetAllDevSInfo","DevSRd","DevSName","DevSName",condition,currentPage,config);
    });

    //只刷新树
    var loadtree = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"DevSName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var trees = [];
        request({url:'/DevState/GetAllDevSInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){
            if(Body.Data != null){
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
                        id: treeData[i].DevSRd == undefined ? "" : treeData[i].DevSRd,
                        name: treeData[i].DevSName == undefined ? "" : treeData[i].DevSName
                    }
                    trees.push(tree);
                }
                /*----------------------定义控件规则-------------------*/
                config.data.source = trees;
                $.JstreeEx.init(config);//先调用后加载
            }
        });
    };
    loadtree();

    /*--------------新增----------*/
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("devSform");
        treeID = null;
        $("#ExecType").val("00");
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/DevState/SaveDevSInfo", data: {"ExecType": "01", "busData": "{\"DevSRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        condition = "";
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage = 0;
                        loadtree();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------保存----------*/
    var formData = {};
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("devSform");

        var status = $("#Status").val();

        if ($("#DevSName").val().trim() != "") {

            //新增设备信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {

                newData = {
                    "DevSName": formData["DevSName"],
                    "Status":status,
                    "Remark": $('#beizhu').val()
                };

                request({url:"/DevState/SaveDevSInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loadtree();
                });
            }
            //编辑设备信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "DevSRd": treeID,
                    "DevSName": formData["DevSName"],
                    "Status": status,
                    "Remark": $('#beizhu').val()
                };
                request({url:"/DevState/SaveDevSInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loadtree();
                    treeID = null;
                });
            }
        } else {
            if ($("#DevSName").val().trim() == "") {
                $("#DevSName").css("border-color", "red").prop("placeholder", "不能为空！");
            }
            if ($("#supName").val().trim() == "") {
                $("#supName").css("border-color", "red").prop("placeholder", "不能为空！");
            }
        }
    });

});