package com.liyulin.shading.jdbc.demo.task;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.liyulin.shading.jdbc.demo.entity.ProductInfoEntity;
import com.liyulin.shading.jdbc.demo.uitl.DbTableUtil;
import com.liyulin.shading.jdbc.demo.uitl.WeekShardingUtil;

/**
 * 创建表定时任务
 * 
 * @author liyulin
 * @date 2019年6月4日 下午7:58:42
 */
@Service
public class CreateProductInfoTableTask {

	@Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean;

	@Scheduled(cron = "* * * 1/1 * *")
	@PostConstruct
	public void createTableSchedule() {
		String logicTableName = WeekShardingUtil.getLogicTableName(ProductInfoEntity.class);
		String actualTableName = WeekShardingUtil.getActualTableName(new Date(), logicTableName);
		DbTableUtil.createTableIfAbsent(logicTableName, actualTableName, sqlSessionFactoryBean);
	}

}