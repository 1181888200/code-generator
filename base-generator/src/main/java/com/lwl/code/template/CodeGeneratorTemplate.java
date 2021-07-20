package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.lwl.code.exception.GeneratorException;
import com.lwl.code.generator.*;
import com.lwl.code.param.MpGeneratorParam;
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

    /**
     * 自定义数据源配置
     */
    private DataSourceConfigTemplate dataSourceConfigTemplate;

    /**
     * 自定义文件输出位置配置
     */
    private FileOutPathTemplate fileOutPathTemplate;

    /**
     *  初始化参数配置
     */
    private GeneratorParamTemplate generatorParamTemplate;
    /**
     * 自定义策略配置
     */
    private StrategyTemplate strategyTemplate;

    /**
     * 自定义模板配置
     */
    private TemplateConfigTemplate templateConfigTemplate;

    /**
     *  自定义模板变量配置
     */
    private InjectionConfigTemplate injectionConfigTemplate;
    /**
     * 自定义全局配置
     */
    private GlobalConfigTemplate globalConfigTemplate;

    /**
     *  自定义包信息
     */
    private PackageConfigTemplate packageConfigTemplate;

    public CodeGeneratorTemplate(GeneratorParamTemplate generatorParamTemplate) throws GeneratorException {
        this(new MysqlDataSourceConfig(),new DefaultFileOutPath()
                ,new DefaultStrategy(),new DefaultTemplateConfig()
                ,new DefaultInjectionConfig(),new DefaultGlobalConfig(),
                new DefaultPackageConfig());
        this.generatorParamTemplate = generatorParamTemplate;
    }

    public CodeGeneratorTemplate(DataSourceConfigTemplate dataSourceConfigTemplate
            , FileOutPathTemplate fileOutPathTemplate, StrategyTemplate strategyTemplate,
                                 TemplateConfigTemplate templateConfigTemplate,
                                 InjectionConfigTemplate injectionConfigTemplate,
                                 GlobalConfigTemplate globalConfigTemplate,
                                 PackageConfigTemplate packageConfigTemplate) throws GeneratorException {
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
        this.injectionConfigTemplate = injectionConfigTemplate;
        this.globalConfigTemplate = globalConfigTemplate;
        this.packageConfigTemplate = packageConfigTemplate;
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
        globalConfigTemplate.setConfig(param, mpg);

        // 数据源配置
        dataSourceConfigTemplate.addDb(mpg,param);

        //策略配置
        strategyTemplate.initStrategy(mpg,param);

        //包配置
        packageConfigTemplate.setPackage(mpg,param);
        // 关闭默认 xml 生成，调整生成 至 根目录
        templateConfigTemplate.setTemplate(mpg,param);
        // 设置标签
        InjectionConfig cfg = injectionConfigTemplate.injectionConfig(mpg,param);
        // 设置文件存储位置
        fileOutPathTemplate.setFileOutPath(param,mpg, cfg);
        // 执行生成
        mpg.execute();

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
