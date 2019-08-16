package com.code.generate.template.entity;

import java.util.List;

/**
 * 描述：扩展参数转换器参数
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class ConverterParam {
    private String converterName; // 转换器spring bean 名
    private List<String> mainTemplateParam; // 指定root参数进行转换
    private List<String> fieldsTemplateParam; // 指定field参数进行转换
    private List<String> params; // 执行参数

    public String getConverterName() {
        return converterName;
    }

    public void setConverterName(String converterName) {
        this.converterName = converterName;
    }

    public List<String> getMainTemplateParam() {
        return mainTemplateParam;
    }

    public void setMainTemplateParam(List<String> mainTemplateParam) {
        this.mainTemplateParam = mainTemplateParam;
    }

    public List<String> getFieldsTemplateParam() {
        return fieldsTemplateParam;
    }

    public void setFieldsTemplateParam(List<String> fieldsTemplateParam) {
        this.fieldsTemplateParam = fieldsTemplateParam;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
