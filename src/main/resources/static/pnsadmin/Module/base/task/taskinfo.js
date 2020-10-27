$(function () {
    //定义表格的全局唯一id
    var addid=1;
    //点击表格的时候获取表格的唯一id
    var tableaddid="";
    //表格已有数据，处理既有新增也有修改的
    var resultFlag=false;
    var colNamesArr = [
        {"Caption": "id", "Name": "TaskDatialRd", "Hidden": true },
        {"Caption": "任务项", "Name": "TaskItemName" },
        {"Caption": "确认方式", "Name": "SureType"},
        {"Caption": "最大执行次数", "Name": "MaxExCount"},
        {"Caption": "隐藏的id", "Name": "addid", "Hidden": true},

    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.84,
        height:0.36
    };
    fullTable(config1);//加载空表格
    var totalj=0;
    var treeID = null;
    /*-----------2:点击树的节点的时候获取技能信息----------------------------*/
    var onClicks = function (nodeinfo, handle) {

         $('#_right').show();
        treeID = nodeinfo.nodeID;

        var objBusData = JSON.stringify({"TaskRd": treeID})
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        }
        request({url: "/Task/GetTaskInfo", data: objData}, function (Body) {

            $("#taskName").val(Body.Data.TaskName );
            $("#decription").val(Body.Data.Description );
            $("#status").val(Body.Data.Status);
            $("#creatPeople").val(Body.Data.Creator);
            $("#creatTime").val(Body.Data.CreateTime);
            $("#modifyPeople").val(Body.Data.LastModifyMan);
            $("#modifyTime").val(Body.Data.LastModifyTime);
            $("#beizhu").val(Body.Data.Remark);
            $("#rwlx").val(Body.Data.TaskType);
            var TaskListDatialInfos=[];
            if(Body.Data.TaskDetailInfo!=null){
                var a=Body.Data.TaskDetailInfo;
               // var j=1;
                //totalj=a.length;//获取总个数
                for(var i in a ){
                    var TaskListDatialInfo={
                        "TaskDetailRd":a[i].TaskDetailRd,
                        "TaskItemName":a[i].TaskItemName,
                        "SureType":a[i].SureType=="00"?"打勾":"记录结果",
                        "MaxExCount":a[i].MaxExCount,

                        "addid":addid++,
                    }
                    TaskListDatialInfos.push(TaskListDatialInfo);
                  //  j++;
                }
                var config1 = {
                    tableId: 'list4',
                    data:TaskListDatialInfos,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height:0.36
                };
                fullTable(config1);
                trDBLClick();
            }else {
                var config1 = {
                    tableId: 'list4',
                    data:[],
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height:0.36
                };
                fullTable(config1);
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
        currentPage = treeSearchs("/Task/GetAllTaskInfo","TaskRd","TaskName","TaskName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Task/GetAllTaskInfo","TaskRd","TaskName","TaskName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Task/GetAllTaskInfo","TaskRd","TaskName","TaskName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
            currentPage = treeSearchs("/Task/GetAllTaskInfo","TaskRd","TaskName","TaskName",condition,currentPage,config);
    });
    $("#_right").hide();
    /*-------- 4:只刷新树(提高性能)---------*/
    var loaddata = function () {
        var trees = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"TaskName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        request({url:'/Task/GetAllTaskInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){
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
                    id: treeData[i].TaskRd == undefined ? "" : treeData[i].TaskRd,
                    name: treeData[i].TaskName == undefined ? "" : treeData[i].TaskName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();
    
    
    //添加
    $(".cAdd").click(function () {
        addid=1;
        $("#_right").show();
        treeID=null;
        $("#taskName").val("");
        $("#status").val("00");
        $("#decription").val("");
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#beizhu").val("");
        var config1 = {
            tableId: 'list4',
            data: [],
            colArr: colNamesArr,
            multiselect: true,
            width: 0.84,
            height:0.36
        };
        fullTable(config1);//加载空表格

    });

    //保存
    $(".cSave").click(function(){

        if($("#taskName").val().trim()==null||""==$("#taskName").val().trim()){
            toastr.warning("保存失败，名称不能为空");
            return false;
        }
        var list4=getRowData("list4");
        if(list4.length<=0){
            toastr.warning("保存失败，明细不能为空");
            return false;
        }
        if(treeID==null){


            var TaskListDetailInfos=[];
            for(var i in list4){
                delete list4[i].addid;
                var TaskListDetailInfo={
                    "TaskItemName":list4[i].TaskItemName,
                    "SureType":list4[i].SureType=="打勾"?"00":"01",
                    "MaxExCount":list4[i].MaxExCount,
                }
                TaskListDetailInfos.push(TaskListDetailInfo);
            }



            var data={
                "TaskName":$("#taskName").val().trim(),
                "Description":$("#decription").val(),
                "Status":$("#status option:selected").val(),
                "TaskType":$("#rwlx").val(),
                "Remark":$("#beizhu").val(),
                "TaskDetailInfo":TaskListDetailInfos
            }

            request({url:"/Task/SaveTaskInfo", data: {"ExecType": "00", "busData": JSON.stringify(data)}},function(Body){
                toastr.success(Body.MsgDes);
                currentPage=0;
                condition = '';
                treeID="";
                loaddata();
            });
        }else {

            //var list4=getRowData("list4");
            var TaskListDetailInfos=[];
            for(var i in list4){

                delete list4[i].addid;
                var TaskListDetailInfo={
                    "TaskDetailRd":list4[i].TaskDetailRd,
                    "TaskItemName":list4[i].TaskItemName,
                    "SureType":list4[i].SureType=="打勾"?"00":"01",
                    "MaxExCount":list4[i].MaxExCount,
                }
                TaskListDetailInfos.push(TaskListDetailInfo);
            }


            var data={
                "TaskRd":treeID,
                "TaskName":$("#taskName").val().trim(),
                "Description":$("#decription").val(),
                "Status":$("#status option:selected").val(),
                "TaskType":$("#rwlx").val(),
                "Remark":$("#beizhu").val(),
                "TaskDetailInfo":TaskListDetailInfos
            }

            request({url:"/Task/SaveTaskInfo", data: {"ExecType": "02", "busData": JSON.stringify(data)}},function(Body){
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
                request({url:"/Task/SaveTaskInfo", data: {"ExecType": "01", "busData": "{\"TaskRd\":" + treeID + "}"}},function(Body){
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

    //弹出框保存
    $("#savadetail").click(function () {
        if($("#TaskItemName").val().trim()==null||""==$("#TaskItemName").val().trim()){
            toastr.warning("保存失败，任务描述不能为空");
            return false;
        }
        if($("#qrfs").val()==null||""==$("#qrfs").val()){
            toastr.warning("保存失败，确认方式不能为空");
            return false;
        }

        if($("#max").val().trim()==null||""==$("#max").val().trim()){
            toastr.warning("保存失败，最大执行次数不能为空");
            return false;
        }
        $('#myModal').modal('hide');
        if(resultFlag){
            var list4=getRowData("list4");
            for(var i in list4){
                if(list4[i].addid==tableaddid){

                    //if($("#opertype option:selected").text()=="是/否"){
                        list4[i].TaskDatialRd=$("#hidden").val();
                        list4[i].TaskItemName=$("#TaskItemName").val().trim();
                        list4[i].SureType=$("#qrfs").val()=="00"?"打勾":"记录结果";
                        list4[i].MaxExCount=$("#max").val().trim();
                        list4[i].addid=tableaddid;
                    //}
                }
            }
            var config1 = {
                tableId: 'list4',
                data:list4,
                colArr: colNamesArr,
                multiselect: true,
                width: 0.84,
                height:0.36
            };
            fullTable(config1);
            resultFlag=false;
        }else {
            var data={
            "TaskDatialRd":"",
            "TaskItemName":$("#TaskItemName").val().trim(),
            "SureType":$("#qrfs").val()=="00"?"打勾":"记录结果",
            "MaxExCount":$("#max").val().trim(),
           "addid":addid++,
        }
            addSrow("list4", data, "first");

        }

        trDBLClick();
    });
    //表格删除
    $(".del1").click(function(){
        derow("list4");
    });
    //表格新增
    $(".add1").click(function () {
        var list4=getRowData("list4");
        $("#TaskItemName").val("");
        $("#qrfs").val("00");
        $("#min").val(1);
        $("#max").val(1);
    });
    //操作类型下拉框
    $("#opertype").change(function(){
        if($(this).val()=="00"){
            $("#sjcj").hide();
            $("#defaultSelect").clearseldata("DcRd");
        }else {
            $("#sjcj").show();
        }
    });


    //对表格的事件处理 编辑的时候
    trDBLClick();
    function trDBLClick() {
        $("#list4").find("tbody").on("dblclick","tr",function () {
            resultFlag=true;
            var $td= $(this).find('td');

            var tableData={
                "TaskDetailRd":$td[1].innerHTML,
                "TaskItemName":$td[2].innerHTML,
                "SureType":$td[3].innerHTML,

                "MaxExCount":$td[5].innerHTML,
                "addid":$td[6].innerHTML,
            }
            //每次双击的时候给个标识即TaskListDatialRd 主要用来区分编辑
            $("#hidden").val(tableData.TaskDetailRd);
            //console.log(tableData)
            $("#TaskItemName").val(tableData.TaskItemName);
            $("#qrfs").val(tableData.SureType=="记录结果"?"01":"00");

            $("#max").val(tableData.MaxExCount);
            tableaddid=tableData.addid;
            $("#myModal").modal("show");
        })
    }


});