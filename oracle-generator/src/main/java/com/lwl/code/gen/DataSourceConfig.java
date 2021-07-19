package com.lwl.code.gen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.lwl.code.param.MpGeneratorParam;
import com.lwl.code.template.DataSourceConfigTemplate;

public class DataSourceConfig extends DataSourceConfigTemplate {

    @Override
    public void addDb(AutoGenerator mpg, MpGeneratorParam param) {

        com.baomidou.mybatisplus.generator.config.DataSourceConfig dsc = new com.baomidou.mybatisplus.generator.config.DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new SqlServerTypeConvert() {
            public DbColumnType processTypeConvert(String fieldType) {
                String t = fieldType.toLowerCase();
                DbColumnType type = !t.contains("char") && !t.contains("text") ? (t.contains("bigint") ? DbColumnType.LONG : (t.contains("int") ? DbColumnType.INTEGER : (!t.contains("date") && !t.contains("time") && !t.contains("year") ? (t.contains("text") ? DbColumnType.STRING : (t.contains("bit") ? DbColumnType.INTEGER : (t.contains("decimal") ? DbColumnType.DOUBLE : (t.contains("clob") ? DbColumnType.CLOB : (t.contains("blog") ? DbColumnType.BLOB : (t.contains("binary") ? DbColumnType.BYTE_ARRAY : (t.contains("float") ? DbColumnType.FLOAT : (t.contains("double") ? DbColumnType.DOUBLE : (!t.contains("json") && !t.contains("enum") ? DbColumnType.STRING : DbColumnType.STRING))))))))) : DbColumnType.DATE))) : DbColumnType.STRING;
                return type;
            }
        });
        dsc.setDriverName(param.getDriverName());
        dsc.setUsername(param.getUsername());
        dsc.setPassword(param.getPassword());
        dsc.setUrl(param.getUrl());
        mpg.setDataSource(dsc);


    }
}
