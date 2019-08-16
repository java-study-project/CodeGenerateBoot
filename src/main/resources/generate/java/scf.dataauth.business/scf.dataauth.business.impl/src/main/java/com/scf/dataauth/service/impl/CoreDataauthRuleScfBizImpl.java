/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.dataauth.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.scf.component.api.IBaseDbAbility;
import com.scf.component.api.IBaseDetailDbAbility;
import com.scf.component.core.biz.AbstractMainBizImpl;
import com.scf.component.core.domain.dto.RequestParams;
import com.scf.component.core.domain.example.BaseExample;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.scf.dataauth.ability.ICoreDataauthRuleScfDbAbility;
import com.scf.dataauth.ability.ICoreDataauthRuleMapperScfDbAbility;
import com.scf.dataauth.domain.CoreDataauthRuleScf;
import com.scf.dataauth.dto.query.CoreDataauthRuleScfQueryData;
import com.scf.dataauth.dto.save.CoreDataauthRuleScfSaveData;
import com.scf.dataauth.service.ICoreDataauthRuleScfBiz;

/**
 * 描述：规则定义表业务接口实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON           REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
@Service(value="coreDataauthRuleScfBizImpl")
public class CoreDataauthRuleScfBizImpl extends AbstractMainBizImpl<CoreDataauthRuleScf, CoreDataauthRuleScfSaveData> implements ICoreDataauthRuleScfBiz {

	private static Map<String, IBaseDetailDbAbility> detailAbilityMap;
    @Resource
    private ICoreDataauthRuleScfDbAbility dbAbility;
	@Resource
	private ICoreDataauthRuleMapperScfDbAbility ruleMapperDbAbility;

	@Override
	public IBaseDbAbility<CoreDataauthRuleScf> getDbAbility() {
		return dbAbility;
	}

	@Override
	public Map<String, IBaseDetailDbAbility> getDetailAbilityMap() {
		if (detailAbilityMap == null) {
			Map<String, IBaseDetailDbAbility> map = new HashMap<>();
			map.put(CoreDataauthRuleScfSaveData.RuleMapper, ruleMapperDbAbility);
			detailAbilityMap = map;
		}
		return detailAbilityMap;
	}

	@Override
	public BaseExample getQueryParam(RequestParams requestParams) {
		CoreDataauthRuleScfQueryData queryParam = new CoreDataauthRuleScfQueryData();
		queryParam.setDeleted(0); // 默认查询未删除的数据
		SimpleBeanUtils.copyProperties(queryParam, requestParams.getValues(), new String[]{
			"deleted",
		});
		BaseExample<CoreDataauthRuleScf> example = new BaseExample<>(queryParam);
		example.createCriteria()
				.andEqualTo("deleted", queryParam.getDeleted(), true)
		;
		return example;
	}

	@Override
	public Object beforeSave(String saveType, CoreDataauthRuleScfSaveData saveData, CoreDataauthRuleScf infoData, RequestParams requestParams) {
		//infoData.setState("normal");
		return null;
	}
}
