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
        multiselect: true,
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
    var config5 = {
        tableId: "list5",
        data: [],
        colArr: colNamesArr5,
        multiselect: true,
        width: 0.95,
        height: 0.8
    };
    fullTable(config5);

    $("#Code").keydown(function(event) {
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
                addid=0;
                for (var j in data) {
                    var packGd = data[j].packGd;
                    var data = {packGd: packGd};
                    $.get(getBasePath() + "/HisPrintLog/SelectAllXiangDetail", data, function (data) {
                        if (data.length > 0) {
                            var config5 = {
                                tableId: "list5",
                                data: data,
                                colArr: colNamesArr5,
                                multiselect: true,
                                width: 0.95,
                                height: 0.8
                            };
                            fullTable(config5);
                            totalnum=data.length;
                            $("#totalnum").text(data.length);
                            $("#checktotalnum").text(addid);
                        } else {
                            var config5 = {
                                tableId: "list5",
                                data: [],
                                colArr: colNamesArr5,
                                multiselect: true,
                                width: 0.95,
                                height: 0.8
                            };
                            fullTable(config5);
                        }

                    })

                }



               /* var config = {
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
                fullTable(config);*/
            });
        }
    })

    $("#save").click(function(){
        var BarCode=$("#Code").val().trim();
        var Remark=$("#remark").val().trim();
        if(BarCode==""||BarCode==null){
            toastr.warning("箱号不能为空");
            return;
        }
        var list5Data=getSeRowData('list5');
        if(list5Data.length<=0){
            toastr.warning("选中数据失败");
            return;
        }
        var Detail=[];
        for(var i in  list5Data){
            var Details={
                "Batch":list5Data[i].packCode
            }
            Detail.push(Details);
        }
        var data={
            Status:"00",
            Remark:Remark,
            BarCode:BarCode,
            Detail:Detail
        }

        $.ajax({
            url: getBasePath() + "/RePack/UpDataAllRePack",
            type: "POST",
            data: {"busData": JSON.stringify(data)},
            success: function (Body) {
                if(Body.Status=="200"){
                    toastr.success("操作成功");
                    $("#remark").text("");
                    derow("list5");
                }else {
                    toastr.warning(Body.Body.MsgDes);
                }
                $("#totalnum").text(parseInt(totalnum)-parseInt(list5Data.length));
                $("#checktotalnum").text(parseInt(addid)-parseInt(list5Data.length));
            }
        });

    });


    $(document).on("click","tbody tr",function () {
        if($(this).attr("aria-selected")=="true"){
            ++addid;
        }else {
            --addid;
        }
        $("#checktotalnum").text(addid);
    });

    //$("#cb_list5").click(function(){
    $(document).on("click","#cb_list5",function () {
        if($(this).is(":checked")){
            var list5Data=getSeRowData('list5');
            addid=list5Data.length;
            $("#checktotalnum").text(addid);
        }else {
            addid=0;
            $("#checktotalnum").text(0);
        }
    });
});