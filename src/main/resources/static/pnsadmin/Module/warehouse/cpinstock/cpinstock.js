$(function(){

    var date = new Date();
    this.year = date.getFullYear();
    this.month = parseInt(date.getMonth())   + parseInt(1) < 10 ? "0" + (date.getMonth() +  parseInt(1)) :parseInt(date.getMonth()) +  parseInt(1);
    this.date = date.getDate();
    this.date1 = date.getDate() - 1;
    this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

    //时间段
    var dateTime = this.year + "-" + this.month + "-" + this.date1 + " " + this.hour + ":" + this.minute + ":" + this.second + " - " + this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;


    var flag=true;
    //日期时间范围
    laydate.render({
        elem: '#test10'
        , type: 'datetime'
        , range: true
        ,value:dateTime
    });




    var PKDtlRd="";
    var UnitName="";
    var _MaDes = "";
    var _MaCode = "";
    var resultFlag = true;//判断表格是编辑还是新增
    var modifytableid = '';
    var addid = 1;

    var colNamesArr2 = [
        //{"Caption": "表格的唯一一个数据", "Name": "addid", 'Hidden': true,Width:100},
        // {"Caption": "OutDtlRd", "Name": "OutDtlRd", "Hidden": true},
        {"Caption": "MaVerRd", "Name": "MaVerRd", "Hidden": true},
        {"Caption": "箱号/组件", "Name": "Batch", 'Editable': false,Width:100},
        {"Caption": "库存数量", "Name": "Num", 'Editable': false,Width:50},
        {"Caption": "产品代码", "Name": "MaterialCode", 'Editable': false,Width:100},
        {"Caption": "产品名称", "Name": "MaterialName",'Editable': false},
        {"Caption": "功率", "Name": "PowerGear",'Editable': false,Width:70},
        {"Caption": "电流", "Name": "ELGear",'Editable': false,Width:40},
        {"Caption": "颜色", "Name": "ColorGear", 'Editable': false,Width:40},
        {"Caption": "综合等级", "Name": "GradeName", 'Editable': false,Width:50},
        {"Caption": "识别码", "Name": "UniqueText", 'Editable': false,Width:50},
        {"Caption": "仓库id", "Name": "StoreRd", 'Editable': false, "Hidden": true},
        {"Caption": "仓库", "Name": "StoreName", 'Editable': false,Width:50},
        {"Caption": "库位id", "Name": "LRd", 'Editable': false,Width:80, "Hidden": true},
        {"Caption": "箱或组件", "Name": "BarType", 'Editable': false,Width:80, "Hidden": true},
        {"Caption": "库位", "Name": "LName", 'Editable': false,Width:80},
        {"Caption": "备注", "Name": "Remark", 'Editable': false,Width:80},
        {"Caption": "单位", "Name": "UnitName", 'Editable': false,Width:30},
        {"Caption": "产品描述", "Name": "MaterialDes",'Editable': false,Width:80},
        {"Caption": "创建时间", "Name": "CreateTime", 'Editable': false,Width:130, "Hidden":false}
    ];
    var config2 = {
        tableId: 'list6',
        data: [],
        colArr: colNamesArr2,
        width: 0.9,
        height:0.36
    };
    fullTable(config2);//加载空表格


    var $tab_4_li = $("#tab_4_li");
    var $tab_4 = $("#tab_4");
    var flag = true;
    var $maName = $("#maName");

    var $JGL = $("#JGL");
    var $CGNum = $("#CGNum");
    var $unit = $(".unit");
    var _PurChDlRd = "";


    var dSource = true;
    var elStatus="";
    var treeID="";
    //放在最上面。目的是为了实现是否可以点击领用人这个插件
    var ssStaatus="";
    var str="";
    var str1="";


    var dataStore=[];

    //存放新增明细的时候表格数据
    var tab_TR = [];

    var list4Date=getTableData("list4");
    //测试每次的表格信息

    var temporaryDatas=[];
    var temporaryData={};
    //领用物料
    //领用数量的新增事件 即保存在一个集合里
    var insertData={};
    var lywl = {
        "displaymode": "1",
        "title": "领用物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "版本Rd",
                    "name": "MaVerRd",
                    "hidden":true
                }, {
                    "caption": "领用物料代码",
                    "name": "MaCode"
                }, {
                    "caption": "领用物料名称",
                    "name": "MaName"
                }, {
                    "caption": "领用物料描述",
                    "name": "MaDes"
                },{
                    "caption": "lywlid",
                    "name": "StoreNum",
                    "hidden":true
                },
                {
                    "caption": "单位",
                    "name": "UnitName",
                    "hidden":true
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                if(storeid==null||storeid==""||!Number(storeid)){
                    toastr.warning("操作失败，没有获取到仓库")
                    return false;
                }
                var objDate={
                    "StoreRd":storeid
                };
                var xldata = [];
                request({url: "/BOM/GetNMaTotalInfo", data: {"ExecType": "00","busData": JSON.stringify(objDate)}}, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data= {
                            "MaVerRd": datas[i].MaVerRd,
                            "MaCode": datas[i].MaCode,
                            "MaName": datas[i].MaName,
                            "MaDes": datas[i].MaDes,
                            "StoreNum": datas[i].StoreNum,
                            "UnitName": datas[i].UnitName
                        };
                        xldata.push(data);
                    }
                });

                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;

            },
            "onclick":function (res) {
                $("#ccsl").val(res.StoreNum);
                //此处拼下面的表格数据
                //目的是为了获取仓库name
                var newData1={
                    "StoreRd":storeid
                }
                var StoreName="";
                request({url: "/Store/GetStoreInfo", data: {"ExecType": "00","busData": JSON.stringify(newData1)}}, function (Body) {
                    StoreName=Body.Data.StoreName;
                });
                UnitName=res.UnitName;
                temporaryData={
                    "MaVerRd":res.MaVerRd/*+"_"+storeid*/,
                    "MaCode":res.MaCode,
                    "MaName":res.MaName,
                    "MaDes":res.MaDes,
                    "Num":res.StoreNum,
                    "StoreRd":storeid,
                    "UnitName":res.UnitName,
                    "StoreName":StoreName
                }

            },
            "onformatval": function(seldata){
                xldata=[];
                return seldata.MaCode + "-" + seldata.MaName+"-"+seldata.MaDes;
            }
        }};
    $("#lymater").zc_select(lywl);


    //仓库
    //获取点击仓库后的id
    var storeid="";
    var store = {
        "displaymode": "0",
        "title": "store",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "storeid",
                    "name": "StoreRd"
                }, {
                    "caption": "store名称",
                    "name": "StoreName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                //每次点击的时候，把领用物料里面加载的数据清空和显示的信息以及库存数量去拿不清空
                xldata = [];
                $("#lymater").clearseldata("Num");
                $("#ccsl").val("");


                var xldata1 = [];
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"StoreName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "StoreName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Store/GetAllStoreInfo"
                };

                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "StoreRd": datas[i].StoreRd,
                            "StoreName": datas[i].StoreName
                        };
                        xldata1.push(data);
                    }
                });
                var obj = {
                    data:xldata1,
                    showrow:500
                };
                return obj;
            },
            "onclick":function (res) {
                storeid=res.StoreRd;
            }
        }
    };
    $("#Store").zc_select(store);


    var InitData = {
        "FiledList":[
            {
                "FieldName": "RealName",
                "FieldOpt": "Order BY"
            }
        ]
    };

    var obj = {
        data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
        url:"/User/GetAllUserInfo"
    };
    var xlstore="";
    request(obj,function(Body) {
        var data=Body.Data;
        if(data.length==1){
            xlstore+="\""+data[0].UserRd+"\":\""+data[0].RealName+"\"";
        }else if(data.length>1) {
            for(var i=0;i<data.length;i++){
                xlstore+="\""+data[i].UserRd+"\":\""+data[i].RealName+"\""+"|";
            }
        }
    });


    //仓库
    //获取点击仓库后的id
    var storeid="";
    var store = {
        "displaymode": "0",
        "title": "store",
        "binddata": {
            "keyfield": "StoreRd",
            "fields": [
                {
                    "caption": "storeid",
                    "name": "StoreRd"
                }, {
                    "caption": "store名称",
                    "name": "StoreName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                //每次点击的时候，把领用物料里面加载的数据清空和显示的信息以及库存数量去拿不清空
                xldata = [];
                $("#lymater").clearseldata("Num");
                $("#ccsl").val("");


                var xldata1 = [];
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"StoreName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "StoreName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)},
                    url:"/Store/GetAllStoreInfo"
                };

                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "StoreRd": datas[i].StoreRd,
                            "StoreName": datas[i].StoreName
                        };
                        xldata1.push(data);
                    }
                });
                var obj = {
                    data:xldata1,
                    showrow:500
                };
                return obj;
            },
            "onclick":function (res) {
                storeid=res.StoreRd;
            }
        }
    };
    $("#cangku").zc_select(store);


    var insertData={};
    var lywl = {
        "displaymode": "1",
        "title": "领用物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "版本Rd",
                    "name": "MaVerRd",
                    "hidden":true
                }, {
                    "caption": "领用物料代码",
                    "name": "MaCode"
                }, {
                    "caption": "领用物料名称",
                    "name": "MaName"
                }, {
                    "caption": "领用物料描述",
                    "name": "MaDes"
                },{
                    "caption": "lywlid",
                    "name": "StoreNum",
                    "hidden":true
                },
                {
                    "caption": "单位",
                    "name": "UnitName",
                    "hidden":true
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                if(storeid==null||storeid==""||!Number(storeid)){
                    toastr.warning("操作失败，没有获取到仓库")
                    return false;
                }
                var objDate={
                    "StoreRd":storeid
                };
                var xldata = [];
                request({url: "/BOM/GetNMaTotalInfo", data: {"ExecType": "00","busData": JSON.stringify(objDate)}}, function (Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data= {
                            "MaVerRd": datas[i].MaVerRd,
                            "MaCode": datas[i].MaCode,
                            "MaName": datas[i].MaName,
                            "MaDes": datas[i].MaDes,
                            "StoreNum": datas[i].StoreNum,
                            "UnitName": datas[i].UnitName
                        };
                        xldata.push(data);
                    }
                });

                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;

            },
            "onclick":function (res) {
                $("#ccsl").val(res.StoreNum);

            },
            "onformatval": function(seldata){
                xldata=[];
                return seldata.MaCode + "-" + seldata.MaName+"-"+seldata.MaDes;
            }
        }};
    $("#chanpin").zc_select(lywl);


    //加载功率
    request({url: "/SunPort/PowerGear/GetAllPGInfo", data: {"ExecType": "00","InitData": JSON.stringify({
                "FiledList": [
                    {
                        "FieldName": "PowerName",
                        "FieldOpt": "ORDER BY",
                    }]
            })}}, function (Body) {
        var datas = Body.Data;
        var data_="<option></option>";
        for(var i in  datas){
            data_+="<option>"+datas[i].PowerName+"</option>";
        }
        $("#gonglv").empty().html(data_);
    });
    //加载电流
    request({url: "/SunPort/CurrentGear/GetAllCurrentInfo", data: {"ExecType": "00","InitData": JSON.stringify({
                "FiledList": [
                    {
                        "FieldName": "CurrentName",
                        "FieldOpt": "ORDER BY",
                    }]
            })}}, function (Body) {
        var datas = Body.Data;
        var data_="<option></option>";
        for(var i in  datas){
            data_+="<option>"+datas[i].CurrentName+"</option>";
        }
        $("#dianliu").empty().html(data_);
    });

    //加载颜色
    request({url: "/SunPort/ColorGear/GetAllColorInfo", data: {"ExecType": "00","InitData": JSON.stringify({
                "FiledList": [
                    {
                        "FieldName": "ColorName",
                        "FieldOpt": "ORDER BY",
                    }]
            })}}, function (Body) {
        var datas = Body.Data;
        var data_="<option></option>";
        for(var i in  datas){
            data_+="<option>"+datas[i].ColorName+"</option>";
        }
        $("#yanse").empty().html(data_);
    });

    //加载等级
    request({url: "/SunPort/Grade/GetAllGradeInfo", data: {"ExecType": "00","InitData": JSON.stringify({
                "FiledList": [{
                    "FieldName": "GradeType",
                    "FieldOpt": "=",
                    "FieldVal": "01" //a   a.GradeName
                },
                    {
                        "FieldName": "GradeName",
                        "FieldOpt": "ORDER BY",
                    }]
            })}}, function (Body) {
        var datas = Body.Data;
        var data_="<option></option>";
        for(var i in  datas){
            data_+="<option>"+datas[i].GradeName+"</option>";
        }
        $("#dengji").empty().html(data_);
    });

    $("#huizongselect").click(function(){
        var time = $("#test10").val();
        var createtime1 = null;
        var createtime2 = null;
        var batch=$("#batch").val().trim();


        var MaVerRd= 0;
        var StoreRd=0;
        var LRd=0;
        var F1=$("#gonglv").val();
        var F2=$("#dianliu").val();
        var F3=$("#yanse").val();
        var F4=$("#dengji").val();
        MaVerRd= $("#chanpin").getseldata().MaVerRd;
        StoreRd=$("#cangku").getseldata().StoreRd;
        LRd=0;
        var data={};
        if(MaVerRd>0){
            data.MaVerRd=MaVerRd;
        }
        if (time != "") {
            data.CreateTime1 = time.split(" - ")[0];
            data.CreateTime2 = time.split(" - ")[1];
        }
        if(batch!=""){
            data.Batch=batch;
        }

        if(StoreRd>0){
            data.StoreRd=StoreRd;
        }
        if(LRd>0){
            data.LRd=LRd;
        }
        if(F1!=""){
            data.F1=F1;
        }
        if(F2!=""){
            data.F2=F2;
        }
        if(F3!=""){
            data.F3=F3;
        }
        if(F4!=""){
            data.F4=F4;
        }
        /*  var data={MaVerRd:$("#chanpin").getseldata().MaVerRd,StoreRd:,LRd:'',F1:$("#gonglv").val(),F2:$("#dianliu").val(),F3:$("#yanse").val(),F4:$("#dengji").val()}*/



        $.get(getBasePath()+"/ZOutMa/GetAllXiangAndBatch",data,function (data) {
            var datas=data;
            console.log(datas);
            var total=0;
            var pretotal=0;
            if(datas.length>0){
                for(var i  in  datas){
                    pretotal+=datas[i].Num;
                    if(datas[i].LRd==null||datas[i].LRd==""){
                        datas[i].LRd="";
                    }
                    if(datas[i].LName==null||datas[i].LName==""){
                        datas[i].LName="";
                    }
                    if(datas[i].MaVerRd==null||datas[i].MaVerRd==""){
                        datas[i].MaVerRd="";
                    }
                    if(datas[i].MaterialCode==null||datas[i].MaterialCode==""){
                        datas[i].MaterialCode="";
                    }
                    if(datas[i].MaterialName==null||datas[i].MaterialName==""){
                        datas[i].MaterialName="";
                    }
                    if(datas[i].MaterialDes==null||datas[i].MaterialDes==""){
                        datas[i].MaterialDes="";
                    }
                    if(datas[i].PowerGear==null||datas[i].PowerGear==""){
                        datas[i].PowerGear="";
                    }
                    if(datas[i].ELGear==null||datas[i].ELGear==""){
                        datas[i].ELGear="";
                    }
                    if(datas[i].ColorGear==null||datas[i].ColorGear==""){
                        datas[i].ColorGear="";
                    }
                    if(datas[i].GradeName==null||datas[i].GradeName==""){
                        datas[i].GradeName="";
                    }
                    if(datas[i].UnitName==null||datas[i].UnitName==""){
                        datas[i].UnitName="";
                    }
                }

                var config2 = {
                    tableId: 'list6',
                    data: datas,
                    colArr: colNamesArr2,
                    width: 0.9,
                    height:0.36
                };
                fullTable(config2);

                total=datas.length;
                $("#total").text(total);
                $("#pretotal").text(pretotal);
            }
            else {
                var config2 = {
                    tableId: 'list6',
                    data: [],
                    colArr: colNamesArr2,
                    width: 0.9,
                    height:0.36
                };
                fullTable(config2);
                $("#total").text(total);
                $("#pretotal").text(pretotal);
            }

        });


    });


    $("#sureshutup").click(function(){
        var list6Data= getSeRowData("list6");
        if(list6Data.length<=0){
            toastr.warning("请选择你要出库的数据")
            return false;
        }
        // var list4NewDatas=[];
        var i=1;
        for(var i in  list6Data){
            /* if(parseInt(list6Data[i].NNum)<=0){
                 toastr.warning("第",i+"行的"+list6Data[i].MaterialCode+"出库数量不能小于等于零");
                 return false;
             }*/
            /*if(parseInt(list6Data[i].NNum)>parseInt(list6Data[i].Num)){
                toastr.warning("第",i+"行的"+list6Data[i].MaterialCode+"出库数量不能大于库存数量");
                return false;
            }*/


            var list4NewDatas={
                "addid":i,
                "OutDtlRd":"",
                "Batch":list6Data[i].Batch,
                "BarType":list6Data[i].BarType,
                "LRd":list6Data[i].LRd,
                "LName":list6Data[i].LName,
                "MaVerRd":list6Data[i].MaVerRd,
                "MaCode":list6Data[i].MaterialCode,
                "MaName":list6Data[i].MaterialName,
                "MaDes":list6Data[i].MaterialDes,
                "StoreRd":list6Data[i].StoreRd,
                "StoreName":list6Data[i].StoreName,
                "Num":list6Data[i].Num,
                "UnitName":list6Data[i].UnitName,
                "Remark":"",
                "F1":list6Data[i].PowerGear,
                "F2":list6Data[i].ELGear,
                "F3":list6Data[i].ColorGear,
                "F4":list6Data[i].GradeName,
            }
            addSrow("list4",list4NewDatas);
            i++;
        }
        $("#myModal").modal('hide');
        //$("#myModal").removeAttr("data-backdrop");
        //$("#myModal").removeAttr("data-keyboard");

        /* var config1 = {
             tableId: 'list4',
             data: list4NewDatas,
             colArr: colNamesArr,
             multiselect: true,
             width: 0.84,
             height: 0.415
         };
         fullTable(config1);
 */


    });



