package com.lwl.code.generator;

import com.lwl.code.exception.GeneratorException;
import com.lwl.code.template.CodeGeneratorTemplate;
import com.lwl.code.template.GeneratorParamTemplate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CodeGeneratorExecute extends CodeGeneratorTemplate {

    private GeneratorParamTemplate generatorParamTemplate;

    public CodeGeneratorExecute(GeneratorParamTemplate generatorParamTemplate) throws GeneratorException {
        super(generatorParamTemplate);
    }

}
