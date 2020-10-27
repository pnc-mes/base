/**
 * Created by liufuzhi on 2017/6/29.
 */
$(function () {
    //关闭弹出层
    $(".cLose").on("click", function () {
        parent.layer.closeAll();
    });

    var params = {
        "displaymode": "0",
        "title": "仓库",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "仓库id",
                    "name": "StoreRd"
                }, {
                    "caption": "仓库名称",
                    "name": "StoreName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"StoreName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                        url: "/Store/GetAllStoreInfo",
                        data: {
                            "ExecType": "00",
                            "InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)
                        }
                    },
                    function (Body) {
                        var datas = Body.Data;

                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "StoreRd": datas[i].StoreRd,
                                "StoreName": datas[i].StoreName,
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
    $("#defaultSelect").zc_select(params);

    //编辑
    var PDRd = $("#hidden").val();

    var pdRd = {
        "PDRd": PDRd
    }

    var objData = {
        "ExecType": "00",
        "BusData": JSON.stringify(pdRd)
    };
    request({
            url: "/PD/GetPDInfo",
            data: objData
        },
        function (Body) {
            $("#pand").attr("disabled",true);
            var PDCode = $("#pand").val(Body.Data.PDCode);
            $("#defaultSelect").showData({
                id:Body.Data.StoreInfo[0].StoreRd,
                name:Body.Data.StoreInfo[0].StoreName,
                keyfield:"StoreRd",
                fields:[
                    {"name":"StoreRd"},
                    {"name":"StoreName"}
                ]
            });
        });


    //保存
    $(".cSave").click(function () {

        var id = $("#hidden").val();
        var Remark = $("#beizhu").val();
        var StoreRd = $("#defaultSelect").getseldata().StoreRd;
        if(StoreRd==""){
            toastr.warning("仓库不能为空");
            return;
        }
        var newData = {
            "PDRd": id,
            "StoreRd": StoreRd,
            "Remark": Remark
        }
        request({
                url: "/PD/SavePDInfo",
                data: {
                    "ExecType": "02",
                    "busData": JSON.stringify(newData)
                }
            },
            function (Body) {
                layer.alert(Body.MsgDes, {
                    icon: 1,
                    skin: 'layer-ext-moon'
                },function() {
                    parent.layer.closeAll();
                });
            });

    });


});