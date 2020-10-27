/**
 * Created by liufuzhi on 2017/6/23.
 */
/*-------------------树形的处理-------------------*/
$(function () {

    /*------------------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {
        $(".right").show();
        $("#supCode").attr("readonly",true);
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"SupRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({
                url:"/Supplier/GetSupInfo",
                data: objData},
            function (Body) {
                fillform("supplierForm", rule,Body.Data);
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
                    $("#supCode").attr("readonly",true);
                    $("#supName").attr("readonly",true);
                    $("#supFName").attr("readonly",true);
                    $("#Status").prop("disabled",true);
                    $("#contactor").attr("readonly",true);
                    $("#mobile").attr("readonly",true);
                    $("#address").attr("readonly",true);
                }else{
                    $("#supName").attr("readonly",false);
                    $("#supFName").attr("readonly",false);
                    $("#Status").prop("disabled",false);
                    $("#contactor").attr("readonly",false);
                    $("#mobile").attr("readonly",false);
                    $("#address").attr("readonly",false);
                }
            }
        );
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
        currentPage = treeSearchs("/Supplier/GetAllSupInfo","SupRd","SupName","SupplierName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Supplier/GetAllSupInfo","SupRd","SupName","SupplierName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Supplier/GetAllSupInfo","SupRd","SupName","SupplierName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Supplier/GetAllSupInfo","SupRd","SupName","SupplierName",condition,currentPage,config);
    });
    $(".right").hide();
    var treeID = null;

    var rule = [
        {
         "ctlid": "supCode",
         "param": "SupCode"
        },{
        //供应商名称
        "ctlid": "supName", //自定义名字：标签id名字
        "param": "SupName" //规则中自定义的名字：对应报文中的id字段
    },{
        "ctlid": "supFName",
        "param": "SupFName"
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
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
                url: '/Supplier/GetAllSupInfo',
                data: {
                    "ExecType": "00",
                    "PageInfo":JSON.stringify(pageInfo),
                    "InitData": JSON.stringify({
                        "FiledList": [
                            {
                                "FieldName": "CreateTime DESC",
                                "FieldOpt": "Order BY"
                            }]})
                }
            },
            function (Body) {
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].SupRd == undefined ? "" : treeData[i].SupRd,
                        name: treeData[i].SupName == undefined ? "" : treeData[i].SupName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
        })
    };

    loaddata();

    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};

    $(".cAdd").click(function () {
        $(".right").show();
        clearForm("supplierForm");
        $("#ExecType").val("00");
        $("#supCode").attr("readonly",false);
        $("#supName").attr("readonly",false);
        $("#supFName").attr("readonly",false);
        $("#Status").prop("disabled",false);
        $("#contactor").attr("readonly",false);
        $("#mobile").attr("readonly",false);
        $("#address").attr("readonly",false);
        treeID=null;
    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            var DSource = $("#hidden").attr("h");
            if(DSource=="00"){
                toastr.warning("外部数据不能删除");
                return;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url:"/Supplier/SaveSupInfo",
                        data: {
                            "ExecType": "01",
                            "busData": "{\"SupRd\":" + treeID + "}"
                        }},
                        function (Body) {
                            layer.closeAll("dialog");
                            toastr.success(Body.MsgDes);
                            $("#ExecType").val("");
                            treeID = null;
                            currentPage=0;
                            condition = '';
                            loaddata();
                            $(".right").hide();
                        }
                    );
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    /*--------------顶部菜单点击操作中的复制按钮----------*/
    $("a[name='copy']").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            request({
                url:"/Supplier/SaveSupInfo",
                data: {
                    "ExecType": "03",
                    "busData": "{\"SupRd\":" + treeID + "}"
                }},
                function (Body) {
                    toastr.success(Body.MsgDes);
                    $("#ExecType").val("");
                    treeID = null;
                    currentPage=0;
                    condition = '';
                    loaddata();
                }
            );
        } else {
            toastr.warning("请选择左侧要复制的一项再进行复制!");
        }
    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        if($("#supName").val().trim()!= "" && $("#supFName").val().trim()!= "") {
            // 获取所有表单数据封装成json对象
            formData = transfer("supplierForm");
            var status = $("#Status").val();
            if ((treeID == null || treeID == "") && $("#ExecType").val() == "00") {
                newData = {
                    "SupCode": formData["supCode"],
                    "SupName": formData["supName"],
                    "SupFName": formData["supFName"],
                    "Contactor": formData["contactor"],
                    "Mobile": formData["mobile"],
                    "Address": formData["address"],
                    "Status": status,
                    "Remark": formData["remark"]
                };
                request({
                    url: "/Supplier/SaveSupInfo",
                    data: {
                        "ExecType": $("#ExecType").val(),
                        "busData": JSON.stringify(newData)
                    }},
                    function (Body) {
                        toastr.success("供应商信息新增成功,供应商代码{"+Body.Data.SupCode+"}");
                        currentPage=0;
                        condition = '';
                        loaddata();
                        $("#ExecType").val("");
                        $("#hidden1").attr("editid", Body.Data.SupRd);
                        treeID=Body.Data.SupRd;
                        $("#supCode").val(Body.Data.SupCode);
                        backfill(treeID);
                    }
                );
            }
            //编辑制造商
            else if (treeID != null && treeID != "") {

                newData = {
                    "SupRd": treeID,
                    "SupName": formData["supName"],
                    "SupFName": formData["supFName"],
                    "Contactor": formData["contactor"],
                    "Mobile": formData["mobile"],
                    "Address": formData["address"],
                    "Status": status,
                    "Remark": formData["remark"]
                };
               request({
                    url:"/Supplier/SaveSupInfo",
                    data: {
                        "ExecType":"02",
                        "busData": JSON.stringify(newData)
                    }},
                   function (Body) {
                       currentPage=0;
                       condition = '';
                       loaddata();
                       $("#ExecType").val("");
                       toastr.success(Body.MsgDes);
                       backfill(treeID);
                    }
                );
            }
        }else{
            if ($("#supName").val().trim() == "") {
                $("#supName").css("border-color", "red").prop("placeholder", "不能为空！");
            }
            if ($("#supFName").val().trim() == "") {
                $("#supFName").css("border-color", "red").prop("placeholder", "不能为空！");
            }
        }
    });
    //数据回填
    function backfill(treeID){
        $(".right").show();
        $("#supCode").attr("readonly",true);
        var objBusData = JSON.stringify({"SupRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({
                url:"/Supplier/GetSupInfo",
                data: objData},
            function (Body) {
                fillform("supplierForm", rule,Body.Data);
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
                    $("#supCode").attr("readonly",true);
                    $("#supName").attr("readonly",true);
                    $("#supFName").attr("readonly",true);
                    $("#Status").prop("disabled",true);
                    $("#contactor").attr("readonly",true);
                    $("#mobile").attr("readonly",true);
                    $("#address").attr("readonly",true);
                }else{
                    $("#supName").attr("readonly",false);
                    $("#supFName").attr("readonly",false);
                    $("#Status").prop("disabled",false);
                    $("#contactor").attr("readonly",false);
                    $("#mobile").attr("readonly",false);
                    $("#address").attr("readonly",false);
                }
            }
        );
    }
});
