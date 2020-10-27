$(function () {
    //表格
    var colNamesArr = [
        {"Caption": "批次", "Name": "Batch"},
        {"Caption": "数量", "Name": "Num"},
        {"Caption": "状态", "Name": "Status", "Editable": false, "CType": "text"},
        {"Caption": "工序", "Name": "SpecName", "Editable": false, "CType": "text"},
        {"Caption": "工单", "Name": "WoCode", "Editable": false, "CType": "text"},
        {"Caption": "产品代码", "Name": "MaCode", "Editable": false, "CType": "text"}
    ];
    //Commom/GetCMBInfo
    var obj = {
        data:[],
        colArr: colNamesArr,
        tableId: "list3",
        multiselect: false,
        width:0.84,
        height:0.528
    };
    fullTable(obj);
    var obj5 = {
        data:[],
        colArr: colNamesArr,
        tableId: "list5",
        multiselect: true,
        width:0.84,
        height:0.528
    };
    fullTable(obj5);
    var colNamesArr1 = [
        {"Caption": "载具编码", "Name": "CarrierRd"},
        {"Caption": "载具编码", "Name": "CarrierName"}
    ];

    var obj1 = {
        data:[],
        colArr: colNamesArr1,
        tableId: "list4",
        multiselect: false,
        width:0.84,
        height:0.528
    };
    fullTable(obj1);

    //当选择一批多载具的时候存放的数据，即单个批次信息
    var singsBatch="";
    var zjbmid=null;
    var zjbmstatus=null;
    //卸载的时候存放数据
    var xzdata="";
    $(document).keydown(function (event) {

        if (event.keyCode == 13) {
            //载具
            if ($("#zjbm").is(":focus")) {
                var inputValue = $("#zjbm").val().trim();

                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CarrierName",
                            "FieldOpt": "=",
                            "FieldVal": inputValue
                        }
                    ]
                };
                request({
                    url: "/Carrier/GetAllCarriersInfo",
                    data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    if (Body.Data.length <= 0) {
                        toastr.warning("该载具信息不存在");
                        return false;
                    } else {
                        $("#zjbm").val(Body.Data[0].CarrierName)
                        zjbmid = Body.Data[0].CarrierRd;
                        zjbmstatus = Body.Data[0].Status;
                    }

                })

            }

            //批次
            if ($("#pc").is(":focus")) {
                //处理自动装载
                if ($("#check").is(':checked') && $("#sing").prop("checked") && !$("#double").prop("checked")) {


                    if ("01"!=zjbmstatus) {
                        var zjbm = $("#zjbm").val().trim();
                        if (zjbm == null || zjbm == "") {
                            toastr.warning("该载具信息不存在");
                            return false;
                        }
                        var CarrierInfos = [];
                        var CarrierInfo = {
                            "CarrierRd": zjbmid
                        }
                        CarrierInfos.push(CarrierInfo);
                        var inputValue = $("#pc").val().trim();
                        var InitData = {
                            "Batch": inputValue
                        }
                        request({
                            url: "/Commom/GetCMBInfo",
                            data: {"ExecType": "00", "busData": JSON.stringify(InitData)}
                        }, function (Body) {
                            if (Body.Data.length <= 0) {
                                toastr.warning("该批次信息不存在");
                                return false;
                            } else {
                                /* for(var i in List3){
                                     delete List3[i].Num;
                                     delete List3[i].Status;
                                     delete List3[i].SpecName;
                                     delete List3[i].WoCode;
                                     delete List3[i].MaCode;
                                 }*/
                                var BInfos = [];
                                var BInfo = {
                                    "Batch": Body.Data.Batch
                                }
                                BInfos.push(BInfo);
                                var newData = {
                                    "CarrierInfo": CarrierInfos,
                                    "BInfo": BInfos,
                                };
                                request({
                                    url: "/CarrierRelation/SaveCarrierRelationInfo",
                                    data: {"ExecType": "00", "busData": JSON.stringify(newData)}
                                }, function (Body) {
                                    toastr.success(Body.MsgDes);
                                });

                                $("#zjbm").val("");
                                $("#zjbm").focus();
                                $("#pc").val("");
                                var obj = {
                                    data:[],
                                    colArr: colNamesArr,
                                    tableId: "list3",
                                    multiselect: false,
                                    width:0.84,
                                    height:0.528
                                };
                                fullTable(obj);


                            }
                        })
                    } else {

                        toastr.warning("该载具已上载具，请卸载载具");
                        return false;
                    }


                }
                //处理一载具装多批
                else if (!$("#check").is(':checked') && $("#sing").prop("checked") && !$("#double").prop("checked")) {

                    if ("01"!=zjbmstatus) {
                        var zjbm = $("#zjbm").val().trim();
                        if (zjbm == null || zjbm == "") {
                            toastr.warning("该载具信息不能为空");
                            return false;
                        }
                        var CarrierInfos = [];
                        var CarrierInfo = {
                            "CarrierRd": zjbmid
                        }
                        CarrierInfos.push(CarrierInfo);
                        var inputValue = $("#pc").val().trim();
                        var InitData = {
                            "Batch": inputValue
                        }
                        var List3 = getRowData("list3");
                        for (var j in List3) {
                            if (List3[j].Batch == $("#pc").val().trim()) {
                                toastr.warning("该批次信息已存在");
                                return false;
                            }
                        }

                        request({
                            url: "/Commom/GetCMBInfo",
                            data: {"ExecType": "00", "busData": JSON.stringify(InitData)}
                        }, function (Body) {
                            if (Body.Data.length <= 0) {
                                toastr.warning("该批次信息不存在");
                                return false;
                            } else {
                                $("#pc").val(Body.Data.Batch)
                                var states="";
                                if(Body.Data.Status=="00"){
                                    states="创建"
                                }else if(Body.Data.Status=="01"){
                                    states="下达"
                                }else if(Body.Data.Status=="02"){
                                    states="取消"
                                }else if(Body.Data.Status=="03"){
                                    states="处理中"
                                }else if(Body.Data.Status=="04"){
                                    states="冻结"
                                }else if(Body.Data.Status=="05"){
                                    states="终止"
                                }else if(Body.Data.Status=="06"){
                                    states="完成"
                                }else {
                                    states="关闭"
                                }

                                var allSources=[];
                                var sources={
                                    "Batch":Body.Data.Batch,
                                    "Num":Body.Data.Num,
                                    "Status":states,
                                    "SpecName":Body.Data.WFInfo==null?"":Body.Data.WFInfo.CSpecInfo.SpecName,
                                    "WoCode":Body.Data.WoCode,
                                    "MaCode":Body.Data.MaCode,
                                }
                                addSrow("list3", sources, "first");
                                $("#pc").focus();
                                $("#pc").val("");
                            }})
                    } else {

                        toastr.warning("该载具已上载具，请卸载载具");
                        return false;
                    }
                }

                else {
                    var List3 = getRowData("list3");
                    for (var j in List3) {
                        if (List3[j].Batch == $("#pc").val().trim()) {
                            toastr.warning("该批次信息已存在");
                            return false;
                        }
                    }
                }
            }
            if ($("#pc1").is(":focus")) {
                var binputvalue=$("#pc1").val().trim();
                var InitData = {
                    "Batch": binputvalue
                }
                request({
                    url: "/Commom/GetCMBInfo",
                    data: {"ExecType": "00", "busData": JSON.stringify(InitData)}
                }, function (Body) {
                    if (Body.Data.length <= 0) {
                        toastr.warning("该批次信息不存在");
                        return false;
                    } else {
                        singsBatch=Body.Data.Batch;
                    }})
            }

            //载具卸载
            if ($("#xzzjname").is(":focus")) {
                //自动卸载
                if ($("#autoxzzj").prop("checked")) {
                    if ($("#xzzjname").val().trim() == null || $("#xzzjname").val().trim() == "") {
                        toastr.warning("卸载失败，载具编码不能为空");
                        return false;
                    }
                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "CarrierName",
                                "FieldOpt": "=",
                                "FieldVal": $("#xzzjname").val().trim()
                            }
                        ]
                    };
                    request({
                        url: "/Carrier/GetAllCarriersInfo",
                        data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
                    }, function (Body) {
                        if (Body.Data.length <= 0) {
                            toastr.warning("该载具信息不存在");
                            return false;
                        } else {
                            $("#zjbm").val(Body.Data[0].CarrierName)
                            zjbmid = Body.Data[0].CarrierRd;
                            zjbmstatus = Body.Data[0].Status;


                        var newDatass={
                            "CarrierRd": zjbmid,
                            "BInfo":[],
                        }

                            request({url:"/CarrierRelation/SaveCarrierRelationInfo",data:{"ExecType": "01","busData": JSON.stringify(newDatass)}},function(Body){
                                toastr.success(Body.MsgDes);
                                $("#xzzjname").val("");
                                $("#xzzjname").focus();
                                var obj = {
                                    data:[],
                                    colArr: colNamesArr,
                                    tableId: "list3",
                                    multiselect: false,
                                    width:0.84,
                                    height:0.528
                                };
                                fullTable(obj);
                            });
                        }

                    })
                }else {
                 //卸载的时候不经过自动装载
                    if ($("#xzzjname").val().trim() == null || $("#xzzjname").val().trim() == "") {
                        toastr.warning("卸载失败，载具编码不能为空");
                        return false;
                    }
                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "CarrierName",
                                "FieldOpt": "=",
                                "FieldVal": $("#xzzjname").val().trim()
                            }
                        ]
                    };
                    request({
                        url: "/Carrier/GetAllCarriersInfo",
                        data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
                    }, function (Body) {
                        if (Body.Data.length <= 0) {
                            toastr.warning("该载具信息不存在");
                            return false;
                        } else {
                            var CarrierRd = Body.Data[0].CarrierRd;
                            var data={
                                "CarrierRd":CarrierRd
                            }
                            xzdata=CarrierRd;

                            request({
                                url: "/CarrierRelation/GetCarrierRelationInfo",
                                data: {"ExecType": "00", "BusData": JSON.stringify(data)}
                            }, function (Body) {
                                if (Body.Data.length <= 0) {
                                    toastr.warning("该载具没有批次信息");
                                    return false;
                                } else {


                                  //  alert(JSON.stringify(Body.Data));
                                    for(var i  in  Body.Data){
                                        var Batch=Body.Data[i].Batch;
                                        request({
                                            url: "/Commom/GetCMBInfo",
                                            data: {"ExecType": "00", "BusData":JSON.stringify({"Batch":Batch}) }
                                        }, function (Body) {
                                            if (Body.Data.length <= 0) {
                                                toastr.warning("该批次信息不存在");
                                                return false;
                                            } else {
                                                var states="";
                                                if(Body.Data.Status=="00"){
                                                    states="创建"
                                                }else if(Body.Data.Status=="01"){
                                                    states="下达"
                                                }else if(Body.Data.Status=="02"){
                                                    states="取消"
                                                }else if(Body.Data.Status=="03"){
                                                    states="处理中"
                                                }else if(Body.Data.Status=="04"){
                                                    states="冻结"
                                                }else if(Body.Data.Status=="05"){
                                                    states="终止"
                                                }else if(Body.Data.Status=="06"){
                                                    states="完成"
                                                }else {
                                                    states="关闭"
                                                }

                                                var allSources=[];
                                                var sources={
                                                    "Batch":Body.Data.Batch,
                                                    "Num":Body.Data.Num,
                                                    "Status":states,
                                                    "SpecName":Body.Data.WFInfo==null?"":Body.Data.WFInfo.CSpecInfo.SpecName,
                                                    "WoCode":Body.Data.WoCode,
                                                    "MaCode":Body.Data.MaCode,
                                                }
                                                addSrow("list5", sources, "first");
                                                $("#xzzjname").focus();
                                                $("#xzzjname").val("");
                                            }})
                                    }
                                }
                            })
                        }
                    })
                }
            }
            //一批多载具事件
            if ($("#zjbm2").is(":focus")){
                //点击一批装多载具
                if (!$("#check").is(':checked') && !$("#sing").prop("checked") && $("#double").prop("checked")) {
                    if ($("#zjbm2").val().trim() == null || $("#zjbm2").val().trim() == "") {
                        toastr.warning("卸载失败，载具编码不能为空");
                        return false;
                    }

                    var List4 = getRowData("list4");
                    for (var j in List4) {
                        if (List4[j].CarrierName == $("#zjbm2").val().trim()) {
                            toastr.warning("该载具信息已存在");
                            return false;
                        }
                    }

                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "CarrierName",
                                "FieldOpt": "=",
                                "FieldVal": $("#zjbm2").val().trim()
                            }
                        ]
                    };
                    request({
                        url: "/Carrier/GetAllCarriersInfo",
                        data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
                    }, function (Body) {
                        if (Body.Data.length <= 0) {
                            toastr.warning("该载具信息不存在");
                            return false;
                        } else {

                            if(Body.Data[0].Status){
                                toastr.warning("该载具已上载具，请卸载载具");
                                $("#zjbm2").val("");
                                $("#zjbm2").focus();
                                return false;
                            }else {
                                var aa={
                                    "CarrierRd": Body.Data[0].CarrierRd,
                                    "CarrierName":Body.Data[0].CarrierName
                                }
                                addSrow("list4", aa, "first");
                            }
                            $("#zjbm2").val("");
                            $("#zjbm2").focus();
                            }

                    })



                }


                //自动一批装多载具
                if ($("#check").is(':checked') && !$("#sing").prop("checked") && $("#double").prop("checked")) {
                    if ($("#zjbm2").val().trim() == null || $("#zjbm2").val().trim() == "") {
                        toastr.warning("卸载失败，载具编码不能为空");
                        return false;
                    }

                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "CarrierName",
                                "FieldOpt": "=",
                                "FieldVal": $("#zjbm2").val().trim()
                            }
                        ]
                    };
                    request({
                        url: "/Carrier/GetAllCarriersInfo",
                        data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
                    }, function (Body) {
                        if (Body.Data.length <= 0) {
                            toastr.warning("该载具信息不存在");
                            return false;
                        } else {
                            if(Body.Data[0].Status){
                                toastr.warning("该载具已上载具，请卸载载具");
                                $("#zjbm2").val("");
                                $("#zjbm2").focus();
                                return false;
                            }else {
                                var BInfos=[];
                                var BInfo={
                                    "Batch":singsBatch
                                }
                                BInfos.push(BInfo);

                                var CarrierDatas=[];
                                var CarrierData={
                                    "CarrierRd": Body.Data[0].CarrierRd
                                }
                                CarrierDatas.push(CarrierData)
                                var newData = {
                                    "CarrierInfo": CarrierDatas,
                                    "BInfo": BInfos,
                                };
                                request({url:"/CarrierRelation/SaveCarrierRelationInfo",data:{"ExecType": "00","busData": JSON.stringify(newData)}},function(Body){
                                    toastr.success(Body.MsgDes);
                                    $("#pc1").val("");
                                    $("#zjbm2").val("");
                                    $("#zjbm2").focus();
                                    var obj = {
                                        data:[],
                                        colArr: colNamesArr,
                                        tableId: "list4",
                                        multiselect: false,
                                        width:0.84,
                                        height:0.528
                                    };
                                    fullTable(obj);
                                });
                            }
                        }
                    })
                }
            }
        }


        }
  );

    $("#nav_tab4").click(function(){
        $("._zhuangzai").attr("a","2");
        $("._zhuangzai").hide();
        $(".saves").show();
    });

    $("#nav_tab3").click(function(){
        $(".saves").hide();
        $("._zhuangzai").show();
    });


    //装载保存
    $("._zhuangzai").click(function(){;
        if("01"!=zjbmstatus){
            if($("#sing").prop("checked")){
                var zjbm=$("#zjbm").val().trim();
                if(zjbm==null||zjbm==""){
                    toastr.warning("该载具信息不能为空");
                    return false;
                }
                var List3=getRowData("list3");
                if(List3==null||List3==""||List3.length<=0){
                    toastr.warning("该载具信息不存在");
                    return false;
                }
                for(var i in List3){
                    delete List3[i].Num;
                    delete List3[i].Status;
                    delete List3[i].SpecName;
                    delete List3[i].WoCode;
                    delete List3[i].MaCode;
                }
                var CarrierInfos=[];
                var CarrierInfo={
                    "CarrierRd":zjbmid
                }
                CarrierInfos.push(CarrierInfo);
                var newData = {
                    "CarrierInfo": CarrierInfos,
                    "BInfo": List3,
                };
                request({url:"/CarrierRelation/SaveCarrierRelationInfo",data:{"ExecType": "00","busData": JSON.stringify(newData)}},function(Body){
                    toastr.success(Body.MsgDes);
                    $("#zjbm").val("");
                    $("#zjbm").focus();
                    $("#pc").val("");
                    var obj = {
                        data:[],
                        colArr: colNamesArr,
                        tableId: "list3",
                        multiselect: false,
                        width:0.84,
                        height:0.528
                    };
                    fullTable(obj);

                });
            }
        }else {
            toastr.warning("该载具已上载具，请卸载载具");
            return false;
        }
        //处理点击多批装一载具的保存数据
        if($(this).attr("a")=="1"){
            var BInfos=[];
            var BInfo={
                "Batch":singsBatch
            }
            BInfos.push(BInfo);


            var List4=getRowData("list4");
            if(List4==null||List4==""||List4.length<=0){
                toastr.warning("该载具信息不存在");
                return false;
            }
            var CarrierInfos=[];
            for(var i in List4){
                delete List4[i].CarrierName;
                var CarrierInfo={
                    "CarrierRd":List4[i].CarrierRd
                }
                CarrierInfos.push(CarrierInfo);
            }

            var newData = {
                "CarrierInfo": CarrierInfos,
                "BInfo": BInfos,
            };
            request({url:"/CarrierRelation/SaveCarrierRelationInfo",data:{"ExecType": "00","busData": JSON.stringify(newData)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#zjbm2").val("");
                $("#pc1").val("");
                $("#pc1").focus();
                var obj = {
                    data:[],
                    colArr: colNamesArr,
                    tableId: "list4",
                    multiselect: false,
                    width:0.84,
                    height:0.528
                };
                fullTable(obj);

            });


        }


        //处理卸载的保存事件
        if($(this).attr("a")=="2"&&!$("#autoxzzj").prop("checked")){

            console.log("不执行，无条件")
        }



        /*if($("#double").prop("checked")){
            alert(2)
        }*/
    });

    //list5表格删除
    $(".del1").click(function(){
        derow("list5");
    });
    //卸载保存
    $(".saves").click(function(){

        var List5 = getRowData("list5");
        if(List5.length<=0){
            toastr.warning("卸载失败，批次不能为空");
            return false;
        }
       var sources=[];

        for (var j in List5) {
            var source={
                "Batch":List5[j].Batch
            }
            sources.push(source)
        }
        var InitData={
            "CarrierRd":xzdata,
            "BInfo":sources
        }

        request({url:"/CarrierRelation/SaveCarrierRelationInfo",data:{"ExecType": "01","busData": JSON.stringify(InitData)}},function(Body){
            toastr.success(Body.MsgDes);
            $("#xzzjname").val("");
            $("#xzzjname").focus();
            var obj = {
                data:[],
                colArr: colNamesArr,
                tableId: "list5",
                multiselect: false,
                width:0.84,
                height:0.528
            };
            fullTable(obj);
        });


    });

    $("#double").click(function(){
        $("._zhuangzai").attr("a","1");

        $("#pcxx").hide();
        $("#zjxx").show();
        $("#zjbm").val("");
        $("#zjbm").focus();
        $("#pc").val("");
        $("#b1").show();
        $("#b2").show();
        $("#a1").hide();
        $("#a2").hide();
    });
    $("#sing").click(function(){
        $("._zhuangzai").attr("a","");
        $("#pcxx").show();
        $("#zjxx").hide();
        $("#zjbm").val("");
        $("#zjbm").focus();
        $("#pc").val("");
        $("#b1").hide();
        $("#b2").hide();
        $("#a1").show();
        $("#a2").show();
    });






});