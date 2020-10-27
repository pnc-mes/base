/**
 * Created by zhangliangliang on 2017/10/24.
 */
$(function(){
    var PKDtlRd="";
    var UnitName="";
    var _MaDes = "";
    var _MaCode = "";
    var resultFlag = true;//判断表格是编辑还是新增
    var modifytableid = '';
    var addid = 1;

    var colNamesArr = [
        {"Caption": "表格的唯一一个数据", "Name": "addid", 'Hidden': true,Width:100},
        {"Caption": "OutDtlRd", "Name": "OutDtlRd", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
        {"Caption": "物料名称", "Name": "MaName",'Editable': false},
        {"Caption": "物料描述", "Name": "MaDes",'Editable': false},
        {"Caption": "出库仓库", "Name": "StoreName",'Editable': false},
        {"Caption": "领用仓库id", "Name": "StoreRd",'Editable': false, "Hidden": true},
        {"Caption": "出库数量", "Name": "Num", 'Editable': true,Width:80},
        {"Caption": "单位", "Name": "UnitName", 'Editable': false,Width:50},
        {"Caption": "备注", "Name": "Remark", 'Editable': false,Width:80}
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

    var $tab_4_li = $("#tab_4_li");
    var $tab_4 = $("#tab_4");
    var flag = true;
    var $maName = $("#maName");

    var $JGL = $("#JGL");
    var $CGNum = $("#CGNum");
    var $unit = $(".unit");
    var _PurChDlRd = "";


    var dSource = true;
    var elStatus="";
    var treeID="";
    //放在最上面。目的是为了实现是否可以点击领用人这个插件
    var ssStaatus="";
    var str="";
    var str1="";
    //是否双击事件
    // var isclick=true;
    var onClicks = function (nodeinfo, handle) {
        //isclick=false;
        $("#lymater").clearseldata("Num");
        $("#ccsl").val("");
        $("#unit").text("");
        $("#lysl").val("");
        $("#stat").show();
        $("#shzt").show();
        $("#PickCode").attr("readonly",true);
        //点击时 即更新操作
        $(".cadd").attr("a","01");
        //删除之后这边执行点击显示
        $("#_right").show();
        $(".cSave").attr("a",nodeinfo.nodeID);
        //下达和取消赋值， 这里是首先要点击的
        $(".xd").attr("a",nodeinfo.nodeID);
        $("#cancel").attr("a",nodeinfo.nodeID);
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"OutMaRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/OutMa/GetOutMaInfo",data: objData},function(Body){
            if(Body.Data.DSource == "00"){
                dSource = false;
            }else{
                dSource = true;
            }
            //其他
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            $('#PickCode').val(Body.Data.OutCode);
            $('#ArrivalTime').val(Body.Data.PreOutDate);
            if(Body.Data.PickerInfo!=null&&Body.Data.PickerInfo!=""){
                $("#Buyers").showData({
                    id:Body.Data.PickerInfo.PickerRd,
                    name:Body.Data.PickerInfo.PickerName,
                    keyfield:"UserRd",
                    fields:[
                        {"name":"UserRd"},
                        {"name":"RealName"}
                    ]
                });
            }



            $("#use").val(Body.Data.Use);


            if("00"==Body.Data.ExStatus){
                str="待执行";
            }
            if("01"==Body.Data.ExStatus){
                str="进行中";
            }
            if("02"==Body.Data.ExStatus){
                str="已完成";
            }
            if("03"==Body.Data.ExStatus){
                str="已取消";
            }

            $tab_4_li.css("display","none").prev().addClass("active");
            $tab_4.removeClass("active").prev().addClass("active");

            elStatus=Body.Data.ExStatus;
            $("#status").val(str);
            //隐藏按钮

            ssStaatus=Body.Data.SStatus;


            if("00"==Body.Data.SStatus){
                $("#maInfoButton").show();
                $("#use").prop("disabled",false);
                $("#ArrivalTime").prop("disabled",false);
                //目的是00才可以编辑  点击的时候，根据状态来判断是否可编辑 领用人插件，  判断是再点击领用人的时候 根据树的点击获取到的值
                $(".inpSearch").attr("readonly",false);
                $(".inpSearch").attr("disabled",false);
                str1="未下达";
            }
            if("01"==Body.Data.SStatus){
                $("#maInfoButton").hide();
                $("#use").prop("disabled",true);
                $("#ArrivalTime").prop("disabled",true);
                str1="已下达";
            }
            if("02"==Body.Data.SStatus){
                $("#maInfoButton").hide();
                $("#use").prop("disabled",true);
                $("#ArrivalTime").prop("disabled",true);
                str1="已取消";
            }
            $("#shztss").text(str1);
            var PurMaData = Body.Data.OutDlInfo;

            if(PurMaData==null){
                var config1 = {
                    tableId: 'list4',
                    data: [],
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height:0.36
                };
                fullTable(config1);
                return;
            }

            addid = 1;
            var PurMaInfos = [];
            for (var i = 0; i < PurMaData.length; i++) {
                var PurMaInfo = {
                    "addid": addid++,
                    "OutDtlRd":PurMaData[i].OutDtlRd,
                    "MaVerRd":PurMaData[i].MaVerRd,
                    "MaCode": PurMaData[i].MaCode,
                    "MaName": PurMaData[i].MaName,
                    "MaDes": PurMaData[i].MaDes,
                    "StoreName":PurMaData[i].StoreInfo.StoreName,
                    "StoreRd":PurMaData[i].StoreInfo.StoreRd,
                    "Num": PurMaData[i].Num,
                    "UnitName": PurMaData[i].UnitName,
                    "Remark":PurMaData[i].Remark,
                };
                PurMaInfos.push(PurMaInfo);
            }
            var config1 = {
                tableId: 'list4',
                data: PurMaInfos,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);
            //每次点击触发这个事件
            click();
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
        currentPage = treeSearchs("//OutMa/GetAllOutMaInfo","OutMaRd","OutCode","OutCode",condition,currentPage,config);
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
            currentPage = treeSearchs("/OutMa/GetAllOutMaInfo","OutMaRd","OutCode","OutCode",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })




    var list=[];
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/OutMa/GetAllOutMaInfo","OutMaRd","OutCode","OutCode",condition,currentPage,config,list);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/OutMa/GetAllOutMaInfo","OutMaRd","OutCode","OutCode",condition,currentPage,config,list);
    });



    var loadTree=function(){
        var treedataList = [];
        var currentPage=0;
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData1 = {
            "FiledList": [
                {
                    "FieldName": "CreateTime DESC",
                    "FieldOpt": "order by",
                }
            ]
        };
        PageInfo.PageIndex = currentPage;
        request({url: '/OutMa/GetAllOutMaInfo', data: {"ExecType": "00","InitData": JSON.stringify(InitData1),"PageInfo":JSON.stringify(pageInfo)}}, function (Body) {
            $("#jstree_demo1").empty();
            var treeData = Body.Data;
            if(treeData.length <= 0) {
                currentPage--;
                PageInfo.PageIndex = currentPage;
                return;
            }
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
                    id: treeData[i].OutMaRd == undefined ? "" : treeData[i].OutMaRd,
                    name: treeData[i].OutCode == undefined ? "" : treeData[i].OutCode
                }
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        })
        click();
    }
    loadTree();

    //采购员
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
                    "name": "RealName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                if(ssStaatus!=null||""!=ssStaatus){
                    if(ssStaatus=="01"||ssStaatus=="02"){
                        $(".inpSearch").attr("readonly","readonly");
                        $(".inpSearch").attr("disabled","disabled");
                        toastr.warning("该单子处于已下达或已取消状态");
                        return false;
                    }
                }


                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RealName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "RealName",
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
                            "RealName": datas[i].RealName,
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
            /* "onformatval":function(seldata){
                // alert(seldata.UserRd+"-----"+seldata.RealName)
                 $("#Buyers").val(seldata.RealName);
             }*/
        }
    };
    $("#Buyers").zc_select(user);
    /*var StoreRd=""*/
    var dataStore=[];

    //存放新增明细的时候表格数据
    var tab_TR = [];

    var list4Date=getTableData("list4");
    //测试每次的表格信息

    var temporaryDatas=[];
    var temporaryData={};
    //领用物料
    //领用数量的新增事件 即保存在一个集合里
    var insertData={};
    var lywl = {
        "displaymode": "1",
        "title": "领用物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "版本Rd",
                    "name": "MaVerRd",
                    "hidden":true
                }, {
                    "caption": "领用物料代码",
                    "name": "MaCode"
                }, {
                    "caption": "领用物料名称",
                    "name": "MaName"
                }, {
                    "caption": "领用物料描述",
                    "name": "MaDes"
                },{
                    "caption": "lywlid",
                    "name": "StoreNum",
                    "hidden":true
                },
                {
                    "caption": "单位",
                    "name": "UnitName",
                    "hidden":true
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                if(storeid==null||storeid==""||!Number(storeid)){
                    toastr.warning("操作失败，没有获取到仓库")
                    return false;
                }
                var objDate={
                    "StoreRd":storeid
                };
                var xldata = [];
                request({url: "/BOM/GetNMaTotalInfo", data: {"ExecType": "00","busData": JSON.stringify(objDate)}}, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data= {
                            "MaVerRd": datas[i].MaVerRd,
                            "MaCode": datas[i].MaCode,
                            "MaName": datas[i].MaName,
                            "MaDes": datas[i].MaDes,
                            "StoreNum": datas[i].StoreNum,
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

            },
            "onclick":function (res) {
                $("#ccsl").val(res.StoreNum);
                //此处拼下面的表格数据
                //目的是为了获取仓库name
                var newData1={
                    "StoreRd":storeid
                }
                var StoreName="";
                request({url: "/Store/GetStoreInfo", data: {"ExecType": "00","busData": JSON.stringify(newData1)}}, function (Body) {
                    StoreName=Body.Data.StoreName;
                });
                UnitName=res.UnitName;
                temporaryData={
                    "MaVerRd":res.MaVerRd/*+"_"+storeid*/,
                    "MaCode":res.MaCode,
                    "MaName":res.MaName,
                    "MaDes":res.MaDes,
                    "Num":res.StoreNum,
                    "StoreRd":storeid,
                    "UnitName":res.UnitName,
                    "StoreName":StoreName
                }

            },
            "onformatval": function(seldata){
                xldata=[];
                return seldata.MaCode + "-" + seldata.MaName+"-"+seldata.MaDes;
            }
        }};
    $("#lymater").zc_select(lywl);

    //仓库
    //获取点击仓库后的id
    var storeid="";
    var store = {
        "displaymode": "0",
        "title": "store",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "storeid",
                    "name": "StoreRd"
                }, {
                    "caption": "store名称",
                    "name": "StoreName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                //每次点击的时候，把领用物料里面加载的数据清空和显示的信息以及库存数量去拿不清空
                xldata = [];
                $("#lymater").clearseldata("Num");
                $("#ccsl").val("");


                var xldata1 = [];
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"StoreName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "StoreName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Store/GetAllStoreInfo"
                };

                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "StoreRd": datas[i].StoreRd,
                            "StoreName": datas[i].StoreName
                        };
                        xldata1.push(data);
                    }
                });
                var obj = {
                    data:xldata1,
                    showrow:500
                };
                return obj;
            },
            "onclick":function (res) {
                storeid=res.StoreRd;
            }
        }
    };
    $("#Store").zc_select(store);
    //新增
    $("#addPick").click(function(){
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
        // isclick==true;
        addid = 1;
        $("#lymater").clearseldata("Num");
        $("#ycyid").val("");
        $("#ccsl").val("");
        $("#unit").text("");
        $("#lysl").val("");
        $("#_right").show();

        $("#maInfoButton").show();
        $("#use").prop("disabled",false);
        $("#ArrivalTime").prop("disabled",false);
        $(".inpSearch").attr("readonly",false);
        $(".inpSearch").attr("disabled",false);
        ssStaatus="";
        $("#PickCode").val("");
        $("#PickCode").attr("readonly",false);
        $("#Buyers").clearseldata("UserRd");
        $("#Store").clearseldata("StoreRd");
        $("#ArrivalTime").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $("#use").val("");
        $("#stat").hide();
        $("#status").text("");
        $("#shzt").hide();
        $("#shztss").text("");
        $("#PickCode").val("");

        treeID=null;
        //保存新增事件
        $("#ExecType").val("00");
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height:0.36
        };
        fullTable(config1);//加载空表格
    });

    //保存事件
    $(".cSave").click(function(){

        var OutCode=$("#PickCode").val().trim();
        var PickerRd=$("#Buyers").getseldata().UserRd;
        if(PickerRd==""){
            toastr.warning("领用人不能为空");
            return false;
        }
        var PreOutDate=$("#ArrivalTime").val().trim();
        if(PreOutDate==""){
            toastr.warning("领用日期不能为空");
            return false;
        }

        var Use=$("#use").val().trim();
        if(Use==""){
            toastr.warning("用途不能为空");
            return false;
        }
        var OutMaInfo = getTableData("list4");
        if(OutMaInfo===undefined || OutMaInfo.length==0){
            toastr.warning("领料明细不能为空");
            return false;
        }
        //触发点击事件更新
        if($(".cadd").attr("a")=="01" && !dSource){
            toastr.warning("导入的领料单数据不允许修改");
        }

        //触发新增保存事件
        if((treeID == null || treeID == "") && $("#ExecType").val() == "00"){
            var TabSources = getTableData("list4");


            //  alert(JSON.stringify(TabSources))
            //  return;
            for(var a in OutMaInfo){
                var sp=OutMaInfo[a].MaVerRd;
                OutMaInfo[a].MaVerRd=sp;
                delete OutMaInfo[a].OutDtlRd;
                delete OutMaInfo[a].MaCode;
                delete OutMaInfo[a].MaDes;
                delete OutMaInfo[a].MaName;
                delete OutMaInfo[a].StoreName;
                delete OutMaInfo[a].addid;
            }

            var PKMaInfodata={
                "OutCode":OutCode,
                "PickerRd":PickerRd,
                "PreOutDate":PreOutDate,
                "Use":Use,
                "OutMaInfo":OutMaInfo,
                "Remark":$("#beizhu").val()
            }

            request({url: "/OutMa/SaveOutMaInfo", data: {"ExecType": "00","busData": JSON.stringify(PKMaInfodata)}}, function (Body) {
                loadTree();
                $(".xd").attr("a",Body.Data.OutMaRd);
                $("#cancel").attr("a",Body.Data.OutMaRd);
                $("#stat").show();
                $("#status").text("待执行");
                $("#shzt").show();
                $("#shztss").text("未下达");

                layer.closeAll("dialog");
                toastr.success("领料单信息新增成功,领料单号{"+Body.Data.OutCode+"}");

                $(".cadd").attr("a","");

                $("#hidden1").attr("editid", Body.Data.OutMaRd);
                treeID=$("#hidden1").attr("editid");
                $("#hidden2").attr("editcode", Body.Data.OutCode);

               /* var objBusData = JSON.stringify({"PickRd": treeID});
                var objData = {
                    "ExecType": "00",
                    "BusData": objBusData
                };
                request({url:"/Pick/GetNPickInfo",data: objData},function(Body){

                    if(Body.Data.DSource == "00"){
                        dSource = false;
                    }else{
                        dSource = true;
                    }
                    //其他
                    $("#creatPeople").val(Body.Data.Creator);
                    $("#creatTime").val(Body.Data.CreateTime);
                    $("#modifyPeople").val(Body.Data.LastModifyMan);
                    $("#modifyTime").val(Body.Data.LastModifyTime);
                    $("#beizhu").val(Body.Data.Remark);
                    $('#PurChCode').val(Body.Data.PickCode);
                    $('#ArrivalTime').val(Body.Data.PrePickDate);
                    if(Body.Data.PickerInfo!=null&&Body.Data.PickerInfo!=""){
                        $("#Buyers").showData({
                            id:Body.Data.PickerInfo.PickerRd,
                            name:Body.Data.PickerInfo.PickerName,
                            keyfield:"UserRd",
                            fields:[
                                {"name":"UserRd"},
                                {"name":"RealName"}
                            ]
                        });
                    }


                    $("#Store").showData({
                        id:Body.Data.StoreInfo.StoreRd,
                        name:Body.Data.StoreInfo.StoreName,
                        keyfield:"StoreRd",
                        fields:[
                            {"name":"StoreRd"},
                            {"name":"StoreName"}
                        ]
                    });
                    $("#use").val(Body.Data.Use);

                    var str="";
                    if("00"==Body.Data.ExStatus){
                        str="待处理";
                    }
                    if("01"==Body.Data.ExStatus){
                        str="进行中";
                    }
                    if("02"==Body.Data.ExStatus){
                        str="已完成";
                    }
                    if("03"==Body.Data.ExStatus){
                        str="已取消";
                    }

                    $("#status").text(str);
                    elStatus=Body.Data.SStatus;
                    var OutMaInfo = Body.Data.PickDlInfo;
                    var PurMaInfos = [];
                    for (var i = 0; i < PurMaData.length; i++) {
                        var PurMaInfo = {
                            "PKDtlRd":PurMaData[i].PKDtlRd,
                            "MaVerRd":PurMaData[i].MaVerRd,
                            "MaCode": PurMaData[i].MaCode,
                            "MaName": PurMaData[i].MaName,
                            "MaDes": PurMaData[i].MaDes,
                            "Num": PurMaData[i].Num,
                            "UnitName": PurMaData[i].UnitName,
                            "Remark":PurMaData[i].Remark
                        };
                        PurMaInfos.push(PurMaInfo);
                    }
                    var config1 = {
                        tableId: 'list4',
                        data: PurMaInfos,
                        colArr: colNamesArr,
                        multiselect: true,
                        width: 0.84,
                        height:0.36
                    };
                    fullTable(config1);
                });*/
            });

        }
        else if( treeID!=null && treeID!="" && dSource){

            for(var a in OutMaInfo){
                if(OutMaInfo[a].OutDtlRd=="undefined"||OutMaInfo[a].OutDtlRd==""||OutMaInfo[a].OutDtlRd==null){
                    OutMaInfo[a].OutDtlRd="";
                }
                var sp=OutMaInfo[a].MaVerRd;
                // var sp1=sp.split("_");
                OutMaInfo[a].MaVerRd=sp;
                delete OutMaInfo[a].MaCode;
                delete OutMaInfo[a].MaDes;
                delete OutMaInfo[a].MaName;
                delete OutMaInfo[a].StoreName;
                delete OutMaInfo[a].addid;
            }

            var PKMaInfodata={
                "OutMaRd":treeID,
                "OutCode":OutCode,
                "PickerRd":PickerRd,
                "PreOutDate":PreOutDate,
                "Use":Use,
                "OutMaInfo":OutMaInfo,
                "Remark":$("#beizhu").val()
            }

            if("02"==elStatus){
                toastr.warning("操作失败，该单子已完成");
                return false;
            }

            request({url: "/OutMa/SaveOutMaInfo", data: {"ExecType": "02","busData": JSON.stringify(PKMaInfodata)}}, function (Body) {
                layer.closeAll("dialog");
                toastr.success(Body.MsgDes);
                loadTree();//局部刷新
                $(".cadd").attr("a","");
            });
        }

    });

    //删除
    $("#delete").click(function(){
        var OutMaRd=$(".cSave").attr("a");
        if(OutMaRd==null||OutMaRd==""){
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else{
            var newData1={
                "OutMaRd":OutMaRd
            }

            request({url: "/OutMa/SaveOutMaInfo", data: {"ExecType": "01","busData": JSON.stringify(newData1)}}, function (Body) {
                layer.closeAll("dialog");
                toastr.success(Body.MsgDes);
                loadTree();//局部刷新
                $(".cSave").attr("a","");
                $("#_right").hide();
            });
        }
    });


    // 表格的删除
    function delTr(tableId) {
        //getGridParam:表示的是获取表格的参数
        var selectedRowIds = $("#" + tableId).jqGrid("getGridParam", "selarrrow");
        var gr = 0;
        if (selectedRowIds != undefined && selectedRowIds != null)
            gr = selectedRowIds.length;
        if (gr > 0) {
            layer.confirm('', {
                    type: 0,
                    btn: ['确认', '取消'], //按钮
                    content: '确认删除吗？',
                    icon: "fa-check-circle"
                }, function () {
                    for (var i = 0; i < gr; i++) {
                        $("#" + tableId).jqGrid("delRowData", selectedRowIds[0]);//每次都取第0个数据
                    }
                    layer.closeAll();
                    return gr;
                }, function () {

                }
            );
        } else {
            alertWarn("请选择要删除的数据再进行删除！")
            return -1;
        }
    }

    $(".del1").click(function(){
        delTr("list4");
    });

    $(".add1").click(function(){
        $("#sure2").css("display","inline-block");
        // isclick=true;
        //每次点击新增清空拼接的集合值
        tab_TR = [];
        // totalData=[];
        temporaryData={};
        //temporaryDatas=[];
        $("#ccsl").val("");
        $("#lysl").val("");
        $("#Remark").val("");
        $("#ycyid").val("");
        $("#Store").clearseldata("StoreRd");
        $("#lymater").clearseldata("MaVerRd");

        $("#ccsl").attr("disabled",true);
        //显示明细设置
        flag = false;
        //把物料消耗的tab显示出来,并设置li的class为active,而其他的li移除active
        $tab_4_li.css("display","block").addClass("active").siblings().removeClass("active");
        $tab_4.addClass("active").siblings().removeClass("active");
        //清空物料消耗页面数据
        $maName.clearseldata("MaVerRd");
        $maName.clearseldata("MaCode");
        $maName.clearseldata("MaName");
        $maName.clearseldata("MaDes");

        $CGNum.val("");
        $unit.html("");
        //所有内容都可编辑
        $(".sure2").css("display","-webkit-box");
        $("#tab_4").find("input").each(function () {
            $(this).prop("readonly",false);
        });
        $("#tab_4").find("select").prop("disabled",false);
        $("#tab_4").find("img").css("display","inline-block");
        resultFlag = true;
    });

    //明细的确认按钮
    $(".sure2").on("click",function () {

        var flags=/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
        var lysl=$("#lysl").val().trim();

        if(storeid==null||storeid==""){
            toastr.warning("领用失败，领用仓库不能为空")
            return false;
        }
        if($("#ccsl").val().trim()==null||$("#ccsl").val().trim()==""){
            toastr.warning("领用失败，领用物料不能为空")
            return false;
        }
        if(lysl==null||lysl==""){
            toastr.warning("领用失败，领用数量不能为空")
            return false;
        }
        var ccsl=$("#ccsl").val().trim();
        if(flags.test(lysl)) {
            //前端判断领用数量和库存数量进行对比
            if (parseFloat(lysl) > parseFloat(ccsl)) {
                toastr.warning("领用失败，领用数量不能大于库存数量");
                return false;
            }
        }
        var Remark=$("#Remark").val();

        var MaVerRd = $(this).getseldata().MaVerRd;
        var MaCode = $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode;
        var MaName = $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split(($(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode) + "-")[0];
        var MaDes = $(this).getseldata().MaDes == null ? _MaDes:$(this).getseldata().MaDes;
        var StoreRd = $(this).getseldata().StoreRd;
        var StoreName = $(this).getseldata().StoreName;
        var UnitName = $(this).getseldata().UnitName;
        if(MaVerRd == undefined || MaVerRd == ""){
            toastr.warning("物料名称不能为空");
            return false;
        }

        if(!resultFlag){
            //新增表格
            var tableData = getTableData("list4");
            if(tableData.length>0){
                for(var i in tableData){
                    if(tableData[i].addid == modifytableid){
                        tableData[i].MaVerRd = MaVerRd;
                        tableData[i].MaCode = MaCode;
                        tableData[i].MaName = MaName;
                        tableData[i].MaDes = MaDes;
                        tableData[i].StoreRd = StoreRd;
                        tableData[i].StoreName = StoreName;
                        tableData[i].Num = lysl;
                        tableData[i].UnitName = UnitName;
                        tableData[i].Remark = Remark;
                    }
                }

                var config1 = {
                    tableId: 'list4',
                    data: tableData,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config1);
            }
        }else{
            //修改表格
            var tab_TR = {
                addid: addid++,
                PKDtlRd: "",
                MaVerRd: MaVerRd,
                MaName: MaName,
                MaCode: MaCode,
                MaDes: MaDes,
                StoreRd: StoreRd,
                StoreName: StoreName,
                Num: lysl,
                UnitName: UnitName,
                Remark:Remark
            };

            addSrow("list4", tab_TR);
        }

        /*if(!flag) {
            //获取里面所有的数据，添加到用料清单的表格中
            var tab_TR = {
                addid: addid++,
                PKDtlRd: "",
                MaVerRd: $(this).getseldata().MaVerRd,
                MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                MaName: $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split(($(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode) + "-")[0],
                MaDes: $(this).getseldata().MaDes == null ? "":$(this).getseldata().MaDes ,
                StoreRd: $(this).getseldata().StoreRd,
                StoreName: $(this).getseldata().StoreName,
                Num: lysl,
                UnitName: UnitName,
                Remark:Remark
            };

            //新增表格
            var tableData = getTableData("list4");
            if(tableData.length>0){
                for(var i in tableData){
                    if(tableData[i].addid == modifytableid){
                        tableData[i].PKDtlRd = PKDtlRd,
                        tableData[i].MaVerRd = MaVerRd;
                        tableData[i].MaCode = MaCode;
                        tableData[i].MaName = MaName;
                        tableData[i].MaDes = MaDes;
                        tableData[i].AFeed = AFeed;
                        tableData[i].AFeedName = AFeedName;
                        tableData[i].StoreRd = StoreRd;
                        tableData[i].StoreName = StoreName;
                        tableData[i].Num = Num;
                        tableData[i].UnitName = UnitName;
                    }
                }

                var config1 = {
                    tableId: 'list4',
                    data: tableData,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config1);
            }

            var _flag = true;
            var tab_list4 = getTableData("list4");
            for (var i in tab_list4) {
                if (tab_list4[i].addid == modifytableid) {
                    //toastr.warning(tab_TR.MaName + "，已经被添加");
                    $("#list4").find("tbody tr").each(function () {
                        if($(this).find(".addid").text() == modifytableid){
                            $(this).find(".Num").html(/!*(parseFloat(tab_TR.Num)*1000 + parseFloat($(this).find(".Num").text())*1000)/1000*!/tab_TR.Num);
                            _flag = false;
                            return false;
                        }
                    });
                    break;
                }
            }
            if(_flag)
                addSrow("list4", tab_TR);
        }else{
            //获取里面所有的数据，添加到用料清单的表格中
            var tab_TR = {
                PKDtlRd: PKDtlRd,
                MaVerRd: $(this).getseldata().MaVerRd,
                MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                MaName: $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split("-")[1],
                MaDes: $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split("-")[2],
                StoreRd: $(this).getseldata().StoreRd,
                StoreName: $(this).getseldata().StoreName,
                Num: lysl,
                UnitName: UnitName,
                Remark:Remark
            };
            var tab_list4 = getTableData("list4");
           // alert(JSON.stringify(tab_TR))
            $("#list4").find("tbody tr").each(function () {
                if($(this).find(".PKDtlRd").text() == PKDtlRd){
                    $(this).find(".MaVerRd").html(tab_TR.MaVerRd);
                    $(this).find(".MaCode").html(tab_TR.MaCode);
                    $(this).find(".MaName").html(tab_TR.MaName);
                    $(this).find(".MaDes").html(tab_TR.MaDes);
                    $(this).find(".Num").html(tab_TR.Num);
                    $(this).find(".UnitName").html(tab_TR.UnitName);
                    $(this).find(".StoreRd").html(tab_TR.StoreRd);
                    $(this).find(".StoreName").html(tab_TR.StoreName);
                    $(this).find(".Remark").html(tab_TR.Remark);
                    return false;
                }
            });
        }*/
        /*  //隐藏当前tab
          $tab_4_li.css("display","none").prev().addClass("active");
          $tab_4.removeClass("active").prev().addClass("active");*/


        // isclick=true;
        //每次点击新增清空拼接的集合值
        tab_TR = [];
        // totalData=[];
        temporaryData={};
        //temporaryDatas=[];
        $("#ccsl").val("");
        $("#lysl").val("");
        $("#Remark").val("");
        $("#ycyid").val("");
        $("#Store").clearseldata("StoreRd");
        $("#lymater").clearseldata("MaVerRd");

        $("#ccsl").attr("disabled",true);
        //显示明细设置
        flag = false;
        //把物料消耗的tab显示出来,并设置li的class为active,而其他的li移除active
        $tab_4_li.css("display","block").addClass("active").siblings().removeClass("active");
        $tab_4.addClass("active").siblings().removeClass("active");
        //清空物料消耗页面数据
        $maName.clearseldata("MaVerRd");
        $maName.clearseldata("MaCode");
        $maName.clearseldata("MaName");
        $maName.clearseldata("MaDes");

        $CGNum.val("");
        $unit.html("");
        //所有内容都可编辑
        $(".sure2").css("display","-webkit-box");
        $("#tab_4").find("input").each(function () {
            $(this).prop("readonly",false);
        });
        $("#tab_4").find("select").prop("disabled",false);
        $("#tab_4").find("img").css("display","inline-block");

        click();
    });
    //明细上面的X
    $(".XH").on("click",function () {
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
    });
    //点击请求之后触发这个事件
    click();
    function click(){
        $("#list4").find("tbody").on("dblclick", "tr", function () {
            if (ssStaatus != null || "" != ssStaatus) {
                if (ssStaatus == "01" || ssStaatus == "02") {
                    //$("#sure2").css("display","none");
                    toastr.warning("领料单已下达或已取消无法修改");
                    return false;
                }
                resultFlag = false;

                modifytableid = $(this).find(".addid").text();

                $("#lymater").clearseldata("MaVerRd");
                $("#ccsl").attr("disabled",true);
                //把物料消耗的tab显示出来,并设置li的class为active,而其他的li移除active
                $tab_4_li.css("display","block").addClass("active").siblings().removeClass("active");
                $tab_4.addClass("active").siblings().removeClass("active");

                $CGNum.val("");
                $unit.html("");

                $(".sure2").css("display","-webkit-box");
                $("#tab_4").find("input").each(function () {
                    $(this).prop("readonly",false);
                });
                $("#tab_4").find("select").prop("disabled",false);
                $("#tab_4").find("img").css("display","inline-block");

                //获取表格所有数据
                var StoreRd=$(this).find(".StoreRd").text();
                var StoreName=$(this).find(".StoreName").attr('title');
                var MaverRd=$(this).find(".MaVerRd").text();
                var Num=$(this).find(".Num").attr('title');
                var Remark=$(this).find(".Remark").text();
                PKDtlRd=$(this).find(".PKDtlRd").text();
                var MaCode=$(this).find(".MaCode").text();
                var MaName=$(this).find(".MaName").text();
                var MaDes=$(this).find(".MaDes").text();
                var UnitName=$(this).find(".UnitName").text();
                var _MaVerRd = $(this).find(".MaVerRd").text();
                _MaCode = $(this).find(".MaCode").text();
                var _MaName = $(this).find(".MaName").text();
                _MaDes = $(this).find(".MaDes").text();
                var _UnitName = $(this).find(".UnitName").text();
                /*   alert(_MaName+"--"+MaDes+"--"+MaName+"--"+_MaDes);*/
                $maName.showData1({
                    id:_MaVerRd,
                    name:_MaCode + "-" + _MaName,
                    name1: [MaverRd, _MaCode, MaName, _MaDes, _UnitName],
                    keyfield:"MaVerRd",
                    fields:[
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
                        }, {
                            "caption": "物料单位",
                            "name": "UnitName"
                        }
                    ]
                });
                var clickMaCode=$(this).find(".MaCode").text();
                $(".inpSearch").val($(this).find(".MaCode").text()+"-"+$(this).find(".MaName").text());
                temporaryData={
                    "MaVerRd":MaverRd/*+"_"+storeid*/,
                    "MaCode":MaCode,
                    "MaName":MaName,
                    "MaDes":MaDes,
                    "Num":Num,
                    "StoreRd":StoreRd,
                    "UnitName":UnitName,
                    "StoreName":StoreName
                }
                //把id 给过去，要转化一下判断  PKDtlRd？？？
                //用隐藏域实现
                $("#ycyid").val(PKDtlRd);
                $("#lysl").val(Num);
                $("#Remark").val(Remark);
                $("#Store").showData({
                    id:StoreRd,
                    name:StoreName,
                    keyfield:"StoreRd",
                    fields:[
                        {"name":"StoreRd"},
                        {"name":"StoreName"}
                    ]
                });
                // 把id 带过来，目的是为了 直接操作领用物料
                storeid=StoreRd;
                var objDate={
                    "StoreRd":StoreRd
                }
                request({url:"/BOM/GetNMaTotalInfo", data: {"ExecType": "00","busData": JSON.stringify(objDate)}}, function (Body) {
                    var DS = Body.Data;
                    for(var J  in DS){
                        if(DS[J].MaCode==clickMaCode){
                            $("#ccsl").val(DS[J].StoreNum);
                        }
                    }

                });
                if(PKDtlRd == ""){
                    flag = false;
                }else{
                    flag = true;
                }
            }
        })
    }

    //导出领料确认单
    $("#export2").on('click', function () {
        if(treeID != "" && treeID != null){
            if(str1!="已下达"){
                toastr.warning("该单处于未下达或者已取消状态下，不能进行导出操作");
                return;
            }
            layer.confirm('确认要导出该领料单信息？', {
                btn: ['确认', '取消']
            }, function () {
                layer.closeAll("dialog");
                var url = getBasePath() + "/Pick/exportnPickExcel1";
                var data_ = "ExecType=00&BusData="+treeID;
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

                            filename = "无工单领料单-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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
        }else{
            toastr.warning("请选择要导出的领料单再进行导出操作");
        }
    });

    var InitData = {
        "FiledList":[
            {
                "FieldName": "RealName",
                "FieldOpt": "Order BY"
            }
        ]
    };

    var obj = {
        data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
        url:"/User/GetAllUserInfo"
    };
    var xlstore="";
    request(obj,function(Body) {
        var data=Body.Data;
        if(data.length==1){
            xlstore+="\""+data[0].UserRd+"\":\""+data[0].RealName+"\"";
        }else if(data.length>1) {
            for(var i=0;i<data.length;i++){
                xlstore+="\""+data[i].UserRd+"\":\""+data[i].RealName+"\""+"|";
            }
        }
    });


    //下达
    $(".xd").click(function(){
        var PickRd=$(this).attr("a");
        if(PickRd==null||""==PickRd){
            toastr.warning("下达失败，该信息不存在");
            return false;
        }
        var newData1={
            "OutMaRd":PickRd
        }
        request({url: "/OutMa/SaveOutMaInfo", data: {"ExecType": "03","busData": JSON.stringify(newData1)}}, function (Body) {
            layer.closeAll("dialog");
            toastr.success(Body.MsgDes);
            loadTree();//局部刷新
            $("#shztss").text("已下达");
            $(".xd").attr("a","");
            ssStaatus="01";
            $("#maInfoButton").hide();
        });
    });
    //取消
    $("#cancel").click(function(){
        var OutMaRd=$(this).attr("a");
        if(OutMaRd==null||""==OutMaRd){
            toastr.warning("取消失败，该信息不存在");
            return false;
        }
        var newData1={
            "OutMaRd":OutMaRd
        }
        request({url: "/OutMa/SaveOutMaInfo", data: {"ExecType": "04","busData": JSON.stringify(newData1)}}, function (Body) {
            layer.closeAll("dialog");
            toastr.success(Body.MsgDes);
            loadTree();//局部刷新
            $("#shztss").text("已取消");
            $("#cancel").attr("a","");
            ssStaatus="02";
            $("#maInfoButton").hide();
        });
    });


    //筛选
    var params = [{
        "caption": "领料单号",
        "name": "PickCode",
        "valtype": "00"
    }, {
        "caption": "状态",
        "name": "ExStatus",
        "valtype": "01",
        "data": "\"00\":\"待处理\"|\"01\":\"处理中\"|\"02\":\"已完成\""
    }, {
        "caption": "时间段",
        "name": "CreateTime",
        "valtype": "02"
    }, {
        "caption": "领用人",
        "name": "UserRd",
        "valtype": "01",
        "data":xlstore
    }];
    var datasources={};
    var event = {
        onsure: function (result) {
            var data=result[2].CreateTime.split("|");
            var data1=data[0];
            var data2=data[1];
            datasources={
                "PickCode":result[0].PickCode,
                "ExStatus":result[1].ExStatus,
                "CreateDate":data1,
                "CreateDate1":data2 == "" ? "" : (data1 == data2 ? data2 + " 23:59:59" : data2),
                "UserRd":result[3].UserRd
            }
            var treedataList=[];
            PageInfo.PageIndex = currentPage;
            request({url: '/Pick/GetAllNPickInfo1',data: {"ExecType": "00","BusData": JSON.stringify(datasources)}}, function (Body) {
                currentPage = 0;
                var treeData = Body.Data;
                if(treeData.length <= 0) {
                    currentPage--;
                    PageInfo.PageIndex = currentPage;
                    return;
                }
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
                        id: treeData[i].PickRd == undefined ? "" : treeData[i].PickRd,
                        name: treeData[i].PickCode == undefined ? "" : treeData[i].PickCode
                    }
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            });
            delete datasources.PickCode;
            list=datasources;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);

});