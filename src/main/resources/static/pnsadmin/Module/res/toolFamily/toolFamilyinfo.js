/**
 * Created by 郝赞 on 2018/6/14.
 */

$(function () {
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //工具家族名称
        "ctlid": "ToolFamilyName", //自定义名字：标签id名字
        "param": "ToolFamilyName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //描述
        "ctlid": "Description",
        "param": "Description"
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

    //保养计划
    request({url:"/CyclePlan/GetAllCyclePlanInfo", data: {"ExecType": "00"}},function(Body){
        var data=Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option  value='+data[i].CyclePlanRd+ ',0'+ '>'+data[i].CyclePlanName +'</option>';
        }
        $("#byjh #zqxbyjh").html(aa);
    });
    request({url:"/FrePlan/GetAllFrePlanInfo", data: {"ExecType": "00"}},function(Body){
        var data=Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].FrePlanRd+ ',1'+ '>'+data[i].FrePlanName +'</option>';
        }
        $("#byjh #csbyjh").html(aa);
        $("#byjh").selectpicker({
            width:200
        });
    });


    /*-------------------点击树节点------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"ToolFamilyRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/ToolFamily/GetToolFamilyInfo",data: objData},function(Body){
            fillform("devform", rule, Body.Data);
            /*工厂信息*/
            if(Body.Data.Factory!=null){
                $("#defaultSelect2").showData({
                    id:Body.Data.Factory.FaRd,
                    name:Body.Data.Factory.FaName,
                    keyfield:"FaRd",
                    fields:[
                        {"name":"FaRd"},
                        {"name":"FaName"}
                    ]
                });
            }

            /*状态,模型信息*/
            if(Body.Data.Modle!=null){
                $("#defaultSelect3").showData({
                    id:Body.Data.Modle.DSMRd,
                    name:Body.Data.Modle.ModelName,
                    keyfield:"DSMRd",
                    fields:[
                        {"name":"DSMRd"},
                        {"name":"ModelName"}
                    ]
                });
            }
            var data=Body.Data.PMInfo;
            if(data!=null&&data!=""&&data.length>0){
                var a =[];
                for(var i  in data){
                    if(data[i].PMType=="00"){
                        a.push(data[i].PMRd+",0")
                    }
                    if(data[i].PMType=="01"){
                        a.push(data[i].PMRd+",1")
                    }
                }
                $("#byjh").selectpicker('val', a);
            }else {
                $("#byjh").selectpicker('val', "");
            }

        })
    }
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
        //定义事件获取点击的值
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
        currentPage = treeSearchs("/ToolFamily/GetAllToolFamilyInfo","ToolFamilyRd","ToolFamilyName","ToolFamilyName",condition,currentPage,config);
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
            currentPage = treeSearchs("/ToolFamily/GetAllToolFamilyInfo","ToolFamilyRd","ToolFamilyName","ToolFamilyName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/ToolFamily/GetAllToolFamilyInfo","ToolFamilyRd","ToolFamilyName","ToolFamilyName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/ToolFamily/GetAllToolFamilyInfo","ToolFamilyRd","ToolFamilyName","ToolFamilyName",condition,currentPage,config);
    });



    //加载工厂下拉框数据
    var params2 = {
        "displaymode": "0",
        "title": "选择工厂",
        "binddata": {
            "keyfield": "FaRd",
            "fields": [
                {
                    "caption": "工厂id",
                    "name": "FaRd"
                }, {
                    "caption": "工厂名称",
                    "name": "FaName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"FactoryName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"FactoryName",
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
                    url:"/Factory/GetAllFaInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "FaRd": datas[i].FaRd,
                            "FaName": datas[i].FaName,
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
    $("#defaultSelect2").zc_select(params2);

    //加载状态模型下拉框数据
    var params3 = {
        "displaymode": "0",
        "title": "选择设备状态模型",
        "binddata": {
            "keyfield": "DSMRd",
            "fields": [
                {
                    "caption": "设备状态模型id",
                    "name": "DSMRd"
                }, {
                    "caption": "设备状态模型",
                    "name": "ModelName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ModelName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"ModelName",
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
                    url:"/DevSM/GetAllDevSMInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DSMRd": datas[i].DSMRd,
                            "ModelName": datas[i].ModelName,
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
    $("#defaultSelect3").zc_select(params3);



    /*-----------------加载树------------*/
    var loaddata = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
               {
                    "FieldName":"ToolFamilyName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var treedataList = [];
        request({url:'/ToolFamily/GetAllToolFamilyInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){

            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            if(treeData.length<=0){
                return false;
            }
            var len = treeData.length >= 20 ? 20:treeData.length;

            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].ToolFamilyRd == undefined ? "" : treeData[i].ToolFamilyRd,
                    name: treeData[i].ToolFamilyName == undefined ? "" : treeData[i].ToolFamilyName
                }
                treedataList.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载

        });
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $("#byjh").selectpicker('val',"");
        $("#_right").show();
        clearForm("devform");
        treeID = null;
        $("#defaultSelect2").clearseldata("FaRd");
        $("#defaultSelect3").clearseldata("DSMRd");
        $("#ExecType").val("00");
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null) {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/ToolFamily/SaveToolFamilyInfo", data: {"ExecType": "01", "busData": "{\"ToolFamilyRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        condition = "";
                        currentPage = 0;
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
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

            request({url:"/ToolFamily/SaveToolFamilyInfo", data: {"ExecType": "03", "busData": "{\"ToolFamilyRd\":" + treeID + "}"}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                treeID = null;
                condition = "";
                currentPage = 0;
                loaddata();
            });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        //保养计划
        var PMInfo=[];
        var data=$("#byjh").val();
        if(data!=null&&data!=""){
            if(data.length>0){
                for(var i in data){
                    if(data[i].indexOf(",0")>=0){
                        var split=data[i].split(",0");
                        var PMInfos={
                            "PMRd":  split[0],
                            "PMType":"00"
                        }
                        PMInfo.push(PMInfos)
                    }
                    if(data[i].indexOf(",1")>=0){
                        var split=data[i].split(",1");
                        var PMInfos={
                            "PMRd":  split[0],
                            "PMType":"01"
                        }
                        PMInfo.push(PMInfos)
                    }
                }
            }

        }
        // 获取所有表单数据封装成json对象
        formData = transfer("devform");
        //工厂下拉框选中数据
        var FaRd = $("#defaultSelect2").getseldata().FaRd;
        //设备状态模型下拉框选中数据
        var DSMRd = $("#defaultSelect3").getseldata().DSMRd;
        if ($("#ToolName").val()!= "" ) {
            //新增设备信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "ToolFamilyName": formData["ToolFamilyName"],
                    "Description":formData["Description"],
                    "FaRd": FaRd,
                    "DSMRd":DSMRd,
                    "Remark": formData["remark"],
                    "PMInfo":PMInfo
                };
                request({url:"/ToolFamily/SaveToolFamilyInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loaddata();
                });
            }
            //编辑设备信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "ToolFamilyRd": treeID,
                    "ToolFamilyName": formData["ToolFamilyName"],
                    "Description":formData["Description"],
                    "FaRd": FaRd,
                    "DSMRd":DSMRd,
                    "Remark": formData["remark"],
                    "PMInfo":PMInfo
                };
                request({url:"/ToolFamily/SaveToolFamilyInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loaddata();
                    treeID = null;
                });
            }
        }
    });

});