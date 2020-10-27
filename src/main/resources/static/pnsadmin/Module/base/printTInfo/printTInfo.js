/*-------------------树形的处理-------------------*/
$(function () {

    var  newArr=[];
    /*------------------点击左侧树的时候加载对应右侧表格的信息---------------*/
    var onClicks = function (id, text) {
        $("#_right").show();
        treeID = id.nodeID;
        var objBusData = JSON.stringify({"PtRd": treeID});
        var objData = {
            url: "/PrintT/GetPtInfo",
            data: {
                "ExecType": "00",
                "BusData": objBusData,
            }
        };
        // 使用封装的ajax请求后台服务
        request(objData, function (Body) {
            // 1.处理表单数据
            fillform("printForm", rule, Body.Data);


            var newsData=[];

            if(Body.Data.Customer.OutParams.length>0){
                for(var i  in Body.Data.Customer.OutParams){
                    var newsDatas={
                        "BFRd":"",
                        "BFCode":Body.Data.Customer.OutParams[i].FieldCode,
                        "BFName":Body.Data.Customer.OutParams[i].FieldName,
                        "BFSource":"01",
                    }
                    newsData.push(newsDatas)
                }
            }

            newArr=newsData.concat([]);

            // 2.处理表格
            var obj = {
                data: Body.Data.BFInfo,
                colArr: colNamesArr,
                tableId: tableId,
                divId: divId,
                pageBean: pageBean,
                multiselect: true,
                width:0.84,height:0.64
            };

            if(Body.Data.Customer!=null||Body.Data.Customer!=""){
                $("#jbmc").val(Body.Data.Customer.ScriptName);
                if(Body.Data.Customer.IsScript=="00"){
                    $("#isdefault").prop("checked",true);
                }else{
                    $("#isdefault").prop("checked",false);
                }

                if(Body.Data.Customer.InParams.length>0){
                    var config2 = {
                        tableId: 'list2',
                        data: Body.Data.Customer.InParams,
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.4,
                        height: 0.415
                    };
                    fullTable(config2);//加载空表格
                }else {
                    var config2 = {
                        tableId: 'list2',
                        data: [],
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.4,
                        height: 0.415
                    };
                    fullTable(config2);//加载空表格
                }
                if(Body.Data.Customer.OutParams.length>0){
                    var config3 = {
                        tableId: 'list3',
                        data: Body.Data.Customer.OutParams,
                        colArr: colNamesArr3,
                        multiselect: true,
                        width: 0.35,
                        height: 0.415
                    };
                    fullTable(config3);//加载空表格
                }else{
                    var config3 = {
                        tableId: 'list3',
                        data: [],
                        colArr: colNamesArr3,
                        multiselect: true,
                        width: 0.35,
                        height: 0.415
                    };
                    fullTable(config3);//加载空表格
                }


            }
            fullTable(obj);
            clickSelect();
            select();
            $("#filedown").attr("href", Body.Data.FileName);
        })
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
        currentPage = treeSearchs("/PrintT/GetAllPtInfo","PtRd","PtName","TempName",condition,currentPage,config);
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
        currentPage = treeSearchs("/PrintT/GetAllPtInfo","PtRd","PtName","TempName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/PrintT/GetAllPtInfo","PtRd","PtName","TempName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/PrintT/GetAllPtInfo","PtRd","PtName","TempName",condition,currentPage,config);
    });
    $("#_right").hide();
    var clickSelect = function () {
        var newBFNames=[];
        console.log()
        if(newArr.length>0){
            if(BFNames.length>0){
                for(var j  in BFNames){
                    var newsData1={
                        "BFRd":BFNames[j].BFRd,
                        "BFCode":BFNames[j].BFCode,
                        "BFName":BFNames[j].BFName,
                        "BFSource":"00",
                    }
                    newBFNames.push(newsData1)
                }
                for(var i  in newArr){
                    var newsDatas={
                        "BFRd":newArr[i].BFRd,
                        "BFCode":newArr[i].BFCode,
                        "BFName":newArr[i].BFName,
                        "BFSource":"01",
                    }
                    newBFNames.push(newsDatas)
                }
            }
        }else{
            var aas=[];
                for(var i  in   BFNames){
                        var aa={
                            "BFRd":BFNames[i].BFRd,
                            "BFCode":BFNames[i].BFCode,
                            "BFName":BFNames[i].BFName,
                            "BFSource":"00",
                        }
                    aas.push(aa);
                }

            newBFNames=aas.concat([]);
        }

        $(".BFName").on("click", function () {
            $(this).unbind("click");
            if ($(this).prev().text().trim() == ""){
                $(this).next().text(newBFNames[0].BFCode);
                $(this).next().next().text(newBFNames[0].BFSource);
            }

            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < newBFNames.length; i++) {
                if (current_Td_Val == newBFNames[i].BFName)
                    str += "<option selected>" + newBFNames[i].BFName + "</option>";
                else
                    str += "<option>" + newBFNames[i].BFName + "</option>";
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
                for (var i = 0; i < newBFNames.length; i++) {
                    if (newBFNames[i].BFName == $(this).find("option:selected").text()){
                        $(this).parent().next().text(newBFNames[i].BFCode);
                        $(this).parent().next().next().text(newBFNames[i].BFSource);
                    }
                }
            })
        });




    }
    // 处理文件上传
    $("#file").change(function () {
        var index = $(this).val().lastIndexOf("\\");
        if (index != -1)
            $("#fileName").val($(this).val().substring(index + 1));
        else
            $("#fileName").val($(this).val());
    })
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
    var colNamesArr = [
        {"Caption": "id", "Name": "BFRd", "CType": "text", "Hidden": true},
        {"Caption": "绑定名称", "Name": "BFName", "CType": "text", "Editable": false},
        {"Caption": "绑定代码", "Name": "BFCode"},
        {"Caption": "绑定来源", "Name": "BFSource", "Hidden": true}
    ];
    var rule = [{
        "ctlid": "printModelName", //自定义名字：标签id名字
        "param": "PtName" //规则中自定义的名字：对应报文中的id字段
    }, {
        "ctlid": "fileName",
        "param": "FileName"
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
    var treedataList = [];
    var treeID = null;
    // 首次加载数据
    var newTree=[];

    var colNamesArr2 = [
        {"Caption": "输入参数", "Name": "FieldCode", "CType": "text", "Editable": true,"width":50},
        {"Caption": "参数值来源", "Name": "FieldSource", "CType": "text", "Editable": false,"width":50},
        {"Caption": "参数值", "Name": "Val", "CType": "text", "Editable": true,"width":50}
    ];
    var config2 = {
        tableId: 'list2',
        data: [],
        colArr: colNamesArr2,
        multiselect: true,
        width: 0.4,
        height: 0.415
    };
    config2.data.length = 0;
    fullTable(config2);//加载空表格
    var ItemTypes = [
        {TypeText: "自定义", TypeVal: "01"},
        {TypeText: "系统内置", TypeVal: "00"},

    ];

    /*var ItemTypes1 = [
        {TypeText1: "批次", TypeVal1: "批次"},
        {TypeText1: "物料代码", TypeVal1: "物料代码"},
    ];*/
    var ItemTypes1=[];
    request({url:'/PrintT/GetPtBFInfo',async:true, data: {"ExecType": "00"}},function (Body) {
        if(Body.Data.length>0){
            for(var i  in  Body.Data){
                var ItemType={
                    "TypeText1":Body.Data[i].BFName,
                    "TypeVal1":Body.Data[i].BFCode
                }
                ItemTypes1.push(ItemType);
            }
        }
    });

    $(".add2").click(function(){

        addErow("list2");

        var trObj = $("#list2 tbody>tr:eq(1)");
        trObj.find("td").each(function () {
            if ($(this).attr("class") == "FieldSource") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < ItemTypes.length; i++) {
                    if (current_Td_Val == ItemTypes[i].TypeText1)
                        str += "<option selected value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
                    else
                        str += "<option value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
                }
                ;
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("width", "135px");
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
        });





  /*
*/

/*
        var config2 = {
            tableId: 'list2',
            data: list2Data,
            colArr: colNamesArr2,
            multiselect: true,
            width: 0.36,
            height: 0.415
        };
        fullTable(config2);//加载空表格*/









        /*var trObj = $("#list2 tbody>tr:eq(1)");
        trObj.find("td").each(function () {
            if ($(this).attr("class") == "ItemType") {
                var current_Td_Val = $(this).text();
                var str = "<select>";
                for (var i = 0; i < ItemTypes.length; i++) {
                    if (current_Td_Val == ItemTypes[i].TypeText)
                        str += "<option selected value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
                    else
                        str += "<option value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
                }
                str += "</select>";
                $(this).html(str);
                $(this).find("select").css("border", "0px");
                $(this).find("select").css("height", "19px");
                $(this).find("select").css("color", "#000000");
            }
            if ($(this).attr("class") == "Checked") {
                var current_Td_Val = $(this).text();
                var str = "";
                if (current_Td_Val == "00")
                    str += "<input type='checkbox' name='Checked' checked value='00'/>";
                else
                    str += "<input type='checkbox' name='Checked' value='01'/>";
                $(this).html(str);
                $("input[name='Checked']").on("click", function () {
                    if ($(this).prop("checked"))
                        $(this).val("00");
                    else
                        $(this).val("01");
                });
            }
        });
*/
       /*var trObj = $("#list2 tbody>tr:eq(1)");
        trObj.find("td").each(function () {


        });*/


        select();
    });
    $(".del2").click(function(){
        derow("list2");
    });
    var select = function () {
        $(".FieldSource").on("click", function () {
            $(this).unbind("click");
            if ($(this).find("option:selected").text() == "")
                $(this).next().empty().html("<input type='text' name='Val' role='textbox' class='editable' style='width:100%;'/>");
            var current_Td_Val =$(this).find("option:selected").text();
            var str = "<select>";
            for (var i = 0; i < ItemTypes.length; i++) {
                if (current_Td_Val == ItemTypes[i].TypeVal)
                    str += "<option selected value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
                else
                    str += "<option value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
            }
            str += "</select>";
            $(this).empty().html(str);
            $(this).find("select").css("width", "135px");
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
            $(this).find("select").on("change", function () {
                if($(this).find("option:selected").val()=="00"){
                    var str = "<select style='width:135px;border:0px;height:19px;color:#000000;'>";
                    for (var i = 0; i < ItemTypes1.length; i++) {
                        if (current_Td_Val == ItemTypes1[i].TypeText1)
                            str += "<option selected value='" + ItemTypes1[i].TypeVal1 + "'>" + ItemTypes1[i].TypeText1 + "</option>";
                        else
                            str += "<option value='" + ItemTypes1[i].TypeVal1 + "'>" + ItemTypes1[i].TypeText1 + "</option>";
                    }

                    str += "</select>";
                    $(this).parent().next().empty().html(str);
                }else {
                    // <input type="text" id="1_FieldName"  role="textbox" class="editable" style="width: 100%;">
                    var str1="<input type='text' name='Val' role='textbox' class='editable' style='width:100%;'/>"
                    $(this).parent().next().empty().html(str1);
                }

                /*alert($(this).find("option:selected").text())*/
                /*for (var i = 0; i < ItemTypes.length; i++) {
                    if (ItemTypes[i].TypeText == $(this).find("option:selected").text())
                        $(this).parent().next().text(BFNames[i].BFCode);
                }*/
            })
        });


        /*$(".FieldSource").each(function () {
            var current_Td_Val = $(this).val();
            var str = "<select>";
            for (var i = 0; i < ItemTypes.length; i++) {
                if (current_Td_Val == ItemTypes[i].TypeVal)
                    str += "<option selected value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
                else
                    str += "<option value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
            };
            str += "</select>";
            $(this).html(str);

            $(this).find("select").css("width", "135px");
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
            $("select").on("click", function () {
                if($(this).find("option:selected").val()=="00"){
                    var str = "<select style='width:135px;border:0px;height:19px;color:#000000;'>";
                    for (var i = 0; i < ItemTypes1.length; i++) {
                        if (current_Td_Val == ItemTypes1[i].TypeText1)
                            str += "<option selected value='" + ItemTypes1[i].ItemTypes1 + "'>" + ItemTypes1[i].TypeText1 + "</option>";
                        else
                            str += "<option value='" + ItemTypes1[i].TypeVal1 + "'>" + ItemTypes1[i].TypeText1 + "</option>";
                    }
                    ;
                    str += "</select>";
                    $(this).parent().next().empty().html(str);
                }else {
                    // <input type="text" id="1_FieldName"  role="textbox" class="editable" style="width: 100%;">
                    var str1="<input type='text' name='Val' role='textbox' class='editable' style='width:100%;'/>"
                    $(this).parent().next().empty().html(str1);
                }
            });
        });*/
    }

    var colNamesArr3 = [
        {"Caption": "输出参数", "Name": "FieldCode", "CType": "text", "Editable": true},
        {"Caption": "输出参数别名", "Name": "FieldName", "CType": "text", "Editable": true},
    ];
    var config3 = {
        tableId: 'list3',
        data: [],
        colArr: colNamesArr3,
        multiselect: true,
        width: 0.35,
        height: 0.415
    };
    config3.data.length = 0;
    fullTable(config3);//加载空表格


    $(".add3").click(function(){
        addErow("list3");
    });
    $(".del3").click(function(){
        derow("list3");
    });




    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/PrintT/GetAllPtInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "TempName",
                            "FieldOpt": "Order BY"
                        }]})
            }}, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;

            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].PtRd == undefined ? "" : treeData[i].PtRd,
                    name: treeData[i].PtName == undefined ? "" : treeData[i].PtName
                }
                newTree.push(tree);
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载

            var ptName=$("#printModelName").val();

            for (var i in treeData) {
                if (ptName == treeData[i].PtName) {
                    var ptRd=treeData[i].PtRd;
                    var objBusData = JSON.stringify({"PtRd": ptRd});
                    //锁定更新的节点
                    var objData = {
                        url: "/PrintT/GetPtInfo",
                        data: {
                            "ExecType": "00",
                            "BusData": objBusData,
                        }
                    };
                    // 使用封装的ajax请求后台服务
                    request(objData, function (Body) {
                        // 1.处理表单数据
                        fillform("printForm", rule, Body.Data);
                        // 2.处理表格
                        var obj = {
                            data: Body.Data.BFInfo,
                            colArr: colNamesArr,
                            tableId: tableId,
                            divId: divId,
                            pageBean: pageBean,
                            multiselect: true,
                            width:0.84,height:0.64
                        };
                        fullTable(obj);
                        clickSelect();
                        $("#filedown").attr("href", Body.Data.FileName);
                    });
                }
            }

        });
    };
    loaddata();
    /*-------------------树加载结束------------------*/
    /*------------------------表格的删除方法----------------------------------------------------*/
    $(".del1").click(function () {
        derow(tableId);
    });
    /*------------------------表格的新增操作开始-------------------------*/
    $(".add1").click(function () {
        addErow(tableId);
        clickSelect();
    });
    //----------------------------------------------------------------------------------------------------------------------
    // 顶部菜单的操作
    var formData = {};
    /*---------新增----------*/
    $(".cAdd").click(function () {
        clearForm("printForm");
        $("#_right").show();
        $("#ExecType").val("00");
        var obj = {
            data: null,
            colArr: colNamesArr,
            tableId: tableId,
            divId: divId,
            pageBean: pageBean,
            multiselect: true,
            width:0.84,height:0.64
        }
        treeID = null;
        fullTable(obj);
        clickSelect();
        currentPage=0;
        condition = '';
        newArr=[];
        loaddata();
    });
    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != null) {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url: "/PrintT/SavePtInfo", data: {"ExecType": "01", "BusData": "{\"PtRd\":" + treeID + "}"}}, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#ExecType").val("");
                        $("#_right").hide();
                        currentPage=0;
                        condition = '';
                        loaddata();
                        treeID = null;
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    /*------------------------保存--------------------*/
    var newData = {};
    $(".cSave").click(function () {


        if ($("#printModelName").val().trim() == ""||$("#printModelName").val().trim()==null) {
            //layer.msg("请上传模板文件再上传!");
            toastr.warning("名称不能为空");
            return false;
        }
        if ($("#fileName").val().trim() == ""||$("#fileName").val().trim()==null) {
            /*$("#fileName").css("border-color", "red");
            $("#fileName").prop("placeholder", "不能为空！");*/
            toastr.warning("请上传模板文件再上传");
            return false;
        }
        var IsScript=null;
        if($("#isdefault").prop("checked")){
            IsScript="00";
        }else {
            IsScript="01";
        }
        var ScriptName=$("#jbmc").val().trim();
        var InParams=getTableData("list2");
        var OutParams=getTableData("list3");
        var Customer={
            "IsScript":IsScript,
            "ScriptName":ScriptName,
            "InParams":InParams,
            "OutParams":OutParams
        }


        if ($("#printModelName").val().trim() != "" && $("#fileName").val().trim() != "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("printForm");
            // 获取所有表格的数据，提交到后台
            var bfsinfo = getTableData(tableId);
            if (treeID == null) {
                for (var i = 0; i < bfsinfo.length; i++) {
                    delete bfsinfo[i]["BFRd"]
                }
                newData = {
                    "PtName": formData["PtName"],
                    "FileName": formData["FileName"],
                    "Remark": $("#beizhu").val(),
                    "BFInfo": bfsinfo,
                    "Customer":Customer

                };
                var name = $("#fileName").val();
                if (window.FormData) {
                    var formData = new FormData();
                    formData.append('upload', document.getElementById('file').files[0]);
                    formData.append('ExecType', $("#ExecType").val());
                    formData.append('BusData', JSON.stringify(newData));

                    uploadRequest({url: "/PrintT/SavePtInfo", data: formData}, function (Body) {
                        if(Body.MsgCode=="xxx"){
                            toastr.warning(Body.MsgDes);
                        }else {
                            $("#ExecType").val("");
                            toastr.success(Body.MsgDes);
                            currentPage=0;
                            condition = '';
                            loaddata();
                            loadName($("#printModelName").val().trim());

                        }
                    });

                    /*$.ajax({
                        url: getBasePath() + "/PrintT/SavePtInfo",
                        type: "POST",
                        data: formData,


                        success: function (data) {
                            if(data.Body.MsgCode=="xxx"){
                                toastr.warning(data.Body.MsgDes);
                            }else {
                                $("#ExecType").val("");
                                toastr.success(data.Body.MsgDes);
                                currentPage=0;
                                condition = '';
                                loaddata();
                            }
                        }
                    });*/
                }
            }
            else if (treeID != null) {
                newData = {
                    "PtRd": treeID,
                    "PtName": formData["PtName"],
                    "FileName": formData["FileName"],
                    "Remark": $("#beizhu").val(),
                    "BFInfo": bfsinfo,
                    "Customer":Customer
                };
                if (window.FormData) {
                    var formData = new FormData();
                    formData.append('upload', document.getElementById('file').files[0]);
                    formData.append('ExecType', "02");
                    formData.append('BusData', JSON.stringify(newData));
                    /*$.ajax({
                        url: getBasePath() + "/PrintT/SavePtInfo",
                        type: "POST",
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            if(data.Body.MsgCode=="xxx"){
                                toastr.warning(data.Body.MsgDes);
                            }else {

                            $("#ExecType").val("");
                            treeID = null;
                            toastr.success(data.Body.MsgDes);
                            currentPage=0;
                            condition = '';
                            loaddata();
                            }
                        }
                    });*/
                    uploadRequest({url: "/PrintT/SavePtInfo", data: formData}, function (Body) {
                        if(Body.MsgCode=="xxx"){
                            toastr.warning(Body.MsgDes);
                        }else {
                            $("#ExecType").val("");
                            toastr.success(Body.MsgDes);
                            loaddata();
                            loadName($("#printModelName").val().trim());
                            currentPage=0;
                            condition = '';


                        }
                    });
                }
            }
        }
    });
    $("input").each(function () {
        $(this).blur(function () {
            if ($(this).val().trim() != "") {
                $(this).css("border-color", "");
                $(this).prop("placeholder", "");
            } else {
                $(this).css("border-color", "red");
                $(this).prop("placeholder", "不能为空！");
            }
        });
    });





    function loadName(pram){
        request({
            url: '/PrintT/GetAllPtInfo',
            data: {
                "ExecType": "00",
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "TempName",
                            "FieldOpt": "=",
                            "FieldVal":""+pram+""
                        }]})
            }}, function (Body) {
            var treeData = Body.Data;
            if(treeData.length>0){
                var objBusData = JSON.stringify({"PtRd": Body.Data[0].PtRd});
                var objData = {
                    url: "/PrintT/GetPtInfo",
                    data: {
                        "ExecType": "00",
                        "BusData": objBusData,
                    }
                };
                // 使用封装的ajax请求后台服务
                request(objData, function (Body) {
                    // 2.处理表格
                    var obj = {
                        data: Body.Data.BFInfo,
                        colArr: colNamesArr,
                        tableId: tableId,
                        divId: divId,
                        pageBean: pageBean,
                        multiselect: true,
                        width:0.84,height:0.64
                    };
                    fullTable(obj);

                })
            }else {
                var obj = {
                    data: [],
                    colArr: colNamesArr,
                    tableId: tableId,
                    divId: divId,
                    pageBean: pageBean,
                    multiselect: true,
                    width:0.84,height:0.64
                };
                fullTable(obj);
            }
            $("#atthome").parent().attr("class","active");
            $("#atthome").attr("aria-expanded","true");
            $("#atthome").attr("href","#home");
            $("#home").removeAttr("class");
            $("#home").attr("class","tab-pane fade active in");
            $("#anonthertab").removeAttr("aria-expanded");
            $("#anonthertab").parent().removeAttr("class");
            $("#ios").removeAttr("class");
            $("#ios").attr("class","tab-pane fade");
            if(Body.Data.Customer!=null||Body.Data.Customer!=""){
                $("#jbmc").val(Body.Data.Customer.ScriptName);
                if(Body.Data.Customer.IsScript=="00"){
                    $("#isdefault").prop("checked",true);
                }else{
                    $("#isdefault").prop("checked",false);
                }

                if(Body.Data.Customer.InParams.length>0){
                    var config2 = {
                        tableId: 'list2',
                        data: Body.Data.Customer.InParams,
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.4,
                        height: 0.415
                    };
                    fullTable(config2);//加载空表格
                }else {
                    var config2 = {
                        tableId: 'list2',
                        data: [],
                        colArr: colNamesArr2,
                        multiselect: true,
                        width: 0.4,
                        height: 0.415
                    };
                    fullTable(config2);//加载空表格
                }
                if(Body.Data.Customer.OutParams.length>0){
                    var config3 = {
                        tableId: 'list3',
                        data: Body.Data.Customer.OutParams,
                        colArr: colNamesArr3,
                        multiselect: true,
                        width: 0.35,
                        height: 0.415
                    };
                    fullTable(config3);//加载空表格
                }else {
                    var config3 = {
                        tableId: 'list3',
                        data: [],
                        colArr: colNamesArr3,
                        multiselect: true,
                        width: 0.35,
                        height: 0.415
                    };
                    fullTable(config3);//加载空表格
                }
            }

            /*$("#jbmc").val("");
            $("#isdefault").prop("checked",false);
            var config2 = {
                tableId: 'list2',
                data: [],
                colArr: colNamesArr2,
                multiselect: true,
                width: 0.4,
                height: 0.415
            };
            config2.data.length = 0;
            fullTable(config2);//加载空表格
            var config3 = {
                tableId: 'list3',
                data: [],
                colArr: colNamesArr3,
                multiselect: true,
                width: 0.35,
                height: 0.415
            };
            config3.data.length = 0;
            fullTable(config3);//加载空表格*/


        });
    }

/*$("#anonthertab").click(function(){
    $("#anonthertab").parent().attr("class","active");
    $("#anonthertab").attr("aria-expanded","true");

    $("#atthome").removeAttr("aria-expanded");
    $("#atthome").parent().removeAttr("class");
});

    $("#atthome").click(function(){
        $("#atthome").parent().attr("class","active");
        $("#atthome").attr("aria-expanded","true");

        $("#anonthertab").removeAttr("aria-expanded");
        $("#anonthertab").parent().removeAttr("class");
    });*/



});