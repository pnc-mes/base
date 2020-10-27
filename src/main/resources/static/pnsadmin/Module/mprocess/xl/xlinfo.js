$(function(){
    var colNamesArr4 = [
        {"Caption": "物料批次", "Name": "Batch", "CType": "text", "Editable": false},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text", "Editable": false},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "CType": "text", "Editable": false},
        {"Caption": "批次可用数量", "Name": "Num", "CType": "text", "Editable": false},
        {"Caption": "时间", "Name": "CreateTime", "CType": "text", "Editable": false},
    ];
    var config4 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr4,
        multiselect: true,
        width: 0.9,
        height: 0.415
    };
    config4.data.length = 0;
    fullTable(config4);//加载空表格

    $("._select").click(function(){
        request({
            url:'/MO/GetMOInfo',
            },function(Body){
            var treeData = Body.Data;
            var datas=[];
                if(treeData.length>0){
                    for(var i in  treeData){
                        var data={
                            "MaCode":treeData[i].MaCode,
                            "MaName":treeData[i].MaName,
                            "MaDes":treeData[i].MaDes==null?"":treeData[i].MaDes,
                            "Num":treeData[i].Num+treeData[i].UnitName,
                            "Batch":treeData[i].Batch,
                            "CreateTime":treeData[i].CreateTime,
                        }
                        datas.push(data);
                    }
                    var config1 = {
                        tableId: 'list4',
                        data: datas,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.9,
                        height:0.36
                    };
                    fullTable(config1);
                }else {
                    var config1 = {
                        tableId: 'list4',
                        data: datas,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.9,
                        height:0.36
                    };
                    fullTable(config1);
                }
        });
    });


    $("._xl").click(function(){
        var list4Data=getSeRowData("list4");
        if(list4Data.length<=0){
            toastr.warning("下料失败,无数据");
            return false;
        }
        var datas=[];
        for(var i  in  list4Data){
            var data={
                "Batch":list4Data[i].Batch
            }
            datas.push(data);
        };

        request({url:"/MO/SaveMOInfo", data: {"ExecType": "01", "BusData": JSON.stringify(datas)}},function(Body){
            toastr.success(Body.MsgDes);
            request({
                url:'/MO/GetMOInfo',
            },function(Body){
                var treeData = Body.Data;
                var datas=[];
                if(treeData.length>0){
                    for(var i in  treeData){
                        var data={
                            "MaCode":treeData[i].MaCode,
                            "MaName":treeData[i].MaName,
                            "MaDes":treeData[i].MaDes==null?"":treeData[i].MaDes,
                            "Num":treeData[i].Num+treeData[i].UnitName,
                            "Batch":treeData[i].Batch,
                            "CreateTime":treeData[i].CreateTime,
                        }
                        datas.push(data);
                    }
                    var config1 = {
                        tableId: 'list4',
                        data: datas,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.9,
                        height:0.36
                    };
                    fullTable(config1);
                }else {
                    var config1 = {
                        tableId: 'list4',
                        data: [],
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.9,
                        height:0.36
                    };
                    fullTable(config1);
                }
            });
        });

    });
    //回车事件
    $("#wl").keydown(function (event) {
        if (event.keyCode == '13') {
            if ($("#wl").is(":focus")) {
                var list4Data=getRowData("list4");
                var datas=[];
                var data=$(this).val().trim();
                if(data==null||data==""){
                    request({
                        url:'/MO/GetMOInfo',
                    },function(Body){
                        var treeData = Body.Data;
                        var datas=[];
                        if(treeData.length>0){
                            for(var i in  treeData){
                                var data={
                                    "MaCode":treeData[i].MaCode,
                                    "MaName":treeData[i].MaName,
                                    "MaDes":treeData[i].MaDes==null?"":treeData[i].MaDes,
                                    "Num":treeData[i].Num+treeData[i].UnitName,
                                    "Batch":treeData[i].Batch,
                                    "CreateTime":treeData[i].CreateTime,
                                }
                                datas.push(data);
                            }
                            var config1 = {
                                tableId: 'list4',
                                data: datas,
                                colArr: colNamesArr4,
                                multiselect: true,
                                width: 0.9,
                                height:0.36
                            };
                            fullTable(config1);
                        }else {
                            var config1 = {
                                tableId: 'list4',
                                data: datas,
                                colArr: colNamesArr4,
                                multiselect: true,
                                width: 0.9,
                                height:0.36
                            };
                            fullTable(config1);
                        }
                    });
                }else {
                    for(var i  in  list4Data){
                        if(list4Data[i].MaCode.indexOf(data)>=0||list4Data[i].MaName.indexOf(data)>=0||list4Data[i].MaDes.indexOf(data)>=0||list4Data[i].Batch.indexOf(data)>=0){
                            var dataArr={
                                "Batch":list4Data[i].Batch,
                                "MaCode":list4Data[i].MaCode,
                                "MaName":list4Data[i].MaName,
                                "MaDes":list4Data[i].MaDes==null?"":list4Data[i].MaDes,
                                "CreateTime":list4Data[i].CreateTime,

                            }
                            datas.push(dataArr);
                        }
                    }
                    var config1 = {
                        tableId: 'list4',
                        data: datas,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.9,
                        height:0.36
                    };
                    fullTable(config1);
                }
            }
        }
    });


   /* $("#wlS").keyup(function(){
        var list4Data=getRowData("list4");
        var datas=[];
        var data=$(this).val().trim();
        if(data==null||data==""){
            request({
                url:'/MO/GetMOInfo',
            },function(Body){
                var treeData = Body.Data;
                if(treeData.length>0){
                    var datas=[];
                    for(var i in  treeData){
                        var data={
                            "MaCode":treeData[i].MaCode,
                            "MaName":treeData[i].MaName,
                            "MaDes":treeData[i].MaDes==null?"":treeData[i].MaDes,
                            "Batch":treeData[i].Batch,
                            "CreateTime":treeData[i].CreateTime,
                        }
                        datas.push(data);
                    }
                    var config1 = {
                        tableId: 'list4',
                        data: datas,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.9,
                        height:0.36
                    };
                    fullTable(config1);
                }
            });
        }else {
            for(var i  in  list4Data){
                if(list4Data[i].MaCode.indexOf(data)>=0||list4Data[i].MaName.indexOf(data)>=0||list4Data[i].MaDes.indexOf(data)>=0||list4Data[i].Batch.indexOf(data)>=0){
                    var data={
                        "Batch":list4Data[i].Batch,
                        "MaCode":list4Data[i].MaCode,
                        "MaName":list4Data[i].MaName,
                        "MaDes":list4Data[i].MaDes==null?"":list4Data[i].MaDes,
                        "CreateTime":list4Data[i].CreateTime,

                    }
                    datas.push(data);
                }
            }
            var config1 = {
                tableId: 'list4',
                data: datas,
                colArr: colNamesArr4,
                multiselect: true,
                width: 0.9,
                height:0.36
            };
            fullTable(config1);
        }


    });*/


});