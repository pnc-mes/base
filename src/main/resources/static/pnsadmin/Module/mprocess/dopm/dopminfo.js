$(function () {
    //查询事件
    var sourceData=[];
    var tableSource=[];

    //设备
    request({url:"/Device/GetAllDevInfo", data: {"ExecType": "00"}},function(Body2){
        var data = Body2.Data;
        var aa="";
        if(data != undefined) {
            for (var i in data) {
                aa += '<option value=' + data[i].DevRd + '>' + data[i].DevName + '</option>';
            }
        }
        $("#shebeis").html(aa);
        $("#shebeis").selectpicker({
            width:200
        });
        //$("#shebeis").selectpicker('refresh');
    });
    //工具
    request({url:"/Tool/GetAllToolsInfo", data: {"ExecType": "00"}},function(Body2){
        var data = Body2.Data;
        var aa="";
        if(data != undefined) {
            for (var i in data) {
                aa += '<option value=' + data[i].ToolRd + '>' + data[i].ToolName + '</option>';
            }
        }
        $("#gongjus").html(aa);
        $("#gongjus").selectpicker({
            width:200
        });
        //$("#gongjus").selectpicker('refresh');
    });

    //载具
    request({url:"/Carrier/GetAllCarriersInfo", data: {"ExecType": "00"}},function(Body2){
        var data = Body2.Data;
        var aa="";
        if(data != undefined) {
            for (var i in data) {
                aa += '<option value=' + data[i].CarrierRd + '>' + data[i].CarrierName + '</option>';
            }
        }
        $("#zaijus").html(aa);
        $("#zaijus").selectpicker({
            width:200
        });
        //$("#zaijus").selectpicker('refresh');
    });


 /*   var params0 = {
        "displaymode": "0",
        "title": "设备",
        "binddata": {
            "keyfield": "DevRd",
            "fields": [
                {
                    "caption": "设备id",
                    "name": "DevRd"
                }, {
                    "caption": "设备名称",
                    "name": "DevName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "DevName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },{
                            "FieldName": "DevName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/Device/GetAllDevInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevRd": datas[i].DevRd,
                            "DevName": datas[i].DevName,
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
    $("#shebei").zc_select(params0);*/



    var function00=function(){
        $("#shebei").show();
        $("#gongju").hide();
        $("#zaiju").hide();
    }

    //工具
    /*var params1 = {
        "displaymode": "0",
        "title": "工具",
        "binddata": {
            "keyfield": "ToolRd",
            "fields": [
                {
                    "caption": "工具id",
                    "name": "ToolRd"
                }, {
                    "caption": "工具名称",
                    "name": "ToolName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "ToolName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },{
                            "FieldName": "ToolName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/Tool/GetAllToolsInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "ToolRd": datas[i].ToolRd,
                            "ToolName": datas[i].ToolName,
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
    $("#gongju").zc_select(params1);*/
    var function01=function(){
        $("#shebei").hide();
        $("#gongju").show();
        $("#zaiju").hide();
    }

    //载具
    /*var params2 = {
        "displaymode": "0",
        "title": "载具",
        "binddata": {
            "keyfield": "CarrierRd",
            "fields": [
                {
                    "caption": "载具id",
                    "name": "CarrierRd"
                }, {
                    "caption": "载具名称",
                    "name": "CarrierName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onclick": function (res) {

            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CarrierName",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        },{
                            "FieldName": "CarrierName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({
                    url: "/Carrier/GetAllCarriersInfo",
                    data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CarrierRd": datas[i].CarrierRd,
                            "CarrierName": datas[i].CarrierName,
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
    $("#zaiju").zc_select(params2);*/
    var function02=function(){
        $("#zaiju").show();
        $("#shebei").hide();
        $("#gongju").hide();
    }

    //点击页面时默认加载设备
    $("#bydxlx").val("00");
    function00();

    //保养对象类型
    $("#bydxlx").change(function () {
        var value=$(this).val();
        if(value=="00"){
            function00();
        }
        if(value=="01"){
            function01();
        }
        if(value=="02"){
            function02()
        }
    });

    //任务清单事件
    var seLSources=[];
    var ff=function(){
        $("#rwqd").change(function(){
            var DoTaskRd=$(this).val();
            if(sourceData.length>0){
                for(var i  in  sourceData){
                    if(sourceData[i].DoTaskRd==DoTaskRd){
                        seLSources=sourceData[i].DoTaskDtlInfo;
                        var start = new Date(sourceData[i].MaTime);
                        if(new Date()>start){
                            $("#rwms").attr("style","color:red;text-align:center;vertical-align:middle;");
                            var strHtml="";
                            for(var i in seLSources){
                                strHtml+="<tr><td id='"+seLSources[i].DoTaskDtlRd+"' style='display: none;'>"+seLSources[i].DoTaskDtlRd+"</td><td style='color: red; text-align:center;vertical-align:middle;'>"+seLSources[i].TaskItemName+"</td><td style='display: none;'>"+seLSources[i].ExecResult+"</td><td style='text-align:left;'>"+(seLSources[i].SureType=="00"?" <input type='checkbox' id='"+seLSources[i].DoTaskDtlRd+'s'+"' />打勾":"<input type='text' style='width: 350px;' id='"+seLSources[i].DoTaskDtlRd+'s'+"' />")+"</td></tr>"
                            }
                            $("#tbody").empty().append(strHtml);
                        }else {
                            $("#rwms").removeAttr("style");
                            var strHtml="";
                            for(var i in seLSources){
                                strHtml+="<tr><td id='"+seLSources[i].DoTaskDtlRd+"'  style='display: none;'>"+seLSources[i].DoTaskDtlRd+"</td><td>"+seLSources[i].TaskItemName+"</td><td style='display: none;'>"+seLSources[i].ExecResult+"</td><td>"+(seLSources[i].SureType=="00"?" <input type='checkbox' id='"+seLSources[i].DoTaskDtlRd+'s'+"' />打勾":"<input type='text' style='width: 350px;' id='"+seLSources[i].DoTaskDtlRd+'s'+"'/>")+"</td></tr>"
                            }
                            $("#tbody").empty().append(strHtml);
                        }
                    }
                }
            }
        });
    }
    ff();

      //  var currData=[];
    //保存
    $(".cSave").click(function(){
        var DoPMRd = $("#rwqd").val();
        if (DoPMRd == null || DoPMRd == "") {
            toastr.warning("保存失败，任务清单不能为空");
            return false;
        }
        var PMDtl=[];
        $("#tbody tr").each(function() {
            var flag = $(this).find("td").eq(3).find("input[type=checkbox]").is(":checked");
            var textFlag = $(this).find("td").eq(3).find("input[type=text]");
            var txtValue = $(this).find("td").eq(3).find("input[type=text]").val();
            var PMDtls = {
                "DoPMDtlRd": $(this).find("td").eq(0).html(),
                "DoPMResult": textFlag.length == 0 ? (flag ? "00" : "01") : txtValue
            }
            PMDtl.push(PMDtls);



        })

        if (PMDtl.length <= 0) {
            toastr.warning("保存失败，任务结果和描述不能为空");
            return false;
        }
        var chec = {
            "DoPMRd": DoPMRd,
            "PMDtl": PMDtl
        }

        request({
            url: "/DoTask/SavePMACInfo",
            data: {"ExecType": "00", "busData": JSON.stringify(chec)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
            $("#rwqd").empty().val("");
            $("#tbody").empty();
            $("#rwms").attr("style","text-align:center;vertical-align:middle;color:#000;");
        });
    });

    //加载出来的日期
    $("#chaxun").click(function(){
        var PMType=$("#bylx").val();
        var PMObjType=$("#bydxlx").val();
        var Status=$("#status").val();
        var PMObjRd="";
        if(PMObjType=="00"){
            PMObjRd=$("#shebeis").val();
        }else if(PMObjType=="01"){
            PMObjRd=$("#gongjus").val();
        }else {
            PMObjRd=$("#zaijus").val();
        }

        if(PMObjRd==null||PMObjRd==""||PMObjRd==undefined){
            toastr.warning("查询失败，保养对象不能为空");
            return false;
        }
        var objBusData = JSON.stringify({"PMType": PMType,"PMObjType":PMObjType,"PMObjRd":PMObjRd[0],"Status":Status});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/DoTask/GetPMACInfo", data: objData,}, function (Body) {
            sourceData=Body.Data;
            if(sourceData.length>0){
                var aa="";
                for(var i  in sourceData){
                    aa+="<option value='"+sourceData[i].DoTaskRd+"'>"+sourceData[i].DoTaskName+"</option>"
                }
                $("#rwqd").empty().html(aa);

                $("#rwqd").val(sourceData[0].DoTaskRd);

                for(var i in sourceData){
                    if(sourceData[0].DoTaskRd==sourceData[i].DoTaskRd){
                        tableSource=sourceData[i].DoTaskDtlInfo;
                    }
                }

                var    Dates=sourceData[0].MaTime;
                if(Dates!=null||Dates!=""){
                    var start = new Date(Dates);
                    if(new Date()>start){
                        $("#rwms").attr("style","color:red;text-align:center;vertical-align:middle;");
                        var strHtml="";
                        for(var i in tableSource){
                            strHtml+="<tr><td style='display: none;' id='"+tableSource[i].DoTaskDtlRd+"' >"+tableSource[i].DoTaskDtlRd+"</td><td   style='color: red;text-align:center;vertical-align:middle;text-overflow:ellipsis; white-space:nowrap;overflow:hidden;'>"+tableSource[i].TaskItemName+"</td><td style='display: none;'>"+tableSource[i].ExecResult+"</td><td style='text-align:left;'>"+(tableSource[i].SureType=="00"?" <input type='checkbox' id='"+tableSource[i].DoTaskDtlRd+'s'+"' />打勾":"<input type='text'  style='width: 350px;'  id='"+tableSource[i].DoTaskDtlRd+'s'+"'/>")+"</td></tr>"
                        }
                        $("#tbody").empty().append(strHtml);
                    }else {
                        $("#rwms").removeAttr("style");
                        var strHtml="";
                        for(var i in tableSource){
                            strHtml+="<tr><td style='display: none;' id='"+tableSource[i].DoTaskDtlRd+"'  >"+tableSource[i].DoTaskDtlRd+"</td><td style='text-overflow:ellipsis; white-space:nowrap;overflow:hidden;'>"+tableSource[i].TaskItemName+"</td><td style='display: none;'>"+tableSource[i].ExecResult+"</td><td>"+(tableSource[i].SureType=="00"?" <input type='checkbox'  id='"+tableSource[i].DoTaskDtlRd+'s'+"' />打勾":"<input type='text'  style='width: 350px;'  id='"+tableSource[i].DoTaskDtlRd+'s'+"'/>")+"</td></tr>"
                        }
                        $("#tbody").empty().append(strHtml);
                    }
                }
                ff();
            }else {
                toastr.warning("查询失败，无数据");
                $("#tbody").empty();
                $("#rwqd").empty();
                $("#rwms").removeAttr("style");
                $("#rwms").attr("style","text-align:center;vertical-align:middle;");
            }
        });
        ff();
    });
    $("#tbody tr").blur("mouseenter",function() {
        alert()
        if (this.offsetWidth < this.scrollWidth) {
            var that = this;
            var text = $(this).text();
            layer.tips(text, that,{
                tips: 1,
                time: 2000
            });
        }
    });
});