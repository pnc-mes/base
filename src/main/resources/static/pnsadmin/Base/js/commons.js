/**
 * Created by test on 2017/6/20.
 */
toastr.options = {
    "closeButton": false,
    "debug": true,
    "positionClass": "toast-bottom-full-width",
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};
var storage = window.sessionStorage;

function fillform(formid, rules, data) {
    clearForm(formid);
    if (data == undefined || data == null) {
        return;
    }
    var arr = [];
    //将每个控件类型都添加到数组中，key，ctl都是形参
    var objFormCtrlList1 = $("#" + formid).find("input");
    objFormCtrlList1.each(function (key, ctl) {
        arr.push(ctl);
    });
    var objFormCtrlList2 = $("#" + formid).find("select");
    objFormCtrlList2.each(function (key, ctl) {
        arr.push(ctl);
    });
    var objFormCtrlList3 = $("#" + formid).find("textarea");
    objFormCtrlList3.each(function (key, ctl) {
        arr.push(ctl);
    });
    //拆包解析data
    $.each(arr, function (key, control) {
        var ctlid = $(control).attr("id");//获取到每个控件的id
        $.each(rules, function (key, rule) {
            var value = data[rule.param];
            if (rule.ctlid == ctlid && (value != undefined && value != null)) {
                var tagname = $(control).get(0).tagName.toLowerCase();
                //获取input类型控件的值并填充
                if (tagname == "input") {
                    if ($(control).attr("type") == "text") {
                        $(control).val(data[rule.param]);
                    }
                    else if ($(control).attr("type") == "hidden") {
                        $(control).val(data[rule.param]);
                    }
                    else if ($(control).attr("type") == "checkbox") {
                        if (data[rule.param] == "01") {
                            $(control).attr("checked", true);
                        }
                    }
                } else if (tagname == "select") {

                    for (var i = 0; i < data[rule.param].length; i++) {
                        $(control).append("<option>" + data[rule.param][i].name + "</option>");
                    }
                }
                else if (tagname == "textarea") {
                    $(control).val(data[rule.param]);
                }
            }
        });
    });


    /*//拆包解析data
     $.each(arr,function(key,control){
     var ctlid=$(control).attr("id");//获取到每个控件的id
     $.each(rules,function(key,rule){
     var value=data[rule.param];
     if(rule.ctlid==ctlid && (value!=undefined && value!=null) ) {
     var tagname=$(control).get(0).tagName.toLowerCase();
     //获取input类型控件的值并填充
     if(tagname=="input") {
     if ($(control).attr("type") == "text") {
     $(control).val(data[rule.param]);
     }
     else if ($(control).attr("type") == "checkbox") {
     if (data[rule.param] == "01") {
     $(control).attr("checked", true);
     }
     }
     }else if(tagname=="select") {
     for(var i=0;i<data[rule.param].length;i++){
     $(control).append("<option>"+res.body.data[rule.param][i].name+"</option>");
     }
     }
     else if(tagname=="textarea") {
     $(control).val(data[rule.param]);
     }
     }
     });
     });*/
};
/* fillform("form1",rule,data);
 form1表示的是表单form的id
 * */


/************************************单表格操作***********************************/
// 显示表格并填充
function fillTable_one(tableData, colNames, colModel, objTable) {
    pageInit_one(tableData, colNames, colModel, objTable.tableId,
        objTable.divId == undefined ? "divId" : objTable.divId,
        objTable.width == undefined ? 0.88 : objTable.width,
        objTable.url == undefined ? "" : objTable.url,
        objTable.loadonce == undefined ? true : false
    );
    tableData = null;
}
function pageInit_one(tableData, colNames, colModel, tableId, divId, width, url, loadonce) {
    //var table = $("#" + tableId);
    $("#" + tableId).jqGrid('GridUnload');
    $("#" + tableId).trigger("reloadGrid");
    $("#" + tableId).jqGrid(
        {
            datastr: JSON.stringify({
                "rows": tableData
            }),
            datatype: "jsonstring",
            colNames: colNames,
            colModel: colModel,
            autowidth: true,
            height: 'auto',
            autoScroll: true,
            viewrecords: true,//是否显示总记录数
            rowNum: 10,//一页显示多少条
            rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: "#" + divId,
            gridview: true,
            multiselect: true, //表示的是前面的那个checkbox标签
            //cellEdit: true,
            editurl: "http://www.baidu.com",
            loadonce: loadonce,
            //shrinkToFit:false,
            jsonReader: {
                repeatitems: false
            },
            /*afterInsertRow: function (rowid, aData) {
             $("#" + tableId).jqGrid('editRow', rowid);
             console.log("afterInsertRow");
             },
             gridComplete: function () {
             $("#" + tableId + " tr:not(:first-child)").each(function () {
             $("#" + tableId).jqGrid('editRow', $(this).attr("id"));
             });
             console.log("gridComplete");
             },*/
            /*onCellSelect:function(rowid,iCol,cellcontent,e){
             table.jqGrid('editRow',rowid);
             }*/
        }
    );
    $("#" + tableId).setGridWidth(document.body.clientWidth * width);
    $(window).resize(function () {
        $("#" + tableId).setGridWidth(document.body.clientWidth * width);
    });
    /*if(tableData != null){
     $("#"+tableId + " tr:not(:first-child)").each(function(){
     console.log();
     table.jqGrid('editRow',$(this).attr("id"));
     });
     }*/
}
/************************************单表格操作***********************************/

/**********************************获取单表格数据*********************************/
// 获取表格里面的所有数据
function getTableData_one(tableId) {
    var o = $("#" + tableId);
    //获取当前显示的数据
    var rows = o.jqGrid('getRowData');
    var rowNum = o.jqGrid('getGridParam', 'rowNum'); //获取显示配置记录数量
    var total = o.jqGrid('getGridParam', 'records'); //获取查询得到的总记录数量
    //设置rowNum为总记录数量并且刷新jqGrid，使所有记录现出来调用getRowData方法才能获取到所有数据
    o.jqGrid('setGridParam', {rowNum: total}).trigger('reloadGrid');
    var rows = o.jqGrid('getRowData');  //此时获取表格所有匹配的
    o.jqGrid('setGridParam', {rowNum: rowNum}).trigger('reloadGrid'); //还原原来显示的记录数量
    //console.log(rows);
    return rows;
}
/**********************************获取单表格数据*********************************/

