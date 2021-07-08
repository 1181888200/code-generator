package com.lwl.code.generator;

import com.lwl.code.param.MyStrategyConfig;
import com.lwl.code.template.StrategyTemplate;

public class MyStrategyExecute extends StrategyTemplate {

    @Override
    public void addStrategy(MyStrategyConfig strategy) {
        strategy.setAddController(true);
    }
}
