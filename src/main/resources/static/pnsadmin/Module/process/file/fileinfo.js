/**
 * Created by liufuzhi on 2017/7/4.
 */

$(function () {
    var parentTreeID = null;
    var childTreeID = null;
    var parentTreeText = null;
    var tempTreeID = null;
    var tempTreeID1 = null;

    // 处理文件上传
    $("#file").change(function () {
        var index = $(this).val().lastIndexOf("\\");
        if (index != -1)
            $("#filePath").val($(this).val().substring(index + 1));
        else
            $("#filePath").val($(this).val());
    })
    //获取第一个子节点信息
    var GetfileVer = function (FileRd) {
        var test = {
            "FileRd": FileRd
        }
        request({
                url: "/File/GetFileVInfo",
                data: {
                    "ExecType": "00",
                    "busData": JSON.stringify(test),
                }
            },
            function (Body) {
                $("#hidden").val(Body.Data.FileRd);
                $("#hidden").attr("h", Body.Data.FileVerRd);
                $("#isDef").removeAttr("checked");
                $("#isDef").prop("disabled", true);
                if ("00" == Body.Data.IsDef) {
                    $("input[type='checkbox']").prop("checked", true);
                }
                else {
                    $("input[type='checkbox']").prop("checked", false);
                    $("#isDef").prop("disabled", false);
                }

                $("#sop").val(Body.Data.FilePath);//文件上传

                $("#zxck").attr("href", Body.Data.FilePath);
                $("#filePath").val(Body.Data.FilePath);
                $("#fileName").val(Body.Data.FileName);
                $("#version").val(Body.Data.Version);
                $("#creatPeople").val(Body.Data.Creator)
                $("#creatTime").val(Body.Data.CreateTime)
                $("#modifyPeople").val(Body.Data.LastModifyMan)
                $("#modifyTime").val(Body.Data.LastModifyTime)
                $("#beizhu").val(Body.Data.Remark)
                $("#Status").find("option").each(function () {
                    if($(this).val() == Body.Data.Status[0].Checked){
                        $(this).prop("selected",true);
                        $(this).siblings().prop("selected",false);
                        return false;
                    }
                });

                $("#filedown").attr("href", Body.Data.FilePath);//文件下载

                var name = Body.Data.FilePath;

                $("#filedown").attr("download", name.substring(name.lastIndexOf("/") + 1));

            });
    };

    /*------------------处理树节点的点击事件----------------*/
    var onClicks = function (nodeinfo, handle) {
        //根节点
        if (nodeinfo.isRoot) {
            $(".right").show();
            $("#filedowns").show();
            $("#zxck").show();
            parentTreeID = nodeinfo.nodeID;
            tempTreeID = nodeinfo.nodeID;
            parentTreeText = nodeinfo.nodeText;
            tempTreeID1 = null;
            $("#hidden").attr("r", "00");

            request({
                    url: "/File/GetFileTreeInfo",
                    data: {
                        "ExecType": "00",
                        "busData": "{\"FileRd\":" + parentTreeID + "}"
                    }
                },
                function (Body) {
                    $("input[type='checkbox']").prop("checked", true);
                    childTreeID = Body.Data.FileVerRd;
                    $("#hidden").val(Body.Data.FileVerRd);

                    var rule = [{
                        id: "FileVerRd",
                        text: "Version"
                    }];

                    handle.ckAddChild(rule, Body.Data);
                    $("#fileName").val(Body.Data.FileName);
                    $("#version").val(Body.Data.Version);
                    $("#creatPeople").val(Body.Data.Creator)
                    $("#creatTime").val(Body.Data.CreateTime)
                    $("#modifyPeople").val(Body.Data.LastModifyMan)
                    $("#modifyTime").val(Body.Data.LastModifyTime)
                    $("#beizhu").val(Body.Data.Remark)
                    GetfileVer(parentTreeID);
                });

        } else {
            $("#filedowns").show();
            $("#zxck").show();
            parentTreeID = null;
            childTreeID = nodeinfo.nodeID;
            $("#hidden").attr("r", "00");
            request({
                    url: "/File/GetFileVInfo",
                    data: {
                        "ExecType": "01",
                        "busData": "{\"FileVerRd\":" + childTreeID + "}"
                    }
                },
                function (Body) {
                    $("#hidden").val(Body.Data.FileRd);
                    $("#hidden").attr("h", Body.Data.FileVerRd);
                    $("#isDef").removeAttr("checked");
                    $("#isDef").prop("disabled", true);
                    if ("00" == Body.Data.IsDef) {
                        $("input[type='checkbox']").prop("checked", true);
                    }
                    else {
                        $("input[type='checkbox']").prop("checked", false);
                        $("#isDef").prop("disabled", false);
                    }

                    $("#sop").val(Body.Data.FilePath);//文件上传

                    $("#zxck").attr("href", Body.Data.FilePath);
                    $("#filePath").val(Body.Data.FilePath);
                    $("#fileName").val(Body.Data.FileName);
                    $("#version").val(Body.Data.Version);
                    $("#creatPeople").val(Body.Data.Creator)
                    $("#creatTime").val(Body.Data.CreateTime)
                    $("#modifyPeople").val(Body.Data.LastModifyMan)
                    $("#modifyTime").val(Body.Data.LastModifyTime)
                    $("#beizhu").val(Body.Data.Remark)
                    $("#Status").find("option").each(function () {
                        if($(this).val() == Body.Data.Status[0].Checked){
                            $(this).prop("selected",true);
                            $(this).siblings().prop("selected",false);
                            return false;
                        }
                    });

                    $("#filedown").attr("href", Body.Data.FilePath);//文件下载

                    var name = Body.Data.FilePath;

                    $("#filedown").attr("download", name.substring(name.lastIndexOf("/") + 1));
                }
            );

        }
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
        currentPage = treeSearchs("/File/GetAllFileInfo","FileRd","FileName","FileName",condition,currentPage,config);
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
            currentPage = treeSearchs("/File/GetAllFileInfo","FileRd","FileName","FileName",condition,currentPage,config);
            if(currentPage < 0){
                condition = "";
                currentPage = temp;
            }
        }
    })

    $("#prev").on("click",function(){
        if(currentPage > 0){
            currentPage --;
            currentPage = treeSearchs("/File/GetAllFileInfo","FileRd","FileName","FileName",condition,currentPage,config);
        }
    });
    $("#next").on("click",function(){
        currentPage ++;
        currentPage = treeSearchs("/File/GetAllFileInfo","FileRd","FileName","FileName",condition,currentPage,config);
    });
    //只刷新树
    var loadtree = function () {
        var InitData = {
            "FiledList":[
                {
                    "FieldName":"FileName",
                    "FieldOpt":"Order BY",
                }

            ]
        }
        var treedataList = [];
        request({
                url: "/File/GetAllFileInfo",
                data: {
                    "ExecType": "00",
                    "InitData":JSON.stringify(InitData)
                }
            },
            function (Body) {
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
                for (var i = 0; i < len; i++) {//版本
                    var vars = {
                        id: treeData[i].FileRd == undefined ? "" : treeData[i].FileRd,
                        name: treeData[i].FileName == undefined ? "" : treeData[i].FileName
                    };
                    treedataList.push(vars);
                }
                //定义树的规则
                config.data.source = treedataList;
                $.JstreeEx.init(config);

                var ttt = $("#fileName").val();

                for (var i in treeData) {
                    if (ttt == treeData[i].FileName) {
                        var FileRd = treeData[i].FileRd;
                        var test = {
                            "FileRd": FileRd
                        }
                        request({
                                url: "/File/GetFileVInfo",
                                data: {
                                    "ExecType": "00",
                                    "busData": JSON.stringify(test),
                                }
                            },
                            function (Body) {
                                $("#hidden").val(Body.Data.FileRd);
                                $("#hidden").attr("h", Body.Data.FileVerRd);
                                $("#isDef").removeAttr("checked");
                                $("#isDef").prop("disabled", true);
                                if ("00" == Body.Data.IsDef) {
                                    $("input[type='checkbox']").prop("checked", true);
                                }
                                else {
                                    $("input[type='checkbox']").prop("checked", false);
                                    $("#isDef").prop("disabled", false);
                                }

                                $("#sop").val(Body.Data.FilePath);//文件上传

                                $("#zxck").attr("href", Body.Data.FilePath);
                                $("#filePath").val(Body.Data.FilePath);
                                $("#fileName").val(Body.Data.FileName);
                                $("#version").val(Body.Data.Version);
                                $("#creatPeople").val(Body.Data.Creator)
                                $("#creatTime").val(Body.Data.CreateTime)
                                $("#modifyPeople").val(Body.Data.LastModifyMan)
                                $("#modifyTime").val(Body.Data.LastModifyTime)
                                $("#beizhu").val(Body.Data.Remark)
                                $("#Status").find("option").each(function () {
                                    if($(this).val() == Body.Data.Status[0].Checked){
                                        $(this).prop("selected",true);
                                        $(this).siblings().prop("selected",false);
                                        return false;
                                    }
                                });

                                $("#filedown").attr("href", Body.Data.FilePath);//文件下载

                                var name = Body.Data.FilePath;

                                $("#filedown").attr("download", name.substring(name.lastIndexOf("/") + 1));

                            });
                    }

                }
            })
    };
    loadtree();


    //新增按钮
    $("#add").click(function () {
        clearForm("fileForm");
        $("#ExecType").val("00");
        parentTreeID = null;
        $(".right").show();
        $("#filedowns").hide();
        $("#zxck").hide();
        $("#version").val("1");
        $("#isDef").prop("checked", true);
        $("#isDef").prop("disabled", true);
        $("#isstatus").val("可用")
    });

    //新增版本
    $("#addVersion").click(function () {
        $("#creatPeople").val("");
        $("#creatTime").val("");
        $("#modifyPeople").val("");
        $("#modifyTime").val("");
        $("#version").val("");
        $("#filePath").val("");
        $("#filedowns").hide();
        $("#zxck").hide();
        $("#hidden").attr("r", "");
        $("#ExecType").val("04");
        childTreeID = null;
        $(".right").show();
        $("#isDef").prop("checked", false);
        $("#isDef").prop("disabled", false);
        $("#isstatus").val("可用")
    });

    //保存
    var newData = {};
    $("#save").click(function () {
        formData = transfer("fileForm");

        var fileName = $("#fileName").val();
        if (fileName == "") {
            toastr.warning("文件名称不能为空");
            return;
        }
        var version = $("#version").val();
        if (version == "") {
            toastr.warning("版本不能为空");
            return;
        }
        var beizhu = $("#beizhu").val();
        var filePath = $("#filePath").val();
        if (filePath == "") {
            toastr.warning("上传文件不能为空");
            return;
        }
      /*  var value2 = $("#isstatus").val();
        var status = "";
        $('#status option').each(function () {
            if (value2 == $(this).val())
                status = $(this).prop("label");
        });*/

        var status = $("#Status").val();

        var aa = $("#hidden").val();
        var fileVerRd = $("#hidden").attr("h");



        if ($("#ExecType").val() == "00" && parentTreeID == null) {
            newData = {
                "FileName": fileName,
                "Version": version,
                "IsDef": "00",
                "Status": status,
                "FilePath": filePath,
                "Remark": beizhu
            };

            if (window.FormData) {
                var formData = new FormData();

                formData.append('upload', document.getElementById('file').files[0]);
                formData.append('ExecType', $("#ExecType").val());
                formData.append('BusData', JSON.stringify(newData));

                uploadRequest({url: "/File/SaveFileInfo", data: formData}, function (Body) {
                    if(Body.MsgCode=="0x00000") {
                        toastr.success(Body.MsgDes);
                        $("#filedowns").show();
                        $("#zxck").show();
                        $("#ExecType").val("");
                        condition = "";
                        currentPage = 0;
                        loadtree();
                    }else{
                        toastr.warning(Body.MsgDes);
                    }
                });


            /*    $.ajax({
                    url: getBasePath() + "/File/SaveFileInfo",
                    type: "POST",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if(data.Body.MsgCode=="0x00000") {
                            toastr.success(data.Body.MsgDes);
                            $("#filedowns").show();
                            $("#zxck").show();
                            $("#ExecType").val("");
                            condition = "";
                            currentPage = 0;
                            loadtree();
                        }else{
                            toastr.warning(data.Body.MsgDes);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error(textStatus);
                    }
                });*/

            }
        }
        else if ($("#hidden").attr("r") == "00") {
            if (fileVerRd == "") {
                toastr.warning("版本不能为空");
                return;
            }
            if ($("#isDef").prop("checked") == true) {
                var istrue = "00"
            } else {
                var istrue = "01"
            }

            newData2 = {
                "FileRd": aa,
                "FileVerRd": fileVerRd,
                "FileName": fileName,
                "Version": version,
                "IsDef": istrue,
                "Status": status,
                "FilePath": filePath,
                "Remark": beizhu
            };
            if (window.FormData) {
                var formData = new FormData();

                formData.append('upload', document.getElementById('file').files[0]);
                formData.append('ExecType', "02");
                formData.append('BusData', JSON.stringify(newData2));

                uploadRequest({url: "/File/SaveFileInfo", data: formData}, function (Body) {
                    if(Body.MsgCode=="0x00000") {
                        toastr.success(Body.MsgDes);
                        $("#filedown").attr("href", filePath);//文件下载
                        $("#zxck").attr("href", filePath);
                        $("#ExecType").val("");
                        condition = "";
                        currentPage = 0;
                        loadtree();
                        $("#hidden").val("");
                        $("#hidden").attr("h", "");
                        $("#hidden").attr("r", "");
                        childTreeID = null;
                        parentTreeID = null;
                    }else{
                        toastr.warning(Body.MsgDes);
                    }
                });

               /* $.ajax({
                    url: getBasePath() + "/File/SaveFileInfo",
                    type: "POST",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if(data.Body.MsgCode=="0x00000") {
                            toastr.success(data.Body.MsgDes);
                            $("#filedown").attr("href", filePath);//文件下载
                            $("#zxck").attr("href", filePath);
                            $("#ExecType").val("");
                            condition = "";
                            currentPage = 0;
                            loadtree();
                            $("#hidden").val("");
                            $("#hidden").attr("h", "");
                            $("#hidden").attr("r", "");
                            childTreeID = null;
                            parentTreeID = null;
                        }else{
                            toastr.warning(data.Body.MsgDes);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error(textStatus);
                    }
                });*/
            }
        } else if ($("#ExecType").val() == "04" && parentTreeID != null && childTreeID == null) {
            var fileName = $("#fileName").val();
            var version = $("#version").val();
            var filePath = $("#filePath").val();
            if ($("input[type='checkbox']").is(':checked') == true) {
                var ck = "00";
            } else {
                var ck = "01";
            }

            var aa = $("#hidden").attr("h");
            newData3 = {
                "FileRd": parentTreeID,
                "FileName": fileName,
                "Version": version,
                "IsDef": ck,
                "Status": status,
                "FilePath": filePath,
                "Remark": beizhu
            };

            if (window.FormData) {
                var formData = new FormData();

                formData.append('upload', document.getElementById('file').files[0]);
                formData.append('ExecType', $("#ExecType").val());
                formData.append('BusData', JSON.stringify(newData3));

                uploadRequest({url: "/File/SaveFileInfo", data: formData}, function (Body) {
                    if(Body.MsgCode=="0x00000") {
                        toastr.success(Body.MsgDes);
                        condition = "";
                        currentPage = 0;
                        loadtree();
                        childTreeID = null;
                        parentTreeID = null;
                        $("#ExecType").val("");
                    }else{
                        toastr.warning(Body.MsgDes);
                    }
                });


            /*    $.ajax({
                    url: getBasePath() + "/File/SaveFileInfo",
                    type: "POST",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if(data.Body.MsgCode=="0x00000") {
                            toastr.success(data.Body.MsgDes);
                            condition = "";
                            currentPage = 0;
                            loadtree();
                            childTreeID = null;
                            parentTreeID = null;
                            $("#ExecType").val("");
                        }else{
                            toastr.warning(data.Body.MsgDes);
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error(textStatus);
                    }
                });*/
            }
        }
    });

    //删除根
    $("#remove").click(function () {
        var aa = $("#hidden").val();

        if(childTreeID!=undefined){
            toastr.warning("删除只针对整个文件操作,请选择对应的文件后再进行删除");
            return false;
        }

        if (aa != null && aa != "" && aa != undefined) {
            var bus = {
                "FileRd": aa
            }
            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                        url: "/File/SaveFileInfo",
                        data: {
                            "ExecType": "01",
                            "busData": JSON.stringify(bus)
                        }
                    },
                    function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        childTreeID = null;
                        aa = null;
                        $("#ExecType").val("");
                        condition = "";
                        currentPage = 0;
                        loadtree();
                        $(".right").hide();
                    });
                tempTreeID1 = 1;
            });
        }
        else
            toastr.warning("请选中文件之后再进行删除!");
    });

    //删除子
    $("#removeVersion").click(function () {

        if (childTreeID != null && childTreeID != "" && childTreeID != undefined) {

            var bus = {
                "FileVerRd": childTreeID
            }

            layer.confirm('确认要删除吗？', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                request({
                        url: "/File/SaveFileInfo",
                        data: {
                            "ExecType": "05",
                            "busData": JSON.stringify(bus)
                        }
                    },
                    function (Body) {
                        layer.closeAll("dialog");
                        toastr.success(Body.MsgDes);
                        childTreeID = null;
                        parentTreeID = null;
                        $("#ExecType").val("");
                        condition = "";
                        currentPage = 0;
                        loadtree();
                        $(".right").hide();
                    });
            });
        } else
            toastr.warning("请选中文件下的版本之后再进行删除!");
    });

    //复制文件
    $("#copy").click(function () {

        if (parentTreeID != null && parentTreeID != "" && parentTreeID != undefined && tempTreeID1 !=1) {

            request({
                    url: "/File/SaveFileInfo",
                    data: {
                        "ExecType": "03",
                        "busData": "{\"FileRd\":" + parentTreeID + "}"
                    }
                },
                function (Body) {
                    toastr.success(Body.MsgDes);
                    childTreeID = null;
                    parentTreeID = null;
                    $("#ExecType").val("");
                    condition = "";
                    currentPage = 0;
                    loadtree();
                });
        } else
            toastr.warning("请选中文件之后再进行复制!");
    });

});