// 调用编辑表格的方法
function editTable() {
    // 获取页面所有的有class的标签
    var editClassNameArr = [];
    $('form *').each(function () {
        if ($(this).attr("class") != undefined && $(this).attr("class") != null && $(this).attr("class") != "") {
            var editTitle = {};
            if ($(this).is("select")) {
                editTitle[$(this).attr("class")] = $(this).find("option:selected").text();
                editClassNameArr.push(editTitle);
            } else if ($(this).is("input")) {
                editTitle[$(this).attr("id")] = $(this).val();
                editClassNameArr.push(editTitle);
            }
        }
    })
    // 把当前select的值存入到session中
    storage.setItem("editClassNameArr", JSON.stringify(editClassNameArr));
    // 遍历整行数据的每个td的class，然后根据需要，改变哪个class了，回显的时候就把哪个class对应的td的text给替换了
    var jsonObj = JSON.parse(storage.getItem("editClassNameArr"));
    // select的值发生改变的时候进行取值保存到session中
    $("select").each(function () {
        $(this).change(function () {
            var className = $(this).attr("class");
            for (var i = 0; i < jsonObj.length; i++) {
                for (var key in jsonObj[i]) {
                    if (key == className)
                        jsonObj[i][key] = $(this).find("option:selected").text();
                }
            }
            storage.setItem("editClassNameArr", JSON.stringify(jsonObj));
        });
    });
}
// 获取整行要编辑的数据，保存到session中
function editBefore() {
    storage.setItem("rd", $(":checkbox:checked").parent().next().text());
    //获取整行的所有className作为key，把对应td的text作为value存入session中
    var td = $(":checkbox:checked").parent().next();
    var classNameArr = [];
    loopTdTitle(td, 0);
    function loopTdTitle(td, n) {
        var key = storage.getItem("modelArr");
        var className = {};
        className[key.split(",")[n]] = $(td).text();
        classNameArr.push(className);
        if ($(td).next().text() != undefined && $(td).next().text() != null && $(td).next().text() != "") {
            n++;
            loopTdTitle($(td).next(), n);
        }
    };
    storage.setItem("classNameArr", JSON.stringify(classNameArr));
}
// 编辑之后，把数据回显到页面
function editAfter() {
    var editBefore = JSON.parse(storage.getItem("classNameArr"));
    var editAfter = JSON.parse(storage.getItem("editClassNameArr"));
    for (var i = 0; i < editBefore.length; i++) {
        a:
            for (var key1 in editBefore[i]) {
                for (var j = 0; j < editAfter.length; j++) {
                    for (var key2 in editAfter[j]) {
                        if (key1 == key2) {
                            editBefore[i][key1] = editAfter[j][key2];
                            break a;
                        }
                    }
                }
            }
    }
    var arr = editBefore;
    var tdObj = $(":checkbox:checked").parent().next().next();

    fullTD(tdObj);
    function fullTD(tdObj) {
        for (var i = 0; i < arr.length; i++) {
            for (var key in arr[i]) {
                if (key == $(tdObj).attr("class")) {
                    $(tdObj).text(arr[i][key]);
                    break;
                }
            }
            if ($(tdObj).next().text() != undefined && $(tdObj).next().text() != null && $(tdObj).next().text() != "") {
                fullTD($(tdObj).next());
            }
        }
    }
}
// 填充表格
function fullTable(tableData, modelArr, colNamesArr, tableId, divId, pageBean) {
    var width = 0.88;
    if (tableData != null) {
        var objtableRow = {};
        var objtableRows = [];
        var objbfsinfo = tableData;
        if (objbfsinfo != undefined && objbfsinfo.length > 0) {
            $.each(objbfsinfo, function (key, objbfinfo) {
                objtableRow = {};
                for (var key in objbfinfo) {
                    if (objbfinfo[key] instanceof Array) {
                        var str = "<select class='_bgColor' name='ItemType'>";
                        for (var k in objbfinfo[key]) {
                            if (objbfinfo[key][k].TypeText != undefined) {
                                if (objbfinfo[key][k].Checked == "00")
                                    str = str + "<option selected value='" + objbfinfo[key][k].TypeVal + "'>" + objbfinfo[key][k].TypeText + "</option>";
                                else
                                    str = str + "<option value='" + objbfinfo[key][k].TypeVal + "'>" + objbfinfo[key][k].TypeText + "</option>";
                            }
                        }
                        str = str + "</select>";
                        objtableRow[key] = str;
                    }
                    else
                        objtableRow[key] = objbfinfo[key];
                }
                //layer.alert(JSON.stringify(objtableRow));
                objtableRows.push(objtableRow);
            });
        }
    }

    // -------------------------------------------------------------------
    var colModel = [
        {name: modelArr[0], index: modelArr[0], classes: modelArr[0], hidden: true, sortable: false},
    ];
    //var flag = false;
    if (modelArr.length != colNamesArr.length)
        for (var i = 1; i < modelArr.length - 1; i++) {
            /*if(modelArr[i].lastIndexOf("Rd")!= -1)
             var entity = {
             name: modelArr[i],
             index: modelArr[i],
             classes: modelArr[i],
             hidden: true,
             //width: 100,
             sortable:false
             };
             else*/
            var model = {
                name: modelArr[i],
                index: modelArr[i],
                classes: modelArr[i],
                //width: 100,
                sortable: false
            };

            colModel.push(model);
            width = modelArr[modelArr.length - 1]
        } else {
        for (var i = 1; i < modelArr.length; i++) {
            /*if(modelArr[i].lastIndexOf("Rd")!= -1)
             var entity = {
             name: modelArr[i],
             index: modelArr[i],
             classes: modelArr[i],
             hidden: true,
             //width: 100,
             sortable:false
             };
             else*/
            var model = {
                name: modelArr[i],
                index: modelArr[i],
                classes: modelArr[i],
                //width: 100,
                sortable: false
            };
            colModel.push(model);
        }
    }
    //把字段作为key存起来，方便修改的时候知道修改的哪个字段
    var storage = window.sessionStorage;
    storage.setItem("modelArr", modelArr);
    pageInit(objtableRows, colNamesArr, colModel, tableId, divId, pageBean, width);
    objtableRows = null;
}
// 定义table
/*-------------------定义表格--------------------------*/
function pageInit(objtableRows, colNamesArr, colModel, tableId, divId, pageBean, width) {
    $("#" + tableId).jqGrid('GridUnload');
    $("#" + tableId).trigger("reloadGrid");
    $("#" + tableId).jqGrid(
        {
            datastr: JSON.stringify({
                "page": pageBean.page,
                "total": pageBean.total,
                "records": pageBean.records,
                "rows": objtableRows
            }),
            datatype: "jsonstring",
            colNames: colNamesArr,
            colModel: colModel,
            autowidth: true,
            height: 'auto',
            autoScroll: true,
            viewrecords: true,//是否显示总记录数
            rowNum: 10,//一页显示多少条
            rowList: [10, 20, 30],//可供用户选择一页显示多少条
            pager: "#" + divId,
            gridview: true,
            multiselect: true, //表示的是前面的那个checkbox标签
            //shrinkToFit:false,
            jsonReader: {
                repeatitems: false
            }
        }
    );
    $("#" + tableId).setGridWidth(document.body.clientWidth * width);
    $(window).resize(function () {
        //$("#"+tableId).setGridWidth($("#"+tableId).parent().width());　　
        $("#" + tableId).setGridWidth(document.body.clientWidth * width);
    });

    //jQuery("#" + tableId).jqGrid('navGrid', "#" + divId, {edit: false, add: false, del: false});
}
// 表格的删除
function delTr(tableId) {
    //getGridParam:表示的是获取表格的参数
    var selectedRowIds = $("#" + tableId).jqGrid("getGridParam", "selarrrow");
    var gr = 0;
    if (selectedRowIds != undefined && selectedRowIds != null)
        gr = selectedRowIds.length;
    if (gr > 0) {
        layer.confirm('', {
                type: 0,
                btn: ['确认', '取消'], //按钮
                content: '确认删除吗？',
                icon: "fa-check-circle"
            }, function () {
                for (var i = 0; i < gr; i++) {
                    $("#" + tableId).jqGrid("delRowData", selectedRowIds[0]);//每次都取第0个数据
                }
                layer.closeAll();
                return gr;
            }, function () {

            }
        );
    } else {
        alertWarn("请选择要删除的数据再进行删除！")
        return -1;
    }
}
// 添加数据
function addAfter(tableId, className) {
    var editClassNameArr = JSON.parse(storage.getItem("editClassNameArr"));
    var addTrArr = {};
    var bfsInfo = JSON.parse(storage.getItem("bfinfo"));
    if (bfsInfo != undefined && bfsInfo.length > 0) {
        $.each(bfsInfo, function (key, bfinfo) {
            for (var key1 in bfinfo) {
                for (var i = 0; i < editClassNameArr.length; i++) {
                    for (var key2 in editClassNameArr[i]) {
                        if (key1 == key2 && bfinfo[key1] == editClassNameArr[i][key2]) {
                            addTrArr = bfinfo;
                            return false;
                        }
                    }
                }
            }
        });
    }
    var dataRow = addTrArr;
    $("#" + tableId).addRowData(1, dataRow, "first");
    function loop(obj) {
        var className = $(obj).attr("class");
        if (className != undefined) {
            for (var key in dataRow) {
                // layer.alert("className:"+className + " , key:" + key)
                if (className == key) {
                    $(obj).text(dataRow[key])
                    break;
                }
            }
            if ($(obj).next().text().trim() == "") {
                loop($(obj).next());
            }
        }
    }

    $("." + className).each(function () {
        if ($(this).text().trim() == "") {
            var td = $(this).prev();
            var random = getRandomString(3);
            var id = "jqg_list4_jqg" + random;
            var name = "jqg_list4_jqg" + random;
            $(td).find('[type=checkbox]').attr("id", id);
            $(td).find('[type=checkbox]').attr("name", name);
            loop($(this));
        }
    })
}
// 获取长度为len的随机字符串
function getRandomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

