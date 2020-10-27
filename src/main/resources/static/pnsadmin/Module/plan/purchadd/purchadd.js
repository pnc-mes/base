/**
 * Created by test on 2017/8/23.
 */
$(function(){
    var $tab_4_li = $("#tab_4_li");
    var $tab_4 = $("#tab_4");
    var flag = true;
    var $maName = $("#maName");
    var $storeName = $("#storeName");
    var $JGL = $("#JGL");
    var $CGNum = $("#CGNum");
    var $unit = $(".unit");
    var _PurChDlRd = "";
    var _MaDes = "";
    var _MaCode = "";
    var resultFlag = true;//判断表格是编辑还是新增
    var modifytableid = '';
    var addid = 1;

    var colNamesArr = [
        {"Caption": "表格的唯一一个数据", "Name": "addid", 'Hidden': true,Width:100},
        {"Caption": "PurChDlRd", "Name": "PurChDlRd", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode","CType": "text", 'Editable': false,Width:100},
        {"Caption": "物料名称", "Name": "MaName","CType": "text", 'Editable': false},
        {"Caption": "物料描述", "Name": "MaDes","CType": "text", 'Editable': false},
        {"Caption": "甲供料ID", "Name": "AFeed","Hidden": true},
        {"Caption": "甲供料", "Name": "AFeedName","CType": "text",'Editable': false,Width:50},
        {"Caption": "收料ID", "Name": "StoreRd","Hidden": true},
        {"Caption": "收料仓库", "Name": "StoreName","CType": "text",'Editable': false,Width:80},
        {"Caption": "采购数量", "Name": "Num","CType": "text", 'Editable': false,Width:80},
        {"Caption": "单位", "Name": "UnitName", 'Editable': false,Width:80}
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height:0.54
    };
    fullTable(config1);//加载空表格
    /*方法：------获取点击之后一个节点的数据------------------*/
    var str1="";
    var str="";
    var onClicks = function (nodeinfo, handle) {
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
        $("#PurChCode").attr("readonly",true);
        $("#stat").show();
        $("#status").show();
        $("#SStatus").show();
        $("#sstatus1").show();
        $("#_right").show();//每次点击树节点的时候都把右侧展开

        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"PurChRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/Purch/GetPurchInfo",data: objData},function(Body){
            $("#ExecType").val("02");
            //其他
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);

            $("#hidden3").attr("h",Body.Data.DSource);
            var DSource = $("#hidden3").attr("h");

            if(DSource=="00"){
                $("#Buyers").prop("disabled",true);
                $("#SupNames").prop("disabled",true);
                $("#ArrivalTime").attr("readonly",true);
            }else{
                $("#Buyers").prop("disabled",false);
                $("#SupNames").prop("disabled",false);
                $("#ArrivalTime").attr("readonly",false);
            }

            //
            $('#PurChCode').val(Body.Data.PurChCode);
            if(Body.Data.ArrivalTime != null && Body.Data.ArrivalTime != "" && Body.Data.ArrivalTime != undefined)
                $('#ArrivalTime').val(Body.Data.ArrivalTime.split(" ")[0]);
            $("#Buyers").showData({
                id:Body.Data.Buyer,
                name:Body.Data.Buyer,
                keyfield:"UserRd",
                fields:[
                    {"name":"UserRd"},
                    {"name":"UserName"}
                ]
            });
            $("#SupNames").showData({
                id:Body.Data.SupCode,
                name:Body.Data.SupName,
                keyfield:"SupCode",
                fields:[
                    {"name":"SupCode"},
                    {"name":"SupName"}
                ]
            });

            if("00"==Body.Data.Status){
                str="待处理";
            }
            if("01"==Body.Data.Status){
                str="进行中";
            }
            if("02"==Body.Data.Status){
                str="已完成";
            }
            if("03"==Body.Data.Status){
                str="已取消";
            }
            $("#status").text(str);


            if("00"==Body.Data.SStatus){
                str1="未下达";
            }
            if("01"==Body.Data.SStatus){
                str1="已下达";
            }
            if("02"==Body.Data.SStatus){
                str1="已取消";
            }
            $("#sstatus1").text(str1);

            if(str1=="已下达" || str1=="已取消"){
                $("#maInfoButton").css("display","none");
                $("#ArrivalTime").attr("readonly",true);
            }else {
                $("#maInfoButton").css("display","block");
                $("#ArrivalTime").attr("readonly",false);
            }

            var PurMaData = Body.Data.PurChDlInfo;

            var PurMaInfos = [];
            addid = 1;
            for (var i = 0; i < PurMaData.length; i++) {
                var PurMaInfo = {
                    "addid": addid++,
                    "PurChDlRd":PurMaData[i].PurChDlRd,
                    "MaVerRd":PurMaData[i].MaInfo.MaVerRd,
                    "MaCode": PurMaData[i].MaInfo.MaCode,
                    "MaName": PurMaData[i].MaInfo.MaName,
                    "MaDes": PurMaData[i].MaInfo.MaDes,
                    "AFeed": PurMaData[i].AFeed,
                    "AFeedName":PurMaData[i].AFeed === "00" ? "是":"否",
                    "StoreRd": PurMaData[i].StoreInfo == null ? "":PurMaData[i].StoreInfo.StoreRd,
                    "StoreName": PurMaData[i].StoreInfo == null ? "":PurMaData[i].StoreInfo.StoreName,
                    "Num": PurMaData[i].Num,
                    "UnitName": PurMaData[i].UnitName
                };
                PurMaInfos.push(PurMaInfo);
            }
            config1.data = PurMaInfos;
            fullTable(config1);
            trDBLClick();
        });
    };
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
        event:{
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
        currentPage = treeSearchs("/Purch/GetAllPurchInfo","PurChRd","PurChCode","PurChCode",condition,currentPage,config);
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
        currentPage = treeSearchs("/Purch/GetAllPurchInfo","PurChRd","PurChCode","PurChCode",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    var list = [];
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Purch/GetAllPurchInfo","PurChRd","PurChCode","PurChCode",condition,currentPage,config,list);
        }
    });

    $("#next").on("click",function(){
        currentPage++;
        currentPage = treeSearchs("/Purch/GetAllPurchInfo", "PurChRd", "PurChCode", "PurChCode", condition, currentPage, config,list);
    });

    $("#_right").hide();

    //采购员
    var params = {
        "displaymode": "0",
        "binddata": {
            "keyfield": "UserRd",
            "fields": [
                {
                    "caption": "用户ID",
                    "name": "UserRd"
                }, {
                    "caption": "姓名",
                    "name": "UserName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RealName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/User/GetAllUserInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UserRd": datas[i].UserRd,
                            "UserName": datas[i].RealName
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
    $("#Buyers").zc_select(params);
    //供应商
    var params = {
        "displaymode": "1",
        "title": "供应商",
        "binddata": {
            "keyfield": "SupCode",
            "fields": [
                {
                    "caption": "供应商代码",
                    "name": "SupCode"
                }, {
                    "caption": "供应商名称",
                    "name": "SupName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"SupplierName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName": "SupplierName",
                            "FieldOpt": "Order BY"
                        },{
                            "FieldName":"Status",
                            "FieldOpt":"=",
                            "FieldVal":"00"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/Supplier/GetAllSupInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {

                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SupCode": datas[i].SupCode,
                            "SupName": datas[i].SupName
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
    $("#SupNames").zc_select(params);
    var InitData = {
        "FiledList":[{
            "FieldName":"IsDef",
            "FieldOpt":"=",
            "FieldVal":"00"
        },
            {
                "FieldName":"MaterialType",
                "FieldOpt":"in",
                "FieldVal":"('02','03')"
            }
        ]
    };

    var treeID = null;

    //加载树
    var loadPage = function () {
        var newTree=[];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData2 = {
            "FiledList": [
                {
                    "FieldName": "CreateTime DESC",
                    "FieldOpt": "order by",
                }
            ]
        };
        request({url: '/Purch/GetAllPurchInfo', data: {"ExecType": "00","InitData": JSON.stringify(InitData2),"PageInfo":JSON.stringify(pageInfo)}}, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].PurChRd == undefined ? "" : treeData[i].PurChRd,
                    name: treeData[i].PurChCode == undefined ? "" : treeData[i].PurChCode
                };
                newTree.push(tree);
            }
            config.data.source = newTree;
            $.JstreeEx.init(config);//先调用后加载
        })
    };
    loadPage();

    //菜单新增
    $('#addPurch').on('click',function(){
        addid = 1;
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
        $("#creatPeople").val('');
        $("#creatTime").val('');
        $("#modifyPeople").val('');
        $("#modifyTime").val('');
        $("#beizhu").val('');
        $("#ExecType").val("00");
        $("#stat").hide();
        $("#status").hide();
        $("#SStatus").hide();
        $("#sstatus1").hide();
        $("#addPurch").show();
        $("#delete").show();
        $('#PurChCode').val('');
        $("#PurChCode").attr("readonly",false);
        $("#Buyers").prop("disabled",false);
        $("#SupNames").prop("disabled",false);
        $("#ArrivalTime").attr("readonly",false);
        $('#ArrivalTime').val('');
        $("#Buyers").clearseldata("UserName");
        $('#SupNames').clearseldata("SupCode");
        $('#SupNames').clearseldata("SupName");
        $("#_right").show();
        $("#maInfoButton").css("display","block");
        $("#ArrivalTime").attr("readonly",false);
        //表格清空
        config1.data = [];
        fullTable(config1);//加载空表格
    });
    //表格的新增
    $('.add1').on('click',function(){
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
        $storeName.clearseldata("StoreRd");
        $storeName.clearseldata("StoreName");
        $CGNum.val("");
        $unit.html("");
        //所有内容都可编辑
        $(".sure2").css("display","-webkit-box");
        $("#tab_4").find("input").each(function () {
            $(this).prop("readonly",false);
        });
        $("#tab_4").find("select").prop("disabled",false);
        $("#tab_4").find("img").css("display","inline-block");
        str1 = "";
        resultFlag = true;
    });

    //下达
    $("#xiada").on("click",function () {
        if(treeID==null || treeID==""){
            toastr.warning("请选择左侧审核状态为未下达，再点击下达!");
            return;
        }
        if(str1=="已下达"){
            toastr.warning("采购单已下达,不能重复下达");
            return;
        }
        if(str1=="已取消"){
            toastr.warning("采购单已取消,不能进行下达");
            return;
        }

        if(str1=="未下达" && (treeID!=null || treeID!="")){
            var objBusData = JSON.stringify({"PurChRd": treeID});
            request({
                url: "/Purch/SavePurchInfo",
                data: {"ExecType": "03", "BusData": objBusData}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loaddart(treeID);
                loadPage();
            });
        }

    });

    //取消
    $("#cancel").on("click",function () {
        if(treeID==null || treeID==""){
            toastr.warning("请选择左侧审核状态为已下达，再点击取消");
            return;
        }
        if(str1=="未下达"){
            toastr.warning("采购单未下达，不能取消");
            return;
        }
        if(str1=="已取消"){
            toastr.warning("采购单已取消，不能重复取消");
            return;
        }
        if(str1=="已下达" && str=="已完成"){
            toastr.warning("采购单已完成不能取消");
            return;
        }
        if(str1 == "已下达" && str=="待处理"&& (treeID!=null || treeID!="")){
            var objBusData = JSON.stringify({"PurChRd": treeID});
            request({
                url: "/Purch/SavePurchInfo",
                data: {"ExecType": "04", "BusData": objBusData}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loaddart(treeID);
                loadPage();
            });
        }

        if(str1 == "已下达" && str=="进行中" && (treeID!=null || treeID!="")){
            var objBusData = JSON.stringify({"PurChRd": treeID});
            layer.confirm('目前该单处于收货中，是否强制取消？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                    url: "/Purch/SavePurchInfo",
                    data: {"ExecType": "04", "BusData": objBusData}
                }, function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    loaddart(treeID);
                    loadPage();
                });
            });
        }
    })

    // 删除采购单
    $("#delete").on("click",function () {

        if (treeID == null ) {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            if(str1=="已下达" || str1=="已取消"){
                toastr.warning("采购单处于已下达或者已取消状态不能删除！");
                return;
            }
            var DSource = $("#hidden3").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确定要删除吗?', {
                btn: ["确认", "取消"],
            }, function () {
                var busData = {
                    "PurChRd": treeID
                };
                request({url:"/Purch/SavePurchInfo", data: {"ExecType": "01", "BusData": JSON.stringify(busData)}},function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    $("#_right").hide();
                    condition = "";
                    currentPage = 0;
                    loadPage();
                    condition="";
                });
            })
        }
    });


    //保存
    $('#save').on('click',function(){
        var str=/^\d+(\.\d+)?$/;　//非负浮点数（正浮点数 + 0）
        var PurMaData = getTableData("list4");
        var PurMaInfos = [];
        for (var i = 0; i < PurMaData.length; i++) {
            var PurMaInfo = {
                "PurChDlRd":PurMaData[i].PurChDlRd,
                "MaVerRd":PurMaData[i].MaVerRd,
                "MaCode": PurMaData[i].MaCode,
                "MaName": PurMaData[i].MaName,
                "Num": PurMaData[i].Num,
                "UnitName": PurMaData[i].UnitName,
                "AFeed": PurMaData[i].AFeed /*== "是" ? "00":"01"*/,
                "StoreRd":PurMaData[i].StoreRd
            };
            PurMaInfos.push(PurMaInfo);
        }

        //获取采购单号
        var PurChCode = $('#PurChCode').val();

        var Buyer=$("#Buyers").getseldata().UserName;
        if(Buyer==""){
            toastr.warning("采购员不能为空");
            return;
        }
        var SupName=$("#SupNames").getseldata().SupName;
        if(SupName==""){
            toastr.warning("供应商不能为空");
            return;
        }
        var SupCode= $("#SupNames").getseldata().SupCode;
        if(SupCode==""){
            toastr.warning("供应商不能为空");
            return;
        }
        //获取要求到货日期
        var ArrivalTime = $('#ArrivalTime').val();

        var pucherInfos = {
            "Buyer":Buyer,
            "SupCode":SupCode,
            "SupName":SupName,
            "Remark": PurChCode,
            "ArrivalTime": ArrivalTime,
            "PurMaInfo": PurMaInfos
        };
        if($("#ExecType").val() === "00") {
            //表格新增
            for(var i in pucherInfos.PurMaInfo ){
                var flag=str.test(pucherInfos.PurMaInfo[i].Num);
                if(flag==false){
                    toastr.warning("输入有误，"+pucherInfos.PurMaInfo[i].MaCode+"数量为正浮点数！")
                    return false;
                }

                if(pucherInfos.PurMaInfo[i].Num<=0){
                    toastr.warning("新增失败，"+pucherInfos.PurMaInfo[i].MaCode+"的数量不能小于等于0");
                    return false;
                }
                delete pucherInfos.PurMaInfo[i].PurChDlRd;
            }
            pucherInfos.PurChCode = $("#PurChCode").val();
            request({
                url: "/Purch/SavePurchInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(pucherInfos)}
            }, function (Body) {
                toastr.success("采购单信息新增成功,采购单号{" + Body.Data.PurChCode + "}");
                currentPage = 0;
                condition = "";
                $("#ExecType").val("");
                loadPage();
                condition = "";
                $("#hidden1").attr("editid", Body.Data.PurChRd);
                treeID = $("#hidden1").attr("editid");
                $("#hidden2").attr("editcode", Body.Data.PurChCode);
                loaddart(treeID);
            });
        }else{
            for(var i in pucherInfos.PurMaInfo ){
                var flag=str.test(pucherInfos.PurMaInfo[i].Num);
                if(flag==false){
                    toastr.warning("输入有误，"+pucherInfos.PurMaInfo[i].MaCode+"数量为正浮点数！")
                    return false;
                }

                if(pucherInfos.PurMaInfo[i].Num<=0){
                    toastr.warning("更新失败，"+pucherInfos.PurMaInfo[i].MaCode+"的数量不能小于等于0");
                    return false;
                }
            }
            pucherInfos.PurChRd = treeID;
            request({
                url: "/Purch/SavePurchInfo",
                data: {"ExecType": "02", "BusData": JSON.stringify(pucherInfos)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                condition = "";
                currentPage = 0;
                loadPage();
                condition="";
            })
        }
    });
    //表格删除
    $('.del1').on('click',function(){
        derow('list4');
    });

    function loaddart(treeID) {
        $("#PurChCode").attr("readonly",true);
        $("#stat").show();
        $("#status").show();
        $("#SStatus").show();
        $("#sstatus1").show();
        $("#_right").show();
        var objBusData = JSON.stringify({"PurChRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/Purch/GetPurchInfo",data: objData},function(Body){
            $("#ExecType").val("02");
            //其他
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            //
            $('#PurChCode').val(Body.Data.PurChCode);
            if(Body.Data.ArrivalTime != null && Body.Data.ArrivalTime != "" && Body.Data.ArrivalTime != undefined)
                $('#ArrivalTime').val(Body.Data.ArrivalTime.split(" ")[0]);
            $("#Buyers").showData({
                id:Body.Data.Buyer,
                name:Body.Data.Buyer,
                keyfield:"UserRd",
                fields:[
                    {"name":"UserRd"},
                    {"name":"UserName"}
                ]
            });
            $("#SupNames").showData({
                id:Body.Data.SupCode,
                name:Body.Data.SupName,
                keyfield:"SupCode",
                fields:[
                    {"name":"SupCode"},
                    {"name":"SupName"}
                ]
            });

            if("00"==Body.Data.Status){
                str="待处理";
            }
            if("01"==Body.Data.Status){
                str="进行中";
            }
            if("02"==Body.Data.Status){
                str="已完成";
            }
            if("03"==Body.Data.Status){
                str="已取消";
            }
            $("#status").text(str);

            if("00"==Body.Data.SStatus){
                str1="未下达";
            }
            if("01"==Body.Data.SStatus){
                str1="已下达";
            }
            if("02"==Body.Data.SStatus){
                str1="已取消";
            }
            $("#sstatus1").text(str1);
            if(str1=="已下达" || str1=="已取消"){
                $("#maInfoButton").css("display","none");
                $("#ArrivalTime").attr("readonly",true);
            }else {
                $("#maInfoButton").css("display","block");
                $("#ArrivalTime").attr("readonly",false);
            }

            var PurMaData = Body.Data.PurChDlInfo;

            addid = 1;
            var PurMaInfos = [];
            for (var i = 0; i < PurMaData.length; i++) {
                var PurMaInfo = {
                    "addid": addid++,
                    "PurChDlRd":PurMaData[i].PurChDlRd,
                    "MaVerRd":PurMaData[i].MaInfo.MaVerRd,
                    "MaCode": PurMaData[i].MaInfo.MaCode,
                    "MaName": PurMaData[i].MaInfo.MaName,
                    "MaDes": PurMaData[i].MaInfo.MaDes,
                    "AFeed": PurMaData[i].AFeed,
                    "AFeedName":PurMaData[i].AFeed === "00" ? "是":"否",
                    "StoreRd": PurMaData[i].StoreInfo == null ? "":PurMaData[i].StoreInfo.StoreRd,
                    "StoreName": PurMaData[i].StoreInfo == null ? "":PurMaData[i].StoreInfo.StoreName,
                    "Num": PurMaData[i].Num,
                    "UnitName": PurMaData[i].UnitName
                };
                PurMaInfos.push(PurMaInfo);
            }
            config1.data = PurMaInfos;
            fullTable(config1);
            trDBLClick();
        });
    }
    //物料的搜索
    var params_ma = {
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
        "event": {
            "onformatval":function(data){
                return data.MaCode + "-" + data.MaName;
            },
            "onseardata": function (o) {
                var _InitData = {
                    "FiledList":[
                        {
                            "FieldName":"CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"MaterialType",
                            "FieldOpt":"in",
                            "FieldVal":"(02,03)"
                        }
                    ]
                };
                var datas = [];
                Materialinfo(_InitData,"",datas);
                var obj = {
                    data: datas,
                    showrow: 500
                };
                return obj;
            },
            "onclick":function (res) {
                request({url:"/Commom/GetCMWFInfo",async:true,data:{"ExecType":"00","BusData":JSON.stringify({"MVerRd":res.MaVerRd})}},function(Body){
                    $unit.html(Body.Data.MaInfo.UnitInfo == null ?"":Body.Data.MaInfo.UnitInfo.UnitName);
                })
            }
        }
    };
    $maName.zc_select(params_ma);

    //收料仓的搜索
    var params_store = {
        "displaymode": "0",
        "title": "收料仓",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "收料仓id",
                    "name": "StoreRd"
                }, {
                    "caption": "收料仓名称",
                    "name": "StoreName"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var _InitData = {
                    "FiledList":[
                        {
                            "FieldName":"StoreName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var datas = [];
                request({url:"/Store/GetAllStoreInfo",data:{"ExecType":"00","InitData":JSON.stringify(_InitData)}},function(Body){
                    for(var i in Body.Data){
                        var data = {
                            "StoreRd":Body.Data[i].StoreRd,
                            "StoreName":Body.Data[i].StoreName
                        };
                        datas.push(data);
                    }
                });
                var obj = {
                    data: datas,
                    showrow: 500
                };
                return obj;
            }
        }
    };
    $storeName.zc_select(params_store);

    //明细的确认按钮
    $(".sure2").on("click",function () {
        var MaVerRd = $(this).getseldata().MaVerRd;
        var MaCode = $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode;
        var MaName = $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split(($(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode) + "-")[0];
        var MaDes = $(this).getseldata().MaDes == null ? _MaDes:$(this).getseldata().MaDes;
        var AFeed = $JGL.find("option:selected").val();
        var AFeedName = $JGL.find("option:selected").val() == "00" ? "是":"否";
        var StoreRd = $(this).getseldata().StoreRd;
        var StoreName = $(this).getseldata().StoreName;
        var Num = $CGNum.val();
        var UnitName = $unit.text();
        if(MaVerRd == undefined || MaVerRd == ""){
            toastr.warning("物料名称不能为空");
            return false;
        }
        if(Num == undefined || Num == "" || Num <= 0){
            toastr.warning("采购数量不能小于等于零");
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
        }else{
            //修改表格
            var tab_TR = {
                addid: addid++,
                PurChDlRd: "",
                MaVerRd: MaVerRd,
                MaName: MaName,
                MaCode: MaCode,
                MaDes: MaDes,
                AFeed: AFeed,
                AFeedName: AFeedName,
                StoreRd: StoreRd,
                StoreName: StoreName,
                Num: Num,
                UnitName: UnitName
            };

            addSrow("list4", tab_TR);
        }

        /*if(!flag) {
            //获取里面所有的数据，添加到用料清单的表格中
            var tab_TR = {
                PurChDlRd: "",
                MaVerRd: $(this).getseldata().MaVerRd,
                MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                MaName: $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split(($(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode) + "-")[0],
                MaDes: $(this).getseldata().MaDes == null ? "":$(this).getseldata().MaDes ,
                AFeed: $JGL.find("option:selected").val(),
                AFeedName: $JGL.find("option:selected").val() == "00" ? "是":"否",
                StoreRd: $(this).getseldata().StoreRd,
                StoreName: $(this).getseldata().StoreName,
                Num: $CGNum.val(),
                UnitName: $unit.text()
            };

            for(var i in tab_TR){
                if(i == "PurChDlRd")
                    continue;
                else if(i == "MaDes")
                    continue;
                else if(i == "StoreRd")
                    continue;
                else if(i == "StoreName")
                    continue;
                else if(i == "MaName")
                    continue;
                else if(tab_TR[i] == "" || tab_TR[i] == null || tab_TR[i] == undefined){
                    toastr.warning("物料名称、采购数量有空值");
                    return false;
                }
            }

            var _flag = true;
            var tab_list4 = getTableData("list4");
            for (var i in tab_list4) {
                if (tab_list4[i].MaVerRd == tab_TR.MaVerRd) {
                    //toastr.warning(tab_TR.MaName + "，已经被添加");
                    $("#list4").find("tbody tr").each(function () {
                        if($(this).find(".MaVerRd").text() == tab_TR.MaVerRd){
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
                PurChDlRd: _PurChDlRd,
                MaVerRd: $(this).getseldata().MaVerRd,
                MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                MaName: $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split(($(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode) + "-")[1],
                MaDes: $(this).getseldata().MaDes == null ? "":$(this).getseldata().MaDes,
                AFeed: $JGL.find("option:selected").val(),
                AFeedName: $JGL.find("option:selected").val() == "00" ? "是":"否",
                StoreRd: $(this).getseldata().StoreRd,
                StoreName: $(this).getseldata().StoreName,
                Num: $CGNum.val(),
                UnitName: $unit.text()
            };
            var tab_list4 = getTableData("list4");
//
            for(var i in tab_TR){
                if(i == "PurChDlRd")
                    continue;
                else if(i == "MaDes")
                    continue;
                else if(i == "StoreRd")
                    continue;
                else if(i == "StoreName")
                    continue;
                else if(i == "MaName")
                    continue;
                else if(tab_TR[i] == "" || tab_TR[i] == null || tab_TR[i] == undefined){
                    toastr.warning("物料名称、采购数量有空值");
                    return false;
                }
            }
            $("#list4").find("tbody tr").each(function () {
                if($(this).find(".PurChDlRd").text() == _PurChDlRd){
                    $(this).find(".MaVerRd").html(tab_TR.MaVerRd);
                    $(this).find(".MaCode").html(tab_TR.MaCode);
                    $(this).find(".MaName").html(tab_TR.MaName);
                    $(this).find(".MaDes").html(tab_TR.MaDes);
                    $(this).find(".AFeed").html(tab_TR.AFeed);
                    $(this).find(".AFeedName").html(tab_TR.AFeedName);
                    $(this).find(".Num").html(tab_TR.Num);
                    $(this).find(".UnitName").html(tab_TR.UnitName);
                    $(this).find(".StoreRd").html(tab_TR.StoreRd);
                    $(this).find(".StoreName").html(tab_TR.StoreName);
                    return false;
                }
            });*/
        //}
       /* //隐藏当前tab
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");*/
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
        $storeName.clearseldata("StoreRd");
        $storeName.clearseldata("StoreName");
        $CGNum.val("");
        $unit.html("");
        //所有内容都可编辑
        $(".sure2").css("display","-webkit-box");
        $("#tab_4").find("input").each(function () {
            $(this).prop("readonly",false);
        });
        $("#tab_4").find("select").prop("disabled",false);
        $("#tab_4").find("img").css("display","inline-block");

        trDBLClick();
    });
    //明细上面的X
    $(".XH").on("click",function () {
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
    });
    trDBLClick();
    function trDBLClick() {
        $("#list4").find("tbody").on("dblclick", "tr", function () {
            if(str1=="已下达" || str1=="已取消"){
                /*$(".sure2").hide();
                str1="";*/
                toastr.warning("采购单已下达或取消，不允许编辑");
                return false;
            }
            resultFlag = false;
            //查看
            $tab_4_li.css("display","block").addClass("active").siblings().removeClass("active");
            $tab_4.addClass("active").siblings().removeClass("active");
            //获取当前双击表格的数据

            _PurChDlRd = $(this).find(".PurChDlRd").text();
            if(_PurChDlRd == ""){
                flag = false;
            }else{
                flag = true;
            }

            var _MaVerRd = $(this).find(".MaVerRd").text();
            _MaCode = $(this).find(".MaCode").text();
            var _MaName = $(this).find(".MaName").text();
            _MaDes = $(this).find(".MaDes").text();
            var _StoreRd = $(this).find(".StoreRd").text();
            var _StoreName = $(this).find(".StoreName").text();
            var _Num = $(this).find(".Num").text();
            var _UnitName = $(this).find(".UnitName").text();
            modifytableid = $(this).find(".addid").text();

            $maName.showData1({
                id:_MaVerRd,
                name:_MaCode + "-" + _MaName,
                name1: [_MaVerRd, _MaCode, _MaName, _MaDes],
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
                    }
                ]
            });
            $storeName.showData({
                id:_StoreRd,
                name:_StoreName,
                keyfield:"SpecVerRd",
                fields:[
                    {"name":"SpecVerRd"},
                    {"name":"SpecName"}
                ]
            });
            var jgl = $(this).find(".AFeed").text();
            $JGL.find("option").each(function () {
                if($(this).val() == jgl){
                    $(this).prop("selected",true).siblings().prop("selected",false);
                    return false;
                }
            });
            $unit.html(_UnitName);
            $CGNum.val(_Num);
        });
    }

    //筛选
    var params = [ {
        "caption": "采购单号",
        "name": "PurChCode",
        "valtype": "00"
    }, {
        "caption": "状态",
        "name": "Status",
        "valtype": "01",
        "data": "\"00\":\"待处理\"|\"01\":\"处理中\"|\"02\":\"已完成\""
    }, {
        "caption": "时间段",
        "name": "CreateTime",
        "valtype": "02"
    }];
    var InitData1={};
    var event = {
        onsure: function (result) {
            var FiledList = [];
            for (var i in result) {
                for (var j in result[i]) {
                    if (result[i][j].trim() != "" && result[i][j] != null) {
                        if (j == "CreateTime") {
                            var Filed = {
                                "FieldName": j,
                                "FieldOpt": ">=",
                                "FieldVal": result[i][j].split("|")[0]
                            };
                            FiledList.push(Filed);
                            Filed = {
                                "FieldName": j,
                                "FieldOpt": "<=",
                                "FieldVal": result[i][j].split("|")[1] + " 23:59:59"
                            };
                            FiledList.push(Filed);
                            break;
                        }
                        var Filed = {
                            "FieldName": j,
                            "FieldOpt": "like",
                            "FieldVal": "%" + result[i][j] + "%"
                        };
                        FiledList.push(Filed);
                    }
                }
            }
            var newTree=[];
            InitData1 = {
                FiledList:FiledList
            }
            request({url: '/Purch/GetAllPurchInfo', data: {"ExecType": "00","InitData": JSON.stringify(InitData1)}}, function (Body) {
                currentPage = 0;
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].PurChRd == undefined ? "" : treeData[i].PurChRd,
                        name: treeData[i].PurChCode == undefined ? "" : treeData[i].PurChCode
                    };
                    newTree.push(tree);
                }
                config.data.source = newTree;
                $.JstreeEx.init(config);//先调用后加载
            })
            delete InitData1.FiledList['PurChCode'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);

});
