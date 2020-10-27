/**
 * Created by liufuzhi on 2018/1/30.
 */
$(function () {
    var treeID = null;
    var isdes="";
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"SgyRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/Strategy/GetSgyInfo",data: objData},function(Body){
            fillform("syginfoForm", rule, Body.Data);
            $("#hidden").attr("h",Body.Data.IsDef);
            isdes=$("#hidden").attr("h");
            //默认策略
            if (Body.Data.IsDef == "00") {
                $("#IsDef").prop("checked", true);
            } else {
                $("#IsDef").prop("checked", false);
            }

            //允许超收
            if (Body.Data.SInCome == "00") {
                $("#SInCome").prop("checked", true);
            } else {
                $("#SInCome").prop("checked", false);
            }

            //允许部分收货
            if (Body.Data.PartRev == "00") {
                $("#PartRev").prop("checked", true);
            } else {
                $("#PartRev").prop("checked", false);
            }
            //作用于
            $("#ActOn").find("option").each(function () {
                if($(this).val() == Body.Data.ActOn){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });
            //状态
            $("#Status").find("option").each(function () {
                if($(this).val() == Body.Data.Status){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });
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
        currentPage = treeSearchs("/Strategy/GetAllSgyInfo","SgyRd","SgyName","StrategyName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Strategy/GetAllSgyInfo","SgyRd","SgyName","StrategyName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })
    var list = [];
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Strategy/GetAllSgyInfo","SgyRd","SgyName","StrategyName",condition,currentPage,config,list);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Strategy/GetAllSgyInfo","SgyRd","SgyName","StrategyName",condition,currentPage,config,list);
    });

    $("#_right").hide();

    var rule = [{
        "ctlid": "SgyName", //自定义名字：标签id名字
        "param": "SgyName" //规则中自定义的名字：对应报文中的id字段
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
            url:'/Strategy/GetAllSgyInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CreateTime DESC",
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
                    id: treeData[i].SgyRd == undefined ? "" : treeData[i].SgyRd,
                    name: treeData[i].SgyName == undefined ? "" : treeData[i].SgyName
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

    $("#add").click(function () {
        $("#_right").show();
        $("#Status").val("00");
        $("#ActOn").val("00");
        clearForm("syginfoForm");
        $("#ExecType").val("00");
        treeID = null;
        isdes="01";
    });

    /*---------删除----------*/
    $("#remove").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            if(isdes=="00"){
                toastr.warning("默认策略不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Strategy/SaveSgyInfo", data: {"ExecType": "01", "busData": "{\"SgyRd\":" + treeID + "}"}},function(Body){
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


    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $("#save").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("syginfoForm");
        if($("#IsDef").prop("checked") == false && isdes=="00"){
            toastr.warning("不能保存，因为必须保证有且只有一个默认策略");
            return;
        }

        if ($("#SInCome").prop("checked") == true) {
            var istrue = "00"
        } else {
            var istrue = "01"
        }
        if ($("#PartRev").prop("checked") == true) {
            var istrue1 = "00"
        } else {
            var istrue1 = "01"
        }
        if ($("#IsDef").prop("checked") == true) {
            var istrue2 = "00"
        } else {
            var istrue2 = "01"
        }

        var status = $("#Status").val();

        var ActOn = $("#ActOn").val();
        if($("#SgyName").val().trim()!= ""){
            //新增等级批次信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "SgyName": formData["SgyName"],
                    "IsDef": istrue2,
                    "SInCome": istrue,
                    "PartRev": istrue1,
                    "ActOn": ActOn,
                    "Status": status,
                    "Remark": formData["remark"]
                };
                request({url:"/Strategy/SaveSgyInfo",data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    $("#hidden1").attr("h",Body.Data.SgyRd);
                    treeID=$("#hidden1").attr("h");
                    $("#_right").hide();

                });
            }
            //编辑等级批次信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "SgyRd": treeID,
                    "SgyName": formData["SgyName"],
                    "IsDef": istrue2,
                    "SInCome": istrue,
                    "PartRev": istrue1,
                    "ActOn": ActOn,
                    "Status": status,
                    "Remark": formData["remark"]
                };
                request({url:"/Strategy/SaveSgyInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val("");
                    treeID = null;
                    toastr.success(Body.MsgDes);
                    $("#_right").hide();

                });
            }
        }else{
            $("#SgyName").css("border-color","red");
            $("#SgyName").prop("placeholder","不能为空！");
        }
    });

    //筛选
    var params = [ {
        "caption": "策略名称",
        "name": "StrategyName",
        "valtype": "00"
    }, {
        "caption": "作用于",
        "name": "ActOn",
        "valtype": "01",
        "data": "\"00\":\"采购单\"|\"01\":\"来料收货通知单\""
    }, {
        "caption": "状态",
        "name": "Status",
        "valtype": "01",
        "data": "\"00\":\"可用\"|\"01\":\"不可用\""
    }];
    var InitData1={};
    var event = {
        onsure: function (result) {
            var FiledList = [];
            for (var i in result) {
                for (var j in result[i]) {
                    if (result[i][j].trim() != "" && result[i][j] != null) {
                        var Filed = {
                            "FieldName": j,
                            "FieldOpt": "like",
                            "FieldVal": "%" + result[i][j] + "%"
                        };
                        FiledList.push(Filed);
                    }
                }
            }
            var newTree=[];
            InitData1 = {
                FiledList:FiledList
            }
            request({url: '/Strategy/GetAllSgyInfo', data: {"ExecType": "00","InitData": JSON.stringify(InitData1)}}, function (Body) {
                currentPage = 0;
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].SgyRd == undefined ? "" : treeData[i].SgyRd,
                        name: treeData[i].SgyName == undefined ? "" : treeData[i].SgyName
                    };
                    newTree.push(tree);
                }
                config.data.source = newTree;
                $.JstreeEx.init(config);//先调用后加载
            })
            delete InitData1.FiledList['SgyName'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);

});