// 获取表格里面的所有数据
function getTableData(tableId) {
    var bfsinfo = [];
    if ($("#" + tableId + " tr:not(:first-child)").length > 0) {
        $("#" + tableId + " tr:not(:first-child)").each(function () {
            var bfinfo = {};
            $(this).find("td:not(:first-child)").each(function () {
                if ($(this).children().length > 0) {
                    if ($(this).children().is("select")) {
                        bfinfo[$(this).attr("class")] = $(this).children().find("option:selected").val();
                    } else if ($(this).children().is("input")) {
                        bfinfo[$(this).attr("class")] = $(this).children().val();
                    }
                } else {
                    bfinfo[$(this).attr("class")] = $(this).text().trim();
                }
            });
            bfsinfo.push(bfinfo);
        });
    }
    return bfsinfo;
}
// 自动获取页面所有的表单数据，并转换成json对象
function transfer(formId) {
    var jsonuserinfo = $('#' + formId).serializeObject();
    return jsonuserinfo;
}
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

// 控制表单数据是否可写
function readOnly(formId, boolean) {
    $("#" + formId).find('input, select, textarea').each(function () {
        if ($(this).is("select") || $(this).prop("type") == "checkbox" || $(this).prop("type") == "radio") {
            $(this).attr("disabled", boolean);
        }
        $(this).attr("readonly", boolean);
        if ($(this).attr("class") != undefined && $(this).attr("class") != null && $(this).attr("class") != "") {
            var classNameArr = $(this).attr("class").split(" ");
            for (var i = 0; i < classNameArr.length; i++) {
                if (classNameArr[i] == "read") {
                    $("." + classNameArr[i]).attr("readonly", true);
                }
            }
        }
    });
}
// 清空所有表单数据
function clearForm(formId) {
    $("#" + formId).find('input, textarea').each(function () {
        if($(this).attr("type") == "checkbox" || $(this).attr("type") == "radio"){
            $(this).attr("value","");
            $(this).prop("checked",false);
        }
        else{
            $(this).val("");
        }
    });
}

//配置url
var url = "";

// 获取站点根目录
function getBasePath() {
    if(url == undefined || url == ""){
        var obj = window.location;
        var contextPath = obj.pathname.split("/")[1];
        var basePath = /*obj.protocol+"//"+obj.host+*/"/" + contextPath;
        return basePath;
    }

    return url;
}

// 对一些不能为空的form标签进行判断，需要给定要判断的form标签一个class为check的名字
$(".check").on("blur", function () {
    if ($(this).val() == "" || $(this).val() == null || $(this).val() == undefined) {
        $(this).css("border-color", "red");
        $(this).prop("placeholder", "不能为空！");
    } else {
        $(this).css("border-color", "");
    }
})

//菜单按钮点击效果处理
$('.btn-primary').on('click', function () {
    $('.btn-primary').css('backgroundColor', '');
    $(this).css('backgroundColor', '#2c3e50');
});
$('#option1').on('click',function(){
    $(this).css('backgroundColor', '');
});

function showMsg(msg) {
    var _span = $(".sysSpan");
    _span.css("color", "lawngreen");
    _span.text(msg);
    setTimeout(function () {
        _span.text("");
    }, 2000);
}
function showWarn(msg) {
    var _span = $(".sysSpan");
    _span.css("color", "yellow");
    _span.text(msg);
}

//封装ajax
function request(obj, callback) {
    if(obj.url.toLowerCase().indexOf("save") >= 0){
        App.blockUI({
            target: "body",
            message: "正在加载中...",
            boxed:true
        });
    }
    $.ajax({
        type: obj.type == undefined ? "post" : "get",
        url: getBasePath() + obj.url,
        data: obj.data == undefined ? {} : obj.data,
        async: obj.async == undefined ? false : true,
        contentType: obj.upload == undefined ? "application/x-www-form-urlencoded" : false,
        beforeSend: function () {
            /*if( obj.id != undefined && obj.id != null){
                App.blockUI({
                    target: "#" + obj.id,
                    message: "正在加载中...",
                    boxed:true
                });
            }*//*else{
                App.blockUI({
                    target: "body",
                    message: "正在加载中...",
                    boxed:true
                });
            }*/
        },
        complete: function () {
            App.unblockUI("body");
            /*if( obj.id != undefined && obj.id != null){
                App.unblockUI("#" + obj.id);
            }*//*else{
                App.unblockUI("body");
            }*/
        },
        success: function (res) {
            setTimeout("App.unblockUI('body')", 3000);
            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                callback(res.Body);
            } else {
                toastr.warning(res.Body.MsgDes);
                return;
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 401) {
                window.location.href = getBasePath() + "/Login";
            } else {
                toastr.error(/*textStatus*/"系统异常,请联系管理员!");
            }
        }
    });
}

function uploadRequest(obj, callback) {
    App.blockUI({
        target: "body",
        message: "正在加载中...",
        boxed:true
    });
    $.ajax({
        type: obj.type == undefined ? "post" : "get",
        url: getBasePath() + obj.url,
        data: obj.data == undefined ? {} : obj.data,
        async: obj.async == undefined ? false : true,
        contentType: false,
        processData: false,
        beforeSend: function () {

        },
        complete: function () {
            App.unblockUI('body');
        },
        success: function (res) {
            setTimeout("App.unblockUI('body')", 3000);
            if (res.Status == "00" && res.Body.MsgCode == "0x00000") {
                callback(res.Body);
            } else {
                toastr.warning(res.Body.MsgDes);
                return;
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 401) {
                window.location.href = getBasePath() + "/Login";
            } else {
                toastr.error(/*textStatus*/"系统异常,请联系管理员!");
            }
        }
    });
}


var getSelectPr = function (objSelectPr) {
    var returnData = "";
    $(objSelectPr.Data).each(function () {
        returnData += "" + this[objSelectPr.DataValue] + ":" + this[objSelectPr.DisplayName] + ";";
    });
    if (returnData != "") {
        returnData = returnData.substring(0, returnData.length - 1);
    }
    return returnData;
};


