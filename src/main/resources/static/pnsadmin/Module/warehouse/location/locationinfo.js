/**
 * Created by liufuzhi on 2017/7/1.
 */
/*-------------------树形的处理-------------------*/
$(function () {
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "lCode", //自定义名字：标签id名字
        "param": "LCode" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "lName",
        "param": "LName"
    }, {
        //创建人
        "ctlid": "creatPeople",
        "param": "Creator"
    }, {
        //创建时间
        "ctlid": "creatTime",
        "param": "CreateTime"
    }, {
        //修改人
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    }, {
        //修改时间
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    }, {
        //备注
        "ctlid": "beizhu",
        "param": "Remark"
    }];

    //加载下拉框仓库信息
    var params = {
        "displaymode": "1",
        "title": "仓库",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "仓库id",
                    "name": "StoreRd"
                }, {
                    "caption": "仓库名称",
                    "name": "StoreName"
                }
            ]
        },
        "showresult": {"ishead": true},
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"StoreName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"StoreName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                var obj = {
                    data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url: "/Store/GetAllStoreInfo"
                };
                request(obj, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "StoreRd": datas[i].StoreRd,
                            "StoreName": datas[i].StoreName,
                        }
                        xldata.push(data);
                    }
                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    }

    $("#defaultSelect").zc_select(params);

    /*-------------------点击树节点------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        $("#lCode").attr("readonly",true);
        var objBusData = JSON.stringify({"LRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url: "/Store/GetLInfo", data: objData}, function (Body) {
            fillform("locationinfo", rule, Body.Data);
            $("#defaultSelect").showData({
                id: Body.Data.StoreRd,
                name: Body.Data.StoreName,
                keyfield: "StoreRd",
                fields: [
                    {"name": "StoreRd"},
                    {"name": "StoreName"}
                ]
            });

        })
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
        "PageSize":20,
        "PageIndex":currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/Store/GetAllLInfo","LRd","LName","LName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }
    });
    $(".input1").keydown(function(event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/Store/GetAllLInfo","LRd","LName","LName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    var list=[];
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Store/GetAllLInfo","LRd","LName","LName",condition,currentPage,config,list);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Store/GetAllLInfo","LRd","LName","LName",condition,currentPage,config,list);
    });

    //只刷新树
    var loadtree = function () {
        var trees = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"CreateTime DESC",
                    "FieldOpt":"Order BY"
                }
            ]
        };
        request({url: '/Store/GetAllLInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}}, function (Body) {
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                if(treeData.length<=0){
                    return false;
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].LRd == undefined ? "" : treeData[i].LRd,
                        name: treeData[i].LName == undefined ? "" : treeData[i].LName
                    }
                    trees.push(tree);
                }
                /*----------------------定义控件规则-------------------*/
                config.data.source = trees;
                $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadtree();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("locationinfo");
        $("#defaultSelect").clearseldata("StoreRd");
        $("#lCode").attr("readonly",false);
        treeID = null;
        $("#ExecType").val("00");
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/Store/SaveLInfo",
                        data: {"ExecType": "01", "busData": "{\"LRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        loadtree();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });


    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        var StoreRd = $("#defaultSelect").getseldata().StoreRd;

        if ($("#lName").val().trim() != "" && StoreRd != "" ) {
            // 获取所有表单数据封装成json对象
            formData = transfer("locationinfo");
            //新增库位信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "LCode": formData["lCode"],
                    "LName": formData["lName"],
                    "StoreRd": StoreRd,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/Store/SaveLInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success("库位信息新增成功,库位代码{"+Body.Data.LCode+"}");
                    $("#ExecType").val("");
                    loadtree();

                    $("#hidden1").attr("editid", Body.Data.LRd);
                    treeID=$("#hidden1").attr("editid");
                    $("#hidden2").attr("editcode", Body.Data.LCode);
                    backfill(treeID);
                });
            }
            //编辑工作中心信息
            else if (treeID != null && treeID != "") {

                newData = {
                    "LRd": treeID,
                    "LName": formData["lName"],
                    "StoreRd": StoreRd,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/Store/SaveLInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    loadtree();
                    backfill(treeID);
                });
            }
        } else {
            if (StoreRd == "") {
                $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect").find("input:eq(0)").val("");
                $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
            }

            if ($("#lName").val() == "") {
                $("#lName").css("border-color", "red");
                $("#lName").prop("placeholder", "不能为空！");
            }
        }
    });

    var xlstore="";
    request({url:'/Store/GetAllStoreInfo', data: {"ExecType": "00"}},function(Body) {
        var data=Body.Data;
        if(data.length==1){
            xlstore+="\""+data[0].StoreRd+"\":\""+data[0].StoreName+"\"";
        }else if(data.length>1) {
                for(var i=0;i<data.length;i++){
                    xlstore+="\""+data[i].StoreRd+"\":\""+data[i].StoreName+"\""+"|";
                }
        }
    });
    //筛选
    var params = [{
        "caption": "仓库名称",
        "name": "StoreRd",
        "valtype": "01",
        "data":xlstore
    }];

    var StoreRd="";
    var event = {
        onsure: function (result) {
            for (var i in result) {
                StoreRd=result[i].StoreRd;
                var data={
                    "StoreRd":StoreRd
                }
                var trees = [];
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"LName",
                            "FieldOpt":"Order BY"
                        }
                    ]
                };
                request({url: '/Store/GetAllLInfo1', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"BusData":JSON.stringify(data)}}, function (Body) {
                    currentPage = 0;
                    var treeData = Body.Data;
                    if (treeData.length > PageInfo.PageSize) {
                        $pages.css('display', 'block');
                    } else {
                        $pages.css('display', 'none');
                    }
                    if(treeData.length<=0){
                        return false;
                    }
                    var len = treeData.length >= 20 ? 20:treeData.length;
                    for (var i = 0; i < len; i++) {
                        var tree = {
                            id: treeData[i].LRd == undefined ? "" : treeData[i].LRd,
                            name: treeData[i].LName == undefined ? "" : treeData[i].LName
                        }
                        trees.push(tree);
                    }
                    /*----------------------定义控件规则-------------------*/
                    config.data.source = trees;
                    $.JstreeEx.init(config);//先调用后加载
                });
                //delete InitData.FiledList['LName'];
                list = InitData.FiledList;
            }
        }
    };

    //筛选
    var config1 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config1);

    //导出库位信息
    $("#export").on('click', function () {
        layer.confirm('确认要导出该库位信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            var data={
                "StoreRd":StoreRd
            }
            layer.closeAll("dialog");
            var url = getBasePath() + "/Store/ExportGetLInfo";
            var data_ = "ExecType=00&BusData="+JSON.stringify(data);
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

                        filename = "库位信息表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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

    //数据回填
    function backfill(treeID){
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#lCode").attr("readonly",true);
        var objBusData = JSON.stringify({"LRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        /*------------------获取点击之后一个节点的数据------------------*/
        request({url: "/Store/GetLInfo", data: objData}, function (Body) {
            fillform("locationinfo", rule, Body.Data);
            $("#defaultSelect").showData({
                id: Body.Data.StoreRd,
                name: Body.Data.StoreName,
                keyfield: "StoreRd",
                fields: [
                    {"name": "StoreRd"},
                    {"name": "StoreName"}
                ]
            });

        })
    }
});
