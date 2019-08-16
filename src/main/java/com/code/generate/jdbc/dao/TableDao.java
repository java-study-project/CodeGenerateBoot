package com.code.generate.jdbc.dao;

import com.code.generate.jdbc.metadata.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：表元数据查询dao
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/3      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
@Mapper
public interface TableDao {

    /**
     * 表元数据列表查询
     * @param bean 查询条件对象
     * @return 返回列表集合
     */
    List<Table> findPageByTable(@Param("bean") Table bean);

}
