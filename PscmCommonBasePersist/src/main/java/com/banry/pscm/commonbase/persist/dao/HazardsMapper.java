package com.banry.pscm.commonbase.persist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.banry.pscm.commonbase.service.biz.Hazards;

/**
 * 危险源对外接�?
 * @author chenshiyu
 *
 */
@Mapper
@Component(value = "hazardsMapper")
public interface HazardsMapper {
	
	/**
	 * 根据指定危险源code删除危险源�?�由于危险源关联�?其它表，�?以删除前必须进行�?查，如果有关联，则提示，并禁止删除�??//返回�? :0 未删�?;1 删除
	 * @param hazardscode
	 * @return
	 */
    int deleteByPrimaryKey(String hazardscode);
    /**
     * 插入危险源信息�?�无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int insert(Hazards record);
    /**
     * 插入危险源信息�?�如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int insertSelective(Hazards record);
    /**
     * 根据code查找危险�?
     * @param hazardscode
     * @return
     */
    Hazards selectByPrimaryKey(String hazardscode);
    /**
     * 更新危险源信息�?�如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Hazards record);
    /**
     * 更新危险源信息�?�无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int updateByPrimaryKey(Hazards record);

	/**
	 * 根据divcode查找 n�? 危险�?
	 * @param divcode
	 * @return
	 */
	@Select({
		"select ",
		"hazards_code, div_item_code, div_level, hazards_factors, hazards_level, accidents, controls_measures, description ",
		"from hazards ",
		"where div_item_code = #{divcode,jdbcType=VARCHAR}"
	})
	@Results(id="BaseHazards",value={
		@Result(column = "hazards_code", property = "hazardsCode", jdbcType = JdbcType.VARCHAR, id = true),
		@Result(column = "div_item_code", property = "divItemCode", jdbcType = JdbcType.VARCHAR),
		@Result(column = "div_level", property = "divLevel", jdbcType = JdbcType.CHAR),
		@Result(column = "hazards_factors", property = "hazardsFactors", jdbcType = JdbcType.VARCHAR),
		@Result(column = "hazards_level", property = "hazardsLevel", jdbcType = JdbcType.VARCHAR),
		@Result(column = "accidents", property = "accidents", jdbcType = JdbcType.VARCHAR),
		@Result(column = "controls_measures", property = "controlsMeasures", jdbcType = JdbcType.VARCHAR),
		@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
	})
    List<Hazards> selectHazardsListByDivCode(String divcode);
	/**
	 * 查找�?有危险源
	 * @return
	 */
	@Select({
		"select ",
		"hazards_code, div_item_code, div_level, hazards_factors, hazards_level, accidents, controls_measures, description ",
		"from hazards "
	})
	@ResultMap("BaseHazards")
	List<Hazards> selectAll();
	/**
	 * @param divname
	 * @return
	 */
	@Select({
		"select ",
		"hazards_code, div_item_code, div_level, hazards_factors, hazards_level, accidents, controls_measures, description ",
		"from hazards h where h.div_item_code in (",
		"select e.div_item_code from eng_division e where e.div_name like concat('%',#{divname,jdbcType=VARCHAR},'%')",
		")"
	})
	@ResultMap("BaseHazards")
	List<Hazards> findHazardsByName(String divname);

	
}