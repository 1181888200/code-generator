package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.lwl.code.param.MpGeneratorParam;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public abstract class StrategyTemplate {

    public  void initStrategy(AutoGenerator mpg, MpGeneratorParam param){
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表// 设置生成的表
        strategy.setInclude(param.getTableNames());
        if(StringUtils.isNotBlank(param.getTablePrefix())){
            strategy.setTablePrefix(param.getTablePrefix());
        }
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        if(!Objects.isNull(param.getSuperEntityClass())){
            strategy.setSuperEntityClass(param.getSuperEntityClass());
        }
        addStrategy(strategy);
        mpg.setStrategy(strategy);
    }

    public abstract void addStrategy(StrategyConfig strategy);

}
