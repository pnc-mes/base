$(function() {
    var treeID = null;
    var childrenID = null;
    var treeText = null;
    var treedataList = [];
    var rules = [{
        "ctlid": "LabelID", //自定义名字：标签id名字
        "param": "LabelID" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "LabelDes", //自定义名字：标签id名字
        "param": "LabelDes" //规则中自定义的名字：对应报文中的id字段
    },{
        "ctlid": "Version",
        "param": "Version"
    }, {
        "ctlid": "creatPeople",
        "param": "Creator"
    }, {
        "ctlid": "creatTime",
        "param": "CreateTime"
    }, {
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    }, {
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    }, {
        "ctlid": "beizhu",
        "param": "Remark"
    }];
    var onClicks = function (id, handle) {
        $("#_right").show();
        if (id.isRoot) {
            treeID = id.nodeID;
            var objBusData = JSON.stringify({"LanCode": id.nodeID});
            var objData = {
                "ExecType": "00",
                "BusData": objBusData
            };
            //加载属于该下面的孩子信息
            request({url:'/Dic/GetAllDicInfo', data: objData},function (Body) {
                var rule = [{
                    id: "DicRd",
                    text: "LabelID"
                }];
                for(var i in Body.Data){
                    delete Body.Data[i].LabelDes;
                }
                handle.ckAddChild(rule, Body.Data);
                request({url:'/Dic/GetDicInfo', data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify({"DicRd":Body.Data[0].DicRd})
                }},function (Body) {
                    fillform('dicForm',rules,Body.Data);
                });
            });
        }
        else {
            childrenID = id.nodeID;
            var objData = {
                "ExecType": "00",
                "BusData": JSON.stringify({"DicRd":childrenID})
            };
            request({url:'/Dic/GetDicInfo', data: objData},function (Body) {
                fillform('dicForm',rules,Body.Data);
            });
        }
    };
    //加载页面
    var loadPage = function () {
        var config = {
            id: "jstree_demo1",
            data: {
                source: treedataList,
                rule: [{
                    id: "id",
                    text: "name"
                }]
            }
        };
        request({url:'/Dic/GetDicLanType', data: {"ExecType": "00"}},function (Body) {
            treedataList = [];
            for (var i = 0; i < Body.Data.length; i++) {
                var tree = {
                    id: Body.Data[i].LanCode == undefined ? "" : Body.Data[i].LanCode,
                    name: Body.Data[i].LanName == undefined ? "" : Body.Data[i].LanName + "(" + Body.Data[i].Count + ")"
                };
                treedataList.push(tree);
            }
            var config = {
                id: "jstree_demo1",
                data: {
                    source: treedataList,
                    rule: [{
                        id: "id",
                        text: "name"
                    }]
                },
                event: {
                    onClick: onClicks
                }
            };
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadPage();
    /*//处理筛选
    $.ajax({
        url: getBasePath() +'/static/pnsadmin/Module/process/material/1.json',
        type: 'get',
        success: function (params) {
            $('.cSelect').zc_filter(params, function (res) {
                console.log(res);
            });
        }
    });*/

    //仅仅刷新树的定义
    var refreshTree = function(){
        request({url:"/Dic/GetDicLanType", data: {
                "ExecType": "00"
            }},function(Body){
            if (Body.MsgCode == "0x00000") {
                treedataList = [];
                var treeData = Body.Data;
                for (var i = 0; i < treeData.length; i++) {
                    var tree = {
                        id: treeData[i].LanCode == undefined ? "" : treeData[i].LanCode,
                        name: treeData[i].LanName == undefined ? "" : treeData[i].LanName + "(" + treeData[i].Count + ")"
                    };
                    treedataList.push(tree);
                }
                var config = {
                    id: "jstree_demo1",
                    data: {
                        source: treedataList,
                        rule: [{
                            id: "id",
                            text: "name"
                        }]
                    }
                };
                $.JstreeEx.init(config);//先调用后加载
            }
        });


       /* $.ajax({
            url: getBasePath() + '/Dic/GetDicLanType',
            type: 'post',
            data: {
                "ExecType": "00"
            },
            success: function (res) {
                if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                    treedataList = [];
                    var treeData = res.Body.Data;
                    for (var i = 0; i < treeData.length; i++) {
                        var tree = {
                            id: treeData[i].LanCode == undefined ? "" : treeData[i].LanCode,
                            name: treeData[i].LanName == undefined ? "" : treeData[i].LanName + "(" + treeData[i].Count + ")"
                        };
                        treedataList.push(tree);
                    }
                    var config = {
                        id: "jstree_demo1",
                        data: {
                            source: treedataList,
                            rule: [{
                                id: "id",
                                text: "name"
                            }]
                        }
                    };
                    $.JstreeEx.init(config);//先调用后加载
                }
            }
        });*/
    };
    /*********************处理新增*****************************************/
    $('.cAdd').on('click', function () {
        clearForm('dicForm');
        $('#ExecType').val('00');
    });
    /*********************处理删除*****************************************/
    $('.cDel').on('click', function () {
        if (childrenID == null || childrenID == "") {
            toastr.warning("请选择左侧要删除的标签ID再进行删除!");
        }
        else {
            layer.confirm('确定要删除吗?', {
                btn: ["确认", "取消"]
            }, function () {
                var busData = [{
                    "DicRd": childrenID
                }];
                request({url:"/Dic/SaveDicInfo", data: {"ExecType": "01", "BusData": JSON.stringify(busData)}},function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    clearForm('dicForm');
                    childrenID = null;
                    $("#_right").hide();
                    loadPage();
                });
            })
        }
    });
    //保存
    $('.cSave').on('click',function () {
        if($('#LabelID').val().trim()==""){
            toastr.warning("保存失败，标签ID不允许为空!");
            return false;
        }
        if($('#LabelDes').val().trim()==""){
            toastr.warning("保存失败，标签描述不允许为空!");
            return false;
        }
        //新增保存
        if($('#ExecType').val() == '00' && treeID != null){
            var busData = {
                "LanCode": treeID,
                "LabelID": $('#LabelID').val(),
                "LabelDes": $('#LabelDes').val(),
                "Remark": $('#beizhu').val()
            };
            request({url:"/Dic/SaveDicInfo", data: {"ExecType": "00", "BusData": JSON.stringify(busData)}},function (Body) {
                toastr.success(Body.MsgDes);
                //refreshTree();
                loadPage();
                $('#ExecType').val('');
            });
        }else if(childrenID != null){
            var busData = {
                "DicRd": childrenID,
                "LabelID": $('#LabelID').val(),
                "LabelDes": $('#LabelDes').val(),
                "Remark": $('#beizhu').val()
            };
            request({url:"/Dic/SaveDicInfo", data: {"ExecType": "02", "BusData": JSON.stringify(busData)}},function (Body) {
                toastr.success(Body.MsgDes);
                childrenID = null;
                //refreshTree();
                loadPage();
                $('#ExecType').val('');
            });
        }
    });
    /*request({url:"/Dic/SaveDicInfo",async:true, data: {"ExecType": "01", "BusData": JSON.stringify(busData)}},function (Body) {

        toastr.success(Body.MsgDes);
        childrenID = null;
        $("#_right").hide();
        refreshTree();
    });*/

});
