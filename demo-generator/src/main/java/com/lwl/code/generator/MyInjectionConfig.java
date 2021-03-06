package com.lwl.code.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.InjectionConfigTemplate;

import java.util.Map;


/**
 * @description： 实现自定义标签和值
 * @author     ：lwl
 * @date       ：2021/7/8 13:31
 * @version:     1.0.0
 */
public class MyInjectionConfig extends InjectionConfigTemplate {

    @Override
    public void addVmValue(Map<String, Object> map, AutoGenerator mpg, MpGeneratorParam param) {

        // 是否实现控制器 add方法
        map.put("isAddController",true);


    }
}
