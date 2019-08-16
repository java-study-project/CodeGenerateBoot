/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.${module}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.scf.component.api.IBaseDbAbility;
import com.scf.component.core.biz.AbstractBaseBizImpl;
import com.scf.component.core.domain.dto.RequestParams;
import com.scf.component.core.domain.dto.ResultObject;
import com.scf.component.core.domain.example.BaseExample;

import com.scf.${module}.ability.I${tableName_class}DbAbility;
import com.scf.${module}.domain.${tableName_class};
import com.scf.${module}.dto.query.${tableName_class}QueryData;
import com.scf.${module}.dto.save.${tableName_class}SaveData;
import com.scf.${module}.service.I${tableName_class}Biz;

/**
 * 描述：${tableComment}业务接口实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON           REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
@Service(value="${tableName_TF}BizImpl")
public class ${tableName_class}BizImpl extends AbstractBaseBizImpl<${tableName_class}, ${tableName_class}SaveData> implements I${tableName_class}Biz {

    @Resource
    private I${tableName_class}DbAbility dbAbility;

	@Override
	public IBaseDbAbility<${tableName_class}> getDbAbility() {
		return dbAbility;
	}

	@Override
	public BaseExample getQueryParam(RequestParams requestParams) {
		${tableName_class}QueryData queryParam = new ${tableName_class}QueryData();
		queryParam.setDeleted(0); // 默认查询未删除的数据
		SimpleBeanUtils.copyProperties(queryParam, requestParams.getValues(), new String[]{
			<#list fields as item><#if item.QueryAble><#if item.QueryAbleType=="range" && item.dataType_isNum>"${item.columnName_TF}Before", "${item.columnName_TF}After",<#elseif item.QueryAbleType=="range" && item.dataType_java=="Date">"${item.columnName_TF}Before", "${item.columnName_TF}After",<#elseif item.QueryAbleType=="in" && (item.dataType_isNum || item.dataType_java=="String")>"${item.columnName_TF}List",<#else>"${item.columnName_TF}",</#if></#if></#list>
		});
		BaseExample<${tableName_class}> example = new BaseExample<>(queryParam);
		example.createCriteria()
<#list fields as item>
<#if item.QueryAble>
<#if item.QueryAbleType=="like" && item.dataType_java=="String">
				.andLike("${item.columnName_TF}", queryParam.get${item.columnName_class}(), true)
<#elseif item.QueryAbleType=="range" && item.dataType_isNum>
				.andBetween("${item.columnName_TF}", queryParam.get${item.columnName_class}Before(), queryParam.get${item.columnName_class}After(), true)
<#elseif item.QueryAbleType=="range" && item.dataType_java=="Date">
				.andDataBetween("${item.columnName_TF}", queryParam.get${item.columnName_class}Before(), queryParam.get${item.columnName_class}After())
<#elseif item.QueryAbleType=="in" && (item.dataType_isNum || item.dataType_java=="String")>
				.andIn("${item.columnName_TF}", queryParam.get${item.columnName_class}List(), true)
<#else>
				.andEqualTo("${item.columnName_TF}", queryParam.get${item.columnName_class}(), true)
</#if></#if></#list>
		;
		return example;
	}
<#if WindowSelectAble>

	@Override
	public BaseExample getQuerySelectParam(RequestParams requestParams){
		return getQueryParam(); // 搜索帮助查询条件默认和列表查询一致
	}
</#if>

	@Override
	public Object beforeSave(String saveType, ${tableName_class}SaveData saveData, ${tableName_class} infoData, RequestParams requestParams) {
		//infoData.setState("normal");
		return null;
	}
}
