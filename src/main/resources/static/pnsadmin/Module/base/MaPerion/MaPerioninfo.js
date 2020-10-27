$(function () {
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
        $("#expiremailgroup input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                c1=null;
            }
        });
    }

    var d1=null;
    function  dd() {
        $("#expiremailcontent input").keyup(function () {
            if($(this).val()==""||$(this).val()==null){
                d1=null;
            }
        });
    }
    var PresetEmailDistributionRd="";
    var OverEmailMessageRd="";
    var OverEmailDistributionRd="";
    var PresetEmailMessageRd="";
    //树的列表信息
    var nodeid=null;
    var MaVerRd="";
    var StartSpecRd="";
    var EndSpecRd="";
    var wfData=[];


    var gylcid="";
    var onClicks = function (nodeinfo, handle) {
        aa()
        bb()
        cc()
        dd()
        $(".right").show();
        nodeid=nodeinfo.nodeID;
        $(".cAdd").attr("a","01")

        var objBusData = JSON.stringify({"MaPerionRd": nodeid});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/MaPerion/GetMaPerionInfo", data: objData,}, function (Body) {

            $("#supCode").val(Body.Data.MaPeriodName);
            $("#textr").val(Body.Data.Description);
            $("#maxtime").val(Body.Data.PeriodTime);
            $("#expricetime").val(Body.Data.PresetTime);
            $("#creatPeople").val(Body.Data.CreateTime);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            $("#csxd").val(Body.Data.OverTimeAction);




            if(Body.Data.PresetEmailMessageInfo!=null){
                PresetEmailMessageRd  =Body.Data.PresetEmailMessageInfo.EmailMessageRd;
                b1=Body.Data.PresetEmailMessageInfo.EmailMessageRd;
                $("#goaheademailcontent").showData({
                    id:Body.Data.PresetEmailMessageInfo.EmailMessageRd,
                    name:Body.Data.PresetEmailMessageInfo.EmailMessageName,
                    keyfield:"EmailRd",
                    fields:[
                        {"name":"EmailRd"},
                        {"name":"EmailName"}
                    ]
                });
            }else {
                $("#goaheademailcontent").clearseldata("EmailRd");
                b1=null;
            }


            if(Body.Data.PresetEmailDistributionInfo!=null){
                PresetEmailDistributionRd=Body.Data.PresetEmailDistributionInfo.EmailDistributionRd;
                a1=Body.Data.PresetEmailDistributionInfo.EmailDistributionRd;
                $("#goaheademail").showData({
                    id:Body.Data.PresetEmailDistributionInfo.EmailDistributionRd,
                    name:Body.Data.PresetEmailDistributionInfo.EmailDistributionName,
                    keyfield:"EmailDisRd",
                    fields:[
                        {"name":"EmailDisRd"},
                        {"name":"EmailDisName"}
                    ]
                });
            }else {
                $("#goaheademail").clearseldata("EmailDisRd");
                a1=null;


            }
            if(Body.Data.OverdueEmailMessageInfo!=null){
                OverEmailMessageRd=Body.Data.OverdueEmailMessageInfo.EmailMessageRd;
                d1=Body.Data.OverdueEmailMessageInfo.EmailMessageRd;
                $("#expiremailcontent").showData({
                    id:Body.Data.OverdueEmailMessageInfo.EmailMessageRd,
                    name:Body.Data.OverdueEmailMessageInfo.EmailMessageName,
                    keyfield:"EmailRd",
                    fields:[
                        {"name":"EmailRd"},
                        {"name":"EmailName"}
                    ]
                });
            }else {
                d1=null;
                $("#expiremailcontent").clearseldata("EmailRd");
            }
            if(Body.Data.OverdueEmailDistributionInfo!=null){
                OverEmailDistributionRd=Body.Data.OverdueEmailDistributionInfo.EmailDistributionRd;
                c1=Body.Data.OverdueEmailDistributionInfo.EmailDistributionRd;
                $("#expiremailgroup").showData({
                    id:Body.Data.OverdueEmailDistributionInfo.EmailDistributionRd,
                    name:Body.Data.OverdueEmailDistributionInfo.EmailDistributionName,
                    keyfield:"EmailDisRd",
                    fields:[
                        {"name":"EmailDisRd"},
                        {"name":"EmailDisName"}
                    ]
                });

            }else {
                c1=null;
                $("#expiremailgroup").clearseldata("EmailDisRd");
            }


        });
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
        currentPage = treeSearchs("/MaPerion/GetAllMaPerionInfo","MaPerionRd","MaPeriodName","MaPeriodName",condition,currentPage,config);
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
            currentPage = treeSearchs("/MaPerion/GetAllMaPerionInfo","MaPerionRd","MaPeriodName","MaPeriodName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/MaPerion/GetAllMaPerionInfo","MaPerionRd","MaPeriodName","MaPeriodName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/MaPerion/GetAllMaPerionInfo","MaPerionRd","MaPeriodName","MaPeriodName",condition,currentPage,config);
    });
    $(".right").hide();
    //$(".right").show();
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
                url: '/MaPerion/GetAllMaPerionInfo',
                data: {
                    "ExecType": "00",
                    "PageInfo":JSON.stringify(pageInfo),
                    "InitData": JSON.stringify({
                        "FiledList": [
                            {
                                "FieldName": "CreateTime DESC",
                                "FieldOpt": "Order BY"
                            }]})
                }
            },
            function (Body) {
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].MaPerionRd == undefined ? "" : treeData[i].MaPerionRd,
                        name: treeData[i].MaPeriodName == undefined ? "" : treeData[i].MaPeriodName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            })
    };

    loaddata();


    //点击的时候获取wf数据
   /* var nullData=[];
    //加载产品
    var material = {
        "displaymode": "1",
        "title": "物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "MaVerRd",
                    "name": "MaVerRd"
                }, {
                    "caption": "物料名称",
                    "name": "MaName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"MaterialName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "MaterialName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Material/GetAllMaInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "MaVerRd":datas[i].MaVerRd,
                            "MaName": datas[i].MaName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow: 50
                };
                return obj;
            },
            "onclick": function (data) {
                var objData = {
                    "ExecType": "00",
                    "BusData": JSON.stringify({"MVerRd": data.MaVerRd})
                };
                request({url:"/Commom/GetCMWFInfo", data: objData},function(Body){
                   var treedataList=Body.Data.WFInfo;
                    nullData=Body.Data.WFInfo;
                    var aa="";
                    var obj=[];
                    for(var i  in  treedataList){
                        aa+='<option  value='+treedataList[i].WFVerRd+'>'+treedataList[i].WFName +'</option>';
                    }
                    $("#gylc").html(aa);
                    var aadata=[];
                    for(var j  in  treedataList){
                        if($("#gylc option:first-child").val()==treedataList[j].WFVerRd){
                            aadata=treedataList[j].SpecInfo;
                        }
                    }

                    if(treedataList.length<=0){
                        toastr.warning("查询失败，该产品没有工序信息，请重新选择");
                        $("#startSpec").empty();
                        $("#endSpec").empty();
                        return false;
                    }
                    var startSpec="";
                    for(var i  in aadata){
                        startSpec+="<option value='"+aadata[i].SpecVerRd+"'>"+aadata[i].SpecName+"</option>"
                    }
                    $("#startSpec").html(startSpec);
                    $("#endSpec").html(startSpec)
                    $("#startSpec").val(StartSpecRd==""||StartSpecRd==null?$("#startSpec option:first-child").val():StartSpecRd);
                    $("#endSpec").val(EndSpecRd==""||EndSpecRd==null?$("#endSpec option:first-child").val():EndSpecRd);
                   // gylcfunction();
                });

                MaVerRd=data.MaVerRd;
              //  gylcfunction();
            },
        }
    };

    $("#material").zc_select(material);*/

    //提前邮件组
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



    //提前邮件内容
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
                c1=data.EmailDisRd;
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
                d1=data.EmailRd;
            },
        }
    };

    $("#expiremailcontent").zc_select(expireemailgroupcontent);


    //开始工序下拉框选中事件
    /*$("#startSpec").change(function(){
        var opt=$(this).val();
        StartSpecRd=opt;
    });

    //结束工序选中事件
    $("#endSpec").change(function(){
        var opt=$(this).val();
        EndSpecRd=opt;
    });*/
   /* $("#gylc").change(function(){
        if(wfData.length>0&&wfData!=null&&wfData!=""){
            var aadata=[];
            for(var j  in  wfData){
                if($("#gylc option:selected").val()==wfData[j].WFVerRd){
                    aadata=wfData[j].SpecInfo;
                }
            }
            var startSpec="";
            for(var i  in aadata){
                startSpec+="<option value='"+aadata[i].SpecVerRd+"'>"+aadata[i].SpecName+"</option>"
            }
            $("#startSpec").empty().html(startSpec);
            $("#endSpec").empty().html(startSpec)

        }
        else {
            if(nullData.length>0){
                var aadata=[];
                for(var j  in  nullData){
                    if($("#gylc option:selected").val()==nullData[j].WFVerRd){
                        aadata=nullData[j].SpecInfo;
                    }
                }
                var startSpec="";
                for(var i  in aadata){
                    startSpec+="<option value='"+aadata[i].SpecVerRd+"'>"+aadata[i].SpecName+"</option>"
                }
                $("#startSpec").empty().html(startSpec);
                $("#endSpec").empty().html(startSpec)
            }
        }
    });*/
    //新增
    $(".cAdd").click(function () {


        a1=null;
        b1=null;
        c1=null;
        d1=null;
        $(".right").show();
        $("#supCode").val("");
        $("#textr").val("");
        $(".checkFloat").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $("#goaheademail").clearseldata("EmailDisRd");
        $("#goaheademailcontent").clearseldata("EmailRd");
        $("#expiremailgroup").clearseldata("EmailDisRd");
        $("#expiremailcontent").clearseldata("EmailRd");
        $("#startSpec").empty();
        $("#gylc").empty();
        $("#endSpec").empty();

        $(this).attr("a","00");
    });

    //保存
    $(".cSave").click(function () {
        if($("#supCode").val().trim()==null||$("#supCode").val().trim()==""){
            toastr.warning("保存失败，名称不能为空");
            return false;
        }


        if($("#maxtime").val().trim()==null||$("#maxtime").val().trim()==""){
            toastr.warning("保存失败，开箱使用有效时间不能为空");
            return false;
        }
        if($("#csxd").val().trim()==null||$("#csxd").val().trim()==""){
            toastr.warning("保存失败，超时采取行动不能为空");
            return false;
        }

        if($("#expricetime").val().trim()==null||$("#csxd").val().trim()==""){
            toastr.warning("保存失败，提前提醒时间不能为空");
            return false;
        }

        if(parseFloat($("#expricetime").val().trim())>= parseFloat($("#maxtime").val().trim())){
            toastr.warning("保存失败，提前提醒时间不能大于开箱使用有效时间");
            return false;
        }

        //新增保存
        if($(".cAdd").attr("a")=="00"){

            var InitDta={
                "MaPeriodName":$("#supCode").val().trim(),
                "Description":$("#textr").val().trim(),
                /*"MaVerRd":MaVerRd,
                "WfVerRd":WfVerRd,
                "StartSpecVerRd":StartSpecRd,
                "EndSpecVerRd":EndSpecRd,*/
                "PeriodTime":$("#maxtime").val().trim(),
                "PresetTime":$("#expricetime").val().trim(),
                "PresetEmailDistributionRd":$("#goaheademail").getseldata().EmailDisRd==null||$("#goaheademail").getseldata().EmailDisRd==""?"":PresetEmailDistributionRd,
                "PresetEmailMessageRd":$("#goaheademailcontent").getseldata().EmailRd==null||$("#goaheademailcontent").getseldata().EmailRd==""?"":PresetEmailMessageRd,
                "OverTimeAction":$("#csxd option:selected").val(),
                "OverEmailDistributionRd":$("#expiremailgroup").getseldata().EmailDisRd==null||$("#expiremailgroup").getseldata().EmailDisRd==""? "": OverEmailDistributionRd,
                "OverEmailMessageRd":$("#expiremailcontent").getseldata().EmailRd==null||$("#expiremailcontent").getseldata().EmailRd==""? "":OverEmailMessageRd,
                "Remark":$("#beizhu").val()
            }

            request({
                url: "/MaPerion/SaveMaPerionInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(InitDta)}
            }, function (Body) {

              toastr.success(Body.MsgDes);
              currentPage=0;
              condition = '';
              loaddata();
            });
        }
        //修改保存
        if($(".cAdd").attr("a")=="01"){
            /*if($("#supCode").val().trim()==null||$("#supCode").val().trim()==""){
                toastr.warning("保存失败，名称不能为空");
                return false;
            }
            if(MaVerRd==null||MaVerRd==""){
                toastr.warning("保存失败，产品不能为空");
                return false;
            }
            if(StartSpecRd==null||StartSpecRd==""){
                toastr.warning("保存失败，开始工序不能为空");
                return false;
            }
            if(EndSpecRd==null||EndSpecRd==""){
                toastr.warning("保存失败，结束工序不能为空");
                return false;
            }


            if($("#maxtime").val().trim()==null||$("#maxtime").val().trim()==""){
                toastr.warning("保存失败，最大时间不能为空");
                return false;
            }

            if($("#csxd").val().trim()==null||$("#csxd").val().trim()==""){
                toastr.warning("保存失败，超时采取行动不能为空");
                return false;
            }*/

            var InitDta={
                "MaPerionRd":nodeid,
                "MaPeriodName":$("#supCode").val().trim(),
                "Description":$("#textr").val().trim(),
                /*"MaVerRd":MaVerRd,
                "WfVerRd":WfVerRd,
                "StartSpecVerRd":StartSpecRd,
                "EndSpecVerRd":EndSpecRd,*/
                "PeriodTime":$("#maxtime").val().trim(),
                "PresetTime":$("#expricetime").val().trim(),
                "PresetEmailDistributionRd":a1==null?"":PresetEmailDistributionRd,
                "PresetEmailMessageRd":b1==null?"":PresetEmailMessageRd,
                "OverTimeAction":$("#csxd option:selected").val(),
                "OverEmailDistributionRd":c1==null?"":OverEmailDistributionRd,
                "OverEmailMessageRd":d1==null?"":OverEmailMessageRd,
                "Remark":$("#beizhu").val()
            }

            request({
                url: "/MaPerion/SaveMaPerionInfo",
                data: {"ExecType": "02", "busData": JSON.stringify(InitDta)}
            }, function (Body) {

                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = '';
                loaddata();
            });
        }
    });

    //删除
    $(".cDel").click(function(){
        if(nodeid==null){
            toastr.warning("删除失败，请选择左侧你要删除的一项");
            return false;
        }
        var initData={
            "MaPerionRd":nodeid
        }
        layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
            request({
                url: "/MaPerion/SaveMaPerionInfo",
                data: {"ExecType": "01", "busData": JSON.stringify(initData)}
            }, function (Body) {
                layer.closeAll("dialog");
                nodeid = null;
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = '';
                loaddata();
                $(".right").hide();
            });
            }
        );

    });
});