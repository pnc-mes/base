/**
 * Created by test on 2017/8/17.
 */
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CMethodRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/CMethod/GetCMethodInfo", data: objData},function(Body){
            //去除边框颜色
            $("#CheckMethodCode").removeAttr("style");
            $("#CheckMethodName").removeAttr("style");
            fillform("urgencyForm", rule, Body.Data);
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
        currentPage = treeSearchs("/CMethod/GetAllCMethodInfo","CMethodRd","CheckMethodName","CheckMethodName",condition,currentPage,config);
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
        currentPage = treeSearchs("/CMethod/GetAllCMethodInfo","CMethodRd","CheckMethodName","CheckMethodName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/CMethod/GetAllCMethodInfo","CMethodRd","CheckMethodName","CheckMethodName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/CMethod/GetAllCMethodInfo","CMethodRd","CheckMethodName","CheckMethodName",condition,currentPage,config);
    });
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "CheckMethodCode", //自定义名字：标签id名字
        "param": "CheckMethodCode" //规则中自定义的名字：对应报文中的id字段
    },{
        "ctlid": "CheckMethodName", //自定义名字：标签id名字
        "param": "CheckMethodName" //规则中自定义的名字：对应报文中的id字段
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
    /*-----------------加载树------------*/
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/CMethod/GetAllCMethodInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CheckMethodName",
                            "FieldOpt": "Order BY"
                        }]})
            }},function(Body){
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].CMethodRd == undefined ? "" : treeData[i].CMethodRd,
                    name: treeData[i].CheckMethodName == undefined ? "" : treeData[i].CheckMethodName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();

    /*-------------【新增】按钮----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("urgencyForm");
        $("#ExecType").val("00");
        treeID = null;
    });

    /*---------【删除】----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/CMethod/SaveCMethodInfo", data: {"ExecType": "01", "busData": "{\"CMethodRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        $("#ExecType").val("");
                        treeID = null;
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------【保存】按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("urgencyForm");
        if ($("#CheckMethodCode").val().trim() != "" && $("#CheckMethodName").val().trim() != "") {
            //新增
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "CheckMethodCode": formData["CheckMethodCode"],
                    "CheckMethodName": formData["CheckMethodName"],
                    "Remark":$('#beizhu').val()
                };
                request({url:"/CMethod/SaveCMethodInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                    treeID = null;
                });
            }
            //【编辑】
            else if (treeID != null && treeID != "") {
                newData = {
                    "CMethodRd": treeID,
                    "CheckMethodCode": formData["CheckMethodCode"],
                    "CheckMethodName": formData["CheckMethodName"],
                    "Remark":$('#beizhu').val()
                };
                request({url:"/CMethod/SaveCMethodInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val("");
                    treeID = null;
                });
            }
        } else {
            if ($("#CheckMethodCode").val().trim() == "") {
                $("#CheckMethodCode").css("border-color", "red").prop("placeholder", "不能为空！");
            }else if($("#CheckMethodName").val().trim() == ""){
                $("#CheckMethodName").css("border-color", "red").prop("placeholder", "不能为空！");
            }

        }
    });
});
