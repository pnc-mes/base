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
        multiselect: true,
        width:0.84,
        height:0.528
    };
    fullTable(obj);

    //载具序列号回车
    $("#carrierSN").keydown(function (event) {
        if(event.keyCode=="13"){
            if($("#carrierSN").is(":focus")){
                var VenderSN=$("#carrierSN").val();
                if(VenderSN==null||VenderSN==""){
                 toastr.warning("请输入载具序列号");
                 return false;
                }

                //获取载具关系 信息
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
                        var table = {
                            data:sources,
                            colArr: colNamesArr,
                            tableId: "table",
                            multiselect: true,
                            width:0.84,
                            height:0.528
                        };
                        fullTable(table);
                    }
                );

            }
        }

    });

    //保存
    $("._Save").click(function () {
        var VenderSN=$("#carrierSN").val();
        if(VenderSN==null||VenderSN==""){
            toastr.warning("请输入载具序列号");
            return false;
        }
        //批次信息
        var tableData = getSeRowData("table");
        var BInfo=[];
        tableData.forEach(function (e) {
            BInfo.push({"Batch":e.Batch});
        });

        if(BInfo==null||BInfo.length==0){
            toastr.warning("请选择批次信息");
            return false;
        }
        var data={"VenderSN":VenderSN,
                    "BInfo":BInfo};
        request(
            {   url: "/CarrierRelation/SaveCarrierRelationInfo",
                data: {"ExecType": "01", "BusData": JSON.stringify(data)}
            },function (e) {
                toastr.success(e.MsgDes);
                //获取载具关系 信息
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
                        var table = {
                            data:sources,
                            colArr: colNamesArr,
                            tableId: "table",
                            multiselect: true,
                            width:0.84,
                            height:0.528
                        };
                        fullTable(table);
                    }
                );
            });




    });
    
});