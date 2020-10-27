/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理JS
 * 创建人：乔帅阳
 * 创建时间：2019-04-09
 * 修改人：
 * 修改时间：
 */
window.onload = function () {
    var treeID = null;

    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开

        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"QCheckMaRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };

        // 使用封装的ajax请求后台服务
        request({url: "/SunPort/QCM/GetQCMInfo", data: objData,}, function (Body) {
            fillform("printForm", rule, Body.Data);
            $("#QCheckMaCode").val(Body.Data.QCheckMaCode);
            $("#QCheckMaType").val(Body.Data.QCheckMaType);
            $("#Status").val(Body.Data.Status);
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#Remark").val(Body.Data.Remark);
            $("#StockMaCode").val(Body.Data.StockMaCode);
            if (Body.Data.FinalResult != undefined && Body.Data.FinalResult != null) {
                if (Body.Data.FinalResult == "00") {
                    $("#hege").prop("checked", true);
                } else {
                    $("#buhege").prop("checked", true);
                }
            }
            /*  fillform("factoryinfo", rule, Body.Data);*/

            //每次点击某个节点之后加载的树
            function ss() {
                var QCMaInfoArr = [];
                var treeData = Body.Data.QCMaInfo;
                for (var i = 0; i < treeData.length; i++) {
                    var specInfo = {
                        "id": treeData[i].QCMaRd,
                        "name": treeData[i].BarCode,
                        "barType": treeData[i].BarType,
                        "gonglv": treeData[i].PowerGear,
                        "dianliu": treeData[i].ELGear,
                        "yanse": treeData[i].ColorGear,
                        "zonghedengji": treeData[i].GradeName,
                        "ShbCode": treeData[i].ShbCode,
                        "MixedBag": treeData[i].MixedBag,
                        "tbody": treeData[i].CellData,
                        "tbody1": treeData[i].IVData
                    };
                    QCMaInfoArr.push(specInfo);
                }
                initThisZtree(QCMaInfoArr);//传入数据,初始化ztree
                fuzzySearch('book', '#keyword', null, true);
            }

            ss();
        });

    };

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
        if (condition == "") {
            /*condition = "CKJ"*/
        }
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/SunPort/QCM/GetAllQCMInfoByCQJ", "QCheckMaRd", "QCheckMaCode", "QCheckMaCode", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function (event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            if (condition == "") {
                /* condition = "CKJ"*/
            }
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/SunPort/QCM/GetAllQCMInfoByCQJ", "QCheckMaRd", "QCheckMaCode", "QCheckMaCode", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click", function () {
        condition = $(".input1").val().trim();
        if (condition == "") {
            /* condition = "CKJ"*/
        }
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/SunPort/QCM/GetAllQCMInfoByCQJ", "QCheckMaRd", "QCheckMaCode", "QCheckMaCode", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        condition = $(".input1").val().trim();
        if (condition == "") {
            /* condition = "CKJ"*/
        }
        currentPage++;
        currentPage = treeSearchs("/SunPort/QCM/GetAllQCMInfoByCQJ", "QCheckMaRd", "QCheckMaCode", "QCheckMaCode", condition, currentPage, config);
    });

    //右边数据显示规则
    var rule = [{
        "ctlid": "WfRd",
        "param": "WfRd"
    }, {
        "ctlid": "WfVerRd",
        "param": "WfVerRd"
    }, {
        "ctlid": "WfName", //自定义名字：标签id名字
        "param": "WfName" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "Version",
        "param": "Version"
    }, {
        "ctlid": "IsDef",
        "param": "IsDef"
    }, {
        "ctlid": "Status",
        "param": "Status"
    }, {
        "ctlid": "creatPeople",
        "param": "Creator"
    }
        , {
            "ctlid": "creatTime",
            "param": "CreateTime"
        }
        , {
            "ctlid": "modifyPeople",
            "param": "LastModifyMan"
        }, {
            "ctlid": "modifyTime",
            "param": "LastModifyTime"
        }, {
            "ctlid": "beizhu",
            "param": "Remark"
        }];


    // 方法：只刷新树
    var loadTree = function () {
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        var trees = [];
        // 调用封装的ajax，传递两个参数（第一个参数是一个json对象，第二个是回调函数）具体看commons.js的request
        request({
            url: '/SunPort/QCM/GetAllQCMInfoByCQJ', data: {
                "ExecType": "00", "PageInfo": JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [{"FieldName": "CreateTime DESC", "FieldOpt": "Order BY"}]
                })
            }
        }, function (Body) {
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
                    id: treeData[i].QCheckMaRd == undefined ? "" : treeData[i].QCheckMaRd,
                    name: treeData[i].QCheckMaCode == undefined ? "" : treeData[i].QCheckMaCode
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        })
    };

    loadTree();

    //树的拖曳方法
    var onExpand = function (node) {
        $.each(node.children, function (key, childnode) {
            $("#" + childnode.id).draggable({
                helper: 'clone',
                scope: 'topo',
                start: function () {
                    //console.log(1);
                }
            });
        });
    };
    var treeSearchs1 = function (url, ids, names, name1, inputValue, cPage, config, FiledList) {

        if (cPage == undefined || cPage == null || cPage == "")
            cPage = 0;
        var retCurrentPage;

        var InitData = {
            "FiledList": [
                {
                    "FieldName": name1,
                    "FieldOpt": "like",
                    "FieldVal": "%" + inputValue + "%"
                }
            ]
        };

        if (FiledList != null && FiledList != undefined && FiledList != "") {
            console.log(FiledList instanceof Array);
            Array.prototype.push.apply(InitData.FiledList, FiledList);
        }

        var currentPage = cPage;
        var PageInfo = {
            "PageSize": 20,
            "PageIndex": currentPage
        };
        //分页处理
        var treedataList = [];

        request({
            url: url,
            data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(PageInfo)}
        }, function (Body) {
            var treeData = Body.Data;
            if (treeData.length <= 0) {
                currentPage--;
                retCurrentPage = currentPage;
                toastr.warning("无更多记录");
                return;
            }
            retCurrentPage = currentPage;

            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i][ids] == undefined ? "" : treeData[i][ids],
                    name: treeData[i][names] == undefined ? "" : treeData[i][names]
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
        return retCurrentPage;
    };
    //右边树点击事件        详情
    var onClicksRight = function (nodeinfo, handle) {

        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"QCheckMaRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };

        // 使用封装的ajax请求后台服务
        request({url: "/SunPort/QCM/GetQCMInfo", data: objData,}, function (Body) {
            fillform("printForm", rule, Body.Data);
            $("#QCheckMaCode").val(Body.Data.QCheckMaCode);
            $("#QCheckMaType").val(Body.Data.QCheckMaType);
            $("#Status").val(Body.Data.Status);
            /*  fillform("factoryinfo", rule, Body.Data);*/
            if (Body.Data.QCMaInfo != null) {
                var aa = Body.Data.OEMLine;
                var a = [];
                for (var i in aa) {
                    a.push(aa[i].LineRd)
                }
                $("#scxb").selectpicker('val', a);
            }

            //加载右边树
            function gg() {
                var specInfoArr = [];

                var treeData = Body.Data.QCMaInfo;
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
                    var specInfo = {
                        "id": treeData[i].QCMaRd,
                        "name": treeData[i].BarCode
                    };
                    specInfoArr.push(specInfo);
                }
                config3.data.source = specInfoArr;
                $.JstreeEx.init(config3);
            }

            gg();


        });

    };


    //=============================top项操作===================

    //关闭
    $("#close").click(function () {
        //window.parent.document.getElementsByClassName("page_tab_close")[0].click();
    });

    $("#addOff").click(function () {

        var msg = "确定要取消吗?";


        if (treeID == null) {
            toastr.warning("请选择左侧要取消的一项再进行取消!");
        } else {
            layer.confirm(msg, {
                btn: ["确认", "取消"]
            }, function () {
                var busData = {
                    "QCheckMaRd": treeID
                };
                saveICH("03", busData);
            })
        }
    });

    //=============================endtop项操作===================

    function saveICH(execType, obj) {
        request({
            url: "/SunPort/QCM/SaveQCMInfo",
            data: {"ExecType": execType, "BusData": JSON.stringify(obj)}
        }, function (Body) {
            layer.closeAll("dialog");
            toastr.success(Body.MsgDes);
            condition = "";
            currentPage = 0;
            var objData = {
                "ExecType": "00",
                "BusData": JSON.stringify(obj)
            };
            loadData(objData);
        });
    }

    //ztree配置
    var setting = {
        check: {
            enable: false//checkbox
        },
        view: {
            nameIsHTML: true, //允许name支持html
            selectedMulti: false
        },
        edit: {
            enable: false,
            editNameSelectAll: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            // 单击事件
            onClick: zTreeOnClick,
            /*        onCheck: zTreeOnCheck,
                    beforeRemove: zTreeBeforeRemove,
                    onRemove: zTreeOnRemove,
                    onRename: zTreeOnRename*/
        }
    };

