$(function () {
/*    var c1=null;
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
    }*/
    //树的列表信息
    var nodeid=null;
    var MaVerRd="";
    var StartSpecRd="";
    var DevGD="";
   var MaRd="";
    var wfData=[];
    var gylcid="";
    var onClicks = function (nodeinfo, handle) {

       /* cc()
        dd()*/
        $(".right").show();
        nodeid=nodeinfo.nodeID;
        $(".cAdd").attr("a","01")

        var objBusData = JSON.stringify({"MinTimeWindowRd": nodeid});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/MTV/GetMinTimeWindowInfo", data: objData}, function (Body) {

            if(Body.Data.SpecInfo!=null){
                var optbusData = {
                    "ExecType": "01",
                    "BusData": JSON.stringify({"SpecVerRd":Body.Data.SpecInfo.SpecVerRd })
                };

                request({url:"/Spec/GetSVInfo", data: optbusData},function(Body){

                    var Devices=Body.Data.Device;
                    if(Devices!=null){
                        var optbusData = {
                            "ExecType": "00",
                            "BusData": JSON.stringify({"DevGRd":Devices.DevGpRd })
                        };
                        request({url:"/DeviceG/GetDevGInfo", data: optbusData},function(Body){
                            var DevInfos=Body.Data.DevInfo;
                            if(DevInfos==null){
                                /*           toastr.warning("查询失败，该工序没有设备信息，请重新选择");*/
                                $("#DevGD").empty();
                                $("#DevGD").val("");
                                return false;
                            }
                            var DevGD="";
                            for(var i  in DevInfos){
                                DevGD+="<option value='"+DevInfos[i].DevRd+"'>"+DevInfos[i].DevName+"</option>"
                            }
                            $("#DevGD").html(DevGD);
                        });
                    }

                });
            }
            if(Body.Data.WFInfo!=null){
                gylcid=Body.Data.WFInfo.WfVerRd;
            }

            $("#supCode").val(Body.Data.MinTimeWindowName);
            $("#textr").val(Body.Data.Description);
            $("#maxtime").val(Body.Data.MinTime);
            $("#creatPeople").val(Body.Data.CreateTime);  //创建人
            $("#creatTime").val(Body.Data.CreateTime);  //创建时间
            $("#modifyPeople").val(Body.Data.LastModifyMan);//修改人
            $("#modifyTime").val(Body.Data.LastModifyTime); //修改时间
            $("#beizhu").val(Body.Data.Remark);
            $("#csxd").val(Body.Data.OverTimeAction);  //超时提醒
           // alert(JSON.stringify(Body.Data.MaInfo))
            if(Body.Data.MaInfo!=null){
                MaVerRd=Body.Data.MaInfo.MaVerRd;
                var objData = {
                    "ExecType": "00",
                    "BusData": JSON.stringify({"MVerRd": MaVerRd})
                };
                request({url:"/Commom/GetCMWFInfo", data: objData},function(Body){
                    var treedataList=Body.Data.WFInfo;
                    wfData=Body.Data.WFInfo;
                    var aa="";
                    var obj=[];
                    for(var i  in  treedataList){
                        aa+='<option  value='+treedataList[i].WFVerRd+'>'+treedataList[i].WFName +'</option>';
                    }
                    $("#gylc").html(aa);
                    $("#gylc").val(gylcid);
                    var aadata=[];
                    for(var j  in  treedataList){
                        if($("#gylc option:first-child").val()==treedataList[j].WFVerRd){
                            aadata=treedataList[j].SpecInfo;
                        }
                    }

                    if(treedataList.length<=0){
                        toastr.warning("查询失败，该产品没有工序信息，请重新选择");
                        $("#startSpec").empty();
                        return false;
                    }

                    var startSpec="";
                    for(var i  in aadata){
                        startSpec+="<option value='"+aadata[i].SpecVerRd+"'>"+aadata[i].SpecName+"</option>"
                    }

                    $("#startSpec").html(startSpec);
                    $("#startSpec").val(StartSpecRd);

                });
            }
          //  MaVerRd=Body.Data.MaterialInfo.MaVerRd;
           // MaRd=Body.Data.MaterialInfo.MaterialRd;
            if(Body.Data.SpecInfo!=null){
                StartSpecRd=Body.Data.SpecInfo.SpecVerRd;
                $("#startSpec").val(StartSpecRd);
            }

            if(Body.Data.DevInfo!=null){
                $("#DevGD").val(Body.Data.DevInfo.DevRd);
            }

            $("#material").showData({
                id:Body.Data.MaInfo.MaVerRd,
                name:Body.Data.MaInfo.MaName,
                keyfield:"MaVerRd",
                fields:[
                    {"name":"MaVerRd"},
                    {"name":"MaName"}
                ]
            });
            if(Body.Data.EmailDistributionInfo!=null) {
                OverEmailDistributionRd = Body.Data.EmailDistributionInfo.EmailDistributionRd;
                //c1 = Body.Data.EmailDistributionInfo.EmailDistributionRd;
                $("#expiremailgroup").showData({
                    id: Body.Data.EmailDistributionInfo.EmailDistributionRd,
                    name: Body.Data.EmailDistributionInfo.EmailDistributionName,
                    keyfield: "EmailDistributionRd",
                    fields: [
                        {"name": "EmailDistributionRd"},
                        {"name": "EmailDistributionName"}
                    ]
                });
            }else {
                //c1=null;
                $("#expiremailgroup").clearseldata("EmailDistributionRd");
            }

            if(Body.Data.EmailMessageInfo!=null){
                OverEmailMessageRd=Body.Data.EmailMessageInfo.EmailMessageRd;
                //d1=Body.Data.EmailMessageInfo.EmailMessageRd;
            $("#expiremailcontent").showData({
                id:Body.Data.EmailMessageInfo.EmailMessageRd,
                name:Body.Data.EmailMessageInfo.EmailMessageName,
                keyfield:"EmailMessageRd",
                fields:[
                    {"name":"EmailMessageRd"},
                    {"name":"EmailMessageName"}
                ]
            });
            }else {
                //d1=null;
                $("#expiremailcontent").clearseldata("EmailMessageRd");
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
        currentPage = treeSearchs("/MTV/GetAllMinTimeWindowInfo","MinTimeWindowRd","MinTimeWindowName","MinTimeWindowName",condition,currentPage,config);
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
            currentPage = treeSearchs("/MTV/GetAllMinTimeWindowInfo","MinTimeWindowRd","MinTimeWindowName","MinTimeWindowName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/MTV/GetAllMinTimeWindowInfo","MinTimeWindowRd","MinTimeWindowName","MinTimeWindowName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/MTV/GetAllMinTimeWindowInfo","MinTimeWindowRd","MinTimeWindowName","MinTimeWindowName",condition,currentPage,config);
    });
    $(".right").hide();

    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
                url: '/MTV/GetAllMinTimeWindowInfo',
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
                        id: treeData[i].MinTimeWindowRd == undefined ? "" : treeData[i].MinTimeWindowRd,
                        name: treeData[i].MinTimeWindowName == undefined ? "" : treeData[i].MinTimeWindowName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            })
    };
    //处理点击新增时的 物料加载的产品
    var nullData=[];
    loaddata();
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
                        },
                        {
                            "FieldName":"MaterialType",
                            "FieldOpt":"in",
                            "FieldVal":"(01,00)"
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
                    showrow:30
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
                    if(treedataList.length<=0){
                        toastr.warning("查询失败，该产品没有工序信息，请重新选择");
                        $("#startSpec").empty();
                        $("#DevGD").empty();
                        StartSpecRd="";
                        DevGD="";
                        return false;
                    }
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

                    var startSpec="";
                    for(var i  in aadata){
                        startSpec+="<option value='"+aadata[i].SpecVerRd+"'>"+aadata[i].SpecName+"</option>"
                    }
                    $("#startSpec").html(startSpec);

                    StartSpecRd=$("#startSpec").val();
                    var optbusData = {
                        "ExecType": "01",
                        "BusData": JSON.stringify({"SpecVerRd":StartSpecRd })
                    };

                    request({url:"/Spec/GetSVInfo", data: optbusData},function(Body){
                        var Devices=Body.Data.Device;
                        if(Devices==null){
                            toastr.warning("");
                            return false;
                        }
                        var optbusData = {
                            "ExecType": "00",
                            "BusData": JSON.stringify({"DevGRd":Devices.DevGpRd })
                        };
                        request({url:"/DeviceG/GetDevGInfo", data: optbusData},function(Body){
                            var DevInfos=Body.Data.DevInfo;
                            if(DevInfos==null){
                                /*           toastr.warning("查询失败，该工序没有设备信息，请重新选择");*/
                                $("#DevGD").empty();
                                $("#DevGD").val("");
                                return false;
                            }
                            var DevGD="";
                            for(var i  in DevInfos){
                                DevGD+="<option value='"+DevInfos[i].DevRd+"'>"+DevInfos[i].DevName+"</option>"
                            }
                            $("#DevGD").html(DevGD);
                            DevGD= $("#DevGD").val();

                        });
                    });
                    //gylcfunction();
                });

               MaVerRd=data.MaVerRd;

            },
        }
    };

    $("#material").zc_select(material);
    ////////
    //工序下拉框选中事件
    $("#startSpec").change(function(){
        var opt=$(this).val();
        StartSpecRd=opt;
        var optbusData = {
            "ExecType": "01",
            "BusData": JSON.stringify({"SpecVerRd":opt })
        };

        request({url:"/Spec/GetSVInfo", data: optbusData},function(Body){
            var Devices=Body.Data.Device;
            if(Devices==null){
                toastr.warning("");
                return false;
            }
            var optbusData = {
                "ExecType": "00",
                "BusData": JSON.stringify({"DevGRd":Devices.DevGpRd })
            };
            request({url:"/DeviceG/GetDevGInfo", data: optbusData},function(Body){
                var DevInfos=Body.Data.DevInfo;
                if(DevInfos==null){
         /*           toastr.warning("查询失败，该工序没有设备信息，请重新选择");*/
                    $("#DevGD").empty();
                    $("#DevGD").val("");
                    return false;
                }
                var DevGD="";
                for(var i  in DevInfos){
                    DevGD+="<option value='"+DevInfos[i].DevRd+"'>"+DevInfos[i].DevName+"</option>"
                }
                $("#DevGD").html(DevGD);
                DevGD= $("#DevGD").val();

            });
        });
    });

    $("#gylc").change(function(){
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
            StartSpecRd=$("#startSpec").val();
            var optbusData = {
                "ExecType": "01",
                "BusData": JSON.stringify({"SpecVerRd":StartSpecRd })
            };

            request({url:"/Spec/GetSVInfo", data: optbusData},function(Body){
                var Devices=Body.Data.Device;
                if(Devices==null){
                    toastr.warning("");
                    return false;
                }
                var optbusData = {
                    "ExecType": "00",
                    "BusData": JSON.stringify({"DevGRd":Devices.DevGpRd })
                };
                request({url:"/DeviceG/GetDevGInfo", data: optbusData},function(Body){
                    var DevInfos=Body.Data.DevInfo;
                    if(DevInfos==null){
                        /*           toastr.warning("查询失败，该工序没有设备信息，请重新选择");*/
                        $("#DevGD").empty();
                        $("#DevGD").val("");
                        return false;
                    }
                    var DevGD="";
                    for(var i  in DevInfos){
                        DevGD+="<option value='"+DevInfos[i].DevRd+"'>"+DevInfos[i].DevName+"</option>"
                    }
                    $("#DevGD").html(DevGD);
                    DevGD= $("#DevGD").val();
                });
            });
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
                StartSpecRd=$("#startSpec").val();
                var optbusData = {
                    "ExecType": "01",
                    "BusData": JSON.stringify({"SpecVerRd":StartSpecRd })
                };

                request({url:"/Spec/GetSVInfo", data: optbusData},function(Body){
                    var Devices=Body.Data.Device;
                    if(Devices==null){
                        toastr.warning("");
                        return false;
                    }
                    var optbusData = {
                        "ExecType": "00",
                        "BusData": JSON.stringify({"DevGRd":Devices.DevGpRd })
                    };
                    request({url:"/DeviceG/GetDevGInfo", data: optbusData},function(Body){
                        var DevInfos=Body.Data.DevInfo;
                        if(DevInfos==null){
                            /*           toastr.warning("查询失败，该工序没有设备信息，请重新选择");*/
                            $("#DevGD").empty();
                            $("#DevGD").val("");
                            return false;
                        }
                        var DevGD="";
                        for(var i  in DevInfos){
                            DevGD+="<option value='"+DevInfos[i].DevRd+"'>"+DevInfos[i].DevName+"</option>"
                        }
                        $("#DevGD").html(DevGD);
                        DevGD= $("#DevGD").val();
                    });
                });


            }
        }
        /*  if(aadata.length>0&&aadata!=null&&aadata!=""){
              for(var i  in aadata){
                  if(aadata[i].WFVerRd==$(this).find("option:selected").val()){
                      aadata=aadata[j].SpecInfo;
                  }
              }

              $("#startSpec").val(StartSpecRd==""||StartSpecRd==null?$("#startSpec option:first-child").val():StartSpecRd);
              $("#endSpec").val(EndSpecRd==""||EndSpecRd==null?$("#endSpec option:first-child").val():EndSpecRd);
          }*/
    });


    //邮件组
    var OverEmailDistributionRd="";
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
               // c1=data.EmailDisRd;
            },
        }
    };

    $("#expiremailgroup").zc_select(expireemailgroup);

 /*   var gylcfunction=function(){
        $("#gylc").change(function(){
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
                StartSpecRd=$("#startSpec").val();
                var optbusData = {
                    "ExecType": "01",
                    "BusData": JSON.stringify({"SpecVerRd":StartSpecRd })
                };

                request({url:"/Spec/GetSVInfo", data: optbusData},function(Body){
                    var Devices=Body.Data.Device;
                    if(Devices==null){
                        toastr.warning("");
                        return false;
                    }
                    var optbusData = {
                        "ExecType": "00",
                        "BusData": JSON.stringify({"DevGRd":Devices.DevGpRd })
                    };
                    request({url:"/DeviceG/GetDevGInfo", data: optbusData},function(Body){
                        var DevInfos=Body.Data.DevInfo;
                        if(DevInfos==null){
                            /!*           toastr.warning("查询失败，该工序没有设备信息，请重新选择");*!/
                            $("#DevGD").empty();
                            $("#DevGD").val("");
                            return false;
                        }
                        var DevGD="";
                        for(var i  in DevInfos){
                            DevGD+="<option value='"+DevInfos[i].DevRd+"'>"+DevInfos[i].DevName+"</option>"
                        }
                        $("#DevGD").html(DevGD);
                        DevGD= $("#DevGD").val();
                    });
                });
            }
            /!*  if(aadata.length>0&&aadata!=null&&aadata!=""){
                  for(var i  in aadata){
                      if(aadata[i].WFVerRd==$(this).find("option:selected").val()){
                          aadata=aadata[j].SpecInfo;
                      }
                  }

                  $("#startSpec").val(StartSpecRd==""||StartSpecRd==null?$("#startSpec option:first-child").val():StartSpecRd);
                  $("#endSpec").val(EndSpecRd==""||EndSpecRd==null?$("#endSpec option:first-child").val():EndSpecRd);
              }*!/
        });
    }
    gylcfunction();*/

    //邮件内容
    var OverEmailMessageRd="";
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
                OverEmailMessageRd=data.EmailRd
              //  d1=data.EmailRd;
            },
        }
    };

    $("#expiremailcontent").zc_select(expireemailgroupcontent);




    //新增
    $(".cAdd").click(function () {
        //gylcfunction();
        $("#gylc").empty();
        $(".right").show();
        $("#supCode").val("");
        $("#textr").val("");
        $(".checkFloat").val("");
        $("#creatPeople").val("");  //创建人
        $("#creatTime").val("");    //时间
        $("#modifyPeople").val("");  //修改人
        $("#modifyTime").val("");    //时间
        $("#beizhu").val("");   //备注
        $("#DevGD").empty();
        //$("#csxd").val("");
        $("#material").clearseldata("MaVerRd");  //产品
        $("#expiremailgroup").clearseldata("EmailDisRd"); //邮件组
        $("#expiremailcontent").clearseldata("EmailRd"); //邮件内容
        $(this).attr("a","00");
        $("#startSpec").empty();
         MaVerRd="";
         StartSpecRd="";
         DevGD="";
         MaRd="";



    });
    $("#DevGD").change(function() {
        var opt=$(this).val();
        DevGD=opt;

    });
    //保存
    $(".cSave").click(function () {
        if($("#supCode").val().trim()==null||$("#supCode").val().trim()==""){
            toastr.warning("保存失败，名称不能为空");
            return false;
        }
        var WfVerRd=$("#gylc").find("option:selected").val();

        if(WfVerRd==null||""==WfVerRd){
            toastr.warning("保存失败，工艺流程不能为空");
            return false;
        }
        if(MaVerRd==null||MaVerRd==""){
            toastr.warning("保存失败，产品不能为空");
            return false;
        }
        if(StartSpecRd==null||StartSpecRd==""){
            toastr.warning("保存失败，工序不能为空");
            return false;
        }
        if($("#maxtime").val().trim()==null||$("#maxtime").val().trim()==""){
            toastr.warning("保存失败，最小时间不能为空");
            return false;
        }

        if($("#csxd").val().trim()==null||$("#csxd").val().trim()==""){
            toastr.warning("保存失败，超时采取行动不能为空");
            return false;
        }
        //新增保存
        if($(".cAdd").attr("a")=="00"){

            var InitDta={
                "MinTimeWindowName":$("#supCode").val().trim(),  //名称
                "Description":$("#textr").val().trim(),    //描述
                "MaVerRd":MaVerRd,                             //产品
                "SpecVerRd":StartSpecRd,          //工序
                "DevRd":$("#DevGD").val()==null?0:$("#DevGD").val(),
                "WfVerRd":WfVerRd,
                "MinTime":$("#maxtime").val(),  //时间
               /* "PresetEmailDistributionRd":PresetEmailDistributionRd,
                "PresetEmailMessageRd":PresetEmailMessageRd,*/
                "OverTimeAction":$("#csxd option:selected").val(),
                "EmailDistributionRd":$("#expiremailgroup").getseldata().EmailDisRd==null|| $("#expiremailgroup").getseldata().EmailDisRd==""?"": $("#expiremailgroup").getseldata().EmailDisRd,
                "EmailMessageRd":$("#expiremailcontent").getseldata().EmailRd==null||$("#expiremailcontent").getseldata().EmailRd==""?"":$("#expiremailcontent").getseldata().EmailRd,
                "Remark":$("#beizhu").val()
            }

            request({
                url: "/MTV/SaveMinTimedowInfo",
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



            var InitDta={
                "MinTimeWindowRd": nodeid,
                "MinTimeWindowName":$("#supCode").val().trim(),  //名称
                "Description":$("#textr").val().trim(),    //描述
                "MaVerRd":MaVerRd,                             //产品
                "SpecVerRd":StartSpecRd,          //工序
                "DevRd":$("#DevGD").val()==null?0:$("#DevGD").val(),
                "WfVerRd":WfVerRd,
                "MinTime":$("#maxtime").val(),  //时间
                /* "PresetEmailDistributionRd":PresetEmailDistributionRd,
                 "PresetEmailMessageRd":PresetEmailMessageRd,*/
                "OverTimeAction":$("#csxd option:selected").val(),
                "EmailDistributionRd": $("#expiremailgroup").getseldata().EmailDisRd==null|| $("#expiremailgroup").getseldata().EmailDisRd==""?"": $("#expiremailgroup").getseldata().EmailDisRd,
                "EmailMessageRd":$("#expiremailcontent").getseldata().EmailRd==null||$("#expiremailcontent").getseldata().EmailRd==""?"":$("#expiremailcontent").getseldata().EmailRd,
                "Remark":$("#beizhu").val()
            }
            request({
                url: "/MTV/SaveMinTimedowInfo",
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
            "MinTimedowInfoRd":nodeid
        }
        layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                    url: "/MTV/SaveMinTimedowInfo",
                    data: {"ExecType": "01", "busData": JSON.stringify(initData)}
                }, function (Body) {
                    layer.closeAll("dialog");
                    nodeid = null;
                    toastr.success(Body.MsgDes);
                    $(".right").hide();
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
        );

    });
});