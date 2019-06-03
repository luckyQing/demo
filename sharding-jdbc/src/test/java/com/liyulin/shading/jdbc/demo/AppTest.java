package com.liyulin.shading.jdbc.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.shading.jdbc.demo.entity.ProductInfoEntity;
import com.liyulin.shading.jdbc.demo.enums.DelStateEnum;
import com.liyulin.shading.jdbc.demo.mapper.ProductInfoBaseMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {
	
	@Autowired
	private ProductInfoBaseMapper productInfoBaseMapper;
	
	@Test
	public void testInsert() {
		ProductInfoEntity entity = new ProductInfoEntity();
		entity.setId(1000L);
		entity.setName("iphone");
		entity.setSellPrice(1000L);
		entity.setStock(2000L);
		entity.setAddTime(new Date());
		entity.setDelState(DelStateEnum.NORMAL.getDelState());
		productInfoBaseMapper.insertSelective(entity);
	}
	
}