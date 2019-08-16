package com.scf.${module}.dto.save;

import com.scf.component.core.domain.entity.BaseEntity;
import com.scf.component.core.domain.entity.BaseMainSaveData;
import com.scf.${module}.domain.${tableName_class};
<#list details as detail>
import com.scf.${module}.domain.${detail.tableName_class};
</#list>

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ${tableName_class}SaveData implements BaseMainSaveData<${tableName_class}> {
    private static Map<String, Class< ? extends BaseEntity>> detailTypeMap;
    public static final String INFO_KEY = "${simpleName_TF}Data";
<#list details as detail>
    public static final String ${detail.simpleName_i18n} = "${detail.simpleName_TF}List";
</#list>

    private ${tableName_class} ${simpleName_TF}Data; // ${tableComment}(主表)
<#list details as detail>
    private List<${detail.tableName_class}> ${detail.simpleName_TF}List; // ${detail.tableComment}(明细)
</#list>

    public ${tableName_class} get${simpleName_class}Data() {
        return ${simpleName_TF}Data;
    }
    public void set${simpleName_class}Data(${tableName_class} ${simpleName_TF}Data) {
        this.${simpleName_TF}Data = ${simpleName_TF}Data;
    }

<#list details as detail>
    public List<${detail.tableName_class}> get${detail.simpleName_class}List() {
        return ${detail.simpleName_TF}List;
    }
    public void set${detail.simpleName_class}List(List<${detail.tableName_class}> ${detail.simpleName_TF}List) {
        this.${detail.simpleName_TF}List = ${detail.simpleName_TF}List;
    }
</#list>

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

    @Override
    public Map<String, List<? extends BaseEntity>> getDetailDataMap() {
        Map<String, List<? extends BaseEntity>> map = new HashMap<>();
<#list details as detail>
        map.put(${detail.simpleName_i18n}, ${detail.simpleName_TF}List);
</#list>
        return map;
    }

    @Override
    public Map<String, Class< ? extends BaseEntity>> getDetailTypeMap() {
        if (detailTypeMap == null) {
            Map<String, Class<? extends BaseEntity>> map = new HashMap<>();
<#list details as detail>
            map.put(${detail.simpleName_i18n}, ${detail.tableName_class}.class);
</#list>
            detailTypeMap = map;
        }
        return detailTypeMap;
    }
}
