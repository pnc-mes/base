jsPlumb.ready(function () {

    let relationArr = ['正常路线', '可选路线', '返工路线'],
        labelTxt = relationArr[0],
        colorArr = ['#5c96bc', '#5c96bc', '#A52A2A'],
        labelCol = colorArr[0],
        lineArr = ['', '2 4', '',],
        labelline = lineArr[0],
        data,
        rm_flag = true;

    let instance = jsPlumb.getInstance({
        Endpoint: ["Dot", {radius: 2}],
        Connector:"StateMachine",
        HoverPaintStyle: {stroke: "#1e8151", strokeWidth: 2 },
        ConnectionOverlays: [
            [ "Arrow", {
                location: 1,
                id: "arrow",
                length: 14,
                foldback: 0.8,
                events:{
                    click:function() { alert("you clicked on the arrow overlay")}
                }
            } ],
            [ "Label", { label: "", id: "label", cssClass: "aLabel", events:{
                    tap:function(c) {}
                } }
            ]
        ],
        Container: "canvas"
    });

    instance.registerConnectionType("basic", { anchor:"Continuous", connector:"StateMachine" });

    window.jsp = instance;

    let canvas = document.getElementById("canvas");
    let windows = jsPlumb.getSelector(".statemachine-demo .w");

    //更改当前状态
    $("._top").find('.lbl').each(function(i) {
        $(this).click(function() {

            if($(this).attr("switch") == "true"){
                $(this).css("color","#000");
                labelCol = "#5c96bc";
                $(this).attr("switch", "false");
            }else{
                labelCol = "#A52A2A";
                $(this).css("color","red");
                $(this).attr("switch", "true");
            }

            labelline = "";
        });
    });

    //连接线单击事件
    instance.bind("click", function (c) {
        layer.confirm('你确定删除吗？', {
            btn: ['确定','取消']
        }, function(index){
            let sourceId = c.sourceId;
            let targetId = c.targetId;
            let _target = $("#" + sourceId).attr("_target");
            if(_target != undefined && _target != "") {
                let data = eval('(' + _target + ')');

                for (let i = 0; i < data.length; i++) {
                    if (data[i].Id == targetId) {
                        data.splice(i, 1);
                    }
                }

                $("#" + sourceId).attr("_target", JSON.stringify(data));
                $("#" + sourceId).removeAttr("NextSpecVerRd");
            }

            instance.deleteConnection(c);
            layer.close(index);
        }, function(index){
        });
    });

    //设置连接名字
    instance.bind("connection", function(info) {

        //var a = 'a';

        //$("#" + info.sourceId).attr("NextSpecVerRd", $("#" + info.targetId).attr("specVerRd"));

        //$("#" + info.targetId);

        //info.connection.getPaintStyle('label').dashstyle

        info.connection.getOverlay("label").setLabel("");
    });

    //设置连接线颜色
    instance.bind("connectionDrag", function(info) {//更改连接线颜色

        var id = info.sourceId;

        if(labelCol != "#A52A2A") {

            var status = true;

            $.each(instance.getAllConnections(), function (idx, connection) {

                if (id == connection.sourceId) {

                    if (connection.getPaintStyle('label').stroke != "#A52A2A") { //返工
                        if (connection.getPaintStyle('label').dashstyle == null ||
                            connection.getPaintStyle('label').dashstyle == "") { //正常

                            labelline = "2 4";

                            status = false;
                            return;
                        }
                    }
                }
            });

            if (status) {
                labelline = "";
            }
        }

        info.setPaintStyle({stroke:labelCol, strokeWidth:2, outlineStroke:"transparent", outlineWidth:4, dashstyle:labelline });
    });

    //鼠标右键点击事件
    $("#canvas").on({
        'mousedown': function(e){
            if(e.which==3){
                var $this = $(this),
                    oLeft = parseInt(e.clientX),
                    oTop = parseInt(e.clientY);
                $("#rightkey").css({"left":oLeft, "top":oTop}).attr("w_id",$this.attr("id")).show();
            }

        },
        'mouseout': function(e){
            //$("#rightkey").hide();
        },
        'mouseover': function(e){
            return false;
        }
    }, '.w');

    //去除浏览器右键默认事件
    $(document).on("contextmenu", function() { return false; });

    //关闭菜单
    $("#canvas").click(function(){
        $("#rightkey").hide();
    });

    //删除DOM元素及其连接线
    $("#delElement").click(function(){
        let w_id = $("#rightkey").attr("w_id");

        let busData = {
            WfVerRd: $("#WfVerRd").val(),
            SpecVerRd: $("#" + w_id).attr("specverrd")
        };
        let b = true;

        /*request({url:"/WF/SaveWfInfo",data: {"ExecType": "06", "BusData": JSON.stringify(busData)}},function(Body) {
            if(Body.MsgDes != "成功"){
                //toastr.warning(Body.MsgDes);
                b = false;
            }
        });*/

        if(b) {
            $.each(instance.getAllConnections(), function (idx, connection) {

                if (w_id == connection.targetId) {
                    var _target = $("#" + connection.sourceId).attr("_target");
                    if (_target != undefined && _target != "") {
                        var data = eval('(' + _target + ')');

                        for (var i = 0; i < data.length; i++) {
                            if (data[i].Id == w_id) {
                                data.splice(i, 1);
                            }
                        }

                        $("#" + connection.sourceId).attr("_target", JSON.stringify(data));
                    }
                }
            });

            instance.remove(w_id);
            $("#rightkey").hide();
        }
    });

    //设置为开始工序
    $("#defElement").click(function(){
        let w_id = $("#rightkey").attr("w_id");

        startSpec(w_id);

        $("#rightkey").hide();
    });

    // 初始化渲染
    let initNode = function(el) {

        // initialise draggable elements.
        instance.draggable(el, { containment: "parent" });

        instance.makeSource(el, {
            filter: ".ep",
            anchor: "Continuous",
            connectorStyle: { stroke: "#5c96bc", strokeWidth: 2, outlineStroke: "transparent", outlineWidth: 4 },
            connectionType:"basic",
            extract:{
                "action":"the-action"
            },
            //maxConnections: 2,
            onMaxConnections: function (info, e) {
                alert("Maximum connections (" + info.maxConnections + ") reached");
            }
        });

        instance.makeTarget(el, {
            dropOptions: { hoverClass: "dragHover" },
            anchor: "Continuous",
            allowLoopback: true,
            //uniqueEndpoint:true
        });

        instance.bind("beforeDrop", function(params) {

            var flag = isST(params.sourceId, params.targetId);

            if(!flag){
                showWarn("请勿重复连接");
            }

            return flag;
        });

        // this is not part of the core demo functionality; it is a means for the Toolkit edition's wrapped
        // version of this demo to find out about new nodes being added.
        //
        instance.fire("jsPlumbDemoNodeAdded", el);
    };

    //判断是否存在重复连接
    let isST = function(sourceId, targetId){
        let flag = true;

        $.each(instance.getAllConnections(), function (idx, connection) {

            if(sourceId == connection.sourceId && targetId == connection.targetId){
                flag = false;
                return false;
            }
        });

        return flag;
    };

    //元素双击事件
    $("#canvas").on("dblclick", ".w", function(e) {
        let id = e.toElement.getAttribute("id");

        if(id != undefined && id != "") {

            window.sessionStorage.setItem("_SpecName", e.toElement.innerText);

            let data = get_Data(id);

            window.sessionStorage.setItem("_Data", data);

            layer.open({
                type: 2,
                title: '工序设置',
                shadeClose: true,
                shade: 0.8,
                area: ['75%', '80%'],
                content: getBasePath() + "/WF/SpecAttr"
            });
        }
    });

    //增加元素
    let newNode = function(x, y,specVerRd, name) {

        //过滤重复工序
        try {
            $(".statemachine-demo .w").each(function (idx, ele) {
                if($(ele).attr("specverrd")==specVerRd){
                    throw Error();
                }
            });
        }catch (e){
            layer.msg("请勿添加重复工序", {icon: 2});
            return;
        }

        let d = document.createElement("div");
        let id = jsPlumbUtil.uuid();
        d.className = "w";
        d.id = id;
        //d.innerHTML = id.substring(0, 7) + "<div class=\"ep\"></div>";
        d.innerHTML = name + "<div class=\"ep\"></div>";
        d.style.left = x + "px";
        d.style.top = y + "px";
        d.setAttribute("data", "");
        //d.setAttribute("Expression", "");
        //d.setAttribute("RoutePath", name);
        d.setAttribute("_target", "[]");
        d.setAttribute("specVerRd", specVerRd);

        if($("#canvas").find('.w').length <= 0){
            d.style.color = "red";
            d.setAttribute("SeSpecType", "00");
        }else{
            d.setAttribute("SeSpecType", "01");
        }

        instance.getContainer().appendChild(d);
        initNode(d);
        return d;
    };

    //获取下个元素
    let get_Data = function(name){

        let pagetarget = [];

        let nextId;

        $.each(instance.getAllConnections(), function(idx, connection) {

            if(name == connection.sourceId){

                let id = connection.targetId;

                let b = true, c = true;

                if($("#" + name).attr("_target") != "" && $("#" + name).attr("_target") != null){

                    let data = eval("(" + $("#" + name).attr("_target") + ")");

                    for(let i=0; i<data.length; i++){

                        if(id == data[i].Id){
                            pagetarget.push(data[i]);
                            b = false;
                            break;
                        }

                    }
                }

                if(b){

                    //正常、可选、返工
                    let status;

                    if(connection.getPaintStyle('label').stroke == "#A52A2A"){ //返工
                        status = "返工";
                    }else{
                        if(connection.getPaintStyle('label').dashstyle == null ||
                            connection.getPaintStyle('label').dashstyle == ""){ //正常
                            status = "正常";
                            //nextId = id;
                            nextId = $("#" + id).attr("specverrd");
                            c = false;
                        }else{
                            status = "可选";
                        }
                    }

                    if(c) {
                        pagetarget.push({
                            Id: id,
                            WfCirRd: $("#" + id).attr("specVerRd"),
                            Expression: "",//$("#" + id).attr("Expression"),
                            RoutePath: $("#" + id).text(), //.attr("RoutePath"),
                            RouteType: status
                        });
                    }
                }
            }
        });

        return "{\"Id\":\""+  name +"\",\"Condition\":" + JSON.stringify(pagetarget)
            + ",\"NSpecVerRd\":"+ nextId +"}";
    }

    //保存数据
    save_Data = function(){
        let connects = [], blocks = [], all_data;

        //判断数据是否正确
        let s = new Set();//过滤
        let i = 0;//计数

        //是否有开始工序 true -> 没有
        let start = true;

        $.each(instance.getAllConnections(), function(idx, connection) {

            if (connection.getPaintStyle('label').stroke != "#A52A2A") { //返工
                if (connection.getPaintStyle('label').dashstyle == null ||
                    connection.getPaintStyle('label').dashstyle == "") { //正常

                    $("#" + connection.sourceId).attr("NextSpecVerRd", $("#" + connection.targetId).attr("specverrd")); //specverrd

                    s.add(connection.sourceId);
                    i++;
                }
            }

            connects.push({
                lineColor: connection.getPaintStyle('label').stroke,
                dashStyle: connection.getPaintStyle('label').dashstyle,
                connectionLabel: connection.getOverlay('label').label,
                connectionLabelText: connection.getOverlay('label').labelText,
                pageSourceId: connection.sourceId,
                pageTargetId: connection.targetId
            });
        });

        if(s.size < i){
            layer.msg("用工序不该同时存在两条正常路线", {icon: 2});
            return "";
        }

        $(".statemachine-demo .w").each(function(idx, ele) {
            let $ele = $(ele);

            let nextSpecVerRd = $ele.attr("NextSpecVerRd");

            if(nextSpecVerRd == undefined){
                nextSpecVerRd == "";
            }

            if($ele.attr("SeSpecType") == "00"){
                start = false;
            }

            blocks.push({
                blockId: $ele.attr("id"),
                blockClass: $ele.attr("class").split(" ")[0],
                blockTxt: $ele.text(),
                blockX: parseInt($ele.css("left")),
                blockY: parseInt($ele.css("top")),
                //blockData: $ele.attr("data"),
                blockTarget: $ele.attr("_target"),
                blockSpecVerRd: $ele.attr("specVerRd"),
                blockNextSpecVerRd: nextSpecVerRd,//$ele.attr("NextSpecVerRd"),
                blockSeSpecType: $ele.attr("SeSpecType")
            });
        });

        if(start){
            layer.msg("开始工序不能为空", {icon: 2});
            return "";
        }

        return all_data = JSON.stringify(connects) + "~" + JSON.stringify(blocks);
    }

    //设置下一道工序
    let setNextSpec = function(){
        $.each(instance.getAllConnections(), function(idx, connection) {

            if (connection.getPaintStyle('label').stroke != "#A52A2A") { //返工
                if (connection.getPaintStyle('label').dashstyle == null ||
                    connection.getPaintStyle('label').dashstyle == "") { //正常

                    $("#" + connection.sourceId).attr("NextSpecVerRd", connection.targetId);

                    s.add(connection.sourceId);
                    i++;
                }
            }
        });
    };

    //初始化加载样式文件
    loadJsplumb = function(json){

        instance.empty("canvas");

        instance.batch(function (){

            //读取文件
            let ss = json.split("~");

            let connects = eval(ss[0]),
                blocks = eval(ss[1]);

            for(let i=0; i<blocks.length; i++){
                let d = document.createElement("div");
                d.className = "w";
                d.id = blocks[i].blockId;
                //id.substring(0, 7)
                d.innerHTML = blocks[i].blockTxt + "<div class=\"ep\"></div>";
                d.style.left = blocks[i].blockX + "px";
                d.style.top = blocks[i].blockY + "px";
                //d.setAttribute("data", blocks[i].blockData);
                d.setAttribute("_target", blocks[i].blockTarget == null ? "": JSON.stringify(blocks[i].blockTarget));
                d.setAttribute("specVerRd", blocks[i].blockSpecVerRd);

                if(blocks[i].blockNextSpecVerRd == undefined){
                    d.setAttribute("NextSpecVerRd", "");
                }else{
                    d.setAttribute("NextSpecVerRd", blocks[i].blockNextSpecVerRd);
                }
                if(blocks[i].blockSeSpecType == "00"){
                    d.style.color="red";
                }
                d.setAttribute("SeSpecType", blocks[i].blockSeSpecType);
                instance.getContainer().appendChild(d);

                initNode(d, true);
            }
            for(let i in connects){
                let conor = instance.connect({ source: connects[i].pageSourceId,
                    target: connects[i].pageTargetId, type: "basic"});
                conor.getOverlay("label").label = connects[i].connectionLabel;
                conor.getOverlay("label").labelText = connects[i].connectionLabelText;
                conor.setPaintStyle({stroke : connects[i].lineColor,strokeWidth:2,
                    dashstyle: connects[i].dashStyle, outlineWidth:4, outlineStroke: "transparent"});
            }

        });

    }

    //清空样式
    emptyJsplumb = function(){

        instance.empty("canvas");
    }

    newSpecNode = function (id, name) {
        newNode(0, 0, id,name);
    }

    //设置开始工序
    function startSpec(id){

        $("#canvas").find('.w').each(function(i) {

            $(this).css("color","#000");
            $(this).attr("SeSpecType", "01");
            //$(this).css("color","red");
        });

        //$("#" + id).addClass("icons");
        $("#" + id).css("color", "red");

        $("#" + id).attr("SeSpecType", "00");

    }

    jsPlumb.fire("jsPlumbDemoLoaded", instance);
});
