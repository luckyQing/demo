package com.liyulin.binlog;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.network.SSLMode;
import com.google.common.collect.Sets;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MysqlBinLogListener {

    private static final ConcurrentMap<Long, String> TABLEID_MAP = new ConcurrentHashMap<>();
    private static final Set<String> LISTENER_TABLES = Sets.newHashSet("rc_deploy_ser_db.t_deploy_records",
            "rc_deploy_ser_db.t_rule_deploy_records",
            "strategy_config_db.t_decision_package_detail",
            "strategy_config_db.t_decision_basic_rule_detail",
            "strategy_config_bypass_db.t_deploy_records");

    /**
     * 连接mysqlBinLog
     */
    public void connectMysqlBinLog(long serverId, String host, int port, String username, String password) {
        BinaryLogClient client = new BinaryLogClient(host, port, username, password);
        client.setServerId(serverId);
        client.setSSLMode(SSLMode.DISABLED);

        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof TableMapEventData) {
                TableMapEventData tableMapEventData = (TableMapEventData) data;
                String fullTable = String.format("%s.%s", tableMapEventData.getDatabase(), tableMapEventData.getTable());
                if (TABLEID_MAP.size() != LISTENER_TABLES.size() && LISTENER_TABLES.contains(fullTable) && !TABLEID_MAP.containsKey(tableMapEventData.getTableId())) {
                    TABLEID_MAP.put(tableMapEventData.getTableId(), fullTable);
                }
            }
            if (data instanceof WriteRowsEventData) {
                WriteRowsEventData rowsEventData = (WriteRowsEventData) data;
                if (TABLEID_MAP.containsKey(rowsEventData.getTableId())) {
                    System.out.println("data changed--->" + data);
                }
            }
            if (data instanceof UpdateRowsEventData) {
                UpdateRowsEventData rowsEventData = (UpdateRowsEventData) data;
                if (TABLEID_MAP.containsKey(rowsEventData.getTableId())) {
                    System.out.println("data changed--->" + data);
                }
            }
            if (data instanceof DeleteRowsEventData) {
                DeleteRowsEventData rowsEventData = (DeleteRowsEventData) data;
                if (TABLEID_MAP.containsKey(rowsEventData.getTableId())) {
                    System.out.println("data changed--->" + data);
                }
            }
        });

        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}