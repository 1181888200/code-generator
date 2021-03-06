package com.lwl.code.execute;

import com.lwl.code.exception.GeneratorException;
import com.lwl.code.template.CodeGeneratorTemplate;
import com.lwl.code.template.GeneratorParamTemplate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CodeGeneratorExecute extends CodeGeneratorTemplate {

    public CodeGeneratorExecute(GeneratorParamTemplate generatorParamTemplate) throws GeneratorException {
        super(generatorParamTemplate);
    }
}
