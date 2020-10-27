$(function(){
    var treeID=null;
    var colNamesArr = [
        {"Caption": "UserRd", "Name": "UserRd", "Hidden": true},
        {"Caption": "员工名称", "Name": "UserName"},
        {"Caption": "邮件地址", "Name": "EmailAddress"},
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height:0.36
    };
    fullTable(config1);//加载空表格

    var colNamesArr1 = [
        {"Caption": "RoleRd", "Name": "RoleRd", "Hidden": true},
        {"Caption": "角色名称", "Name": "RoleName"},
    ];
    var config2 = {
        tableId: 'list5',
        data: [],
        colArr: colNamesArr1,
        multiselect: true,
        width: 0.84,
        height:0.36
    };
    fullTable(config2);//加载空表格
    var colNamesArr2 = [
        {"Caption": "名称", "Name": "Recipient","Editable": true, "CType": "text"},
        {"Caption": "邮件地址", "Name": "EmailAddress","Editable": true, "CType": "text"},
    ];
    var config3 = {
        tableId: 'list6',
        data: [],
        colArr: colNamesArr2,
        multiselect: true,
        width: 0.84,
        height:0.36
    };
    fullTable(config3);//加载空表格
    $("#_right").hide();

    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"EmailDisRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/EmailDis/GetEmailDisInfo", data: objData},function(Body){

            //去除边框颜色
            $("#EmailDisName").removeAttr("style");
            $("#EmailDisName").val(Body.Data.EmailDisName);
            $("#deception").val(Body.Data.Description);
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            $("#Buyers").clearseldata("UserRd");
            $("#Roles").clearseldata("RoleRd");
            var config1 = {
                tableId: 'list4',
                data: Body.Data.UserList,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);
            var config2 = {
                tableId: 'list5',
                data: Body.Data.RoleList,
                colArr: colNamesArr1,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config2);//加载空表格
            var config3 = {
                tableId: 'list6',
                data: Body.Data.AddressList,
                colArr: colNamesArr2,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config3);//加载空表格
        });
    };
    /!*----------------------定义控件规则-------------------*!/
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
        currentPage = treeSearchs("/EmailDis/GetAllEmailDisInfo","EmailDisRd","EmailDisName","distributionName",condition,currentPage,config);
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
        currentPage = treeSearchs("/EmailDis/GetAllEmailDisInfo","EmailDisRd","EmailDisName","distributionName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/EmailDis/GetAllEmailDisInfo","EmailDisRd","EmailDisName","distributionName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/EmailDis/GetAllEmailDisInfo","EmailDisRd","EmailDisName","distributionName",condition,currentPage,config);
    });


    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        request({
            url:'/EmailDis/GetAllEmailDisInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "distributionName",
                            "FieldOpt": "Order BY"
                        }]})
            }},function(Body){
            var treeData = Body.Data;
            if(treeData.length>0){
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].EmailDisRd == undefined ? "" : treeData[i].EmailDisRd,
                        name: treeData[i].EmailDisName == undefined ? "" : treeData[i].EmailDisName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }

        });
    };

    loaddata();
    var UserRd="";
    var UserName="";
    var EmailAddress="";
    var user = {
        "displaymode": "0",
        "title": "lywl",
        "binddata": {
            "keyfield": "UserRd",
            "fields": [
                {
                    "caption": "userid",
                    "name": "UserRd"
                }, {
                    "caption": "user名称",
                    "name": "UserName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"UserName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "UserName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/User/GetAllUserInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UserRd": datas[i].UserRd,
                            "UserName": datas[i].UserName,
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
            "onclick": function (data) {
                UserRd=data.UserRd;
                var objBusData = JSON.stringify({"UserRd": UserRd});
                var objData = {
                    "ExecType": "00",
                    "BusData": objBusData,
                };
                UserName=data.UserName;
                request({url:"/User/GetUserInfo", data: objData},function(Body){
                    EmailAddress=Body.Data.EmailAddress==null?"":Body.Data.EmailAddress;
                })

            },
        }
    };
    $("#Buyers").zc_select(user);


    var RoleRd="";
    var RoleName="";
    var role = {
        "displaymode": "0",
        "title": "lywl",
        "binddata": {
            "keyfield": "RoleRd",
            "fields": [
                {
                    "caption": "RoleRd",
                    "name": "RoleRd"
                }, {
                    "caption": "user名称",
                    "name": "RoleName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RoleName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "RoleName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Role/GetAllRoleInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "RoleRd": datas[i].RoleRd,
                            "RoleName": datas[i].RoleName,
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
            "onclick": function (data) {
                RoleRd=data.RoleRd;
                RoleName=data.RoleName;

            },
        }
    };

    $("#Roles").zc_select(role);


    //新增
    $(".cAdd").click(function () {
        $("#_right").show();
        $("#EmailDisName").val("");
        $("#deception").val("");
        $("#Buyers").clearseldata("UserRd");
        $("#Roles").clearseldata("RoleRd");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");

        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height:0.36
        };
        fullTable(config1);
        var config2 = {
            tableId: 'list5',
            data: [],
            colArr: colNamesArr1,
            multiselect: true,
            width: 0.84,
            height:0.36
        };
        fullTable(config2);//加载空表格

        var config3 = {
            tableId: 'list6',
            data: [],
            colArr: colNamesArr2,
            multiselect: true,
            width: 0.84,
            height:0.36
        };
        fullTable(config3);//加载空表格

        $("#ExecType").val("00");
        treeID = null;
    });
    //添加用户
    $(".adduser").click(function(){
        if($("#Buyers").getseldata().UserRd==null||$("#Buyers").getseldata().UserRd==""){
            toastr.warning("新增失败，员工清单不能为空");
            return false;
        }
        var tab_TR={
            "UserRd":UserRd,
            "UserName":UserName,
            "EmailAddress":EmailAddress
        }
        if(tab_TR.UserRd==undefined||tab_TR.UserRd==""||tab_TR.UserRd==null){
            toastr.warning("新增失败，员工清单不能为空");
            return false;
        }

        var PKMaInfo = getTableData("list4");
        for(var i  in  PKMaInfo){
            if(PKMaInfo[i].UserRd==UserRd){
                toastr.warning("新增失败，该用户:"+PKMaInfo[i].UserName+"已存在");
                return false;
            }
        }
        addSrow("list4", tab_TR);
        tab_TR=[];
    });
    $('.deluser').on('click',function(){
        derow('list4');
    });

    //添加角色
    $(".addrole").click(function(){
        if($("#Roles").getseldata().RoleRd==null||$("#Roles").getseldata().RoleRd==""){
            toastr.warning("新增失败，角色清单不能为空");
            return false;
        }
        var tab_TR={
            "RoleRd":RoleRd,
            "RoleName":RoleName,
        }
        if(tab_TR.RoleRd==undefined||tab_TR.RoleRd==""||tab_TR.RoleRd==null){
            toastr.warning("新增失败，角色清单不能为空");
            return false;
        }

        var PKMaInfo = getTableData("list5");
        for(var i  in  PKMaInfo){
            if(PKMaInfo[i].RoleRd==null||""==PKMaInfo[i].RoleRd||PKMaInfo[i].RoleRd==undefined){
                toastr.warning("新增失败，角色清单不能为空");
                return false;
            }
            if(PKMaInfo[i].RoleRd==RoleRd){
                toastr.warning("新增失败，该用户:"+PKMaInfo[i].RoleName+"已存在");
                return false;
            }
        }
        addSrow("list5", tab_TR);
        tab_TR=[];
    });
    $('.delrole').on('click',function(){
        derow('list5');
    });
    var tableId = "list6";
    //其他添加
    $(".otheradds").click(function(){
        addErow(tableId);
    });
    $('.otherdel').on('click',function(){
        derow('list6');
    });

    //保存
    var newData = {};
    $(".cSave").click(function () {
        var UserList = getTableData("list4");
        var RoleList = getTableData("list5");
        var AddressList=getTableData("list6");
        var j=1;
        for(var i in AddressList){
            if(AddressList[i].Recipient==null||AddressList[i].Recipient==""){
                toastr.warning("保存失败，第"+j+"行的外部人员名称不能为空");
                return false;
            }
            if(AddressList[i].EmailAddress==null||AddressList[i].EmailAddress==""){
                toastr.warning("保存失败，第"+j+"行的外部人员邮件地址不能为空");
                return false;
            }
            var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
            var isok= reg.test(AddressList[i].EmailAddress);
            if(!isok){
                toastr.warning("保存失败，第"+j+"行的邮件地址格式不正确");
                return false;
            }
            j++;
        }

        if(treeID==null){
            var data={
                "EmailDisName":$("#EmailDisName").val().trim(),
                "Description":$("#deception").val().trim(),
                "UserList":UserList,
                "RoleList":RoleList,
                "AddressList":AddressList,
                "Remark":$("#beizhu").val().trim(),
            }

            request({url:"/EmailDis/SaveEmailDisInfo", data: {"ExecType": "00", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }else {
            var data={
                "EmailDisRd":treeID,
                "EmailDisName":$("#EmailDisName").val().trim(),
                "Description":$("#deception").val().trim(),
                "UserList":UserList,
                "RoleList":RoleList,
                "AddressList":AddressList,
                "Remark":$("#beizhu").val().trim(),
            }
            request({url:"/EmailDis/SaveEmailDisInfo", data: {"ExecType": "02", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }
    });

    //删除
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != ""){
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/EmailDis/SaveEmailDisInfo", data: {"ExecType": "01", "busData": "{\"EmailDisRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        $("#ExecType").val("");
                        treeID = null;
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });



})