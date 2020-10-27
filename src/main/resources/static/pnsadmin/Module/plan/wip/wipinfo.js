$(function () {

    //定义表格信息
    var colNamesArr = [
        {"Caption": "RMaNDtlRd", "Name": "RMaNDtlRd", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
        {"Caption": "应入数量", "Name": "RMaNNum","Editable": false,Width:80},
        {"Caption": "实入数量", "Name": "SRMaNNum","CType": "text","Editable": true,Width:80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
    ];
    var tab_config = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height:0.64
    };
    tab_config.data.length = 0;
    fullTable(tab_config);//加载空表格

    var treeID = null;
    var str="";
    var SStatus="";
    /*方法：------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $(".h").show();
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#RMaNCode").attr("readonly",true);
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"RMaNRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/RMaN/GetRMaNInfo",data: objData},function(Body){

            $("#defaultSelect").showData({
                id:Body.Data.AssCode,
                name:Body.Data.AssCode,
                keyfield:"WoRd",
                fields:[
                    {"name":"WoRd"},
                    {"name":"WoCode"}
                ]
            });
            $("#RMaNCode").val(Body.Data.RMaNCode);
            $("#AssSource").val(Body.Data.AssSource);

            $("#hidden3").attr("h",Body.Data.DSource);
            var DSource = $("#hidden3").attr("h");

            if(DSource=="00"){
                $("#defaultSelect").prop("disabled",true);
            }else{
                $("#defaultSelect").prop("disabled",false);
            }
            // var $ExStatus = $("#ExStatus");
           // $ExStatus.attr("data-status",Body.Data.ExStatus);

            if("00" === Body.Data.ExStatus){
                str="待执行";
            }
            else if("01" === Body.Data.ExStatus){
                str="进行中";
            }
            else if("02" === Body.Data.ExStatus){
                str="已完成";
            }
            else if("03" === Body.Data.ExStatus){
                str="已取消";
            }
            $("#ExStatus").text(str);

            if("00" === Body.Data.SStatus){
                SStatus="未下达";
            }
            else if("01" === Body.Data.SStatus){
                SStatus="已下达";
            }
            else if("02" === Body.Data.SStatus){
                SStatus="已取消";
            }
            $("#SStatus").text(SStatus);

            if(SStatus=="已下达" || SStatus=="已取消"){
                colNamesArr = [
                    {"Caption": "RMaNDtlRd", "Name": "RMaNDtlRd", "Hidden": true},
                    {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
                    {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
                    {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                    {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                    {"Caption": "应入数量", "Name": "RMaNNum","Editable": false,Width:80},
                    {"Caption": "实入数量", "Name": "SRMaNNum","Editable": false,Width:80},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
                ];
            }else {
                colNamesArr = [
                    {"Caption": "RMaNDtlRd", "Name": "RMaNDtlRd", "Hidden": true},
                    {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
                    {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
                    {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                    {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                    {"Caption": "应入数量", "Name": "RMaNNum","Editable": false,Width:80},
                    {"Caption": "实入数量", "Name": "SRMaNNum","Editable": true,Width:80},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
                ];
            }

            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            // 对表格进行显示相应数据
            tab_config.colArr=colNamesArr;
            tab_config.data = Body.Data.RMaNDlInfo;
            fullTable(tab_config);
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
        currentPage = treeSearchs("/RMaN/GetAllRMaNInfo","RMaNRd","RMaNCode","RMaNCode",condition,currentPage,config);
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
        currentPage = treeSearchs("/RMaN/GetAllRMaNInfo","RMaNRd","RMaNCode","RMaNCode",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/RMaN/GetAllRMaNInfo","RMaNRd","RMaNCode","RMaNCode",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/RMaN/GetAllRMaNInfo","RMaNRd","RMaNCode","RMaNCode",condition,currentPage,config);
    });

    $("#_right").hide();



    //加载工单
    var params = {
        "displaymode": "1",
        "title": "工单号",
        "binddata": {
            "keyfield": "WoRd",
            "fields": [
                {
                    "caption": "工单号id",
                    "name": "WoRd"
                }, {
                    "caption": "工单号",
                    "name": "WoCode"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onclick":function (res) {

                var colNamesArr = [
                    {"Caption": "RMaNDtlRd", "Name": "RMaNDtlRd", "Hidden": true},
                    {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
                    {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
                    {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                    {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                    {"Caption": "应入数量", "Name": "RMaNNum","Editable": false,Width:80},
                    {"Caption": "实入数量", "Name": "SRMaNNum","Editable": true,Width:80},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
                ];
                var tab_config = {
                    tableId: 'list4',
                    data: [],
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height:0.64
                };
                fullTable(tab_config);//加载空表格

                var woCode = res.WoCode;

                request({url: '/RMaN/GetRMaNTotalInfo',
                        data: {"ExecType": "00","BusData":JSON.stringify({"AssCode": woCode,"AssSource":"00"})}},
                    function (Body) {
                        for(var i in Body.Data){
                            Body.Data[i].RMaNDtlRd = "";
                            Body.Data[i].SRMaNNum = Body.Data[i].RMaNNum;
                            Body.Data[i].MaDes = Body.Data[i].MaDes;
                        }
                        tab_config.data = Body.Data;
                        fullTable(tab_config);
                    });
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "Status",
                            "FieldOpt": "=",
                            "FieldVal": "03"
                        },
                        {
                            "FieldName":"WoCode",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"WoCode",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };

                var xldata = [];
                request({url:"/WO/GetAllWOInfo",data:{"ExecType":"00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WoRd": datas[i].WoRd,
                            "WoCode": datas[i].WoCode,
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
    $("#defaultSelect").zc_select(params);


    //加载所有的树，并加载第一条信息
    var loadPage = function(){
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        var  InitData = {
                "FiledList": [
                    {
                        "FieldName": "CreateTime DESC",
                        "FieldOpt": "order by",
                    }
                ]
            };

        request({url: '/RMaN/GetAllRMaNInfo',data: {"ExecType": "00","InitData": JSON.stringify(InitData),"PageInfo": JSON.stringify(pageInfo)}}, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].RMaNRd == undefined ? "" : treeData[i].RMaNRd,
                    name: treeData[i].RMaNCode == undefined ? "" : treeData[i].RMaNCode
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        })
    };
    // 调用，首次加载页面
    loadPage();

//点击新增
    $("#addWip").on("click",function () {
        //1.清空页面数据
        clearForm("wipForm");
        $("#defaultSelect").clearseldata("WoRd");
        $("#RMaNCode").attr("readonly",false);
        $("#defaultSelect").prop("disabled",false);

        $("#ExecType").val("00");
        $("#ExStatus").text("待执行");
        $("#ExStatus").attr("data-status","00");
        $("#SStatus").text("未下达");
        $("#SStatus").attr("data-status","00");
        SStatus="未下达";
        $(".h").hide();
        treeID = null;
        $("#_right").show();
        var colNamesArr = [
            {"Caption": "RMaNDtlRd", "Name": "RMaNDtlRd", "Hidden": true},
            {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
            {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
            {"Caption": "物料名称", "Name": "MaName", "Editable": false},
            {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
            {"Caption": "应入数量", "Name": "RMaNNum","Editable": false,Width:80},
            {"Caption": "实入数量", "Name": "SRMaNNum","Editable": true,Width:80},
            {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
        ];
        var tab_config = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height:0.64
        };
        fullTable(tab_config);//加载空表格
    });

    //下达
    $("#xiada").on("click",function () {
        if(treeID==null || treeID==""){
            toastr.warning("请选择左侧审核状态为未下达，再点击下达!");
            return;
        }
        if(SStatus=="已下达"){
            toastr.warning("产成品入库单已下达,不能重复下达");
            return;
        }
        if(SStatus=="已取消"){
            toastr.warning("产成品入库单已取消,不能进行下达");
            return;
        }
        if(SStatus=="未下达" && (treeID!=null || treeID!="")){
            var objBusData = JSON.stringify({"RMaNRd": treeID});
            request({
                url: "/RMaN/SaveRMaNInfo",
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
        if(SStatus=="未下达"){
            toastr.warning("产成品入库单未下达，不能取消");
            return;
        }
        if(SStatus=="已取消"){
            toastr.warning("产成品入库单已取消，不能重复取消");
            return;
        }
        if(SStatus=="已下达" && str=="已完成"){
            toastr.warning("产成品入库单已完成不能取消");
            return;
        }
        if(SStatus=="已下达" && str=="待执行"&& (treeID!=null || treeID!="")){
            var objBusData = JSON.stringify({"RMaNRd": treeID});
            request({
                url: "/RMaN/SaveRMaNInfo",
                data: {"ExecType": "04", "BusData": objBusData}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loaddart(treeID);
                loadPage();
            });

        }

        if(SStatus == "已下达" && str=="进行中" && (treeID!=null || treeID!="")){
            var objBusData = JSON.stringify({"RMaNRd": treeID});
            layer.confirm('目前该单处于收货中，是否强制取消？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                    url: "/RMaN/SaveRMaNInfo",
                    data: {"ExecType": "04", "BusData": objBusData}
                }, function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    loaddart(treeID);
                    loadPage();
                });
            });
        }
    });
    //点击删除
    $("#delete").on("click",function () {
        if (treeID != null) {
            if(SStatus=="已下达" || SStatus=="已取消"){
                toastr.warning("成品入库单审核状态为已下达或者已取消，不能删除");
                return;
            }
            var DSource = $("#hidden3").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    var RMaNRdObj = {
                        RMaNRd:treeID
                    };
                    request({url:"/RMaN/SaveRMaNInfo",data:{"ExecType":"01","BusData":JSON.stringify(RMaNRdObj)}},function(Body){
                        layer.closeAll("dialog");
                        $("#ExecType").val("");
                        treeID = null;
                        toastr.success(Body.MsgDes);
                        currentPage = 0;
                        condition = "";
                        loadPage();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    //保存
    $(".cSave").on("click",function () {
        //1.判断入库单号不能为空
        var retCode = $("#RMaNCode").val();

        var tabData = getTableData("list4");
        var flag = false;
        for(var i in tabData){
            if(tabData[i].SRMaNNum > tabData[i].RMaNNum){
                toastr.warning("第" + (i+1) + "行，实入数量不能大于应入数量");
                flag = true;
                break;
            }
        }
        if(flag) return;
       var AssCode=$("#defaultSelect").getseldata().WoCode;

        if($("#ExecType").val() == "00" && AssCode!=""){
            var busData = {
                "RetCode": retCode,
                "AssCode":AssCode ,
                "AssSource": "00"
            };
            for(var i in tabData){
                delete tabData[i].RMaNDtlRd;
                delete tabData[i].MaCode;
                delete tabData[i].MaName;
                delete tabData[i].RMaNNum;
                delete tabData[i].MaDes;
            }
            busData.RMaNDlInfo = tabData;
            request({
                    url: '/RMaN/SaveRMaNInfo', async: true,
                    data: {"ExecType": "00", "BusData": JSON.stringify(busData)}
                },
                function (Body) {
                    toastr.success("成品入库信息新增成功,成品入库单号{"+Body.Data.RMaNCode+"}");
                    $("#ExecType").val("");
                    treeID = null;
                    condition = "";
                    currentPage = 0;
                    loadPage();
                    $("#hidden1").attr("editid", Body.Data.RMaNRd);
                    treeID=$("#hidden1").attr("editid");
                    loaddart(treeID);
                }
            );
        }else if(treeID != null){
            var busData = {
                "RMaNRd": treeID
            };
            for(var i in tabData){
                delete tabData[i].MaCode;
                delete tabData[i].MaName;
                delete tabData[i].RMaNNum;
                delete tabData[i].MaDes;
            }
            busData.RMaNDlInfo = tabData;
            request({
                    url: '/RMaN/SaveRMaNInfo', async: true,
                    data: {"ExecType": "02", "BusData": JSON.stringify(busData)}
                },
                function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    treeID = null;
                    condition = "";
                    currentPage = 0;
                    loadPage();
                }
            );
        }else{
            $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
            $("#defaultSelect").find("input:eq(0)").val("");
            $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
        }
    });
    function loaddart(treeID){
        $(".h").show();
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#RMaNCode").attr("readonly",true);
        var objBusData = JSON.stringify({"RMaNRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/RMaN/GetRMaNInfo",data: objData},function(Body){

            $("#defaultSelect").showData({
                id:Body.Data.AssCode,
                name:Body.Data.AssCode,
                keyfield:"WoRd",
                fields:[
                    {"name":"WoRd"},
                    {"name":"WoCode"}
                ]
            });
            $("#RMaNCode").val(Body.Data.RMaNCode);
            $("#AssSource").val(Body.Data.AssSource);
            // var $ExStatus = $("#ExStatus");
            // $ExStatus.attr("data-status",Body.Data.ExStatus);
            if("00" === Body.Data.ExStatus){
                str="待执行";
            }
            else if("01" === Body.Data.ExStatus){
                str="进行中";
            }
            else if("02" === Body.Data.ExStatus){
                str="已完成";
            }
            else if("03" === Body.Data.ExStatus){
                str="已取消";
            }
            $("#ExStatus").text(str);

            if("00" === Body.Data.SStatus){
                SStatus="未下达";
            }
            else if("01" === Body.Data.SStatus){
                SStatus="已下达";
            }
            else if("02" === Body.Data.SStatus){
                SStatus="已取消";
            }
            $("#SStatus").text(SStatus);
            if(SStatus=="已下达" || SStatus=="已取消"){
                colNamesArr = [
                    {"Caption": "RMaNDtlRd", "Name": "RMaNDtlRd", "Hidden": true},
                    {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
                    {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
                    {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                    {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                    {"Caption": "应入数量", "Name": "RMaNNum","Editable": false,Width:80},
                    {"Caption": "实入数量", "Name": "SRMaNNum",Width:80},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
                ];
            }else {
                colNamesArr = [
                    {"Caption": "RMaNDtlRd", "Name": "RMaNDtlRd", "Hidden": true},
                    {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
                    {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
                    {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                    {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                    {"Caption": "应入数量", "Name": "RMaNNum","Editable": false,Width:80},
                    {"Caption": "实入数量", "Name": "SRMaNNum","Editable": true,Width:80},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50}
                ];
            }
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            // 对表格进行显示相应数据
            tab_config.data = Body.Data.RMaNDlInfo;
            fullTable(tab_config);
        });
    }

});