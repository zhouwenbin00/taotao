package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
/**
 * 处理内容的Controller
 * @author root
 *
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	/**查询内容列表
	 * /content/query/list
	 * method:get
	 */
	@RequestMapping(value="/content/query/list",method=RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult queryContentList(Long categoryId, Integer page, Integer rows) {
		return contentService.queryContentList(categoryId, page, rows);
	}
	
	/**
	 * 添加内容
	 * url:/content/save
	 * method:post
	 */
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveContent(TbContent content) {
		//1.引入服务
		//2.注入服务
		//3.调用
		return contentService.saveContent(content);
	}
	/**
	 * /rest/content/edit
	 * method:post
	 */
	@RequestMapping(value="/rest/content/edit",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult editContent(TbContent content) {
		return contentService.editContent(content);
	}
	
	/**
	 * 删除内容
	 * url:/content/delete
	 * method:post
	 * 参数：ids
	 */
	@RequestMapping(value="/content/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContent(Long[] ids) {
		return contentService.deleteContent(ids);
	}
}
