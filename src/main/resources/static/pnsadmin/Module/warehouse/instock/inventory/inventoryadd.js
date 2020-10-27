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

    $(".cSave").click(function () {

        var Remark = $("#beizhu").val();
        var StoreRd = $("#defaultSelect").getseldata().StoreRd;
        if(StoreRd==""){
            toastr.warning("仓库不能为空");
            return;
        }
        var newData = {
            "StoreRd": StoreRd,
            "Remark": Remark
        }

        request({
                url: "/PD/SavePDInfo",
                data: {
                    "ExecType": "00",
                    "busData": JSON.stringify(newData)
                }
            },
            function (Body) {
                layer.alert("盘点信息新增成功,盘点单号{"+Body.Data.PDCode+"}", {
                    icon: 1,
                    skin: 'layer-ext-moon'
                },function() {
                    parent.layer.closeAll();
                });
            });
    });
});