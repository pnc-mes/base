/**
 * Created by liufuzhi on 2017/7/3.
 */

$(function () {

    $("#_right").hide();
    var treeID = null;
    var maps=[];//接受扩展字段
    var rule = [
        {
            "ctlid": "devName",
            "param": "DevName"
        }, {
            "ctlid": "creatPeople",
            "param": "Creator"
        }, {
            "ctlid": "creatTime",
            "param": "CreateTime"
        }, {
            "ctlid": "modifyPeople",
            "param": "LastModifyMan"
        }, {
            "ctlid": "modifyTime",
            "param": "LastModifyTime"
        }, {
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

    /*-------------------点击树节点------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"DevRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };


        /*------------------获取点击之后一个节点的数据------------------*/
        request({url:"/Device/GetDevInfo",data: objData},function(Body){

                var aa =Body.Data.LineInfo;
            var a=[];
            for(var i in aa){
                a.push(aa[i].LineRd)
            }

            $("#scxb").selectpicker('val', a);

            fillform("devform", rule, Body.Data);

            $("#sbbm").val(Body.Data.DevCode);
            $("#Place").val(Body.Data.Place);
            $("#defaultSelect").showData({
                id:Body.Data.SupRd,
                name:Body.Data.SupName,
                keyfield:"SupRd",
                fields:[
                    {"name":"SupRd"},
                    {"name":"SupName"}
                ]
            });

            if(Body.Data.ToolFInfo!=null&&Body.Data.ToolFInfo!=""){
                $("#toolfamil").showData({
                    id:Body.Data.ToolFInfo.ToolFRd,
                    name:Body.Data.ToolFInfo.ToolFName,
                    keyfield:"ToolFamilyRd",
                    fields:[
                        {"name":"ToolFamilyRd"},
                        {"name":"ToolFamilyName"}
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

            var CheckPlanInfo=Body.Data.CheckPlanInfo;
            if(CheckPlanInfo!=null&&CheckPlanInfo!=""&&CheckPlanInfo.length>0){
                var a =[];
                for(var i  in CheckPlanInfo){
                    a.push(CheckPlanInfo[i].CheckPlanRd)
                }
                $("#djjh").selectpicker('val', a);
            }else {
                $("#djjh").selectpicker('val', "");
            }


            $("#defaultSelect1").showData({
                id:Body.Data.DevFRd,
                name:Body.Data.DevFName,
                keyfield:"DevFRd",
                fields:[
                    {"name":"DevFRd"},
                    {"name":"DevFName"}
                ]
            });


            $("#defaultSelect2").showData({
                id:Body.Data.DSMRd,
                name:Body.Data.ModelName,
                keyfield:"DSMRd",
                fields:[
                    {"name":"DSMRd"},
                    {"name":"ModelName"}
                ]
            });
           //标准值
            $("#Reference").val(Body.Data.DevBZInfo.Reference);
            if(Body.Data.DevBZInfo.BZDtl.length>0){
                var config={
                    tableId:"list4",
                    data: Body.Data.DevBZInfo.BZDtl,
                    colArr:colNamesArr,
                    multiselect:true,
                    width:0.84,
                    height:0.35
                };
                fullTable(config);
            }
            //加载设备特性
            var data=Body.Data.DevPropertyInfo;
            if(data.length>0){
                $("#sbtx").attr("style","display:block;");
            }else {
                $("#sbtx").attr("style","display:none;");
            }
            var i=1;
            $(".formdata").empty();
            maps=[];

            data.forEach(function (e) {
                maps.push(e.FieldName);
                if(e.FiledType=="00"){
                    $(".formdata").append(e.DisplayName + "&emsp;"+"<input  class=' formgroup maBottom'  style='width: 17.4%;margin-top: 0px;;' id="+e.FieldName+" name="+e.DisplayName+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    if(e.Val!=null){
                        $("#"+e.FieldName).val(e.Val);
                    }else {
                        $("#"+e.FieldName).val();
                    }


                    if(i%2==0){
                        $(".formdata").append("<tr>");
                    }
                }
                if(e.FiledType=="01"){

                    $(".formdata").append(e.DisplayName+ "&emsp;" + " <select  class=' formgroup maBottom'  style='width: 17.4%;margin-top:0px;;' id="+e.FieldName+" name="+e.DisplayName+" ><option>"+''+"</option></select>");

                    if(e.Option!=undefined&&e.Option!=null){
                        e.Option.forEach(function (s) {
                            var option=$("<option value="+s.Val+">"+s.DisplayName+"</option>");
                            $("#"+e.FieldName).append(option);
                        });}
                    $("#"+e.FieldName).val(e.Val);
                    if(i%2==0){
                        $(".formdata").append("<tr>");
                    }
                }
                i++

            });


        })
    }
    //点击设备映射



    $("#sltBatch").click(function(){
        if(treeID==null ||treeID==""){
            toastr.warning("请先点击左侧节点!");
            return;
        }
        else{
            layer.open({
                type: 2,
                title:'设备映射',
                shadeClose: true,
                area: ['50%', '80%'],
                content:getBasePath()+"/Device/DeviceChangePG/"+treeID
            });
        }
    });




    var colNamesArr = [
        {"Caption": "标准值名称", "Name": "KeyName"},
        {"Caption": "标准值", "Name": "KeyValue"}
    ];
    var config={
        tableId:"list4",
        data: [],
        colArr:colNamesArr,
        multiselect:true,
        width:0.84,
        height:0.35
    };
    fullTable(config);




    //补录组件条码确认
    $("#save").click(function() {
        var DevMapCode = $("#DevMapCode").val().trim();
        if(DevMapCode == undefined || DevMapCode == ""){
            toastr.warning("映射设备编号不能为空");
            return;
        }

        $("#myModal").modal("hide");
        var  DevRd=treeID;
        var BusData={
            "DevRd":DevRd,
            "DevMapCode":DevMapCode
        };

        request({url:"/Device/SaveDevInfo", data: {"ExecType":"04", "busData": JSON.stringify(BusData)}},function(Body){
            toastr.success("成功");
            loaddata();
            treeID = null;
        });

    });
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
        currentPage = treeSearchs("/Device/GetAllDevInfo","DevRd","DevName","DevName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Device/GetAllDevInfo","DevRd","DevName","DevName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })



    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Device/GetAllDevInfo","DevRd","DevName","DevName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Device/GetAllDevInfo","DevRd","DevName","DevName",condition,currentPage,config);
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

    //点检计划
    request({url:"/CheckPlan/GetAllCheckPlanInfo", data: {"ExecType": "00"}},function(Body){
        var data=Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].CheckPlanRd+ '>'+data[i].CheckPlanName +'</option>';
        }
        $("#djjh").html(aa);
        $("#djjh").selectpicker({
            width:200
        });
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
                // obj : 业务数据
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

    //工具家族
    var toolfamil = {
        "displaymode": "0",
        "title": "工具家族",
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
            ]
        },
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

    $("#toolfamil").zc_select(toolfamil);



   //加载设备族下拉框数据
    var params1 = {
        "displaymode": "0",
        "title": "选择设备家族",
        "binddata": {
            "keyfield": "DevFRd",
            "fields": [
                {
                    "caption": "设备家族id",
                    "name": "DevFRd"
                }, {
                    "caption": "设备家族名称",
                    "name": "DevFName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"FamilyName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"FamilyName",
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
                    url:"/DeviceF/GetAllDevFInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevFRd": datas[i].DevFRd,
                            "DevFName": datas[i].DevFName,
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

    //加载设备状态模型下拉框数据
    var params2 = {
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
    $("#defaultSelect2").zc_select(params2);



    /*-----------------加载树------------*/
    var loaddata = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
               {
                    "FieldName":"DevName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var treedataList = [];
        request({url:'/Device/GetAllDevInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){

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
                    id: treeData[i].DevRd == undefined ? "" : treeData[i].DevRd,
                    name: treeData[i].DevName == undefined ? "" : treeData[i].DevName
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
        $("#toolfamil").clearseldata("ToolFamilyRd");
        $("#byjh").selectpicker('val',"");
        $("#djjh").selectpicker('val',"");
        $("#scxb").selectpicker('val',"");
        $("#sbbm").val("");
        $("#_right").show();
        clearForm("devform");
        treeID = null;
        $("#defaultSelect").clearseldata("SupRd");
        $("#defaultSelect1").clearseldata("DevFRd");
        $("#defaultSelect2").clearseldata("DSMRd");
        $("#ExecType").val("00");

        //加载设备扩展字段
        $(".formdata").empty();
        var  datas={
            "ExpandType":"01"
        }
        request({
                url:'/Expand/GetDWExpandInfo',
                async:true,
                data:{"ExecType":"01","BusData":""}
            },function (Body) {
            var data = Body.Data;
            maps = [];
            var i = 1;
            if(data.length>0){
                $("#sbtx").attr("style","display:block;");
            }else {
                $("#sbtx").attr("style","display:none;");
            }

            data.forEach(function (e) {

                maps.push(e.FieldName);
                if (e.FiledType == "00") {
                    $(".formdata").append(e.DisplayName + "&emsp;" + "<input class=' formgroup' style='width: 17.4%;margin-top: 0px;;' id=" + e.FieldName + " name=" + e.DisplayName + ">&emsp;&emsp;&emsp;");
                    if (e.Val != null) {
                        $("#" + e.FieldName).val(e.Val);
                    } else {
                        $("#" + e.FieldName).val();
                    }


                    if (i % 2 == 0) {
                        $(".formdata").append("<tr>");
                    }
                }
                if (e.FiledType == "01") {

                    $(".formdata").append(e.DisplayName + "&emsp;" + " <select class=' formgroup maBottom' style='width: 17.4%;margin-top: 0px;' id=" + e.FieldName + " name=" + e.DisplayName + " ><option>" + '' + "</option></select>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;");

                    if (e.Option != undefined && e.Option != null) {
                        e.Option.forEach(function (s) {
                            var option = $("<option value=" + s.Val + ">" + s.DisplayName + "</option>");
                            $("#" + e.FieldName).append(option);
                        });
                    }
                    $("#" + e.FieldName).val(e.Val);
                    if (i % 2 == 0) {
                        $(".formdata").append("<tr>");
                    }
                }
                i++

            });
        });
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null) {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Device/SaveDevInfo", data: {"ExecType": "01", "busData": "{\"DevRd\":" + treeID + "}"}},function(Body){
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

            request({url:"/Device/SaveDevInfo", data: {"ExecType": "03", "busData": "{\"DevRd\":" + treeID + "}"}},function(Body){
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
        var DevPropertyInfo=[];
        for(var i=0;i<maps.length;i++){
            var Val=$("#"+maps[i]).val();
            Val=Val==undefined?"":Val.trim();
            DevPropertyInfo.push({"FieldName":maps[i],"Val":Val})
        }
        var LineInfoSources=$("#scxb").val();

       var LineInfo=[];
       for(var  obj in LineInfoSources){
           var LineInfos={
               "LineRd":LineInfoSources[obj]
           }
           LineInfo.push(LineInfos);
       }

        var sbbm=$("#sbbm").val();
        if(sbbm==null||""==sbbm){
            toastr.warning("保存失败,设备编码不能为空");
            return false;
        }

        // 获取所有表单数据封装成json对象
        formData = transfer("devform");
        //制造商下拉框选中数据
        var SupRd = $("#defaultSelect").getseldata().SupRd;
        //设备家族下拉框选中数据
        var DevFRd = $("#defaultSelect1").getseldata().DevFRd;
        //设备状态模型下拉框选中数据
        var DSMRd = $("#defaultSelect2").getseldata().DSMRd;

        //工具家族
        var ToolFRd=$("#toolfamil").getseldata().ToolFamilyRd;
        if(SupRd==null||SupRd==""){
            toastr.warning("供应商不能为空");
            return false;
        }
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
        var aa=$("#djjh").val();
        var CheckPlanInfo=[];
        if(aa!=null&&aa!=""){
            if(aa.length>0){
                for(var i in aa){
                    var CheckPlanInfos={
                        "CheckPlanRd":aa[i]
                    }
                    CheckPlanInfo.push(CheckPlanInfos);
                }
            }
        }




        var list4Data= getTableData("list4");
        var BusData={
            "Reference":$("#Reference").val(),
            "BZDtl":list4Data
        };



        var list4Data= getTableData("list4");
        if ($("#devName").val().trim() != "" && SupRd !="") {
            //新增设备信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "DevName": formData["devName"],
                    "DevCode":$("#sbbm").val(),
                    "Place": $("#Place").val(),
                    "SupRd": SupRd,
                    "DevFRd":DevFRd,
                    "DSMRd":DSMRd,
                    "DevPropertyInfo":DevPropertyInfo,
                    "Remark": formData["remark"],
                    "LineInfo":LineInfo,
                    "ToolFRd":ToolFRd,
                    "PMInfo":PMInfo,
                    "CheckPlanInfo":CheckPlanInfo,
                    "DevBZInfo":BusData,

                };
                request({url:"/Device/SaveDevInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
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
                    "DevRd": treeID,
                    "DevName": formData["devName"],
                    "Place": $("#Place").val(),
                    "DevCode":$("#sbbm").val(),
                    "SupRd": SupRd,
                    "DevFRd":DevFRd,
                    "DSMRd":DSMRd,
                    "DevPropertyInfo":DevPropertyInfo,
                    "Remark": formData["remark"],
                    "LineInfo":LineInfo,
                    "ToolFRd":ToolFRd,
                    "PMInfo":PMInfo,
                    "CheckPlanInfo":CheckPlanInfo,
                    "DevBZInfo":BusData,
                };
                request({url:"/Device/SaveDevInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
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
    //加载线体
    request({url:"/Line/GetAllLineInfo", data: {"ExecType": "00"}},function(Body){
        var data=Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].LineRd+'>'+data[i].LineName +'</option>';
        }
        $("#scxb").html(aa);
        $("#scxb").selectpicker({
            width:200
        });
    });
});