/**
 * Created by test on 2017/8/11.
 */
$(function () {
    //处理变更原因
    var ReaDesReason = [];
    request({url:"/Reason/GetReaTypeInfo",data:{"ExecType":"00"}},function (Body) {
        var resonDatas = Body.Data;
        for(var i in resonDatas){
            var data = {
                "ReaType":resonDatas[i].ReaType ,
                "ReaDes": resonDatas[i].ReaTDes
            }
            ReaDesReason.push(data);
        }
    })
    //1.定义表格的列信息
    var colModelArr = [
        {"Caption": "批次", "Name": "Batch", "CType": "text", "Editable": false,Width:120},
        {"Caption": "产品/物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "原批次数量", "Name": "Num", "CType": "text", "Editable": false,Width:80},
        {"Caption": "变更后数量", "Name": "AfNum", "CType": "text", "Editable": true,Width:80},
        {"Caption": "变更原因", "Name": "ReaDesInfo", "CType": "select",
            "SelectPr":{"Data":ReaDesReason,"DisplayName":"ReaDes","DataValue":"ReaType"},
            'Editable': true,Width:120
        },
        {"Caption": "备注", "Name": "Remark", "CType": "text", "Editable": true,Width:120}
    ];
    var tableObj = {
        tableId:"list4",
        colArr:colModelArr,
        data:[],
        width:0.98,
        multiselect:true,
        height:0.72
    };
    fullTable(tableObj);

    //2.加载所有的批次号
    /*request({url:"/Batch/GetAllBatchInfo",data:{"ExeType":"00"}},function (Body) {
        var batchListData = Body.Data;
        var batchList = $("#batchList");
        for(var i = 0; i < batchListData.length; i++){
            batchList.append("<option value='"+ batchListData[i].Batch +"' />");
        }
    })*/
    //输入批次号进行查询
    var $batchNum = $('#batch');
    var checkTableData = function(){
        request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch":$batchNum.val()})}},function (Body) {
            if(Body.Data.Status == '00') {
                var $batchNameTds = $("#list4 .Batch");//获取所有的批次号对应的td对象
                if ($batchNameTds.length > 0) {//表示批次号关联的信息存在
                    $batchNameTds.each(function () {//遍历td得到里面的值
                        if ($(this).text() == $batchNum.val()) {//如果输入的批次号和表格里面的批次号相等，则不添加
                            toastr.warning("该批次已经存在表格中");
                            return false;
                        } else {//不存在添加
                            var table = {
                                Batch: $batchNum.val(),
                                MaName: Body.Data.MaName,
                                Num: Body.Data.Num,
                                AfNum: Body.Data.Num,
                                ReaDesInfo: {
                                    "ReaType": ReaDesReason[0].ReaType,
                                    "ReaDes": ReaDesReason[0].ReaDes
                                },
                                Remark: ""
                            };
                            addSrow("list4", table);
                        }
                    });
                }
                else {
                    var table = {
                        Batch: $batchNum.val(),
                        MaName: Body.Data.MaName,
                        Num: Body.Data.CanNum,
                        AfNum: Body.Data.Num,
                        ReaDesInfo: {
                            "ReaType": ReaDesReason[0].ReaType,
                            "ReaDes": ReaDesReason[0].ReaDes
                        },
                        Remark: ""
                    };
                    addSrow("list4", table);
                }
            }else{
                toastr.warning("该批次无法进行更改数量...")
            }
        })
    }
    $batchNum.on('keydown', function (event) {
            if (event.keyCode == 13) {
                checkTableData();
            }
        })
    //确认
    $("#check").on("click",function(){

        //获取表格数据
        var data = getRowData("list4");
        var tables = [];
        for(var i in data){
            var table = {
                "Batch": data[i].Batch,
                "BeNum":data[i].Num,
                "AfNum":data[i].AfNum,
                "ReaDes":data[i].ReaDes,
                "Remark": data[i].Remark
            };
            tables.push(table);
        }
        if($('#batch').val().trim() == ''){
            toastr.warning('请填写批次号进行操作之后再进行确认')
        } else {
            request({url: "/Batch/SaveBOptInfo", data: {"ExecType": "02", "BusData": JSON.stringify(tables)}}, function (Body) {
                toastr.success(Body.MsgDes);
            })
        }
    });
    //删除
    $(".cDel").on("click",function(){
        var selectedRowIds = $("#list4").jqGrid("getGridParam", "selarrrow");
        var gr = 0;
        if (selectedRowIds != undefined && selectedRowIds != null)
            gr = selectedRowIds.length;
        if (gr > 0) {
            layer.confirm('', {
                    type: 0,
                    btn: ['确认', '取消'], //按钮
                    content: '确认删除吗？',
                    icon: "fa-check-circle"
                }, function () {
                    for (var i = 0; i < gr; i++) {
                        derow("list4");
                    }
                    layer.closeAll();
                }
            );
        } else {
            toastr.warning("请选择要删除的数据再进行删除！")
        }
    })
});