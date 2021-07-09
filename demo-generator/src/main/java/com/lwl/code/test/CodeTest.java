package com.lwl.code.test;

import com.lwl.code.execute.CodeGeneratorExecute;
import com.lwl.code.generator.GeneratorParamExecute;
import com.lwl.code.generator.MyInjectionConfig;
import com.lwl.code.generator.MyTemplateConfig;
import com.lwl.code.template.GeneratorParamTemplate;
import com.lwl.code.template.InjectionConfigTemplate;
import com.lwl.code.template.TemplateConfigTemplate;

public class CodeTest {

    public static void main(String[] args) throws Exception {
        // 初始化数据库等相关信息 【必选】
        GeneratorParamTemplate generatorParamTemplate = new GeneratorParamExecute();
        // 实现自定义模板及位置 【可选】
        TemplateConfigTemplate templateConfigTemplate = new MyTemplateConfig();
        // 实现自定义标签和值 【可选】
        InjectionConfigTemplate injectionConfigTemplate = new MyInjectionConfig();

           new CodeGeneratorExecute(generatorParamTemplate)
                   .setTemplateConfigTemplate(templateConfigTemplate)
                   .setInjectionConfigTemplate(injectionConfigTemplate)
                   .execute("");
    }

}
