package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;

import java.util.HashMap;
import java.util.Map;

public abstract class InjectionConfigTemplate {

    public abstract void addVmValue(Map<String, Object> map);

    public  InjectionConfig injectionConfig(AutoGenerator mpg){
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
                addVmValue(this.getMap());
            }
        };

        return cfg;
    };

}
