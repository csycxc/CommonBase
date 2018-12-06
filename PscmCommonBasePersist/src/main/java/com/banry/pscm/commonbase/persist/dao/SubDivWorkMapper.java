package com.banry.pscm.commonbase.persist.dao;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.banry.pscm.commonbase.service.biz.SubDivWork;

@Mapper
@Component(value = "subDivWorkMapper")
public interface SubDivWorkMapper {
	
	/**
	 * 根据指定分项工程code删除分项工程。由于分项工程关联着其它表，�?以删除前必须进行�?查，
	 * @param subdivcode
	 * @return
	 */
    int deleteByPrimaryKey(String subdivcode);
    /**
	 * 插入分项工程信息。无论属性是否为null，都保存。如果为null，则置空�?
	 * @param subdivcode
	 * @return
	 */
    int insert(SubDivWork record);
    /**
	 * 插入分项工程信息。如果某属�?�为null，则不保存�??
	 * @param subdivcode
	 * @return
	 */
    int insertSelective(SubDivWork record);
    /**
	 * 根据code查找分项工程
	 * @param subdivcode
	 * @return
	 */
    SubDivWork selectByPrimaryKey(String subdivcode);
    /**
	 * 更新分项工程信息。如果某属�?�为null，则不保存�??
	 * @param subdivcode
	 * @return
	 */
    int updateByPrimaryKeySelective(SubDivWork record);
    /**
	 * 更新分项工程信息。无论属性是否为null，都保存。如果为null，则置空�?
	 * @param subdivcode
	 * @return
	 */
    int updateByPrimaryKey(SubDivWork record);
    
	/**
	 * 根据分项工程名查找分项工程�?�这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包�?
	 * @param name
	 * @return
	 */
    @Select({
    	"select ",
    	"sub_div_code, name, charact_des, unit, number, comp_unit_price, temporary_measure_price, quota_manual_fee ",
    	"from sub_div_work ",
    	"where name like concat('%',#{name,jdbcType=VARCHAR},'%')"
    })
    @Results(id="BaseSubDivWork",value= {
    	@Result(column = "sub_div_code", property = "subDivCode", jdbcType = JdbcType.VARCHAR, id = true),
    	@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "charact_des", property = "charactDes", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "number", property = "number", jdbcType = JdbcType.DOUBLE),
    	@Result(column = "comp_unit_price", property = "compUnitPrice", jdbcType = JdbcType.DOUBLE),
    	@Result(column = "temporary_measure_price", property = "temporaryMeasurePrice", jdbcType = JdbcType.DOUBLE),
    	@Result(column = "quota_manual_fee", property = "quotaManualFee", jdbcType = JdbcType.DOUBLE)
    })
	List<SubDivWork> selectByName(String name);
	/**
	 * 查询�?有分项工�?
	 * @return
	 */
    @Select({
    	"select ",
    	"sub_div_code, name, charact_des, unit, number, comp_unit_price, temporary_measure_price, quota_manual_fee ",
    	"from sub_div_work "
    })
    @ResultMap("BaseSubDivWork")
	List<SubDivWork> selectAll();
    
    /**
     * 根据多个主键查询多个对象数据
     * @param arr
     * @return
     */
    @Select({
    	"select ",
    	"sub_div_code, name, charact_des, unit, number, comp_unit_price, temporary_measure_price, quota_manual_fee ",
    	"from sub_div_work ",
    	"where sub_div_code in(#{s,jdbcType=VARCHAR})"
    })
    @ResultMap("BaseSubDivWork")
	List<SubDivWork> findSubDivWorksBySubDivCode(String s);
    
}