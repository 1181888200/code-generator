package com.lwl.code.template;

import com.lwl.code.exception.GeneratorException;
import com.lwl.code.param.MpGeneratorParam;

import java.util.Objects;
import java.util.Properties;

/**
 * @description： 初始化构造参数模板
 * @author     ：lwl
 * @date       ：2021/7/8 9:25
 * @version:     1.0.0
 */
public abstract class GeneratorParamTemplate {

    public abstract Properties initProperties(String propertiesPath) throws GeneratorException;

    public MpGeneratorParam initParam(String propertiesPath) throws GeneratorException {
        MpGeneratorParam param = new MpGeneratorParam();
        Properties properties = initProperties(propertiesPath);

        String url = properties.getProperty("database.url");
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");
        String driverName = properties.getProperty("database.driverName");

        String javaPath = properties.getProperty("path.javaPath");
        String xmlPath = properties.getProperty("path.xmlPath");

        String tablePrefix = properties.getProperty("table.tablePrefix");
        String tableNames = properties.getProperty("table.tableNames");

        String moduleName = properties.getProperty("model.moduleName");
        String packageName = properties.getProperty("model.packageName");
        String author = properties.getProperty("model.author");
        String entityName = properties.getProperty("model.entityName");
        String mapperName = properties.getProperty("model.mapperName");

        if(!Objects.isNull(properties.get("baseClass"))) {
            param.setSuperEntityClass((Class) properties.get("baseClass"));
        }
        String projectPath = properties.getProperty("user.dir");

        param.builderPath(projectPath + javaPath,projectPath + xmlPath).builderTable(tableNames.split(","),tablePrefix)
                .builderPackage(moduleName,packageName).builderDb(url,username,password,driverName)
                .setAuthor(author).setEntityName(entityName).setMapperName(mapperName);
        return param;
    }

}
