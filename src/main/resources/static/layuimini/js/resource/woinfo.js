layui.use(['form', 'table', 'laydate', 'element','formSelects',], function () {
    let $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        formSelects=layui.formSelects,
        laydate = layui.laydate;

    //时间
    laydate.render({
        elem: '#date',
    });

    //页面首次加载
    load();

    //toolbar监听事件
    table.on('toolbar(currentTableFilter)', function (obj) {
        if (obj.event === 'add') {  // 监听添加操作
            clear();
            let index = layer.open({
                title: '添加班别',
                type: 1,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['50%', '70%'],
                content: $("#content"),//'factory',
                btn: ['确认保存'],
                yes: function (index, layero) {
                    save(index, 1);
                }
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        }
    });

    //表格中按钮事件
    table.on('tool(currentTableFilter)', function (obj) {
        let data = obj.data;
        if (obj.event === 'edit') {
            //获取选中id
            let shiftId = data.ShiftRd;
            requests({url: "/Shift/GetShiftInfo", contentType: "application/x-www-form-urlencoded", data: {"ExecType": "00",
                    "BusData": JSON.stringify({"ShiftRd": shiftId})
                }}, function (res) {
                let data = res.Data;

                clear();

                $("#name").val(data.ShiftName);
                $("#des").val(data.ShiftDes);
                $("#startTime").val(data.StartTime);
                $("#endTime").val(data.EndTime);
                $("#remark").val(data.Remark);

                let index = layer.open({
                    title: '编辑班别',
                    type: 1,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['50%', '70%'],
                    content: $("#content"),
                    btn: ['确认保存'],
                    yes: function (index, layero) {
                        save(index, 2, shiftId);
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            });

            return false;
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除行么', function (index) {
                requests({url: "/Shift/SaveShiftInfo", contentType: "application/x-www-form-urlencoded", data: {"ExecType": "01",
                        "BusData": JSON.stringify({ShiftRd: data.ShiftRd})
                    }}, function (res) {
                    //obj.del();
                    layer.msg("删除成功", {icon: 1});
                    table.reload('currentTableId');
                });

                layer.close(index);
            });
        } else if(obj.event === 'read') {
            read(data);

            return false;
        }
    });

    //监听行双击事件
    table.on('rowDouble(currentTableFilter)', function(obj){
        read(obj.data);
        return false;
    });

    //监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        // console.log('--------------');

        //执行搜索重载
        //alert($("#date").val())
        table.reload('currentTableId', {
            page: {
                curr: 1
            }
            , where: {
                fields: [
                    {
                        fieldName: "woCode",
                        fieldOpt: "like",
                        fieldVal: data.field.woName
                    },
                    {
                        fieldName: "Ruid",
                        fieldOpt: "Order BY",
                        fieldVal: ""
                    },
                    {
                        fieldName: "maverGd",
                        fieldOpt: "=",
                        fieldVal: data.field.materialRd
                    }
                ]
            }
        }, 'data');

        return false;
    });

    //查看
    function read(data) {
        console.log(data);
        /* //获取选中id
         let shiftId = data.ShiftRd;
         requests({url: "/Shift/GetShiftInfo", contentType: "application/x-www-form-urlencoded", data: {"ExecType": "00",
                 "BusData": JSON.stringify({"ShiftRd": shiftId})
             }}, function (res) {
             let data = res.Data;

             clear();

             $("#name_").val(data.ShiftName);
             $("#des_").val(data.ShiftDes);
             $("#startTime_").val(data.StartTime);
             $("#endTime_").val(data.EndTime);
             $("#remark_").val(data.Remark);

             $("#creator").val(data.Creator);
             $("#createTime").val(data.CreateTime);
             $("#lastModifyMan").val(data.LastModifyMan);
             $("#lastModifyTime").val(data.LastModifyTime);

             let index = layer.open({
                 title: '查看工单',
                 type: 1,
                 shade: 0.2,
                 maxmin: true,
                 shadeClose: true,
                 area: ['50%', '70%'],
                 content: $("#readContent"),
             });
             $(window).on("resize", function () {
                 layer.full(index);
             });
         });*/
    }

    //保存
    function save(index, type, shiftId) {
        //type-> 1.新增 2.编辑 3.删除
        let data = {
            ShiftName: $("#name").val(),
            ShiftDes: $("#des").val(),
            StartTime: $("#startTime").val(),
            EndTime: $("#endTime").val(),
            Remark: $("#remark").val()
        };
        if(type === 1){
            requests({url: "/Shift/SaveShiftInfo", contentType: "application/x-www-form-urlencoded", data: {"ExecType": "00",
                    "BusData": JSON.stringify(data)
                }}, function (res) {
                layer.msg("新增成功", {icon: 1});
                layer.close(index);
                table.reload('currentTableId');
            });
        }else if(type === 2){
            data.ShiftRd = shiftId;
            requests({url: "/Shift/SaveShiftInfo", contentType: "application/x-www-form-urlencoded", data: {"ExecType": "02",
                    "BusData": JSON.stringify(data)
                }}, function (res) {
                layer.msg("保存成功", {icon: 1});
                layer.close(index);
                table.reload('currentTableId');
            });
        }
    }

    //初始化表格数据
    function load() {
        table.render({
            elem: '#currentTableId',
            url: getBasePath() + "/WO/GetAllWOInfo",
            method: 'post',
            contentType: 'application/json',
            toolbar: '#toolbar',
            where: {
                fields: [{
                    fieldName: "Ruid",
                    fieldOpt: "Order BY",
                    fieldVal: ""
                }]
            },
            defaultToolbar: ['filter', 'exports', 'print'],
            cols: [
                [
                    {field: 'woRd', title: 'id', sort: true},
                    {field: 'woCode', title: '工单名称'},
                    {field: 'materialCode', title: '产品代码'},
                    {field: 'materialName', title: '产品名称'},
                    {field: 'woTName', title: '工单类型'},
                    {field: 'maverGd', title: '物料的Gd',hide:true},
                    {field: 'urcyDes', title: '紧急度'},
                    {field: 'lineInfo', title: '线体名称',templet:'#lineInfo'},
                    {field: 'wfName', title: '工艺名称'},
                    {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
                ]
            ],
            request: {
                pageName: 'current',
                limitName: 'size'
            },
            parseData: function(res){
                return {
                    code: res.status,
                    msg: res.body.msgDes,
                    count: res.body.data.total,
                    data: res.body.data.list
                }
            },
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'row '
        });

    }

    //清空
    function clear() {
        $("#name").val("");
        $("#des").val("");
        $("#startTime").val("");
        $("#endTime").val("");
        $("#remark").val("");

        $("#name_").val("");
        $("#des_").val("");
        $("#startTime_").val("");
        $("#endTime_").val("");
        $("#remark_").val("");

        $("#creator").val("");
        $("#createTime").val("");
        $("#lastModifyMan").val("");
        $("#lastModifyTime").val("");
    }

    //加载所有产品信息
    function getAllMaterial(){

        var fields=[];
        fields.push({
            fieldName:'MaterialType',
            fieldOpt:'in',
            fieldVal:'00,01'
        })
        var urlData={
            fields
        }
        requests({url:'/Material/GetAllMaNew', contentType: "application/json; charset=utf-8", data: JSON.stringify(urlData)}, function (res) {
            var data=res.data.list;
            var selectData='<option selected></option>';
            if(data.length>0){0
                for(var i in data){
                    selectData+="<option value='"+data[i].MaVerGd+"'>"+data[i].MaCode+"</option>"
                }
                $("select[name='materialRd']").empty().html(selectData);
                renderForm()
            }
        });


    }
    getAllMaterial();

    //重新渲染表单函数
    function renderForm() {
        layui.use('form', function() {
            var form = layui.form;
            form.render();
        });
    }

});