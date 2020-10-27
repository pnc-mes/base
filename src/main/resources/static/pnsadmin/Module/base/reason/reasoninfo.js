/**
 * Created by liufuzhi on 2017/7/1.
 */

/*-------------------树形的处理-------------------*/
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"ReaRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/Reason/GetRealInfo", data: objData},function(Body){
            fillform("resoninfo", rule, Body.Data);

            $("#defaultSelect").showData({
                id:Body.Data.ReaTInfo.ReaTID,
                name:Body.Data.ReaTInfo.ReaTDes,
                keyfield:"ReaTID",
                fields:[
                    {"name":"ReaTID"},
                    {"name":"ReaTDes"}
                ]
            });
        });
    };
    /*----------------------定义控件规则-------------------*/
    var config = {
        id: "jstree_demo1",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
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
        currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config);
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
        currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Reason/GetAllReaInfo","ReaRd","ReaCode","ReaCode",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //原因代码
        "ctlid": "reaCode", //自定义名字：标签id名字
        "param": "ReaCode" //规则中自定义的名字：对应报文中的id字段
    }, {
        //原因描述
        "ctlid": "reaDes",
        "param": "ReaDes"
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

    //下拉框
    var params = {
        "displaymode": "0",
        "title": "选择原因类型",
        "binddata": {
            "keyfield": "ReaTID",
            "fields": [
                {
                    "caption": "原因类别",
                    "name": "ReaTID"
                }, {
                    "caption": "原因类别描述",
                    "name": "ReaTDes"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {

                var xldata = [];
                //加载下拉框原因类型信息
                request({url: "/Reason/GetReaTypeInfo", data: {"ExecType": "00"}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "ReaTID": datas[i].ReaType,
                            "ReaTDes": datas[i].ReaTDes,
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
    /*-----------------加载树------------*/
    var newTree=[];
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/Reason/GetAllReaInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "ReaCode",
                            "FieldOpt": "Order BY"
                        }]})
            }},function(Body){
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].ReaRd == undefined ? "" : treeData[i].ReaRd,
                    name: treeData[i].ReaCode == undefined ? "" : treeData[i].ReaCode
                }
                newTree.push(tree);
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();

    /*--------------点击新增按钮顶部菜单----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("resoninfo");
        $("#defaultSelect").clearseldata("ReaTID");
        $("#ExecType").val("00");
        treeID = null;
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Reason/SaveReaInfo", data: {"ExecType": "01", "busData": "{\"ReaRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        $("#_right").hide();
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------复制按钮顶部菜单操作中----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            request({url:"/Reason/SaveReaInfo", data: {"ExecType": "03", "busData": "{\"ReaRd\":" + treeID + "}"}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                loaddata();
                treeID = null;
            })
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------点击保存按顶部菜单钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        if($("#reaCode").val().trim()==""||$("#reaCode").val().trim()==null){
            toastr.warning("名称不能为空");
            return false;
        }
        if($("#reaDes").val().trim()==""||$("#reaDes").val().trim()==null){
            toastr.warning("原因描述不能为空");
            return false;
        }
        var ReaTID = $("#defaultSelect").getseldata().ReaTID;
        if(ReaTID==""||ReaTID==null||ReaTID<=0){
            toastr.warning("原因类型不能为空");
            return false;
        }

        // 获取所有表单数据封装成json对象
        formData = transfer("resoninfo");



        if ($("#reaCode").val().trim() != "" && $("#reaDes").val().trim() != "") {
            //新增原因代码信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "ReaCode": formData["reaCode"],
                    "ReaDes": formData["reaDes"],
                    "ReaTID": ReaTID==""?"17":ReaTID,
                    "Remark": formData["remark"]
                };
                request({url:"/Reason/SaveReaInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    currentPage=0;
                    condition = '';
                    loaddata();
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val('');
                    treeID = null;
                });
            }
            //编辑原因代码
            else if (treeID != null && treeID != "") {
                newData = {
                    "ReaRd": treeID,
                    "ReaCode": formData["reaCode"],
                    "ReaDes": formData["reaDes"],
                    "ReaTID": ReaTID==""?"17":ReaTID,
                    "Remark": formData["remark"]
                };
                request({url:"/Reason/SaveReaInfo",data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    currentPage=0;
                    condition = '';
                    loaddata();
                    toastr.success(Body.MsgDes);
                    treeID = null;
                    $("#ExecType").val('');
                });
            }
        } /*else {
            //   处理原因代码的情况
            if ($("#reaCode").val().trim() == "") {
                $("#reaCode").css("border-color", "red").prop("placeholder", "不能为空！");
            }
            //    处理原因描述的情况
            if ($("#reaDes").val().trim() == '') {
                $("#reaDes").css("border-color", "red").prop('placeholder', '不能为空！')
            }
          /!*  //    处理原因类型的情况
            if (ReaTID == "") {
                $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect").find("input:eq(0)").val("");
                $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
            }*!/
        }*/
    });
});
