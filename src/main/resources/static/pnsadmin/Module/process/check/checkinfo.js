$(function(){
    $("#tixingshijian").show();
    $("#yanqishijian").show();
    $("#jgsj").click(function () {
        if($("#jgsj").is(":checked")){
            $("#tixingshijian").hide();
            $("#yanqishijian").hide();
            $("#rxsxgc").val("0");
            $("#xtsj").val("0");
        }
    });
    $("#zdsj").click(function () {
        if($("#zdsj").is(":checked")){
            $("#tixingshijian").show();
            $("#yanqishijian").show();
            $("#rxsxgc").val("0");
            $("#xtsj").val("0");
        }
    });
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
    var PresetEmailDistributionRd="";
    var PresetEmailMessageRd="";

    var StartEmailDistributionRd="";
    var StartEmailMessageRd="";

    var OverEmailDistributionRd="";
    var OverEmailMessageRd="";

    var edit=false;

    var tableflag=false;
    var treeID=null;
    var colNamesArr = [
        {"Caption": "CheckPlanDtlRd", "Name": "CheckPlanDtlRd", "Hidden": true},
        {"Caption": "TaskRd", "Name": "TaskRd", "Hidden": true},
        {"Caption": "任务名称", "Name": "TaskName","Hidden": true},
        {"Caption": "任务项", "Name": "TaskItemName", "CType": "text"},
        {"Caption": "确认方式", "Name": "SureType", "CType": "text"},
/*        {"Caption": "最小执行次数", "Name": "MinExCount", "CType": "text"},*/
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
        $("#bynrqd").selectpicker('val',"");
        edit=true;
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CheckPlanRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/CheckPlan/GetCheckPlanInfo", data: objData},function(Body){
            $("#CyclePlanName").val(Body.Data.CheckPlanName);
            $("#status").val(Body.Data.Status);
            $("#CheckPlanType").val(Body.Data.CheckPlanType);
            $("#Description").val(Body.Data.Description);
            $("#startdata").val(Body.Data.StarDate);
            $("#enddata").val(Body.Data.EndDate);
            $("#Reference").val(Body.Data.Reference);
            if(Body.Data.DayInfo!=null){
                var check=Body.Data.DayInfo.TimeType;
                if(check!=null||check!=""){
                    if(check=="00"){
                        $("#zdsj").prop("checked",true);
                        if(Body.Data.DayInfo.TimeContent==null||Body.Data.DayInfo.TimeContent==""){
                            $("#TimeContent").val("");
                        }else {
                            $("#TimeContent").val(Body.Data.DayInfo.TimeContent);
                        }
                        $("#iddiv").show();
                        $("#jgsjdivs").hide();
                    }else{
                        $("#jgsj").prop("checked",true);
                        var gg=Body.Data.DayInfo.TimeContent;
                        $("#jgsjvalue").val(gg);
                        $("#iddiv").hide();
                        $("#jgsjdivs").show();
                    }
                }

            }

            $("#bybzzys").showData({
                id:Body.Data.FileInfo.FileVerRd,
                name:Body.Data.FileInfo.FileName,
                keyfield:"FileVerRd",
                fields:[
                    {"name":"FileVerRd"},
                    {"name":"FileName"}
                ]
            });


            var data=Body.Data.MTType;
            $("#byzq").val(data);
            if(data=="00"){

                $("#meiridiv").show();
                $("#jidudiv").hide();
                $("#meizhoudiv").hide();
                $("#meiyuediv").hide();
                $("#meiniandiv").hide();
                $("#zhidingriqi").hide();
            }
            if(data=="01"){
                if(Body.Data.QuarterInfo!=null||Body.Data.QuarterInfo!=""){
                    var jddata=Body.Data.QuarterInfo.QuarterContent;
                    if(jddata!=null||""!=jddata){
                        var aa=jddata.split("|");
                        $("#jidu").val(aa[0]);
                        $("#yuefen").val(aa[1]);
                        $("#hao").val(aa[2]);
                    }
                }


                $("#jidudiv").show();
                $("#meiridiv").hide();
                $("#meizhoudiv").hide();
                $("#meiyuediv").hide();
                $("#meiniandiv").hide();
                $("#zhidingriqi").hide();
            }
            if(data=="02"){
                $("#meizhou").val(Body.Data.WeekInfo.WeekContent);
                $("#meizhoudiv").show();
                $("#jidudiv").hide();
                $("#meiridiv").hide();
                $("#meiyuediv").hide();
                $("#meiniandiv").hide();
                $("#zhidingriqi").hide();
            }
            if(data=="03"){
                $("#yuehao").val(Body.Data.MonthInfo.MonthContent);
                $("#meiyuediv").show();
                $("#meizhoudiv").hide();
                $("#jidudiv").hide();
                $("#meiridiv").hide();
                $("#meiniandiv").hide();
                $("#zhidingriqi").hide();
            }
            if(data=="04"){



                var bbdata=Body.Data.YearInfo.YearContent;
                if(bbdata!=null||""!=bbdata){
                    var bb=bbdata.split("|");
                    $("#meiniandivyue").val(bb[0]);
                    $("#meiniandivhao").val(bb[1]);
                }

                $("#meiniandiv").show();
                $("#meiyuediv").hide();
                $("#meizhoudiv").hide();
                $("#jidudiv").hide();
                $("#meiridiv").hide();
                $("#zhidingriqi").hide();

            }
            if(data=="05"){
               // $("#zdri").val(Body.Data.DesInfo.DesContent);
                if(Body.Data.DesInfo!=null){
                    var gg=Body.Data.DesInfo.DesContent;

                    var ggs=gg.split("|");
                    if(gg==""||gg==null){
                        $("#zdri").val("");
                    }else {
                        $("#zdri").val(ggs[0]+"-"+ggs[1]+"-"+ggs[2]);
                    }

                }

                $("#zhidingriqi").show();
                $("#meiniandiv").hide();
                $("#meiyuediv").hide();
                $("#meizhoudiv").hide();
                $("#jidudiv").hide();
                $("#meiridiv").hide();
            }

            $("#rxsxgc").val(Body.Data.UpTime);
            $("#xtsj").val(Body.Data.PresetTime);


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

                $("#goaheademail").clearseldata("EmailDisRd");


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
                $("#goaheademailcontent").clearseldata("EmailRd");

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
                $("#daoqiGroup").clearseldata("EmailDisRd");

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
                $("#daoqiContent").clearseldata("EmailRd");

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
                $("#expiremailgroup").clearseldata("EmailDisRd");

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
                $("#expiremailcontent").clearseldata("EmailRd");
            }



           var sourcedata=Body.Data.CheckPlanDtlInfo;


            tableflag=true;
            var dataS=[];
            for(var i in sourcedata){
                data={
                    "CheckPlanDtlRd":sourcedata[i].CheckPlanDtlRd,
                    "TaskRd":sourcedata[i].TaskRd,
                   /* "TaskName":sourcedata[i].TaskName,*/
                    "TaskItemName":sourcedata[i].TaskItemName,
                    "SureType":sourcedata[i].SureType=="00"?"打勾":"记录结果",
            /*        "MinExCount":sourcedata[i].MinExCount,*/
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


            if($("#zdsj").is(":checked")){
                $("#tixingshijian").show();
                $("#yanqishijian").show();
                $("#rxsxgc").val("0");
                $("#xtsj").val("0");
            }
            if($("#jgsj").is(":checked")){
                $("#tixingshijian").hide();
                $("#yanqishijian").hide();
                $("#rxsxgc").val("0");
                $("#xtsj").val("0");
            }

            select1();

        });
    };
    $("#zdsj").click(function () {
        if($(this).is(":checked")){
            $("#iddiv").show();
            $("#jgsjdivs").hide();
            $("#TimeContent").val("");
        }
    });
    $("#jgsj").click(function () {
        if($(this).is(":checked")){
            $("#iddiv").hide();
            $("#jgsjdivs").show();
            $("#jgsjvalue").val("");
        }
    });
    $("#jidu").change(function () {
        var t=$(this).val();
        if(t=="1"){
            var option="<option value='1'>1</option><option value='2'>2</option><option value='3'>3</option>"
            $("#yuefen").empty().html(option);
        }
        if(t=="2"){
            var option="<option value='4'>4</option><option value='5'>5</option><option value='6'>6</option>"
            $("#yuefen").empty().html(option);
        }
        if(t=="3"){
            var option="<option value='7'>7</option><option value='8'>8</option><option value='9'>9</option>"
            $("#yuefen").empty().html(option);
        }
        if(t=="4"){
            var option="<option value='10'>10</option><option value='11'>11</option><option value='12'>12</option>"
            $("#yuefen").empty().html(option);
        }

        var yy=$("#yuefen").val();
        if(yy=="1"||yy=="7"||yy=="10"){
            var aa="";
            for(var i =1;i<=31;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#hao").empty().html(aa);
        }
        if(yy=="4"){
            var aa="";
            for(var i =1;i<=30;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#hao").empty().html(aa);
        }
    });




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
        currentPage = treeSearchs("/CheckPlan/GetAllCheckPlanInfo","CheckPlanRd","CheckPlanName","CheckPlanName",condition,currentPage,config);
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
            currentPage = treeSearchs("/CheckPlan/GetAllCheckPlanInfo","CheckPlanRd","CheckPlanName","CheckPlanName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/CheckPlan/GetAllCheckPlanInfo","CheckPlanRd","CheckPlanName","CheckPlanName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/CheckPlan/GetAllCheckPlanInfo","CheckPlanRd","CheckPlanName","CheckPlanName",condition,currentPage,config);
    });


    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        request({
            url:'/CheckPlan/GetAllCheckPlanInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CheckPlanName",
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
                        id: treeData[i].CheckPlanRd == undefined ? "" : treeData[i].CheckPlanRd,
                        name: treeData[i].CheckPlanName == undefined ? "" : treeData[i].CheckPlanName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }

        });
    };
    loaddata();
    //加载点检计划清单
    var globalData=[];
    var InitData = {
        "FiledList":[
            {
                "FieldName":"TaskType",
                "FieldOpt":"=",
                "TaskType":"01"
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


    $("#yuefen").change(function () {
        var yue=$(this).val();
        if(yue=="1"||yue=="3"||yue=="5"||yue=="7"||yue=="8"||yue=="10"||yue=="11"||yue=="12"){
            var aa="";
            for(var i =1;i<=31;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#hao").empty().html(aa);
        }else if (yue=="2") {
            var aa="";
            for(var i =1;i<=28;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#hao").empty().html(aa);
        }else {
            var aa="";
            for(var i =1;i<=30;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#hao").empty().html(aa);
        }
    });
    $("#meiniandivyue").change(function () {
        var yue=$(this).val();
        if(yue=="1"||yue=="3"||yue=="5"||yue=="7"||yue=="8"||yue=="10"||yue=="11"||yue=="12"){
            var aa="";
            for(var i =1;i<=31;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#meiniandivhao").empty().html(aa);
        }else if (yue=="2") {
            var aa="";
            for(var i =1;i<=28;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#meiniandivhao").empty().html(aa);
        }else {
            var aa="";
            for(var i =1;i<=30;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#meiniandivhao").empty().html(aa);
        }
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
                    "caption": "物料名称",
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
                d1=data.EmailDisRd;
            },
        }
    };
    $("#daoqiContent").zc_select(daoqiContent);

    //保养周期事件
    $("#byzq").change(function(){
        var data=$(this).val();
        if(data=="00"){
            $("#zdsj").prop("checked",true);
            $("#iddiv").show();
            $("#jgsjdivs").hide();
            $("#TimeContent").val("");
            $("#TimeContent").val("");
            $("#meiridiv").show();
            $("#jidudiv").hide();
            $("#meizhoudiv").hide();
            $("#meiyuediv").hide();
            $("#meiniandiv").hide();
            $("#zhidingriqi").hide();
        }
        if(data=="01"){
            $("#jidudiv").show();
            $("#meiridiv").hide();
            $("#meizhoudiv").hide();
            $("#meiyuediv").hide();
            $("#meiniandiv").hide();
            $("#zhidingriqi").hide();
            $("#jidu").val(1);
            var t=$("#jidu").val();
            if(t=="1"){
                var option="<option value='1'>1</option><option value='2'>2</option><option value='3'>3</option>"
                $("#yuefen").empty().html(option);
            }

            var yy=$("#yuefen").val();
            if(yy=="1"||yy=="7"||yy=="10"){
                var aa="";
                for(var i =1;i<=31;i++){
                    aa+=  "<option value='"+i+"'>"+i+"</option>"
                }
                $("#hao").empty().html(aa);
            }
            if(yy=="4"){
                var aa="";
                for(var i =1;i<=30;i++){
                    aa+=  "<option value='"+i+"'>"+i+"</option>"
                }
                $("#hao").empty().html(aa);
            }


        }
        if(data=="02"){
            $("#meizhoudiv").show();
            $("#jidudiv").hide();
            $("#meiridiv").hide();
            $("#meiyuediv").hide();
            $("#meiniandiv").hide();
            $("#zhidingriqi").hide();
        }
        if(data=="03"){
            $("#meiyuediv").show();
            $("#meizhoudiv").hide();
            $("#jidudiv").hide();
            $("#meiridiv").hide();
            $("#meiniandiv").hide();
            $("#zhidingriqi").hide();
        }
        if(data=="04"){

            $("#meiniandivyue").val("1");

            var aa="";
            for(var i =1;i<=31;i++){
                aa+=  "<option value='"+i+"'>"+i+"</option>"
            }
            $("#meiniandivhao").empty().html(aa);

            $("#meiniandiv").show();
            $("#meiyuediv").hide();
            $("#meizhoudiv").hide();
            $("#jidudiv").hide();
            $("#meiridiv").hide();
            $("#zhidingriqi").hide();

        }
        if(data=="05"){
            $("#zhidingriqi").show();
            $("#meiniandiv").hide();
            $("#meiyuediv").hide();
            $("#meizhoudiv").hide();
            $("#jidudiv").hide();
            $("#meiridiv").hide();
        }



    });
    //新增
    $(".cAdd").click(function () {
        $("#tixingshijian").show();
        $("#yanqishijian").show();
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

        $("#byzq").val("00");
        $("#iddiv").show();
        $("#jgsjdivs").show();

        var data=new Date();
        var hourse=data.getMonth()+1;
        var min=data.getMinutes();
        var data="";
        if(hourse<10){
            if(min<10){
                date="0"+hourse+":"+"0"+min;
            }else {
                date="0"+hourse+":"+min;
            }
        }else {
            if(min<10){
                date=hourse+":"+"0"+min;
            }else {
                date=hourse+":"+min;
            }
        }
        $("#bybzzys").clearseldata("FileVerRd");
        $("#TimeContent").val(date);
        $("#iddiv").show();
        $("#jgsjdivs").hide();
        $("#_right").show();
        $("#CyclePlanName").val("");
        $("#Description").val("");

        $("#status").val("00");
  /*      $("#rxxxgc").val("0");*/
        $("#rxsxgc").val("0");
        $("#xtsj").val("0");
        $("#bynrqd").selectpicker('val',"");
       // $("#startdata").val('');
        $("#enddata").val('');
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height:0.36
        };
        fullTable(config1);
        $("#gqcqxd").val("00");

        $("#goaheademail").clearseldata("EmailDisRd");
        $("#goaheademailcontent").clearseldata("EmailRd");
        $("#daoqiGroup").clearseldata("EmailDisRd");
        $("#daoqiContent").clearseldata("EmailRd");
        $("#expiremailgroup").clearseldata("EmailDisRd");
        $("#expiremailcontent").clearseldata("EmailRd");
        $("#beizhu").val("");

        $("#startdata").val("0000-09-10");
        $("#zdsj").prop("checked",true);
        $("#jgsj").prop("checked",false);
        $("#meiridiv").show();
        $("#jidudiv").hide();
        $("#meizhoudiv").hide();
        $("#meiyuediv").hide();
        $("#meiniandiv").hide();
        $("#zhidingriqi").hide();
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
            var TaskItemName="";
            if(globalData.length>0){
                for(var j in globalData){
                    if(parseInt(globalData[j].TaskRd)==parseInt(data[i])){
                        TaskItemName=globalData[j].TaskItemName;
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
                        "CheckPlanDtlRd":"",
                        "TaskRd":id,
                      /*  "TaskName":TaskName,*/
                        "TaskItemName":data[j].TaskItemName,
                        "SureType":data[j].SureType=="00"?"打勾":"记录结果",
               /*         "MinExCount":data[j].MinExCount,*/
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

        var CyclePlanName=$("#CyclePlanName").val().trim();
        if(CyclePlanName==null||CyclePlanName==""){
            toastr.warning("名称不能为空");
            return false;
        }
        var Status=$("#status").val();
        var Description=$("#Description").val().trim();

        var CheckPlanType=$("#CheckPlanType").val().trim();
        if(CheckPlanType ==null || CheckPlanType==""){
            toastr.warning("点检计划类型不能为空");
            return false;
        }

        var FileVerRd=$("#bybzzys").getseldata().FileVerRd;
        if(FileVerRd==null||FileVerRd==""){
            toastr.warning("保养标准作业书不能为空");
            return false;
        }

        var StarDate=$("#startdata").val();
        if(StarDate==null||StarDate==""){
            toastr.warning("起算日期不能为空");
            return false;
        }
        var EndDate=$("#enddata").val();
        if(EndDate!=null||EndDate!=""){
            var start = new Date(StarDate.replace("-", "/").replace("-", "/"));
            var end = new Date(EndDate.replace("-", "/").replace("-", "/"));
            if(start>end){
                toastr.warning("保存失败,起算日期不能大于截止日期");
                return false;
            }
        }




        var MTType=$("#byzq").val();
        var TimeType="";
        var TimeContent="";
        if(MTType=="00"){
            if($("#zdsj").is(":checked")){
                TimeType="00";
                TimeContent=$("#TimeContent").val();


            }
            if($("#jgsj").is(":checked")){
                TimeType="01";
                var gg=$("#jgsjvalue").val();
                TimeContent=gg
            }
        }

        var QuarterContent="";
        if(MTType=="01"){
            var a=$("#jidu").val();
            var b=$("#yuefen").val();
            var c=$("#hao").val();
            QuarterContent=a+"|"+b+"|"+c;
        }

        var WeekContent="";
        if(MTType=="02"){
            WeekContent=$("#meizhou").val();
        }

        var MonthContent="";
        if(MTType=="03"){
            MonthContent=$("#yuehao").val();
        }

        var YearContent="";
        if(MTType=="04"){
            var a=$("#meiniandivyue").val();
            var b=$("#meiniandivhao").val();
            YearContent=a+"|"+b;
        }

        var DesContent="";
        if(MTType=="05"){
            var gg =$("#zdri").val().split("-");

            DesContent=gg[0]+"|"+gg[1]+"|"+gg[2]
        }
        var data = getTableData("list4");
        if(data.length<=0||data==null||data==""){
            toastr.warning("保养清单明细不能为空");
            return false;
        }

        var Remark=$("#beizhu").val();
/*        var DownTime=$("#rxxxgc").val().trim();*/
        var DownTime="0";
        var UpTime=$("#rxsxgc").val().trim();
        var PresetTime="";
        if(TimeType=="00"||TimeType=="01"){
            PresetTime=$("#xtsj").val().trim();
        }else {
            PresetTime="0";
        }


        if(treeID==null){
            var CyclePlanDtlInfos=[];
            for(var i in data){
                var CyclePlanDtlInfo={
                    "TaskRd":data[i].TaskRd,
                    "TaskName":data[i].TaskName,
                    "TaskItemName":data[i].TaskItemName,
                    "SureType":data[i].SureType,
        /*            "MinExCount":data[i].MinExCount,*/
                    "MaxExCount":data[i].MaxExCount,
                }
                CyclePlanDtlInfos.push(CyclePlanDtlInfo);
            }

            var data={
                "CheckPlanName":CyclePlanName,
                "Status":Status,
                "CheckPlanType":CheckPlanType,
                "Description":Description,
                "StarDate":StarDate,
                "EndDate":EndDate,
                "MTType":MTType,
                "DayInfo":{
                    "TimeType":TimeType,
                    "TimeContent":TimeContent
                },
                "WeekInfo":{
                    "WeekContent":WeekContent
                },
                "MonthInfo":{
                    "MonthContent":MonthContent
                },
                "YearInfo":{
                    "YearContent":YearContent
                },
                "DesInfo":{
                    "DesContent":DesContent
                },
                "QuarterInfo":{
                    "QuarterContent":QuarterContent
                },
                "UpTime":UpTime,
                "DownTime":DownTime,
                "PresetTime":PresetTime,
                "Reference":$("#Reference").val(),
                "FileVerRd":FileVerRd,
                "PresetEmailDistributionRd":a1==null?"":  PresetEmailDistributionRd,
                "PresetEmailMessageRd":b1==null?"":PresetEmailMessageRd,
                "StartEmailDistributionRd":  c1==null?"": StartEmailDistributionRd,
                "StartEmailMessageRd":d1==null?"":StartEmailMessageRd,
                "OverEmailDistributionRd":e1==null?"":OverEmailDistributionRd,
                "OverEmailMessageRd":f1==null?"":OverEmailMessageRd,
                "CheckPlanDtlInfo":CyclePlanDtlInfos,
                "Remark":Remark,
            }

            request({url:"/CheckPlan/SaveCheckPlanInfo", data: {"ExecType": "00", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#bynrqd").selectpicker('val',"");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();

            });
        }else {
            var CyclePlanDtlInfos=[];
            for(var i in data){
                var CyclePlanDtlInfo={
                    "CheckPlanDtlRd":data[i].CheckPlanDtlRd,
                    "TaskRd":data[i].TaskRd,
                    "TaskName":data[i].TaskName,
                    "TaskItemName":data[i].TaskItemName,
                    "SureType":data[i].SureType,
                 /*   "MinExCount":data[i].MinExCount,*/
                    "MaxExCount":data[i].MaxExCount,
                }
                CyclePlanDtlInfos.push(CyclePlanDtlInfo);
            }

           if(StarDate==""||EndDate==""){
               toastr.warning("保存失败,开始或者结束日期不能为空");
               return false;
           }

            var data={
                "CheckPlanRd":treeID,
                "CheckPlanName":CyclePlanName,
                "Status":Status,
                "CheckPlanType":CheckPlanType,
                "Description":Description,
                "StarDate":StarDate,
                "EndDate":EndDate,
                "MTType":MTType,
                "DayInfo":{
                    "TimeType":TimeType,
                    "TimeContent":TimeContent
                },
                "WeekInfo":{
                    "WeekContent":WeekContent
                },
                "MonthInfo":{
                    "MonthContent":MonthContent
                },
                "YearInfo":{
                    "YearContent":YearContent
                },
                "DesInfo":{
                    "DesContent":DesContent
                },
                "QuarterInfo":{
                    "QuarterContent":QuarterContent
                },
                "UpTime":UpTime,
                "Reference":$("#Reference").val(),
                "FileVerRd":FileVerRd,
                "DownTime":DownTime,
                "PresetTime":PresetTime,
                "PresetEmailDistributionRd":a1==null?"":  PresetEmailDistributionRd==""?"":PresetEmailDistributionRd,
                "PresetEmailMessageRd":b1==null?"":PresetEmailMessageRd==""?"":PresetEmailMessageRd,
                "StartEmailDistributionRd":  c1==null?"": StartEmailDistributionRd==""?"":StartEmailDistributionRd,
                "StartEmailMessageRd":d1==null?"":StartEmailMessageRd==""?"":StartEmailMessageRd,
                "OverEmailDistributionRd":e1==null?"":OverEmailDistributionRd==""?"":OverEmailDistributionRd,
                "OverEmailMessageRd":f1==null?"":OverEmailMessageRd==""?"":OverEmailMessageRd,
                "CheckPlanDtlInfo":CyclePlanDtlInfos,
                "Remark":Remark,
            }
            request({url:"/CheckPlan/SaveCheckPlanInfo", data: {"ExecType": "02", "busData": JSON.stringify(data)}},function(Body){
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
                    request({url:"/CheckPlan/SaveCheckPlanInfo", data: {"ExecType": "01", "busData": "{\"CheckPlanRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        treeID = null;
                        currentPage=0;
                        condition = '';
                        loaddata();
                        edit=false;
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

})