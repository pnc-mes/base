/**
 * Created by xfxi on 2017/7/11.
 */
$(function(){

    //表格定义
    var colNamesArr = [
        { "Caption":"批次号", "Name":"Batch", "CType":"text",Width:100},
        { "Caption":"产品/物料", "Name":"MaName", "CType":"text"},
        { "Caption":"数量", "Name":"Num", "CType":"text",Width:80},
        { "Caption":"单位", "Name":"UnitName", "CType":"text",Width:50}
    ];

    var config = {
        tableId: "list4",
        data: [],
        colArr:colNamesArr,
        multiselect:true,
        width:0.97,
        height:0.6
    };

    fullTable(config);

    //加载打印模板
    var params = {
        "displaymode": "0",
        "title": "打印模板",
        "binddata": {
            "keyfield": "PtRd",
            "fields": [
                {
                    "caption": "打印模板id",
                    "name": "PtRd"
                }, {
                    "caption": "打印模板名称",
                    "name": "PtName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {

                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"TempName",
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
                request({url:"/PrintT/GetAllPtInfo",data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},function(Body){

                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PtRd": datas[i].PtRd,
                            "PtName": datas[i].PtName,
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

    //加载打印机
    var params1 = {
        "displaymode": "0",
        "title": "打印机",
        "binddata": {
            "keyfield": "PrRd",
            "fields": [
                {
                    "caption": "打印机id",
                    "name": "PrRd"
                }, {
                    "caption": "打印机名称",
                    "name": "PrName"
                }
            ]},
        "showresult": { "ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"PrinterName",
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
                request({url:"/Printer/GetAllPrInfo",data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},function(Body){
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PrRd": datas[i].PrRd,
                            "PrName": datas[i].PrName,
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
    $("#defaultSelect1").zc_select(params1);

    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#batch").is(":focus")){
                var batch = $("#batch").val().trim();
                if(batch != undefined && batch != ""){
                    getSUBInfo(batch);
                }
            }
        }
    });

    //删除按钮点击事件
    $(".cDel").click(function(){
        derow("list4");
    });

    //确认按钮点击事件
    $(".cbb").click(function(){
        var batch = $("#batch").val().trim();
        if(batch != undefined &&　batch != ""){
            var m_tableData = getRowData("list4");
            if(m_tableData != undefined &&　m_tableData.length >0){
                //获取打印机、打印模板
                var printTRd =$("#defaultSelect").getseldata().PtRd;

                if(printTRd == ""){
                    toastr.warning("打印模板不能为空");
                    return;
                }

                var printRd = $("#defaultSelect1").getseldata().PrRd;

                if(printRd == ""){
                    toastr.warning("打印机不能为空");
                    return;
                }
                //是否打印
                var isPrint = "01";
                if($("#isPrint").is(":checked")){
                    isPrint = "00";
                }

                m_tableData.forEach(function(obj){
                    delete obj.MaName;
                    delete obj.Num;
                    delete obj.UnitName;
                });

                var busData = {
                    BatchInfo: m_tableData,
                    PrintTRd: printTRd,
                    PrintRd: printRd,
                    IsPrint: isPrint
                };

                saveSUBInfo(JSON.stringify(busData));
            }else{
                toastr.warning("请完善拆批信息");
            }
        }else{
            toastr.warning("批次不能为空");
        }
    });

    //获取批次拆分、合并信息
    function getSUBInfo(batch){
        var _data = {
            Batch: batch
        };

        request({url:"/Batch/GetSUBInfo",data: {"ExecType": "00", "BusData": JSON.stringify(_data)}},function(Body){
            //打印机、打印模板(选中)
            if(Body.Data.PrintTRd != undefined ){
                $("#defaultSelect").showData({
                    id:Body.Data.PrintTRd,
                    name:Body.Data.PtName,
                    keyfield:"PtRd",
                    fields:[
                        {"name":"PtRd"},
                        {"name":"PtName"}
                    ]
                });
            }
            if(Body.Data.PrintRd != undefined ){
                $("#defaultSelect1").showData({
                    id:Body.Data.PrintRd,
                    name:Body.Data.PrName,
                    keyfield:"PrRd",
                    fields:[
                        {"name":"PrRd"},
                        {"name":"PrName"}
                    ]
                });
            }
            /*$("#MaVerRd").val(Body.Data.MaVerRd);
            $("#MaCode").val(Body.Data.MaCode);
            $("#MaName").val(Body.Data.MaName);
            $("#Num").val(Body.Data.Num);
            $("#UnitName").val(Body.Data.UnitName);*/
            var flag = true;
            var m_tableData = getRowData("list4");
            if(m_tableData != undefined &&　m_tableData.length >0){
                for(var i=0; i<m_tableData.length; i++){
                    if(m_tableData[i].Batch == batch){
                        toastr.warning("请勿重复添加批次");
                        flag = false;
                        break;
                    }
                }
            }

            if(flag){
                var data = {
                    Batch: batch,
                    MaName: Body.Data.MaName,
                    Num: Body.Data.Num,
                    UnitName: Body.Data.UnitName
                };
                addSrow("list4", data, "first");
            }
        });
    }

    //保存拆分、合并批次
    function saveSUBInfo(busData){

        request({url:"/Batch/SaveSUBInfo",data: {"ExecType": "01", "BusData": busData}},function(Body){

            toastr.success(Body.MsgDes);
        });
    }
});