
function check(t,oper){
    var data_tr=$(t).parent().parent(); //获取到触发的tr
    if(oper=="MoveUp"){    //向上移动
        if($(data_tr).prev().html()==null){ //获取tr的前一个相同等级的元素是否为空
            // alert("已经是最顶部了!");
            return;
        }{
            $(data_tr).insertBefore($(data_tr).prev()); //将本身插入到目标tr的前面
        }
    }else{
        if($(data_tr).next().html()==null){
            // alert("已经是最低部了!");
            return;
        }{
            $(data_tr).insertAfter($(data_tr).next()); //将本身插入到目标tr的后面
        }
    }
}

$(function(){

    var colNamesArr4 = [
        {"Caption": "工厂代码", "Name": "FromVal", "CType": "text", "Editable": true},
        {"Caption": "工厂描述", "Name": "TargetVal", "CType": "text", "Editable": true},
    ];
    var config4 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr4,
        multiselect: true,
        width: 0.8,
        height: 0.415
    };
    config4.data.length = 0;
    fullTable(config4);//加载空表格



    $(".add5").click(function(){
        var CZRelType=$("#select").find("option:selected").val();
        if(CZRelType=="05"){
            $("#pp").show();
            $("#tbody").prepend("<tr><td><input name='ckb' type='checkbox'/></td><td><input type='text' style='width:100%'  value='' name='FromVal'/></td><td><input style='width:100%' type='text' value='' name='TargetVal'/></td><td><a onclick=check(this,'MoveUp')>上移&nbsp;&nbsp;</a><a  onclick=check(this,'MoveDown')>下移</a></td></tr>");
        }else {
            addErow("list4");
        }


    });


    function allCheck(allCkb, items){
        $("#"+allCkb).click(function(){

        });
    }
    $(document).on("click","#aa",function(){
        if($(this).prop("checked")==true){
            $("input[name='ckb']:checkbox").prop("checked", true );
        }else {
            $("input[name='ckb']:checkbox").prop("checked", false );
        }
    });



    $(".del5").click(function(){
        var CZRelType=$("#select").find("option:selected").val();
        if(CZRelType=="05"){
            var ckbs=$("input[name='ckb']:checked");
            if(ckbs.size()==0){
                toastr.warning("要删除指定行，需选中要删除的行");
                return;
            }
            ckbs.each(function(){
                $(this).parent().parent().remove();
            });
        }else {
            derow("list4");
        }

    });

    $("#select").change(function(){
        var table4Data = getTableData("list4");
        var value="";
        value=$(this).find("option:selected").val();
        var test1 = {
            "CZRelType": $(this).find("option:selected").val()
        };
        var thead="<tr>"
        if(value=="00"){
            $("#section").show();
            $("#table").hide();
            $("#pp").hide();
            var colNamesArr4 = [
                {"Caption": "工厂代码", "Name": "FromVal", "CType": "text", "Editable": true},
                {"Caption": "工厂描述", "Name": "TargetVal", "CType": "text", "Editable": true},
            ];


            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                    if(Body.Data.length>0){
                        var config4 = {
                            tableId: 'list4',
                            data: Body.Data,
                            colArr: colNamesArr4,
                            multiselect: true,
                            width: 0.8,
                            height: 0.415
                        };
                        fullTable(config4);//加载空表格
                    }else {
                        var config4 = {
                            tableId: 'list4',
                            data: [],
                            colArr: colNamesArr4,
                            multiselect: true,
                            width: 0.8,
                            height: 0.415
                        };
                        config4.data.length = 0;
                        fullTable(config4);//加载空表格
                    }
            });

        }else if(value=="01"){
            $("#section").show();
            $("#table").hide();
            $("#pp").hide();
            var colNamesArr4 = [
                {"Caption": "单/多晶代码", "Name": "FromVal", "CType": "text", "Editable": true},
                {"Caption": "单/多晶描述", "Name": "TargetVal", "CType": "text", "Editable": true},
            ];

            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                if(Body.Data.length>0){
                    var config4 = {
                        tableId: 'list4',
                        data: Body.Data,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    fullTable(config4);//加载空表格
                }else {
                    var config4 = {
                        tableId: 'list4',
                        data: [],
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    config4.data.length = 0;
                    fullTable(config4);//加载空表格
                }
            });

        }else if(value=="02"){
            $("#section").show();
            $("#table").hide();
            $("#pp").hide();
            var colNamesArr4 = [
                {"Caption": "电池片尺寸代码", "Name": "FromVal", "CType": "text", "Editable": true},
                {"Caption": "电池片尺寸描述", "Name": "TargetVal", "CType": "text", "Editable": true},
            ];

            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                if(Body.Data.length>0){
                    var config4 = {
                        tableId: 'list4',
                        data: Body.Data,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    fullTable(config4);//加载空表格
                }else {
                    var config4 = {
                        tableId: 'list4',
                        data: [],
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    config4.data.length = 0;
                    fullTable(config4);//加载空表格
                }
            });


        }
        else if(value=="03"){
            $("#section").show();
            $("#table").hide();
            $("#pp").hide();
            var colNamesArr4 = [
                {"Caption": "外观图形代码", "Name": "FromVal", "CType": "text", "Editable": true},
                {"Caption": "外观图形描述", "Name": "TargetVal", "CType": "text", "Editable": true},
            ];


            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                if(Body.Data.length>0){
                    var config4 = {
                        tableId: 'list4',
                        data: Body.Data,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    fullTable(config4);//加载空表格
                }else {
                    var config4 = {
                        tableId: 'list4',
                        data: [],
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    config4.data.length = 0;
                    fullTable(config4);//加载空表格
                }
            });


        }
        else if(value=="04"){
            $("#section").show();
            $("#table").hide();
            $("#pp").hide();
            var colNamesArr4 = [
                {"Caption": "技术信息代码", "Name": "FromVal", "CType": "text", "Editable": true},
                {"Caption": "技术信息描述", "Name": "TargetVal", "CType": "text", "Editable": true},
            ];


            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                if(Body.Data.length>0){
                    var config4 = {
                        tableId: 'list4',
                        data: Body.Data,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    fullTable(config4);//加载空表格
                }else {
                    var config4 = {
                        tableId: 'list4',
                        data: [],
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    config4.data.length = 0;
                    fullTable(config4);//加载空表格
                }
            });

        }
        else if(value=="05"){
           // alert(5)
            $("#pp").show();
            $("#section").hide();
            $("#table").show();
            thead+="<tr><th><input type='checkbox' id='aa'/></th><th>电池片效率代码</th><th>电池片效率描述 </th><th>操作</th><tr/>";
            $("#thead").empty().html(thead);
            var test1 = {
                "CZRelType": "05"
            };
            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                //alert(JSON.stringify((Body.Data)))
                if(Body.Data.length>0){
                        var aa=Body.Data;
                        var tr_="";
                        for(var i  in  aa){
                            tr_+="<tr><td><input  name='ckb' type='checkbox'/></td><td><input style='width:100%' type='text' value='"+aa[i].FromVal+"' name='FromVal'/></td><td><input style='width:100%' type='text' value='"+aa[i].TargetVal+"' name='TargetVal'/></td><td><a onclick=check(this,'MoveUp')>上移&nbsp;&nbsp;</a><a  onclick=check(this,'MoveDown')>下移</a></td></tr>";
                        }
                    $("#tbody").empty().html(tr_);
                    function check(t,oper){
                        var data_tr=$(t).parent().parent(); //获取到触发的tr
                        if(oper=="MoveUp"){    //向上移动
                            if($(data_tr).prev().html()==null){ //获取tr的前一个相同等级的元素是否为空
                                // alert("已经是最顶部了!");
                                return;
                            }{
                                $(data_tr).insertBefore($(data_tr).prev()); //将本身插入到目标tr的前面
                            }
                        }else{
                            if($(data_tr).next().html()==null){
                                // alert("已经是最低部了!");
                                return;
                            }{
                                $(data_tr).insertAfter($(data_tr).next()); //将本身插入到目标tr的后面
                            }
                        }
                    }
                }else {
                    $("#tbody").empty();
                }
            });

        }
        else if(value=="06"){
            $("#pp").hide();
            $("#section").show();
            $("#table").hide();
            var colNamesArr4 = [
                {"Caption": "电池片颜色代码", "Name": "FromVal", "CType": "text", "Editable": true},
                {"Caption": "电池片颜色描述", "Name": "TargetVal", "CType": "text", "Editable": true},
            ];

            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                if(Body.Data.length>0){
                    var config4 = {
                        tableId: 'list4',
                        data: Body.Data,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    fullTable(config4);//加载空表格
                }else {
                    var config4 = {
                        tableId: 'list4',
                        data: [],
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    config4.data.length = 0;
                    fullTable(config4);//加载空表格
                }
            });

        }
        else if(value=="07"){
            $("#section").show();
            $("#table").hide();
            $("#pp").hide();
            var colNamesArr4 = [
                {"Caption": "电池片等级代码", "Name": "FromVal", "CType": "text", "Editable": true},
                {"Caption": "电池片等级描述", "Name": "TargetVal", "CType": "text", "Editable": true},
            ];

            request({
                url: "/SunPort/CZRel/GetCZRelInfo",
                data: {
                    "ExecType": "00",
                    "BusData": JSON.stringify(test1)
                },async: false
            }, function (Body) {
                if(Body.Data.length>0){
                    var config4 = {
                        tableId: 'list4',
                        data: Body.Data,
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    fullTable(config4);//加载空表格
                }else {
                    var config4 = {
                        tableId: 'list4',
                        data: [],
                        colArr: colNamesArr4,
                        multiselect: true,
                        width: 0.8,
                        height: 0.415
                    };
                    config4.data.length = 0;
                    fullTable(config4);//加载空表格
                }
            });

        }



    });



    var colNamesArr4 = [
        {"Caption": "工厂代码", "Name": "FromVal", "CType": "text", "Editable": true},
        {"Caption": "工厂描述", "Name": "TargetVal", "CType": "text", "Editable": true},
    ];
    var test1 = {
        "CZRelType": $(this).find("option:selected").val()
    };
    request({
        url: "/SunPort/CZRel/GetCZRelInfo",
        data: {
            "ExecType": "00",
            "BusData": JSON.stringify(test1)
        },async: false
    }, function (Body) {
        if(Body.Data.length>0){
            var config4 = {
                tableId: 'list4',
                data: Body.Data,
                colArr: colNamesArr4,
                multiselect: true,
                width: 0.8,
                height: 0.415
            };
            fullTable(config4);//加载空表格
        }else {
            var config4 = {
                tableId: 'list4',
                data: [],
                colArr: colNamesArr4,
                multiselect: true,
                width: 0.8,
                height: 0.415
            };
            config4.data.length = 0;
            fullTable(config4);//加载空表格
        }
    });



    $("#save_").click(function(){
        var CZRelType=$("#select").find("option:selected").val();
        var CZRelData=[];
        if(CZRelType=="05"){

            $("#tbody tr").each(function() {
                var CZRelDatas={
                    "FromVal":$(this).find("input[name='FromVal']").val(),
                    "TargetVal": $(this).find("input[name='TargetVal']").val(),
                }
                CZRelData.push(CZRelDatas);
            })

        }else{
            CZRelData=getTableData("list4");
            if(CZRelData.length<=0){
                toastr.warning("明细不能为空");
                return false;
            }
        }

        var busdata={
           "CZRelType":CZRelType,
           "CZRelData" :CZRelData
        }


        request({url:"/SunPort/CZRel/SaveCZRelInfo", data: {"ExecType": "00", "busData": JSON.stringify(busdata)}},function(Body){
            toastr.success(Body.MsgDes);
        });


    });

});