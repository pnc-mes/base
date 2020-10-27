$(function () {
    var isEdit=false;
    var treeID=null;
    /*方法：------获取点击之后一个节点的数据------------------*/
    var onClicks = function (nodeinfo, handle) {

        var aas=[];
        for(var i=1;i<=10;i++){
            var aa={
                "ExpandDtlRd":"",
                "FieldName":"F"+i,
                "FiledType":"00",
                "DisplayName":"",
                "CusData":"",
            }
            aas.push(aa);
        }
        var config1 = {
            tableId: 'list4',
            data: aas,
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height: 0.415
        };
        fullTable(config1);

        isEdit=true;
        $("#_right").show();//每次点击树节点的时候都把右侧展开

        treeID =nodeinfo.nodeID;
        var objBusData = JSON.stringify({"ExpandRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url:"/Expand/GetExpandInfo",data: objData},function(Body){
            $("#CusDataName").val(Body.Data.ExpandName);
            $("#beizhu").val(Body.Data.Remark);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#creatPeople").val(Body.Data.Creator);
            var IsSettleObj=Body.Data.IsSettleObj;
            if(IsSettleObj=="00"){
                $("#objcheck").prop("checked",true);
            }else {
                $("#objcheck").prop("checked",false);
            }
            $("#kzdx").val(Body.Data.ExpandType);
            var tablelist4data=getTableData("list4");

            var dd=  Body.Data.ExpandDtl;


              for(var j in tablelist4data){
                  for(var i in dd){
                      if(tablelist4data[j].FieldName==dd[i].FieldName){
                          tablelist4data[j].ExpandDtlRd=dd[i].ExpandDtlRd;
                          tablelist4data[j].FieldName=dd[i].FieldName;
                          tablelist4data[j].FiledType=dd[i].FiledType;
                          tablelist4data[j].DisplayName=dd[i].DisplayName;
                          tablelist4data[j].CusData=dd[i].CusData;
                      }

                  }
              }


            var config1 = {
                tableId: 'list4',
                data: tablelist4data,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height: 0.415
            };
           fullTable(config1);//加载空表格
            clickSelect();
            select();
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
        currentPage = treeSearchs("/Expand/GetAllExpandInfo","ExpandRd","ExpandName","ExpandName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Expand/GetAllExpandInfo","ExpandRd","ExpandName","ExpandName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }}
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Expand/GetAllExpandInfo","ExpandRd","ExpandName","ExpandName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Expand/GetAllExpandInfo","ExpandRd","ExpandName","ExpandName",condition,currentPage,config);
    });



    /*方法：首次加载页面------------*/

    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        request({
            url: '/Expand/GetAllExpandInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "ExpandName DESC",
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
                    id: treeData[i].ExpandRd == undefined ? "" : treeData[i].ExpandRd,
                    name: treeData[i].ExpandName == undefined ? "" : treeData[i].ExpandName
                };
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        })
    };
    // 调用，首次加载页面
    loaddata();

    //获取所有的自定义数据
    var treedataList=[];
    request({
        url: '/CusData/GetAllCDataInfo',
        data: {
            "ExecType": "00",
            "InitData": JSON.stringify({
                "FiledList": [
                    {
                        "FieldName": "CusDataName DESC",
                        "FieldOpt": "Order BY"
                    }]})
        }}, function (Body) {
        var treeData = Body.Data;

        for (var i = 0; i < treeData.length; i++) {
            var tree = {
                CusDataRd: treeData[i].CusDataRd == undefined ? "" : treeData[i].CusDataRd,
                CusDataName: treeData[i].CusDataName == undefined ? "" : treeData[i].CusDataName
            };
            treedataList.push(tree);
        }

    })

   /* var datas=[];
    datas.push();*/
    var colNamesArr = [
        {"Caption": "id", "Name": "ExpandDtlRd", "Hidden": true},
        {"Caption": "字段", "Name": "FieldName", "Hidden": false,"Width":50},
        {"Caption": "定义类型", "Name": "FiledType", "CType": "text", "Editable": false,"Width":80},
        {"Caption": "字段显示名", "Name": "DisplayName", "Hidden": false,"CType": "text", "Editable": true},
        {"Caption": "自定义数据", "Name": "CusData", "Hidden": false},
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height: 0.415
    };
    config1.data.length = 0;
    fullTable(config1);//加载空表格



    //物料事件
    $("#kzdx").change(function () {

       var ExpandType=$(this).find("option:selected").attr("value");
        if(ExpandType==null||""==ExpandType){
            var config1 = {
                tableId: 'list4',
                data: [],
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height: 0.415
            };
            fullTable(config1);//加载空表格
        }else {
            var objBusData = JSON.stringify({"ExpandType": ExpandType});
            var objData = {
                "ExecType": "00",
                "BusData": objBusData
            };
            request({url:"/Expand/GetExpandFieldInfo",data: objData},function(Body){
                var data=Body.Data;
                var datas=[];
                for(var i in data){
                    var da={
                        "ExpandDtlRd":"",
                        "FieldName":data[i].FieldName,
                        "FiledType":"",
                        "DisplayName":"",
                        "CusData":"",
                    }
                    datas.push(da);
                }
                var config1 = {
                    tableId: 'list4',
                    data: datas,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config1);//加载空表格
                clickSelect();
               select();

            });
        }
    });

    //新增
    $(".cAdd").click(function(){
        isEdit=true;
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height: 0.415
        };
        config1.data.length = 0;
        fullTable(config1);//加载空表格
        $("#CusDataName").val("");
        $("#kzdx").val("0");
        $("#objcheck").prop("checked",false);
        $("#beizhu").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID=null
    });

    var aa="<select role=\"select\" id=\"2_CusData\" name=\"CusData\" size=\"1\" class=\"editable\">";
    for(var i  in  treedataList){
        aa+='<option  role="option" value='+treedataList[i].CusDataRd+'>'+treedataList[i].CusDataName +'</option>';
    }
    aa+="</select>";
    var clickSelect = function () {

        $(".FiledType").change(function () {
            //$(this).unbind("click");
            var val=$(this).find("option:selected").attr("value");
            if(val=="01"){
                $(this).next().next().html(aa)
            }else {
                $(this).next().next().html("<input type=\"text\" id=\"2_CusData\" name=\"CusData\" role=\"textbox\" class=\"editable\" style=\"width: 100%;\">")
            }
        });
    }
    var ItemTypes = [
        {"value":"00","txt":"用户输入"},{"value":"01","txt":"定义枚举"}
    ];
    // 定义表格中下拉框的“定义类型”的事件
    var select = function () {
        $(".FiledType").each(function () {
            var current_Td_Val = $(this).text();
            var str = "<select>";
            for (var i = 0; i < ItemTypes.length; i++) {
                if (current_Td_Val == ItemTypes[i].value){
                    str += "<option selected value='" + ItemTypes[i].value + "'>" + ItemTypes[i].txt + "</option>";
                    var id=null;
                    for(var k  in  treedataList){
                       if(treedataList[k].CusDataName==$(this).next().next().prop("title")){
                           id=treedataList[k].CusDataRd;
                           break;
                       }
                    }
                    $(this).next().next().html(aa);
                    $(this).parent().find("#2_CusData").val(id)

                }
                else{
                    str += "<option value='" + ItemTypes[i].value + "'>" + ItemTypes[i].txt + "</option>";
                    $(this).next().next().html("<input type=\"text\"  value='"+$(this).next().next().prop("title")+"' id=\"2_CusData\"  name=\"CusData\" role=\"textbox\" class=\"editable\" style=\"width: 100%;\">");
                }
            }
            ;
            str += "</select>";
            $(this).html(str);
            $(this).find("select").css("border", "0px");
            $(this).find("select").css("height", "19px");
            $(this).find("select").css("color", "#000000");
        });
    }

    //保存
    $(".cSave").click(function () {
        if(!isEdit){
            toastr.warning("请先点击新增或点击左边树的信息");
            return false;
        }

        var ExpandName=$("#CusDataName").val().trim();
        var IsSettleObj="";
        if($("#objcheck").prop("checked")==true){
            IsSettleObj="00";
        }else {
            IsSettleObj="01";
        }
        if(ExpandName==null||ExpandName==""){
            toastr.warning("保存失败，扩展字段定义名称不能为空");
            return false;
        }

        var tableData = getTableData("list4");

        if(treeID==null){
            var datas=[];
            var k =1;
            for(var i  in tableData){
                if(tableData[i].DisplayName!=null&&tableData[i].DisplayName!=""){
                    var type=""
                    if(tableData[i].FiledType=="00"){
                        type="00";
                    }else if(tableData[i].FiledType=="01"){
                        type="01";
                    }else if(tableData[i].FiledType=="用户输入"){
                        type="00";
                    }else {
                        type="01";
                    }
                    var data={
                        "FieldName":tableData[i].FieldName,
                        "FiledType":type,
                        "DisplayName":tableData[i].DisplayName,
                        "CusData":tableData[i].CusData,
                    }
                    datas.push(data);
                    k++;
                }
            }
            var realData={
                "ExpandName":ExpandName,
                "IsSettleObj":IsSettleObj,
                "Remark":$("#beizhu").val().trim(),
                "ExpandType": $("#kzdx").find("option:selected").attr("value"),
                "ExpandDtl":datas
            }
            request({
                url: "/Expand/SaveExpandInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(realData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = "";
                loaddata();
                treeID=null;
            });
        }else {
            var datas=[];
            var k =1;
            for(var i  in tableData){
                if(tableData[i].DisplayName!=null&&tableData[i].DisplayName!=""){
                    var type=""
                    if(tableData[i].FiledType=="00"){
                        type="00";
                    }else if(tableData[i].FiledType=="01"){
                        type="01";
                    }else if(tableData[i].FiledType=="用户输入"){
                        type="00";
                    }else {
                        type="01";
                    }
                    var data={
                        "ExpandDtlRd":tableData[i].ExpandDtlRd,
                        "FieldName":tableData[i].FieldName,
                        "FiledType":type,
                        "DisplayName":tableData[i].DisplayName,
                        "CusData":tableData[i].CusData,
                    }
                    datas.push(data);
                    k++;
                }
            }
            var realData={
                "ExpandRd":treeID,
                "ExpandName":ExpandName,
                "IsSettleObj":IsSettleObj,
                "Remark":$("#beizhu").val().trim(),
                "ExpandType": $("#kzdx").find("option:selected").attr("value"),
                "ExpandDtl":datas
            }
            request({
                url: "/Expand/SaveExpandInfo",
                data: {"ExecType": "02", "busData": JSON.stringify(realData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = "";
                loaddata();
                treeID=null;
            });
        }
    });

    //重置
    $(".resert").click(function () {
        if(!isEdit){
            toastr.warning("请点击左边树的信息");
            return false;
        }
        //获取选择行
        var checkData=getSeRowData("list4");
        //获取所有行
        var list4Data=getRowData("list4");

        if(checkData.length>0){
            for(var j  in checkData){
                checkData[j].DisplayName="";
                checkData[j].CusData="";
                checkData[j].FiledType="00";
            }
            var config1 = {
                tableId: 'list4',
                data: checkData,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height: 0.415
            };
            fullTable(config1);
        }else {

            for(var i  in list4Data){
                list4Data[i].DisplayName="";
                list4Data[i].CusData="";
                list4Data[j].FiledType="00";
            }
            var config1 = {
                tableId: 'list4',
                data: list4Data,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height: 0.415
            };
            fullTable(config1);
        }
    });

    //删除
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID != "") {

            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    request({url:"/Expand/SaveExpandInfo",async:true, data:{"ExecType": "01","busData": "{\"ExpandRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
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

});