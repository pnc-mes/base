/**
 * Created by liufuzhi on 2017/6/30.
 */
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"BLRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/BatchL/GetBLInfo",data: objData},function(Body){
            fillform("batchlevelinfo", rule, Body.Data);
        });
    };
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
        currentPage = treeSearchs("/BatchL/GetAllBLInfo","BLRd","BLName","BLName",condition,currentPage,config);
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
            currentPage = treeSearchs("/BatchL/GetAllBLInfo","BLRd","BLName","BLName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })








    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/BatchL/GetAllBLInfo","BLRd","BLName","BLName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/BatchL/GetAllBLInfo","BLRd","BLName","BLName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //等级批次名称
        "ctlid": "bLName", //自定义名字：标签id名字
        "param": "BLName" //规则中自定义的名字：对应报文中的id字段
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

    /*-----------------加载树------------*/
    var newTree=[];
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/BatchL/GetAllBLInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "BLName",
                            "FieldOpt": "Order BY"
                        }]})
        }},function(Body){
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].BLRd == undefined ? "" : treeData[i].BLRd,
                    name: treeData[i].BLName == undefined ? "" : treeData[i].BLName
                };
                newTree.push(tree);
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("batchlevelinfo");
        treeID = null;
        $("#ExecType").val("00");
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/BatchL/SaveBLInfo", data: {"ExecType": "01", "busData": "{\"BLRd\":" + treeID + "}"}},function(Body){
                        currentPage=0;
                        condition = '';
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#_right").hide();
                        $("#ExecType").val("");
                        loaddata();
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
            request({url:"/BatchL/SaveBLInfo", data: {"ExecType": "03", "busData": "{\"BLRd\":" + treeID + "}"}},function(Body){
                currentPage=0;
                condition = '';
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                loaddata();
                treeID = null;
            });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("batchlevelinfo");
        if($("#bLName").val().trim()!= ""){
            //新增等级批次信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "BLName": formData["bLName"],
                    "Remark": formData["remark"]
                };
                request({url:"/BatchL/SaveBLInfo",data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                });
            }
            //编辑等级批次信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "BLRd": treeID,
                    "BLName": formData["bLName"],
                    "Remark": formData["remark"]
                };
                request({url:"/BatchL/SaveBLInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val("");
                    treeID = null;
                    toastr.success(Body.MsgDes);
                });
            }
        }else{
            $("#bLName").css("border-color","red");
            $("#bLName").prop("placeholder","不能为空！");
        }
    });
});
