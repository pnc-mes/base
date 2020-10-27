/**
 * Created by liufuzhi on 2017/7/5.
 */

/*-------------------树形的处理-------------------*/
$(function () {
    var treeID = null;
    var selectID=null;
    $("#_right").hide();
    /*------------------获取点击之后一个节点的数据------------------*/

    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;

        var objBusData = JSON.stringify({"DevGRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/DeviceG/GetDevGInfo", data: objData}, function (Body) {
            fillform("devGpForm", rule, Body.Data);

            var data= Body.Data.DevInfo;
            // 处理表格
            var config= {
                tableId: "list4",
                data: data,
                colArr: colNamesArr,
                multiselect: true,
                width:0.84,
                height:0.55

            };
            fullTable(config);
        });
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
        currentPage = treeSearchs("/DeviceG/GetAllDevGInfo","DevGRd","DevGpName","DevGpName",condition,currentPage,config);
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
            currentPage = treeSearchs("/DeviceG/GetAllDevGInfo","DevGRd","DevGpName","DevGpName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/DeviceG/GetAllDevGInfo","DevGRd","DevGpName","DevGpName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/DeviceG/GetAllDevGInfo","DevGRd","DevGpName","DevGpName",condition,currentPage,config);
    });



    //获取所有设备名称
    var DevNames = [];
    request({url:"/Device/GetAllDevInfo", data: {"ExecType": "00"}},function(Body){
        for (var i in Body.Data) {
            var DevName = {
                "DevRd": Body.Data[i].DevRd,
                "DevName": Body.Data[i].DevName
            }
            DevNames.push(DevName);
        };
    });

    var colNamesArr=[
        {"Caption":"id", "Name":"DevRd", "IsKey": true, "Hidden":true},
        {"Caption": "设备名称", "Name": "DevInfo", "CType": "select", "Editable": true,
            "SelectPr":{
                "Data": DevNames,
                "DisplayName": "DevName",
                "DataValue": "DevRd"
            }}
    ];

    var rule = [{
        //设备组名称
        "ctlid": "devGpName", //自定义名字：标签id名字
        "param": "DevGpName" //规则中自定义的名字：对应报文中的id字段
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

    //只刷新树
    var loadtree = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"DevGpName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var trees = [];
        request({url: '/DeviceG/GetAllDevGInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}}, function (Body) {
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
                    id: treeData[i].DevGRd == undefined ? "" : treeData[i].DevGRd,
                    name: treeData[i].DevGpName == undefined ? "" : treeData[i].DevGpName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadtree();
    /*--------------点击新增按钮顶部菜单----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("devGpForm");//清空表单中的数据然后对其新增数据
        treeID = null;//防止点击了树的节点之后，再点新增
        $("#ExecType").val("00");
        var config={
            tableId: "list4",
            data: [],
            colArr:colNamesArr,
            multiselect:true,
            width:0.84,
            height:0.55
            
        };

        fullTable(config);
    });

    /*****************表格的新增**************************/
    $(".add1").click(function () {
        addErow("list4");
    })

    /*****************表格的删除**************************/
    $(".del1").click(function () {
        derow("list4");
    })
    /***************************************************/

    /*---------顶端操作中的删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID!= "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/DeviceG/SaveDevGpInfo",
                        data: {"ExecType": "01", "busData": "{\"DevGRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        treeID = null;
                        condition = "";
                        currentPage = 0;
                        $("#ExecType").val("");
                        loadtree();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID!= "") {
            request({url:"/DeviceG/SaveDevGpInfo", data: {"ExecType": "03", "busData": "{\"DevGRd\":" + treeID + "}"}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                treeID = null;
                condition = "";
                currentPage = 0;
                loadtree();
            });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------点击保存按钮顶部菜单----------*/
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("devGpForm");

        var bfsinfo = getRowData("list4");

        var devrd=true;
        for (var i = 0; i < bfsinfo.length; i++) {
            delete bfsinfo[i]["DevName"]
            if(bfsinfo[i].DevRd !="" && bfsinfo[i].DevRd !=undefined){
                devrd=false;
            }
        }

        if(devrd){
            toastr.warning("请新增设备，再保存");
            return;
        }

        if ($("#devGpName").val().trim() != "") {

            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "DevGpName": formData["devGpName"],
                    "DevInfo": bfsinfo,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/DeviceG/SaveDevGpInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val('');
                    condition = "";
                    currentPage = 0;
                    treeID = null;
                    loadtree();
                });
            }
            else if (treeID!="" && treeID!=null) {

                newData = {
                    "DevGRd": treeID,
                    "DevGpName": formData["devGpName"],
                    "DevInfo": bfsinfo,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/DeviceG/SaveDevGpInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    condition = "";
                    currentPage = 0;
                    treeID = null;
                    loadtree();
                });
            }
        } else {
            $("#devGpName").css("border-color", "red");
            $("#devGpName").prop("placeholder", "不能为空！");
        }
    });
});