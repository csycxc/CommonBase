package com.banry.pscm.commonbase.persist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.banry.pscm.commonbase.service.biz.ItemConsScheme;

@Mapper
@Component(value = "itemConsSchemeMapper")
public interface ItemConsSchemeMapper {
	
	/**
	 * 根据指定施工方案code删除施工方案。由于施工方案关联着其它表，�?以删除前必须进行�?查，
	 * 如果有关联，则提示，并禁止删除�??
	 * @param schemecode
	 * @return
	 */
    int deleteByPrimaryKey(String schemecode);
    /**
     * 插入施工方案信息。无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int insert(ItemConsScheme record);
    /**
     * 插入施工方案信息。如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int insertSelective(ItemConsScheme record);
    /**
     * 根据code查找施工方案
     * @param schemecode
     * @return
     */
    ItemConsScheme selectByPrimaryKey(String schemecode);
    /**
     * 更新施工方案信息。如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ItemConsScheme record);
    /**
     * 更新施工方案信息。无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int updateByPrimaryKey(ItemConsScheme record);
	
    /**
	 * 根据code查找施工方案
	 * 
	 * @param divcode
	 * @return
	 */
    @Select({
    	"select ",
    	"scheme_code, div_item_code, div_level, name, sch_type, paths, description ",
    	" from item_cons_scheme ",
    	"where div_item_code = #{divcode,jdbcType=VARCHAR}"
    })
    @Results(id="BaseItemConsScheme",value={
    	@Result(column = "scheme_code", property = "schemeCode", jdbcType = JdbcType.VARCHAR, id = true),
    	@Result(column = "div_item_code", property = "divItemCode", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "div_level", property = "divLevel", jdbcType = JdbcType.CHAR),
    	@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "sch_type", property = "schType", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "paths", property = "paths", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "description", property = "", jdbcType = JdbcType.VARCHAR),
    })
	List<ItemConsScheme> selectItemConsSchemeListByDivCode(String divcode);
	/**
	 * 查询�?有施工方�?
	 * @return
	 */
    @Select({
    	"select ",
    	"scheme_code, div_item_code, div_level, name, sch_type, paths, description ",
    	"from item_cons_scheme "
    })
    @ResultMap("BaseItemConsScheme")
	List<ItemConsScheme> selectAll();
	/**
	 * @param divname
	 * @return
	 */
    @Select({
    	"select ",
    	"scheme_code, div_item_code, div_level, name, sch_type, paths, description ",
    	"from item_cons_scheme h where h.div_item_code in (",
    	"select e.div_item_code from eng_division e where e.div_name like concat('%',#{divname,jdbcType=VARCHAR},'%')",
    	");"
    })
    @ResultMap("BaseItemConsScheme")
    List<ItemConsScheme> findItemConsSchemeByName(String divname);
	
	
}