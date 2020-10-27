$(function(){

    var treeID=null;
    var colNamesArr = [
        {"Caption": "日期", "Name": "CalendarDate"},
        {"Caption": "班别", "Name": "ShiftName"},
        {"Caption": "团队", "Name": "TeamName"},
        {"Caption": "删除", "Name": "rm"}
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        width: 0.84,
        height:0.36
    };
    fullTable(config1);//加载空表格


    var onClicks = function (nodeinfo, handle) {
        $("#_right").show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"CalendarRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        };
        request({url:"/Calendar/GetCalendarInfo", data: objData},function(Body){

            //去除边框颜色
            $("#calendarname").removeAttr("style");
            $("#calendarname").val(Body.Data.CalendarName);
            $("#deception").val(Body.Data.Description);
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            $("#excelfile").val("");
            var config1 = {
                tableId: 'list4',
                data: Body.Data.CalendarDetail,
                colArr: colNamesArr,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);
        });
    };
    /!*----------------------定义控件规则-------------------*!/
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
        currentPage = treeSearchs("/Calendar/GetAllCalendarsInfo","CalendarRd","CalendarName","CalendarName",condition,currentPage,config);
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
        currentPage = treeSearchs("/Calendar/GetAllCalendarsInfo","CalendarRd","CalendarName","CalendarName",condition,currentPage,config);
        if(currentPage < 0){
            condition = "";
            currentPage = temp;
        }}
    })


    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Calendar/GetAllCalendarsInfo","CalendarRd","CalendarName","CalendarName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Calendar/GetAllCalendarsInfo","CalendarRd","CalendarName","CalendarName",condition,currentPage,config);
    });

    $("#_right").hide();


    var loaddata = function () {
        var treedataList = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };

        request({
            url:'/Calendar/GetAllCalendarsInfo',
            data: {
                "ExecType": "00",
                "PageInfo":JSON.stringify(pageInfo),
                "InitData": JSON.stringify({
                    "FiledList": [
                        {
                            "FieldName": "CalendarName",
                            "FieldOpt": "Order BY"
                        }]})
            }},function(Body){
            var treeData = Body.Data;
            if(treeData.length>0){
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20:treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].CalendarRd == undefined ? "" : treeData[i].CalendarRd,
                        name: treeData[i].CalendarName == undefined ? "" : treeData[i].CalendarName
                    };
                    treedataList.push(tree);
                }
                config.data.source = treedataList;
                $.JstreeEx.init(config);//先调用后加载
            }

        });
    };

    loaddata();

    $(".download").on("click", function () {
        window.location.href = getBasePath() + "/upload/生产日历模板.xlsx";
    });




    //文件上传
    //禁止onchange只执行一次
    var zzexcel="";
    var autoid=1;
    $("#file").live("change", function (e) {
        var value=$(this).val();
        if (!(value.endsWith('.xlsx') || value.endsWith('.xls') )) {
            toastr.warning("不是有效文件，请选择正确的excel文件");
            return false;
        }
        $("#excelfile").val($(this).val());
        var f = e.target.files[0];
        var reader = new FileReader();
        reader.readAsBinaryString(f);
        reader.onload = function(e) {
            var data = e.target.result;
            zzexcel = XLSX.read(data, {
                type: 'binary'
            });
            var data = [];
            for (var i = 0; i < zzexcel.SheetNames.length; i++) {
                data = XLSX.utils.sheet_to_json(zzexcel.Sheets[zzexcel.SheetNames[i]]);
            }
            var datasource = [];
            for (var i  in data) {
                var dataSourceP = {
                    "CalendarDate": data[i].日期,
                    "ShiftName": data[i].班别,
                    "TeamName": data[i].团队,
                }
                datasource.push(dataSourceP);
            }

            var config1 = {
                tableId: 'list4',
                data: datasource,
                colArr: colNamesArr,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);
            $("#file").replaceWith("<input type='file' id='file' readonly=''   name='file' multiple='' enctype='multipart/form-data' a =\" + count + \"'>");
        }});

    $('.deluser').on('click',function(){
        deallrow('list4');
    });

    $(".cAdd").click(function () {
        $("#_right").show();
        $("#calendarname").val("");
        $("#deception").val("");
        $("#excelfile").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");

        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            width: 0.84,
            height:0.36
        };
        fullTable(config1);

        $("#ExecType").val("00");
        treeID = null;
    });
    /*---------删除----------*/
    $(".cDel").click(function () {
        if (treeID != undefined && treeID != null && treeID.trim() != ""){
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/Calendar/SaveCalendarInfo", data: {"ExecType": "01", "busData": "{\"CalendarRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        $("#ExecType").val("");
                        treeID = null;
                        currentPage=0;
                        condition = '';
                        loaddata();
                    });
                }
            );
        } else {
            toastr.warning("请选择左侧要删除的一项再进行删除!");
        }
    });

    var newData = {};
    $(".cSave").click(function () {
        var list = getTableData("list4");
        if(treeID==null){
            var data={
                "CalendarName":$("#calendarname").val().trim(),
                "Description":$("#deception").val().trim(),
                "CalendarDetail":list,
                "Remark":$("#beizhu").val().trim(),
            }

            request({url:"/Calendar/SaveCalendarInfo", data: {"ExecType": "00", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }else {
            var data={
                "CalendarRd":treeID,
                "CalendarName":$("#calendarname").val().trim(),
                "Description":$("#deception").val().trim(),
                "CalendarDetail":list,
                "Remark":$("#beizhu").val().trim(),
            }

            request({url:"/Calendar/SaveCalendarInfo", data: {"ExecType": "02", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }
    });
});