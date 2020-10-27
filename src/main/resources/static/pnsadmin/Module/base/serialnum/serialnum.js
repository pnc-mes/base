/**
 * Created by liufuzhi on 2017/7/1.
 */

/*-------------------树形的处理-------------------*/
$(function () {
    var data=[];
    var rule = [{
        //名称
        "ctlid": "sNName", //自定义名字：标签id名字
        "param": "SNName" //规则中自定义的名字：对应报文中的id字段
    },{
        "ctlid": "jname",
        "param": "ScriptName",
    }, {
        "ctlid": "prefix",
        "param": "Prefix"
    }, {
        "ctlid": "suffix",
        "param": "Suffix"
    }, {
        "ctlid": "sNLength",
        "param": "SNLength"
    }, {
        "ctlid": "start",
        "param": "Start"
    }, {
        "ctlid": "step",
        "param": "Step"
    }, {
        "ctlid": "lastLevel",
        "param": "LastLevel"
    }, {
        "ctlid": "reset",
        "param": "Reset"
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

    //选中脚本实现
    $("#ck").click(function(){
        if($("#IsScript").is(':checked')){
            if(treeID==null){
                $("#ta1 input").val("");
            }

            $("#ta1").hide();
            $("#ta2").show();
            $("#jb").show()
            var objTable={
                data:data,
                colArr:colNamesArr,
                tableId:"list4",
                multiselect:true,
                width:0.84,
                height:0.528
            };
            fullTable(objTable);


        }else {
            if(treeID==null){
                $("#ta2 input").val("");
            }
            $("#ta1").show();
            $("#ta2").hide();
            var objTable={
                data:[],
                colArr:colNamesArr,
                tableId:"list4",
                multiselect:true,
                width:0.84,
                height:0.528
            };
            fullTable(objTable);
        }
    });
    /*---------首先，定义表单规则，处理表单信息加载右边所有表单相关的信息--------------------*/
    var tableId = "list4";
    var divId = "pager4";
    var pageBean = {
        "page": 1,
        "total": 1,
        "records": 20
    };
    var BFNames = [];
    request({url: "/PrintT/GetPtBFInfo", data: {"ExecType": "00"}}, function (Body) {
        BFNames = Body.Data;
    });
 /*   var colNamesArr = [
        {"Caption": "id", "Name": "BFRd", "CType": "text", "Hidden": true},
        {"Caption": "绑定名称", "Name": "BFName", "CType": "text", "Editable": false},
        {"Caption": "绑定代码", "Name": "BFCode"}

    ];*/
    var tableId = "list4";
    var divId = "pager4";
    var pageBean = {
        "page": 1,
        "total": 1,
        "records": 20
    };
    var BFNames = [];
    request({url: "/PrintT/GetPtBFInfo", data: {"ExecType": "00"}}, function (Body) {
        BFNames = Body.Data;
    });
    var colNamesArr = [
        {"Caption": "绑定名称", "Name": "BFName", "CType": "text", "Editable": false},
        {"Caption": "绑定代码", "Name": "BFCode"}

    ];
        var objTable={
            data:[],
            colArr:colNamesArr,
            tableId:"list4",
            multiselect:true,
            width:0.84,
            height:0.528
        };
        fullTable(objTable);
    var clickSelect = function () {
        $(".BFName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == "")
                $(this).next().text(BFNames[0].BFCode);
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < BFNames.length; i++) {
                if (current_Td_Val == BFNames[i].BFName)
                    str += "<option selected>" + BFNames[i].BFName + "</option>";
                else
                    str += "<option>" + BFNames[i].BFName + "</option>";
            }
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border","0px");
            var str_Width = $(this).css("width");
            var index = str_Width.indexOf("px");
            var width = parseInt(str_Width.substring(0,index))*0.95;
            $(this).find("select").css("width",width);
            $(this).find("select").css("height","19px");
            $(this).find("select").css("color","#000000");
            $(this).find("select").on("change", function () {
                for (var i = 0; i < BFNames.length; i++) {
                    if (BFNames[i].BFName == $(this).find("option:selected").text())
                        $(this).parent().next().text(BFNames[i].BFCode);
                }
            })
        });
    }
    $(".add1").click(function () {
        addErow(tableId);
        clickSelect();
    });
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"SNRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/SN/GetSNInfo", data: objData,},function(Body){

            //去除边框颜色
            $("#sNName").removeAttr("style");
            fillform("serialnum", rule, Body.Data);
            if(Body.Data.IsScript=="00"){
                var chk = document.getElementById('IsScript')
                chk.checked = true;
                $("#ta1 input").val("");
                $("#ta1").hide();
                $("#ta2").show();
                $("#jb").show()
                data=Body.Data.BFInfo;
                var objTable={
                    data:data,
                    colArr:colNamesArr,
                    tableId:"list4",
                    multiselect:true,
                    width:0.84,
                    height:0.528
                };
                fullTable(objTable);
                clickSelect();

            }else {
                data=null;
                $("#ta2 input").val("");
                $("#ta1").show();
                $("#ta2").hide();
                var objTable={
                    data:[],
                    colArr:colNamesArr,
                    tableId:"list4",
                    multiselect:true,
                    width:0.84,
                    height:0.528
                };
            if ("00" == Body.Data.Reset) {
                var chk = document.getElementById('reset')
                chk.checked = true;
            }



            $("#sNLength").val(Body.Data.SNLength);
            $("#start").val(Body.Data.Start);
            $("#step").val(Body.Data.Step);
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
        currentPage = treeSearchs("/SN/GetAllSNInfo","SNRd","SNName","RuleName",condition,currentPage,config);
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
        currentPage = treeSearchs("/SN/GetAllSNInfo","SNRd","SNName","RuleName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/SN/GetAllSNInfo","SNRd","SNName","RuleName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/SN/GetAllSNInfo","SNRd","SNName","RuleName",condition,currentPage,config);
    });


    $("#_right").hide();
    var treeID = null;


    /*-----------------加载树------------*/
    var newTree=[];
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/SN/GetAllSNInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "RuleName",
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
                    id: treeData[i].SNRd == undefined ? "" : treeData[i].SNRd,
                    name: treeData[i].SNName == undefined ? "" : treeData[i].SNName
                }
                newTree.push(tree);
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        })
    };

    loaddata();


    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};
    $(".cAdd").click(function () {
        var objTable={
            data:[],
            colArr:colNamesArr,
            tableId:"list4",
            multiselect:true,
            width:0.84,
            height:0.528
        };
        fullTable(objTable);
        $("#ta1").show();

        $("#ta2").hide();
        data=null;
        clearForm("serialnum");
        $("#ExecType").val("00");
        treeID = null;
        $("#_right").show();


    });

    /*------------------------表格的删除方法----------------------------------------------------*/
    $(".del1").click(function () {
        derow(tableId);
    });
    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/SN/SaveSNInfo", data: {"ExecType": "01", "busData": "{\"SNRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        currentPage=0;
                        condition = '';
                        loaddata();
                        treeID = null;
                        $("#ExecType").val("");
                        $('#_right').hide();
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
            request({url:"/SN/SaveSNInfo",data: {"ExecType": "03", "busData": "{\"SNRd\":" + treeID + "}"}},function(Body){
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
        var BFInfo=[];
        var isScript="";
        var ScriptName="";
        if($("#IsScript").is(':checked')){
            isScript='00';
            ScriptName=$("#jname").val();
            BFInfo = getTableData(tableId);

        }else {
            isScript='01';
        }
        if(isScript=='01'){
            //不起用脚本
            var r = /^\+?[1-9][0-9]*$/;　　//正整数
            var str = $("#sNLength").val().trim();
            var flag=r.test(str);
            if(flag==false){
                toastr.warning("输入有误，流水号长度为正整数！")
                return false;
            }
            var start = $("#start").val().trim();
            var flag1=r.test(start);
            if(flag1==false){
                toastr.warning("输入有误，起始位为正整数！")
                return false;
            }
            var step = $("#step").val().trim();
            var flag2=r.test(step);
            if(flag2==false){
                toastr.warning("输入有误，步长为正整数！")
                return false;
            }


            if ($("#sNName").val().trim() != "") {


                // 获取所有表单数据封装成json对象
                formData = transfer("serialnum");
                var istrue='01';
                if($("#reset").is(':checked')){
                    istrue= "00";
                } else {
                    istrue = "01";
                }
              /*  if ($("input[type='checkbox']").is(':checked') == "checked") {
                    var istrue = "00";
                } else {
                    var istrue = "01";
                }*/
                //新增序号信息
                if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                    newData = {
                        "SNName": formData["sNName"],
                        "IsScript":isScript,
                        "Prefix": formData["prefix"],
                        "Suffix": formData["suffix"],
                        "SNLength": str,
                        "Start": start,
                        "Step": step,
                        "Reset": istrue,
                        "Remark": formData["remark"]
                    };
                    request({url:"/SN/SaveSNInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                        treeID = null;
                        $("#ExecType").val("");
                        toastr.success(Body.MsgDes);
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
                //编辑序号信息
                else if (treeID != null && treeID != "") {

                    newData = {
                        "SNRd": treeID,
                        "SNName": formData["sNName"],
                        "IsScript":isScript,
                        "Prefix": formData["prefix"],
                        "Suffix": formData["suffix"],
                        "SNLength": str,
                        "Start": start,
                        "Step": step,
                        "Reset": istrue,
                        "Remark": formData["remark"]
                    };
                    request({url:"/SN/SaveSNInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                        treeID = null;
                        $("#ExecType").val("");
                        toastr.success(Body.MsgDes);
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
            } else {
                if ($("#sNName").val().trim() == "") {
                    $("#sNName").css("border-color", "red");
                    $("#sNName").prop("placeholder", "不能为空！");
                }
            }
        }else if(isScript=='00') {
            //启用脚本
            if ($("#sNName").val().trim() != ""&&ScriptName!="") {
                // 获取所有表单数据封装成json对象
                formData = transfer("serialnum");

                //新增序号信息
                if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                    newData = {
                        "SNName": formData["sNName"],
                        "IsScript":isScript,
                        "ScriptName":ScriptName,
                        "BFInfo":BFInfo,
                        "Remark": formData["remark"]
                    };
                    request({url:"/SN/SaveSNInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                        treeID = null;
                        $("#ExecType").val("");
                        toastr.success(Body.MsgDes);
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });

                }
                //编辑序号信息
                else if (treeID != null && treeID != "") {
                    newData = {
                        "SNRd": treeID,
                        "SNName": formData["sNName"],
                        "IsScript":isScript,
                        "ScriptName":ScriptName,
                        "BFInfo":BFInfo,
                        "Remark": formData["remark"]
                    };
                    request({url:"/SN/SaveSNInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                        treeID = null;
                        $("#ExecType").val("");
                        toastr.success(Body.MsgDes);
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
            } else {
                if ($("#sNName").val().trim() == "") {
                    $("#sNName").css("border-color", "red");
                    $("#sNName").prop("placeholder", "不能为空！");
                }
                if(ScriptName==""){
                    $("#jname").css("border-color", "red");
                    $("#jname").prop("placeholder", "不能为空！");

                }
            }
        }
    });
});
