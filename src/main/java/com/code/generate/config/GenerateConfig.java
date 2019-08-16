package com.code.generate.config;

import com.code.generate.config.entity.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：生成配置
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
@Configuration
public class GenerateConfig {

    @Value("${modulePath}")
    private String modulePath;

    @Bean
    public CommonConfigData commonData(){
        return CommonConfigData.readConfig(modulePath);
    }

    @Bean
    public ModuleConfigData moduleData(){
        return ModuleConfigData.readConfig(modulePath);
    }

    @Bean
    public TemplateConfigData templateConfigData(){
        return TemplateConfigData.readConfig(modulePath);
    }

    @Bean
    public TemplateFileConfigData singleTemplateFile(ModuleConfigData moduleConfigData){
        String singleTableTemplateDir = moduleConfigData.getData().getSingleTableTemplateDir();
        String[] dirs = singleTableTemplateDir.split(",");
        return TemplateFileConfigData.readConfig(modulePath, dirs);
    }


    @Bean
    public TemplateFileConfigData mainTemplateFile(ModuleConfigData moduleConfigData){
        String mainTableTemplateDir = moduleConfigData.getData().getMainTableTemplateDir();
        String[] dirs = mainTableTemplateDir.split(",");
        return TemplateFileConfigData.readConfig(modulePath, dirs);
    }


    @Bean
    public TemplateFileConfigData detailTemplateFile(ModuleConfigData moduleConfigData){
        String detailTableTemplateDir = moduleConfigData.getData().getDetailTableTemplateDir();
        String[] dirs = detailTableTemplateDir.split(",");
        return TemplateFileConfigData.readConfig(modulePath, dirs);
    }

    @Bean
    public TableConfigData tableConfigData(ModuleConfigData moduleData){
        return TableConfigData.readConfig(modulePath, moduleData);
    }
}
