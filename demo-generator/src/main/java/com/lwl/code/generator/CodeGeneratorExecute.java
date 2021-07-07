package com.lwl.code.generator;

import com.lwl.code.exception.GeneratorException;
import com.lwl.code.template.CodeGeneratorTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Properties;

public class CodeGeneratorExecute extends CodeGeneratorTemplate {


    @Override
    public Properties initProperties(String propertiesPath) throws GeneratorException {
        Properties properties = new Properties();
        try{
            propertiesPath = StringUtils.isBlank(propertiesPath)?"generator//generator.properties":propertiesPath;
            Resource resource = new ClassPathResource(propertiesPath);
            InputStream inputStream = resource.getInputStream();
            properties.load(inputStream);
            properties.setProperty("user.dir",System.getProperty("user.dir"));
            String baseClass = properties.getProperty("model.baseClass");
            if(StringUtils.isNotBlank(baseClass)){
                properties.put("baseClass", Class.forName(baseClass));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneratorException("自动创建代码-->未读取到本地文件");
        }
        return properties;
    }
}
