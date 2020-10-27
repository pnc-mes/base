$(function () {

    //处理表格
    var colNamesArr = [
        {"Caption": "id", "Name": "InsRd", "CType": "text", "Hidden": true},
        {"Caption": "仓库", "Name": "StoreName", "CType": "text", "Editable": false, Width: 50},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text", "Editable": false, Width: 80},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "CType": "text", "Editable": false},
        {"Caption": "库存数量", "Name": "Num", "CType": "text", "Editable": false, Width: 50},
        {"Caption": "可用库存", "Name": "Num", "CType": "text", "Editable": false,Width: 50},
        {"Caption": "单位", "Name": "UnitName", "CType": "text", "Editable": false, Width: 50},
        {"Caption": "库存上限", "Name": "MaxSNum", "CType": "text", "Editable": false, Width: 50},
        {"Caption": "库存下限", "Name": "MinSNum", "CType": "text", "Editable": false, Width: 50}
    ];

    //处理表格
    var colNamesArr1 = [
        {"Caption": "id", "Name": "InsRd", "CType": "text", "Hidden": true},
        {"Caption": "批次", "Name": "Batch", "CType": "text", "Editable": false, Width: 150},
        {
            "Caption": "仓库名称",
            "Name": "StoreName",
            "CType": "text",
            "Editable": false
        },
        {"Caption": "库位名称", "Name": "LName", "CType": "text", "Editable": false},
        {"Caption": "数量", "Name": "Num", "CType": "text", "Editable": false, Width: 80},
        {"Caption": "剩余数量", "Name": "CanNum", "CType": "text", "Editable": false, Width: 80},
        {"Caption": "单位", "Name": "UnitName", "CType": "text", "Editable": false, Width: 80},
        {"Caption": "入库人", "Name": "RKOpter", "CType": "text", "Editable": false, Width: 80},
        {"Caption": "入库时间", "Name": "RKTime", "CType": "text", "Editable": false, Width: 180},
        {"Caption": "备注", "Name": "Remark", "CType": "text", "Editable": false}
    ];

    //处理表格
    var colNamesArr2 = [
        {"Caption": "id", "Name": "InsRd", "CType": "text", "Hidden": true},
        {"Caption": "物料代码", "Name": "MaCode", "CType": "text", "Editable": false, Width: 80},
        {"Caption": "物料名称", "Name": "MaName", "CType": "text", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "CType": "text", "Editable": false},
        {
            "Caption": "仓库",
            "Name": "StoreName",
            "CType": "text",
            "Editable": false
        },
        {"Caption": "库位", "Name": "LName", "CType": "text", "Editable": false},
        {"Caption": "数量", "Name": "Num", "CType": "text", "Editable": false, Width: 80},
        {"Caption": "单位", "Name": "UnitName", "CType": "text", "Editable": false, Width: 80}
    ];


    var StoreRd0 = "";
    /*------------------点击左侧树的时候加载对应右侧表格的信息---------------*/
    var onClicks = function (nodeinfo, handle) {
        $(".rightl").show();
        var config = {
            tableId: "list4",
            data: [],
            colArr: colNamesArr,
            width: 0.85,
            multiselect: true,
            height: 0.39,
        };
        fullTable(config);
        var config1 = {
            tableId: "list5",
            data: [],
            width: 0.85,
            colArr: colNamesArr1,
            multiselect: true,
            height: 0.39
        };

        fullTable(config1);

        var config2 = {
            tableId: "list6",
            data: [],
            width: 0.85,
            colArr: colNamesArr2,
            multiselect: true,
            height: 0.39
        };

        fullTable(config2);

        //点击父节点
        if (nodeinfo.isRoot) {  //父节点
            StoreRd0 = nodeinfo.nodeID;
            var objBusData = JSON.stringify({"StoreRd": StoreRd0});
            var objData = {
                "ExecType": "01",
                "BusData": objBusData,
            };

            /*------------------获取点击之后一个节点的数据------------------*/
            request({
                id: "list4",
                url: "/InStore/GetAllMaSTInfo",
                async: true,
                data: objData
            }, function (Body) {

                var Data1=Body.Data;
                for(var i in Data1){
                    if(Data1[i].MinSNum==0.0){
                        Data1[i].MinSNum="-";
                    }
                    if(Data1[i].MaxSNum==0.0){
                        Data1[i].MaxSNum="-";
                    }
                }
                //处理表格
                var config = {
                    tableId: "list4",
                    data: Data1,
                    colArr: colNamesArr,
                    width: 0.85,
                    multiselect: true,
                    height: 0.39,
                    event: {
                        onrowselect: function (rowdata) {
                            for (var i in rowdata) {
                                var InsRd = rowdata[i].InsRd;
                                var InsRd1 = {
                                    "InsRd": InsRd
                                }

                                var objData1 = {
                                    "ExecType": "00",
                                    "BusData": JSON.stringify(InsRd1)
                                };

                                request({
                                        url: "/InStore/GetAllMaSDTInfo",
                                        data: objData1
                                    },
                                    function (Body) {

                                        var config1 = {
                                            tableId: "list5",
                                            data: Body.Data.BInfo,
                                            width: 0.85,
                                            colArr: colNamesArr1,
                                            multiselect: true,
                                            height: 0.39
                                        };

                                        fullTable(config1);

                                        var config2 = {
                                            tableId: "list6",
                                            data: Body.Data.MaBInfo,
                                            width: 0.85,
                                            colArr: colNamesArr2,
                                            multiselect: true,
                                            height: 0.39
                                        };

                                        fullTable(config2);
                                    });
                            }
                        }
                    }
                };

                fullTable(config);

                if (Body.Data[0] == undefined) {
                    return;
                }
                //默认加载第一个表的第一条数据的相关信息
                var InsRd = Body.Data[0].InsRd;
                var InsRd1 = {
                    "InsRd": InsRd
                }

                var objData2 = {
                    "ExecType": "00",
                    "BusData": JSON.stringify(InsRd1)
                };

                request({
                        id: "list5",
                        url: "/InStore/GetAllMaSDTInfo",
                        async: true,
                        data: objData2
                    },
                    function (Body) {

                        var config1 = {
                            tableId: "list5",
                            data: Body.Data.BInfo,
                            width: 0.85,
                            colArr: colNamesArr1,
                            multiselect: true,
                            height: 0.39
                        };

                        fullTable(config1);

                        var config2 = {
                            tableId: "list6",
                            data: Body.Data.MaBInfo,
                            width: 0.85,
                            colArr: colNamesArr2,
                            multiselect: true,
                            height: 0.39
                        };

                        fullTable(config2);
                    });
            });
        } else {
            //点击子节点

        }
    }
    var config = {
        id: "jstree_demo1",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        //定义事件获取点击的值
        event: {
            onClick: onClicks
        }
    };
    var $pages = $('.pages');

    var condition = "";

    var currentPage = 0;
    var PageInfo = {
        "PageSize": 20,
        "PageIndex": currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/Store/GetAllStoreInfo", "StoreRd", "StoreName", "StoreName", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function(event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/Store/GetAllStoreInfo", "StoreRd", "StoreName", "StoreName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/Store/GetAllStoreInfo", "StoreRd", "StoreName", "StoreName", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/Store/GetAllStoreInfo", "StoreRd", "StoreName", "StoreName", condition, currentPage, config);
    });
    /*-----------------加载树------------*/
    var treedataList = [];
    var pageInfo = {
        "PageSize": 21,
        "PageIndex": 0
    };

    request({
            url: '/Store/GetAllStoreInfo',
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(pageInfo)
            }
        },
        function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            if (treeData.length <= 0) {
                return false;
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].StoreRd == undefined ? "" : treeData[i].StoreRd,
                    name: treeData[i].StoreName == undefined ? "" : treeData[i].StoreName
                }
                treedataList.push(tree);
            }

            /*----------------------定义控件规则-------------------*/
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });

    //筛选
    var xlstore = "";
    request({url: '/Store/GetAllStoreInfo', data: {"ExecType": "00"}}, function (Body) {
        var data = Body.Data;

        if (data.length == 1) {
            xlstore += "\"" + data[0].StoreRd + "\":\"" + data[0].StoreName + "\"";
        } else if (data.length > 1) {
            xlstore += "\"" + "" + "\":\"" + "" + "\"" + "|";
            for (var i = 0; i < data.length; i++) {
                xlstore += "\"" + data[i].StoreRd + "\":\"" + data[i].StoreName + "\"" + "|";
            }
        }
    });

    var params = [{
        "caption": "仓库名称",
        "name": "StoreRd",
        "valtype": "01",
        "data": xlstore
    }, {
        "caption": "物料代码",
        "name": "MaterialCode",
        "valtype": "00"
    }, {
        "caption": "物料名称",
        "name": "MaterialName",
        "valtype": "00"
    }, {
        "caption": "物料描述",
        "name": "MaterialDes",
        "valtype": "00"
    }, {
        "caption": "订货号",
        "name": "OrderNum",
        "valtype": "00"
    }, {
        "caption": "品牌",
        "name": "Brand",
        "valtype": "00"
    }, {
        "caption": "尺寸",
        "name": "Size",
        "valtype": "00"
    }, {
        "caption": "材质",
        "name": "Material",
        "valtype": "00"
    }, {
        "caption": "规格",
        "name": "Norm",
        "valtype": "00"
    }, {
        "caption": "型号",
        "name": "Model",
        "valtype": "00"
    }];

    var datasources = {};
    var MaCode = "";
    var MaName = "";
    var MaterialDes="";
    var OrderNum="";
    var Brand="";
    var Size="";
    var Material="";
    var Norm="";
    var Model="";
    var event = {
        onsure: function (result) {
            StoreRd0 = result[0].StoreRd;
            MaCode = result[1].MaterialCode;
            MaName = result[2].MaterialName;
            MaterialDes=result[3].MaterialDes;
            OrderNum=result[4].OrderNum;
            Brand=result[5].Brand;
            Size=result[6].Size;
            Material=result[7].Material;
            Norm=result[8].Norm;
            Model=result[9].Model;
            datasources = {
                "StoreRd": StoreRd0,
                "MaCode": MaCode,
                "MaName": MaName,
                "MaterialDes":MaterialDes,
                "OrderNum":OrderNum,
                "Brand":Brand,
                "Size":Size,
                "Material":Material,
                "Norm":Norm,
                "Model":Model,
            };

            $(".rightl").show();
            //加载仓库并显示节点对应的数据
            var config = {
                tableId: "list4",
                data: [],
                colArr: colNamesArr,
                width: 0.85,
                multiselect: true,
                height: 0.39,
            };
            fullTable(config);
            var config1 = {
                tableId: "list5",
                data: [],
                width: 0.85,
                colArr: colNamesArr1,
                multiselect: true,
                height: 0.39
            };

            fullTable(config1);

            var config2 = {
                tableId: "list6",
                data: [],
                width: 0.85,
                colArr: colNamesArr2,
                multiselect: true,
                height: 0.39
            };

            fullTable(config2);
            request({
                    id: "list4",
                    url: "/InStore/GetAllMaSTInfo1",
                    async: true,
                    data: {
                        "ExecType": "00",
                        "busData": JSON.stringify(datasources)
                    }
                },
                function (Body) {
                    var Data1=Body.Data;
                    for(var i in Data1){
                        if(Data1[i].MinSNum==0.0){
                            Data1[i].MinSNum="-";
                        }
                        if(Data1[i].MaxSNum==0.0){
                            Data1[i].MaxSNum="-";
                        }
                    }
                    var config = {
                        tableId: "list4",
                        data: Data1,
                        colArr: colNamesArr,
                        width: 0.85,
                        multiselect: true,
                        height: 0.39,
                        event: {
                            onrowselect: function (rowdata) {
                                for (var i in rowdata) {
                                    var InsRd = rowdata[i].InsRd;

                                    var InsRd1 = {
                                        "InsRd": InsRd
                                    }

                                    var objData1 = {
                                        "ExecType": "00",
                                        "BusData": JSON.stringify(InsRd1)
                                    };

                                    request({
                                            url: "/InStore/GetAllMaSDTInfo",
                                            data: objData1
                                        },
                                        function (Body) {

                                            var config1 = {
                                                tableId: "list5",
                                                data: Body.Data.BInfo,
                                                width: 0.85,
                                                colArr: colNamesArr1,
                                                multiselect: true,
                                                height: 0.39
                                            };

                                            fullTable(config1);

                                            var config2 = {
                                                tableId: "list6",
                                                data: Body.Data.MaBInfo,
                                                width: 0.85,
                                                colArr: colNamesArr2,
                                                multiselect: true,
                                                height: 0.39
                                            };

                                            fullTable(config2);
                                        });
                                }
                            }
                        }
                    };

                    fullTable(config);

                    //默认加载第一个表的第一条数据的相关信息
                    if (Body.Data.length <= 0)
                        return;
                    var InsRd = Body.Data[0].InsRd;
                    var InsRd1 = {
                        "InsRd": InsRd
                    }

                    var objData2 = {
                        "ExecType": "00",
                        "BusData": JSON.stringify(InsRd1)
                    };

                    request({
                            id: "list5",
                            url: "/InStore/GetAllMaSDTInfo",
                            async: true,
                            data: objData2
                        },
                        function (Body) {

                            var config1 = {
                                tableId: "list5",
                                data: Body.Data.BInfo,
                                width: 0.85,
                                colArr: colNamesArr1,
                                multiselect: true,
                                height: 0.39
                            };

                            fullTable(config1);
                            var config2 = {
                                tableId: "list6",
                                data: Body.Data.MaBInfo,
                                width: 0.85,
                                colArr: colNamesArr2,
                                multiselect: true,
                                height: 0.39
                            };

                            fullTable(config2);
                        });
                });
        }
    };

    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);

    //导出
    $("#export").on('click', function () {
        layer.confirm('确认要导出该库存信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");
            var url = getBasePath() + "/InStore/ExportInStore";

            if (MaCode == "" && MaName == "" && MaterialDes=="" && OrderNum=="" && Brand=="" && Size=="" && Material=="" && Norm=="" && Model=="" ) {
                datasources = {
                    "StoreRd": StoreRd0
                };
            } else {
                datasources = {
                    "StoreRd": StoreRd0,
                    "MaCode": MaCode,
                    "MaName": MaName,
                    "MaterialDes":MaterialDes ,
                    "OrderNum":OrderNum,
                    "Brand":Brand,
                    "Size":Size,
                    "Material":Material,
                    "Norm":Norm,
                    "Model":Model,
                };
            }

            var data_ = "ExecType=01&BusData=" + JSON.stringify(datasources);
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

                        filename = "库存管理表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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


});
