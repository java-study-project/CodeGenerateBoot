package com.code.generate.template.param.extend;

import com.code.generate.entity.TableData;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：mysql参数类型扩展
 * -java类型：             _java
 * -带包名的java类型：      _import
 * -是否为数字类型：        _isNum
 * 例：{
 *          dataType:           'varchar',              // mysql类型
 *          dataType_java:      'String',               // java类型
 *          dataType_import:    'java.lang.String',     // 带包名的java类型
 *          dataType_isNum:      false,                 // 是否为数字类型
 *          dataType_isImport:   false,                  // 是否需要导入类
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
@Component("dbTypeExtend")
public class MysqlColumnTypeParamExtend implements ITemplateParamExtend{

    private String[] params = {"_java", "_import", "_isNum", "_isImport"};
    // 数字类型的java类型
    private static List<String> numTypes = Arrays.asList("Integer", "Long", "BigDecimal");
    // 不是默认包和基础类型的Map<类型，带包名类型>
    private static Map<String,String> noLangFillType = new HashMap<>();
    static {
        noLangFillType.put("BigDecimal", "java.math.BigDecimal");
        noLangFillType.put("Date", "java.util.Date");
    }

    @Override
    public void extend(Map<String, Object> templateParam, String paramName, TableData tableData, List<String> params) {
        String dbType = ((String) templateParam.get(paramName)).toUpperCase();

        // 根据数据库类型获得java类型
        String javaType = getJavaType(dbType);
        String jdbcType = getJdbcType(dbType);
        String importType = "";
        Boolean isNum = Boolean.FALSE;
        Boolean isImport = Boolean.FALSE;
        if (numTypes.contains(javaType)) {
            isNum = Boolean.TRUE;
        }
        if (noLangFillType.containsKey(javaType)){
            importType = noLangFillType.get(javaType);
            isImport = Boolean.TRUE;
        }
        // 更具数据库类型，获得转换org.apache.ibatis.type.JdbcType.对应符合的类型
        String mybatisType = getMybatisType(dbType);

        templateParam.put(paramName, dbType);
        templateParam.put(paramName+"_mybatis", mybatisType);
        templateParam.put(paramName+"_jdbc", jdbcType);
        templateParam.put(paramName+"_java", javaType);
        templateParam.put(paramName+"_import", importType);
        templateParam.put(paramName+"_isNum", isNum);
        templateParam.put(paramName+"_isImport", isImport);
    }

    private String getJavaType(String dbType) {
        switch (dbType) {
            case "TINYINT":
            case "SMALLINT":
            case "MEDIUMINT":
            case "INT":
            case "INTEGER":
                return "Integer";
            case "BIGINT":
                return "Long";
            case "FLOAT":
            case "DOUBLE":
            case "DECIMAL":
                return "BigDecimal";
            case "DATE":
            case "TIME":
            case "YEAR":
            case "DATETIME":
            case "TIMESTAMP":
                return "Date";
            case "CHAR":
            case "VARCHAR":
            case "TINYBLOB":
            case "TINYTEXT":
            case "BLOB":
            case "TEXT":
                return "String";
        }
        return "";
    }

    private String getJdbcType(String dbType) {
        switch (dbType) {
            case "TINYINT":
            case "SMALLINT":
            case "BIGINT":
            case "DOUBLE":
            case "DECIMAL":
            case "CHAR":
            case "VARCHAR":
            case "DATE":
            case "TIME":
                return dbType;
            case "MEDIUMINT":
            case "INT":
            case "INTEGER":
                return "INTEGER";
            case "FLOAT":
                return "REAL";
            case "YEAR":
                return "DATE";
            case "DATETIME":
            case "TIMESTAMP":
                return "TIMESTAMP";
            case "TINYBLOB":
            case "BLOB":
                return "BINARY";
            case "TINYTEXT":
                return "VARCHAR";
            case "TEXT":
                return "LONGVARCHAR";
        }
        return dbType;
    }

    /**
     * JDBC 中的dbType有DATETIME，但是mybatis中的jdbcType只有timestamp，没有DATETIME
     * 所以这边遇到datetime转换为timestamp
     */
    private String getMybatisType(String dbType) {
        switch (dbType) {
            case "INT":
                return "INTEGER";
            case "DATETIME":
                return "TIMESTAMP";
            default:
                return dbType;
        }

    }
}
