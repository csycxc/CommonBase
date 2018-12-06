package com.banry.pscm.commonbase.persist.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DDSMaper {

	@Select("select * from common_db_cat")
	public List<HashMap> getDSList();

}
