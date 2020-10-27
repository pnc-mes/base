/**
 * Created by liufuzhi on 2017/7/1.
 */
/*-------------------树形的处理-------------------*/
$(function () {

    /*-------------------点击树节点------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"WCRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/WorkC/GetWCInfo",data: objData},function(Body){
            fillform("wcenterinfo", rule, Body.Data);
            $("#defaultSelect").showData({
                id:Body.Data.FaRd,
                name:Body.Data.FaName,
                keyfield:"FaRd",
                fields:[
                    {"name":"FaRd"},
                    {"name":"FaName"}
                ]
            });
        })
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
        currentPage = treeSearchs("/WorkC/GetAllWCInfo","WCRd","WCName","CenterName",condition,currentPage,config);
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
        currentPage = treeSearchs("/WorkC/GetAllWCInfo","WCRd","WCName","CenterName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/WorkC/GetAllWCInfo","WCRd","WCName","CenterName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/WorkC/GetAllWCInfo","WCRd","WCName","CenterName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //工作中心名称
        "ctlid": "wCName", //自定义名字：标签id名字
        "param": "WCName" //规则中自定义的名字：对应报文中的id字段
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

    //加载下拉框工厂信息
    var params = {
        "displaymode": "0",
        "title": "选择工厂",
        "binddata": {
            "keyfield": "FaRd",
            "fields": [
                {
                    "caption": "工厂id",
                    "name": "FaRd"
                }, {
                    "caption": "所属工厂",
                    "name": "FaName"
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
                            "FieldName":"FactoryName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "FactoryName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url:"/Factory/GetAllFaInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "FaRd": datas[i].FaRd,
                            "FaName": datas[i].FaName
                        };
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

    /*-----------------加载树------------*/
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/WorkC/GetAllWCInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CenterName",
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
                    id: treeData[i].WCRd == undefined ? "" : treeData[i].WCRd,
                    name: treeData[i].WCName == undefined ? "" : treeData[i].WCName
                }
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
        clearForm("wcenterinfo");
        $("#defaultSelect").clearseldata("FaRd");
        treeID = null;
        $("#ExecType").val("00");
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
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/WorkC/SaveWCInfo", data: {"ExecType": "01", "busData": "{\"WCRd\":" + treeID + "}"}},function(Body){
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
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            request({url:"/WorkC/SaveWCInfo", data: {"ExecType": "03", "busData": "{\"WCRd\":" + treeID + "}"}},function(Body){
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

        var FaRd = $("#defaultSelect").getseldata().FaRd;

        if ($("#wCName").val().trim() != "" && FaRd != "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("wcenterinfo");
            //新增工作中心信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "WCName": formData["wCName"],
                    "FaRd": FaRd,
                    "Remark": formData["remark"]
                };
                request({url:"/WorkC/SaveWCInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
            //编辑工作中心信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "WCRd": treeID,
                    "WCName": formData["wCName"],
                    "FaRd": FaRd,
                    "Remark": formData["remark"]
                };
                request({url:"/WorkC/SaveWCInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                    treeID = null;
                });
            }
        } else {
            if ($("#wCName").val().trim() == "") {
                $("#wCName").css("border-color", "red");
                $("#wCName").prop("placeholder", "不能为空！");
            }
            if (FaRd == "") {
                $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect").find("input:eq(0)").val("");
                $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
            }
        }
    });
});
