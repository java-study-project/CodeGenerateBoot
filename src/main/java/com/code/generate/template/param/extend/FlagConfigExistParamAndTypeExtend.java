package com.code.generate.template.param.extend;

import com.code.generate.config.entity.ModuleConfigData;
import com.code.generate.entity.TableData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述：是否在配置字段中存在参数扩展（并支持设置参数的类型）
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
@Component("ableFlagAndTypeExtend")
public class FlagConfigExistParamAndTypeExtend implements ITemplateParamExtend{

    private static final List<String> defaultAbleParams = Arrays.asList("QueryAble");
    public static final String SEPARATE = "-";
    @Resource
    private ModuleConfigData moduleConfigData;

    @SuppressWarnings("unchecked")
    @Override
    public void extend(Map<String, Object> templateParam, String paramName, TableData tableData, List<String> params) {
        List<String> queryAbleParams = defaultAbleParams;
        if (params != null && !params.isEmpty()) {
            queryAbleParams = params;
        }
        // 当前参数值
        String paramNameValue = (String) templateParam.get(paramName);
        if (paramNameValue!=null) {
            // 模块配置扩展参数
            Map<String, Object> moduleExtendParams = moduleConfigData.getData().getExtendParams();
            // 表配置扩展参数
            Map<String, Object> tableExtendParams = tableData.getExtendParams();
            for (String queryParam : queryAbleParams) {
                // 优先级： 表配置 > 模块配置
                List<String> tableAbleNames = (List<String>) tableExtendParams.get(queryParam);
                if (queryValid(templateParam, paramNameValue, queryParam, tableAbleNames))
                    continue;
                List<String> moduleAbleNames = (List<String>) moduleExtendParams.get(queryParam);
                if (queryValid(templateParam, paramNameValue, queryParam, moduleAbleNames))
                    continue;
                templateParam.put(queryParam, false);
            }
        }
    }

    private boolean queryValid(Map<String, Object> templateParam, String paramNameValue, String queryParam, List<String> ableNames) {
        if (ableNames !=null) {
            // 获取属性值在配置中是否存在，并获取属性类型
            String queryParamType = queryParamContains(ableNames, paramNameValue);
            if (queryParamType != null) {
                templateParam.put(queryParam, true);
                templateParam.put(queryParam+"Type", queryParamType);
                return true;
            }
        }
        return false;
    }

    private String queryParamContains(List<String> queryAbleNames, String paramNameValue) {
        for (String queryAbleName : queryAbleNames) {
            if (queryAbleName.contains(SEPARATE)){
                String[] split = queryAbleName.split(SEPARATE);
                if (split[0].equals(paramNameValue)) {
                    return split[1]; // 配置现在限制只支持分隔一次（返回设置的类型）
                }
            }else if (queryAbleName.equals(paramNameValue)) {
                return "all"; // 类型未设置则类型为 all
            }
        }
        return null; // 匹配失败
    }
}
