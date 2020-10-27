/**
 * Created by test on 2017/8/11.
 */
$(function () {
    //默认加载上
    var colNamesArr1=[
        {"Caption":"id", "Name":"RelRd","IsKey":true,"Hidden":true},
        { "Caption":"箱/批次", "Name":"BarCode","Editable":false,Width:100},
        { "Caption":"物料代码", "Name":"MaCode", "Editable":false,Width:100},
        { "Caption":"物料名称", "Name":"MaName", "Editable":false },
        {  "Caption":"数量", "Name":"Num", "Editable":false,Width:80},
        {  "Caption":"单位", "Name":"UnitName", "Editable":false,Width:50}
    ];

    var config1 = {
        tableId: "list4",
        data: [],
        colArr:colNamesArr1,
        multiselect:true,
        width:0.97,
        height:0.73
    };
    fullTable(config1);

    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#batchNum").is(":focus")){
                getDP();
            }
        }
    });
    var data="";

    function getDP(){
        var BarCode = $("#batchNum").val().trim();
        if(BarCode == ""){
            toastr.warning("操作失败，该值不能为空!");
            return false;
        }
        var data={
            "BarCode":BarCode
        };

        request({url:"/Disassoc/GetSplitPInfo",async:true,data: {"ExecType": "00", "BusData": JSON.stringify(data)}},function(Body){
            if(Body.Data.BarInfo.length<=0){
                toastr.warning("获取失败，该值为空！");
                return false;
            }
            $("#BarType").val(Body.Data.BarType);
            data=Body.Data.BarInfo;
            var config1 = {
                tableId: "list4",
                data: data,
                colArr:colNamesArr1,
                multiselect:true,
                width:0.97,
                height:0.73
            };
            fullTable(config1);
        });
    }

    $("#save").click(function(){
        var BarType=$("#BarType").val();
        var data=getSeRowData("list4");
        var BarInfo=[];
        for(var a in data){
            var data1={
                "RelRd":data[a].RelRd
            }
            BarInfo.push(data1);
        }

        var data={
            "BarType":BarType,
            "BarInfo":BarInfo
        };
        if(BarInfo.length<=0){
            toastr.warning("拆包失败，请选择你要拆包的信息进行拆包!");
            return false;
        }else {
            request({url:"/Disassoc/SaveSplitPInfo",async:true,data: {"ExecType": "00", "BusData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
            });
        }
    });
});
