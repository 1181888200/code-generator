package com.lwl.code.param;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description： 自定义实现配置类
 * @author     ：lwl
 * @date       ：2021/7/8 9:48
 * @version:     1.0.0
 */
@Data
@Accessors(chain = true)
public class MyStrategyConfig extends StrategyConfig {

    /**
     *  是否实现控制器 add方法
     */
    private boolean isAddController = false;

    /**
     *  是否实现控制器 删除方法
     */
    private boolean isDelController = false;

    /**
     *  是否实现控制器 获取详情方法
     */
    private boolean isGetController = false;

    /**
     *  是否实现控制器 更新方法
     */
    private boolean isUpdController = false;

    /**
     *  是否实现控制器 分页方法
     */
    private boolean isPageController = false;

    private String add = "aaaa";



}
