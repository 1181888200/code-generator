package com.lwl.code.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.GlobalConfigTemplate;
import org.apache.commons.lang3.StringUtils;

public class DefaultGlobalConfig extends GlobalConfigTemplate {

    @Override
    public void setConfig(MpGeneratorParam param, AutoGenerator mpg) {

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
}
