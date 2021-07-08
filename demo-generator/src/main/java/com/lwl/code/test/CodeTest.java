package com.lwl.code.test;

import com.lwl.code.generator.CodeGeneratorExecute;
import com.lwl.code.generator.GeneratorParamExecute;
import com.lwl.code.template.GeneratorParamTemplate;

public class CodeTest {

    public static void main(String[] args) throws Exception {
        GeneratorParamTemplate generatorParamTemplate = new GeneratorParamExecute();

           new CodeGeneratorExecute(generatorParamTemplate).execute("");
    }

}
