package com.lwl.code.template;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.lwl.code.exception.GeneratorException;
import com.lwl.code.generator.MpGeneratorParam;
import org.apache.commons.lang3.StringUtils;
import java.util.*;


/**
 * @description： 代码生成器
 * @author     ：lwl
 * @date       ：2021/7/7 15:35
 * @version:     1.0.0
 */
public abstract class CodeGeneratorTemplate {


    /**
     *  执行代码生成
     * result:
     * author: lwl
     * date: 2021/7/7 15:35
     */
    public  void execute(String path) throws GeneratorException {
        MpGeneratorParam param = getParam(path);
        checkParam(param);
        doExecute(param);
    }

    private  void doExecute(MpGeneratorParam param) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        setConfig(param, mpg);

        // 数据源配置
        addDb(mpg,param);

        //策略配置
        addStrategy(mpg,param);

        //包配置
        addPackage(mpg,param);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        handleMapper(param, mpg);
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();

    }

    public  void setConfig(MpGeneratorParam param, AutoGenerator mpg) {
        final GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(param.getJavaPath());
        gc.setFileOverride(true);
        gc.setActiveRecord(false);

        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        gc.setAuthor(StringUtils.isNotBlank(param.getAuthor())?param.getAuthor():"admin");
        mpg.setGlobalConfig(gc);
    }

    public void addDb(AutoGenerator mpg,MpGeneratorParam param){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new SqlServerTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            public DbColumnType processTypeConvert(String fieldType) {
                String t = fieldType.toLowerCase();
                DbColumnType type = !t.contains("char") && !t.contains("text") ? (t.contains("bigint") ? DbColumnType.LONG
                        : (t.contains("int") ? DbColumnType.INTEGER
                        : (!t.contains("date") && !t.contains("time") && !t.contains("year") ?
                        (t.contains("text") ? DbColumnType.STRING
                                : (t.contains("bit") ? DbColumnType.INTEGER
                                : (t.contains("decimal") ? DbColumnType.DOUBLE
                                : (t.contains("clob") ? DbColumnType.CLOB
                                : (t.contains("blog") ? DbColumnType.BLOB
                                : (t.contains("binary") ? DbColumnType.BYTE_ARRAY
                                : (t.contains("float") ? DbColumnType.FLOAT
                                : (t.contains("double") ? DbColumnType.DOUBLE
                                : (!t.contains("json") && !t.contains("enum") ? DbColumnType.STRING
                                : DbColumnType.STRING)))))))))
                        : DbColumnType.DATE))) : DbColumnType.STRING;
                return type;
            }
        });
        dsc.setDriverName(param.getDriverName());
        dsc.setUsername(param.getUsername());
        dsc.setPassword(param.getPassword());
        dsc.setUrl(param.getUrl());
        mpg.setDataSource(dsc);
    }

    public static void addStrategy(AutoGenerator mpg,MpGeneratorParam param){
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表// 设置生成的表
        strategy.setInclude(param.getTableNames());
        if(StringUtils.isNotBlank(param.getTablePrefix())){
            strategy.setTablePrefix(param.getTablePrefix());
        }
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        if(!Objects.isNull(param.getSuperEntityClass())){
            strategy.setSuperEntityClass(param.getSuperEntityClass());
        }
        mpg.setStrategy(strategy);
    }

    public static void addPackage(AutoGenerator mpg,MpGeneratorParam param){
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(param.getModuleName());
        pc.setParent(param.getPackageName());
        pc.setEntity(param.getEntityName());
        pc.setMapper(param.getMapperName());
        mpg.setPackageInfo(pc);
    }

    private static void handleMapper(MpGeneratorParam param, AutoGenerator mpg) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return param.getXmlPath() + ("/" + param.getPackageName().substring(param.getPackageName().lastIndexOf(".")+1)
                        + "/" + mpg.getConfig().getPackageInfo().get(ConstVal.MODULE_NAME)).replace(".", "/")
                        + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    /**
     *  检查参数
     * @param param
     * result:
     * author: lwl
     * date: 2020/12/30 13:50
     */
    private static void checkParam(MpGeneratorParam param) throws GeneratorException {
        if(param==null){
            throw new GeneratorException("自动创建代码-->参数不能为空");
        }

        if(StringUtils.isBlank(param.getUrl())
                ||StringUtils.isBlank(param.getUsername())
                ||StringUtils.isBlank(param.getDriverName())){
            throw new GeneratorException("自动创建代码-->数据库配置有误");
        }

        if(StringUtils.isBlank(param.getJavaPath())){
            throw new GeneratorException("自动创建代码-->代码存储位置未配置");
        }
        if(StringUtils.isBlank(param.getModuleName())){
            throw new GeneratorException("自动创建代码-->模块名称未配置");
        }
        if(StringUtils.isBlank(param.getPackageName())){
            throw new GeneratorException("自动创建代码-->包名未配置");
        }
        if(StringUtils.isBlank(param.getXmlPath())){
            param.setXmlPath(param.getJavaPath());
        }
        if(param.getTableNames()==null||param.getTableNames().length==0){
            throw new GeneratorException("自动创建代码-->请输入数据库表名");
        }
    }

    public abstract Properties initProperties(String propertiesPath) throws GeneratorException;

    private   MpGeneratorParam getParam(String propertiesPath) throws GeneratorException {
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
