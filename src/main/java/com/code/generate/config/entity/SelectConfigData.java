package com.code.generate.config.entity;

import com.code.generate.entity.SelectData;
import com.code.generate.entity.SelectItem;
import com.code.generate.utils.YumUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：下拉数据配置
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class SelectConfigData {

    private Map<String, SelectData> data;

    public static SelectConfigData readConfig(String url){
        String filePath = url + "/select.yml";
        try {
            Map<String, SelectData> map = new HashMap<>();
            Map<String, Map<String, String>> parserMap = YumUtils.readYumProps(filePath);
            for (String column : parserMap.keySet()) {
                SelectData selectData = new SelectData();
                selectData.setColumn(column);
                Map<String, String> datasMap = parserMap.get(column);
                if (datasMap!=null && !datasMap.isEmpty()) {
                    List<SelectItem> datas = new ArrayList<>();
                    for (String value : datasMap.keySet()) {
                        String label = datasMap.get(value);
                        SelectItem item = new SelectItem();
                        item.setLabel(label);
                        item.setValue(value);
                        datas.add(item);
                    }
                    selectData.setDatas(datas);
                    selectData.setSelectType(SelectData.SelectType.STATIC);
                }
                map.put(column, selectData);
            }
            return new SelectConfigData();
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件["+filePath+"]报错", e);
        }
    }

    public Map<String, SelectData> getData() {
        return data;
    }

    public void setData(Map<String, SelectData> data) {
        this.data = data;
    }
}
