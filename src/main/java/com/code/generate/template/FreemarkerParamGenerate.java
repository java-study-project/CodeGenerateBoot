package com.code.generate.template;

import com.code.generate.GenerateApplication;
import com.code.generate.config.entity.ModuleConfigData;
import com.code.generate.entity.DetailTableData;
import com.code.generate.entity.ModuleData;
import com.code.generate.entity.TableData;
import com.code.generate.template.entity.ConverterParam;
import com.code.generate.template.param.extend.ITemplateParamExtend;
import com.code.generate.utils.MyBeanUtils;
import com.code.generate.jdbc.metadata.Column;
import com.code.generate.jdbc.metadata.Table;
import com.code.generate.template.entity.FieldsTemplateParam;
import com.code.generate.template.entity.MainTemplateParam;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述：模板参数处理类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
@Component
public class FreemarkerParamGenerate {

    public static final Map<String, Object> constantParam = new HashMap<>();
    static {
        String day = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        constantParam.put("l", "{");
        constantParam.put("r", "}");
        constantParam.put("crateDate",day);//创建时间
    }

    private static final String FIELDS = "fields";
    private static final String DETAILS = "details";
    @Resource
    private ModuleConfigData moduleConfigData;

    public Map<String,Object> getTableTemplateParam(TableData tableData, Table table, List<Column> columnList,
                                                    Map<String, Map<String,Object>> tableTemplateParamMap) {
        // 常量参数设置
        Map<String, Object> templateParam = new HashMap<>(constantParam);
        // 一级参数生成
        setRootParam(templateParam, tableData, table);
        // 二级参数fields生成
        setFieldsParam(templateParam, columnList);
        // 参数转换扩展设置
        setExtendParam(templateParam, tableData);
        // 二级参数details(带明细表时设置)
        setDetailsParam(templateParam, tableData, tableTemplateParamMap);
        return templateParam;
    }

    private void setDetailsParam(Map<String, Object> templateParam, TableData tableData, Map<String, Map<String, Object>> tableTemplateParamMap) {
        // 表类型
        TableData.TableType tableType = tableData.getTableType();
        if (tableType.equals(TableData.TableType.MAIN)){
            // 获得明细表
            List<DetailTableData> detailTables = tableData.getDetailTables();
            List<Map<String, Object>> details = new ArrayList<>();
            for (DetailTableData detailTable : detailTables) {
                String tableName = detailTable.getTableName();
                // 明细表模板参数
                Map<String, Object> childTableTemplateParam = tableTemplateParamMap.get(tableName);
                details.add(childTableTemplateParam);
            }
            templateParam.put(DETAILS, details);
        }
    }

    @SuppressWarnings("unchecked")
    private void setExtendParam(Map<String, Object> templateParam, TableData tableData) {
        List<ConverterParam> converterList = moduleConfigData.getData().getExtendParamConverter();
        for (ConverterParam converter : converterList) {
            String converterName = converter.getConverterName(); // 转换器spring bean 名称
            List<String> params = converter.getParams(); // 转换参数
            ITemplateParamExtend extendConverter = GenerateApplication.applicationContext.getBean(converterName, ITemplateParamExtend.class);
            // root参数扩展
            List<String> mainTemplateParam = converter.getMainTemplateParam();
            if (mainTemplateParam!=null) {
                for (String paramName : mainTemplateParam) {
                    extendConverter.extend(templateParam, paramName, tableData, params);
                }
            }
            // fields项参数扩展
            List<String> fieldsTemplateParam = converter.getFieldsTemplateParam();
            if (fieldsTemplateParam != null) {
                List<Map<String, Object>> fields = (List<Map<String, Object>>) templateParam.get(FIELDS);
                for (String fieldParamName : fieldsTemplateParam) {
                    for (Map<String, Object> fieldItem : fields) {
                        extendConverter.extend(fieldItem, fieldParamName, tableData, params);
                    }
                }
            }
        }
    }

    private void setFieldsParam(Map<String, Object> templateParam, List<Column> columnList) {
        List<Map<String, Object>> fields = new ArrayList<>();
        FieldsTemplateParam fieldsTemplateParam = moduleConfigData.getData().getFieldsTemplateParam();
        // 列元数据参数
        List<String> columnMetadataParam = fieldsTemplateParam.getColumnMetadata();
        for (Column column : columnList) {
            Map<String, Object> fieldItem = new HashMap<>();
            for (String fieldName : columnMetadataParam) {
                Object fieldValue = MyBeanUtils.getBeanFieldValue(column, fieldName);
                setParam(fieldItem, fieldName, fieldValue);
            }
            fields.add(fieldItem);
        }
        templateParam.put(FIELDS, fields);
    }

    private void setRootParam(Map<String,Object> templateParam, TableData tableData, Table table) {
        ModuleData moduleData = moduleConfigData.getData();
        MainTemplateParam mainTemplateParam = moduleData.getMainTemplateParam();
        // 模块参数设置(优先级： 固定参数 > 模块扩展参数)
        List<String> moduleParam = mainTemplateParam.getModule();
        for (String fieldName : moduleParam) {
            Object fieldValue = getMainField(moduleData, fieldName, ModuleData.mainFields);
            if (fieldValue == null) {
                // 参数来自模块扩展配置参数
                fieldValue = moduleData.getExtendParams().get(fieldName);
            }
            setParam(templateParam, fieldName, fieldValue);
        }
        // 表参数设置(优先级： 固定参数 > 表扩展参数 > 模块扩展参数)
        List<String> tableParam = mainTemplateParam.getTable();
        for (String fieldName : tableParam) {
            Object fieldValue = getMainField(tableData, fieldName, TableData.mainFields);
            if (fieldValue == null) {
                // 参数来自表配置扩展参数
                fieldValue = tableData.getExtendParams().get(fieldName);
            }
            if (fieldValue == null) {
                // 参数来自模块配置扩展参数
                fieldValue = moduleData.getExtendParams().get(fieldName);
            }
            setParam(templateParam, fieldName, fieldValue);
        }
        // 表元数据参数设置
        List<String> tableMetadataParam = mainTemplateParam.getTableMetadata();
        for (String fieldName : tableMetadataParam) {
            Object fieldValue = MyBeanUtils.getBeanFieldValue(table, fieldName);
            setParam(templateParam, fieldName, fieldValue);
        }
    }

    private Object getMainField(Object data, String fieldName, String[] mainFields) {
        for (String mainFieldName : mainFields) {
            if (mainFieldName.equals(fieldName)) {
                return MyBeanUtils.getBeanFieldValue(data, mainFieldName);
            }
        }
        return null;
    }

    private void setParam(Map<String, Object> templateParam, String fieldName, Object fileValue) {
        if (fileValue == null){
            templateParam.put(fieldName, ""); // 为空时，设置为空字符串，不能为null
        }
        if (fileValue instanceof String && fileValue.equals("true")){
            templateParam.put(fieldName, true); // 字符串bool值转换
        } else if (fileValue instanceof String && fileValue.equals("false")){
            templateParam.put(fieldName, false); // 字符串bool值转换
        }else{
            templateParam.put(fieldName, fileValue);
        }
    }
}
