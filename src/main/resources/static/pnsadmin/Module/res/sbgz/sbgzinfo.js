/**
 * Created by liufuzhi on 2017/6/23.
 */
/*-------------------树形的处理-------------------*/
$(function () {
    /*方法：------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#cpCode").attr("readonly",true);
        treeID =nodeinfo.nodeID;
        var objBusData = JSON.stringify({"DevMalfRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/DeviceMalf/GetDevMalfInfo",data: objData},function(Body){

            $("#sbgzdm").val(Body.Data.DevMalfCode);
            $("#sbgzmc").val(Body.Data.DevMalfName);
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
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
        currentPage = treeSearchs("/DeviceMalf/GetAllDevMalfInfo","DevMalfRd","DevMalfName","DevMalfName",condition,currentPage,config);
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
            currentPage = treeSearchs("/DeviceMalf/GetAllDevMalfInfo","DevMalfRd","DevMalfName","DevMalfName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/DeviceMalf/GetAllDevMalfInfo","DevMalfRd","DevMalfName","DevMalfName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/DeviceMalf/GetAllDevMalfInfo","DevMalfRd","DevMalfName","DevMalfName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;

    /*方法：首次加载页面------------*/

    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/DeviceMalf/GetAllDevMalfInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "DevMalfName DESC",
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
                    id: treeData[i].DevMalfRd == undefined ? "" : treeData[i].DevMalfRd,
                    name: treeData[i].DevMalfName == undefined ? "" : treeData[i].DevMalfName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        })
    };
    // 调用，首次加载页面
    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        $("#ExecType").val("00");
        treeID = null;
        $("#sbgzdm").val("");
        $("#sbgzmc").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");

    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            var DSource = $("#hidden").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    request({url:"/DeviceMalf/SaveDevMalfInfo",async:true, data:{"ExecType": "01","busData": "{\"DevMalfRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        $("#ExecType").val("");
                        treeID = null;
                        toastr.success(Body.MsgDes);
                        currentPage=0;
                        condition = "";
                        loaddata();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });


    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    var CpCode="";
    $(".cSave").click(function () {
            if($("#sbgzdm").val().trim()==null||$("#sbgzdm").val().trim()==""){
                toastr.warning("设备故障类型代码不能为空");
                return false;
            }
            if($("#sbgzmc").val().trim()==null||$("#sbgzmc").val().trim()==""){
                toastr.warning("设备故障类型名称不能为空");
                return false;
            }
            //新增信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "DevMalfCode": $("#sbgzdm").val().trim(),
                    "DevMalfName": $("#sbgzmc").val().trim(),
                    "Remark": $("#beizhu").val().trim()
                };
                request({url:"/DeviceMalf/SaveDevMalfInfo",data:{"ExecType": $("#ExecType").val(),"busData": JSON.stringify(newData)}},function(Body){
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = "";
                    loaddata();

                });
            }
            //编辑信息
            else if(treeID != null && treeID != ""){
                newData = {
                    "DevMalfRd": treeID,
                    "DevMalfCode": $("#sbgzdm").val().trim(),
                    "DevMalfName": $("#sbgzmc").val().trim(),
                    "Remark": $("#beizhu").val().trim()
                };
                request({url:"/DeviceMalf/SaveDevMalfInfo",data:{"ExecType": "02","busData": JSON.stringify(newData)}},function(Body){
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = "";
                    loaddata();
                });
            }
    });

});
