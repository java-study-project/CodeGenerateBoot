/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.${module}.abilitydao.impl;

import org.springframework.stereotype.Repository;

import com.scf.component.core.dao.impl.BaseDaoImpl;
import com.scf.${module}.abilitydao.${tableName_class}Dao;
import com.scf.${module}.domain.${tableName_class};

/**
 * 描述：${tableComment}dao实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
@Repository("${tableName_TF}DaoImpl")
public class ${tableName_class}DaoImpl extends BaseDaoImpl<${tableName_class}> implements ${tableName_class}Dao{

}
