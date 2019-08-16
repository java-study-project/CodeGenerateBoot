/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.dataauth.api.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.scf.component.core.api.AbstractBaseApiImpl;
import com.scf.component.api.IBaseBiz;
import com.scf.dataauth.api.ICoreDataauthRuleScfApi;
import com.scf.dataauth.domain.CoreDataauthRuleScf;
import com.scf.dataauth.service.ICoreDataauthRuleScfBiz;

/**
 * 描述：规则定义表Api接口实现
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
@Service(value="coreDataauthRuleScfApiImpl")
public class CoreDataauthRuleScfApiImpl extends AbstractBaseApiImpl<CoreDataauthRuleScf> implements ICoreDataauthRuleScfApi {

	@Resource
	private ICoreDataauthRuleScfBiz biz;

	@Override
	protected IBaseBiz<CoreDataauthRuleScf> getBiz() {
		return biz;
	}
}
