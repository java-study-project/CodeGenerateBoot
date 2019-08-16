package com.code.generate.jdbc;

import com.code.generate.jdbc.dao.ColumnsDao;
import com.code.generate.jdbc.dao.TableDao;
import com.code.generate.jdbc.metadata.Column;
import com.code.generate.jdbc.metadata.Table;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：连接类
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
public class Connection {

    @Resource
    private TableDao tableDao;
    @Resource
    private ColumnsDao columnsDao;

    public Table getTableMetadata(String tableName) {
        Table queryParam = new Table();
        queryParam.setTableName(tableName);
        List<Table> list = tableDao.findPageByTable(queryParam);
        if(list.isEmpty()){
            throw new RuntimeException(tableName+"表不存在，请检查数据库地址是否正确。");
        }
        if(list.size()>1){
            throw new RuntimeException(tableName+"找到多个表，需要指定数据库过滤。");
        }
        return list.get(0);
    }

    public List<Column> getTableColumnMetadata(String tableName) {
        Column queryParam = new Column();
        queryParam.setTableName(tableName);
        return columnsDao.findPageByColumn(queryParam);
    }

}
