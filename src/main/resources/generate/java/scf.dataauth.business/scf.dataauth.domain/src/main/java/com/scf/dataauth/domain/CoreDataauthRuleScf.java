/*
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.dataauth.domain;

import com.scf.component.core.domain.constants.Constants;
import com.scf.component.core.domain.entity.BaseEntity;
import com.scf.component.core.domain.entity.HasDeleteFlag;

import java.io.Serializable;

/**
 * 描述：规则定义表实体类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
public class CoreDataauthRuleScf extends BaseEntity implements HasDeleteFlag, Serializable{

	private static final long serialVersionUID = 1L;
	private String ruleName; // 规则名称
	private String ruleDesc; // 规则描述
	private Integer deleted; // 是否删除

	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
		return "CoreDataauthRuleScf ["
				+ "ruleName=" + ruleName + ","
				+ "ruleDesc=" + ruleDesc + ","
				+ "deleted=" + deleted + ","
		+ super.toString() + "]";
	}
}
