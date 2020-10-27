$(function () {

    //定义表格信息
    var colNamesArr = [
        {"Caption":"BomChgRd", "Name":"BomChgRd", "CType":"text", "Hidden":true},
        {"Caption":"工程变更单号", "Name":"Version", "CType":"text"},
        {"Caption":"工程变更原因", "Name":"Remark", "CType":"text"},
        {"Caption":"变更时间", "Name":"CreateTime", "CType":"text"},
        {"Caption":"变更人", "Name":"Creator", "CType":"text"}
    ];

    var tabConfig = {
        data:[],
        colArr:colNamesArr,
        tableId:"list4",
        multiselect:false,
        width:0.99,
        height:0.4
    };

    fullTable(tabConfig);

    //根据BOMRd查询历史变更记录
    request({url:"/BOM/GetAllBHChgInfo", data: {"ExecType": "00", "BusData": "{\"BomRd\":" + storage.getItem("BOMRd") + "}"}},function (Body) {
        tabConfig.data = Body.Data;
        fullTable(tabConfig);
    });
    $("#list4").find("tbody").on("click","tr",function () {
        var bomChgRd = $(this).find(".BomChgRd").text();
        request({url:"/BOM/GetBHChgDtlInfo", data: {"ExecType": "00", "BusData": "{\"BomChgRd\":" + bomChgRd + "}"}},
            function (Body) {
            if(Body.Data.length){
                $("#tbody").empty();
                //填充变更明细的表格
                var StrTr = "<tr><td>物料代码</td><td>物料名称</td><td>工序</td><td>数量</td><td>消耗方式</td><td>是否检验</td>" +
                    "<td>物料代码</td><td>物料名称</td><td>工序</td><td>数量</td><td>消耗方式</td><td>是否检验</td>" +
                    "<td>新增</td><td>修改</td><td>删除</td></tr>";
                for(var i in Body.Data){
                    if(Body.Data[i].HChg.ConSMode == "00"){
                        Body.Data[i].HChg.ConSMode = "仅显示";
                    }else if(Body.Data[i].HChg.ConSMode == "01"){
                        Body.Data[i].HChg.ConSMode = "批次";
                    }else if(Body.Data[i].HChg.ConSMode == "02"){
                        Body.Data[i].HChg.ConSMode = "序号";
                    }
                    if(Body.Data[i].QChg.ConSMode == "00"){
                        Body.Data[i].QChg.ConSMode = "仅显示";
                    }else if(Body.Data[i].QChg.ConSMode == "01"){
                        Body.Data[i].QChg.ConSMode = "批次";
                    }else if(Body.Data[i].QChg.ConSMode == "02"){
                        Body.Data[i].QChg.ConSMode = "序号";
                    }
                    if(Body.Data[i].QChg.MaCode == ""){
                        StrTr += "<tr>" +
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>";
                    }else{
                        StrTr += "<tr>" +
                            "<td>" + Body.Data[i].QChg.MaCode + "</td>" +
                            "<td>" + Body.Data[i].QChg.MaName + "</td>" +
                            "<td>" + Body.Data[i].QChg.SpecName + "</td>" +
                            "<td>" + Body.Data[i].QChg.Num + "</td>" +
                            "<td>" + Body.Data[i].QChg.ConSMode + "</td>" +
                            "<td>" + (Body.Data[i].QChg.Checked == "00"?"是":"否") + "</td>";
                    }
                    if(Body.Data[i].HChg.MaCode == ""){
                        StrTr +=
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>" +
                            "<td>--</td>";
                    }else{
                        StrTr +=
                            "<td>" + Body.Data[i].HChg.MaCode + "</td>" +
                            "<td>" + Body.Data[i].HChg.MaName + "</td>" +
                            "<td>" + Body.Data[i].HChg.SpecName + "</td>" +
                            "<td>" + Body.Data[i].HChg.Num + "</td>" +
                            "<td>" + Body.Data[i].HChg.ConSMode + "</td>" +
                            "<td>" + (Body.Data[i].HChg.Checked == "00"?"是":"否") + "</td>";
                    }

                    if(Body.Data[i].OptType == "00"){
                        StrTr += "<td>√</td><td></td><td></td>";
                    }else if(Body.Data[i].OptType == "01"){
                        StrTr += "<td></td><td></td><td>√</td>";
                    }else if(Body.Data[i].OptType == "02"){
                        StrTr += "<td></td><td>√</td><td></td>";
                    }
                    StrTr += "</tr>";
                }
                $("#tbody").append(StrTr).find("td,th,tr").css({"border-bottom":"0.5px solid darkgray","border-right":"0.5px solid darkgray"});
                $("#tbody").find("tr:eq(0)").css("border-top","0.5px solid darkgray");
                $("#tbody").find("td").css("text-align","center");
            }else{
                toastr.warning("没有变更记录");
            }
        });
    });
});