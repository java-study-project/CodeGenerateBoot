/*
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.${module}.domain;

import com.scf.component.core.domain.constants.Constants;
import com.scf.component.core.domain.entity.BaseEntity;
import com.scf.component.core.domain.entity.HasDeleteFlag;

import java.io.Serializable;
<#list fields as item>
<#if item.baseField==false && item.dataType_isImport>
import ${item.dataType_import};
</#if>
</#list>

/**
 * 描述：${tableComment}实体类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
public class ${tableName_class} extends BaseEntity implements HasDeleteFlag, Serializable{

	private static final long serialVersionUID = 1L;
<#list fields as item>
	<#if item.baseField==false>
	private ${item.dataType_java} ${item.columnName_TF}; // ${item.columnComment}
	</#if>
</#list>

<#list fields as item>
	<#if item.baseField==false>
	public ${item.dataType_java} get${item.columnName_class}() {
		return ${item.columnName_TF};
	}
	public void set${item.columnName_class}(${item.dataType_java} ${item.columnName_TF}) {
		this.${item.columnName_TF} = ${item.columnName_TF};
	}

	</#if>
</#list>

	@Override
	public void initField() {
		setDeleted(Constants.Normal);
	}

	@Override
	public void delete() {
		setDeleted(Constants.DELETE);
	}

	@Override
	public String toString() {
		return "${tableName_class} ["
<#list fields as item>
	<#if item.baseField==false>
				+ "${item.columnName_TF}=" + ${item.columnName_TF} + ","
	</#if>
</#list>
		+ super.toString() + "]";
	}
}
