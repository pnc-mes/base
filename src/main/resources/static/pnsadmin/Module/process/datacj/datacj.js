/**
 * Created by test on 2017/6/29.
 */
//@ sourceURL=test.js
$(function () {

    //到期邮件通知组
    var expireemailgroup = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailDisRd",
            "fields": [
                {
                    "caption": "EmailDisRd",
                    "name": "EmailDisRd"
                }, {
                    "caption": "过期名称",
                    "name": "EmailDisName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DistributionName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DistributionName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/EmailDis/GetAllEmailDisInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailDisRd": datas[i].EmailDisRd,
                            "EmailDisName": datas[i].EmailDisName,
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
    $("#expiremailgroup").zc_select(expireemailgroup);

    //提醒(邮件内容)
    var expireemailgroupcontent = {
        "displaymode": "0",
        "title": "email",
        "binddata": {
            "keyfield": "EmailRd",
            "fields": [
                {
                    "caption": "EmailRd",
                    "name": "EmailRd"
                }, {
                    "caption": "内容名称",
                    "name": "EmailName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"EmailName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "EmailName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Email/GetAllEmailInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "EmailRd": datas[i].EmailRd,
                            "EmailName": datas[i].EmailName,
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

    $("#expiremailcontent").zc_select(expireemailgroupcontent);


    //默认表头为空
    var tableId = "list4";
    var divId = "pager4";
    var pageBean = {
        "page": 1,
        "total": 1,
        "records": 20
    };
    var colNamesArr = [
        {"Caption": "id", "Name": "ItemRd", "CType": "text", "Hidden": true},
        {"Caption": "采集内容", "Name": "ItemName", "Editable": true, "CType": "text","Width":120},
        {"Caption": "数据别名", "Name": "DataAlias", "Editable": true, "CType": "text","Width":120},
        {"Caption": "数据类型", "Name": "ItemType", "CType": "text", "Editable": false,"Width":80},
        {"Caption": "标准值", "Name": "SValue", "Editable": true, "CType": "text","Width":90},
        {"Caption": "上浮值", "Name": "UpLimit", "Editable": true, "CType": "text","Width":80},
        {"Caption": "下浮值", "Name": "DwLimit", "Editable": true, "CType": "text","Width":80},
        {"Caption": "默认值", "Name": "DefValue", "Editable": true,  "CType": "text","Width":90},
        {"Caption": "是否检查", "Name": "Checked", "Editable": false, "CType": "text","Width":70},
        {"Caption": "不良行为", "Name": "Action", "Editable": false, "CType": "text"}
    ];

    var treeID = null;

    var treeID1=null;
    var map=new Map();

    var rule = [{
        "ctlid": "DCName", //自定义名字：标签id名字
        "param": "DCName" //规则中自定义的名字：对应报文中的id字段
    }, {
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
    //树的点击事件
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        treeID1=null;
        if (nodeinfo.isRoot) {
            $("#datacjForm").show();
            $("#DCName").attr("readonly",false);
            //判断是否可以新增版本
            $("#hidden").attr("hi", "00");
            $("#remove").attr("b", treeID);
            $(".cEdit").attr("c", treeID);
            $("#addver").attr("d", treeID);
            $(".cEdit").attr("e", "00");
            $("#recopy").attr("re", treeID);
            //加载属于该下面的孩子信息
            var newData = {
                "DcRd": treeID
            };
            request({url:"/DC/GetDVTreeInfo", async: true, data: {"ExecType": "00", "BusData": JSON.stringify(newData)}}, function (Body) {
                var data = Body.Data;
                var rule = [{
                    id: "DCVerRd",
                    text: "Version"
                }];
                handle.ckAddChild(rule, data);
                //点击加载右边填充数据
                var busData = {
                    "DcRd": treeID
                };
                request({url:"/DC/GetDcInfo",async:true, data: {"ExecType": "00", "busData": JSON.stringify(busData)}},function (Body) {
                    $("#Version").val(Body.Data.Version)
                    $("#DCName").val(Body.Data.DCName)
                    $("#creatPeople").val(Body.Data.Creator)
                    $("#creatTime").val(Body.Data.CreateTime)
                    $("#modifyPeople").val(Body.Data.LastModifyMan)
                    $("#modifyTime").val(Body.Data.LastModifyTime)
                    $("#beizhu").val(Body.Data.Remark)
                    $("#hidden").val(Body.Data.DCVerRd)


                    if(Body.Data.EmailDistributionInfo!=null){
                        $("#expiremailgroup").showData({
                            id:Body.Data.EmailDistributionInfo.EmailDistributionRd,
                            name:Body.Data.EmailDistributionInfo.EmailDistributionName,
                            keyfield:"EmailDisRd",
                            fields:[
                                {"name":"EmailDistributionRd"},
                                {"name":"EmailDistributionName"}
                            ]
                        });
                    }else {
                        $("#expiremailgroup").clearseldata("EmailDisRd");
                    }
                    if(Body.Data.EmailMessageInfo!=null){
                        $("#expiremailcontent").showData({
                            id:Body.Data.EmailMessageInfo.EmailMessageRd,
                            name:Body.Data.EmailMessageInfo.EmailMessageName,
                            keyfield:"EmailRd",
                            fields:[
                                {"name":"EmailMessageRd"},
                                {"name":"EmailMessageName"}
                            ]
                        });
                    }else {
                        $("#expiremailcontent").clearseldata("EmailRd");
                    }


                    if ("00" == Body.Data.IsDef) {
                        $("#IsDef").prop("checked", true);
                        $("#IsDef").prop("disabled", true);
                        $("#DCName").prop("disabled", false)
                    } else {
                        $("#IsDef").prop("checked", false);
                        $("#IsDef").prop("disabled", false);
                        $("#DCName").prop("disabled", true)
                    }
                    $("#Status").find("option").each(function () {
                        if($(this).val() == Body.Data.Status){
                            $(this).prop("selected",true);
                            $(this).siblings().prop("selected",false);
                            return false;
                        }
                    });
                    $("#hidden").attr("editdj", "00");
                    $("#hidden").attr("editid", Body.Data.DcRd);
                    $("#hidden").attr("editvid", Body.Data.DCVerRd);

                    var d = Body.Data.DCItemsInfo;// d：表示数据采集的表格的数据
                    for (var i = 0; i < d.length; i++) {
                        d[i].ItemType = d[i].ItemType.TypeText;
                    }
                    var obj = {
                        data: d,
                        colArr: colNamesArr,
                        tableId: tableId,
                        divId: divId,
                        pageBean: pageBean,
                        multiselect: true,
                        width:0.84,
                        height:0.528
                    };
                    fullTable(obj);
                    select();
                    select1();

                    checkbox();
                });
            });
        }
        else {
            $(".cEdit").attr("e", "01");
            $("#removeVer").attr("rm", treeID);

            var newData1 = {
                "DCVerRd": treeID
            };
            request({url: "/DC/GetDVInfo",async:true, data: {"ExecType": "00", "busData": JSON.stringify(newData1)}},function (Body) {
                $("#Version").val(Body.Data.Version);
                $("#DCName").val(Body.Data.DCName);
                $("#creatPeople").val(Body.Data.Creator);
                $("#creatTime").val(Body.Data.CreateTime);
                $("#modifyPeople").val(Body.Data.LastModifyMan);
                $("#modifyTime").val(Body.Data.LastModifyTime);
                $("#beizhu").val(Body.Data.Remark);

                $("#hidden").val(Body.Data.DCVerRd);
                if ("00" == Body.Data.IsDef) {
                    $("#IsDef").prop("checked", true);
                    $("#IsDef").prop("disabled", true);
                    $("#DCName").prop("readonly", false)
                } else {
                    $("#IsDef").prop("checked", false);
                    $("#IsDef").prop("disabled", false);
                    $("#DCName").prop("readonly", true)
                }
                $("#Status").find("option").each(function () {
                    if($(this).val() == Body.Data.Status){
                        $(this).prop("selected",true);
                        $(this).siblings().prop("selected",false);
                        return false;
                    }
                });

                if(Body.Data.EmailDistributionInfo!=null){
                    $("#expiremailgroup").showData({
                        id:Body.Data.EmailDistributionInfo.EmailDistributionRd,
                        name:Body.Data.EmailDistributionInfo.EmailDistributionName,
                        keyfield:"EmailDisRd",
                        fields:[
                            {"name":"EmailDistributionRd"},
                            {"name":"EmailDistributionName"}
                        ]
                    });
                }else {
                    $("#expiremailgroup").clearseldata("EmailDisRd");
                }
                if(Body.Data.EmailMessageInfo!=null){
                    $("#expiremailcontent").showData({
                        id:Body.Data.EmailMessageInfo.EmailMessageRd,
                        name:Body.Data.EmailMessageInfo.EmailMessageName,
                        keyfield:"EmailRd",
                        fields:[
                            {"name":"EmailMessageRd"},
                            {"name":"EmailMessageName"}
                        ]
                    });
                }else {
                    $("#expiremailcontent").clearseldata("EmailRd");
                }

                $("#hidden").attr("editdj", "00");
                $("#hidden").attr("editid", Body.Data.DcRd);
                $("#hidden").attr("editvid", Body.Data.DCVerRd);

                var d = Body.Data.DCItemsInfo;// d：表示数据采集的表格的数据
                for (var i = 0; i < d.length; i++) {
                    d[i].ItemType = d[i].ItemType.TypeText;
                }
                var obj = {
                    data: d,
                    colArr: colNamesArr,
                    tableId: tableId,
                    divId: divId,
                    pageBean: pageBean,
                    multiselect: true,
                    width:0.84,
                    height:0.528
                }
                fullTable(obj);
                select();
                select1();
                checkbox();
            });
        }
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
        currentPage = treeSearchs("/DC/GetAllDcInfo","DcRd","DcName","DCName",condition,currentPage,config);
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
            currentPage = treeSearchs("/DC/GetAllDcInfo","DcRd","DcName","DCName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/DC/GetAllDcInfo","DcRd","DcName","DCName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/DC/GetAllDcInfo","DcRd","DcName","DCName",condition,currentPage,config);
    });


    // 定义表格中下拉框“数据类型”的数组
    var ItemTypes = [
        {TypeText: "数值类型", TypeVal: "00"},
        {TypeText: "浮点类型", TypeVal: "01"},
        {TypeText: "字符串类型", TypeVal: "02"}
    ];
    // 定义表格中下拉框的“数据类型”的事件
    var select = function () {
        $(".ItemType").each(function () {
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < ItemTypes.length; i++) {
                if (current_Td_Val == ItemTypes[i].TypeText)
                    str += "<option selected value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
                else
                    str += "<option value='" + ItemTypes[i].TypeVal + "'>" + ItemTypes[i].TypeText + "</option>";
            }
            ;
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
        });
    }
    // 定义表格中下拉框“数据类型”的数组
    var ItemTypes1 = [
        {TypeText: "冻结", TypeVal: "00"},
        {TypeText: "允许流转", TypeVal: "01"}
    ];
    // 定义表格中下拉框的“数据类型”的事件
    var select1 = function () {
        $(".Action").each(function () {
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < ItemTypes1.length; i++) {
                if (current_Td_Val == ItemTypes1[i].TypeVal)
                    str += "<option selected value='" + ItemTypes1[i].TypeVal + "'>" + ItemTypes1[i].TypeText + "</option>";
                else
                    str += "<option value='" + ItemTypes1[i].TypeVal + "'>" + ItemTypes1[i].TypeText + "</option>";
            }
            ;
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
        });
    }
    // 定义表格中“是否检查”复选框的事件
    var checkbox = function () {
        $(".Checked").each(function () {
            var current_Td_Val = $(this).text();
            var str = "";
            if (current_Td_Val == "00")
                str += "<input type='checkbox' name='Checked' checked value='00'/>";
            else
                str += "<input type='checkbox' name='Checked' value='01'/>";
            $(this).html(str);
        });
        $("input[name='Checked']").on("click", function () {
            if ($(this).prop("checked"))
                $(this).val("00");
            else
                $(this).val("01");
        });
    }




    //加载页面
    var loadPage = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
               {
                    "FieldName":"DCName",
                    "FieldOpt":"Order BY",
                }

            ]
        }
        request({url:'/DC/GetAllDcInfo',async:true, data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function (Body) {
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
                    id: treeData[i].DcRd == undefined ? "" : treeData[i].DcRd,
                    name: treeData[i].DcName == undefined ? "" : treeData[i].DcName
                }

                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    }
    loadPage();
//新增
    $(".cAdd").click(function () {
        $("#expiremailgroup").clearseldata("EmailDisRd");
        $("#expiremailcontent").clearseldata("EmailRd");
        treeID == null;
        $("#_right").show();
        $("#DCName").attr("readonly", false)
        $("#datacjForm").show()
        $("#IsDef").attr("checked", true)
        //保存为了鉴别
        $(this).attr("a", "00");
        treeID = null;// 清空选中的ID，防止点击编辑的时候没有可以编辑的数据
        clearForm("datacjForm");
        $("#DCName").removeClass("read");
        defaultValueForNew();
        $("#ExecType").val("00");
        var obj = {
            data: null,
            colArr: colNamesArr,
            tableId: tableId,
            divId: divId,
            pageBean: pageBean,
            multiselect: true,
            width:0.84,
            height:0.528
        }
        fullTable(obj);
        select();
        checkbox();
        select1();
    });

//保存
    var newData = {};
    $(".cSave").click(function () {

        if ($("#IsDef").is(":checked") == true) {
            var IsDef = "00"
        } else {
            var IsDef = "01"
        }
        var DCName = $("#DCName").val();
        var Version = $("#Version").val();
        var Remark = $("#beizhu").val();
        var EmailDistributionRd=$("#expiremailgroup").getseldata().EmailDisRd;

        var EmailMessageRd=$("#expiremailcontent").getseldata().EmailRd;

        var Status = $("#Status").val();
        var json = getTableData(tableId);
        for (var i = 0; i < json.length; i++) {
            json[i].DefValue = '0';
        }

        if (json.length > 0) {
            //直接新增
            if (treeID == null &&  $(".cAdd").attr("a") == "00") {

                var newData = {
                    "DCName": DCName,
                    "Version": Version,
                    "Status": Status,
                    "Remark": Remark,
                    "IsDef": IsDef,
                    "EmailDistributionRd":EmailDistributionRd,
                    "EmailMessageRd":EmailMessageRd,
                    "DCItemsInfo": json
                }

                //点击新增执行
                request({url:"/DC/SaveDcInfo",async:true, data: {"ExecType": "00", "BusData": JSON.stringify(newData)}},function (Body) {
                    $("#jstree_demo1 ").show();
                    condition = "";
                    currentPage = 0;
                    loadPage();
                    $(".cAdd").attr("a", "");
                    toastr.success(Body.MsgDes);
                    $("#remove").attr("b", "");
                    $("#recopy").attr("re", "");
                    $("#hidden").attr("editdj",'');
                    treeID=1;
                });
            }
            //编辑
            else if ($("#hidden").attr("editdj") == "00") {

                var DCRd = $("#hidden").attr("editid");
                var DCVerRd = $("#hidden").attr("editvid");
                var newData = {
                    "DCRd": DCRd,
                    "DCVerRd": DCVerRd,
                    "DCName": DCName,
                    "Version": Version,
                    "Status": Status,
                    "Remark": Remark,
                    "IsDef": IsDef,
                    "EmailDistributionRd":EmailDistributionRd,
                    "EmailMessageRd":EmailMessageRd,
                    "DCItemsInfo": json
                };
                request({url: "/DC/SaveDcInfo",async:true,data: {"ExecType": "02", "BusData": JSON.stringify(newData)}},function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#hidden").attr("editid", "");
                    $("#hidden").attr("editvid", "");
                    $("#hidden").attr("editdj", "");
                    treeID = null;
                    condition = "";
                    currentPage = 0;
                    loadPage();
                });
            }
            //新增版本
            else if ($("#hidden").attr("hi") == "00" && $("#addver").attr("a") == "00") {

                var DCRd = $("#addver").attr("d");
                var newData = {
                    "DCRd": DCRd,
                    "DCName": DCName,
                    "Version": Version,
                    "Status": Status,
                    "Remark": Remark,
                    "IsDef": IsDef,
                    "EmailDistributionRd":EmailDistributionRd,
                    "EmailMessageRd":EmailMessageRd,
                    "DCItemsInfo": json
                };
                request({url:"/DC/SaveDcInfo",async:true, data: {"ExecType": "04", "BusData": JSON.stringify(newData)}},function (Body) {
                    condition = "";
                    currentPage = 0;
                    loadPage();
                    toastr.success(Body.MsgDes);
                    $("#hidden").attr("hi", "");
                    $("#addver").attr("d", "");
                    $("#_right").hide();
                });
            }else {
                toastr.warning("请选择左边数据信息或新增");
            }
        } else {
            toastr.warning("采集内容不能为空!");
        }
    });

//删除
    $("#remove").click(function () {
        var DcRd = $(this).attr("b");
        if(DcRd!=treeID){
            toastr.warning("请选择左侧要删除的主项再进行删除!");
            return false;
        }
        if (DcRd == null || DcRd == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }else {
            layer.confirm('确定要删除吗?', {
                btn: ["确认", "取消"],
            }, function () {
                var busData = {
                    "DcRd": DcRd
                };
                request({url:"/DC/SaveDcInfo",async:true, data: {"ExecType": "01", "BusData": JSON.stringify(busData)}},function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    $("#remove").attr("b", "");
                    $("#_right").hide();
                    condition = "";
                    currentPage = 0;
                    loadPage();
                });
                treeID1=1;
            })
        }
    });

//新增版本
    $("#addver").click(function () {
        $("#expiremailgroup").clearseldata("EmailDisRd");
        $("#expiremailcontent").clearseldata("EmailRd");

        var DCRd = $(this).attr("d");
        if (DCRd == null || DCRd == "" || $("#hidden").attr("hi") != "00"||treeID1==1||treeID==null) {
            toastr.warning("请选择左侧要新增版本的一项再进行新增!");
            return false;
        }
        else {
            $("#_right").show();
            $("#Version").val("");
            $(".cAdd").attr("a", "");
            $("#DCName").attr("readonly", true);
            $("#IsDef").attr("disabled", false);
            $("#IsDef").attr("checked", false);
            $("#creatPeople").val("");
            $("#creatTime").val("");
            $("#modifyPeople").val("");
            $("#modifyTime").val("");
            $("#beizhu").val("");
            var obj = {
                data: null,
                colArr: colNamesArr,
                tableId: tableId,
                divId: divId,
                pageBean: pageBean,
                multiselect: true,
                width:0.84,
                height:0.528
            }
            fullTable(obj);
            select();
            select1();
            checkbox();
            //用来标识是新增保存还是编辑保存
            $("#addver").attr("a", "00");
            $("#hidden").attr("editdj","");
        }
    });

//复制
    $("#recopy").click(function () {
        var DCRd = $(this).attr("re")
        if (DCRd == null || DCRd == ""||treeID1==1) {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
            return;
        }
        var data = {
            "DCRd": DCRd
        };
        request({url:"/DC/SaveDcInfo",async:true, data: {"ExecType": "03", "BusData": JSON.stringify(data)}},function (Body) {
            toastr.success(Body.MsgDes);
            condition = "";
            currentPage = 0;
            loadPage();
            $("#recopy").attr("re", "");
            $("#remove").attr("b", "");
        });
    });

//删除版本
    $("#removeVer").click(function () {
        var DCVerRd = $(this).attr("rm");

        if (DCVerRd == null || DCVerRd == "") {
            toastr.warning("请选择左侧要删除的子项再进行删除!");
        } else {
            if(map.get(DCVerRd)==undefined){
                map.set(String(DCVerRd),treeID);
            }
            var id= map.get(String(DCVerRd));

            if(id!=treeID){
                toastr.warning("请选择左侧要删除的子项再进行删除!");
                return false;
            }
            layer.confirm("确认要删除吗？", {btn: ["确认", "取消"]}, function () {
                var data = {
                    "DCVerRd": DCVerRd
                };
                request({url:"/DC/SaveDcInfo",async:true,data: {"ExecType": "05", "BusData": JSON.stringify(data)}},function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    condition = "";
                    currentPage = 0;
                    loadPage();//局部刷新
                    $("#removeVer").attr("rm", "");
                    $("#_right").hide();
                   // loadPage();
                });
                treeID1="1";
            });
        }

    });
    /*****************表格的新增**************************/
    $(".add1").click(function () {
        addErow(tableId);
        //获取第一条
        var trObj = $("#list4 tbody>tr:eq(1)");
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
        select1();
        //select();
        //checkbox();
    })
    /***************************************************/
    /*****************表格的删除**************************/
    $(".del1").click(function () {
        delTr("list4");
    })

})