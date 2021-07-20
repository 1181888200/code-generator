package com.lwl.code.param;

import com.lwl.code.config.MpGeneratorParamConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @description： 自动创建代码参数
 * @author     ：lwl
 * @date       ：2020/12/30 13:40
 * @version:     1.0.0
 */
@Data
@Accessors(chain = true)
public class MpGeneratorParam {

    public MpGeneratorParam() {}
    public MpGeneratorParam(MpGeneratorParamConfig config) {
        this.url = config.getUrl();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.driverName = config.getDriverName();
        this.superEntityClass = config.getSuperEntityClass();
        this.javaPath = config.getJavaPath();
        this.xmlPath = config.getXmlPath();
        this.tablePrefix = config.getTablePrefix();
        this.tableNames = config.getTableNames();
        this.moduleName = config.getModuleName();
        this.packageName = config.getPackageName();
        this.author = config.getAuthor();
        this.entityName =config.getEntityName();
        this.mapperName = config.getMapperName();
        this.controllerName = config.getControllerName();
        this.serviceImplName = config.getServiceImplName();
        this.serviceName = config.getServiceName();
        this.modulePackage = config.isModulePackage();
        this.labelMap = config.getLabelMap();
    }


    /**
     *  数据库相关的
     */
    private String url;
    private String username;
    private String password;
    private String driverName;

    /**
     * 实体类的父类
     */
    private Class superEntityClass;

    /**
     * java代码生成位置
     */
    private String javaPath;
    /**
     *  mapper.xml生成的位置
     */
    private String xmlPath;

    /**
     *  表头前缀（去除）
     */
    private String tablePrefix;

    /**
     *  表名数组
     */
    private String[] tableNames;

    /**
     *  模块名称
     */
    private String moduleName;

    /**
     *  包名
     */
    private String packageName;

    /**
     *  创建人
     */
    private String author;

    /**
     *  实体类包名最后一个xxx.xxx.domain
     */
    private String entityName = "domain";
    /**
     *  mapper文件包名的最后一个值 xxx.xxx.mapper
     */
    private String mapperName = "mapper";

    /**
     *  包名控制层
     */
    private String controllerName = "controller";
    /**
     *  包名服务层
     */
    private String serviceName = "service";

    private String serviceImplName = "service.impl";

    private boolean modulePackage = true;

    /**
     *  自定义标签
     */
    private Map<String,Object> labelMap;

    public MpGeneratorParam builderDb(String url,String username,String password,String driverName){
        return setUrl(url).setPassword(password).setUsername(username).setDriverName(driverName);
    }

    public MpGeneratorParam builderPath(String javaPath,String xmlPath){
        return setJavaPath(javaPath).setXmlPath(StringUtils.isNotBlank(xmlPath)?xmlPath:javaPath);
    }

    public MpGeneratorParam builderPackage(String moduleName,String packageName){
        return setModuleName(moduleName).setPackageName(packageName);
    }

    public MpGeneratorParam builderTable(String[] tableNames,String tablePrefix){
        return setTableNames(tableNames).setTablePrefix(tablePrefix);
    }


}
