/**
 * Created by PNC on 2017/6/27.
 */
$(function(){


    /*-----------------------点击新增按钮---------------------------------------------------*/
    $('#you').on('click', function() {
        layer.alert('', {
            type: 2,
            title: '新增',
            shadeClose: true,
            shade: 0.8,
            area: ['85%', '75%'],
            content: './batchwaddyougd' //iframe的url
        });
    })

    $('#wu').on('click', function() {
        layer.alert('', {
            type: 2,
            title: '新增',
            shadeClose: true,
            shade: 0.8,
            area: ['85%', '75%'],
            content: './batchwadd' //iframe的url
        });
    })
    /*-----------------------点击删除按钮---------------------------------------------------*/
    $("#batch_del").on("click",function () {
        if($("#batch_del").attr("value") == "" || $("#batch_del").attr("value") == undefined){
            toastr.warning("请先选择一个批次");
            return ;
        }
        //询问框
        layer.confirm('批次信息非常重要,是否确定删除？', {
            btn: ['删除','取消'] //按钮
        }, function(){   //删除事件
            var objBusData ={
                "BRd":$("#batch_del").attr("value")
            };


            request({url: "/Batch/SaveBatchInfo", data:  {
                    "ExecType": "01",
                    "BusData": JSON.stringify(objBusData)
                },async: false}, function (Body) {
                layer.closeAll("dialog");
                toastr.success(Body.MsgDes);
            });


          /*  $.ajax({
                url: getBasePath()+"/Batch/SaveBatchInfo",
                type: "POST",
                async: false,//设为同步请求
                data: {
                    "ExecType": "01",
                    "BusData": JSON.stringify(objBusData)
                },
                success: function (res) {
                    layer.closeAll("dialog");
                    if(res.Status == "00"){
                        toastr.success(res.Body.MsgDes);
                    }else{
                        toastr.warning(res.Body.MsgDes);
                    }
                }
            });*/
        }, function(){
        });
    });

    /*-----------------------点击编辑按钮---------------------------------------------------*/
    $('.cEdit').on('click', function() {
        if($("#batch_et").attr("value") == "" || $("#batch_et").attr("value") == undefined){
            toastr.warning("请先选择一个批次");
            return ;
        }
        var BRd = $("#batch_et").attr("value");
        console.log("BRd:"+BRd);
        layer.confirm('', {
            type: 2,
            title: '编辑',
            shadeClose: true,
            shade: 0.8,
            area: ['95%', '75%'],
            content: './batchedit/'+BRd //iframe的url
        });
    })

    /*********************************************请求工单列表信息*********************************************/
    request({url: "/Batch/GetAllBatchInfo", data:  {
            "ExecType": "00",
            "BusData": JSON.stringify(objBusData)
        },async: false}, function (Body) {
        var colNamesArr = [
            {"Caption": "id", "Name": "BRd", "Hidden": true},
            {"Caption": "物料", "Name": "MaName", 'Editable': false},
            {"Caption": "批次号", "Name": "Batch", "Editable": false},
            {"Caption": "数量", "Name": "Num", "Editable": false},
            {"Caption": "单位", "Name": "UnitName", "Editable": false},
            {"Caption": "关联单据", "Name": "WoCode",  "Editable": false},
            {"Caption": "单据类型", "Name": "WoSource",  "Editable": false},
            {"Caption": "计划完工日期", "Name": "JFDate", "Editable": false},
            {"Caption": "实际完工日期", "Name": "SFDate", "Editable": false},
            {"Caption": "状态", "Name": "Status", "Editable": false}

        ];

        var config1 = {
            tableId: 'list4',
            data: Body.Data,
            colArr: colNamesArr,
            multiselect: false,
            width: 0.995,
            height:0.375
        };
        fullTable(config1);//加载表格

        getGLBatch(Body.Data[0].BRd);
    });

   /* $.ajax({
        url: getBasePath()+"/Batch/GetAllBatchInfo",
        type: "POST",
       async: false,//设为同步请求
        data:{
            "ExecType": "00"
        },
        success: function (res) {
            if(res.Status =="00"){
                // 1.处理表单数据
                /!*---------------------当点击事件触发之后，加载表单数据处理开始--------------------*!/
                //  fillform("printForm", rule, res.Body.Data);
                /!*-------------------表单数据处理结束---------------------*!/
                // 2.处理表格
                var colNamesArr = [
                    {"Caption": "id", "Name": "BRd", "Hidden": true},
                    {"Caption": "物料", "Name": "MaName", 'Editable': false},
                    {"Caption": "批次号", "Name": "Batch", "Editable": false},
                    {"Caption": "数量", "Name": "Num", "Editable": false},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false},
                    {"Caption": "关联单据", "Name": "WoCode",  "Editable": false},
                    {"Caption": "单据类型", "Name": "WoSource",  "Editable": false},
                    {"Caption": "计划完工日期", "Name": "JFDate", "Editable": false},
                    {"Caption": "实际完工日期", "Name": "SFDate", "Editable": false},
                    {"Caption": "状态", "Name": "Status", "Editable": false}

                ];

                var config1 = {
                    tableId: 'list4',
                    data: res.Body.Data,
                    colArr: colNamesArr,
                    multiselect: false,
                    width: 0.995,
                    height:0.375
                };
                fullTable(config1);//加载表格

                getGLBatch(res.Body.Data[0].BRd);
            }else{
                toastr.warning(res.Body.MsgDes);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            toastr.error(textStatus);
        }
    });*/

    function getGLBatch(BRd){
        var a = {
            "BRd": BRd
        }
        var objData = {
            "ExecType": "01",
            "BusData":JSON.stringify(a)
        };

        request({url: "/WO/GetAllWOBInfo", data: objData,async: false}, function (Body) {
            var colNamesArr = [
                {"Caption": "id", "Name": "id", "Hidden": true},
                {"Caption": "批次号", "Name": "Batch", 'Editable': false},
                {"Caption": "生产数量", "Name": "Num", "Editable": false},
                {"Caption": "单位", "Name": "UnitName", "Editable": false},
                {"Caption": "计划完成日期", "Name": "JFDate", "Editable": false},
                {"Caption": "实际完成日期", "Name": "SFDate", "Editable": false},
                {"Caption": "状态", "Name": "Status", "Editable": false}
            ];

            var config1 = {
                tableId: 'list5',
                data: Body.Data,
                colArr: colNamesArr,
                multiselect: false,
                width: 0.995,
                height:0.375
            };
            fullTable(config1);//加载表格
        });


      /*  $.ajax({
            url: getBasePath()+"/WO/GetAllWOBInfo",
            type: "POST",
            async: false,//设为同步请求
            data: objData,
            success: function (res) {
                if (res.Status == "00") {
                    // 1.处理表单数据
                    /!*---------------------当点击事件触发之后，加载表单数据处理开始--------------------*!/
                    //  fillform("printForm", rule, res.Body.Data);
                    /!*---------------------表单数据处理结束--------------------*!/
                    // 2.处理表格
                    /!*  var modelArr = ['id','Batch', 'Num', 'UnitName', 'JFDate', 'SFDate', 'Status',0.995];
                     var colNamesArr = ['id','批次号', '生产数量','单位','计划完成日期','实际完成日期','状态'];*!/
                    var colNamesArr = [
                        {"Caption": "id", "Name": "id", "Hidden": true},
                        {"Caption": "批次号", "Name": "Batch", 'Editable': false},
                        {"Caption": "生产数量", "Name": "Num", "Editable": false},
                        {"Caption": "单位", "Name": "UnitName", "Editable": false},
                        {"Caption": "计划完成日期", "Name": "JFDate", "Editable": false},
                        {"Caption": "实际完成日期", "Name": "SFDate", "Editable": false},
                        {"Caption": "状态", "Name": "Status", "Editable": false}
                    ];

                    var config1 = {
                        tableId: 'list5',
                        data: res.Body.Data,
                        colArr: colNamesArr,
                        multiselect: false,
                        width: 0.995,
                        height:0.375
                    };
                    fullTable(config1);//加载表格
                } else {
                    toastr.warning(res.Body.MsgDes);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }
        });*/
    }

    $("#list4 tr[role='row']").each(function() {
        $(this).click(function () {
            var BRd = $(this).find("td").eq(0).text();
            var a = {
                "BRd": BRd
            }
            $("#batch_et").attr("value",BRd);
            $("#batch_del").attr("value",BRd);
            var objData = {
                "ExecType": "01",
                "BusData":JSON.stringify(a)
            };

            request({url: "/WO/GetAllWOBInfo", data: objData,async: false}, function (Body) {
                var colNamesArr = [
                    {"Caption": "id", "Name": "id", "Hidden": true},
                    {"Caption": "批次号", "Name": "Batch", 'Editable': false},
                    {"Caption": "生产数量", "Name": "Num", "Editable": false},
                    {"Caption": "单位", "Name": "UnitName", "Editable": false},
                    {"Caption": "计划完成日期", "Name": "JFDate", "Editable": false},
                    {"Caption": "实际完成日期", "Name": "SFDate", "CType": "text", "Editable": false},
                    {"Caption": "状态", "Name": "Status", "CType": "text", "Editable": false}
                ];

                var config1 = {
                    tableId: 'list5',
                    data: Body.Data,
                    colArr: colNamesArr,
                    multiselect: false,
                    width: 0.995,
                    height:0.375
                };
                fullTable(config1);//加载表格
            });
          /*  $.ajax({
                url: getBasePath()+"/WO/GetAllWOBInfo",
                type: "POST",
                async: false,//设为同步请求
                data: objData,
                success: function (res) {
                    console.log(res)
                    if (res.Status == "00") {

                        // 1.处理表单数据
                        /!*---------------------当点击事件触发之后，加载表单数据处理开始--------------------*!/
                        //  fillform("printForm", rule, res.Body.Data);
                        /!*---------------------表单数据处理结束--------------------*!/
                        // 2.处理表格
                        var colNamesArr = [
                            {"Caption": "id", "Name": "id", "Hidden": true},
                            {"Caption": "批次号", "Name": "Batch", 'Editable': false},
                            {"Caption": "生产数量", "Name": "Num", "Editable": false},
                            {"Caption": "单位", "Name": "UnitName", "Editable": false},
                            {"Caption": "计划完成日期", "Name": "JFDate", "Editable": false},
                            {"Caption": "实际完成日期", "Name": "SFDate", "CType": "text", "Editable": false},
                            {"Caption": "状态", "Name": "Status", "CType": "text", "Editable": false}
                        ];

                        var config1 = {
                            tableId: 'list5',
                            data: res.Body.Data,
                            colArr: colNamesArr,
                            multiselect: false,
                            width: 0.995,
                            height:0.375
                        };
                        fullTable(config1);//加载表格
                    } else {
                        toastr.warning(res.Body.MsgDes);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    toastr.error(textStatus);
                }
            });*/

        });
    });

});