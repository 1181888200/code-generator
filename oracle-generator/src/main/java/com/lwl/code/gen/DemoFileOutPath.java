package com.lwl.code.gen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.FileOutPathTemplate;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 修改文件存储位置 样例
 * @author     ：lwl
 * @date       ：2021/7/8 16:14
 * @version:     1.0.0
 */
public class DemoFileOutPath extends FileOutPathTemplate {


    @Override
    public void setFileOutPath(MpGeneratorParam param, AutoGenerator mpg, InjectionConfig cfg) {

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates1/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                String moduleName = "";
                if(StringUtils.isNotBlank(param.getModuleName())){
                    moduleName ="/" + param.getModuleName();
                }

                return param.getXmlPath() + (
                        moduleName).replace(".", "/")
                        + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        focList.add(new FileOutConfig("/templates1/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                String moduleName = "";
                if(StringUtils.isNotBlank(param.getModuleName())){
                    moduleName ="/" + param.getModuleName();
                }

                String packName = "/" + param.getPackageName()+ moduleName +"/domain";
                String fullPath =  param.getJavaPath() + (
                        packName ).replace(".", "/")
                        + "/" + tableInfo.getEntityName() + ".java";

                return fullPath;
            }
        });

        focList.add(new FileOutConfig("/templates1/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                String moduleName = "";
                if(StringUtils.isNotBlank(param.getModuleName())){
                    moduleName ="/" + param.getModuleName();
                }
                String packName = "/" + param.getPackageName() + moduleName+"/controller";
                String fullPath =  param.getJavaPath() + (
                        packName ).replace(".", "/")
                        + "/" + tableInfo.getEntityName() + "Controller.java";

                return fullPath;
            }
        });

        focList.add(new FileOutConfig("/templates1/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                String moduleName = "";
                if(StringUtils.isNotBlank(param.getModuleName())){
                    moduleName ="/" + param.getModuleName();
                }
                String packName = "/" + param.getPackageName() + moduleName+"/service";
                return param.getJavaPath() + (
                        packName ).replace(".", "/")
                        + "/I" + tableInfo.getEntityName() + "Service.java";
            }
        });

        focList.add(new FileOutConfig("/templates1/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                String moduleName = "";
                if(StringUtils.isNotBlank(param.getModuleName())){
                    moduleName ="/" + param.getModuleName();
                }
                String packName = "/" + param.getPackageName()+ moduleName +"/service";
                return param.getJavaPath() + (
                        packName +"/impl").replace(".", "/")
                        + "/" + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

    }
}
