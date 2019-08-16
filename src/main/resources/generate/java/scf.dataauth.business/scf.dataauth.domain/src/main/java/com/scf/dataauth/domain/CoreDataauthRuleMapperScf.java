/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.dataauth.domain;

import com.scf.component.core.domain.constants.Constants;
import com.scf.component.core.domain.entity.BaseEntity;
import com.scf.component.core.domain.entity.HasDeleteFlag;
import com.scf.component.core.domain.entity.HasMainTableId;

import java.io.Serializable;

/**
 * 描述：规则数据权限Mapper明细表实体类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
public class CoreDataauthRuleMapperScf extends BaseEntity implements HasMainTableId, HasDeleteFlag, Serializable{

	private static final long serialVersionUID = 1L;
	private String ruleId; // 规则id
	private String ruleName; // 规则名称
	private String dimensionTypeId; // 维度类别id
	private String dimensionType; // 维度类别
	private String dimensionTypeDesc; // 维度描述
	private String tablePrefix; // 表名前缀
	private String goalFieldNo; // 目标字段名称
	private String sourceFieldNo; // 来源字段名称
	private Integer isEnable; // 是否启用
	private Integer deleted; // 是否删除

	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getDimensionTypeId() {
		return dimensionTypeId;
	}
	public void setDimensionTypeId(String dimensionTypeId) {
		this.dimensionTypeId = dimensionTypeId;
	}

	public String getDimensionType() {
		return dimensionType;
	}
	public void setDimensionType(String dimensionType) {
		this.dimensionType = dimensionType;
	}

	public String getDimensionTypeDesc() {
		return dimensionTypeDesc;
	}
	public void setDimensionTypeDesc(String dimensionTypeDesc) {
		this.dimensionTypeDesc = dimensionTypeDesc;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getGoalFieldNo() {
		return goalFieldNo;
	}
	public void setGoalFieldNo(String goalFieldNo) {
		this.goalFieldNo = goalFieldNo;
	}

	public String getSourceFieldNo() {
		return sourceFieldNo;
	}
	public void setSourceFieldNo(String sourceFieldNo) {
		this.sourceFieldNo = sourceFieldNo;
	}

	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}


	@Override
	public String getRefId() {
		return ruleId;
	}
	@Override
	public void setRefId(String refId) {
		this.ruleId = refId;
	}

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
		return "CoreDataauthRuleMapperScf ["
				+ "ruleId=" + ruleId + ","
				+ "ruleName=" + ruleName + ","
				+ "dimensionTypeId=" + dimensionTypeId + ","
				+ "dimensionType=" + dimensionType + ","
				+ "dimensionTypeDesc=" + dimensionTypeDesc + ","
				+ "tablePrefix=" + tablePrefix + ","
				+ "goalFieldNo=" + goalFieldNo + ","
				+ "sourceFieldNo=" + sourceFieldNo + ","
				+ "isEnable=" + isEnable + ","
				+ "deleted=" + deleted + ","
				+ super.toString() + "]";
	}
}
