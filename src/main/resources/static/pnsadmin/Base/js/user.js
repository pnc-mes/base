			$(function() {
			    var dealEven = {
			        init: function() {
			            this.dealAdd(); // 处理点击加号之后添加元素
			            this.dealSpan(); //换符号
			        },
			        dealAdd: function() {
			            $('.add').on('click', function() {
			                $('.down').css('display', 'block');
			                $('.up').css('display', 'none');
			                var table = document.getElementById("myTable");
			                //	newRow.insertCell().innerHTML = newRow.rowIndex;
			                var newRow = table.insertRow(-1);

			                newRow.insertCell(0).innerHTML = " <select onchange='javasript:load(this);' class='form-control input-sm' name='select'>" +
			                    "<option selected=''>查询条件</option>" +
			                    "<option class='0'>下拉框</option>" +
			                    "<option class='1'> 文本框 </option>" + " <option > 查询条件 4 </option> " + "<option> 查询条件 5 </option>" + " </select>";
			                newRow.insertCell(1).innerHTML = "<select class='form-control input-sm' name='select'>" +
			                    "<option selected=''>等于</option>" +
			                    "<option > 大于 </option> <option > 小于 </option> <option > 不等于 </option> <option > 不小于 </option> <option > 不大于 </option> <option > 包括 </optiosn> </select>";
			                newRow.insertCell(2).innerHTML = "<select   class='form-control input-sm' name='select'> <option selected = '' >  值 </option> <option > option 2 </option> <option > option 3 </option> <option > option 4 </option> <option > option 5 </option> </select> <input type='text'  style='display:none;'/>";

			                newRow.insertCell(3).innerHTML = "<span class='glyphicon glyphicon-minus downMenu' onclick='deleteRow(this)' style='cursor: pointer;font-size:14px;color: green;margin-left:13%; '" + ">" + "</span>";


			            })
			        },
			        dealSpan: function() {
			            $('.up').on('click', function() {
			                $('#myTable tr').children(":gt(6)").fadeIn();
			                $(this).css('display', 'none');
			                $('.down').css('display', 'block');
			            })
			            $('.down').on('click', function() {
			                $('#myTable tr').children(":gt(6)").fadeOut();
			                $('.up').css('display', 'block');
			                $(this).css('display', 'none');
			            })
			        }
			    }
			    dealEven.init();
			});
			// 删除一行
			function deleteRow(r) {
			    var i = r.parentNode.parentNode.rowIndex
			    document.getElementById('myTable').deleteRow(i)
			}
			// 处理下拉框或者文本框的显示
			function load(obj) {
			    var a = $(obj).find("option:checked").prop("class");
			    if (a == 0) {
			        $(obj).parent().next().next().children(":eq(0)").show();
			        $(obj).parent().next().next().children(":eq(1)").hide();
			    }
			    if (a == 1) {
			        $(obj).parent().next().next().children(":eq(0)").hide();
			        $(obj).parent().next().next().children(":eq(1)").show();
			    }
			};

			$('#select').on('click', function() {
			    var $modal = $('#modal').modal({
			        show: true
			    });
			})


