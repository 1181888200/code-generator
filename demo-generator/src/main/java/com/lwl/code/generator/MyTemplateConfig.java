package com.lwl.code.generator;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.TemplateConfigTemplate;
import org.apache.commons.lang3.StringUtils;

public class MyTemplateConfig extends TemplateConfigTemplate {
    @Override
    public void handleTemplatePath(TemplateConfig tc, MpGeneratorParam param) {
        if(StringUtils.isNotBlank(param.getTemplatePath())){
            tc.setController( "/templates/controller.java");
        }
    }
}
