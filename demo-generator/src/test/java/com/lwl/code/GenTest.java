package com.lwl.code;

import com.lwl.code.config.MpGeneratorParamConfig;
import com.lwl.code.exception.GeneratorException;
import com.lwl.code.execute.CodeGeneratorExecute;
import com.lwl.code.generator.GeneratorParamConfigExecute;
import com.lwl.code.generator.GeneratorParamExecute;
import com.lwl.code.generator.MyInjectionConfig;
import com.lwl.code.generator.MyTemplateConfig;
import com.lwl.code.template.GeneratorParamTemplate;
import com.lwl.code.template.InjectionConfigTemplate;
import com.lwl.code.template.TemplateConfigTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
@ContextConfiguration(classes = {MpGeneratorParamConfig.class})


public class GenTest {

    @Autowired
    private MpGeneratorParamConfig paramConfig;



    /**
     * @description： 根据读取yml文件代码生成
     * @author     ：lwl
     * @date       ：2021/7/20 10:53
     * @version:     1.0.0
     */
    @Test
    public void genYml() throws GeneratorException {

        // 初始化数据库等相关信息 【必选】
        GeneratorParamConfigExecute generatorParamTemplate = new GeneratorParamConfigExecute(paramConfig);
        execute(generatorParamTemplate);
    }


    /**
     *  读取配置文件properties方式
     * result:
     * author: lwl
     * date: 2021/7/20 10:54
     */
    @Test
    public void genProperties() throws GeneratorException {
        // 初始化数据库等相关信息 【必选】
        GeneratorParamTemplate generatorParamTemplate = new GeneratorParamExecute();
        execute(generatorParamTemplate);
    }



    public void execute(GeneratorParamTemplate generatorParamTemplate) throws GeneratorException {
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
