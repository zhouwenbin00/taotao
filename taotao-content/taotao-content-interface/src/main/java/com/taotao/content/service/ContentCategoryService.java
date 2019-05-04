package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	//通过节点id查询该节点的子节点列表
	public List<EasyUITreeNode> getContentCategoryList(Long parentId);
	//添加内容分类 
	//父节点id
	//新增节点name
	public TaotaoResult createContentCategory(Long parentId,String name);
	//修改节点,参数：id、text
	public TaotaoResult updateContentCategory(Long id,String name);
	//删除节点,参数:id
	public TaotaoResult deleteContentCategory(Long id);
	
}
