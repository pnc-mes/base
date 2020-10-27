/**
 * Created by liufuzhi on 2017/6/23.
 */
/*-------------------树形的处理-------------------*/
$(function () {

    var gys = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "SupRd",
            "fields": [
                {
                    "caption": "SupRd",
                    "name": "SupRd"
                }, {
                    "caption": "SupName",
                    "name": "SupName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"SupplierName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "SupplierName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Supplier/GetAllSupInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SupRd": datas[i].SupRd,
                            "SupName": datas[i].SupName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            },
        }
    };

    $("#gys").zc_select(gys);


    var gc = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "FaRd",
            "fields": [
                {
                    "caption": "FaRd",
                    "name": "FaRd"
                }, {
                    "caption": "FaName",
                    "name": "FaName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
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

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Factory/GetAllFaInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "FaRd": datas[i].FaRd,
                            "FaName": datas[i].FaName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            },
        }
    };

    $("#gc").zc_select(gc);

    /*方法：------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#cpCode").attr("readonly",true);
        treeID =nodeinfo.nodeID;
        var objBusData = JSON.stringify({"SPartRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/SPart/GetSPartInfo",data: objData},function(Body){
            if(Body.Data.Suppier!=null){
                $("#gys").showData({
                    id:Body.Data.Suppier.SupRd,
                    name:Body.Data.Suppier.SupName,
                    keyfield:"SupRd",
                    fields:[
                        {"name":"SupRd"},
                        {"name":"SupName"}
                    ]
                });
            }
            if(Body.Data.Factory!=null){
                $("#gc").showData({
                    id:Body.Data.Factory.FaRd,
                    name:Body.Data.Factory.FaName,
                    keyfield:"FaRd",
                    fields:[
                        {"name":"FaRd"},
                        {"name":"FaName"}
                    ]
                });
            }

            $("#SPartName").val(Body.Data.SPartName);
            $("#VenderSN").val(Body.Data.VenderSN);
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
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
        currentPage = treeSearchs("/SPart/GetAllSPartInfo","SPartRd","SPartName","PartName",condition,currentPage,config);
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
            currentPage = treeSearchs("/SPart/GetAllSPartInfo","SPartRd","SPartName","PartName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/SPart/GetAllSPartInfo","SPartRd","SPartName","PartName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/SPart/GetAllSPartInfo","SPartRd","SPartName","PartName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;

    /*方法：首次加载页面------------*/

    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/SPart/GetAllSPartInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "PartName DESC",
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
                    id: treeData[i].SPartRd == undefined ? "" : treeData[i].SPartRd,
                    name: treeData[i].SPartName == undefined ? "" : treeData[i].SPartName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        })
    };
    // 调用，首次加载页面
    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        $("#ExecType").val("00");
        treeID = null;
        $("#gys").clearseldata("SupRd");
        $("#gc").clearseldata("FaRd");
        $("#SPartName").val("");
        $("#VenderSN").val("");


        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");

    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            var DSource = $("#hidden").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    request({url:"/SPart/SaveSPartInfo",async:true, data:{"ExecType": "01","busData": "{\"SPartRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        $("#ExecType").val("");
                        treeID = null;
                        toastr.success(Body.MsgDes);
                        currentPage=0;
                        condition = "";
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
    var CpCode="";
    $(".cSave").click(function () {
        var SPartName=$("#SPartName").val().trim();
        if(SPartName==""||SPartName==null){
            toastr.warning("名称不能为空");
            return false;
        }
        var SupRd=$("#gys").getseldata().SupRd;
        if(SupRd==null||SupRd==""){
            toastr.warning("供应商不能为空");
            return false;
        }
        var Remark=$("#beizhu").val().trim();

        var VenderSN=$("#VenderSN").val().trim();

        var FaRd=$("#gc").getseldata().FaRd;

        //新增信息
        if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
            newData = {
                "SPartName": SPartName,
                "FaRd": FaRd,
                "SupRd": SupRd,
                "VenderSN": VenderSN,
                "Remark":Remark
            };
            request({url:"/SPart/SaveSPartInfo",data:{"ExecType": $("#ExecType").val(),"busData": JSON.stringify(newData)}},function(Body){
                $("#ExecType").val("");
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = "";
                loaddata();

            });
        }
        //编辑信息
        else if(treeID != null && treeID != ""){
            newData = {
                "SPartRd": treeID,
                "SPartName": SPartName,
                "FaRd": FaRd,
                "SupRd": SupRd,
                "VenderSN": VenderSN,
                "Remark":Remark
            };
            request({url:"/SPart/SaveSPartInfo",data:{"ExecType": "02","busData": JSON.stringify(newData)}},function(Body){
                $("#ExecType").val("");
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = "";
                loaddata();
            });
        }
    });

});
