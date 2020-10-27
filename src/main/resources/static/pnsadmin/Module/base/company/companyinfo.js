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
        var objBusData = JSON.stringify({"CpRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/Cp/GetCpInfo",data: objData},function(Body){
            //去除边框颜色
            fillform("companyinfo", rule, Body.Data);
            $("#hidden").attr("h",Body.Data.DSource);
            $("#Status").find("option").each(function () {
                if($(this).val() == Body.Data.Status){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });

            var DSource = $("#hidden").attr("h");

            if(DSource=="00"){
                $("#cpName").attr("readonly",true);
                $("#contactor").attr("readonly",true);
                $("#address").attr("readonly",true);
                $("#Status").prop("disabled",true);
            }else{
                $("#cpName").attr("readonly",false);
                $("#contactor").attr("readonly",false);
                $("#address").attr("readonly",false);
                $("#Status").prop("disabled",false);
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
        currentPage = treeSearchs("/Cp/GetAllCpInfo","CpRd","CpName","CompanyName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Cp/GetAllCpInfo","CpRd","CpName","CompanyName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Cp/GetAllCpInfo","CpRd","CpName","CompanyName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Cp/GetAllCpInfo","CpRd","CpName","CompanyName",condition,currentPage,config);
    });

    $("#_right").hide();
    var treeID = null;
    var rule = [{
        "ctlid": "cpCode",
        "param": "CpCode"
    },{
        //企业名称
        "ctlid": "cpName", //自定义名字：标签id名字
        "param": "CpName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //联系人
        "ctlid": "contactor",
        "param": "Contactor"
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

    /*方法：首次加载页面------------*/

    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/Cp/GetAllCpInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CreateTime DESC",
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
                    id: treeData[i].CpRd == undefined ? "" : treeData[i].CpRd,
                    name: treeData[i].CpName == undefined ? "" : treeData[i].CpName
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
        clearForm("companyinfo");
        $("#ExecType").val("00");
        $("#Status").val("00");
        treeID = null;
        $("#cpCode").attr("readonly",false);
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
                    request({url:"/Cp/SaveCpInfo",async:true, data:{"ExecType": "01","busData": "{\"CpRd\":" + treeID + "}"}},function(Body){
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

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            request({url:"/Cp/SaveCpInfo",data: {"ExecType": "03", "busData": "{\"CpRd\":" + treeID + "}"}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                treeID = null;
                currentPage=0;
                condition = "";
                loaddata();
            })
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });
    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    var CpCode="";
    $(".cSave").click(function () {
        if ($("#cpName").val().trim() != "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("companyinfo");
            var status = $("#Status").val();

            //新增信息
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "CpCode": formData["cpCode"],
                    "CpName": formData["cpName"],
                    "Contactor": formData["contactor"],
                    "Address": formData["address"],
                    "Status": status,
                    "Remark": formData["remark"]
                };
                request({url:"/Cp/SaveCpInfo",data:{"ExecType": $("#ExecType").val(),"busData": JSON.stringify(newData)}},function(Body){
                    $("#ExecType").val("");
                    toastr.success("企业信息新增成功,企业代码{"+Body.Data.CpCode+"}");
                    currentPage=0;
                    condition = "";
                    loaddata();
                    $("#hidden1").attr("editid", Body.Data.CpRd);
                    treeID=$("#hidden1").attr("editid");
                    $("#hidden2").attr("editcode", Body.Data.CpCode);
                    backfill(treeID);
                });
            }
            //编辑信息
            else if(treeID != null && treeID != ""){
                newData = {
                    "CpRd": treeID,
                    "CpName": formData["cpName"],
                    "Contactor": formData["contactor"],
                    "Address": formData["address"],
                    "Status": status,
                    "Remark": formData["remark"]
                };
                request({url:"/Cp/SaveCpInfo",data:{"ExecType": "02","busData": JSON.stringify(newData)}},function(Body){
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = "";
                    backfill(treeID);
                    loaddata();
                });
            }
        }
    });

    //数据回填
    function backfill(treeID){
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        $("#cpCode").attr("readonly",true);
        var objBusData = JSON.stringify({"CpRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/Cp/GetCpInfo",data: objData},function(Body){
            //去除边框颜色
            fillform("companyinfo", rule, Body.Data);
            $("#hidden").attr("h",Body.Data.DSource);
            $("#Status").find("option").each(function () {
                if($(this).val() == Body.Data.Status){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });

            var DSource = $("#hidden").attr("h");

            if(DSource=="00"){
                $("#cpName").attr("readonly",true);
                $("#contactor").attr("readonly",true);
                $("#address").attr("readonly",true);
                $("#Status").prop("disabled",true);
            }else{
                $("#cpName").attr("readonly",false);
                $("#contactor").attr("readonly",false);
                $("#address").attr("readonly",false);
                $("#Status").prop("disabled",false);
            }
        });
    }

});
