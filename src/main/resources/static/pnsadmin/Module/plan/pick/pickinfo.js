$(function () {
    var ZLNum=0;
    $("#_right").hide();
    var dSource = true;
    var treeID = null;
    var _trObj = null;//当前页面选中（点击）的tr对象
    var re_trObj = null;//替代料弹窗里面的tr对象
    var resultFlag = true;//判断表格是编辑还是新增

    var isHUiZong = true;//判断是否有对应的明细信息
    var signDetailTotal=[];//获取单个的点击明细信息
    var modifytableid = '';
    var addid = 1;

    var detailDatas=[];
    //主页面表格定义
    var colNamesArr7 = [
        {"Caption": "ces", "Name": "aaaaa", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
        {"Caption": "领用仓库id", "Name": "StoreRd",  "Hidden": true},
        {"Caption": "领料仓库", "Name": "StoreName7",  Width:80},
        {"Caption": "库存数量", "Name": "InStoreNum", Width:80},
        {"Caption": "asdsad", "Name": "StoreName", "Hidden": true},
        {"Caption": "领用数量", "Name": "UNNum", "CType": "text", "Editable": true,Width:80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
        {"Caption": "备注", "Name": "Remark", "CType": "text", "Editable": true,Width:100},
    ];
    var config7 = {
        tableId: 'list7',
        data: [],
        colArr: colNamesArr7,
        width: 0.84,
        height: 0.415
    };
    config7.data.length = 0;
    fullTable(config7);//加载空表格



    /*方法：------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#PickCode").attr("readonly",true);
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"PickRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url: "/Pick/GetPickInfo", data: objData}, function (Body) {
            $("#PickCode").val(Body.Data.PickCode);
            if(Body.Data.DSource == "00"){
                dSource = false;
            }else{
                dSource = true;
            }

            $("#UnitName").html(Body.Data.UnitName);
            $("#AssCode").showData({
                id: Body.Data.AssCode,
                name: Body.Data.AssCode,
                keyfield: "rd",
                fields: [
                    {"name": "rd"},
                    {"name": "WoCode"}
                ]
            });


            var $ExStatus = $("#ExStatus");
            $ExStatus.attr("data-status", Body.Data.ExStatus);
            if ("00" === Body.Data.ExStatus) {
                $ExStatus.val("待执行");
            }
            else if ("01" === Body.Data.ExStatus) {
                $ExStatus.val("进行中");
            }
            else if ("02" === Body.Data.ExStatus) {
                $ExStatus.val("已完成");
            }
            else if ("03" === Body.Data.ExStatus) {
                $ExStatus.val("已取消");
            }

            var $SStatus = $("#SStatus");
            $SStatus.attr("data-status", Body.Data.SStatus);
            if ("00" === Body.Data.SStatus) {
                $SStatus.val("未下达");
            }
            else if ("01" === Body.Data.SStatus) {
                $SStatus.val("已下达");
                $("#maInfoButton").hide();
            }
            else if ("02" === Body.Data.SStatus) {
                $SStatus.val("已取消");
                $("#maInfoButton").hide();
            }

            $("#tab_4_li").css("display","none").prev().addClass("active");
            $("#tab_44").removeClass("active").prev().addClass("active");

            $("#AssSource").val(Body.Data.AssSource);
            $("#MaVerRd").val(Body.Data.MaInfo.MaVerRd);
            $("#MaCode").val(Body.Data.MaInfo.MaCode);
            $("#MaName").val(Body.Data.MaInfo.MaName);
            $("#Num").val(Body.Data.Num);
            $("#UnitName").val(Body.Data.UnitName);
            $("#PrePickDate").val(Body.Data.PrePickDate);
            //$("#material_warehouse").val(Body.Data.StoreInfo == null? "":Body.Data.StoreInfo.StoreName);

            if (Body.Data.PickDlInfo.length>0) {
                signDetailTotal=Body.Data.PickDlInfo;
                $("#material_warehouse").showData({
                    id: Body.Data.PickDlInfo[0].StoreInfo.StoreRd,
                    name: Body.Data.PickDlInfo[0].StoreInfo.StoreName,
                    keyfield: "StoreRd",
                    fields: [
                        {"name": "StoreRd"},
                        {"name": "StoreName"}
                    ]
                });
            }

            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            // 对表格进行显示相应数据
            var PickDlInfo = Body.Data.PickDlInfo;
            ZLNum=0;
            addid = 1;
            for (var i in PickDlInfo) {

                if (PickDlInfo[i].ReMaInfo.length)
                    Body.Data.PickDlInfo[i].IsReMa = "<a href='javascript:void(0);' class='replace'>替换</a>";
                else
                    Body.Data.PickDlInfo[i].IsReMa = "";
                Body.Data.PickDlInfo[i].ReMaInfo = JSON.stringify(Body.Data.PickDlInfo[i].ReMaInfo);
                if(PickDlInfo[i].StoreInfo == undefined){
                    Body.Data.PickDlInfo[i].StoreName = "";
                }else{
                    Body.Data.PickDlInfo[i].StoreRd = Body.Data.PickDlInfo[i].StoreInfo.StoreRd;
                    Body.Data.PickDlInfo[i].StoreName = Body.Data.PickDlInfo[i].StoreInfo.StoreName;
                }
                Body.Data.PickDlInfo[i].addid = addid++;
            }
            config1.data = Body.Data.PickDlInfo;

            fullTable(config1);
            //a_click();
            click();
            if(Body.Data.AssCode!=null){
                var WoCode = Body.Data.AssCode;
                var WOData = {
                    "FiledList":[
                        {
                            "FieldName":"WoCode",
                            "FieldOpt":"=",
                            "FieldVal":WoCode
                        }
                    ]
                };
                request({
                        url: '/WO/GetAllWOInfo',
                        data: {"ExecType": "00","InitData":JSON.stringify(WOData)}
                    },
                    function (Body) {
                        var WoRd=Body.Data[0].WoRd
                        var busData={
                            "WoRd":WoRd
                        }
                        request({
                                url: '/WO/GetWOInfo',
                                data: {"ExecType": "00","BusData": JSON.stringify(busData)}
                            },
                            function (Body) {
                                $("#MaName").val(Body.Data.MaInfo.MaName);
                                $("#MaVerRd").val(Body.Data.MaInfo.MaVerRd);
                                $("#MaCode").val(Body.Data.MaInfo.MaCode);
                            })
                    }
                );
            }
        });

    };
    /*----------------------定义控件规则-------------------*/
    var config = {
        id: "jstree_demo1",
        data: {
            source:[],
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
        currentPage = treeSearchs("/Pick/GetAllPickInfo","PickRd","PickCode","PickCode",condition,currentPage,config);
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
        currentPage = treeSearchs("/Pick/GetAllPickInfo","PickRd","PickCode","PickCode",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })


    var list=[];
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Pick/GetAllPickInfo","PickRd","PickCode","PickCode",condition,currentPage,config,list);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Pick/GetAllPickInfo","PickRd","PickCode","PickCode",condition,currentPage,config,list);
    });


    //主页面表格定义
    var colNamesArr = [
        {"Caption": "表格的唯一一个数据", "Name": "addid", 'Hidden': true,Width:100},
        {"Caption": "PKDtlRd", "Name": "PKDtlRd", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
        {"Caption": "领用仓库", "Name": "StoreRd", "CType": "text", "Hidden": true},
        {"Caption": "领用仓库", "Name": "StoreName", "CType": "text", Width:80},
        {"Caption": "领用数量", "Name": "UNNum", "CType": "text", "Editable": true,Width:80},
        /*{"Caption": "库存数量", "Name": "StoreNum", "CType": "text", "Editable": false,Width:80},*/
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
        /*{"Caption": "替代料", "Name": "IsReMa", "CType": "text", "Editable": false,Width:50},*/
        {"Caption": "备注", "Name": "Remark", "CType": "text", Width:100},
        {"Caption": "ReMaInfo", "Name": "ReMaInfo", "Hidden": true},
        /*{"Caption": "ReML", "Name": "ReML", "Hidden": true},*/
        {"Caption": "MaVerGd", "Name": "MaVerGd", "Hidden": true}
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height: 0.415
    };
    config1.data.length = 0;
    fullTable(config1);//加载空表格

    var selectData = [];
    var woCode = "";
    var params2 = {
        "displaymode": "1",
        "title": "工单",
        "binddata": {
            "keyfield": "WoRd",
            "fields": [
                {
                    "caption": "工单id",
                    "name": "WoRd"
                }, {
                    "caption": "工单号",
                    "name": "WoCode"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (data) {
                //根据工单ID查询产品信息
                var woRd = data.WoRd;
                woCode = data.WoCode;
                request({
                        url: '/WO/GetWOInfo',
                        data: {"ExecType": "00", "BusData": JSON.stringify({"WoRd": woRd})}
                    },
                    function (Body) {
                        $("#MaVerRd").val(Body.Data.MaInfo.MaVerRd);
                        $("#MaCode").val(Body.Data.MaInfo.MaCode);
                        $("#MaName").val(Body.Data.MaInfo.MaName);
                        $("#Num").val(Body.Data.Num);
                        $("#UnitName").html(Body.Data.UnitInfo.UnitName);
                        //清空仓库信息
                        $("#material_warehouse").clearseldata("StoreRd");
                        config1.data = [];
                        fullTable(config1);//加载空表格
                    }
                );

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName":"WoCode",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                       },
                        {
                            "FieldName": "PKEFlag",
                            "FieldOpt": "=",
                            "FieldVal": "00"
                        },
                        {
                            "FieldName": "Status",
                            "FieldOpt": "in",
                            "FieldVal": "('01','03')"
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
                request({url: "/WO/GetAllWOInfo", data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}}, function (Body) {
                    selectData = Body.Data;
                    for (var i = 0; i < selectData.length; i++) {
                        var data = {
                            WoRd: selectData[i].WoRd,
                            WoCode: selectData[i].WoCode
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
    $("#AssCode").zc_select(params2);


    /*加载仓库信息【领料之后，选择一个仓库向里面放产品】*/
    var params2 = {
        "displaymode": "0",
        "title": "仓库",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "仓库id",
                    "name": "StoreRd"
                }, {
                    "caption": "仓库名称",
                    "name": "StoreName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                var storeRd = res.StoreRd;
                request({
                        url: '/BOM/GetMaTotalInfo',
                        data: {
                            "ExecType": "00", "BusData": JSON.stringify({
                                "AssCode": $("#AssCode").getseldata().WoCode,
                                "AssSource": "00",
                                "StoreRd": storeRd
                            })
                        }
                    },
                    function (Body) {
                        var ReMaInfos=Body.Data;
                        if(ReMaInfos.length>0){
                            for(var i in ReMaInfos ){
                                var aa={
                                    "MaVerRd":ReMaInfos[i].MaVerRd,
                                    "ReMaInfo":ReMaInfos[i].ReMaInfo.length>0?ReMaInfos[i].ReMaInfo:[]
                                }
                                detailDatas.push(aa);
                                /*for(var j  in  ReMaInfos[i].ReMaInfo){
                                    var detailData={
                                        MaVerRd:ReMaInfos[i].ReMaInfo[j].MaVerRd,
                                        MaCode:ReMaInfos[i].ReMaInfo[j].MaCode,
                                        MaDes:ReMaInfos[i].ReMaInfo[j].MaDes,
                                        MaName:ReMaInfos[i].ReMaInfo[j].MaName,
                                        UnitName:ReMaInfos[i].ReMaInfo[j].UnitName,
                                    }
                                    detailDatas.push(detailData);
                                }*/
                            }
                        }

                        var config1 = {
                            tableId: 'list4',
                            data: [],
                            colArr: colNamesArr,
                            multiselect: true,
                            width: 0.84,
                            height: 0.415
                        };
                        config1.data.length = 0;
                        fullTable(config1);
                        addid = 1;
                        for (var i in Body.Data) {

                            if (Body.Data[i].ReMaInfo.length) {
                                Body.Data[i].IsReMa = "<a class='replace' href='javascript:void(0);'>替换</a>";
                            } else {
                                Body.Data[i].IsReMa = "";
                            }
                            Body.Data[i].PKDtlRd = "";
                            Body.Data[i].ReMaVerRd = "";
                            Body.Data[i].ReMaInfo = JSON.stringify(Body.Data[i].ReMaInfo);
                            Body.Data[i].ReML = Body.Data[i].MaVerGd;
                            if( Body.Data[i].Remark==undefined){
                                Body.Data[i].Remark = "";
                            }
                            if(Body.Data[i].StoreInfo==undefined){
                                Body.Data[i].StoreRd = "";
                                Body.Data[i].StoreName = "";
                            }else{
                                Body.Data[i].StoreRd = Body.Data[i].StoreInfo.StoreRd;
                                Body.Data[i].StoreName = Body.Data[i].StoreInfo.StoreName;
                                Body.Data[i].UNNum = Body.Data[i].SLNum;
                            }
                            Body.Data[i].addid = addid++;
                        }

                        config1.data = Body.Data;
                        fullTable(config1);
                       // a_click();
                        click();
                    }
                );

        },
        "onseardata": function (o) {
            var InitData = {
                "FiledList": [
                    {
                        "FieldName":"StoreName",
                        "FieldOpt":"like",
                        "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                    },{
                        "FieldName":"StoreName",
                        "FieldOpt":"Order BY",
                    }
                ]
            };
            var page = {
                PageIndex: "0",
                PageSize: o.num
            };

            var xldata = [];
            request({url: '/Store/GetAllStoreInfo', data: {'ExecType': '00',"PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}}, function (Body) {
                datas = Body.Data;
                for (var i = 0; i < datas.length; i++) {
                    var data = {
                        "StoreRd": datas[i].StoreRd,
                        "StoreName": datas[i].StoreName,
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
    $("#material_warehouse").zc_select(params2);

    // 加载树
    var loadPage = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList": [
                {
                    "FieldName": "CreateTime DESC",
                    "FieldOpt": "order by",
                }
            ]
        };
        request({
            url: '/Pick/GetAllPickInfo',
            async: false,
            data: {"ExecType": "00","InitData": JSON.stringify(InitData),"PageInfo": JSON.stringify(pageInfo)}
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
                    id: treeData[i].PickRd == undefined ? "" : treeData[i].PickRd,
                    name: treeData[i].PickCode == undefined ? "" : treeData[i].PickCode
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
    $("#addPick").on("click", function () {
        addid = 1;
        //1.清空页面数据
        clearForm("pickForm");
        $("#UnitName").html('');
        //$("#UnitName_1").html('');
        config1.data.length = 0;
        fullTable(config1);
        $("#ExecType").val("00");
        $("#ExStatus").val("待执行");
        $("#SStatus").val("未下达");
        $("#AssCode").clearseldata("WoCode");
        $("#material_warehouse").clearseldata("StoreRd");
        treeID = null;
        //获取当前日期
        var d = new Date();
        var m = d.getMonth() + 1 < 12 ? "0" + (d.getMonth() + 1) : d.getMonth();
        var str = d.getFullYear() + "-" + m + "-" + d.getDate();
        $('#PrePickDate').val(str);
        $("#_right").show();
        $("#PickCode").attr("readonly",false);
        $("#tab_4_li").css("display","none").prev().addClass("active");
        $("#tab_44").removeClass("active").prev().addClass("active");
        $("#maInfoButton").show();
    });

    //点击下达
    $("#onPick").on("click", function () {
        if (treeID != null) {
            var PickRdObj = {
                PickRd: treeID
            };
            request({
                url: "/Pick/SavePickInfo",
                data: {"ExecType": "03", "BusData": JSON.stringify(PickRdObj)}
            }, function (Body) {
                //$("#ExecType").val("");
                toastr.success(Body.MsgDes);
                condition = "";
                currentPage = 0;
                $("#SStatus").val("已下达");
            });
        } else {
            toastr.warning("请选择左侧要下达的一项再进行下达!");
        }
    });

    //点击取消
    $("#offPick").on("click", function () {
        if (treeID != null) {
            var PickRdObj = {
                PickRd: treeID
            };
            request({
                url: "/Pick/SavePickInfo",
                data: {"ExecType": "04", "BusData": JSON.stringify(PickRdObj)}
            }, function (Body) {
                //$("#ExecType").val("");
                toastr.success(Body.MsgDes);
                condition = "";
                currentPage = 0;
                $("#SStatus").val("已取消");
            });
        } else {
            toastr.warning("请选择左侧要取消的一项再进行取消!");
        }
    });

    //点击删除
    $("#delete").on("click", function () {
        if (treeID != null) {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    var PickRdObj = {
                        PickRd: treeID
                    };
                    request({
                        url: "/Pick/SavePickInfo",
                        data: {"ExecType": "01", "BusData": JSON.stringify(PickRdObj)}
                    }, function (Body) {

                        $("#ExecType").val("");
                        treeID = null;
                        toastr.success(Body.MsgDes);
                        layer.closeAll("dialog");
                        condition = "";
                        currentPage = 0;
                        loadPage();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    //下面表格的删除
    $(".del1").on("click", function () {
        derow("list4");
    });

    $(".add1").click(function(){
        //清空物料消耗页面数据
        $("#Store").clearseldata("StoreRd");
        $("#Store").clearseldata("StoreName");
        $("#lymater").clearseldata("MaVerRd");
        $("#lymater").clearseldata("MaCode");
        $("#lymater").clearseldata("MaName");
        $("#lymater").clearseldata("MaDes");
        $("#ccsl").val("");
        $("#ccs2").val("");
        $("#lysl").val("");
        $("#Remark").html("");
        //所有内容都可编辑
        $(".sure2").css("display","-webkit-box");
        $("#tab_4").find("input").each(function () {
            $(this).prop("readonly",false);
        });
        //显示明细设置
        //把物料消耗的tab显示出来,并设置li的class为active,而其他的li移除active
        $("#tab_4_li").css("display","block").addClass("active").siblings().removeClass("active");
        $("#tab_44").addClass("active").siblings().removeClass("active");
        resultFlag = true;

        config7.data.length = 0;
        fullTable(config7);
        //处理替代料的下拉框


    });

    //明细上面的X
    $(".XH").on("click",function () {
        $("#tab_4_li").css("display","none").prev().addClass("active");
        $("#tab_44").removeClass("active").prev().addClass("active");
    });

    //存放仓库所有信息
    var ConmmonStoreInfo=[];
    var objs = {
        data:{"ExecType": "00"},
        url:"/Store/GetAllStoreInfo"
    };

    var select1 = function () {
        $(".StoreName7").each(function () {
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < ConmmonStoreInfo.length; i++) {
                if (current_Td_Val == ConmmonStoreInfo[i].StoreName)
                    str += "<option selected value='" + ConmmonStoreInfo[i].StoreRd + "'>" + ConmmonStoreInfo[i].StoreName + "</option>";
                else
                    str += "<option value='" + ConmmonStoreInfo[i].StoreRd + "'>" + ConmmonStoreInfo[i].StoreName + "</option>";
            }
            ;
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("width", "100px");
            $(this).find("select").css("color", "#000000");
            $(this).find("select").on("change", function () {
                for (var i = 0; i < ConmmonStoreInfo.length; i++) {
                    if (ConmmonStoreInfo[i].StoreName == $(this).find("option:selected").text()){
                        var id=$(this).parent().prev().prev().prev().prev().prev().text();
                        $(this).parent().prev().text(ConmmonStoreInfo[i].StoreRd);

                        $(this).parent().prev().text(ConmmonStoreInfo[i].StoreRd);
                        $(this).parent().next().next().text($(this).find("option:selected").text())
                        var objData = {
                            "ExecType": "01",
                            "BusData": JSON.stringify({
                                "StoreRd":ConmmonStoreInfo[i].StoreRd
                            }),
                        };
                        var num="";

                        /*------------------获取点击之后一个节点的数据------------------*/
                        request({
                            url: "/InStore/GetAllMaSTInfo",
                            data: objData
                        }, function (Body) {
                             if(Body.Data.length>0){
                                 for(var i  in  Body.Data){
                                        if(Body.Data[i].MaVerRd==id){
                                            num=Body.Data[i].Num
                                        }
                                 }
                             }else {
                                 toastr.warning("你选择的仓库下没有该行料，请重新选择仓库");
                                 return false;
                             }
                        });

                        $(this).parent().next().text(num)

                      //  $(this).parent().text($(this).find("option:selected").text())




                    }
                }

            })


        });
    }


    request(objs,function(Body) {
        var datas = Body.Data;
        for (var i = 0; i < datas.length; i++) {
            var data = {
                "StoreRd": datas[i].StoreRd,
                "StoreName": datas[i].StoreName
            };
            ConmmonStoreInfo.push(data);
        }
    });


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
                $("#ccsl").val("");
                $("#ccs2").val("");
                $("#lysl").val("");
                $("#Remark").val("");
                $("#ycyid").val("");
                $("#lymater").clearseldata("MaVerRd");
            }
        }
    };
    $("#Store").zc_select(store);

    //领用物料
    //领用数量的新增事件 即保存在一个集合里
    var insertData={};
    var xldata = [];

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
                }, {
                    "caption": "lywlid",
                    "name": "StoreNum",
                    "hidden":true
                }, {
                    "caption": "单位",
                    "name": "UnitName",
                    "hidden":true
                }, {
                    "caption": "SLNum",
                    "name": "SLNum",
                    "hidden":true
                },
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var assCode = $("#AssCode").getseldata().WoCode;

                if(assCode==""){
                    toastr.warning("工单号不能为空");
                    return false;
                }
                if(storeid==null||storeid==""||!Number(storeid)){
                    toastr.warning("操作失败，没有获取到仓库")
                    return false;
                }
                var objDate={
                    "AssCode": assCode,
                    "AssSource": "00",
                    "StoreRd":storeid
                }
                request({url: "/BOM/GetMaTotalInfo", data: {"ExecType": "00","busData": JSON.stringify(objDate)}}, function (Body) {
                    var datas = Body.Data;
                    xldata = [];
                    for (var i = 0; i < datas.length; i++) {
                        var data= {
                            "MaVerRd": datas[i].MaVerRd,
                            "MaCode": datas[i].MaCode,
                            "MaName": datas[i].MaName,
                            "MaDes": datas[i].MaDes,
                            "StoreNum": datas[i].StoreNum,
                            "UnitName": datas[i].UnitName,
                            "SLNum": datas[i].SLNum,
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
            if(detailDatas.length>0){
                var shouDatas=[];
                for(var i in detailDatas){
                    if(detailDatas[i].MaVerRd==res.MaVerRd){
                        if(detailDatas[i].ReMaInfo.length>0){
                            for(var j  in  detailDatas[i].ReMaInfo){
                                var data={
                                    "MaVerRd": detailDatas[i].ReMaInfo[j].MaVerRd,
                                    "MaCode": detailDatas[i].ReMaInfo[j].MaCode,
                                    "MaName": detailDatas[i].ReMaInfo[j].MaName,
                                    "MaDes": detailDatas[i].ReMaInfo[j].MaDes,
                                    "UnitName": detailDatas[i].ReMaInfo[j].UnitName,
                                    "StoreRd":"",
                                    "StoreName7":"",
                                    "StoreName":"",
                                    "InStoreNum":"",
                                    "UNNum":"",
                                    "Remark":""
                                };
                                shouDatas.push(data);
                            }
                        }

                    }
                }

                var config7 = {
                    tableId: 'list7',
                    data: shouDatas,
                    colArr: colNamesArr7,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config7);//加载空表格

                var trObj = $("#list7 tbody>tr:eq(1)");
                trObj.find("td").each(function () {
                    if ($(this).attr("class") == "StoreName7") {
                        var current_Td_Val = $(this).text();
                        var str = "<select>";
                        for (var i = 0; i < ConmmonStoreInfo.length; i++) {
                            if (current_Td_Val == ConmmonStoreInfo[i].StoreName)
                                str += "<option selected value='" + ConmmonStoreInfo[i].StoreRd + "'>" + ConmmonStoreInfo[i].StoreName + "</option>";
                            else
                                str += "<option value='" + ConmmonStoreInfo[i].StoreRd + "'>" + ConmmonStoreInfo[i].StoreName + "</option>";
                        }
                        str += "</select>";
                        $(this).html(str);
                        $(this).find("select").css("border", "0px");
                        $(this).find("select").css("height", "19px");
                        $(this).find("select").css("color", "#000000");
                        $(this).find("select").css("width", "100px");
                    }
                });
                select1();
            }

                $("#ccsl").val(res.StoreNum);
                $("#ccs2").val(res.SLNum);
                $("#lysl").val(res.SLNum);
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
                 //   "LNNum":res.SLNum,
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


    function shouData(MaverRd){

    }

    //明细的确认按钮
    $(".sure2").on("click",function () {
        var flags=/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
        var lysl=$("#lysl").val().trim();
        var SLNum=$("#ccs2").val().trim();

        if(storeid==null||storeid==""){
            toastr.warning("领用失败，领用仓库不能为空")
            return false;
        }

        if($("#ccsl").val().trim()==null||$("#ccsl").val().trim()==""){
            toastr.warning("领用失败，库存数量不能为空")
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
        /*if(Number(lysl)>SLNum){
            toastr.warning("请勿超领!")
            return false;
        }*/
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

        var list6Data=getTableData("list7");

        if(list6Data.length>0){
            for(var i  in list6Data ){
                if(parseInt(list6Data[i].UNNum)>0){
                    if(parseInt(list6Data[i].UNNum)>SLNum){
                        toastr.warning("该行的料数量不能大于应领数量");
                        return false;
                    }
                    if(parseInt(list6Data[i].UNNum)+parseInt(lysl)>parseInt(SLNum)){
                        toastr.warning("该行的料数量和领用数量不能大于应领数量");
                        return false;
                    }
                }

            }
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
                        tableData[i].UNNum = lysl;
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

            var s=[];

            var tab_TR = {
                addid: addid++,
                PKDtlRd: "",
                MaVerRd: MaVerRd,
                MaName: MaName,
                MaCode: MaCode,
                MaDes: MaDes,
                StoreRd: StoreRd,
                StoreName: StoreName,
                UNNum: lysl,
                UnitName: UnitName,
                Remark:Remark
            };

          addSrow("list4", tab_TR);

            if(list6Data.length>0){
                for(var i  in list6Data ){
                    if(parseInt(list6Data[i].UNNum)>0){
                        var ss={
                            addid: addid++,
                            PKDtlRd: "",
                            MaVerRd: list6Data[i].MaVerRd,
                            MaName: list6Data[i].MaName,
                            MaCode: list6Data[i].MaCode+"(替"+MaCode+")",
                            MaDes: list6Data[i].MaDes,
                            StoreRd: list6Data[i].StoreRd,
                            StoreName: list6Data[i].StoreName,
                            UNNum: list6Data[i].UNNum,
                            UnitName: list6Data[i].UnitName,
                            Remark:list6Data[i].Remark
                        }
                        addSrow("list4", ss);
                    }
                }




                config7.data.length = 0;
                fullTable(config7);
            }
        }



        /*//获取里面所有的数据，添加到用料清单的表格中
        var tab_TR = {
            addid: addid++,
            PKDtlRd: "",
            MaVerRd: $(this).getseldata().MaVerRd,
            MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
            MaName: $(this).getseldata().MaName==undefined?"":$(this).getseldata().MaName.split(($(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode) + "-")[0],
            MaDes: $(this).getseldata().MaDes == null ? "":$(this).getseldata().MaDes ,
            StoreRd: $(this).getseldata().StoreRd,
            StoreName: $(this).getseldata().StoreName,
            UNNum: lysl,
            UnitName: UnitName,
            Remark:Remark
        };

        console.log(tab_TR)

        var _flag = true;
        var tab_list4 = getTableData("list4");
        for (var i in tab_list4) {
            if (tab_list4[i].addid == modifytableid) {
                //toastr.warning(tab_TR.MaName + "，已经被添加");
                $("#list4").find("tbody tr").each(function () {
                    if($(this).find(".addid").text() == modifytableid){
                        $(this).find(".UNNum").html(/!*(parseFloat(tab_TR.Num)*1000 + parseFloat($(this).find(".Num").text())*1000)/1000*!/tab_TR.UNNum);
                        $(this).find(".Remark").attr('title', tab_TR.Remark);
                        _flag = false;
                        return false;
                    }
                });
                break;
            }
        }
        if(_flag){
            addSrow("list4", tab_TR);
        }*/

        // isclick=true;
        //每次点击新增清空拼接的集合值
        tab_TR = [];
        // totalData=[];
        temporaryData={};
        //temporaryDatas=[];
        $("#ccsl").val("");
        $("#ccs2").val("");
        $("#lysl").val("");
        $("#Remark").val("");
        $("#ycyid").val("");
        $("#Store").clearseldata("StoreRd");
        $("#lymater").clearseldata("MaVerRd");

        click();
    });

    click();

    function click(){
        $("#list4").find("tbody").on("dblclick", "tr", function () {
            var ssStatus = $("#SStatus").attr("data-status");

            if ($("#SStatus").val() != null || "" != $("#SStatus").val()) {
                if ($("#SStatus").val() == "已下达" || $("#SStatus").val() == "已取消") {
                    //$("#sure2").css("display","none");
                    toastr.warning("领料单已下达或取消，不允许编辑");
                    return false;
                }
                resultFlag = false;

                modifytableid = $(this).find(".addid").text();

                $("#lymater").clearseldata("MaVerRd");
                $("#ccsl").attr("disabled",true);
                //把物料消耗的tab显示出来,并设置li的class为active,而其他的li移除active
                $("#tab_4_li").css("display","block").addClass("active").siblings().removeClass("active");
                $("#tab_44").addClass("active").siblings().removeClass("active");


                $(".sure2").css("display","-webkit-box");
                $("#tab_4").find("input").each(function () {
                    $(this).prop("readonly",false);
                });
                $("#tab_4").find("select").prop("disabled",false);
                $("#tab_4").find("img").css("display","inline-block");

                //获取表格所有数据
                var StoreRd = $(this).find(".StoreRd").text();
                var StoreName = $(this).find(".StoreName").attr('title');
                var MaverRd = $(this).find(".MaVerRd").text();
                var MaCode = $(this).find(".MaCode").text();
                var MaName = $(this).find(".MaName").text();
                var MaDes = $(this).find(".MaDes").text();
                var Num = $(this).find(".UNNum").attr('title');
                var Remark = $(this).find(".Remark").attr('title');

                var UnitName=$(this).find(".UnitName").text();

                PKDtlRd=$(this).find(".PKDtlRd").text();
                _MaCode = $(this).find(".MaCode").text();
                _MaDes = $(this).find(".MaDes").text();

                $("#lymater").showData1({
                    id: MaverRd,
                    name:_MaCode + "-" + MaName,
                    name1: [MaverRd, _MaCode, MaName, _MaDes, UnitName],
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

                $("#tab_4 .inpSearch").val($(this).find(".MaCode").text()+"-"+$(this).find(".MaName").text());
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
                var assCode = $("#AssCode").getseldata().WoCode;

                if(assCode==""){
                    toastr.warning("工单号不能为空");
                    return false;
                }
                storeid=StoreRd;
                var objDate={
                    "AssCode": assCode,
                    "AssSource": "00",
                    "StoreRd":StoreRd
                }
                request({url:"/BOM/GetMaTotalInfo", data: {"ExecType": "00","busData": JSON.stringify(objDate)}}, function (Body) {
                    var DS = Body.Data;
                    for(var J  in DS){
                        if(DS[J].MaCode==MaCode){
                            $("#ccsl").val(DS[J].StoreNum);
                            $("#ccs2").val(DS[J].SLNum);
                        }else {
                            isHUiZong=false;
                            break;
                        }
                    }

                    if(!isHUiZong){
                            if(signDetailTotal.length>0){
                                for(var i  in  signDetailTotal){
                                    if(signDetailTotal[i].MaCode==MaCode){
                                        $("#ccsl").val(signDetailTotal[i].StoreNum);
                                        $("#ccs2").val(Num);
                                    }
                                }
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

    /*替代料弹窗表格*/
/*
    var modal_colNamesArr1 = [
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "库存数量", "Name": "StoreNum", "CType": "text", "Editable": false},
        {"Caption": "单位", "Name": "UnitName", "Editable": false},
        {"Caption": "MaVerGd", "Name": "MaVerGd", "Hidden": true}
    ];
    var modal_config1 = {
        tableId: 'list5',
        data: [],
        colArr: modal_colNamesArr1,
        multiselect: false,
        width: 0.45,
        height: 0.4
    };
*/

    /*点击保存触发的事件*/
    $('#save').on('click', function () {

        var $PickCode = $("#PickCode");
        var PickCode = $PickCode.val();//领料单号
        /*if (PickCode.trim() != "") {*/
            //仓库id
            var StoreRd = null;
            //本次生产数量
            //日期
            var PrePickDate = $("#PrePickDate").val();
            /*if(PrePickDate==""){
                toastr.warning("预领料日期不能为空");
                return;
            }*/
            //获取领料仓库输入的input的值
            /*具体获取领料仓库的id*/
            /*StoreRd = $("#material_warehouse").getseldata().StoreRd;
            if(StoreRd==""){
                toastr.warning("仓库不能为空");
                return;
            }*/

            var AssCode = $("#AssCode").getseldata().WoCode;

            if(AssCode==""){
               toastr.warning("工单号不能为空");
               return;
            }

            //1.获取所有的表格里面的数据
            //获取表格数据
            var tableData = getRowData('list4');
            console.log(tableData);

            if (tableData=="") {
                toastr.warning('物料信息至少保留一条信息！');
                return;
            }
            //2.这个是保存的时候的判断
            for (var i = 0; i < tableData.length; i++) {
                if (tableData[i].UNNum.trim() == "" || tableData[i].UNNum.trim() == "0") {
                    toastr.warning('第' + (i + 1) + '行领料数量不能为0或者空！');
                    return;
                } /*else if (parseFloat(tableData[i].Num) > parseFloat(tableData[i].StoreNum)) {
                    toastr.warning('第' + (i + 1) + '行领料数量不能为大于仓库数量！');
                    return;
                }*/
            }

            if ($("#ExecType").val() == "00" && treeID==null ) {
                //工单号
                var AssSource = "00";
                //组装成后台需要的格式
                var busData = {};
                var PKMaInfos = [];
                for (var i = 0; i < tableData.length; i++) {
                    if (tableData[i].UNNum > 0) {
                        var PKMaInfo = {
                            "ReMaVerRd": '',
                            "MaVerRd": tableData[i].MaVerRd,
                            "StoreRd": isNaN(tableData[i].StoreRd) ? '':tableData[i].StoreRd,
                            /*"ReML": tableData[i].ReML,*/
                            "Num": tableData[i].UNNum,
                            "UnitName": tableData[i].UnitName,
                            "Remark":tableData[i].Remark
                        };
                        PKMaInfos.push(PKMaInfo);
                    }
                }
                busData = {
                    "PickCode": PickCode,
                    //"StoreRd": StoreRd,
                    "AssCode": AssCode,
                    "AssSource": AssSource,
                    "PrePickDate": PrePickDate,
                    "PKMaInfo": PKMaInfos,
                    "Remark": $("#beizhu").val(),
                };
                request({
                 url: '/Pick/SavePickInfo', async: true,
                 data: {"ExecType": "00", "BusData": JSON.stringify(busData)}
                 },
                 function (Body) {
                 toastr.success("领料单信息新增成功,领料单号{"+Body.Data.PickCode+"}");
                 $("#ExecType").val("");
                     condition = "";
                     currentPage = 0;
                     loadPage();
                     $("#hidden1").attr("editid", Body.Data.PickRd);
                     treeID=$("#hidden1").attr("editid");
                     $("#hidden2").attr("editcode", Body.Data.PickCode);
                     var objBusData = JSON.stringify({"PickRd": treeID});
                     var objData = {
                         "ExecType": "00",
                         "BusData": objBusData
                     };
                     request({url: "/Pick/GetPickInfo", data: objData}, function (Body) {
                         $("#PickCode").val(Body.Data.PickCode);
                         if(Body.Data.DSource == "00"){
                             dSource = false;
                         }else{
                             dSource = true;
                         }

                         $("#UnitName").html(Body.Data.UnitName);
                         $("#AssCode").showData({
                             id: Body.Data.AssCode,
                             name: Body.Data.AssCode,
                             keyfield: "rd",
                             fields: [
                                 {"name": "rd"},
                                 {"name": "WoCode"}
                             ]
                         });


                         var $ExStatus = $("#ExStatus");
                         $ExStatus.attr("data-status", Body.Data.ExStatus);
                         if ("00" === Body.Data.ExStatus) {
                             $ExStatus.val("待执行");
                         }
                         else if ("01" === Body.Data.ExStatus) {
                             $ExStatus.val("进行中");
                         }
                         else if ("02" === Body.Data.ExStatus) {
                             $ExStatus.val("已完成");
                         }
                         else if ("03" === Body.Data.ExStatus) {
                             $ExStatus.val("已取消");
                         }

                         $("#AssSource").val(Body.Data.AssSource);
                         $("#MaVerRd").val(Body.Data.MaInfo.MaVerRd);
                         $("#MaCode").val(Body.Data.MaInfo.MaCode);
                         $("#MaName").val(Body.Data.MaInfo.MaName);
                         $("#Num").val(Body.Data.Num);
                         $("#UnitName").val(Body.Data.UnitName);
                         $("#PrePickDate").val(Body.Data.PrePickDate);
                         //$("#material_warehouse").val(Body.Data.StoreInfo == null? "":Body.Data.StoreInfo.StoreName);

                         if (Body.Data.StoreInfo != null) {
                             $("#material_warehouse").showData({
                                 id: Body.Data.StoreInfo.StoreRd,
                                 name: Body.Data.StoreInfo.StoreName,
                                 keyfield: "StoreRd",
                                 fields: [
                                     {"name": "StoreRd"},
                                     {"name": "StoreName"}
                                 ]
                             });
                         }

                         $("#creatPeople").val(Body.Data.Creator);
                         $("#creatTime").val(Body.Data.CreateTime);
                         $("#modifyPeople").val(Body.Data.LastModifyMan);
                         $("#modifyTime").val(Body.Data.LastModifyTime);
                         $("#beizhu").val(Body.Data.Remark);
                         // 对表格进行显示相应数据
                         var PickDlInfo = Body.Data.PickDlInfo;
                         addid = 1;
                         for (var i in PickDlInfo) {
                             if (PickDlInfo[i].ReMaInfo.length)
                                 Body.Data.PickDlInfo[i].IsReMa = "<a href='javascript:void(0);' class='replace'>替换</a>";
                             else
                                 Body.Data.PickDlInfo[i].IsReMa = "";
                             Body.Data.PickDlInfo[i].ReMaInfo = JSON.stringify(Body.Data.PickDlInfo[i].ReMaInfo);

                             if(PickDlInfo[i].StoreInfo == undefined){
                                 Body.Data.PickDlInfo[i].StoreName = "";
                             }else{
                                 Body.Data.PickDlInfo[i].StoreRd = Body.Data.PickDlInfo[i].StoreInfo.StoreRd;
                                 Body.Data.PickDlInfo[i].StoreName = Body.Data.PickDlInfo[i].StoreInfo.StoreName;
                             }
                             Body.Data.PickDlInfo[i].addid = addid++;
                         }
                         config1.data = Body.Data.PickDlInfo;
                         fullTable(config1);
                         //a_click();
                         click();
                         if(Body.Data.AssCode!=null){
                             var WoCode = Body.Data.AssCode;
                             var WOData = {
                                 "FiledList":[
                                     {
                                         "FieldName":"WoCode",
                                         "FieldOpt":"=",
                                         "FieldVal":WoCode
                                     }
                                 ]
                             };
                             request({
                                     url: '/WO/GetAllWOInfo',
                                     data: {"ExecType": "00","InitData":JSON.stringify(WOData)}
                                 },
                                 function (Body) {
                                     var WoRd=Body.Data[0].WoRd
                                     var busData={
                                         "WoRd":WoRd
                                     }
                                     request({
                                             url: '/WO/GetWOInfo',
                                             data: {"ExecType": "00","BusData": JSON.stringify(busData)}
                                         },
                                         function (Body) {
                                             $("#MaName").val(Body.Data.MaInfo.MaName);
                                         })
                                 }
                             );
                         }
                     });
                 }
                 );
            }else if(treeID != null && !dSource){
                toastr.warning("导入的领料单数据不允许修改");
            }
            else if (treeID != null && dSource) {
                //更新
                //组装成后台需要的格式
                var busData = {};
                var PKMaInfos = [];
                for (var i = 0; i < tableData.length; i++) {
                    if (tableData[i].UNNum > 0) {
                        var PKMaInfo = {
                            "PKDtlRd": tableData[i].PKDtlRd,
                            "MaVerRd": tableData[i].MaVerRd,
                            "ReMaVerRd": "",
                            "StoreRd": isNaN(tableData[i].StoreRd) ? '':tableData[i].StoreRd,
                            //"ReML": tableData[i].ReML,
                            "Num": tableData[i].UNNum,
                            "UnitName": tableData[i].UnitName,
                            "Remark":tableData[i].Remark
                        };
                        PKMaInfos.push(PKMaInfo);
                    }
                }
                busData = {
                    "PickRd": treeID,
                    //"StoreRd": StoreRd,
                    "PrePickDate": PrePickDate,
                    "PKMaInfo": PKMaInfos,
                    "Remark": $("#beizhu").val(),
                };
                request({
                        url: '/Pick/SavePickInfo', async: true,
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

            }
       /* } else {
            $PickCode.attr('placeholder', '不能为空!').css('border-color', 'red');
        }*/
    });

    /*点击替换的时候弹窗方法*/
/*    var a_click = function () {
        /!*点击替换的时候弹窗*!/
        $('.replace').on('click', function (event) {
            event.stopPropagation();
            _trObj = $(this).parent().parent();
            $("#code").val(_trObj.find(".MaCode").text());
            $("#name").val(_trObj.find(".MaName").text());
            var UnitName = _trObj.find(".UnitName").text();
            $("#UnitName_2").html(UnitName);

            var MVerRdVal = _trObj.find('td:eq(1)').text();
            //获取替代料信息
            var reMaInfoStr = _trObj.find(".ReMaInfo").text();
            //转换成json对象

            /!*渲染弹窗表格的信息*!/
            var ReMaInfos = JSON.parse(reMaInfoStr);
            if (ReMaInfos.length) {
                var ReMaInfoDatas = [];
                for (var i = 0; i < ReMaInfos.length; i++) {
                    var ReMaInfoData = {
                        "MaVerRd": ReMaInfos[i].MaVerRd,
                        "MaCode": ReMaInfos[i].MaCode,
                        "MaName": ReMaInfos[i].MaName,
                        "StoreNum": ReMaInfos[i].StoreNum,
                        "UnitName": ReMaInfos[i].UnitName
                    };
                    ReMaInfoDatas.push(ReMaInfoData);
                }
                modal_config1.data = ReMaInfoDatas;
                fullTable(modal_config1);
                tr_click("list5");
            } else {
                modal_config1.data.length = 0;
                fullTable(modal_config1);
            }
            $('#myModal').modal({
                keyboard: true
            });
            $('.confirm').on('click', function () {
                var rowid = jQuery("#list5").jqGrid("getGridParam", "selrow");
                if (rowid != null) {
                    //1.获取替代料的选中数据
                    var MaVerGd = null;
                    var MaVerRd = null;
                    var MaCode = null;
                    var MaName = null;
                    var StoreNum = null;
                    var flag = false;
                    re_trObj.children().each(function () {
                        if ($(this).attr("class") == "MaVerRd") {
                            MaVerRd = $(this).text();
                        }
                        else if ($(this).attr("class") == "MaCode") {
                            MaCode = $(this).text();
                        }
                        else if ($(this).attr("class") == "MaName") {
                            MaName = $(this).text();
                        }
                        else if ($(this).attr("class") == "StoreNum") {
                            if (parseFloat($(this).text()) >= parseFloat($("#reNum").val()) && parseFloat($(this).text()) > 0)
                                StoreNum = $(this).text();
                            else {
                                toastr.warning("替换数量必须小于等于库存数量，并且不能为0");
                                flag = true;
                                return false;
                            }
                        }
                        else if ($(this).attr("class") == "MaVerGd") {
                            MaVerGd = $(this).text();
                        }
                    });
                    if (flag) {
                        return;
                    }
                    //修改要替换的物料的领料数量
                    var reNumVal = $("#reNum").val();
                    var $numObj = _trObj.find(".Num");
                    if ($numObj.find("input").length == 0) {
                        $numObj.html($numObj.html() - reNumVal);
                    } else {
                        $numObj.find("input").val($numObj.find("input").val() - reNumVal);
                    }
                    //如果替换成功，那么要判断领料页面中是否已经存在替代料的物料
                    //0.获取页面所有物料的数据
                    var tableData = getRowData("list4");
                    //1.循环物料信息，判断：
                    $("#list4").find(".MaVerRd").each(function () {
                        if ($(this).text() == MaVerRd) {//如果存在，
                            var trObj = $(this).parent();//保存当前的tr对象
                            trObj.find(".Num").html(reNumVal);
                            var ReMLStr = trObj.find(".ReML").html();
                            trObj.find(".ReML").html(ReMLStr == "" ? ReMLStr : ReMLStr + "|" + _trObj.find(".MaVerGd").text());
                        } else {//如果不存在，直接新增一行
                            var jsonObj = {
                                "PKDtlRd": "",
                                "MaVerRd": MaVerRd,
                                "MaVerGd": MaVerGd,
                                "MaCode": MaCode,
                                "MaName": MaName,
                                "Num": reNumVal,
                                "StoreNum": StoreNum,
                                "UnitName": UnitName,
                                "IsReMa": "",
                                "ReMaInfo": MaVerRd,
                                "ReML": _trObj.find(".MaVerGd").text()
                            };
                            addSrow("list4", jsonObj);
                            return false;
                        }
                    });

                    $('#myModal').modal('hide');
                    /!*处理物料代码和名称的替换问题*!/

                }
            })
        });
    };*/
    $('#myModal').on('hidden.bs.modal', function () {
        $('.confirm').unbind('click');
    });
    //给弹出层表格的每一行一个点击事件
    var tr_click = function (tableId) {
        $("#" + tableId + " tr").each(function () {
            $(this).click(function () {
                re_trObj = $(this);
            })
        });
    };
    //给表格的每一行一个点击事件
    var _click = function (tableId) {
        $("#" + tableId + " tr").each(function () {
            $(this).click(function () {
                $(".replace").css("color", "");
                $(this).find(".replace").css("color", "#ffffff");
            })
        });
    };

    $("#import").on("click",function () {
        //弹窗
        layer.open({
            type: 2,
            title:'领料单导入',
            shadeClose: true,
            area: ['60%', '50%'],
            content:getBasePath()+"/Pick/importpickPG"
        });
    });
    //导出
    $("#export").on('click', function () {
        if(treeID != "" && treeID != null){
            layer.confirm('确认要导出该领料单信息？', {
                btn: ['确认', '取消']
            }, function () {
                layer.closeAll("dialog");
                var url = getBasePath() + "/Pick/exportPickExcel";
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

                            filename = "领料单-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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

    //筛选
    var params = [{
        "caption": "工单号",
        "name": "AssCode",
        "valtype": "00"
    }, {
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
            var treedataList = [];

            request({
                url: '/Pick/GetAllPickInfo',
                data: {"ExecType": "00","InitData": JSON.stringify(InitData1)}
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
                        id: treeData[i].PickRd == undefined ? "" : treeData[i].PickRd,
                        name: treeData[i].PickCode == undefined ? "" : treeData[i].PickCode
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            });
            delete InitData1.FiledList['PickCode'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);


    //下载推荐库位单
    $("#export1").on('click', function () {
        if(treeID != "" && treeID != null){
            layer.confirm('确认要导出该推荐库位单信息？', {
                btn: ['确认', '取消']
            }, function () {
                layer.closeAll("dialog");
                var url = getBasePath() + "/Pick/ExportPickExcel";
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

                            filename = "推荐库位单-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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
            toastr.warning("请选择要导出的推荐库位单再进行导出操作");
        }
    });
























});