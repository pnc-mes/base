

$(function () {
   //主页面表格定义
    var colNamesArr = [
        {"Caption": "工单号", "Name": "woCode", "Hidden": false},
        {"Caption": "组件条码", "Name": "batch", "Hidden": false},
        {"Caption": "导电芯板编号", "Name": "cZPCode", 'Editable': false},
        {"Caption": "产品代码", "Name": "maCode", "Editable": false},
        {"Caption": "产品名称", "Name": "maName", "Editable": false},
        {"Caption": "电池产率档", "Name": "eff", "CType": "text", "Hidden": false},
        {"Caption": "电池片等级", "Name": "grade", "CType": "text"},
        {"Caption": "电池片颜色", "Name": "color", "CType": "text"},
        {"Caption": "等级名称", "Name": "gradeName", "Editable": false},
        {"Caption": "不良明细", "Name": "badName"},
        {"Caption": "不良位置", "Name": "locationName", "Hidden": false},
        {"Caption": "物料代码", "Name": "maCode1", "Hidden": false},
        {"Caption": "物料名称", "Name": "maName1", "Hidden": false},
        {"Caption": "BOM信息", "Name": "bomName", "Hidden": false},
        {"Caption": "操作时间", "Name": "createTime", "Hidden": false},
        {"Caption": "操作人", "Name": "creator", "Hidden": false},
    ];
    var config1 = {
        tableId: 'list4',
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.98,
        height: 0.7
    };
    config1.data.length = 0;
    fullTable(config1);//加载空表格



    var date = new Date();
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;
    this.date = date.getDate();
    this.date1 = date.getDate()-1;
    this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

    //时间段
    var dateTime=this.year+"-"+this.month+"-"+this.date1+" "+this.hour+":"+this.minute+":"+this.second+" - "+this.year+"-"+this.month+"-"+this.date+" "+this.hour+":"+this.minute+":"+this.second;

    //默认加载当前的时间，范围为1天
    laydate.render({
        elem: '#test10'
        ,type: 'datetime'
        ,range: true
        ,value:dateTime
    });

    //加载线别，由于线别就几个因此这边的控件就用原生的select即可
    request({
        url: '/Line/GetAllLineInfo',
        data: {
            "ExecType": "00",
        }}, function (Body) {
        var resultData = Body.Data;
        if(resultData.length>0){
            //定义下拉框的option进行拼接
            var option_="";
            for(var i  in  resultData){
                option_+="<option value='"+resultData[i].LineRd+"'>"+resultData[i].LineName+"</option>"
            }
            $("#lineinfo").empty().html(option_);

        }
    });
    //点击查询按钮进行搜索
    $("#select").click(function(){
        //获取站点的信息
        var id=$("#station").find("option:selected").val();
        if(id==null||id==""){
            toastr.warning("站点不能为空");
            return false;
        }
        var lineGd=$("#lineinfo").find("option:selected").val();
        if(lineGd==null||lineGd==""){
            toastr.warning("线别不能为空");
            return false;
        }
        var data=$("#test10").val();
        if(data==""||data==null){
            toastr.warning("时间不能为空");
            return false;
        }
       var createTimeStart=data.split(" - ")[1];
       var createTimeEnd=data.split(" - ")[0];
       //后端ajax 拼接的参数，json形式的
       var data_={
           "id":id,
           "lineGd":lineGd,
           "createTimeStart":createTimeStart,
           "createTimeEnd":createTimeEnd,
           "woGd":[]//
       }
        $.ajax({
            "url": getBasePath() +"/Zdjlcx/getAllCommonPageInfo",
            "type": "post",
            "data": JSON.stringify(data_),
            "contentType": "application/json",
            "dataType": "json",
            "success": function(result){
                console.log(result)
                var config1 = {
                    tableId: 'list4',
                    data: result,
                    colArr: colNamesArr,
                    multiselect: true,
                    width: 0.84,
                    height: 0.415
                };
                fullTable(config1);
            }
        });



    });




});