// 单击事件
    function zTreeOnClick(event, treeId, treeNode) {
        App.blockUI({
            target: "body",
            message: "正在加载中...",
            boxed: true
        });
        var Data = {
            "BarCode": treeNode.name
        };
        $.ajax({
            url: getBasePath() + "/Pack/GetPackDetails",
            type: "POST",
            data: JSON.stringify(Data),
            dataType: "json",
            contentType: "application/json",
            complete: function () {
                App.unblockUI("body");
            },
            success: function (Body) {
                setTimeout("App.unblockUI('body')", 3000);
                if (Body.Body.MsgCode != "0x00000") {
                    toastr.warning(Body.Body.MsgDes);
                } else {
                    $("#xianghao").text(Body.Body.Data.BarCode);
                    $("#barType").text(Body.Body.Data.BarType);
                    $("#gonglv").text(Body.Body.Data.PowerGear);
                    $("#dianliu").text(Body.Body.Data.ELGear);
                    $("#yanse").text(Body.Body.Data.ColorGear);
                    $("#zonghedengji").text(Body.Body.Data.GradeName);
                    $("#ShbCode").text(Body.Body.Data.ShbCode);
                    $("#MixedBag").text(Body.Body.Data.MixedBag);
                    //电池片数据
                    var aa = Body.Body.Data.CellData;
                    if (aa != null) {
                        var tr_ = "";
                        for (var i  in  aa) {
                            tr_ += "<tr><td> " + aa[i].BarCode + "</td><td>" + aa[i].Color + "</td><td>" + aa[i].DanDuoJingName + "</td><td>" + aa[i].Eff + "</td><td>" + aa[i].GongChangName + "</td><td>" + aa[i].Grade + "</td><td>" + aa[i].GradeName + "</td><td>" + aa[i].ZJBlBadName + "</td><td>" + aa[i].ELBlBadName + "</td></tr>";
                        }
                        $("#tbody").empty().html(tr_);
                    } else {
                        $("#tbody").empty();
                    }

                    //IV数据
                    var bb = Body.Body.Data.IVData;
                    $("#num").text(bb.length);
                    if (bb != null) {
                        var tr_ = "";
                        for (var i  in  bb) {
                            tr_ += "<tr><td> " + bb[i].BarCode + "</td><td>" + bb[i].Isc + "</td><td>" + bb[i].Uoc + "</td><td>" + bb[i].Impp + "</td><td>" + bb[i].Umpp + "</td><td>" + bb[i].Pmpp + "</td><td>" + bb[i].FF + "</td></tr>";

                            /*tr_ += "<tr><td> " + bb[i].BarCode + "</td><td>" + bb[i].Eta + "</td><td>" + bb[i].Isc + "</td><td>" + bb[i].Uoc + "</td><td>" + bb[i].Impp + "</td><td>" + bb[i].Umpp + "</td><td>" + bb[i].Pmpp + "</td><td>" + bb[i].FF + "</td><td>" + bb[i].Tcell + "</td><td>" + bb[i].Tmonicell + "</td><td>" + bb[i].Rser + "</td><td>" + bb[i].Rshunt + "</td><td>" + bb[i].Insol + "</td></tr>";*/
                        }
                        $("#tbody1").empty().html(tr_);
                    } else {
                        $("#tbody1").empty();
                    }
                }
            },
            error: function () { //请求数据失败
                toastr.warning("服务器繁忙!");
            }
        });
        /*if (treeNode.name == "" || treeNode.name == null) {
            $("#xianghao").text("");
        } else {
            $("#xianghao").text(treeNode.name);
        }
        if (treeNode.barType == "" || treeNode.barType == null) {
            $("#barType").text("");
        } else {
            $("#barType").text(treeNode.barType);
        }
        if (treeNode.gonglv == "" || treeNode.gonglv == null) {
            $("#gonglv").text("");
        } else {
            $("#gonglv").text(treeNode.gonglv);
        }
        if (treeNode.dianliu == "" || treeNode.dianliu == null) {
            $("#dianliu").text("");
        } else {
            $("#dianliu").text(treeNode.dianliu);
        }
        if (treeNode.yanse == "" || treeNode.yanse == null) {
            $("#yanse").text("");
        } else {
            $("#yanse").text(treeNode.yanse);
        }
        if (treeNode.zonghedengji == "" || treeNode.zonghedengji == null) {
            $("#zonghedengji").text("");
        } else {
            $("#zonghedengji").text(treeNode.zonghedengji);
        }
        if (treeNode.ShbCode == "" || treeNode.ShbCode == null) {
            $("#ShbCode").text("");
        } else {
            $("#ShbCode").text(treeNode.ShbCode);
        }
        if (treeNode.MixedBag == "" || treeNode.MixedBag == null) {
            $("#MixedBag").text("");
        } else {
            $("#MixedBag").text(treeNode.MixedBag);
        }


        //电池片数据
        var aa = treeNode.tbody;
        if (aa != null) {
            var tr_ = "";
            for (var i  in  aa) {
                tr_ += "<tr><td> " + aa[i].BarCode + "</td><td>" + aa[i].Color + "</td><td>" + aa[i].DanDuoJingName + "</td><td>" + aa[i].Eff + "</td><td>" + aa[i].GongChangName + "</td><td>" + aa[i].Grade + "</td><td>" + aa[i].GradeName + "</td><td>" + aa[i].ZJBlBadName + "</td><td>" + aa[i].ELBlBadName + "</td></tr>";
            }
            $("#tbody").empty().html(tr_);
        } else {
            $("#tbody").empty();
        }

        //IV数据
        var bb = treeNode.tbody1;
        $("#num").text(bb.length);
        if (bb != null) {
            var tr_ = "";
            for (var i  in  bb) {
                tr_ += "<tr><td> " + bb[i].BarCode + "</td><td>" + bb[i].Isc + "</td><td>" + bb[i].Uoc + "</td><td>" + bb[i].Impp + "</td><td>" + bb[i].Umpp + "</td><td>" + bb[i].Pmpp + "</td><td>" + bb[i].FF + "</td></tr>";
                /!*tr_ += "<tr><td> " + bb[i].BarCode + "</td><td>" + bb[i].Eta + "</td><td>" + bb[i].Isc + "</td><td>" + bb[i].Uoc + "</td><td>" + bb[i].Impp + "</td><td>" + bb[i].Umpp + "</td><td>" + bb[i].Pmpp + "</td><td>" + bb[i].FF + "</td><td>" + bb[i].Tcell + "</td><td>" + bb[i].Tmonicell + "</td><td>" + bb[i].Rser + "</td><td>" + bb[i].Rshunt + "</td><td>" + bb[i].Insol + "</td></tr>";*!/
            }
            $("#tbody1").empty().html(tr_);
        } else {
            $("#tbody1").empty();
        }*/
    }

    /*    $(document).ready(function(){
            //从服务器读取数据并初始化树形图
            //loadDataFromServer(urlStr);
            //本例直接从模拟数据dataset.js读取
            loadDataFromLocal();//从本地dataset.js文件读取模拟数据并初始化树形图
        });*/

    /**
     * 通过ajax方法从服务器获取数据并初始化树形图
     */
    /*           function loadDataFromServer(urlStr){
     $.ajax({
     type: "get",
     dataType: "json",
     url: urlStr,  //服务请求地址
     success: function(data) {
     initThisZtree(data);//传入数据,初始化ztree
     fuzzySearch('book','#keyword',null,true); //初始化模糊搜索方法
     }
     });
     }*/
    /**
     * 从本地js/dataset.js文件读取模拟数据并初始化树形图
     */

    /*    function loadDataFromLocal(){
            //此处的树节点数据mockData是在本地js/dataSet.js中预先定义的模拟数据
            initThisZtree(mockData);//传入数据,初始化ztree
            // zTreeId ztree对象的id,不需要#
            // searchField 输入框选择器
            // isHighLight 是否高亮,默认高亮,传入false禁用
            // isExpand 是否展开,默认合拢,传入true展开
            fuzzySearch('book','#keyword',null,true); //初始化模糊搜索方法
        }*/

    /**
     * 初始化ztree
     *
     * @param {Object} data
     */
    function initThisZtree(data) {
        //初始化ztree三个参数分别是(jQuery对象,ztree设置,树节点数据)
        var treeObj = $.fn.zTree.init($("#book"), setting, data);
        treeObj.expandAll(true);
    }


    //=================================筛选===================================
    var params = [{
        "caption": "备货单号",
        "name": "StockMaCode",
        "valtype": "00"
    }/*, {
        "caption": "请检单类型",
        "name": "QCheckMaType",
        "valtype": "01",
        "data": "\"00\":\"生产入库请检\"|\"01\":\"销售出库请检\""
    }*/];
    var InitData1 = {};
    var event = {
        onsure: function (result) {
            var FiledList = [];
            for (var i in result) {
                for (var j in result[i]) {
                    if (result[i][j].trim() != "" && result[i][j] != null) {
                        var Filed = {
                            "FieldName": j,
                            "FieldOpt": "like",
                            "FieldVal": "%" + result[i][j] + "%"
                        };
                        FiledList.push(Filed);
                    } else {
                        FiledList = [];
                    }
                }
            }
            var newTree = [];
            InitData1 = {
                FiledList: FiledList
            }
            request({
                url: '/SunPort/QCM/GetAllQCMInfoByCQJ',
                data: {"ExecType": "00", "InitData": JSON.stringify(InitData1)}
            }, function (Body) {
                currentPage = 0;
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20 : treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].QCheckMaRd == undefined ? "" : treeData[i].QCheckMaRd,
                        name: treeData[i].QCheckMaCode == undefined ? "" : treeData[i].QCheckMaCode
                    };
                    newTree.push(tree);
                }
                config.data.source = newTree;
                $.JstreeEx.init(config);//先调用后加载
            })
            delete InitData1.FiledList['QCheckMaCode'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);
