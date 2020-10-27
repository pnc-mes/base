/**
 * Created by test on 2017/7/14.
 */
$(function () {

    //表格定义
    var colNamesArr = [
        {"Caption": "批次号", "Name": "Batch", "CType": "text", Width: 150},
        {"Caption": "线体", "Name": "LineName", "CType": "text", Width: 150},
        {"Caption": "工序", "Name": "SpecName", "CType": "text", Width: 150},
        {"Caption": "异常消息", "Name": "Msg", "CType": "text", Width: 350},
        {"Caption": "时间", "Name": "CreateTime", "CType": "text", Width: 150}
    ];

    var busData = {
        "Batch": "",
        "CreateTimeStart": "",
        "CreateTimeEnd": ""
    };
    SelectGzYc(busData);

    $(".cSelect").click(function () {
        var batch = $("#batch").val().trim();
        var CreateTimeStart = $("#StartTime").val().trim();
        var CreateTimeEnd = $("#EndTim").val().trim();
        var busData = {
            "Batch": batch,
            "CreateTimeStart": CreateTimeStart,
            "CreateTimeEnd": CreateTimeEnd
        };
        SelectGzYc(busData);
    });


    function SelectGzYc(data) {
        var busData = {
            "Batch": data.Batch,
            "CreateTimeStart": data.CreateTimeStart,
            "CreateTimeEnd": data.CreateTimeEnd
        };
        request({
            url: "/Report/GetAllGZYCInfo",
            data: {"ExecType": "00", "BusData": JSON.stringify(busData)}
        }, function (Body) {
            if (Body.Data != null) {
                var config = {
                    tableId: "list4",
                    data: Body.Data,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.99,
                    height: 0.88
                };
                fullTable(config);
            } else {
                toastr.warning(Body.MsgDes);
                return;
            }
        });
    }

});
