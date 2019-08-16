package com.code.generate.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述：表对象数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class TableData {
    // 固定参数
    public static final String[] mainFields={"tableName", "simpleName", "mainTableName", "mainSimpleName", "refId"};
    private String tableName; // 表名
    private String simpleName; // 缩略名

    // 扩展参数
    private Map<String, Object> extendParams = new HashMap<>();

    // 代码解析自定义参数
    private TableType tableType; // 表类型（主、明细、单表）
    private List<DetailTableData> detailTables; // 关联明细表

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public List<DetailTableData> getDetailTables() {
        return detailTables;
    }

    public void setDetailTables(List<DetailTableData> detailTables) {
        this.detailTables = detailTables;
    }

    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }

    public Map<String, Object> getExtendParams() {
        return extendParams;
    }

    public void setExtendParams(Map<String, Object> extendParams) {
        this.extendParams = extendParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TableData tableData = (TableData) o;
        return Objects.equals(tableName, tableData.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName);
    }

    public static enum TableType {
        SINGLE, // 单表
        MAIN, // 带明细的主表
        DETAIL; // 明细表
    }
}
