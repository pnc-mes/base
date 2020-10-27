/**
 * Created by zhangliangliang on 2017/11/2.
 */
$(function(){

    var colNamesArr3 = [
        {"Caption": "物料代码", "Name": "MaCode", "Editable": false,Width:100},
        {"Caption": "物料名称", "Name": "MaName", "Editable": false},
        {"Caption": "物料描述", "Name": "MaDes", "Editable": false},
        {"Caption": "批次号", "Name": "Batch", "Editable": false,Width:100},
        {"Caption": "调出仓库", "Name": "MOSName", "Editable": false,Width:80},
        {"Caption": "调出数量", "Name": "MONum", "Editable": false,Width:50},
        {"Caption": "调入仓库", "Name": "MISName", "Editable": false,Width:80},
        {"Caption": "调入数量", "Name": "MINum", "Editable": false,Width:50}
    ];


    var onClicks = function(nodeinfo, handler) {
        $("#_right").show();
        var MVRd=nodeinfo.nodeID;
        var data={
            "MVRd":MVRd
        }
        request({url:"/MV/GetMVMaInfo", data: {"ExecType": "00","BusData":JSON.stringify(data)}},function(Body){
            $("#storeName").val(Body.Data.MVCode);
            var str="";
            if(Body.Data.ExStatus=="00"){
                str="待执行";
            }
            if(Body.Data.ExStatus=="01"){
                str="进行中";
            }
            if(Body.Data.ExStatus=="02"){
                str="已完成";
            }
            if(Body.Data.ExStatus=="03"){
                str="已取消";
            }
            $(".status").text(str);

            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);

            var config3 = {
                tableId: "list4",
                data: Body.Data.MVDtlInfo,
                colArr:colNamesArr3,
                multiselect:true,
                width:0.85
            };
            fullTable(config3);
        });


        /*$.ajax({
            url: getBasePath()+"/MV/GetMVMaInfo",
            type:"POST",
            data: {"ExecType": "00","BusData":JSON.stringify(data)},
            success:function(res){

                $("#storeName").val(res.Body.Data.MVCode);
                var str="";
                if(res.Body.Data.ExStatus=="00"){
                    str="待执行";
                }
                if(res.Body.Data.ExStatus=="01"){
                    str="进行中";
                }
                if(res.Body.Data.ExStatus=="02"){
                    str="已完成";
                }
                if(res.Body.Data.ExStatus=="03"){
                    str="已取消";
                }
                $(".status").text(str);

                $("#creatPeople").val(res.Body.Data.Creator);
                $("#creatTime").val(res.Body.Data.CreateTime);
                $("#modifyPeople").val(res.Body.Data.LastModifyMan);
                $("#modifyTime").val(res.Body.Data.LastModifyTime);
                $("#beizhu").val(res.Body.Data.Remark);

                var config3 = {
                    tableId: "list4",
                    data: res.Body.Data.MVDtlInfo,
                    colArr:colNamesArr3,
                    multiselect:true,
                    width:0.85
                };
                fullTable(config3);
            }
        })*/
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
        currentPage = treeSearchs("/MV/GetAllMVMaInfo","MVRd","MVCode","MVCode",condition,currentPage,config);
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
            currentPage = treeSearchs("/MV/GetAllMVMaInfo","MVRd","MVCode","MVCode",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/MV/GetAllMVMaInfo","MVRd","MVCode","MVCode",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/MV/GetAllMVMaInfo","MVRd","MVCode","MVCode",condition,currentPage,config);
    });

