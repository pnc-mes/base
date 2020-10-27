/**
 * Created by liufuzhi on 2017/7/1.
 */
/*-------------------树形的处理-------------------*/
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"FaRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/Factory/GetFaInfo", data: objData,}, function (Body) {
            fillform("factoryinfo", rule, Body.Data);
            $("#defaultSelect").showData({
                id:Body.Data.CpRd,
                name:Body.Data.CpName,
                keyfield:"CpRd",
                fields:[
                    {"name":"CpRd"},
                    {"name":"CpName"}
                ]
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
        currentPage = treeSearchs("/Factory/GetAllFaInfo","FaRd","FaName","FactoryName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Factory/GetAllFaInfo","FaRd","FaName","FactoryName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Factory/GetAllFaInfo","FaRd","FaName","FactoryName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Factory/GetAllFaInfo","FaRd","FaName","FactoryName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //工厂名称
        "ctlid": "faName", //自定义名字：标签id名字
        "param": "FaName" //规则中自定义的名字：对应报文中的id字段
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

    //下拉框
    var params = {
        "displaymode": "0",
        "title": "选择企业",
        "binddata": {
            "keyfield": "CpRd",
            "fields": [
                {
                    "caption": "企业id",
                    "name": "CpRd"
                }, {
                    "caption": "企业名称",
                    "name": "CpName"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"CompanyName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "CompanyName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //加载下拉框企业信息
                request({url: "/Cp/GetAllCpInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CpRd": datas[i].CpRd,
                            "CpName": datas[i].CpName,
                        }
                        xldata.push(data);
                    }

                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    };

    $("#defaultSelect").zc_select(params);

    //加载
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/Factory/GetAllFaInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "FactoryName",
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
                    id: treeData[i].FaRd == undefined ? "" : treeData[i].FaRd,
                    name: treeData[i].FaName == undefined ? "" : treeData[i].FaName
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
        clearForm("factoryinfo");
        $("#ExecType").val("00");
        $("#defaultSelect").clearseldata("CpRd");
        treeID = null;
        $("#_right").show();
    });

    /*--------------顶部菜单点击编辑按钮----------*/
    $(".cEdit").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            $("#ExecType").val("02");
            $("#cpName").attr("disabled", false);
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
                        url: "/Factory/SaveFaInfo",
                        data: {"ExecType": "01", "busData": "{\"FaRd\":" + treeID + "}"}
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

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            request({
                url: "/Factory/SaveFaInfo",
                data: {"ExecType": "03", "busData": "{\"FaRd\":" + treeID + "}"}
            }, function (Body) {
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
        // 获取所有表单数据封装成json对象
        formData = transfer("factoryinfo");

        var CpRd =  $("#defaultSelect").getseldata().CpRd;

        if ($("#faName").val() != "" &&  CpRd != "") {
            //新增工厂信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "FaName": formData["faName"],
                    "Contactor": formData["contactor"],
                    "Address": formData["address"],
                    "CpRd": CpRd,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/Factory/SaveFaInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
            //更新工厂信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "FaRd": treeID,
                    "FaName": formData["faName"],
                    "Contactor": formData["contactor"],
                    "Address": formData["address"],
                    "CpRd": CpRd,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/Factory/SaveFaInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
        } else {
            if (CpRd == "") {
                /*$("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect").find("input:eq(0)").val("");
                $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");*/
                toastr.warning("企业不能为空");
                return false;
            }
            if ($("#faName").val() == "") {
                toastr.warning("名称不能为空");
                return false;
            }
        }
    });
});
