package com.lwl.code.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.PackageConfigTemplate;
import org.apache.commons.lang3.StringUtils;

public class DefaultPackageConfig extends PackageConfigTemplate {

    @Override
    public void setPackage(AutoGenerator mpg, MpGeneratorParam param) {
        final PackageConfig pc = new PackageConfig();

        if(StringUtils.isNotBlank(param.getModuleName())&&param.isModulePackage()){
            pc.setModuleName(param.getModuleName());
        }
        pc.setParent(param.getPackageName());
        pc.setEntity(param.getEntityName());
        pc.setMapper(param.getMapperName());
        pc.setController(param.getControllerName());
        pc.setService(param.getServiceName());
        pc.setServiceImpl(param.getServiceImplName());
        mpg.setPackageInfo(pc);
    }
}
