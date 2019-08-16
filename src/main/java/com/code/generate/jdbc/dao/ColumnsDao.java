package com.code.generate.jdbc.dao;

import com.code.generate.jdbc.metadata.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：表字段元数据查询dao
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           	   PERSON          REASON
 *  1    2018年12月03日        fjj        Create
 * ****************************************************************************
 * </pre>
 * @author fjj
 * @since 1.0
 */
@Mapper
public interface ColumnsDao  {

	/**
	 * 表字段员数据列表查询
	 * @param bean 查询条件对象
	 * @return 返回列表集合
	 */
	List<Column> findPageByColumn(@Param("bean") Column bean);

}
