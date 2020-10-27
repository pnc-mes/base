$(function () {
    //树的点击事件
    var RetRd="";
    var exstatus = "";
    var SStatus="";
    var onClicks = function (nodeinfo, handle) {
        $("#tuiLOrder").prop("readonly",true);
        $(".right").show();
        $(".statu").show();
        $(".SStatus").show();
        $("#remove").attr("a", nodeinfo.nodeID);
        RetRd=nodeinfo.nodeID;
        var BusData = {"RetRd": RetRd};
        //this do update
        $(".cSave").attr("b", nodeinfo.nodeID);
        $(".cAdd").attr("a", "01");

        request({url: "/RetMa/GetRMInfo",data: {
                "ExecType": "00",
                "BusData": JSON.stringify(BusData),
            }}, function (Body) {
            $("#tuiLOrder").val(Body.Data.RetCode);
            $("#jobNumber").showData({
                id:Body.Data.AssCode,
                name:Body.Data.AssCode,
                keyfield:"rd",
                fields:[
                    {"name":"rd"},
                    {"name":"WoCode"}
                ]
            });
            console.log($("#jobNumber").getseldata())
            $("#jobNumber").find("input").attr("disabled",true);
            $("#jobNumber").find(".defaultImg").css("display","none");
            //$("#jobNumber_text").val(res.Body.Data.AssCode);

            if ("00" == Body.Data.ExStatus) {
                exstatus = "待执行";
            }
            if ("01" == Body.Data.ExStatus) {
                exstatus = "进行中";
            }
            if ("02" == Body.Data.ExStatus) {
                exstatus = "已完成";
            }
            if ("03" == Body.Data.ExStatus) {
                exstatus = "已取消";
            }
            $(".status").text(exstatus);

            if ("00" == Body.Data.SStatus) {
                SStatus = "未下达";
            }
            if ("01" == Body.Data.SStatus) {
                SStatus = "已下达";
            }
            if ("02" ==Body.Data.SStatus) {
                SStatus = "已取消";
            }
            $(".SStatus1").text(SStatus);

            if(SStatus=="已下达" || SStatus=="已取消"){
                $(".rmtab").hide();
                colNamesArr = [
                    {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
                    {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
                    {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
                    {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                    {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                    {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
                    {"Caption": "实退数量", "Name": "SRetNum", "Editable":false, "CType": "text",Width:80},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
                ];
            }else {

                colNamesArr = [
                    {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
                    {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
                    {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
                    {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                    {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                    {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
                    {"Caption": "实退数量", "Name": "SRetNum", "Editable":true, "CType": "text",Width:80},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
                ];
                $(".rmtab").show();
            }

            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            var config2 = {
                tableId: "list4",
                data: Body.Data.RetDlInfo,
                colArr: colNamesArr,
                multiselect: true,
                width:0.84,
                height: 0.57
            };
            fullTable(config2);
        });
       /* $.ajax({
            url: getBasePath() + "/RetMa/GetRMInfo",
            type: "POST",
            data: {
                "ExecType": "00",
                "BusData": JSON.stringify(BusData),
            },
            success: function (res) {
                $("#tuiLOrder").val(res.Body.Data.RetCode);
                $("#jobNumber").showData({
                    id:res.Body.Data.AssCode,
                    name:res.Body.Data.AssCode,
                    keyfield:"rd",
                    fields:[
                        {"name":"rd"},
                        {"name":"WoCode"}
                    ]
                });
                console.log($("#jobNumber").getseldata())
                $("#jobNumber").find("input").attr("disabled",true);
                $("#jobNumber").find(".defaultImg").css("display","none");
                //$("#jobNumber_text").val(res.Body.Data.AssCode);

                if ("00" == res.Body.Data.ExStatus) {
                    exstatus = "待执行";
                }
                if ("01" == res.Body.Data.ExStatus) {
                    exstatus = "进行中";
                }
                if ("02" == res.Body.Data.ExStatus) {
                    exstatus = "已完成";
                }
                if ("03" == res.Body.Data.ExStatus) {
                    exstatus = "已取消";
                }
                $(".status").text(exstatus);

                if ("00" == res.Body.Data.SStatus) {
                    SStatus = "未下达";
                }
                if ("01" == res.Body.Data.SStatus) {
                    SStatus = "已下达";
                }
                if ("02" == res.Body.Data.SStatus) {
                    SStatus = "已取消";
                }
                $(".SStatus1").text(SStatus);

                if(SStatus=="已下达" || SStatus=="已取消"){
                    $(".rmtab").hide();
                    colNamesArr = [
                        {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
                        {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
                        {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
                        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                        {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
                        {"Caption": "实退数量", "Name": "SRetNum", "Editable": false, "CType": "text",Width:80},
                        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
                    ];
                }else {
                    $(".rmtab").show();
                }

                $("#creatPeople").val(res.Body.Data.Creator);
                $("#creatTime").val(res.Body.Data.CreateTime);
                $("#modifyPeople").val(res.Body.Data.LastModifyMan);
                $("#modifyTime").val(res.Body.Data.LastModifyTime);
                $("#beizhu").val(res.Body.Data.Remark);
                var config2 = {
                    tableId: "list4",
                    data: res.Body.Data.RetDlInfo,
                    colArr: colNamesArr,
                    multiselect: true,
                    width:0.84,
                    height: 0.57
                };
                fullTable(config2);
            }
        });*/
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
        currentPage = treeSearchs("/RetMa/GetAllRMInfo","RetRd","RetCode","RetCode",condition,currentPage,config);
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
        currentPage = treeSearchs("/RetMa/GetAllRMInfo","RetRd","RetCode","RetCode",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    var list=[];
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/RetMa/GetAllRMInfo","RetRd","RetCode","RetCode",condition,currentPage,config,list);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/RetMa/GetAllRMInfo","RetRd","RetCode","RetCode",condition,currentPage,config,list);
    });

    $(".right").hide();
    //默认加载表头
    var colNamesArr = [
        {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
        {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
        {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
        {"Caption": "实退数量", "Name": "SRetNum", "Editable": true, "CType": "text",Width:80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
    ];
    var config2 = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width:0.84,
        height: 0.57
    };
    fullTable(config2);

    var params2 = {
        "displaymode": "1",
        "title": "工单",
        "binddata": {
            "keyfield": "WoRd",
            "fields": [
                {
                    "caption": "工单ID",
                    "name": "WoRd"
                }, {
                    "caption": "工单号",
                    "name": "WoCode"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                //根据工单ID查询产品信息
                var woCode = res.WoCode;
                var AssSource = "00";
                var busdata = {
                    "AssCode": woCode,
                    "AssSource": "00"
                };
                request({
                        url: '/RetMa/GetRMTotalInfo', async: true,
                        data: {"ExecType": "00", "BusData": JSON.stringify(busdata)}
                    },
                    function (Body) {
                        for (var i in Body.Data) {
                            Body.Data[i].SRetNum = Body.Data[i].RetNum
                        }

                        if (Body.Data == null || Body.Data.length <= 0) {
                            var config2 = {
                                tableId: "list4",
                                data: [],
                                colArr: colNamesArr,
                                multiselect: true,
                                width:0.84,
                                height: 0.57
                            };
                            fullTable(config2);
                        }
                        var config2 = {
                            tableId: "list4",
                            data: Body.Data,
                            colArr: colNamesArr,
                            multiselect: true,
                            width:0.84,
                            height: 0.57
                        };
                        fullTable(config2);
                    });
            },
            "onseardata": function (o) {

                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"WoCode",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"WoCode",
                            "FieldOpt":"Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };

                var xldata=[];
                request({url: "/WO/GetAllWOInfo", data: {"ExecType": "00","PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}}, function (Body) {
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
    };
    $("#jobNumber").zc_select(params2);

    //获取生产退料列表信息

    var loadTree = function () {
        var trees1 = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        var InitData = {
            "FiledList":[
               {
                    "FieldName": "CreateTime DESC",
                    "FieldOpt": "Order BY"
                }
            ]
        };
        request({
            url: '/RetMa/GetAllRMInfo',
            data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo": JSON.stringify(pageInfo)}
        }, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].RetRd == undefined ? "" : treeData[i].RetRd,
                    name: treeData[i].RetCode == undefined ? "" : treeData[i].RetCode
                }
                trees1.push(tree);
            }
            config.data.source = trees1;
            $.JstreeEx.init(config);//先调用后加载
        })
    };
    //删除事件
    $("#remove").click(function () {
        var RetRd = $("#remove").attr("a");
        if (RetRd != undefined && RetRd != null && RetRd != "") {
            if(SStatus=="已下达" || SStatus=="已取消"){
                toastr.warning("退料单审核状态为已下达或者已取消，不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/RetMa/SaveRMInfo",
                        data: {"ExecType": "01", "busData": "{\"RetRd\":" + RetRd + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        $("#remove").attr("a", "");
                        toastr.success(Body.MsgDes);
                        currentPage = 0;
                        condition = "";
                        loadTree();

                        $(".right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    loadTree();
    //新增
    $(".cAdd").click(function () {
        $("#tuiLOrder").prop("readonly",false);
        $(".right").show();
        $(".rmtab").show();
        $(".cAdd").attr("a", "00");
        $("#ExecType").val("00");
        $("#tuiLOrder").val("");
        $("#jobNumber").clearseldata();
        $("#jobNumber").find("input").attr("disabled",false);
        $("#jobNumber").find(".defaultImg").css("display","block");
        $(".status").text("");
        $(".statu").hide();
        $(".SStatus1").text("");
        $(".SStatus").hide();
        $("#beizhu").val("");
        RetRd=null;
        colNamesArr = [
            {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
            {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
            {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
            {"Caption": "物料名称", "Name": "MaName", "Editable": false},
            {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
            {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
            {"Caption": "实退数量", "Name": "SRetNum", "Editable":true, "CType": "text",Width:80},
            {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
        ];
        var config2 = {
            tableId: "list4",
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width:0.84,
            height: 0.57
        };
        fullTable(config2);
    });

    //下达
    $("#xiada").on("click",function () {
        if(RetRd==null || RetRd==""){
            toastr.warning("请选择左侧审核状态为未下达，再点击下达!");
            return;
        }
        if(SStatus=="已下达"){
            toastr.warning("退料单已下达,不能重复下达");
            return;
        }
        if(SStatus=="已取消"){
            toastr.warning("退料单已取消,不能进行下达");
            return;
        }
        if(SStatus=="未下达" && (RetRd!=null || RetRd!="")){
            var objBusData = JSON.stringify({"RetRd": RetRd});
            request({
                url: "/RetMa/SaveRMInfo",
                data: {"ExecType": "03", "BusData": objBusData}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loaddart(RetRd);
                loadTree();
            });
        }

    });

    //取消
    $("#cancel").on("click",function () {
        if(RetRd==null || RetRd==""){
            toastr.warning("请选择左侧审核状态为已下达，再点击取消!");
            return;
        }
        if(SStatus=="未下达"){
            toastr.warning("退料单未下达，不能取消");
            return;
        }
        if(SStatus=="已取消"){
            toastr.warning("退料单已取消，不能重复取消");
            return;
        }
        if(SStatus=="已下达" && exstatus=="已完成"){
            toastr.warning("退料单已完成不能取消");
            return;
        }

        if(SStatus == "已下达" && exstatus=="待执行" && (RetRd!=null || RetRd!="")){
            var objBusData = JSON.stringify({"RetRd": RetRd});
            request({
                url: "/RetMa/SaveRMInfo",
                data: {"ExecType": "04", "BusData": objBusData}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loaddart(RetRd);
                loadTree();
            });
        }

        if(SStatus == "已下达" && exstatus=="进行中" && (RetRd!=null || RetRd!="")){
            var objBusData = JSON.stringify({"RetRd": RetRd});
            layer.confirm('目前该单处于退货中，是否强制取消？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                    url: "/RetMa/SaveRMInfo",
                    data: {"ExecType": "04", "BusData": objBusData}
                }, function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    loaddart(RetRd);
                    loadTree();
                });
            });
        }
    })


    //保存
    $(".cSave").click(function () {
        var RetCode = $("#tuiLOrder").val();
        var AssCode = $("#jobNumber").getseldata().WoCode;
        var AssSource = "00";
        var RetDlInfodata = getTableData("list4");
        var RetDlInfo=[];
        for (var i = 0; i < RetDlInfodata.length; i++) {
            var PurMaInfos = {
                "RetDtlRd":RetDlInfodata[i].RetDtlRd,
                "MaVerRd":RetDlInfodata[i].MaVerRd,
                "MaCode": RetDlInfodata[i].MaCode,
                "MaName": RetDlInfodata[i].MaName,
                "RetNum": RetDlInfodata[i].RetNum,
                "SRetNum": RetDlInfodata[i].SRetNum,
                "UnitName": RetDlInfodata[i].UnitName
            };
            RetDlInfo.push(PurMaInfos);
        }

        var data = {
            "RetCode": RetCode,
            "AssCode": AssCode,
            "AssSource": "00",
            "RetDlInfo": RetDlInfo
        }
        //var RetRd = $(".cSave").attr("b");
        //新增保存
        if ((RetRd=="" || RetRd==null) && $("#ExecType").val() == "00") {

            if (AssCode == null) {
                toastr.warning("新增失败，工单号不能为空");
                return false;
            }

            if (RetDlInfo.length <= 0) {
                toastr.warning("明细不能为空");
                return false;
            }
            for (var i in data.RetDlInfo) {
                if (parseFloat(data.RetDlInfo[i].SRetNum) > parseFloat(data.RetDlInfo[i].RetNum)) {
                    toastr.warning("新增失败！" + data.RetDlInfo[i].MaCode + "的实退数量:" + data.RetDlInfo[i].SRetNum + " 应该小于应退数量:" + data.RetDlInfo[i].RetNum);
                    return false;
                }
                delete data.RetDlInfo[i].RetDtlRd;
                delete data.RetDlInfo[i].MaCode;
                delete data.RetDlInfo[i].MaName;
                delete data.RetDlInfo[i].RetNum;
                delete data.RetDlInfo[i].MaDes;
            }

            request({
                url: "/RetMa/SaveRMInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(data)}
            }, function (Body) {
                layer.closeAll("dialog");
                $(".cAdd").attr("a", "")
                toastr.success("退料信息新增成功,退料单号{" + Body.Data.RetCode + "}");
                currentPage = 0;
                condition = "";
                loadTree();

                $("#hidden1").attr("editid", Body.Data.RetRd);
                RetRd=$("#hidden1").attr("editid");
                loaddart(RetRd);
            });
        }
        else if (RetRd!=null && RetRd!="") {
            if (AssCode == null) {
                toastr.warning("更新失败，工单号号不能为空");
                return false;
            }
           /* var RetDlInfo1 = getTableData("list4");*/
            if(RetDlInfo==""){
                toastr.warning("明细不能为空");
                return false;
            }
            for (var i in data.RetDlInfo) {
                if (parseFloat(data.RetDlInfo[i].SRetNum) > parseFloat(data.RetDlInfo[i].RetNum)) {
                    toastr.warning("新增失败！" + data.RetDlInfo[i].MaCode + "的实退数量:" + data.RetDlInfo[i].SRetNum + " 应该小于应退数量:" + data.RetDlInfo[i].RetNum);
                    return false;
                }
                delete data.RetCode;
                delete data.AssCode;
                delete data.AssSource;
                delete data.RetDlInfo[i].MaCode;
                delete data.RetDlInfo[i].MaName;
                delete data.RetDlInfo[i].RetNum;
                delete data.RetDlInfo[i].MaDes;
            }
            data.RetRd=RetRd;
            request({
                url: "/RetMa/SaveRMInfo",
                data: {"ExecType": "02", "busData": JSON.stringify(data)}
            }, function (Body) {
                layer.closeAll("dialog");
                $(".cAdd").attr("a", "")
                toastr.success(Body.MsgDes);
                currentPage = 0;
                condition = "";
                loadTree();

            });
        }
    });

    //表格的删除
    $(".rmtab").click(function () {
        derow('list4');
    });

   function loaddart(RetRd){
       $("#tuiLOrder").prop("readonly",true);
       $(".right").show();
       $(".statu").show();
       $(".SStatus").show();
       $("#remove").attr("a", RetRd);
       var BusData = {"RetRd": RetRd};

       request({
           url: "/RetMa/GetRMInfo",
           data: {"ExecType": "00", "BusData": JSON.stringify(BusData)}
       }, function (Body) {
           $("#tuiLOrder").val(Body.Data.RetCode);
           $("#jobNumber").showData({
               id:Body.Data.AssCode,
               name:Body.Data.AssCode,
               keyfield:"rd",
               fields:[
                   {"name":"rd"},
                   {"name":"WoCode"}
               ]
           });
           console.log($("#jobNumber").getseldata())
           $("#jobNumber").find("input").attr("disabled",true);
           $("#jobNumber").find(".defaultImg").css("display","none");
           //$("#jobNumber_text").val(res.Body.Data.AssCode);

           if ("00" == Body.Data.ExStatus) {
               exstatus = "待执行";
           }
           if ("01" == Body.Data.ExStatus) {
               exstatus = "进行中";
           }
           if ("02" == Body.Data.ExStatus) {
               exstatus = "已完成";
           }
           if ("03" == Body.Data.ExStatus) {
               exstatus = "已取消";
           }
           $(".status").text(exstatus);

           if ("00" == Body.Data.SStatus) {
               SStatus = "未下达";
           }
           if ("01" == Body.Data.SStatus) {
               SStatus = "已下达";
           }
           if ("02" == Body.Data.SStatus) {
               SStatus = "已取消";
           }
           $(".SStatus1").text(SStatus);

           if(SStatus=="已下达" || SStatus=="已取消"){
               $(".rmtab").hide();
               colNamesArr = [
                   {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
                   {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
                   {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
                   {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                   {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                   {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
                   {"Caption": "实退数量", "Name": "SRetNum", "Editable": false, "CType": "text",Width:80},
                   {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
               ];
           }else {

               colNamesArr = [
                   {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
                   {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
                   {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
                   {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                   {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                   {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
                   {"Caption": "实退数量", "Name": "SRetNum", "Editable":true, "CType": "text",Width:80},
                   {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
               ];
               $(".rmtab").show();
           }

           $("#creatPeople").val(Body.Data.Creator);
           $("#creatTime").val(Body.Data.CreateTime);
           $("#modifyPeople").val(Body.Data.LastModifyMan);
           $("#modifyTime").val(Body.Data.LastModifyTime);
           $("#beizhu").val(Body.Data.Remark);
           var config2 = {
               tableId: "list4",
               data: Body.Data.RetDlInfo,
               colArr: colNamesArr,
               multiselect: true,
               width:0.84,
               height: 0.57
           };
           fullTable(config2);
       });


       /*$.ajax({
           url: getBasePath() + "/RetMa/GetRMInfo",
           type: "POST",
           data: {
               "ExecType": "00",
               "BusData": JSON.stringify(BusData),
           },
           success: function (res) {
               $("#tuiLOrder").val(res.Body.Data.RetCode);
               $("#jobNumber").showData({
                   id:res.Body.Data.AssCode,
                   name:res.Body.Data.AssCode,
                   keyfield:"rd",
                   fields:[
                       {"name":"rd"},
                       {"name":"WoCode"}
                   ]
               });
               console.log($("#jobNumber").getseldata())
               $("#jobNumber").find("input").attr("disabled",true);
               $("#jobNumber").find(".defaultImg").css("display","none");
               //$("#jobNumber_text").val(res.Body.Data.AssCode);

               if ("00" == res.Body.Data.ExStatus) {
                   exstatus = "待执行";
               }
               if ("01" == res.Body.Data.ExStatus) {
                   exstatus = "进行中";
               }
               if ("02" == res.Body.Data.ExStatus) {
                   exstatus = "已完成";
               }
               if ("03" == res.Body.Data.ExStatus) {
                   exstatus = "已取消";
               }
               $(".status").text(exstatus);

               if ("00" == res.Body.Data.SStatus) {
                   SStatus = "未下达";
               }
               if ("01" == res.Body.Data.SStatus) {
                   SStatus = "已下达";
               }
               if ("02" == res.Body.Data.SStatus) {
                   SStatus = "已取消";
               }
               $(".SStatus1").text(SStatus);

               if(SStatus=="已下达" || SStatus=="已取消"){
                   $(".rmtab").hide();
                   colNamesArr = [
                       {"Caption": "id", "Name": "RetDtlRd", "IsKey": true, "Hidden": true},
                       {"Caption": "id", "Name": "MaVerRd", "Hidden": true},
                       {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
                       {"Caption": "物料名称", "Name": "MaName", "Editable": false},
                       {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
                       {"Caption": "应退数量", "Name": "RetNum", "Editable": false,Width:80},
                       {"Caption": "实退数量", "Name": "SRetNum", "Editable": false, "CType": "text",Width:80},
                       {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
                   ];
               }else {
                   $(".rmtab").show();
               }

               $("#creatPeople").val(res.Body.Data.Creator);
               $("#creatTime").val(res.Body.Data.CreateTime);
               $("#modifyPeople").val(res.Body.Data.LastModifyMan);
               $("#modifyTime").val(res.Body.Data.LastModifyTime);
               $("#beizhu").val(res.Body.Data.Remark);
               var config2 = {
                   tableId: "list4",
                   data: res.Body.Data.RetDlInfo,
                   colArr: colNamesArr,
                   multiselect: true,
                   width:0.84,
                   height: 0.57
               };
               fullTable(config2);
           }
       });*/
   }

    //筛选
    var params = [{
        "caption": "工单号",
        "name": "AssCode",
        "valtype": "00"
    }, {
        "caption": "退料单号",
        "name": "RetCode",
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
            InitData1 = {
                "FiledList": FiledList
            }
            var trees1 = [];

            request({
                url: '/RetMa/GetAllRMInfo',
                data: {"ExecType": "00", "InitData": JSON.stringify(InitData1)}
            }, function (Body) {
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
                        id: treeData[i].RetRd == undefined ? "" : treeData[i].RetRd,
                        name: treeData[i].RetCode == undefined ? "" : treeData[i].RetCode
                    }
                    trees1.push(tree);
                }
                config.data.source = trees1;
                $.JstreeEx.init(config);//先调用后加载
            });
            delete InitData1.FiledList['RetCode'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);
});

