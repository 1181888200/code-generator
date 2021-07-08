package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.lwl.code.param.MpGeneratorParam;

/**
 * @description： 模板设置
 * @author     ：lwl
 * @date       ：2021/7/8 12:52
 * @version:     1.0.0
 */
public abstract class TemplateConfigTemplate {

    public abstract void handleTemplatePath(TemplateConfig tc,MpGeneratorParam param);

    public  void setTemplate(AutoGenerator mpg, MpGeneratorParam param){
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        handleTemplatePath(tc,param);
        tc.setXml(null);
        mpg.setTemplate(tc);
    };

}
