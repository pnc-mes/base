/**
 * Created by xfxi on 2017/9/12.
 */
//@ sourceURL=test.js
$(function(){

    //表格1
    var colNamesArr1 = [
        {  "Caption":"物料Rd", "Name":"MaVerRd", "CType":"text", "Hidden":true},
        { "Caption":"物料代码", "Name":"MaCode", "CType":"text"},
        { "Caption":"物料名称", "Name":"MaName", "CType":"text"},
        {  "Caption":"用量", "Name":"DoNum", "CType":"text",Width:80},
        {  "Caption":"存量", "Name":"EoNum", "CType":"text",Width:80},
        {  "Caption":"单位", "Name":"UnitName", "CType":"text",Width:50}
    ];
    //表格2
    var colNamesArr2 = [
        { "Caption":"批次号", "Name":"Batch", "CType":"text"},
        { "Caption":"数量", "Name":"Num", "CType":"text",Width:80},
        {  "Caption":"物料Rd", "Name":"MaVerRd", "CType":"text", "Hidden":true},
        {  "Caption":"单位", "Name":"UnitName", "CType":"text", "Hidden":true,Width:50}
    ];

    //批次号、设备
    var batch = storage.getItem("Batch");
    var devRd = storage.getItem("DevRd");
    var specVerRd = storage.getItem("SpecVerRd");
    //清空
    storage.removeItem("Batch");
    storage.removeItem("DevRd");
    storage.removeItem("SpecVerRd");

    //存取页面数据
    var map = new Map();
    //当前选中的物料Rd
    var _maverrd = "";

    //获取装料信息
    request({url:"/MODev/GetMODevInfo",
            data: {"ExecType": "00", "BusData": "{\"Batch\":\"" + batch + "\",\"DevRd\":\"" + devRd + "\",\"SpecVerRd\":\"" + specVerRd + "\"}"}},
        function(Body) {
            Body.Data.forEach(function(obj){
                var eobainfo = obj.EoBaInfo;
                if(eobainfo == undefined || eobainfo == ""){
                    eobainfo = [];
                }else{
                    eobainfo.MaVerRd = obj.MaVerRd;
                    eobainfo.UnitName = obj.UnitName;
                }
                map.set(String(obj.MaVerRd), eobainfo);
            });

            var config1 = {
                tableId: "list1",
                data: Body.Data,
                colArr:colNamesArr1,
                width: 0.7,
                multiselect:true,
                event: {
                    onrowselect: function (rowdatas) {
                        console.log(rowdatas);
                        if(rowdatas != undefined && rowdatas.length > 0) {
                            _maverrd = rowdatas[rowdatas.length-1].MaVerRd;
                            var _data = map.get(_maverrd);
                            if (_data == undefined) {
                                _data = [];
                            }
                        }else{
                            _data = [];
                        }
                        var config2 = {
                            tableId: "list2",
                            data: _data,
                            colArr: colNamesArr2,
                            width: 0.5
                        };
                        fullTable(config2);
                    }
                }
            };
            fullTable(config1);
        }
    );

    var config2 = {
        tableId: "list2",
        data: [],
        colArr:colNamesArr2,
        width: 0.5
    };
    fullTable(config2);

    //增加批次（回车）
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            if($("#addBatch").is(":focus")){
                if(_maverrd == undefined || _maverrd == ""){
                    toastr.warning("请先选中你要添加的物料");
                    return;
                }
                var batch = $("#addBatch").val();
                if(batch == undefined || batch == ""){
                    toastr.warning("批次不能为空");
                    return;
                }

                getBatch(_maverrd, batch);
            }
        }
    });

    //保存
    $("._cSave").click(function(){

        var data = [];

        if(map != undefined && map.size > 0){
            map.forEach(function(value){
                if(value != undefined){
                    value.forEach(function(obj){
                        if(obj.MaVerRd != undefined && obj.MaVerRd != ""){
                            delete obj.Num;
                            data.push(obj);
                        }
                    });
                }
            });

            console.log(data);

            if(data == undefined || data.length <= 0){
                toastr.warning("您还没有往里面添加任何新的物料批次");
                return;
            }

            var busData = {
                DevRd: devRd,
                EoBaInfo: data
            };
            console.log(JSON.stringify(busData));
            request({url:"/MODev/SaveMODevInfo", data: {"ExecType": "00", "BusData": JSON.stringify(busData)}}, function(Body) {
                toastr.success(Body.MsgDes);
                parent.layer.closeAll("iframe");
            });
        }else{
            toastr.warning("该设备不需要装料");
        }

        /*data.forEach(function(obj){
            delete obj.Num;
        });
        console.log(data);*/
    });

    //查询批次
    function getBatch(maVerRd, batch){
        request({url:"/Commom/GetCMBInfo", data: {"ExecType": "00", "BusData": "{\"Batch\":\"" + batch + "\"}"}},
            function(Body) {
                if(maVerRd != Body.Data.MaVerRd){
                    toastr.warning("该批次号所属物料不符合");
                    return;
                }else{
                    var eoBainfo = [];
                    if(map.has(maVerRd)){
                        eoBainfo = map.get(maVerRd);
                    }
                    var f = true;
                    //判断是否重复输入批次
                    eoBainfo.forEach(function(obj){
                        if(obj.Batch == batch){
                            toastr.warning("该批次已经存在");
                            f = false;
                        }
                    });

                    if(f) {
                        var _data = {
                            MaVerRd: maVerRd,
                            Batch: batch,
                            Num: Body.Data.CanNum,
                            UnitName: Body.Data.UnitInfo.UnitName
                        };
                        eoBainfo.push(_data);
                        map.set(maVerRd, eoBainfo);
                        addSrow("list2", _data, 0, true);
                    }
                }
            }
        );
    }
});
