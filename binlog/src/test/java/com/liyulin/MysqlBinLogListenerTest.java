package com.liyulin;

import com.liyulin.binlog.MysqlBinLogListener;
import org.junit.Test;

public class MysqlBinLogListenerTest {

    @Test
    public void test() {
        MysqlBinLogListener mysqlBinLogListener = new MysqlBinLogListener();
        mysqlBinLogListener.connectMysqlBinLog(5001, "192.168.15.67",3306,"root" ,"welcome123");
    }

}