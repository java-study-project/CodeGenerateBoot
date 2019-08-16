package com.code.generate.entity;

/**
 * 描述：明细表对象数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class DetailTableData extends TableData{
    private String mainTableName;
    private String mainSimpleName;
    private String refId;

    public String getMainTableName() {
        return mainTableName;
    }

    public void setMainTableName(String mainTableName) {
        this.mainTableName = mainTableName;
    }

    public String getMainSimpleName() {
        return mainSimpleName;
    }

    public void setMainSimpleName(String mainSimpleName) {
        this.mainSimpleName = mainSimpleName;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

}
