package com.code.generate.jdbc.metadata;

/**
 * 描述：字段员数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/3      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class Column {
     private String tableCatalog;           //表限定符。
     private String tableSchema;            //表所有者(数据库名)。
     private String tableName;              //表名。
     private String columnName;             //列名。
     private Long ordinalPosition;        //列标识号。
     private String columnDefault;          //列的默认值。
     private String isNullable;             //列的为空性。如果列允许 NULL，那么该列返回 YES。否则，返回 NO。
     private String dataType;               //系统提供的数据类型。
     private Long characterMaximumLength; //以字符为单位的最大长度，适于二进制数据、字符数据，或者文本和图像数据。否则，返回 NULL
     private Long characterOctetLength;   //以字节为单位的最大长度，适于二进制数据、字符数据，或者文本和图像数据。否则，返回 NULL。
     private Long numericPrecision;       //近似数字数据、精确数字数据、整型数据或货币数据的精度。否则，返回 NULL。
     private Long numericScale;           //近似数字数据、精确数字数据、整型数据或货币数据的精度基数。否则，返回 NULL。
     private Long datetimePrecision;      //近似数字数据、精确数字数据、整数数据或货币数据的小数位数。否则，返回 NULL。
     private String characterSetName;       //datetime 及 SQL-92 interval 数据类型的子类型代码。对于其它数据类型，返回 NULL。
     private String collationName;          //如果列是字符数据或 text 数据类型，那么返回 master，指明字符集所在的数据库。否则，返回 NULL。
     private String columnType;             //如果列是字符数据或 text 数据类型，那么返回 DBO，指明字符集的所有者名称。否则，返回 NULL。
     private String columnKey;              //如果该列是字符数据或 text 数据类型，那么为字符集返回唯一的名称。否则，返回 NULL。
     private String extra;                  //如果列是字符数据或 text 数据类型，那么返回 master，指明在其中定义排序次序的数据库。否则此列为 NULL。
     private String privileges;             //返回 DBO，为字符数据或 text 数据类型指明排序次序的所有者。否则，返回 NULL。
     private String columnComment;          //如果列是字符数据或 text 数据类型，那么为排序次序返回唯一的名称。否则，返回 NULL。
     private String generationExpression;   //如果列是一种用户定义数据类型，那么该列是某个数据库名称，在该数据库名中创建了这种用户定义数据类型。否则，返回 NULL。

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(Long characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public Long getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Long characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public Long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Long getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Long numericScale) {
        this.numericScale = numericScale;
    }

    public Long getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(Long datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getGenerationExpression() {
        return generationExpression;
    }

    public void setGenerationExpression(String generationExpression) {
        this.generationExpression = generationExpression;
    }

    @Override
    public String toString() {
        return "Column{" + "tableCatalog='" + tableCatalog + '\'' + ", tableSchema='" + tableSchema + '\'' + ", tableName='" + tableName + '\'' + ", columnName='" + columnName + '\'' + ", ordinalPosition=" + ordinalPosition + ", columnDefault='" + columnDefault + '\'' + ", isNullable='" + isNullable + '\'' + ", dataType='" + dataType + '\'' + ", characterMaximumLength=" + characterMaximumLength + ", characterOctetLength=" + characterOctetLength + ", numericPrecision=" + numericPrecision + ", numericScale=" + numericScale + ", datetimePrecision=" + datetimePrecision + ", characterSetName='" + characterSetName + '\'' + ", collationName='" + collationName + '\'' + ", columnType='" + columnType + '\'' + ", columnKey='" + columnKey + '\'' + ", extra='" + extra + '\'' + ", privileges='" + privileges + '\'' + ", columnComment='" + columnComment + '\'' + ", generationExpression='" + generationExpression + '\'' + '}';
    }
}
