package com.lwl.code.template;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.lwl.code.param.MpGeneratorParam;

public abstract class PackageConfigTemplate {

    /**
     *  设置包信息
     * @param mpg
     * @param param
     * result:
     * author: lwl
     * date: 2021/7/20 9:38
     */
    public abstract void setPackage(AutoGenerator mpg, MpGeneratorParam param);
}
