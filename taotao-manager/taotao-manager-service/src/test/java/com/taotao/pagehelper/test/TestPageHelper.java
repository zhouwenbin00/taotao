package com.taotao.pagehelper.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {
	@Test
	public void test1() {
		//加载配置文件
		ApplicationContext applicationContext =new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//获取mapper的代理对象
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		//设置分页信息
		PageHelper.startPage(1, 3);
		//调用，mapper的方法查询数据
		TbItemExample example=new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		List<TbItem> list2 = tbItemMapper.selectByExample(example);
		
		System.out.println("第一个list的长度"+list.size());
		System.out.println("第二个list的长度"+list2.size());

		
		//取分页信息
		PageInfo<TbItem> info =new PageInfo<TbItem>(list);
		
		//遍历结果集，打印
		System.out.println("查询的总记录数:"+info.getTotal());
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getId()+">>>>"+tbItem.getTitle());
		}
	}

}
