/**
 * Created by test on 2017/6/28.
 */
$(function () {

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
        {"Caption": "物料名称", "Name": "MaName", "Editable": true,"CType":"select",
            "SelectPr": {
                "Data": MasInfo,
                "DisplayName": "MaName",
                "DataValue": "MaVerRd"
            }},
        {"Caption": "数量", "Name": "Num", "Editable": true},
        {"Caption": "单位", "Name": "UnitName", "Editable": false}
    ];

    var config1 = {
        tableId: "list1",
        data: null,
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
    request({url:"/Store/GetAllStoreInfo",  data: {
            "ExecType": "00"
        }},function(Body){
        if (Body.MsgCode == "0x00000") {
            for (var i = 0; i < Body.Data.length; i++) {
                $("#cangku").append("<option id='" + Body.Data[i].StoreRd + "'>" + Body.Data[i].StoreName + "</option>")
            }
        }
    });
    //获取仓库
   /* $.ajax({
        url: getBasePath() + '/Store/GetAllStoreInfo',
        type: 'POST',
        data: {
            "ExecType": "00"
        },
        success: function (data) {
            if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                for (var i = 0; i < data.Body.Data.length; i++) {
                    $("#cangku").append("<option id='" + data.Body.Data[i].StoreRd + "'>" + data.Body.Data[i].StoreName + "</option>")
                }
            }
        }
    })*/

    var daSource = [];
    $("#addMve").click(function () {
        addErow("list1");
    });

    //删除表格
    $(".del1").click(function(){
      derow("list1");
    })

    //新增
    var jsonSource=[];
    $("#save").click(function () {
        var StoreRd = $('#cangku option:selected').attr("id");
        var AssCode = $("#AssCode").val();
        if ($('#type option:selected').attr("id") == "00") {
            var RkType = "00";
        }
        if ($('#type option:selected').attr("id") == "01") {
            var RkType = "01";
        }
        if ($('#type option:selected').attr("id") == "02") {
            var RkType = "02";
        }
        if ($('#type option:selected').attr("id") == "03") {
            var RkType = "03";
        }
        var Remark = $("#beizhu").val();
        var data = getRowData("list1");

        if(data.length==0) {
            alertError("新增物料版本不能为空")
            return false;
        }

        for(var i in data){
            if(data[i].Num==""||data[i].Num==null){
                alertError("新增数量不能为空")
                return false;
            }
        }

        var RKMaInfo=[];
        jQuery(data).each(function () {
            var RKMa = {
                "MaVerRd": this.MaVerRd,
                "Num": this.Num,
                "UnitName": this.UnitName
            };
            RKMaInfo.push(RKMa);
        });

        var newDate = {
            "StoreRd": StoreRd,
            "AssCode": AssCode,
            "RkType": RkType,
            "RKMaInfo": RKMaInfo,
            "Remark": Remark
        };

        request({url:"/Rk/SaveRKInfo",data: {"ExecType": "00" ,"busData": JSON.stringify(newDate)}},function(res){
            layer.alert(res.MsgDes, {
                    icon: 1,
                    skin: 'layer-ext-moon'
                },function() {
                    layer.closeAll();
                    if(!confirm("确认要继续新增吗？")){
                        parent.layer.closeAll();
                    }else {
                        $("#cangku").val($('#cangku option:first').val());
                        $("#type").val($('#type option:first').val());
                        $("#AssCode").val("");
                        var config1 = {
                            tableId: "list1",
                            data: null,
                            colArr: colNamesArr1,
                            multiselect: true
                        };
                        fullTable(config1);
                    }
                }
            );
        });
    });
})