package com.lwl.code.param;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * @description： 自动创建代码参数
 * @author     ：lwl
 * @date       ：2020/12/30 13:40
 * @version:     1.0.0
 */
@Data
@Accessors(chain = true)
public class MpGeneratorParam {

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
     *  自定义模板位置
     */
    private String templatePath;

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
