/**
 * Created by LENOVO on 2018/9/4.
 */
$(function () {


var setting = {
    view: {
        dblClickExpand: false,
        selectedMulti: false,
        autoCancelSelected: true
    },
    check: {
        enable: true,
        radioType: "all"
    },
    data: {
        simpleData: {
            idKey: "id",
            pIdKey: "pid",
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick,
        onClick: onClick,
        onDblClick: onDblClick
    }
};
//var zNodes =${znode};
var zNodes;
//可以判断哪些节点不可选
    function beforeClick(treeId, treeNode) {
        var check = true;
        /*if(treeNode.id.toString().substring(8).match(/^0{4}$/)){
         check = false;
         alert("只能选择第三级菜单。。。");
         }*/
        return check;
    }
//点击节点动作
    function onClick(e, treeId, treeNode) {
        /*var pNode = treeNode.getParentNode();
         var ppNode = pNode.getParentNode();

         $('#areaid').val(ppNode.id);
         $('#areaname').val(ppNode.name);

         $('#substationid').val(pNode.id);
         $('#substationname').val(pNode.name);*/

        $('#zzjgOfficeId').val(treeNode.id);
        $('#zzjgName').val(treeNode.name);
        $('#areaSel').val(treeNode.name);


    }
    //双击节点动作
    function onDblClick(e, treeId, treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj("areaTree");
        $('#zzjgOfficeId').val("");
        $('#zzjgName').val("");
        $('#areaSel').val("");
        treeObj.cancelSelectedNode(treeNode);
    }
//显示下拉树
    function showMenu() {
        var areaObj = $("#areaSel");
        var areaOffset = $("#areaSel").offset();
        $("#menuContent").css({left:areaOffset.left + "px", top:areaOffset.top + areaObj.outerHeight() + "px"}).slideDown("fast");
        $("#bodyId").bind("mousedown", onBodyDown);
    }
    //隐藏下拉树
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("#bodyId").unbind("mousedown", onBodyDown);
    }
    //注册关闭下拉树的事件
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "areaSel" ||  event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }
    //下拉树搜索框搜索
    function AutoMatch(txtObj) {
        if (txtObj.value.length > 0) {
            var treeObj = $.fn.zTree.getZTreeObj("areaTree");
            var nodeList = treeObj.getNodesByParamFuzzy("name", txtObj.value,null);
            //将找到的nodelist节点更新至Ztree内
            $.fn.zTree.init($("#areaTree"), setting, nodeList);
        } else {
            $.fn.zTree.init($("#areaTree"), setting, zNodes);
        }
    }
    $(function () {
        $.ajax({
            url: '',
            type: 'POST',
            dataType : "json",
            data: {id: ""},
            async: false,
            success: function (data) {
                var d = $.parseJSON(data);
                zNodes = d;
            }
        });
        //初始化下拉树
        $.fn.zTree.init($("#areaTree"), setting, zNodes);
    });

});