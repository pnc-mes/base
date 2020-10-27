/**
 * Created by PNC on 2017/6/27.
 */
$(function(){
    var colNamesArr=[
        {"Caption": "工单号", "Name": "Batch", "CType": "text", "Editable": false},
        {"Caption": "批次", "Name": "Batch", "CType": "text", "Editable": false},
        {"Caption": "产品/物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "描述", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "原批次数量", "Name": "Num", "CType": "text", "Editable": true},
        {"Caption": "变更原因", "Name": "UnitName", "CType": "text", "Editable": false}
    ];
    var config={
        tableId: "list4",
        data: [],
        colArr:colNamesArr,
        multiselect:true,
        width:0.8
    };
    fullTable(config);
    //不良原因
    request({url:"/Reason/GetAllReaInfo",data:{"ExecType":"00"}},function (Body) {
        var $reason_list = $('#ReaDes_list');
        var listStr = null;
        for(var i in Body.Data){
            listStr += "<option value='"+ Body.Data[i].ReaDes+"' />"
        }
        $reason_list.append(listStr);
    })
    //输入批次号，点击查询
    var $batchNum = $("#InCCode");
    var findBatch = function () {
        request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch":$batchNum.val()})}},function (Body) {
            var $batchNameTds = $("#list4 .Batch");//获取所有的批次号对应的td对象
            if($batchNameTds.length > 0){
                $batchNameTds.each(function () {//遍历td得到里面的值
                    if($(this).text() == $batchNum.val()){//如果输入的批次号和表格里面的批次号相等，则不添加
                        showWarn("该批次已经存在表格中");
                        return false;
                    }else{//不存在添加
                        var table = {
                            Batch:$batchNum.val(),
                            MaName:Body.Data.MaName,
                            Num:Body.Data.Num,
                            UnitName:''
                        };
                        addSrow("list4",table);
                    }
                });
            }
            else{
                var table = {
                    Batch:$batchNum.val(),
                    MaName:Body.Data.MaName,
                    Num:Body.Data.Num,
                    UnitName:''
                };
                addSrow("list4",table);
            }
        })
    }
    $("#find").on("click",function(){
        findBatch();
    });
    $batchNum.on("keyup",function (e) {
        if(e.keyCode == "13")
            findBatch();
    })
    //标记不良
    $("#save").on("click",function(){
        //获取表格数据
        var data = getRowData("list4");
        var tables = [];
        for(var i in data){
            var table = {
                "Batch": data[i].Batch
            };
            tables.push(table);
        }
        var busData = {
            "ReaDes": $("#ReaDes").val(),
            "BatchInfo": tables
        };
        request({url:"/Batch/SaveBOptInfo",data:{"ExecType":"03","BusData":JSON.stringify(busData)}},function (Body) {
            showMsg(Body.MsgDes);
        })
    });
    //删除
    $(".del1").on("click",function(){
        derow("list4");
    })

});