var lastFlag;
var saveFlag = 0;
function fullTable(config) {
    var tableId = config.tableId;
    var tableData = config.data;
    var width = config.width == undefined ? 0.85 : config.width;
    var height = config.height;
    var colArr = config.colArr;
    var multiselect = config.multiselect == undefined ? false : config.multiselect;
    var rowedit=config.rowedit==undefined?true :config.rowedit;
    var ispage = config.ispage == undefined ? false : config.ispage;
    var event = config.event == undefined ? null : config.event;
    saveFlag = 0;

    var pageBean = {
        "page": 1,
        "total": 2,
        "records": 20,
    };

    var colModel = [];
    var objtableRows = [];
    if (tableData != null) {
        var objtableRow = {};
        var objbfsinfo = tableData;
        if (objbfsinfo != undefined && objbfsinfo.length > 0) {
            $.each(objbfsinfo, function (key, objbfinfo) {
                objtableRow = {};
                for (var key in objbfinfo) {
                    objtableRow[key] = objbfinfo[key];
                }
                objtableRows.push(objtableRow);
            });
        }
    }
    if (colArr != undefined && colArr.length > 0) {
        $.each(colArr, function (key, colName) {
            var model = {
                label: colName.Caption,
                name: colName.Name,
                index: colName.Name,
                classes: colName.Name,
                resizable: true,
                sortable: false,
                align: "left"
            };
            if(colName.Width != undefined) {
                model["width"] = colName.Width;
            }
            var Hidden = colName.Hidden == undefined ? false : colName.Hidden;
            if (Hidden) {
                model["hidden"] = true;
            }
            var IsKey = colName.IsKey == undefined ? false : colName.IsKey;
            if (IsKey) {
                model["IsKey"] = true;
            }
            var editable = colName.Editable == undefined ? false : colName.Editable;
            if (editable) {
                model["editable"] = true;
            }
            var LinkAge = colName.LinkAge == undefined ? "" : colName.LinkAge;
            if (LinkAge) {
                model["linkage"] = LinkAge;
            }
            if (model["editable"]) {
                if (colName.CType == "text") {
                    model["edittype"] = "text";
                } else if (colName.CType == "textarea") {
                    model["edittype"] = "textarea";
                } else if (colName.CType == "select") {
                    model["edittype"] = "select";
                    //entity["editrules"] = {required:true};
                    var objSelectPr = colName.SelectPr;
                    if (objSelectPr != undefined && objSelectPr != null) {
                        model["editoptions"] = {value: getSelectPr(objSelectPr)};
                        model["selectpr"] = objSelectPr;
                    }
                } else if (colName.CType == "checkbox") {
                    model["edittype"] = "checkbox";
                } else if (colName.CType == "password") {
                    model["edittype"] = "password";
                } else if (colName.CType == "button") {
                    model["edittype"] = "button";
                } else if (colName.CType == "image") {
                    model["edittype"] = "image";
                } else if (colName.CType == "file") {
                    model["edittype"] = "file";
                } else if (colName.CType == "autocount") {
                    model["fixed"]=true;
                    model["shrinkToFit"]=true;
                    model["edittype"] = "autocount";
                    var objAutoCountPr = colName.AutoCountPr;
                    if (objAutoCountPr != undefined && objAutoCountPr != null) {
                        model["autocountpr"] = objAutoCountPr;
                    }
                    var newmodel = {
                        label: "autocount",
                        name: "autocount",
                        index: "autocount",
                        classes: "autocount",
                        hidden: true
                    };
                    colModel.push(newmodel);
                }
            }
            model["formatter"] = function (cellvalue, options, rowObject) {
                if (options.colModel.edittype == "checkbox") {
                    if (cellvalue == "00" || cellvalue.toUpperCase() == "YES" || cellvalue.toUpperCase() == "ON") {
                        options.colModel["attach"] = 1;
                        return "<input type='checkbox' checked class='cbox' val='00'/>";
                        //return "<i class=\"fa fa-toggle-on\" val='00'></i>";
                    }
                    else if (cellvalue == "01" || cellvalue.toUpperCase() == "NO") {
                        options.colModel["attach"] = 1;
                        return "<input type='checkbox' class='cbox' val='01'/>";
                        //return "<i class=\"fa fa-toggle-off\" val='01'></i>";
                    }
                }
                if (options.colModel.edittype == "select") {
                    var rowid = options.rowId[0];
                    var colname = options.colModel.name;
                    var selectpr = options.colModel.selectpr;
                    if (saveFlag == 1) {
                        if (rowObject[colname] instanceof Array) {
                            $("tbody tr[id='" + rowid + "'] " + "." + colname, $("#" + tableId)).attr("text", rowObject[colname][0][selectpr.DisplayName]);
                            $("tbody tr[id='" + rowid + "'] " + "." + colname, $("#" + tableId)).attr("val", rowObject[colname][0][selectpr.DataValue]);
                        }
                        else {
                            var celldata = {};
                            jQuery(selectpr.Data).each(function () {
                                if (this[selectpr.DisplayName] == cellvalue) {
                                    celldata[selectpr.DisplayName] = this[selectpr.DisplayName];
                                    celldata[selectpr.DataValue] = this[selectpr.DataValue];
                                }
                            });
                            $("tbody tr[id='" + rowid + "'] " + "." + colname, $("#" + tableId)).attr("title", celldata[selectpr.DisplayName]);
                            $("tbody tr[id='" + rowid + "'] " + "." + colname, $("#" + tableId)).attr("text", celldata[selectpr.DisplayName]);
                            $("tbody tr[id='" + rowid + "'] " + "." + colname, $("#" + tableId)).attr("val", celldata[selectpr.DataValue]);
                        }
                    }
                    else {
                        if (rowObject.oper != "edit") {
                            if (rowObject[colname] instanceof Array) {
                                cellvalue = rowObject[colname][0][selectpr.DisplayName];
                            } else {
                                cellvalue = rowObject[colname] == null ? "" : rowObject[colname][selectpr.DisplayName];
                                if (cellvalue == undefined || cellvalue == "") {
                                    cellvalue = rowObject[selectpr.DisplayName] == null ? "" : rowObject[selectpr.DisplayName];
                                }
                            }
                        }
                        else {
                            cellvalue = rowObject[colname];
                        }
                    }
                }
                if (options.colModel.edittype == "autocount") {
                    return "<div class=\"text1\"></div><input type=\"text\" id=\"text01\" name=\"text01\" role=\"textbox\" class=\"editable\"/>";
                }
                return cellvalue;
            };
            colModel.push(model);
        });
    }

    var datastr = "";
    if (ispage) {

    } else {
        datastr = JSON.stringify({
            "page": 1,
            "total": objtableRows.length,
            "records": objtableRows.length,
            "rows": objtableRows
        });
    }

    $("#" + tableId).jqGrid('GridUnload');
    $("#" + tableId).trigger("reloadGrid");
    $("#" + tableId).jqGrid(
        {
            datastr: datastr,
            datatype: "jsonstring",
            colModel: colModel,
            viewrecords: true,
            rowNum: objtableRows.length,
            autoScroll: true,
            //rowList: [10, 20, 30],
            gridview: true,
            multiselect: multiselect,
            jsonReader: {
                repeatitems: false
            },
            onSelectRow: function (id, status) {
                if(!rowedit) {
                    return false;
                }
                if (id && id != lastFlag) {
                    $('#' + tableId).jqGrid('saveRow', lastFlag);
                    //$('#' + tableId).jqGrid("resetSelection");
                    lastFlag = id;
                }
                $('#' + tableId).jqGrid('editRow', id, true);
                if (event != null && (event.onrowselect != undefined)) {
                    event.onrowselect(getSeRowData(tableId));
                }
                var _event=event;
                var colModel = $("#" + tableId).jqGrid('getGridParam', 'colModel');
                jQuery(colModel).each(function () {
                    var colname = this.name;
                    var linkage = this.linkage;
                    if (this.editable && this.edittype == "text") {
                        var e = $._data($("tbody tr[id='" + id + "'] " + "." + this.name, $("#" + tableId)).get(0), "events")
                        if (e && e["keydown"]) {

                        }
                        else {
                            $("tbody tr[id='" + id + "'] " + "." + this.name, $("#" + tableId)).on("keydown", function (e) {
                                if (e.keyCode == 13) {
                                    if (event != null && (event.oncellchange != undefined)) {
                                        var cellvalue = $($($(this).get(0).children[0]).get(0)).val();
                                        $('#' + tableId).jqGrid("setSelection");
                                        var selectedId = $("#" + tableId).jqGrid("getGridParam", "selrow");
                                        var selectedIds = [];
                                        selectedIds.push(selectedId);
                                        var data = {
                                            "rowed": selectedId,
                                            "colname": colname,
                                            "cellvalue": cellvalue,
                                            "row": getData(tableId, selectedIds)[0]
                                        };
                                        event.oncellchange(data);
                                    }
                                }
                            });
                        }
                    }
                    else if (this.editable && this.edittype == "select") {
                        //var e = $._data($("tbody tr[id='" + id + "'] " + "." + this.name, $("#" + tableId)).get(0).childNodes[0], "events");
                        var e = $("tbody tr[id='" + id + "'] " + "." + this.name, $("#" + tableId)).get(0).childNodes[0].onchange;
                        if (e) {

                        }
                        else {
                            $("tbody tr[id='" + id + "'] " + "#" + id + "_" + this.name, $("#" + tableId)).on("click", function () {

                                // });
                                // $("tbody tr[id='" + id + "'] " + "#" +id+"_"+this.name, $("#" + tableId)).get(0).childNodes[0].onchange = function () {
                                $(this).parent().attr("val", $(this).find("option:selected").get(0).value);
                                $(this).parent().attr("text", $(this).find("option:selected").get(0).text);
                                $(this).parent().attr("title", $(this).find("option:selected").get(0).text);
                                if (event != null && (event.oncellchange != undefined)) {
                                    var cellvalue = $(this).find("option:selected").get(0).value;
                                    $('#' + tableId).jqGrid("setSelection");
                                    var selectedId = $("#" + tableId).jqGrid("getGridParam", "selrow");
                                    var selectedIds = [];
                                    selectedIds.push(selectedId);
                                    var data = {
                                        "rowed": selectedId,
                                        "colname": colname,
                                        "cellvalue": cellvalue,
                                        "row": getData(tableId, selectedIds)[0]
                                    }
                                    event.oncellchange(data);
                                }
                                if ((linkage != undefined && linkage instanceof Array) && (event != null && (event.onlinkchange != undefined))) {
                                    jQuery(linkage).each(function () {
                                        var data = {
                                            "cellvalue": cellvalue,
                                            "row": getSeRowData(tableId)
                                        };
                                        event.onlinkchange(colname, data, this.PrimitiveValue);
                                    });
                                }
                            });
                        }
                    }
                });
            },
            afterInsertRow: function (rowid, rowdata, rowelem) {
                jQuery(colModel).each(function () {
                    var colName = this.name;
                    var edittype = this.edittype;
                    if (colName == "cb") {
                        return true;
                    }
                    else if (edittype == "select") {
                        var DisplayName = "", DataValue = "";
                        if (rowdata[colName] instanceof Array) {
                            DisplayName = rowdata[colName][0][this.selectpr.DisplayName];
                            DataValue = rowdata[colName][0][this.selectpr.DataValue];
                            $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId)).attr("title", DisplayName);
                            $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId)).attr("text", DisplayName);
                            $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId)).attr("val", DataValue);
                        }
                        else {
                            DisplayName = rowdata[colName][this.selectpr.DisplayName];
                            if (DisplayName == undefined) {
                                DisplayName = rowdata[colName];
                            }
                            DataValue = rowdata[colName][this.selectpr.DataValue];
                            if (DataValue == undefined) {
                                DataValue = rowdata[colName];
                            }

                            $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId)).attr("title", DisplayName);
                            $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId)).attr("text", DisplayName);
                            $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId)).attr("val", DataValue);
                        }
                        if (event != null && (event.oncellchange != undefined)) {
                            var cellvalue = DataValue;
                            $('#' + tableId).jqGrid("setSelection", rowid);
                            var selectedId = $("#" + tableId).jqGrid("getGridParam", "selrow");
                            var selectedIds = [];
                            selectedIds.push(selectedId);
                            var data = {
                                "rowed": selectedId,
                                "colname": colName,
                                "cellvalue": cellvalue,
                                "row": getData(tableId, selectedIds)[0]
                            }
                            event.oncellchange(data);
                        }
                    }
                    else if(edittype=="autocount") {
                        var text1 = $('.text1', $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId))).searchableDropdown();
                        $("#text01", $("tbody tr[id='" + rowid + "'] " + "." + this.name, $("#" + tableId))).on("keydown", text1, function (event) {
                            if (event.which == 13) {
                                if (event.onautocountchange(colname, data, this.PrimitiveValue)) {
                                    DisplayName = rowdata[colName][0][this.autocountpr.DisplayName];
                                    DataValue = rowdata[colName][0][this.autocountpr.DataValue];
                                    text1.add({
                                        "Key": DataValue,
                                        "Value": DisplayName
                                    });
                                    $(this).val("");
                                }
                            }
                        });
                    }
                });
            },
            ondblClickRow: function (rowid, iRow, iCol, e) {
                if (event != null && (event.ondblclickrow != undefined)) {
                    var rowData = $("#" + id).jqGrid("getRowData", rowid);
                    event.ondblclickrow(rowData);
                }
            },
            loadComplete:function() {
                var selectedIds = $("#" + tableId).jqGrid("getDataIDs");
                if (selectedIds.length > 0) {
                    var _event = event;
                    var colModel = $("#" + tableId).jqGrid("getGridParam", "colModel");
                    jQuery(selectedIds).each(function () {
                        var id = this;
                        var _rowdata=tableData[id-1];
                        jQuery(colModel).each(function () {
                            if (this.edittype == "autocount") {
                                $("tbody tr[id='" + id + "'] " + "." + this.name, $("#" + tableId)).html("<div><div class=\"text1\"></div><input type=\"text\"  class=\"editable text01\" role=\"textbox\" class=\"editable\" style=\"float:left;margin-left:23px;margin-top:-19px;width:121px;height:22px;border-style:solid;border-width:1px;border-color:silver\"/></div>");
                                var ddllist = _rowdata[this.name] == undefined ? [] : _rowdata[this.name];
                                var _colmodel1 = this;
                                var ddllist0 = [];
                                $.each(ddllist, function () {
                                    ddllist0.push({
                                        "Key": this[_colmodel1.autocountpr.DataValue],
                                        "Value": this[_colmodel1.autocountpr.DisplayName]
                                    });
                                });
                                var option1 = {
                                    data: ddllist0,
                                    callback: {
                                        removeItem: function (own, data) {
                                            var config = {
                                                "rowid": id,
                                                "colname": "autocount",
                                                "celldata": JSON.stringify(own)
                                            };
                                            setCell(tableId, config);
                                            if (_event != undefined && _event.onautocountitemremove != undefined) {
                                                var data12 = {};
                                                data12[_colmodel1.autocountpr.DataValue] = data.key;
                                                data12[_colmodel1.autocountpr.DisplayName] = data.value;
                                                _event.onautocountitemremove({"rowid": id, "data": data12});
                                            }
                                        }
                                    }
                                };
                                var text1 = $('.text1', $("tbody tr[id='" + id + "'] " + "." + this.name, $("#" + tableId))).searchableDropdown(option1);
                                var config = {
                                    "rowid": id,
                                    "colname": "autocount",
                                    "celldata": JSON.stringify(text1)
                                };
                                setCell(tableId, config);
                                var temp = {
                                    "ctl": text1,
                                    "rowid": id,
                                    "colname": this.name,
                                    "col": this
                                };
                                $(".text01", $("tbody tr[id='" + id + "'] " + "." + this.name, $("#" + tableId))).on("keydown", temp, function (event) {
                                    if (event.which == 13) {
                                        var curValue = $(this).val();
                                        if (curValue == "") {
                                            return false;
                                        }
                                        var regu = "^[ ]+$";
                                        var re = new RegExp(regu);
                                        if(re.test(curValue)){
                                            return false;
                                        }

                                        var getdata = getData(tableId, Number(temp.rowid))[0];
                                        delete getdata[temp.colname];
                                        var data = {
                                            "rowid": temp.rowid,
                                            "colname": temp.colname,
                                            "cellvalue": $(this).val(),
                                            "row": getdata
                                        };
                                        var CkRepeat = temp.col.autocountpr.CkRepeat == undefined ? "01" : temp.col.autocountpr.CkRepeat;
                                        var RepeatMsg = temp.col.autocountpr.RepeatMsg == undefined ? "信息已经存在,请检查" : temp.col.autocountpr.RepeatMsg;
                                        if (CkRepeat == "00") {
                                            var checkResult = false;
                                            var dddata = text1.getdata();
                                            $.each(dddata, function () {
                                                if (this.Value == curValue) {
                                                    checkResult = true;
                                                    return false;
                                                }
                                            });
                                            if (checkResult) {
                                                toastr.warning(RepeatMsg);
                                                return;
                                            }
                                        }

                                        var onautocountchange = _event.onautocountchange(data);
                                        if (_event.onautocountchange != undefined && typeof(onautocountchange) == "object" && onautocountchange.result) {
                                            var DisplayName = onautocountchange.data.DisplayName;
                                            var DataValue = onautocountchange.data.DataValue;
                                            if (DisplayName == "" || DataValue == "") {
                                                return;
                                            }
                                            text1.add({
                                                "Key": DataValue,
                                                "Value": DisplayName
                                            });
                                            $(this).val("");
                                            var config = {
                                                "rowid": id,
                                                "colname": "autocount",
                                                "celldata": JSON.stringify(text1)
                                            };
                                            setCell(tableId, config);
                                            if (onautocountchange.wrap != undefined && onautocountchange.wrap) {
                                                var hhid = temp.rowid;
                                                var search = function (id) {
                                                    if (id > tableData.length) {
                                                        return;
                                                    }
                                                    if ($(".text01", $("tbody tr[id='" + id + "'] " + "." + temp.colname, $("#" + tableId))).length == 0) {
                                                        search(++id)
                                                    }
                                                    else {
                                                        $(".text01", $("tbody tr[id='" + id + "'] " + "." + temp.colname, $("#" + tableId))).focus();
                                                    }
                                                };
                                                search(++hhid);
                                            }
                                        }
                                    }
                                });
                            }
                        });
                        if(_event!=undefined && _event.onrowbind!=undefined) {
                            var rowdata = {
                                "rowid": id,
                                "data": getRowById(tableId, id)
                            }
                            _event.onrowbind(rowdata);
                        }
                    });
                }
            },
            gridComplete: function () {
            }
        }
    );

    $("#" + tableId).setGridWidth(document.body.clientWidth * width);
    $(window).resize(function () {
        $("#" + tableId).setGridWidth(document.body.clientWidth * width);
    });
    if (height) {
        setHeight(tableId,height);
    }
}

