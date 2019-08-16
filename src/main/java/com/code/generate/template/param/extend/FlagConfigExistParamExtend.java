package com.code.generate.template.param.extend;

import com.code.generate.config.entity.ModuleConfigData;
import com.code.generate.entity.TableData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述：是否在配置字段中存在参数扩展
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
@Component("ableFlagExtend")
public class FlagConfigExistParamExtend implements ITemplateParamExtend{

    private static final List<String> defaultAbleParams = Arrays.asList(
            "ComboAble", "ListHidden", "DetailHidden", "baseField", "OpTimeAble", "i18NCommonAble", "QueryAble");
    @Resource
    private ModuleConfigData moduleConfigData;

    @SuppressWarnings("unchecked")
    @Override
    public void extend(Map<String, Object> templateParam, String paramName, TableData tableData, List<String> params) {
        List<String> ableParams = defaultAbleParams;
        if (params != null && !params.isEmpty()) {
            ableParams = params;
        }
        // 当前参数值
        String paramNameValue = (String) templateParam.get(paramName);
        if (paramNameValue!=null) {
            // 模块配置扩展参数
            Map<String, Object> moduleExtendParams = moduleConfigData.getData().getExtendParams();
            // 表配置扩展参数
            Map<String, Object> tableExtendParams = tableData.getExtendParams();
            for (String ableParam : ableParams) {
                List<String> moduleAbleNames = (List<String>) moduleExtendParams.get(ableParam);
                if (moduleAbleNames !=null && moduleAbleNames.contains(paramNameValue)) {
                    templateParam.put(ableParam, true);
                    continue;
                }
                List<String> tableAbleNames = (List<String>) tableExtendParams.get(ableParam);
                if (tableAbleNames !=null && tableAbleNames.contains(paramNameValue)) {
                    templateParam.put(ableParam, true);
                    continue;
                }
                templateParam.put(ableParam, false);
            }
        }
    }
}
