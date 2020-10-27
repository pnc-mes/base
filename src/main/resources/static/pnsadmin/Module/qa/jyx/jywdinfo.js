$(function(){

    //保存物料的所有信息
    var CDocInfo =[];
    var wgBadInfos = [];
    var busData=[];
    var maInfo = {};
    var obj1 =[];
    var name ="";
    var f=0;
    var id=null;
    var test=null;
    var newDatas=[]; //获取一开始的所有物料信息
    //右边树节点
    //定义全局变量id 代表节点的id
    var treeID=null;
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CDOCRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url: "/CDOC/GetCDOCInfo", data: objData,}, function (Body) {
            $("#CheckDocName").val(Body.Data.CheckDocName);
            $("#sliname").val(Body.Data.CheckDocName);
            $("#slinameRd").val(Body.Data.CDOCRd);
            if(Body.Data.RelType=="00"){
                $("#material").showData({
                    id:Body.Data.RelRd,
                    name:Body.Data.RelName,
                    keyfield:"RelRd",
                    fields:[
                        {"name":"RelRd"},
                        {"name":"RelName"}
                    ]
                });
            }else {
                maType = Body.Data.RelName;
                $("#type").val(Body.Data.RelName == null ? "":Body.Data.RelName);
                $("#MaTypeRd").val(Body.Data.RelRd == null ? "":Body.Data.RelRd);

            }
            var guanliantype=Body.Data.RelType;
            if(guanliantype=="00"){
                $("#guanlian1").show();
                $("#guanlian2").hide();
                $("#correlationtype").val("00");
            }else {
                $("#guanlian1").hide();
                $("#guanlian2").show();
                $("#correlationtype").val("01");
            }

            if (Body.Data.CDocInfo.length > 0) {
                var busData = Body.Data.CDocInfo;
                var CTDtlInfo = [];

                for (var i = 0; i < busData.length; i++) {
                 //   $("#uploadForm").text(busData[i].FileName)file
                    $("#file").text(busData[i].FileName)
                }

            }
 /*           $("#exit").hide();
            $("#exit1").show();
            if (Body.Data.CDocInfo.length > 0) {
                var busData = Body.Data.CDocInfo;
                var CTDtlInfo = [];

                for (var i = 0; i < busData.length; i++) {
                    //   $("#uploadForm").text(busData[i].FileName)file
                    $("#exit2").text(busData[i].FileName)
                }

            } else{
                $("#exit2").text("");
            }
*/
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
        "PageSize": 20,
        "PageIndex": currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/CDOC/GetAllCDOCInfo", "CDOCRd", "CheckDocName", "CheckDocName", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function (event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/CDOC/GetAllCDOCInfo", "CDOCRd", "CheckDocName", "CheckDocName", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/CDOC/GetAllCDOCInfo", "CDOCRd", "CheckDocName", "CheckDocName", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/CDOC/GetAllCDOCInfo", "CDOCRd", "CheckDocName", "CheckDocName", condition, currentPage, config);
    });


    //上传添加
    $(document).on("click","#add",function(){
        var str_tr = "<tr>"+$(this).parents("tr").html()+"</tr>";
        //js 替换字符串样式
        str_tr = str_tr.replace(/\+/,'-');
        var new_str_tr = str_tr.replace(/add/,'del');
        $(this).parents("tr").after(new_str_tr);
    })
    //上传删除
    $(document).on("click","#del",function(){
        $(this).parents("tr").remove();
    })
    //文件上传
    $("#but").click(function(){
        var formData = new FormData($( "#uploadForm" )[0]);
        $.ajax({
            url: 'http://localhost:8080/mesadmin/AppSet/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                if (returndata.Body.MsgCode == "0x00000") {
                    var busData = {
                        "file": returndata.Body.Data.file,
                        "fileName":returndata.Body.Data.fileName
                    };
                    CDocInfo.push(busData);
                    toastr.success("上传完成！");
                }
            },
            error: function (returndata) {
                toastr.error("上传失败！");
            }
        });
    })

    //关联类型事件
    $("#correlationtype").change(function(){
        //要触发的事件
        var aa =$(this).val();
        if(aa=="00"){
            $("#guanlian1").show();
            $("#guanlian2").hide();
        }else if ($(this).val()=="01"){
            $("#guanlian1").hide();
            $("#guanlian2").show();
        }
    });

    var InitData = {
        "FiledList":[
            {
                "FieldName":"MaterialType",
                "FieldOpt":"=",
                "FieldVal":""
            },
            {
                "FieldName":"MaTypeRd",
                "FieldOpt":"=",
                "FieldVal":""
            },
            {
                "FieldName":"IsDef",
                "FieldOpt":"=",
                "FieldVal":"00"
            },
            {
                "FieldName":"MaterialName",
                "FieldOpt":"order by"
            }
        ]
    };
    //点击新增
    $(".cAdd").click(function () {
        treeID = null;
        $("#CheckDocName").val("");
        $("#_right").show();
        //点击新增默认的关联类型为物料，并且显示物料编码
        $("#correlationtype").val("00");
        $("#guanlian1").show();
        $("#guanlian2").hide();
        $("#material").clearseldata("MaVerRd");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
/*        $("#exit").show();
        $("#exit1").hide();*/
      // $("#uploadForm").show();
    });
    var rootNode=[];
    var WFInfoData=[];
    //物料类型相关事件
    var clic = function (nodeinfo, handle) {
        App.unblockUI("body");
        //if("00" != nodeinfo.nodeID && "01" != nodeinfo.nodeID && "02" != nodeinfo.nodeID && "03" != nodeinfo.nodeID){
        $("#MaTypeRd").val(nodeinfo.nodeID);
        $("#type").val(nodeinfo.nodeText);

        // if(f==1){
        te=nodeinfo.nodeID;
        if(te!=""&&te!=null){
            //物料扩展字段页面加载数据
            var  datas={
                "MTRd":te
            }
            request({
                url:'/Expand/GetDWExpandInfo',
                data:{"ExecType":"00","BusData":JSON.stringify(datas)}},function (Body) {
                var data=Body.Data;
                if(data.length>0){
                    $("#MType").attr("style","display:block;");
                }else {
                    $("#MType").attr("style","display:none;");
                }
                maps=[];
                var j=1;
                data.forEach(function (e) {
                    maps.push(e.FieldName);
                    if(e.FiledType=="00"){
                        $(".formdata").append(e.DisplayName+"&ensp;"+"<input class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        if(e.Val!=null){
                            $("#"+e.FieldName).val(e.Val);
                        }else {
                            $("#"+e.FieldName).val();
                        }
                        if(j%2==0){
                            $(".formdata").append("<tr>");
                        }
                    }
                    if(e.FiledType=="01"){
                        $(".formdata").append(e.DisplayName+"&ensp;"+" <select class=' formgroup maBottom' id="+e.FieldName+" name="+e.DisplayName+" ><option>"+''+"</option></select>&ensp;&ensp;");
                        if(e.Option!=undefined&&e.Option!=null){
                            e.Option.forEach(function (s) {
                                var option=$("<option value="+s.Val+">"+s.DisplayName+"</option>");
                                $("#"+e.FieldName).append(option);
                            });
                        }
                        $("#"+e.FieldName).val(e.Val);
                        if(j%2==0){
                            $(".formdata").append("<tr>");
                        }
                    }
                    j++

                });

            });
        }
        if ("00" == nodeinfo.nodeID) {
            str = "00";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("01" == nodeinfo.nodeID) {
            str = "01";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
        }
        if ("02" == nodeinfo.nodeID) {
            str = "02";
            //隐藏工艺流程下面的信息
            $("#a_tab_4").css("display","none");
            $("#tab_4").removeClass("active");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","none");
        }
        if ("03" == nodeinfo.nodeID) {
            str = "03";
            $("#a_tab_4").css("display","block");
            $("#a_tab_1").addClass("active").siblings().removeClass("active");
            $("#tab_1").addClass("active").siblings().removeClass("active");
            //$("#tab_4").css("display","block");
        }
        InitData.FiledList[0].FieldVal = str;
        if (nodeinfo.isRoot) {  //父节点
            var MaTypeInitData = {
                "FiledList": [
                    {
                        "FieldName": "MaterialType",
                        "FieldOpt": "=",
                        "FieldVal": str
                    }
                ]
            };
            request({url: '/MaType/GetAllMTInfo', data: {"ExecType": "00", "InitData": JSON.stringify(MaTypeInitData)}}, function (Body) {
                var treeData = Body.Data.CMTInfo;
                handle.ckAddChild(rules, treeData);
            });
        }
        if(str == "00" || str == "01"){
            $("#maBatch").prop("checked", true);
            $("#maBatch").attr("disabled",true);
            //$("#MaBarCode").val("");
            $("#MaBarCode").attr("readonly",true);
            $("#dd3").css("display", "");
        }else{
            if($("#maBatch").is(":checked")){
                //$("#MaBarCode").val("");
                $("#MaBarCode").attr("readonly",true);
            }else{
                //$("#MaBarCode").val("");
                $("#MaBarCode").attr("readonly",false);
            }
            $("#maBatch").attr("disabled", false);
        }
    };
    var rules = [{
        id: "MTRd",
        text: "MTName",
        children: "CMTInfo"
    },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        },
        {
            id: "MTRd",
            text: "MTName",
            children: "CMTInfo"
        }
    ];
    var tCon = {
        id: "t",
        data: {
            source: [],
            rule: [{
                id: "id",
                text: "name"
            }]
        },
        event: {
            onClick: clic

        }
    };
    var list = [];
    var f = true;
    var a = function () {
        var treedataList = [];
        request({url: '/Material/GetMaTypeInfo', data: {"ExecType": "00"}}, function (Body) {
            var treeData = Body.Data;
            for (var i = 0; i < treeData.length; i++) {
                var tree = {
                    id: treeData[i].MaType == undefined ? "" : treeData[i].MaType,
                    name: treeData[i].TName == undefined ? "" : treeData[i].TName
                }
                treedataList.push(tree);
            }
            tCon.data.source = treedataList;
            if(f){
                list = treedataList;
                f = false;
            }
            $.JstreeEx.init(tCon);//先调用后加载
        });
    };
    a();
    var ff = true;
    $("#t").on("dblclick","a",function () {
        var a_Arr = $(this).attr("id").split("_");
        var id = a_Arr[a_Arr.length - 2];
        //if("00" != id && "01" != id && "02" != id && "03" != id){
        ff = true;
        $("#t").css("display","none");
        //}
    });
    $("#t").on("click",function (e) {
        e.stopPropagation();
    });
    $("#type").on({
        "focus": function () {
            if(ff){
                $("#t").css("display", "block");
                tCon.data.source = list;
                $.JstreeEx.init(tCon);
                ff = false;
            }
        },
        "click":function (e) {
            e.stopPropagation();
            if(ff) {
                $("#t").css("display", "block");
                tCon.data.source = list;
                $.JstreeEx.init(tCon);
            }
        },
        "keydown":function (e) {
            if(e.keyCode == 8){
                $(this).val("");
                $("#MaTypeRd").val("");
            }else{
                //$(this).val(inVal);
            }
        }

    })
    $("body").on("click",function () {
        ff = true;
        $("#t").css("display","none");
    });


    //加载树列表
    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        request({
            url: '/CDOC/GetAllCDOCInfo',
            data: {
                "ExecType": "00",
                "PageInfo": JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CheckDocName",
                            "FieldOpt": "Order BY"
                        }]
                })
            }
        }, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].CDOCRd == undefined ? "" : treeData[i].CDOCRd,
                    name: treeData[i].CheckDocName == undefined ? "" : treeData[i].CheckDocName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();

    function f1() {
        var material = {
            "displaymode": "1",
            "title": "物料",
            "binddata": {
                "keyfield": "MaVerRd",
                "fields": [

                    {
                        "caption": "MaVerRd",
                        "name": "MaVerRd"
                    }, {
                        "caption": "物料代码",
                        "name": "MaCode"
                    }, {
                        "caption": "物料名称",
                        "name": "MaName"
                    }
                ]
            },
            "showresult": {"ishead": true},
            "event": {
                "onseardata": function (o) {
                    var InitData = {
                        "FiledList": [
                            {
                                "FieldName": "MaterialName",
                                "FieldOpt": "like",
                                "FieldVal": "%" + excludeSpecial(o.condition) + "%"
                            }, {
                                "FieldName": "MaterialName",
                                "FieldOpt": "Order BY"
                            },
                            {
                                "FieldName": "MaterialType",
                                "FieldOpt": "in",
                                "FieldVal": "(01,00)"
                            }
                        ]
                    };

                    var obj = {
                        data: {"ExecType": "00", "InitData": JSON.stringify(InitData)},
                        url: "/Material/GetAllMaInfo"
                    };
                    var xldata = [];
                    request(obj, function (Body) {
                        var datas = Body.Data;
                        for (var i = 0; i < datas.length; i++) {
                            var data = {
                                "MaVerRd": datas[i].MaVerRd,
                                "MaCode": datas[i].MaCode,
                                "MaName": datas[i].MaName,
                                "MaDes": datas[i].MaDes
                            };
                            xldata.push(data);
                        }
                    });
                    var obj = {
                        data: xldata,
                        showrow: 50
                    };
                    return obj;
                }
            }
        };

        $("#material").zc_select(material);


    }
    f1();


    /*--------------顶部菜单点击新增按钮----------*/
    var formData = {};
    loaddata();
    $(".cAdd").click(function () {
        // $("#Status").val("00");
        $("#ExecType").val("00");
        //  $("#djpdlx").val("00");
        //   treeID = null;
        $("#_right").show();
        $("#sliname").val("");
        $("#slinameRd").val("");
        $("#MTNameifon").val("");
        $("#replaceMaInfo").val("");
        $("#guanlian1").val("");
        $("#guanlian2").val("");

        treeID = null;
        $("#CheckDocName").val("");

        //点击新增默认的关联类型为物料，并且显示物料编码
        $("#correlationtype").val("00");
        $("#guanlian1").show();
        $("#guanlian2").hide();
        $("#material").clearseldata("MaVerRd");

        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
    });
    /*--------------顶部菜单点击编辑按钮----------*/
    $(".cEdit").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != "") {
            $("#ExecType").val("02");
            $("#cpName").attr("disabled", false);
        } else {
            toastr.warning("请选择左侧要编辑的一项再进行编辑!");
        }

    });

    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({
                        url: "/CDOC/SaveCDOCInfo",
                        data: {"ExecType": "01", "busData": "{\"CDOCRd\":" + treeID + "}"}
                    }, function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        treeID = null;
                        $("#ExecType").val("");
                        currentPage = 0;
                        condition = '';
                        loaddata();
                        $("#_right").hide();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });
    // 复制
    $("a[name='copy']").click(function () {

        $("#myModal").modal("show");
    });
    $("#save").click(function () {
        var objBusData = $("#slinameRd").val();
        if (objBusData == null || objBusData == "") {
            toastr.warning("请选择复制内容！");
            return false;
        }
        var CheckDocName = "";
        CheckDocName = $("#sliname").val();
        var newData = {"CDOCRd": treeID, "CheckDocName": CheckDocName};
        request({
            url: "/CDOC/SaveCDOCInfo",
            data: {"ExecType": "03", "busData": JSON.stringify(newData)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
            $("#sliname").val('');
            loaddata();
        });
        $("#myModal").modal("hide");
    });
    /*--------------顶部菜单点击保存按钮----------*/
    $(".cSave").click(function () {
        var MaRd =  $("#guanlian1").getseldata().MaVerRd;
        var MTRd =  $("#guanlian2").getseldata().MaType;
        var CheckDocName = $("#CheckDocName").val().trim();
        if (CheckDocName == null || CheckDocName == "") {
            toastr.warning("检验文档名称不能为空");
            return false;
        }
        var correlationtype = $("#correlationtype").val();
      /*  if (correlationtype == null || correlationtype == "") {
            toastr.warning("关联类型不能为空");
            return false;
        }*/
        var list4Data = getTableData("list4");
        var Remark = $("#beizhu").val().trim();
        var newData = {};
        //新增
        if ((treeID == null || treeID == "") && $("#ExecType").val() == "00"){
            var wglist4Data = [];
            var CDocInfo1 = [];
            var obj1=[];
            if(CDocInfo.length>0){
                var j =1;
                for(var i in CDocInfo){
                    var obj = {
                        "FileUrl":CDocInfo[i].file ,
                        "FileName":CDocInfo[i].fileName
                    };
                    obj1.push(obj);
                    j++;
                }
            }
            if(obj1 ==null || obj1 ==undefined ){
                //        toastr.warning("请先上传文件");
                return toastr.warning("请先上传文件");
            }

            var RelRd="";

            if(correlationtype=="00"){
                RelRd= MaRd;
            }else{
                RelRd=$("#MaTypeRd").val();
            }
           /* if (RelRd == null || RelRd == "") {
                toastr.warning("产品或者物料类别不能为空");
                return false;
            }*/
                newData = {
                    "CheckDocName": CheckDocName,
                    "RelType": correlationtype,
                    "RelRd":RelRd,
                    "CDocInfo":obj1,
                    "Remark": Remark,
                };
            request({
                url: "/CDOC/SaveCDOCInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        }

        //修改
        else if (treeID != null && treeID != "") {
            var boj2=[];
            if(boj2 ==null || boj2 ==undefined  ){
                //    toastr.warning("请先上传文件");
            }
            if(CDocInfo.length>0){
                var j =1;
                for(var i in CDocInfo){
                    var obj = {
                        "FileUrl":CDocInfo[i].file ,
                        "FileName":CDocInfo[i].fileName
                    };
                    boj2.push(obj);
                    j++;
                }
            }

            if(correlationtype=="00"){
                RelRd= MaRd;
            }else{
                RelRd=$("#MaTypeRd").val();
            }
            /*if (RelRd == null || RelRd == "") {
                toastr.warning("初次修改检验文档信息，请重新选择物料编码保证信息正确");
                return false;
            }*/
                newData = {
                    "CDOCRd":treeID,
                    "CheckDocName": CheckDocName,
                    "RelType": correlationtype,
                    "RelRd":RelRd ,
                    "CDocInfo":boj2,
                    "Remark": Remark,
                };
            console.log(newData);
            request({
                url: "/CDOC/SaveCDOCInfo",
                data: {"ExecType": "02", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        }else{
            if ($("#guanlian1").val().trim() == "") {
                $("#guanlian1").css("border-color", "red").prop("placeholder", "不能为空！");
            }else if($("#guanlian2").val().trim() == ""){
                $("#guanlian2").css("border-color", "red").prop("placeholder", "不能为空！");
            }
        }
    });
});