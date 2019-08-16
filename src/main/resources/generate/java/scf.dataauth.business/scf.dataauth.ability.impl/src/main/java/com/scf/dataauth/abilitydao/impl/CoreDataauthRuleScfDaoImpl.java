/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.dataauth.abilitydao.impl;

import org.springframework.stereotype.Repository;

import com.scf.component.core.dao.impl.BaseDaoImpl;
import com.scf.dataauth.abilitydao.CoreDataauthRuleScfDao;
import com.scf.dataauth.domain.CoreDataauthRuleScf;

/**
 * 描述：规则定义表dao实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
@Repository("coreDataauthRuleScfDaoImpl")
public class CoreDataauthRuleScfDaoImpl extends BaseDaoImpl<CoreDataauthRuleScf> implements CoreDataauthRuleScfDao{

}
