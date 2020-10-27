/**
 * Created by HAOZAN on 2018/6/8.
 */
/*-------------------树形的处理-------------------*/
$(function () {


    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开

        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"LineRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };

        // 使用封装的ajax请求后台服务
        request({url: "/Line/GetLineInfo", data: objData,}, function (Body) {


            fillform("factoryinfo", rule, Body.Data);
            if(Body.Data.OEMLine!=null){
                var aa =Body.Data.OEMLine;
                var a=[];
                for(var i in aa){
                    a.push(aa[i].LineRd)
                }
                $("#scxb").selectpicker('val', a);
            }

            if(Body.Data.Factory!=null){
                $("#defaultSelect").showData({
                    id:Body.Data.Factory.FaRd,
                    name:Body.Data.Factory.FaName,

                    keyfield:"FaRd",
                    fields:[
                        {"name":"FaRd"},
                        {"name":"FaName"}
                    ]
                });
            }


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
        currentPage = treeSearchs("/Line/GetAllLineInfo","LineRd","LineName","LineName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Line/GetAllLineInfo","LineRd","LineName","LineName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Line/GetLineInfo","LineRd","LineName","LineName",condition,currentPage,config);
        }
    });

    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Line/GetLineInfo","LineRd","LineName","LineName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //线体名称
        "ctlid": "liName", //自定义名字：标签id名字
        "param": "LineName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //线体代码
        "ctlid":"lineCode",
        "param":"LineCode",
    },{
        //线别描述
        "ctlid": "lineDes",
        "param": "LineDes"
    }, {
        //线产能
        "ctlid": "Linecapacity",
        "param": "Linecapacity"
    }, {
        //是否代工
        "ctlid": "IsOEM",
        "param": "IsOEM"
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

    //下拉框
    var params = {
        "displaymode": "0",
        "title": "选择工厂",
        "binddata": {
            "keyfield": "FaRd",
            "fields": [
                {
                    "caption": "工厂id",
                    "name": "FaRd"
                }, {
                    "caption": "工厂名称",
                    "name": "FaName"
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
                            "FieldName":"FactoryName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "FactoryName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //加载下拉框工厂信息
                request({url: "/Factory/GetAllFaInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "FaRd": datas[i].FaRd,
                            "FaName": datas[i].FaName,
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

    $("#defaultSelect").zc_select(params);


    //加载   树
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        request({
            url: '/Line/GetAllLineInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "LineName",
                            "FieldOpt": "Order BY"
                        }]})
            }}, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20:treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].LineRd == undefined ? "" : treeData[i].LineRd,
                    name: treeData[i].LineName == undefined ? "" : treeData[i].LineName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载


        });
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

  /*  var LineInfoSources=$("#scxb").val();

    var LineInfo=[];
    for(var  obj in LineInfoSources){
        var LineInfos={
            "LineRd":LineInfoSources[obj],
        }
        LineInfo.push(LineInfos);
    }*/

    $(".cAdd").click(function () {
        $("#scxb").selectpicker('val',"");

        clearForm("factoryinfo");
        $("#ExecType").val("00");
        $("#defaultSelect").clearseldata("FaRd");
        treeID = null;
        $("#_right").show();
    });

    /*--------------顶部菜单点击编辑按钮----------*/
    $(".cEdit").click(function () {

        var LineInfoSources=$("#scxb").val();

        var LineInfo=[];
        for(var  obj in LineInfoSources){
            var LineInfos={
                "LineRd":LineInfoSources[obj],
            }
            LineInfo.push(LineInfos);
        }
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            $("#ExecType").val("02");
            $("#cpName").attr("disabled", false);
        } else {
            toastr.warning("请选择左侧要编辑的一项再进行编辑!");
        }

    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/Line/SaveLineInfo",
                        data: {"ExecType": "01", "busData": "{\"LineRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage=0;
                        condition = '';
                        loaddata();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            request({
                url: "/Line/SaveLineInfo",
                data: {"ExecType": "03", "busData": "{\"LineRd\":" + treeID + "}"}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                treeID = null;
                currentPage=0;
                condition = '';
                loaddata();
            });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        /////////////////////

        // 获取所有表单数据封装成json对象
        formData = transfer("factoryinfo");

        var FaRd =  $("#defaultSelect").getseldata().FaRd;

        if ($("#liName").val() != "" ) {
            //新增线体信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                ////////////////
                var LineInfoSources=$("#scxb").val();

                var LineInfo=[];
                for(var  obj in LineInfoSources){
                    var LineInfos={
                        "LineRd":LineInfoSources[obj],
                    }
                    LineInfo.push(LineInfos);
                }
                    newData = {
                        "FaRd": FaRd,
                        "LineCode": formData["lineCode"],
                        "LineName": formData["liName"],
                        "LineDes": formData["lineDes"],
                        "Remark": formData["remark"],
                        "OEMLine":LineInfo
                    };

                request({
                    url: "/Line/SaveLineInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
            //更新工厂信息
            else if (treeID != null && treeID != "") {
                var LineInfoSources=$("#scxb").val();

                var LineInfo=[];
                for(var  obj in LineInfoSources){
                    var LineInfos={
                        "LineRd":LineInfoSources[obj],
                    }
                    LineInfo.push(LineInfos);
                }
                newData = {
                    "LineRd": treeID,
                    "LineCode": formData["lineCode"],
                    "LineName": formData["liName"],
                    "FaRd": FaRd,
                    "LineDes": formData["lineDes"],
                    "Remark": formData["remark"],
                    "OEMLine":LineInfo
                };

                request({
                    url: "/Line/SaveLineInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
        } else {
            if ($("#liName").val() == "") {
                $("#liName").css("border-color", "red");
                $("#liName").prop("placeholder", "不能为空！");
                toastr.warning("保存失败，线体名称不能为空");
                return false;
            }
            if (FaRd == "") {
                $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect").find("input:eq(0)").val("");
                $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
                toastr.warning("保存失败，工厂不能为空");
                return false;
            }

        }
    });


    //加载线体
    request({url:"/Line/GetAllLineInfo", data: {"ExecType": "00"}},function(Body){
        var data=Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].LineRd+'>'+data[i].LineName +'</option>';
        }
        $("#scxb").html(aa);
    });





});
