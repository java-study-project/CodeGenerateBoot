package com.code.generate.template.param.extend;

import com.code.generate.entity.TableData;

import java.util.List;
import java.util.Map;

/**
 * 描述：模板参数扩展接口
 * map参数，参数名称，表元数据，参数列表
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public interface ITemplateParamExtend {

    /**
     * 参数扩展：
     * 通过对于模板参数的原有字段进行扩展字段的扩展
     * 主要逻辑：
     * 获取模板参数，通过对关键key的获取，结合表元数据部分依赖数据的扩展处理，实现传入的params的列表参数的扩展
     * 在模板中能够直接使用
     * @param templateParam 模板参数
     * @param paramName 参数名称
     * @param tableData 表元数据
     * @param params 参数列表
     */
    void extend(Map<String,Object> templateParam, String paramName, TableData tableData, List<String>params);

}
