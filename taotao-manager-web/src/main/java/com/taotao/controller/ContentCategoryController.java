package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
/**
 * 内容分类的处理Controller
 * @author root
 *
 */
@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService service;
	/**
	 * url:/content/category/list
	 * animate:true
	 * method:GET
	 * 参数：id
	 */
	@RequestMapping(value="/content/category/list",method=RequestMethod.GET)
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		//1.引入服务
		//2.注入
		//3.调用
		return service.getContentCategoryList(parentId);
	}
	
	/**
	 * 添加节点
	 * url:/content/category/create
	 * method:post
	 * 参数：parentId:父节点id,name:新增节点的文本
	 * 返回值：taotaoresult 包含分类的id
	 */
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId,String name) {
		//1.引入
		
		return service.createContentCategory(parentId, name);
	}
	
	/**
	 * url:"/content/category/update"
	 * method:post
	 * 参数：id,name
	 * 返回值：taotaoresult
	 */
	@RequestMapping(value="/content/category/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id,String name) {
		return service.updateContentCategory(id, name);
	}
	
	/**
	 * $.post("/content/category/delete/",{id:node.id}
	 */
	@RequestMapping(value="/content/category/delete/",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id) {
		return service.deleteContentCategory(id);
	}
	
}
