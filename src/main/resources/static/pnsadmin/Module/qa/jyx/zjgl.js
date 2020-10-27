$(function () {
//用来标志表格的按钮是唯一的
    var addid = 1;
    var tableid = 1;
    var aa = [];
    var treeID = null;
    var tbodyaddid = 1;
    /*-----------2:点击树的节点的时候获取技能信息----------------------------*/
    var onClicks = function (nodeinfo, handle) {
        tableid = 1;
        tbodyaddid = 1;
        // window.location.reload()
        $("#_right").show();//每次点击树节点的时候都把右侧展开
        treeID = nodeinfo.nodeID;
        var objBusData = JSON.stringify({"QCheckReportRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData,
        }
        request({url: "/SunPort/QCMR/GetQCMRInfo", data: objData}, function (Body) {
            $("#QCheckReportCode").val(Body.Data.QCheckReportCode);  //检验报告
            $("#QCheckMaCode").val(Body.Data.QCheckMaCode);
            $("#MaInfo").val(Body.Data.MaInfo.MaName + Body.Data.MaInfo.MaDes);
            if ("00" == Body.Data.QCheckMaType) {
                $("#aa").val("生产入库请检");
            } else {
                $("#aa").val("销售出库请检");
            }
            // $("#QCheckMaType").val(Body.Data.QCheckMaType);
            $("#CheckNum").val(Body.Data.CheckNum);
            $("#SChecker").val(Body.Data.SChecker);
            $("#SourceName").val(Body.Data.SourceName);
            $("#Status").val(Body.Data.Status);
            $("#creatTime").val(Body.Data.CreateTime);
            if (Body.Data.FinalResult == "00") {
                $("#hege").prop("checked", true);
            } else if (Body.Data.FinalResult == "01") {
                $("#buhege").prop("checked", true);
            }
            //   $("#defaultSelect").val(Body.Data.Reviewer)
            $("#defaultSelect").val(Body.Data.Reviewer);
            $("#Inspector").val(Body.Data.Checker);
            /*         var s="1-2-3-4-5";
                     var arr2 =[];
                     arr2 =s.split("-");
                     alert(JSON.stringify(arr2))
                     if(arr2.length>0){
                         for (var i = 0; i < arr2.length; i++) {
                             $("#FileName").text(arr2[i].FileName)
                             //         $('#FileName').attr('href',arr2[i].FileUrl);
                         }
                     }
                     */


            //处理null
            if (Body.Data.Checker == null) {
                $("#Checker").text("");
            } else {
                $("#Checker").text(Body.Data.Checker);
            }
            //处理null
            if (Body.Data.CheckRemark == null) {
                $("#CheckRemark").text("");
            } else {
                $("#CheckRemark").text(Body.Data.CheckRemark);
            }
            if (Body.Data.Files.length > 0) {
                var busData = Body.Data.Files;
                var CTDtlInfo = [];
                for (var i = 0; i < busData.length; i++) {
                    $("#FileName").text(busData[i].FileName)
                    $('#FileName').attr('href', busData[i].FileUrl);
                }
            }
            // aa检验报告AQL信息 bb检验报告明细
            var bb = [];
            aa = Body.Data.ReportDtl;
            if (aa.length > 0) {
                var div_ = "";
                for (var i in aa) {
                    var xuhao = 1;
                    var sum = 0;
                    if (aa[i].CheckItem.length > 0) {
                        var tbody_ = "<tr><td style='text-align: center;'>序号</td><td style='text-align: center;'>检验项名称</td><td style='text-align: center;'>检验方法</td><td style='text-align: center;'>检验内容</td><td style='text-align: center;'>确认方式</td><td style='text-align: center;'>不合格数量</td></tr>";
                        bb = aa[i].CheckItem;
                        for (var j in bb) {
                            sum += parseInt(bb[j].RejNum)
                            var SureType = bb[j].SureType;
                            var resltType = "";
                            var texttype = "";
                            if (SureType == "00") {
                                resltType == "00";
                                texttype = "<input type='radio'> </input>OK<input type='radio'> </input>NG"
                            } else {
                                resltType == "01";
                                texttype = "<input style='width: 100%' type='button' id='button' a=" + addid + "  value='填写'> </input>"
                            }
                            tbody_ += "<tr a='" + bb[j].ReportItemRd + "'  b='" + resltType + "'  id='tr" + tbodyaddid + "' ><td>" + xuhao + "</td><td style='width: 160px;text-align: center; '>" + bb[j].CheckItemName + "</td><td style='width: 160px;text-align: center;'>" + bb[j].CheckItemC + "</td><td style='text-align: center;'>" + bb[j].CheckMethodName + "</td><td style='text-align: center;' f=" + bb[j].SureResult + ">" + texttype + "</td><td style='text-align: center;'>" + '<input type="text" id="inputblur"  style="width: 100%;text-align: center;";" value=' + bb[j].RejNum + ' ></input>' + "</td></tr>";//
                            xuhao++;
                            addid++;
                            tbodyaddid++;
                        }
                    }
                    div_ += "<table class='table" + addid + "' id='table" + tableid + "'  a=" + addid + " border='1' style='line-height: 35px;width: 800px;'><thead><tr bgcolor='#a9a9a9'><th colspan=2 style='text-align: center;'>抽样水平= " + aa[i].CheckLevelName + "</th><th style='text-align: center;'>AQL=" + aa[i].AQLRuleName + "</th><th style='width: 160px;text-align: center;'>抽样数量=" + aa[i].SamplingNum + "</th><th  id='sumadd" + addid + "' style='width: 160px;text-align: center;'>不合格数量=" + aa[i].AcceptNum + "</th><th style=' 80%;text-align: center;'>AC/RE=" + aa[i].RejectNum + "</th></tr></thead>"
                        + "<tbody id='tbody" + tableid + "'>" + tbody_ + "</tbody></table>";
                    tableid++;

                }
                $("#tabledetail").empty().html(div_);

            } else {
                $("#tabledetail").empty();
            }

        });
    }
    //筛选
    var params = [{
        "caption": "检验报告号",
        "name": "QCheckReportCode",
        "valtype": "00"
    }, {
        "caption": "请检单号",
        "name": "QCheckMaCode",
        "valtype": "00"
    }, , {
        "caption": "时间段",
        "name": "CreateTime",
        "valtype": "02"
    }];
    var InitData1 = {};
    var event = {
        onsure: function (result) {
            var FiledList = [];
            for (var i in result) {
                for (var j in result[i]) {
                    if (result[i][j].trim() != "" && result[i][j] != null) {
                        if (j == "CreateTime") {
                            var Filed = {
                                "FieldName": j,
                                "FieldOpt": ">=",
                                "FieldVal": result[i][j].split("|")[0]
                            };
                            FiledList.push(Filed);
                            Filed = {
                                "FieldName": j,
                                "FieldOpt": "<=",
                                "FieldVal": result[i][j].split("|")[1] + " 23:59:59"
                            };
                            FiledList.push(Filed);
                            break;
                        }
                        var Filed = {
                            "FieldName": j,
                            "FieldOpt": "like",
                            "FieldVal": "%" + result[i][j] + "%"
                        };
                        FiledList.push(Filed);
                    }
                }
            }
            InitData1 = {
                "FiledList": FiledList
            };

            var trees = [];

            request({
                url: '/SunPort/QCMR/GetAllQCMRInfo',
                data: {"ExecType": "00", "InitData": JSON.stringify(InitData1)}
            }, function (Body) {
                currentPage = 0;
                var treeData = Body.Data;
                if (treeData.length > PageInfo.PageSize) {
                    $pages.css('display', 'block');
                } else {
                    $pages.css('display', 'none');
                }
                var len = treeData.length >= 20 ? 20 : treeData.length;
                for (var i = 0; i < len; i++) {
                    var tree = {
                        id: treeData[i].QCheckReportRd == undefined ? "" : treeData[i].QCheckReportRd,
                        name: treeData[i].QCheckReportCode == undefined ? "" : treeData[i].QCheckReportCode
                    }
                    trees.push(tree);
                }
                config.data.source = trees;
                $.JstreeEx.init(config);//先调用后加载
            });
            delete InitData1.FiledList['QCheckReportCode'];
            list = InitData1.FiledList;
        }
    };

    var config3 = {
        params: params,
        event: event
    };

    $(".cSelect").zc_filter(config3);

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
        "PageSize": 20,
        "PageIndex": currentPage
    };

    $(".searchTree").click(function () {
        condition = $(".input1").val().trim();
        var temp = currentPage;
        currentPage = 0;
        currentPage = treeSearchs("/SunPort/QCMR/GetAllQCMRInfo", "QCheckReportRd", "QCheckReportCode", "QCheckReportCode", condition, currentPage, config);
        if (currentPage < 0) {
            condition = "";
            currentPage = temp;
        }
    });

    $(".input1").keydown(function (event) {
        if (event.keyCode == 13) {
            condition = $(".input1").val().trim();
            var temp = currentPage;
            currentPage = 0;
            currentPage = treeSearchs("/SunPort/QCMR/GetAllQCMRInfo", "QCheckReportRd", "QCheckReportCode", "QCheckReportCode", condition, currentPage, config);
            if (currentPage < 0) {
                condition = "";
                currentPage = temp;
            }
        }
    })
    $("#prev").on("click", function () {
        if (currentPage > 0) {
            currentPage--;
            currentPage = treeSearchs("/SunPort/QCMR/GetAllQCMRInfo", "QCheckReportRd", "QCheckReportCode", "QCheckReportCode", condition, currentPage, config);
        }
    });
    $("#next").on("click", function () {
        currentPage++;
        currentPage = treeSearchs("/SunPort/QCMR/GetAllQCMRInfo", "QCheckReportRd", "QCheckReportCode", "QCheckReportCode", condition, currentPage, config);
    });
    $("#_right").hide();

    /*    //下拉框
        var params = {
            "displaymode": "0",
            "title": "用户",
            "binddata": {
                "keyfield": "UserRd",
                "fields": [
                    {
                        "caption": "用户id",
                        "name": "UserRd"
                    }, {
                        "caption": "真实姓名",
                        "name": "RealName"
                    }
                ]
            },
            "showresult": {
                "ishead": true
            },
            "event": {
                "onseardata": function (o) {
                    var InitData = {
                        "FiledList":[
                            {
                                "FieldName": "RealName",
                                "FieldOpt": "Order BY"
                            }
                        ]
                    };
                    var page = {
                        PageIndex: "0",
                        PageSize: o.num
                    };
                    var xldata = [];
                    //加载下拉框企业信息
                    request({url: "/User/GetAllUserInfo", data: {"ExecType": "00","InitData":JSON.stringify(InitData), "PageInfo": JSON.stringify(page)}}, function (Body) {
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
        };

        $("#defaultSelect").zc_select(params);*/
    /*-------- 4:只刷新树(提高性能)---------*/

    var loaddata = function () {
        var trees = [];
        var pageInfo = {
            "PageSize": 21,
            "PageIndex": 0
        };
        var InitData = {
            "FiledList": [
                {"FieldName": "CreateTime DESC", "FieldOpt": "Order BY"}
            ]
        }
        request({
            url: '/SunPort/QCMR/GetAllQCMRInfo',
            data: {"ExecType": "00", "InitData": JSON.stringify(InitData), "PageInfo": JSON.stringify(pageInfo)}
        }, function (Body) {
            var treeData = Body.Data;
            if (treeData.length > PageInfo.PageSize) {
                $pages.css('display', 'block');
            } else {
                $pages.css('display', 'none');
            }
            if (treeData.length <= 0) {
                return false;
            }
            var len = treeData.length >= 20 ? 20 : treeData.length;
            for (var i = 0; i < len; i++) {
                var tree = {
                    id: treeData[i].QCheckReportRd == undefined ? "" : treeData[i].QCheckReportRd,
                    name: treeData[i].QCheckReportCode == undefined ? "" : treeData[i].QCheckReportCode
                }
                trees.push(tree);
            }
            /*----------------------定义控件规则-------------------*/
            config.data.source = trees;
            $.JstreeEx.init(config);//先调用后加载
        });
    };
    loaddata();


    /*    //删除
        $(".cDel").click(function () {
            if(treeID==null){
                toastr.warning("请选择左侧要删除的一项再进行删除!");
                return false;
            }
            layer.confirm('确认要删除吗？', {
                    btn: ['确认', '取消'], //按钮
                }, function () {
                    request({url:"/AQL/SaveAQLInfo", data: {"ExecType": "01", "busData": "{\"AQLRuleRd\":" + treeID + "}"}},function(Body){
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        $("#_right").hide();
                        loaddata();
                        treeID = null;
                        currentPage=0;
                        condition = '';
                    });
                }
            );
        });*/
    //导出
    $(".dChu").click(function () {
        var objBusData = JSON.stringify({"QCheckReportRd": treeID});
        var objData = {
            "ExecType": "00",
            "BusData": objBusData
        };
        request({url: "/SunPort/QCMR/GetQCMRInfo", data: objData}, function (Body) {
            if (Body.Data.Status == "00") {
                toastr.warning("该单还未确认，不能导出");
                return;
            } else {
                $("#QCheckReportRd").val(treeID);
                tijiao();
            }

        });


    });

    function tijiao() {
        document.getElementById("storeInfo").submit();//表单提交
    }

    $(document).on("click", "#button", function () {
        var addid = $(this).attr("a");
        $("#submits").attr("a", addid);
        $('#myModal').modal('show')
    });

    $("#submits").click(function () {
        var value = $("#textarea").val();

        //遍历所有表格所有的行
        $("table input[type='button']").each(function () {
            var thisid = $("table input[type='button']").attr("a");
            if (thisid == $(this).attr("a")) {
                $("table input[type='button']").attr("b", value);
            }
        });
        $('#myModal').modal('hide')

    });

    $(document).on("blur", "#inputblur", function () {
        //文本框对应的表格
        // alert($(this).parent().parent().parent().parent().attr("class"))
        var sum = 0;
        var ceshi = $("." + $(this).parent().parent().parent().parent().attr('class') + " input[type='text']");
        $.each(ceshi, function () {
            var num = $(this).val();
            sum += parseInt(num)
        })
        var id = $(this).parent().parent().parent().parent().attr("a")
        //遍历表格所有行，进行数量累加  sum=99;

        var nnum = 0;
        nnum += parseInt(sum + sum - sum)
        $("#sumadd" + parseInt(id)).text("不合格数量=" + nnum);

    });

    /*--------------顶部菜单点击保存按钮----------*/
    var newData = {};
    $(".cSave").click(function () {
        save();
    });

    function save() {
        if (tbodyaddid > 0) {
            var CheckItem = [];
            for (var i = 1; i <= tbodyaddid - 1; i++) {
                var CheckItems = {
                    "ReportItemRd": $("#tr" + i).attr("a"),
                    "SureResult": $("#tr" + i).attr("b"),
                    "RejNum": $("#tr" + i).children("td:eq(5)").find("input").val()
                }
                CheckItem.push(CheckItems);
            }
        }

        // 获取所有表单数据封装成json对象
        formData = transfer("storeInfo");
        var CheckRemark = $("#CheckRemark").val()
        //保存信息
        if (treeID != null && treeID != "") {
            var FinalResult = "";
            if ($("#hege").is(":checked")) {
                FinalResult = "00";
            }
            if ($("#buhege").is(":checked")) {
                FinalResult = "01";
            }
            var Reviewer = $("#defaultSelect").val();
            var Inspector = $("#Inspector").val();
            if (FinalResult == null || FinalResult == "") {
                toastr.warning("请确认最终检验结果!");
                return false;
            }
            if (Reviewer == null || Reviewer == "") {
                toastr.warning("请填写核准人!");
                return false;
            }
            if (Inspector == null || Inspector == "") {
                toastr.warning("请填写质检员!");
                return false;
            }
            newData = {
                "QCheckReportRd": treeID,
                "Reviewer": Reviewer,
                "Checker": Inspector,
                "FinalResult": FinalResult,
                "CheckRemark": CheckRemark,
                "CheckItem": CheckItem
            };
            request({
                url: "/SunPort/QCMR/SaveQCMRInfo",
                data: {"ExecType": "00", "busData": JSON.stringify(newData)}
            }, function (Body) {
                toastr.success(Body.MsgDes);
                $("#ExecType").val("");
                currentPage = 0;
                condition = '';
                loaddata();
            });
        }
    }

    /*--------------顶部菜单点击确定按钮----------*/
    var newData1 = {};
    $(".cConfirm").click(function () {
        var FinalResult = "";
        if ($("#hege").is(":checked")) {
            FinalResult = "00";
        }
        if ($("#buhege").is(":checked")) {
            FinalResult = "01";
        }
        var Reviewer = $("#defaultSelect").val();
        var Inspector = $("#Inspector").val();
        if (FinalResult == null || FinalResult == "") {
            toastr.warning("请确认最终检验结果!");
            return false;
        }
        if (Reviewer == null || Reviewer == "") {
            toastr.warning("请填写核准人!");
            return false;
        }
        if (Inspector == null || Inspector == "") {
            toastr.warning("请填写质检员!");
            return false;
        }
        if (treeID == null || treeID == "") {
            toastr.warning("请选择左侧数据!");
            return false;
        }
        newData1 = {
            "QCheckReportRd": treeID
        };
        App.blockUI({
            target: "body",
            message: "正在处理中...",
            boxed: true
        });
        layer.confirm('一旦确认操作后将不可修改！', {
            btn: ['确认', '取消'], //按钮
        }, function () {
            $.ajax({
                url: getBasePath() + "/SunPort/QCMR/SaveQCMRInfo",
                type: "POST",
                data: {"ExecType": "01", "busData": JSON.stringify(newData1)},
                dataType: "json",
                beforeSend: function () {
                },
                complete: function () {
                    App.unblockUI("body");
                },
                success: function (Body) {
                    setTimeout("App.unblockUI('body')", 3000);
                    if (Body.Body.MsgCode != "0x00000") {
                        toastr.warning(Body.Body.MsgDes);
                    } else {
                        toastr.success(Body.Body.MsgDes);
                    }
                },
                error: function () { //请求数据失败
                    toastr.warning("服务器繁忙!");
                }
            });
            layer.closeAll("dialog");
        });
    });

    /* //打印 适配IE
     $(".print").click(function () {
         alert("打印测试");
         var Template = "http://192.168.20.230:8087/group1/M00/00/01/wKgU5lzQ4eWANr-OAADtOpZaUIQ438.xls";
         alert("打印测试");
         var xlApp = new ActiveXObject("Excel.Application");
         alert("打印测试");
         var xlBook = xlApp.Workbooks.add(Template);
         alert("打印测试");
         var xlsheet = xlBook.ActiveSheet;
         alert("打印测试");
         xlsheet.printout;
         xlApp = null;
         xlsheet = null;
         alert("打印测试");
     });*/


    $(".print").click(function () {
        /* var iframe = document.createElement('IFRAME');
        var doc = null;
        document.body.appendChild(iframe);
        doc = iframe.contentWindow.document;
        //保存写入内容
        var newContent = "<html><head><meta charset='utf-8'/><title>打印</title></head><body  Style='border: 1px dashed rgb(255, 255, 255); '>"
        newContent += document.getElementById("print-content").outerHTML;
        newContent += document.getElementById("print-content1").outerHTML;
        newContent += "</body></html>";
        doc.write(newContent);//放入需要打印的内容，最好拼接为一个完整的html
        doc.close();
        iframe.contentWindow.focus();
        iframe.contentWindow.print();*/


        //打印 适配chrome
        if (treeID == null || treeID == "") {
            toastr.warning("请选择要导出的检验报告");
            return;
        }
        $("#myModal_1").modal("show");
        $("#modelItem").val("00");
        document.getElementById("BigDiv").style.width = 500 + "px";
        document.getElementById("BigDiv").style.height = 250 + "px";
        $("#khnamediv").hide();
        $("#imags").attr("src", "");
        $("#AppLogo").val("");
    });

    $("#hhsave").click(function () {
        var datas;
        request({
            url: "/SunPort/QCMR/GetAllQcMaInfo",
            data: {"QCheckReportRd": treeID, "FinalResult": $("#modelItem").val()}
        }, function (Body) {
            datas = Body.Data;
        });
        if (datas.length == 0) {
            toastr.warning("检验报告没有对应的箱号！");
            return false;
        }
        //区分单多晶
        if ($("#modelItem").val() == "01") { //国际版本
            $(".chain").hide();
            if ($("#type").val() == "00") { //单晶
                document.getElementById("ProductType").innerText = "High-Efficiency Mono-Si ";
                document.getElementById("ProductName").innerText = "";
            } else { //多晶
                document.getElementById("ProductType").innerText = "High-Efficiency Poly-Si ";
                document.getElementById("ProductName").innerText = "";
            }
            var AppLogo = $("#AppLogo").val();
            if (AppLogo == "" || AppLogo == undefined) {
                toastr.warning("请上传LOGO图片！");
                return false;
            }
            $("#images").attr("src", AppLogo);
        } else { //默认版本
            $(".chain").show();
            if ($("#type").val() == "00") { //单晶
                document.getElementById("ProductType").innerText = "High-Efficiency Mono-Si ";
                document.getElementById("ProductName").innerText = "高效单晶硅组件";
            } else { //多晶
                document.getElementById("ProductType").innerText = "High-Efficiency Poly-Si ";
                document.getElementById("ProductName").innerText = "高效多晶硅组件 ";
            }
        }
        debugger;
        var newWindow;
        //打开一个新的窗口
        newWindow = window.open();
        //获得焦点
        newWindow.focus();
        datas.forEach(function (object, index, array) {
            document.getElementById("Model").innerText = object.Model;
            document.getElementById("CTNNo").innerText = object.CTNNo;
            document.getElementById("ProductionDate").innerText = object.ProductionDate;
            document.getElementById("Qty").innerText = object.Qty + "pcs";
            document.getElementById("InspectionDate").innerText = object.InspectionDate;
            document.getElementById("Inspector1").innerText = object.Inspector;
            document.getElementById("Approval").innerText = object.Approval;
            //保存写入内容
            var newContent = "<html><head><meta charset='utf-8'/><title>打印</title></head><body  Style='border: 1px dashed rgb(255, 255, 255); '>"
            newContent += document.getElementById("print-content").outerHTML;
            newContent += document.getElementById("print-content1").outerHTML;
            newContent += document.getElementById("print-content2").outerHTML;
            newContent += "</body></html>";
            // 将HTML代码写入新窗口中
            newWindow.document.write(newContent);
        });
        newWindow.print();
        // close layout stream
        newWindow.document.close();
        //关闭打开的临时窗口
        newWindow.close();
        $("#myModal_1").modal("hide");
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        $("#images").attr("src", localhostPaht + projectName + "/static/pnsadmin/Base/images/admin/sunprotlogo.png");
        return false;
    });

    //图片上传功能
    $("#pushImg").click(function () {
        var formData = new FormData();
        var img_file = document.getElementById("imageFile");
        var Obj = img_file.files[0];
        formData.append("classIcon", Obj);
        $.ajax({
            url: getBasePath() + "/AppSet/upload",
            type: "POST",
            data: formData,
            async: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.Body.MsgCode == "0x00000") {
                    $("#imags").attr("src", data.Body.Data.file);
                    $("#AppLogo").val(data.Body.Data.file);
                    toastr.success("图片上传完成！");
                } else {
                    toastr.warning("图片上传失败" + data.Body.MsgDes);
                }
            }
        });
    });

    $("#modelItem").change(function () {
        if ($("#modelItem").val() == "01") {
            document.getElementById("BigDiv").style.width = 500 + "px";
            document.getElementById("BigDiv").style.height = 400 + "px";
            $("#khnamediv").show();
        } else {
            document.getElementById("BigDiv").style.width = 500 + "px";
            document.getElementById("BigDiv").style.height = 250 + "px";
            $("#khnamediv").hide();
        }
    });

});