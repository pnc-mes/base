$(function () {
    //设备
    var params0 = {
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
    $("#shebei").zc_select(params0);

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

    var sourceData=[];
    $("#chaxun").click(function(){
        var CheckObjRd=$("#shebei").getseldata().DevRd
        var Status=$("#status").val();
        if(CheckObjRd==null||CheckObjRd==""){
            toastr.warning("查询失败，点检对象不能为空");
            return false;
        }
        var objBusData = JSON.stringify({"CheckObjRd": CheckObjRd,"Status":Status});
        var objData = {
            "ExecType": "01",
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
                            strHtml+="<tr><td style='display: none;' id='"+tableSource[i].DoTaskDtlRd+"' >"+tableSource[i].DoTaskDtlRd+"</td><td   style='color: red;text-align:center;vertical-align:middle;'>"+tableSource[i].TaskItemName+"</td><td style='display: none;'>"+tableSource[i].ExecResult+"</td><td style='text-align:left;'>"+(tableSource[i].SureType=="00"?" <input type='checkbox' id='"+tableSource[i].DoTaskDtlRd+'s'+"' />打勾":"<input type='text'  style='width: 350px;'  id='"+tableSource[i].DoTaskDtlRd+'s'+"'/>")+"</td></tr>"
                        }
                        $("#tbody").empty().append(strHtml);
                    }else {
                        $("#rwms").removeAttr("style");
                        var strHtml="";
                        for(var i in tableSource){
                            strHtml+="<tr><td style='display: none;' id='"+tableSource[i].DoTaskDtlRd+"'  >"+tableSource[i].DoTaskDtlRd+"</td><td>"+tableSource[i].TaskItemName+"</td><td style='display: none;'>"+tableSource[i].ExecResult+"</td><td>"+(tableSource[i].SureType=="00"?" <input type='checkbox'  id='"+tableSource[i].DoTaskDtlRd+'s'+"' />打勾":"<input type='text'  style='width: 350px;'  id='"+tableSource[i].DoTaskDtlRd+'s'+"'/>")+"</td></tr>"
                        }
                        $("#tbody").empty().append(strHtml);
                    }
                }
                ff();
            }else {
                toastr.warning("查询失败，无数据");
                $("#rwqd").empty();
                $("#tbody").empty();
                $("#rwms").removeAttr("style");
                $("#rwms").attr("style","text-align:center;vertical-align:middle;");
            }
        });
        ff();
    });


    //保存
    $(".cSave").click(function(){
        var DoCheckRd=$("#rwqd").val();
        if(DoCheckRd==null||DoCheckRd==""){
            toastr.warning("保存失败，任务清单不能为空");
            return false;
        }
        var PMDtl=[];
        $("#tbody tr").each(function() {
            var flag = $(this).find("td").eq(3).find("input[type=checkbox]").is(":checked");
            var textFlag = $(this).find("td").eq(3).find("input[type=text]");
            var txtValue = $(this).find("td").eq(3).find("input[type=text]").val();
            var PMDtls = {
                "DoCheckDtlRd": $(this).find("td").eq(0).html(),
                "DoCheckResult": textFlag.length == 0 ? (flag ? "00" : "01") : txtValue
            }
            PMDtl.push(PMDtls);
        })

        if (PMDtl.length <= 0) {
            toastr.warning("保存失败，任务结果和描述不能为空");
            return false;
        }
        var chec={
            "DoCheckRd":DoCheckRd,
            "CheckDtl":PMDtl
        }

        request({url:"/DoTask/SavePMACInfo", data: {"ExecType": "01", "busData": JSON.stringify(chec)}},function(Body){
            toastr.success(Body.MsgDes);
            $("#rwqd").empty().val("");
            $("#tbody").empty();
            $("#rwms").attr("style","text-align:center;vertical-align:middle;color:#000;");
        });


    });

});