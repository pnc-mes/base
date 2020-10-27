/**
 * Created by liufuzhi on 2017/6/30.
 */
/*-------------------树形的处理-------------------*/
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"PrRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/Printer/GetPrInfo", data: objData},function(Body){
            fillform("printerinfo", rule, Body.Data);
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
        currentPage = treeSearchs("/Printer/GetAllPrInfo","PrRd","PrName","PrinterName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Printer/GetAllPrInfo","PrRd","PrName","PrinterName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Printer/GetAllPrInfo","PrRd","PrName","PrinterName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Printer/GetAllPrInfo","PrRd","PrName","PrinterName",condition,currentPage,config);
    });
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //打印机名称
        "ctlid": "prName", //自定义名字：标签id名字
        "param": "PrName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //联系人
        "ctlid": "prSer",
        "param": "PrSer"
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
            url:'/Printer/GetAllPrInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "PrinterName",
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
                    id: treeData[i].PrRd == undefined ? "" : treeData[i].PrRd,
                    name: treeData[i].PrName == undefined ? "" : treeData[i].PrName
                }
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
        $("#ExecType").val("00");
        clearForm("printerinfo");
        treeID = null;
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Printer/SavePrInfo", data: {"ExecType": "01", "busData": "{\"PrRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        $("#_right").hide();
                        toastr.success(Body.MsgDes);
                        treeID = null;//树的id置空表示在删除成功之后树的节点不在处于点击状态，防止再次对此节点进行其他操作
                        $("#ExecType").val("");//树的id变为空
                        currentPage=0;
                        condition = '';
                        loaddata();//再次加载树的节点
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    /*--------------复制按钮顶部菜单点击操作中的----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            request({url:"/Printer/SavePrInfo", data: {"ExecType": "03", "busData": "{\"PrRd\":" + treeID + "}"}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                loaddata();
            });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });
    /*--------------保存按钮顶部菜单点击----------*/
    var newData = {};
    $(".cSave").click(function () {
        formData = transfer("printerinfo");// 获取所有表单数据封装成json对象即数组
        if ($("#prName").val().trim() != "" && $("#prSer").val().trim() != "") {
            //新增打印机信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "PrName": formData["prName"],
                    "PrSer": formData["prSer"],
                    "Remark": formData["remark"]
                };
                request({
                    url: "/Printer/SavePrInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                    treeID=null;
                });
            }
            //编辑打印机信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "PrRd": treeID,
                    "PrName": formData["prName"],
                    "PrSer": formData["prSer"],
                    "Remark": formData["remark"]
                };
                request({
                    url: "/Printer/SavePrInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val('');
                    treeID = null;
                });
            }
        }else{
            if ($("#prName").val().trim() == "") {
                $("#prName").css("border-color", "red");
                $("#prName").prop("placeholder", "不能为空！");
            }
            if ($("#prSer").val().trim() == "") {
                $("#prSer").css("border-color", "red");
                $("#prSer").prop("placeholder", "不能为空！");
            }
        }
    });
});
