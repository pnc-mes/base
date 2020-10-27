$(function () {

    //定义表格
    //表格
    var colNamesArr = [
        {"Caption": "批次/序列号", "Name": "Batch"},
        {"Caption": "物料代码", "Name": "MaCode"},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false, "CType": "text"},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false, "CType": "text"},
    ];

    var obj = {
        data:[],
        colArr: colNamesArr,
        tableId: "table",
        multiselect: false,
        width:0.84,
        height:0.528
    };
    fullTable(obj);

    //载具编号回车
    $("#carrierSN").keydown(function (event) {
        if(event.keyCode=="13"){
            if($("#carrierSN").is(":focus")){
                var carrierSN=$("#carrierSN").val();
                if(carrierSN==null||carrierSN==""){
                    toastr.warning("载具序列号!");
                    return false;
                }
                //回车成功焦点转移到ID
                $("#ID").focus();
            }

        }
    });

    //ID回车
    $("#ID").keydown(function (event) {
        if(event.keyCode=="13"){
            if($("#ID").is(":focus")){
                var Batch=$("#ID").val();
                var VenderSN=$("#carrierSN").val();
                if(carrierSN==null||carrierSN==""){
                    toastr.warning("载具序列号!");
                    return false;
                }
                if(Batch==""||Batch==null){
                    toastr.warning("请输入ID");
                    return false;
                }
                var data={"VenderSN":VenderSN,"Batch":Batch};
                request(
                    {   url: "/CarrierRelation/SaveCarrierRelationInfo",
                        data: {"ExecType": "00", "BusData": JSON.stringify(data)}
                    },function (e) {
                        request(
                            {   url: "/CarrierRelation/GetCarrierRelationInfo",
                                data: {"ExecType": "00", "BusData": JSON.stringify({"VenderSN":VenderSN})}
                            },function (Body) {
                                var Data=Body.Data;
                                var sources=[];
                                Data.forEach(function (e) {
                                    sources.push(
                                        {
                                            "Batch":e.Batch,
                                            "MaCode":e.MaCode,
                                            "MaName":e.MaName,
                                            "MaDes":e.MaDes,
                                        }
                                    );
                                });
                                //添加表格信息
                              //  addSrow("table", sources, "first");
                                var table = {
                                    data:sources,
                                    colArr: colNamesArr,
                                    tableId: "table",
                                    multiselect: false,
                                    width:0.84,
                                    height:0.528
                                };
                                fullTable(table);
                            }
                        );

                    }
                );
            }
        }
    });

    //保存
    $("._Save").click(function () {
        var Batch=$("#ID").val();
        var VenderSN=$("#carrierSN").val();
        if(carrierSN==null||carrierSN==""){
            toastr.warning("载具序列号!");
            return false;
        }
        if(Batch==""||Batch==null){
            toastr.warning("请输入ID");
            return false;
        }
        var data={"VenderSN":VenderSN,"Batch":Batch};
        request(
            {   url: "/CarrierRelation/SaveCarrierRelationInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(data)}
            },function (e) {
                request(
                    {   url: "/CarrierRelation/GetCarrierRelationInfo",
                        data: {"ExecType": "00", "BusData": JSON.stringify({"VenderSN":VenderSN})}
                    },function (Body) {
                        var Data=Body.Data;
                        var sources=[];
                        Data.forEach(function (e) {
                            sources.push(
                                {
                                    "Batch":e.Batch,
                                    "MaCode":e.MaCode,
                                    "MaName":e.MaName,
                                    "MaDes":e.MaDes,
                                }
                            );
                        });
                        //添加表格信息addSrow("table", sources, "first");
                        var table = {
                            data:sources,
                            colArr: colNamesArr,
                            tableId: "table",
                            multiselect: false,
                            width:0.84,
                            height:0.528
                        };
                        fullTable(table);
                    }
                );

            }
        );
    });

});