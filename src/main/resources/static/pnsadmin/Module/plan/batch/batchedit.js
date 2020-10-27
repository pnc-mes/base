/**
 * Created by PNC on 2017/6/29.
 */
$(function(){

    var objBRd = $("#BRd").attr("value");
    var objGetBatchBusData = {
        "BRd":objBRd
    }
    var objBInfo;

    $("#jsdate").jeDate({
        isinitVal:true,
        //festival:true,
        ishmsVal:false,
        minDate: '2016-06-16 23:59:59',
        maxDate: $.nowDate({DD:0}),
        format:"YYYY-MM-DD hh:mm:ss",
        zIndex:3000,
    })

    $("#jfdate").jeDate({
        isinitVal:true,
        //festival:true,
        ishmsVal:false,
        minDate: '2016-06-16 23:59:59',
        maxDate: $.nowDate({DD:0}),
        format:"YYYY-MM-DD hh:mm:ss",
        zIndex:3000,
    })

    request({url: "/Batch/GetBatchInfo", data:  {
            "ExecType": "00",
            "BusData": JSON.stringify(objGetBatchBusData)
        },async: false}, function (Body) {
        objBInfo = Body.Data;
        $("#woCode").val(objBInfo.WoCode);
        $("#woNum").val(objBInfo.Num);
        $("#maName").val(objBInfo.MaName);
        $("#gylc").val(objBInfo.WFName);
        $("#gx").append("<option>"+objBInfo.Spec.SpecName+"</option>");
        $("#jsdate").val(objBInfo.JStartDate);
        $("#jfdate").val(objBInfo.JFinishDate);
        $("#bcount").val(objBInfo.BCount);
        $("#dymb").val(objBInfo.PrintTInfo.PrintTName);
        $("#dyj").append("<option>"+objBInfo.PrintInfo.PrintName+"</option>");
    });

    /*$.ajax({
        url: getBasePath()+"/Batch/GetBatchInfo",
        type: 'post',
        data: {
            "ExecType": "00",
            "BusData":JSON.stringify(objGetBatchBusData)
        },
        success: function (res) {
            console.log(res);
             objBInfo = res.Body.Data;
            $("#woCode").val(objBInfo.WoCode);
            $("#woNum").val(objBInfo.Num);
            $("#maName").val(objBInfo.MaName);
            $("#gylc").val(objBInfo.WFName);
            $("#gx").append("<option>"+objBInfo.Spec.SpecName+"</option>");
            $("#jsdate").val(objBInfo.JStartDate);
            $("#jfdate").val(objBInfo.JFinishDate);
            $("#bcount").val(objBInfo.BCount);
            $("#dymb").val(objBInfo.PrintTInfo.PrintTName);
            $("#dyj").append("<option>"+objBInfo.PrintInfo.PrintName+"</option>");
           /!* if(res.Status=="00"){
                alert(res.Body.MsgDes);
            }*!/
        }
    });*/

    $("#cSave").click(function () {
        var objBusData = {
            "BRd": objBInfo.BRd,
            "SpecVerRd": objBInfo.Spec.SpecVerRd,
            "PrintTRd": objBInfo.PrintTRd,
            "PrintRd": objBInfo.PrintRd,
            "BCount": objBInfo.BCount,
            "JStartDate": "2017-06-11 19:31:21",
            "JFinishDate": "2017-06-11 19:31:21",
            "Remark": "编辑测试"
        }
        request({url: "/Batch/SaveBatchInfo", data: {
                "ExecType": "02",
                "BusData":JSON.stringify(objBusData)
            }}, function (Body) {
            showMsg(Body.MsgDes);
        });
        //编辑批次信息
        /*$.ajax({
            url: getBasePath()+"/Batch/SaveBatchInfo",
            type: 'post',
            data: {
                "ExecType": "02",
                "BusData":JSON.stringify(objBusData)
            },
            success: function (res) {
                console.log(res);
                if(res.Status=="00"){
                    showMsg(res.Body.MsgDes);
                }
            }
        });*/
    });
});