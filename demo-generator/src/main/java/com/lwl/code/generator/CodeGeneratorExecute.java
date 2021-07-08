package com.lwl.code.generator;

import com.lwl.code.template.CodeGeneratorTemplate;
import com.lwl.code.template.GeneratorParamTemplate;
import lombok.Data;

@Data
public class CodeGeneratorExecute extends CodeGeneratorTemplate {

    private GeneratorParamTemplate generatorParamTemplate;

    public CodeGeneratorExecute(GeneratorParamTemplate generatorParamTemplate) {
        super(generatorParamTemplate);
    }

}
