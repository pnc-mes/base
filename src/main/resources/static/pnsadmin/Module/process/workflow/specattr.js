/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理JS
 * 创建人：潘俊峰
 * 创建时间：2017-06-27
 * 修改人：
 * 修改时间：
 */
$(function () {

    //@ sourceURL=xxxx.js
    //getSpec(window.sessionStorage.getItem("_WfVerRd"), window.sessionStorage.getItem("_SpecVerRd"));

    $("#SpecName").val(window.sessionStorage.getItem("_SpecName"));

    var data = window.sessionStorage.getItem("_Data");

    console.log(data);

    window.sessionStorage.removeItem("_SpecName");
    window.sessionStorage.removeItem("_Data");

    var json = eval('(' + data + ')');

    //路线表格
    var colNamesArr = [
        { "Caption":"Id", "Name":"Id", "CType":"text", "Hidden":true},
        { "Caption":"WfCirRd", "Name":"WfCirRd", "CType":"text", "Hidden":true},
        { "Caption":"表达式", "Name":"Expression", "CType":"text", "Editable":true},
        { "Caption":"路径", "Name":"RoutePath", "CType":"text"},
        { "Caption":"路线类型", "Name":"RouteType", "CType":"text"}
    ];
    var config = {
        tableId: "list4",
        data: json.Condition,
        colArr:colNamesArr
    };
    fullTable(config);

    //确认
    $("#_finish").click(function() {

        var id = json["Id"];

        var _target = [];

        var parentFrame = window.parent.document.getElementById(id);

        var _target = getRowData("list4");

        /*for(var i=0; i<json.Condition.length; i++){

            json.Condition[i].Expression = "测试";

            //var targetId = json.Condition[i].Id;

            _target.push(json.Condition[i]);

            //$(parentFrame).attr(targetId, JSON.stringify(json.Condition[i]));
        }*/

        $(parentFrame).attr("_target", JSON.stringify(_target));

        //$(parentFrame).attr("NextSpecVerRd", json["NSpecVerRd"]);

        parent.layer.closeAll("iframe");
    });

    //关闭
    $("#_close").click(function() {
        parent.layer.closeAll("iframe");
    });

    //加载流程工序信息
    /*function getSpec(WfVerRd, SpecVerRd){

        var busData = "{\"WfVerRd\":" + WfVerRd + ",\"SpecVerRd\":" + SpecVerRd +"}";

        $.ajax({
            url: "/mesadmin/WF/GetWfSInfo",
            type: "POST",
            data: {
                "ExecType": "00",
                "BusData": busData
            },
            success: function (data) {
                if("00" == data.Body.Data.SeSpecType){
                    $("#check").prop("checked", true);
                }
                else {
                    $("#check").prop("checked", false);
                }

                var modelArr = ['WfCirRd','Expression','RoutePath', 'RouteType'];
                var colNamesArr = ['id','表达式', '路径','路线类型'];
                var tableId = "list4";
                var divId = "pager4";
                var pageBean = {
                    "page": 1,
                    "total": 2,
                    "records": 20,
                };

                fullTable(data.Body.Data.Condition, modelArr, colNamesArr, tableId, divId, pageBean);

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.closeAll();
                alertError(textStatus);
            }
        });
    }*/

});

