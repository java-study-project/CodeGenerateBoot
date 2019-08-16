/* 
 * Copyright (c) 2018, yingtongscf Inc. All rights reserved.
 */
package com.scf.backcontroller.controller.${module};

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scf.${module}.api.I${tableName_class}Api;
import com.scf.component.commoncontroller.controller.BackBaseBusinessController;

/**
 * ${tableComment}控制器
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    ${crateDate}        ${auth}        Create
 * ****************************************************************************
 * </pre>
 * @author ${auth}
 * @since 1.0
 */
@Controller
@RequestMapping("/${tableName_LXX}")
public class ${tableName_class}Controller extends BackBaseBusinessController{

	@Resource
	private I${tableName_class}Api api;

	/**
	 * ${tableComment}分页查询
	 * ${crateDate}  ${auth}
	 */
    @RequestMapping("query")
    @ResponseBody
    public Object queryPage() {
        return api.queryPage(createRequestParams());
    }

<#if WindowSelectAble>
	/**
	 * ${tableComment}搜索帮助分页查询
	 * ${crateDate}  ${auth}
	 */
	@RequestMapping("helpQuery")
	@ResponseBody
	public Object querySelectPage() {
		return api.querySelectPage(createRequestParams());
	}

</#if>

    /**
     * ${tableComment}新增保存
	 * ${crateDate}  ${auth}
     */
    @RequestMapping("add")
    @ResponseBody
    public Object add() {
    	return api.insert(createRequestParams());
    }

    /**
     * ${tableComment}，根据id，获得详情
	 * ${crateDate}  ${auth}
     */
    @RequestMapping("getDetail")
    @ResponseBody
    public Object getDetail() {
    	return api.findById(createRequestParams());
    }
    
    /**
     * ${tableComment}编辑保存
	 * ${crateDate}  ${auth}
     */
    @RequestMapping("update")
    @ResponseBody
    public Object update() {
        return api.update(createRequestParams());
    }
    
    /**
     * ${tableComment}批量删除
	 * ${crateDate}  ${auth}
     */
    @RequestMapping("dels")
    @ResponseBody 
    public Object dels() {
        return api.batchDel(createRequestParams());
    }
}
