/*
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.${module}.dto.view;

import java.io.Serializable;
import java.util.List;
import com.scf.${module}.domain.${tableName_class};
<#list details as detail>
import com.scf.${module}.domain.${detail.tableName_class};
</#list>

/**
 * 描述：${tableComment}搜索帮助视图类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
public class ${tableName_class}SelectViewData extends ${tableName_class} implements Serializable{
	private static final long serialVersionUID = 1L;

<#list details as detail>
	private List<${detail.tableName_class}> ${detail.simpleName_TF}List; // ${detail.tableComment}
</#list>

<#list details as detail>
	public List<${detail.tableName_class}> get${detail.simpleName_class}List() {
		return ${detail.simpleName_TF}List;
	}
	public void set${detail.simpleName_class}List(List<${detail.tableName_class}> ${detail.simpleName_TF}List) {
		this.${detail.simpleName_TF}List = ${detail.simpleName_TF}List;
	}

</#list>
}