//导出
    $("._export").on('click', function () {
        layer.confirm('确认要导出该库存信息？', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            layer.closeAll("dialog");


            var time = $("#test10").val();
            var createtime1 = null;
            var createtime2 = null;
            var batch=$("#batch").val().trim();


            var MaVerRd= 0;
            var StoreRd=0;
            var LRd=0;
            var F1=$("#gonglv").val();
            var F2=$("#dianliu").val();
            var F3=$("#yanse").val();
            var F4=$("#dengji").val();
            MaVerRd= $("#chanpin").getseldata().MaVerRd;
            StoreRd=$("#cangku").getseldata().StoreRd;
            LRd=0;
            var data={};
            if(MaVerRd>0){
                data.MaVerRd=MaVerRd;
            }
            if (time != "") {
                data.CreateTime1 = time.split(" - ")[0];
                data.CreateTime2 = time.split(" - ")[1];
            }
            if(batch!=""){
                data.Batch=batch;
            }

            if(StoreRd>0){
                data.StoreRd=StoreRd;
            }
            if(LRd>0){
                data.LRd=LRd;
            }
            if(F1!=""){
                data.F1=F1;
            }
            if(F2!=""){
                data.F2=F2;
            }
            if(F3!=""){
                data.F3=F3;
            }
            if(F4!=""){
                data.F4=F4;
            }
            var url = getBasePath() + "/InStore/CPExport";



            var form = $("<form>");
            form.attr('style', 'display:none');
            form.attr('target', '');
            form.attr('method', 'post');
            form.attr('action', url);

            var input1 = $('<input>');
            input1.attr('type', 'hidden');
            input1.attr('name', 'MaVerRd');
            input1.attr('value', data.MaVerRd==undefined?null:data.MaVerRd);


            var input2 = $('<input>');
            input2.attr('type', 'hidden');
            input2.attr('name', 'StoreRd');
            input2.attr('value', data.StoreRd==undefined?null:data.StoreRd);


            var input3 = $('<input>');
            input3.attr('type', 'hidden');
            input3.attr('name', 'LRd');
            input3.attr('value', data.LRd=undefined?null:data.LRd);


            var input4 = $('<input>');
            input4.attr('type', 'hidden');
            input4.attr('name', 'F1');
            input4.attr('value', data.F1=undefined?null:data.F1);


            var input5 = $('<input>');
            input5.attr('type', 'hidden');
            input5.attr('name', 'F2');
            input5.attr('value', data.F2==undefined?null:data.F2);


            var input6 = $('<input>');
            input6.attr('type', 'hidden');
            input6.attr('name', 'F3');
            input6.attr('value',data.F3==undefined?null:data.F3);


            var input7 = $('<input>');
            input7.attr('type', 'hidden');
            input7.attr('name', 'F4');
            input7.attr('value',data.F4==undefined?null:data.F4);


            var input8 = $('<input>');
            input8.attr('type', 'hidden');
            input8.attr('name', 'CreateTime1');
            input8.attr('value',data.CreateTime1==undefined?null:data.CreateTime1);


            var input9 = $('<input>');
            input9.attr('type', 'hidden');
            input9.attr('name', 'CreateTime2');
            input9.attr('value', data.CreateTime2==undefined?null:data.CreateTime2);


            var input10 = $('<input>');
            input10.attr('type', 'hidden');
            input10.attr('name', 'Batch');
            input10.attr('value', data.Batch==undefined?null:data.Batch);



            /* JSON.stringify($.serializeObject($('#searchForm'))) */

            $('body').append(form);
            form.append(input1);
            form.append(input2);
            form.append(input3);
            form.append(input4);
            form.append(input5);
            form.append(input6);
            form.append(input7);
            form.append(input8);
            form.append(input9);
            form.append(input10);

            form.submit();
            form.remove();

           /* return;

          //  var data_ =  JSON.stringify(data);
            var xhr = new XMLHttpRequest();
            xhr.open('POST', url, true);
            xhr.responseType = 'arraybuffer';
            xhr.onload = function () {
                if (this.status === 200) {
                    var filename = "";
                    var disposition = xhr.getResponseHeader('Content-Disposition');
                    if (disposition && disposition.indexOf('attachment') !== -1) {
                        var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                        var matches = filenameRegex.exec(disposition);
                        if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');

                        var type = xhr.getResponseHeader('Content-Type');

                        var date = new Date();

                        filename = "库存-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

                        var blob = new Blob([this.response], {type: type});
                        if (typeof window.navigator.msSaveBlob !== 'undefined') {
                            window.navigator.msSaveBlob(blob, filename);
                        } else {
                            var URL = window.URL || window.webkitURL;
                            var downloadUrl = URL.createObjectURL(blob);

                            if (filename) {

                                var a = document.createElement("a");

                                if (typeof a.download === 'undefined') {
                                    window.location = downloadUrl;
                                } else {
                                    a.href = downloadUrl;
                                    a.download = filename;
                                    document.body.appendChild(a);
                                    a.click();
                                }
                            } else {
                                window.location = downloadUrl;
                            }

                            setTimeout(function () {
                                URL.revokeObjectURL(downloadUrl);
                            }, 100); // cleanup
                        }
                    } else {
                        var un = new Uint8Array(this.response);
                        var s = "";
                        for (var i = 0; i < un.length; i++) {
                            s += String.fromCharCode(un[i]);
                        }
                        var json = JSON.parse(s);
                    }
                }
            };
            xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
          xhr.send(data_);*/
        });
    });


});
