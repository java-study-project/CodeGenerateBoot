/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.${module}.ability.impl;

import com.scf.component.core.ability.AbstractBaseDetailDbAbilityImpl;
import com.scf.component.core.dao.BaseDao;
import com.scf.${module}.ability.I${tableName_class}DbAbility;
import com.scf.${module}.abilitydao.${tableName_class}Dao;
import com.scf.${module}.domain.${tableName_class};
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 描述：${tableComment}增删改查能力实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
@Service
public class ${tableName_class}DbAbilityImpl extends AbstractBaseDetailDbAbilityImpl<${tableName_class}> implements I${tableName_class}DbAbility {
	
    @Resource(name="${tableName_TF}DaoImpl")
    private ${tableName_class}Dao dao;

	@Override
	protected BaseDao<${tableName_class}> getDao() {
		return dao;
	}
}
