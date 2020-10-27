/**
 * Created by liufuzhi on 2017/7/1.
 */
/*-------------------树形的处理-------------------*/
$(function () {
    $(".right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "pdfName", //自定义名字：标签id名字
        "param": "PdfName" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "sNName",
        "param": "SRName"
    },{
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

    //获取序号规则
    var params_sr = {
        "displaymode": "0",
        "binddata": {
            "keyfield": "SNRd",
            "fields": [
                {
                    "caption": "序号ID",
                    "name": "SNRd"
                }, {
                    "caption": "序号名称",
                    "name": "SNName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RuleName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName":"RuleName",
                            "FieldOpt":"Order BY",
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/SN/GetAllSNInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SNRd": datas[i].SNRd,
                            "SNName": datas[i].SNName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#defaultSelect").zc_select(params_sr);

    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo,handle) {
        $(".right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"PdfRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({
            url: "/PF/GetPdfInfo",
            data: objData},
            function (Body) {
                //去除边框颜色
                $("#pdfName").removeAttr("style");
                fillform("prodfamily", rule,Body.Data);

                if(Body.Data.PtRd!=null&&Body.Data.PtName!=null){
                    $("#wlbq").showData({
                        id:Body.Data.PtRd == null ? "":Body.Data.PtRd,
                        name:Body.Data.PtName == null ? "":Body.Data.PtName,
                        keyfield:"PtRd",
                        fields:[
                            {"name":"PtRd"},
                            {"name":"PtName"}
                        ]
                    });
                }

                $("#defaultSelect").showData({
                    id:Body.Data == null ? "":Body.Data.SRRd,
                    name:Body.Data == null ? "":Body.Data.SRName,
                    keyfield:"SNRd",
                    fields:[
                        {"name":"SNRd"},
                        {"name":"SNName"}
                    ]
                });
            }
        );
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
        currentPage = treeSearchs("/PF/GetAllPdfInfo","PdfRd","PdfName","FamilyName",condition,currentPage,config);
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
            currentPage = treeSearchs("/PF/GetAllPdfInfo","PdfRd","PdfName","FamilyName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })




    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/PF/GetAllPdfInfo","PdfRd","PdfName","FamilyName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/PF/GetAllPdfInfo","PdfRd","PdfName","FamilyName",condition,currentPage,config);
    });


    //只刷新树的方法
    var loadtree=function(){
        var trees=[];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        var InitData = {
            "FiledList":[
                {
                    "FieldName":"FamilyName",
                    "FieldOpt":"Order BY"
                }

            ]
        }

        request({
            url:'/PF/GetAllPdfInfo',
            data: {
                "ExecType": "00",
                "InitData":JSON.stringify(InitData),
                "PageInfo":JSON.stringify(pageInfo)
            }},
            function (Body) {
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
                            id: treeData[i].PdfRd == undefined ? "" : treeData[i].PdfRd,
                            name: treeData[i].PdfName == undefined ? "" : treeData[i].PdfName
                        }
                        trees.push(tree);
                    }

                config.data.source = trees;
                    $.JstreeEx.init(config);//先调用后加载
            }
        );
    };
    loadtree();


    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $(".right").show();
        clearForm("prodfamily");
        $("#defaultSelect").clearseldata("SNRd");
        $("#wlbq").clearseldata("PtRd");
        $("#ExecType").val("00");
        treeID = null;
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url:"/PF/SavePdfInfo",
                        data: {
                            "ExecType": "01",
                            "busData": "{\"PdfRd\":" + treeID + "}"
                        }},
                        function (Body) {
                            layer.closeAll("dialog");
                            toastr.success(Body.MsgDes);
                            treeID = null;
                            $("#ExecType").val("");
                            condition = "";
                            currentPage = 0;
                            loadtree();
                            $(".right").hide();
                        }
                    );
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            request({
                url:"/PF/SavePdfInfo",
                data: {
                    "ExecType": "03",
                    "busData": "{\"PdfRd\":" + treeID + "}"
                }},
                function (Body) {
                    toastr.success(Body.MsgDes);
                    treeID = null;
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loadtree();
                }
            );
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    //加载物料标签 即模板
    var wlbq={
        "displaymode": "0",
        "binddata": {
            "keyfield": "PtRd",
            "fields": [
                {
                    "caption": "模板ID",
                    "name": "PtRd"
                }, {
                    "caption": "模板名称",
                    "name": "PtName"
                }
            ]
        },
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"TempName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName":"TempName",
                            "FieldOpt":"order by"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/PrintT/GetAllPtInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PtRd": datas[i].PtRd,
                            "PtName": datas[i].PtName
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };
    $("#wlbq").zc_select(wlbq);

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {

        if($("#pdfName").val().trim()!="") {
            // 获取所有表单数据封装成json对象
            formData = transfer("prodfamily");

            var SRRd = $("#defaultSelect").getseldata().SNRd;

            //新增产品族信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "PdfName": formData["pdfName"],
                    "SRRd": SRRd,
                    "Remark": formData["remark"],
                    "PtRd":$("#wlbq").getseldata().PtRd == undefined ? "":$("#wlbq").getseldata().PtRd,
                };
                request({
                    url: "/PF/SavePdfInfo",
                    data: {
                        "ExecType": $("#ExecType").val(),
                        "busData": JSON.stringify(newData)
                    }},
                    function (Body) {
                        condition = "";
                        currentPage = 0;
                        loadtree();
                        $("#ExecType").val("");
                        toastr.success(Body.MsgDes);
                    }
                );
            }
            //编辑产品族信息
            else if (treeID != null && treeID != "") {
                newData = {
                    "PdfRd": treeID,
                    "PdfName": formData["pdfName"],
                    "SRRd": SRRd,
                    "Remark": formData["remark"],
                    "PtRd":$("#wlbq").getseldata().PtRd == undefined ? "":$("#wlbq").getseldata().PtRd,
                };
                request({
                    url:"/PF/SavePdfInfo",
                    data: {
                        "ExecType": "02",
                        "busData": JSON.stringify(newData)
                    }},
                    function (Body) {
                        condition = "";
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage = 0;
                        loadtree();
                        toastr.success(Body.MsgDes);
                    }
                );
            }
        }else {
            if($("#pdfName").val().trim() == ""){
                readOnly("prodfamily", false);
                $("#pdfName").css("border-color","red");
                $("#pdfName").prop("placeholder","不能为空！");
            }
        }
    });
});
