package com.code.generate.main;

import com.code.generate.config.entity.ModuleConfigData;
import com.code.generate.config.entity.TableConfigData;
import com.code.generate.config.entity.TemplateConfigData;
import com.code.generate.config.entity.TemplateFileConfigData;
import com.code.generate.entity.TableData;
import com.code.generate.entity.TemplateData;
import com.code.generate.jdbc.Connection;
import com.code.generate.jdbc.metadata.Column;
import com.code.generate.template.FreemarkerParamGenerate;
import com.code.generate.template.FreemarkerTemplateParser;
import com.code.generate.jdbc.metadata.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：入口类
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/3      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
@Component
public class GenerateMain {
    private static final Logger logger = LoggerFactory.getLogger(GenerateMain.class);

    /**
     * Field:
     * 数据库连接
     * 模板参数处理类
     * 模块数据
     * 表配置数据
     * 模板配置数据
     * singleTemplateFile：模板文件数据
     * mainTemplateFile：模板文件数据
     * detailTemplateFile：模板文件数据
     * 表对应元数据map
     * 表对应字段的元数据集合map
     * 表对应的模板配置
     */
    @Resource
    private Connection connection;
    @Resource
    private FreemarkerParamGenerate paramGenerate;

    @Resource
    private ModuleConfigData moduleConfigData;
    @Resource
    private TableConfigData tableConfigData;
    @Resource
    private TemplateConfigData templateConfigData;

    @Resource(name="singleTemplateFile")
    private TemplateFileConfigData singleTemplateFile;
    @Resource(name="mainTemplateFile")
    private TemplateFileConfigData mainTemplateFile;
    @Resource(name="detailTemplateFile")
    private TemplateFileConfigData detailTemplateFile;

       // 表对应元数据map
    private Map<String, Table> tableMetadataMap = new HashMap<>();
    // 表对应字段的元数据集合map
    private Map<String, List<Column>> tableColumnMetadataMap = new HashMap<>();
    // 表对应的模板配置
    private Map<String, Map<String,Object>> tableTemplateParamMap = new HashMap<>();

    public void generate() {
        // 获得表数据库元数据
        logger.info("----------------获取元数据------------------");
        getTableMetadata(tableConfigData.getSingleTables());
        getTableMetadata(tableConfigData.getDetailTables());
        getTableMetadata(tableConfigData.getMainTables());
        if (!tableMetadataMap.isEmpty()) {
            // 生成模板参数
            logger.info("----------------拼装模板参数------------------");
            templateParamGenerate(tableConfigData.getSingleTables());
            templateParamGenerate(tableConfigData.getDetailTables());
            templateParamGenerate(tableConfigData.getMainTables());
            // 开始生成文件
            generateCodeFile(tableConfigData.getSingleTables(), singleTemplateFile.getFileList());
            generateCodeFile(tableConfigData.getDetailTables(), detailTemplateFile.getFileList());
            generateCodeFile(tableConfigData.getMainTables(), mainTemplateFile.getFileList());
        }
    }

    private void templateParamGenerate(Set<? extends TableData> tables) {
        for (TableData tableData : tables) {
            String tableName = tableData.getTableName();
            Table table = tableMetadataMap.get(tableName);
            List<Column> columnList = tableColumnMetadataMap.get(tableName);
            Map<String, Object> templateParam = paramGenerate.getTableTemplateParam(
                    tableData, table, columnList, tableTemplateParamMap);
            tableTemplateParamMap.put(tableName, templateParam);
        }
    }

    private void getTableMetadata(Set<? extends TableData> tables) {
        for (TableData tableData : tables) {
            String tableName = tableData.getTableName();
            tableMetadataMap.put(tableName, connection.getTableMetadata(tableName));
            tableColumnMetadataMap.put(tableName,connection.getTableColumnMetadata(tableName));
        }
    }

    private void generateCodeFile(Set<? extends TableData> tables, List<File> templateFileList) {
        String generateDirectory = moduleConfigData.getData().getGenerateDirectory();
        for (TableData tableData : tables) {
            String tableName = tableData.getTableName();
            Map<String, Object> templateParam = tableTemplateParamMap.get(tableName);
            logger.info("----------------开始生成["+tableName + "]------------------");
            for (File templateFile : templateFileList) {
                String fileName = templateFile.getName();
                logger.info("----------------["+fileName + "]------------------");
                TemplateData templateFileParam = templateConfigData.getData().get(fileName);
                boolean canGenerate = false;
                if (templateFileParam==null) {
                    canGenerate = true; // 为配置模板参数，使用默认生成
                } else {
                    List<String> useAble = templateFileParam.getUseAble();
                    if (useAble == null || useAble.isEmpty()) {
                        canGenerate = true; // 模板配置未配置useAble属性，默认生成
                    } else {
                        for (String param : useAble) {
                            if (templateParam.containsKey(param) && Boolean.valueOf(templateParam.get(param).toString())) {
                                canGenerate = true; // 模板配置配置useAble了属性集合，存在属性为true时才生成
                                break;
                            }
                        }
                    }
                }
                if (canGenerate) {
                    FreemarkerTemplateParser.generateCodeFile(generateDirectory, templateFile, templateFileParam, templateParam);
                }else{
                    logger.info("跳过："+templateFileParam.getTemplateName());
                }
            }
        }
    }
}
