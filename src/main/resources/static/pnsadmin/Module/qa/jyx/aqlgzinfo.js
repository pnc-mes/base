$(function(){
    var resultFlag=false;
    //定义第一个表格的修改和新增
    var isUpdate1=false;
    //定义第二个表格的修改和新增
    var isUpdate2=false;
    //定义第三个个表格的修改和新增
    var isUpdate3=false;
    //定义第三个个表格的修改和新增
    var isUpdate4=false;

    //每次定义表格的新增的序号变化
    var addid1=1;

    var addid2=1;

    var addid3=1;

    $(".cAdd").click(function(){
        $(".right").show();
        treeID = null;
    });
    $(".add1").click(function(){
        isUpdate1=true;
        $("#liemingcheng1").val("");
        $("#liebieming1").val("");
        $("#SamplingNum").val("");
        $("#AcceptNum").val("");
        $("#RejectNum").val("");
        $("#select1").val("01");
        $("#aa").hide();
        $("#xsnezd").val("");
        $("#zdz").val("");
        $("#txtRea").val("");
        addid1=$("#tbody").find("tr:last").children().eq(1).text();
    });

    $("#select1").change(function () {
        var value=$(this).find("option:selected").val();
        if(value=="00"){
            $("#aa").show();
            $("#xsnezd").val("");
            $("#zdz").val("");
            $("#txtRea").val("");
        }else {
            $("#aa").hide();
        }
    });

    var i=1;
    //查询表格设置的新增
    $("#savadetail1").click(function(){
        var ColumnName=$("#liemingcheng1").val().trim();
        var AliasName=$("#liebieming1").val().trim();
     /*   var SamplingNum=$("#SamplingNum").val().trim();
        var AcceptNum=$("#AcceptNum").val().trim();
        var RejectNum=$("#RejectNum").val().trim();*/
        var SamplingNum=$("#SamplingNum").val().trim();
        var AcceptNum=$("#AcceptNum").val().trim();
        var RejectNum=$("#RejectNum").val().trim();
        var select1=$("#select1").find("option:selected").val();
        var selecttext=$("#select1").find("option:selected").text();
        var DisFieldName="";
        var ValFiledName="";
        var SqlConfig="";
        if(select1=="00"){
            DisFieldName=$("#xsnezd").val().trim();
            ValFiledName=$("#zdz").val().trim();
            SqlConfig=$("#txtRea").val().trim();
        }

        // var isupdate=$("#liemingcheng1").attr("a");
        addid1++;
        if(isUpdate1){
            $("#tbody").append("<tr><td><input type='checkbox'/></td><td>"+addid1+"</td><td>"+ColumnName+"</td><td>"+AliasName+"</td><td>"+SamplingNum+"</td><td>"+AcceptNum+"</td><td>"+RejectNum+"</td></tr>");
            i++;
        }else {
            $("#tbody input[type='checkbox']").each(function(){
                if($(this).is(":checked")){
                    var value=$(this).parent().next().text();
                    $(this).parent().parent().empty().append("<td><input type='checkbox'/></td><td>"+value+"</td><td DisFieldName='"+DisFieldName+"' ValFiledName='"+ValFiledName+"' SqlConfig='"+SqlConfig+"'>"+ColumnName+"</td><td>"+AliasName+"</td><td>"+SamplingNum+"</td><td>"+AcceptNum+"</td><td>"+RejectNum+"</td>");
                }
            });
        }
        $('#myModal1').modal('hide');
    });

    //查询表格编辑
    $(".edit1").click(function(){
        isUpdate1=false;
        var checklength=$("#tbody input[type='checkbox']:checked");
        if (checklength.length == 0) {         //如果不勾选弹出警告框
            toastr.warning("请至少选择一个数据集");
            return false;
        }
        if(checklength.length>1){
            toastr.warning("请选择你要编辑的一项再进行编辑");
            return false;
        }
        var index="";
        var lmc="";
        var lbm="";
        var zly="";
       var  SamplingNum="";
        var  AcceptNum="";
        var  RejectNum="";
        var DisFieldName="";
        var ValFiledName="";
        var SqlConfig="";
        var liemingcheng1="";
        $("#tbody input[type='checkbox']").each(function(){
            if($(this).is(":checked")){
                index=$(this).parent().next().text();
                lmc=$(this).parent().next().next().text();
                DisFieldName=$(this).parent().next().next().attr("DisFieldName");
                ValFiledName=$(this).parent().next().next().attr("ValFiledName");
                SqlConfig=$(this).parent().next().next().attr("SqlConfig");
                lbm=$(this).parent().next().next().next().text();
                SamplingNum=$(this).parent().next().next().next().next().text();
                AcceptNum=$(this).parent().next().next().next().next().next().text();
                RejectNum=$(this).parent().next().next().next().next().next().next().text();
                zly=$(this).parent().next().next().next().next().text();
            }
        });
        $("#liemingcheng1").val(lmc);
        $("#liemingcheng1").attr("a",index);
        $("#liebieming1").val(lbm);
        $("#SamplingNum").val(SamplingNum);
        $("#AcceptNum").val(AcceptNum);
        $("#RejectNum").val(RejectNum);
        var value="";
        if(zly=="下拉框"){
            value="00";
        }else if(zly=="文本框"){
            value="01";
        }else if(zly=="日期段"){
            value="02";
        }else if("时间段") {
            value="03";
        }else {
            value="04";
        }

        $('#select1').val(value);
        if(value=="00"){
            $("#aa").show();
            $("#xsnezd").val(DisFieldName);
            $("#zdz").val(ValFiledName);
            $("#txtRea").val(SqlConfig);
        }else {
            $("#aa").hide();
            $("#xsnezd").val("");
            $("#zdz").val("");
            $("#txtRea").val("");
        }
    });

    //查询表格删除
    $(".del1").click(function(){
        var checklength=$("#tbody input[type='checkbox']:checked");
        if (checklength.length == 0) {         //如果不勾选弹出警告框
            toastr.warning("请至少选择一个数据集");
            return false;
        }
        $("#tbody input[type='checkbox']").each(function(){
            if($(this).is(":checked")){
                $(this).parent().parent().remove();
            }
        });
        var i1=1;
        $("#tbody tr").each(function(){
            var tdArr = $(this).children();
            tdArr.eq(1).text(i1);
            i1++;
        })
    });
/*    $(".adddel").click(function(){
        var checklength=$("#tbody5add input[type='checkbox']:checked");
        if (checklength.length == 0) {         //如果不勾选弹出警告框
            toastr.warning("请至少选择一个数据集");
            return false;
        }
        $("#tbody5add input[type='checkbox']").each(function(){
            if($(this).is(":checked")){
                $(this).parent().parent().remove();
            }
        });
    });*/


    //表格查询的上移动
    $(".up1").click(function(){
        var checklength=$("#tbody input[type='checkbox']:checked");
        if (checklength.length == 0) {
            toastr.warning("请至少选择一个数据集");
            return false;
        }
        if (checklength.length > 1) {
            toastr.warning("请选择你要操作的一项");
            return false;
        }

        $.each($("#tbody input:checked"), function () {
            var onthis = $(this).parent().parent();
            var getUp = onthis.prev();
            //  $(onthis).fadeOut().fadeIn();
            $(onthis).after(getUp);
        });


        var i1=1;
        $("#tbody tr").each(function(){
            var tdArr = $(this).children();
            tdArr.eq(1).text(i1);
            i1++;
        })

    });
    //表格查询的下移动
    $(".down1").click(function(){
        var checklength=$("#tbody input[type='checkbox']:checked");
        if (checklength.length == 0) {         //如果不勾选弹出警告框
            toastr.warning("请至少选择一个数据集");
            return false;
        }
        if (checklength.length > 1) {
            toastr.warning("请选择你要操作的一项");
            return false;
        }
        $.each($("#tbody input:checked"),function(){
            var $tr = $(this).parents("tr");
            if ($tr.index() != checklength - 1) {
                $tr.next().after($tr);
            }
        });
        var i1=1;
        $("#tbody tr").each(function(){
            var tdArr = $(this).children();
            tdArr.eq(1).text(i1);
            i1++;
        })
    });


/*
    $(".saveend").click(function(){
        $("#endicon").hide();
        var ConfigName=$("#pzmc").val().trim();

        var SqlConfig=$("#sqltext").val().trim();

        var Columns=[];
        $("#tbody5s").find("tr").each(function(){
            var tdArr = $(this).children();

            var Column={
                "ColumnName":tdArr.eq(1).text(),
                "OutPutText":tdArr.eq(2).text(),
            }
            Columns.push(Column);
        });


        var SearchFields=[];
        $("#tbody8").find("tr").each(function(){
            var tdArr = $(this).children();

            var SearchField={
                "CdName":tdArr.eq(1).text(),
                "AliasName":tdArr.eq(2).text(),
            }
            SearchFields.push(SearchField);
        });

        if(isUpdate3){
            $("#tbody5add").append("<tr><td><input type='checkbox'/></td><td Columns='"+JSON.stringify(Columns)+"' SearchFields='"+JSON.stringify(SearchFields)+"' SqlConfig='"+SqlConfig+"'>"+ConfigName+"</td></tr>");
        }else {
            $("#tbody5add input[type='checkbox']").each(function(){
                if($(this).is(":checked")){
                    $(this).parent().parent().empty().append("<td><input type='checkbox'/></td><td Columns='"+JSON.stringify(Columns)+"' SearchFields='"+JSON.stringify(SearchFields)+"' SqlConfig='"+SqlConfig+"'>"+ConfigName+"</td>");
                }
            });
        }


        $("#tjszs").parent().attr("class","active");
        $("#tjszs").attr("aria-expanded",true);

        $("#tjmxs").hide();
        $("#tjmxs").removeAttr("aria-expanded");
        $("#tjmxs").parent().removeAttr("class");

        $("#tjmx").removeAttr("class");
        $("#tjmx").attr("class","tab-pane fade");
        $("#tjsz").attr("class","tab-pane fade active in");



    });*/

/*    $(".addbianji").click(function(){
        $("#endicon").show();
        isUpdate3=false;
        var checklength=$("#tbody5add input[type='checkbox']:checked");
        if (checklength.length == 0) {         //如果不勾选弹出警告框
            toastr.warning("请至少选择一个数据集");
            return false;
        }
        if(checklength.length>1){
            toastr.warning("请选择你要编辑的一项再进行编辑");
            return false;
        }
        var pzmc="";
        var SqlConfig="";
        var Columns="";
        var SearchFields="";
        $("#tbody5add input[type='checkbox']").each(function(){
            if($(this).is(":checked")){
                pzmc=$(this).parent().next().text();
                SqlConfig=$(this).parent().next().attr("SqlConfig");
                Columns=$(this).parent().next().attr("Columns");
                SearchFields=$(this).parent().next().attr("SearchFields");
            }
        });
        $("#pzmc").val(pzmc);
        $("#sqltext").val(SqlConfig);
        var rtr_="";
        var l=1;
        var first = JSON.parse(Columns);
        if(first.length>0){
            for(var i  in  first){
                rtr_+="<tr><td><input type='checkbox'/></td><td>"+ l+"</td><td>"+first[i].ColumnName+"</td><td>"+first[i].OutPutText+"</td></tr>"
                l++;
            }
            $("#tbody7").empty().html(rtr_);
        }
        var firsts = JSON.parse(SearchFields);
        var trts_="";
        if(firsts.length>0){
            for(var j  in  firsts){
                trts_+="<tr><td><input type='checkbox'/><td>"+firsts[j].CdName+"</td><td>"+firsts[j].AliasName+"</td></tr>"
            }
            $("#tbody8").empty().html(trts_);

        }
        $("#tjszs").removeAttr("aria-expanded");
        $("#tjszs").parent().removeAttr("class");

        $("#tjmxs").show();
        $("#tjmxs").parent().attr("class","active");
        $("#tjmxs").attr("aria-expanded",true);

        $("#tjsz").removeAttr("class");
        $("#tjsz").attr("class","tab-pane fade");
        $("#tjmx").attr("class","tab-pane fade active in");
    });*/

/*    $(".add3").click(function(){
        var datas=[];
        $("#tbody").find("tr").each(function(){
            var tdArr = $(this).children();
            var data={
                "text":tdArr.eq(2).text(),
                "textvalue":tdArr.eq(3).text()
            }
            datas.push(data);
        });

        var tbody5s1="";
        for(var i in datas){
            tbody5s1+="<tr><td><input type='checkbox'/></td><td>"+datas[i].text+"</td><td>"+datas[i].textvalue+"</td></tr>"
        }
        $("#tbody5s1").empty().html(tbody5s1);
        //alert(JSON.stringify(datas))
    });*/
/*    $("#savadetail6").click(function(){

        var checklength=$("#tbody5s1 input[type='checkbox']:checked");
        var datas=[];
        $.each($("#tbody5s1 input:checked"),function(){
            var data={
                "text":$(this).parent().next().text(),
                "textvalue":  $(this).parent().next().next().text(),
            }
            datas.push(data);
        })

        $("#tbody5s").find("tr").each(function(){
            var tdArr = $(this).children();
            var data={
                "text":tdArr.eq(1).text(),
                "textvalue":tdArr.eq(2).text()
            }
            datas.push(data);
        });

        var tbody5s1="";
        for(var i in datas){
            tbody5s1+="<tr><td><input type='checkbox'/></td><td>"+datas[i].text+"</td><td>"+datas[i].textvalue+"</td></tr>"
        }
        $("#tbody5s").empty().html(tbody5s1);


        $('#myModal6').modal('hide');
    });*/

/*    $(".del8").click(function(){
        var checklength=$("#tbody8 input[type='checkbox']:checked");
        if (checklength.length == 0) {         //如果不勾选弹出警告框
            toastr.warning("请至少选择一个数据集");
            return false;
        }
        $("#tbody8 input[type='checkbox']").each(function(){
            if($(this).is(":checked")){
                $(this).parent().parent().remove();
            }
        });
    });*/

    var treeID = null;
    /*-----------2:点击树的节点的时候获取技能信息----------------------------*/
    var onClicks = function (nodeinfo, handle) {
        $("#endicon").hide();
        resultFlag=true;
        $('#_right').show();
  /*      $("#chaxun").parent().attr("class","active");
        $("#chaxun").attr("aria-expanded",true);*/
        $("#home").removeAttr("class");
        $("#home").attr("class","tab-pane fade");
        $("#home").attr("class","tab-pane fade active in");

     /*   $("#bgcx").parent().removeAttr("class");
        $("#bgcx").attr("aria-expanded",false);
        $("#bgsz").attr("class","tab-pane fade");

        $("#tjszs").parent().removeAttr("class");
        $("#tjszs").attr("aria-expanded",false);
        $("#tjsz").attr("class","tab-pane fade");

        $("#tjmxs").parent().removeAttr("class");
        $("#tjmxs").attr("aria-expanded",false);
        $("#tjmx").attr("class","tab-pane fade");
        $("#tjmxs").hide();
        $("#tjmxs").removeAttr("aria-expanded");
        $("#tjmxs").parent().removeAttr("class");*/

        treeID = nodeinfo.nodeID;

        var objBusData = JSON.stringify({"AQLRuleRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        }
        request({url: "/AQL/GetAQLInfo", data: objData}, function (Body) {
            $("#AQLRuleName").val(Body.Data.AQLRuleName);  //名称
            $("#defaultSelect").showData({                //水平下拉框
                id:Body.Data.CheckLevel.CLevelRd,
                name:Body.Data.CheckLevel.CheckLevelName,
                keyfield:"CLevelRd",
                fields:[
                    {"name":"CLevelRd"},
                    {"name":"CheckLevelName"}
                ]
            });
            var aa=Body.Data.AQLDtlInfo;
            if(aa!=null){
                var tr_="";
                var j=1;
                for(var i  in  aa){
                    tr_+=    "<tr><td><input type='checkbox' a/></td><td>"+j+"</td><td> "+aa[i].MinNum+"</td><td>"+aa[i].MaxNum+"</td><td>"+aa[i].SamplingNum+"</td><td>"+aa[i].AcceptNum+"</td><td>"+aa[i].RejectNum+"</td></tr>";
                    j++;
                }
                $("#tbody").empty().html(tr_);
            }else {
                $("#tbody").empty();
            }
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
        currentPage = treeSearchs("/AQL/GetAllAQLInfo","AQLRuleRd","AQLRuleName","AQLRuleName",condition,currentPage,config);
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
            currentPage = treeSearchs("/AQL/GetAllAQLInfo","AQLRuleRd","AQLRuleName","AQLRuleName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/AQL/GetAllAQLInfo","AQLRuleRd","AQLRuleName","AQLRuleName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/AQL/GetAllAQLInfo","AQLRuleRd","AQLRuleName","AQLRuleName",condition,currentPage,config);
    });
    $("#_right").hide();
    /*-------- 4:只刷新树(提高性能)---------*/



    //下拉框
    var params = {
        "displaymode": "0",
        "title": "检验水平",
        "binddata": {
            "keyfield": "CLevelRd",
            "fields": [
                {
                    "caption": "水平id",
                    "name": "CLevelRd"
                }, {
                    "caption": "检验水平名称",
                    "name": "CheckLevelName"
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
                            "FieldName": "CheckLevelName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                //加载下拉框企业信息
                request({url: "/CLevel/GetAllCLevelInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "CLevelRd": datas[i].CLevelRd,
                            "CheckLevelName": datas[i].CheckLevelName,
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
    };

    $("#defaultSelect").zc_select(params);
    var loaddata = function () {
        var trees = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"AQLRuleName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        request({url:'/AQL/GetAllAQLInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){
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
            for (var i = 0; i <len; i++) {
                var tree = {
                    id: treeData[i].AQLRuleRd == undefined ? "" : treeData[i].AQLRuleRd,
                    name: treeData[i].AQLRuleName == undefined ? "" : treeData[i].AQLRuleName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();

    $(".cAdd").click(function(){

        addid1=1;
        addid2=1;
        addid3=1;
        resultFlag=true
        $("#AQLRuleName").val("");
        $("#defaultSelect").clearseldata("CLevelRd");
        $("#tbody").empty();
        $("#home").removeAttr("class");
        $("#home").attr("class","tab-pane fade");
        $("#home").attr("class","tab-pane fade active in");
    });

    //保存
    $(".cSave").click(function(){
        var CLevelRd =  $("#defaultSelect").getseldata().CLevelRd;
        if(!resultFlag){
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }
        var AQLRuleName=$("#AQLRuleName").val().trim();
        var AQLDtlInfo1=[];
        $("#tbody").find("tr").each(function(){
            var tdArr = $(this).children();
            if(tdArr.eq(2).text()==null||""==tdArr.eq(2).text()){
                toastr.warning("批次最小数量不能为空");
                return false;
            }
            if(tdArr.eq(3).text()==null||""==tdArr.eq(3).text()){
                toastr.warning("批次最大数量不能为空");
                return false;
            }
            if(tdArr.eq(4).text()==null||""==tdArr.eq(4).text()){
                toastr.warning("抽检数量不能为空");
                return false;
            }
            if(tdArr.eq(5).text()==null||""==tdArr.eq(5).text()){
                toastr.warning("合格数量不能为空");
                return false;
            }
            if(tdArr.eq(6).text()==null||""==tdArr.eq(6).text()){
                toastr.warning("不合格数量不能为空");
                return false;
            }

        });
        $("#tbody").find("tr").each(function(){
            var tdArr = $(this).children();

                var Field={
                  //  "AQLRuleRd":treeID,
                    "MinNum":tdArr.eq(2).text(),
                    "MaxNum":tdArr.eq(3).text(),
                    "SamplingNum":tdArr.eq(4).text(),
                    "AcceptNum":tdArr.eq(5).text(),
                    "RejectNum":tdArr.eq(6).text(),
                }
                AQLDtlInfo1.push(Field);

        });


        if($("#AQLRuleName").val().trim()==null||""==$("#AQLRuleName").val().trim()){
            toastr.warning("配置SQL名称不能为空");
            return false;
        }

        var Columns=[];
        $("#tbody5").find("tr").each(function(){
            var tdArr = $(this).children();
            var ColumnType="";
            var type=tdArr.eq(4).text();
            if(type=="文本"){
                ColumnType="00";
            }else if(type=="链接"){
                ColumnType="01";
            }
            var IsDisplay="";
            if(tdArr.eq(5).text()=="是"){
                IsDisplay="00";
            }else {
                IsDisplay="01";
            }
            var Truncated="";
            if(tdArr.eq(7).text()=="是"){
                Truncated="00";
            }else {
                Truncated="01";
            }
            var Column={
                "ColumnName":tdArr.eq(2).text(),
                "AliasName":tdArr.eq(3).text(),
                "ColumnType":ColumnType,
                "IsDisplay":IsDisplay,
                "ColumnWidth":tdArr.eq(6).text(),
                "Truncated":Truncated
            }
            Columns.push(Column);
        });


        var SearchFields=[];
        $("#tbody5s").find("tr").each(function(){
            var tdArr = $(this).children();

            var SearchField={
                "CdName":tdArr.eq(1).text(),
                "AliasName":tdArr.eq(2).text(),
            }
            SearchFields.push(SearchField);
        });
        var CSTotal=[];
        if(treeID==null||treeID==""){
            var Data={
                "CLevelRd": CLevelRd,
                "AQLRuleName":AQLRuleName,
                "AQLDtlInfo":AQLDtlInfo1
            }
            console.log(Data)
            request({url:"/AQL/SaveAQLInfo", data: {"ExecType": "00", "busData": JSON.stringify(Data)}},function(Body){
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }else {
            var AQLDtlInfo=[];
            $("#tbody").find("tr").each(function(){
                var tdArr = $(this).children();
                var Field={
                    //  "AQLRuleRd":treeID,
                    "MinNum":tdArr.eq(2).text(),
                    "MaxNum":tdArr.eq(3).text(),
                    "SamplingNum":tdArr.eq(4).text(),
                    "AcceptNum":tdArr.eq(5).text(),
                    "RejectNum":tdArr.eq(6).text(),
                }
                AQLDtlInfo.push(Field);

            });
            var datas={
                "AQLRuleRd":treeID,
                "AQLRuleName":AQLRuleName,
                "CLevelRd": CLevelRd,
                "AQLDtlInfo":AQLDtlInfo
            }
            request({url:"/AQL/SaveAQLInfo", data: {"ExecType": "02", "busData": JSON.stringify(datas)}},function(Body){
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }
    });

    //删除
    $(".cDel").click(function () {
        if(treeID==null){
            toastr.warning("请选择左侧要删除的一项再进行删除!");
            return false;
        }
        layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({url:"/AQL/SaveAQLInfo", data: {"ExecType": "01", "busData": "{\"AQLRuleRd\":" + treeID + "}"}},function(Body){
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    $("#_right").hide();
                    loaddata();
                    treeID = null;
                    currentPage=0;
                    condition = '';
                });
            }
        );



    });


    var objBusData = {"RoleRd": ""};


    request({
        url: "/Role/GetRoleInfo", async: false,//设为同步请求
        data: {
            "ExecType": "00",
            "BusData": JSON.stringify(objBusData),
        }
    }, function (Body) {
        $("#table>#tbody").empty();
        if (Body.MsgCode == "0x00000") {

            var MenuInfoList = Body.Data.MenuInfo;
            if (MenuInfoList == null || MenuInfoList.length == 0) {
                return;
            }
            var strModule = "";
            var strModule1 = "";
            var strModule2 = "";
            var strRes = "";
            var i = 0;
            $.each(MenuInfoList, function (key, MenuInfo) {
                strModule = "<tr style='display:block;width: 500px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='modulerow'>" +
                    "<td style='width: 300px; border:0;cellspacing:0;cellpadding:0;' class='guanli' id='" + MenuInfo.ModuleRd + "'>" + "<a class='Icons'  id='icon" + i + "' style='margin-right: 5px;'><i class='fa fa-clipboard fa-fw'></i></a>" + "<input style='margin-right: 5px; margin-top: 10px;' class = 'Taisyo'  name='radio'  r='" + MenuInfo.ModuleRd + "' type='radio' id='checkname" + i + "'/>" + MenuInfo.ModuleName + "</td>" +
                    "<td style='width: 500px; border:0;cellspacing:0;cellpadding:0;'></td>" +
                    "</tr>";
                $("#table>#tbodynew").append(strModule);
                var j = 1;
                if (MenuInfo.ResInfo != undefined && MenuInfo.ResInfo.length > 0) {
                    $.each(MenuInfo.ResInfo, function (key, ResInfo) {
                        strRes = "<tr class='resrow' id='trRow" + i + "win" + j + "' style='display:none;width: 500px;height: 30px;'><td style='width: 300px;' class='xinxiushan' pid='" + MenuInfo.ModuleRd + "' id='" + ResInfo.ResRd + "'>" + "<span class='resname' style='margin-left: 30px'>" + "<a style='margin-right: 5px; margin-left: 5px' class='Icons' id='icon_file" + i + "'><i class='fa fa-file-text fa-fw'></i></a>" + "<input  style='margin-right: 5px;'class = 'Taisyo' r='"+ResInfo.ResRd+"'  name='radio' type='radio' id='checkname" + i + "win" + j + "'/>" + ResInfo.ResName + "</span>" + "</td><td style='width: 500px;'>";
                        if (ResInfo.OptInfo != null && ResInfo.OptInfo.length > 0) {
                            /*$.each(ResInfo.OptInfo, function (key, OptInfo) {
                             strRes += "&nbsp;&nbsp;&nbsp;" + "<input type='radio'  name='radio' id='checkname" + i + "win" + j + "' value='" + OptInfo.OptRd + "' " + (OptInfo.Checked == "00" ? "checked" : "") + "/>" + "&nbsp;" + "<span>" + OptInfo.OptName + "</span>" + "&nbsp;&nbsp;";
                             });*/
                        }
                        strRes += "</td></tr>";
                        $("#table>#tbodynew").append(strRes);
                        j++;
                    });
                }
                if (MenuInfo.MenuInfo.length > 0) {
                    $.each(MenuInfo.MenuInfo, function (key, MenuInfo_for) {
                        var reqs = i + "win" + j;
                        var menuInfo_f = [];
                        menuInfo_f = MenuInfo_for;
                        MenuInfosFun_Add(menuInfo_f, reqs, menuInfo_f.ModuleName, 0);
                        j++;
                    });
                }
                ;
                i++;
            });
        }
    });

    var MenuInfosFun_Add = function (MenuInfo, i, lastMenuInfo, leftLenght) {
        lastMenuInfo = lastMenuInfo + ">" + MenuInfo.ModuleName;
        leftLenght = leftLenght + 20;
        var leftRowLenght = leftLenght + 30;
        strModule = "<tr style='display:none;width: 500px;height: 30px;border:0;cellspacing:0;cellpadding:0;' class='modulerow' id='trRow" + i + "'>"
            + "<td style='width: 300px; border:0;cellspacing:0;cellpadding:0;' class='guanli' id='" + MenuInfo.ModuleRd + "'>"
            + "<span style='margin-left:" + leftLenght + "px;'><a class='Icons'  style='margin-right: 5px;' id='icon" + i + "'><i class='fa fa-clipboard fa-fw'></i></a><input style='margin-right: 5px; margin-top: 10px;'  r='"+MenuInfo.ModuleRd+"'  class = 'Taisyo' type='radio' name='radio' id='checkname" + i + "'/>" + MenuInfo.ModuleName + "</span>"
            + "</td><td style='width: 500px; border:0;cellspacing:0;cellpadding:0;'></td></tr>";
        $("#table>#tbodynew").append(strModule);
        if (MenuInfo.ResInfo == null || MenuInfo.ResInfo.length == 0) {
            return false;
        }

        var z = 1;
        $.each(MenuInfo.ResInfo, function (key, ResInfo) {
            strRes = "<tr class='resrow' id='trRow" + i + "_" + z + "' style='display:none;width: 500px;height: 30px;'><td style='width: 300px;' class='xinxiushan' pid='" + MenuInfo.ModuleRd + "' id='" + ResInfo.ResRd + "'>" + "<span class='resname' style='margin-left: " + leftRowLenght + "px'>" + "<a style='margin-right: 5px; margin-left: 5px' class='Icons' id='icon_file" + i + "'><i class='fa fa-file-text fa-fw'></i></a>" + "<input style='margin-right: 5px;' r='"+ResInfo.ResRd+"'  class = 'Taisyo' type='radio' name='radio' id='checkname" + i + "_" + z + "'/>" + ResInfo.ResName + "</span>" + "</td><td style='width: 500px;'>";
            if (ResInfo.OptInfo != null && ResInfo.OptInfo.length > 0) {
                /*$.each(ResInfo.OptInfo, function (key, OptInfo) {
                 strRes += "&nbsp;&nbsp;&nbsp;" + "<input type='radio'  name='radio' id='checkname" + i + "_" + z + "' value='" + OptInfo.OptRd + "' " + (OptInfo.Checked == "00" ? "checked" : "") + "/>" + "&nbsp;" + "<span>" + OptInfo.OptName + "</span>" + "&nbsp;&nbsp;";
                 });*/
            }
            strRes += "</td></tr>";
            $("#table>#tbodynew").append(strRes);
            z++;
        });
        if (MenuInfo.MenuInfo.length > 0) {
            $.each(MenuInfo.MenuInfo, function (key, MenuInfo_funFor) {
                idSize = i + "_" + z;
                var menuInfo_fun = [];
                menuInfo_fun = MenuInfo_funFor;
                MenuInfosFun_Add(menuInfo_fun, idSize, lastMenuInfo, leftLenght);
                z++;
            });

        }
    };

    $(document).on("click", ".Icons", function () {
        var icon = $(this)[0];
        var ids = "#trRow";
        var Number = icon.id.substring(4, icon.id.length);
        for (var i = 1; i < 10000; i++) {
            var idNumber = ids + Number + "win" + i;
            var idNumber_ = ids + Number + "_" + i;
            if ($(idNumber)[0] != undefined) {
                var style_ = $(idNumber).attr("style").substring(0, 14);
                var style_ours = $(idNumber).attr("style").substring(14, $(idNumber).attr("style").length);
                var style_block = "display:block;";
                var style_none = "display: none;";
                var istrue;
                if (style_ == style_block) {
                    $(idNumber).attr("style", style_none + style_ours);
                    istrue = false;
                } else {
                    $(idNumber).attr("style", style_block + style_ours);
                    istrue = true;
                }
                var idNumberA = idNumber + "_1";
                if ($(idNumberA)[0] != undefined) {
                    clickFuns(idNumber, istrue);
                }
            } else if ($(idNumber_)[0] != undefined) {
                var istrue;
                var style_ = $(idNumber_).attr("style").substring(0, 14);
                var style_ours = $(idNumber_).attr("style").substring(14, $(idNumber_).attr("style").length);
                var style_block = "display:block;";
                var style_none = "display: none;";
                if (style_ == style_block) {
                    $(idNumber_).attr("style", style_none + style_ours);
                    istrue = false;
                } else {
                    $(idNumber_).attr("style", style_block + style_ours);
                    istrue = true;
                }
                var idNumberA = idNumber_ + "_1";
                if ($(idNumberA)[0] != undefined) {
                    clickFuns(idNumber_, istrue);
                }
            } else {
                return;
            }
        }
    });

    var clickFuns = function (idNumber, istrue) {
        for (var i = 1; i < 10000; i++) {
            var idNumberA = idNumber + "_" + i;
            if ($(idNumberA)[0] != undefined) {
                var style_ours = $(idNumberA).attr("style").substring(14, $(idNumberA).attr("style").length);
                var style_block = "display:block;";
                var style_none = "display: none;";
                if (istrue) {
                    $(idNumberA).attr("style", style_block + style_ours);
                } else {
                    $(idNumberA).attr("style", style_none + style_ours);
                }
                var idNumberB = idNumberA + "_1";
                if ($(idNumberB)[0] != undefined) {
                    clickFuns(idNumberA, istrue);
                }
            } else {
                return;
            }
        }
    }


    //生成菜单
    $(".sccd").click(function(){
        if(treeID==null||treeID==""){
            toastr.warning("请选择左侧要操作的一项再进行生成菜单");
            return false;
        }
        $("#sccd").val("");




    });





    $("#savadetail3").click(function(){
        var ModuleRd="";

        $('table input').each(function(){
            if($(this).is(":checked")){
                ModuleRd=$(this).attr("r");
            }
        });



        var ResName=$("#sccd").val().trim();

        if(ResName==null||ResName==""){
            toastr.warning("菜单名称不能为空");
            return false;
        }
        if(ModuleRd==null||ModuleRd==""){
            toastr.warning("请选择菜单");
            return false;
        }

        var data={
            "CSConfigRd":treeID,
            "ResName":ResName,
            "ModuleRd":ModuleRd
        }

        request({url:"/CSConfig/SaveCSInfo", data: {"ExecType": "03", "busData": JSON.stringify(data)}},function(Body){
            toastr.success(Body.MsgDes);
            currentPage=0;
            condition = '';
            treeID="";
            loaddata();
        });
        $('#myModal3').modal('hide');
    });

//处理每个表格的checked事件
    $(document).on("click","#check1",function(){
        if($(this).prop("checked")==true){
            $("#tbody input[type='checkbox']").each(function(){
                $(this).prop("checked",true);
            });
        }else {
            $("#tbody input[type='checkbox']").each(function(){
                $(this).prop("checked",false);
            });
        }
    });
    $(document).on("click","#tbody input[type='checkbox']",function(){
        var checklength=$("#tbody input[type='checkbox']:checked");
        var rowlength=$("#tbody tr").length;
        if(checklength.length==rowlength){
            $("#check1").prop("checked",true);
        }else {
            $("#check1").prop("checked",false);
        }
    });

    $(document).on("click","#check2",function(){
        if($(this).prop("checked")==true){
            $("#tbody5 input[type='checkbox']").each(function(){
                $(this).prop("checked",true);
            });
        }else {
            $("#tbody5 input[type='checkbox']").each(function(){
                $(this).prop("checked",false);
            });
        }
    });
    $(document).on("click","#tbody5 input[type='checkbox']",function(){
        var checklength=$("#tbody5 input[type='checkbox']:checked");
        var rowlength=$("#tbody5 tr").length;
        if(checklength.length==rowlength){
            $("#check2").prop("checked",true);
        }else {
            $("#check2").prop("checked",false);
        }
    });

    $(document).on("click","#check3",function(){
        if($(this).prop("checked")==true){
            $("#tbody5s input[type='checkbox']").each(function(){
                $(this).prop("checked",true);
            });
        }else {
            $("#tbody5s input[type='checkbox']").each(function(){
                $(this).prop("checked",false);
            });
        }
    });
    $(document).on("click","#tbody5s input[type='checkbox']",function(){
        var checklength=$("#tbody5s input[type='checkbox']:checked");
        var rowlength=$("#tbody5s tr").length;
        if(checklength.length==rowlength){
            $("#check3").prop("checked",true);
        }else {
            $("#check3").prop("checked",false);
        }
    });

    $(document).on("click","#check4",function(){
        if($(this).prop("checked")==true){
            $("#tbody5s1 input[type='checkbox']").each(function(){
                $(this).prop("checked",true);
            });
        }else {
            $("#tbody5s1 input[type='checkbox']").each(function(){
                $(this).prop("checked",false);
            });
        }
    });
    $(document).on("click","#tbody5s1 input[type='checkbox']",function(){
        var checklength=$("#tbody5s1 input[type='checkbox']:checked");
        var rowlength=$("#tbody5s1 tr").length;
        if(checklength.length==rowlength){
            $("#check4").prop("checked",true);
        }else {
            $("#check4").prop("checked",false);
        }
    });

    $(document).on("click","#check5",function(){
        if($(this).prop("checked")==true){
            $("#tbody5add input[type='checkbox']").each(function(){
                $(this).prop("checked",true);
            });
        }else {
            $("#tbody5add input[type='checkbox']").each(function(){
                $(this).prop("checked",false);
            });
        }
    });
    $(document).on("click","#tbody5add input[type='checkbox']",function(){
        var checklength=$("#tbody5add input[type='checkbox']:checked");
        var rowlength=$("#tbody5add tr").length;
        if(checklength.length==rowlength){
            $("#check5").prop("checked",true);
        }else {
            $("#check5").prop("checked",false);
        }
    });

    $(document).on("click","#check6",function(){
        if($(this).prop("checked")==true){
            $("#tbody7 input[type='checkbox']").each(function(){
                $(this).prop("checked",true);
            });
        }else {
            $("#tbody7 input[type='checkbox']").each(function(){
                $(this).prop("checked",false);
            });
        }
    });
    $(document).on("click","#tbody7 input[type='checkbox']",function(){
        var checklength=$("#tbody7 input[type='checkbox']:checked");
        var rowlength=$("#tbody7 tr").length;
        if(checklength.length==rowlength){
            $("#check6").prop("checked",true);
        }else {
            $("#check6").prop("checked",false);
        }
    });

    $(document).on("click","#check7",function(){
        if($(this).prop("checked")==true){
            $("#tbody8 input[type='checkbox']").each(function(){
                $(this).prop("checked",true);
            });
        }else {
            $("#tbody8 input[type='checkbox']").each(function(){
                $(this).prop("checked",false);
            });
        }
    });
    $(document).on("click","#tbody8 input[type='checkbox']",function(){
        var checklength=$("#tbody8 input[type='checkbox']:checked");
        var rowlength=$("#tbody8 tr").length;
        if(checklength.length==rowlength){
            $("#check7").prop("checked",true);
        }else {
            $("#check7").prop("checked",false);
        }
    });

});