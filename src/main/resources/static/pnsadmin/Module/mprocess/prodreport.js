$(function () {
    request({url: "/Report/GetAllProdInfo", data: {"ExecType": "00"}}, function (Body) {
        if (Body.Data.length) {
            var $tbody = $("tbody");
            var $tr = $("#column");
            var $topTr = $("#topTr");
            $topTr.append("<th></th>");
            $tr.append("<td>日期</td>");
            for (var i in Body.Data) {
                var $ths = $topTr.find("th:gt(0)");
                var len = $ths.length;
                for (var j in Body.Data[i].SpecPDInfo) {
                    if (len == 0) {
                        $topTr.append("<th colspan='4' class='text-center'>" + Body.Data[i].SpecPDInfo[j].SpecName + "</th>");
                        $tr.append("<td>进站数</td><td>出站数</td><td>良率</td><td>工时(分钟)</td>");
                    }
                    else {
                        $ths.each(function (index, ele) {
                            if ($(ele).text() != Body.Data[i].SpecPDInfo[j].SpecName && index == len - 1) {
                                $topTr.append("<th colspan='4' class='text-center'>" + Body.Data[i].SpecPDInfo[j].SpecName + "</th>");
                                $tr.append("<td>进站数</td><td>出站数</td><td>良率</td><td>工时(分钟)</td>");
                            }
                        });
                    }
                }
            }
            for (var i in Body.Data) {
                var $ths = $topTr.find("th:gt(0)");
                $tbody.append("<tr id='" + i + "'><td>" + Body.Data[i].ProdDate + "</td></tr>");
                for (var j in Body.Data[i].SpecPDInfo) {
                    $ths.each(function (index, ele) {
                        if ($(ele).text() == Body.Data[i].SpecPDInfo[j].SpecName) {
                            $("#" + i).append("<td>" + Body.Data[i].SpecPDInfo[j].MINum + "</td>"
                                + "<td>" + Body.Data[i].SpecPDInfo[j].MONum + "</td>"
                                + "<td>" + Body.Data[i].SpecPDInfo[j].Yield + "</td>"
                                + "<td>" + Body.Data[i].SpecPDInfo[j].WHours + "</td>"
                            );
                            return false;
                        } else {//TODO -- 有问题，后续改掉
                            $("#" + i).append("<td></td><td></td><td></td><td></td>");
                        }
                    });
                }
            }
        }
    });

});
