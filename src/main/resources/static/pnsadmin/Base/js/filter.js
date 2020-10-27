/*
//序列化表单数据
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

// 方法
var parameter = {
	"params":params,
	"event":event
};

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
        //显示表格
        containerr.css('display', 'block');
        //1.把要筛选的字段都显示出来
        for (var i in data) {
            //文本类型
            if (data[i].valtype == '00') {
                filterTableObj.append("<tr>" +
                    "<td class='pBottom'>" + data[i].caption +
                    "</td>" +
                    "<td>" +
                    "<input type='text' value='' name='" + data[i].name + "'/> " +
                    "</td></tr>");
                //text样式
                var $tab = $("#filterTable");
                $tab.find('input[type="text"]').css('width', '221px');
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
                filterTableObj.append("<tr><td class='pBottom'>" + data[i].caption + "</td><td>" + selectStr + "</td></tr>");
                continue;
            }
            //日期范围控件
            if (data[i].valtype == '02') {
                var $kj = $('.datakj');
                $kj.find("input:eq(0)").attr("name", data[i].name);
                var datakj = $kj.css('display', 'block').get(0);
                filterTableObj.append("<tr><td class='pBottom'>" + data[i].caption + "</td><td id='td2'></td></tr>");
                $("#td2").get(0).appendChild(datakj);
                continue;
            }
        }
        //样式
        $('.pBottom').css({'padding-bottom':'16px','width': '90px','padding-left':'10px'});
        $("tr>td>input[type='text']").css('margin-right','0px');
        //再给筛选一个事件，可以让其继续筛选
        $(this).on('click', function () {
            containerr.css({'display':'block','width':'100px'});
        });
    });
    //确认
         //获取填写的相应的内容然后隐藏给后端一个标志
        var formData = transfer('filterForms');
        delete formData.startDate;
        delete formData.endDate; 
		if(parameter.event != undefined){
			if(parameter.event.onsure != undefined){
				parameter.event.onsure(formData);
			}
		}else{
			console.log('onsure is undefined');
		}
        //隐藏整个表格和按钮
        containerr.css('display', 'none');
    /!*});*!/
    //取消
    $('.unSure').on('click', function () {
        containerr.css('display', 'none');
    });
    //点击图片的叉号
    $('.imgClose').on('click', function () {
        containerr.css('display', 'none');
    });
    $( "#moveDiv" ).draggable();
};
//清空表单数据
function clearForm(formId) {
    $("#" + formId).find('input, textarea').each(function () {
        $(this).val("");
    });
};*/
