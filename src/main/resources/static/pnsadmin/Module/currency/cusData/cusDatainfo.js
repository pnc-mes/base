/**
 * Created by liufuzhi on 2017/6/23.
 */
/*-------------------树形的处理-------------------*/
$(function () {
    $("#_right").hide();
    var treeID = null;
    //处理页面加载时，直接点击保存事件
    var isEdit=false;
    var colNamesArr = [
        {"Caption": "CusDataDtlRd", "Name": "CusDataDtlRd", "Hidden": true},
        {"Caption": "显示名", "Name": "DisplayName", "Hidden": false,"CType": "text", "Editable": true },
        {"Caption": "值", "Name": "Val", "Hidden": false,"CType": "text", "Editable": true},
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height: 0.415
    };
    config1.data.length = 0;
    fullTable(config1);//加载空表格

    /*方法：------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        isEdit=true;
        $("#_right").show();//每次点击树节点的时候都把右侧展开

        treeID =nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CusDataRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/CusData/GetCDataInfo",data: objData},function(Body){
            $("#CusDataName").val(Body.Data.CusDataName);
            $("#beizhu").val(Body.Data.Remark);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#creatPeople").val(Body.Data.Creator);
            var config1 = {
                tableId: 'list4',
                data: Body.Data.CusDataDtl,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height: 0.415
            };
            fullTable(config1);//加载空表格

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
        currentPage = treeSearchs("/CusData/GetAllCDataInfo","CusDataRd","CusDataName","CusDataName",condition,currentPage,config);
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
            currentPage = treeSearchs("/CusData/GetAllCDataInfo","CusDataRd","CusDataName","CusDataName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/CusData/GetAllCDataInfo","CusDataRd","CusDataName","CusDataName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/CusData/GetAllCDataInfo","CusDataRd","CusDataName","CusDataName",condition,currentPage,config);
    });



    /*方法：首次加载页面------------*/

    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/CusData/GetAllCDataInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CusDataName DESC",
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
                    id: treeData[i].CusDataRd == undefined ? "" : treeData[i].CusDataRd,
                    name: treeData[i].CusDataName == undefined ? "" : treeData[i].CusDataName
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
        isEdit=true;
        $("#_right").show();
        treeID = null;
        $("#CusDataName").val("");
        $("#beizhu").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#creatTime").val("");
        $("#creatPeople").val("");
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height: 0.415
        };
        config1.data.length = 0;
        fullTable(config1);//加载空表格
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            var DSource = $("#hidden").attr("h");

            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    request({url:"/CusData/SaveCDataInfo",async:true, data:{"ExecType": "01","busData": "{\"CusDataRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
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
    $(".cSave").click(function () {
        if(!isEdit){
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }

        var CusDataName=$("#CusDataName").val().trim();
        if(CusDataName==null||""==CusDataName){
            toastr.warning("保存失败，自定义数据名称不能为空");
            return false;
        }
        var Remark=$("#beizhu").val().trim();

        var tableData = getTableData("list4");
        if(tableData.length<=0){
            toastr.warning("保存失败，明细不能为空");
            return false;
        }

       if(treeID==null){
            var CusDataDtls=[];
            var j=1;
            for(var i in tableData){
                if(tableData[i].DisplayName==null||tableData[i].DisplayName==""){
                    toastr.warning("保存失败，第"+j+"行的显示名不能为空");
                    return false;
                }
                if(tableData[i].Val==null||tableData[i].Val==""){
                    toastr.warning("保存失败，第"+j+"行的值不能为空");
                    return false;
                }
                var CusDataDtl={
                    "DisplayName":tableData[i].DisplayName,
                    "Val":tableData[i].Val
                }
                CusDataDtls.push(CusDataDtl);
                j++;
            }
            var newData={
                "CusDataName":CusDataName,
                "Remark":Remark,
                "CusDataDtl":CusDataDtls
            }
           request({url:"/CusData/SaveCDataInfo",data:{"ExecType":"00","busData": JSON.stringify(newData)}},function(Body){
               toastr.success(Body.MsgDes);
               currentPage=0;
               condition = "";
               loaddata();
                treeID="";
           });
       }
        else {
           var CusDataDtls=[];
           var j=1;
           for(var i in tableData){
               if(tableData[i].DisplayName==null||tableData[i].DisplayName==""){
                   toastr.warning("修改失败，第"+j+"行的显示名不能为空");
                   return false;
               }
               if(tableData[i].Val==null||tableData[i].Val==""){
                   toastr.warning("修改失败，第"+j+"行的值不能为空");
                   return false;
               }
               var CusDataDtl={
                   "DisplayName":tableData[i].DisplayName,
                   "Val":tableData[i].Val
               }
               CusDataDtls.push(CusDataDtl);
               j++;
           }
           var newData={
               "CusDataRd":treeID,
               "CusDataName":CusDataName,
               "Remark":Remark,
               "CusDataDtl":CusDataDtls
           }
           request({url:"/CusData/SaveCDataInfo",data:{"ExecType":"02","busData": JSON.stringify(newData)}},function(Body){
               toastr.success(Body.MsgDes);
               currentPage=0;
               condition = "";
               loaddata();
               treeID="";
           });
       }





    });

    //表格的新增
    $(".add1").click(function () {
        addErow("list4");
    });
    //表格的删除
    $(".del1").on("click", function () {
        derow("list4");
    });

});
