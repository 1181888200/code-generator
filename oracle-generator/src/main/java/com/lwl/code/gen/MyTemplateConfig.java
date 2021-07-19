package com.lwl.code.gen;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.TemplateConfigTemplate;

public class MyTemplateConfig extends TemplateConfigTemplate {
    @Override
    public void handleTemplatePath(TemplateConfig tc, MpGeneratorParam param) {
        tc.setController( "/templates1/controller.java");
        tc.setMapper( "/templates1/mapper.java");
        tc.setEntity( "/templates1/entity.java");
        tc.setService( "/templates1/service.java");
        tc.setServiceImpl( "/templates1/serviceImpl.java");
        tc.setXml( "/templates1/mapper.xml");
    }
}
