package com.lwl.code.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Accessors(chain = true)
@Configuration
public class MpGeneratorParamConfig {

    /**
     *  数据库相关的
     */
    @Value("${gen.database.url}")
    private String url;
    @Value("${gen.database.username}")
    private String username;
    @Value("${gen.database.password}")
    private String password;
    @Value("${gen.database.driverName}")
    private String driverName;


    /**
     * java代码生成位置
     */
    @Value("${gen.path.javaPath}")
    private String javaPath;
    /**
     *  mapper.xml生成的位置
     */
    @Value("${gen.path.xmlPath}")
    private String xmlPath;

    /**
     *  表头前缀（去除）
     */
    @Value("${gen.table.tablePrefix}")
    private String tablePrefix;

    /**
     *  表名数组
     */
    @Value("#{'${gen.table.tableNames}'.split(',')}")
    private String[] tableNames;

    /**
     *  模块名称
     */
    @Value("${gen.model.moduleName}")
    private String moduleName;

    /**
     *  包名
     */
    @Value("${gen.model.packageName}")
    private String packageName;

    /**
     * 实体类的父类
     */
    @Value("${gen.model.superEntityClass}")
    private Class superEntityClass;

    /**
     *  创建人
     */
    @Value("${gen.model.author}")
    private String author;

    /**
     *  实体类包名最后一个xxx.xxx.domain
     */
    @Value("${gen.model.entityName:domain}")
    private String entityName = "domain";
    /**
     *  mapper文件包名的最后一个值 xxx.xxx.mapper
     */
    @Value("${gen.model.mapperName:mapper}")
    private String mapperName = "mapper";

    /**
     *  包名控制层
     */
    @Value("${gen.model.controllerName:controller}")
    private String controllerName = "controller";
    /**
     *  包名服务层
     */
    @Value("${gen.model.serviceName:service}")
    private String serviceName = "service";

    @Value("${gen.model.serviceImplName:service.impl}")
    private String serviceImplName = "service.impl";

    @Value("${gen.model.modulePackage:true}")
    private boolean modulePackage = true;


}