function getRowData(id) {
    var selectedIds = $("#" + id).jqGrid("getDataIDs");
    return getData(id, selectedIds);
}

function getSeRowData(id) {
    var selectedIds = $("#" + id).jqGrid("getGridParam", "selarrrow");
    return getData(id, selectedIds);
}

function getData(id, selectedIds) {
    var colModel = $("#" + id).jqGrid("getGridParam", "colModel");
    var newRowDatas = [];
    jQuery(selectedIds).each(function () {
        var selectedId = this;
        saveFlag = 1;
        $('#' + id).jqGrid('saveRow', selectedId);
        //$('#' + id).jqGrid('editRow', selectedId);
        var rowData = $("#" + id).jqGrid("getRowData", selectedId);
        var newRowData = {};
        jQuery(colModel).each(function () {
            var colName = this.name;
            var edittype = this.edittype;
            if (colName == "cb") {
                return true;
            }
            if(colName=="autocount"){
                return true;
            }
            if (edittype == "text" || edittype == "textarea" || edittype == "password") {
                if ($("#" + id).find("tr[id='" + selectedId + "']").find("." + colName).find("input").length > 0)
                    newRowData[colName] = $("#" + id).find("tr[id='" + selectedId + "']").find("." + colName).find("input").val();
                else
                    newRowData[colName] = $("#" + id).find("tr[id='" + selectedId + "']").find("." + colName).text();
            }
            else if (edittype == "select") {
                var title = $("tbody tr[id='" + selectedId + "'] " + "." + colName, $("#" + id)).attr("title");
                var text = $("tbody tr[id='" + selectedId + "'] " + "." + colName, $("#" + id)).attr("text");
                var value = $("tbody tr[id='" + selectedId + "'] " + "." + colName, $("#" + id)).attr("val");
                if (text == undefined || text == "") {
                    text = title;
                    $("tbody tr[id='" + selectedId + "'] " + "." + colName, $("#" + id)).attr("text", text);
                }

                if (value == undefined || text == "") {
                    var selectpr = this.selectpr;
                    jQuery(selectpr.Data).each(function () {
                        if (this[selectpr.DisplayName] == text) {
                            value = this[selectpr.DataValue];
                        }
                    });
                    $("tbody tr[id='" + selectedId + "'] " + "." + colName, $("#" + id)).attr("val", value);
                }
                newRowData[this.selectpr.DisplayName] = text;
                newRowData[this.selectpr.DataValue] = value;
            }
            else if (edittype == "checkbox") {
                if ($("#" + selectedId).attr("editable") == 1) {
                    newRowData[colName] = $(rowData[colName]).val() == "on" ? "00" : "01";
                }
                else {
                    newRowData[colName] = $(rowData[colName]).attr("val");
                }
            }else if(edittype == "autocount") {
                var DisplayName = this.autocountpr.DisplayName;
                var DataValue = this.autocountpr.DataValue;
                var ddldata = JSON.parse(rowData["autocount"]).data;
                if (ddldata.length > 0) {
                    var re=[];
                    $.each(ddldata, function () {
                        var rerow = {};
                        rerow[DisplayName]=this.Value;
                        rerow[DataValue]=this.Key;
                        re.push(rerow);
                    });
                    newRowData[colName] = re;
                }
                else {
                    newRowData[colName] = [];
                }
            }
            else {
                newRowData[colName] = rowData[colName];
            }
        });
        newRowDatas.push(newRowData);
    });
    return newRowDatas;
}

