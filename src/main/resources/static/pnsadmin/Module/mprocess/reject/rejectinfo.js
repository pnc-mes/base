$(function () {

    var map = new Map()//设备名称
    var map1=new Map();//单位
    var listArr=[];//存放数据库初始化的不良标记

    //表格
    var tableArr=[
        {"Caption":"批次","Name": "Batch" },
        {"Caption":"不良工序","Name":"SpecName"},
        {"Caption":"不良原因","Name":"ReaDes"},
        {"Caption":"产品/物料","Name":"MaName"},
        {"Caption":"批次数量","Name":"Num"},
        {"Caption":"不良数量","Name":"BadNum"},
        {"Caption":"单位","Name":"UnitName"}
    ];
    var objTable={
        data:[],
        colArr:tableArr,
        tableId:"list",
        multiselect:true,
        width:0.84,
        height:0.528
    };
    fullTable(objTable);

//WO2018071800027
    //回车查询
  $(document).keydown(function (event) {
      //输入批次号产生联动
      if(event.keyCode==13){
          if($("#Batch").is(":focus")){
              var batch = $("#Batch").val().trim();
              //调取批次查询工单接口
              request({
                  url: "/Commom/GetCMBInfo",
                  data: {"ExecType": "00", "BusData": JSON.stringify({"Batch":batch})}
              },function (Body) {
                  if(Body.Data==undefined){
                      toastr.warning("该批次信息不存在!");
                      return false
                  }else if(Body.Data.WoCode==undefined||Body.Data.WoCode==''){
                      toastr.warning("该批次未建立工单");
                      return false
                  }else{

                      //给工单 产品名称/料单/数量赋值
                      $("#WoCode").val(Body.Data.WoCode);
                      $("#MaName").val(Body.Data.MaName);
                      $("#Num").val(Body.Data.Num);
                      //通过工单信息调取获取工单相关信息公共接口
                      var WoCode=Body.Data.WoCode;
                      request({
                          url:"/Commom/GetCMWInfo",
                          data:{"ExecType":"00","BusData":JSON.stringify({"WoCode":WoCode})}
                      },function (Body1) {
                          $("#SpecName").empty();
                         var specinfo= Body1.Data.WFInfo.SpecInfo;
                          if(specinfo!=undefined){
                              Body1.Data.WFInfo.SpecInfo.forEach(function (obj) {

                                  //给不良工序下拉框赋值
                                  $("#SpecName") .append("<option id='"+obj.SpecVerRd+"' value='" + obj.SpecVerRd + "'>" + obj.SpecName +"</option>");
                                  var devginfo = obj.DevGInfo;

                                  if(devginfo != undefined){
                                      //以不良工序id作为KEY值,设备信息作为value值保存到map对象 方便产生联动

                                      map.set(String(obj.SpecVerRd), devginfo);

                                  }

                                  var unitInfo=Body1.Data.UnitInfo;

                                  if(unitInfo!=undefined){
                                      //以不良工序id作为KEY值,单位信息作为value值 方便保存
                                      map1.set(String(obj.SpecVerRd),unitInfo);
                                  }

                              });

                             loadDev(specinfo[0].DevGInfo); //默认加载第一个
                         }

                      });
                  }
              });
              //获取不良原因
              request({
                  url: "/Reason/GetAllReaInfo",
                  data:{"ExecType":"00"},
              },function (Body2) {
                  //alert(JSON.stringify(Body2.Data));
                  var reject=Body2.Data;

                  if(reject!=null){
                      Body2.Data.forEach(function (obj) {
                          $("#ReaDes").append("<option value='"+obj.ReaRd+"'>"+obj.ReaDes+"</option>");
                      })
                  }

              });
              request({
                  url:"/Reject/GetAllRejectsInfo",
                  data:{"ExecType":"00","BusData":JSON.stringify({"Batch":batch})},
              },function (Body3) {
                  var rejectDate=Body3.Data;
                  if(rejectDate.length>0){
                      rejectDate.forEach(function (o) {

                          var tableData={
                              Batch:o.Batch,
                              SpecName:o.SpecName,
                              ReaDes:o.ReaDes,
                              MaName:o.MaName,
                              Num:o.Num,
                              BadNum:o.BadNum,
                              UnitName:o.UnitName
                          };
                          var objTable={
                              data:rejectDate,
                              colArr:tableArr,
                              tableId:"list",
                              multiselect:true,
                              width:0.84,
                              height:0.528
                          };
                          fullTable(objTable);
                          listArr.push(tableData)
                      });

                 }else {
                      var objTable1={
                          data:[],
                          colArr:tableArr,
                          tableId:"list",
                          multiselect:true,
                          width:0.84,
                          height:0.528
                      };
                      fullTable(objTable1);
                  }
              });

          }

      };



  });
  //点击加载设备
   /* $("#DevName").click(function () {
        $("#DevName").empty();
        var id=$("#SpecName").find("option:selected").val();
        var Dev=map.get(id);
        Dev.forEach(function (obj) {
            $("#DevName").append("<option value='" + obj.DevRd + "'>" + obj.DevName + "</option>")
        });
    });*/
  //加载设备
    function loadDev(data) {
        $("#DevName").empty();
        data.forEach(function(obj){

            $("#DevName").append("<option value='" + obj.DevRd + "'>" + obj.DevName + "</option>")
        });
    }

    //工序切换事件
    $("#SpecName").change(function(){

        swSpecData($(this).val());
    });
    //工序切换数据处理
    function swSpecData(key){
        $("#DevName").empty();
        var dev=map.get(key);
        if(dev == undefined){
            dev = [];
        }

        loadDev(dev);

    }
    //点击增加给table加数据
    $("#add").click(function () {
        var SpecName=$("#SpecName").find("option:selected").text();
        var batch1=$("#Batch").val().trim();
        var reades=$("#ReaDes").find("option:selected").text();
        var maName=$("#MaName").val().trim();
        var num=$("#Num").val().trim();
        var badNum=$("#BadNum").val().trim();
        if(badNum==undefined||badNum==0||badNum==""){
            toastr.warning("请输入不良数量!") ;
            return false
        }

        if((badNum+0)>(num+0)){
            toastr.warning("不良数量过大请更改!");
            return false
        }

        var id=$("#SpecName").val();
        var unitInfo1=map1.get(id);
        var table={
            Batch:batch1,
            SpecName:SpecName,
            ReaDes:reades,
            MaName:maName,
            Num:num,
            BadNum:badNum,
            UnitName:unitInfo1.UnitName
        };
        var dataAll=getRowData("list");
        var flag=true;

        dataAll.forEach(function (arr) {
            if(arr.Batch==table.Batch&&arr.SpecName==table.SpecName&&arr.ReaDes==table.ReaDes){
                toastr.warning("请勿重复增加同批次，同工序，同原因的不良信息");
                flag=false;
                return false;
            }
        });
        if(flag){
          /* rejectDate.push(table);
            var objTable={
                data:rejectDate,
                colArr:tableArr,
                tableId:"list",
                multiselect:true,
                width:0.84,
                height:0.528
            };
           // fullTable(objTable);*/
          if(listArr.length<=0){
              var table1=[table];
              var objTable={
                  data:table1,
                  colArr:tableArr,
                  tableId:"list",
                  multiselect:true,
                  width:0.84,
                  height:0.528
              }
              fullTable(objTable);
          }else {
            addSrow("list",table);
          }
        }

    });
    //删除事件
    $("#del").click(function () {
        //获取选择的对象
        var dataAll=getSeRowData("list");
        if(dataAll.length<=0){
            toastr.warning("请选择删除项");
            return false;
        };
        //判断选中的信息中是否有已保存过的不良信息
        var fals1=true;
        dataAll.forEach(function (all) {
          listArr.forEach(function (arr) {
              if(all.Batch==arr.Batch&&all.SpecName==arr.SpecName&&all.ReaDes==arr.ReaDes
                  &&all.MaName==arr.MaName&&all.Num==arr.Num &&all.BadNum==arr.BadNum&&all.UnitName==arr.UnitName){
                  toastr.warning("删除只能选择新增的不良信息");
                  fals1=false;
                  return false;
              }
          });
        });
        if(fals1){
            derow("list");
        }
    });

    //保存按钮
    $("._save").click(function(){
        //获取选择的对象
        var dataAll=getSeRowData("list");
        if(dataAll.length<=0){
            toastr.warning("请选择保存项");
            return false;
        };
        //判断选中的信息中是否有已保存过的不良信息
        var fals1=true;
        dataAll.forEach(function (all) {
            listArr.forEach(function (arr) {
                if(all.Batch==arr.Batch&&all.SpecName==arr.SpecName&&all.ReaDes==arr.ReaDes
                    &&all.MaName==arr.MaName&&all.Num==arr.Num &&all.BadNum==arr.BadNum&&all.UnitName==arr.UnitName){
                    toastr.warning("请勿选择已保存过的不良信息");
                    fals1=false;
                    return false;
                }
            });
        });
        if(fals1){
            request({
                url:"/Reject/SaveRejectInfo",
                data:{"ExecType":"00","BusData":JSON.stringify(dataAll)},
            },function (Body) {
                toastr.success("成功");
                dataAll.forEach(function (all) {
                    listArr.push(all);
                });

            });
        }


    });
  });