$(function(){
    var treeID=null;
    var colNamesArr = [
        {"Caption": "UserRd", "Name": "UserRd", "Hidden": true},
        {"Caption": "真实名字", "Name": "RealName"},
        {"Caption": "邮箱", "Name": "EmailAddress"},
        {"Caption": "手机号码", "Name": "MobileNo"}
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


  var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"TeamRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/Team/GetTeamInfo", data: objData},function(Body){
            var aa =Body.Data.LineInfo;
            var a=[];
            for(var i in aa){
                a.push(aa[i].LineRd)
            }

            $("#scxb").selectpicker('val', a);

            //去除边框颜色
            $("#teamName").removeAttr("style");
            $("#teamName").val(Body.Data.TeamName);
            $("#deception").val(Body.Data.Description);
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            $("#Buyers").clearseldata("UserRd");
            var config1 = {
                tableId: 'list4',
                data: Body.Data.TeamMember,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);


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
        currentPage = treeSearchs("/Team/GetAllTeamsInfo","TeamRd","TeamName","teamName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Team/GetAllTeamsInfo", "TeamRd", "TeamName", "teamName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Team/GetAllTeamsInfo","TeamRd","TeamName","teamName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Team/GetAllTeamsInfo","TeamRd","TeamName","teamName",condition,currentPage,config);
    });

    $("#_right").hide();


    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        request({
            url:'/Team/GetAllTeamsInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "teamName",
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
                        id: treeData[i].TeamRd == undefined ? "" : treeData[i].TeamRd,
                        name: treeData[i].TeamName == undefined ? "" : treeData[i].TeamName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }

        });
    };

    loaddata();

    var userid="";
    var username="";
    var realname="";
    var EmailAddress="";
    var MobileNo="";
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
                var value=o.condition;//.replaceAll("/","&apos");
               var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"UserName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(value)/*value.replace(/[\'\"\\\/\b\f\n\r\t]/g, '')*/ + "%"
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
                userid=data.UserRd;
                username=data.UserName;

                var objBusData = JSON.stringify({"UserRd": userid});
                var objData = {
                    "ExecType": "00",
                    "BusData": objBusData,
                };
                request({url:"/User/GetUserInfo", data: objData},function(Body){
                    realname=Body.Data.RealName;
                    EmailAddress=Body.Data.EmailAddress;
                    MobileNo=Body.Data.MobileNo;
                });


            },
        }
    };
    $("#Buyers").zc_select(user);
    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $("#scxb").selectpicker('val',"");
        $("#_right").show();
        $("#teamName").val("");
        $("#deception").val("");
        $("#Buyers").clearseldata("UserRd");
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

        $("#ExecType").val("00");
        treeID = null;
    });


    //添加用户
    $(".adduser").click(function(){
        var tab_TR={
            "UserRd":userid,
            "MobileNo":MobileNo==null?"":MobileNo,
            "RealName":realname,
            "EmailAddress":EmailAddress==null?"":EmailAddress
        }
        if(tab_TR.RealName==undefined||tab_TR.RealName==""||tab_TR.RealName==null){
            toastr.warning("新增失败，用户不能为空");
            return false;
        }

        var PKMaInfo = getTableData("list4");
        for(var i  in  PKMaInfo){
            if(PKMaInfo[i].RealName==realname){
                toastr.warning("新增失败，该用户:"+realname+"已存在");
                return false;
            }
        }
        addSrow("list4", tab_TR);
    });
    $('.deluser').on('click',function(){
        derow('list4');
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != ""){
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Team/SaveTeamInfo", data: {"ExecType": "01", "busData": "{\"TeamRd\":" + treeID + "}"}},function(Body){
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

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            request({url:"/Team/SaveTeamInfo", data: {"ExecType": "03", "busData": "{\"TeamRd\":" + treeID + "}"}},function(Body){
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

        var LineInfoSources=$("#scxb").val();

        var LineInfo=[];
        for(var  obj in LineInfoSources){
            var LineInfos={
                "LineRd":LineInfoSources[obj]
            }
            LineInfo.push(LineInfos);
        }


        var list = getTableData("list4");
        if(list!=null){
            for(var i in list){
                delete  list[i].RealName;
                delete  list[i].MobileNo;
                delete  list[i].EmailAddress;
            }
        }
        if(treeID==null){
            var data={
                "TeamName":$("#teamName").val().trim(),
                "Description":$("#deception").val().trim(),
                "TeamMember":list,
                "Remark":$("#beizhu").val().trim(),
                "LineInfo":LineInfo
            }

            request({url:"/Team/SaveTeamInfo", data: {"ExecType": "00", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }else {
            var data={
                "TeamRd":treeID,
                "TeamName":$("#teamName").val().trim(),
                "Description":$("#deception").val().trim(),
                "TeamMember":list,
                "Remark":$("#beizhu").val().trim(),
                "LineInfo":LineInfo
            }

            request({url:"/Team/SaveTeamInfo", data: {"ExecType": "02", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
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
        $('#scxb').selectpicker('refresh');
    });

    //点击的时候再执行
    $('#scxb').on('shown.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        request({url:"/Line/GetAllLineInfo", data: {"ExecType": "00"}},function(Body){
            var data=Body.Data;
            var aa="";
            for(var i in data){
                aa+='<option value='+data[i].LineRd+'>'+data[i].LineName +'</option>';
            }
            $("#scxb").html(aa);
            $('#scxb').selectpicker('refresh');
        });
    });
});