$(function () {
    $("#_right").hide();
    //获取用户/角色id
    var v = $('#userRd', window.parent.document).val();
    var b = $('#roleRd', window.parent.document).val();
    var BusDatadata={
        "UserRd":v,
        "RoleRd":b
    }
    var treeID = null;
    var rule = [{
        "ctlid": "storeCode",
        "param": "StoreCode"
    },{
        "ctlid": "storeName", //自定义名字：标签id名字
        "param": "StoreName" //规则中自定义的名字：对应报文中的id字段
    },{
        "ctlid": "F1",
        "param": "F1"
    },{
        "ctlid": "F2", //自定义名字：标签id名字
        "param": "F2" //规则中自定义的名字：对应报文中的id字段
    },{
        "ctlid": "F3", //自定义名字：标签id名字
        "param": "F3" //规则中自定义的名字：对应报文中的id字段
    },{
        "ctlid": "F4", //自定义名字：标签id名字
        "param": "F4" //规则中自定义的名字：对应报文中的id字段
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

    var colNamesArr = [
        {"Caption": "PMorRd", "Name": "PMorRd", "Hidden": true},
        {"Caption": "用户/角色信息", "Name": "PMor", 'Editable': false},
        {"Caption": "类型", "Name": "PMorType", 'Editable': false,"Hidden": true}
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height:0.36
    };
    fullTable(config1);

    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        $("#StoreType").val("");
        $("#storeCode").attr("readonly",true);
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"StoreRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/Store/GetStoreInfo", data: objData},function(Body){
            //去除边框颜色
            $("#storeName").removeAttr("style");
            fillform("storeInfo", rule, Body.Data);

            $("#StoreType").find("option").each(function () {
                if($(this).val() == Body.Data.StoreType){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });
            $("#hidden").attr("h",Body.Data.DSource);
            $("#Status").find("option").each(function () {
                if($(this).val() == Body.Data.Status){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });
            var DSource = $("#hidden").attr("h");

            if(DSource=="00"){
                $("#storeName").attr("readonly",true);
                $("#StoreType").prop("disabled",true);
                $("#Status").prop("disabled",true);
            }else{
                $("#storeName").attr("readonly",false);
                $("#StoreType").prop("disabled",false);
                $("#Status").prop("disabled",false);
            }
            var config1 = {
                tableId: 'list4',
                data: Body.Data.PMInfo,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);
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
        currentPage = treeSearchs1("/Store/GetAllStoreInfo","StoreRd","StoreName","StoreName",condition,currentPage,config,BusDatadata);
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
            currentPage = treeSearchs1("/Store/GetAllStoreInfo","StoreRd","StoreName","StoreName",condition,currentPage,config,BusDatadata);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })




    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs1("/Store/GetAllStoreInfo","StoreRd","StoreName","StoreName",condition,currentPage,config,BusDatadata);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs1("/Store/GetAllStoreInfo","StoreRd","StoreName","StoreName",condition,currentPage,config,BusDatadata);
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
                    "FieldName":"CreateTime DESC",
                    "FieldOpt":"Order BY",
                }
            ]
        };

        request({url:'/Store/GetAllStoreInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"BusData": JSON.stringify(BusDatadata)},"PageInfo":JSON.stringify(pageInfo)},function(Body){
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
                    id: treeData[i].StoreRd == undefined ? "" : treeData[i].StoreRd,
                    name: treeData[i].StoreName == undefined ? "" : treeData[i].StoreName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadtree();

    var insertData={};

    var lywl = {
        "displaymode": "1",
        "title": "用户/角色信息",
        "binddata": {
            "keyfield": "PMorRd",
            "fields": [
                {
                    "caption": "PMorRd",
                    "name": "PMorRd"
                }, {
                    "caption": "用户/角色信息",
                    "name": "PMor"
                },
                {
                    "caption": "权限人类型",
                    "name": "PMorType",
                    "hidden":true
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RealName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var mac=o.condition;
                var data={
                    "RoleName":mac
                }
                var xldata = [];
                request({url:"/User/GetAllURInfo",data: {"ExecType": "00","InitData":JSON.stringify(InitData),"BusData": JSON.stringify(data)}},function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PMorRd": datas[i].PMorRd,
                            "PMor": datas[i].PMor,
                            "PMorType": datas[i].PMorType
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
            "onclick":function (res) {
                insertData={
                    "PMorRd":res.PMorRd,
                    "PMor":res.PMor,
                    "PMorType":res.PMorType
                }
            }
        }};
    $("#lymater").zc_select(lywl);


    $(".del1").click(function(){
        derow("list4");
    });
    $(".add1").click(function(){
        var PMorRd=insertData.PMorRd;
        if(PMorRd==undefined){
            toastr.warning("请选择用户/角色信息，再点击新增");
            return;
        }

        var tb_data = getTableData("list4");

        for(var i in tb_data){
            if(tb_data[i].PMor == insertData.PMor){
                $("tbody tr").find(".PMor").each(function () {
                    if($(this).text() == insertData.PMor){
                        toastr.warning("新增用户/角色信息已存在");
                    }
                });
                return;
            }
        }

        addSrow("list4",insertData,"first");

    });

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("storeInfo");
        $("#StoreType").val("");
        $("#Status").val("00");
        $("#ExecType").val("00");
        treeID = null;
        $("#storeCode").attr("readonly",false);
        $("#storeName").attr("readonly",false);
        $("#StoreType").prop("disabled",false);
        $("#Status").prop("disabled",false);
        fullTable(config1);
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            var DSource = $("#hidden").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Store/SaveStoreInfo", data: {"ExecType": "01", "busData": "{\"StoreRd\":" + treeID + "}"}},function(Body){
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
        if ($("#storeName").val().trim() != "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("storeInfo");
            var PMInfo = getTableData("list4");
            var StoreType = $("#StoreType").val();

            var status = $("#Status").val();

            var devrd=true;
            for (var i = 0; i < PMInfo.length; i++) {
                delete PMInfo[i]["PMor"];
                if(PMInfo[i].PMorRd !="" && PMInfo[i].PMorRd !=undefined){
                    devrd=false;
                }
            }
            if(devrd){
                toastr.warning("请新增用户/角色信息，再保存");
                return;
            }
            //新增仓库信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00" ) {
                newData = {
                    "StoreCode":formData["storeCode"],
                    "StoreName": formData["storeName"],
                    "StoreType": StoreType,
                    "Status": status,
                    "F1": formData["F1"],
                    "F2": formData["F2"],
                    "F3": formData["F3"],
                    "F4": formData["F4"],
                    "PMInfo":PMInfo,
                    "Remark": formData["remark"]
                };
                request({url:"/Store/SaveStoreInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success("仓库信息新增成功,仓库代码{"+Body.Data.StoreCode+"}");

                    currentPage = 0;
                    condition = "";
                    loadtree();
                    $("#hidden1").attr("editid", Body.Data.StoreRd);
                    treeID=$("#hidden1").attr("editid");
                    $("#hidden2").attr("editcode", Body.Data.StoreCode);
                    $("#_right").show();
                    $("#StoreType").val("");
                    $("#storeCode").attr("readonly",true);

                    var objBusData = JSON.stringify({"StoreRd": treeID});
                    var objData = {
                        "ExecType": "00",
                        "BusData": objBusData,
                    };
                    request({url:"/Store/GetStoreInfo", data: objData},function(Body){
                        //去除边框颜色
                        $("#storeName").removeAttr("style");
                        fillform("storeInfo", rule, Body.Data);

                        $("#StoreType").find("option").each(function () {
                            if($(this).val() == Body.Data.StoreType){
                                $(this).prop("selected",true);
                                $(this).siblings().prop("selected",false);
                                return false;
                            }
                        });
                        $("#hidden").attr("h",Body.Data.DSource);
                        $("#Status").find("option").each(function () {
                            if($(this).val() == Body.Data.Status){
                                $(this).prop("selected",true);
                                $(this).siblings().prop("selected",false);
                                return false;
                            }
                        });
                        var DSource = $("#hidden").attr("h");

                        if(DSource=="00"){
                            $("#storeName").attr("readonly",true);
                            $("#StoreType").prop("disabled",true);
                            $("#Status").prop("disabled",true);
                        }else{
                            $("#storeName").attr("readonly",false);
                            $("#StoreType").prop("disabled",false);
                            $("#Status").prop("disabled",false);
                        }
                        var config1 = {
                            tableId: 'list4',
                            data: Body.Data.PMInfo,
                            colArr: colNamesArr,
                            multiselect: true,
                            width: 0.84,
                            height:0.36
                        };
                        fullTable(config1);
                    });
                    $("#ExecType").val("");
                });
            }
            //编辑仓库信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "StoreRd": treeID,
                    "StoreName": formData["storeName"],
                    "StoreType": StoreType,
                    "Status": status,
                    "F1": formData["F1"],
                    "F2": formData["F2"],
                    "F3": formData["F3"],
                    "F4": formData["F4"],
                    "PMInfo":PMInfo,
                    "Remark": formData["remark"]
                };
                request({url:"/Store/SaveStoreInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    currentPage = 0;
                    condition = "";
                    var objBusData = JSON.stringify({"StoreRd": treeID});
                    var objData = {
                        "ExecType": "00",
                        "BusData": objBusData,
                    };
                    request({url:"/Store/GetStoreInfo", data: objData},function(Body){
                        //去除边框颜色
                        $("#storeName").removeAttr("style");
                        fillform("storeInfo", rule, Body.Data);

                        $("#StoreType").find("option").each(function () {
                            if($(this).val() == Body.Data.StoreType){
                                $(this).prop("selected",true);
                                $(this).siblings().prop("selected",false);
                                return false;
                            }
                        });
                        $("#hidden").attr("h",Body.Data.DSource);
                        $("#Status").find("option").each(function () {
                            if($(this).val() == Body.Data.Status){
                                $(this).prop("selected",true);
                                $(this).siblings().prop("selected",false);
                                return false;
                            }
                        });
                        var DSource = $("#hidden").attr("h");

                        if(DSource=="00"){
                            $("#storeName").attr("readonly",true);
                            $("#StoreType").prop("disabled",true);
                            $("#Status").prop("disabled",true);
                        }else{
                            $("#storeName").attr("readonly",false);
                            $("#StoreType").prop("disabled",false);
                            $("#Status").prop("disabled",false);
                        }
                        var config1 = {
                            tableId: 'list4',
                            data: Body.Data.PMInfo,
                            colArr: colNamesArr,
                            multiselect: true,
                            width: 0.84,
                            height:0.36
                        };
                        fullTable(config1);
                    });
                    loadtree();
                    $("#ExecType").val("");
                });
            }
        } else {
            if ($("#storeName").val().trim() == "") {
                $("#storeName").css("border-color", "red");
                $("#storeName").prop("placeholder", "不能为空！");
            }
        }
    });
});
