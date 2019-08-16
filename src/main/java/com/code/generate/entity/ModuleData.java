package com.code.generate.entity;

import com.code.generate.template.entity.ConverterParam;
import com.code.generate.template.entity.FieldsTemplateParam;
import com.code.generate.template.entity.MainTemplateParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：模块数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class ModuleData {
    // 固定参数
    public static final String[] mainFields={"auth", "module"};
    private String auth; // 作者
    private String module; // 模块名
    private String generateDirectory; // 生成目录
    private List<String> openTables; // 开启的表
    // 扩展参数
    private Map<String, Object> extendParams = new HashMap<>();

    // 模板文件夹参数
    private String singleTableTemplateDir; // 单表模板文件夹
    private String mainTableTemplateDir; // 带明细的主表模板文件夹
    private String detailTableTemplateDir; // 明细表模板文件夹

    // 模板参数配置
    private MainTemplateParam mainTemplateParam; // root参数
    private FieldsTemplateParam fieldsTemplateParam; // fields参数
    private List<ConverterParam> extendParamConverter; // 参数转换器集合

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getGenerateDirectory() {
        return generateDirectory;
    }

    public void setGenerateDirectory(String generateDirectory) {
        this.generateDirectory = generateDirectory;
    }

    public List<String> getOpenTables() {
        return openTables;
    }

    public void setOpenTables(List<String> openTables) {
        this.openTables = openTables;
    }

    public String getSingleTableTemplateDir() {
        return singleTableTemplateDir;
    }

    public void setSingleTableTemplateDir(String singleTableTemplateDir) {
        this.singleTableTemplateDir = singleTableTemplateDir;
    }

    public String getMainTableTemplateDir() {
        return mainTableTemplateDir;
    }

    public void setMainTableTemplateDir(String mainTableTemplateDir) {
        this.mainTableTemplateDir = mainTableTemplateDir;
    }

    public String getDetailTableTemplateDir() {
        return detailTableTemplateDir;
    }

    public void setDetailTableTemplateDir(String detailTableTemplateDir) {
        this.detailTableTemplateDir = detailTableTemplateDir;
    }

    public Map<String, Object> getExtendParams() {
        return extendParams;
    }

    public void setExtendParams(Map<String, Object> extendParams) {
        this.extendParams = extendParams;
    }

    public MainTemplateParam getMainTemplateParam() {
        return mainTemplateParam;
    }

    public void setMainTemplateParam(MainTemplateParam mainTemplateParam) {
        this.mainTemplateParam = mainTemplateParam;
    }

    public FieldsTemplateParam getFieldsTemplateParam() {
        return fieldsTemplateParam;
    }

    public void setFieldsTemplateParam(FieldsTemplateParam fieldsTemplateParam) {
        this.fieldsTemplateParam = fieldsTemplateParam;
    }

    public List<ConverterParam> getExtendParamConverter() {
        return extendParamConverter;
    }

    public void setExtendParamConverter(List<ConverterParam> extendParamConverter) {
        this.extendParamConverter = extendParamConverter;
    }
}
