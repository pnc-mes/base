/**
 * Created by liufuzhi on 2017/7/1.
 */


/*-------------------加载表格数据、树形的处理-------------------*/
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"RCGRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/ReasonG/GetRCGInfo", data: objData},function(Body){
            fillform("resongForm", rule, Body.Data);
            //填充表格
            var RCInfoDatas = Body.Data.RCInfo;
            if(RCInfoDatas.length>0){
                for(var i = 0 ; i < RCInfoDatas.length; i++ ){
                    config1.data = RCInfoDatas;
                    fullTable(config1);
                }
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
        currentPage = treeSearchs("/PrintT/GetAllPtInfo","RCGRd","RCGpName","ReaCGName",condition,currentPage,config);
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
        currentPage = treeSearchs("/PrintT/GetAllPtInfo","RCGRd","RCGpName","ReaCGName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/PrintT/GetAllPtInfo","RCGRd","RCGpName","ReaCGName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/PrintT/GetAllPtInfo","RCGRd","RCGpName","ReaCGName",condition,currentPage,config);
    });
    $("#_right").hide();
    //预处理，表格新增列数据的加载
    var tablData = null;
    request({url:"/Reason/GetAllReaInfo", data:{"ExecType": "00"}},function(Body){
         tablData = Body.Data;
    })
    var clickSelect =function(){

        $(".ReaCode").on("click", function () {
            $(this).unbind("click");
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < tablData.length; i++) {
                if (current_Td_Val == tablData[i].ReaCode) {
                    str += "<option selected>" + tablData[i].ReaCode + "</option>";
                }else {
                    str += "<option>" + tablData[i].ReaCode + "</option>";
                }
            };
            str += "</select>";
            $(this).html(str);
            $(this).next().text(tablData[0].ReaDes);
            $(this).prev().text(tablData[0].ReaRd);
            $(this).find("select").change(function () {
                for (var i = 0; i < tablData.length; i++) {
                    if (tablData[i].ReaCode == $(this).find("option:selected").text()) {
                        $(this).parent().next().text(tablData[i].ReaDes);
                        selectID=tablData[i].ReaRd;
                        $(this).closest(".jqgrow").find(".ReaRd").get(0).innerHTML = selectID;
                    }
                }
            });
            $(this).find("select").css("border","0px");
            var str_Width = $(this).css("width");
            var index = str_Width.indexOf("px");
            var width = parseInt(str_Width.substring(0,index))*0.95;
            $(this).find("select").css("width",width);
            $(this).find("select").css("height","19px");
            $(this).find("select").css("color","#000000");
        });
    }
    var colNamesArr = [
        {"Caption": "ReaRd", "Name": "ReaRd", "Hidden": true},
        {"Caption": "原因代码", "Name": "ReaCode", 'Editable': false},
        {"Caption": "原因描述", "Name": "ReaDes", "Editable": false}
    ];
    var config1 = {
        tableId: 'list6',
        data: [],
        colArr: colNamesArr,
        multiselect:true,
        width: 0.84,height:0.54
    };
    fullTable(config1);//加载空表格


    var treeID = null;
    var rule = [{
        //原因代码
        "ctlid": "RCGpName", //自定义名字：标签id名字
        "param": "RCGpName" //规则中自定义的名字：对应报文中的id字段
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
    /*-----------------加载树------------*/
    var newTree=[];
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/ReasonG/GetAllRCGInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "ReaCGName",
                            "FieldOpt": "Order BY"
                        }]})
            }},function(Body){
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].RCGRd == undefined ? "" : treeData[i].RCGRd,
                    name: treeData[i].RCGpName == undefined ? "" : treeData[i].RCGpName
                }
                newTree.push(tree);
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();
    /*---------------------------------------------------------------操作-------------------------------------------------------------*/
    /*--------------点击新增按钮顶部菜单----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("resongForm");
        $("#ExecType").val("00");
        treeID = null;
        config1.data = [];
        fullTable(config1);
    });

    /*---------删除----------*/
    $('#remove').on('click',function(){
        if(treeID !=undefined && treeID != null &&　treeID.trim() != ''){
            layer.confirm('确定要删除吗？',{
                btn:['确认','取消'],
            },function () {
                request({url:"/ReasonG/SaveRCGPInfo", data: {"ExecType": "01", "BusData": "{\"RCGRd\":" + treeID + "}"}},function(Body){
                    layer.closeAll('dialog');
                    $('#_right').hide();
                    toastr.success(Body.MsgDes);
                    treeID = null;
                    currentPage=0;
                    condition = '';
                    loaddata();
                })
                }
            )
        }else{
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    })

    /*-------------处理保存--------------*/
    $('.cSave').on('click',function(){
        if($("#RCGpName").val().trim() != "") {
            formData = transfer("resongForm");
            //新增
            if ((treeID == null || treeID == '') && $("#ExecType").val() == '00') {
                var tableData = getTableData('list6');
                for (var i = 0; i < tableData.length; i++) {
                    if (tableData[i].ReaCode == "") {
                        toastr.warning("原因代码不能有空的");
                        return;
                    }
                }
                var RCGpName = $("#RCGpName").val();
                //var Remark = $("#beizhu").val();
                var newData = {
                    "RCGpName": RCGpName,
                    "RCInfo": null,
                    "Remark": formData["remark"]
                }
                var dataArrs = [];
                for (var i = 0; i < tableData.length; i++) {
                    var dataArr = {
                        'ReaRd': tableData[i].ReaRd
                    }
                    dataArrs.push(dataArr);
                }
                newData.RCInfo = dataArrs;
                request({
                    url: "/ReasonG/SaveRCGPInfo",
                    data: {"ExecType": "00", "BusData": JSON.stringify(newData)}
                }, function (Body) {
                    currentPage=0;
                    condition = '';
                    /*-----刷新树、右侧不刷新、$("#ExecType")的值（状态）置空*/
                    loaddata();
                    $("#ExecType").val('');
                    toastr.success(Body.MsgDes);
                });
            }
            //编辑
            else if (treeID != null && treeID != '') {
                var tableData = getTableData('list6');
                var RCGpName = $("#RCGpName").val();
                //var Remark = $('#beizhu').val();
                var newData = {
                    'RCGRd': treeID,
                    'RCGpName': RCGpName,
                    'RCInfo': null,
                    "Remark": formData["remark"]
                }
                var RCInfoArr = [];
                for (var i = 0; i < tableData.length; i++) {
                    var ReaRdId = {
                        'ReaRd': tableData[i].ReaRd
                    }
                    RCInfoArr.push(ReaRdId);
                }
                newData.RCInfo = RCInfoArr;

                request({
                    url: "/ReasonG/SaveRCGPInfo",
                    data: {"ExecType": "02", "BusData": JSON.stringify(newData)}
                }, function (Body) {
                    /*-----刷新树、右侧不刷新、$("#ExecType")的值（状态）置空、加载消息*/
                    treeID = null;
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val('');
                });
            }
        }else{
            if ($("#RCGpName").val().trim() == "") {
                $("#RCGpName").css("border-color", "red").prop("placeholder", "不能为空！");
            }
        }
    });
    /*--------------------处理表格的新增/删除-----------------------------------*/
    //新增
    $('.add1').on('click',function(){
        addErow("list6");
        clickSelect();
    });
    //删除
    $(".del1").click(function () {
        derow("list6");
    });
});
