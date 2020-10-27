/**
 * Created by xfxi on 2017/7/2.
 */
$(function(){

    /*--------------------------表格----------------------------*/
    //进出站
    var colNamesArr = [
        { "Caption":"生产批次", "Name":"Batch", "CType":"text",Width:100},
        { "Caption":"当前工序", "Name":"SpecName", "CType":"text",Width:80},
        { "Caption":"物料代码", "Name":"MaCode", "CType":"text",Width:100},
        { "Caption":"物料名称", "Name":"MaName", "CType":"text"},
        { "Caption":"物料描述", "Name":"MaDes", "CType":"text"},
        {  "Caption":"数量", "Name":"Num", "CType":"text",Width:80},
        {  "Caption":"单位", "Name":"UnitName", "CType":"text",Width:50}
        /*{  "Caption":"作业", "Name":"OptName", "CType":"text", "Hidden":true}*/
    ];

    var config1 = {
        tableId: "list1",
        data: [],
        colArr:colNamesArr,
        width: 0.715,height:0.4
    };

    var config2 = {
        tableId: "list2",
        data: [],
        colArr:colNamesArr,
        width: 0.715,height:0.4
    };

    fullTable(config1);
    fullTable(config2);

    //物料清单
    var colNamesArr3 = [
        {  "Caption":"输入消耗信息", "Name":"materialname", "CType":"autocount", "Editable":true,"AutoCountPr": {
            "DisplayName": "DoBatch",
            "DataValue": "Num",
            "CkRepeat":"00",
            "RepeatMsg":"消耗信息已经存在!",
        }},
        { "Caption":"MaVerRd", "Name":"MaVerRd", "CType":"text", "Hidden":true},
        { "Caption":"Data", "Name":"Data", "CType":"text", "Hidden":true},
        {"Caption":"需求量", "Name":"AllNum", "CType":"text",Width:80},
        {"Caption":"已扫描量", "Name":"Num", "CType":"text",Width:80},
        { "Caption":"物料代码", "Name":"MaCode", "CType":"text",Width:100},
        { "Caption":"物料名称", "Name":"MaName", "CType":"text"},
        /*{ "Caption":"物料描述", "Name":"MaDes", "CType":"text"},*/
        { "Caption":"品牌", "Name":"Brand", "CType":"text", Width:80},
        { "Caption":"订货号", "Name":"OrderNum", "CType":"text", Width:80},
        {  "Caption":"单位用量", "Name":"DoNum", "CType":"text",Width:80},
        /*{  "Caption":"单位", "Name":"UnitName", "CType":"text",Width:50},*/
        {  "Caption":"输入消耗信息", "Name":"DoBatch", "Editable":true, "CType":"text",Width:100,"Hidden":true},
        /*{  "Caption":"记住输入", "Name":"Remember", "CType":"checkbox", "Editable":true},*/
        { "Caption":"消耗方式", "Name":"ConSMode", "CType":"text", "Hidden":true},
        { "Caption":"用料量", "Name":"DoNum", "CType":"text", "Hidden":true,Width:80},
        { "Caption":"是否检查", "Name":"Checked", "CType":"text", "Hidden":true}
    ];
    var colNamesArr3_1 = [
        { "Caption":"MaVerRd", "Name":"MaVerRd", "CType":"text", "Hidden":true},
        { "Caption":"Data", "Name":"Data", "CType":"text", "Hidden":true},
        { "Caption":"物料代码", "Name":"MaCode", "CType":"text"},
        { "Caption":"物料名称", "Name":"MaName", "CType":"text"},
        { "Caption":"物料描述", "Name":"MaDes", "CType":"text"},
        {  "Caption":"用量", "Name":"DoNum", "CType":"text",Width:80},
        {  "Caption":"单位", "Name":"UnitName", "CType":"text",Width:50},
        {  "Caption":"输入消耗信息", "Name":"DoBatch", "CType":"text", "Hidden":true}
    ];
    var config3 = {
        tableId: "list3",
        data: [],
        colArr:colNamesArr3,
        width: 0.715,height:0.51
    };
    fullTable(config3);

    //数据采集
    var colNamesArr4 = [
        { "Caption":"DCItemRd", "Name":"DCItemRd", "CType":"text", "Hidden":true},
        { "Caption":"采集内容", "Name":"ItemName", "CType":"text"},
        { "Caption":"采集值", "Name":"CValue", "CType":"text", "Editable":true}
    ];
    var config4 = {
        tableId: "list4",
        data: [],
        colArr:colNamesArr4,
        width: 0.715,height:0.45
    };
    fullTable(config4);

    //不良代码采集
    var colNamesArr5 = [
        { "Caption":"不良代码", "Name":"ReaCode", "CType":"text"},
        { "Caption":"不良表述", "Name":"ReaDes", "CType":"text"}
    ];
    var config5 = {
        tableId: "list5",
        data: [],
        colArr:colNamesArr5,
        width: 0.715,height:0.45,
        multiselect:true
    };
    fullTable(config5);

    //记录输入批次信息
    var iptData = new Map();

    /*--------------------------end----------------------------*/

    //查询
    $("#_query").click(function(){

        var batch = $("#Batch").val().trim();

        getSpecB(batch);

    });

    //回车查询
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#Batch").is(":focus")){
                var batch = $("#Batch").val().trim();

                getSpecB(batch);
            }
        }
    });

    //确定
    $("#_sure").click(function () {
        //批次号、工序版本Rd
        var batch = $("#Batch").val().trim();
        if(batch == undefined || batch == ""){
            toastr.warning("批次不能为空");
            return;
        }
        var specVerRd = $("#SpecVerRd").val();
        if(specVerRd == undefined || specVerRd == ""){
            toastr.warning("工序不能为空");
            return;
        }

        //数据采集
        var doDCInfo = getRowData("list4");
        for(var i=0; i<doDCInfo.length; i++){
            delete doDCInfo[i]["ReferVal"];
            if(doDCInfo[i]["CValue"] == undefined || doDCInfo[i]["CValue"] == "undefined"){
                doDCInfo[i]["CValue"] = "";
            }
        }

        //不良代码采集
        var doRCInfo = getRowData("list5");

        var busData = {
            "Batch": batch,
            "SpecVerRd": specVerRd,
            "DoDCInfo": doDCInfo,
            "DoRCInfo": doRCInfo
        };

        saveIOS("01", JSON.stringify(busData));
    });

    //进站
    $("#_in").click(function(){

        var batch = $("#Batch").val().trim();
        var specVerRd = $("#SpecVerRd").val();

        saveIOS("00", "{\"Batch\":\"" + batch + "\" , \"SpecVGd\":\"" + specVerRd + "\"}");
    });

    //出站
    $("#_out").click(function(){

        //批次号、工序版本Rd、消耗物料、采集信息
        var batch = $("#Batch").val().trim();
        var specVerRd = $("#SpecVerRd").val();

        var b = true;
        var num = $("#Num").val();
        if(isNaN(num)){
            toastr.warning("批次数量非数字");
            return;
        }
        if(num<=0){
            toastr.warning("批次数量不能小于零");
            return;
        }else if(num>startNum){
            toastr.warning("批次数量不能大于批次实际数量");
            return;
        }else if(num != startNum){
            b = false;
            var f = true;
            layer.confirm('当前生产批次为不足量生产,你确认要进行出站吗?如果确认,则系统将进行强制拆批操作', {
                btn: ['确认', '取消'], //按钮
            }, function () {
                layer.closeAll("dialog");
                if(f) {
                    f = false;
                    aa(batch, specVerRd, num);
                }
            });
        }

        if(b){
            aa(batch, specVerRd, num);
        }
    });

    function aa(batch, specVerRd, num){
        //物料消耗
        var doMaInfo = [];
        var _doMaInfo = getRowData("list3");

        try {
            _doMaInfo.forEach(function (obj) {
                var dm = {
                    "MaVerRd": obj.MaVerRd,
                    "ConSMode": obj.ConSMode,
                    "DoNum": obj.DoNum
                };
                /*if (obj.Data == undefined || obj.Data == "undefined" || obj.Data == "") {
                    //throw new Error();
                }else{
                    dm.DoBaInfo = JSON.parse(obj.Data);
                }*/
                if(obj.materialname != undefined && obj.materialname.length > 0){
                    var data = obj.materialname;
                    data.forEach(function(o){
                        delete o.Num;
                    });
                    dm.DoBaInfo = data;
                }else{
                    if(obj.ConSMode == "00"){
                        toastr.warning("未刷料号");
                        throw error;
                    }else if(obj.ConSMode == "02" && obj.Checked == "00"){
                        toastr.warning("未刷SN号");
                        throw error;
                    }
                }

                doMaInfo.push(dm);
            });
        }catch (e){
            //toastr.warning("未刷物料");
            return;
        }

        //数据采集
        var doDCInfo = getRowData("list4");
        for(var i=0; i<doDCInfo.length; i++){
            delete doDCInfo[i]["ReferVal"];
            if(doDCInfo[i]["CValue"] == undefined || doDCInfo[i]["CValue"] == "undefined"){
                doDCInfo[i]["CValue"] = "";
            }
        }

        //不良代码采集
        var doRCInfo = getRowData("list5");

        var busData = {
            "Batch": batch,
            "SpecVerRd": specVerRd,
            "Num": num,
            "DoMaInfo": doMaInfo,
            "DoDCInfo": doDCInfo,
            "DoRCInfo": doRCInfo
        };

        saveIOS("01", JSON.stringify(busData));
    }

    //上机
    $("#_up").click(function(){

        //批次号、工序版本Rd、设备Rd
        var batch = $("#Batch").val().trim();
        if(batch == undefined || batch == ""){
            toastr.warning("批次不能为空");
            return;
        }
        var specVerRd = $("#SpecVerRd").val();
        if(specVerRd == undefined || specVerRd == ""){
            toastr.warning("工序不能为空");
            return;
        }
        var devRd = $("#devg").val();
        if(devRd == undefined || devRd == ""){
            toastr.warning("设备不能为空");
            return;
        }
        var busData = {
            "Batch": batch,
            "SpecVerRd": specVerRd,
            "DevRd": devRd
        };

        saveIOS("02", JSON.stringify(busData));
    });

    //下机
    $("#_down").click(function(){
        //批次号、工序版本Rd、设备Rd
        var batch = $("#Batch").val().trim();
        if(batch == undefined || batch == ""){
            toastr.warning("批次不能为空");
            return;
        }
        var specVerRd = $("#SpecVerRd").val();
        if(specVerRd == undefined || specVerRd == ""){
            toastr.warning("工序不能为空");
            return;
        }
        var devRd = $("#devg").val();
        if(devRd == undefined || devRd == ""){
            toastr.warning("设备不能为空");
            return;
        }

        //数据采集
        var doDCInfo = getRowData("list4");
        for(var i=0; i<doDCInfo.length; i++){
            delete doDCInfo[i]["ReferVal"];
            if(doDCInfo[i]["CValue"] == undefined || doDCInfo[i]["CValue"] == "undefined"){
                doDCInfo[i]["CValue"] = "";
            }
        }

        //不良代码采集
        var doRCInfo = getRowData("list5");

        var busData = {
            "Batch": batch,
            "SpecVerRd": specVerRd,
            "DevRd": devRd,
            "DoDCInfo": doDCInfo,
            "DoRCInfo": doRCInfo
        };

        saveIOS("03", JSON.stringify(busData));
    });

    //保存数据
    $("#_save").click(function(){
        //批次号、工序版本Rd、消耗物料、采集信息
        var batch = $("#Batch").val().trim();

        //物料消耗
        var doMaInfo = [];
        var _doMaInfo = getRowData("list3");

        try {
            _doMaInfo.forEach(function (obj) {
                var dm = {
                    "MaVerRd": obj.MaVerRd,
                    "DoNum": obj.Num,
                    "Lock": "00"
                };
                /*if (obj.Data == undefined || obj.Data == "undefined" || obj.Data == "") {
                    //throw new Error();
                }else{
                    dm.DoBaInfo = JSON.parse(obj.Data);
                }*/
                if(obj.materialname != undefined){
                    var data = obj.materialname;
                    /*data.forEach(function(o){
                        delete o.Num;
                    });*/
                    dm.DoBaInfo = data;
                }

                doMaInfo.push(dm);
            });
        }catch (e){
            //toastr.warning("未刷物料");
            //return;
        }

        //数据采集
        var doDCInfo = getRowData("list4");
        for(var i=0; i<doDCInfo.length; i++){
            delete doDCInfo[i]["ReferVal"];
            if(doDCInfo[i]["CValue"] == undefined || doDCInfo[i]["CValue"] == "undefined"){
                doDCInfo[i]["CValue"] = "";
            }
        }

        //不良代码采集
        var doRCInfo = getRowData("list5");

        var busData = {
            "Batch": batch,
            "DoMaInfo": doMaInfo,
            "DoDCInfo": doDCInfo,
            "DoRCInfo": doRCInfo
        };

        saveIOS("06", JSON.stringify(busData));
    });

    var startNum = 0, lastNum = 0;

    //获取工序批次
    function getSpecB(Batch){
        Batch = Batch.trim();

        request({url:"/SpecOpert/GetSpecBInfo",
            data: {"ExecType": "00", "BusData": "{\"Batch\":\"" + Batch + "\"}"}
            , id: "aaa"},function(Body){
            toastr.success(Body.MsgDes);
            clearData();

            //按钮显示
            isButton(Body.Data.Status);

            startNum = Body.Data.Num;
            lastNum = startNum;

            //设备
            if(Body.Data.DevGInfo.length > 0) {
                Body.Data.DevGInfo.forEach(function (value, index) {
                    $("#devg").append("<option value='" + value.DevRd + "'>" + value.DevName + "</option>");
                });
                $("#isDev").show();
            }else{
                $("#isDev").hide();
            }

            //工艺指导书
            if(Body.Data.SOPInfo == undefined || Body.Data.SOPInfo.length == 0){
                $("#sop").append("暂无发布任何工艺指导文件");
            }else {
                Body.Data.SOPInfo.forEach(function (value, index) {
                    //$("#sop").append("<li><a href=\"" + getBasePath() + "/Opert/FilePG/" + value.FilePath + "\" target=\"_blank\">" + value.FileName + "</a></li>");
                    $("#sop").append("<li><a class='files' uri=\"" + value.FilePath + "\" >" + value.FileName + "</a></li>");
                });
            }

            //等待进站批次
            var config1 = {
                tableId: "list1",
                data: Body.Data.WJSpecInfo,
                colArr:colNamesArr,
                width: 0.715,height:0.4
            };
            fullTable(config1);

            //等待出站批次
            var config2 = {
                tableId: "list2",
                data: Body.Data.WCSpecInfo,
                colArr:colNamesArr,
                width: 0.715,height:0.4
            };
            fullTable(config2);

            var doBatch = Body.Data.DoBatchInfo;
            var doMaMap = new Map();
            var doDCMap = new Map();
            if(doBatch != undefined){
                var doMaInfo = doBatch.DoMaInfo;
                if(doMaInfo != undefined){
                    doMaInfo.forEach(function(obj){
                        doMaMap.set(obj.MaVerRd, obj);
                    });
                }

                var doDCInfo = doBatch.DoDCInfo;
                doDCInfo.forEach(function(obj){
                    doDCMap.set(obj.DCItemRd, obj);
                });

                var doRCInfo = doBatch.DoRCInfo;
                if(doRCInfo != undefined){
                    doRCInfo.forEach(function (obj) {
                        addSrow("list5", obj, 0, true);
                    });
                }
            }

            //物料清单
            var bomInfo = Body.Data.BOMInfo;
            bomInfo.forEach(function(obj){
                /*var _data = iptData.get(String(obj.MaVerRd));
                if(_data != undefined){
                    _data = JSON.parse(_data);
                    var s = "";
                    _data.forEach(function(obj_){
                        s += obj_.DoBatch + "  ";
                    });
                    obj.DoBatch = s;
                    /!*obj.Remember = "00";*!/
                }else {
                    obj.DoBatch = "";
                    /!*obj.Remember = "01";*!/
                }*/
                //DoBatchInfo
                var dmm = doMaMap.get(obj.MaVerRd);
                if(dmm != undefined && dmm.DoBaInfo != undefined){
                    obj.materialname = dmm.DoBaInfo;
                    obj.Num = dmm.DoNum;
                }else{
                    obj.materialname = [];
                    obj.Num = 0;
                }
                obj.AllNum = obj.DoNum * startNum;
                obj.DoBatch = "";
            });
            console.log(bomInfo)
            var config3 = {
                tableId: "list3",
                data: bomInfo,
                colArr:colNamesArr3,
                rowedit:false,
                width: 0.715,height:0.51,
                event:{
                    oncellchange: function(data){
                        var $this = data.row;

                        //是否检查
                        if("00" == $this.Checked) {
                            request({
                                url: "/Commom/GetCMBInfo",
                                data: {"ExecType": "00", "BusData": "{\"Batch\":\"" + data.cellvalue + "\"}"}
                            }, function (Body) {

                                /*if(Body.Data.InvalidStatus == "00"){
                                    toastr.warning("该批次已作废");
                                    return;
                                }
                                if(Body.Data.Bad == "00"){

                                }
                                if(Body.Data.IsFreeze == "01"){
                                    toastr.warning("该批次已冻结");
                                    return;
                                }
                                if(Body.Data.IsOpen == "01"){
                                    toastr.warning("该批次已关闭");
                                    return;
                                }
                                if(Body.Data.Status != "02"){
                                    toastr.warning("该批次尚未完成");
                                    return;
                                }*/
                                /*if(Body.Data.Status != "00"){
                                    toastr.warning("该批次尚未完成");
                                    return;
                                }*/
                                //MaVerRd
                                var maVerRd = $this.MaVerRd;//$this.siblings(".MaVerRd").html();
                                if (maVerRd != Body.Data.MaVerRd) {
                                    toastr.warning("该批次不属于这个");
                                    return;
                                }
                                //消耗方式
                                //var conSMode = $this.ConSMode;

                                //判断是否重复添加相同批次（标识->默认 false）
                                var flag = false;

                                //生产数量
                                var countNum = Number($("#Num").val());

                                //总共需要数量
                                var allNum = Number($this.DoNum) * countNum; //* countNum;//Number($this.siblings(".DoNum").html()) * countNum;

                                //当前批次可用量
                                var canNum = Number(Body.Data.CanNum);

                                //已经扫描总数量
                                var allNum_ = 0;

                                var _data = $this.Data;//$this.siblings(".Data").html();
                                if (_data == undefined || _data == "" || _data == "undefined") {
                                    /*if(/!*$this.Remember == "00" && *!/iptData.get(String(maVerRd)) != undefined){
                                        _data = JSON.parse(iptData.get(String(maVerRd)));
                                    }else{
                                        _data = [];
                                    }*/
                                    _data = [];
                                } else {
                                    _data = JSON.parse(_data);
                                }
                                _data.forEach(function (obj) {

                                    if (obj.DoBatch == data.cellvalue) {
                                        toastr.warning("请勿重复添加相同批次");
                                        flag = true;
                                        return;
                                    }
                                    allNum_ += Number(obj.DoNum);
                                });

                                if (flag) {
                                    return;
                                }

                                /*$this.siblings(".Data").children(".dm").each(function(){

                                 allNum_ += Number($(this).attr("num"));
                                 });*/

                                //当前批次消耗量
                                var num = 0;
                                if (allNum == allNum_) {
                                    toastr.success("数量已足够");
                                    return;
                                } else if (allNum <= canNum + allNum_) {
                                    num = allNum - allNum_;
                                    toastr.success("数量已足够");
                                } else if (allNum > canNum + allNum_) {
                                    num = canNum;
                                    toastr.warning("物料不够,请继续刷批次");
                                }

                                var data_ = {
                                    DoBatch: data.cellvalue,
                                    //DoNum: num
                                };

                                _data.push(data_);

                                setCell("list3", {
                                    rowid: data.rowed,
                                    colname: "Data",
                                    celldata: JSON.stringify(_data)
                                });
                                //iptData.set(String(maVerRd), JSON.stringify(_data));
                            });
                        }else{
                            setCell("list3", {
                                rowid: data.rowed,
                                colname: "Data",
                                celldata: JSON.stringify([{
                                    DoBatch: data.cellvalue
                                }])
                            });
                            toastr.success("验证通过");
                        }
                    },
                    onrowselect:function(rowdata){
                        //$(this).children(".DoBatch");
                    },
                    onautocountchange:function(data){
                        var $this = data.row;
                        var res = {
                            result: false,
                            wrap: false
                        };

                        if("00" == $this.ConSMode){
                            if(data.cellvalue.trim().toLocaleUpperCase() == $this.MaCode.trim().toLocaleUpperCase()){
                                setCell("list3", {
                                    rowid: data.rowid,
                                    colname: "Num",
                                    celldata: $this.AllNum
                                });
                                setCell("list3", {
                                    rowid: data.rowid,
                                    colname: "AllNum",
                                    celldata: $this.AllNum,
                                    bgcolor: "#CCCC00"
                                });
                                res.result = true;
                                res.wrap = true;
                                res.data = {
                                    DisplayName: data.cellvalue,
                                    DataValue: $this.AllNum
                                };
                                return res;
                            }else{
                                toastr.warning("料号不对!");
                                return res;
                            }
                        }else if("01" == $this.ConSMode){
                            return res;
                        }

                        var num__ = 0;

                        //是否检查
                        if("00" == $this.Checked) {
                            var flag = true;
                            request({
                                url: "/Commom/GetCMBInfo",
                                data: {"ExecType": "00", "BusData": "{\"Batch\":\"" + data.cellvalue + "\"}"}
                            }, function (Body) {
                                //MaVerRd
                                var maVerRd = $this.MaVerRd;//$this.siblings(".MaVerRd").html();
                                if (maVerRd != Body.Data.MaVerRd) {
                                    toastr.warning("该批次不属于这个");
                                    return res;
                                }
                                //消耗方式
                                //var conSMode = $this.ConSMode;

                                //生产数量
                                var countNum = Number($("#Num").val());

                                //总共需要数量
                                var allNum = Number($this.DoNum) * countNum; //* countNum;//Number($this.siblings(".DoNum").html()) * countNum;

                                //当前批次可用量
                                var canNum = Number(Body.Data.CanNum);

                                //已经扫描总数量
                                var allNum_ = Number($this.Num);

                                /*$this.siblings(".Data").children(".dm").each(function(){

                                 allNum_ += Number($(this).attr("num"));
                                 });*/
                                num__ = canNum;

                                //当前批次消耗量
                                var num = 0;
                                if (allNum == allNum_) {
                                    toastr.success("数量已足够");
                                    return res;
                                } else if (allNum <= canNum + allNum_) {
                                    num = allNum - allNum_;
                                    toastr.success("数量已足够");
                                    res.result = true;
                                    res.wrap = true;
                                } else if (allNum > canNum + allNum_) {
                                    num = canNum;
                                    toastr.warning("物料不够,请继续刷批次");
                                    res.result = true;
                                    res.wrap = false;
                                }

                                $this.Num = Number($this.Num) + canNum;
                                //iptData.set(String(maVerRd), JSON.stringify(_data));
                                flag = false;
                            });

                            if(flag) {
                                return res;
                            }
                        }else{
                            $this.Num = Number($this.Num) + 1;
                            num__ = 1;
                            //toastr.success("验证通过");
                            res.result = true;
                        }

                        if($this.Num >= $this.AllNum){
                            setCell("list3", {
                                rowid: data.rowid,
                                colname: "AllNum",
                                celldata: $this.AllNum,
                                bgcolor: "#CCCC00"
                            });
                            res.wrap = true;
                        }
                        setCell("list3", {
                            rowid: data.rowid,
                            colname: "Num",
                            celldata: $this.Num
                        });

                        res.data = {
                            DisplayName: data.cellvalue,
                            DataValue: num__
                        };

                        return res;
                    },
                    onautocountitemremove: function(data){
                        var $this=getRowById("list3",data.rowid);
                        var sc = {
                            rowid: data.rowid,
                            colname: "AllNum",
                            celldata: $this.AllNum
                        };

                        if($this.AllNum > $this.Num - data.data.Num){
                            sc.bgcolor = "#00CCCC";
                        }else{
                            sc.bgcolor = "#CCCC00";
                        }
                        setCell("list3", sc);
                        var NumResult=$this.Num - data.data.Num;
                        if(NumResult>0){
                            setCell("list3", {
                                rowid: data.rowid,
                                colname: "Num",
                                celldata: NumResult
                            });
                        }
                        else{
                            setCell("list3", {
                                rowid: data.rowid,
                                colname: "Num",
                                celldata: "0"
                            });
                        }

                    },
                    onrowbind:function(rowdata){
                        var sc = {
                            rowid: rowdata.rowid,
                            colname: "AllNum",
                            celldata: rowdata.data.AllNum
                        };

                        if(rowdata.data.ConSMode == "00"){
                            if(rowdata.data.Num > 0) {
                                sc.bgcolor = "#CCCC00";
                            }else{
                                sc.bgcolor="#00CCCC";
                            }

                            setCell("list3",{
                                rowid:rowdata.rowid,
                                colname:"materialname",
                                placeholder: "请输入料号"
                            });
                        }else if(rowdata.data.ConSMode == "01"){
                            setCell("list3",{
                                rowid:rowdata.rowid,
                                colname:"materialname",
                                celldata:""
                            });
                        }else if(rowdata.data.ConSMode == "02"){
                            sc.bgcolor="#00CCCC";
                            setCell("list3",{
                                rowid:rowdata.rowid,
                                colname:"materialname",
                                placeholder: "请输入序号"
                            });
                        }
                        setCell("list3", sc);
                    }
                }
            };
            fullTable(config3);

            if(bomInfo == undefined || bomInfo.length <= 0){
                $("#_put").css("display", "none");
            }

            //数据采集
            var dcInfo = Body.Data.DCInfo;
            dcInfo.forEach(function(obj){
                var ddc = doDCMap.get(obj.DCItemRd);
                if(ddc != undefined && ddc.CValue != undefined){
                    obj.CValue = ddc.CValue;
                }else{
                    obj.CValue = "";
                }
            });

            var config4 = {
                tableId: "list4",
                data: Body.Data.DCInfo,
                colArr:colNamesArr4,
                width: 0.715,height:0.45
            };
            fullTable(config4);

            //不良代码
            var rcgInfo = Body.Data.RCGInfo;
            $("#cSearch").append("<option value=''>请选择</option>" );
            rcgInfo.forEach(function(obj){
                $("#cSearch").append("<option value='" + obj.ReaCode + "'>" + obj.ReaDes + "</option>" );
            });

            //tab 显示
            isTable(2, "");
            if(rcgInfo.length > 0){
                isTable(0, "5");
            }
            if(dcInfo.length > 0){
                isTable(0, "4");
            }
            if(bomInfo.length > 0){
                isTable(0, "3");
            }

            loadData(Body.Data);
        });
    }

    //进出站
    function saveIOS(ExecType, BusData){

        request({url:"/SpecOpert/GetIOSInfo",data: {"ExecType": ExecType, "BusData": BusData}},function(Body){

            if(ExecType == "00" || ExecType == "02") {
                getSpecB($("#Batch").val());
            }else{
                //清空页面
                isTable(1, "");
                clearData();
                $("#Batch").val("");
                toastr.success(Body.MsgDes);
            }
        });
    }

    //选择不良事件
    $("#cSearch").change(function(){

        var $this = $(this);
        var flag = true;

        if($this == undefined || $this.val() == ""){
            return;
        }

        //获取不良表格数据
        var allData = getRowData("list5");
        $.each(allData, function(idx){
            if(allData[idx].ReaCode == $this.val()){
                toastr.warning("原因代码已经存在");
                flag = false;
                return false;
            }
        });

        if(flag) {
            var data = {
                "ReaCode": $this.val(),
                "ReaDes": $this.find("option:selected").text()
            }
            addSrow("list5", data, 0, true);
        }
    });

    //不良删除事件
    $("#deleteReaCode").click(function(){
        derow("list5");
    });

    //批次数量改变事件
    $("#Num").change(function(){
        var allRow = getRowIds("list3");
        if($(this).val().trim()<=0){
            $(this).val("");
        }

        var flag = false;
        if(allRow != undefined){
            allRow.forEach(function(obj){
                var d = getRowById("list3", obj);

                //当前需求量
                var an = Number($("#Num").val()) * d.DoNum;
                var sc = {
                    rowid: obj,
                    colname: "AllNum",
                    celldata: an
                };
                if (an < d.Num) {
                    if(d.ConSMode == "02"){
                        flag = true;
                        toastr.warning("请删除批次以保证消耗量不能大于需求量");
                        return false;
                    }
                }else if(an == d.Num) {
                    sc.bgcolor = "#CCCC00";
                }else{
                    sc.bgcolor = "#00CCCC";
                }

                if(d.ConSMode == "01") {
                    delete sc.bgcolor;
                }

                setCell("list3", sc);
            });
        }
        if(flag){
            $("#Num").val(lastNum);
        }else{
            lastNum = Number($("#Num").val());
        }
    });

    //工艺文件点击事件
    $(document).on('click','.files',function(){
        $("#txtUrl").val($(this).attr("uri"));
        $("#txtFileName").val($(this).text());
        $('#printForm').submit();
    });

    //加载基本数据
    function loadData(data){
        $("#MaCode").val(data.MaCode);
        $("#MaDes").val(data.MaDes);
        $("#MaName").val(data.MaName);
        $("#WFName").val(data.WFName);
        $("#SpecVerRd").val(data.SpecVerRd);
        $("#SpecName").val(data.SpecName);
        $("#Num").val(data.Num);
        $("#UnitName").text(data.UnitName);
        $("#OptName").val(data.OptName);
    }

    //清空所有数据
    function clearData(){
        $("#MaCode").val("");
        $("#MaDes").val("");
        $("#MaName").val("");
        $("#WFName").val("");
        $("#SpecVerRd").val("");
        $("#SpecName").val("");
        $("#Num").val("");
        $("#UnitName").text("");
        $("#OptName").val("");

        $("#devg").empty();
        $("#sop").empty();
        $("#cSearch").empty();

        var config1 = {
            tableId: "list1",
            data: [],
            colArr:colNamesArr,
            width: 0.715,height:0.4
        };
        fullTable(config1);

        var config2 = {
            tableId: "list2",
            data: [],
            colArr:colNamesArr,
            width: 0.715,height:0.4
        };
        fullTable(config2);

        var config3 = {
            tableId: "list3",
            data: [],
            colArr:colNamesArr3,
            width: 0.715,height:0.51
        };
        fullTable(config3);

        var config4 = {
            tableId: "list4",
            data: [],
            colArr:colNamesArr4,
            width: 0.715,height:0.45
        };
        fullTable(config4);

        deallrow("list5");
    }

    //控制物料清单、数据采集表格的显示
    function isTable(type, id){

        if(type == 0){//显示物料清单

            $("#nav_tab3").removeClass("active");
            $("#nav_tab4").removeClass("active");
            $("#nav_tab5").removeClass("active");

            $("#tab_3").removeClass("active");
            $("#tab_4").removeClass("active");
            $("#tab_5").removeClass("active");

            $("#nav_tab" + id).addClass("active");

            $("#nav_tab" + id).css("display", "block");

            $("#tab_" + id).addClass("active");
        }else if(type == 1){//显示所有

            $("#nav_tab3").addClass("active");
            $("#nav_tab4").removeClass("active");
            $("#nav_tab5").removeClass("active");

            $("#nav_tab3").css("display", "block");
            $("#nav_tab4").css("display", "block");
            $("#nav_tab5").css("display", "block");

            $("#tab_3").addClass("active");
            $("#tab_4").removeClass("active");
            $("#tab_5").removeClass("active");

        }else if(type == 2){//都不显示
            $("#nav_tab3").removeClass("active");
            $("#nav_tab4").removeClass("active");
            $("#nav_tab5").removeClass("active");

            $("#nav_tab3").css("display", "none");
            $("#nav_tab4").css("display", "none");
            $("#nav_tab5").css("display", "none");

            $("#tab_3").removeClass("active");
            $("#tab_4").removeClass("active");
            $("#tab_5").removeClass("active");
        }
    }

    function isButton(type){

        $("#_in").css("display", "none");
        $("#_save").css("display", "none");
        $("#_out").css("display", "none");
        $("#_up").css("display", "none");
        $("#_down").css("display", "none");
        //$("#_put").css("display", "none");
        $("#devg").attr("disabled", true);

        if("00" == type){ //显示待入站按钮
            $("#_in").css("display", "block");
        }else if("01" == type){ //显示待上机按钮
            $("#_up").css("display", "block");
            //$("#_put").css("display", "block");
            $("#devg").attr("disabled", false);
        }else if("02" == type){ //显示待下机按钮
            $("#_down").css("display", "block");
        }else if("03" == type){ //显示待出站按钮
            $("#_out").css("display", "block");
            $("#_save").css("display", "block");
        }
    }

    //加载已扫描数据
    function loadCheckData(data){
        setCell("list3", {
            rowid: "1",
            colname: "DoBatch",
            celldata: "<span style='color:red;'>123</span>"
        });
    }
});
