/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.dataauth.ability.impl;

import com.scf.component.core.ability.AbstractBaseDetailDbAbilityImpl;
import com.scf.component.core.dao.BaseDao;
import com.scf.dataauth.ability.ICoreDataauthRuleMapperScfDbAbility;
import com.scf.dataauth.abilitydao.CoreDataauthRuleMapperScfDao;
import com.scf.dataauth.domain.CoreDataauthRuleMapperScf;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 描述：规则数据权限Mapper明细表增删改查能力实现
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
public class CoreDataauthRuleMapperScfDbAbilityImpl extends AbstractBaseDetailDbAbilityImpl<CoreDataauthRuleMapperScf> implements ICoreDataauthRuleMapperScfDbAbility {
	
    @Resource(name="coreDataauthRuleMapperScfDaoImpl")
    private CoreDataauthRuleMapperScfDao dao;

	@Override
	protected BaseDao<CoreDataauthRuleMapperScf> getDao() {
		return dao;
	}
}
