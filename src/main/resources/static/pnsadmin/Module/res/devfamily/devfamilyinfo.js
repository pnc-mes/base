$(function () {
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "devFName", //自定义名字：标签id名字
        "param": "DevFName" //规则中自定义的名字：对应报文中的id字段
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
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"DevFRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/DeviceF/GetDevFInfo", data: objData},function(Body){
            //去除边框颜色
            $("#devFName").removeAttr("style");
            fillform("devfamilyinfo", rule, Body.Data);

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




        });
    }
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
        currentPage = treeSearchs("/DeviceF/GetAllDevFInfo","DevFRd","DevFName","FamilyName",condition,currentPage,config);
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
            currentPage = treeSearchs("/DeviceF/GetAllDevFInfo","DevFRd","DevFName","FamilyName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/DeviceF/GetAllDevFInfo","DevFRd","DevFName","FamilyName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/DeviceF/GetAllDevFInfo","DevFRd","DevFName","FamilyName",condition,currentPage,config);
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

    //只刷新树
    var loadtree = function () {
        var trees = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"FamilyName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        request({url:'/DeviceF/GetAllDevFInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){
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
                    id: treeData[i].DevFRd == undefined ? "" : treeData[i].DevFRd,
                    name: treeData[i].DevFName == undefined ? "" : treeData[i].DevFName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadtree();


    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $("#byjh").selectpicker('val',"");
        $("#djjh").selectpicker('val',"");
        $("#_right").show();
        clearForm("devfamilyinfo");
        $("#ExecType").val("00");
        treeID = null;
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/DeviceF/SaveDevFInfo", data: {"ExecType": "01", "busData": "{\"DevFRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        $("#ExecType").val("");
                        treeID = null;
                        condition = "";
                        currentPage = 0;
                        loadtree();
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
      if($("#devFName").val().trim()==null||$("#devFName").val().trim()=="" ){
          toastr.warning("保存失败，设备家族名称不能为空");
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


        if ($("#devFName").val().trim() != "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("devfamilyinfo");
            //新增计量单位信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "PMInfo":PMInfo,
                    "CheckPlanInfo":CheckPlanInfo,
                    "DevFName": formData["devFName"],
                    "Remark": formData["remark"]
                };
                request({url:"/DeviceF/SaveDevFInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loadtree();
                });
            }
            //编辑计量单位信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "PMInfo":PMInfo,
                    "CheckPlanInfo":CheckPlanInfo,
                    "DevFRd": treeID,
                    "DevFName": formData["devFName"],
                    "Remark": formData["remark"]
                };
                request({url:"/DeviceF/SaveDevFInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    condition = "";
                    currentPage = 0;
                    loadtree();
                    $("#ExecType").val("");
                    treeID = null;
                });
            }
        } else {
            if ($("#devFName").val().trim() == "") {
                $("#devFName").css("border-color", "red");
                $("#devFName").prop("placeholder", "不能为空！");
            }
        }
    });

});