function getKeyName(id) {
    var colModel = $("#" + id).jqGrid("getGridParam", "colModel");
    var keyname = "";
    jQuery(colModel).each(function () {
        if (this.IsKey) {
            keyname = this.name;
            return false;
        }
    });
    return keyname;
}

function getRowKey(id) {
    var selectedRowIds = $("#" + id).jqGrid("getGridParam", "selarrrow");
    var newKeyDatas = [];
    var keyname = "";
    jQuery(selectedRowIds).each(function () {
        keyname = getKeyName()
        var newKeyData = {};
        var rowData = $("#" + id).jqGrid("getRowData", this);
        newKeyData[keyname] = rowData[keyname];
        newKeyDatas.push(newKeyData);
    });
    return newKeyDatas;
}

function getRowId(id) {
    return $("#" + id).jqGrid("getGridParam", "selrow");
}

function addErow(id, pos) {
    if (pos == undefined) {
        pos = "first";
    }
    var rowData = {};
    var colModel = $("#" + id).jqGrid("getGridParam", "colModel");
    jQuery(colModel).each(function () {
        var colName = this.name;
        if (this.edittype == "select") {
            rowData[this.name] = this.selectpr.Data;
        }
        else {
            rowData[this.name] = "";
        }
    });
    var ids = jQuery("#" + id).jqGrid('getDataIDs');
    var rowid = Math.max.apply(null, ids);
    if (rowid == "-Infinity") {
        rowid = 0;
    }
    newrowid = rowid + 1;
    lastFlag = newrowid;
    var su = $("#" + id).jqGrid('addRowData', newrowid, rowData, pos);
    $("#" + id).setGridParam({cellEdit: false});
    $('#' + id).jqGrid('editRow', newrowid);
    $('#' + id).jqGrid("resetSelection");
}

function checkdup(keyName, newrow, oldData) {
    if (keyName == "") {
        return true;
    }
    var newrows = [];
    if (!(newrow instanceof Array)) {
        newrows.push(newrow);
    }
    else {
        newrows = newrow;
    }
    var a, b, c = true;
    jQuery(newrow).each(function () {
        a = this;
        jQuery(oldData).each(function () {
            b = this;
            if (a[keyName] == b[keyName]) {
                c = false;
                alertError(a[keyName] + "已存在");
                return false;
            }
        });
        if (!c) {
            return false;
        }
    });
    return c;
}

