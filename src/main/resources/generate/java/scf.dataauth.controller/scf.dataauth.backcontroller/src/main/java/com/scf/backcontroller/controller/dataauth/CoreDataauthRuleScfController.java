/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.backcontroller.controller.dataauth;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scf.dataauth.api.ICoreDataauthRuleScfApi;
import com.scf.component.commoncontroller.controller.BackBaseBusinessController;

/**
 * 规则定义表控制器
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    2019年08月16日        wulonghuai        Create
 * ****************************************************************************
 * </pre>
 * @author wulonghuai
 * @since 1.0
 */
@Controller
@RequestMapping("/coredataauthrulescf")
public class CoreDataauthRuleScfController extends BackBaseBusinessController{

	@Resource
	private ICoreDataauthRuleScfApi api;

	/**
	 * 规则定义表分页查询
	 * 2019年08月16日  wulonghuai
	 */
    @RequestMapping("query")
    @ResponseBody
    public Object queryPage() {
        return api.queryPage(createRequestParams());
    }


    /**
     * 规则定义表新增保存
	 * 2019年08月16日  wulonghuai
     */
    @RequestMapping("add")
    @ResponseBody
    public Object add() {
    	return api.insert(createRequestParams());
    }

    /**
     * 规则定义表，根据id，获得详情
	 * 2019年08月16日  wulonghuai
     */
    @RequestMapping("getDetail")
    @ResponseBody
    public Object getDetail() {
    	return api.findById(createRequestParams());
    }
    
    /**
     * 规则定义表编辑保存
	 * 2019年08月16日  wulonghuai
     */
    @RequestMapping("update")
    @ResponseBody
    public Object update() {
        return api.update(createRequestParams());
    }
    
    /**
     * 规则定义表批量删除
	 * 2019年08月16日  wulonghuai
     */
    @RequestMapping("dels")
    @ResponseBody 
    public Object dels() {
        return api.batchDel(createRequestParams());
    }
}
