$(function(){

    var MasInfo = [];
    request({url: "/Material/GetAllMaInfo", data: {"ExecType": "00"}}, function (data) {
        for (var i in data.Data) {
            var MaInfo = {
                "MaVerRd": data.Data[i].MaVerRd,
                "MaName": data.Data[i].MaName
            }
            MasInfo.push(MaInfo);
        }
    });


    //默认加载上
    var colNamesArr1 = [
        {"Caption": "id", "Name": "MaVerRd", "IsKey": true, "Hidden": true},
        {"Caption": "id", "Name": "RkDtlRd", "Hidden": true},
        {"Caption": "物料名称", "Name": "MaName", "Editable": true,"CType":"select",
            "SelectPr": {
                "Data": MasInfo,
                "DisplayName": "MaName",
                "DataValue": "MaVerRd"
            }},
        {"Caption": "数量", "Name": "Num", "Editable": true},
        {"Caption": "单位", "Name": "UnitName", "Editable": false}
    ];

    //获取仓库

    request({url:"/Store/GetAllStoreInfo", data: {
            "ExecType": "00"
        }},function(Body){
        if (Body.MsgCode == "0x00000") {
            for (var i = 0; i < Body.Data.length; i++) {
                $("#cangku").append("<option id='" + Body.Data[i].StoreRd + "'>" + Body.Data[i].StoreName + "</option>")
            }
        }
    });


   /* $.ajax({
        url:getBasePath()+'/Store/GetAllStoreInfo',
        type:'POST',
        data: {
            "ExecType": "00"
        },
        success:function(data) {
            if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                for (var i = 0; i < data.Body.Data.length; i++) {
                    $("#cangku").append("<option id='" + data.Body.Data[i].StoreRd + "'>" + data.Body.Data[i].StoreName + "</option>")
                }
            }}
    })*/

    var  RkRd=$("#hidden").val()
    var data={
        "RkRd":RkRd
    }

    request({url:"/Rk/GetRKInfo",data: {
            "ExecType": "00",
            "BusData":JSON.stringify(data)
        }},function(Body){
        if(Body.Data==null){
            return false;
        }
        if (Body.MsgCode == "0x00000") {

            $("#AssCode").val(Body.Data.AssCode);
            $('#cangku option').each(function () {
                if ($(this).html() == Body.Data.StoreInfo.StoreName) {
                    $(this).attr("selected", "selected");
                }
            })
            $("#asSource").val(Body.Data.AssSource);
            $("#type option").each(function () {
                if ($(this).attr("id") == Body.Data.RkType) {
                    $(this).attr("selected", "selected");
                }
            });
            var config1 = {
                tableId: "list1",
                data: Body.Data.RKMaInfo,
                colArr: colNamesArr1,
                multiselect: true,
                event: {
                    oncellchange: function (data) {
                        var MaVerRd = data.cellvalue;
                        var busData = {
                            "MVerRd": MaVerRd
                        };
                        request({
                            url: "/Commom/GetCMWFInfo",
                            data: {"ExecType": "00", "busData": JSON.stringify(busData)}
                        }, function (resdata) {
                            var config = {
                                "rowid": data.rowed,
                                "colname": "UnitName",
                                "celldata": resdata.Data.MaInfo.UnitInfo.UnitName
                            };
                            setCell("list1", config);
                        });
                    }
                }
            };
            fullTable(config1);
        }
        if (Body.MsgCode == "MES_002") {
            alertError(Body.MsgDes)
        }
    });

    /*$.ajax({
        url:getBasePath()+'/Rk/GetRKInfo',
        type:'POST',
        data: {
            "ExecType": "00",
            "BusData":JSON.stringify(data)
        },
        success:function(data) {

            if(data.Status == "00"&&data.Body.Data==null){
                return false;
            }
            if (data.Status == "00" && data.Body.MsgCode == "0x00000") {

                $("#AssCode").val(data.Body.Data.AssCode);
                $('#cangku option').each(function () {
                    if ($(this).html() == data.Body.Data.StoreInfo.StoreName) {
                        $(this).attr("selected", "selected");
                    }
                })
                $("#asSource").val(data.Body.Data.AssSource);
                $("#type option").each(function () {
                    if ($(this).attr("id") == data.Body.Data.RkType) {
                        $(this).attr("selected", "selected");
                    }
                });
                var config1 = {
                    tableId: "list1",
                    data: data.Body.Data.RKMaInfo,
                    colArr: colNamesArr1,
                    multiselect: true,
                    event: {
                        oncellchange: function (data) {
                            var MaVerRd = data.cellvalue;
                            var busData = {
                                "MVerRd": MaVerRd
                            };
                            request({
                                url: "/Commom/GetCMWFInfo",
                                data: {"ExecType": "00", "busData": JSON.stringify(busData)}
                            }, function (resdata) {
                                var config = {
                                    "rowid": data.rowed,
                                    "colname": "UnitName",
                                    "celldata": resdata.Data.MaInfo.UnitInfo.UnitName
                                };
                                setCell("list1", config);
                            });
                        }
                    }
                };
                fullTable(config1);
            }
            if (data.Status == "00" && data.Body.MsgCode == "MES_002") {
                    alertError(data.Body.MsgDes)
            }

        }
       //
    })*/


    //添加

    $("#addMve").click(function () {
        addErow("list1");
    });

    //删除表格
    $(".del1").click(function(){
        derow("list1");
    })
    //保存
    var jsonSource=[];
    $("#save").click(function(){
        var StoreRd= $('#cangku option:selected').attr("id");
        var AssCode=$("#AssCode").val();
        var Remark=$("#beizhu").val();
        var AssSource=$("#asSource").val();
        var data = getRowData("list1");

        if(data.length==0) {
            alertError("更新物料版本不能为空")
            return false;
        }

        for(var j in data){
            if(data[j].Num==""||data[j].Num==null){
                alertError("更新数量不能为空")
                return false;
            }
        }

        var RKMaInfo=[];
        jQuery(data).each(function () {
            var RKMa = {
                "MaVerRd": this.MaVerRd,
                "Num": this.Num,
                "UnitName": this.UnitName,
                "RkDtlRd":this.RkDtlRd
            };
            RKMaInfo.push(RKMa);
        });

        var newDate={
            "RkRd":RkRd,
            "StoreRd":StoreRd,
            "AssCode":AssCode,
            "AssSource":AssSource,
            "Remark":Remark,
            "RKMaInfo":RKMaInfo
        }
        request({url:"/Rk/SaveRKInfo",  data: {
                "ExecType": "02",
                "busData": JSON.stringify(newDate)
            }},function(Body){
            if(Body.MsgCode=="MES_001"){
                alertSuccess(Body.MsgDes)
            }
            if (Body.MsgCode == "0x00000") {
                alertSuccess(Body.MsgDes)
                $("#hidden").val("")
            }
            if (Body.MsgCode == "MG_MES3001012010001F") {
                alertError(Body.MsgDes)
            }

        });
    /*  $.ajax({
            url:getBasePath()+"/Rk/SaveRKInfo",
            type:"POST",
            data: {
                "ExecType": "02",
                "busData": JSON.stringify(newDate)
            },
            success:function (res) {
            if(res.Body.MsgCode=="MES_001"){
                alertSuccess(res.Body.MsgDes)
            }
            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                alertSuccess(res.Body.MsgDes)
                $("#hidden").val("")
            }
            if (res.Status == "00" && res.Body.MsgCode == "MG_MES3001012010001F") {
                alertError(res.Body.MsgDes)
            }

            }
        });*/

    });
})
