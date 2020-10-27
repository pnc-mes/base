/**
 * Created by test on 2017/8/15.
 */
$(function(){
    var rule = [{
        "ctlid": "devGpName", //自定义名字：标签id名字
        "param": "SGName" //规则中自定义的名字：对应报文中的id字段
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

    //获取全部技能信息
    var SkillInfos = [];
    request({url: "/Skill/GetAllSkillInfo", data: {"ExecType": "00"}}, function (data) {
        for (var i in data.Data) {
            var SkillInfo = {
                "SkillRd": data.Data[i].SkillRd,
                "SkillName": data.Data[i].SkillName
            }
            SkillInfos.push(SkillInfo);
        }
    });

    //树点击事件
    var onClicks = function (id, text) {
        $("#_right").show();
        $("#delSkillGd").attr("a", id.nodeID);
        $("#cSave").attr("a", id.nodeID);
        loadSkill(id.nodeID);
    };
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
        currentPage = treeSearchs("/SkillG/GetAllSGInfo","SGRd","SGName","SkillGName",condition,currentPage,config);
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
            currentPage = treeSearchs("/SkillG/GetAllSGInfo","SGRd","SGName","SkillGName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/SkillG/GetAllSGInfo","SGRd","SGName","SkillGName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/SkillG/GetAllSGInfo","SGRd","SGName","SkillGName",condition,currentPage,config);
    });



    //加载指定组的技能组信息
    function loadSkill(SGRd){
        //加载技能信息
        var objBusData = {"SGRd": SGRd};
        request({url:"/SkillG/GetSGInfo",data: {"ExecType": "00", "BusData": JSON.stringify(objBusData)}}, function (Body) {
            var sgData = Body.Data;
            console.log(Body)
            var sgDataSkillInfo = Body.Data.SkillInfo;
            $("#devGpName").val(sgData.SGName);
            fillform("devGpForm", rule, Body.Data);
            var skDatas = [];
            if(sgDataSkillInfo != null){
                //表格定义
                for(var i=0;i<sgDataSkillInfo.length;i++){
                    var _dataCent = {
                        SkillRd: sgDataSkillInfo[i].SkillRd,
                        SkillName:sgDataSkillInfo[i].SkillName
                    };
                    skDatas.push(_dataCent);
                }
            }
            var colNamesArrCent = [
                {"Caption": "SkillRd", "Name": "SkillRd", "Hidden": "Hidden", "CType": "text"},
                {"Caption": "技能名称", "Name": "SkillName","Editable": true, "CType": "select","SelectPr": {
                    "Data": SkillInfos,
                    "DisplayName": "SkillName",
                    "DataValue": "SkillRd"
                },"width":"0.55"},
            ];
            var configCent = {
                tableId: "list4",
                data: skDatas,
                colArr: colNamesArrCent,
                multiselect:true,
                width:0.84,height:0.55
            };
            fullTable(configCent);
            
        });
    }

    //加载树
    function loadTree() {
        var pageInfo = {
            "PageSize":21,
            "PageIndex":0
        };
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"SkillGName",
                    "FieldOpt":"Order BY",
                }
            ]
        }
        var treedataList = [];
        request({url:"/SkillG/GetAllSGInfo",data: {"ExecType": "00","InitData":JSON.stringify(InitData),"PageInfo":JSON.stringify(pageInfo)}}, function (Body) {

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
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].SGRd == undefined ? "" : treeData[i].SGRd,
                    name: treeData[i].SGName == undefined ? "" : treeData[i].SGName
                }
                treedataList.push(tree);
            }
            config.data.source = treedataList;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loadTree();


    var formData = {};
    //新增清除现有信息
    $("#cAdd").on("click",function () {
        $("#cSave").attr("a","");
        $("#devGpName").val("");
        $("#_right").show();
        if($("#cSave").attr("a") != "" && $("#cSave").attr("a") != undefined){
            console.log("00000:"+$("#cSave").attr("a"));
            $("#devGpName").val("");
            deallrow("list4");
        }

        //表格定义
        var colNamesArrCent = [
            {"Caption": "SkillRd", "Name": "SkillRd", "Hidden": "Hidden", "CType": "text"},
            {"Caption": "技能名称", "Name": "SkillName","Editable": true, "CType": "select","SelectPr": {
                "Data": SkillInfos,
                "DisplayName": "SkillName",
                "DataValue": "SkillRd"
            },"width":"0.55"},
        ];
        var config1 = {
            tableId: "list4",
            data: null,
            colArr: colNamesArrCent,
            multiselect: true,
            width:0.84,height:0.55,
            event: {
                oncellchange: function (data) {
                   /* var SkillRd = data.cellvalue;
                    var busData = {
                        "SkillRd": SkillRd
                    };*/
                }
            }
        };
        fullTable(config1);
    });

    //编辑技能组
    function editNewSkillGd(busData){

        var SGRd = $("#cSave").attr("a");
        var  objBusData = {
            "SGRd": SGRd,
            "SGName": busData.SGName,
            "SkillInfo": busData.SkillInfo,
            "Remark": formData["remark"]
        }
        console.log(JSON.stringify(objBusData));
        request({url:"/SkillG/SaveSGInfo",
                data: {"ExecType": "02", "BusData": JSON.stringify(objBusData)}},
            function (Body) {
                toastr.success(Body.MsgDes);
                condition = "";
                currentPage = 0;
                SGRd=null;
                loadTree();
            });
    }

    //新增技能组
    function addNewSkillGd(objBusData){
        console.log(JSON.stringify(objBusData));
        request({url:"/SkillG/SaveSGInfo",
                data: {"ExecType": "00", "BusData": JSON.stringify(objBusData)}},
            function (Body) {
                toastr.success(Body.MsgDes);
                condition = "";
                currentPage = 0;
                loadTree();
            });
    }

    //编辑/新增技能组
    $("#cSave").on("click",function () {
        var SGName = $("#devGpName").val().trim();
        //var remark = $("#beizhu").val();
        var skilList = getRowData("list4");
        if (SGName == ""||SGName==null) {
            toastr.warning("技能组名不能为空")
            return;
        }

        if (skilList.length <= 0) {
            toastr.warning("技能不能为空")
            return;
        }
        if ($("#devGpName").val().trim() != "") {
            formData = transfer("devGpForm");






            for (var i = 0; i < skilList.length; i++) {
                delete skilList[i].SkillName;
            }
            var objBusData = {
                "SGName": SGName,
                "SkillInfo": skilList,
                "Remark": formData["remark"]
            }

            if ($("#cSave").attr("a") == "") {
                addNewSkillGd(objBusData);  //新增技能组
            } else {
                editNewSkillGd(objBusData);  //编辑技能组
            }
        }
    });

    //添加技能行
    $("#add1").on("click",function () {
        addErow("list4");
    });

    //删除技能行
    $("#del1").on("click",function () {
            derow("list4");
    });

    //删除选中技能组信息
    $("#delSkillGd").on("click",function () {
        if($("#cSave").attr("a") != "") {
            var SkillGdRd = $("#delSkillGd").attr("a");
            if (SkillGdRd == "" || SkillGdRd == undefined) {
                toastr.warning("请先选择一个技能组");
            } else {
                layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    var objBusData = {
                        "SGRd": SkillGdRd
                    }
                    request({
                            url: "/SkillG/SaveSGInfo",
                            data: {"ExecType": "01", "BusData": JSON.stringify(objBusData)}
                        },
                        function (Body) {
                            layer.closeAll();
                            deallrow("list4");
                            $("#cSave").attr("a", "");
                            $("#devGpName").val("");
                            $("#_right").hide();
                            condition = "";
                            currentPage = 0;
                            loadTree();
                        });
                });
            }
        }else{
            toastr.warning("请选择左侧一行再进行删除!");
        }
    });

    //复制技能组
    $("#copySkillGd").on("click",function (){
        if($("#cSave").attr("a") != ""){
                var SGRd = $("#cSave").attr("a");
                var objBusData = {
                    "SGRd": SGRd
                }
                request({url:"/SkillG/SaveSGInfo",
                        data: {"ExecType": "03", "BusData": JSON.stringify(objBusData)}},
                    function (Body) {
                        condition = "";
                        currentPage = 0;
                       loadTree();
                        layer.closeAll();
                    });
        }
        else{
            toastr.warning("请选择左侧一行再进行复制!");
        }
    });

});