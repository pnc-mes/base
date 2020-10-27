$(function(){
   //处理页面加载时，直接点击保存事件
    var isEdit=false;
    //加载树点击事件
    var onClicks = function (id, text) {
        isEdit=true;
        $(".right").show();
        $(".cSave").attr('a',"00");
        reloadTable();
        $("#pwdhidden").hide();
        $(".right").show();

        $("#copy").attr("co",id.nodeID);
        $(".cDel").attr("a",id.nodeID);
        $("#repwd").attr("r",id.nodeID);
        var objBusData = {"UserRd": id.nodeID};
        request({url:"/User/GetUserInfo",async: false,//设为同步请求
            data: {
                "ExecType": "00",
                "BusData": JSON.stringify(objBusData)
            }},function(Body){
            if(Body.MsgCode == "0x00000"){
                if(Body.Data.ShiftInfo!=null&&Body.Data.ShiftInfo!=""){
                    $("#banbie").showData({
                        id:Body.Data.ShiftInfo.ShiftRd,
                        name:Body.Data.ShiftInfo.ShiftName,
                        keyfield:"ShiftRd",
                        fields:[
                            {"name":"ShiftRd"},
                            {"name":"ShiftName"}
                        ]
                    });
                }
                $("#printModelName").val(Body.Data.UserName);
                $("#name").val(Body.Data.RealName);
                $("#creatPeople").val(Body.Data.Creator);
                $("#creatTime").val(Body.Data.CreateTime);
                $("#modifyPeople").val(Body.Data.LastModifyMan);
                $("#modifyTime").val(Body.Data.LastModifyTime);
                $("#beizhu").val(Body.Data.Remark);
                $("#EmailAddress").val(Body.Data.EmailAddress);
                $("#MobileNo").val(Body.Data.MobileNo);
                //默认时删除
                $(".cDel").attr("a",Body.Data.UserRd);
                //默认时复制
                $("#copy").attr("co",Body.Data.UserRd);
                //默认时重置密码
                $("#repwd").attr("r",Body.Data.UserRd);

                $(".RoleRd").each(function(){
                    for(var i in Body.Data.RoleInfo){
                        delete Body.Data.RoleInfo[i].RoleName;
                        if(Body.Data.RoleInfo[i].RoleRd==$(this).attr("title")){
                            $(this).siblings(":first").children().attr("checked","checked");
                        }
                    }
                });

            }

        });

       /* $.ajax({
            url: getBasePath()+"/User/GetUserInfo",
            type: "POST",
            async: false,//设为同步请求
            data: {
                "ExecType": "00",
                "BusData": JSON.stringify(objBusData)
            },
            success: function (res) {
                if(res.Status =="00" && res.Body.MsgCode == "0x00000"){
                    $("#printModelName").val(res.Body.Data.UserName);
                    $("#name").val(res.Body.Data.RealName);
                    $("#creatPeople").val(res.Body.Data.Creator);
                    $("#creatTime").val(res.Body.Data.CreateTime);
                    $("#modifyPeople").val(res.Body.Data.LastModifyMan);
                    $("#modifyTime").val(res.Body.Data.LastModifyTime);
                    $("#beizhu").val(res.Body.Data.Remark);
                    $("#EmailAddress").val(res.Body.Data.EmailAddress);
                    $("#MobileNo").val(res.Body.Data.MobileNo);
                    //默认时删除
                    $(".cDel").attr("a",res.Body.Data.UserRd);
                    //默认时复制
                    $("#copy").attr("co",res.Body.Data.UserRd);
                    //默认时重置密码
                    $("#repwd").attr("r",res.Body.Data.UserRd);

                    $(".RoleRd").each(function(){
                        for(var i in res.Body.Data.RoleInfo){
                            delete res.Body.Data.RoleInfo[i].RoleName;
                            if(res.Body.Data.RoleInfo[i].RoleRd==$(this).attr("title")){
                                $(this).siblings(":first").children().attr("checked","checked");
                            }
                        }
                    });

                }
            }
        });*/
    };
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
        currentPage = treeSearchs("/User/GetAllUserInfo","UserRd","UserName","UserName",condition,currentPage,config);
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
            currentPage = treeSearchs("/User/GetAllUserInfo","UserRd","UserName","UserName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/User/GetAllUserInfo","UserRd","UserName","UserName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/User/GetAllUserInfo","UserRd","UserName","UserName",condition,currentPage,config);
    });

    var treeDate=[];
    //默认表头显示
    var colNamesArr = [
        {"Caption":"id", "Name":"RoleRd", "Hidden":true},
        { "Caption":"角色名字", "Name":"RoleName","Editable":false}
    ];
    var config1 = {
        tableId: "list4",
        data: [],
        colArr:colNamesArr,
        multiselect:true,
        width:0.84,
        height:0.55
    };
    fullTable(config1);
    var reloadTable=function () {
        request({url: "/Role/GetAllRoleInfo", data: {"ExecType": "00"}}, function (data) {
            var config1 = {
                tableId: "list4",
                data: data.Data,
                colArr:colNamesArr,
                multiselect:true,
                width:0.84,
                height:0.55
            };
            fullTable(config1);
        });
    };

    $("#pwdhidden").hide();

    //加载班别
    var params1 = {
        "displaymode": "0",
        "title": "选择班别",
        "binddata": {
            "keyfield": "ShiftRd",
            "fields": [
                {
                    "caption": "班别id",
                    "name": "ShiftRd"
                }, {
                    "caption": "班别名称",
                    "name": "ShiftName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ShiftName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"ShiftName",
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
                    url:"/Shift/GetAllShiftsInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "ShiftRd": datas[i].ShiftRd,
                            "ShiftName": datas[i].ShiftName,
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
    $("#banbie").zc_select(params1);


    var loadTree=function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        //加载树


        request({url:"/User/GetAllUserInfo", async:false,
            data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo)},},function(Body){
            if(Body.MsgCode == "0x00000"){

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
                        id: treeData[i].UserRd == undefined ? "" : treeData[i].UserRd,
                        name: treeData[i].UserName == undefined ? "" : treeData[i].UserName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }

        });
        /*$.ajax({
            url: getBasePath()+"/User/GetAllUserInfo",
            type:"POST",
            async:false,
            data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo)},
            success:function(res){

                if(res.Status =="00" && res.Body.MsgCode == "0x00000"){

                    var treeData = res.Body.Data;
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
                            id: treeData[i].UserRd == undefined ? "" : treeData[i].UserRd,
                            name: treeData[i].UserName == undefined ? "" : treeData[i].UserName
                        };
                        treedataList.push(tree);
                    }
                    config.data.source = treedataList;
                    $.JstreeEx.init(config);//先调用后加载
                }
            }
        })*/
    };

    loadTree();

    $(".add1").attr("style","pointer-events:block");
    $(".edit1").attr("style","pointer-events:block");
    $(".remove").attr("style","pointer-events:block");


    //新增
    $(".cAdd").click(function(){
        isEdit=true;
        $(".right").show();
        $(".cAdd").attr("b","00");
        $(".cSave").attr("a","");
        //为防止错误操作
        $("#copy").attr("co","");
        $(".cDel").attr("a","");
        $("#repwd").attr("r","");
        $("#pwdhidden").show();
        $("#EmailAddress").val("");
        $("#MobileNo").val("");
        $(".add1").attr("style","pointer-events:block");
        $(".edit1").attr("style","pointer-events:block");
        $(".remove").attr("style","pointer-events:block");
        readOnly("printForm",false);
        $("#banbie").clearseldata("ShiftRd");
        clearForm("printForm");
        reloadTable()
    });

    //删除
    $(".cDel").click(function(){
            var UserRd = $(this).attr("a");
            var objData = {
                "UserRd": UserRd
            };
            if (UserRd == null || UserRd == "") {
                toastr.warning("请选择左侧要删除的一项再进行删除!");
            }
            else {
                layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'] //按钮
                },function () {

                    request({url:"/User/SaveUserInfo", async: false,//设为同步请求
                        data: {
                            "ExecType": "01",
                            "BusData": JSON.stringify(objData)
                        }},function(Body){
                        layer.closeAll("dialog");
                        if (Body.MsgCode == "0x00000") {
                            currentPage=0;
                            condition = "";
                            toastr.success(Body.MsgDes);
                            loadTree(); //局部刷新
                            $(".cDel").attr("a","");
                            $(".right").hide();
                        }
                    });

                /*$.ajax({
                    url: getBasePath() + "/User/SaveUserInfo",
                    type: "POST",
                    async: false,//设为同步请求
                    data: {
                        "ExecType": "01",
                        "BusData": JSON.stringify(objData)
                    },
                    success: function (res) {
                        layer.closeAll("dialog");
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            currentPage=0;
                            condition = "";
                            toastr.success(res.Body.MsgDes);
                           loadTree(); //局部刷新
                            $(".cDel").attr("a","");
                            $(".right").hide();
                        }
                    }
                });*/
            })
        }
    });
    //重置密码
    $("#repwd").click(function(){
    var UserRd=$(this).attr("r");
    if(UserRd==null||UserRd==""){
        toastr.warning("请选择左侧要重置的一项再进行重置!");
    }
    else{
        layer.open({
            type: 2,
            title:'重置密码',
            shadeClose: true,
            area: ['25%', '35%'],
            content:getBasePath()+"/User/RePWD/"+UserRd
        });
        }
    });


    //复制
    $("#copy").click(function () {
        var UserRd=$(this).attr("co");
        var objData={
            "UserRd": UserRd
        };
        if(UserRd==null||UserRd==""){
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
        request({url:"/User/SaveUserInfo", async: false,//设为同步请求
            data: {
                "ExecType":"04",
                "BusData":JSON.stringify(objData)
            }},function(Body){
            if(Body.MsgCode == "0x00000"){
                currentPage=0;
                condition = "";
                toastr.success(Body.MsgDes);
                loadTree(); //局部刷新
                $("#copy").attr("co","")
            }

        });
       /* $.ajax({
            url: getBasePath()+"/User/SaveUserInfo",
            type: "POST",
            async: false,//设为同步请求
            data: {
                "ExecType":"04",
                "BusData":JSON.stringify(objData)
            },
            success: function (res) {
                if(res.Status =="00" && res.Body.MsgCode == "0x00000"){
                    currentPage=0;
                    condition = "";
                    toastr.success(res.Body.MsgDes);
                    loadTree(); //局部刷新
                    $("#copy").attr("co","")
                }
            }
        });*/
    });

    //保存
    $(".cSave").click(function() {
        if(!isEdit){
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }
        var printModelName = $("#printModelName").val();

        var pwd = $("#mima").val();

        var name = $("#name").val();

        if(printModelName==null||""===printModelName){
            toastr.warning("保存失败，用户名不能为空");
            return false;
        }
        if(name==null||""===name){
            toastr.warning("保存失败，真实名字不能为空");
            return false;
        }
        var ShiftRd=$("#banbie").getseldata().ShiftRd;
        var remark = $("#beizhu").val();

        var UserRd = $(".cDel").attr("a");
        var str=[];
        $(".cbox").each(function(){
            if($(this).is(":checked")==true){
               var  RoleRd=$(this).parent().siblings().attr("title");
                var role={"RoleRd":RoleRd};
                str.push(role);
            }
        });
        for(var i =0;i<str.length;i++){
            if(str[i].RoleRd==undefined){
                str.shift();
            }
        }

        var dada1 = {
            "UserRd": UserRd,
            "UserName": printModelName,
            "RealName": name,
            "EmailAddress":$("#EmailAddress").val().trim(),
            "MobileNo":$("#MobileNo").val().trim(),
            "Remark": remark,
            "RoleInfo": str,
            "ShiftRd":ShiftRd==null||ShiftRd==""?"":ShiftRd
        };

        if($(this).attr("a")=="00"){
            if(printModelName!=null&&printModelName!=""&&name!=null&&name!=""){
                if("admin"==$("#printModelName").val().trim()){
                    toastr.warning("更新失败，用户名admin为内置管理员账号，请更换成其他用户名");
                    return false;
                }

                request({url:"/User/SaveUserInfo", async: false,//设为同步请求
                    data: {
                        "ExecType": "02",
                        "BusData": JSON.stringify(dada1)
                    }},function(Body){
                    if (Body.MsgCode == "MG_MES1001112020003F") {
                        toastr.warning(Body.MsgDes);
                    }
                    if (Body.MsgCode == "MG_MES1001112020005F") {
                        toastr.warning(Body.MsgDes);
                    }

                    if (Body.MsgCode == "0x00000") {
                        condition = "";
                        currentPage=0;
                        $(".cExit").attr("c", "");
                        toastr.success(Body.MsgDes);
                        loadTree();
                        $("#copy").attr("co", "");
                        $(".cDel").attr("a","");
                    }

                });
                /*$.ajax({
                    url: getBasePath() + "/User/SaveUserInfo",
                    type: "POST",
                    async: false,//设为同步请求
                    data: {
                        "ExecType": "02",
                        "BusData": JSON.stringify(dada1)
                    },
                    success: function (res) {
                        if (res.Status == "00" && res.Body.MsgCode == "MG_MES1001112020003F") {
                            toastr.warning(res.Body.MsgDes);
                        }
                        if (res.Status == "00" && res.Body.MsgCode == "MG_MES1001112020005F") {
                            toastr.warning(res.Body.MsgDes);
                        }

                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            condition = "";
                            currentPage=0;
                            $(".cExit").attr("c", "");
                            toastr.success(res.Body.MsgDes);
                            loadTree();
                            $("#copy").attr("co", "");
                            $(".cDel").attr("a","");
                        }
                    }
                });*/
            }
        }



        $(".cAdd").attr("ca", "00");

        var dada = {
            "UserName": printModelName,
            "RealName": name,
            "EmailAddress":$("#EmailAddress").val().trim(),
            "MobileNo":$("#MobileNo").val().trim(),
            "Password": pwd,
            "Remark": remark,
            "RoleInfo": str,
            "ShiftRd":ShiftRd==null||ShiftRd==""?"":ShiftRd
        };

        if ($(".cAdd").attr("b") == "00") {
            if(pwd==null||""===pwd){
                toastr.warning("保存失败，密码不能为空");
                return false;
            }
            if(printModelName!=null&&printModelName!=""&&name!=null&&name!=""){
                if("admin"==$("#printModelName").val().trim()){
                    toastr.warning("新增失败，用户名admin为内置管理员账号，请更换成其他用户名");
                    return false;
                }



                request({url:"/User/SaveUserInfo", async: false,//设为同步请求
                    data: {
                        "ExecType": "00",
                        "BusData": JSON.stringify(dada)
                    }},function(Body){
                    if (Body.MsgCode == "MG_MES1001112020003F") {
                        toastr.warning(Body.MsgDes);
                    }
                    if (Body.MsgCode == "MG_MES1001112020005F") {
                        toastr.warning(Body.MsgDes);
                    }
                    if (Body.MsgCode == "0x00000") {
                        condition = "";
                        currentPage=0;
                        toastr.success(Body.MsgDes);
                        $("#pwdhidden").hide();
                        $(".cAdd").attr("b", "");
                        loadTree();
                    }

                });

                /*$.ajax({
                    url: getBasePath() + "/User/SaveUserInfo",
                    type: "POST",
                    async: false,//设为同步请求
                    data: {
                        "ExecType": "00",
                        "BusData": JSON.stringify(dada)
                    },
                    success: function (res) {
                        if (res.Status == "00" && res.Body.MsgCode == "MG_MES1001112020003F") {
                            toastr.warning(res.Body.MsgDes);
                        }
                        if (res.Status == "00" && res.Body.MsgCode == "MG_MES1001112020005F") {
                            toastr.warning(res.Body.MsgDes);
                        }
                        if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                            condition = "";
                            currentPage=0;
                            toastr.success(res.Body.MsgDes);
                            $("#pwdhidden").hide();
                            $(".cAdd").attr("b", "");
                            loadTree();
                        }
                    }
                });*/
            }
        }
    });

})