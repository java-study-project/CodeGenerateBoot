/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.${module}.api.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.scf.component.core.api.AbstractBaseApiImpl;
import com.scf.component.api.IBaseBiz;
import com.scf.${module}.api.I${tableName_class}Api;
import com.scf.${module}.domain.${tableName_class};
import com.scf.${module}.service.I${tableName_class}Biz;

/**
 * 描述：${tableComment}Api接口实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
@Service(value="${tableName_TF}ApiImpl")
public class ${tableName_class}ApiImpl extends AbstractBaseApiImpl<${tableName_class}> implements I${tableName_class}Api {

	@Resource
	private I${tableName_class}Biz biz;

	@Override
	protected IBaseBiz<${tableName_class}> getBiz() {
		return biz;
	}
}
