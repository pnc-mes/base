/**
 * Created by 郝赞 on 2018/12/20.
 * 通用查询js
 */

$(function(){
    var CreateTime =0;
    var CreateTime1 =0;

    var date = new Date();
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;
    this.date = date.getDate();
    this.date1 = date.getDate()-1;
    this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();


   /*laydate.render({
        elem: '#CreateTime'
        ,type: 'datetime'
       ,value:""+this.year+"-"+this.month+"-"+this.date1+" "+this.hour+":"+this.minute+":"+this.second+""
        ,range: true
    });





    laydate.render({
        elem: '#CreateTime1'
        ,type: 'datetime'
        ,value:""+date1=='null'?(this.year+"-"+this.month+"-"+this.date+" "+this.hour+":"+this.minute+":"+this.second):date1+""
    });*/
    $(document).on("click","#dk",function () {
        $("#open").show();
    });
    $(document).on("click","#yc",function () {
        $("#open").hide();
    });

    $("#cx").click(function () {

        //查询div下所有的id和name
        var objs = $("#xlh").find("*");
        var map=new Map();
        var formArr=[];
        var list ={};
        for(var k in objs){
            if(objs[k].id!='undefined'&&objs[k].id!=''&&objs[k].id!=null){
                var keys=objs[k].id;
              //  alert($("#"+keys).val())
                map.set(keys,objs[k].value);
                formArr.push({name:keys,value:objs[k].value})

            }
        }
        for (var item in formArr){
            list[formArr[item].name] = formArr[item].value;
        };




        var cre=$("#CreateTime").val().trim();

     /*   if(cre==""||cre==null){
            toastr.warning("查询时间不能为空!");
            return false;
        }*/
        var time=cre.split(" ");
        var CreateTime_Start="";
        var CreateTime_end="";
        if(time.length>3){
            CreateTime_Start=time[0]+" "+time[1];
            CreateTime_end=time[time.length-2]+" "+time[time.length-1];
        }else {
            CreateTime_Start=time[0];
            CreateTime_end=time[time.length-1];
        }

        var sta_date=CreateTime_Start.replace('-',"/");
        var end_date=CreateTime_end.replace('-',"/");
        var num = (new Date(end_date)-new Date(sta_date))/(1000*3600*24);//求出两个时间的时间差，这个是天数
        if(num>7){
          //  toastr.warning("查询时间请勿超出一周,周期较长建议分批查询或分批导出!");
        //    return false;
        }

        //form 表单提交
        /*   document.forms['addForm'].action=action;
         document.getElementById('addForm').submit();*/

        //ajax提交
        $.ajax({
            type:"post",
            url:getBasePath()+"/Sys/tycxzxTable",
            data: list,
            beforeSend: function() { //ajax进度条
                $("#circle1").circleChart()
                $("#myModal").modal("show")
                //进度条完成率
                $("#circle1").circleChart({value:50});

            },
            success: function(Body){
                $("#circle1").circleChart({value:75});

                var data=Body.Body.Data;
                if(data!=null&&data!=''&&data!=undefined){

                    //渲染表头
                    var head=data.tableHead;
                    var headArr=[];
                    var headText=[];

                    for(var key in  head){
                        headArr.push(key);
                        headText.push(head[key])
                    }


                    //加载表内容
                    var body=data.tableBody;
                    //表内容的样式和显示信息
                    var tableType=data.tableType;


                    var tableBody="";
                    //循环表内容
                    body.forEach(function (e) {

                        tableBody+="<tr >"
                        tableType.forEach(function (t) {

                            //显示
                            if(t.isDisplay=="00"){
                                //文本
                                if(t.columnType=="00"){
                                    //截断
                                    if(t.truncated=="00"){
                                        tableBody+="<td style='text-align: center; width:"+t.columnWidth+"px'><div title='"+e[t.columnName]+"' style='width:"+t.columnWidth+"px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>&emsp;"+e[t.columnName]+"&emsp;</div></td>";

                                    }
                                    //不截断
                                    if(t.truncated=="01"){
                                        tableBody+="<td style='text-align: center;width:"+t.columnWidth+"px'>&emsp;"+e[t.columnName]+"&emsp;</td>";

                                    }

                                }
                                //链接
                                if(t.columnType=="01"){
                                    //截断
                                    if(t.truncated=="00"){
                                        tableBody+="<td style='text-align: center;width:"+t.columnWidth+"px'><div title='"+e[t.columnName]+"' style='width:"+t.columnWidth+"px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>&emsp;<a href='"+e[t.columnName]+"' target='_Blank'></a>&emsp;</div></td>";

                                    }


                                    //不截断
                                    if(t.truncated=="01"){
                                        tableBody+="<td style='text-align: center;width:"+t.columnWidth+"px'>&emsp;<a href='"+e[t.columnName]+"' target='_Blank'>点击我</a>&emsp;</td>";

                                    }
                                }

                            }
                            //不显示
                            if(t.isDisplay=="01"){
                                //文本
                                if(t.columnType=="00"){
                                    //截断
                                    if(t.truncated=="00"){
                                        tableBody+="<td style='text-align: center;display: none;width:"+t.columnWidth+"px;'><div title='"+e[t.columnName]+"' style='width:"+t.columnWidth+"px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>&emsp;"+e[t.columnName]+"&emsp;</div></td>";

                                    }
                                    //不截断
                                    if(t.truncated=="01"){
                                        tableBody+="<td style='text-align: center;display: none;width:"+t.columnWidth+"px'>&emsp;"+e[t.columnName]+"&emsp;</td>";

                                    }

                                }
                                //链接
                                if(t.columnType=="01"){
                                    //截断
                                    if(t.truncated=="00"){
                                        tableBody+="<td style='text-align: center;display: none;width:"+t.columnWidth+"px'><div title='"+e[t.columnName]+"' style='width:"+t.columnWidth+"px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>&emsp;<a href='"+e[t.columnName]+"' target='_Blank'>点击我</a>&emsp;</div></td>";

                                    }


                                    //不截断
                                    if(t.truncated=="01"){
                                        tableBody+="<td style='text-align: center;display: none;width:"+t.columnWidth+"px'>&emsp;<a href='"+e[t.columnName]+"' target='_Blank'>点击我</a>&emsp;</td>";

                                    }
                                }
                               // tableBody+="<td style='align-content: center;display: none;'><div title='"+e[t.aliasName]+"' style='width:"+t.ColumnWidth+"px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;'>&emsp;"+e[t.aliasName]+"&emsp;</div></td>";

                            }


                        });
                        tableBody+="</tr>"

                    });

                    $("#tableBody").html("");
                    $("#tableBody").append(tableBody);

                    //往统计里加数据
                    $("#tjnum").html("")
                    $("#tjnum").append("<span style='margin-top: 10px;'>当前记录条数&ensp;<label style='font-weight: 600'>【"+body.length+"条】</label></span>")

                    //后太返回的统计信息
                    var tableTop=data.tableTop;
                    if(tableTop!=null&&tableTop.length>0){

                        tableTop.forEach(function (e) {
                            for(var top in e){
                                $("#tjnum").append("<span style='margin-top: 10px;margin-left: 10px;'>"+top+"&ensp;<label style='font-weight: 600'>【"+e[top]+"条】</label></span>")

                            }
                        })

                    }
                    $("#circle1").circleChart({value:99});

                }else {
                    toastr.warning(Body.Body.MsgDes);

                }
            },
            complete:function () {
              //  $("#circle1").circleChart({value:100});
                $("#circle1").circleChart({value:0});
                $("#myModal").modal("hide")
            },
            error:function(retMsg){
                $("#circle1").circleChart({value:0});
                $("#myModal").modal("hide")
            }


        });

    });

    $("#dc").click(function () {
        var action=getBasePath()+"/Sys/getExcel";
        document.forms['addForm'].action=action;
        document.forms['addForm'].method='post';

        document.getElementById('addForm').submit();


        //ajax请求
      /*  $.ajax({
            type:"get",
            url:"/mesadmin/Sys/getExcel",
            data: $("#addForm").serialize(),
            dataType:"JSON",
            beforeSend: function() { //ajax进度条
                console.log("请求前...")
                $("#myModal").modal("show")
                //进度条完成率
                $("#circle1").circleChart({value:50});
            },
            success: function(Body){
                alert(JSON.stringify(Body))
                $("#circle1").circleChart({value:99});
            },
            complete:function () {
                $("#circle1").circleChart({value:100});
                $("#myModal").modal("hide")
            }
        });;*/

    });



});