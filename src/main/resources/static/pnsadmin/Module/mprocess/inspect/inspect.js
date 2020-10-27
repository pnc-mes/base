/**
 * Created by xfxi on 2017/7/11.
 */
$(function(){

    //表格定义
    var colNamesArr1 = [
        { "Caption":"组件条码", "Name":"SplitBatch", "CType":"text"},
        { "Caption":"工单号", "Name":"BadFlag", "CType":"text"},
        { "Caption":"生产线别", "Name":"Num", "Editable":true, "CType":"text"},
        { "Caption":"班别", "Name":"Num", "Editable":true, "CType":"text"},
        { "Caption":"操作员", "Name":"Num", "Editable":true, "CType":"text"},
        { "Caption":"检验时间", "Name":"Num", "Editable":true, "CType":"text"},
        { "Caption":"检验结果", "Name":"Num", "Editable":true, "CType":"text"}
    ];

    var config1 = {
        tableId: "list4",
        data: [],
        colArr:colNamesArr1,
        multiselect:true,
        width:0.98
    };

    fullTable(config1);


    //获取目检等级
    var insepctLevel;
    $("#A").click(function () {
        insepctLevel="A";
        alert(insepctLevel);
    });
    //获取目检等级
    $("#C").click(function () {
        insepctLevel="C";
        alert(insepctLevel);
    });
    //获取目检等级
    $("#scrap").click(function () {
        insepctLevel="报废";
        alert(insepctLevel);
    });
    //获取目检等级
    $("#APlus").click(function () {
        insepctLevel="A+";
        alert(insepctLevel);
    });


    //确认健点击事件
    $(".cSplit").click(function(){
        var batch = $("#batch").val().trim();
        if(batch != undefined &&　batch != ""){
            var datas = [];
            if(datas != undefined &&　datas.length >0){
                var busData = {
                    Batch: batch,
                    SplitInfo: m_tableData,
                    PrintTRd: printTRd,
                    PrintRd: printRd,
                    IsPrint: isPrint
                };

                saveSUBInfo(JSON.stringify(busData));
            }else{
                toastr.warning("请完善拆批信息");
            }
        }else{
            toastr.warning("组件条码不能为空！");
        }
    });


    //提交操作
    function saveSUBInfo(busData){
        request({url:"/Batch/SaveSUBInfo",data: {"ExecType": "00", "BusData": busData}},function(Body){
            toastr.success(Body.MsgDes);
            getSUBInfo($("#batch").val());
        });
    }

});