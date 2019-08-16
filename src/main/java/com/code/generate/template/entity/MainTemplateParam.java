package com.code.generate.template.entity;

import java.util.List;

/**
 * 描述：第一层模板参数
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class MainTemplateParam {
    private List<String> module; // 指定module配置参数，设置到模板root参数
    private List<String> table; // 指定table配置参数，设置到模板root参数
    private List<String> tableMetadata; // 指定table元数据，设置到模板root参数

    public List<String> getModule() {
        return module;
    }

    public void setModule(List<String> module) {
        this.module = module;
    }

    public List<String> getTable() {
        return table;
    }

    public void setTable(List<String> table) {
        this.table = table;
    }

    public List<String> getTableMetadata() {
        return tableMetadata;
    }

    public void setTableMetadata(List<String> tableMetadata) {
        this.tableMetadata = tableMetadata;
    }
}
