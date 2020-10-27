/**
 * Created by test on 2017/6/28.
 */
$(function () {
    var datas=null;
    //处理表格
    var colNamesArr = [
        {"Caption": "id", "Name": "PDRd", "IsKey": true, "Hidden": true},
        {"Caption": "盘点任务单号", "Name": "PDCode", "CType": "text", "Editable": false,Width:200},
        {"Caption": "仓库名称", "Name": "StoreName", "CType": "text", "Editable": false,Width:100},
        {"Caption": "执行人", "Name": "Execor", "CType": "text", "Editable": false,Width:70},
        {"Caption": "执行时间", "Name": "ExecTime", "CType": "text", "Editable": false,Width:170},
        {"Caption": "取消人", "Name": "Cancelor", "CType": "text", "Editable": false,Width:70},
        {"Caption": "取消时间", "Name": "CancelTime", "CType": "text", "Editable": false,Width:170},
        {"Caption": "完成人", "Name": "Finishor", "CType": "text", "Editable": false,Width:70},
        {"Caption": "完成时间", "Name": "FinishTime", "CType": "text", "Editable": false,Width:170},
        {"Caption": "审核人", "Name": "Finishor", "CType": "text", "Editable": false,Width:70},
        {"Caption": "审核时间", "Name": "FinishTime", "CType": "text", "Editable": false,Width:170},
        {"Caption": "状态", "Name": "ExStatus", "CType": "text", "Editable": false,Width:60}
    ];
    var pageBean = {
        "page": 1,
        "total": 1,
        "records": 20
    };
    var config = {
        tableId: "list4",
        divId: "pager4",
        pageBean: pageBean,
        data: [],
        colArr: colNamesArr,
        width:0.99,
        height:0.87
    };
    fullTable(config);
    //获取盘点单列表信息
    var GettableData = function () {
        var objData = {
            "ExecType": "00"
        };
        //table
        request({
                url: "/PD/GetAllPDInfo",
                data: objData
            },
            function (Body) {
                var data = Body.Data;
                for (var i = 0; i < data.length; i++) {
                    if (data[i].Execor == null) {
                        data[i].Execor = "";
                    }
                    if (data[i].Cancelor == null) {
                        data[i].Cancelor = "";
                    }
                    if (data[i].Finishor == null) {
                        data[i].Finishor = "";
                    }
                }
                var config = {
                    tableId: "list4",
                    data: Body.Data,
                    divId: "pager4",
                    pageBean: pageBean,
                    colArr: colNamesArr,
                    multiselect: true,
                    width:0.99,
                    height:0.87
                };
                fullTable(config);

                for (var i = 0; i < Body.Data.length; i++) {
                    var str = "<tr>"
                        + "<td>" + Body.Data[i].PDCode + "</td>"
                        + "<td>" + Body.Data[i].StoreName + "</td>"
                        + "<td>" + Body.Data[i].Execor + "</td>"
                        + "<td>" + Body.Data[i].ExecTime + "</td>"
                        + "<td>" + Body.Data[i].Cancelor + "</td>"
                        + "<td>" + Body.Data[i].CancelTime + "</td>"
                        + "<td>" + Body.Data[i].Finishor + "</td>"
                        + "<td>" + Body.Data[i].FinishTime + "</td>"
                        + "<td>" + Body.Data[i].Finishor + "</td>"
                        + "<td>" + Body.Data[i].FinishTime + "</td>"
                        + "<td>" + Body.Data[i].ExStatus + "</td>"
                        + "</tr>";
                    $("#CyContent").append(str);
                }

            });
    }

    GettableData();

    /*-----------------------点击新增按钮---------------------------------------------------*/
    $('.cAdd').on('click', function () {
        top.layer.open({
            type: 2,
            title: '新增',
            shadeClose: true,
            shade: 0.8,
            area: ['65%', '55%'],
            content: getBasePath() + "/PD/ADDPDPG",
            end: function () {
                GettableData();
            }
        });
    });

    /*-----------------------双击编辑---------------------------------------------------*/
    $(document).on("dblclick", "#list4 tr[role='row']", function () {
        var PDRd = $(this).children(":first").next().attr("title");
        top.layer.open({
            type: 2,
            title: '编辑',
            shadeClose: true,
            shade: 0.8,
            area: ['65%', '55%'],
            content: getBasePath() + "/PD/EditPDPG/" + PDRd,
            end: function () {
                GettableData();
            }
        });
    });

    /*-----------------------点击操作盘点差异按钮---------------------------------------------------*/
    $("a[name='pdcy']").on('click', function () {
        var ckl = $("#list4 .cbox:checked").length;
        if (ckl > 0) {
           if(ckl>1){
               toastr.warning("盘点差异时请不要同时选择多条数据!");
               return false;
           }

            var data = getSeRowData("list4");
            if(datas!=null&&data!=datas){
                toastr.warning("时请不要同时打开多个盘点差异!");
                return false;
            }
                datas=data;

            if(data[0].ExStatus=="已完成") {
                var PDRd = data[0].PDRd;
                window.sessionStorage.setItem("PDRd", PDRd);
                parent.addTabs({id: '999', title: '盘点差异', close: true, url: getBasePath() + "/PD/PDPDPG/" + PDRd});
            }else{
                toastr.warning("该盘点未完成,不能查看差异!");
            }
        } else {
            toastr.warning("请选择一行查看盘点差异信息!");
        }
    })
    //删除
    $(".cDel").click(function () {
        var datas = getSeRowData("list4");
        var PDRd = datas[0].PDRd;
        var data = {
            "PDRd": PDRd
        }
        layer.confirm('确认要删除吗？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            request({url: "/PD/SavePDInfo", data: {"ExecType": "01", "busData": JSON.stringify(data)}}, function (Body) {
                    PDRd = null;
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    GettableData();
                });
        });
    });
//筛选
    var params = [{
        "caption": "盘点单号",
        "name": "PDCode",
        "valtype": "00"
    }, {
        "caption": "盘点人",
        "name": "Execor",
        "valtype": "00"
    }, {
        "caption": "盘点时间段",
        "name": "ExecTime",
        "valtype": "02"
    }];

    var event = {
        onsure: function (result) {
            var FiledList = [];
            for (var i in result) {
                for (var j in result[i]) {
                    if (result[i][j].trim() != "" && result[i][j] != null) {
                        if (j == "ExecTime") {
                            var Filed = {
                                "FieldName": j,
                                "FieldOpt": ">=",
                                "FieldVal": result[i][j].split("|")[0]
                            };
                            FiledList.push(Filed);
                            Filed = {
                                "FieldName": j,
                                "FieldOpt": "<=",
                                "FieldVal": result[i][j].split("|")[1]+ " 23:59:59"

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
           var InitData = {
                "FiledList": FiledList
            }
            var objData = {
                "ExecType": "00",
                "InitData": JSON.stringify(InitData)
            };
            //table
            request({
                    url: "/PD/GetAllPDInfo",
                    data: objData
                },
                function (Body) {
                    var data = Body.Data;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].Execor == null) {
                            data[i].Execor = "";
                        }
                        if (data[i].Cancelor == null) {
                            data[i].Cancelor = "";
                        }
                        if (data[i].Finishor == null) {
                            data[i].Finishor = "";
                        }
                    }
                    var config = {
                        tableId: "list4",
                        data: Body.Data,
                        divId: "pager4",
                        pageBean: pageBean,
                        colArr: colNamesArr,
                        multiselect: true,
                        width:0.99,
                        height:0.87
                    };
                    fullTable(config);
                });
        }
    };

    //筛选
    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);
});