<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">  </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#contentCategory").tree({
		//获取数据的连接
		url : '/content/category/list',
		animate: true,//动画效果开
		method : "GET",//get方式提交
		fit:true,
		//当右键点击节点时触发事件
		onContextMenu: function(e,node){
			//preventDefault() 方法阻止元素发生默认的行为（此处消除默认右键菜单）。
            e.preventDefault();
            //目标节点
            $(this).tree('select',node.target);
            //显示自定义菜单
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        //	编辑节点后触发事件
        onAfterEdit : function(node){
        	//获取树本身
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点
        		//ajax请求
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,//更新那个节点
            				id : data.data.id//更新新增节点的id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:node.id,name:node.text});
        	}
        }
	});
});
function menuHandler(item){
	var tree = $("#contentCategory");
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: '新建分类',//节点的内容
                id : 0,//节点的id
                parentId : node.id//新增节点的父节点的id
            }]
        }); 
        //找到id为0的节点,就是要添加的节点
		var _node = tree.tree('find',0);
		//选择id为0的节点，开启编辑
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		if(!tree.tree("isLeaf",node.target)){
			$.messager.alert('提示',"该分类下面还有内容,删除失败!")
		}else{
			$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
				if(r){
					$.post("/content/category/delete/",{id:node.id},function(){
						tree.tree("remove",node.target);
					});	
				}
			});
		}
	}
}
</script>