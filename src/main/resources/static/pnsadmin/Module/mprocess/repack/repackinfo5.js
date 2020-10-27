$(function () {
    var addid=0;
    var totalnum=0;
    //表格定义
    var colNamesArr = [
        {"Caption": "箱的guid", "Name": "packGd", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "箱号", "Name": "packCode", "CType": "text", Width: 100},
        {"Caption": "物料代码", "Name": "maCode", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "物料名称", "Name": "maName", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "模板地址", "Name": "fileName", "CType": "text", Width: 100, Hidden: true},
        {"Caption": "功率", "Name": "powerGear", "CType": "text", Width: 100},
        {"Caption": "电流", "Name": "elgear", "CType": "text"},
        {"Caption": "颜色", "Name": "colorGear", "CType": "text", Width: 50},
        {"Caption": "等级", "Name": "gradeName", "CType": "text", Width: 50},
        {"Caption": "包装数量", "Name": "num", "CType": "text", Width: 100},
        {"Caption": "打包时间", "Name": "createTime", "CType": "text", Width: 60},
        {"Caption": "打包人", "Name": "creator", "CType": "text", Width: 60,},
    ];
    var config = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        width: 0.95,
        height: 0.8
    };
    fullTable(config);

    //表格定义
    var colNamesArr5 = [
        {"Caption": "组件", "Name": "packCode", "CType": "text", Width: 100},
        {"Caption": "功率", "Name": "powerGear", "CType": "text", Width: 100},
        {"Caption": "电流", "Name": "elgear", "CType": "text"},
        {"Caption": "颜色", "Name": "colorGear", "CType": "text", Width: 50},
        {"Caption": "等级", "Name": "gradeName", "CType": "text"},
    ];
    var config5s = {
        tableId: "list5",
        data: [],
        colArr: colNamesArr5,
        width: 0.95,
        height: 0.8
    };
    fullTable(config5s);
    function add0(m){return m<10?'0'+m:m }
    function format(shijianchuo)
    {
//shijianchuo是整数，否则要parseInt转换
        var time = new Date(shijianchuo);
        var y = time.getFullYear();
        var m = time.getMonth()+1;
        var d = time.getDate();
        var h = time.getHours()+1;
        var mm = time.getMinutes()+1;
        var s = time.getSeconds()+1;
        return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
    }


    var colNamesArr5s = [
        {"Caption": "箱号", "Name": "barCode", "CType": "text", Width: 100},
        {"Caption": "冻结原因", "Name": "operateText", "CType": "text", Width: 100},
        {"Caption": "冻结人", "Name": "creator", "CType": "text"},
        {"Caption": "冻结时间", "Name": "createTime", "CType": "text", Width: 50}

    ];
    var config5 = {
        tableId: "list5",
        data: [],
        colArr: colNamesArr5s,
        multiselect: true,
        width: 0.95,
        height: 0.8
    };
    fullTable(config5);


    $("#select").click(function(){
        var barCode=$("#selectBarCode").val().trim();
        if(barCode!=""){
            var data={};
            data.barCode=barCode;
            $.get(getBasePath()+"/Disassoc/GetOperateLogInfoByOperateTypeAndbarCode",data,function (result) {
                var datas=[];
                if(result.barCode!=undefined){
                    datas.push({
                        "barCode":result.barCode,
                        "operateText":result.operateText,
                        "creator":result.creator,
                        "createTime":format((result.createTime)),
                    })
                    var config5 = {
                        tableId: "list5",
                        data: datas,
                        colArr: colNamesArr5s,
                        multiselect: true,
                        width: 0.95,
                        height: 0.8
                    };
                    fullTable(config5);
                }else{
                    var config5 = {
                        tableId: "list5",
                        data: [],
                        colArr: colNamesArr5s,
                        multiselect: true,
                        width: 0.95,
                        height: 0.8
                    };
                    fullTable(config5);
                }
            });
        }else {
            $.get(getBasePath()+"/Disassoc/GetAllOperateLogInfoByOperateType",function (data) {
                if(data.length>0){
                    for(var i  in  data){
                        data[i].createTime=format((data[i].createTime))
                    }
                    var config5 = {
                        tableId: "list5",
                        data: data,
                        colArr: colNamesArr5s,
                        multiselect: true,
                        width: 0.95,
                        height: 0.8
                    };
                    fullTable(config5);
                }else{
                    var config5 = {
                        tableId: "list5",
                        data: [],
                        colArr: colNamesArr5s,
                        multiselect: true,
                        width: 0.95,
                        height: 0.8
                    };
                    fullTable(config5);
                }
            });
        }



    });


  /*  $("#Code").keydown(function(event) {
        if (event.keyCode == 13) {
            var batch=$("#Code").val().trim();
            var data = {};
            if (batch != "") {
                data.batch = batch;
            }else {
                toastr.warning("查询失败，该箱子不存在");
            }

            $.get(getBasePath() + "/HisPrintLog/SelectAllXiang1", data, function (data) {
                if(data.length<=0){
                    toastr.warning("该箱无组件存在");
                    return;
                }

                for (var j in data) {
                    var packGd = data[j].packGd;
                    var data = {packGd: packGd};
                    $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {
                        if (data.length > 0) {
                            var config5 = {
                                tableId: "list5",
                                data: data,
                                colArr: colNamesArr5,
                                width: 0.95,
                                height: 0.8
                            };
                            fullTable(config5);
                            totalnum=data.length;
                            $("#totalnum").text(data.length);
                        } else {
                            var config5 = {
                                tableId: "list5",
                                data: [],
                                colArr: colNamesArr5,
                                width: 0.95,
                                height: 0.8
                            };
                            fullTable(config5);
                        }

                    })

                }



               /!* var config = {
                    tableId: "list4",
                    data: data,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.99,
                    height: 0.3,
                    event: {
                        onrowselect: function (rowdatas) {
                            //console.log(rowdatas)
                            //alert(rowdatas.packGd)


                        }
                    }
                };
                fullTable(config);*!/
            });
        }
    })*/

    $("#save").click(function(){
       // var BarCode=$("#Code").val().trim();
        var remark=$("#remark").val().trim();
       // if(BarCode==""||BarCode==null){
            //toastr.warning("箱号不能为空");
           // return;
     //   }
        var data={};
       // data.barCode=BarCode;
        data.Status="01";
        data.remark=remark;
       // var str=[];
        var str="";

        var listData=getSeRowData("list5");
        if(listData.length>0){
            for(var i  in  listData){
               // str.push(listData[i].barCode)
                str+=listData[i].barCode+","
            }
        }else {
            toastr.warning("选择的箱子冻结信息不能为空");
            return;
        }
        data.str=str


        $.get( getBasePath() + "/Disassoc/DJBarCode",data,function (data) {
            if(data.Body.MsgCode=="xxx"){
                toastr.warning(data.Body.MsgDes);
                derow("list5");
            }else {
                $("#Code").val("");
                $("#remark").val("");
                var config5 = {
                    tableId: "list5",
                    data: [],
                    colArr: colNamesArr5,
                    width: 0.95,
                    height: 0.8
                };
                fullTable(config5);
                toastr.success("成功");
            }
        });


    });



});