package com.code.generate.config.entity;

import com.code.generate.entity.TableData;
import com.code.generate.entity.DetailTableData;
import com.code.generate.utils.MyBeanUtils;
import com.code.generate.utils.YumUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：表配置数据
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/5      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class TableConfigData {
    private Set<TableData> mainTables;
    private Set<TableData> singleTables;
    private Set<DetailTableData> detailTables;

    public static TableConfigData readConfig(String url, ModuleConfigData moduleData){
        String filePath = url + "/tables.yml";
        try {
            _TablesConfig config = YumUtils.readYumProps(filePath, _TablesConfig.class);
            List<TableData> tables = MyBeanUtils.convertList(config.getTables(), TableData.class);
            List<DetailTableData> detailTables = MyBeanUtils.convertList(config.getDetailTables(), DetailTableData.class);
            return setTablesChild(tables, detailTables, moduleData);
        } catch (Exception e) {
            throw new RuntimeException("解析配置文件["+filePath+"]报错", e);
        }
    }

    private static TableConfigData setTablesChild(List<TableData> tables, List<DetailTableData> detailTables, ModuleConfigData moduleData) {
        Set<TableData> _mainTables = new HashSet<>();
        Set<TableData> _singleTables = new HashSet<>();
        Set<DetailTableData> _detailTables = new HashSet<>();
        for (TableData table : tables) {
            List<DetailTableData> childTables = new ArrayList<>();
            for (DetailTableData detailTable : detailTables) {
                if (table.getTableName().equals(detailTable.getMainTableName())){
                    childTables.add(detailTable);
                }
            }
            if (childTables.isEmpty()){
                // 校验表是否开启生成
                if (ModuleConfigData.checkOpenTable(moduleData, table.getTableName())) {
                    table.setTableType(TableData.TableType.SINGLE);
                    _singleTables.add(table);
                }
            }else{
                // 校验表是否开启生成, 主表开启，关联明细表自动开启
                if (ModuleConfigData.checkOpenTable(moduleData, table.getTableName())) {
                    table.setDetailTables(childTables);
                    table.setTableType(TableData.TableType.MAIN);
                    _mainTables.add(table);
                    for (DetailTableData childTable : childTables) {
                        childTable.setTableType(TableData.TableType.DETAIL);
                        childTable.setMainSimpleName(table.getSimpleName());
                        _detailTables.add(childTable);
                    }
                } else {
                    for (DetailTableData childTable : childTables) {
                        // 主表未开启，关联明细表可以配置开启
                        if (ModuleConfigData.checkOpenTable(moduleData, childTable.getTableName())) {
                            childTable.setTableType(TableData.TableType.DETAIL);
                            _detailTables.add(childTable);
                        }
                    }
                }
            }
        }
        TableConfigData data = new TableConfigData();
        data.setMainTables(_mainTables);
        data.setSingleTables(_singleTables);
        data.setDetailTables(_detailTables);
        return data;
    }

    public static class _TablesConfig {
        private List<TableData> tables; // 主表配置
        private List<DetailTableData> detailTables; // 明细表配置

        public List<TableData> getTables() {
            return tables;
        }

        public void setTables(List<TableData> tables) {
            this.tables = tables;
        }

        public List<DetailTableData> getDetailTables() {
            return detailTables;
        }

        public void setDetailTables(List<DetailTableData> detailTables) {
            this.detailTables = detailTables;
        }
    }

    public Set<TableData> getMainTables() {
        return mainTables;
    }

    public void setMainTables(Set<TableData> mainTables) {
        this.mainTables = mainTables;
    }

    public Set<TableData> getSingleTables() {
        return singleTables;
    }

    public void setSingleTables(Set<TableData> singleTables) {
        this.singleTables = singleTables;
    }

    public Set<DetailTableData> getDetailTables() {
        return detailTables;
    }

    public void setDetailTables(Set<DetailTableData> detailTables) {
        this.detailTables = detailTables;
    }
}