//=================================筛选===================================

    /*------------------点击换货显示页面------------------*/
    $(".cSave00").click(function () {
        var status = $("#Status").val();
        if (status != "00") {
            toastr.warning("当前确认单状态不允许进行该操作!");
            return false;
        }
        if (treeID == null) {
            toastr.warning("请选择左侧一项再进行换货操作!");
            return false;
        }
        var barcode = $("#xianghao").text();
        if (barcode == null || barcode == "") {
            toastr.warning("请点击下方页面左侧选择需要被替换掉的箱/组件!");
            return false;
        }
        var barType = $("#barType").text();
        if (barType == "00") {
            $("#hhbarType").val("箱");
        }
        if (barType == "01") {
            $("#hhbarType").val("组件");
        }
        $("#bacth").val(barcode); //当前选择条码
        $("#Sbacth").val(""); //替代条码
        $("#SbarType").val(barType); //条码类型
        $("#hhRemark").val(""); //原因

        //挡位信息
        $("#gonglv1").text($("#gonglv").text());
        $("#dianliu1").text($("#dianliu").text());
        $("#yanse1").text($("#yanse").text());
        $("#zonghedengji1").text($("#zonghedengji").text());
        $("#hunbao").hide();
        $("#gonglv2").text("");

        $("#dianliu2").text("");
        $("#yanse2").text("");
        $("#zonghedengji2").text("");

        $("#myModal").modal("show");
    });

    //换货
    $("#hhsave").click(function () {
        if (treeID == null) {
            toastr.warning("请选择左侧一项再进行换货操作!");
            return false;
        }
        var QCheckMaRd = treeID;

        var BarCode = $("#bacth").val();
        if (BarCode == null || BarCode == "") {
            toastr.warning("请选择箱号/组件！");
            return false;
        }
        var ReplaceBarCode = $("#Sbacth").val();
        if (ReplaceBarCode == null || ReplaceBarCode == "") {
            toastr.warning("请输入替代箱号/组件号！");
            return false;
        }
        if (ReplaceBarCode.length < 5) {
            toastr.warning("请输入正确的替代箱号/组件号！");
            return false;
        }
        var BarType = $("#SbarType").val();
        var ReplaceType = "01";

        var Remark = $("#hhRemark").val();
        if (Remark == null || Remark == "") {
            toastr.warning("请填写换货原因!");
            return false;
        }
        if (Remark.length < 5) {
            toastr.warning("填写的换货原因请描述的详细一点!");
            return false;
        }

        var Data = {
            QCheckMaRd: QCheckMaRd,
            ReplaceType: ReplaceType,
            BarType: BarType,
            BarCode: BarCode,
            ReplaceBarCode: ReplaceBarCode,
            Remark: Remark,
        };
        layer.confirm('确认进行换货操作？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                    url: "/SunPort/MQC/SaveQCheckMa",
                    data: {"ExecType": "00", "BusData": JSON.stringify(Data)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#bacth").val(""); //当前选择条码
                    $("#Sbacth").val(""); //替代条码
                    $("#SbarType").val(""); //条码类型
                    $("#hhRemark").val(""); //原因
                    loadTree();
                });
                layer.closeAll("dialog");
            }
        );

        $("#myModal").modal("hide");
    });

    //============================确认按钮操作==============================
    /*------------------点击确认按钮显示页面------------------*/
    $(".cSave01").click(function () {
        var status = $("#Status").val();
        if (status != "00") {
            toastr.warning("当前确认单状态不允许进行该操作!");
            return false;
        }
        if (treeID == null) {
            toastr.warning("请选择左侧一项再进行确认操作!");
        }
        var QCheckMaCode = $("#QCheckMaCode").val();
        $("#sliname2").val(QCheckMaCode);
        $("#myModal2").modal("show");
    });

    $("#save2").click(function () {

        var FinalResult = "";
        if ($("#hege").is(":checked")) {
            FinalResult = "00";
        }
        if ($("#buhege").is(":checked")) {
            FinalResult = "01";
        }
        if (FinalResult == "") {
            toastr.warning("请对当前出库确定进行判定是否合格!");
            return false;
        }
        if (treeID == null) {
            toastr.warning("请选择左侧一项再进行确定!");
            return false;
        }
        var Remark = $("#beizhuaaaa2").val();
        var Data = {
            QCheckMaRd: treeID,
            FinalResult: FinalResult,
            Remark: Remark
        };

        layer.confirm('一旦确认之后将不能进行第二次确认操作以及换货操作！', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            request({
                url: "/SunPort/MQC/SaveQCheckMa",
                data: {"ExecType": "01", "BusData": JSON.stringify(Data)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                loadTree();
            });
            layer.closeAll("dialog");
        });
        $("#myModal2").modal("hide");
    })

    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if ($("#Sbacth").is(":focus")) {
                var batch = $("#Sbacth").val().trim();
                if (batch != undefined && batch != "") {
                    var Data = {"BarType": $("#barType").text(), "ReplaceBarCode": batch};
                    request({
                        url: "/SunPort/MQC/GetBacthDWInfo",
                        data: {"ExecType": "00", "BusData": JSON.stringify(Data)}
                    }, function (Body) {
                        //挡位信息
                        $("#gonglv2").text(Body.Data.PowerGear);
                        $("#dianliu2").text(Body.Data.ELGear);
                        $("#yanse2").text(Body.Data.ColorGear);
                        $("#zonghedengji2").text(Body.Data.GradeName);
                        $("#ShbCode").text(Body.Data.ShbCode);
                        if (Body.Data.MixedBag != null && Body.Data.MixedBag != "" && Body.Data.MixedBag != "00") {
                            $("#hunbao").show();
                        }
                        toastr.success("条码状态符合，请填写备注完后进行提交");
                    });

                }
            }
        }
    });


    //出货确认单导出
    $("#export").click(function () {
        /*var status = $("#Status").val();
        if (status == "00") {
            toastr.warning("当前确认单状态不允许进行该操作!");
            return false;
        }*/
        if (treeID == null) {
            toastr.warning("请选择左侧一项再进行确认操作!");
        }
        window.location.href = getBasePath() + "/SunPort/MQC/ExportPackingList/" + treeID + "/0";
    });

};

