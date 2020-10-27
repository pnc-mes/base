$(function () {
    //默认的给个表格id
    var addid=1;

    //处理表格的新增还是修改
    var resultFlag=true;

    //存放修改的表格id
    var modifyTable=null;
    //点击表格的时候，获取表格属性的addid
    var clickaddid=null;

    var treeID = null;
    var tr_BMRd = null;
    var flag = true;
    var $historyChange = $("#historyChange");
    var _MaCode;
    var _BMRd;
    var _TDL;
    var bomid=null; //用来标识变更后还可以点击以及历史变更记录
    var $reason = $("#reason");
    var $BomVerRd = $("#BomVerRd");
    var $BomRd = $("#BomRd");
    var $BomCode = $("#BomCode");
    var $BomName = $("#BomName");
    var $Status = $("#Status");
    var $Version = $("#Version");//工程变更单号的下拉
    var $addVersion = $("#addVersion");//工程变更单号的文本框
    var $ExecType = $("#ExecType");
    var $consume = $("#consume");
    var $specName = $("#specName");
    var $maName = $("#maName");
    var $consumeNum = $("#consumeNum");
    var $unit = $(".unit");
    var $aRate=$("#ARate");
    var $check = $("#check");
    var $replaceMaInfo = $("#replaceMaInfo");

    var $creatPeople =  $("#creatPeople");
    var $creatTime = $("#creatTime");
    var $modifyPeople = $("#modifyPeople");
    var $modifyTime = $("#modifyTime");
    var $beizhu = $("#beizhu");

    var $tab_4_li = $("#tab_4_li");
    var $tab_5_li = $("#tab_5_li");
    var $tab_4 = $("#tab_4");
    var $tab_5 = $("#tab_5");
    var $VersionNo = $("#VersionNo");
    var $BomNo = $("#BomNo");

    var isSwitch = true;

    $historyChange.on("click",function () {
        if(treeID != null){
            parent.addTabs({id:'337',title: 'BOM变更历史查询',close: true,url: getBasePath() + "/BOM/BOMChangePG"});
            storage.setItem("BOMRd",treeID);
        }
        if(bomid != null){
            parent.addTabs({id:'337',title: 'BOM变更历史查询',close: true,url: getBasePath() + "/BOM/BOMChangePG"});
            storage.setItem("BOMRd",bomid);
        }
    });

    //替代料表格
    var repTabColumns = [
        {"Caption":"MaVerRd", "Name":"MaVerRd", "CType":"text","Hidden":true},
        {"Caption":"物料代码", "Name":"MaCode", "CType":"text"},
        {"Caption":"物料名称", "Name":"MaName", "CType":"text"},
        {"Caption":"物料描述", "Name":"MaDes", "CType":"text"},
        {"Caption":"消耗数量", "Name":"Num", "CType":"text"},
        {"Caption":"损耗率", "Name":"ARate", "CType":"text"},
    ];
    var repTabConfig = {
        data:[],
        colArr:repTabColumns,
        tableId:"list5",
        multiselect:true,
        width:0.84,
        height:0.35
    };
    fullTable(repTabConfig);

    //定义变量存储点击的BOM节点下面的所有物料信息：因为里面包含很多物料的替代料信息
    var BOMInfos = [];

    //用料清单表格
    var colNamesArr = [
        {"Caption":"替代料", "Name":"Look","CType":"text",Width:50},
        {"Caption":"addid", "Name":"addid", "CType":"text", "Hidden":true},
        {"Caption":"BMRd", "Name":"BMRd", "CType":"text", "Hidden":true},
        {"Caption":"MaVerRd", "Name":"MaVerRd", "CType":"text","Hidden":true},
        {"Caption":"物料代码", "Name":"MaCode", "CType":"text",Width:120},
        {"Caption":"物料名称", "Name":"MaName", "CType":"text",Width:100},
        {"Caption":"物料描述", "Name":"MaDes", "CType":"text",Width:260},
        {"Caption":"工序版本ID", "Name":"SpecVerRd", "CType":"text","Hidden":true},
        {"Caption":"工序", "Name":"SpecName", "CType":"text",Width:100},
        {"Caption":"数量", "Name":"Num", "CType":"text",Width:50},
        {"Caption":"损耗率", "Name":"ARate", "CType":"text",Width:50},
        {"Caption":"单位", "Name":"UnitName","CType":"text",Width:50},
        {"Caption":"消耗方式", "Name":"ConSMode","CType":"text","Hidden":false,Width:50},
        {"Caption":"是否检查", "Name":"Checked","CType":"text","Hidden":true},
        {"Caption":"替代料_", "Name":"ReMaInfo","CType":"text","Hidden":true},
    ];
    var n = 0;
    var tabConfig = {
        data:[],
        colArr:colNamesArr,
        tableId:"list4",
        multiselect:true,
        width:0.84,
        height:0.35,
        event:{
            onrowselect:function (rowdata) {
                if(rowdata.length != 0){
                    tr_BMRd = rowdata[rowdata.length - 1].BMRd;
                    if(rowdata[rowdata.length - 1].addid!=null){
                        clickaddid=rowdata[rowdata.length - 1].addid
                    }

                    //通过循环来遍历点击的物料对应的替代料信息
                    for(var i in BOMInfos){
                        if(tr_BMRd == BOMInfos[i].BMRd){
                            //填充替代料表格
                            repTabConfig.data = BOMInfos[i].ReMaInfo instanceof Array ? BOMInfos[i].ReMaInfo : [];
                            fullTable(repTabConfig);
                        }
                    }
                }
            }
        }
    };
    fullTable(tabConfig);

    var BomVerRds = [];
    var isDef = null;
    /*------------------点击事件的处理----------------*/


    var onClicks = function (nodeinfo, handle) {
        $ExecType.val("");
        $("#reasonDiv").css("display","none");
        //设置物料清单的按钮为不可点击
        $("#maInfoButton").css("display","none");

        //隐藏物料消耗和设置替代料的tab
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
        $tab_5_li.css("display","none");
        $tab_5.removeClass("active");

        $("#printForm").show();
        treeID = nodeinfo.nodeID;
        bomid=nodeinfo.nodeID;
        getBVInfo("00",{"BomRd":treeID});
        //查询版本列表
        request({url:"/BOM/GetBOMTreeInfo", data: {"ExecType": "00", "BusData": "{\"BomRd\":" + treeID + "}"}},function (Body) {
            BomVerRds = Body.Data;
            $("#BomVerRd").attr("editid", Body.Data.BomVerRd);
            $Version.html("");
            for(var i in Body.Data){
                if("00" == isDef){
                    $Version.append("<option selected>"+ Body.Data[i].Version +"</option>");
                }else{
                    $Version.append("<option>"+ Body.Data[i].Version +"</option>");
                }
            }
        });
        trDBLClick();
    };
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

    $(".searchTree").on("click",function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        var a = [];
        if(isSwitch){
            a.push({
                "FieldName": "isDef",
                "FieldOpt": "=",
                "FieldVal": "00"
            });
        }
        currentPage = treeSearchs("/BOM/GetAllBOMInfo","BomRd","BomName","BomName",condition,currentPage,config, a);
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
            var a = [];
            if(isSwitch){
                a.push({
                    "FieldName": "isDef",
                    "FieldOpt": "=",
                    "FieldVal": "00"
                });
            }
            currentPage = treeSearchs("/BOM/GetAllBOMInfo","BomRd","BomName","BomName",condition,currentPage,config, a);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })



    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/BOM/GetAllBOMInfo","BomRd","BomName","BomName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/BOM/GetAllBOMInfo","BomRd","BomName","BomName",condition,currentPage,config);
    });

    $("#switch").on("click",function () {
        var s = $("#switch").html();
        if(s == "显示全部"){
           isSwitch = false;
           $("#switch").html("显示可用");
        }else{
           isSwitch = true;
           $("#switch").html("显示全部");
        }
        $(".cAdd").click();
        $("#printForm").hide();
        loadPage();
    });

    //树刷新
    var loadPage = function () {
        $("#reasonDiv").css("display","none");
        //清空所有设置
        treeID = null;
        $ExecType.val("");
        var dataArr = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var a = [];
        if(isSwitch){
            a.push({
                "FieldName": "isDef",
                "FieldOpt": "=",
                "FieldVal": "00"
            });
        }
        a.push({
            "FieldName": "CreateTime DESC",
            "FieldOpt": "Order BY"
        });
        //获取列表信息
        request({url:"/BOM/GetAllBOMInfo",
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": a})
            }},function (Body) {
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
                var data = {
                    "id": treeData[i].BomRd,
                    "name": treeData[i].BomName
                };
                dataArr.push(data);
            }
            config.data.source = dataArr;
            $.JstreeEx.init(config);
        });
    };
    loadPage();

    //设置用料清单的新增按钮事件
    $(".add1").on("click",function () {
        addid++;
        resultFlag=false;
        flag = false;
        //把物料消耗的tab显示出来,并设置li的class为active,而其他的li移除active
        $tab_4_li.css("display","block").addClass("active").siblings().removeClass("active");
        $tab_4.addClass("active").siblings().removeClass("active");
        //清空物料消耗页面数据
        $maName.clearseldata();
        $specName.clearseldata();
        $consumeNum.val("");
        $unit.html("");
        $aRate.html("");
        $check.prop("checked",false);
        $("#ARate").val("");

        //所有内容都可编辑
        $(".sure").css("display","-webkit-box");
        $("#tab_4").find("input").each(function () {
            $(this).prop("readonly",false);
            if($(this).attr("type") == "checkbox"){
                $(this).prop("disabled",false);
            }
        });
        $("#tab_4").find("select").prop("disabled",false);
        $("#tab_4").find("img").css("display","inline-block");
    });

    var hasData=[];
    //设置用料清单的设置替代料按钮事件
    $(".substitution").on("click",function () {
        var tableData = getSeRowData("list4");
        if(tableData.length<=0){
            toastr.warning("请选择你要设置的替代料");
            return false;
        }
        if(tableData.length>=2){
            toastr.warning("请选择一个设置替代料");
            return false;
        }
        $("#ARate").val("");
        //tr_BMRd这个信息表示的是只新增的时候而不是在BOM变更的
        if(tr_BMRd==null&&tr_BMRd==""){
            if(clickaddid!=null){
                $(".del2").parent().css("display","block");
                //确认按钮显示
                $(".sure1").parent().css("display","-webkit-box");
                //搜索框隐藏
                $("#tidailiao").css("display","block");
                //把替代料的tab显示出来
                $tab_5_li.css("display","block").addClass("active").siblings().removeClass("active");
                $tab_5.addClass("active").siblings().removeClass("active");



                alert(clickaddid)
            }

        }else {
            for(var i  in tableData ){
                if(tableData[i].BMRd==tr_BMRd){
                    //删除按钮显示
                    $(".del2").parent().css("display","block");
                    //确认按钮显示
                    $(".sure1").parent().css("display","-webkit-box");
                    //搜索框隐藏
                    $("#tidailiao").css("display","block");
                    //把替代料的tab显示出来
                    $tab_5_li.css("display","block").addClass("active").siblings().removeClass("active");
                    $tab_5.addClass("active").siblings().removeClass("active");

                    var objBusData = JSON.stringify({"MaVerRd":tableData[i].MaVerRd});
                    var objData = {
                        "ExecType": "01",
                        "BusData": objBusData,
                    };
                    request({url:"/Material/GetMaInfo", data: objData},function(Body){
                        hasData=Body.Data.ReMaInfo;
                        if(hasData.length<=0){
                            $("#replaceMaInfo").show();
                            $("#replaceMaInfoselect").hide();
                        }else {
                            $("#replaceMaInfo").hide();
                            $("#replaceMaInfoselect").show();
                            var aa="";
                            for(var j  in  hasData){
                                aa += '<option value=' + hasData[j].MaVerRd + ' a='+hasData[j].MaCode+'  b='+hasData[j].MaName+' c='+hasData[j].MaDes+'     >' + hasData[j].MaName+ '</option>';
                            }

                            $("#replaceMaInfoselectdatas").empty().html(aa);
                            var objBusData = JSON.stringify({"MaVerRd":$("#replaceMaInfoselectdatas").find("option:selected").val()});
                            var objData = {
                                "ExecType": "01",
                                "BusData": objBusData,
                            };
                            request({url:"/Material/GetMaInfo", data: objData},function(Body){
                                $("#dw").text(Body.Data.UnitInfo.UnitName);
                            });
                        }
                    });
                }
            }
        }
        var repTabConfig = {
            data:[],
            colArr:repTabColumns,
            tableId:"list5",
            multiselect:true,
            width:0.84,
            height:0.35
        };
        fullTable(repTabConfig);


    });


    //点击事件
   $('#replaceMaInfoselectdatas').change(function(){
       var objBusData = JSON.stringify({"MaVerRd":$(this).val()});
       var objData = {
           "ExecType": "01",
           "BusData": objBusData,
       };
       request({url:"/Material/GetMaInfo", data: objData},function(Body){
           $("#dw").text(Body.Data.UnitInfo.UnitName);
       });
    });

    //设置用料清单的删除按钮事件
    $(".del1").on("click",function () {
        derow("list4");
    });

    //设置物料消耗的确认事件
    $(".sure").on("click",function () {
        if(_BMRd==undefined){
            _TDL=undefined;
        }

        if(resultFlag){
            //修改

            if( $(this).getseldata().MaName== "" ||  $(this).getseldata().MaName == null ||  $(this).getseldata().MaName== undefined){

                toastr.warning("物料名称、工序不能为空,消耗数量为正整数,单位不能为空");
                return false;
            }
            if( $consumeNum.val()== "" || $consumeNum.val() == null ||  $consumeNum.val()== undefined){

                toastr.warning("物料名称、工序不能为空,消耗数量为正整数,单位不能为空");
                return false;
            }
            if( $aRate.val()== "" || $aRate.val() == null ||  $aRate.val()== undefined){

                toastr.warning("消耗率不能为空");
                return false;
            }
            if($(this).getseldata().SpecName== "" ||  $(this).getseldata().SpecName== null ||  $(this).getseldata().SpecName== undefined){

                toastr.warning("物料名称、工序不能为空,消耗数量为正整数,单位不能为空");
                return false;
            }
            if($unit.text()== "" ||  $unit.text()== null ||  $unit.text()== undefined){

                toastr.warning("物料名称、工序不能为空,消耗数量为正整数,单位不能为空");
                return false;
            }


            var tab_list4 = getTableData("list4");
          var j=1;
          //alert(modifyTable);
          for(var i in tab_list4){
            /*  if (tab_list4[i].MaVerRd == $(this).getseldata().MaVerRd && tab_list4[i].SpecVerRd == $(this).getseldata().SpecVerRd) {
                 alert("5555")
                  toastr.warning($(this).getseldata().MaName + "，已经存在");
                  return
              }*/
              if(tab_list4[i].addid==modifyTable && tab_list4[i].BMRd==_BMRd){
                  tab_list4[i].addid=j,
                      tab_list4[i].BMRd=_BMRd,
                      tab_list4[i].MaVerRd= $(this).getseldata().MaVerRd,
                      tab_list4[i].MaCode= $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                      tab_list4[i].MaName= $(this).getseldata().MaName,
                      tab_list4[i].MaDes=$(this).getseldata().MaDes==""||$(this).getseldata().MaDes==null||$(this).getseldata().MaDes==undefined?"":$(this).getseldata().MaDes,
                      tab_list4[i].SpecVerRd= $(this).getseldata().SpecVerRd,
                      tab_list4[i].SpecName= $(this).getseldata().SpecName,
                      tab_list4[i].Num= $consumeNum.val(),
                      tab_list4[i].ARate= $aRate.val(),
                      tab_list4[i].UnitName=$unit.text(),
                      tab_list4[i].ConSMode=  $consume.val()=="00"?"仅显示":$consume.val()=="01"?"批次":"序号",
                      tab_list4[i].Checked= $check.prop("checked") == true ? "00" : "01",
                      tab_list4[i].Look=_TDL == undefined?"":_TDL
              }




          }
            var config1 = {
                tableId: 'list4',
                data: tab_list4,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height: 0.415
            };
            fullTable(config1);


        }else {

            var tab_TR = {
                addid:addid++,
                BMRd: "",
                MaVerRd: $(this).getseldata().MaVerRd,
                MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                MaName: $(this).getseldata().MaName,
                MaDes: $(this).getseldata().MaDes==""||$(this).getseldata().MaDes==null||$(this).getseldata().MaDes==undefined?"":$(this).getseldata().MaDes,
                SpecVerRd: $(this).getseldata().SpecVerRd,
                SpecName: $(this).getseldata().SpecName,
                Num: $consumeNum.val(),
                ARate: $aRate.val(),
                UnitName: $unit.text(),
                ConSMode: $consume.val()=="00"?"仅显示":$consume.val()=="01"?"批次":"序号",
                Checked: $check.prop("checked") == true ? "00" : "01",
                ReMaInfo: [],
                Look:_TDL == undefined?"":_TDL
            };
            for( var i in tab_TR){
                if(i == "addid")
                    continue;
                else if(i == "BMRd")
                    continue;
                else if(i == "ReMaInfo")
                    continue;
                else if(i == "MaDes")
                    continue;
                else if(i == "Look")
                    continue;
                if(tab_TR[i] == "" || tab_TR[i] == null || tab_TR[i] == undefined){
                    toastr.warning("物料名称、工序不能为空,消耗数量为正整数,单位不能为空");
                    return false;
                }
            }
            var tab_list4 = getTableData("list4");

            for (var i in tab_list4) {
                if (tab_list4[i].MaVerRd == tab_TR.MaVerRd && tab_list4[i].SpecVerRd == tab_TR.SpecVerRd) {
                    toastr.warning(tab_TR.MaName + "，已经存在");
                    return;
                }
            }

            addSrow("list4", tab_TR);
        }


       /* if(!flag) {
            if(_BMRd==undefined){
                _TDL=undefined;
            }

            //获取里面所有的数据，添加到用料清单的表格中
            var tab_TR = {
                BMRd: "",
                MaVerRd: $(this).getseldata().MaVerRd,
                MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                MaName: $(this).getseldata().MaName,
                SpecVerRd: $(this).getseldata().SpecVerRd,
                SpecName: $(this).getseldata().SpecName,
                Num: $consumeNum.val(),
                UnitName: $unit.text(),
                ConSMode: $consume.val()=="00"?"仅显示":$consume.val()=="01"?"批次":"序号",
                Checked: $check.prop("checked") == true ? "00" : "01",
                ReMaInfo: [],
                Look:_TDL == undefined?"":_TDL
            };
            //
            console.log(tab_TR)
            for(var i in tab_TR){
                if(i == "BMRd")
                    continue;
                else if(i == "ReMaInfo")
                    continue;
                else if(i == "Look")
                    continue;

                else if(tab_TR[i] == "" || tab_TR[i] == null || tab_TR[i] == undefined){
                    toastr.warning("物料名称、工序不能为空,消耗数量为正整数");
                    return false;
                }
            }

            var tab_list4 = getTableData("list4");
            for (var i in tab_list4) {
                if (tab_list4[i].MaVerRd == tab_TR.MaVerRd && tab_list4[i].SpecVerRd == tab_TR.SpecVerRd) {
                    toastr.warning(tab_TR.MaName + "，已经被添加");
                    return;
                }
            }
            addSrow("list4", tab_TR);
        }else{
            //获取里面所有的数据，添加到用料清单的表格中
            var tab_TR = {
                BMRd: _BMRd,
                MaVerRd: $(this).getseldata().MaVerRd,
                MaCode: $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                MaName: $(this).getseldata().MaName,
                SpecVerRd: $(this).getseldata().SpecVerRd,
                SpecName: $(this).getseldata().SpecName,
                Num: $consumeNum.val(),
                UnitName: $unit.text(),
                ConSMode: $consume.val(),
                Checked: $check.prop("checked") == true ? "00" : "01",
                Look:_TDL == undefined?"":_TDL
            };
            var tab_list4 = getTableData("list4");

            for(var i in tab_TR){
                if(i == "BMRd")
                    continue;
                else if(i == "ReMaInfo")
                    continue;
                else if(i == "Look")
                    continue;
                else if(tab_TR[i] == "" || tab_TR[i] == null || tab_TR[i] == undefined){
                    toastr.warning("物料名称、工序不能为空,消耗数量为正整数");
                    return false;
                }
            }

            for (var i in tab_list4) {
                if (tab_list4[i].MaVerRd == tab_TR.MaVerRd && tab_list4[i].SpecVerRd == tab_TR.SpecVerRd && tab_list4[i].BMRd != _BMRd ) {
                    toastr.warning(tab_TR.MaName + "，已经被添加");
                    return;
                }
             /!*   if(_BMRd==tab_list4[i].BMRd ){
                    tab_list4[i].BMRd=_BMRd,
                        tab_list4[i].MaVerRd= $(this).getseldata().MaVerRd,
                        tab_list4[i].MaCode= $(this).getseldata().MaCode == undefined?_MaCode:$(this).getseldata().MaCode,
                        tab_list4[i].MaName= $(this).getseldata().MaName,
                        tab_list4[i].SpecVerRd= $(this).getseldata().SpecVerRd,
                        tab_list4[i].SpecName= $(this).getseldata().SpecName,
                        tab_list4[i].Num= $consumeNum.val(),
                        tab_list4[i].UnitName=$unit.text(),
                        tab_list4[i].ConSMode= $consume.val(),
                        tab_list4[i].Checked= $check.prop("checked") == true ? "00" : "01",
                        tab_list4[i].Look=_TDL == undefined?"":_TDL
                }*!/
            }

            $("#list4").find("tbody tr").each(function () {
                alert($(this).find(".BMRd").text() +":"+_BMRd)
                if($(this).find(".BMRd").text() == _BMRd){
                    $(this).find(".MaVerRd").html(tab_TR.MaVerRd);
                    $(this).find(".MaCode").html(tab_TR.MaCode);
                    $(this).find(".MaName").html(tab_TR.MaName);
                    $(this).find(".SpecVerRd").html(tab_TR.SpecVerRd);
                    $(this).find(".SpecName").html(tab_TR.SpecName);
                    $(this).find(".Num").html(tab_TR.Num);
                    $(this).find(".UnitName").html(tab_TR.UnitName);
                    $(this).find(".ConSMode").html(tab_TR.ConSMode);
                    $(this).find(".Checked").html(tab_TR.Checked);
                    return false;
                }
            });
        }*/
        //隐藏当前tab
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");

        $("#list4").find("tbody").on("dblclick", "tr", function () {
            flag = true;
            showXH(this);
        });

    });


    //设置替代料的确认事件
    $(".sure1").on("click",function () {
        var repTabData = getTableData("list5");
        //if(repTabData.length > 0){
            //添加到用料清单表格中对应的物料上面
            if(tr_BMRd!=null&&tr_BMRd!=""){
                for(var i in BOMInfos){
                    if(BOMInfos[i].BMRd == tr_BMRd){
                        $("#list4").find(".BMRd").each(function () {
                            if($(this).text() == tr_BMRd){
                                $(this).parent().find(".ReMaInfo").html(JSON.stringify(repTabData));
                                BOMInfos[i].ReMaInfo = JSON.stringify(repTabData);
                                if(repTabData.length){
                                    _TDL = "<a href='javascript:void(0);'>查看</a>";
                                    $(this).parent().find(".Look").html(_TDL);
                                }
                                return false;
                            }
                        })
                    }
                }
            }else{
                var list5tableData = getTableData("list5");

                if(clickaddid!=null||clickaddid!=""){
                    var list4tableData = getTableData("list4");
                    if(list4tableData.length>0){
                        $("#list4").find(".addid").each(function () {
                            if($(this).text() == clickaddid){
                                $(this).parent().find(".ReMaInfo").html(JSON.stringify(list5tableData));

                                console.log(list5tableData)
                                for(var j in  list4tableData){
                                    if(list4tableData[j].addid==clickaddid){
                                        list4tableData[j].ReMaInfo=JSON.stringify(list5tableData);
                                        _TDL = "<a href='javascript:void(0);'>查看</a>";
                                        $(this).parent().find(".Look").html(_TDL);
                                        return false;
                                    }
                                }




                            }
                        })
                      /*  for(var i in list4tableData){
                            if(list4tableData[i].addid==clickaddid){
                                alert(2)
                            }
                        }*/
                    }

                   /* for(var i in BOMInfos){
                        if(BOMInfos[i].BMRd == tr_BMRd){
                            $("#list4").find(".BMRd").each(function () {
                                if($(this).text() == tr_BMRd){
                                    $(this).parent().find(".ReMaInfo").html(JSON.stringify(repTabData));
                                    BOMInfos[i].ReMaInfo = JSON.stringify(repTabData);
                                    if(repTabData.length){
                                        _TDL = "<a href='javascript:void(0);'>查看</a>";
                                        $(this).parent().find(".Look").html(_TDL);
                                    }
                                    return false;
                                }
                            })
                        }
                    }*/
                }
            }

            //隐藏当前tab
            $tab_5_li.css("display","none").prev().prev().addClass("active");
            $tab_5.removeClass("active").prev().prev().addClass("active");
    });
    //设置替代料的删除事件
    $(".del2").on("click",function () {
        derow("list5");
    });

    //新增
    $(".cAdd").click(function () {
        $("#reasonDiv").css("display","none");

        $("#maInfoButton").css("display","inline-block");
        //隐藏物料消耗和设置替代料的tab
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
        $tab_5_li.css("display","none");
        $tab_5.removeClass("active");

        $addVersion.val("");
        $BomVerRd.val("");
        $BomCode.val("");
        $BomName.val("");
        $aRate.val("");
        $creatPeople.val("");
        $creatTime.val("");
        $modifyPeople.val("");
        $modifyTime.val("");
        $beizhu.val("");
        $reason.val("");
        $ExecType.val("00");
        $VersionNo.val("");
        $BomNo.val("");

        //设置成可编辑模式
        $BomCode.attr("readonly",false);
        $BomName.attr("readonly",false);
        $Status.prop("disabled",false);
        $VersionNo.attr("readonly",false);
        $BomNo.attr("readonly",false);

        //把input框显示，下拉框隐藏
        $Version.css("display","none");
        $addVersion.css("display","inline-block");

        //把用料清单表格清空
        tabConfig.data = [];
        fullTable(tabConfig);

        //把替代料表格清空
        repTabConfig.data = [];
        fullTable(repTabConfig);

        //清空物料消耗页面数据
        $maName.clearseldata();
        $specName.clearseldata();
        $consumeNum.val("");
        $unit.html("");
        $check.prop("checked",false);

        //清空工程单号和替代料搜索的信息
        $replaceMaInfo.clearseldata();

        $("#printForm").show();
        $("#addVersion").val("1");
    });

    //删除
    $("#remove").click(function () {
        var bomRd = $(this).attr("rm");
        if (bomRd == null || bomRd == "") {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
        else {
            var bomRd = {
                "BomRd": bomRd
            }
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({url:"/BOM/SaveBomInfo",data: {"ExecType": "01", "BusData": JSON.stringify(bomRd)}},function (Body) {
                    layer.closeAll("dialog");
                    toastr.success(Body.MsgDes);
                    $("#printForm").hide();
                    condition = "";
                    currentPage = 0;
                    loadPage();
                    $("#BomRd").val("");
                    $("#remove").attr("rm", "")
                });
            })
        }
    });

    //BOM变更
    $("#bomChange").on("click",function () {
        if(treeID != null){
            //删除按钮显示
            $(".del2").css("display","block");
            //确认按钮显示
            $(".sure1").css("display","-webkit-box");
            //搜索框隐藏
            $("#tidailiao").css("display","block");
            $reason.val("");
            $("#reasonDiv").css("display","block");
            $("#maInfoButton").css("display","inline-block");

            $ExecType.val("03");

            //清空物料消耗页面数据
            $maName.clearseldata();
            $specName.clearseldata();
            $consumeNum.val("");
            $unit.val("");
            $check.attr("checked",false);

            //清空工程单号和替代料搜索的信息
            $replaceMaInfo.clearseldata();

            //设置成可编辑模式
            $BomCode.attr("readonly",false);
            $BomName.attr("readonly",false);
            $Status.prop("disabled",false);
            $VersionNo.attr("readonly",false);
            $BomNo.attr("readonly",false);
        }
        if(bomid != null){
            //删除按钮显示
            $(".del2").css("display","block");
            //确认按钮显示
            $(".sure1").css("display","-webkit-box");
            //搜索框隐藏
            $("#tidailiao").css("display","block");
            $reason.val("");
            $("#reasonDiv").css("display","block");
            $("#maInfoButton").css("display","inline-block");

            $ExecType.val("03");

            //清空物料消耗页面数据
            $maName.clearseldata();
            $specName.clearseldata();
            $consumeNum.val("");
            $unit.val("");
            $check.prop("checked",false);

            //设置成可编辑模式
            /*$BomCode.attr("readonly",false);
            $BomName.attr("readonly",false);
            $Status.prop("disabled",false);*/
        }
        //清空工程单号和替代料搜索的信息
        $replaceMaInfo.clearseldata();
        //把input框显示，下拉框隐藏
        $Version.css("display","none");
        $addVersion.css("display","inline-block");

        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
    });

    //保存
    $(".cSave").click(function () {
        if($BomName.val() == "" || $Version.val() == ""){
            toastr.warning("BOM名称、工程变更单号都不能为空");
            return;
        }
        if($ExecType.val() == "03" && $addVersion.val().trim() == ""){
            toastr.warning("工程变更单号不能为空");
            return;
        }
        //获取表格数据
        var tableData = getTableData("list4");
        if(!tableData.length){
            toastr.warning("用料清单不能为空");
            return;
        }
        var execType = $ExecType.val();
        if(execType==""){
            toastr.warning("若要修改该数据，请点击操作中BOM变更");
            return;
        }
        console.log(tableData);
        for(var i = 0;i<tableData.length;i++){


            if(execType == "00")
                delete tableData[i].addid;
                delete tableData[i].BMRd;
                delete tableData[i].MaDes;
                delete tableData[i].SpecName;
                delete tableData[i].Look;
                tableData[i].ReMaInfo = tableData[i].ReMaInfo == "" ? []:JSON.parse(tableData[i].ReMaInfo);

            if(execType == "03"){
                delete tableData[i].addid;
            }
            if(tableData[i].ReMaInfo.length>0){
                for(var ii in tableData[i].ReMaInfo){
                    delete tableData[i].ReMaInfo[ii].MaDes;
                }
            }

        }

        if ("00" == execType) { //新增
            var newData = {
                "BomCode": $BomCode.val(),
                "BomName": $BomName.val(),
                "Version": $addVersion.val(),
                "BMInfo": tableData,
                "Remark": $reason.val(),
                "VersionNo": $VersionNo.val(),
                "BomNo": $BomNo.val()
            };
            if ( $BomName.val() != null && $BomName.val().trim() != ""
                && $addVersion.val() != null && $addVersion.val().trim() != "") {
                request({url:"/BOM/SaveBomInfo", data: {"ExecType": "00", "BusData": JSON.stringify(newData)}},function (Body) {
                    toastr.success("BOM信息新增成功,BOM代码{"+Body.Data.BomCode+"}");
                    condition = "";
                    currentPage = 0;
                    loadPage();
                    $("#BomRd").attr("editid", Body.Data.BomRd);
                    treeID=$("#BomRd").attr("editid");
                    getBVInfo("00",{"BomRd":treeID});
                    $("#reasonDiv").css("display","none");
                    //设置物料清单的按钮为不可点击
                    $("#maInfoButton").css("display","none");

                    //隐藏物料消耗和设置替代料的tab
                    $tab_4_li.css("display","none").prev().addClass("active");
                    $tab_4.removeClass("active").prev().addClass("active");
                    $tab_5_li.css("display","none");
                    $tab_5.removeClass("active");

                    //查询版本列表
                    request({url:"/BOM/GetBOMTreeInfo", data: {"ExecType": "00", "BusData": "{\"BomRd\":" + treeID + "}"}},function (Body) {
                        BomVerRds = Body.Data;
                        $("#BomVerRd").attr("editid", Body.Data.BomVerRd);
                        $Version.html("");
                        for(var i in Body.Data){
                            if("00" == isDef){
                                $Version.append("<option selected>"+ Body.Data[i].Version +"</option>");
                            }else{
                                $Version.append("<option>"+ Body.Data[i].Version +"</option>");
                            }
                        }
                    });
                    trDBLClick();
                });
            }
        } else if ("03" == execType) { //新增版本
            //if(confirm("工程变更单号："+ $addVersion.val() + "；工程变更原因："+ $beizhu.val())){
                var newData = {
                    "BomVerRd": $BomVerRd.val(),
                    "Version": $addVersion.val(),
                    "BMInfo": tableData,
                    "Remark": $reason.val(),
                    "VersionNo": $VersionNo.val(),
                    "BomNo": $BomNo.val(),
                    "IsDef": $Status.val()
                };
                if ($BomCode.val() != null && $BomCode.val().trim() != ""
                    && $BomName.val() != null && $BomName.val().trim() != ""
                    && $addVersion.val() != null && $addVersion.val().trim() != "") {
                    request({
                        url: "/BOM/SaveBomInfo",
                        data: {"ExecType": "03", "BusData": JSON.stringify(newData)}
                    }, function (Body) {
                        toastr.success(Body.MsgDes);
                        if($Status.val() == "01"){
                            $(".cAdd").click();
                            $("#printForm").hide();
                            loadPage();
                        }else {
                            condition = "";
                            currentPage = 0;
                            $("#reasonDiv").css("display", "none");
                            getBVInfo("00", {"BomRd": treeID});

                            $("#reasonDiv").css("display", "none");
                            //设置物料清单的按钮为不可点击
                            $("#maInfoButton").css("display", "none");

                            //隐藏物料消耗和设置替代料的tab
                            $tab_4_li.css("display", "none").prev().addClass("active");
                            $tab_4.removeClass("active").prev().addClass("active");
                            $tab_5_li.css("display", "none");
                            $tab_5.removeClass("active");

                            //查询版本列表
                            request({
                                url: "/BOM/GetBOMTreeInfo",
                                data: {"ExecType": "00", "BusData": "{\"BomRd\":" + treeID + "}"}
                            }, function (Body) {
                                BomVerRds = Body.Data;
                                $("#BomVerRd").attr("editid", Body.Data.BomVerRd);
                                $Version.html("");
                                for (var i in Body.Data) {
                                    if ("00" == isDef) {
                                        $Version.append("<option selected>" + Body.Data[i].Version + "</option>");
                                    } else {
                                        $Version.append("<option>" + Body.Data[i].Version + "</option>");
                                    }
                                }
                            });
                            loadPage();
                            trDBLClick();
                        }
                    });
                }
            //}
        }
    });

    /*---------------------下面-----------------------*/
    //物料的搜索
    var params_ma = {
        "displaymode": "1",
        "title": "物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "物料id",
                    "name": "MaVerRd"
                }, {
                    "caption": "物料代码",
                    "name": "MaCode"
                }, {
                    "caption": "物料名称",
                    "name": "MaName"
                }, {
                    "caption": "物料描述",
                    "name": "MaDes"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event": {
            "onformatval":function(data){
                console.log(data);
                return data.MaCode + "-" + data.MaName;
            },
            "onseardata": function (o) {
                var _InitData = {
                    "FiledList":[
                        {
                            "FieldName":"CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "order by",
                        }
                    ]
                };
                var datas = [];
                Materialinfo(_InitData,"",datas);
                var obj = {
                    data: datas,
                    showrow: 500
                };
                return obj;
            },
            "onclick":function (res) {
                getUnit(res.MaVerRd);
            }
        }
    };
    $maName.zc_select(params_ma);

    //替代料
    var list5MaterialMaverRd="";
    var list5MaterialName="";
    var list5MaterialCode="";
    var list5MaterialDes="";
    var list5MaterialNum="";
    var list5MaterialRate="";
    var repMa = {
        "displaymode": "1",
        "title": "物料",
        "binddata": {
            "keyfield": "MaVerRd",
            "fields": [
                {
                    "caption": "物料id",
                    "name": "MaVerRd"
                }, {
                    "caption": "物料代码",
                    "name": "MaCode"
                },{
                    "caption": "物料名称",
                    "name": "MaName"
                }, {
                    "caption": "物料描述",
                    "name": "MaDes"
                }
            ]
        },
        "showresult": {
            "ishead": true
        },
        "event":{
            "onformatval":function(data){
                console.log(data);
                return data.MaCode + "-" + data.MaName;
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "CONCAT(IFNULL(MaterialName,''''),IFNULL(MaterialCode,''''),IFNULL(MaterialDes,''''))",
                            "FieldOpt": "like",
                            "FieldVal": "%" + o.condition + "%"
                        }, {
                            "FieldName": "ReMaterial",
                            "FieldOpt": "=",
                            "FieldVal": "00"
                        },
                        {
                            "FieldName": "MaterialName",
                            "FieldOpt": "order by",
                        }
                    ]
                };
                var datas = [];
                Materialinfo(InitData,"",datas);
                var obj = {
                    data: datas,
                    showrow: 500
                };
                return obj;
            },
            "onclick": function (res) {
              //  alert(res.MaVerRd+"代码"+res.MaCode+"名称"+res.MaName+"描述"+res.MaDes);

                var objBusData = JSON.stringify({"MaVerRd": res.MaVerRd});
                var objData = {
                    "ExecType": "01",
                    "BusData": objBusData,
                };
                request({url:"/Material/GetMaInfo", data: objData},function(Body){
                            $("#dw").text(Body.Data.UnitInfo.UnitName);
                });

                list5MaterialName=res.MaName;
                list5MaterialCode=res.MaCode;
                list5MaterialDes=res.MaDes;

                list5MaterialMaverRd= res.MaVerRd;

                //填充替代料表格
                /*var rep_Tab_Tr = {
                    MaVerRd:$replaceMaInfo.getseldata().MaVerRd,
                    MaCode:$replaceMaInfo.getseldata().MaCode,
                    MaName:$replaceMaInfo.getseldata().MaName,
                    MaDes:$replaceMaInfo.getseldata().MaDes
                }
                var tab_list5 = getTableData("list5");
                for(var i in tab_list5){
                    if(tab_list5[i].MaVerRd == rep_Tab_Tr.MaVerRd){
                        toastr.warning(rep_Tab_Tr.MaName + "，已经被添加");
                        return;
                    }
                }
                addSrow("list5",rep_Tab_Tr);*/
            }
        }
    };
    $replaceMaInfo.zc_select(repMa);

    $(".add2").click(function () {
        var rep_Tab_Tr={}
        list5MaterialNum=$("#xhsl").val().trim();
        list5MaterialRate=$("#shl").val().trim();
        if($("#replaceMaInfoselect").is(':visible')){
            list5MaterialMaverRd=$("#replaceMaInfoselectdatas").find("option:selected").val();
            list5MaterialCode=$("#replaceMaInfoselectdatas").find("option:selected").attr("a");
            list5MaterialName=$("#replaceMaInfoselectdatas").find("option:selected").attr("b");
            list5MaterialDes=$("#replaceMaInfoselectdatas").find("option:selected").attr("c");
              rep_Tab_Tr={
                "MaVerRd":list5MaterialMaverRd,
                "MaCode":list5MaterialCode,
                "MaName":list5MaterialName,
                "MaDes":list5MaterialDes,
                "Num":list5MaterialNum,
                "ARate":list5MaterialRate,
            };
        }else {
            rep_Tab_Tr={
                "MaVerRd":list5MaterialMaverRd,
                "MaCode":list5MaterialCode,
                "MaName":list5MaterialName,
                "MaDes":list5MaterialDes,
                "Num":list5MaterialNum,
                "ARate":list5MaterialRate,
            };
        }


        console.log(rep_Tab_Tr);
        if(rep_Tab_Tr.MaVerRd==""||rep_Tab_Tr.MaVerRd==null){
            toastr.warning("物料不能为空");
            return false;
        }
        if(rep_Tab_Tr.Num==""||rep_Tab_Tr.Num==null){
            toastr.warning("消耗数量不能为空");
            return false;
        }
        if(rep_Tab_Tr.ARate==""||rep_Tab_Tr.ARate==null){
            toastr.warning("损耗率不能为空");
            return false;
        }
        addSrow("list5",rep_Tab_Tr);
        var list5tableData = getTableData("list5");
        if(list5tableData.length>0){
            $replaceMaInfo.clearseldata();
            $("#xhsl").val("");
            $("#shl").val("");
           $("#dw").text("");
        }

    });
    //查询所有工序信息
    var params = {
        "displaymode": "0",
        "title": "工序",
        "binddata": {
            "keyfield": "SpecVerRd",
            "fields": [
                {
                    "caption": "工序id",
                    "name": "SpecVerRd"
                }, {
                    "caption": "工序名称",
                    "name": "SpecName"
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
                            "FieldName":"SpecName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata=[];
                request({url:"/Spec/GetAllSpecInfo",data:{"ExecType":"00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}},function(Body){
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "SpecVerRd": datas[i].SpecVerRd,
                            "SpecName": datas[i].SpecName
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
    $specName.zc_select(params);

    $Version.on("change",function () {
        for(var i in BomVerRds){
            if(BomVerRds[i].Version == $(this).val()){
                getBVInfo("01",{"BomVerRd":BomVerRds[i].BomVerRd});
                break;
            }
        }
    });

    //根据BomRd获取BOM列表信息
    function getBomTree(BomRd) {
        var data;
        request({url:"/BOM/GetBOMTreeInfo", data: {"ExecType": "00", "BusData": "{\"BomRd\":" + BomRd + "}"}},function(Body){
            data = Body.Data;
        });
        return data;
    }

    function getUnit(MVerRd){
        request({url:"/Commom/GetCMWFInfo",async:true,data:{"ExecType":"00","BusData":JSON.stringify({"MVerRd":MVerRd})}},function(Body){
            $unit.html(Body.Data.MaInfo.UnitInfo == null ?"":Body.Data.MaInfo.UnitInfo.UnitName);
        })
    }

    function getBVInfo(state,BOM) {
        //查询默认版本信息
        request({url:"/BOM/GetBVInfo", data: {"ExecType": state, "BusData": JSON.stringify(BOM)}},function (Body) {
            BOMInfos = Body.Data.BMInfo;
            $reason.val(Body.Data.Remark);

            $BomVerRd.val(Body.Data.BomVerRd);
            $BomCode.val(Body.Data.BomCode);
            $BomName.val(Body.Data.BomName);
            $creatPeople.val(Body.Data.Creator);
            $creatTime.val(Body.Data.CreateTime);
            $modifyPeople.val(Body.Data.LastModifyMan);
            $modifyTime.val(Body.Data.LastModifyTime);
            $beizhu.val(Body.Data.Remark);
            $VersionNo.val(Body.Data.VersionNo);
            $BomNo.val(Body.Data.BomNo);
            $Status.val(Body.Data.IsDef);

            //设置只读
            $BomCode.attr("readonly",true);
            $BomName.attr("readonly",true);
            $Status.prop("disabled",true);
            $VersionNo.attr("readonly",true);
            $BomNo.attr("readonly",true);

            //把input框隐藏，下拉框显示
            $Version.css("display","inline-block");
            $addVersion.css("display","none");

            isDef = Body.Data.IsDef;

            //填充用料清单表格
            var BMInfo = Body.Data.BMInfo;
            for(var i = 0;i < BMInfo.length;i++){
                // Body.Data.BMInfo[i]["MaVerRd"] = Body.Data.BMInfo[i].MaInfo.MaVerRd;
                // Body.Data.BMInfo[i]["MaCode"] = Body.Data.BMInfo[i].MaInfo.MaCode;
                // Body.Data.BMInfo[i]["MaName"] = Body.Data.BMInfo[i].MaInfo.MaName;
                // Body.Data.BMInfo[i]["SpecVerRd"] = Body.Data.BMInfo[i].SpecInfo.SpecVerRd;
                // Body.Data.BMInfo[i]["SpecName"] = Body.Data.BMInfo[i].SpecInfo.SpecName;
                BMInfo[i].ReMaInfo = JSON.stringify(BMInfo[i].ReMaInfo);
                if(!JSON.parse(BMInfo[i].ReMaInfo).length)
                    BMInfo[i]["Look"] = "";
                else
                    BMInfo[i]["Look"] = "<a href='javascript:void(0);'>查看</a>";
            }
            tabConfig.data = Body.Data.BMInfo;
           for(var i=0;i<tabConfig.data.length;i++){
               tabConfig.data[i].ConSMode=="00"?tabConfig.data[i].ConSMode="仅显示":
                   tabConfig.data[i].ConSMode=="01"?tabConfig.data[i].ConSMode="批次":
                       tabConfig.data[i].ConSMode="序号";
           }
            fullTable(tabConfig);
        });
    }

    $("#import").on("click",function () {
        //弹窗
        layer.open({
            type: 2,
            title:'BOM导入',
            shadeClose: true,
            area: ['60%', '50%'],
            content:getBasePath()+"/BOM/importbomPG"
        });
    });
    //导出
    $("#export").on('click', function () {
        if($BomVerRd.val() != "" && $BomVerRd.val() != null){
            layer.confirm('确认要导出该BOM信息？', {
                btn: ['确认', '取消']
            }, function () {
                layer.closeAll("dialog");
                var url = getBasePath() + "/BOM/exportBOMExcel";
                var data_ = "ExecType=00&BusData="+$BomVerRd.val();
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

                            filename = "BOM清单表-" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();

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
                            if (json.Body != undefined) {
                                toastr.warning(json.Body.MsgDes);
                            }
                        }
                    }
                };
                xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xhr.send(data_);
            });
        }else{
            toastr.warning("请选择要导出的BOM再进行导出操作");
        }
    });

    //function Look() {
        //查看替代料
        $(document).on("click",".Look",function (e) {
            if($(this).text() == "")
                return;
            var $tr = $(this).parents("tr");
            e.stopPropagation();
            //显示物料消耗的tab
            $tab_5_li.css("display","block").addClass("active").siblings().removeClass("active");
            $tab_5.addClass("active").siblings().removeClass("active");
            //有数据填充替代料的表格，没有就清空
            console.log($tr.find(".ReMaInfo").text())
            repTabConfig.data = JSON.parse($tr.find(".ReMaInfo").text());
            fullTable(repTabConfig);
            //删除按钮隐藏
            $(".del2").parent().css("display","none");
            //确认按钮显示
            $(".sure1").parent().css("display","none");
            //搜索框隐藏
            $("#tidailiao").css("display","none");
        });
    //}
    function showXH(obj) {
        resultFlag=true;
        modifyTable=$(obj).find(".addid").text();
        //alert(modifyTable)

        //显示物料消耗的tab
        $tab_4_li.css("display","block").addClass("active").siblings().removeClass("active");
        $tab_4.addClass("active").siblings().removeClass("active");
        //获取当前双击表格的数据

        _BMRd = $(obj).find(".BMRd").text();
        var _MaVerRd = $(obj).find(".MaVerRd").text();
        _MaCode = $(obj).find(".MaCode").text();
        var _MaName = $(obj).find(".MaName").text();
        var _SpecVerRd = $(obj).find(".SpecVerRd").text();
        var _SpecName = $(obj).find(".SpecName").text();
        var _Num = $(obj).find(".Num").text();
        var _aRate=$(obj).find(".ARate").text();
        var _UnitName = $(obj).find(".UnitName").text();
        var _ConSMode = $(obj).find(".ConSMode").text();
        var _Checked = $(obj).find(".Checked").text();

        $maName.showData({
            id:_MaVerRd,
            name:_MaName,
            keyfield:"MaVerRd",
            fields:[
                {"name":"MaVerRd"},
                {"name":"MaName"}
            ]
        });
        $specName.showData({
            id:_SpecVerRd,
            name:_SpecName,
            keyfield:"SpecVerRd",
            fields:[
                {"name":"SpecVerRd"},
                {"name":"SpecName"}
            ]
        })
        $aRate.val(_aRate);
        $consumeNum.val(_Num);
        $consume.find("option").each(function () {
            if(_ConSMode == $(this).val()){
                $(this).prop("selected",true).siblings().prop("selected",false);
                return false;
            }
        })

        $check.prop("checked",_Checked == "00" ? true:false);
        $unit.html(_UnitName);
    }
    trDBLClick();
    function trDBLClick() {
        $("#list4").find("tbody").on("dblclick", "tr", function () {
            if("03" == $ExecType.val()){
                flag = true;
                showXH(this);
                //所有内容都可编辑
                $(".sure").show();
                $("#tab_4").find("input").each(function () {
                    $(this).prop("readonly",false);
                    if($(this).attr("type") == "checkbox"){
                        $(this).prop("disabled",false);
                    }
                });
                $("#tab_4").find("select").prop("disabled",false);
                $("#tab_4").find("img").show();
            }else{
               // alert(this);
                showXH(this);
                //所有内容都不可编辑
                $(".sure").css("display","none");
                $("#tab_4").find("input").each(function () {
                    $(this).prop("readonly",true);
                    if($(this).attr("type") == "checkbox"){
                        $(this).prop("disabled",true);
                    }
                });
                $("#tab_4").find("select").prop("disabled",true);
                $("#tab_4").find("img").css("display","none");
            }
        });
    }
    //物料消耗的关闭
    $(".XH").on("click",function () {
        $tab_4_li.css("display","none").prev().addClass("active");
        $tab_4.removeClass("active").prev().addClass("active");
    });
    $tab_5_li.css("display","none");
    $tab_5.removeClass("active");
    //替代料的关闭
    $(".TDL").on("click",function () {
        $tab_5_li.css("display","none").prev().prev().addClass("active");
        $tab_5.removeClass("active").prev().prev().addClass("active");
    });
});