function addSrow(id, rowData, pos, ckdup) {
    if (pos == undefined) {
        pos = "first";
    }
    if (ckdup != undefined && ckdup) {
        if (!checkdup(getKeyName(id), rowData, getRowData(id))) {
            return;
        }
    }
    var ids = jQuery("#" + id).jqGrid('getDataIDs');
    var rowid = Math.max.apply(null, ids);
    if (rowid == "-Infinity") {
        rowid = 0;
    }
    newrowid = rowid + 1;
    lastFlag = newrowid;
    var su = $("#" + id).jqGrid('addRowData', newrowid, rowData, pos);
    $("#" + id).setGridParam({cellEdit: false});
    $('#' + id).jqGrid('editRow', newrowid);
    $('#' + id).jqGrid("resetSelection");
}

function addMrow(id, rowDatas, pos, ckdup) {
    if (pos == undefined) {
        pos = "first";
    }
    if (ckdup != undefined && ckdup) {
        if (!checkdup(getKeyName(id), rowData, getRowData(id))) {
            return;
        }
    }
    jQuery(rowDatas).each(function () {
        var ids = jQuery("#" + id).jqGrid('getDataIDs');
        var rowid = Math.max.apply(null, ids);
        if (rowid == "-Infinity") {
            rowid = 0;
        }
        newrowid = rowid + 1;
        lastFlag = newrowid;
        var su = $("#" + id).jqGrid('addRowData', newrowid, rowData, pos);
        $("#" + id).setGridParam({cellEdit: false});
        $('#' + id).jqGrid('editRow', newrowid);
        $('#' + id).jqGrid("resetSelection");
    });
}

function derow(id) {
    var selectedRowIds = $("#" + id).jqGrid("getGridParam", "selarrrow");
    var len = selectedRowIds.length;
    for (var i = 0; i < len; i++) {
        $("#" + id).jqGrid("delRowData", selectedRowIds[0]);
    }
    var ids = jQuery("#" + id).jqGrid('getDataIDs');
    if (ids.length == 0) {
        var ck = $("table thead tr th " + "#cb_" + id, $("#gview_" + id));
        if (ck.is(":checked")) {
            ck.get(0).checked = false;
        }
    }
}

function setCell(id, config) {
    var rowid = config.rowid;
    var colname = config.colname;
    var celldata = config.celldata;
    if(celldata!=undefined && celldata!=""){
        $("#" + id).setCell(rowid, colname, celldata);
    }
    if(celldata!=undefined && celldata=="") {
        $("tbody tr[id='" + rowid + "'] " + "." + colname, $("#" + id)).html("");
    }

    if(config.bgcolor!=undefined) {
        $("tbody tr[id='" + rowid + "'] " + "." + colname, $("#" + id)).css("background-color", config.bgcolor);
    }
    if(config.placeholder!=undefined) {
        var colModel = $("#" + id).jqGrid("getGridParam", "colModel");
        jQuery(colModel).each(function () {
            if (this.edittype == "autocount") {
                $("tbody tr[id='" + rowid + "'] " + "." + colname+" .text01", $("#" + id)).attr("placeholder", config.placeholder);
            }
        });
    }
}

function deallrow(id) {
    $("#" + id).jqGrid("clearGridData");
    var ck = $("table thead tr th " + "#cb_" + id, $("#gview_" + id));
    ck.get(0).checked = false;

}

function getRowIds(id) {
    return  $("#" + id).jqGrid("getDataIDs");
}

function getRowById(id,rowid) {
    return $("#" + id).jqGrid("getRowData", rowid);
}

// 成功信息弹窗
function alertSuccess(msg) {
    layer.alert(msg, {
            icon: 1,
            skin: 'layer-ext-moon'
        }
    );
}
// 失败信息弹窗
function alertError(msg) {
    layer.alert(msg, {
            icon: 2,
            skin: 'layer-ext-moon'
        }
    );
}
// 提示信息
function alertWarn(msg) {
    layer.alert(msg, {
            icon: 8,
            skin: 'layer-ext-moon'
        }
    );
}
//新增对版本默认值和状态下拉框的默认值
function defaultValueForNew() {
    $(".version").val("1");
    $(".status").val("可用");
}


//树的公共搜索功能
var treeSearch = function (s1) {
    $(".input1").keyup(function (event) {
        var ss1 = []
        for (var i in s1) {
            if (s1[i].name.indexOf($(this).val().trim()) >= 0) {
                var ss = {
                    "id": s1[i].id,
                    "name": s1[i].name
                }
                ss1.push(ss);
            }
            else if ($(this).val().trim().length <= 0) {
                $("#jstree_demo1").empty();
                var config1 = {
                    id: "jstree_demo1",
                    data: {
                        source: s1,
                        rule: [{
                            id: "id",
                            text: "name"
                        }]
                    }
                };
                $.JstreeEx.init(config1);//先调用后加载

                break;
            }
            else {
                $("#jstree_demo1").empty().html("无更多记录")
                $("#jstree_demo1").attr("style", "color:red")
            }
        }
        if (ss1.length > 0) {
            $("#jstree_demo1").attr("style", "color:#000")
            $("#jstree_demo1").empty();
            var config2 = {
                id: "jstree_demo1",
                data: {
                    source: ss1,
                    rule: [{
                        id: "id",
                        text: "name"
                    }]
                }
            };
            $.JstreeEx.init(config2);//先调用后加载
        }
    })
}


//树的搜索
var treeSearchs = function (url, ids, names, name1,inputValue, cPage , config, FiledList) {

    if (cPage == undefined || cPage == null || cPage == "")
        cPage = 0;
    var retCurrentPage;

    var InitData = {
        "FiledList": [
            {
                "FieldName": name1,
                "FieldOpt": "like",
                "FieldVal": "%" + inputValue + "%"
            },
            {
                "FieldName": name1,
                "FieldOpt": "Order BY"
            }
        ]
    };

    /* if(config1!=""&&config1!=undefined&&config1!=null){
     InitData.FiledList.push(config1);
     }*/
    if(FiledList != null && FiledList != undefined && FiledList != ""){
        console.log(FiledList instanceof Array);
        Array.prototype.push.apply(InitData.FiledList,FiledList);
    }

    var currentPage = cPage;
    var PageInfo = {
        "PageSize": 20,
        "PageIndex": currentPage
    };
    //分页处理
    var treedataList = [];

    request({
        url: url,
        data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(PageInfo)}
    }, function (Body) {
        var treeData = Body.Data;
        if (treeData.length <= 0) {
            currentPage--;
            retCurrentPage = currentPage;
            toastr.warning("无更多记录");
            return;
        }
        retCurrentPage = currentPage;

        var len = treeData.length >= 20 ? 20:treeData.length;
        for (var i = 0; i < len; i++) {
            var tree = {
                id: treeData[i][ids] == undefined ? "" : treeData[i][ids],
                name: treeData[i][names] == undefined ? "" : treeData[i][names]
            };
            treedataList.push(tree);
        }
        config.data.source = treedataList;
        $.JstreeEx.init(config);//先调用后加载
    });
    return retCurrentPage;
};

//针对于人来操作
var treeSearchs1 = function (url, ids, names, name1,inputValue, cPage , config, FiledList) {

    if (cPage == undefined || cPage == null || cPage == "")
        cPage = 0;
    var retCurrentPage;

    var InitData = {
        "FiledList": [
            {
                "FieldName": name1,
                "FieldOpt": "like",
                "FieldVal": "%" + inputValue + "%"
            },
            {
                "FieldName": name1,
                "FieldOpt": "Order BY"
            }
        ]
    };

    /* if(config1!=""&&config1!=undefined&&config1!=null){
     InitData.FiledList.push(config1);
     }*/
    if(FiledList != null && FiledList != undefined && FiledList != ""){
        console.log(FiledList instanceof Array);
        Array.prototype.push.apply(InitData.FiledList,FiledList);
    }

    var currentPage = cPage;
    var PageInfo = {
        "PageSize": 20,
        "PageIndex": currentPage
    };
    //分页处理
    var treedataList = [];

    request({
        url: url,
        data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(PageInfo),"BusData":JSON.stringify(FiledList)}
    }, function (Body) {
        var treeData = Body.Data;
        if (treeData.length <= 0) {
            currentPage--;
            retCurrentPage = currentPage;
            toastr.warning("无更多记录");
            return;
        }
        retCurrentPage = currentPage;

        var len = treeData.length >= 20 ? 20:treeData.length;
        for (var i = 0; i < len; i++) {
            var tree = {
                id: treeData[i][ids] == undefined ? "" : treeData[i][ids],
                name: treeData[i][names] == undefined ? "" : treeData[i][names]
            };
            treedataList.push(tree);
        }
        config.data.source = treedataList;
        $.JstreeEx.init(config);//先调用后加载
    });
    return retCurrentPage;
};


