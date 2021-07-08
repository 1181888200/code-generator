package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.lwl.code.exception.GeneratorException;
import com.lwl.code.generator.DefaultFileOutPath;
import com.lwl.code.generator.DefaultStrategy;
import com.lwl.code.generator.DefaultTemplateConfig;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.generator.MysqlDataSourceConfig;
import com.lwl.code.param.MyStrategyConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import java.util.*;


/**
 * @description： 代码生成器
 * @author     ：lwl
 * @date       ：2021/7/7 15:35
 * @version:     1.0.0
 */
@Data
@Accessors(chain = true)
public abstract class CodeGeneratorTemplate {


    private DataSourceConfigTemplate dataSourceConfigTemplate;

    private FileOutPathTemplate fileOutPathTemplate;

    private GeneratorParamTemplate generatorParamTemplate;

    private StrategyTemplate strategyTemplate;

    private TemplateConfigTemplate templateConfigTemplate;


    public CodeGeneratorTemplate(GeneratorParamTemplate generatorParamTemplate) throws GeneratorException {
        this(new MysqlDataSourceConfig(),new DefaultFileOutPath(),new DefaultStrategy(),new DefaultTemplateConfig());
        this.generatorParamTemplate = generatorParamTemplate;
    }

    public CodeGeneratorTemplate(DataSourceConfigTemplate dataSourceConfigTemplate
            , FileOutPathTemplate fileOutPathTemplate, StrategyTemplate strategyTemplate,
                                 TemplateConfigTemplate templateConfigTemplate) throws GeneratorException {
        if(Objects.isNull(dataSourceConfigTemplate)) {
            throw new GeneratorException("自动创建代码-->数据源没有配置");
        }
        if(Objects.isNull(fileOutPathTemplate)) {
            fileOutPathTemplate = new DefaultFileOutPath();
        }
        this.dataSourceConfigTemplate = dataSourceConfigTemplate;
        this.fileOutPathTemplate =fileOutPathTemplate;
        this.strategyTemplate = strategyTemplate;
        this.templateConfigTemplate = templateConfigTemplate;
    }


    /**
     *  执行代码生成
     * result:
     * author: lwl
     * date: 2021/7/7 15:35
     */
    public  void execute(String path) throws GeneratorException {
        MpGeneratorParam param = generatorParamTemplate.initParam(path);
        execute(param);
    }

    public  void execute(MpGeneratorParam param) throws GeneratorException {
        checkParam(param);
        doExecute(param);
    }

    private  void doExecute(MpGeneratorParam param) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        setConfig(param, mpg);

        // 数据源配置
        dataSourceConfigTemplate.addDb(mpg,param);

        //策略配置
        strategyTemplate.initStrategy(mpg,param);

        //包配置
        addPackage(mpg,param);


        MyStrategyConfig strategy = (MyStrategyConfig) mpg.getStrategy();

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                map.put("isAddController", strategy.isAddController());
                this.setMap(map);
            }
        };
        fileOutPathTemplate.handleMapper(param,mpg, cfg);
        // 关闭默认 xml 生成，调整生成 至 根目录
        templateConfigTemplate.setTemplate(mpg,param);
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


    public static void addPackage(AutoGenerator mpg,MpGeneratorParam param){
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(param.getModuleName());
        pc.setParent(param.getPackageName());
        pc.setEntity(param.getEntityName());
        pc.setMapper(param.getMapperName());
        mpg.setPackageInfo(pc);
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

}
