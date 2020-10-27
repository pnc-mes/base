/**
 * Created by test on 2017/6/28.
 */
/******************************左侧的表格***********************************************/
$(function () {

    //关闭弹出层
    $(".cLose").on("click", function () {
        parent.layer.closeAll();
    });

    //默认加载上
    var colNamesArr = [
        {"Caption": "id", "Name": "PDRd", "IsKey": true, "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false, "CType": 'text',Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false, "CType": 'text'},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false, "CType": 'text'},
        {"Caption": "仓库", "Name": "StoreName", "Editable": false, "CType": 'text',Width:80},
        {"Caption": "库位", "Name": "LName", "Editable": false, "CType": 'text',Width:80},
        {"Caption": "数量", "Name": "Num", "Editable": false, "CType": 'text',Width:50},
        {"Caption": "变更原因", "Name": "ChgReason", "Editable": false, "CType": 'text',Width:80}
    ];
    var pageBean = {
        "page": 1,
        "total": 1,
        "records": 20
    };
    var config = {
        tableId: "list4",
        divId: "pager4",
        pageBean: pageBean,
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.48
    };
    fullTable(config);


    var colNamesArr1 = [
        {"Caption": "id", "Name": "PDRd", "IsKey": true, "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false, "CType": 'text',Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false, "CType": 'text'},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false, "CType": 'text'},
        {"Caption": "仓库", "Name": "StoreName", "Editable": false, "CType": 'text',Width:80},
        {"Caption": "库位", "Name": "LName", "Editable": false, "CType": 'text',Width:80},
        {"Caption": "数量", "Name": "Num", "Editable": false, "CType": 'text',Width:50}
    ];

    var config1 = {
        tableId: "list5",
        divId: "pager5",
        pageBean: pageBean,
        data: [],
        colArr: colNamesArr1,
        multiselect: true,
        width: 0.48
    };
    fullTable(config1);

    var Gettabledata = function () {
        var PDRd = $("#hidden").val();
        var pdRd1 = {
            "PDRd": PDRd
        }

        var objData = {
            "ExecType": "00",
            "BusData": JSON.stringify(pdRd1)
        };
        //填充表格
        request({
                url: "/PD/GetPDCInfo",
                data: objData
            },
            function (Body) {

                pdcyBody = Body;

                var config = {
                    tableId: "list4",
                    data: Body.Data.PDInfo,
                    divId: "pager4",
                    pageBean: pageBean,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.48
                };

                fullTable(config);

                var config1 = {
                    tableId: "list5",
                    data: Body.Data.InstockInfo,
                    divId: "pager5",
                    pageBean: pageBean,
                    colArr: colNamesArr1,
                    multiselect: true,
                    width: 0.48
                };

                fullTable(config1);

                /*填充差异信息*/
                $("#PDCode").append(Body.Data.PDCode);
                $("#StoreName").append(Body.Data.StoreName);
                $("#StartTime").append(Body.Data.ExecTime);
                $("#FinishTime").append(Body.Data.FinishTime);
                $("#Execor").append(Body.Data.Execor);
                $("#Checkor").append(Body.Data.Checkor);
                console.log(Body);
                for(var i=0;i<Body.Data.PDInfo.length;i++){
                    var str = "<tr>";
                    if(Body.Data.PDInfo.length > 0){
                        str = str  +"<td>"+Body.Data.PDInfo[i].MaCode+"</td>"
                            +"<td>"+Body.Data.PDInfo[i].MaName+"</td>"
                            +"<td>"+Body.Data.PDInfo[i].MaDes+"</td>";
                    }
                    if(Body.Data.InstockInfo.length > 0){
                        str = str +"<td>"+Body.Data.InstockInfo[i].StoreName+"</td>"
                            +"<td>"+Body.Data.InstockInfo[i].LName+"</td>"
                            +"<td>"+Body.Data.InstockInfo[i].Num+"</td>";
                    }
                    if(Body.Data.PDInfo.length > 0){
                        str = str +"<td>"+Body.Data.PDInfo[i].StoreName+"</td>"
                            +"<td>"+Body.Data.PDInfo[i].LName+"</td>"
                            +"<td>"+Body.Data.PDInfo[i].Num+"</td>";
                    }
                    if(Body.Data.PDInfo.length > 0) {
                        str = str + "<td>" + Body.Data.PDInfo[i].UnitName + "</td>"
                            + "<td>" + Body.Data.PDInfo[i].ChgReason + "</td>"
                            + "</tr>";
                    }
                    $("#CyContent").append(str);
                }

            });
    }

    Gettabledata();

//点击确定按钮
    $("#cSave").click(function () {

        var PDRd = $("#hidden").val();
        var pdRd = {
            "PDRd": PDRd
        }
        var objData = {
            "ExecType": "04",
            "BusData": JSON.stringify(pdRd)
        };

        layer.confirm('确认差异吗？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            request({
                    url: "/PD/SavePDInfo",
                    data: objData
                },
                function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                });
        });

    });


    /******************导出***王怀龙********************/
    $("#export").on('click', function () {

        var PDRd = $("#hidden").val();
        var data = {
            "PDRd": PDRd
        }
        layer.confirm('确认要导出该盘点单的差异信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            /*request({
                url: "/PD/ExportPDCInfo",
                data: {"ExecType": "01", "busData": JSON.stringify(data)}
            }, function (Body) {
                PDRd = null;
                console.log(Body);
                toastr.success(Body.MsgDes);

            });*/
            var url = getBasePath() + "/PD/ExportPDCInfo";
            var data_ = "ExecType=01&BusData=" + JSON.stringify(data);
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

                        filename = "盘点差异表-" + date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();

                        var blob = new Blob([this.response], { type: type });
                        if (typeof window.navigator.msSaveBlob !== 'undefined') {
                            // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
                            window.navigator.msSaveBlob(blob, filename);
                        } else {
                            var URL = window.URL || window.webkitURL;
                            var downloadUrl = URL.createObjectURL(blob);

                            if (filename) {
                                // use HTML5 a[download] attribute to specify filename
                                var a = document.createElement("a");
                                // safari doesn't support this yet
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

                            setTimeout(function () { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
                        }
                    }else{
                        var un = new Uint8Array(this.response);
                        var s = "";
                        for(var i=0; i<un.length; i++){
                            s += String.fromCharCode(un[i]);
                        }
                        var json = JSON.parse(s);
                        if(json.Body != undefined){
                            toastr.warning(json.Body.MsgDes);
                        }
                    }
                }
            };
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xhr.send(data_);
        });
    });

    $("#print").on('click', function () {

        /* debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
         importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
         printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
         operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true*/
        var options = {
            debug: false,
            importCSS: true,
            printContainer: true,
            operaSupport: true
        }
        var obj = $("#panDianaForm").clone();
        obj.removeAttr("style");
        obj.jqprint();
    });

});
