/**
 * Created by PNC on 2017/6/27.
 */
$(function(){
// 2.处理表格
    var colNamesArr = [
        {"Caption": "物料Rd", "Name": "MaVerRd", 'Hidden': true,Width:120},
        {"Caption": "批次号", "Name": "Batch", 'Editable': false,Width:120},
        {"Caption": "物料代码", "Name": "MaCode", 'Editable': false,Width:150},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false,Width:200},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false,Width:260},
        {"Caption": "数量", "Name": "Num", "Editable": false,Width:80},
        {"Caption": "单位", "Name": "UnitName", "Editable": false,Width:50},
        {"Caption": "创建时间", "Name": "CreateDate", "Editable": false}
    ];
    var config1 = {
        tableId: 'list4',
        data:[],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.995,
        height:0.85
    };
    fullTable(config1);
    //今天的时间
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();

    //明天的时间
    var day3 = new Date();
    day3.setTime(day3.getTime()+24*60*60*1000);
    var s3 = day3.getFullYear()+"-" + (day3.getMonth()+1) + "-" + day3.getDate();

    /*********************************************请求批次列表信息*********************************************/
    var  datasources={};
       datasources={
        "MaCode":"",
        "MaName":"",
        "CreateDate":s2,
        "CreateDate1":s3,
        "InStockStatus":"02"
    }

    request({
        id:"list4",
        url: "/Batch/GetAllBatchInfo",
        async:true,
        data: {"ExecType": "00", "BusData": JSON.stringify(datasources)}
    }, function (Body) {
        var config1 = {
            tableId: 'list4',
            data: Body.Data,
            colArr: colNamesArr,
            multiselect: true,
            width: 0.995,
            height:0.85
        };
        fullTable(config1);
    });



    var params = [{
        "caption": "物料代码",
        "name": "MaterialCode",
        "valtype": "00"
    }, {
        "caption": "物料名称",
        "name": "MaterialName",
        "valtype": "00"
    }, {
        "caption": "时间段",
        "name": "CreateTime",
        "valtype": "02"
    },{
        "caption": "是否在库",
        "name": "InStockStatus",
        "valtype": "01",
        "data": "\"02\":\"未在库\"|\"00\":\"在库\""
    }];


    var event = {
        onsure: function (result) {
            var data=result[2].CreateTime.split("|");
            var data1=data[0];
            var data2=data[1];
            datasources={
                    "MaCode":result[0].MaterialCode,
                    "MaName":result[1].MaterialName,
                    "CreateDate":data1,
                    "CreateDate1":data2 == "" ? "" : (data1 == data2 ? data2 + " 23:59:59" : data2),
                    "InStockStatus":result[3].InStockStatus
                }
            $("#list4").html("");
            request({
                id:"list4",
                url: "/Batch/GetAllBatchInfo",
                async:true,
                data: {"ExecType": "00", "BusData": JSON.stringify(datasources)}
            }, function (Body) {
                var config1 = {
                    tableId: 'list4',
                    data: Body.Data,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.995,
                    height:0.85
                };
                fullTable(config1);
            });
        }
    };

    //筛选
    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);

    //导出
    $("#export").on('click', function () {
        layer.confirm('确认要导出该批次查询信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            var url = getBasePath() + "/Batch/ExportBatchInfo";
            var data_ = "ExecType=00&BusData="+JSON.stringify(datasources);
            var xhr = new XMLHttpRequest();
            xhr.open('POST', url, true);
            xhr.responseType = 'arraybuffer';
            xhr.onload = function () {
                if (this.status === 200) {
                    var filename = "";
                    var disposition = xhr.getResponseHeader('Content-Disposition');
                    if (disposition && disposition.indexOf('attachment') !== -1) {
                        var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                        var matches = filenameRegex.exec(disposition);
                        if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');

                        var type = xhr.getResponseHeader('Content-Type');

                        var date = new Date();

                        filename = "批次查询表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

                        var blob = new Blob([this.response], {type: type});
                        if (typeof window.navigator.msSaveBlob !== 'undefined') {
                            window.navigator.msSaveBlob(blob, filename);
                        } else {
                            var URL = window.URL || window.webkitURL;
                            var downloadUrl = URL.createObjectURL(blob);

                            if (filename) {

                                var a = document.createElement("a");

                                if (typeof a.download === 'undefined') {
                                    window.location = downloadUrl;
                                } else {
                                    a.href = downloadUrl;
                                    a.download = filename;
                                    document.body.appendChild(a);
                                    a.click();
                                }
                            } else {
                                window.location = downloadUrl;
                            }

                            setTimeout(function () {
                                URL.revokeObjectURL(downloadUrl);
                            }, 100); // cleanup
                        }
                    } else {
                        var un = new Uint8Array(this.response);
                        var s = "";
                        for (var i = 0; i < un.length; i++) {
                            s += String.fromCharCode(un[i]);
                        }
                        var json = JSON.parse(s);
                        if (json.Body != undefined) {
                            toastr.warning(json.Body.MsgDes);
                        }
                    }
                }
            };
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xhr.send(data_);
        });
    });

    //加载线体
    var xt = {
        "displaymode": "0",
        "title": "line",
        "binddata": {
            "keyfield": "LineRd",
            "fields": [
                {
                    "caption": "LineRd",
                    "name": "LineRd"
                }, {
                    "caption": "线体名称",
                    "name": "LineName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"LineName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "LineName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Line/GetAllLineInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "LineRd": datas[i].LineRd,
                            "LineName": datas[i].LineName,
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
    $("#xt").zc_select(xt);
    //往后道往前道
    var Direction="";

    //往后道
    $("#up").click(function(){
        if($(this).is(":checked")){
            Direction="00";
        }
    });
    //往前道
    $("#down").click(function(){
        if($(this).is(":checked")){
            Direction="01";
        }
    });
    var newBatchData=[];
    //异常调站
    $("#error").click(function(){

        $("#xt").selectpicker('val',"");
        $("#mbgx").html("");
        $("#wf").html("");

        $("#up").prop("checked",false);
        $("#down").prop("checked",false);
        $("#in").prop("checked",false);
        $("#out").prop("checked",false);
        $("#textarea").val("");


        $("#myModal1").modal("show")

        var list4Data=getSeRowData("list4");
        if(list4Data.length<=0){
            toastr.warning("请至少选中一项");
            return false;
        }


        var Batch=[];
        var s1 = new Set();
        for(var i  in  list4Data){
            s1.add(list4Data[i].MaVerRd);
            var Batchs={
                "Batch":list4Data[i].Batch
            }
            Batch.push(Batchs);
        }
        if(s1.size==1){
            $("#wfdata").hide();
            s1.forEach(function (element, sameElement, set) {
                    //加载工艺流程
                request({url:"/Commom/GetCMWFInfo", data: {"ExecType": "00", "busData": JSON.stringify({MVerRd: sameElement})}},function(Body2){
                        var data = Body2.Data.WFInfo;
                        if(data.length>0){
                            for (var i in data) {
                                //map.set(data[i].WFVerRd,data[i].WFName)
                                var str="";
                                if(data[i].SpecInfo.length>0){
                                    for(var j in data[i].SpecInfo){
                                        str += '<option value=' + data[i].SpecInfo[j].SpecVerRd + '>' + data[i].SpecInfo[j].SpecName + '</option>';
                                    }
                                }
                            }
                            $("#mbgx").empty().html(str);
                            $('#mbgx').selectpicker('refresh');
                            $("#mbgx").selectpicker({
                                width:198
                            });
                        }
                    });

            });
        }else {
            var map=new Map();
            for(var i  in  list4Data){
                //加载工艺流程
                request({url:"/Commom/GetCMWFInfo", data: {"ExecType": "00", "busData": JSON.stringify({MVerRd: list4Data[i].MaVerRd})}},function(Body2){
                    var data = Body2.Data.WFInfo;
                    if(data.length>0){
                        for (var i in data) {
                            map.set(data[i].WFVerRd,data[i].WFName)
                            var str="";
                            if(data[i].SpecInfo.length>0){
                                for(var j in data[i].SpecInfo){
                                    str += '<option value=' + data[i].SpecInfo[j].SpecVerRd + '>' + data[i].SpecInfo[j].SpecName + '</option>';
                                }
                            }
                        }

                        $("#mbgx").empty().html(str);
                        $('#mbgx').selectpicker('refresh');
                        $("#mbgx").selectpicker({
                            width:198
                        });


                    }

                });
            }
            var aa="";
            for(var key in map){
                aa += '<option value=' + key + '>' + map[key] + '</option>';
            }
            $("#wf").empty().html(aa);
            $('#wf').selectpicker('refresh');
            $("#wf").selectpicker({
                width:198
            });
            $("#wfdata").show()
        }

        newBatchData=Batch.concat([])

    });

    //弹出框的确认设置
    $("#confirm").click(function(){
        var LineRd=$("#xt").getseldata().LineRd;

        var SpecVerRd = $("#mbgx").val();
        //目标工序行为
        var ActionType="";
        if($("#in").prop("checked")){
            ActionType="00";
        }else  if($("#out").prop("checked")){
            ActionType="01";
        }else {
            ActionType="02";
        }
        if(newBatchData.length<0){
            toastr.warning("批次不能为空");
            return false;
        }
        if(LineRd==null||LineRd==""){
            toastr.warning("线体不能为空");
            return false;
        }
        if(SpecVerRd[0]==null||SpecVerRd[0]==""){
            toastr.warning("工序不能为空");
            return false;
        }


        if(Direction==null){
            toastr.warning("调站方向");
            return false;
        }
        var Remark=$("#textarea").val().trim();
        var busData={
            "BInfo":newBatchData,
            "LineRd":LineRd,
            "Direction":Direction,
            "SpecVerRd":SpecVerRd[0],
            "ActionType":ActionType,
            "Remark":Remark
        }

        request({url:"/SpecOpert/GetIOSInfo",data: {"ExecType": "08", "BusData": JSON.stringify(busData)}},function(Body) {
            $("#myModal1").modal("hide");
            toastr.success(Body.MsgDes)

        });
       // alert(JSON.stringify(newBatchData))
    });
    $("#show").change(function(){
        var text=$(this).find("option:selected").text();
        if(text=="其他说明"){
            $("#textarea").prop("disabled",false);
        }
        if(text=="产线异常快速跳站"){
            $("#textarea").prop("disabled",true);
        }
        if(text=="产品重用"){
            $("#textarea").prop("disabled",true);
        }

    });
});