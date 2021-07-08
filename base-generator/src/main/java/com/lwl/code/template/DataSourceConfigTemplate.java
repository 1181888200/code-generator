package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.lwl.code.generator.MpGeneratorParam;

/**
 * @description： 数据源配置
 * @author     ：lwl
 * @date       ：2021/7/8 9:12
 * @version:     1.0.0
 */
public abstract class DataSourceConfigTemplate {

    public abstract void addDb(AutoGenerator mpg, MpGeneratorParam param);

}
