/**
 * Created by test on 2017/8/21.
 */
$(function(){
    //定义表单规则
    var rule = [{
        "ctlid": "MPName",
        "param": "MPName"
    }, {
        "ctlid": "TempName",
        "param": "TempName"
    }, {
        "ctlid": "SRName",
        "param": "SRName"
    },{
        "ctlid": "Num",
        "param": "Num"
    },{
        "ctlid": "Weight",
        "param": "Weight"
    },{
        "ctlid": "UpLimit",
        "param": "UpLimit"
    },{
        "ctlid": "DownLimit",
        "param": "DownLimit"
    },{
        "ctlid": "UnitName",
        "param": "UnitName"
    },{
        "ctlid": "creatPeople",
        "param": "Creator"
    }, {
        "ctlid": "creatTime",
        "param": "CreateTime"
    }, {
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    }, {
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    }, {
        "ctlid": "beizhu",
        "param": "Remark"
    }];

    //加载页面数据
    /*------------------点击事件的处理----------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        // 加载右侧数据
        request({url:"/PackSp/GetMPKInfo",data:{"ExecType":"00","BusData":JSON.stringify({"MPRd":treeID})}},function (Body) {
            fillform("packSP",rule,Body.Data);

           $("#Num").val(Body.Data.Num);
           $("#Weight").val(Body.Data.Weight);
           $("#UpLimit").val(Body.Data.UpLimit);
           $("#DownLimit").val(Body.Data.DownLimit);

            $("#defaultSelect").showData({
                id:Body.Data.PTInfo.PTRd,
                name:Body.Data.PTInfo.TempName,
                keyfield:"PTRd",
                fields:[
                    {"name":"PTRd"},
                    {"name":"TempName"}
                ]
            });

            $("#defaultSelect1").showData({
                id:Body.Data.SNInfo.SNRd,
                name:Body.Data.SNInfo.SNName,
                keyfield:"SNRd",
                fields:[
                    {"name":"SNRd"},
                    {"name":"SNName"}
                ]
            });
            $("#PackType").find("option").each(function () {
                if($(this).val() == Body.Data.PackType){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });

            if(Body.Data.IsProperty == "00"){
                $("#isProperty").prop("checked", true);
                $("#isShow").css("display", "block");
            }else{
                $("#isProperty").prop("checked", false);
                $("#isShow").css("display", "none");
            }
            $("#power_").selectpicker('val', Body.Data.PowerRd);
            $("#current_").selectpicker('val', Body.Data.CurrentRd);
            $("#color_").selectpicker('val', Body.Data.ColorRd);
            $("#grade_").selectpicker('val', Body.Data.GradeRd);
            $("#ma_").selectpicker('val', Body.Data.MaRd);

            /*var aa = Body.Data.PowerProperty;
            var aas = [];
            for(var i in aa){
                aas.push(aa[i].id)
            }
            $("#power").selectpicker('val', aas);*/
            $("#power_s").val(Body.Data.StartPower);
            $("#power_e").val(Body.Data.EndPower);

            var bb = Body.Data.CurrentProperty;
            var bbs = [];
            for(var i in bb){
                bbs.push(bb[i].id)
            }
            $("#current").selectpicker('val', bbs);

            var cc = Body.Data.ColorProperty;
            var ccs = [];
            for(var i in cc){
                ccs.push(cc[i].id)
            }
            $("#color").selectpicker('val', ccs);

            var dd = Body.Data.GradeProperty;
            var dds = [];
            for(var i in dd){
                dds.push(dd[i].id)
            }
            $("#grade").selectpicker('val', dds);

            var ee = Body.Data.MaProperty;
            var ees = [];
            for(var i in ee){
                ees.push(ee[i].id)
            }
            $("#ma").selectpicker('val', ees);
        })
    };
    var config = {
        id :"jstree_demo1",
        data:{
            source:[],
            rule:[{
                id:"id",
                text:"name",
            }]
        },
        event:{
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
        currentPage = treeSearchs("/PackSp/GetAllMPKInfo","MPRd","MPName","PackName",condition,currentPage,config);
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
        currentPage = treeSearchs("/PackSp/GetAllMPKInfo","MPRd","MPName","PackName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/PackSp/GetAllMPKInfo","MPRd","MPName","PackName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/PackSp/GetAllMPKInfo","MPRd","MPName","PackName",condition,currentPage,config);
    });
    $("#_right").hide();
    var treeID = null;
    // 获取所有的打印模板信息
    var params = {
        "displaymode": "0",
        "title": "选择打印模板",
        "binddata": {
            "keyfield": "PTRd",
            "fields": [
                {
                    "caption": "打印模板id",
                    "name": "PTRd"
                }, {
                    "caption": "打印模板",
                    "name": "TempName"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"TempName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "TempName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/PrintT/GetAllPtInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "PTRd": datas[i].PtRd,
                            "TempName": datas[i].PtName,
                        }
                        xldata.push(data);
                    }

                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    }

    $("#defaultSelect").zc_select(params);

    // 获取所有的序号管理信息

    var params = {
        "displaymode": "0",
        "title": "选择序号",
        "binddata": {
            "keyfield": "SNRd",
            "fields": [
                {
                    "caption": "序号id",
                    "name": "SNRd"
                }, {
                    "caption": "序号规则",
                    "name": "SNName"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RuleName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "RuleName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                request({url: "/SN/GetAllSNInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SNRd": datas[i].SNRd,
                            "SNName": datas[i].SNName,
                        }
                        xldata.push(data);
                    }

                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    }

    $("#defaultSelect1").zc_select(params);



    var loadData =function(){
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url:"/PackSp/GetAllMPKInfo",
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "PackName",
                            "FieldOpt": "Order BY"
                        }]})
            }},function(Body){
            var data = Body.Data;
            var dataArrs = [];

            if (data.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            var len = data.length >= 20 ? 20 : data.length;
            for( var i = 0 ; i < len; i ++){
                var dataArr = {
                    "id":data[i].MPRd,
                    "name":data[i].MPName
                }

                dataArrs.push(dataArr);
            }
            config.data.source = dataArrs;
            $.JstreeEx.init(config);
        })
    }
    loadData();

    //功率
    request({url: "/SunPort/PowerGear/GetAllPGInfo", data: {"ExecType": "00","InitData": JSON.stringify({
                "FiledList": [
                    {
                        "FieldName": "PowerName",
                        "FieldOpt": "ORDER BY",
                    }]
            })}}, function (Body) {
        var data = Body.Data;
        var aa="<option></option>";
        for(var i in data){
            aa+='<option value='+data[i].PowerGRd+'>'+data[i].PowerName +'</option>';
        }
        /*$("#power").html(aa);
        $("#power").selectpicker({
            width:200
        });*/
        $("#power_").html(aa);
        $("#power_").selectpicker({
            width:200
        });
    });
    //电流
    request({url: "/SunPort/CurrentGear/GetAllCurrentInfo", data: {"ExecType": "00","InitData": JSON.stringify({
                "FiledList": [
                    {
                        "FieldName": "CurrentName",
                        "FieldOpt": "ORDER BY",
                    }]
            })}}, function (Body) {
        var data = Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].CurrentGRd+'>'+data[i].CurrentName +'</option>';
        }
        $("#current").html(aa);
        $("#current").selectpicker({
            width:200
        });
        $("#current_").html("<option></option>" + aa);
        $("#current_").selectpicker({
            width:200
        });
    });
    //颜色
    request({url: "/SunPort/ColorGear/GetAllColorInfo",
        data: {"ExecType": "00","InitData": JSON.stringify({
                "FiledList": [
                    {
                        "FieldName": "ColorName",
                        "FieldOpt": "ORDER BY",
                    }]
            })}},
        function (Body) {
        var data = Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].ColorGRd+'>'+data[i].ColorName +'</option>';
        }
        $("#color").html(aa);
        $("#color").selectpicker({
            width:200
        });
        $("#color_").html("<option></option>"+aa);
        $("#color_").selectpicker({
            width:200
        });
    });
    //等级
    request({
        url: "/SunPort/Grade/GetAllGradeInfo",
        data: {
            "ExecType": "00",
            "InitData": JSON.stringify({
                "FiledList": [{
                    "FieldName": "GradeType",
                    "FieldOpt": "=",
                    "FieldVal": "01" //a   a.GradeName
                },
                    {
                        "FieldName": "GradeName",
                        "FieldOpt": "ORDER BY",
                    }]
            })
        }
    }, function (Body) {
        var data = Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].GradeRd+'>'+data[i].GradeName +'</option>';
        }
        $("#grade").html(aa);
        $("#grade").selectpicker({
            width:200
        });
        $("#grade_").html("<option></option>"+aa);
        $("#grade_").selectpicker({
            width:200
        });
    });

    var InitData = {
        "FiledList":[
            {
                "FieldName":"MaterialType",
                "FieldOpt":"in",
                "FieldVal":"(01,00)"
            },
            {
                "FieldName": "MaterialName",
                "FieldOpt": "Order BY"
            }
        ]
    };
    //加载产品
    request({url:"/Material/GetAllMaInfo", data:{"ExecType": "00","InitData":JSON.stringify(InitData)}},function(Body){
        var data = Body.Data;
        var aa="";
        for(var i in data){
            aa+='<option value='+data[i].MaVerRd+'>'+data[i].MaName +'</option>';
        }
        $("#ma").html(aa);
        $("#ma").selectpicker({
            width:200
        });
        $("#ma_").html("<option></option>" + aa);
        $("#ma_").selectpicker({
            width:200
        });
    });
    
    $("#isProperty").click(function () {
        if($(this).is(":checked")){
            $("#isShow").css("display", "block");
        }else{
            $("#isShow").css("display", "none");
        }
    });

    // 新增
    $(".cAdd").click(function () {
        clearForm("packSP");
        $("#defaultSelect").clearseldata("PTRd");
        $("#defaultSelect1").clearseldata("SNRd");
        treeID = null;
        $("#ExecType").val("00");
        $("#_right").show();
        $("#isShow").css("display", "none");
        //$("#power").selectpicker('val',"");
        $("#power_s").val("");
        $("#power_e").val("");
        $("#power_").selectpicker('val',"");
        $("#current").selectpicker('val',"");
        $("#current_").selectpicker('val',"");
        $("#color").selectpicker('val',"");
        $("#color_").selectpicker('val',"");
        $("#grade").selectpicker('val',"");
        $("#grade_").selectpicker('val',"");
        $("#ma").selectpicker('val',"");
        $("#ma_").selectpicker('val',"");
    })
    
    // 保存
    $(".cSave").click(function () {
        var formData = transfer("packSP");
        var str=/^\d+(\.\d+)?$/;　//非负浮点数（正浮点数 + 0）
        var Num=$("#Num").val().trim();
        var Weight=$("#Weight").val().trim();
        var UpLimit=$("#UpLimit").val().trim();
        var DownLimit=$("#DownLimit").val().trim();
        var power_s = $("#power_s").val().trim();
        var power_e = $("#power_e").val().trim();
        if(str.test(Num)==false){
            toastr.warning("包装数量值应为正数");
            return false;
        }

        if(str.test(Weight)==false){
            toastr.warning("标准重量值应为正数");
            return false;
        }
        if(UpLimit!=null&&UpLimit!=""){
            if(str.test(UpLimit)==false){
                toastr.warning("上限浮动值应为正数");
                return false;
            }
        }
        if(DownLimit!=null&&DownLimit!=""){
            if(str.test(DownLimit)==false){
                toastr.warning("下限浮动值应为正数");
                return false;
            }
        }

        var SNRd = $("#defaultSelect1").getseldata().SNRd;

        var PTRd = $("#defaultSelect").getseldata().PTRd;

        var packType = $("#PackType").val();

        if ($("#MPName").val().trim() != "" && $("#PackType").val().trim() != "" && SNRd != "" &&
            PTRd != "" && $("#Num").val().trim() != "") {

            var BusData = {
                "MPRd": treeID,
                "MPName": formData.MPName,
                "PackType": packType,
                "PTRd": PTRd,
                "SNRd": SNRd,
                "Num": formData.Num,
                "Weight": formData.Weight,
                "UpLimit": formData.UpLimit,
                "DownLimit": formData.DownLimit,
                "UnitName": formData.UnitName,
                "Remark": $("#beizhu").val(),
                "Property": []
            };

            if($("#isProperty").is(":checked")){
                var p = $("#power").val();
                var ps = [];
                if(p != undefined) {
                    for (var obj in p) {
                        ps.push({
                            id: p[obj],
                            type: '00'
                        });
                    }
                }
                var c = $("#current").val();
                if(c != undefined) {
                    for (var obj in c) {
                        ps.push({
                            id: c[obj],
                            type: '01'
                        });
                    }
                }
                var c2 = $("#color").val();
                if(c2 != undefined) {
                    for (var obj in c2) {
                        ps.push({
                            id: c2[obj],
                            type: '02'
                        });
                    }
                }
                var g = $("#grade").val();
                if(g != undefined) {
                    for (var obj in g) {
                        ps.push({
                            id: g[obj],
                            type: '03'
                        });
                    }
                }
                var m = $("#ma").val();
                if(m != undefined) {
                    for (var obj in m) {
                        ps.push({
                            id: m[obj],
                            type: '04'
                        });
                    }
                }
                BusData.Property = ps;
                BusData.IsProperty = "00";
                BusData.PowerRd = $("#power_").val();
                BusData.CurrentRd = $("#current_").val();
                BusData.ColorRd = $("#color_").val();
                BusData.GradeRd = $("#grade_").val();
                BusData.MaRd = $("#ma_").val();

                if(str.test(power_s)==false || str.test(power_e)==false){
                    toastr.warning("功率区间值应为正数");
                    return false;
                }
                if(power_s > power_e){
                    toastr.warning("功率区间值开始值不能大于结束值");
                    return false;
                }
                BusData.StartPower = power_s;
                BusData.EndPower = power_e;
            }else{
                BusData.IsProperty = "01";
            }

            if ((treeID == null || treeID == "") && $("#ExecType").val() === "00") {
                delete BusData.MPRd;
                request({
                    url: "/PackSp/SaveMPKInfo",
                    data: {"ExecType": "00", "BusData": JSON.stringify(BusData)}
                }, function (Body) {
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    //刷新树
                    loadData();
                    treeID=null;
                });
            }
            else if (treeID != null) {
                request({
                    url: "/PackSp/SaveMPKInfo",
                    data: {"ExecType": "02", "BusData": JSON.stringify(BusData)}
                }, function (Body) {
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    //刷新树
                    loadData();
                    treeID=null;
                });
            }
        }else{
            if ($("#MPName").val().trim() == "") {
                $("#MPName").css("border-color", "red");
                $("#MPName").prop("placeholder", "不能为空！");
            }
            if ($("#PackType").val().trim() == "") {
                $("#PackType").css("border-color", "red");
                $("#PackType").prop("placeholder", "不能为空！");
            }
            if (PTRd == "") {
                $("#defaultSelect").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect").find("input:eq(0)").val("");
                $("#defaultSelect").find("input:eq(0)").prop("placeholder", "不能为空!");
            }
            if (SNRd == "") {
                $("#defaultSelect1").find("input:eq(0)").css("border-color", "red");
                $("#defaultSelect1").find("input:eq(0)").val("");
                $("#defaultSelect1").find("input:eq(0)").prop("placeholder", "不能为空!");
            }
            if ($("#Num").val().trim() == "") {
                $("#Num").css("border-color", "red");
                $("#Num").prop("placeholder", "不能为空！");
            }
        }
    });
    // 删除
    $("#remove").click(function () {
        if (treeID != null) {
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({url:"/PackSp/SaveMPKInfo", data: {"ExecType": "01", "BusData": JSON.stringify({"MPRd":treeID})}},function(Body){
                    treeID = null;
                    $("#_right").hide();
                    $("#ExecType").val("");
                    toastr.success(Body.MsgDes);
                    currentPage=0;
                    condition = '';
                    //刷新树
                    loadData();
                });
                layer.closeAll("dialog");

            }, function () {

            });
        } else {
            toastr.warning("请选择左侧的一项根节点再进行删除!");
        }
    });
})