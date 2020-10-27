$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"UnitRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/Unit/GetUnitInfo", data: objData},function(Body){
            //去除边框颜色
            $("#unitName").removeAttr("style");
            fillform("unitInfo", rule, Body.Data);
            $("#hidden").attr("h",Body.Data.DSource);
            var DSource = $("#hidden").attr("h");

            if(DSource=="00"){
                $("#unitName").attr("readonly",true);
            }else{
                $("#unitName").attr("readonly",false);
            }
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
        currentPage = treeSearchs("/Unit/GetAllUnitInfo","UnitRd","UnitName","UnitName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Unit/GetAllUnitInfo","UnitRd","UnitName","UnitName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Unit/GetAllUnitInfo","UnitRd","UnitName","UnitName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Unit/GetAllUnitInfo","UnitRd","UnitName","UnitName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "unitName", //自定义名字：标签id名字
        "param": "UnitName" //规则中自定义的名字：对应报文中的id字段
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
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/Unit/GetAllUnitInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "UnitName",
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
                    id: treeData[i].UnitRd == undefined ? "" : treeData[i].UnitRd,
                    name: treeData[i].UnitName == undefined ? "" : treeData[i].UnitName
                };
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
        clearForm("unitInfo");
        $("#ExecType").val("00");
        treeID = null;
        $("#unitName").attr("readonly",false);
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != ""){
            var DSource = $("#hidden").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Unit/SaveUnitInfo", data: {"ExecType": "01", "busData": "{\"UnitRd\":" + treeID + "}"}},function(Body){
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

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            request({url:"/Unit/SaveUnitInfo", data: {"ExecType": "03", "busData": "{\"UnitRd\":" + treeID + "}"}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                treeID = null;
                currentPage=0;
                condition = '';
                loaddata();
            });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        if ($("#unitName").val().trim() != "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("unitInfo");
            //新增计量单位信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "UnitName": formData["UnitName"],
                    "Remark": formData["remark"]
                };
                request({url:"/Unit/SaveUnitInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
            //编辑计量单位信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "UnitRd": treeID,
                    "UnitName": formData["UnitName"],
                    "Remark": formData["remark"]
                };
                request({url:"/Unit/SaveUnitInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val("");
                    treeID = null;
                });
            }
        } else {
            if ($("#unitName").val().trim() == "") {
                $("#unitName").css("border-color", "red");
                $("#unitName").prop("placeholder", "不能为空！");
            }
        }
    });
});
