/*-------------------树形的处理-------------------*/
$(function () {
    //处理页面加载时，直接点击保存事件
    var isEdit=false;

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "CTName", //自定义名字：标签id名字
        "param": "CTName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //联系人
        "ctlid": "contactor",
        "param": "Contactor"
    }, {
        //联系地址
        "ctlid": "address",
        "param": "Address"
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
        isEdit=true;
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CTRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/CoType/GetCTInfo", data: objData,}, function (Body) {
            fillform("factoryinfo", rule, Body.Data);

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
        currentPage = treeSearchs("/CoType/GetAllCTInfo","CTRd","CTName","CoTName",condition,currentPage,config);
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
            currentPage = treeSearchs("/CoType/GetAllCTInfo","CTRd","CTName","CoTName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/CoType/GetAllCTInfo","CTRd","CTName","CoTName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/CoType/GetAllCTInfo","CTRd","CTName","CoTName",condition,currentPage,config);
    });





    //加载
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/CoType/GetAllCTInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CoTName",
                            "FieldOpt": "Order BY"
                        }]})
            }}, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].CTRd == undefined ? "" : treeData[i].CTRd,
                    name: treeData[i].CTName == undefined ? "" : treeData[i].CTName
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
        isEdit=true;
        clearForm("factoryinfo");
        $("#ExecType").val("00");
        treeID = null;
        $("#_right").show();
    });

    /*--------------顶部菜单点击编辑按钮----------*/
    $(".cEdit").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            $("#ExecType").val("02");
        } else {
            toastr.warning("请选择左侧要编辑的一项再进行编辑!");
        }

    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/CoType/SaveCTInfo",
                        data: {"ExecType": "01", "busData": "{\"CTRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage=0;
                        condition = '';
                        loaddata();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });


    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        if(!isEdit){
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }
        // 获取所有表单数据封装成json对象
        formData = transfer("factoryinfo");

        if ($("#CTName").val() == ""||$("#CTName").val()==null) {
            toastr.warning("名称不能为空");
            return false;
        }




        if ($("#CTName").val() != "" &&  treeID != "") {
            //新增工厂信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "CTName": formData["CTName"],
                    "Remark": formData["remark"]
                };
                request({
                    url: "/CoType/SaveCTInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    treeID=="";
                    loaddata();
                });
            }
            //更新工厂信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "CTRd": treeID,
                    "CTName": formData["CTName"],
                    "Remark": formData["remark"]
                };
                request({
                    url: "/CoType/SaveCTInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    treeID=="";
                    loaddata();
                });
            }
        }
    });
});