var close = function () {
    // tab标签在iframe的上面，所以要先获取iframe对象
    var _iframe = parent.$("iframe");
    // 通过ifram对象，找到所有的tab，然后遍历
    _iframe.parent().parent().parent().prev().find("nav").children().children().each(function () {
        // 获取每一个tab对应的class，它是一个字符串
        var classStr = $(this).attr("class");
        // 所以要用空格分割classStr，得到所有的className给一个数组
        var a_classArr = classStr.split(" ");
        var i_obj = null;
        // 通过浏览器查看源码的方式发现：只要是正在展示的内容那么它的class都是两个，所以判断这个数组的长度大于1再进行变量是否含有active
        // -->其实如果能够判断出长度大于1的话，那么就说明这个一定是正在活动的那个tab，这个是通过代码发现的规律
        if (a_classArr.length > 1) {
            i_obj = $(this).children()
            // 把那个×的对象获取之后给关闭按钮一个点击事件，并调用父节点的closeTab方法，把i这个对象传递进去
            $("._close").on("click", function () {
                parent.closeTab(i_obj);
            });
            return false;
        }
    });
}
close();

function setHeight(tableId,_height) {
    /*设置高度*/
    var bodyHeight = document.documentElement.clientHeight * _height;//获取可见区域的高度；
    $("#" + tableId).parent().parent().css('height', bodyHeight + 'px');
    $(window).resize(function () {
        var bodyHeight = document.documentElement.clientHeight * _height;//获取可见区域的高度；
        $("#" + tableId).parent().parent().css('height', bodyHeight + 'px');
    })
}

$(".checkNumber").blur(function(){
    var isNumber= /^\+?[1-9][0-9]*$/;
    if(isNumber.test($(this).val().trim())){
        $(this).val($(this).val().trim());
    }else {
        $(this).val("");
    }
});
// 去掉转义字符
var excludeSpecial = function(s) {

    s = s.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');

    return s;
};

$(".checkFloat").blur(function(){

    if (!/^\d+(\.\d+)?$/.test($(this).val().trim())){
        $(this).val("");
    }
});

//物料信息封装
var Materialinfo = function(InitData , page,xldata){

    if(page==null || page==""){
        request({
            url: "/Material/GetAllMaInfo",
            data: {"ExecType": "00","InitData": JSON.stringify(InitData)}
        }, function (Body) {
            var datas = Body.Data;
            for (var i = 0; i < datas.length; i++) {
                var data = {
                    "MaVerRd": datas[i].MaVerRd,
                    "MaCode": datas[i].MaCode,
                    "MaName": datas[i].MaName,
                    "MaDes": datas[i].MaDes
                };
                xldata.push(data);
            }
        });
    }else{
        request({
            url: "/Material/GetAllMaInfo",
            data: {"ExecType": "00", "PageInfo": JSON.stringify(page), "InitData": JSON.stringify(InitData)}
        }, function (Body) {
            var datas = Body.Data;
            for (var i = 0; i < datas.length; i++) {
                var data = {
                    "MaVerRd": datas[i].MaVerRd,
                    "MaCode": datas[i].MaCode,
                    "MaName": datas[i].MaName,
                    "MaDes": datas[i].MaDes
                };
                xldata.push(data);
            }
        });
    }
    return xldata;
}

// 筛选按钮控件的方法
//函数的入口
$.fn.zc_filter = function (parameter) {
    var data = parameter.params;
    //点击筛选按钮
    var containerr = $('.containerr');
    $(this).on('click', function () {//点击筛选把表格中可以筛选的字段也显示出来
        //取消绑定事件
        var $shaixuan = $(this);
        $shaixuan.unbind('click');
        //把表格对象保存起来
        var filterTableObj = $('#filterTable');
        filterTableObj.empty();
        //显示表格
        containerr.css('display', 'block');
        //1.把要筛选的字段都显示出来
        //1.1开始获取后台的数据
        //1.1.1解析json，遍历每一个key,之后得到它的value的值++++判断是什么类型的，然后将这些类型追加在表格里面
        for (var i in data) {
            //文本类型
            if (data[i].valtype == '00') {
                filterTableObj.append("<tr>" +
                    "<td>" + data[i].caption +
                    "</td>" +
                    "<td>" +
                    "<input type='text' value='' name='" + data[i].name + "'/> " +
                    "</td></tr>");
                //text样式
                var $tab = $("#filterTable");
                $tab.find('input[type="text"]').css({'width': '221px', 'margin-right': '0px'});
                $tab.find('input[name="startDate"]').css('width', '');
                $tab.find('input[name="endDate"]').css('width', '');
                $(".ui-datepicker-date").css("padding-top", "10px");
                continue;
            }
            //下拉框类型
            if (data[i].valtype == '01') {
                var selectStr = "<select name='" + data[i].name + "'>";
                //获取下拉框的字符串
                var optionList = data[i].data;
                var opListArr = optionList.split('|');
                for (var w in opListArr) {
                    var optionObj = JSON.parse("{" + opListArr[w] + "}");
                    for (var key in optionObj) {
                        selectStr += '<option value="' + key + '">' + optionObj[key] + '</option>';
                    }
                }
                filterTableObj.append("<tr><td>" + data[i].caption + "</td><td>" + selectStr + "</td></tr>");
                continue;
            }
            //日期范围控件
            if (data[i].valtype == '02') {
                var $kj = $('.datakj');
                $kj.find("input:eq(0)").attr("name", data[i].name).val("");
                $kj.find("div:eq(0)").css({
                    "top": "60px",
                    "left": "40px",
                    "background-color": "#3c8dbc"
                }).draggable({containment: "body", scroll: false, cursor: "move"});
                var datakj = $kj.css({'display': 'block'}).get(0);
                filterTableObj.append("<tr><td>" + data[i].caption + "</td><td id='td2'></td></tr>");
                $("#td2").get(0).appendChild(datakj);
                continue;
            }
        }
        $('#filterForms .table>tbody>tr>td').css({'border': '0px', 'padding': '5px 0px 5px 10px'});
        //再给筛选一个事件，可以让其继续筛选
        $(this).on('click', function () {
            containerr.css('display', 'block');
            clearForm('filterForms');
        });
    });
    //确认
    $('.sure').on('click', function () {
        //获取填写的相应的内容然后隐藏给后端一个标志
        var formData = transfer('filterForms');
        delete formData.startDate;
        delete formData.endDate;
        var arr = [];
        for (var key in formData) {
            var one = {};
            one[key] = formData[key];
            arr.push(one);
        }
        if (parameter != undefined) {
            if (parameter.event.onsure != undefined) {
                parameter.event.onsure(arr);
            }
        } else {
            console.log('onsure is undefined !');
        }

        //隐藏整个表格和按钮
        containerr.css('display', 'none');
    });
    //取消
    var $kj = $('.datakj');
    $('.unSure').on('click', function () {
        $kj.find("div:eq(0)").css("display", "none");
        containerr.css('display', 'none');
    });
    //点击图片的叉号
    $('.imgClose').on('click', function () {
        $kj.find("div:eq(0)").css("display", "none");
        containerr.css('display', 'none');
    });
    $(".minClose").on('click', function () {
        $kj.find("div:eq(0)").css("display", "none");
    });
    $("#moveDiv").draggable().css("padding", "0.4px");



};




