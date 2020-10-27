/**
 * Created by test on 2017/6/28.
 */
/******************************左侧的表格***********************************************/
$(function () {

    $("#content").on('click', function () {
        var PDRd = $("#hidden").val();
        var data = {
            "PDRd": PDRd
        }

        request({
                url: "/PD/ExportPDCInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(data)}
            }, function (Body) {
                PDRd = null;
                console.log(Body);

            });
        });
});
