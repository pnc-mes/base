/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理JS
 * 创建人：潘俊峰
 * 创建时间：2017-06-27
 * 修改人：
 * 修改时间：
 */
$(function () {
    var flag = null;
    var maRd = null;
    var maInfo = {};
    var maType = "";
    var $replaceMaInfo = $("#replaceMaInfo");
    var $input = $("#version");
    var $select = $("#materVersion");
    var MTRds=null;
    var node=null;
    var te=null;
    var id=null;
    var test=null;
    var f=0;
    var rules = [{
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        }
    ];
    var colNamesArr = [
        {"Caption":"物料ID", "Name":"MaRd", "CType":"text", "Hidden":true},
        {"Caption":"物料代码", "Name":"MaCode", "CType":"text", "Editable":false,"Width":50},
        {"Caption":"物料名称", "Name":"MaName", "CType":"text", "Editable":false},
        {"Caption":"物料描述", "Name":"MaDes", "CType":"text", "Editable":false}
    ];
   <!--下拉框演示-->
    var rootNode=[];
    var WFInfoData=[];
    request({url: "/WF/GetAllWfInfo",data: {"ExecType": "00","PageInfo": "00", "InitData":""}},function(Body){
        var datas = Body.Data;

        for (var i = 0; i < datas.length; i++) {

            <!--下拉框演示-->
            var rootNode1 = {
                "id": datas[i].WfRd,
                "title": datas[i].WfName,
                value:  datas[i].WfVerRd,
                "children": []
            };
            rootNode.push(rootNode1);
        }
    });
    //获取下拉框的value
    $('div.treeSelector').treeSelector(rootNode, WFInfoData, function (e, values) {
        WFInfoData=[];
      //  console.info('onChange', e, values);
        values.forEach(function (s) {

            WFInfoData.push({"WfVerRd":s})
        });

    }, {
        //点选下拉框的值
        checkWithParent: true,
        titleWithParent: true,
        notViewClickParentTitle: true
    });

    var InitData = {
        "FiledList":[
            {
                "FieldName":"MaterialType",
                "FieldOpt":"=",
                "FieldVal":""
            },
            {
                "FieldName":"MaTypeRd",
                "FieldOpt":"=",
                "FieldVal":""
            },
            {
                "FieldName":"IsDef",
                "FieldOpt":"=",
                "FieldVal":"00"
            },
            {
                "FieldName":"MaterialName",
                "FieldOpt":"order by"
            }
        ]
    };
    var tabConfig = {
        data:[],
        colArr:colNamesArr,
        tableId:"list4",
        multiselect:false,
        height:0.28,
        width:0.84
    };
    fullTable(tabConfig);
    var reMaInfos = [];
    var reMaInitData = {
        "FiledList":[
            {
                "FieldName":"ReMaterial",
                "FieldOpt":"=",
                "FieldVal":"00"
            },
            {
                "FieldName":"MaterialName",
                "FieldOpt":"order by"
            }
        ]
    };
    //定义物料特性表格
    var colNamesArr6=[
        {"Caption":"字段名称","Name":"FieldName","CType":"text","Editable":false},
        {"Caption":"定义类型","Name":"FiledType","CType":"text","Editable":false},
        {"Caption":"字段显示名","Name":"DisplayName","CType":"text","Editable":false},
        {"Caption":"字段值","Name":"Val","CType":"text","Editable":false}
    ];
    request({url:"/Material/GetAllMaInfo",async:true, data: {"ExecType": "00","InitData":JSON.stringify(reMaInitData)}},function(Body){
        reMaInfos = Body.Data;
    });
    /*------------------点击事件的处理----------------*/
    //最大最小数量初始化

  //  MinSNum.val("0");

    var str = "";
    //点击类型节点
    var treeID = null;
    var MaVerRd="";
    var maps=[];
    var mapsA=[];
    var onClicks = function (nodeinfo, handle) {

        if($("#ExecType").val()==""||$("#ExecType").val()==null){
            $("#ExecType").val("00");
        }
        treeID = nodeinfo.nodeID;
        id=nodeinfo.nodeID;
        test=nodeinfo.nodeText;

        //alert(treeID);
        //加载物料特性
        $(".formdata").empty();

        te=nodeinfo.nodeID;
        if(te!=""&&te!=null) {
            //物料扩展字段页面加载数据
            var datas = {
                "MTRd": te
            }
            request({
                url: '/Expand/GetDWExpandInfo', async: true,
                data: {"ExecType": "00", "BusData": JSON.stringify(datas)}
            }, function (Body) {
                $(".formdata").empty();
                var data = Body.Data;
                if(data.length>0){
                    $("#MType").attr("style","display:block;");
                }else {
                    $("#MType").attr("style","display:none;");
                }
                maps = [];
                var j = 1;
                data.forEach(function (e) {

                    maps.push(e.FieldName);
                    if (e.FiledType == "00") {
                        $(".formdata").append(e.DisplayName + "  :" + "<input class=' formgroup maBottom' id=" + e.FieldName + " name=" + e.DisplayName + ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        if (e.Val != null) {
                            $("#" + e.FieldName).val(e.Val);
                        } else {
                            $("#" + e.FieldName).val();
                        }


                        if (j % 2 == 0) {
                            $(".formdata").append("<tr>");
                        }
                    }
                    if (e.FiledType == "01") {

                        $(".formdata").append(e.DisplayName + "&ensp;" + " <select class=' formgroup maBottom' id=" + e.FieldName + " name=" + e.DisplayName + " ><option>" + '' + "</option></select>&ensp;&ensp;");

                        if (e.Option != undefined && e.Option != null) {
                            e.Option.forEach(function (s) {
                                var option = $("<option value=" + s.Val + ">" + s.DisplayName + "</option>");
                                $("#" + e.FieldName).append(option);
                            });
                        }
                        $("#" + e.FieldName).val(e.Val);
                        if (j % 2 == 0) {
                            $(".formdata").append("<tr>");
                        }
                    }
                    j++

                });

            });
        }
        //
        re_obj.data = [];
        fullTable(re_obj);
        $input.css("display","inline-block");
        $select.css("display","none");
        $input.val("1");
        $("#_right").show();
        if ("00" == nodeinfo.nodeID) {
            str = "00";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("01" == nodeinfo.nodeID) {
            str = "01";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("02" == nodeinfo.nodeID) {
            str = "02";
            //隐藏工艺流程下面的信息
            $("#a_tab_4").css("display","block");
       //     $("#tab_4").removeClass("active");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","none");
        }
        if ("03" == nodeinfo.nodeID) {
            str = "03";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","block");
        }
        InitData.FiledList[0].FieldVal = str;
        $(".tab-content input").val("");
        //$("#MaCode").prop("readonly",false);
        $("#ExecType").val("00");
        maRd=null;
        if (nodeinfo.isRoot) {  //父节点
            $("#MaDes").val("");
            $("#import").attr("a",JSON.stringify(InitData));
            var MaTypeInitData = {
                "FiledList": [
                    {
                        "FieldName": "MaterialType",
                        "FieldOpt": "=",
                        "FieldVal": str
                    }
                ]
            };
            InitData.FiledList[1].FieldVal = "";
            request({url: '/MaType/GetAllMTInfo',async:true, data: {"ExecType": "00", "InitData": JSON.stringify(MaTypeInitData)}}, function (Body) {
                var treeData = Body.Data.CMTInfo;
                handle.ckAddChild(rules, treeData);
                f=0;

            });
        }else{
            InitData.FiledList[1].FieldVal = nodeinfo.nodeID;
            $("#import").attr("a",JSON.stringify(InitData));
        }
        $("#_right").show();
        $("#list4").html("");
        $(".formdata").empty();
        //物料类别赋值
        $("#MaTypeRd").val(id);
        $("#type").val(test);
        request({id:"list4",url:"/Material/GetAllMaInfo",async:true, data: {"ExecType": "00","InitData":JSON.stringify(InitData)}},function(Body){
            f=0;
            //设置下拉框
            $('div.treeSelector').treeSelector(rootNode, [], function (e, values) {

                //  console.info('onChange', e, values);
                values.forEach(function (s) {

                    WFInfoData.push({"WfVerRd":s})
                });
            }, {
                //点选下拉框的值
                checkWithParent: true,
                titleWithParent: true,
                notViewClickParentTitle: true
            });
            for(var i in Body.Data){
                if(Body.Data[i].MaDes == null)
                    Body.Data[i].MaDes = "";
            }
            tabConfig.data = Body.Data;
            fullTable(tabConfig);
            $("#list4").find("tbody tr").on("click",function () {
                re_obj.data = [];
                fullTable(re_obj);
                $("#type").prop("disabled",false);
               // $("#MaDes").prop("disabled",true);

                $("#materFamily").find("input").prop("disabled",false);
                $("#materFamily").find("img").css("display","inline-block");

                $("#ExecType").val("02");
                maInfo.MaName = $(this).find(".MaName").text();
                maInfo.MaCode = $(this).find(".MaCode").text();
                maInfo.MaDes = $(this).find(".MaDes").text();
                maRd = $(this).find(".MaRd").text();
                var MaInfoData = getMa(maRd);
                $("#MaVerRd").val(MaInfoData.MaVerRd);
                MaVerRd=$("#MaVerRd").val();
                //获取版本列表
                $select.empty();
                var versionList = getMVTree(maRd);
                for(var i in versionList){
                    if(MaInfoData.MaVerRd == versionList[i].MaVerRd) {
                        flag = versionList[i].Version;
                        $select.append("<option selected>" + versionList[i].Version + "</option>");
                    }
                    else
                        $select.append("<option>"+ versionList[i].Version +"</option>");
                }
                $input.css("display","none");
                $select.css("display","inline-block");
                $select.on("change",function () {
                    //通过不同的版本号的切换，然后发送请求，加载不同的信息
                    for(var i in versionList){
                        if($(this).val() == versionList[i].Version){
                            getMV(versionList[i].MaVerRd);
                            $("#MaVerRd").val(versionList[i].MaVerRd);
                            break;
                        }
                    }
                });
            });
        });
       //设置最低库存和最高库存默认信息
        $(".num").val("0")
    };
    //点击物料特性
    $("#MType").click(function () {

    });


    /*----------------------定义控件规则-------------------*/
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
    /*-----------------加载树------------*/
    var loaddata = function () {
        var treedataList = [];
        request({url: '/Material/GetMaTypeInfo',async:true, data: {"ExecType": "00"}}, function (Body) {
            var treeData = Body.Data;
            for (var i = 0; i < treeData.length; i++) {
                var tree = {
                    id: treeData[i].MaType == undefined ? "" : treeData[i].MaType,
                    name: treeData[i].TName == undefined ? "" : treeData[i].TName
                }
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();

    $("#Interval").keyup(function(){
            $(this).val($(this).val().replace(/\D/g,''));
            $(this).val($(this).val().replace(/^(0)/,''));
    });

    // 初始化时,对启动/不起动保质期进行判断
    $("#Interval").prop("readonly",true);
    $("#SUnit").prop("disabled",true);
    $("#Shelflife").on("click",function(){
        $("#Interval").prop("readonly",!$(this).prop("checked"));
        $("#SUnit").prop("disabled",!$(this).prop("checked"));
    });

    // 定义替代料的表格信息
    //替代料
    var re_colNamesArr = [
        {"Caption":"替代料ID", "Name":"ReMaRd", "CType":"text", "Hidden":true},
        {"Caption":"物料ID", "Name":"MaVerRd", "CType":"text", "Hidden":true},
        {"Caption":"物料代码", "Name":"MaCode", "CType":"text", "Editable":false,"Width":100},
        {"Caption":"物料名称", "Name":"MaName", "CType":"text", "Editable":false,"Width":150},
        {"Caption":"物料描述", "Name":"MaDes", "CType":"text", "Editable":false,"Width":150},
        {"Caption":"版本", "Name":"Version", "CType":"text", "Editable":false,"Hidden":true}
    ];
    var re_tableId = "list5";
    var re_obj = {
        data:[],
        colArr:re_colNamesArr,
        tableId:"list5",
        multiselect:true,
        height:0.28,
        width:0.84
    };
    fullTable(re_obj);

    // 定义替代料表格里面的下拉框方法
    var selectMaCode = function(){
        $("#list5").find(".MaCode").on("click", function () {
            $(this).unbind("click");
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for(var i = 0;i < reMaInfos.length;i++){
                if(current_Td_Val == reMaInfos[i].MaCode)
                    str += "<option selected>"+ reMaInfos[i].MaCode +"</option>";
                else
                    str += "<option>"+ reMaInfos[i].MaCode +"</option>";
            };
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border","0px");
            var str_Width = $(this).css("width");
            var index = str_Width.indexOf("px");
            var width = parseInt(str_Width.substring(0,index))*0.95;
            $(this).find("select").css("width",width);
            $(this).find("select").css("height","19px");
            $(this).find("select").css("color","#000000");
            $(this).prev().text(reMaInfos[0].MaVerRd);
            $(this).next().text(reMaInfos[0].MaName);
            $(this).next().next().text(reMaInfos[0].Version);
            $(this).find("select").on("change", function () {
                for (var i = 0; i < reMaInfos.length; i++) {
                    if (reMaInfos[i].MaCode == $(this).find("option:selected").text()) {
                        $(this).parent().prev().text(reMaInfos[i].MaVerRd);
                        $(this).parent().next().text(reMaInfos[i].MaName);
                        $(this).parent().next().next().text(reMaInfos[i].Version);
                    }
                }
            })
        });
    };


    //左边数据显示规则
    var ruleMaVer = [{
        "ctlid": "MaCode",
        "param": "MaCode"
    }, {
        "ctlid": "MaName",
        "param": "MaName"
    }, {
        "ctlid": "Version",
        "param": "Version"
    }, {
        "ctlid": "Interval",
        "param": "Interval"
    }, {
        "ctlid": "MaDes",
        "param": "MaDes"
    }, {
        "ctlid": "MaBarCode",
        "param": "MaBarCode"
    }, {
        "ctlid": "SuName",
        "param": "SuName"
    }, {
        "ctlid": "customerPcode",
        "param": "CusMaCode"
    }, {
        "ctlid": "SuMaCode",
        "param": "SuMaCode"
    }, {
        "ctlid": "MinSNum",
        "param": "MinSNum"
    }, {
        "ctlid": "MaxSNum",
        "param": "MaxSNum"
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
    }, {
        "ctlid": "Brand",
        "param": "Brand"
    }, {
        "ctlid": "OrderNum",
        "param": "OrderNum"
    }];
    //获取序号规则
    var params_sr = {
        "displaymode": "0",
        "binddata": {
            "keyfield": "SNRd",
            "fields": [
                {
                    "caption": "序号ID",
                    "name": "SNRd"
                }, {
                    "caption": "序号名称",
                    "name": "SNName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {

                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RuleName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"RuleName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/SN/GetAllSNInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SNRd": datas[i].SNRd,
                            "SNName": datas[i].SNName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#SRName").zc_select(params_sr);

    //获取单位
    var params_un = {
        "displaymode": "0",
        "binddata": {
            "keyfield": "UnitRd",
            "fields": [
                {
                    "caption": "单位ID",
                    "name": "UnitRd"
                }, {
                    "caption": "单位名称",
                    "name": "UnitName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"UnitName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"UnitName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
               /* var page = {
                    PageIndex: "0",
                    PageSize: 1000
                };*/
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Unit/GetAllUnitInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UnitRd": datas[i].UnitRd,
                            "UnitName": datas[i].UnitName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#UnitName").zc_select(params_un);

    //加载物料标签 即模板
    var wlbq={
        "displaymode": "0",
        "binddata": {
            "keyfield": "PtRd",
            "fields": [
                {
                    "caption": "模板ID",
                    "name": "PtRd"
                }, {
                    "caption": "模板名称",
                    "name": "PtName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"TempName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"TempName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/PrintT/GetAllPtInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PtRd": datas[i].PtRd,
                            "PtName": datas[i].PtName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#wlbq").zc_select(wlbq);
    //加载企业
    //下拉框
    var params = {
        "displaymode": "0",
        "title": "选择企业",
        "binddata": {
            "keyfield": "CpRd",
            "fields": [
                {
                    "caption": "企业id",
                    "name": "CpRd"
                }, {
                    "caption": "企业名称",
                    "name": "CpName"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"CompanyName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "CompanyName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //加载下拉框企业信息
                request({url: "/Cp/GetAllCpInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CpRd": datas[i].CpRd,
                            "CpName": datas[i].CpName,
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
    };

    $("#defaultSelect").zc_select(params);

    //获取工艺流程
    var params_LC = {
        "displaymode": "1",
        "title": "在线物料有效期管控",
        "binddata": {
            "keyfield": "WfRd",
            "fields": [
                {
                    "caption": "工艺流程id",
                    "name": "WfRd"
                }, {
                    "caption": "工艺流程名称",
                    "name": "WfName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"WFName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"WFName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/WF/GetAllWfInfo",data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WfRd": datas[i].WfRd,
                            "WfName": datas[i].WfName,
                        }
                        xldata.push(data);
                        <!--下拉框演示-->
                        var rootNode1 = [{
                            "id": datas[i].WfRd,
                            "title": datas[i].WfName,
                            value:  datas[i].WfVerRd,
                            "children": []
                        }];
                        rootNode.push(rootNode1);
                    }



                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#workflow").zc_select(params_LC);

    //获取在线物料有效期管控
    var params_Time = {
        "displaymode": "1",
        "title": "在线物料有效期管控",
        "binddata": {
            "keyfield": "MaPerionRd",
            "fields": [
                {
                    "caption": "在线物料有效期管控id",
                    "name": "MaPerionRd"
                }, {
                    "caption": "在线物料有效期管控名称",
                    "name": "MaPeriodName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"MaPeriodName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"MaPeriodName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/MaPerion/GetAllMaPerionInfo",data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "MaPerionRd": datas[i].MaPerionRd,
                            "MaPeriodName": datas[i].MaPeriodName,
                        }
                        xldata.push(data);
                    }



                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#wlgkTime").zc_select(params_Time);

    //获取产品族
    var params_Pro = {
        "displaymode": "0",
        "title": "产品族",
        "binddata": {
            "keyfield": "PdfRd",
            "fields": [
                {
                    "caption": "产品族id",
                    "name": "PdfRd"
                }, {
                    "caption": "产品族名称",
                    "name": "PdfName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"FamilyName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"FamilyName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url:"/PF/GetAllPdfInfo", data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PdfRd": datas[i].PdfRd,
                            "PdfName": datas[i].PdfName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#materFamily").zc_select(params_Pro);

    //获取BOM
    var params_BOM = {
        "displaymode": "1",
        "title": "BOM",
        "binddata": {
            "keyfield": "BomRd",
            "fields": [
                {
                    "caption": "BOMid",
                    "name": "BomRd"
                }, {
                    "caption": "BOM名称",
                    "name": "BomName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"BomName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"BomName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/BOM/GetAllBOMInfo", data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "BomRd": datas[i].BomRd,
                            "BomName": datas[i].BomName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500000
                };
                return obj;
            }
        }
    };
    $("#bom").zc_select(params_BOM);

    //替代料
    var repMa = {
        "displaymode": "1",
        "title": "物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "物料id",
                    "name": "MaVerRd"
                }, {
                    "caption": "物料代码",
                    "name": "MaCode"
                }, {
                    "caption": "物料名称",
                    "name": "MaName"
                }, {
                    "caption": "物料描述",
                    "name": "MaDes"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event":{
            "onformatval":function(data){
                return data.MaCode + "-" + data.MaName;
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        }, {
                            "FieldName": "ReMaterial",
                            "FieldOpt": "=",
                            "FieldVal": "00"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "order by",
                        }
                    ]
                };
                var datas = [];
                Materialinfo(InitData,"",datas);
                var obj = {
                    data: datas,
                    showrow: 500
                };
                return obj;
            },
            "onclick": function (res) {
                //填充替代料表格
                var rep_Tab_Tr = {
                    ReMaRd:"",
                    MaVerRd:$replaceMaInfo.getseldata().MaVerRd,
                    MaCode:$replaceMaInfo.getseldata().MaCode,
                    MaName:$replaceMaInfo.getseldata().MaName,
                    MaDes: $replaceMaInfo.getseldata().MaDes,
                    Version: $replaceMaInfo.getseldata().Version
                }
                var tab_list5 = getTableData("list5");
                for(var i in tab_list5){
                    if(tab_list5[i].MaVerRd == rep_Tab_Tr.MaVerRd){
                        toastr.warning(rep_Tab_Tr.MaName + "，已经被添加");
                        return;
                    }
                }
                addSrow("list5",rep_Tab_Tr);
            }
        }
    };
    $replaceMaInfo.zc_select(repMa);

    //制造商
    var SuName = {
        "displaymode": "0",
        "binddata": {
            "keyfield": "SupRd",
            "fields": [
                {
                    "caption": "制造商id",
                    "name": "SupRd"
                }, {
                    "caption": "制造商名称",
                    "name": "SupName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"SupplierName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"SupplierName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                /* var page = {
                     PageIndex: "0",
                     PageSize: 1000
                 };*/
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Supplier/GetAllSupInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SupRd": datas[i].SupRd,
                            "SupName": datas[i].SupName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#SuName").zc_select(SuName);



    //保存
    $(".cSave").click(function () {
        var MaPropertyInfo=[];
        for(var i=0;i<maps.length;i++){
            var Val=$("#"+maps[i]).val();
            Val=Val==undefined?"":Val.trim();
            MaPropertyInfo.push({"FieldName":maps[i],"Val":Val})
        }
        var CpRd =  $("#defaultSelect").getseldata().CpRd;



       /* if(CpRd==null||""==CpRd){
            toastr.warning("企业不能为空");
            return false;
        }*/
        var isstaartb="";
        if($("#maBatch").is(":checked")){
            isstaartb="00";
        }else {
            isstaartb="01";
        }
        var execType = $("#ExecType").val();
        // 获取所有表单数据封装成json对象
        var formData = transfer("printForm");
        /*if($("#distribution").prop("checked")){
            formData["DisRule"] = "00";
            formData["MinPack"] = "0";
        }else{
            formData["DisRule"] = "01";
        }*/

        if(formData["MaName"]== ""){
            $("#MaName").css("border-color", "red");
            $("#MaName").prop("placeholder", "不能为空！");
            return;
        }
        else if(formData["version"]== ""){
            $("#version").css("border-color", "red");
            $("#version").prop("placeholder", "不能为空！");
            return;
        }
        //是否允许设为替代料
        var rm = "01";
        if ($("#ReMaterial").is(":checked")) {
            rm = "00";
        }
        //是否启用保质期
        var sf = "01";
        if ($("#Shelflife").is(":checked")) {
            sf = "00";
        }
        //是否免检
        var mj = "01";
        if ($("#mianjian").prop("checked")) {
            mj = "00";
        }
        /*var pc = "01";
        if ($("#maBatch").prop("checked")) {
            pc = "00";
        }*/
        var SupName=$("#SuName").getseldata().SupName;

        //替代料
        var m_tableData = getTableData("list5");
        if ("00" == execType) { //新增
            if (formData["MaName"] != "" ) {

                for(var i = 0; i < m_tableData.length;i++){
                    delete m_tableData[i].ReMaRd;
                    delete m_tableData[i].MaDes;
                }

                var newData = {
                    "CpRd":CpRd,
                    "MaBarCode":$("#MaBarCode").val().trim(),
                    "IsBatch":isstaartb,
                    "MaPropertyInfo":MaPropertyInfo,
                    "MaCode": formData["MaCode"],
                    "MaName": formData["MaName"],
                    "MaDes":$("#MaDes").val(),
                    "Version": $input.val(),
                    "MaTRd": $("#MaTypeRd").val(),
                    "PtRd":$("#wlbq").getseldata().PtRd == undefined ? "":$("#wlbq").getseldata().PtRd,
                    "WFInfo": WFInfoData,
                    "BOMRd": $("#bom").getseldata().BomRd == undefined ? "":$("#bom").getseldata().BomRd,
                    "SRRd": $("#SRName").getseldata().SNRd,
                    "UnitRd": $("#UnitName").getseldata().UnitRd,
                    "SuName":SupName,
                    "SuMaCode":formData["SuMaCode"],
                    "CusMaCode":formData["customerPcode"],
                    "PDFRd": $("#materFamily").getseldata().PdfRd,
                    "TrayPSpRd": $("#zhanban").getseldata().MPRd_,
                    "BoxPSpRd": $("#xiangbao").getseldata().MPRd,
                    "MaPerionRd": $("#wlgkTime").getseldata().MaPerionRd == undefined ? "":$("#wlgkTime").getseldata().MaPerionRd,
                    "ReMaterial": rm,
                    "Shelflife": sf,
                    "Interval": formData["Interval"],
                    "SUnit": $("#SUnit").val(),
                    "IsExem":mj,
                    "DisRule": $("#minDistribution").prop("checked") ? "01":"00",
                    "ReMaInfo": m_tableData,
                    "Status": formData["Status"],
                    "Remark": $("#beizhu").val(),
                    "MinSNum": formData["MinSNum"],
                    "MaxSNum": formData["MaxSNum"],
                    "Brand":formData["Brand"],
                    "OrderNum":formData["OrderNum"],
                    "MinPQ":$("#MixPQ").val().trim(),
                };
                saveMa(execType, JSON.stringify(newData));
            }
        } else if ("02" == execType) { //编辑
            if (formData["MaName"] != "") {
                for(var i = 0; i < m_tableData.length;i++){
                    delete m_tableData[i].MaDes;
                }

                var newData = {
                    "CpRd":CpRd,
                    "MaPropertyInfo":MaPropertyInfo,
                    "IsBatch":isstaartb,
                    "MaVerRd": MaVerRd,
                    "MaCode":formData["MaCode"] ,
                    "MaName": formData["MaName"],
                    "MaDes":$("#MaDes").val(),
                    "Version": $select.val(),
                    "MaTRd": $("#MaTypeRd").val(),
                    "PtRd":$("#wlbq").getseldata().PtRd == undefined ? "":$("#wlbq").getseldata().PtRd,
                    "WFInfo": WFInfoData,
                    "BOMRd": $("#bom").getseldata().BomRd == undefined ? "":$("#bom").getseldata().BomRd,
                    "SRRd": $("#SRName").getseldata().SNRd,
                    "UnitRd": $("#UnitName").getseldata().UnitRd,
                    "SuName":SupName,
                    "SuMaCode":formData["SuMaCode"],
                    "CusMaCode":formData["customerPcode"],
                    "PDFRd": $("#materFamily").getseldata().PdfRd,
                    "TrayPSpRd": $("#zhanban").getseldata().MPRd_,
                    "BoxPSpRd": $("#xiangbao").getseldata().MPRd,
                    "MaPerionRd": $("#wlgkTime").getseldata().MaPerionRd == undefined ? "":$("#wlgkTime").getseldata().MaPerionRd,
                    "ReMaterial": rm,
                    "Shelflife": sf,
                    "Interval": formData["Interval"],
                    "SUnit": $("#SUnit").val(),
                    "IsExem":mj,
                    "DisRule": $("#minDistribution").prop("checked") ? "01":"00",
                    "ReMaInfo": m_tableData,
                    "Status": formData["Status"],
                    "Remark": $("#beizhu").val(),
                    "MinSNum": formData["MinSNum"],
                    "MaxSNum": formData["MaxSNum"],
                    "Brand":formData["Brand"],
                    "OrderNum":formData["OrderNum"],
                    "MinPQ":$("#MixPQ").val().trim(),
                };
                saveMa("02", JSON.stringify(newData));
            }
        } else if (maRd != null && "03" == execType) { //新增版本
            if (formData["MaCode"] != "" && formData["MaName"] != "" ) {

                for(var i = 0; i < m_tableData.length;i++){
                    delete m_tableData[i].ReMaRd;
                }
                var newData = {
                    "MaRd": maRd,
                    "Version": $input.val(),
                    "PtRd":$("#wlbq").getseldata().PtRd == undefined ? "":$("#wlbq").getseldata().PtRd,
                    "WfRd": $("#workflow").getseldata().WfRd == undefined ? "":$("#workflow").getseldata().WfRd,
                    "BOMRd": $("#bom").getseldata().BomRd == undefined ? "":$("#bom").getseldata().BomRd,
                    "SRRd": $("#SRName").getseldata().SNRd,
                    "UnitRd": $("#UnitName").getseldata().UnitRd,
                    "SuName": /*formData["SuName"]*/SuMaCode==null||SuMaCode==""?"":SuMaCode.split(",")[1],
                    "SuMaCode":formData["SuMaCode"],
                    "CusMaCode":formData["customerPcode"],
                    "PDFRd": $("#materFamily").getseldata().PdfRd,
                    "TrayPSpRd": $("#zhanban").getseldata().MPRd_,
                    "BoxPSpRd": $("#xiangbao").getseldata().MPRd,
                    "ReMaterial": rm,
                    "Shelflife": sf,
                    "Interval": formData["Interval"],
                    "SUnit": $("#SUnit").val(),
                    "IsExem":mj,
                    "DisRule": $("#minDistribution").prop("checked") ? "01":"00",
                    "ReMaInfo": m_tableData,
                    "Status": formData["Status"],
                    "Remark": $("#beizhu").val(),
                    "MinSNum": formData["MinSNum"],
                    "MaxSNum": formData["MaxSNum"],
                    "Brand":formData["Brand"],
                    "OrderNum":formData["OrderNum"],
                    "MinPQ":$("#MixPQ").val().trim(),
                };
                saveMa(execType, JSON.stringify(newData));
            }
        }
    });


    //删除
    $("#remove").click(function () {
        if (flag != $select.val() && maRd != null) {
            layer.confirm('', {
                type: 0,
                btn: ['确认', '取消'], //按钮
                content: '确认删除吗？',
                icon: "fa-check-circle"
            }, function () {
                $("#ExecType").val("01");
                saveMa("01", "{\"MaVerRd\":" + $("#MaVerRd").val() + "}");
                layer.closeAll("dialog");
            }, function () {

            });
        } else if (flag == $select.val() && maRd != null) {
            layer.confirm('', {
                type: 0,
                btn: ['确认', '取消'], //按钮
                content: '该物料版本是默认版本,如果删除，此物料下面所有版本信息将全部被删除,您确认这样做吗？',
                icon: "fa-check-circle"
            }, function () {
                $("#ExecType").val("01");
                saveMa("01", "{\"MaVerRd\":" + $("#MaVerRd").val() + "}");
                layer.closeAll("dialog");
            }, function () {

            });
        }else {
            toastr.warning("请选择右侧的物料信息再进行删除!");
        }
    });

    //新增替代料
    /*$(".add2").click(function () {
        addErow(re_tableId);
        selectMaCode();
    });*/

    //删除替代料
    $(".del2").click(function () {
        derow(re_tableId);
    });

    //获取物料版本列表信息
    function getMVTree(MaRd) {
        var data;
        request({url:"/Material/GetMVTreeInfo", data: {"ExecType": "00", "BusData": "{\"MaRd\":" + MaRd + "}"}},function(Body){
            data = Body.Data;
        });
        return data;
    }

    //获取物料信息
    function getMa(MaRd) {


        var data;
        //parentTreeID = MaRd;
        request({url:"/Material/GetMaInfo", data: {"ExecType": "00", "BusData": "{\"MaRd\":" + MaRd + "}"}},function(Body){
            f=0;
            data = Body.Data;
            loadData(Body);
            if(data.DSource == "00"){
                isAllDisable(true);
            }else{
                isAllDisable(false);
            }
            var arr=[];
            WFInfoData=[];
            data.WFInfo.forEach(function (aa) {
                arr.push(aa.WfVerRd);
                WFInfoData.push({'WfVerRd':aa.WfVerRd});
            });
            $('div.treeSelector').treeSelector(rootNode,arr,function (e, values) {
                WFInfoData=[];
                //  console.info('onChange', e, values);
                values.forEach(function (s) {
                    WFInfoData.push({"WfVerRd":s})
                });
            }, {

                //点选下拉框的值
                checkWithParent: true,
                titleWithParent: true,
                notViewClickParentTitle: true,
            });
        });
        return data;
    }


    //获取物料版本信息
    function getMV(MaVerRd) {
        request({url:"/Material/GetMaInfo", data: {"ExecType": "01", "BusData": "{\"MaVerRd\":" + MaVerRd + "}"}},function(Body){
            loadData(Body);
            if(Body.Data.DSource == "00"){
                isAllDisable(true);
            }else{
                isAllDisable(false);
            }
        });
    }

    //加载数据
    function loadData(Body) {
       //$("#MaCode").attr("readonly",true);
        //$("#MaDes").prop("disabled",true);
        var matype = Body.Data.MaType.MaTRd;
        if ("00" == matype) {
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("01" == matype) {
            str = "01";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("02" == matype) {
            //隐藏工艺流程下面的信息
            $("#a_tab_4").css("display","block");
           // $("#tab_4").removeClass("active");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("03" == matype) {
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }

        fillform("printForm", ruleMaVer, Body.Data);
        $("#MixPQ").val(Body.Data.MinPQ);
        var data=Body.Data.MaPropertyInfo;
        if(data.length>0){
            $("#MType").attr("style","display:block;");
        }else {
            $("#MType").attr("style","display:none;");
        }
        var i=1;
        $(".formdata").empty();
        maps=[];

        data.forEach(function (e) {

            maps.push(e.FieldName);
            if(e.FiledType=="00"){
                $(".formdata").append(e.DisplayName+"&ensp;"+"<input  class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
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

                $(".formdata").append(e.DisplayName+"&ensp;"+" <select  class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+" ><option>"+''+"</option></select>&ensp;&ensp;");

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
        /* if(Body.Data.MaPropertyInfo!=null){
            $("#chicun").val(Body.Data.MaPropertyInfo.Size);
            $("#caizhi").val(Body.Data.MaPropertyInfo.Material);
            $("#guige").val(Body.Data.MaPropertyInfo.Norm);
            $("#xinghao").val(Body.Data.MaPropertyInfo.Model);
        }*/

        if(Body.Data.DisRule == "00"){
            //$("#distribution").prop("checked",true);
            $("#minDistribution").prop("checked",false);
            $("#zxbzsl").hide();
            //$("#MinPack").prop("disabled",true);
        }
        else if(Body.Data.DisRule == "01") {
            $("#zxbzsl").show();
            //$("#distribution").prop("checked", false);
            $("#minDistribution").prop("checked", true);
            //$("#MinPack").prop("disabled",false);
        }

        //$("#MinPack").val(Body.Data.MinPack);

        if(Body.Data.PtInfo!=null){
            $("#wlbq").showData({
                id:Body.Data.PtInfo == null ? "":Body.Data.PtInfo.PtRd,
                name:Body.Data.PtInfo == null ? "":Body.Data.PtInfo.PtName,
                keyfield:"PtRd",
                fields:[
                    {"name":"PtRd"},
                    {"name":"PtName"}
                ]
            });
        }
        if(Body.Data.SRInfo!=null){
            $("#SRName").showData({
                id:Body.Data.SRInfo == null ? "":Body.Data.SRInfo.SRRd,
                name:Body.Data.SRInfo == null ? "":Body.Data.SRInfo.SRName,
                keyfield:"SNRd",
                fields:[
                    {"name":"SNRd"},
                    {"name":"SNName"}
                ]
            });
        }


        if(Body.Data.UnitInfo!= null){
            $("#UnitName").showData({
                id:Body.Data.UnitInfo == null ? "":Body.Data.UnitInfo.UnitRd,
                name:Body.Data.UnitInfo == null ? "":Body.Data.UnitInfo.UnitName,
                keyfield:"UnitRd",
                fields:[
                    {"name":"UnitRd"},
                    {"name":"UnitName"}
                ]
            });
        }
        if(Body.Data.MaType!= null){
            maType = Body.Data.MaType.MaTName;
            $("#type").val(Body.Data.MaType == null ? "":Body.Data.MaType.MaTName);
            $("#MaTypeRd").val(Body.Data.MaType == null ? "":Body.Data.MaType.MaTRd);

            /*$("#MaType").showData({
                id:Body.Data.MaType == null ? "":Body.Data.MaType.MaTRd,
                name:Body.Data.MaType == null ? "":Body.Data.MaType.MaTName,
                keyfield:"MTRd",
                fields:[
                    {"name":"MTRd"},
                    {"name":"MTName"}
                ]
            });*/
        }
        $("#Status option").each(function(){
            if(Body.Data.Status === $(this).val()){
                $(this).attr("selected","selected");
            }else{
                $(this).attr("selected",false);
            }
        })
        $("#materFamily").showData({
            id:Body.Data.PDFInfo == null? "":Body.Data.PDFInfo.PDFRd,
            name:Body.Data.PDFInfo == null? "":Body.Data.PDFInfo.PDFName,
            keyfield:"PdfRd",
            fields:[
                {"name":"PdfRd"},
                {"name":"PdfName"}
            ]
        });
        //alert("1555");
        $("#workflow").showData({
            id:Body.Data.WFInfo == null? "":Body.Data.WFInfo.WfRd,
            name:Body.Data.WFInfo == null? "":Body.Data.WFInfo.WFName,
            keyfield:"WfRd",
            fields:[
                {"name":"WfRd"},
                {"name":"WfName"}
            ]
        });

        $("#bom").showData({
            id:Body.Data.BOMInfo == null? "":Body.Data.BOMInfo.BOMRd,
            name:Body.Data.BOMInfo == null? "":Body.Data.BOMInfo.BOMName,
            keyfield:"BomRd",
            fields:[
                {"name":"BomRd"},
                {"name":"BomName"}
            ]
        });
        //BOM层级
        if(Body.Data.BOMInfo == null){
            $("#bomCJ").css("display","none");
        }else{
            $("#bomCJ").css("display","inline-block");
        }
       //物料管控时间

        $("#wlgkTime").showData({
            id:Body.Data.OnLineMaPeriodInfo == null? "":Body.Data.OnLineMaPeriodInfo.MaPerionRd,
            name:Body.Data.OnLineMaPeriodInfo == null? "":Body.Data.OnLineMaPeriodInfo.MaPeriodName,
            keyfield:"MaPerionRd",
            fields:[
                {"name":"MaPerionRd"},
                {"name":"MaPeriodName"}
            ]
        });
        $("#zhanban").showData({
            id:Body.Data.TrayPackInfo == null? "":Body.Data.TrayPackInfo.PackSpRd,
            name:Body.Data.TrayPackInfo == null? "":Body.Data.TrayPackInfo.PackName,
            keyfield:"MPRd_",
            fields:[
                {"name":"MPRd_"},
                {"name":"MPName_"}
            ]
        });
        $("#xiangbao").showData({
            id:Body.Data.BoxPackInfo == null? "":Body.Data.BoxPackInfo.PackSpRd,
            name:Body.Data.BoxPackInfo == null? "":Body.Data.BoxPackInfo.PackName,
            keyfield:"MPRd",
            fields:[
                {"name":"MPRd"},
                {"name":"MPName"}
            ]
        });
        //是否允许设为替代料
        if ("00" == Body.Data.ReMaterial) {
            $("#ReMaterial").prop("checked", true);
        }
        else {
            $("#ReMaterial").prop("checked", false);
        }

        //启用保质期
        if ("00" == Body.Data.Shelflife) {
            $("#Shelflife").prop("checked", true);
            $("#Interval").prop("readonly",false);
            $("#SUnit").prop("disabled",false);
        }
        else {
            $("#Shelflife").prop("checked", false);
            $("#Interval").prop("readonly",true);
            $("#SUnit").prop("disabled",true);
        }

        //年月周日
        $("#SUnit option").each(function () {
            if ($(this).val() == Body.Data.SUnit) {
                $(this).prop("selected", true);
                return;
            }
        });

        //免检
        if ("00" == Body.Data.IsExem) {
            $("#mianjian").prop("checked", true);
        }
        else {
            $("#mianjian").prop("checked", false);
        }

        /*if ("00" == Body.Data.ReMaterial) {
            $("#maBatch").prop("checked", true);
        }
        else {
            $("#maBatch").prop("checked", false);
        }*/
        if(Body.Data.SuName!=null&&Body.Data.SuName!=""){
            $("#SuName").showData({
                id:Body.Data.SuName == null? "":Body.Data.SuName,
                name:Body.Data.SuName == null? "":Body.Data.SuName,
                keyfield:"SupRd",
                fields:[
                    {"name":"SupRd"},
                    {"name":"SupName"}
                ]
            });
        }
        $("#defaultSelect").showData({
            id:Body.Data.CPInfo == null? "":Body.Data.CPInfo.CpRd,
            name:Body.Data.CPInfo == null? "":Body.Data.CPInfo.CpName,
            keyfield:"CpRd",
            fields:[
                {"name":"CpRd"},
                {"name":"CpName"}
            ]
        });
        if("00"==Body.Data.MaterialType||"01"==Body.Data.MaterialType){
            //if("00"==Body.Data.IsBatch){
                $("#maBatch").prop("checked", true);
                $("#maBatch").attr("disabled",true);
            //}
            //$("#MaBarCode").val("");
            $("#MaBarCode").attr("readonly",true);
            $("#dd3").css("display", "");
        }
        if("02"==Body.Data.MaterialType||"03"==Body.Data.MaterialType){
            if("00"==Body.Data.IsBatch){
                $("#maBatch").prop("checked", true);
                //$("#maBatch").attr("disabled",true);
                //$("#MaBarCode").val("");
                $("#MaBarCode").attr("readonly",true);
                $("#dd3").css("display", "");
            }
            if("01"==Body.Data.IsBatch){
                $("#maBatch").prop("checked", false);
                $("#MaBarCode").val(Body.Data.MaBarCode);
                $("#MaBarCode").attr("readonly",false);
                $("#dd3").css("display", "none");
            }
            $("#maBatch").attr("disabled", false);
        }

        //
        var reMaTableData = Body.Data.ReMaInfo;
        // 填充最下面替代料的表格
        re_obj.data = reMaTableData;

        fullTable(re_obj);
        //selectMaCode();
    }

    //保存物料信息
    function saveMa(ExecType, BusData) {

        request({url:"/Material/SaveMaInfo",async:true, data: {"ExecType": ExecType, "BusData": BusData}},function(Body){
            if($("#ExecType").val() != "00"){
                toastr.success(Body.MsgDes);
            }

            if($("#ExecType").val() == "01"){
                $("#_right").hide();
                clearForm("printForm");
                $("#ExecType").clearseldata();
                re_obj.data = [];
                fullTable(re_obj);
            }

            $("#list4").html("");
            request({id:"list4",url:"/Material/GetAllMaInfo",async:true, data: {"ExecType": "00","InitData":JSON.stringify(InitData)}},function(Body){
                toastr.success("成功");

                for(var i in Body.Data){
                    if(Body.Data[i].MaDes == null)
                        Body.Data[i].MaDes = "";
                }
                tabConfig.data = Body.Data;
                fullTable(tabConfig);
                $("#list4").find("tbody tr").on("click",function () {
                    re_obj.data = [];
                    fullTable(re_obj);
                    //$("#MaCode").prop("readonly",false);
                    $("#MaName").prop("readonly",false);
                   // $("#MaDes").prop("disabled",true);
                    $("#type").prop("disabled",false);

                    $("#materFamily").find("input").prop("disabled",false);
                    $("#materFamily").find("img").css("display","inline-block");

                    $("#ExecType").val("02");
                    maInfo.MaName = $(this).find(".MaName").text();
                    maInfo.MaCode = $(this).find(".MaCode").text();
                    maInfo.MaDes = $(this).find(".MaDes").text();
                    maRd = $(this).find(".MaRd").text();
                    var MaInfoData = getMa(maRd);

                    $("#MaVerRd").val(MaInfoData.MaVerRd);
                    //获取版本列表
                    $select.empty();
                    var versionList = getMVTree(maRd);
                    for(var i in versionList){
                        if(MaInfoData.MaVerRd == versionList[i].MaVerRd) {
                            flag = versionList[i].Version;
                            $select.append("<option selected>" + versionList[i].Version + "</option>");
                        }
                        else
                            $select.append("<option>"+ versionList[i].Version +"</option>");
                    }
                    $input.css("display","none");
                    $select.css("display","inline-block");
                    $select.on("change",function () {
                        //通过不同的版本号的切换，然后发送请求，加载不同的信息
                        for(var i in versionList){
                            if($(this).val() == versionList[i].Version){
                                getMV(versionList[i].MaVerRd);
                                $("#MaVerRd").val(versionList[i].MaVerRd);
                                break;
                            }
                        }
                    });
                });
            });


            //tabConfig.data = [];
            //fullTable(tabConfig);
            //$("#ExecType").val("");
            str = "";
            maRd = null;
            InitData.FiledList[1].FieldVal = "";
          //  toastr.success("物料信息新增成功,物料代码{"+Body.Data.MaCode+"}");
            //刷新树
            loaddata();
            $("#hidden3").attr("editid", Body.Data.MaRd);
            $("#hidden1").attr("editid", Body.Data.MaVerRd);
            maRd=$("#hidden3").attr("editid");
            MaVerRd=$("#hidden1").attr("editid");
            $("#hidden2").attr("editcode", Body.Data.MaCode);

            getMV(MaVerRd);
            var versionList = getMVTree(maRd);
            for(var i in versionList){
                if(MaVerRd == versionList[i].MaVerRd) {
                    flag = versionList[i].Version;
                    $select.append("<option selected>" + versionList[i].Version + "</option>");
                }
                else
                    $select.append("<option>"+ versionList[i].Version +"</option>");
            }
            $input.css("display","none");
            $select.css("display","inline-block");
            $select.on("change",function () {
                //通过不同的版本号的切换，然后发送请求，加载不同的信息
                for(var i in versionList){
                    if($(this).val() == versionList[i].Version){
                        getMV(versionList[i].MaVerRd);
                        $("#MaVerRd").val(versionList[i].MaVerRd);
                        MaVerRd=$("#MaVerRd").val();
                        break;
                    }
                }
            });
            $("#ExecType").val("02");
        });
    }

    /*$("#distribution").on("click",function () {
        $("#MinPack").attr("disabled",true);
        $("#MinPack").val("");
    });*/

    //新增物料
    $("#addRoot").on("click",function () {
        //最大最小数量初始化

       // document.getElementById()
        $("#MaCode").val("00");
        //初始化工艺流程
        WFInfoData=[];
        $("#MaDes").val("");
        $("#a_tab_1").addClass("active").siblings().removeClass("active");
        $("#tab_1").addClass("active").siblings().removeClass("active");
        f=1;
        $("#MType").attr("style","display:none;");
        $(".formdata").empty();
        if(te==null){
            var tes='00';
            request({url: '/Material/GetMaTypeInfo',async:true, data: {"ExecType": "00"}}, function (Body) {
                var treeData = Body.Data;

                $("#MaTypeRd").val( treeData[0].MaType == undefined ? "" : treeData[0].MaType);
                $("#type").val(treeData[0].TName == undefined ? "" : treeData[0].TName);


            });
            //物料扩展字段页面加载数据


            if(tes!=""&&tes!=null){
                var  datas={
                    "MTRd":tes
                }

                request({
                    url:'/Expand/GetDWExpandInfo',async:true,
                    data:{"ExecType":"00","BusData":JSON.stringify(datas)}},function (Body) {
                    $(".formdata").empty();
                    var data=Body.Data;
                    if(data.length>0){
                        $("#MType").attr("style","display:block;");
                    }else {
                        $("#MType").attr("style","display:none;");
                    }
                    maps=[];
                    var j=1;
                    data.forEach(function (e) {

                        maps.push(e.FieldName);
                        if(e.FiledType=="00"){
                            $(".formdata").append(e.DisplayName+"&ensp;"+"<input class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                            if(e.Val!=null){
                                $("#"+e.FieldName).val(e.Val);
                            }else {
                                $("#"+e.FieldName).val();
                            }


                            if(j%2==0){
                                $(".formdata").append("<tr>");
                            }
                        }
                        if(e.FiledType=="01"){

                            $(".formdata").append(e.DisplayName+"&ensp;"+" <select class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+" ><option>"+''+"</option></select>&ensp;&ensp;");

                            if(e.Option!=undefined&&e.Option!=null){
                                e.Option.forEach(function (s) {
                                    var option=$("<option value="+s.Val+">"+s.DisplayName+"</option>");
                                    $("#"+e.FieldName).append(option);
                                });
                            }
                            $("#"+e.FieldName).val(e.Val);
                            if(j%2==0){
                                $(".formdata").append("<tr>");
                            }
                        }
                        j++

                    });

                });

            }

        }

        //下拉框的value初始化
        $('div.treeSelector').treeSelector(rootNode, [], function (e, values) {
            WFInfoData=[];
            //  console.info('onChange', e, values);
            values.forEach(function (s) {

                WFInfoData.push({"WfVerRd":s})
            });

        }, {
            //点选下拉框的值
            checkWithParent: true,
            titleWithParent: true,
            notViewClickParentTitle: true
        });


        $("#MixPQ").val("");
        $("#SuName").clearseldata("SupRd");
        $("#wlbq").clearseldata("PtRd");
        $("#maBatch").prop("checked",false);
        $("#maBatch").attr("disabled",false);
        $("#MaBarCode").val("");
        $("#MaBarCode").attr("readonly",false);
        $("#dd3").css("display", "none");
        //$("#MaDes").attr("disabled",true);
        $("#_right").show();
        //$("#MaCode").attr("readonly",false);
        $("#ExecType").val("00");
        clearForm("printForm");
        $("#type").prop("disabled",false);
        $(this).clearseldata();
        re_obj.data = [];
        fullTable(re_obj);
        $input.css("display","inline-block");
        $select.css("display","none");
        $(".version").val("1");
        //设置最低库存和最高库存默认信息
        $(".num").val("0");
        isAllDisable(false);
        if(te!=""&&te!=null){
            $("#MaTypeRd").val(id);
            $("#type").val(test);
            var  datas={
                "MTRd":id
            }

            request({
                url:'/Expand/GetDWExpandInfo',async:true,
                data:{"ExecType":"00","BusData":JSON.stringify(datas)}},function (Body) {
                $(".formdata").empty();
                var data=Body.Data;
                if(data.length>0){
                    $("#MType").attr("style","display:block;");
                }else {
                    $("#MType").attr("style","display:none;");
                }
                maps=[];
                var j=1;
                data.forEach(function (e) {

                    maps.push(e.FieldName);
                    if(e.FiledType=="00"){
                        $(".formdata").append(e.DisplayName+"&ensp;"+"<input class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        if(e.Val!=null){
                            $("#"+e.FieldName).val(e.Val);
                        }else {
                            $("#"+e.FieldName).val();
                        }


                        if(j%2==0){
                            $(".formdata").append("<tr>");
                        }
                    }
                    if(e.FiledType=="01"){

                        $(".formdata").append(e.DisplayName+"&ensp;"+" <select class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+" ><option>"+''+"</option></select>&ensp;&ensp;");

                        if(e.Option!=undefined&&e.Option!=null){
                            e.Option.forEach(function (s) {
                                var option=$("<option value="+s.Val+">"+s.DisplayName+"</option>");
                                $("#"+e.FieldName).append(option);
                            });
                        }
                        $("#"+e.FieldName).val(e.Val);
                        if(j%2==0){
                            $(".formdata").append("<tr>");
                        }
                    }
                    j++

                });

            });
        }
    });
    //新增版本
    $("#addChild").on("click",function () {
        //初始化工艺流程
        WFInfoData=[];
        $("#wlbq").clearseldata("PtRd");
        $("#MixPQ").val("");
        $("#maBatch").prop("checked",false);
        $("#maBatch").attr("disabled",false);
        $("#MaBarCode").val("");
        $("#MaBarCode").attr("disabled",false);
        if(maRd == null){
            toastr.warning("请选择物料再进行新增版本");
            return;
        }
        //$(this).app
        $("#ExecType").val("03");
        //clearForm("printForm");
        $("#materFamily").find("input").prop("disabled",true);
        $("#materFamily").find("img").css("display","none");
        $("#UnitName").clearseldata("UnitRd");
        $("#SRName").clearseldata("SNRd");
        $("#workflow").clearseldata("WfRd");
        $("#bom").clearseldata("BomRd");
        $("#wlgkTime").clearseldata("wlgkTime");

      //  $("#MinSNum").val("");
     //   $("#MaxSNum").val("");
        $("#customerPcode").val("");
        //$("#SuName").val("");
        $("#SuName").clearseldata("SupRd");
        $("#SuMaCode").val("");
        $("#Interval").val("");
        $("#Shelflife").prop("checked",false);
        $("#ReMaterial").prop("checked",false);
        $("#minDistribution").prop("checked",false);

        $input.css("display","inline-block").val("");
        $select.css("display","none");
        re_obj.data = [];
        fullTable(re_obj);
        $("#Interval").prop("readonly",true);
        $("#SUnit").prop("disabled",true);
        //$("#distribution").prop("checked",true);
        //$("#MaCode").prop("readonly",true);
        $("#MaName").prop("readonly",true);
        //$("#MaDes").prop("readonly",true);
        $("#type").prop("disabled",true);
        /*$("#MaType").find("input").val(maType).prop("disabled",true);
        $("#MaType").find("img").prop("disabled",true)*/
    });

    var clic = function (nodeinfo, handle) {
        App.unblockUI("body");
        //if("00" != nodeinfo.nodeID && "01" != nodeinfo.nodeID && "02" != nodeinfo.nodeID && "03" != nodeinfo.nodeID){
            $("#MaTypeRd").val(nodeinfo.nodeID);
            $("#type").val(nodeinfo.nodeText);

       // if(f==1){
           // alert("5555")
            $(".formdata").empty();
            te=nodeinfo.nodeID;
            if(te!=""&&te!=null){
                //物料扩展字段页面加载数据
                var  datas={
                    "MTRd":te
                }
                request({
                    url:'/Expand/GetDWExpandInfo',async:true,
                    data:{"ExecType":"00","BusData":JSON.stringify(datas)}},function (Body) {
                    $(".formdata").empty();
                    var data=Body.Data;
                    if(data.length>0){
                        $("#MType").attr("style","display:block;");
                    }else {
                        $("#MType").attr("style","display:none;");
                    }
                    maps=[];
                    var j=1;
                    data.forEach(function (e) {

                        maps.push(e.FieldName);
                        if(e.FiledType=="00"){
                            $(".formdata").append(e.DisplayName+"&ensp;"+"<input class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                            if(e.Val!=null){
                                $("#"+e.FieldName).val(e.Val);
                            }else {
                                $("#"+e.FieldName).val();
                            }


                            if(j%2==0){
                                $(".formdata").append("<tr>");
                            }
                        }
                        if(e.FiledType=="01"){

                            $(".formdata").append(e.DisplayName+"&ensp;"+" <select class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+" ><option>"+''+"</option></select>&ensp;&ensp;");

                            if(e.Option!=undefined&&e.Option!=null){
                                e.Option.forEach(function (s) {
                                    var option=$("<option value="+s.Val+">"+s.DisplayName+"</option>");
                                    $("#"+e.FieldName).append(option);
                                });
                            }
                            $("#"+e.FieldName).val(e.Val);
                            if(j%2==0){
                                $(".formdata").append("<tr>");
                            }
                        }
                        j++

                    });

                });
            /*}else {
                toastr.warning("新增物料信息,需先选择物料类型才能设置物料特性!");
            }*/

        }

        //}
        if ("00" == nodeinfo.nodeID) {
            str = "00";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("01" == nodeinfo.nodeID) {
            str = "01";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("02" == nodeinfo.nodeID) {
            str = "02";
            //隐藏工艺流程下面的信息
            $("#a_tab_4").css("display","none");
            $("#tab_4").removeClass("active");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","none");
        }
        if ("03" == nodeinfo.nodeID) {
            str = "03";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","block");
        }
        InitData.FiledList[0].FieldVal = str;
        if (nodeinfo.isRoot) {  //父节点
            var MaTypeInitData = {
                "FiledList": [
                    {
                        "FieldName": "MaterialType",
                        "FieldOpt": "=",
                        "FieldVal": str
                    }
                ]
            };
            request({url: '/MaType/GetAllMTInfo',async:true, data: {"ExecType": "00", "InitData": JSON.stringify(MaTypeInitData)}}, function (Body) {
                var treeData = Body.Data.CMTInfo;
                handle.ckAddChild(rules, treeData);
                //123456
            });
        }
        if(str == "00" || str == "01"){
            $("#maBatch").prop("checked", true);
            $("#maBatch").attr("disabled",true);
            //$("#MaBarCode").val("");
            $("#MaBarCode").attr("readonly",true);
            $("#dd3").css("display", "");
        }else{
            if($("#maBatch").is(":checked")){
                //$("#MaBarCode").val("");
                $("#MaBarCode").attr("readonly",true);
            }else{
                //$("#MaBarCode").val("");
                $("#MaBarCode").attr("readonly",false);
            }
            $("#maBatch").attr("disabled", false);
        }
    };
    /*----------------------定义控件规则-------------------*/
    var tCon = {
        id: "t",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        event: {
            onClick: clic

        }
    };
    var list = [];
    var f = true;
    var a = function () {
        var treedataList = [];
        request({url: '/Material/GetMaTypeInfo', data: {"ExecType": "00"}}, function (Body) {
            var treeData = Body.Data;
            for (var i = 0; i < treeData.length; i++) {
                var tree = {
                    id: treeData[i].MaType == undefined ? "" : treeData[i].MaType,
                    name: treeData[i].TName == undefined ? "" : treeData[i].TName
                }
                treedataList.push(tree);
            }
            tCon.data.source = treedataList;
            if(f){
                list = treedataList;
                f = false;
            }
            $.JstreeEx.init(tCon);//先调用后加载
        });
    };
    a();
    $("#t").on("dblclick","a",function () {
        var a_Arr = $(this).attr("id").split("_");
        var id = a_Arr[a_Arr.length - 2];
        //if("00" != id && "01" != id && "02" != id && "03" != id){
            ff = true;
            $("#t").css("display","none");
        //}
    });
    $("#t").on("click",function (e) {
        e.stopPropagation();
    });
    var ff = true;
    $("#type").on({
        "focus": function () {
            if(ff){
                $("#t").css("display", "block");
                tCon.data.source = list;
                $.JstreeEx.init(tCon);
                ff = false;
            }
        },
        "click":function (e) {
            e.stopPropagation();
            if(ff) {
                $("#t").css("display", "block");
                tCon.data.source = list;
                $.JstreeEx.init(tCon);
            }
        },
        "keydown":function (e) {
            if(e.keyCode == 8){
                $(this).val("");
                $("#MaTypeRd").val("");
            }else{
                //$(this).val(inVal);
            }
        }

    })
    $("body").on("click",function () {
        ff = true;
        $("#t").css("display","none");
    });
    var params4 = {
        "displaymode": "0",
        "title": "栈板类型",
        "binddata": {
            "keyfield": "MPRd_",
            "fields": [
                {
                    "caption": "栈板类型id",
                    "name": "MPRd_"
                }, {
                    "caption": "栈板类型名称",
                    "name": "MPName_"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "PackName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },
                        {
                            "FieldName": "PackType",
                            "FieldOpt": "=",
                            "FieldVal": "00"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/PackSp/GetAllMPKInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "MPRd_": datas[i].MPRd,
                            "MPName_": datas[i].MPName
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
    $("#zhanban").zc_select(params4);

    var params5 = {
        "displaymode": "0",
        "title": "箱包类型",
        "binddata": {
            "keyfield": "MPRd",
            "fields": [
                {
                    "caption": "箱包类型id",
                    "name": "MPRd"
                }, {
                    "caption": "箱包类型名称",
                    "name": "MPName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "PackName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },
                        {
                            "FieldName": "PackType",
                            "FieldOpt": "=",
                            "FieldVal": "01"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/PackSp/GetAllMPKInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "MPRd": datas[i].MPRd,
                            "MPName": datas[i].MPName
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
    $("#xiangbao").zc_select(params5);

    //BOM层级
    $("#bomCJ").on("click",function () {

        storage.setItem("MaRd",maRd);
        parent.addTabs({id:'2020',title: 'BOM层级',close: true,url: getBasePath()+'/Material/Bomlevel'});
    });


    $("#import").on("click",function () {
        //弹窗
        layer.open({
            type: 2,
            title:'物料导入',
            shadeClose: true,
            area: ['60%', '50%'],
            content:getBasePath()+"/Material/ImportMaterial"
        });
    });


    //筛选物料表格和类型
    var params = [{
        "caption": "物料类型", //显示名
        "name": "MaterialType",//字段名
        "valtype": "01",//字段值类 00#文本 01#下拉框 02#日期段
        "data": "\"\":\"无\"|\"00\":\"产成品\"|\"01\":\"半成品\"|\"02\":\"原料\"|\"03\":\"其他\""
    },{
        "caption": "物料代码",
        "name": "MaterialCode",
        "valtype": "00"
    }, {
        "caption": "物料名称",
        "name": "MaterialName",
        "valtype": "00"
    }];

    var event = {
        onsure: function (result) {

            var datasources={
                "MaType":result[0].MaterialType,
                "MaCode":result[1].MaterialCode,
                "MaName":result[2].MaterialName
            };
            var FiledLists={};
            if(datasources.MaType==""){
                FiledLists= {
                    "FiledList": [
                        {
                            "FieldName": "MaterialCode",
                            "FieldOpt": "like",
                            "FieldVal": ""+datasources.MaCode+"%"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "like",
                            "FieldVal":""+datasources.MaName+"%"
                        },
                        {
                            "FieldName":"IsDef",
                            "FieldOpt":"=",
                            "FieldVal":"00"
                        },
                        {
                            "FieldName":"MaterialName",
                            "FieldOpt":"order by"
                        }
                    ]
                }
            }else {
                FiledLists= {
                    "FiledList": [
                        {
                            "FieldName": "MaterialType",
                            "FieldOpt": "=",
                            "FieldVal": datasources.MaType
                        },
                        {
                            "FieldName": "MaterialCode",
                            "FieldOpt": "like",
                            "FieldVal": ""+datasources.MaCode+"%"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "like",
                            "FieldVal": ""+datasources.MaName+"%"
                        },
                        {
                            "FieldName":"IsDef",
                            "FieldOpt":"=",
                            "FieldVal":"00"
                        },
                        {
                            "FieldName":"MaterialName",
                            "FieldOpt":"order by"
                        }
                    ]
                }
            }

            if(datasources.MaType=="" && datasources.MaCode=="" && datasources.MaName==""){
                return;
            }
            $("#import").attr("a",JSON.stringify(FiledLists));
            $("#_right").show();
            $("#list4").html("");
            request({id:"list4",url:"/Material/GetAllMaInfo",async:true,data: {"ExecType": "00","InitData":JSON.stringify(FiledLists)}},function(Body){
                tabConfig.data = Body.Data;
                fullTable(tabConfig);
                $("#list4").find("tbody tr").on("click",function () {

                    re_obj.data = [];
                    fullTable(re_obj);
                    //$("#MaCode").prop("readonly",false);
                    $("#MaName").prop("readonly",false);
                    //$("#MaDes").prop("readonly",false);
                    $("#type").prop("disabled",false);
                    $("#ExecType").val("02");
                    maInfo.MaName = $(this).find(".MaName").text();
                    maInfo.MaCode = $(this).find(".MaCode").text();
                    maInfo.MaDes = $(this).find(".MaDes").text();
                    maRd = $(this).find(".MaRd").text();
                    var MaInfoData = getMa(maRd);
                    $("#MaVerRd").val(MaInfoData.MaVerRd);
                    MaVerRd=$("#MaVerRd").val();
                    //获取版本列表
                    $select.empty();
                    var versionList = getMVTree(maRd);
                    for(var i in versionList){
                        if(MaInfoData.MaVerRd == versionList[i].MaVerRd) {
                            flag = versionList[i].Version;
                            $select.append("<option selected>" + versionList[i].Version + "</option>");
                        }
                        else
                            $select.append("<option>"+ versionList[i].Version +"</option>");
                    }
                    $input.css("display","none");
                    $select.css("display","inline-block");
                    $select.on("change",function () {
                        //通过不同的版本号的切换，然后发送请求，加载不同的信息
                        for(var i in versionList){
                            if($(this).val() == versionList[i].Version){
                                getMV(versionList[i].MaVerRd);
                                $("#MaVerRd").val(versionList[i].MaVerRd);
                                break;
                            }
                        }
                    });
                });
            });
        }
    };

    //筛选
    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);
    //导出
    $(document).on('click','#export',function () {
        var s=$("#import").attr("a");
        if($("#import").attr("a")==""){
            toastr.warning("请选择要导出的物料或者筛选再进行导出操作");
            return false;
        }
        if(treeID != "" && treeID != null){
            layer.confirm('确认要导出该物料信息？', {
                btn: ['确认', '取消']
            }, function () {
                layer.closeAll("dialog");
                var url = getBasePath() + "/Material/exportMaterialExcel";
                var data_ = "ExecType=00&InitData="+s;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', url, true);
                xhr.responseType = 'arraybuffer';
                xhr.onload = function () {
                    if (this.status === 200) {
                        var filename = "";
                        var disposition = xhr.getResponseHeader('Content-Disposition');
                        if (disposition && disposition.indexOf('attachment') !== -1) {
                            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                            var matches = filenameRegex.exec(disposition);
                            if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');

                            var type = xhr.getResponseHeader('Content-Type');

                            var date = new Date();

                            filename = "物料-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

                            var blob = new Blob([this.response], {type: type});
                            if (typeof window.navigator.msSaveBlob !== 'undefined') {
                                window.navigator.msSaveBlob(blob, filename);
                            } else {
                                var URL = window.URL || window.webkitURL;
                                var downloadUrl = URL.createObjectURL(blob);

                                if (filename) {

                                    var a = document.createElement("a");

                                    if (typeof a.download === 'undefined') {
                                        window.location = downloadUrl;
                                    } else {
                                        a.href = downloadUrl;
                                        a.download = filename;
                                        document.body.appendChild(a);
                                        a.click();
                                    }
                                } else {
                                    window.location = downloadUrl;
                                }

                                setTimeout(function () {
                                    URL.revokeObjectURL(downloadUrl);
                                }, 100); // cleanup
                            }
                        } else {
                            var un = new Uint8Array(this.response);
                            var s = "";
                            for (var i = 0; i < un.length; i++) {
                                s += String.fromCharCode(un[i]);
                            }
                            var json = JSON.parse(s);
                            if (json.Body != undefined) {
                                toastr.warning(json.Body.MsgDes);
                            }
                        }
                    }
                };
                xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xhr.send(data_);
            });
        }
        else {
            layer.confirm('确认要导出该物料信息？', {
                btn: ['确认', '取消']
            }, function () {
                layer.closeAll("dialog");
                var url = getBasePath() + "/Material/exportMaterialExcel";
                var data_1 = "ExecType=00&InitData="+s;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', url, true);
                xhr.responseType = 'arraybuffer';
                xhr.onload = function () {
                    if (this.status === 200) {
                        var filename = "";
                        var disposition = xhr.getResponseHeader('Content-Disposition');
                        if (disposition && disposition.indexOf('attachment') !== -1) {
                            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                            var matches = filenameRegex.exec(disposition);
                            if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');

                            var type = xhr.getResponseHeader('Content-Type');

                            var date = new Date();

                            filename = "物料-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

                            var blob = new Blob([this.response], {type: type});
                            if (typeof window.navigator.msSaveBlob !== 'undefined') {
                                window.navigator.msSaveBlob(blob, filename);
                            } else {
                                var URL = window.URL || window.webkitURL;
                                var downloadUrl = URL.createObjectURL(blob);

                                if (filename) {

                                    var a = document.createElement("a");

                                    if (typeof a.download === 'undefined') {
                                        window.location = downloadUrl;
                                    } else {
                                        a.href = downloadUrl;
                                        a.download = filename;
                                        document.body.appendChild(a);
                                        a.click();
                                    }
                                } else {
                                    window.location = downloadUrl;
                                }

                                setTimeout(function () {
                                    URL.revokeObjectURL(downloadUrl);
                                }, 100); // cleanup
                            }
                        } else {
                            var un = new Uint8Array(this.response);
                            var s = "";
                            for (var i = 0; i < un.length; i++) {
                                s += String.fromCharCode(un[i]);
                            }
                            var json = JSON.parse(s);
                            if (json.Body != undefined) {
                                toastr.warning(json.Body.MsgDes);
                            }
                        }
                    }
                };
                xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xhr.send(data_1);
            });
        }
    });

    $("#maBatch").click(function(){
        //$("#MaBarCode").val("");
        if($("#maBatch").is(":checked")){
            $("#MaBarCode").attr("readonly",true);
            $("#dd3").css("display", "");
        }else{
            $("#MaBarCode").attr("readonly",false);
            $("#dd3").css("display", "none");
        }
    });

    function allDisable(){
        //$("#MaCode").attr('disabled', true);
        $("#MaName").attr('disabled', true);
        $("#materVersion").attr('disabled', true);
        //$("#MaDes").attr('disabled', true);
        //$("#materFamily").attr('disabled', true);
        $("#Status").attr('disabled', true);
        $("#mianjian").attr('disabled', true);

     //   $("#MinSNum").attr('disabled', true);
      //  $("#MaxSNum").attr('disabled', true);
        $("#Shelflife").attr('disabled', true);
        $("#ReMaterial").attr('disabled', true);
        $("#OrderNum").attr('disabled', true);
        $("#Brand").attr('disabled', true);
        $("#customerPcode").attr('disabled', true);
        $("#SuName").attr('disabled', true);
        $("#SuMaCode").attr('disabled', true);
        $("#minDistribution").attr('disabled', true);
    }

    function isAllDisable(is){
        //$("#MaCode").attr('disabled', is);
        $("#MaName").attr('disabled', is);
        $("#materVersion").attr('disabled', is);
       // $("#MaDes").attr('disabled', is);
        //$("#materFamily").attr('disabled', true);
        $("#Status").attr('disabled', is);
        $("#mianjian").attr('disabled', is);

      //  $("#MinSNum").attr('disabled', is);
      //  $("#MaxSNum").attr('disabled', is);
        $("#Shelflife").attr('disabled', is);
        $("#ReMaterial").attr('disabled', is);
        $("#OrderNum").attr('disabled', is);
        $("#Brand").attr('disabled', is);
        $("#customerPcode").attr('disabled', is);
        $("#SuName").attr('disabled', is);
        $("#SuMaCode").attr('disabled', is);
        $("#minDistribution").attr('disabled', is);
    }


    $("#minDistribution").on("click", function(){
        if($(this).is(":checked")){
            $("#zxbzsl").show();
        }else{
            $("#zxbzsl").hide();
        }
    });
});
