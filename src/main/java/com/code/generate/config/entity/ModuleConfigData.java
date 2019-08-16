package com.code.generate.config.entity;

import com.code.generate.entity.ModuleData;
import com.code.generate.utils.YumUtils;

import java.util.List;

/**
 * 描述：模块数据
 * 读取元数据，module.yml，必须有module元数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class ModuleConfigData {

    private ModuleData data;

    public static ModuleConfigData readConfig(String url){
        String filePath = url + "/module.yml";
        try {
            ModuleData data = YumUtils.readYumProps3(filePath, ModuleData.class);
            ModuleConfigData configData = new ModuleConfigData();
            configData.setData(data);
            return configData;
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件["+filePath+"]报错", e);
        }
    }

    static boolean checkOpenTable(ModuleConfigData moduleData, String tableName) {
        List<String> openTables = moduleData.getData().getOpenTables();
        return openTables.contains("all") || openTables.contains(tableName);
    }

    public ModuleData getData() {
        return data;
    }

    public void setData(ModuleData data) {
        this.data = data;
    }
}
