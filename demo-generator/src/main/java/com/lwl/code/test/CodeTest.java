package com.lwl.code.test;

import com.lwl.code.generator.CodeGeneratorExecute;
import com.lwl.code.generator.GeneratorParamExecute;
import com.lwl.code.generator.MyStrategyExecute;
import com.lwl.code.generator.MyTemplateConfig;
import com.lwl.code.template.GeneratorParamTemplate;
import com.lwl.code.template.StrategyTemplate;
import com.lwl.code.template.TemplateConfigTemplate;

public class CodeTest {

    public static void main(String[] args) throws Exception {
        GeneratorParamTemplate generatorParamTemplate = new GeneratorParamExecute();
        StrategyTemplate strategyTemplate = new MyStrategyExecute();
        TemplateConfigTemplate templateConfigTemplate = new MyTemplateConfig();
           new CodeGeneratorExecute(generatorParamTemplate)
                   .setStrategyTemplate(strategyTemplate)
                   .setTemplateConfigTemplate(templateConfigTemplate)
                   .execute("");
    }

}
