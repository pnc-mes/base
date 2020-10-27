/**
 * Created by xfxi on 2017/9/27.
 */
$(function(){
    //表格定义
    var colNamesArr=[
        { "Caption":"批次", "Name":"Batch", "CType":"text"},
        { "Caption":"当前工序", "Name":"SpecVerName", "CType":"text"},
        { "Caption":"目标工序", "Name":"SpecVerNameT", "CType":"text"}
    ];

    var config={
        tableId: "list1",
        data: [],
        colArr:colNamesArr,
        multiselect:true,
        width:0.975,
        height:0.21
    };
    fullTable(config);

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

    //保存
    $("#save_").click(function(){
        var data = getTableData("list1");
        if(data == undefined || data.length <= 0){
            toastr.warning("移动批次列表不能为空");
            return;
        }

        saveIOS(JSON.stringify(data));
    });

    //删除
    $("#del_").click(function(){
        derow("list1");
    });

    //获取批次关联信息
    function getCMB(batch){
        //阻止重复
        var f_data = getTableData("list1");
        var flag = true;
        if(f_data != undefined && f_data.length > 0){
            f_data.forEach(function(obj){
                if(batch == obj.Batch){
                    flag = false;
                    toastr.warning("该批次已经存在");
                    return;
                }
            });
        }

        if(flag){
            request({url:"/Commom/GetCMBInfo",data: {"ExecType": "00", "BusData": "{\"Batch\":\"" + batch + "\"}"}},function(Body){
                reClear();

                var data = Body.Data;

                if(data.WoCode == undefined || data.WoCode == ""){
                    toastr.warning("该批次不是产成品");
                    return;
                }

                $("#woCode").val(data.WoCode);
                $("#procedureCode").val(data.MaCode);
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
                //目标工序
                var specinfo = data.WFInfo.SpecInfo;
                if(specinfo == undefined || specinfo.length <= 0){
                    toastr.warning("该批次工艺流程工序为空");
                    return;
                }
                var specVerT = "<select>";
                specinfo.forEach(function(obj){
                    specVerT += "<option value='" + obj.SpecVerRd + "'>" + obj.SpecName + "</option>"
                });
                specVerT += "</select>";

                var obj = {
                    Batch: batch,
                    SpecVerName: cspecinfo.SpecName,
                    SpecVerNameT: specVerT
                };
                addSrow("list1", obj, "first");
            });
        }
    }

    //非标准移动
    function saveIOS(data){
        request({url:"/SpecOpert/GetIOSInfo",data: {"ExecType": "04", "BusData": data}},function(Body) {
            toastr.success(Body.MsgDes);
            deallrow("list1");
        });
    }

    //清空
    function reClear(){
        $("#woCode").val("");
        $("#procedureCode").val("");
        $("#procedureName").val("");
        $("#workflow").val("");
    }

    //获取表格信息
    function getTableData(id){
        var data = [];
        var rows = $("#" + id + " tr");
        if(rows != undefined || rows.length > 1){
            for(var i=1; i<rows.length; i++){
                var obj = {
                    Batch: rows.eq(i).find('td').eq(1).text(),
                    TarScVRd: rows.eq(i).find('td').eq(3).find('select').eq(0).val()
                };
                data.push(obj);
            }
        }
        return data;
    }
});
