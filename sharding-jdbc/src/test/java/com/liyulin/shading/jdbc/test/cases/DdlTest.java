package com.liyulin.shading.jdbc.test.cases;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.shading.jdbc.uitl.SqlMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DdlTest {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private String QUERY_TABLE_SQL = "SHOW TABLES LIKE 't_api_log_2019_22'";

	@Test
	public void testDdl() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SqlMapper sqlMapper = new SqlMapper(sqlSession);
			String result = sqlMapper.selectOne(QUERY_TABLE_SQL, String.class);
			System.out.println(result);
		}
	}

}