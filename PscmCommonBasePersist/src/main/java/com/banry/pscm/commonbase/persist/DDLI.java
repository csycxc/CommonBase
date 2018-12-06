package com.banry.pscm.commonbase.persist;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * DDL语句接口
 * @author Administrator
 *
 */
public interface DDLI {

	@Update("create database ${dbName} DEFAULT CHARSET utf8 COLLATE utf8_general_ci")
	public void createDataBase(@Param("dbName") String dbName);

}
