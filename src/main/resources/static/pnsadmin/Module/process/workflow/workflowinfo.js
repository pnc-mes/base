/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理JS
 * 创建人：潘俊峰
 * 创建时间：2017-06-27
 * 修改人：
 * 修改时间：
 */
window.onload = function(){
    var treeID=null;
    /*------------------点击事件的处理----------------*/
    var onClicks = function(nodeinfo, handle) {
        treeID=nodeinfo.nodeID;
        if(nodeinfo.isRoot){
            //加载子节点
            var treeData = getWFVerTree(nodeinfo.nodeID);
            var rule = [{
                id: "WfVerRd",
                text: "Version"
            }];
            handle.ckAddChild(rule, treeData);

            //根节点
            getWFVer(nodeinfo.nodeID, "00");
            $("#WfRd").val(nodeinfo.nodeID);
        }else{

            //子节点
            getWFVer(nodeinfo.nodeID, "01");

            $("#WfRd").val("");

            $("#WfVerRd").val(nodeinfo.nodeID);
        }

        $("#_right").show();
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
        currentPage = treeSearchs("/WF/GetAllWfInfo","WfRd","WfName","WFName",condition,currentPage,config);
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
            currentPage = treeSearchs("/WF/GetAllWfInfo","WfRd","WfName","WFName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })





    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/WF/GetAllWfInfo","WfRd","WfName","WFName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/WF/GetAllWfInfo","WfRd","WfName","WFName",condition,currentPage,config);
    });

    var operate = "";

    //右边数据显示规则
    var rule = [{
        "ctlid": "WfRd",
        "param": "WfRd"
    },{
        "ctlid": "WfVerRd",
        "param": "WfVerRd"
    },{
        "ctlid": "WfName", //自定义名字：标签id名字
        "param": "WfName" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "Version",
        "param": "Version"
    },{
        "ctlid": "IsDef",
        "param": "IsDef"
    },{
        "ctlid": "Status",
        "param": "Status"
    },{
        "ctlid": "creatPeople",
        "param": "Creator"
    }
    ,{
        "ctlid": "creatTime",
        "param": "CreateTime"
    }
    ,{
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    },{
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    },{
        "ctlid": "beizhu",
        "param": "Remark"
    }];


    // 方法：只刷新树
    var loadTree = function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var trees = [];
        // 调用封装的ajax，传递两个参数（第一个参数是一个json对象，第二个是回调函数）具体看commons.js的request
        request({url: '/WF/GetAllWfInfo', data: {"ExecType": "00", "PageInfo":JSON.stringify(pageInfo),
            "InitData": JSON.stringify({"FiledList":[{"FieldName":"WFName","FieldOpt":"order by"}]})}
        }, function (Body) {
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
                    id: treeData[i].WfRd == undefined ? "" : treeData[i].WfRd,
                    name: treeData[i].WfName == undefined ? "" : treeData[i].WfName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        })
    };

    loadTree();

    //加载WF版本信息
    function getWFVer(Ruid, ExecType){

        var busData;

        if("00" == ExecType){
            busData = "{\"WfRd\":" + Ruid +"}";
        }else if("01" == ExecType){
            busData = "{\"WfVerRd\":" + Ruid +"}";
        }

        request({url:"/WF/GetWfVInfo",data: {"ExecType": ExecType, "BusData": busData}, async: true},function(Body) {
            fillform("printForm", rule, Body.Data);
            $("#lclx").val(Body.Data.WFType);
            if("00" == Body.Data.IsDef){
                $("#IsDef").prop("checked", true)
                $("#IsDef").attr("disabled", true);
                $("#WfName").removeAttr("readonly");
            }
            else {
                $("#IsDef").prop("checked", false);
                $("#IsDef").attr("disabled", false);
                $("#WfName").attr("readonly","readonly");
            }



            if("00" == Body.Data.Status){
                $('#status_').val("可用");
            }else{
                $('#status_').val("不可用");
            }

            $("#beizhu").text(Body.Data.Remark);

            loadJsplumb(Body.Data.WFJson);

            //记录当前工艺流程版本
            window.sessionStorage.setItem("_WfVerRd", Body.Data.WfVerRd);

            operate = "02";
        });
    }

    //树的拖曳方法
    var onExpand=function(node) {
        $.each(node.children, function (key, childnode) {
            $("#"+childnode.id).draggable({
                helper: 'clone',
                scope: 'topo',
                start: function() {
                    //console.log(1);
                }
            });
        });
    };
    var treeSearchs1 = function (url, ids, names, name1,inputValue, cPage , config, FiledList) {

        if (cPage == undefined || cPage == null || cPage == "")
            cPage = 0;
        var retCurrentPage;

        var InitData = {
            "FiledList": [
                {
                    "FieldName": name1,
                    "FieldOpt": "like",
                    "FieldVal": "%" + inputValue + "%"
                },
                {
                    "FieldName": name1,
                    "FieldOpt": "order by"
                }
            ]
        };

        /* if(config1!=""&&config1!=undefined&&config1!=null){
         InitData.FiledList.push(config1);
         }*/
        if(FiledList != null && FiledList != undefined && FiledList != ""){
            console.log(FiledList instanceof Array);
            Array.prototype.push.apply(InitData.FiledList,FiledList);
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

            var len = treeData.length >= 20 ? 20:treeData.length;
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
    //右边树点击事件
    var onClicksRight = function(nodeinfo, handle){

        if (nodeinfo.isRoot) {  //父节点

            var rule = [{
                id: "SpecVerRd",
                text: "Version"
            }];

            request({url:"/Spec/GetSVTreeInfo",data: {"ExecType": "00", "BusData": "{\"SpecRd\":\"" + nodeinfo.nodeID + "\"}"}},function(Body) {
                handle.ckAddChild(rule, Body.Data);
            });
        }
    };

    var config3 = {
        id: "jstree_demo3",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        //定义事件获取点击的值
        event: {
            onClick: onClicksRight,
            onExpand:onExpand
        }
    };
    var $pages = $('.pages1');

    var condition = "";

    var currentPage = 0;
    var PageInfo = {
        "PageSize":20,
        "PageIndex":currentPage
    };

    $(".searchTree2").click(function () {
        condition = $(".input2").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs1("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config3);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }
    });

    $(".input2").keydown(function(event) {
        if (event.keyCode == 13) {
            condition = $(".input2").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs1("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config3);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })





    $("#prev1").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs1("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config3);
        }
    });
    $("#next1").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs1("/Spec/GetAllSpecInfo","SpecRd","SpecName","SpecName",condition,currentPage,config3);
    });



    //加载右边树
    function  gg() {

        var specInfoArr=[];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };


        request({url:"/Spec/GetAllSpecInfo",data: {
                "ExecType": "00"
                ,"PageInfo":JSON.stringify(pageInfo)
                ,"InitData":JSON.stringify({
                    "FiledList": [{"FieldName": "SpecName", "FieldOpt": "Order BY"}]})
            }},function (Body) {
            if (Body.MsgCode == "0x00000") {
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
                    var specInfo = {
                        "id": treeData[i].SpecRd,
                        "name": treeData[i].SpecName
                    };
                    specInfoArr.push(specInfo);
                }
                config3.data.source = specInfoArr;
                $.JstreeEx.init(config3);
            }
        });


       /* $.ajax({
            url: getBasePath() + '/Spec/GetAllSpecInfo',
            type: 'POST',
            data: {
                "ExecType": "00"
                ,"PageInfo":JSON.stringify(pageInfo)
                ,"InitData":JSON.stringify({
                    "FiledList": [{"FieldName": "SpecName", "FieldOpt": "Order BY"}]})
            },
            success: function (data) {
                if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                    var treeData = data.Body.Data;
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
                        var specInfo = {
                            "id": treeData[i].SpecRd,
                            "name": treeData[i].SpecName
                        };
                        specInfoArr.push(specInfo);
                    }
                    config3.data.source = specInfoArr;
                    $.JstreeEx.init(config3);
                }
            }
        })*/



     /*   request({url:"/Spec/GetAllSpecInfo",data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo)}},function(data) {
            alert()
            if (data.Status == "00" && data.Body.MsgCode == "0x00000") {
                var treeData = data.Body.Data;
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
                    var specInfo = {
                        "id": treeData[i].SpecRd,
                        "name": treeData[i].SpecName
                    };
                    specInfoArr.push(specInfo);
                }
                alert(JSON.stringify(specInfoArr))
                config3.data.source = specInfoArr;
                $.JstreeEx.init(config3);
            }



         /!*   var config = {
                id: "jstree_demo3",
                data: {
                    source: _data,
                    rule: [{
                        id: "SpecRd",
                        text: "SpecName"
                    }]
                },
                //定义事件获取点击的值
                event: {
                    onClick: onClicksRight,
                    onExpand:onExpand
                }
            };*!/

           /!* $.JstreeEx.init(config);*!/
        });
*/
    }
    gg();

    //保存
    $("#save").click(function() {

        if(operate == ""){
            toastr.warning("请选择左侧要操作的一项再进行操作!");
            return;
        }
        if($("#lclx").val()==""||$("#lclx").val()==null){
            toastr.warning("保存失败，流程类型不能为空");
            return;
        }
        var jsplumbdata = save_Data();

        if(jsplumbdata == undefined || jsplumbdata == ""){
            return;
        }

        var wfinfodata = eval(jsplumbdata.split("~")[1]);

        var wfinfo = [];

        console.log(wfinfodata);

        for(var i=0; i<wfinfodata.length; i++){

            var condition = eval(wfinfodata[i].blockTarget);

            var ospec = [];
            var rspec = [];

            if(Array.isArray(condition)) {
                for (var j = 0; j < condition.length; j++) {

                    if (condition[j].WfCirRd != undefined && condition[j].WfCirRd != "" && condition[j].Expression != undefined) {

                        if (condition[j].RouteType == "可选") { //可选

                            ospec.push({
                                OSpecVerRd: condition[j].WfCirRd,
                                Expression: condition[j].Expression
                            });

                        } else {  //返工

                            rspec.push({
                                RSpecVerRd: condition[j].WfCirRd,
                                Expression: condition[j].Expression
                            });

                        }
                    }

                }
            }

            wfinfo.push({
                SpecVerRd: wfinfodata[i].blockSpecVerRd,
                OSpec: ospec,
                RSpec: rspec,
                NSpecVerRd: wfinfodata[i].blockNextSpecVerRd,
                SeSpecType: wfinfodata[i].blockSeSpecType
            });

        }

        console.log(wfinfo);

        if(wfinfo.length <= 0){
            toastr.warning("工序流程不能为空");
            return;
        }

        var execType = operate;//$("#ExecType").val();

        // 获取所有表单数据封装成json对象
        formData = transfer("printForm");

        //是否默认 00默认
        var v = "01";
        if($("#IsDef").is(":checked")){
            v = "00";
        }

        //Status 00启用
        var s = "01";
        /*if($('.selectControl').prop('selectedIndex') == 0){
            s = "00";
        }*/
        if($("#status_").val() == "可用"){
            s = "00";
        }

        if("00" == execType){  //新增

            var newData = {
                "WfName": formData["WfName"],
                "Version": formData["Version"],
                "IsDef": v,
                "WFJson": save_Data(),
                "WFType":$("#lclx").val(),
                "Status": s,
                "Remark": $("#beizhu").val(),
                "WfInfo": wfinfo
            };

            saveWF("00", JSON.stringify(newData));

        }else if("02" == execType){  //编辑

            if($("#WfVerRd").val() == "" || $("#WfRd").val() == ""){
                toastr.warning("请选择左侧要操作的一项再进行操作!");
                return;
            }

            var newData = {
                "WfRd": formData["WfRd"],
                "WfVerRd": formData["WfVerRd"],
                "WfName": formData["WfName"],
                "Version": formData["Version"],
                "WFType":$("#lclx").val(),
                "IsDef": v,
                "WFJson": save_Data(),
                "Status": s,
                "Remark": $("#beizhu").val(),
                "WfInfo": wfinfo
            };

            saveWF("02", JSON.stringify(newData));

        }else if("04" == execType){  //新增版本

            var newData = {
                "WfRd": formData["WfRd"],
                "WfName": formData["WfName"],
                "Version": formData["Version"],
                "WFType":$("#lclx").val(),
                "IsDef": v,
                "WFJson": save_Data(),
                "Status": s,
                "Remark": $("#beizhu").val(),
                "WfInfo": wfinfo
            };

            saveWF("04", JSON.stringify(newData));
        }

    });

    //新增
    $("#add").click(function(){

        $("#_right").show();

        emptyJsplumb();

        clearForm("printForm");
        $("#IsDef").prop("checked",true);
        $('.selectControl').prop('selectedIndex', 0);

        operate = "00";
        $("#IsDef").attr("disabled", false);
        $("#WfName").removeAttr("readonly");
        $("#Version").val("1");
        $("#status_").val("可用");
        $("#lclx").val("00");
    });

    //删除
    $("#remove").click(function() {
        var id = $("#WfRd").val();


        if(id == "" || id == undefined){
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }else  if(id!=treeID){
            toastr.warning("删除只针对整个工艺流程,若要删除工艺版本,请选择版本删除!");
            return;
        }else {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    var data = "{\"WfRd\":" + id + "}";
                    saveWF("01", data);
                    layer.closeAll("dialog");
                    $("#WfRd").val("");
                    $("#_right").hide();
                }
            );
        }
    });

    //复制
    $("#copy").click(function() {
        var id = $("#WfRd").val();

        if(id == "" || id == undefined){
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }else {
            var data = "{\"WfRd\":" + id + "}";
            saveWF("03", data);
            $("#WfRd").val("");
        }
    });

    //TODO 新增版本
    $("#addVersion").click(function() {
        if($("#WfVerRd").val() == "" || operate == ""){
            toastr.warning("请选择左侧要操作的一项再进行操作!");
            return;
        }else{
            operate = "04";
            $("#IsDef").attr("disabled", false);
            //$("#WfName").removeAttr("readonly");

            $("#IsDef").attr("checked", false);
            $("#WfName").attr("readonly", true);
            $("#Version").val("");
            $("#lclx").val("00");
        }
    });

    //删除版本
    $("#rmVersion").click(function() {
        var id = $("#WfVerRd").val();

        if(id == "" || id == undefined){
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }else{

            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    var data = "{\"WfVerRd\":" + id + "}";
                    saveWF("05", data);
                    layer.closeAll("dialog");
                    $("#WfVerRd").val("");
                    $("#_right").hide();
                }
            );
        }
    });

    //关闭
    $("#close").click(function(){
        //window.parent.document.getElementsByClassName("page_tab_close")[0].click();
    });

    //保存工艺流程
    function saveWF(ExecType, Data){

        request({url:"/WF/SaveWfInfo",data: {"ExecType": ExecType, "BusData": Data}},function(Body) {
            toastr.success(Body.MsgDes);
            if(Body.MsgCode == "0x00000"){
                condition = "";
                currentPage = 0;
                loadTree();
                operate = "";
                $("#WfRd").val("");
                $("#WfVerRd").val("");
            }
        });
    }

    //获取流程版本列表信息
    function getWFVerTree(WfRd) {
        var data;

        request({url:"/WF/GetWfVTreeInfo",data: {"ExecType": "00", "BusData": "{\"WfRd\":\"" + WfRd + "\"}"}},function(Body) {
            data = Body.Data;
        });

        return data;
    }

};

