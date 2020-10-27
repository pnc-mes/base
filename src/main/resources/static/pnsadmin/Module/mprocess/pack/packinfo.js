/**
 * Created by xfxi on 2017/7/7.
 */

$(function(){

    //表格定义
    var colNamesArr=[
        { "Caption":"数量", "Name":"Num", "CType":"text", "Hidden": true},
        { "Caption":"条码", "Name":"Batch", "CType":"text"},
        { "Caption":"扫描时间", "Name":"ScanTime", "CType":"text"}
    ];

    var config={
        tableId: "list4",
        data: [],
        colArr:colNamesArr,
        multiselect:true,
        width:0.996,
        height:0.43
    };
    fullTable(config);

    //加载打印机
    var params_print = {
        "displaymode": "0",
        "title": "选择打印机",
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
        "event":{
            "onseardata":function (o) {
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
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Printer/GetAllPrInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PrRd": datas[i].PrRd,
                            "PrName": datas[i].PrName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#Print").zc_select(params_print);

    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#batch").is(":focus")){

                var flag = true;
                var data = getRowData("list4");
                if(data != undefined && data.length > 0){
                    data.forEach(function (obj) {
                        if($("#batch").val().trim() == obj.Batch){
                            flag = false;
                            toastr.warning("批次已经存在");
                            return;
                        }
                    });
                }

                if(flag) {
                    var busData = {
                        Batch: $("#batch").val().trim(),
                        WoRd: $("#woRd").val(),
                        ReNum: Number($("#reNum").val()) - Number($("#num").val()),
                        ScanTime: new Date().toLocaleDateString()
                    };

                    getVBar(JSON.stringify(busData));
                }
            }
        }
    });

    //打印
    $("#save").click(function(){

        var reNum = Number($("#reNum").val());

        var num = Number($("#num").val());

        if(num == reNum){
            savePack();
        }else if(num > reNum){
            toastr.warning("数量已经超出,请删减批次数量");
        }else if(num < reNum){
            layer.confirm('数量不够,确认要打印吗？', {
                btn: ['确认', '取消'], //按钮
            },function () {
                savePack();
                layer.closeAll("dialog");
            });
        }
    });

    //验证条码信息
    function getVBar(busData){

        request({url:"/Pack/VBarInfo",data: {"ExecType": "00", "BusData": busData}},function(Body){

            $("#woCode").val(Body.Data.WoCode);
            $("#woRd").val(Body.Data.WoRd);
            $("#maName").val(Body.Data.MaName);
            $("#maVerRd").val(Body.Data.MaVerRd);

            var bar = {
                "Batch": Body.Data.Batch,
                "ScanTime": Body.Data.ScanTime,
                "Num": Body.Data.BNum
            };

            $("#num").val(Number($("#num").val()) + Number(Body.Data.BNum));


            //打印模板
            $("#ptRd").val("");
            $("#ptName").val("");
            $("#ptRd").val(Body.Data.BoxPackInfo.PTRd);
            $("#ptName").val(Body.Data.BoxPackInfo.PTName);

            //包装数量
            $("#reNum").empty();
            $("#reNum").val(Body.Data.BoxPackInfo.Num);

            //重量范围
            $("#weight").empty();
            var uplimit = Number(Body.Data.BoxPackInfo.Weight + Body.Data.BoxPackInfo.UpLimit);
            var downlimit = Number(Body.Data.BoxPackInfo.Weight - Body.Data.BoxPackInfo.DownLimit);
            $("#weight").val(downlimit + "-" + uplimit);

            //单位
            $("#unitName").empty().text(Body.Data.BoxPackInfo.UnitName);

            addSrow("list4", bar, 0, true);

            if(Number($("#num").val()) == Number($("#reNum").val())){
                savePack();
            }
        });
    }

    //保存包装信息
    function savePack(){

        var busData = checkData();

        if(busData != undefined && busData != null){
            request({url:"/Pack/SavePackInfo",data: {"ExecType": "00", "BusData": JSON.stringify(busData)}},function(Body){
                toastr.success(Body.MsgDes);
            });
        }

        console.log(busData);
    }

    //验证数据准确性
    function checkData(){

        var WoCode = $("#woCode").val();
        if(WoCode == undefined || WoCode == ""){
            toastr.warning("工单号不能为空");
            return;
        }

        var MaVerRd = $("#maVerRd").val();
        if(MaVerRd == undefined || MaVerRd == ""){
            toastr.warning("产品不能为空");
            return;
        }

        var Num = Number($("#num").val());
        if(Num == undefined || Num <= 0){
            toastr.warning("批次数量不能小于等于零");
            return;
        }

        var PTRd = $("#ptRd").val();
        if(PTRd == undefined || PTRd <= 0){
            toastr.warning("打印模板不存在");
            return;
        }

        var PrintRd = $("#Print").getseldata().PrRd;
        if(PrintRd == undefined || PrintRd <= 0){
            toastr.warning("打印机不能为空");
            return;
        }

        var PrintCount = $("#printCount").val();

        var PrintCopy = $("#printCopy").val();

        var BarInfo = getRowData("list4");
        if(BarInfo == undefined || BarInfo.length <= 0){
            toastr.warning("批次未扫描");
            return;
        }

        BarInfo.forEach(function(obj){
           delete obj.Num;
        });

        var busData = {
            "WoCode": WoCode,
            "MaVerRd": MaVerRd,
            "Num": Num,
            "PTRd": PTRd,
            "IsPrint": "00",
            "PrintRd": PrintRd,
            "PrintCount": PrintCount,
            "PrintCopy": PrintCopy,
            "BarInfo": BarInfo
        };

        return busData;
    }

    //删除事件
    $(".del1").click(function(){
        var data = getSeRowData("list4");
        var num = 0, allnum = Number($("#num").val());
        if(data != undefined && data.length > 0){
            for(var i=0; i<data.length; i++){
                num += Number(data[i].Num);
                console.log(1);
            }
        }
        $("#num").val(allnum - num);
        derow("list4");
    });

    //模板改变事件
    $("#PrintT").change(function(){
        var $this = $(this);
        var reNum = 0;
        $(".bbb").each(function(){
            if($this.val() == $(this).val()){
                reNum = $(this).attr("num");
                return;
            }
        });
        numChange(reNum);
        var num = Number($("#num").val());
        if(num == reNum){
            toastr.warning("数量足够，可以打印");
        }else if(num > reNum){
            toastr.warning("数量超出,请删减批次数量");
        }
    });

    //数量改变方法
    function numChange(num){

        $("#reNum").val(num);

    }

});