//加载页面
    var loadPage=function () {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var treedataList = [];
        request({url:"/MV/GetAllMVMaInfo", data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo)}},function(Body){
            if(Body.MsgCode == "0x00000"){
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
                        id: treeData[i].MVRd == undefined ? "" : treeData[i].MVRd,
                        name: treeData[i].MVCode == undefined ? "" : treeData[i].MVCode
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }
            //默认加载
            var MVRd=treedataList[0].id;
            var data={
                "MVRd":MVRd
            }
            request({url:"/MV/GetMVMaInfo", data: {"ExecType": "00","BusData":JSON.stringify(data)}},function(Body){
                $("#storeName").val(Body.Data.MVCode);
                var str="";
                if(Body.Data.ExStatus=="00"){
                    str="待执行";
                }
                if(Body.Data.ExStatus=="01"){
                    str="进行中";
                }
                if(Body.Data.ExStatus=="02"){
                    str="已完成";
                }
                if(Body.Data.ExStatus=="03"){
                    str="已取消";
                }
                $(".status").text(str);

                $("#creatPeople").val(Body.Data.Creator);
                $("#creatTime").val(Body.Data.CreateTime);
                $("#modifyPeople").val(Body.Data.LastModifyMan);
                $("#modifyTime").val(Body.Data.LastModifyTime);
                $("#beizhu").val(Body.Data.Remark);

                var config3 = {
                    tableId: "list4",
                    data: Body.Data.MVDtlInfo,
                    colArr:colNamesArr3,
                    multiselect:true,
                    width:0.85
                };
                fullTable(config3);
            });

            /*$.ajax({
                url: getBasePath()+"/MV/GetMVMaInfo",
                type:"POST",
                data: {"ExecType": "00","BusData":JSON.stringify(data)},
                success:function(res){

                    $("#storeName").val(res.Body.Data.MVCode);
                    var str="";
                    if(res.Body.Data.ExStatus=="00"){
                        str="待执行";
                    }
                    if(res.Body.Data.ExStatus=="01"){
                        str="进行中";
                    }
                    if(res.Body.Data.ExStatus=="02"){
                        str="已完成";
                    }
                    if(res.Body.Data.ExStatus=="03"){
                        str="已取消";
                    }
                    $(".status").text(str);

                    $("#creatPeople").val(res.Body.Data.Creator);
                    $("#creatTime").val(res.Body.Data.CreateTime);
                    $("#modifyPeople").val(res.Body.Data.LastModifyMan);
                    $("#modifyTime").val(res.Body.Data.LastModifyTime);
                    $("#beizhu").val(res.Body.Data.Remark);

                    var config3 = {
                        tableId: "list4",
                        data: res.Body.Data.MVDtlInfo,
                        colArr:colNamesArr3,
                        multiselect:true,
                        width:0.85
                    };
                    fullTable(config3);
                }
            })*/

        });


        /*$.ajax({
            url: getBasePath()+"/MV/GetAllMVMaInfo",
            type:"POST",
            data: {"ExecType": "00","PageInfo":JSON.stringify(pageInfo)},
            success:function(res){
                if(res.Status =="00" && res.Body.MsgCode == "0x00000"){
                    var treeData = res.Body.Data;
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
                            id: treeData[i].MVRd == undefined ? "" : treeData[i].MVRd,
                            name: treeData[i].MVCode == undefined ? "" : treeData[i].MVCode
                        };
                        treedataList.push(tree);
                    }
                    config.data.source = treedataList;
                    $.JstreeEx.init(config);//先调用后加载
                }
                //默认加载
                var MVRd=treedataList[0].id;
                var data={
                    "MVRd":MVRd
                }
                $.ajax({
                    url: getBasePath()+"/MV/GetMVMaInfo",
                    type:"POST",
                    data: {"ExecType": "00","BusData":JSON.stringify(data)},
                    success:function(res){

                        $("#storeName").val(res.Body.Data.MVCode);
                        var str="";
                        if(res.Body.Data.ExStatus=="00"){
                            str="待执行";
                        }
                        if(res.Body.Data.ExStatus=="01"){
                            str="进行中";
                        }
                        if(res.Body.Data.ExStatus=="02"){
                            str="已完成";
                        }
                        if(res.Body.Data.ExStatus=="03"){
                            str="已取消";
                        }
                        $(".status").text(str);

                        $("#creatPeople").val(res.Body.Data.Creator);
                        $("#creatTime").val(res.Body.Data.CreateTime);
                        $("#modifyPeople").val(res.Body.Data.LastModifyMan);
                        $("#modifyTime").val(res.Body.Data.LastModifyTime);
                        $("#beizhu").val(res.Body.Data.Remark);

                        var config3 = {
                            tableId: "list4",
                            data: res.Body.Data.MVDtlInfo,
                            colArr:colNamesArr3,
                            multiselect:true,
                            width:0.85
                        };
                        fullTable(config3);
                    }
                })
            }
        })*/
    };
    loadPage();
});