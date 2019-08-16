package com.code.generate.entity;

import java.util.List;

/**
 * 描述：下拉框对象数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class SelectData {
    private String column;
    private SelectType selectType=SelectType.DYNAMIC;
    private List<SelectItem> datas;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public List<SelectItem> getDatas() {
        return datas;
    }

    public void setDatas(List<SelectItem> datas) {
        this.datas = datas;
    }

    public SelectType getSelectType() {
        return selectType;
    }

    public void setSelectType(SelectType selectType) {
        this.selectType = selectType;
    }

    public static enum SelectType {
        STATIC("static"),
        DYNAMIC("dynamic");

        private String value;
        private SelectType(String value) {
            this.value=value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
