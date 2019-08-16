package com.code.generate.config.entity;

import com.code.generate.entity.TemplateData;
import com.code.generate.utils.YumUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：模板配置数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class TemplateConfigData {
    private Map<String, TemplateData> data;

    // 获得模板配置（模板文件的参数配置,生成文件名、路径等）
    public static TemplateConfigData readConfig(String url){
        String filePath = url + "/template.yml";
        try {
            Map<String, TemplateData> map = new HashMap<>();
            Map<String, Map<String, String>> parserMap = YumUtils.readYumProps(filePath);
            for (String templateName : parserMap.keySet()) {
                Map<String, String> paramMap = parserMap.get(templateName);
                TemplateData template = new TemplateData();
                template.setTemplateName(templateName);
                BeanUtils.copyProperties(template, paramMap );
                map.put(templateName, template);
            }
            TemplateConfigData data = new TemplateConfigData();
            data.setData(map);
            return data;
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件["+filePath+"]报错", e);
        }
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }

}
