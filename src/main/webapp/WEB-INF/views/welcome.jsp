<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html style="height: 100%;width: 100%">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <!-- Tell the browser to be responsive to screen width -->

    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta charset="utf-8" />

</head>
<body style="height: 100%;width: 100%" >
<div style="width:100%;height:100%;">
    <img class="img" src="${pageContext.request.contextPath}/static/pnsadmin/Base/images/flow.jpg" style="width: 100%;height: 99%;"/>
</div>
<!-- jQuery 2.2.3 -->
<script src="${pageContext.request.contextPath}/static/ui/global/jquery-1-11-1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/ui/global/jquery-ui/jquery-ui.min.js"></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery-migrate-1.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/static/plugins/jquery.jqprint/jquery.jqprint-0.3.js'></script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/static/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/min/js/supershopui.common.js"></script>
</body>
<script type="text/javascript">
    document.onmousemove = function(e) {
        //获取body的宽
        var width = document.body.clientWidth;
        //获取body的高
        var hieght = document.body.clientHeight;
        var x = e.clientX;
        var y = e.clientY;
        //拿去用户信息localStorage.user;

        //客户订单
        var x1=42/1134;
        var x2=103/1134;
        var y1=113/559;
        var y2=150/559;

        //生产工单
        var x21=42/1134;
        var x22=95/1134;
        var y21=200/559;
        var y22=235/559;

        //打印条码
        var x31=43/1134;
        var x32=94/1134;
        var y31=276/559;
        var y32=315/559;

        //工厂
        var x41=48/1134;
        var x42=93/1134;
        var y41=350/559;
        var y42=385/559;

        //产线
        var x51=51/1134;
        var x52=88/1134;
        var y51=419/559;
        var y52=450/559;

        //人员
        var x61=47/1134;
        var x62=92/1134;
        var y61=484/559;
        var y62=523/559;

        //产品管理
        var x71=183/1134;
        var x72=275/1134;
        var y71=196/559;
        var y72=239/559;

        //设备
        var x81=157/1134;
        var x82=204/1134;
        var y81=411/559;
        var y82=455/559;

        //BOM
        var x91=350/1134;
        var x92=414/1134;
        var y91=147/559;
        var y92=184/559;

        //工艺流程
        var x101=342/1134;
        var x102=407/1134;;
        var y101=273/559;
        var y102=316/559;

        //背板上料
        var x111=348/1134;
        var x112=405/1134;
        var y111=349/559;
        var y112=388/559;

        //电池摆拍
        var x121=443/1134;
        var x122=491/1134;
        var y121=348/559;
        var y122=387/559;

        //压后检
        var x131=528/1134;
        var x132=584/1134;
        var y131=348/559;
        var y132=384/559;

        //采购订单
        var x141=624/1134;
        var x142=672/1134;
        var y141=17/559;
        var y142=57/559;

        //到货通知单
        var x151=622/1134;
        var x152=666/1134;
        var y151=96/559;
        var y152=132/559;

        //到货清点贴码
        var x161=615/1134;
        var x162=670/1134;
        var y161=172/559;
        var y162=199/559;

        //工位上料
        var x171=619/1134;
        var x172=676/1134;
        var y171=250/559;
        var y172=293/559;

        //接线盒
        var x181=625/1134;
        var x182=679/1134;
        var y181=348/559;
        var y182=384/559;

        //点检
        var x191=614/1134;
        var x192=671/1134;
        var y191=439/559;
        var y192=481/559;

        //IQC检验
        var x201=721/1134;
        var x202=756/1134;
        var y201=172/559;
        var y202=198/559;

        //固化
        var x211=715/1134;
        var x212=773/1134;
        var y211=349/559;
        var y212=385/559;


        //库存管理
        var x221=836/1134;
        var x222=900/1134;
        var y221=168/559;
        var y222=203/559;

        //工单领料
        var x231=846/1134;
        var x232=889/1134;
        var y231=256/559;
        var y232=289/559;

        //终检
        var x241=817/1134;
        var x242=868/1134;
        var y241=348/559;
        var y242=385/559;

        //包装
        var x251=909/1134;
        var x252=965/1134;
        var y251=346/559;
        var y252=387/559;

        //成品出库
        var x261=1028/1134;
        var x262=1072/1134;
        var y261=35/559;
        var y262=67/559;

        //出库申请单
        var x271=1027/1134;
        var x272=1075/1134;
        var y271=105/559;
        var y272=135/559;

        //成品入库
        var x281=1021/1134;
        var x282=1077/1134;
        var y281=173/559;
        var y282=210/559;


        //FQC检验
        var x291=1026/1134;
        var x292=1073/1134;
        var y291=261/559;
        var y292=295/559;

        //入库申请
        var x301=1024/1134;
        var x302=1073/1134;
        var y301=345/559;
        var y302=387/559;



        if (x > (x1 * width) && x < (x2 * width) && y > (y1 * hieght) && y < (y2 * hieght)) {
            //客户订单
            $(".img").css("cursor", "pointer");
        }else  if(x>(x21*width)&&x<(x22*width)&&y>(y21*hieght)&&y<(y22*hieght)){
            //生产工单
            $(".img").css("cursor", "pointer");
        } else  if(x>(x31*width)&&x<(x32*width)&&y>(y31*hieght)&&y<(y32*hieght)){
            //打印条码
            $(".img").css("cursor", "pointer");
        }else  if(x>(x41*width)&&x<(x42*width)&&y>(y41*hieght)&&y<(y42*hieght)){
            //工厂
            $(".img").css("cursor", "pointer");
        }else if(x>(x51*width)&&x<(x52*width)&&y>(y51*hieght)&&y<(y52*hieght)){
            //产线
            $(".img").css("cursor", "pointer");
        }else  if(x>(x61*width)&&x<(x62*width)&&y>(y61*hieght)&&y<(y62*hieght)){
            //人员
            $(".img").css("cursor", "pointer");
        }else  if(x>(x71*width)&&x<(x72*width)&&y>(y71*hieght)&&y<(y72*hieght)){
            //产品管理
            $(".img").css("cursor", "pointer");
        }else  if(x>(x81*width)&&x<(x82*width)&&y>(y81*hieght)&&y<(y82*hieght)){
            //设备
            $(".img").css("cursor", "pointer");
        }else if(x>(x91*width)&&x<(x92*width)&&y>(y91*hieght)&&y<(y92*hieght)){
            //BOM
            $(".img").css("cursor", "pointer");
        }else if(x>(x101*width)&&x<(x102*width)&&y>(y101*hieght)&&y<(y102*hieght)){
            //工艺流程
            $(".img").css("cursor", "pointer");
        }else if(x>(x111*width)&&x<(x112*width)&&y>(y111*hieght)&&y<(y112*hieght)){
            //背板上料
            $(".img").css("cursor", "pointer");
        }else  if(x>(x121*width)&&x<(x122*width)&&y>(y121*hieght)&&y<(y122*hieght)){
            //电池摆拍
            $(".img").css("cursor", "pointer");
        }else  if(x>(x131*width)&&x<(x132*width)&&y>(y131*hieght)&&y<(y132*hieght)){
            //压后检
            $(".img").css("cursor", "pointer");
        }else if(x>(x141*width)&&x<(x142*width)&&y>(y141*hieght)&&y<(y142*hieght)){
            //采购订单
            $(".img").css("cursor", "pointer");
        }else  if(x>(x151*width)&&x<(x152*width)&&y>(y151*hieght)&&y<(y152*hieght)){
            //到货通知单
            $(".img").css("cursor", "pointer");
        }else if(x>(x161*width)&&x<(x162*width)&&y>(y161*hieght)&&y<(y162*hieght)){
            //到货清点贴码
            $(".img").css("cursor", "default");
        }else  if(x>(x171*width)&&x<(x172*width)&&y>(y171*hieght)&&y<(y172*hieght)){
            //工位上料
            $(".img").css("cursor", "default");
        }else  if(x>(x181*width)&&x<(x182*width)&&y>(y181*hieght)&&y<(y182*hieght)){
            //接线盒
            $(".img").css("cursor", "pointer");
        }else if(x>(x191*width)&&x<(x192*width)&&y>(y191*hieght)&&y<(y192*hieght)){
            //点检
            $(".img").css("cursor", "pointer");
        }else if(x>(x201*width)&&x<(x202*width)&&y>(y201*hieght)&&y<(y202*hieght)){
            //IQC检验
            $(".img").css("cursor", "pointer");$(".img").css("cursor", "pointer");
        }else if(x>(x211*width)&&x<(x212*width)&&y>(y211*hieght)&&y<(y212*hieght)){
            //固化
            $(".img").css("cursor", "pointer");
        }else if(x>(x221*width)&&x<(x222*width)&&y>(y221*hieght)&&y<(y222*hieght)){
            //库存管理
            $(".img").css("cursor", "pointer");
        }else  if(x>(x231*width)&&x<(x232*width)&&y>(y231*hieght)&&y<(y232*hieght)){
            //工单领料
            $(".img").css("cursor", "pointer");
        }else  if(x>(x241*width)&&x<(x242*width)&&y>(y241*hieght)&&y<(y242*hieght)){
            //终检
            $(".img").css("cursor", "pointer");
        }else if(x>(x251*width)&&x<(x252*width)&&y>(y251*hieght)&&y<(y252*hieght)){
            //包装
            $(".img").css("cursor", "pointer");
        }else  if(x>(x261*width)&&x<(x262*width)&&y>(y261*hieght)&&y<(y262*hieght)){
            //成品出库
            $(".img").css("cursor", "default");
        }else if(x>(x271*width)&&x<(x272*width)&&y>(y271*hieght)&&y<(y272*hieght)){
            //出库申请单
            $(".img").css("cursor", "default");
        }else if(x>(x281*width)&&x<(x282*width)&&y>(y281*hieght)&&y<(y282*hieght)){
            //成品入库
            $(".img").css("cursor", "default");
        }else if(x>(x291*width)&&x<(x292*width)&&y>(y291*hieght)&&y<(y292*hieght)){
            //FQC检验
            $(".img").css("cursor", "default");
        }else  if(x>(x301*width)&&x<(x302*width)&&y>(y301*hieght)&&y<(y302*hieght)){
            //入库申请
            $(".img").css("cursor", "default");
        }else{
            $(".img").css("cursor", "default");
        }
    }
    document.onclick = function (event) {
        event = event || window.event;

        var x = event.clientX;
        var y = event.clientY;
        //获取body的宽
        var width=document.body.clientWidth;
        //获取body的高
        var hieght= document.body.clientHeight;

        var map=new Map();//存储点击的X,Y轴值
        map.set("客户订单",{"x1":25/1134,"x2":116/1134,"y1":13/559,"y2":48/559});
        map.set("生产工单",{"x1":25/1134,"x2":116/1134,"y1":124/559,"y2":159/559});
        map.set("批次",{"x1":25/1134,"x2":116/1134,"y1":245/559,"y2":280/559});
        map.set("工厂",{"x1":25/1134,"x2":116/1134,"y1":330/559,"y2":365/559});
        map.set("生产线",{"x1":25/1134,"x2":116/1134,"y1":407/559,"y2":442/559});
        map.set("人员",{"x1":25/1134,"x2":116/1134,"y1":497/559,"y2":532/559});
        map.set("产品",{"x1":193/1134,"x2":284/1134,"y1":124/559,"y2":159/559});
        map.set("设备",{"x1":193/1134,"x2":284/1134,"y1":407/559,"y2":442/559});
        map.set("物料清单",{"x1":346/1134,"x2":437/1134,"y1":81/559,"y2":117/559});
        map.set("生产流程",{"x1":346/1134,"x2":437/1134,"y1":245/559,"y2":280/559});
        map.set("工位1",{"x1":346/1134,"x2":437/1134,"y1":330/559,"y2":365/559});
        map.set("工艺1",{"x1":346/1134,"x2":437/1134,"y1":407/559,"y2":442/559});
        map.set("作业1",{"x1":346/1134,"x2":437/1134,"y1":497/559,"y2":532/559});
        map.set("原物料",{"x1":509/1134,"x2":600/1134,"y1":497/559,"y2":532/559});
        map.set("工位2",{"x1":509/1134,"x2":600/1134,"y1":330/559,"y2":365/559});
        map.set("工艺2",{"x1":509/1134,"x2":600/1134,"y1":407/559,"y2":442/559});
        map.set("作业2",{"x1":509/1134,"x2":600/1134,"y1":497/559,"y2":532/559});
        map.set("采购订单",{"x1":666/1134,"x2":761/1134,"y1":13/559,"y2":48/559});
        map.set("采购订单",{"x1":666/1134,"x2":761/1134,"y1":81/559,"y2":116/559});
        map.set("领料出库",{"x1":669/1134,"x2":761/1134,"y1":168/559,"y2":202/559});
        map.set("工位上料",{"x1":669/1134,"x2":761/1134,"y1":245/559,"y2":245/559});
        map.set("工位3",{"x1":670/1134,"x2":761/1134,"y1":331/559,"y2":367/559});
        map.set("工艺3",{"x1":666/1134,"x2":760/1134,"y1":408/559,"y2":443/559});
        map.set("作业3",{"x1":669/1134,"x2":760/1134,"y1":497/559,"y2":532/559});
        map.set("返工流程",{"x1":841/1134,"x2":934/1134,"y1":246/559,"y2":280/559});
        map.set("工位4",{"x1":841/1134,"x2":934/1134,"y1":331/559,"y2":367/559});
        map.set("工艺4",{"x1":841/1134,"x2":934/1134,"y1":408/559,"y2":442/559});
        map.set("作业4",{"x1":841/1134,"x2":934/1134,"y1":496/559,"y2":532/559});
        map.set("销售订单",{"x1":1002/1134,"x2":1096/1134,"y1":13/559,"y2":47/559});
        map.set("成品入库",{"x1":1002/1134,"x2":1096/1134,"y1":79/559,"y2":119/559});
        map.set("成品出库",{"x1":1002/1134,"x2":1096/1134,"y1":245/559,"y2":279/559});
        map.set("包装",{"x1":1002/1134,"x2":1096/1134,"y1":331/559,"y2":368/559});
        // 1134 559

        //alert("x:"+x+","+"y:"+y);
        var data=map.get("客户订单");
        //客户订单
        var x1=42/1134;
        var x2=103/1134;
        var y1=113/559;
        var y2=150/559;
        if(x>(x1*width)&&x<(x2*width)&&y>(y1*hieght)&&y<(y2*hieght)){
            parent.addTabs({
                id: '230',
                title: '订单管理',
                close: true,
                url: "${pageContext.request.contextPath}/CO/COPG"
            });
        }

        //生产工单
        var x21=42/1134;
        var x22=95/1134;
        var y21=200/559;
        var y22=235/559;
        if(x>(x21*width)&&x<(x22*width)&&y>(y21*hieght)&&y<(y22*hieght)){
            parent.addTabs({
                id: '354',
                title: '工单管理',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/NewWO/WOPG"
            });
        }

        //打印条码
        var x31=43/1134;
        var x32=94/1134;
        var y31=276/559;
        var y32=315/559;
        if(x>(x31*width)&&x<(x32*width)&&y>(y31*hieght)&&y<(y32*hieght)){
            parent.addTabs({
                id: '336',
                title: '打印组件条码',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/Board/PrintSNPG"
            });
        }


        //工厂
        var x41=48/1134;
        var x42=93/1134;
        var y41=350/559;
        var y42=385/559;
        if(x>(x41*width)&&x<(x42*width)&&y>(y41*hieght)&&y<(y42*hieght)){
            parent.addTabs({
                id: '5',
                title: '工厂',
                close: true,
                url: "${pageContext.request.contextPath}/Factory/FactoryPG"
            });
        }

        //产线
        var x51=51/1134;
        var x52=88/1134;
        var y51=419/559;
        var y52=450/559;
        if(x>(x51*width)&&x<(x52*width)&&y>(y51*hieght)&&y<(y52*hieght)){
            parent.addTabs({
                id: '120',
                title: '生产线',
                close: true,
                url: "${pageContext.request.contextPath}/Line/LinePG"
            });
        }

        //人员
        var x61=47/1134;
        var x62=92/1134;
        var y61=484/559;
        var y62=523/559;
        if(x>(x61*width)&&x<(x62*width)&&y>(y61*hieght)&&y<(y62*hieght)){
            parent.addTabs({
                id: '1',
                title: '用户管理',
                close: true,
                url: "${pageContext.request.contextPath}/User/UserPG"
            });
        }

        //产品管理
        var x71=183/1134;
        var x72=275/1134;
        var y71=196/559;
        var y72=239/559;
        if(x>(x71*width)&&x<(x72*width)&&y>(y71*hieght)&&y<(y72*hieght)){
            parent.addTabs({
                id: '20',
                title: '物料管理',
                close: true,
                url: "${pageContext.request.contextPath}/Material/MaterialPG"
            });
        }

        //设备
        var x81=157/1134;
        var x82=204/1134;
        var y81=411/559;
        var y82=455/559;
        if(x>(x81*width)&&x<(x82*width)&&y>(y81*hieght)&&y<(y82*hieght)){
            parent.addTabs({
                id: '272',
                title: '设备管理',
                close: true,
                url: "${pageContext.request.contextPath}/Device/DevicePG"
            });
        }

        //BOM
        var x91=350/1134;
        var x92=414/1134;
        var y91=147/559;
        var y92=184/559;
        if(x>(x91*width)&&x<(x92*width)&&y>(y91*hieght)&&y<(y92*hieght)){
            parent.addTabs({
                id: '22',
                title: 'BOM管理',
                close: true,
                url: "${pageContext.request.contextPath}/BOM/BOMPG"
            });
        }

        //工艺流程
        var x101=342/1134;
        var x102=407/1134;;
        var y101=273/559;
        var y102=316/559;
        if(x>(x101*width)&&x<(x102*width)&&y>(y101*hieght)&&y<(y102*hieght)){
            parent.addTabs({
                id: '24',
                title: '工艺流程管理',
                close: true,
                url: "${pageContext.request.contextPath}/WF/WFPG"
            });
        }

        //背板上料
        var x111=348/1134;
        var x112=405/1134;
        var y111=349/559;
        var y112=388/559;
        if(x>(x111*width)&&x<(x112*width)&&y>(y111*hieght)&&y<(y112*hieght)){
            parent.addTabs({
                id: '335',
                title: '组件上载板',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/Board/SnszbPG"
            });
        }


        //电池摆拍
        var x121=443/1134;
        var x122=491/1134;
        var y121=348/559;
        var y122=387/559;
        if(x>(x121*width)&&x<(x122*width)&&y>(y121*hieght)&&y<(y122*hieght)){
            parent.addTabs({
                id: '337',
                title: '摆电池片检验',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/BP/dczzPG"
            });
        }

        //压后检
        var x131=528/1134;
        var x132=584/1134;
        var y131=348/559;
        var y132=384/559;
        if(x>(x131*width)&&x<(x132*width)&&y>(y131*hieght)&&y<(y132*hieght)){
            parent.addTabs({
                id: '239',
                title: '压后目检',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/YHCheck/yhmjPG"
            });
        }

        //采购订单
        var x141=624/1134;
        var x142=672/1134;
        var y141=17/559;
        var y142=57/559;
        if(x>(x141*width)&&x<(x142*width)&&y>(y141*hieght)&&y<(y142*hieght)){
            parent.addTabs({
                id: '62',
                title: '采购单管理',
                close: true,
                url: "${pageContext.request.contextPath}/Purch/PurchPG"
            });
        }

        //到货通知单
        var x151=622/1134;
        var x152=666/1134;
        var y151=96/559;
        var y152=132/559;
        if(x>(x151*width)&&x<(x152*width)&&y>(y151*hieght)&&y<(y152*hieght)){
           parent.addTabs({
                id: '61',
                title: '来料收货通知单管理',
                close: true,
                url: "${pageContext.request.contextPath}/Ich/IchPG"
            });
        }


        //到货清点贴码
        var x161=615/1134;
        var x162=670/1134;
        var y161=172/559;
        var y162=199/559;
        if(x>(x161*width)&&x<(x162*width)&&y>(y161*hieght)&&y<(y162*hieght)){

        }

        //工位上料
        var x171=619/1134;
        var x172=676/1134;
        var y171=250/559;
        var y172=293/559;
        if(x>(x171*width)&&x<(x172*width)&&y>(y171*hieght)&&y<(y172*hieght)){
            /*parent.addTabs({
             id: '',
             title: '',
             close: '',
             url: ""
             });*/
            //alert("工位上料");
        }

        //接线盒
        var x181=625/1134;
        var x182=679/1134;
        var y181=348/559;
        var y182=384/559;

        if(x>(x181*width)&&x<(x182*width)&&y>(y181*hieght)&&y<(y182*hieght)){
            parent.addTabs({
                id: '240',
                title: '安装接线盒',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/JXInstall/JXInstallPG"
            });
        }

        //点检
        var x191=614/1134;
        var x192=671/1134;
        var y191=439/559;
        var y192=481/559;
        if(x>(x191*width)&&x<(x192*width)&&y>(y191*hieght)&&y<(y192*hieght)){
            parent.addTabs({
                id: '301',
                title: '点检计划管理',
                close: true,
                url: "${pageContext.request.contextPath}/CheckPlan/CheckPlanPG"
            });
        }

        //IQC检验
        var x201=721/1134;
        var x202=756/1134;
        var y201=172/559;
        var y202=198/559;
        if(x>(x201*width)&&x<(x202*width)&&y>(y201*hieght)&&y<(y202*hieght)){
            parent.addTabs({
                id: '165',
                title: 'IQC管理',
                close: true,
                url: "${pageContext.request.contextPath}/IQC/IQCPG"
            });
        }

        //固化
        var x211=715/1134;
        var x212=773/1134;
        var y211=349/559;
        var y212=385/559;
        if(x>(x211*width)&&x<(x212*width)&&y>(y211*hieght)&&y<(y212*hieght)){
            parent.addTabs({
                id: '243',
                title: '固化',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/GH/GHPG"
            });
        }

        //库存管理
        var x221=836/1134;
        var x222=900/1134;
        var y221=168/559;
        var y222=203/559;
        if(x>(x221*width)&&x<(x222*width)&&y>(y221*hieght)&&y<(y222*hieght)){
            parent.addTabs({
                id: '329',
                title: '库存信息',
                close: true,
                url: "${pageContext.request.contextPath}/InStore/InStorePG"
            });
        }

        //工单领料
        var x231=846/1134;
        var x232=889/1134;
        var y231=256/559;
        var y232=289/559;
        if(x>(x231*width)&&x<(x232*width)&&y>(y231*hieght)&&y<(y232*hieght)){
            parent.addTabs({
                id: '327',
                title: '工单领料',
                close: true,
                url: "${pageContext.request.contextPath}/Pick/PickPG"
            });
        }


        //终检
        var x241=817/1134;
        var x242=868/1134;
        var y241=348/559;
        var y242=385/559;
        if(x>(x241*width)&&x<(x242*width)&&y>(y241*hieght)&&y<(y242*hieght)){
            parent.addTabs({
                id: '244',
                title: '终检',
                close: true,
                url: "${pageContext.request.contextPath}/SunPort/ZJCheck/zjPG"
            });
        }

        //包装
        var x251=909/1134;
        var x252=965/1134;
        var y251=346/559;
        var y252=387/559;
        if(x>(x251*width)&&x<(x252*width)&&y>(y251*hieght)&&y<(y252*hieght)){
            parent.addTabs({
                id: '28',
                title: '包装',
                close: true,
                url: "${pageContext.request.contextPath}/Pack/PackPG"
            });
        }

        //成品出库
        var x261=1028/1134;
        var x262=1072/1134;
        var y261=35/559;
        var y262=67/559;
        if(x>(x261*width)&&x<(x262*width)&&y>(y261*hieght)&&y<(y262*hieght)){

        }

        //出库申请单
        var x271=1027/1134;
        var x272=1075/1134;
        var y271=105/559;
        var y272=135/559;
        if(x>(x271*width)&&x<(x272*width)&&y>(y271*hieght)&&y<(y272*hieght)){

        }

        //成品入库
        var x281=1021/1134;
        var x282=1077/1134;
        var y281=173/559;
        var y282=210/559;
        if(x>(x281*width)&&x<(x282*width)&&y>(y281*hieght)&&y<(y282*hieght)){
            /*parent.addTabs({
             id: '',
             title: '',
             close: '',
             url: ""
             });*/

        }

        //FQC检验
        var x291=1026/1134;
        var x292=1073/1134;
        var y291=261/559;
        var y292=295/559;
        if(x>(x291*width)&&x<(x292*width)&&y>(y291*hieght)&&y<(y292*hieght)){
            /*parent.addTabs({
             id: '',
             title: '',
             close: '',
             url: ""
             });*/

        }

        //入库申请
        var x301=1024/1134;
        var x302=1073/1134;
        var y301=345/559;
        var y302=387/559;
        if(x>(x301*width)&&x<(x302*width)&&y>(y301*hieght)&&y<(y302*hieght)){
            /*parent.addTabs({
             id: '',
             title: '',
             close: '',
             url: ""
             });*/

        }

        //成品出库
      /**  var x311=1002/1134;
        var x312=1096/1134;
        var y311=245/559;
        var y312=279/559;
        if(x>(x311*width)&&x<(x312*width)&&y>(y311*hieght)&&y<(y312*hieght)){
            parent.addTabs({
             id: '',
             title: '',
             close: '',
             url: ""
             });
            alert("成品出库");
        }*/

        //包装
        /* var x321=1002/1134;
        var x322=1096/1134;
        var y321=331/559;
        var y322=368/559;
        if(x>(x311*width)&&x<(x312*width)&&y>(y311*hieght)&&y<(y312*hieght)){
          parent.addTabs({
             id: '',
             title: '',
             close: '',
             url: ""
             });
            alert("包装");
        }*/


    }

</script>

</html>
