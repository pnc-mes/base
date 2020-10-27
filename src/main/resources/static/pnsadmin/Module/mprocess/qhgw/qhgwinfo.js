$(function(){
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

    var person = {
        "displaymode": "0",
        "title": "user",
        "binddata": {
            "keyfield": "UserRd",
            "fields": [
                {
                    "caption": "UserRd",
                    "name": "UserRd"
                }, {
                    "caption": "UserName",
                    "name": "UserName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"UserName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "UserName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/User/GetAllUserInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UserRd": datas[i].UserRd,
                            "UserName": datas[i].UserName,
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

    $("#person").zc_select(person);

    var shebei = {
        "displaymode": "0",
        "title": "shebei",
        "binddata": {
            "keyfield": "DevRd",
            "fields": [
                {
                    "caption": "DevRd",
                    "name": "DevRd"
                }, {
                    "caption": "DevName",
                    "name": "DevName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"DevName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "DevName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Device/GetAllDevInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "DevRd": datas[i].DevRd,
                            "DevName": datas[i].DevName,
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
        }
    };

    $("#shebei").zc_select(shebei);

    var mbgw = {
        "displaymode": "0",
        "title": "工位",
        "binddata": {
            "keyfield": "StationRd",
            "fields": [
                {
                    "caption": "StationRd",
                    "name": "StationRd"
                }, {
                    "caption": "StationName",
                    "name": "StationName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onseardata":function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"StationName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        },{
                            "FieldName": "StationName",
                            "FieldOpt": "Order BY"
                        }
                    ]
                };

                var obj = {
                    data:{"ExecType": "00","InitData":JSON.stringify(InitData)},
                    url:"/Station/GetAllStationInfo"
                };
                var xldata = [];
                request(obj,function(Body) {
                    var datas = Body.Data;
                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "StationRd": datas[i].StationRd,
                            "StationName": datas[i].StationName,
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
        }
    };

    $("#mbgw").zc_select(mbgw);




    $(".cSelect").click(function(){
        var LineRd=$("#xt").getseldata().LineRd;
        if(LineRd==null||LineRd==""){
            toastr.warning("线体不能为空");
            return false;
        }
        var UserRd=$("#person").getseldata().UserRd;
        var DevRd=$("#shebei").getseldata().DevRd;

        var data={
            "LineRd":LineRd,
            "UserRd":UserRd,
            "DevRd":DevRd
        }

        var objData = {
            "ExecType": "00",
            "BusData": JSON.stringify(data),
        };
        request({url: "/Station/GetAllStationTInfo", data: objData,}, function (Body) {
            var data=Body.Data;
            console.log(data);
            var datas="";
            for(var i  in data){
                var detailS="";
                if(data[i].ExecList.length>0){
                    for(var j  in  data[i].ExecList){
                        detailS+="<a id='aData' a='"+data[i].ExecList[j].ExecRd+"'  style='cursor: pointer;text-decoration:underline;'>"+data[i].ExecList[j].ExecName+"</a>"+" "
                    }
                }
                datas+="<tr>"+"<td  style='text-align: center;'>"+data[i].LineName+"</td>"+"<td style='text-align: center' >"+data[i].SpecName+"</td>"+"<td style='text-align: center'>"+data[i].StationName+"</td>"+"<td a='"+data[i].StationRd+"' style='text-align: center'>"+detailS+"</td>"+"</tr>"
            }
            $("#tbody").empty().html(datas);
            aa();
        });
    });

    var ExexRd="";
    var SStationRd="";
    function aa(){
        $("table").find("tbody").on("click","a",function () {
            ExexRd=$(this).attr("a");
            SStationRd=$(this).parent().attr("a");
            var text=$(this).parent().prev().text();
            $("#myModal").modal("show");
            $("#ygw").text(text);
        })
    }

    $("#save").click(function(){
        if(SStationRd==""||SStationRd==null){
            toastr.warning("源工位不能为空");
            return false;
        }
        if(ExexRd==""||ExexRd==null){
            toastr.warning("操作不能为空");
            return false;
        }

        var TStationRd=$("#mbgw").getseldata().StationRd;
        if(TStationRd==""||TStationRd==null){
            toastr.warning("目标工位不能为空");
            return false;
        }
          var Remark=$("#remark").val().trim();
          var InitDta={
              "SStationRd":SStationRd,
              "ExexRd":ExexRd,
              "TStationRd":TStationRd,
              "Remark":Remark
          }
        request({
            url: "/Station/SaveStationInfo",
            data: {"ExecType": "03", "busData": JSON.stringify(InitDta)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
        });


    });
});