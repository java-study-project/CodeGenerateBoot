package com.scf.${module}.dto.save;

import com.scf.${module}.domain.${tableName_class};
import com.scf.component.core.domain.entity.BaseSaveData;

/**
 * 描述：${tableComment}保存时dto
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON           REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
public class ${tableName_class}SaveData implements BaseSaveData<${tableName_class}> {
    public static final String INFO_KEY = "${simpleName_TF}Data";

    private ${tableName_class} ${simpleName_TF}Data; // ${tableComment}

    public ${tableName_class} get${simpleName_class}Data() {
        return ${simpleName_TF}Data;
    }
    public void set${simpleName_class}Data(${tableName_class} ${simpleName_TF}Data) {
        this.${simpleName_TF}Data = ${simpleName_TF}Data;
    }

    @Override
    public String getMainKey() {
        return INFO_KEY;
    }

    @Override
    public ${tableName_class} getMainData() {
        return ${simpleName_TF}Data;
    }

    @Override
    public void setMainData(${tableName_class} mainData) {
        this.${simpleName_TF}Data = mainData;
    }
}
