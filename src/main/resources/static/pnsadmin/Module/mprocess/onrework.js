/**
 * Created by xfxi on 2017/9/27.
 */
$(function(){
    //查询
    $("#search_").click(function(){
        var batch = $("#batch").val().trim();
        if(batch != undefined && batch != ""){
            getCMB(batch);
        }
    });

    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#batch").is(":focus")){
                var batch = $("#batch").val().trim();
                if(batch != undefined && batch != ""){
                    getCMB(batch);
                }
            }
        }
    });

    //加载原因代码
    var params = {
        "displaymode": "0",
        "title": "原因代码",
        "binddata": {
            "keyfield": "ReaRd",
            "fields": [
                {
                    "caption": "原因代码id",
                    "name": "ReaRd"
                }, {
                    "caption": "原因描述",
                    "name": "ReaDes"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"ReaType",
                            "FieldOpt":"=",
                            "FieldVal":"03"
                        },
                        {
                            "FieldName":"ReaDes",
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
                request({url:"/Reason/GetAllReaInfo",
                        data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},
                    function(Body) {
                        var datas = Body.Data;

                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "ReaRd": datas[i].ReaRd,
                                "ReaDes": datas[i].ReaDes,
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

    //保存
    $("#save_").click(function(){
        var batch = $("#batch").val().trim();
        if(batch == undefined || batch == ""){
            toastr.warning("批次不能为空");
            return;
        }
        var tarScVRd = $("#reSpec").val();
        if(tarScVRd == undefined || tarScVRd == "" || isNaN(tarScVRd)){
            toastr.warning("重工路径不能为空");
            return;
        }
        var reaDes = $("#defaultSelect").getseldata().ReaRd;
        if(reaDes == undefined ){
            toastr.warning("重工原因不能为空");
            return;
        }

        var data = {
            Batch: batch,
            TarScVRd: tarScVRd,
            ReaDes: reaDes
        };

        saveIOS(JSON.stringify(data));
    });

    //获取批次关联信息
    function getCMB(batch){
        request({url:"/Commom/GetCMBInfo",data: {"ExecType": "00", "BusData": "{\"Batch\":\"" + batch + "\"}"}},function(Body){
            reClear();

            var data = Body.Data;

            if(data.WoCode == undefined || data.WoCode == ""){
                toastr.warning("该批次不是产成品");
                return;
            }

            $("#woCode").val(data.WoCode);
            $("#procedureName").val(data.MaName);

            if(data.WFInfo == undefined){
                toastr.warning("该工序流程为空");
                return;
            }
            $("#workflow").val(data.WFInfo.WFName);

            //当前工序
            var cspecinfo = data.WFInfo.CSpecInfo;
            if(cspecinfo == undefined){
                toastr.warning("该批次还未开始生产");
                return;
            }
            $("#specName").val(cspecinfo.SpecName);

            //目标工序
            var path = cspecinfo.Path;
            if(path == undefined || path.length <= 0){
                toastr.warning("该批次下一道工序不存在返工工序");
                return;
            }
            path.forEach(function (obj) {
                if(obj.RouteType == "02") {
                    $("#reSpec").append("<option value='" + obj.SpecVerRd + "'>" + obj.SpecName + "</option>");
                }
            });
        });
    }

    //清空
    function reClear(){
        $("#woCode").val("");
        $("#procedureName").val("");
        $("#workflow").val("");
        $("#specName").val("");
        $("#reSpec").empty();
        $("#reaDes").empty();
    }

    //重工
    function saveIOS(data){
        request({url:"/SpecOpert/GetIOSInfo",data: {"ExecType": "05", "BusData": data}},function(Body) {
            toastr.success(Body.MsgDes);
        });
    }
});
