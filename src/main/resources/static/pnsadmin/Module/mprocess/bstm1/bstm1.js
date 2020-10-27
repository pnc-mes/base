$(function () {
    $("#mbgx").selectpicker({
        width:198
    });
    var Direction="";
    var xt = {
        "displaymode": "0",
        "title": "line",
        "binddata": {
            "keyfield": "LineRd",
            "fields": [
                {
                    "caption": "LineRd",
                    "name": "LineRd"
                }, {
                    "caption": "线体名称",
                    "name": "LineName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"LineName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "LineName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Line/GetAllLineInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "LineRd": datas[i].LineRd,
                            "LineName": datas[i].LineName,
                        };
                        xldata.push(data);
                    }
                });
                var obj = {
                    data:xldata,
                    showrow:500
                };
                return obj;
            }
        }
    };

    $("#xt").zc_select(xt);


    //选择往后道的时候保存的数据
    var Down=[];
    var Up=[];
    $("#ID").on("keydown", function (e) {
        if (e.keyCode == 13) {
            var Batch=$("#ID").val().trim();
            if(Batch==null||Batch==""){
                toastr.warning("数据不能为空");
                return false;
            }
            request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch":Batch})}},function (                      Body) {
                    if(Body.Data!=null||Body.Data){
                        if(Body.Data.WFInfo!=null||Body.Data.WFInfo){
                            if(Body.Data.WFInfo.CSpecInfo!=null||Body.Data.WFInfo.CSpecInfo){
                                  var SpecName=  Body.Data.WFInfo.CSpecInfo.SpecName;

                                  if(Body.Data.WFInfo.SpecInfo.length>0){
                                         var aa=Body.Data.WFInfo.SpecInfo;
                                         var i =1;
                                         var paths=[];
                                         for(var a in aa){
                                             var path={
                                                 "SpecVerRd":aa[a].SpecVerRd,
                                                 "SpecName":aa[a].SpecName,
                                                 "addNum":i
                                             }
                                             paths.push(path);
                                             i++
                                         }
                                         var newaddNum="";
                                         if(paths.length>0){
                                             for(var j in paths){
                                                 if(paths[j].SpecName==SpecName){
                                                     newaddNum=paths[j].addNum;
                                                 }
                                             }
                                         }

                                      var DownData=[];
                                      var UpData=[];
                                         if(newaddNum!=""){
                                             var str="";
                                             for(var a in paths){
                                                 if(newaddNum<=paths[a].addNum){
                                                     str+="<option value='"+paths[a].SpecVerRd+"'>"+paths[a].SpecName+"</option>"
                                                     var UpDatas={
                                                         "SpecVerRd":paths[a].SpecVerRd,
                                                         "SpecName":paths[a].SpecName,
                                                     }
                                                     UpData.push(UpDatas);
                                                 }else{
                                                     var newDatas={
                                                        "SpecVerRd":paths[a].SpecVerRd,
                                                        "SpecName":paths[a].SpecName,
                                                    }
                                                     DownData.push(newDatas);
                                                 }
                                             }
                                             Down=DownData.concat([]);
                                             Up= UpData.concat([]);
                                             Direction="01";
                                             $("#mbgx").empty().html(str);
                                             $('#mbgx').selectpicker('refresh');
                                             $("#mbgx").selectpicker({
                                                 width:198
                                             });
                                             $("#up").attr("checked","checked");
                                         }


                                  }

                                 /* var Path=[];
                                  if(Body.Data.WFInfo.Path.length>0){
                                            var aa=Body.Data.WFInfo.Path;
                                            var str="";
                                            for(var i  in  aa){
                                                if(aa[i].SpecName==SpecName){
                                                    str+="<option value='"+aa[i].SpecVerRd+"'>"+aa[i].SpecName+"</option>"
                                                }
                                             }
                                            $("#mbgx").empty().html(str);
                                  }*/
                            }
                        }
                    }

            })
        }
    });

    $(".cSave").click(function(){
        var Batch=$("#ID").val().trim();
        var LineRd=$("#xt").getseldata().LineRd;
      //  var SpecVerRd=$("#mbgx").find("option:selected").val();

        var SpecVerRd=$("#mbgx").val();

        if(Batch==null||Batch==""){
            toastr.warning("批次不能为空");
            return false;
        }
        if(LineRd==null||LineRd==""){
            toastr.warning("线体不能为空");
            return false;
        }
        if(SpecVerRd==null||SpecVerRd==""){
            toastr.warning("工序不能为空");
            return false;
        }
        var ActionType="";


        if($("#up").is(":checked")){
            Direction="01";

            if($("#in").prop("checked")){
                ActionType="00";
            }else  if($("#out").prop("checked")){
                ActionType="01";
            }


            if(ActionType==""){
                toastr.warning("请选择目标工序行为");
                return false;
            }
        }

        if($("#down").is(":checked")){
            Direction="00";
            if($("#in").prop("checked")){
                ActionType="00";
            }else  if($("#out").prop("checked")){
                ActionType="01";
            }else {
                ActionType="02";
            }
        }
        if(Direction==""){
            toastr.warning("调站方式不能为空");
            return false;
        }
        var Remark=$("#textarea").val().trim();

        var busData={
            "Batch":Batch,
            "Direction":Direction,
            "LineRd":LineRd,
            "Remark":Remark,
            "SpecVerRd":SpecVerRd[0],
            "ActionType":ActionType
        }
        request({url:"/SpecOpert/GetIOSInfo",data: {"ExecType": "07", "BusData": JSON.stringify(busData)}},function(Body) {
            toastr.success(Body.MsgDes)
        });


    });

    $("#up").click(function(){
        if($(this).is(":checked")){
            var str="";
            for(var a in Up) {
                str += "<option value='" + Up[a].SpecVerRd + "'>" + Up[a].SpecName + "</option>"
            }
            $("#mbgx").empty().html(str);
            $('#mbgx').selectpicker('refresh');
            $("#mbgx").selectpicker({
                width:198
            });
        }
    });
    $("#down").click(function(){
        if($(this).is(":checked")){
            $("#in").prop("checked",false)
            $("#out").prop("checked",false)
            var str="";
            for(var a in Down) {
                str += "<option value='" + Down[a].SpecVerRd + "'>" + Down[a].SpecName + "</option>"
            }
            $("#mbgx").empty().html(str);
            $('#mbgx').selectpicker('refresh');
            $("#mbgx").selectpicker({
                width:198
            });
        }
    });
   /* $('#mbgx').on('shown.bs.select', function (e, clickedIndex, isSelected, previousValue) {
        if($("#up").is(":selected")){
            alert(JSON.stringify(Up))
        }
        if($("#Down").is(":selected")){
            alert(JSON.stringify(Down))
        }



        /!*var Batch=$("#ID").val().trim();
        if(Batch==null||Batch==""){
            toastr.warning("ID不能为空");
            return false;
        }
        request({url:"/Commom/GetCMBInfo",data:{"ExecType":"00","BusData":JSON.stringify({"Batch":Batch})}},function (                      Body) {
            if(Body.Data!=null||Body.Data){
                if(Body.Data.WFInfo!=null||Body.Data.WFInfo){
                    if(Body.Data.WFInfo.CSpecInfo!=null||Body.Data.WFInfo.CSpecInfo){
                        var SpecName=  Body.Data.WFInfo.CSpecInfo.SpecName;

                        if(Body.Data.WFInfo.SpecInfo.length>0){
                            var aa=Body.Data.WFInfo.SpecInfo;
                            var i =1;
                            var paths=[];
                            for(var a in aa){
                                var path={
                                    "SpecVerRd":aa[a].SpecVerRd,
                                    "SpecName":aa[a].SpecName,
                                    "addNum":i
                                }
                                paths.push(path);
                                i++
                            }
                            var newaddNum="";
                            if(paths.length>0){
                                for(var j in paths){
                                    if(paths[j].SpecName==SpecName){
                                        newaddNum=paths[j].addNum;
                                    }
                                }
                            }

                            // var newData=[];
                            if(newaddNum!=""){
                                var str="";
                                for(var a in paths){
                                    if(newaddNum<=paths[a].addNum&&$("#up").is(":checked")){
                                        str+="<option value='"+paths[a].SpecVerRd+"'>"+paths[a].SpecName+"</option>"
                                    }else {
                                        str+="<option value='"+paths[a].SpecVerRd+"'>"+paths[a].SpecName+"</option>"
                                    }
                                }
                                $("#mbgx").empty().html(str);
                                $('#mbgx').selectpicker('refresh');
                                $("#mbgx").selectpicker({
                                    width:198
                                });
                            }


                        }

                    }
                }
            }

        })*!/
    });*/


    $("#show").change(function(){
        var text=$(this).find("option:selected").text();
        if(text=="其他说明"){
            $("#textarea").prop("disabled",false);
        }
        if(text=="产线异常快速跳站"){
            $("#textarea").prop("disabled",true);
        }
        if(text=="产品重用"){
            $("#textarea").prop("disabled",true);
        }

    });

});