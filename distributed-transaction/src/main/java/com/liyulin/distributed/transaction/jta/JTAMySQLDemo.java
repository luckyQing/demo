package com.liyulin.distributed.transaction.jta;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.junit.Test;

import com.mysql.cj.jdbc.MysqlXADataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * JTA实例
 * 
 * @author liyulin
 * @version 1.0 2016年9月18日 上午9:23:00
 */
@Slf4j
public class JTAMySQLDemo {

	public static final class DbConstants {
		public final static String DB_USER_NAME = "root";
		public final static String DB_PASSWORD = "lyl123";

		public final static String DB_URL1 = "jdbc:mysql://localhost:3306/jta_demo_db1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
		public final static String DB_URL2 = "jdbc:mysql://localhost:3306/jta_demo_db2?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";

		public final static String DB1_INSERT_SQL = "INSERT INTO `jta_demo_db1`.`t_student`(`f_name`, `f_sex`, `f_age`, `f_grade`)VALUES ('张三', 1, 10, '六年级')";
		public final static String DB2_INSERT_SQL = "INSERT INTO `jta_demo_db2`.`t_teacher`(`f_name`, `f_sex`, `f_age`, `f_title`)VALUES ('李老师', 2, 36, '特级教师')";
	}

	@Test
	public void jtaInsert() {
		Connection mysqlCn1 = null;
		Connection mysqlCn2 = null;

		MysqlXADataSource mysqlDs1 = null;
		MysqlXADataSource mysqlDs2 = null;

		XAConnection xaMysqlCn1 = null;
		XAConnection xaMysqlCn2 = null;

		XAResource xaMysqlRes1 = null;
		XAResource xaMysqlRes2 = null;

		Xid mysqlXid1 = null;
		Xid mysqlXid2 = null;

		Statement mysqlpst1 = null;
		Statement mysqlpst2 = null;
		try {
			// 获得数据源
			mysqlDs1 = new MysqlXADataSource();
			mysqlDs1.setURL(DbConstants.DB_URL1);
			mysqlDs2 = new MysqlXADataSource();
			mysqlDs2.setURL(DbConstants.DB_URL2);
			// 获得连接
			xaMysqlCn1 = mysqlDs1.getXAConnection(DbConstants.DB_USER_NAME, DbConstants.DB_PASSWORD);
			xaMysqlCn2 = mysqlDs2.getXAConnection(DbConstants.DB_USER_NAME, DbConstants.DB_PASSWORD);

			mysqlCn1 = xaMysqlCn1.getConnection();
			mysqlCn2 = xaMysqlCn2.getConnection();

			mysqlpst1 = mysqlCn1.createStatement();
			mysqlpst2 = mysqlCn2.createStatement();

			xaMysqlRes1 = xaMysqlCn1.getXAResource();
			xaMysqlRes2 = xaMysqlCn2.getXAResource();

			mysqlXid1 = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x02 });
			mysqlXid2 = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x04 });

			xaMysqlRes1.start(mysqlXid1, XAResource.TMNOFLAGS);
			mysqlpst1.executeUpdate(DbConstants.DB1_INSERT_SQL);
			xaMysqlRes1.end(mysqlXid1, XAResource.TMSUCCESS);

			xaMysqlRes2.start(mysqlXid2, XAResource.TMNOFLAGS);
			mysqlpst2.executeUpdate(DbConstants.DB2_INSERT_SQL);
			xaMysqlRes2.end(mysqlXid2, XAResource.TMSUCCESS);

			// 准备
			int mysqlRea1 = xaMysqlRes1.prepare(mysqlXid1);
			int mysqlRea2 = xaMysqlRes2.prepare(mysqlXid2);

			// 判断准备就绪与否 提交或回滚
			if (mysqlRea1 == XAResource.XA_OK && mysqlRea2 == XAResource.XA_OK) {
				xaMysqlRes1.commit(mysqlXid1, false);
				System.out.println("Mysql1 事务提交成功！");
				xaMysqlRes2.commit(mysqlXid2, false);
				System.out.println("Mysql2 事务提交成功！");
			} else {
				xaMysqlRes1.rollback(mysqlXid1);
				xaMysqlRes2.rollback(mysqlXid2);
				System.out.println("事务回滚成功！");
			}
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			try {
				xaMysqlRes1.rollback(mysqlXid1);
				xaMysqlRes2.rollback(mysqlXid2);
			} catch (XAException e) {
				log.error(e.getMessage(), e);
			}
		} catch (XAException ex) {
			log.error(ex.getMessage(), ex);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				xaMysqlRes1.rollback(mysqlXid1);
				xaMysqlRes2.rollback(mysqlXid2);
			} catch (XAException ee) {
				log.error(ee.getMessage(), ee);
			}
		} finally {
			try {
				mysqlpst1.close();
				mysqlCn1.close();
				xaMysqlCn1.close();

				mysqlpst2.close();
				mysqlCn2.close();
				xaMysqlCn2.close();
			} catch (SQLException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
	}

}
