/*
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.${module}.dto.query;

import com.scf.${module}.domain.${tableName_class};
<#list fields as item>
<#if item.baseField==false && item.dataType_isImport && item.dataType_java!="Date">
import ${item.dataType_import};
</#if>
</#list>
<#list fields as item>
<#if item.QueryAble && item.QueryAbleType=="in">
<#if item.dataType_isNum || item.dataType_java=="String">
import java.util.List;
<#break>
</#if>
</#if>
</#list>

/**
 * 描述：${tableComment}查询条件类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
public class ${tableName_class}QueryData extends ${tableName_class} {

<#list fields as item>
<#if item.QueryAble>
<#if item.QueryAbleType=="range">
	<#if item.dataType_isNum>
	private ${item.dataType_java} ${item.columnName_TF}Before; // ${item.columnComment}开始
	private ${item.dataType_java} ${item.columnName_TF}After; // ${item.columnComment}结束
	<#elseif item.dataType_java=="Date">
	private String ${item.columnName_TF}Before; // ${item.columnComment}开始
	private String ${item.columnName_TF}After; // ${item.columnComment}结束
	</#if>
<#elseif item.QueryAbleType=="in">
	<#if item.dataType_isNum || item.dataType_java=="String">
	private List<${item.dataType_java}> ${item.columnName_TF}List; // ${item.columnComment}集合
	</#if>
</#if>
</#if>
</#list>

<#list fields as item>
<#if item.QueryAble>
<#if item.QueryAbleType=="range">
	<#if item.dataType_isNum>
	public ${item.dataType_java} get${item.columnName_class}Before() {
		return ${item.columnName_TF}Before;
	}
	public void set${item.columnName_class}Before(${item.dataType_java} ${item.columnName_TF}Before) {
		this.${item.columnName_TF}Before = ${item.columnName_TF}Before;
	}

	public ${item.dataType_java} get${item.columnName_class}After() {
		return ${item.columnName_TF}After;
	}
	public void set${item.columnName_class}After(${item.dataType_java} ${item.columnName_TF}After) {
		this.${item.columnName_TF}After = ${item.columnName_TF}After;
	}

	<#elseif item.dataType_java=="Date">
	public String get${item.columnName_class}Before() {
		return ${item.columnName_TF}Before;
	}
	public void set${item.columnName_class}Before(String ${item.columnName_TF}Before) {
		this.${item.columnName_TF}Before = ${item.columnName_TF}Before;
	}

	public String get${item.columnName_class}After() {
		return ${item.columnName_TF}After;
	}
	public void set${item.columnName_class}After(String ${item.columnName_TF}After) {
		this.${item.columnName_TF}After = ${item.columnName_TF}After;
	}

	</#if>
<#elseif item.QueryAbleType=="in">
	public List<${item.dataType_java}> get${item.columnName_class}List() {
		return ${item.columnName_TF}List;
	}
	public void set${item.columnName_class}List(List<${item.dataType_java}> ${item.columnName_TF}List) {
		this.${item.columnName_TF}List = ${item.columnName_TF}List;
	}

</#if>
</#if>
</#list>
}
