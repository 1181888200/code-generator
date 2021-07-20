package com.lwl.code.generator;

import com.lwl.code.config.MpGeneratorParamConfig;
import com.lwl.code.exception.GeneratorException;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.GeneratorParamTemplate;

import java.util.Properties;

/**
 * @description： 通过读取yml 配置来初始化参数
 * @author     ：lwl
 * @date       ：2021/7/20 10:36
 * @version:     1.0.0
 */
public class GeneratorParamConfigExecute extends GeneratorParamTemplate {

    private MpGeneratorParamConfig config;

    public GeneratorParamConfigExecute(MpGeneratorParamConfig config) {
        this.config = config;
    }

    @Override
    public Properties initProperties(String propertiesPath) throws GeneratorException {
        return null;
    }

    @Override
    public MpGeneratorParam initParam(String propertiesPath) throws GeneratorException {
        String basePath = System.getProperty("user.dir");
        config.setJavaPath(basePath+ config.getJavaPath()).setXmlPath(basePath + config.getXmlPath());
        return new MpGeneratorParam(config);
    }
}
