/**
 * Created by liufuzhi on 2017/7/5.
 */

/*-------------------树形的处理-------------------*/
$(function () {
    var treeID = null;
    var selectID=null;
    $("#_right").hide();
    //获取所有文件名称
    var FileNames = [];
    request({url:"/File/GetAllFileInfo", data: {"ExecType": "00"}},function(Body){
        FileNames = Body.Data;
    });

    //获取下拉框中的值
    var clickSelect = function() {
        $(".FileName").on("click", function () {
            $(this).unbind("click");
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < FileNames.length; i++) {
                if (current_Td_Val == FileNames[i].FileName) {
                    str += "<option selected>" + FileNames[i].FileName + "</option>";
                }else {
                    str += "<option>" + FileNames[i].FileName + "</option>";
                }
            };
            str += "</select>";
            $(this).html(str);
            $(this).next().text(FileNames[0].Version);
            $(this).prev().text(FileNames[0].FileVerRd);
            $(this).find("select").change(function () {
                for (var i = 0; i < FileNames.length; i++) {
                    if (FileNames[i].FileName == $(this).find("option:selected").text()) {
                        $(this).parent().next().text(FileNames[i].Version);
                        selectID=FileNames[i].FileVerRd;
                        $(this).closest(".jqgrow").find(".FileVerRd").get(0).innerHTML = selectID;
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
    };
    var colNamesArr=[
        {"Caption":"id", "Name":"FileVerRd", "CType":"text", "Hidden":true},
        {"Caption":"文件名称", "Name":"FileName","CType":"text", "Editable":false},
        {"Caption":"文件版本", "Name":"Version","CType":"text", "Editable":false},
        {"Caption":"GPDtlRd", "Name":"GPDtlRd","Hidden":true}
    ];


    //获取所有版本
    var Versions=[];
    request({url:"/File/GetFileTreeInfo", data: {"ExecType": "00","BusData": JSON.stringify({"FileRd":selectID})}},function(Body){
        Versions = Body.Data;
    });

    var rule = [{
        //文件组名称
        "ctlid": "fileGpName", //自定义名字：标签id名字
        "param": "FileGpName" //规则中自定义的名字：对应报文中的id字段
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
    /*------------------获取点击之后一个节点的数据------------------*/

    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"FileGRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/FileGroup/GetFileGInfo", data: objData}, function (Body) {
            fillform("filegroupForm", rule, Body.Data);
            showWarn("");
            var data= Body.Data.FileInfo;

            for (var i = 0; i < data.length; i++) {
                if(data[i].FileInfo!= null){
                    data[i].FileVerRd = data[i].FileInfo.FileVerRd;
                    data[i].Version = data[i].FileInfo.Version;
                }
            }

            // 处理表格
            var config={
                tableId: "list4",
                data: data,
                colArr:colNamesArr,
                multiselect:true,
                width:0.84,
                height:0.64
            };
            fullTable(config);
            clickSelect();
        });
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
        currentPage = treeSearchs("/FileGroup/GetAllFileGInfo","FileGRd","FileGpName","FileGpName",condition,currentPage,config);
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
            currentPage = treeSearchs("/FileGroup/GetAllFileGInfo","FileGRd","FileGpName","FileGpName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/FileGroup/GetAllFileGInfo","FileGRd","FileGpName","FileGpName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/FileGroup/GetAllFileGInfo","FileGRd","FileGpName","FileGpName",condition,currentPage,config);
    });


    //只刷新树
    var loadtree = function () {
        var trees = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"FileGpName",
                    "FieldOpt":"Order BY",
                }

            ]
        }
        request({url: '/FileGroup/GetAllFileGInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData)},"PageInfo":JSON.stringify(pageInfo)}, function (Body) {
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
                    id: treeData[i].FileGRd == undefined ? "" : treeData[i].FileGRd,
                    name: treeData[i].FileGpName == undefined ? "" : treeData[i].FileGpName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadtree();
    /*--------------点击新增按钮顶部菜单----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("filegroupForm");//清空表单中的数据然后对其新增数据
        treeID = null;//防止点击了树的节点之后，再点新增
        $("#ExecType").val("00");
        var config={
            tableId: "list4",
            data: [],
            colArr:colNamesArr,
            multiselect:true,
            width:0.84,
            height:0.64
        };

        fullTable(config);
    });

    /*****************表格的新增**************************/
    $(".add1").click(function () {
        addErow("list4");
        clickSelect();
    })

    /*****************表格的删除**************************/
    $(".del1").click(function () {
        derow("list4");
    })
    /***************************************************/

    /*---------顶端操作中的删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID!= "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/FileGroup/SaveFileGpInfo",
                        data: {"ExecType": "01", "busData": "{\"FileGRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        treeID = null;
                        $("#ExecType").val("");
                        condition = "";
                        currentPage = 0;
                        loadtree();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------点击保存按钮顶部菜单----------*/
    var newData = {};
    $(".cSave").click(function () {
        // 获取所有表单数据封装成json对象
        formData = transfer("filegroupForm");

        if ($("#fileGpName").val().trim() != "") {
            var bfsinfo = getRowData("list4");

            var fileverRd=true;
            for (var i = 0; i < bfsinfo.length; i++) {
                delete bfsinfo[i]["FileName"]
                delete bfsinfo[i]["GPDtlRd"]
                delete bfsinfo[i]["Version"]
                if(bfsinfo[i].FileVerRd!=null && bfsinfo[i].FileVerRd!=""){
                    fileverRd=false;
                }
            }
            if(fileverRd){
                toastr.warning("请新增文件,再保存");
                return;
            }

            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {

                newData = {
                    "FileGpName": formData["fileGpName"],
                    "FileInfo": bfsinfo,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/FileGroup/SaveFileGpInfo",
                    data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val('');
                    condition = "";
                    currentPage = 0;
                    loadtree();
                });
            }
           else if (treeID!=null && treeID!="") {
                var bfsinfo1 = getRowData("list4");

                for (var i = 0; i < bfsinfo1.length; i++) {
                    delete bfsinfo1[i]["FileName"]
                    delete bfsinfo1[i]["Version"]
                }

                newData = {
                    "FileGRd": treeID,
                    "FileGpName": formData["fileGpName"],
                    "FileInfo": bfsinfo1,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/FileGroup/SaveFileGpInfo",
                    data: {"ExecType": "02", "busData": JSON.stringify(newData)}
                }, function (Body) {
                    toastr.success(Body.MsgDes);
                    condition = "";
                    currentPage = 0;
                    loadtree();
                    treeID = null;

                });
            }
        } else {
            $("#fileGpName").css("border-color", "red");
            $("#fileGpName").prop("placeholder", "不能为空！");
        }
    });

});