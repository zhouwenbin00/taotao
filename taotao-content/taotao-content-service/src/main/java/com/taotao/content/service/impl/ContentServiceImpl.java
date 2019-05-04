package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	private TbContentMapper mapper;
	@Override
	public TaotaoResult saveContent(TbContent content) {
		//1.注入mapper
		//2.补全属性
		content.setCreated(new Date());
		content.setUpdated(content.getCreated());
		//3。插入
		mapper.insertSelective(content);
		return TaotaoResult.ok();
	}
	/**
	 * 查询内容列表
	 */
	@Override
	public EasyUIDataGridResult queryContentList(Long categoryId, Integer page, Integer rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		// 2.创建example
		TbContentExample example =new TbContentExample();
		//设置条件
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = mapper.selectByExample(example);
		//5.获取分页的信息
		PageInfo<TbContent> info = new PageInfo<TbContent>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int)info.getTotal());
		result.setRows(info.getList());
		return result;
	}
	
	/**
	 * 编辑内容
	 */
	@Override
	public TaotaoResult editContent(TbContent content) {
		//设置更新时间
		content.setUpdated(new Date());
		//执行更新
		mapper.updateByPrimaryKey(content);
		return TaotaoResult.ok();
	}
	/**
	 * 删除内容
	 */
	@Override
	public TaotaoResult deleteContent(Long[] ids) {
		for (Long id : ids) {
			mapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}
	/**
	 * 根据内容分类的ID，查询其下的内容列表
	 */
	@Override
	public List<TbContent> getContentListByCatId(Long categoryId) {
		//1.注入mapper
		//2.创建example
		TbContentExample example=new TbContentExample();
		//3.设置查询条件
		example.createCriteria().andCategoryIdEqualTo(categoryId);//select * from tncontent where categoryIdId = ?
		//4.执行查询
		List<TbContent> list = mapper.selectByExample(example);
		//返回
		return list;
	}

}
