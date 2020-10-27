/**
 * Created by liufuzhi on 2017/7/1.
 */
/*-------------------树形的处理-------------------*/
$(function () {
    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo,handle) {
        $("#_right").show();
        treeID=nodeinfo.nodeID;//将点击的树的id赋值给
        var objBusData = JSON.stringify({"CusRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/Customer/GetCusInfo", data: objData},function(Body){
            fillform("customerinfo", rule, Body.Data);
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
        currentPage = treeSearchs("/Customer/GetAllCusInfo","CusRd","CusName","CustomerName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Customer/GetAllCusInfo","CusRd","CusName","CustomerName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Customer/GetAllCusInfo","CusRd","CusName","CustomerName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Customer/GetAllCusInfo","CusRd","CusName","CustomerName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID=null;//树的id
    /*--------------定义表单的规则---------------------*/
    var rule = [{
        //客户名称
        "ctlid": "cusName", //自定义名字：标签id名字
        "param": "CusName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //联系人
        "ctlid": "contactor",
        "param": "Contactor"
    }, {
        //联系方式
        "ctlid": "mobile",
        "param": "Mobile"
    }, {
        //联系地址
        "ctlid": "address",
        "param": "Address"
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
    var loaddata=function(){
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:'/Customer/GetAllCusInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CustomerName",
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
                    id: treeData[i].CusRd == undefined ? "" : treeData[i].CusRd,
                    name: treeData[i].CusName == undefined ? "" : treeData[i].CusName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载


        });
    };
    // 首次加载数据
    loaddata();

    /*--------------点击新增按钮顶部菜单----------*/
    var formData = {};
    $(".cAdd").click(function () {
        $("#_right").show();
        clearForm("customerinfo");//清空表单中的数据然后对其新增数据
        treeID=null;//防止点击了树的节点之后，再点新增
        $("#ExecType").val("00");
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Customer/SaveCusInfo", data: {"ExecType": "01", "busData": "{\"CusRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID=null;
                        $("#ExecType").val("");
                        $("#_right").hide();
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------点击操作中的复制按钮顶部菜单----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            request({url:"/Customer/SaveCusInfo",data: {"ExecType": "03", "busData": "{\"CusRd\":" + treeID + "}"}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");//复制之后隐藏字段的id的值已经存在了，此时要将其清空
                currentPage=0;
                condition = '';
                loaddata();//重新加载数据,复制之后系统会自动保存到数据库中，只需要重新加载数的节点
                treeID=null;//    清空id,防止再次点击复制的时候，复制相同的数据
            });
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------点击保存按钮顶部菜单----------*/
    var newData = {};
    $(".cSave").click(function () {


        if ($("#cusName").val().trim() == ""||$("#cusName").val().trim()==null) {
            toastr.warning("名称不能为空");
            return false;
        }
        if ($("#contactor").val().trim() == ""||$("#contactor").val().trim()==null) {
            toastr.warning("联系人不能为空");
            return false;
        }
        if ($("#mobile").val().trim() == ""||$("#mobile").val().trim()==null) {
            toastr.warning("联系方式不能为空");
            return false;
        }
        if ($("#address").val().trim() == ""||$("#address").val().trim()==null) {
            toastr.warning("联系地址不能为空");
            return false;
        }
        // 获取所有表单数据封装成json对象
        formData = transfer("customerinfo");
        if ($("#cusName").val().trim() != "") {
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "CusName": formData["cusName"],
                    "Contactor": formData["contactor"],
                    "Mobile": formData["mobile"],
                    "Address": formData["address"],
                    "Remark": formData["remark"]
                };
                request({url: "/Customer/SaveCusInfo", data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function (Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val('');
                    currentPage=0;
                    loaddata();
                });
            }
            else if (treeID != null && treeID != "") {
                newData = {
                    "CusRd": treeID,
                    "CusName": formData["cusName"],
                    "Contactor": formData["contactor"],
                    "Mobile": formData["mobile"],
                    "Address": formData["address"],
                    "Remark": formData["remark"]
                };
                request({url:"/Customer/SaveCusInfo", data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val('');
                    treeID = null;
                    currentPage=0;
                    condition = '';
                    loaddata();
                });
            }
        }


            /*if (CpRd == "") {
                /!*$("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect").find("input:eq(0)").val("");
                $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");*!/
                toastr.warning("企业不能为空");
                return false;
            }*/





    });
});
