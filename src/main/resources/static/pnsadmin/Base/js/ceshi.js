/**
 * Created by test on 2017/10/11.
 */
//下拉+筛选+弹窗控件
$(function () {
    /*if(JSON.parse(localStorage.user).UserName=="system"){
        $(".shutdo").show();
    }else {
        $(".shutdo").hide();
    }*/
    $(".shutdo").show();
    var t_flag = null;
    var changeBeforeHeight = 'auto';//定义变量，接收放大前的高度
    var changeBeforeWidth = '404px';//定义变量，接收放大前的宽度
    /*var _data = [];
     $.fn.setData = function (data) {
     _data = data;
     };*/
    var getData = {};
    $.fn.getseldata = function () {
        /*for(var i in getData){
         getData[i] = getData[i].replace(/'/g,"\\''");
         getData[i] = getData[i].replace(/\\/g,"\\\\");
         }*/
        return getData;
    };
    $('.inpSearch').val('');
    var getkeydata = {};
    $.fn.getselkeydata = function () {
        return getkeydata;
    };
    $.fn.clearseldata = function(keys){
        if(keys == "" || keys == undefined){
            getData = null;
            getData = {};
        }else{
            getData[keys] = "";
        }
        getkeydata = {};
        //清空输入框的值
        $(this).find('input').val('');
    };
    $.fn.showData = function(obj){
        var $inpSearch = $(this).find(".inpSearch");
        $inpSearch.val(obj.name);
        $inpSearch.css('text-decoration','underline');
        $inpSearch.on('keydown',function(even){
            $(this).unbind('keydown');
            if(even.keyCode == 8){
                $inpSearch.val('');
                $(this).css('text-decoration','none');
                $(this).clearseldata(obj.keyfield);
                $(".ul").empty();
            }else{
                $(this).css('text-decoration','none');
                $(this).clearseldata(obj.keyfield);
            }
        });
        $(this).prop("data-id",obj.id);
        obj.name = obj.name == null? "":obj.name;
        var spanArr = (obj.id + "|" + obj.name.substring(0, obj.name.length)).split("|");
        //组装要返回的getseldata数据
        var fields = obj.fields;
        for (var i in fields) {
            getData[fields[i].name] = spanArr[i];
        }
        //组装要返回的getselkeydata数据
        getkeydata[obj.keyfield] = obj.id;
    };

    $.fn.showData1 = function(obj){
        var $inpSearch = $(this).find(".inpSearch");
        $inpSearch.val(obj.name);
        $inpSearch.css('text-decoration','underline');
        $inpSearch.on('keydown',function(even){
            $(this).unbind('keydown');
            if(even.keyCode == 8){
                $inpSearch.val('');
                $(this).css('text-decoration','none');
                $(this).clearseldata(obj.keyfield);
                $(".ul").empty();
            }else{
                $(this).css('text-decoration','none');
                $(this).clearseldata(obj.keyfield);
            }
        });
        $(this).prop("data-id",obj.id);
        obj.name1 = obj.name1 == null? []:obj.name1;
        var spanArr = obj.name1;
        //组装要返回的getseldata数据
        var fields = obj.fields;
        for (var i in fields) {
            getData[fields[i].name] = spanArr[i];
        }
        //组装要返回的getselkeydata数据
        getkeydata[obj.keyfield] = obj.id;
    };

    /*函数的入口*/
    $.fn.zc_select = function (entryParam) {
        var formatVal = "";
        var _data = []; //entryParam.data;
        if(entryParam.displaymode == 1){
            entryParam.shownum = {
                "minnum": 30,
                "maxnum": 500
            }
        }

        //获取调用当前方法的对象，$(this)就是调用的对象
        var $this = $(this);

        /*追加页面代码*/

        $("body").after(
            '<div class="table-responsive layerContent" id="" style="display: none;overflow: hidden">' +
            '<div class="topTitle topInformation clearfix">' +
            '<p><span class="selectSpan" style="color:#fff;margin:0 8px;">选择</span><span class="selectTitle" style="color:#fff;">选择信息</span></p>' +
            '<p><label class="Maximization" style="color:#fff;">最大化</label>' +
            ' <label class="" id="selectClose" style="color:#fff;margin-right: 10px;" >关闭</label></p>' +
            ' </div>' +
            '<div class="f2Title ">' +
            ' <div class="clearfix" style="border: 1px solid #ccc;background: #FFEB3B;">' +
            '<p class="f2P1" style="float: left;width:100%;margin-right: 0;padding-right:0px;margin-bottom:0px;"><label style="margin-left:5px;">查找</label><input style="margin-right: 0px;margin-top: 3px;width: 78%;" id="inp" type="text"/><button type="button" style="margin-left: 5px;font-size: 12px;margin-top: -3px; padding: 0px 9px;" class="btn btn-primary lookup" id="bottonid">查找</button></p>' +
            '<p style="float: left;margin-right:0px;margin-bottom: 0px;display: none" class="pFresh"><a href="javascript:void (0);" style="display: inline-block;height: 16px;line-height: 25px;margin-right: 10px;"></a></p>' +
            '</div>' +
            ' <p class="Ptitle " id="f2" style="clear: both"></p>' +
            ' <ul class="ulList"></ul>' +
            ' </div>' +
            '</div>');

        //把当前的对象换成input输入框，并判断displaymode的值，来作为加上图片来表示是可以弹窗还是仅仅可以下拉
        var imgName =getBasePath()+"/static/pnsadmin/Base/images/admin/default.png";
        if (entryParam.displaymode == 1)
            imgName =getBasePath()+"/static/pnsadmin/Base/images/admin/lager.png";
        $this.append(
            '<div class="floor1Top">' +
            '<input class="inpSearch"/>' +
            '<img class="defaultImg" src="' + imgName + '">' +
            '</div>' +
            '<ul class="ul" style="background-color: #fff;">' +
            '</ul>');

        var $inpSearch = $this.find(".inpSearch");
        var $ul = $this.find(".ul");
        var $defaultImg = $this.find(".defaultImg");
        var $layerContent = $("body").next();
        var $floor1Top = $this.find('.floor1Top');
        $layerContent.css({'border-radius':'8px','border':"3px solid #cccccc","padding":"10px","width":"404px","background":"#fff","z-index":"100000"});
        /*样式*/
        var $lookup = $layerContent.find('.lookup');
        var $ulList = $layerContent.find('.ulList');
        var $inp = $layerContent.find('#inp');
        var $selectTitle = $layerContent.find(".selectTitle");
        var $selectClose = $layerContent.find('#selectClose');
        var $pFresh = $layerContent.find('.pFresh');
        var $pTitle = $layerContent.find('.Ptitle');
        var $topTitle = $layerContent.find('.topTitle');
        /*控制样式*/
        /*$('.f2Title').css('width','100%');*/
        $ul.css({'background':'#fff','z-index':'999'});
        $topTitle.find('p:nth-child(1)').css({'float':"left","margin":'0px'});
        $topTitle.find('p:nth-child(2)').css({"float":"right","margin":"0px"});
        $topTitle.find('div>p').css('margin','2px');
        $('.clearfix:after').css({"clear":"both","height":"0","content":"","visibility":"hidden", "display":" block"});
        $pFresh.css({"float":"right","cursor":"pointer"});
        $ulList.css({"border":"1px solid #ccc","background-color":"#fff","margin":"0","padding-left":"0","height":"200px","overflow-y":"auto","overflow-x":"hidden", "clear":" both","display":"none"});
        $ulList.find('li').css({"list-style":"none","cursor":"pointer","display":"block","white-space":"nowrap", "overflow":" hidden","text-overflow":"ellipsis",'width':'100%'});
        $ulList.find('li:hover').css("background-color",'#60d2dc');
        $pTitle.css({"margin":"0px","clear":"both",'width':'100%'});
        $pTitle.find('span').css("margin-right","5px");
        $('.topInformation').css({'border-radius':'4px','-webkit-box-shadow':"rgb(136, 136, 136) 3px 5px 4px",'-moz-box-shadow':"rgb(136, 136, 136) 3px 5px 4px",'box-shadow':"rgb(136, 136, 136) 3px 5px 4px","padding-top":"7px",'background':'rgb(60, 141, 188)',"margin-bottom":"7px"});
        $('#f2').css('float','left');
        $('.selectSpan').css('margin-right','10px;');
        $this.css("display","inline-block");

        $ul.css({"border": "1px solid darkgray","position":"absolute", "padding": "0px", "margin": "0px", "margin-left": "0px", "display": "none", "height": "200px", "overflow-y": "scroll", "overflow-x": "hidden"});
        $ul.find('li').css({"list-style": "none", "cursor": "pointer"});
        $floor1Top.css({"position": "relative", "width": "196px"});
        $defaultImg.css({"width": "20px", "height": "20px", "position": "absolute", "right": "2px", "top": "4px"});
        $inpSearch.css({'width':'196px','margin-right':'0px','height':'28px','border':'1px solid #d2d6de','padding':'6px 12px'});
        //$inpSearch.val(entryParam.val);
        //值为0的时候不弹窗
        var flagg = true;
        $defaultImg.on("click", function (event) {
            event.stopPropagation();
            if (entryParam.displaymode == 0) {
                if($ul.css("display") == "none"){
                    //$inpSearch.focus();
                    $(".ul").css("display", "none");
                    /*修改开始*/
                    //if($(this).val().length>1){
                    $ul.css("display", "block").css("width", parseFloat($inpSearch.css("width").split("px")[0]) + 2 + "px").empty();
                    $ul.empty();
                    $ul.append("<li style='color: red;'>加载中...</li>");
                    if(entryParam.event != undefined){
                        if(entryParam.event.onseardata != undefined){
                            var obj = entryParam.event.onseardata({"condition": "", "num": 30});
                            $ul.empty();
                            var liStr = "";
                            //加载数据开始
                            liStr += dealLiData(obj.data, obj.data.length, entryParam.binddata.keyfield, entryParam.binddata);
                            $ul.append(liStr);
                            //加载数据结束
                            liStyle($ul, true);
                            dealLiClick();
                            $ul.css("display", "block");
                            if($ul.find("li").length == 0 ){
                                $ul.append("<li style='color: red;' disabled>没有此数据...</li>");
                                /*setTimeout(function () {
                                 $ul.empty();
                                 },800);*/
                            }
                        }
                    }
                    $ul.show();
                    flagg = false;
                }else{
                    $ul.hide();
                    flagg = true;
                }
            }
            //dealLisStyle();
        });
        $(document).bind("click", function () {//点击空白处，设置的弹框消失
            $ul.hide();
        });

        //加载数据[加载li的数据]
        function dealLiData(liDataParmas,showNum,keyField,bindData){//liDataParmas：加载的数据  showNum：加载的条数 keyField:隐藏字段显示与不显示
            //处理显示的条数
            var liStr = '';
            for (var i in liDataParmas) {
                if (i < parseInt(showNum)) {
                    var spanStr = "";
                    for (var key in liDataParmas[i]) {
                        if (key == keyField){
                            spanStr += "<span style='display: none' title='"+liDataParmas[i][key]+"'>" + liDataParmas[i][key] + "</span>";
                        } else{
                            for(var j in bindData.fields){
                                if(key == bindData.fields[j].name){
                                    if(bindData.fields[j].hidden == true){
                                        spanStr += "<span style='display:none;font-family: Courier New;font-size: 14px' title='"+liDataParmas[i][key]+"'>" + liDataParmas[i][key] + "</span>";
                                    }else{
                                        spanStr += "<span style='font-family: Courier New; display: inline-block; font-size: 14px' title='"+liDataParmas[i][key]+"'>" + liDataParmas[i][key] + "</span>";
                                    }
                                }
                            }
                            //spanStr += "<span style='font-family: Courier New;font-size: 14px' title='"+liDataParmas[i][key]+"'>" + liDataParmas[i][key] + "</span>";
                        }
                    }
                    liStr += "<li>" + spanStr + "</li>";
                }
            }
            return liStr;
        }
        //表头数据的加载
        function dealLiHead (bindData){
            var displayStr = "";
            for (var j in bindData.fields) {
                if (bindData.keyfield != bindData.fields[j].name) {
                    if(bindData.fields[j].hidden == true){
                        displayStr += "<span style='display: none;font-family: Courier New;font-size: 14px;font-weight: 500' title='"+bindData.fields[j].caption+"'>" + bindData.fields[j].caption + "</span>";
                    }else{
                        displayStr += "<span style='font-family: Courier New;font-size: 14px; display: inline-block; font-weight: 500' title='"+bindData.fields[j].caption+"'>" + bindData.fields[j].caption + "</span>";
                    }
                } else {
                    displayStr += "<span style='display: none' title='"+bindData.fields[j].caption+"'>" + bindData.fields[j].caption + "</span>";
                }
            }

            return displayStr;
        }
        //表头样式
        /*function dealLiHeadStyle(){
         //表头
         $ul.find("li:eq(0)").css({
         "width": parseFloat($ul.css("width").split("px")[0]) - 17 + "px",
         "background-color": "gainsboro"
         });
         $ul.find("li:eq(1)").css("margin-top", $ul.find("li:eq(0)").css("height"));//数据
         }*/
        //li的样式
        function liStyle(ulObj,isHead){
            var i = 0;
            $(ulObj).find("li").each(function () {
                $(this).css({"cursor": "pointer"});
                $(this).on("mouseover", function () {
                    $(this).css("background-color", "gainsboro").siblings().css("background-color", "");
                    if(isHead) {
                        //$(ulObj).find("li:eq(0)").css({"background-color":"gainsboro","margin-bottom":"20px"});
                    }
                });
                if(isHead)
                //$(ulObj).find("li:eq(1)").css("margin-top","25px");
                    if(isHead && i > 0){
                        $(this).on("mouseout", function () {
                            $(this).css("background-color", "");
                        });
                    }
                //:not(:last-child)
                $(this).find("span:gt(0)").css({ "margin-left": "5px","overflow":'hidden',"text-overflow":"ellipsis","white-space": "nowrap"
                });
                i++;
            });
            return;
        };
//双击处理li数据回显到文本框中？
        function dealLiClick() {
            if (entryParam.showresult.ishead == true) {
                $ul.one("click", "li", function (event) {

                    event.stopPropagation();
                    $ul.find('li').unbind("click");
                    var value = "";
                    $(this).find("span:gt(0)").each(function () {
                        if($(this).css("display") != "none")
                            value += $(this).text() + "|";
                    });

                    $inpSearch.val(value.substring(0, value.length - 1));

                    $inpSearch.css('text-decoration','underline');

                    var index = $inpSearch.css('text-decoration').indexOf("underline");
                    if (index != -1) {
                        $inpSearch.on('keydown',function(even){
                            $(this).unbind('keydown');
                            if(even.keyCode == 8){
                                $inpSearch.val('');
                                $(this).css('text-decoration','none');
                                for(var i in entryParam.binddata.fields){
                                    $(this).clearseldata(entryParam.binddata.fields[i].name);
                                }
                                $ul.empty();
                            }else{
                                $(this).css('text-decoration','none');
                                for(var i in entryParam.binddata.fields){
                                    $(this).clearseldata(entryParam.binddata.fields[i].name);
                                }
                            }
                        });
                    }

                    $ul.css("display", "none");
                    //获取span的值，放到一个数组里面
                    var rd = $(this).find("span:eq(0)").text();
                    value = "";
                    $(this).find("span:gt(0)").each(function () {
                        value += $(this).text() + "|";
                    });
                    var spanArr = (rd + "|" + value.substring(0, value.length - 1)).split("|");
                    //组装要返回的getseldata数据
                    var fields = entryParam.binddata.fields;
                    var formatData = [];
                    for (var i in fields) {
                        getData[fields[i].name] = spanArr[i];
                        formatData[fields[i].name] = spanArr[i];
                    }
                    format(formatData);
                    if(formatVal != ""){
                        $inpSearch.val(formatVal);
                    }
                    //组装要返回的getselkeydata数据
                    getkeydata[entryParam.binddata.keyfield] = rd;
                    if(entryParam.event != undefined){
                        if(entryParam.event.onclick != undefined) {
                            /*for(var i in getData){
                             getData[i] = getData[i].replace(/'/g,"\\''");
                             getData[i] = getData[i].replace(/\\/g,"\\\\");
                             }*/
                            entryParam.event.onclick(getData);
                        }
                    }
                });
            } else {
                $ul.one("click", "li", function () {

                    $ul.find('li').unbind("click");
                    var value = "";
                    $(this).find("span:gt(0)").each(function () {
                        value += $(this).text() + "|";
                    });

                    $inpSearch.val(value.substring(0, value.length - 1));

                    $inpSearch.css('text-decoration','underline');

                    var index = $inpSearch.css('text-decoration').indexOf("underline");
                    if (index != -1) {
                        $inpSearch.on('keydown',function(even){
                            $(this).unbind('keydown');
                            if(even.keyCode == 8){
                                $inpSearch.val('');
                                $(this).css('text-decoration','none');
                                for(var i in entryParam.binddata.fields){
                                    $(this).clearseldata(entryParam.binddata.fields[i].name);
                                }
                                $ul.empty();
                            }else{
                                $(this).css('text-decoration','none');
                                for(var i in entryParam.binddata.fields){
                                    $(this).clearseldata(entryParam.binddata.fields[i].name);
                                }
                            }
                        });
                    }

                    $ul.css("display", "none");
                    //获取span的值，放到一个数组里面
                    var rd = $(this).find("span:eq(0)").text();
                    value = "";
                    $(this).find("span:gt(0)").each(function () {
                        value += $(this).text() + "|";
                    });
                    var spanArr = (rd + "|" + value.substring(0, value.length - 1)).split("|");
                    //组装要返回的getseldata数据
                    var fields = entryParam.binddata.fields;
                    var formatData = [];
                    for (var i in fields) {
                        getData[fields[i].name] = spanArr[i];
                        formatData[fields[i].name] = spanArr[i];
                    }
                    format(formatData);
                    if(formatVal != ""){
                        $inpSearch.val(formatVal);
                    }
                    //组装要返回的getselkeydata数据
                    getkeydata[entryParam.binddata.keyfield] = rd;
                    if(entryParam.event != undefined){
                        if(entryParam.event.onclick != undefined) {
                            /*for(var i in getData){
                             getData[i] = getData[i].replace(/'/g,"\\''");
                             getData[i] = getData[i].replace(/\\/g,"\\\\\\");
                             }*/
                            entryParam.event.onclick(getData);
                        }
                    }
                });
            }
        }
        function dealWindowMaxMin(){
            var flag = false;
            $('.Maximization').on('click', function (e) {
                e.stopPropagation();
                if (flag) {
                    $(this).text("最大化");
                    $('.pFresh').css('float','right');
                    getwihei();
                    flag = false;
                    $layerContent.draggable({containment: "body", scroll: false, cursor: "move"});
                    $ulList.css("height","197px");
                }
                else if (!flag) {
                    $layerContent.draggable("destroy");
                    $(this).text("还原");
                    minWidthHeight();
                    $layerContent.css('z-index', '999999');
                    flag = true;
                    $ulList.css("height",parseFloat($("body").css("height").split("px")[0]) - 117 + 'px');
                }
            });
        }
        function dealClose(close,inpEle,d){
            //关闭
            $(close).on('click', function () {
                getwihei();
                $layerContent.css('display', 'none');
                $inpSearch.val(inpEle.val());
                $ul.css('display','none');
            });
        }
        function dealLayerDblclick(ulList,inp){
            $(ulList).on("dblclick", "li", function () {
                $(ulList).find('li').unbind("dblclick");
                var value = "";
                $(this).find("span:gt(0)").each(function () {
                    if($(this).css("display") != "none")
                        value += $(this).text() + "|";
                });
                $(this).css('background', 'gainsboro').siblings().css('background', '');

                $(inp).val(value.substring(0, value.length - 1));

                //获取span的值，放到一个数组里面
                var rd = $(this).find("span:eq(0)").text();
                value = "";
                $(this).find("span:gt(0)").each(function () {
                    value += $(this).text() + "|";
                });
                var spanArr = (rd + "|" + value.substring(0, value.length - 1)).split("|");
                //组装要返回的getseldata数据
                var fields = entryParam.binddata.fields;
                var formatData = [];
                for (var i in fields) {
                    getData[fields[i].name] = spanArr[i];
                    formatData[fields[i].name] = spanArr[i];
                }
                format(formatData);
                if(formatVal != ""){
                    $(inp).val(formatVal);
                }
                var widthSpan;
                widthSpan = (100/(fields.length-1))+"%";
                $(ulList).find('li').find('span:gt(0)').css('width',widthSpan);
                //组装要返回的getselkeydata数据
                getkeydata[entryParam.binddata.keyfield] = rd;
                $inpSearch.val($(inp).val());
                $inpSearch.css("text-decoration","underline");
                $layerContent.css('display','none');
                var index = $inpSearch.css('text-decoration').indexOf("underline");
                if (index != -1) {
                    $inpSearch.on('keydown', function (even) {
                        $(this).unbind('keydown');
                        if (even.keyCode == 8) {
                            $inpSearch.val('');
                            $(this).css('text-decoration','none');
                            for(var i in entryParam.binddata.fields){
                                $(this).clearseldata(entryParam.binddata.fields[i].name);
                            }
                            $ul.empty();
                        }else{
                            $(this).css('text-decoration','none');
                            for(var i in entryParam.binddata.fields){
                                $(this).clearseldata(entryParam.binddata.fields[i].name);
                            }
                        }
                    });
                }
                /*setTimeout(alert('nihao !'),500000);*/
                if(entryParam.event!= undefined) {
                    if (entryParam.event.onclick != undefined){
                        /*for(var i in getData){
                         getData[i] = getData[i].replace(/'/g,"\\''");
                         getData[i] = getData[i].replace(/\\/g,"\\\\");
                         }*/
                        entryParam.event.onclick(getData);
                    }
                }
            });
            /*for(var i in getData){
             getData[i] = getData[i].replace(/'/g,"\\''");
             getData[i] = getData[i].replace(/\\/g,"\\\\");
             }*/
            return getData;
        }
        /*function dealFresh(pFresh,inp,ulList){
         pFresh.unbind("click");
         $(pFresh).on('click', function () {
         //清空inp里面的内容
         $(inp).val("");
         //显示所有的li
         $(ulList).find("li").css("display", "block");
         });
         }*/

        $(function(){

        });

        function dealLookup(lookup,inp,ulList){
            $('#inp').bind('keypress',function(event){
                if(event.keyCode == "13")
                {
                    var inpVal = inp.val();
                    if(entryParam.event != undefined){
                        if(entryParam.event.onseardata != undefined){
                            inpVal = inpVal.replace(/'/g,"\\''");
                            inpVal = inpVal.replace(/\\/g,"\\\\\\");
                            var obj = entryParam.event.onseardata({"condition":inpVal,"num":500});
                            $ulList.empty();
                            //把数据显示到弹窗里面
                            var liStr = '';
                            /****************表头结束****************************/
                            $ulList.append(dealLiData(obj.data,obj.showrow,entryParam.binddata.keyfield,entryParam.binddata)).css("display", "block");
                            /************表头样式复用开始**********************/
                            liStyle($ulList);
                        }
                    }
                    dealLisStyle();
                }
            });
            $(lookup).on('click', function (e) {
                e.stopPropagation();
                var inpVal = inp.val();
                if(entryParam.event != undefined){
                    if(entryParam.event.onseardata != undefined){
                        inpVal = inpVal.replace(/'/g,"\\''");
                        inpVal = inpVal.replace(/\\/g,"\\\\\\");
                        var obj = entryParam.event.onseardata({"condition":inpVal,"num":500});
                        $ulList.empty();
                        //把数据显示到弹窗里面
                        var liStr = '';
                        /****************表头结束****************************/
                        $ulList.append(dealLiData(obj.data,obj.showrow,entryParam.binddata.keyfield,entryParam.binddata)).css("display", "block");
                        /************表头样式复用开始**********************/
                        liStyle($ulList);
                    }
                }
                dealLisStyle();
            });
        }
        //有标题的弹窗
        function dealOpenHasTitle(){
            /********************字段值未给【判断是否要请求后端数据】*********************************/
            //值为1时，点击图标弹窗
            $defaultImg.on('click', function () {
                if (entryParam.title != undefined && entryParam.title != "" && entryParam.title != null)
                    $selectTitle.html(entryParam.title);
                //清空ul的列表
                $ulList.empty();
                //把数据显示到弹窗里面
                var liStr = '';
                var displayStr = '';
                /****************表头开始复用****************************/
                $pTitle.empty();
                $pTitle.append(dealLiHead(entryParam.binddata));
                //处理弹窗的Ptitle样式
                $pTitle.find('span:gt(0)').css({"width":"89px","cursor":"pointer","overflow":'hidden',"text-overflow":"ellipsis","white-space": "nowrap","height":"18px"});
                /****************表头结束****************************/
                $ulList.append(dealLiData(_data,entryParam.shownum.maxnum,entryParam.binddata.keyfield,entryParam.binddata)).css("display", "block");
                /************表头样式复用开始**********************/
                liStyle($ulList);
                /************表头样式复用结束**********************/
                $layerContent.css('display', 'block');
                //弹窗定位
                $layerContent.css({'position': 'absolute', 'top': '10%', 'left': '40%'});
                //可以自由拖拽
                $layerContent.draggable({containment: "body", scroll: false, cursor: "move"});
                //可以自由拉伸弹窗
                //$layerContent.resizable();
                //将文本框的值添加到弹窗文本框里面并进行自动搜索
                $inp.val($inpSearch.val());
                $lookup.click();
                //隐藏页面中的下拉框
                $ul.css("display", "none");
            });
            /***************复用开始***********************/
            //放大缩小(zoom in and out:放大与缩小)
            dealWindowMaxMin();
            //点击查询进行搜索显示
            dealLookup($lookup,$inp,$ulList);
            var d = dealLayerDblclick($ulList,$inp);
            //刷新
            //dealFresh($pFresh,$inp,$ulList);
            //关闭
            dealClose($selectClose,$inp,d);
        }
        //没有标题弹窗
        function dealOpenNotTitle(){

            /********************字段值未给【判断是否要请求后端数据】*********************************/
            //值为1时，点击图标弹窗
            $defaultImg.on('click', function () {
                //清空ul的列表
                $ulList.empty();
                //把数据显示到弹窗里面
                var liStr = '';
                $ulList.append(dealLiData(_data,entryParam.shownum.maxnum,entryParam.binddata.keyfield,entryParam.binddata)).css("display", "block");
                liStyle($ulList);
                $layerContent.css('display', 'block');
                //弹窗定位
                $layerContent.css({'position': 'absolute', 'top': '10%', 'left': '40%'});
                //可以自由拖拽
                $layerContent.draggable({containment: "body", scroll: false, cursor: "move"});
                //可以自由拉伸弹窗
                //$layerContent.resizable();
                $inp.val($inpSearch.val());
                $lookup.click();
                //隐藏页面中的下拉框
                $ul.css("display", "none");
            });
            //点击查询进行搜索显示
            dealLookup($lookup,$inp,$ulList);
            /*****************复用开始*************************/
            //放大缩小(zoom in and out:放大与缩小)
            dealWindowMaxMin();
            //双击li获取span值放到input框内
            var d =dealLayerDblclick($ulList,$inp);
            //刷新
            //dealFresh($pFresh,$inp,$ulList);
            //关闭
            dealClose($selectClose,$inp,d);
        }
        if(entryParam.displaymode == 1){
            if (entryParam.showresult.ishead == true) {//true：表示显示表头
                dealOpenHasTitle();
            } else {//表示不显示表头
                dealOpenNotTitle();
            }
        }
        $inpSearch.on({
            'focus': function () {//获取焦点的时候不加载数据，当输入字符的长度大于1的时候再加载搜索到的数据
                $(".ul").css("display", "none");
                /*修改开始*/
                //if($(this).val().length>1){
                $ul.css("display", "none").css("width", parseFloat($(this).css("width").split("px")[0]) + 2 + "px").empty();
                if(entryParam.event != undefined){
                    if(entryParam.event.onseardata != undefined){
                        if($(this).val().trim().length >= 2) {
                            var inpV = $inpSearch.val().replace(/'/g,"\\''");
                            inpV = inpV.replace(/\\/g,"\\\\\\");
                            var obj = entryParam.event.onseardata({"condition": inpV, "num": 30});
                            $ul.empty();
                            if (entryParam.displaymode == 0) {//如果是0，表示是只有下拉框，但是下拉框可以显示单列，也可以显示多列//先判断是否显示下拉框的表头
                                //if (entryParam.showresult.ishead == true) {//true:显示
                                var liStr = "";
                                //加载数据开始
                                liStr += dealLiData(obj.data, obj.data.length, entryParam.binddata.keyfield, entryParam.binddata);
                                $ul.append(liStr);
                                //加载数据结束
                                liStyle($ul, true);
                                dealLiClick();
                                //}
                            }
                            else if (entryParam.displaymode == 1) {
                                //if (entryParam.showresult.ishead == true) {//true：表示显示表头
                                var liStr = "";
                                //dealLiHeadStyle();
                                //处理显示的条数
                                liStr += dealLiData(obj.data, entryParam.shownum.minnum, entryParam.binddata.keyfield, entryParam.binddata);
                                $ul.append(liStr);
                                liStyle($ul, true);
                                dealLiClick();
                                //}
                            }
                            $ul.css("display", "block");
                        }
                    }
                }
                //}
                /*if (entryParam.displaymode == 0) {//如果是0，表示是只有下拉框，但是下拉框可以显示单列，也可以显示多列//先判断是否显示下拉框的表头
                 if (entryParam.showresult.ishead == true) {//true:显示
                 var liStr = "";
                 //处理表头
                 liStr += "<li style='position:absolute;border-bottom: 1px solid darkgray'>" + dealLiHead(entryParam.binddata) + "</li>";
                 $ul.append(liStr);
                 //加载数据结束
                 /!************表头样式复用开始**********************!/
                 dealLiHeadStyle();
                 }
                 }
                 else if (entryParam.displaymode == 1) {
                 if (entryParam.showresult.ishead == true) {//true：表示显示表头
                 var liStr = "";
                 var displayStr = "";
                 /!****************表头开始复用****************************!/
                 liStr += "<li style='position:absolute;border-bottom: 1px solid darkgray'>" + dealLiHead(entryParam.binddata) + "</li>";
                 /!****************表头结束****************************!/
                 $ul.append(liStr);
                 /!************表头样式复用开始**********************!/
                 dealLiHeadStyle();
                 }
                 }*/
            },
            'click':function(event){
                event.stopPropagation();
            }
        });
        var serachtimer;
        var s = "";
        $inpSearch.bind("input propertychange", function () {
            $ul.css("display", "block");
            if($(this).val().trim().length < 2){
                $ul.empty();
                s = $(this).val().trim();
            }
            clearTimeout(serachtimer);
            if($inpSearch.val().trim().length >= 2 && s != $(this).val().trim()) {
                $ul.empty();
                $ul.append("<li style='color: red;'>加载中...</li>");
                s = $(this).val().trim();
                serachtimer = setTimeout(function () {
                    if (entryParam.event != undefined) {
                        if (entryParam.event.onseardata != undefined) {
                            $ul.empty();
                            var inpV = $inpSearch.val().replace(/'/g,"\\''");
                            inpV = inpV.replace(/\\/g,"\\\\\\");
                            var obj = entryParam.event.onseardata({"condition":inpV,"num":30});
                            if (entryParam.displaymode == 0) {//如果是0，表示是只有下拉框，但是下拉框可以显示单列，也可以显示多列//先判断是否显示下拉框的表头
                                if (entryParam.showresult.ishead == true) {//true:显示
                                    var liStr = "";
                                    //加载数据开始
                                    liStr += dealLiData(obj.data, obj.data.length, entryParam.binddata.keyfield, entryParam.binddata);
                                    $ul.append(liStr);
                                    dealLiClick();
                                    //加载数据结束
                                    liStyle($ul, true);
                                    if($ul.find("li").length == 0 ){
                                        $ul.append("<li style='color: red;' disabled>没有此数据...</li>");
                                        /*setTimeout(function () {
                                         $ul.empty();
                                         },800);*/
                                    }
                                }
                            }
                            else if (entryParam.displaymode == 1) {
                                if (entryParam.showresult.ishead == true) {//true：表示显示表头
                                    var liStr = "";
                                    //dealLiHeadStyle();
                                    //处理显示的条数
                                    liStr += dealLiData(obj.data, entryParam.shownum.minnum, entryParam.binddata.keyfield, entryParam.binddata);
                                    $ul.append(liStr);
                                    dealLiClick();
                                    liStyle($ul, true);
                                    if($ul.find("li").length == 0 ){
                                        $ul.append("<li style='color: red;'disabled >没有此数据...</li>");
                                        /*setTimeout(function () {
                                         $ul.empty();
                                         },800);*/
                                    }
                                }
                            }
                        }
                    }
                }, 500);
            }
        });
        /*//获取更多
         $layerContent.find(".more").on("click",function () {
         if(entryParam.event != undefined){
         if(entryParam.event.onseardata != undefined){
         var obj = entryParam.event.onseardata();

         $ulList.empty();
         //把数据显示到弹窗里面
         var liStr = '';
         /!****************表头结束****************************!/
         $ulList.append(dealLiData(obj.data,obj.showrow,entryParam.binddata.keyfield,entryParam.binddata)).css("display", "block");
         /!************表头样式复用开始**********************!/
         liStyle($ulList);
         }
         }
         });*/
        //还原
        function getwihei() {
            $(".Maximization").text("最大化");
            $layerContent.css({"width": changeBeforeWidth, "height": changeBeforeHeight, "top": "10%", "left": "40%"});
            $ulList.css({"height":parseFloat(changeBeforeHeight.split("px")[0]) - 97 + 'px',"overflow-y":"auto"});
        }
        //窗口最大化
        function minWidthHeight() {
            $(".Maximization").text("还原");
            changeBeforeHeight = $layerContent.css("height");
            changeBeforeWidth = $layerContent.css("width");
            $layerContent.css({"width": $("body").css("width"), "height": $("body").css("height"), "top": "0px", "left": "0px"});
            $ulList.css({"height":parseFloat($("body").css("height").split("px")[0]) - 97 + 'px',"overflow-y":"auto"});
        }
//处理多列样式
        function dealLisStyle(){
            var spanThis = $ulList.find("li:eq(1)").find("span");
            //先计算有多少个显示列
            var spanLen = 0;
            spanThis.each(function () {
                if($(this).css("display") != "none"){
                    spanLen++;
                }
            });
            var liWidth = $ulList.find("li").css('width');

            if(spanLen == 3){
                $ulList.find('li').find('span').each(function () {
                    if($(this).css("display") != "none"){
                        $(this).css({'border-bottom':'1px dashed darkgray','width':'31%','display': 'inline-block','padding': '0px 10px'});
                    }
                });
                $pTitle.find('span').each(function () {
                    if($(this).css("display") != "none"){
                        $(this).css({'width':'31%','display': 'inline-block','padding-left':'15px'});
                    }
                });
            }
            if(spanLen == 2){
                $ulList.find('li').find('span').each(function () {
                    if($(this).css("display") != "none"){
                        $(this).css({'border-bottom':'1px dashed darkgray','width':'41.5%','display': 'inline-block','padding': '0px 10px'});
                    }
                });
                $pTitle.find('span').each(function () {
                    if($(this).css("display") != "none"){
                        $(this).css({'width':'41.5%','display': 'inline-block','padding-left':'15px'});
                    }
                });
            }
            if(spanLen == 1){
                $ulList.find('li').find('span').each(function () {
                    if($(this).css("display") != "none"){
                        $(this).css({'border-bottom':'1px dashed darkgray','width':'98%','display': 'inline-block','padding': '0px 10px'});
                    }
                });
                $pTitle.find('span').each(function () {
                    if($(this).css("display") != "none"){
                        $(this).css({'width':'98%','display': 'inline-block','padding-left':'15px'});
                    }
                });
            }
        }
        function format(data_format) {
            if(entryParam.event.onformatval != undefined){
                formatVal = entryParam.event.onformatval(data_format);
            }
        }
    };


});
