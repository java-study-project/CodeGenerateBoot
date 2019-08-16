/*
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.dataauth.ability.impl;

import com.scf.component.core.ability.AbstractBaseDbAbilityImpl;
import com.scf.component.core.dao.BaseDao;
import com.scf.dataauth.ability.ICoreDataauthRuleScfDbAbility;
import com.scf.dataauth.abilitydao.CoreDataauthRuleScfDao;
import com.scf.dataauth.domain.CoreDataauthRuleScf;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 描述：规则定义表数据库操作能力实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
@Service
public class CoreDataauthRuleScfDbAbilityImpl extends AbstractBaseDbAbilityImpl<CoreDataauthRuleScf> implements ICoreDataauthRuleScfDbAbility {

	@Resource(name="coreDataauthRuleScfDaoImpl")
	private CoreDataauthRuleScfDao dao;

	@Override
	protected BaseDao<CoreDataauthRuleScf> getDao() {
		return dao;
	}
}
