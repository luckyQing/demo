package com.liyulin.skills.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBTest {

	@Test
	public void testQuery() {
		String querySql = "select * from t_user";
		ConnectionManager connecte = new ConnectionManager();
		try (Connection con = connecte.openConnection();
				PreparedStatement stmt = con.prepareStatement(querySql);
				ResultSet rs = stmt.executeQuery(querySql);) {
			while (rs.next()) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					log.info("{}:{}", resultSetMetaData.getColumnName(i), rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			log.error("sql exception", e);
		}
	}

	@Test
	public void sqlUpdate() {
		String updateSql = "update t_user set f_mobile=223";
		ConnectionManager connecte = new ConnectionManager();
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = connecte.openConnection();
			// 设置事务手动提交
			con.setAutoCommit(false);
			stmt = con.prepareStatement(updateSql);
			stmt.execute(updateSql);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				log.error("sql exception", e1);
			}
			log.error("sql exception", e);
		} finally {
			connecte.closeDatabase(con, stmt, null);
		}
	}
}
