package com.code.generate.config.entity;

import com.code.generate.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：模板文件数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class TemplateFileConfigData {
    private static final Logger logger = LoggerFactory.getLogger(TemplateFileConfigData.class);

    private List<File> fileList;

    // 获得模块指定目录下所有模板文件
    public static TemplateFileConfigData readConfig(String url, String[] dirs) {
        TemplateFileConfigData data = new TemplateFileConfigData();
        try{
            List<File> list = new ArrayList<>();
            for (String dir : dirs) {
                list.addAll(FileUtils.getResourceDirListFiles( url + "/" + dir, true));
            }
            data.setFileList(list);
            return data;
        }catch (RuntimeException e){
            logger.warn(e.getMessage());
            data.setFileList(new ArrayList<>());
            return data;
        }
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
}
