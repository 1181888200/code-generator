package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.lwl.code.param.MpGeneratorParam;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description： 如果要自定义模板中添加属性，重写addVmValue方法
 * @author     ：lwl
 * @date       ：2021/7/8 14:50
 * @version:     1.0.0
 */
public abstract class InjectionConfigTemplate {

    public abstract void addVmValue(Map<String, Object> map, AutoGenerator mpg,MpGeneratorParam param);

    public  InjectionConfig injectionConfig(AutoGenerator mpg, MpGeneratorParam param){
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
                if(!CollectionUtils.isEmpty(param.getLabelMap())){
                    param.getLabelMap().entrySet().stream().forEach(r->this.getMap().put(r.getKey(),r.getValue()));
                }
                addVmValue(this.getMap(),mpg,param);
            }
        };

        return cfg;
    };

}
