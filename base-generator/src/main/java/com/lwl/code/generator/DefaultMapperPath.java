package com.lwl.code.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.lwl.code.template.MapperPathTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description： 配置xml生成的位置
 * @author     ：lwl
 * @date       ：2021/7/8 9:19
 * @version:     1.0.0
 */
public class DefaultMapperPath extends MapperPathTemplate {
    @Override
    public void handleMapper(MpGeneratorParam param, AutoGenerator mpg) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return param.getXmlPath() + ("/" + param.getPackageName().substring(param.getPackageName().lastIndexOf(".")+1)
                        + "/" + mpg.getConfig().getPackageInfo().get(ConstVal.MODULE_NAME)).replace(".", "/")
                        + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }
}
