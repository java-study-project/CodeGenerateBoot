package com.code.generate.template.entity;

import java.util.List;

/**
 * 描述：模板参数fields数组项参数
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class FieldsTemplateParam {
    private List<String> columnMetadata; // 指定列元数据，设置到模板参数fields数组项中

    public List<String> getColumnMetadata() {
        return columnMetadata;
    }

    public void setColumnMetadata(List<String> columnMetadata) {
        this.columnMetadata = columnMetadata;
    }
}
