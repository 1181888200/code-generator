package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.lwl.code.param.MpGeneratorParam;

/**
 * @description： 如果要调整文件生成的位置，重写该方法
 * @author     ：lwl
 * @date       ：2021/7/8 9:16
 * @version:     1.0.0
 */
public abstract class FileOutPathTemplate {

    public abstract void setFileOutPath(MpGeneratorParam param, AutoGenerator mpg,InjectionConfig cfg);
}
