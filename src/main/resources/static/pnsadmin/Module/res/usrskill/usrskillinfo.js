/**
 * Created by test on 2017/8/28.
 */
$(function () {


//加载表单的数据
    var userRd = null;
    var loadTable = function (obj) {
        /*var userName = $(obj).val();
        for (var i in userData) {
            if (userName == userData[i].UserName) {*/
                //userRd = userData[i].UserRd;
                var InitData = {
                    "FiledList": [
                        {
                            "FieldName": "UserRd",
                            "FieldOpt": "=",
                            "FieldVal": obj.getseldata().UserRd
                        }
                    ]
                };
                request({
                    url: "/UsrSkill/GetAllUrSGInfo",
                    data: {"ExecType": "00", "InitData": JSON.stringify(InitData)}
                }, function (Body) {
                    config1.data = Body.Data;
                    fullTable(config1);
                })
            /*}
        }*/


    }
    //技能名称
    var skillNameinfos = [];
    request({url: "/Skill/GetAllSkillInfo", data: {"ExecType": "00"}}, function (Body) {
        var skNameData = Body.Data;
        for (var i in skNameData) {
            var SkillNameinfo = {
                "SkillRd": skNameData[i].SkillRd,
                "SkillName": skNameData[i].SkillName
            }
            skillNameinfos.push(SkillNameinfo);
        }
    })
    var colNamesArr = [
        {"Caption": "培训人员", "Name": "RealName", 'CType': "text", 'Editable': false},
        {
            "Caption": "技能",
            "Name": "SkillInfo",
            'CType': "select",
            'Editable': true,
            "SelectPr": {"Data": skillNameinfos, "DisplayName": "SkillName", "DataValue": "SkillRd"}
        },
        {"Caption": "开始日期", "Name": "SVPDate", 'CType': "text", 'Editable': false},
        {"Caption": "过期日期", "Name": "EVPDate", 'CType': "text", 'Editable': false},
        {"Caption": "是否通过", "Name": "IsPass", 'CType': "checkbox", 'Editable': true},
    ]
    var config1 = {
        tableId: "list4",
        data: [],
        colArr: colNamesArr,
        multiselect: true,
        width: 0.97,
        height:0.63
    };
    fullTable(config1);
//加载表单里面的培训人员下拉列表
   /* var userData = [];
    var usenamelist = function () {

            //设备状态模型
            /!*userData = Body.Data;
            var RealName = $("#RealName");
            RealName.html("");
            var selectStr = null;
            for (var i = 0; i < Body.Data.length; i++) {
                request({url: "/User/GetUserInfo", data: {"ExecType":"00", "BusData": JSON.stringify({"UserRd": Body.Data[i].UserRd})}},
                    function (Body) {
                        realName =
                            userData[i].UserName = Body.Data.RealName;
                        selectStr += "<option label='" + Body.Data.UserRd + "' value='" + Body.Data.RealName + "'/>";
                    });
            }
            RealName.append(selectStr);
            $('#RealNames').val(RealName.find("option:eq(0)").attr("value"));
            userRd = RealName.find("option:eq(0)").attr("label");*!/
            //loadTable($('#RealName'));
        })
    }*/

    var params2 = {
        "displaymode": "0",
        "title": "培训人员",
        "binddata": {
            "keyfield": "UserRd",
            "fields": [
                {
                    "caption": "人员ID",
                    "name": "UserRd"
                }, {
                    "caption": "真实姓名",
                    "name": "RealName"
                }
            ]},
        "showresult": { "ishead": true},
        "event":{
            "onclick":function (res) {
                loadTable($("#RealName"));
            },
            "onseardata": function (o) {
                var InitData = {
                    "FiledList":[
                        {
                            "FieldName":"RealName",
                            "FieldOpt":"like",
                            "FieldVal":"%" + excludeSpecial(o.condition) + "%"
                        }
                    ]
                };
                var page = {
                    PageIndex: "0",
                    PageSize: o.num
                };
                var xldata = [];
                var objUserName = {
                    data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}, url: "/User/GetAllUserInfo"
                }
                request(objUserName, function (Body) {
                    var datas = Body.Data;

                    for (var i = 0; i < datas.length; i++) {
                        var data = {
                            "UserRd": datas[i].UserRd,
                            "RealName": datas[i].RealName,
                        }
                        xldata.push(data);
                    }
                });
                var obj = {
                    data: xldata,
                    showrow: 500
                };
                return obj;
            }
        }
    }
    $("#RealName").zc_select(params2);

    //处理日期


    //选中表单下拉值加载获取培训人员值
    //先选中后新增【禁用掉新增和删除】
    var add1 = $('#add1')


    /*$('#RealNames').on({
        'keyup': function () {
            loadTable(this);
        },
        'blur': function () {
            loadTable(this);
        }
    })*/

//点击新增，添加数据
    add1.on('click', function () {
        addErow('list4');
        $('.RealName').each(function () {
            $(this).html($('#RealName').getseldata().RealName);
        })
        var $this = $('#list4 tr:eq(1)').find('.SVPDate');
        if ($this.val() == '') {
            $this.html('<input type="date" /> ');

        } else {
            $this.html('<input type="date" class="startDate" value="' + $this.text() + '"/> ');
        }
        $this.find('input').css({'border': '0px', 'height': '19px', "color": "#000000"});

        $this.find('input').on("blur", function () {
            var dateTime = $(this).val();
            //alert(dateTime)
            var skillObj = $(this).parent().prev().find("select");
            var selectVal = skillObj.find("option:selected").text();
            for (var i in skillNameinfos) {
                if (selectVal == skillNameinfos[i].SkillName) {
                    var year = dateTime.substr(0, 4);
                    var month = dateTime.substr(5, 2);
                    var day = dateTime.substr(8, 3);
                    request({
                        url: "/Skill/GetSkillInfo",
                        data: {"ExecType": "00", "BusData": JSON.stringify({"SkillRd": skillNameinfos[i].SkillRd})}
                    }, function (Body) {
                        var lastDate = Body.Data;
                        month = parseInt(month) + parseInt(lastDate.VPDate);
                        if (month > 12) {
                            year = parseInt(year) + parseInt(month / 12);
                            month = parseInt(month % 12);
                        }
                        if(parseInt(month) < 10)
                            month = "0"+month;
                    });
                    $(this).parent().next().html(year + "-" + month + "-" + day);
                    return;
                }
            }
        })
    })
//点击删除，删掉数据
    $('#del1').on('click', function () {
        //判断是否是选中状态，如果是的话就删除，如果不是的话就提示请选择一项要删除的数据进行删除
        var selectedRowIds = $("#list4").jqGrid("getGridParam", "selarrrow");
        var gr = 0;
        if (selectedRowIds != undefined && selectedRowIds != null)
            gr = selectedRowIds.length;
        if (gr > 0) {
            derow('list4');
        } else {
            toastr.warning("请选择要删除的数据再进行删除！")
        }

    })
//点击保存，处理新增的数据
    $('.cSave').on('click', function () {

        var list4Data = getRowData("list4");
        var tableData = getTableData("list4");
       var UserRd=$("#RealName").getseldata().UserRd;
       if(UserRd==""||UserRd==null){
           toastr.warning("培训人员不能为空！");
           return;
       }
        //组装数据
        var tabData = [];
        for (var i in list4Data) {
            var datas = {
                "UserRd": UserRd,
                "SkillRd": list4Data[i].SkillRd,
                "SVPDate": tableData[i].SVPDate,
                "EVPDate": tableData[i].EVPDate,
                "IsPass": list4Data[i].IsPass
            }
            tabData.push(datas);
        }
        //编辑
        request({
            url: "/UsrSkill/SaveUrSGInfo",
            data: {"ExecType": "02", "busData": JSON.stringify(tabData)}
        }, function (Body) {
            toastr.success(Body.MsgDes);
        })

    })
})
