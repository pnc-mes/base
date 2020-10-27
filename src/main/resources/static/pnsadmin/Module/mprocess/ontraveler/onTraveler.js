/**
 * Created by test on 2017/6/27.
 */
$(function(){

    //表格定义
    var colNamesArr6=[
        { "Caption":"线体名称", "Name":"LineName", "CType":"text"},
        { "Caption":"工序", "Name":"SpecName", "CType":"text"},
        { "Caption":"事务操作", "Name":"OptType", "CType":"text"},
        {  "Caption":"操作时间", "Name":"OptTime", "CType":"text"},
        {  "Caption":"操作人", "Name":"Optor", "CType":"text"}
    ];
    var config6 = {
        tableId: "list6",
        data: [],
        colArr:colNamesArr6,
        width:0.7,
        height:0.77
    };
    fullTable(config6);

    //批次用料表格
    var colNamesArr7=[
        { "Caption":"线体名称", "Name":"LineName", "CType":"text"},
        { "Caption":"工序", "Name":"SpecName", "CType":"text"},
        { "Caption":"设备", "Name":"DevName", "CType":"text"},
        { "Caption":"消耗方式", "Name":"ConSMode", "CType":"text"},
        {  "Caption":"批次", "Name":"Batch", "CType":"text"},
        {  "Caption":"供应商(炉号,序号)", "Name":"SuppBatch", "CType":"text"},
        {  "Caption":"消耗物料", "Name":"MaName", "CType":"text"},
        {  "Caption":"数量", "Name":"DoNum", "CType":"text"},
        {  "Caption":"单位", "Name":"UnitName", "CType":"text"},
        {  "Caption":"操作人", "Name":"Optor", "CType":"text"}
    ];
    var config7 = {
        tableId: "list7",
        data: [],
        colArr:colNamesArr7,
        width:0.7,
        height:0.77
    };
    fullTable(config7);

    //批次数据采集表格
    var colNamesArr8=[
        { "Caption":"线体名称", "Name":"LineName", "CType":"text"},
        { "Caption":"工序", "Name":"SpecName", "CType":"text"},
        { "Caption":"采集设备", "Name":"DevName", "CType":"text"},
        {  "Caption":"采集数据", "Name":"DCContent", "CType":"text"},
        {  "Caption":"操作人", "Name":"Optor", "CType":"text"}
    ];
    var config8 = {
        tableId: "list8",
        data: [],
        colArr:colNamesArr8,
        width:0.7,
        height:0.77
    };
    fullTable(config8);

    //批次数据采集表格
    var colNamesArr11=[
        { "Caption":"工序", "Name":"SpecName", "CType":"text"},
        { "Caption":"采集设备", "Name":"DevName", "CType":"text"},
        {  "Caption":"不良原因", "Name":"RCContent", "CType":"text"},
        {  "Caption":"操作人", "Name":"Optor", "CType":"text"}
    ];

    var config11 = {
        tableId: "list11",
        data: [],
        colArr:colNamesArr11,
        width:0.7,
        height:0.77
    };
    fullTable(config11);

    var colNamesArr12=[
        { "Caption":"线体名称", "Name":"LineName", "CType":"text"},
        { "Caption":"工序", "Name":"SpecName", "CType":"text"},
        { "Caption":"原因", "Name":"Msg", "CType":"text"},
        {  "Caption":"操作时间", "Name":"OptTime", "CType":"text"},
        {  "Caption":"操作人", "Name":"Execor", "CType":"text"}
    ];

    var config12 = {
        tableId: "list12",
        data: [],
        colArr:colNamesArr12,
        width:0.7,
        height:0.77
    };
    fullTable(config12);
    var colNamesArr13=[
        { "Caption":"工位信息", "Name":"StationName", "CType":"text"},
        { "Caption":"等级", "Name":"GradeName", "CType":"text"},
        { "Caption":"不良代码", "Name":"BadCode", "CType":"text"},
        { "Caption":"不良名称", "Name":"BadName", "CType":"text"},
        {  "Caption":"操作时间", "Name":"CreateTime", "CType":"text"},
        {  "Caption":"操作人", "Name":"Creator", "CType":"text"}
    ];

    var config13 = {
        tableId: "list13",
        data: [],
        colArr:colNamesArr13,
        width:0.7,
        height:0.77
    };
    fullTable(config13);

    //拆批后批次
    var colNamesArr9=[
        { "Caption":"拆分后批次", "Name":"Batch", "CType":"text"}
    ];
    var config9 = {
        tableId: "list9",
        data: [],
        colArr:colNamesArr9,
        width: 0.24,/*
        height:0.58*/
    };
    fullTable(config9);



    //加载右边数据
    function loadData(busData){

        request({url:"/Report/GetOTInfo",data: busData},function(Body){

            if (Body.MsgCode == "0x00000") {

               /* $("#q3").val(Body.Data.Num);*/

                //事物操作表格
                var config6 = {
                    tableId: "list6",
                    data: Body.Data.AffairInfo,
                    colArr:colNamesArr6,
                    width:0.7,
                    height:0.77
                };
                fullTable(config6);

                //批次用料表格
                var config7 = {
                    tableId: "list7",
                    data: Body.Data.DoMaInfo,
                    colArr:colNamesArr7,
                    width:0.7,
                    height:0.77
                };
                fullTable(config7);

                //批次数据采集表格
                var config8 = {
                    tableId: "list8",
                    data: Body.Data.DoDCInfo,
                    colArr:colNamesArr8,
                    width:0.7,
                    height:0.77
                };
                fullTable(config8);

                //批次数据采集表格
                var config11 = {
                    tableId: "list11",
                    data: Body.Data.DoRCInfo,
                    colArr:colNamesArr11,
                    width:0.7,
                    height:0.77
                };
                fullTable(config11);


                var config12 = {
                    tableId: "list12",
                    data: Body.Data.DoExcpInfo,
                    colArr:colNamesArr12,
                    width:0.7,
                    height:0.77
                };
                fullTable(config12);

                var config13 = {
                    tableId: "list13",
                    data: Body.Data.EndData,
                    colArr:colNamesArr13,
                    width:0.7,
                    height:0.77
                };
                fullTable(config13);


                $("#batch1").val(Body.Data.BaseInfo.Batch);
                $("#LineName").val(Body.Data.BaseInfo.LineName);
                $("#materCode").val(Body.Data.BaseInfo.MaCode);
                $("#materName").val(Body.Data.BaseInfo.MaName);
                var str="";
                if(Body.Data.BaseInfo.MaterialType=="00"){
                    str= "产成品";
                }
                else if(Body.Data.BaseInfo.MaterialType=="01"){
                    str= "半成品";
                }
                else if(Body.Data.BaseInfo.MaterialType=="02"){
                    str= "原料";
                }
                else {
                     str= "其他";
                }
                $("#materType").val(str);

                $("#num").val(Body.Data.BaseInfo.CanNum);
                $("#wordCard").val(Body.Data.BaseInfo.WoCode);
                $("#workFlow").val(Body.Data.BaseInfo.WFName);
                $("#bomInfo").val(Body.Data.BaseInfo.BOMName);
                var stat="";
                if(Body.Data.BaseInfo.Status=="00"){
                    stat= "待处理";
                }
                if(Body.Data.BaseInfo.Status=="01"){
                    stat= "处理中";
                }
                if(Body.Data.BaseInfo.Status=="02"){
                    stat= "冻结";
                }
                if(Body.Data.BaseInfo.Status=="03"){
                    stat= "报废";
                }
                if(Body.Data.BaseInfo.Status=="04"){
                    stat= "完工";
                }
                $("#batchStatus").val(stat);
                var package="";
                if(Body.Data.BaseInfo.IsPack=="00"){
                    package= "是";
                }
                if(Body.Data.BaseInfo.IsPack=="01"){
                    package= "否";
                }

                $("#package").val(package);
            }
        });
    }

    //获取批次拆分、合并信息
    function getSUBInfo(batch,objData){

        var _data = {
            Batch: batch
        };

        request({url:"/Batch/GetSUBInfo",data: {"ExecType": "00", "BusData": JSON.stringify(_data)}},function(Body){

            //拆分后批次批次信息
            var config9 = {
                tableId: "list9",
                data: Body.Data.RefBatch,
                colArr:colNamesArr9,
                width: 0.24,/*
                height:0.58*/
            };

            fullTable(config9);
            loadData(objData);

        });
    }


    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#batch").is(":focus")){
                var _batch = $("#batch").val().trim();
                if(_batch != undefined && _batch != ""){
                    var batch={
                        "Batch":_batch
                    };
                    var objData = {
                        "ExecType": "00",
                        "BusData":JSON.stringify(batch)
                    };

                    getSUBInfo(_batch,objData);
                }else {
                    toastr.warning("请输入批次再查询!");
                }
            }
        }
    });

    $(".ocheck").click(function(){
        var _batch=$("#batch").val().trim();
        if(_batch == null||_batch==""){
            toastr.warning("请输入批次再查询!");
            return false;
        }else {
            var batch={
                "Batch":_batch
            };
            var objData = {
                "ExecType": "00",
                "BusData":JSON.stringify(batch)
            };

            getSUBInfo(_batch,objData);
        }
    });
});
