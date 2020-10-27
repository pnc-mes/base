$(function(){
    var tableflag=false;
    var treeID=null;
    var a1=null;
    function  aa() {
        $("#goaheademail input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                a1=null;
            }
        });
    }

    var b1=null;
    function  bb() {
        $("#goaheademailcontent input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                b1=null;
            }
        });
    }

    var c1=null;
    function  cc() {
        $("#daoqiGroup input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                c1=null;
            }
        });
    }

    var d1=null;
    function  dd() {
        $("#daoqiContent input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                d1=null;
            }
        });
    }

    var e1=null;
    function  ee() {
        $("#expiremailgroup input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                e1=null;
            }
        });
    }

    var f1=null;
    function  ff() {
        $("#expiremailcontent input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                f1=null;
            }
        });
    }

    var edit=false;

    var PresetEmailDistributionRd="";
    var PresetEmailMessageRd="";

    var StartEmailDistributionRd="";
    var StartEmailMessageRd="";

    var OverEmailDistributionRd="";
    var OverEmailMessageRd="";
    var colNamesArr = [
        {"Caption": "FrePlanDtlRd", "Name": "FrePlanDtlRd", "Hidden": true},
        {"Caption": "TaskRd", "Name": "TaskRd", "Hidden": true},
        {"Caption": "任务名称", "Name": "TaskName","Hidden": true},
        {"Caption": "任务项", "Name": "TaskItemName", "CType": "text"},
        {"Caption": "确认方式", "Name": "SureType", "CType": "text"},
    /*    {"Caption": "最小执行次数", "Name": "MinExCount", "CType": "text"},*/
        {"Caption": "最大执行次数", "Name": "MaxExCount", "Editable": true,"CType": "text"},
    ];

    var ItemTypes1 = [
        {TypeText: "打勾", TypeVal: "00"},
        {TypeText: "记录结果", TypeVal: "01"}
    ];

    var select1 = function () {
        $(".SureType").each(function () {
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < ItemTypes1.length; i++) {
                if (current_Td_Val == ItemTypes1[i].TypeText)
                    str += "<option selected value='" + ItemTypes1[i].TypeVal + "'>" + ItemTypes1[i].TypeText + "</option>";
                else
                    str += "<option value='" + ItemTypes1[i].TypeVal + "'>" + ItemTypes1[i].TypeText + "</option>";
            }
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
        });
    };
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height:0.36
    };
    fullTable(config1);//加载空表格

    $("#_right").hide();

    var onClicks = function (nodeinfo, handle) {
        aa();
        bb();
        cc();
        dd();
        ee();
        ff();
        edit=true;
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"FrePlanRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/FrePlan/GetFrePlanInfo", data: objData},function(Body){
                $("#bynrqd").selectpicker('val',"");
                $("#FrePlanName").val(Body.Data.FrePlanName);
                $("#status").val(Body.Data.Status);
                $("#Description").val(Body.Data.Description);
               $("#Reference").val(Body.Data.Reference);
            $("#byyy").showData({
                id:Body.Data.ReaInfo.ReaRd,
                name:Body.Data.ReaInfo.ReaDes,
                keyfield:"ReaRd",
                fields:[
                    {"name":"ReaRd"},
                    {"name":"ReaDes"}
                ]
            });


            $("#bybzzys").showData({
                id:Body.Data.FileInfo.FileVerRd,
                name:Body.Data.FileInfo.FileName,
                keyfield:"FileVerRd",
                fields:[
                    {"name":"FileVerRd"},
                    {"name":"FileName"}
                ]
            });

            $("#UseNum").val(Body.Data.UseNum);
            $("#unit").val(Body.Data.UnitType);
            $("#rxxxgc").val(Body.Data.DownUseNum);
          /*  $("#rxsxgc").val(Body.Data.UpUseNum);*/
            $("#xtsj").val(Body.Data.PresetUseNum);


            if(Body.Data.PresetEmailDistributionInfo!=null){
                PresetEmailDistributionRd=Body.Data.PresetEmailDistributionInfo.EmailDistributionRd;
                a1=Body.Data.PresetEmailDistributionInfo.EmailDistributionRd;
                $("#goaheademail").showData({
                    id:Body.Data.PresetEmailDistributionInfo.EmailDistributionRd,
                    name:Body.Data.PresetEmailDistributionInfo.EmailDistributionName,
                    keyfield:"EmailDistributionRd",
                    fields:[
                        {"name":"EmailDistributionRd"},
                        {"name":"EmailDistributionName"}
                    ]
                });
            }else {
                a1=null;
                $("#goaheademail").showData({
                    id:"",
                    name:"",
                    keyfield:"EmailDistributionRd",
                    fields:[
                        {"name":"EmailDistributionRd"},
                        {"name":"EmailDistributionName"}
                    ]
                });
            }
            if(Body.Data.PresetEmailMessageInfo!=null){
                PresetEmailMessageRd=Body.Data.PresetEmailMessageInfo.EmailMessageRd;
                b1=Body.Data.PresetEmailMessageInfo.EmailMessageRd;
                $("#goaheademailcontent").showData({
                    id:Body.Data.PresetEmailMessageInfo.EmailMessageRd,
                    name:Body.Data.PresetEmailMessageInfo.EmailMessageName,
                    keyfield:"EmailMessageRd",
                    fields:[
                        {"name":"EmailMessageRd"},
                        {"name":"EmailMessageName"}
                    ]
                });
            }else {
                b1=null;
                $("#goaheademailcontent").showData({
                    id:"",
                    name:"",
                    keyfield:"EmailMessageRd",
                    fields:[
                        {"name":"EmailMessageRd"},
                        {"name":"EmailMessageName"}
                    ]
                });
            }
            if(Body.Data.StartEmailDistributionInfo!=null){
                StartEmailDistributionRd=Body.Data.StartEmailDistributionInfo.EmailDistributionRd;
                c1=Body.Data.StartEmailDistributionInfo.EmailDistributionRd;
                $("#daoqiGroup").showData({
                    id:Body.Data.StartEmailDistributionInfo.EmailDistributionRd,
                    name:Body.Data.StartEmailDistributionInfo.EmailDistributionName,
                    keyfield:"EmailDistributionRd",
                    fields:[
                        {"name":"EmailDistributionRd"},
                        {"name":"EmailDistributionName"}
                    ]
                });
            }else {
                c1=null;
                $("#daoqiGroup").showData({
                    id:"",
                    name:"",
                    keyfield:"EmailDistributionRd",
                    fields:[
                        {"name":"EmailDistributionRd"},
                        {"name":"EmailDistributionName"}
                    ]
                });
            }
            if(Body.Data.StartEmailMessageInfo!=null){
                StartEmailMessageRd=Body.Data.StartEmailMessageInfo.EmailMessageRd;
                d1=Body.Data.StartEmailMessageInfo.EmailMessageRd;
                $("#daoqiContent").showData({
                    id:Body.Data.StartEmailMessageInfo.EmailMessageRd,
                    name:Body.Data.StartEmailMessageInfo.EmailMessageName,
                    keyfield:"EmailMessageRd",
                    fields:[
                        {"name":"EmailMessageRd"},
                        {"name":"EmailMessageName"}
                    ]
                });
            }else {
                d1=null;
                $("#daoqiContent").showData({
                    id:"",
                    name:"",
                    keyfield:"EmailMessageRd",
                    fields:[
                        {"name":"EmailMessageRd"},
                        {"name":"EmailMessageName"}
                    ]
                });
            }
            if(Body.Data.OverdueEmailDistributionInfo!=null){
                OverEmailDistributionRd=Body.Data.OverdueEmailDistributionInfo.EmailDistributionRd;
                e1=Body.Data.OverdueEmailDistributionInfo.EmailDistributionRd;
                $("#expiremailgroup").showData({
                    id:Body.Data.OverdueEmailDistributionInfo.EmailDistributionRd,
                    name:Body.Data.OverdueEmailDistributionInfo.EmailDistributionName,
                    keyfield:"EmailDistributionRd",
                    fields:[
                        {"name":"EmailDistributionRd"},
                        {"name":"EmailDistributionName"}
                    ]
                });
            }else {
                e1=null;
                $("#expiremailgroup").showData({
                    id:"",
                    name:"",
                    keyfield:"EmailDistributionRd",
                    fields:[
                        {"name":"EmailDistributionRd"},
                        {"name":"EmailDistributionName"}
                    ]
                });
            }
            if(Body.Data.OverdueEmailMessageInfo!=null){
                OverEmailMessageRd=Body.Data.OverdueEmailMessageInfo.EmailMessageRd;
                f1=Body.Data.OverdueEmailMessageInfo.EmailMessageRd;
                $("#expiremailcontent").showData({
                    id:Body.Data.OverdueEmailMessageInfo.EmailMessageRd,
                    name:Body.Data.OverdueEmailMessageInfo.EmailMessageName,
                    keyfield:"EmailMessageRd",
                    fields:[
                        {"name":"EmailMessageRd"},
                        {"name":"EmailMessageName"}
                    ]
                });
            }else {
                f1=null;
                $("#expiremailcontent").showData({
                    id:"",
                    name:"",
                    keyfield:"EmailMessageRd",
                    fields:[
                        {"name":"EmailMessageRd"},
                        {"name":"EmailMessageName"}
                    ]
                });
            }
        //$("#gqcqxd").val(Body.Data.OverTimeAction);
            var sourcedata=Body.Data.FrePlanDtlInfo;
            tableflag=true;
            var dataS=[];
            for(var i in sourcedata){
                data={
                    "FrePlanDtlRd":sourcedata[i].FrePlanDtlRd,
                    "TaskRd":sourcedata[i].TaskRd,
                    "TaskName":sourcedata[i].TaskName,
                    "TaskItemName":sourcedata[i].TaskItemName,
                    "SureType":sourcedata[i].SureType=="00"?"打勾":"记录结果",
                  /*  "MinExCount":sourcedata[i].MinExCount,*/
                    "MaxExCount":sourcedata[i].MaxExCount
                }
                dataS.push(data);
            }
            var config1 = {
                tableId: 'list4',
                data: dataS,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);//加载空表格
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);


            select1();
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
        currentPage = treeSearchs("/FrePlan/GetAllFrePlanInfo","FrePlanRd","FrePlanName","FrePlanName",condition,currentPage,config);
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
            currentPage = treeSearchs("/FrePlan/GetAllFrePlanInfo","FrePlanRd","FrePlanName","FrePlanName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/FrePlan/GetAllFrePlanInfo","FrePlanRd","FrePlanName","FrePlanName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/FrePlan/GetAllFrePlanInfo","FrePlanRd","FrePlanName","FrePlanName",condition,currentPage,config);
    });


    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        request({
            url:'/FrePlan/GetAllFrePlanInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "FrePlanName",
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
                        id: treeData[i].FrePlanRd == undefined ? "" : treeData[i].FrePlanRd,
                        name: treeData[i].FrePlanName == undefined ? "" : treeData[i].FrePlanName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }

        });
    };
    loaddata();
    //加载保养原因
    var byyy = {
        "displaymode": "0",
        "title": "选择原因",
        "binddata": {
            "keyfield": "ReaRd",
            "fields": [
                {
                    "caption": "原因id",
                    "name": "ReaRd"
                }, {
                    "caption": "原因描述",
                    "name": "ReaDes"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ReaDes",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"ReaType",
                            "FieldOpt":"=",
                            "FieldVal":"09"
                        },

                        {
                            "FieldName":"ReaDes",
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
                    url:"/Reason/GetAllReaInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "ReaRd": datas[i].ReaRd,
                            "ReaDes": datas[i].ReaDes,
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
    $("#byyy").zc_select(byyy);

    //加载保养标准作业书
    var bybzzys={
        "displaymode": "0",
        "title": "选择文件",
        "binddata": {
            "keyfield": "FileVerRd",
            "fields": [
                {
                    "caption": "文件id",
                    "name": "FileVerRd"
                }, {
                    "caption": "文件名称",
                    "name": "FileName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"FileName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"FileName",
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
                    url:"/File/GetAllFileInfo"
                };
                request(obj,function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "FileVerRd": datas[i].FileVerRd,
                            "FileName": datas[i].FileName,
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
    $("#bybzzys").zc_select(bybzzys);

    //加载保养内容清单
    var globalData=[];
    var InitData = {
        "FiledList":[
            {
                "FieldName":"TaskType",
                "FieldOpt":"=",
                "TaskType":"00"
            }
        ]
    };
    var obj = {
        data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
        url:"/Task/GetAllTaskInfo"
    };
    request(obj,function(Body){
        globalData = Body.Data;

        var aa="";
        for(var i in globalData){
            if(globalData[i].Status=="00"){
            aa+='<option value='+globalData[i].TaskRd+'>'+globalData[i].TaskName +'</option>';
            }
        }
        $("#bynrqd").html(aa);

    });

    //提前邮件通知组
    var email = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailDisRd",
            "fields": [
                {
                    "caption": "EmailDisRd",
                    "name": "EmailDisRd"
                }, {
                    "caption": "邮件分发名称",
                    "name": "EmailDisName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DistributionName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DistributionName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/EmailDis/GetAllEmailDisInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailDisRd": datas[i].EmailDisRd,
                            "EmailDisName": datas[i].EmailDisName,
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
                PresetEmailDistributionRd=data.EmailDisRd;
                a1=data.EmailDisRd;


            },
        }
    };
    $("#goaheademail").zc_select(email);

    //提前邮件通知组内容
    var emailcontent = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailRd",
            "fields": [
                {
                    "caption": "EmailRd",
                    "name": "EmailRd"
                }, {
                    "caption": "内容名称",
                    "name": "EmailName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"EmailName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "EmailName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Email/GetAllEmailInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailRd": datas[i].EmailRd,
                            "EmailName": datas[i].EmailName,
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
                PresetEmailMessageRd=data.EmailRd;
                b1=data.EmailRd;
            },
        }
    };
    $("#goaheademailcontent").zc_select(emailcontent);

    //过期邮件组
    var expireemailgroup = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailDisRd",
            "fields": [
                {
                    "caption": "EmailDisRd",
                    "name": "EmailDisRd"
                }, {
                    "caption": "过期名称",
                    "name": "EmailDisName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DistributionName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DistributionName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/EmailDis/GetAllEmailDisInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailDisRd": datas[i].EmailDisRd,
                            "EmailDisName": datas[i].EmailDisName,
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
                OverEmailDistributionRd=data.EmailDisRd;
                e1=data.EmailDisRd;
            },
        }
    };
    $("#expiremailgroup").zc_select(expireemailgroup);

    //过期邮件内容
    var expireemailgroupcontent = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailRd",
            "fields": [
                {
                    "caption": "EmailRd",
                    "name": "EmailRd"
                }, {
                    "caption": "内容名称",
                    "name": "EmailName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"EmailName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "EmailName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Email/GetAllEmailInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailRd": datas[i].EmailRd,
                            "EmailName": datas[i].EmailName,
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
                OverEmailMessageRd=data.EmailRd;
                f1=data.EmailRd;
            },
        }
    };
    $("#expiremailcontent").zc_select(expireemailgroupcontent);

    //到期邮件组
    var daoqiGroup = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailDisRd",
            "fields": [
                {
                    "caption": "EmailDisRd",
                    "name": "EmailDisRd"
                }, {
                    "caption": "过期名称",
                    "name": "EmailDisName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DistributionName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DistributionName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/EmailDis/GetAllEmailDisInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailDisRd": datas[i].EmailDisRd,
                            "EmailDisName": datas[i].EmailDisName,
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
                StartEmailDistributionRd=data.EmailDisRd;
                c1=data.EmailDisRd;
            },
        }
    };
    $("#daoqiGroup").zc_select(daoqiGroup);

    //到期邮件组内容
    var daoqiContent = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailRd",
            "fields": [
                {
                    "caption": "EmailRd",
                    "name": "EmailRd"
                }, {
                    "caption": "内容名称",
                    "name": "EmailName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"EmailName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "EmailName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Email/GetAllEmailInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailRd": datas[i].EmailRd,
                            "EmailName": datas[i].EmailName,
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
                StartEmailMessageRd=data.EmailRd;
                d1=data.EmailRd;
            },
        }
    };
    $("#daoqiContent").zc_select(daoqiContent);


    //
    $(".cAdd").click(function () {
        $("#startdata").val('');
        $("#enddata").val('');
        $("#TimeContent").val('');
        PresetEmailDistributionRd="";
        PresetEmailMessageRd="";

        StartEmailDistributionRd="";
        StartEmailMessageRd="";

        OverEmailDistributionRd="";
        OverEmailMessageRd="";

        a1=null;
        b1=null;
        c1=null;
        d1=null;
        e1=null;
        f1=null;


        edit=true;
        treeID = null;
        $("#_right").show();
        $("#FrePlanName").val("");
        $("#Description").val("");
        $("#byyy").clearseldata("ReaRd");
        $("#bybzzys").clearseldata("FileVerRd");
        $("#UseNum").val("0");
        $("#unit").val("00");
        $("#status").val("00");
        $("#rxxxgc").val("0");
        $("#rxsxgc").val("0");
        $("#xtsj").val("0");
        $("#bynrqd").selectpicker('val',"");
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height:0.36
        };
        fullTable(config1);
        //$("#gqcqxd").val("00");

        $("#goaheademail").clearseldata("EmailDisRd");
        $("#goaheademailcontent").clearseldata("EmailRd");
        $("#daoqiGroup").clearseldata("EmailDisRd");
        $("#daoqiContent").clearseldata("EmailRd");
        $("#expiremailgroup").clearseldata("EmailDisRd");
        $("#expiremailcontent").clearseldata("EmailRd");
        $("#beizhu").val("");



    });
    //添加用户
    $(".adduser").click(function(){
        var listdata = getRowData("list4");

        var data=$("#bynrqd").val();

        if(data==null||data.length<=0){
            toastr.warning("新增失败，请选择保养内容清单");
            return false;
        }
        var tab_TR=[];


        for(var i  in data){
            var id=parseInt(data[i]);
            var TaskName="";
            if(globalData.length>0){
                for(var j in globalData){
                    if(parseInt(globalData[j].TaskRd)==parseInt(data[i])){
                        TaskName=globalData[j].TaskName;
                    }
                }
            }
            var objBusData = JSON.stringify({"TaskRd": data[i]});
            var objData = {
                "ExecType": "00",
                "BusData": objBusData,
            };
            request({url:"/Task/GetTaskInfo", data: objData},function(Body){
                    var data=Body.Data.TaskDetailInfo;


                    for(var j  in data){


                        var dataS={
                            "FrePlanDtlRd":"",
                            "TaskRd":id,
                            "TaskName":TaskName,
                            "TaskItemName":data[j].TaskItemName,
                            "SureType":data[j].SureType=="00"?"打勾":"记录结果",
                     /*       "MinExCount":data[j].MinExCount,*/
                            "MaxExCount":data[j].MaxExCount
                        }
                        tab_TR.push(dataS);
                    }
            });
        }

        //addMrow("list4",tab_TR);
        if(listdata.length>0){
            var config1 = {
                tableId: 'list4',
                data: tab_TR.concat(listdata),
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);//加载空表格
        }else {
            var config1 = {
                tableId: 'list4',
                data: tab_TR,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);//加载空表格
        }


   select1();
    });
    //删除用户
    $('.deluser').on('click',function(){
        derow('list4');
    });

    //保存
    var newData = {};
    $(".cSave").click(function () {

        if(!edit){
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }


        var FrePlanName=$("#FrePlanName").val().trim();
        if(FrePlanName==null||FrePlanName==""){
            toastr.warning("名称不能为空");
            return false;
        }
        var Status=$("#status").val();
        var Description=$("#Description").val().trim();

        var ReaRd=$("#byyy").getseldata().ReaRd;
        if(ReaRd==null||ReaRd==""){
            toastr.warning("保养原因不能为空");
            return false;
        }

        var FileVerRd=$("#bybzzys").getseldata().FileVerRd;
        if(FileVerRd==null||FileVerRd==""){
            toastr.warning("保养标准作业书不能为空");
            return false;
        }
        var UseNum=$("#UseNum").val();
        if(UseNum==null||UseNum==""){
            toastr.warning("使用量不能为空");
            return false;
        }
        if(UseNum<=0){
            toastr.warning("使用量应大于0");
            return false;
        }


        var UnitType=$("#unit").val();

        var data = getTableData("list4");
        if(data.length<=0||data==null||data==""){
            toastr.warning("保养清单明细不能为空");
            return false;
        }

        var Remark=$("#beizhu").val();
        var UpUseNum="0";
        var DownUseNum=$("#rxxxgc").val().trim();
        //提醒
        var PresetUseNum=$("#xtsj").val().trim();

        if(PresetUseNum<0){
            toastr.warning("提醒量请勿小于0");
            return false;
        }
        if(Number(PresetUseNum)>Number(UseNum)){
            toastr.warning("提醒量请小于等于使用量");
            return false;
        }

        //var OverTimeAction=$("#gqcqxd").val();

        if(treeID==null){
            var FrePlanDtlInfos=[];
            for(var i in data){
                var FrePlanDtlInfo={
                    "FrePlanDtlRd":"",
                    "TaskRd":data[i].TaskRd,
                    "TaskName":data[i].TaskName,
                    "TaskItemName":data[i].TaskItemName,
                    "SureType":data[i].SureType,
       /*             "MinExCount":data[i].MinExCount,*/
                    "MaxExCount":data[i].MaxExCount,
                }
                FrePlanDtlInfos.push(FrePlanDtlInfo);
            }

            var data={
                "FrePlanName":FrePlanName,
                "Status":Status,
                "Description":Description,
                "ReaRd":ReaRd,
                "FileVerRd":FileVerRd,
                "UseNum":UseNum,
                "UnitType":UnitType,
                "Reference":$("#Reference").val(),
                "UpUseNum":UpUseNum,
                "DownUseNum":DownUseNum,
                "PresetUseNum":PresetUseNum,
                "PresetEmailDistributionRd":a1==null?"":  PresetEmailDistributionRd,
                "PresetEmailMessageRd":b1==null?"":PresetEmailMessageRd,
                "StartEmailDistributionRd":  c1==null?"": StartEmailDistributionRd,
                "StartEmailMessageRd":d1==null?"":StartEmailMessageRd,
               // "OverTimeAction":OverTimeAction,
                "OverEmailDistributionRd":e1==null?"":OverEmailDistributionRd,
                "OverEmailMessageRd":f1==null?"":OverEmailMessageRd,
                "FrePlanDtlInfo":FrePlanDtlInfos,
                "Remark":Remark,
            }

            request({url:"/FrePlan/SaveFrePlanInfo", data: {"ExecType": "00", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#bynrqd").selectpicker('val',"");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();

            });
        }else {
            var FrePlanDtlInfos=[];
            for(var i in data){
                var FrePlanDtlInfo={
                    "FrePlanDtlRd":data[i].FrePlanDtlRd,
                    "TaskRd":data[i].TaskRd,
                    "TaskName":data[i].TaskName,
                    "TaskItemName":data[i].TaskItemName,
                    "SureType":data[i].SureType,
              /*      "MinExCount":data[i].MinExCount,*/
                    "MaxExCount":data[i].MaxExCount,
                }
                FrePlanDtlInfos.push(FrePlanDtlInfo);
            }



            var datas={
                "FrePlanRd":treeID,
                "FrePlanName":FrePlanName,
                "Status":Status,
                "Description":Description,
                "ReaRd":ReaRd,
                "FileVerRd":FileVerRd,
                "UseNum":UseNum,
                "UnitType":UnitType,
                "Reference":$("#Reference").val(),
                "UpUseNum":UpUseNum,
                "DownUseNum":DownUseNum,
                "PresetUseNum":PresetUseNum,
                "PresetEmailDistributionRd":a1==null?"":  PresetEmailDistributionRd==""?"":PresetEmailDistributionRd,
                "PresetEmailMessageRd":b1==null?"":PresetEmailMessageRd==""?"":PresetEmailMessageRd,
                "StartEmailDistributionRd":  c1==null?"": StartEmailDistributionRd==""?"":StartEmailDistributionRd,
                "StartEmailMessageRd":d1==null?"":StartEmailMessageRd==""?"":StartEmailMessageRd,
                //"OverTimeAction":OverTimeAction,
                "OverEmailDistributionRd":e1==null?"":OverEmailDistributionRd==""?"":OverEmailDistributionRd,
                "OverEmailMessageRd":f1==null?"":OverEmailMessageRd==""?"":OverEmailMessageRd,
                "FrePlanDtlInfo":FrePlanDtlInfos,
                "Remark":Remark,
            }

            request({url:"/FrePlan/SaveFrePlanInfo", data: {"ExecType": "02", "busData": JSON.stringify(datas)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#bynrqd").selectpicker('val',"");
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
                    var FrePlanRd=[{"FrePlanRd":treeID}];
                    request({url:"/FrePlan/SaveFrePlanInfo", data: {"ExecType": "01", "busData": JSON.stringify(FrePlanRd)}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        treeID = null;
                        currentPage=0;
                        condition = '';
                        edit=false;
                        loaddata();

                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });



})