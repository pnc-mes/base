//document.write("<script type='text/javascript' src='../../../../mesadmin/static/plugins/jstree/dist/jstree.min.js'></script>");
$(function(){
    var obj = window.location;
    var contextPath = obj.pathname.split("/")[1];
    var basePath = contextPath;
    var src="../../../../"+basePath+"/static/plugins/jstree/dist/jstree.min.js";
    new_element=document.createElement("script");
    new_element.setAttribute("type","text/javascript");
    new_element.setAttribute("src",src);
    document.body.appendChild(new_element);
});

(function() {
	$.JstreeEx = {
		own: null,
		init: function(config) {
            var handle={};
			var id = config.id;
			var data = config.data;
			var event = config.event;
			own = $("#" + id);
            own.data('jstree', false).empty();
            var icon=config.icon;
            var GUID=function () {
                this.date = new Date();
                if (typeof this.newGUID != 'function') {
                    GUID.prototype.newGUID = function () {
                        this.date = new Date(); var guidStr = '';
                        sexadecimalDate = this.hexadecimal(this.getGUIDDate(), 16);
                        sexadecimalTime = this.hexadecimal(this.getGUIDTime(), 16);
                        for (var i = 0; i < 9; i++) {
                            guidStr += Math.floor(Math.random() * 16).toString(16);
                        }
                        guidStr += sexadecimalDate;
                        guidStr += sexadecimalTime;
                        while (guidStr.length < 32) {
                            guidStr += Math.floor(Math.random() * 16).toString(16);
                        }
                        return this.formatGUID(guidStr);
                    }
                    GUID.prototype.getGUIDDate = function () {
                        return this.date.getFullYear() + this.addZero(this.date.getMonth() + 1) + this.addZero(this.date.getDay());
                    }
                    GUID.prototype.getGUIDTime = function () {
                        return this.addZero(this.date.getHours()) + this.addZero(this.date.getMinutes()) + this.addZero(this.date.getSeconds()) + this.addZero(parseInt(this.date.getMilliseconds() / 10));
                    }
                    GUID.prototype.addZero = function (num) {
                        if (Number(num).toString() != 'NaN' && num >= 0 && num < 10) {
                            return '0' + Math.floor(num);
                        } else {
                            return num.toString();
                        }
                    }
                    GUID.prototype.hexadecimal = function (num, x, y) {
                        if (y != undefined) { return parseInt(num.toString(), y).toString(x); }
                        else { return parseInt(num.toString()).toString(x); }
                    }
                    GUID.prototype.formatGUID = function (guidStr) {
                        var str1 = guidStr.slice(0, 8) + '-', str2 = guidStr.slice(8, 12) + '-', str3 = guidStr.slice(12, 16) + '-', str4 = guidStr.slice(16, 20) + '-', str5 = guidStr.slice(20);
                        return str1 + str2 + str3 + str4 + str5;
                    }
                }
            };
            var guid=new GUID();
			if(data != undefined) {
				if((data.source != undefined && data.source != null && data.source instanceof Array) &&
					(data.rule != undefined && data.rule != null && data.rule instanceof Array)) {
                    var level = 0;
                    var treeNodes = [];
                    var createTreeNode = function(parentNode,rule,treedata, level) {
                        var treeNode = {};
                        if(treedata instanceof Array) {
                            for(var i = 0; i < treedata.length; i++) {
                                var id = treedata[i][rule[level].id];
                                var text = treedata[i][rule[level].text];
                                var a_attr = treedata[i][rule[level].a_attr];
                                var li_attr = treedata[i][rule[level].li_attr];
                                var children = treedata[i][rule[level].children];

                                if((id != undefined && id != null) &&
                                    (text != undefined && text != null)) {
                                    treeNode = {
                                        "id": guid.newGUID() + "_" + id,
                                        "text": text

                                    };

                                    if(a_attr!=undefined && a_attr!=null){
                                        treeNode["a_attr"]=a_attr;
                                    }

                                    if(li_attr!=undefined && li_attr!=null){
                                        treeNode["li_attr"]=li_attr;
                                    }

                                    if(children != undefined && children != null) {
                                        treeNode["children"] = [];
                                       // createTreeNode(treeNode, rule,children, ++level);
                                        createTreeNode(treeNode, rule,children, level+1);
                                    }
                                    if(parentNode != null && parentNode['children'] != null) {
                                        parentNode['children'].push(treeNode);
                                    } else {
                                        level = 0;
                                        treeNodes.push(treeNode);
                                    }
                                }
                            }
                        } else {
                            var id = treedata[rule[level].id];
                            var text = treedata[rule[level].text];
                            var a_attr = treedata[i][rule[level].a_attr];
                            var li_attr = treedata[i][rule[level].li_attr];
                            var children = treedata[rule[level].children];
                            if((id != undefined && id != null) &&
                                (text != undefined && text != null)) {
                                treeNode = {
                                    "id": guid.newGUID()+"_"+id,
                                    "text": text
                                };

                                if(a_attr!=undefined && a_attr!=null){
                                    treeNode["a_attr"]=a_attr;
                                }

                                if(li_attr!=undefined && li_attr!=null){
                                    treeNode["li_attr"]=li_attr;
                                }

                                if(children != undefined && children != null) {
                                    treeNode["children"] = [];
                                    //createTreeNode(treeNode, rule,children, ++level);
                                    createTreeNode(treeNode, rule,children, level+1);
                                }
                                if(parentNode != null && parentNode['children'] != null) {
                                    parentNode['children'].push(treeNode);
                                } else {
                                    level = 0;
                                    treeNodes.push(treeNode);
                                }
                            }
                        }
                    };

					createTreeNode(null,data.rule,data.source, level);
                    own.jstree({
						'core': {
							'data': treeNodes,
							"check_callback": true,
							"animation": 0
						},
                        "dnd" : {
                            "drop_target" : false,
                            "drag_target" : false
                        },
                        "plugins" : ["search"]
					});

                  /*  own.jstree(true).settings.core.data = treeNodes;
                    own.jstree(true).refresh();*/
                    own.on("ready.jstree", function (e, data) {
                        if(event != undefined && event.onFinish != undefined) {
                            event.onFinish();
                        }
                    });
                    own.on("open_node.jstree",function (e,data) {
                        if(event != undefined && event.onExpand!=undefined) {
                            var _own = $("#" + data.node.id).closest('.jstree');
                            var node = {"root": data.node};
                            var childnodes=[];
                            $.each(data.node.children, function (key, node_id) {
                                var childnode = _own.jstree("get_node", node_id);
                                childnodes.push(childnode);
                            });
                            if(childnodes.length>0){
                                node["children"]=childnodes;
							}
                            event.onExpand(node);
                        }
                    });
				}
			}
			handle={
				"selectNode":null,
                "instance":null,
				"ckAddChild":function (rule,data) {
                    level = 0;
                    treeNodes = [];
                    createTreeNode(null, rule,data, level);
                    var objselectNode=this.selectNode;
                    var instance=this.instance;

                    var _own = $("#" + objselectNode.id).closest('.jstree');

                    if (objselectNode.children.length == 0) {
                        $.each(treeNodes, function (entryIndex, entry) {
                            _own.jstree('create_node', objselectNode, entry, 'last');                            //instance.create_node(entry, objselectNode,entryIndex + 1,null,true);
                            //instance.refresh();
                        });
                    }
                }
			};
            own.unbind("changed.jstree");
			own.on("changed.jstree", function(e, data) {

				if(event != undefined && event.onClick != undefined) {
					var objNode=data.selected;
                    var nodeinfo = {
                        "nodeID": objNode[0].split("_")[1],
                        "nodeText": data.node.text,
                        "hasChildren": data.instance.is_parent(data.instance.get_node(data.selected[0])),
                        "isRoot": data.instance.get_node(data.selected[0]).parent == "#" ? true : false
                    };
                    handle.instance=data.instance;
                    handle.selectNode=data.instance.get_node(data.selected[0]);
                    App.blockUI({
                        target: "body",
                        message: "正在加载中...",
                        boxed:true
                    });
                    var resultid = event.onClick(nodeinfo,handle);
                    if ((!resultid) || !resultid.empty()) {
                        return;
                    }
                }
			});





			return this;
		},
		setdata: function(data) {
			own.val(data);
		},
		//创建
		addNode:function() {
			var ref = own.jstree(true),
				sel = ref.get_selected();
			if(!sel.length) {
				return false;
			}
			sel = sel[0];
			sel = ref.create_node(sel, {
				"type": "file"
			});
			if(sel) {
				ref.edit(sel);
			}
		},
		//修改 
		editNode: function() {
			var ref = own.jstree(true),
				sel = ref.get_selected();
			if(!sel.length) {
				return false;
			}
			sel = sel[0];
			ref.edit(sel);
		},
		//删除
		removeNode: function() {
			var ref = own.jstree(true),
				sel = ref.get_selected();
			if(!sel.length) {
				return false;
			}
			ref.delete_node(sel);
		}
	}
})(jQuery);