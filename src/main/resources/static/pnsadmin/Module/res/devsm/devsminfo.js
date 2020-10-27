/**
 * Created by test on 2017/8/21.
 */
$(function(){
    var treeID = null;
    $("#_right").hide();
    var rule = [{
        //设备组名称
        "ctlid": "modelName", //自定义名字：标签id名字
        "param": "ModelName" //规则中自定义的名字：对应报文中的id字段
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

    //表格的定义
    var SouDSInfos = [];
    var TarDSInfos = [];
    request({url:"/DevState/GetAllDevSInfo", data: {"ExecType": "00"}},function(Body){
        for(var i in Body.Data){
            var SouDSInfo = {
                "SouDSRd" : Body.Data[i].DevSRd,
                "DevSName" : Body.Data[i].DevSName
            }
            SouDSInfos.push(SouDSInfo);
        }
        console.log(SouDSInfos);
        for(var j in Body.Data){
            var TarDSInfo = {
                "TarDSRd" : Body.Data[j].DevSRd,
                "DevSName" : Body.Data[j].DevSName
            }
            TarDSInfos.push(TarDSInfo);
        }
        console.log(TarDSInfos);
    });
    var colNamesArr = [
        {"Caption": "DSMRd", "Name": "DSMRd", "Hidden": true},
        {"Caption": "源状态", "Name": "SouDSRd", 'CType':"select",'Editable': true,"SelectPr":{"Data":SouDSInfos,"DisplayName":"DevSName","DataValue":"SouDSRd"}},
        {"Caption": "目标状态", "Name": "TarDSRd", 'CType':"select",'Editable': true,"SelectPr":{"Data":TarDSInfos,"DisplayName":"DevSName","DataValue":"TarDSRd"}}
    ];


    /*------------------获取点击之后一个节点的数据------------------*/

    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;

        var objBusData = JSON.stringify({"DSMRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/DevSM/GetDevSMInfo", data: objData}, function (Body) {
           $("#modelName").val( Body.Data.ModelName);
            fillform("devsmForm", rule, Body.Data);
            $("#jyffSelect").showData({
                id:Body.Data.DSInfo.DevSRd,
                name:Body.Data.DSInfo.DevSName,
                keyfield:"DevSRd",
                fields:[
                    {"name":"DevSRd"},
                    {"name":"DevSName"}
                ]
            });
            var datas=Body.Data.DevSMInfo;
            var datas1 =[];
            for(var i = 0;i<datas.length;i++){
                var data = {
                    "SouDSRd": {
                        "DevSRd":  datas[i].SouDSInfo.DevSRd,
                        "DevSName": datas[i].SouDSInfo.DevSName
                    },
                    "TarDSRd": {
                        "DevSRd":  datas[i].TarDSInfo.DevSRd,
                        "DevSName": datas[i].TarDSInfo.DevSName
                    }
                }
                datas1.push(data);
            }

            var config={
                tableId:"list4",
                data: datas1,
                colArr:colNamesArr,
                multiselect:true,
                width:0.84,height:0.55
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
        currentPage = treeSearchs("/DevSM/GetAllDevSMInfo","DSMRd","ModelName","ModelName",condition,currentPage,config);
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
            currentPage = treeSearchs("/DevSM/GetAllDevSMInfo","DSMRd","ModelName","ModelName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/DevSM/GetAllDevSMInfo","DSMRd","ModelName","ModelName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/DevSM/GetAllDevSMInfo","DSMRd","ModelName","ModelName",condition,currentPage,config);
    });

    /*方法：首次加载页面------------*/
    //下拉框
    var params = {
        "displaymode": "0",
        "title": "状态",
        "binddata": {
            "keyfield": "DevSRd",
            "fields": [
                {
                    "caption": "状态id",
                    "name": "DevSRd"
                }, {
                    "caption": "状态名称",
                    "name": "DevSName"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName": "DevSName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //加载下拉框企业信息
                request({url: "/DevState/GetAllDevSInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevSRd": datas[i].DevSRd,
                            "DevSName": datas[i].DevSName,
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
    };

    $("#jyffSelect").zc_select(params);


    //只刷新树
    var loadtree = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"ModelName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var trees = [];
        request({url: '/DevSM/GetAllDevSMInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData)},"PageInfo":JSON.stringify(pageInfo)}, function (Body) {
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
                    id: treeData[i].DSMRd == undefined ? "" : treeData[i].DSMRd,
                    name: treeData[i].ModelName == undefined ? "" : treeData[i].ModelName
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
        clearForm("devsmForm");//清空表单中的数据然后对其新增数据
        treeID = null;//防止点击了树的节点之后，再点新增
        $("#jyffSelect").clearseldata("DevSRd");
        $("#ExecType").val("00");
        var config={
            tableId:"list4",
            data:[],
            colArr:colNamesArr,
            multiselect:true,
            width:0.84,height:0.55
        };
        fullTable(config);
    });

    /*---------顶端操作中的删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID!= "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/DevSM/SaveDevSMInfo",
                        data: {"ExecType": "01", "busData": "{\"DSMRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        treeID = null;
                        $("#ExecType").val("");
                        condition = "";
                        currentPage = 0;
                        loadtree();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    var newData = {};
    //保存
    $('.cSave').on('click',function(){
        formData = transfer("devsmForm");
        var DevSRd =  $("#jyffSelect").getseldata().DevSRd;
        //编辑
        var bfsinfo = getRowData("list4");

        var devrd=true;
        for(var i = 0 ; i < bfsinfo.length; i++){
            delete bfsinfo[i].DevSName
            delete bfsinfo[i].DSMRd
           if(bfsinfo[i].SouDSRd !="" && bfsinfo[i].SouDSRd !=undefined && bfsinfo[i].TarDSRd !="" && bfsinfo[i].TarDSRd !=undefined){
                devrd=false;
            }
        }

        if(devrd){
            toastr.warning("请新增设备状态模型，再保存");
            return;
        }
        if ($("#modelName").val().trim() != "") {
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {

                newData = {
                    "ModelName": formData["modelName"],
                    "DevSRd": DevSRd,
                    "DevSMInfo": bfsinfo
                };

                request({
                    url: "/DevSM/SaveDevSMInfo",
                    data: {"ExecType": "00", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val('');
                    condition = "";
                    currentPage = 0;
                    loadtree();
                })
            }else if(treeID!="" && treeID!=null){
                newData = {
                    "DSMRd": treeID,
                    "ModelName": formData["modelName"],
                    "DevSRd": DevSRd,
                    "DevSMInfo": bfsinfo
                };

                request({
                    url: "/DevSM/SaveDevSMInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val('');
                    condition = "";
                    currentPage = 0;
                    loadtree();
                })
            }
        }else{
            $("#modelName").css("border-color", "red");
            $("#modelName").prop("placeholder", "不能为空！");
        }
    })

    //新增
    $('.add1').on('click',function(){
        addErow("list4");
    })

    //删除
    $('.del1').on('click',function(){
        derow("list4");

    })


})