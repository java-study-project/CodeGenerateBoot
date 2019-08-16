package com.code.generate.template.param.extend;

import com.code.generate.entity.TableData;
import com.code.generate.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 描述：英文字符串参数扩展
 * -驼峰：_TF     驼峰形式（以‘_’作为单词分隔符，去除‘_’拼接单词, 第一个单词全小写，后续单词首字母大写）
 * -类：_class,   类名形式 = 驼峰形式 + 首字母大写
 * -类小写：_LXX   = 类名形式 + 全部转小写
 * -类大写：_LDX   = 类名形式 + 全部转大写
 * -英文：_en      英文 = 类名形式 + 单词以空格分隔，并去除Scf
 * -国际化Key：_i18n  国际化 = 类名形式 + 单词以下划线分隔
 * 例：{
 *          tableName:          'sc_info_scf',  // 原始参数
 *          tableName_TF:       'scInfoScf',    // 驼峰
 *          tableName_class:    'ScInfoScf',    // 类
 *          tableName_LXX:      'scinfoscf',    // 类小写
 *          tableName_LDX:      'SCINFOSCF',    // 类大写
 *          tableName_en:       'Sc Info',  // 英文 （需要去除Scf）
 *          tableName_i18n:     'Sc_Info_Scf',  // 国际化Key
 *     }
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
@Component("stringExtend")
public class EnglishStringParamExtend implements ITemplateParamExtend{

    private static final String SCF = "_scf";
    private String[] params = {"_TF", "_class", "_LXX", "_LDX", "_en", "_i18n"};
    @Override
    public void extend(Map<String, Object> templateParam, String paramName, TableData tableData, List<String> params) {
        String paramValue = (String) templateParam.get(paramName);
        if (paramValue != null) {
            String paramValue_TF = StringUtils.toJavaFieldName(paramValue);
            String paramValue_class = StringUtils.firstCharUpperCas(paramValue_TF);
            templateParam.put(paramName+"_TF", paramValue_TF);
            templateParam.put(paramName+"_class", paramValue_class);
            templateParam.put(paramName+"_LXX", paramValue_class.toLowerCase());
            templateParam.put(paramName+"_LDX", paramValue_class.toUpperCase());
            templateParam.put(paramName+"_en", StringUtils.replaceSeparatorAndWordFirstCharUpper(
                    paramValue.replace(SCF, ""),"_"," "));
            templateParam.put(paramName+"_i18n", StringUtils.replaceSeparatorAndWordFirstCharUpper(
                    paramValue,"_","_"));
        }
    }
}
