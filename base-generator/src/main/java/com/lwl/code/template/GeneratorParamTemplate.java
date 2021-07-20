package com.lwl.code.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwl.code.exception.GeneratorException;
import com.lwl.code.param.MpGeneratorParam;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
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

        String url = properties.getProperty("gen.database.url");
        String username = properties.getProperty("gen.database.username");
        String password = properties.getProperty("gen.database.password");
        String driverName = properties.getProperty("gen.database.driverName");

        String javaPath = properties.getProperty("gen.path.javaPath");
        String xmlPath = properties.getProperty("gen.path.xmlPath");

        String tablePrefix = properties.getProperty("gen.table.tablePrefix");
        String tableNames = properties.getProperty("gen.table.tableNames");

        String moduleName = properties.getProperty("gen.model.moduleName");
        String packageName = properties.getProperty("gen.model.packageName");
        String author = properties.getProperty("gen.model.author");
        String entityName = properties.getProperty("gen.model.entityName");
        String mapperName = properties.getProperty("gen.model.mapperName");

        String controllerName = properties.getProperty("gen.model.controllerName");
        String serviceName = properties.getProperty("gen.model.serviceName");
        String serviceImplName = properties.getProperty("gen.model.serviceImplName");
        String modulePackage = properties.getProperty("gen.model.modulePackage");
        String labelMap = properties.getProperty("gen.label.map");

        if(StringUtils.isNotBlank(modulePackage)&&modulePackage.equals("false")){
            param.setModulePackage(false);
        }

        if(StringUtils.isNotBlank(controllerName)){
            param.setControllerName(controllerName);
        }
        if(StringUtils.isNotBlank(serviceImplName)){
            param.setServiceImplName(serviceImplName);
        }
        if(StringUtils.isNotBlank(serviceName)){
            param.setServiceName(serviceName);
        }
        if(StringUtils.isNotBlank(mapperName)){
            param.setMapperName(mapperName);
        }
        if(StringUtils.isNotBlank(entityName)){
            param.setEntityName(entityName);
        }
        if(StringUtils.isNotBlank(labelMap)){
            Map map = (Map) JSONObject.parse(labelMap);
            param.setLabelMap(map);
        }
        if(!Objects.isNull(properties.get("baseClass"))) {
            param.setSuperEntityClass((Class) properties.get("baseClass"));
        }
        String projectPath = properties.getProperty("user.dir");

        param.builderPath(projectPath + javaPath,projectPath + xmlPath).builderTable(tableNames.split(","),tablePrefix)
                .builderPackage(moduleName,packageName).builderDb(url,username,password,driverName)
                .setAuthor(author);
        return param;
    }

}
