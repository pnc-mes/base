$(function () {
    //增删改查树的局部刷新
    var onClicks = function (nodeinfo, handle) {
        $(".right").show();
        $(".cAdd").attr("ca", "")
        $("#WoCode").attr("readonly",true);
        $(".hiderow").show();
        //默认加载直接保存
        $("#hidden").attr("a", "00")
        //获取默认加载的id
        $("#hidden").attr("editid", nodeinfo.nodeID)

        //默认删除
        $("#remove").attr("a", nodeinfo.nodeID)
        var word = $("#hidden").attr("editid")
        var test1 = {
            "WoRd": word
        };
        request({
            url: "/WO/GetAllWOBInfo",
            data: {
                "ExecType": "00",
                "busData": JSON.stringify(test1)
            },async: false
        }, function (Body) {
            if (Body.MsgCode == "0x00000") {
                if (Body.Data.length <= 0) {
                    var config2 = {
                        tableId: "list4",
                        data: [],
                        colArr: colNamesArr,
                        width: 0.84,
                        height: 0.39
                    };
                    fullTable(config2);
                    return false;
                }



                var config2 = {
                    tableId: "list4",
                    data:Body.Data,
                    colArr: colNamesArr,
                    width: 0.84,
                    height: 0.39
                };
                fullTable(config2);
            }
        });

        var test = {
            "WoRd": word
        };

        request({
            url: "/WO/GetWOInfo",
            data: {"ExecType": "00", "busData": JSON.stringify(test)}
        }, function (Body) {
            if (Body.MsgCode == "0x00000") {
                $("#hidden1").attr("h",Body.Data.DSource);

                var DSource = $("#hidden1").attr("h");

                if(DSource=="00"){
                    $("#comboSelect").prop("disabled",true);
                    $("#Num").attr("readonly",true);
                    $("#WoType").prop("disabled",true);
                    $("#jinjidu").prop("disabled",true);
                    $("#date01").attr("readonly",true);
                    $("#date02").attr("readonly",true);
                    $("#OrderCode").attr("readonly",true);
                    $("#zanban").prop("disabled",true);
                    $("#xiangbao").prop("disabled",true);
                    $("#scxb").selectpicker('disabled', true);
                }else{
                    $("#comboSelect").prop("disabled",false);
                    $("#Num").attr("readonly",false);
                    $("#WoType").prop("disabled",false);
                    $("#jinjidu").prop("disabled",false);
                    $("#date01").attr("readonly",false);
                    $("#date02").attr("readonly",false);
                    $("#OrderCode").attr("readonly",false);
                    $("#zanban").prop("disabled",false);
                    $("#xiangbao").prop("disabled",false);
                    $("#scxb").selectpicker('disabled', false);
                }


                $("#WoCode").val(Body.Data.WoCode);
                $("#Num").val(Body.Data.Num);
                if (Body.Data.UnitInfo.UnitName == null) {
                    $("#unit").text("")
                } else {
                    $("#unit").text(Body.Data.UnitInfo.UnitName)
                }

                //  $("#zanban").val(res.Body.Data.TrayPackInfo.PackName);
                //   $("#xiangbao").val(res.Body.Data.BoxPackInfo.PackName);
                $("#jinjidu").showData({
                    id: Body.Data.UrcyInfo.UrcyRd,
                    name: Body.Data.UrcyInfo.UrcyDes,
                    keyfield: "UrcyRd",
                    fields: [
                        {"name": "UrcyRd"},
                        {"name": "UrcyDes"}
                    ]
                });

                $("#OrderCode").showData({
                    id: "1",
                    name: Body.Data.OrderCode,
                    keyfield: "CoRd",
                    fields: [
                        {"name": "CoRd"},
                        {"name": "OrderCode"}
                    ]
                });

                $("#zanban").showData({
                    id: Body.Data.TrayPackInfo.PackSpRd,
                    name: Body.Data.TrayPackInfo.PackName,
                    keyfield: "MPRd_",
                    fields: [
                        {"name": "MPRd_"},
                        {"name": "MPName_"}
                    ]
                });
                $("#xiangbao").showData({
                    id: Body.Data.BoxPackInfo.PackSpRd,
                    name: Body.Data.BoxPackInfo.PackName,
                    keyfield: "MPRd",
                    fields: [
                        {"name": "MPRd"},
                        {"name": "MPName"},
                        {"name": "MPName"}
                    ]
                });


                var aa=Body.Data.LineInfo;
                var aas=[];
                for(var i in aa){
                    aas.push(aa[i].LineRd)
                }

                $("#scxb").selectpicker('val', aas);

                $("#comboSelect").showData({
                    id: Body.Data.MaInfo.MaVerRd,
                    name: Body.Data.MaInfo.MaCode + "-" + Body.Data.MaInfo.MaName,
                    keyfield: "MaVerRd",
                    fields: [
                        {"name": "MaVerRd"},
                        {"name": "MaName"}
                    ]
                });


                $("#unit").attr("value", Body.Data.UnitInfo.UnitRd)
                //  $("#comboSelect_text").val(res.Body.Data.MaInfo.MaName)


                $("#OrderCode").val(Body.Data.OrderCode)
                var aaaa = Body.Data.JStartDate;
                var a11 = aaaa.split("-");
                var a110 = a11[1] + "/" + a11[2] + "/" + a11[0];
                $("#date01").val(a110);

                $("#OrderCode").val(Body.Data.OrderCode)
                var aaaab = Body.Data.JFinishDate;
                var a112 = aaaab.split("-");
                var a1121 = a112[1] + "/" + a112[2] + "/" + a112[0];
                $("#date02").val(a1121);
                $("#FinishNum").text(Body.Data.FinishNum);
                $("#UnCBatNum").text(Body.Data.UnCBatNum);
                $("#creatPeople").val(Body.Data.Creator)
                $("#creatTime").val(Body.Data.CreateTime)
                $("#modifyPeople").val(Body.Data.LastModifyMan)
                $("#modifyTime").val(Body.Data.LastModifyTime)
                $("#beizhu").val(Body.Data.Remark)

                // $("#jinjidu").val(res.Body.Data.UrcyInfo.UrcyDes)

                $("#WoType").showData({
                    id: Body.Data.WOTInfo.WTRd,
                    name: Body.Data.WOTInfo.WTName,
                    keyfield: "WTRd",
                    fields: [
                        {"name": "WTRd"},
                        {"name": "WTName"}
                    ]
                });

                //  $("#WoType").val(res.Body.Data.WOTInfo.WTName);

                var str = "";
                if ("00" == Body.Data.Status) {
                    str = "待处理";
                } else if ("01" == Body.Data.Status) {
                    str = "下达";
                } else if ("02" == Body.Data.Status) {
                    str = "取消";
                } else if ("03" == Body.Data.Status) {
                    str = "处理中";
                } else if ("04" == Body.Data.Status) {
                    str = "冻结";
                } else if ("05" == Body.Data.Status) {
                    str = "终止";
                } else if ("06" == Body.Data.Status) {
                    str = "完成";
                } else if ("07" == Body.Data.Status) {
                    str = "关闭";
                }
                $("#Status").text(str);

                //加载工艺流程
                request({url:"/Commom/GetCMWFInfo", data: {"ExecType": "00", "busData": JSON.stringify({MVerRd: Body.Data.MaInfo.MaVerRd})}},function(Body2){
                    var data = Body2.Data.WFInfo;
                    var aa="";
                    if(data != undefined) {
                        for (var i in data) {
                            aa += '<option value=' + data[i].WFVerRd + '>' + data[i].WFName + '</option>';
                        }
                    }
                    $("#wf").html(aa);
                    $("#wf").selectpicker({
                        width:200
                    });
                    $("#wf").selectpicker('refresh');
                    $("#wf").selectpicker('val', [Body.Data.WFInfo.WFVerRd]);
                });
            }
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

    // 树的分页
    var _currentPage = 0;
    var _PageInfo = {
        "PageSize": 20,
        "PageIndex": _currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = _currentPage;
        _currentPage = 0;
        _currentPage = treeSearchs("/WO/GetAllWOInfo","WoRd","WoCode","WoCode",condition,_currentPage,config);
        if(_currentPage < 0){
            condition = "";
            _currentPage = temp;
        }
    });

    $(".input1").keydown(function(event) {
        if (event.keyCode == 13) {
        condition = $(".input1").val().trim();
        var temp = _currentPage;
        _currentPage = 0;
        _currentPage = treeSearchs("/WO/GetAllWOInfo","WoRd","WoCode","WoCode",condition,_currentPage,config);
        if(_currentPage < 0){
            condition = "";
            _currentPage = temp;
        }}
    });

    $("#prev").on("click",function(){
        if(_currentPage > 0){
            _currentPage --;
            _currentPage = treeSearchs("/WO/GetAllWOInfo","WoRd","WoCode","WoCode",condition,_currentPage,config);
        }
    });
    $("#next").on("click",function(){
        _currentPage ++;
        _currentPage = treeSearchs("/WO/GetAllWOInfo","WoRd","WoCode","WoCode",condition,_currentPage,config);
    });

    $("#_right").hide();

    //判断是否工单打印批次
    var f = true; //true打印，false不打印

    request({
        url: "/Commom/GetCMGCInfo",
        data: {"ExecType": "00", "BusData": "{\"ModeName\": \"M4\"}"}
    }, function (Body) {
        var data = Body.Data;
        if (data != undefined && data.length > 0 && data[0].Value == "M4_00") {
            f = false;
        }
    });

    if (!f) {
        $("#printDiv").css("display", "");
    }

    var newTree = [];
    // 产品名称
    var currentPage = 0;
    var PageInfo = {
        "PageSize": 100,
        "PageIndex": currentPage
    };

    var MaVerRd = "";

    //默认加载表头
    var colNamesArr = [
        {"Caption": "id", "Name": "WoBRd", "Hidden": true},
        {"Caption": "批次号", "Name": "Batch", "Editable": false,"Width":150},
        {"Caption": "数量", "Name": "CanNum", "Editable": false,"Width":70},
        {"Caption": "当前工序", "Name": "SpecName", "Editable": false,"Width":100},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,"Width":40},
        {"Caption": "计划完成时间", "Name": "JFDate", "Editable": false,"Width":180},
        {"Caption": "实际完成日期", "Name": "SFDate", "Editable": false,"Width":180},
        {"Caption": "状态", "Name": "Status", "Editable": false,"Width":40}
    ];
    var config2 = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        width: 0.84,
        height: 0.39
    };
    fullTable(config2);
    PageInfo.PageIndex = currentPage;
    //加载物料
    var madata = [];

    var params = {
        "displaymode": "1",
        "title": "产品名称",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "产品名称id",
                    "name": "MaVerRd"
                },{
                    "caption": "物料代码",
                    "name": "MaCode"
                },{
                    "caption": "产品名称",
                    "name": "MaName"
                },{
                    "caption": "产品描述",
                    "name": "MaDes"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                MaVerRd = res.MaVerRd;
                var data = {
                    "MaVerRd": MaVerRd
                };
                request({
                    url: "/Material/GetMaInfo",
                    data: {"ExecType": "01", "busData": JSON.stringify(data)}
                }, function (data) {
                    $("#unit").attr("value", data.Data.UnitInfo.UnitRd);
                    $("#unit").text(data.Data.UnitInfo.UnitName);
                    $("#zanban").showData({
                        id: data.Data.TrayPackInfo.PackSpRd,
                        name: data.Data.TrayPackInfo.PackName,
                        keyfield: "MPRd_",
                        fields: [
                            {"name": "MPRd_"},
                            {"name": "MPName_"}
                        ]
                    });
                    $("#xiangbao").showData({
                        id: data.Data.BoxPackInfo.PackSpRd,
                        name: data.Data.BoxPackInfo.PackName,
                        keyfield: "MPRd",
                        fields: [
                            {"name": "MPRd"},
                            {"name": "MPName"}
                        ]
                    });
                });

                //加载工艺流程
                request({url:"/Commom/GetCMWFInfo", data: {"ExecType": "00", "busData": JSON.stringify({MVerRd: MaVerRd})}},function(Body){
                    var data = Body.Data.WFInfo;
                    var aa="";
                    if(data != undefined) {
                        for (var i in data) {
                            aa += '<option value=' + data[i].WFVerRd + '>' + data[i].WFName + '</option>';
                        }
                    }
                    $("#wf").html(aa);
                    $("#wf").selectpicker('refresh');
                });
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },
                        {
                            "FieldName": "MaterialType",
                            "FieldOpt": "in",
                            "FieldVal": "('00','01')"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                Materialinfo(InitData,page,xldata);
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            },
            "onformatval": function(seldata){
                return seldata.MaCode + "-" + seldata.MaName;
            }
        }
    };
    $("#comboSelect").zc_select(params);

    //加载工单类型
    var params2 = {
        "displaymode": "0",
        "title": "工单类型数据",
        "binddata": {
            "keyfield": "WTRd",
            "fields": [
                {
                    "caption": "工单类型id",
                    "name": "WTRd"
                }, {
                    "caption": "工单类型名称",
                    "name": "WTName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "WoTName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },{
                            "FieldName": "WoTName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/WoType/GetAllWTInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "WTRd": datas[i].WTRd,
                            "WTName": datas[i].WTName,
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
    $("#WoType").zc_select(params2);

    //加载工单号
    var wocode = {
        "displaymode": "0",
        "title": "订单号",
        "binddata": {
            "keyfield": "CoRd",
            "fields": [
                {
                    "caption": "订单号id",
                    "name": "CoRd"
                }, {
                    "caption": "订单号名称",
                    "name": "OrderCode"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "OrderCode",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },{
                            "FieldName": "OrderCode",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/CO/GetAllCOInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CoRd": datas[i].CoRd,
                            "OrderCode": datas[i].OrderCode,
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
    $("#OrderCode").zc_select(wocode);

    //加载紧急度
    var params2 = {
        "displaymode": "0",
        "title": "紧急度",
        "binddata": {
            "keyfield": "UrcyRd",
            "fields": [
                {
                    "caption": "紧急度id",
                    "name": "UrcyRd"
                }, {
                    "caption": "紧急度名称",
                    "name": "UrcyDes"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                //   alert(res.UrcyRd)
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "UrcyDes",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },{
                            "FieldName": "UrcyDes",
                            "FieldOpt": "Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/Urgency/GetAllUrcyInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UrcyRd": datas[i].UrcyRd,
                            "UrcyDes": datas[i].UrcyDes,
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
    $("#jinjidu").zc_select(params2);
   $("#xianbie").zc_select(params2);

    var params2 = {
        "displaymode": "0",
        "title": "箱包类型",
        "binddata": {
            "keyfield": "MPRd_",
            "fields": [
                {
                    "caption": "箱包类型id",
                    "name": "MPRd_"
                }, {
                    "caption": "箱包类型名称",
                    "name": "MPName_"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {
                //  alert(res.MPRd)
            },
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
                            "MPName_": datas[i].MPName,
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
    $("#zanban").zc_select(params2);


    var params2 = {
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
            "onclick": function (res) {
                // alert(res.MPRd)
            },
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
                            "MPName": datas[i].MPName,
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
    $("#xiangbao").zc_select(params2);

    $("#date01").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "mm/dd/yyyy"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
    });

    $("#date02").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "mm/dd/yyyy"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
    });


    //加载树
    var loadTree = function () {
        //加载树
        var treedataList = [];

        request({
            url: "/WO/GetAllWOInfo",
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(_PageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CreateTime DESC",
                            "FieldOpt": "Order BY"
                        }]})
            },async: false
        }, function (Body) {
            if (Body.MsgCode == "0x00000") {

                var treeData = Body.Data;
                for (var i = 0; i < treeData.length; i++) {
                    var tree = {
                        id: treeData[i].WoRd == undefined ? "" : treeData[i].WoRd,
                        name: treeData[i].WoCode == undefined ? "" : treeData[i].WoCode
                    }
                    treedataList.push(tree);
                    newTree.push(tree)
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }
        });
    };
    loadTree();

    //保存更新保存
    $(".cSave").click(function () {
        var str=/^\d+(\.\d+)?$/;　//非负浮点数（正浮点数 + 0）

        var WoCode = $("#WoCode").val();
        var Num = $("#Num").val();
        if (Num == "") {
            toastr.warning("数量不能为空！");
            return false;
        }
        var flag=str.test(Num);
        if(flag==false){
            toastr.warning("输入有误，数量为正整数！")
            return false;
        }
        //var OrderCode = $("#OrderCode").val();
        var OrderCode=$("#OrderCode").getseldata().OrderCode;
        if(OrderCode==null||""==OrderCode){
            OrderCode=="";
        }
        var Remark = $("#beizhu").val();
        var JStartDate = $("#date01").val();
        var JFinishDate = $("#date02").val();

        var a = JStartDate.split("/");
        var adata = a[2] + "-" + a[0] + "-" + a[1];
        var b = JFinishDate.split("/");
        var bdata = b[2] + "-" + b[0] + "-" + b[1];

        var d1 = new Date(JStartDate.replace(/\-/g, "\/"));
        var d2 = new Date(JFinishDate.replace(/\-/g, "\/"));

        if (JStartDate != "" && JFinishDate != "" && d1 > d2) {
            toastr.warning("开始计划时间不能大于计划完工时间！");
            return false;
        }
        var UnitRd = $("#unit").attr("value");

        var TrayPSpRd = $("#zanban").getseldata().MPRd_;

        var BoxPSpRd = $("#xiangbao").getseldata().MPRd;
        var UrcyRd = $("#jinjidu").getseldata().UrcyRd;
        //var LineRd = $("#scxb").getseldata().LineRd;
       // var LineRd = $("#scxb").val();
        var LineInfoSources=$("#scxb").val();
        if(LineInfoSources==null||LineInfoSources==""||LineInfoSources.length<=0){
            toastr.warning("生产线别不能为空!");
            return;
        }
        var LineInfo=[];
        for(var  obj in LineInfoSources){
            var LineInfos={
                "LineRd":LineInfoSources[obj]
            }
            LineInfo.push(LineInfos);
        }
        var WfVerRd = $("#wf").val();
        if(WfVerRd == undefined || WfVerRd == ""){
            toastr.warning("工艺流程不能为空!");
            return;
        }

        if (UrcyRd == "") {
            toastr.warning("紧急度不能为空!");
            return;
        }
        var MaVerRd = $("#comboSelect").getseldata().MaVerRd;
        if (MaVerRd == "") {
            toastr.warning("产品名称不能为空!");
            return;
        }
        var WTRd = $("#WoType").getseldata().WTRd;
        if (WTRd == "") {
            toastr.warning("工单类型不能为空");
            return;
        }
        var newData = {
            "WoCode": WoCode,
            "MaVerRd": MaVerRd,
            "Num": Num,
            "UnitRd": UnitRd,
           "OrderCode": OrderCode,
            "Remark": Remark,
            "JStartDate": adata,
            "JFinishDate": bdata,
            "TrayPSpRd": TrayPSpRd,
            "BoxPSpRd": BoxPSpRd,
            "WTRd": WTRd,
            "UrcyRd": UrcyRd,
            "LineInfo":LineInfo,
            "WfVerRd": WfVerRd[0]
        }
        //更新保存
        if ($("#hidden").attr("a") == "00") {

            var WoRd = $("#hidden").attr("editid")
            var newData1 = {
                "WoRd": WoRd,
                "WoCode": WoCode,
                "MaVerRd": MaVerRd,
                "Num": Num,
                "UnitRd": UnitRd,
                "OrderCode": OrderCode,
                "Remark": Remark,
                "JStartDate": adata,
                "JFinishDate": bdata,
                "TrayPSpRd": TrayPSpRd,
                "BoxPSpRd": BoxPSpRd,
                "WTRd": WTRd,
                "UrcyRd": UrcyRd,
                "LineInfo":LineInfo,
                "WfVerRd": WfVerRd[0]
            }
            if (UrcyRd != null && UrcyRd != "" && Num != null && Num != "" && MaVerRd != null && MaVerRd != "" && UnitRd != null && UnitRd != "" && JStartDate != null && JStartDate != "" && JFinishDate != null && JFinishDate != "") {
                request({
                    url: "/WO/SaveWOInfo",
                    data: {"ExecType": "02", "BusData": JSON.stringify(newData1)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    //$("#hidden").attr("a", "");
                    //$("#hidden").attr("editid", "");
                    _currentPage = 0;
                    condition = "";
                    $("#hidden").attr("editid", WoRd);
                    loadTree()  //局部刷新
                });
            }
        }

        //直接保存
        if ($(".cAdd").attr("ca") == "00") {
            var newData = {
                "WoCode": WoCode,
                "MaVerRd": MaVerRd,
                "Num": Num,
                "UnitRd": UnitRd,
                "OrderCode": OrderCode,
                "Remark": Remark,
                "JStartDate": adata,
                "JFinishDate": bdata,
                "TrayPSpRd": TrayPSpRd,
                "BoxPSpRd": BoxPSpRd,
                "WTRd": WTRd,
                "UrcyRd": UrcyRd,
                "LineInfo":LineInfo,
                "WfVerRd": WfVerRd[0]
            }

            if (UrcyRd != null && UrcyRd != "" && Num != null && Num != "" && MaVerRd != null && MaVerRd != "" && UnitRd != null && UnitRd != "" && JStartDate != null && JStartDate != "" && JFinishDate != null && JFinishDate != "") {
                request({
                    url: "/WO/SaveWOInfo",
                    data: {"ExecType": "00", "BusData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success("工单信息新增成功,工单号{"+Body.Data.WoCode+"}");
                    condition = "";
                    _currentPage = 0;
                    loadTree()  //局部刷新
                    $("#hidden").attr("a", "00");
                    $('.cAdd').attr("ca", "");
                    $("#hidden").attr("editid", Body.Data.WoRd);
                    $(".right").show();
                    $(".cAdd").attr("ca", "")
                    $("#WoCode").attr("readonly",true);
                    $(".hiderow").show();
                   var word=$("#hidden").attr("editid");
                    var test1 = {
                        "WoRd": word
                    }

                    request({
                        url: "/WO/GetAllWOBInfo", async: false,
                        data: {
                            "ExecType": "00",
                            "busData": JSON.stringify(test1)
                        }
                    }, function (Body) {
                        if (Body.MsgCode == "0x00000") {
                            if (Body.Data.length <= 0) {
                                var config2 = {
                                    tableId: "list4",
                                    data: [],
                                    colArr: colNamesArr,
                                    width: 0.84,
                                    height: 0.39
                                };
                                fullTable(config2);
                                return false;
                            }
                            var config2 = {
                                tableId: "list4",
                                data: Body.Data,
                                colArr: colNamesArr,
                                width: 0.84,
                                height: 0.39
                            };
                            fullTable(config2);
                        }
                    });

                    var test = {
                        "WoRd": word
                    };

                    request({
                        url: "/WO/GetWOInfo",
                        data: {
                            "ExecType": "00",
                            "busData": JSON.stringify(test)
                        }
                    }, function (Body) {
                        if (Body.MsgCode == "0x00000") {

                            $("#WoCode").val(Body.Data.WoCode);
                            $("#Num").val(Body.Data.Num);
                            if (Body.Data.UnitInfo.UnitName == null) {
                                $("#unit").text("")
                            } else {
                                $("#unit").text(Body.Data.UnitInfo.UnitName)
                            }
                            //  $("#zanban").val(res.Body.Data.TrayPackInfo.PackName);
                            //   $("#xiangbao").val(res.Body.Data.BoxPackInfo.PackName);
                            $("#jinjidu").showData({
                                id: Body.Data.UrcyInfo.UrcyRd,
                                name: Body.Data.UrcyInfo.UrcyDes,
                                keyfield: "UrcyRd",
                                fields: [
                                    {"name": "UrcyRd"},
                                    {"name": "UrcyDes"}
                                ]
                            });
                            $("#zanban").showData({
                                id: Body.Data.TrayPackInfo.PackSpRd,
                                name: Body.Data.TrayPackInfo.PackName,
                                keyfield: "MPRd_",
                                fields: [
                                    {"name": "MPRd_"},
                                    {"name": "MPName_"}
                                ]
                            });
                            $("#xiangbao").showData({
                                id: Body.Data.BoxPackInfo.PackSpRd,
                                name: Body.Data.BoxPackInfo.PackName,
                                keyfield: "MPRd",
                                fields: [
                                    {"name": "MPRd"},
                                    {"name": "MPName"}
                                ]
                            });

                            $("#comboSelect").showData({
                                id: Body.Data.MaInfo.MaVerRd,
                                name: Body.Data.MaInfo.MaCode + "-" + Body.Data.MaInfo.MaName,
                                keyfield: "MaVerRd",
                                fields: [
                                    {"name": "MaVerRd"},
                                    {"name": "MaName"}
                                ]
                            });



                            $("#unit").attr("value", Body.Data.UnitInfo.UnitRd)
                            //  $("#comboSelect_text").val(res.Body.Data.MaInfo.MaName)


                           // $("#OrderCode").val(Body.Data.OrderCode)
                            var aaaa = Body.Data.JStartDate;
                            var a11 = aaaa.split("-");
                            var a110 = a11[1] + "/" + a11[2] + "/" + a11[0];
                            $("#date01").val(a110);

                            //$("#OrderCode").val(Body.Data.OrderCode)
                            var aaaab = Body.Data.JFinishDate;
                            var a112 = aaaab.split("-");
                            var a1121 = a112[1] + "/" + a112[2] + "/" + a112[0];
                            $("#date02").val(a1121);
                            $("#FinishNum").text(Body.Data.FinishNum);
                            $("#UnCBatNum").text(Body.Data.UnCBatNum);
                            $("#creatPeople").val(Body.Data.Creator)
                            $("#creatTime").val(Body.Data.CreateTime)
                            $("#modifyPeople").val(Body.Data.LastModifyMan)
                            $("#modifyTime").val(Body.Data.LastModifyTime)
                            $("#beizhu").val(Body.Data.Remark)

                            // $("#jinjidu").val(res.Body.Data.UrcyInfo.UrcyDes)

                            $("#WoType").showData({
                                id: Body.Data.WOTInfo.WTRd,
                                name: Body.Data.WOTInfo.WTName,
                                keyfield: "WTRd",
                                fields: [
                                    {"name": "WTRd"},
                                    {"name": "WTName"}
                                ]
                            });

                            //  $("#WoType").val(res.Body.Data.WOTInfo.WTName);

                            var str = "";
                            if ("00" == Body.Data.Status) {
                                str = "待处理";
                            } else if ("01" == Body.Data.Status) {
                                str = "下达";
                            } else if ("02" == Body.Data.Status) {
                                str = "取消";
                            } else if ("03" == Body.Data.Status) {
                                str = "处理中";
                            } else if ("04" == Body.Data.Status) {
                                str = "冻结";
                            } else if ("05" == Body.Data.Status) {
                                str = "终止";
                            } else if ("06" == Body.Data.Status) {
                                str = "完成";
                            } else if ("07" == Body.Data.Status) {
                                str = "关闭";
                            }
                            $("#Status").text(str);
                        }
                    });
                });
            }
        }
    });

    //新增
    $(".cAdd").click(function () {
        $("#scxb").selectpicker('val',"");
        $("#wf").selectpicker('val', "");
        $("#wf").html("");
        $("#wf").selectpicker('refresh');
        $("#WoType").clearseldata("WTRd");
        $("#zanban").clearseldata("MPRd_");
        $("#xiangbao").clearseldata("MPRd");
        $("#comboSelect").clearseldata("MaVerRd");
        $("#jinjidu").clearseldata("UrcyRd");
        //$("#scxb").clearseldata("LineRd");
        $("#creatPeople").val("");

        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        $("#hidden").attr("a", "")
        $(".right").show();
        $(".hiderow").hide();
        //直接新增做的判断
        $(this).attr("ca", "00")

        $("#WoCode").val("");
        $("#WoCode").attr("readonly",false);
        $("#comboSelect").prop("disabled",false);
        $("#Num").attr("readonly",false);
        $("#WoType").prop("disabled",false);
        $("#jinjidu").prop("disabled",false);
        //$("#scxb").clearseldata("LineRd",false);
        $("#date01").attr("readonly",false);
        $("#date02").attr("readonly",false);
        //$("#OrderCode").attr("readonly",false);
        $("#zanban").prop("disabled",false);
        $("#xiangbao").prop("disabled",false);
        $("#mater").val("")
        $("#Num").val("1")
        $("#unit").text("")
        //$("#OrderCode").val("")
        $("#OrderCode").clearseldata("WoRd");
        $("#FinishNum").val("")
        $("#UnCBatNum").val("")
        $("#Status").val("")
        $("#beizhu").val("")

        var aaaa = $("#hiddentime").val();
        var a11 = aaaa.split("/");
        var a110 = a11[0] + "/" + a11[1] + "/" + a11[2];
        $("#date01").val(a110);
        $("#date02").val(a110);


        var config2 = {
            tableId: "list4",
            data: [],
            colArr: colNamesArr,
            width: 0.84,
            height: 0.39
        };
        fullTable(config2);
    });

    //删除
    $("#remove").click(function () {
        var WoRd = $(this).attr("a");
        var data = {
            "WoRd": WoRd
        }
        if (WoRd == null || WoRd == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            var DSource = $("#hidden1").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                layer.closeAll("dialog");
                request({
                    url: "/WO/SaveWOInfo",
                    data: {"ExecType": "01", "BusData": JSON.stringify(data)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    _currentPage = 0;
                    condition = "";
                    loadTree();//局部刷新
                    $("#remove").attr("a", "");
                    $(".right").hide();
                });
            })
        }
    });


    //下达
    $("#xd").click(function () {
        if (f) {
            saveWo("03");
        } else {
            //获取打印机、打印模板
            var printRd = $("#defaultSelect1").getseldata().PrRd;

            if (printRd == undefined) {
                toastr.warning("打印机不能为空");
                return;
            }

            var printTRd = $("#defaultSelect").getseldata().PtRd;

            if (printTRd == undefined) {
                toastr.warning("打印模板不能为空");
                return;
            }

            //是否打印
            var isPrint = "00";
            /*if ($("#isPrint").is(":checked")) {
                isPrint = "00";
            }*/

            var printCopy = Number($("#printCopy").val());
            if (!Number.isInteger(printCopy)) {
                toastr.warning("打印份数不是整数");
                return;
            }

            var data = {
                IsPrint: isPrint,
                PrintTRd: printTRd,
                PrintRd: printRd,
                PrintCount: 1,
                PrintCopy: printCopy
            };

            saveWo("03", data);
        }
    });

    //取消
    $("#cancel").click(function () {
        saveWo("04");
    });

    //冻结
    $("#freeze").click(function () {
        saveWo("05");
    });

    //解冻
    $("#thaw").click(function () {
        saveWo("06");
    });

    //终止
    $("#stop").click(function () {
        saveWo("07");
    });

    //开始
    $("#start").click(function () {
        saveWo("08");
    });

    //关闭
    $("#close").click(function () {
        saveWo("09");
    });

    //加载打印模板
    var params_printT = {
        "displaymode": "0",
        "title": "选择打印模板",
        "binddata": {
            "keyfield": "PtRd",
            "fields": [
                {
                    "caption": "打印模板id",
                    "name": "PtRd"
                }, {
                    "caption": "打印模板名称",
                    "name": "PtName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var obj = {
                    data: {"ExecType": "00"},
                    url: "/PrintT/GetAllPtInfo"
                };
                var xldata = [];
                request(obj, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PtRd": datas[i].PtRd,
                            "PtName": datas[i].PtName,
                        };
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
    $("#PrintT").zc_select(params_printT);

    //加载打印机
    var params_print = {
        "displaymode": "0",
        "title": "选择打印机",
        "binddata": {
            "keyfield": "PrRd",
            "fields": [
                {
                    "caption": "打印机id",
                    "name": "PrRd"
                }, {
                    "caption": "打印机名称",
                    "name": "PrName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var obj = {
                    data: {"ExecType": "00"},
                    url: "/Printer/GetAllPrInfo"
                };
                var xldata = [];
                request(obj, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PrRd": datas[i].PrRd,
                            "PrName": datas[i].PrName,
                        };
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
    $("#Print").zc_select(params_print);

    //工单简单数据操作
    function saveWo(execType, busData) {
        busData = busData == undefined ? {} : busData;

        var woRd = $("#hidden").attr("editid");//$("#remove").attr("a");
        if (woRd == null || woRd == "") {
            toastr.warning("请选择左侧要操作的一项!");
        }

        busData.WoRd = woRd;

        request({
            url: "/WO/SaveWOInfo",
            data: {"ExecType": execType, "BusData": JSON.stringify(busData)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
        });
        if(execType == "03"){
            request({
                url: "/WO/GetAllWOBInfo", async: false,
                data: {
                    "ExecType": "00",
                    "busData": JSON.stringify({WoRd: woRd})
                }
            }, function (Body) {
                if (Body.MsgCode == "0x00000") {
                    if (Body.Data.length <= 0) {
                        var config2 = {
                            tableId: "list4",
                            data: [],
                            colArr: colNamesArr,
                            width: 0.84,
                            height: 0.39
                        };
                        fullTable(config2);
                        return false;
                    }
                    var config2 = {
                        tableId: "list4",
                        data: Body.Data,
                        colArr: colNamesArr,
                        width: 0.84,
                        height: 0.39
                    };
                    fullTable(config2);
                }
            });
        }
    }

    //加载线体
    request({url:"/Line/GetAllLineInfo", data: {"ExecType": "00"}},function(Body){
        var data=Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].LineRd+'>'+data[i].LineName +'</option>';
        }
        $("#scxb").html(aa);
        $("#scxb").selectpicker({
            width:200
        });
    });
});