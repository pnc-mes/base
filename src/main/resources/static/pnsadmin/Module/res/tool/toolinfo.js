/**
 * Created by liufuzhi on 2017/7/3.
 */

$(function () {
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //工具名称
        "ctlid": "ToolName", //自定义名字：标签id名字
        "param": "ToolName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //供应商序列号
        "ctlid": "VenderSN",
        "param": "VenderSN"
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

    $("#changeadd").click(function () {
        var KeyName=$("#ysshbh").val().trim();
        var KeyValue=$("#ysshmc").val().trim();
        if(KeyName==null||KeyName==""){
            toastr.warning("不能为空");
            return false;
        }
        if(KeyValue==null||KeyValue==""){
            toastr.warning("不能为空");
            return false;
        }
        var BZDtl={
            "KeyName":KeyName,
            "KeyValue":KeyValue
        };
        addSrow("list4",BZDtl);
        $("#ysshbh").val("");
        $("#ysshmc").val("");
    });

    $('.del1').on('click',function(){
        derow('list4');
    });

    $("._close").click(function () {
        parent.layer.closeAll();
    });
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

    //熊伟33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333
    request({url:"/CheckPlan/GetAllCheckPlanInfo", data: {"ExecType": "00"}},function(Body){
        var data=Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option  value='+data[i].CheckPlanRd+ ',0'+ '>'+data[i].CheckPlanName +'</option>';
        }
        $("#jyffSelect #djjh").html(aa);
    });




    /*-------------------点击树节点------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"ToolRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/Tool/GetToolInfo",data: objData},function(Body){
            fillform("devform", rule, Body.Data);
            /*供应商信息*/
            $("#defaultSelect").showData({
                id:Body.Data.Suppier==null?"":Body.Data.Suppier.SupRd,
                name:Body.Data.Suppier==null?"":Body.Data.Suppier.SupName,
                keyfield:"SupRd",
                fields:[
                    {"name":"SupRd"},
                    {"name":"SupName"}
                ]
            });
            /*工具家族信息*/
            $("#defaultSelect1").showData({
                id:Body.Data.ToolFamily==null?'':Body.Data.ToolFamily.ToolFamilyRd,
                name:Body.Data.ToolFamily==null?'':Body.Data.ToolFamily.ToolFamilyName,
                keyfield:"ToolFamilyRd",
                fields:[
                    {"name":"ToolFamilyRd"},
                    {"name":"ToolFamilyName"}
                ]
            });
            /*工厂信息*/
            $("#defaultSelect2").showData({
                id:Body.Data.Factory==null?"":Body.Data.Factory.FaRd,
                name:Body.Data.Factory==null?"":Body.Data.Factory.FaName,
                keyfield:"FaRd",
                fields:[
                    {"name":"FaRd"},
                    {"name":"FaName"}
                ]
            });
            /*状态,模型信息*/
            $("#defaultSelect3").showData({
                id:Body.Data.Model==null?"":Body.Data.Model.DSMRd,
                name:Body.Data.Model==null?"":Body.Data.Model.ModelName,
                keyfield:"DSMRd",
                fields:[
                    {"name":"DSMRd"},
                    {"name":"ModelName"}
                ]
            });
            //标准值
           $("#Reference").val(Body.Data.ToolBZInfo.Reference);
            if(Body.Data.ToolBZInfo.BZDtl.length>0){
                var config={
                    tableId:"list4",
                    data: Body.Data.ToolBZInfo.BZDtl,
                    colArr:colNamesArr,
                    multiselect:true,
                    width:0.84,
                    height:0.35
                };
                fullTable(config);
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


            var dat=Body.Data.CheckPlanInfo;
            if(dat!=null&&dat!=""&&dat.length>0){
                var as =[];
                for(var i  in dat){
                    if(dat[i].CheckPlanRd!=0){
                        as.push(dat[i].CheckPlanRd+",0")
                    }
                    if(dat[i].CheckPlanRd==0){
                        as.push(dat[i].CheckPlanRd+",1")
                    }
                }
                $("#jyffSelect").selectpicker('val', as);
            }else {
                $("#jyffSelect").selectpicker('val', "");
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
        currentPage = treeSearchs("/Tool/GetAllToolsInfo","ToolRd","ToolName","ToolName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Tool/GetAllToolsInfo","ToolRd","ToolName","ToolName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Tool/GetAllToolsInfo","ToolRd","ToolName","ToolName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Tool/GetAllToolsInfo","ToolRd","ToolName","ToolName",condition,currentPage,config);
    });





    //加载制造商下拉列表
    var params = {
        "displaymode": "1",
        "title": "供应商",
        "binddata": {
            "keyfield": "SupRd",
            "fields": [
                {
                    "caption": "供应商id",
                    "name": "SupRd"
                }, {
                    "caption": "供应商名称",
                    "name": "SupName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"SupplierName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"SupplierName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //加载下拉框供应商信息
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Supplier/GetAllSupInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SupRd": datas[i].SupRd,
                            "SupName": datas[i].SupName,
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

    $("#defaultSelect").zc_select(params);
    var colNamesArr = [
        {"Caption": "标准值名称", "Name": "KeyName"},
        {"Caption": "标准值", "Name": "KeyValue"}
    ];




   //加载工具家族下拉框数据
    var params1 = {
        "displaymode": "0",
        "title": "选择工具家族",
        "binddata": {
            "keyfield": "ToolFamilyRd",
            "fields": [
                {
                    "caption": "工具家族id",
                    "name": "ToolFamilyRd"
                }, {
                    "caption": "工具家族名称",
                    "name": "ToolFamilyName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ToolFamilyName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"ToolFamilyName",
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
                    url:"/ToolFamily/GetAllToolFamilyInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "ToolFamilyRd": datas[i].ToolFamilyRd,
                            "ToolFamilyName": datas[i].ToolFamilyName,
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
    $("#defaultSelect1").zc_select(params1);

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
                    "FieldName":"ToolName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var treedataList = [];
        request({url:'/Tool/GetAllToolsInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){

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
                    id: treeData[i].ToolRd == undefined ? "" : treeData[i].ToolRd,
                    name: treeData[i].ToolName == undefined ? "" : treeData[i].ToolName
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

        var config={
            tableId:"list4",
            data: [],
            colArr:colNamesArr,
            multiselect:true,
            width:0.84,
            height:0.35
        };


        fullTable(config);
        $("#byjh").selectpicker('val',"");
        $("#jyffSelect").selectpicker('val',"");
        $("#_right").show();
        clearForm("devform");
        treeID = null;
        $("#defaultSelect").clearseldata("SupRd");
        $("#defaultSelect1").clearseldata("ToolFamilyRd");
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
                    request({url:"/Tool/SaveToolInfo", data: {"ExecType": "01", "busData": "{\"ToolRd\":" + treeID + "}"}},function(Body){
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

            request({url:"/Tool/SaveToolInfo", data: {"ExecType": "03", "busData": "{\"ToolRd\":" + treeID + "}"}},function(Body){
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
        // 获取所有表单数据封装成json对象
        formData = transfer("devform");

        //供应商下拉框选中数据
        var SupplierRd = $("#defaultSelect").getseldata().SupRd;

        //工厂下拉框选中数据
        var FactoryRd = $("#defaultSelect2").getseldata().FaRd;

        //设备状态模型下拉框选中数据
        var DSMRd = $("#defaultSelect3").getseldata().DSMRd;

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
        if(PMInfo.length>0){
            var j =1;
            for ( var i = 0; i <PMInfo.length; i++){
                if(PMInfo[i].PMType=="01"){
                    j++;
                }
            }
            if(j>2){
                toastr.warning("保养计划中选择次数保养时，只允许选择一条，请重新选择");
                return false;
            }
        }


        //点检计划
        var CheckPlanInfo=[];
        var data2=$("#jyffSelect").val();
        if(data2!=null&&data2!=""){
            if(data2.length>0){
                for(var i in data2){
                    if(data2[i].indexOf(",0")>=0){
                        var split=data2[i].split(",0");
                        var CheckPlanInfos={
                            "CheckPlanRd":  split[0]
                        }
                        CheckPlanInfo.push(CheckPlanInfos)
                    }
                    if(data2[i].indexOf(",1")>=0){
                        var split=data2[i].split(",1");
                        var CheckPlanInfos={
                            "CheckPlanRd":  split[0]
                        }
                        CheckPlanInfo.push(CheckPlanInfos)
                    }
                }
            }

        }

        //工具家族下拉框选中数据
        var ToolFamilyRd = $("#defaultSelect1").getseldata().ToolFamilyRd;
        if (!(formData.ToolName==null||formData.ToolName=="")) {

            var list4Data= getTableData("list4");
            var BusData={
                "Reference":$("#Reference").val(),
                "BZDtl":list4Data
            };



            var list4Data= getTableData("list4");


            //新增设备信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "ToolName": formData["ToolName"],
                    "FaRd": FactoryRd,
                    //"VenderType": formData["VenderType"],
                    "VenderSN": formData["VenderSN"],
                    "ToolFamilyRd":ToolFamilyRd == "null" ? '':ToolFamilyRd,
                    "DSMRd":DSMRd,
                    "SupplierRd":SupplierRd,
                    "Remark": formData["remark"],
                    "PMInfo":PMInfo,
                    "CheckPlanInfo":CheckPlanInfo,
                    "ToolBZInfo":BusData
                };
                request({url:"/Tool/SaveToolInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
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
                    "ToolRd": treeID,
                    "ToolName": formData["ToolName"],
                    "FaRd": FactoryRd,
                    //"VenderType": formData["VenderType"],
                    "VenderSN": formData["VenderSN"],
                    "ToolFamilyRd":ToolFamilyRd == "null" ? '':ToolFamilyRd,
                    "DSMRd":DSMRd,
                    "SupplierRd":SupplierRd,
                    "Remark": formData["remark"],
                    "PMInfo":PMInfo,
                    "CheckPlanInfo":CheckPlanInfo,
                    "ToolBZInfo":BusData
                };
                request({url:"/Tool/SaveToolInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loaddata();
                    treeID = null;
                });
            }
        } else {
            if ($("#ToolName").val().trim() == ""||$("#ToolName").val().trim() ==null) {
                toastr.warning("名称不能为空");
                return false;
            }

        }
    });

});