/**
 * Created by test on 2017/8/22.
 */
/*-------------------加载表格数据、树形的处理-------------------*/
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"WTRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/WoType/GetWTInfo", data: objData},function(Body){
            fillform("wotypeForm", rule, Body.Data);
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
        currentPage = treeSearchs("/WoType/GetAllWTInfo","WTRd","WTName","WoTName",condition,currentPage,config);
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
        currentPage = treeSearchs("/WoType/GetAllWTInfo","WTRd","WTName","WoTName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/WoType/GetAllWTInfo","WTRd","WTName","WoTName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/WoType/GetAllWTInfo","WTRd","WTName","WoTName",condition,currentPage,config);
    });
    //定义表单规则
    $("#_right").hide();
    var treeID = null;
    var rule = [{
        //原因代码
        "ctlid": "WTName", //自定义名字：标签id名字
        "param": "WTName" //规则中自定义的名字：对应报文中的name
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


    //加载树
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/WoType/GetAllWTInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "WoTName",
                            "FieldOpt": "Order BY"
                        }]})
            }},function(Body){
            var treeData = Body.Data;
            for (var i = 0; i < treeData.length; i++) {
                var tree = {
                    id: treeData[i].WTRd == undefined ? "" : treeData[i].WTRd,
                    name: treeData[i].WTName == undefined ? "" : treeData[i].WTName
                };
                treedataList.push(tree);
            }
          config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();
    /*---------------------------------------------------------------操作-------------------------------------------------------------*/
    //新增
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("wotypeForm");
        $("#ExecType").val("00");
        treeID = null;
    });

    //删除
    $('#remove').on('click',function(){
        if(treeID !=undefined && treeID != null &&　treeID.trim() != ''){
            layer.confirm('确定要删除吗？',{
                    btn:['确认','取消'],
                },function () {
                    request({url:"/WoType/SaveWTInfo", data: {"ExecType": "01", "BusData": "{\"WTRd\":" + treeID + "}"}},function(Body){
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

    //保存
    $('.cSave').on('click',function(){
        if ($("#WTName").val().trim() != "") {
            //新增
            if ((treeID == null || treeID == '') && $("#ExecType").val() == '00') {

                var WTName = $('#WTName').val();
                var Remark = $('#beizhu').val();
                var datas = {
                    'WTName': WTName,
                    "Remark": Remark
                }

                request({
                    url: "/WoType/SaveWTInfo",
                    data: {"ExecType": "00", "BusData": JSON.stringify(datas)}
                }, function (Body) {
                    /*-----刷新树、右侧不刷新、$("#ExecType")的值（状态）置空*/
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    loaddata();
                    $("#ExecType").val('');

                });
            }
            //编辑
            else if (treeID != null && treeID != '') {
                var WTName = $("#WTName").val();
                var Remark = $('#beizhu').val();
                var editData = {
                    'WTRd': treeID,
                    'WTName': WTName,
                    'Remark': Remark
                };
                request({
                    url: "/WoType/SaveWTInfo",
                    data: {"ExecType": "02", "BusData": JSON.stringify(editData)}
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
            if ($("#WTName").val().trim() == "") {
                $("#WTName").css("border-color", "red");
                $("#WTName").prop("placeholder", "不能为空！");
            }
        }
    });
});
