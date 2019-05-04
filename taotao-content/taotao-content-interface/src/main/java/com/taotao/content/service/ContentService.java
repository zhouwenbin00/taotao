package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * 内容处理的接口
 * @author root
 *
 */
public interface ContentService {
	/**
	 * 插入内容表
	 * @param content
	 * @return
	 */
	public TaotaoResult	 saveContent(TbContent content);
	
	/**
	 * 显示内容列表
	 * 参数：categoryId=0&page=1&rows=20
	 */
	public EasyUIDataGridResult queryContentList(Long categoryId,Integer page,Integer rows) ;
	/**
	 * 编辑内容
	 * 参数：content
	 */
	public TaotaoResult editContent(TbContent content);
	/**
	 * 删除内容
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteContent(Long[] ids);
	/**
	 * 根据内容分类的ID，查询其下的内容列表
	 * @param categoryId
	 * @return
	 */
	public List<TbContent> getContentListByCatId(Long categoryId);
}
