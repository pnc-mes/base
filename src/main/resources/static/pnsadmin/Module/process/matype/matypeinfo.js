/**
 * Created by liufuzhi on 2017/6/30.
 */
$(function () {
    //

    var kzdxdi = {
        "displaymode": "0",
        "title": "扩展字段定义",
        "binddata": {
            "keyfield": "ExpandRd",
            "fields": [
                {
                    "caption": "扩展字段id",
                    "name": "ExpandRd"
                }, {
                    "caption": "扩展字段名称",
                    "name": "ExpandName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ExpandName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"ExpandType",
                            "FieldOpt":"=",
                            "FieldVal":"00"
                        },{
                            "FieldName":"IsSettleObj",
                            "FieldOpt":"=",
                            "FieldVal":"01"
                        },{
                            "FieldName":"ExpandName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                // obj : 业务数据
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Expand/GetAllExpandInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "ExpandRd": datas[i].ExpandRd,
                            "ExpandName": datas[i].ExpandName,
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
    }
    $("#kzdxdi").zc_select(kzdxdi);




    $("#_right").hide();
    var treeID = null;
    var map=new Map();
    var rule = [{

        "ctlid": "mTCode",
        "param": "MTCode"
    }, {
        "ctlid": "mTName", //自定义名字：标签id名字
        "param": "MTName" //规则中自定义的名字：对应报文中的id字段
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

    var str = "";
    //点击类型节点
    var onClicks = function (nodeinfo, handle) {

        if ("00" == nodeinfo.nodeID) {
            str = "00";
        }
        if ("01" == nodeinfo.nodeID) {
            str = "01";
        }
        if ("02" == nodeinfo.nodeID) {
            str = "02";
        }
        if ("03" == nodeinfo.nodeID) {
            str = "03";
        }

        if (nodeinfo.isRoot) {  //父节点
            var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "MaterialType",
                            "FieldOpt": "=",
                            "FieldVal": str
                        }
                    ]
                };

            request({
                url: '/MaType/GetAllMTInfo',
                data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
            }, function (Body) {
                var treeData = Body.Data.CMTInfo;
                var rules = [{
                    id: "MTRd",
                    text: "MTName",
                    children: "CMTInfo"
                },
                    {
                        id: "MTRd",
                        text: "MTName",
                        children: "CMTInfo"
                    },
                    {
                        id: "MTRd",
                        text: "MTName",
                        children: "CMTInfo"
                    },
                    {
                        id: "MTRd",
                        text: "MTName",
                        children: "CMTInfo"
                    },
                    {
                        id: "MTRd",
                        text: "MTName",
                        children: "CMTInfo"
                    }
                ]

                handle.ckAddChild(rules, treeData);

                if (treeData.length > 0) {

                    $("#_right").show();
                    var treeIDs = treeData[0].MTRd;
                    treeID=treeIDs;
                    var test = {
                        "MTRd": treeIDs
                    };
                    request({
                        url: "/MaType/GetMTInfo",
                        data: {"ExecType": "00", "busData": JSON.stringify(test)}
                    }, function (Body) {
                        fillform("matypeinfo", rule, Body.Data);
                        $("#hidden").attr("h",Body.Data.DSource);
                        var DSource = $("#hidden").attr("h");

                        if(DSource=="00"){
                            $("#mTCode").attr("readonly",true);
                            $("#mTName").attr("readonly",true);
                        }else{
                            $("#mTCode").attr("readonly",false);
                            $("#mTName").attr("readonly",false);
                        }
                        if(Body.Data.ExpandInfo!=null&&Body.Data.ExpandInfo!=""){
                            $("#kzdxdi").showData({
                                id:Body.Data.ExpandInfo.ExpandRd,
                                name:Body.Data.ExpandInfo.ExpandName,
                                keyfield:"ExpandRd",
                                fields:[
                                    {"name":"ExpandRd"},
                                    {"name":"ExpandName"}
                                ]
                            });
                        }
                    });
                }
            });
        }else{
            treeID = nodeinfo.nodeID;

            var test = {
                "MTRd": nodeinfo.nodeID
            };
            request({
                url: "/MaType/GetMTInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(test)}
            }, function (Body) {
                var data=Body.Data;
                str=data.MaterialType;
                fillform("matypeinfo", rule, Body.Data);
                $("#hidden").attr("h",Body.Data.DSource);
                var DSource = $("#hidden").attr("h");

                if(Body.Data.ExpandInfo!=null&&Body.Data.ExpandInfo!=""){
                    $("#kzdxdi").showData({
                        id:Body.Data.ExpandInfo.ExpandRd,
                        name:Body.Data.ExpandInfo.ExpandName,
                        keyfield:"ExpandRd",
                        fields:[
                            {"name":"ExpandRd"},
                            {"name":"ExpandName"}
                        ]
                    });
                }

                if(DSource=="00"){
                    $("#mTCode").attr("readonly",true);
                    $("#mTName").attr("readonly",true);
                }else{
                    $("#mTCode").attr("readonly",false);
                    $("#mTName").attr("readonly",false);
                }
            });
        }

    }


    //只刷新树
    var loadtree = function () {
        var treedataList = [];
        //默认加载无聊类型
        request({url: '/Material/GetMaTypeInfo', data: {"ExecType": "00"}}, function (Body) {
            var treeData = Body.Data;
            for (var i = 0; i < treeData.length; i++) {
                var tree = {
                    id: treeData[i].MaType == undefined ? "" : treeData[i].MaType,
                    name: treeData[i].TName == undefined ? "" : treeData[i].TName
                }
                treedataList.push(tree);
            }
            var config = {
                id: "jstree_demo1",
                data: {
                    source: treedataList,
                    rule: [{
                        id: "id",
                        text: "name"
                    }]
                },
                event: {
                    onClick: onClicks
                }
            };
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    /*-----------------加载树------------*/
    var treedataList = [];
    var loaddata = function () {

        //默认加载无聊类型
        request({url: '/Material/GetMaTypeInfo', data: {"ExecType": "00"}}, function (Body) {
            var treeData = Body.Data;
            for (var i = 0; i < treeData.length; i++) {
                var tree = {
                    id: treeData[i].MaType == undefined ? "" : treeData[i].MaType,
                    name: treeData[i].TName == undefined ? "" : treeData[i].TName
                }
                treedataList.push(tree);
            }
            var config = {
                id: "jstree_demo1",
                data: {
                    source: treedataList,
                    rule: [{
                        id: "id",
                        text: "name"
                    }]
                },
                event: {
                    onClick: onClicks
                }
            };
            $.JstreeEx.init(config);//先调用后加载
        });
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        if (str != null && str!= "") {
            $("#_right").show();
            $("#mTName").val("");
            $("#mTCode").val("");
            $("#ExecType").val("00");
            $("#mTCode").attr("readonly",false);
            $("#mTName").attr("readonly",false);
            $("#kzdxdi").clearseldata("ExpandRd");
            treeID=null;
        } else {
            toastr.warning("请选择左侧一个节点新增类别!");
        }
    });

    $(".cAdd1").click(function () {
        if (treeID != null && treeID != "") {
            $("#_right").show();
            $("#mTName").val("");
            $("#mTCode").val("");
            $("#ExecType").val("00");
            $("#mTCode").attr("readonly",false);
            $("#mTName").attr("readonly",false);
        } else {
            toastr.warning("请选择左侧一个节点新增子类别!");
        }
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            var DSource = $("#hidden").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？将删除下边的所有子类别！', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/MaType/SaveMTInfo",
                        data: {"ExecType": "01", "busData": "{\"MTRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        $("#ExecType").val("");
                        loadtree();
                        treeID = null;
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧节点下的一项再进行删除!");
        }
    });


    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("matypeinfo");
        var ExpandRd=$("#kzdxdi").getseldata().ExpandRd;
        var MaType = str;
        //alert(str);
        if (MaType == "" || MaType == null) {
            toastr.warning("请选择一个类型再新增！");
            return;
        }

        if ($("#mTName").val().trim() != "" && $("#mTCode").val().trim != "") {
            //新增等级批次信息
            if ($("#ExecType").val() == "00") {
                newData = {
                    "ExpandRd":ExpandRd,
                    "PMTRd": treeID,
                    "MTCode": formData["mTCode"],
                    "MTName": formData["mTName"],
                    "MaType": MaType,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/MaType/SaveMTInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    loadtree();
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    treeID=null;
                });
            }
            //编辑等级批次信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "ExpandRd":ExpandRd,
                    "MTRd": treeID,
                    "MTCode": formData["mTCode"],
                    "MTName": formData["mTName"],
                    "Remark": formData["remark"]
                };
                request({
                    url: "/MaType/SaveMTInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    loadtree();
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    treeID=null;
                });
            }
        } else {
            $("#mTName").css("border-color", "red");
            $("#mTName").prop("placeholder", "不能为空！");
            $("#mTCode").css("border-color", "red");
            $("#mTCode").prop("placeholder", "不能为空！");
        }
    });
    var to = false;
    $('.input1').keyup(function () {
        if (to) {
            clearTimeout(to);
        }
        to = setTimeout(function () {
            var v = $('.input1').val();

            $('#jstree_demo1').jstree(true).search(v);
        });

    });

});
