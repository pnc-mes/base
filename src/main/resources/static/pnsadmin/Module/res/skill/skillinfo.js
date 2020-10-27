/**
 * Created by test on 2017/8/15.
 */
$(function () {
    $('#_right').hide();
    var treeID = null;
    var rule = [{
        //等级批次名称
        "ctlid": "skillName", //自定义名字：标签id名字
        "param": "SkillName" //规则中自定义的名字：对应报文中的id字段
    }, {
        //创建人
        "ctlid": "creatPeople",
        "param": "Creator"
    }, {
        //创建时间
        "ctlid": "creatTime",
        "param": "CreateTime"
    }, {
        //修改人
        "ctlid": "modifyPeople",
        "param": "LastModifyMan"
    }, {
        //修改时间
        "ctlid": "modifyTime",
        "param": "LastModifyTime"
    }, {
        //备注
        "ctlid": "beizhu",
        "param": "Remark"
    }];
    /*-----------2:点击树的节点的时候获取技能信息----------------------------*/
    var onClicks = function (nodeinfo, handle) {
        //alert(nodeinfo.nodeID);
        $('#_right').show();
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"SkillRd": treeID})
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        }
        request({url: "/Skill/GetSkillInfo", data: objData}, function (Body) {
            fillform("skillform", rule, Body.Data);
            //获取相应的数据的日期
            var d = Body.Data.VPDate;
            $('#VPDate').val(d);


            $("#Status").find("option").each(function () {
                if($(this).val() == Body.Data.Status){
                    $(this).prop("selected",true);
                    $(this).siblings().prop("selected",false);
                    return false;
                }
            });
            delMake();
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
        currentPage = treeSearchs("/Skill/GetAllSkillInfo","SkillRd","SkillName","SkillName",condition,currentPage,config);
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
            currentPage = treeSearchs("/Skill/GetAllSkillInfo","SkillRd","SkillName","SkillName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/Skill/GetAllSkillInfo","SkillRd","SkillName","SkillName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/Skill/GetAllSkillInfo","SkillRd","SkillName","SkillName",condition,currentPage,config);
    });

    /*-------- 4:只刷新树(提高性能)---------*/
    var loadtree = function () {
        var trees = [];
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"SkillName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        request({url:'/Skill/GetAllSkillInfo', data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}},function(Body){
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
                    id: treeData[i].SkillRd == undefined ? "" : treeData[i].SkillRd,
                    name: treeData[i].SkillName == undefined ? "" : treeData[i].SkillName
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadtree();

    /*------------3:新增删除编辑复制之后进行数据保存---------------------------*/
    //新增
    $('.cAdd').on('click', function () {
        $('#_right').show();
        clearForm("skillform");
        treeID = null;
        $('#ExecType').val('00');
    })
    //删除
    $('.cDel').on('click', function () {
        if (treeID != undefined && treeID != null) {
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消']
            }, function () {
                request({
                    url: "/Skill/SaveSkillInfo",
                    data: {"ExecType": "01", "busData": "{\"SkillRd\":" + treeID + "}"}
                }, function (Body) {
                    layer.closeAll('dialog');
                    toastr.success(Body.MsgDes);
                    condition = "";
                    currentPage = 0;
                    loadtree();
                    treeID = null;
                    $('#_right').hide();
                    $('#ExceType').val("");
                    delMake();
                })
            })
        } else {
            toastr.warning('请选择左侧要删除的一项再进行删除!')
        }
    })
    //复制
    $('.copy').on('click', function () {
        if (treeID != undefined && treeID != null) {
            request({url:"/Skill/SaveSkillInfo", data: {"ExecType": "03", "busData": "{\"SkillRd\":" + treeID + "}"}},function(Body){
                treeID = null;
                $('#ExecType').val();
                condition = "";
                currentPage = 0;
                loadtree();
                toastr.success(Body.MsgDes);
            })
        }else{
            toastr.warning("请选择左侧要复制的一项再进行复制！")
        }
    })
    //保存【编辑】
    var newData = {};
    $('.cSave').on('click',function(){
        var r = /^\+?[1-9][0-9]*$/;　　//正整数
        var str = $("#VPDate").val().trim();
       var flag=r.test(str);
        if(flag==false){
            toastr.warning("输入有误，月份为正整数！")
            return false;
        }
        formData = transfer("skillform");

        var status = $("#Status").val();
        if($('#skillName').val().trim() != '' && $('#VPDate').val() != '' ){
            //将新增的数据按格式保存传给后端
            if((treeID == null || treeID == '') && $('#ExecType').val() == '00'){
                newData = {
                    "SkillName":formData.skillName,
                    "VPDate":formData.VPDate,
                    "Status":status,
                    "Remark":formData["remark"]
                };
                request({url:"/Skill/SaveSkillInfo",data: {"ExecType": $("#ExecType").val(), "busData": JSON.stringify(newData)}},function(Body){
                    condition = "";
                    currentPage = 0;
                    loadtree();
                    $('#ExecType').val('');
                    toastr.success(Body.MsgDes);
                    delMake();
                });
            }
            //编辑
            else if(treeID != null && treeID != ''){
                newData = {
                    "SkillRd":treeID,
                    "SkillName":formData.skillName,
                    "VPDate":formData.VPDate,
                    "Status":status,
                    "Remark":formData["remark"]
                }
                request({url:"/Skill/SaveSkillInfo",data: {"ExecType": "02", "busData": JSON.stringify(newData)}},function(Body){
                    condition = "";
                    currentPage = 0;
                    loadtree();
                    $('#Excetype').val('');
                    treeID = null;
                    toastr.success(Body.MsgDes);
                    delMake();
                })
            }

        }else{
            $('#skillName').prop('placeholder','不能为空').css('border-color','red');
            $('#VPDate').prop('placeholder','不能为空').css('border-color','red');
            $('#Status').prop('placeholder','不能为空').css('border-color','red');
        }
    })
    var delMake = function(){
        $('#skillName').prop('placeholder','').css('border-color','');
        $('#VPDate').prop('placeholder','').css('border-color','');
        $('#Status').prop('placeholder','').css('border-color','');
    }

})































