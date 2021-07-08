package com.lwl.code.generator;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.TemplateConfigTemplate;

public class MyTemplateConfig extends TemplateConfigTemplate {
    @Override
    public void handleTemplatePath(TemplateConfig tc, MpGeneratorParam param) {
        tc.setController( "/templates/controller.java");
    }
}
