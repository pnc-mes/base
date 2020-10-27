/**
 * Created by xfxi on 2017/7/19.
 */

$(function(){

    var rePrInfo = sessionStorage.getItem("data");
    var rePrInfo2 = sessionStorage.getItem("data2");
    var rePrInfo3 = sessionStorage.getItem("data3");

    sessionStorage.removeItem("data");
    sessionStorage.removeItem("data2");
    sessionStorage.removeItem("data3");

    //加载打印机
    request({ url: "/Printer/GetAllPrInfo",data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].PrRd + '>' + data[i].PrName + '【' + data[i].PrSer + '】</option>';
        }

        $("#prints").html(aa);
        $("#prints").selectpicker({
            width:300
        });
    });

    //加载打印模板
    request({ url: "/PrintT/GetAllPtInfo",data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;
        var aa = "";
        for (var i in data) {
            aa += '<option  value=' + data[i].PtRd + '>' + data[i].PtName + '</option>';
        }

        $("#PrintTs").html(aa);
        $("#PrintTs").selectpicker({
            width:300
        });
    });

    /*$("#PrintT").find('input').attr("disabled",true);
    $("#PrintT").find('img').hide();*/
    //默认禁用组件
    $('#PrintTs').prop('disabled', true);
    $('#PrintTs').selectpicker('refresh');

    //确认打印
    $("#confirm").click(function(){
        //printRd
        //var printRd = $("#defaultSelect").getseldata().PrRd;
        var printRd = undefined;
        if($("#prints").val() != undefined){
            printRd = $("#prints").val()[0];
        }
        if(printRd == undefined){
            toastr.warning("打印机不能为空");
            return;
        }

        var busData = {
            PrintRd: printRd,
            //PrintTRd: printTRd,
            PrintCount: $("#printCount").val(),
            PrintCopy: $("#printCopy").val()
        };

        if($("#isPrintT").prop("checked")) {
            //获取打印机、打印模板
            //var printTRd = $("#defaultSelect").getseldata().PtRd;
            var printTRd = undefined;//$("#PrintT").getseldata().PtRd;
            if($("#PrintTs").val() != undefined){
                printTRd = $("#PrintTs").val()[0];
            }
            if(printTRd == undefined){
                toastr.warning("打印模板不能为空");
                return;
            }
            busData.PrintTRd = printTRd;
            busData.RePrInfo = JSON.parse(rePrInfo3);

            saveRePr("02", busData);
        }else{
            busData.RePrInfo = JSON.parse(rePrInfo);
            saveRePr("00", busData);
        }
    });

    $("#isPrintT").click(function () {
        if($(this).prop("checked")){
            /*$("#PrintT").find('input').attr("disabled",false);
            $("#PrintT").find('img').show();*/
            $('#PrintTs').prop('disabled', false);
            $('#PrintTs').selectpicker('refresh');
        }else{
            /*$("#PrintT").find('input').attr("disabled",true);
            $("#PrintT").find('img').hide();*/
            $("#PrintTs").selectpicker('val', "");
            $('#PrintTs').prop('disabled', true);
            $('#PrintTs').selectpicker('refresh');
        }
    });

    //重打
    function saveRePr(exectype, busData){
        request({url:"/HisPrintLog/SaveRePrInfo",data: {"ExecType": exectype, "BusData": JSON.stringify(busData)}},function(Body){
            storage.setItem("sure","0");
            parent.layer.closeAll("iframe");
        });
    }
});