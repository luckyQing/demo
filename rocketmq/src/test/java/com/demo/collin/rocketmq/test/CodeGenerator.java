package com.demo.collin.rocketmq.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.demo.collin.rocketmq.entity.BaseEntity;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Collections;

public class CodeGenerator {

    @Test
    public void test() {
        String url = "jdbc:mysql://127.0.0.1:3306/rocketmq_transaction_db?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        String projectPath = Paths.get(System.getProperty("user.dir")).toString();
        String outputDir = projectPath + "/src/main/java";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder
                        .author("collin")
                        .outputDir(outputDir)
                        .disableOpenDir()
                        .commentDate("yyyy-MM-dd")
                        .disableServiceInterface()
                )
                // 不生成controller、service interface
                .templateConfig(builder -> builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE))
                .packageConfig(builder -> builder
                        .parent("com.demo.collin.rocketmq")
                        .entity("entity")
                        .mapper("dao")
                        .serviceImpl("biz")
                        //.xml("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper"))
                )
                .strategyConfig(builder -> builder
                        .addInclude("t_order_bill", "t_product_info", "t_mq_transaction_log")
                        .addTablePrefix("t_")
                        .addFieldPrefix("f_")
                        .entityBuilder()
                        .formatFileName("%sEntity")
                        .addSuperEntityColumns("f_id", "f_sys_insert_time", "f_sys_upd_time", "f_sys_del_time", "f_sys_insert_user", "f_sys_upd_user", "f_sys_del_user", "f_sys_del_state")
                        .logicDeleteColumnName("f_sys_del_state")
                        .superClass(BaseEntity.class)
                        // 启用字段注解
                        .enableTableFieldAnnotation()
                        .enableRemoveIsPrefix()
                        .enableLombok()
                        .mapperBuilder()
                        .formatMapperFileName("%sDao")
                        .formatXmlFileName("%sDao")
                        .serviceBuilder()
                        .formatServiceImplFileName("%sBiz")
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}