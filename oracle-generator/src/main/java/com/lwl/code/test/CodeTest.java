package com.lwl.code.test;

import com.lwl.code.exception.GeneratorException;
import com.lwl.code.execute.CodeGeneratorExecute;
import com.lwl.code.gen.*;
import com.lwl.code.template.*;

public class CodeTest {

    public static void main(String[] args) throws Exception {
        mybatisPlus();
    }


    public static void mybatisPlus() throws GeneratorException {
        // 初始化数据库等相关信息 【必选】
        GeneratorParamTemplate generatorParamTemplate = new MybatisPlusGeneratorParamExecute();
        // 数据源
        DataSourceConfigTemplate dataSourceConfigTemplate = new DataSourceConfig();
        // 文件存储位置
        FileOutPathTemplate fileOutPath = new MybatisPlusDemoFileOutPath();
        new CodeGeneratorExecute(generatorParamTemplate)
                .setDataSourceConfigTemplate(dataSourceConfigTemplate)
                .setFileOutPathTemplate(fileOutPath)
                .execute("");
    }

    public static void mybatis() throws GeneratorException {
        // 初始化数据库等相关信息 【必选】
        GeneratorParamTemplate generatorParamTemplate = new GeneratorParamExecute();
        // 实现自定义模板及位置 【可选】
        TemplateConfigTemplate templateConfigTemplate = new MyTemplateConfig();
        // 实现自定义标签和值 【可选】
        InjectionConfigTemplate injectionConfigTemplate = new MyInjectionConfig();
        // 数据源
        DataSourceConfigTemplate dataSourceConfigTemplate = new DataSourceConfig();
        // 文件存储位置
        FileOutPathTemplate fileOutPath = new DemoFileOutPath();


        new CodeGeneratorExecute(generatorParamTemplate)
                .setTemplateConfigTemplate(templateConfigTemplate)
                .setDataSourceConfigTemplate(dataSourceConfigTemplate)
                .setInjectionConfigTemplate(injectionConfigTemplate)
                .setFileOutPathTemplate(fileOutPath)
                .execute("");
    }

}
