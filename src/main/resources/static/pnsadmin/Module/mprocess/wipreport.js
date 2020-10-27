$(function () {
    var colNamesArr = [
        {"Caption": "工单号", "Name": "WoCode", "CType": "text",Width:120},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text",Width:120},
        {"Caption": "产品名称", "Name": "MaName", "CType": "text"},
        {"Caption": "产品描述", "Name": "MaDes", "CType": "text"},
        {"Caption": "工艺流程", "Name": "WFName", "CType": "text",Width:100},
        {"Caption": "生产数量", "Name": "Num", "CType": "text",Width:80},
        {"Caption": "未投产", "Name": "UnTNum", "CType": "text",Width:80},
        {"Caption": "在制品数量", "Name": "WIPNum", "CType": "text",Width:90},
        {"Caption": "已完成数量", "Name": "FinishNum", "CType": "text",Width:90},
        {"Caption": "计划开工日期", "Name": "JStartDate", "CType": "text",Width:100},
        {"Caption": "工单下达日期", "Name": "ReleaseDate", "CType": "text",Width:100},
        {"Caption": "实际开工日期", "Name": "SStartDate", "CType": "text",Width:100}
    ];
    //表格
    var obj = {
        data: [],
        colArr: colNamesArr,
        tableId: "list4",
        multiselect: false,
        width: 0.99,
        height:0.85
    };
    fullTable(obj);
    request({
        id: "list4",
        url: "/Report/GetAllWIPInfo",
        async: true,
        data: {
            "ExecType": "00"
        }
    }, function (Body) {
        obj.data = Body.Data;
        fullTable(obj);
    });
    //筛选
    $(".cSelect").zc_filter(
        {
            "params": [
                {
                    "caption": "工单号", //显示名
                    "name": "WoCode",//字段名
                    "valtype": "00"//字段值类 00#文本 01#下拉框 02#日期段
                },
                {
                    "caption": "创建日期段", //显示名
                    "name": "CreateTime",//字段名
                    "valtype": "02"//字段值类 00#文本 01#下拉框 02#日期段
                }
            ],
            "event": {
                onsure: function (res) {
                    var FiledList = [];
                    for (var i in res) {
                        for (var j in res[i]) {
                            if (res[i][j].trim() != "" && res[i][j] != null) {
                                if (j == "CreateTime") {
                                    var Filed = {
                                        "FieldName": j,
                                        "FieldOpt": ">=",
                                        "FieldVal": res[i][j].split("|")[0]
                                    };
                                    FiledList.push(Filed);
                                    Filed = {
                                        "FieldName": j,
                                        "FieldOpt": "<=",
                                        "FieldVal": res[i][j].split("|")[1]+" 23:59:59"

                                    };
                                    FiledList.push(Filed);
                                    break;
                                }
                                var Filed = {
                                    "FieldName": j,
                                    "FieldOpt": "like",
                                    "FieldVal": "%" +res[i][j]+"%"
                                };
                                FiledList.push(Filed);
                            }
                        }
                    }
                    var InitData = {
                        "FiledList": FiledList
                    };
                    $("#list4").html("");
                    request({
                        id: "list4",
                        url: "/Report/GetAllWIPInfo",
                        async: true,
                        data: {
                            "ExecType": "00",
                            "InitData":JSON.stringify(InitData)
                        }
                    }, function (Body) {
                        //表格
                        obj.data = Body.Data;
                        fullTable(obj);
                    });
                }
            }
        }
    );

    //查询点击事件
    $("#Big_").click(function(){
        window.open("/mesadmin/ReportServer?reportlet=入库历史.cpt");
    });


});