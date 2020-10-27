/**
 * Created by test on 2017/8/24.
 */
$(function(){
    if(JSON.parse(localStorage.user).UserName=="system"){
        $("#M6_").css("display", "");
        $("#m2l").css("display", "");
    }else {
        $("#M6_").css("display", "none");
        $("#m2l").css("display", "none");
    }

    var colNamesArr = [
        {"Caption": "id", "Name": "ItemRd", "CType": "text", "Hidden": true},
        {"Caption": "代码类型", "Name": "CodeType"},
        {"Caption": "代码前缀", "Name": "Prefix", "Editable": true, "CType": "text"},
        {"Caption": "时间格式", "Name": "DtFormat", "Editable": true, "CType": "text"},
        {"Caption": "序号位数", "Name": "SNLength", "Editable": true, "CType": "text"},
        {"Caption": "代码后缀", "Name": "Suffix", "Editable": true, "CType": "text"},
        {"Caption": "启用", "Name": "Status", "Editable": false, "CType": "text"}
    ];

    // 定义表格中“是否启用”复选框的事件
    var checkbox = function () {
        $(".Status").each(function () {
            var current_Td_Val = $(this).text();
            var str = "";
            if (current_Td_Val == "00")
                str += "<input type='checkbox' name='Status' checked value='00'/>";
            else
                str += "<input type='checkbox' name='Status' value='01'/>";
            $(this).html(str);
        });
        $("input[name='Status']").on("click", function () {
            if ($(this).prop("checked"))
                $(this).val("00");
            else
                $(this).val("01");
        });
    }
    var treeID = 6;
    var objBusData = JSON.stringify({"SMTPRd": treeID});
    var objData = {
        "ExecType": "00",
        "BusData": objBusData,
    };

    request({url:"/SMTP/GetSMTPInfo",data: objData},function(Body){
        //fillform("devform", rule, Body.Data);
        $("#SMTPName").val( Body.Data.SMTPName);
        $("#UserName").val( Body.Data.UserName);
        $("#Password").val( Body.Data.Password);
        $("#SMTPURL").val( Body.Data.SMTPURL);
        $("#port").val( Body.Data.Port);
        if(Body.Data.UseSSL){
            $("#UseSSL").prop("checked",true);
        }else {
            $("#UseSSL").prop("checked",false);
        }


        //   $("#UseSSL").val( );
    })


    request({url: "/GConfig/GetGCInfo", data: {"ExecType": "00"}}, function (Body) {
        var modeData = Body.Data.ModeC;
        var msgData = Body.Data.MsgC;
        var codeRuleData=Body.Data.CodeRule;
        var synchCdata=Body.Data.SynchC;

        for(var i in synchCdata){
            if(synchCdata[i].SynchName=="RawM_Storage"){
                if(synchCdata[i].Status=="00"){
                    $('#RawM_StorageVal').find("input:eq(0)").attr('checked',true);
                }else{
                    $('#RawM_StorageVal').find("input:eq(0)").attr('checked',false);
                }
            }
            if(synchCdata[i].SynchName=="PickOut_Storage"){
                if(synchCdata[i].Status=="00"){
                    $('#PickOut_StorageVal').find("input:eq(0)").attr('checked',true);
                }else{
                    $('#PickOut_StorageVal').find("input:eq(0)").attr('checked',false);
                }
            }
            if(synchCdata[i].SynchName=="RetMa_Storage"){
                if(synchCdata[i].Status=="00"){
                    $('#RetMa_StorageVal').find("input:eq(0)").attr('checked',true);
                }else{
                    $('#RetMa_StorageVal').find("input:eq(0)").attr('checked',false);
                }
            }
        }

        for(var i in modeData){
            if(modeData[i].ModeName == "M1"){
                if(modeData[i].ModeValue == 'M1_00' ){
                    $('#M1Val').find("input:eq(0)").attr('checked',true);
                }
                else if(modeData[i].ModeValue == 'M1_01'){
                    $('#M1Val').find("input:eq(1)").attr('checked',true);
                }
            }

            if(modeData[i].ModeName == "M2"){
                if(modeData[i].ModeValue == 'M2_00' ){
                    $('#M2Val').find("input:eq(0)").attr('checked',true);
                }
                else if(modeData[i].ModeValue == 'M2_01'){
                    $('#M2Val').find("input:eq(1)").attr('checked',true);
                }else if(modeData[i].ModeValue == 'M2_02'){
                    $('#M2Val').find("input:eq(2)").attr('checked',true);
                }
            }

            if(modeData[i].ModeName == "M3"){
                if(modeData[i].ModeValue == 'M3_00' ){
                    $('#M3Val').find("input:eq(0)").attr('checked',true);
                }
                else if(modeData[i].ModeValue == 'M3_01'){
                    $('#M3Val').find("input:eq(1)").attr('checked',true);
                }
            }

            if(modeData[i].ModeName == "M4"){
                if(modeData[i].ModeValue == 'M4_00' ){
                    $('#M4Val').find("input:eq(0)").attr('checked',true);
                }
                else if(modeData[i].ModeValue == 'M4_01'){
                    $('#M4Val').find("input:eq(1)").attr('checked',true);
                }
            }
            if(modeData[i].ModeName == "M5"){
                if(modeData[i].ModeValue == 'M5_00' ){
                    $('#M5Val').find("input:eq(0)").attr('checked',true);
                }
                else if(modeData[i].ModeValue == 'M5_01'){
                    $('#M5Val').find("input:eq(1)").attr('checked',true);
                }
            }
            if(modeData[i].ModeName == "M6"){
                if(modeData[i].ModeValue == 'M6_00' ){
                    $('#M6Val').find("input:eq(0)").attr('checked',true);
                }
                else if(modeData[i].ModeValue == 'M6_01'){
                    $('#M6Val').find("input:eq(1)").attr('checked',true);
                }
            }

            if(modeData[i].ModeName == "SERVICE_PM"){
                if(modeData[i].ModeValue == 'SERVICE_PM_00' ){
                    $('#SERVICE_PM').prop("checked",true);
                }
                else if(modeData[i].ModeValue == 'SERVICE_PM_01'){
                    $('#SERVICE_PM').prop("checked",false);
                }
            }
            if(modeData[i].ModeName == "SERVICE_DJ"){
                if(modeData[i].ModeValue == 'SERVICE_DJ_00' ){
                    $('#SERVICE_DJ').prop("checked",true);
                }
                else if(modeData[i].ModeValue == 'SERVICE_DJ_01'){
                    $('#SERVICE_DJ').prop("checked",false);
                }
            }

            if(modeData[i].ModeName == "SERVICE_WLGQ"){
                if(modeData[i].ModeValue == 'SERVICE_WLGQ_00' ){
                    $('#SERVICE_WLGQ').prop("checked",true);
                }
                else if(modeData[i].ModeValue == 'SERVICE_WLGQ_01'){
                    $('#SERVICE_WLGQ').prop("checked",false);
                }
            }

            if(modeData[i].ModeName == "SERVICE_ZXSJ"){
                if(modeData[i].ModeValue == 'SERVICE_ZXSJ_00' ){
                    $('#SERVICE_ZXSJ').prop("checked",true);
                }
                else if(modeData[i].ModeValue == 'SERVICE_ZXSJ_01'){
                    $('#SERVICE_ZXSJ').prop("checked",false);
                }
            }

            if(modeData[i].ModeName == "SERVICE_ZDSJ"){
                if(modeData[i].ModeValue == 'SERVICE_ZDSJ_00' ){
                    $('#SERVICE_ZDSJ').prop("checked",true);
                }
                else if(modeData[i].ModeValue == 'SERVICE_ZDSJ_01'){
                    $('#SERVICE_ZDSJ').prop("checked",false);
                }
            }

            if(modeData[i].ModeName == "SERVICE_CJYC"){
                if(modeData[i].ModeValue == 'SERVICE_CJYC_00' ){
                    $('#SERVICE_CJYC').prop("checked",true);
                }
                else if(modeData[i].ModeValue == 'SERVICE_CJYC_01'){
                    $('#SERVICE_CJYC').prop("checked",false);
                }
            }

        }
        for (var i in  msgData) {
            if (msgData[i].MsgValue == 'C1_00') {
                $('#C1').attr('checked',true);
            }
            else if (msgData[i].MsgValue == 'C1_01') {
                $('#C1').attr('checked',false);
            }
        }

        //代码生成表格数据
            if(codeRuleData.length==0){
                var datatype=["物料代码","BOM代码","供应商代码","工单号","采购单号","来料通知单号","领料单号","退料单号","成品入库单号","盘点单号","企业代码","仓库代码","库位代码","成品出库单号","质检单号"];
                var Prefix=["Ma","BOM","GY","WO","PO","LO","LL","TL","CPR","PD","QY","CK","KW","CKPR","OJ"];

                for(var j in datatype){
                    for(var i in Prefix) {
                        if(i===j){
                            if(i==0 || i==1 || i==2 || i==10 || i==11 || i==12){
                                codeRuleData.push({
                                    "CodeType": datatype[j],
                                    "Prefix": Prefix[i],
                                    "DtFormat": "",
                                    "Suffix": "",
                                    "SNLength": "5",
                                    "Status": "00"
                                });
                            }else{
                                codeRuleData.push({
                                    "CodeType": datatype[j],
                                    "Prefix": Prefix[i],
                                    "DtFormat": "yyyymmdd",
                                    "Suffix": "",
                                    "SNLength": "5",
                                    "Status": "00"
                                });
                            }
                        }
                    }
                }

                var obj = {
                    data:codeRuleData,
                    colArr: colNamesArr,
                    tableId: "list3",
                    divId: "pager3",
                    multiselect: false,
                    width:0.84,
                    height:0.528
                };
                fullTable(obj);

            }else{
                var obj = {
                    data: codeRuleData,
                    colArr: colNamesArr,
                    tableId: "list3",
                    divId: "pager3",
                    multiselect: false,
                    width: 0.84,
                    height: 0.528
                };
                fullTable(obj);
            }
        checkbox();
    });

    //保存按钮
    $('.cSave').on('click',function(){
        var ModeCs = [];
        var MsgCs = [];
        var ModeC ={
            "ModeName": "M1",
            "ModeValue":""
        };

        var ModeC1 ={
            "ModeName": "M2",
            "ModeValue":""
        };

        var ModeC2 ={
            "ModeName": "M3",
            "ModeValue":""
        };

        var ModeC3 ={
            "ModeName": "M4",
            "ModeValue":""
        };
        var ModeC4 ={
            "ModeName": "M5",
            "ModeValue":""
        };
        var ModeC5 ={
            "ModeName": "M6",
            "ModeValue":""
        };
        if($('#M1Val').find("input:eq(0)").is(':checked')==true){
            ModeC.ModeValue = 'M1_00';

        }
        else if($('#M1Val').find("input:eq(1)").is(':checked')==true){
            ModeC.ModeValue = 'M1_01';
        }

        if($('#M2Val').find("input:eq(0)").is(':checked')==true){
            ModeC1.ModeValue = 'M2_00';

        }
        else if($('#M2Val').find("input:eq(1)").is(':checked')==true) {
            ModeC1.ModeValue = 'M2_01';
        }else if($('#M2Val').find("input:eq(2)").is(':checked')==true) {
            ModeC1.ModeValue = 'M2_02';
        }

        if($('#M3Val').find("input:eq(0)").is(':checked')==true){
            ModeC2.ModeValue = 'M3_00';

        }
        else if($('#M3Val').find("input:eq(1)").is(':checked')==true) {
            ModeC2.ModeValue = 'M3_01';
        }
        if($('#M4Val').find("input:eq(0)").is(':checked')==true){
            ModeC3.ModeValue = 'M4_00';

        }
        else if($('#M4Val').find("input:eq(1)").is(':checked')==true) {
            ModeC3.ModeValue = 'M4_01';
        }
        if($('#M5Val').find("input:eq(0)").is(':checked')==true){
            ModeC4.ModeValue = 'M5_00';

        }
        else if($('#M5Val').find("input:eq(1)").is(':checked')==true) {
            ModeC4.ModeValue = 'M5_01';
        }
        if($('#M6Val').find("input:eq(0)").is(':checked')==true){
            ModeC5.ModeValue = 'M6_00';

        }
        else if($('#M6Val').find("input:eq(1)").is(':checked')==true) {
            ModeC5.ModeValue = 'M6_01';
        }
        ModeCs.push(ModeC);
        ModeCs.push(ModeC1);
        ModeCs.push(ModeC2);
        ModeCs.push(ModeC3);
        ModeCs.push(ModeC4);
        ModeCs.push(ModeC5);

        if($("#SERVICE_PM").is(":checked")){
            ModeCs.push({
                "ModeName": "SERVICE_PM",
                "ModeValue":"SERVICE_PM_00"
            })
        }else{
            ModeCs.push({
                "ModeName": "SERVICE_PM",
                "ModeValue":"SERVICE_PM_01"
            })
        }
        if($("#SERVICE_DJ").is(":checked")){
            ModeCs.push({
                "ModeName": "SERVICE_DJ",
                "ModeValue":"SERVICE_DJ_00"
            })
        }else{
            ModeCs.push({
                "ModeName": "SERVICE_DJ",
                "ModeValue":"SERVICE_DJ_01"
            })
        }

        if($("#SERVICE_WLGQ").is(":checked")){
            ModeCs.push({
                "ModeName": "SERVICE_WLGQ",
                "ModeValue":"SERVICE_WLGQ_00"
            })
        }else{
            ModeCs.push({
                "ModeName": "SERVICE_WLGQ",
                "ModeValue":"SERVICE_WLGQ_01"
            })
        }

        if($("#SERVICE_ZXSJ").is(":checked")){
            ModeCs.push({
                "ModeName": "SERVICE_ZXSJ",
                "ModeValue":"SERVICE_ZXSJ_00"
            })
        }else{
            ModeCs.push({
                "ModeName": "SERVICE_ZXSJ",
                "ModeValue":"SERVICE_ZXSJ_01"
            })
        }


        if($("#SERVICE_ZDSJ").is(":checked")){
            ModeCs.push({
                "ModeName": "SERVICE_ZDSJ",
                "ModeValue":"SERVICE_ZDSJ_00"
            })
        }else{
            ModeCs.push({
                "ModeName": "SERVICE_ZDSJ",
                "ModeValue":"SERVICE_ZDSJ_01"
            })
        }


        if($("#SERVICE_CJYC").is(":checked")){
            ModeCs.push({
                "ModeName": "SERVICE_CJYC",
                "ModeValue":"SERVICE_CJYC_00"
            })
        }else{
            ModeCs.push({
                "ModeName": "SERVICE_CJYC",
                "ModeValue":"SERVICE_CJYC_01"
            })
        }


        var synchC=[];
        var synch={
            "SynchName": "RawM_Storage",
            "Status":""
        };
        var synch1={
            "SynchName": "PickOut_Storage",
            "Status":""
        };
        var synch2={
            "SynchName": "RetMa_Storage",
            "Status":""
        };
        if($('#RawM_StorageVal').find("input:eq(0)").is(':checked')==true){
            synch.Status = '00';
        }else{
            synch.Status = '01';
        }
        if($('#PickOut_StorageVal').find("input:eq(0)").is(':checked')==true){
            synch1.Status = '00';
        }else{
            synch1.Status = '01';
        }
        if($('#RetMa_StorageVal').find("input:eq(0)").is(':checked')==true){
            synch2.Status = '00';
        }else{
            synch2.Status = '01';
        }
        synchC.push(synch);
        synchC.push(synch1);
        synchC.push(synch2);

        var MsgC ={
            "MsgName": "C1",
            "MsgValue":"C1_00"
        };
        if(!$('.Msg').prop("checked")){
            MsgC.MsgValue = "C1_01";
        }
        MsgCs.push(MsgC);

        var json = getTableData("list3");
        for (var i = 0; i < json.length; i++) {
            delete json[i]["ItemRd"]
        }


        var port = $("#port").val();
        var newData = {};
        if(port=="" && port==null){
            toastr.warning("端口不能为空");
            return false;
        }
        newData = {
            "UserName": $("#UserName").val(),
            "Password": $("#Password").val(),
            "SMTPURL": $("#SMTPURL").val(),
            "Port":$("#port").val(),
            "Ruid":treeID
        };
        var ischeck=$("#UseSSL").prop("checked");
        if(ischeck){
            newData.UseSSL=1;
        }else {
            newData.UseSSL=0;
        }

        request({url: "/GConfig/SaveGCInfo", data: {"ExecType": "00","BusData":JSON.stringify({"ModeC":ModeCs,"MsgC":MsgCs,"CodeRule":json,"SynchC":synchC,"SMTPInfo":newData})}}, function (Body) {
            toastr.success(Body.MsgDes);
        })
    });
});