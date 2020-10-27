/**
 * Created by xfxi on 2017/7/4.
 */

$(function(){

    /*------------------点击事件的处理----------------*/
    var onClicks = function(nodeinfo, handle) {
        if(!nodeinfo.hasChildren) {  //子节点
            getPdBOM(nodeinfo.nodeID);
            //getPdBOMDI(nodeinfo.nodeID);
        }
    }
    var colNamesArr = [

        {"Caption": "物料料号", "Name": "MaCode","CType": "text", 'Editable': false,Width:100},
        {"Caption": "工作站", "Name": "SpecName","CType": "text", 'Editable': false},
        {"Caption": "数量", "Name": "Num","CType": "text", 'Editable': false},
        {"Caption": "单位", "Name": "UnitName", 'Editable': false,Width:80}
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        width: 0.64,
        height:0.54
    };
    fullTable(config1);//加载空表格









    var maRd = window.sessionStorage.getItem("MaRd");

    //加载左边树

    request({url:"/Material/GetPdReGpInfo", data: {"ExecType": "00","BusData": "{\"MaRd\":" + maRd + "}"}},function (Body) {

        var objData =[];

        objData.push(Body.Data);

        var config = {
            id: "jstree_demo1",
            data: {
                source: objData,
                rule: [{
                    id: "PDFRd",
                    text: "PDFName",
                    children: "PDInfo"
                },{
                    id: "MaRd",
                    text: "MaName",
                    children: "Ver"
                },{
                    id: "MaVerRd",
                    text: "Version"
                }]
            },
            //定义事件获取点击的值
            event: {
                onClick: onClicks
            }
        };

        $.JstreeEx.init(config);
    });



  /*  $.ajax({
        url: getBasePath() + "/Material/GetPdReGpInfo",
        type: "POST",
        data: {
            "ExecType": "00",
            "BusData": "{\"MaRd\":" + maRd + "}"
        },
        success: function(data){

            if(data.Body.MsgCode != "0x00000") {
                return;
            }
            var objData =[];

            objData.push(data.Body.Data);

            var config = {
                id: "jstree_demo1",
                data: {
                    source: objData,
                    rule: [{
                        id: "PDFRd",
                        text: "PDFName",
                        children: "PDInfo"
                    },{
                        id: "MaRd",
                        text: "MaName",
                        children: "Ver"
                    },{
                        id: "MaVerRd",
                        text: "Version"
                    }]
                },
                //定义事件获取点击的值
                event: {
                    onClick: onClicks
                }
            };

            $.JstreeEx.init(config);
        }
    });*/



    //加载物料清单
    function getPdBOM(MaVerRd){
        $("#jstree_demo2").hide();
        $("#ss").hide();
        request({url:"/Material/GetPdBOMInfo",async:true, data: {"ExecType": "00", "BusData": "{\"MaVerRd\":" + MaVerRd + "}"}},function (Body) {
            $("#jstree_demo2").show();
            var config = {
                id: "jstree_demo2",
                data: {
                    source: Body.Data,
                    rule: [{
                        id: "MaVerRd",
                        text: "MaName"
                    }]
                },
                //定义事件获取点击的值
                event: {
                    onClick: onClicks_1
                }
            };

            $.JstreeEx.init(config);
        });


       /* $.ajax({
            url: getBasePath() + "/Material/GetPdBOMInfo",
            type: "POST",
            data: {
                "ExecType": "00",
                "BusData": "{\"MaVerRd\":" + MaVerRd + "}"
            },
            success: function(data){

                if(data.Body.MsgCode != "0x00000") {
                    return;
                }

                var config = {
                    id: "jstree_demo2",
                    data: {
                        source: data.Body.Data,
                        rule: [{
                            id: "MaVerRd",
                            text: "MaName"
                        }]
                    },
                    //定义事件获取点击的值
                    event: {
                        onClick: onClicks_1
                    }
                };

                $.JstreeEx.init(config);
            }
        });*/
    }

    /*------------------点击事件的处理----------------*/
    var onClicks_1 = function(nodeinfo, handle) {
        $("#ss").show();
        if(nodeinfo.isRoot) {  //父节点

            var treeData_1 = [{
                "MaVerRd": nodeinfo.nodeID,
                "Name": "物料清单"
            },{
                "MaVerRd": nodeinfo.nodeID,
                "Name": "替代料"
            }];

            var rule = [{
                id: "MaVerRd",
                text: "Name"
            }];
            handle.ckAddChild(rule, treeData_1);
        }
        else {
            if("物料清单"==nodeinfo.nodeText){
                $("#_right").attr("style","display:block");
                request({url:"/Material/GetPdBOMDIInfo",async:true, data: {"ExecType": "00", "BusData": "{\"MaVerRd\":" + nodeinfo.nodeID + "}"}},function (Body) {
                    var config1 = {
                        tableId: 'list4',
                        data: Body.Data.Details,
                        colArr: colNamesArr,
                        width: 0.64,
                        height:0.54
                    };
                    fullTable(config1);
                    var rule1 = [{
                        id: "MaVerRd",
                        text: "MaName"
                    }];
                    handle.ckAddChild(rule1, Body.Data.List);
                });


               /* $.ajax({
                    url: getBasePath() + "/Material/GetPdBOMDIInfo",
                    type: "POST",
                    data: {
                        "ExecType": "00",
                        "BusData": "{\"MaVerRd\":" + nodeinfo.nodeID + "}"
                    },
                    success: function(data){
                        var config1 = {
                            tableId: 'list4',
                            data: data.Body.Data.Details,
                            colArr: colNamesArr,
                            width: 0.64,
                            height:0.54
                        };
                        fullTable(config1);
                        var rule1 = [{
                            id: "MaVerRd",
                            text: "MaName"
                        }];
                        handle.ckAddChild(rule1, data.Body.Data.List);
                    }
                });*/
            }else {
                return false;
            }


        }

    }

    //获取物料清单明细
    function getPdBOMDI(MaVerRd){

        request({url:"/Material/GetPdBOMDIInfo",async:true, data: {"ExecType": "00",  "BusData": "{\"MaVerRd\":" + MaVerRd + "}"}},function (Body) {

            var config = {
                id: "jstree_demo2",
                data: {
                    source: Body.Data,
                    rule: [{
                        id: "MaVerRd",
                        text: "MaName"
                    }]
                },
                //定义事件获取点击的值
                event: {
                    //onClick: onClicks
                }
            };

            $.JstreeEx.init(config);
        });


        /*$.ajax({
            url: getBasePath() + "/Material/GetPdBOMDIInfo",
            type: "POST",
            data: {
                "ExecType": "00",
                "BusData": "{\"MaVerRd\":" + MaVerRd + "}"
            },
            success: function(data){

                if(data.Body.MsgCode != "0x00000") {
                    alertWarn(data.Body.MsgDes);
                    return;
                }

                var config = {
                    id: "jstree_demo2",
                    data: {
                        source: data.Body.Data,
                        rule: [{
                            id: "MaVerRd",
                            text: "MaName"
                        }]
                    },
                    //定义事件获取点击的值
                    event: {
                        //onClick: onClicks
                    }
                };

                $.JstreeEx.init(config);
            }
        });*/
    }

    //获取物料清单替代料
    function getBOMReMa(BomMaRd){
        request({url:"/Material/GetBOMReMaInfo",async:true, data: {"ExecType": "00", "BusData": "{\"BomMaRd\":" + BomMaRd + "}"}},function (Body) {
            var config = {
                id: "jstree_demo2",
                data: {
                    source: Body.Data,
                    rule: [{
                        id: "MaVerRd",
                        text: "MaName"
                    }]
                },
                //定义事件获取点击的值
                event: {
                    //onClick: onClicks
                }
            };

            $.JstreeEx.init(config);
        });

       /* $.ajax({
            url: getBasePath() + "/Material/GetBOMReMaInfo",
            type: "POST",
            data: {
                "ExecType": "00",
                "BusData": "{\"BomMaRd\":" + BomMaRd + "}"
            },
            success: function(data){

                if(data.Body.MsgCode != "0x00000") {
                    alertWarn(data.Body.MsgDes);
                    return;
                }

                var config = {
                    id: "jstree_demo2",
                    data: {
                        source: data.Body.Data,
                        rule: [{
                            id: "MaVerRd",
                            text: "MaName"
                        }]
                    },
                    //定义事件获取点击的值
                    event: {
                        //onClick: onClicks
                    }
                };

                $.JstreeEx.init(config);
            }
        });*/

    }

});