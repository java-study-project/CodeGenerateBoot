package com.code.generate.template;

import com.code.generate.entity.TemplateData;
import com.code.generate.utils.FileUtils;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * 描述：freemarker模板解析
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class FreemarkerTemplateParser {
    private static final Logger logger = LoggerFactory.getLogger(FreemarkerTemplateParser.class);
    private static final FreemarkerTemplateParser parser = new FreemarkerTemplateParser();

    public static void generateCodeFile(String generateDirectory, File templateFile, TemplateData templateFileParam, Map<String, Object> templateParam) {
        // 模板名称
        String templateName = templateFile.getName();
        // 生成文件名
        String fileName = templateName;
        // 生成文件存放路径
        String path = "";
        // 附加还是覆盖
        boolean append = false;
        if (templateFileParam != null) {
            if (!StringUtils.isEmpty(templateFileParam.getName())) {
                fileName = parser.getFreeMarkerString(templateParam, templateFileParam.getName());
            }
            if (!StringUtils.isEmpty(templateFileParam.getPath())) {
                path = parser.getFreeMarkerString(templateParam, templateFileParam.getPath());
            }
            if (templateFileParam.isAppend()) {
                append = templateFileParam.isAppend();
            }
        }
        // 生成文件存放全路径
        String filePath = generateDirectory + File.separator + path;
        //logger.info("----------------["+templateFile.getName() + "]开始生成------------------");
        try{
            //logger.info("----获取模板文件内容.");
            // 根据模板文件获得模板内容
            String fileContent = FileUtils.getFileContent(templateFile);
            //logger.info("----获取模板解析结果.");
            // 根据模板内容和模板参数，得到代码文件内容
            String freeMarkerString = parser.getFreeMarkerString(templateParam, fileContent);
            //logger.info("----生成文件["+fileName+"].");
            if (append) {
                FileUtils.appendGenerateFile(freeMarkerString, filePath, fileName);
                //logger.info("----附加生成成功, path["+filePath+"].");
            }else{
                FileUtils.generateFile(freeMarkerString, filePath, fileName);
                //logger.info("----覆盖生成成功, path["+filePath+"].");
            }
        }catch(Exception e) {
            logger.error("----"+templateName + "生成报错.", e);
            throw new RuntimeException("生成报错");
        }
        //logger.info("----------------["+templateFile.getName() + "]生成结束------------------\n");
    }

    private Configuration configuration = new Configuration();

    private String getFreeMarkerString(Map<String, Object> map, String templateString) {
        Template template = getTemplate(templateString);
        try (Writer out = new StringWriter()) {
            template.process(map, out);
            return out.toString();
        } catch (TemplateException e) {
            throw new RuntimeException("模板异常，终止程序", e);
        } catch (IOException e) {
            throw new RuntimeException("文件异常终止程序", e);
        }
    }

    private Template getTemplate(String templateString) {
        try {
            StringTemplateLoader loader = new StringTemplateLoader();
            loader.putTemplate("temp", templateString);
            configuration.setTemplateLoader(loader);
            return configuration.getTemplate("temp", "utf-8");
        } catch (IOException e) {
            throw new RuntimeException("文件异常终止程序", e);
        }
    }
}
