package com.banry.pscm.commonbase.persist.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.banry.pscm.commonbase.service.biz.EngDivision;

/**
 * 工程划分服务�?
 * @author chenshiyu
 *
 */
@Mapper
@Component(value = "engDivisionMapper")
public interface EngDivisionMapper {
	
	/**
	 * 根据指定工程划分code删除工程划分。由于工程划分关联着其它表，�?以删除前必须进行�?查，如果有关联，则提示，并禁止删除�?�返回�?�为0为有关联，禁止删除；返回1为无关联，直接删除�??
	 * @param divitemcode
	 * @return
	 */
    int deleteByPrimaryKey(String divitemcode);
    /**
     * 保存工程划分信息。无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int insert(EngDivision record);
    /**
     * 保存工程划分信息。如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int insertSelective(EngDivision record);
    /**
     * 根据code查找工程划分
     * @param divitemcode
     * @return
     */
    EngDivision selectByPrimaryKey(String divitemcode);
    /**
     * 保存工程划分信息。如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EngDivision record);
    /**
     * 保存工程划分信息。无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int updateByPrimaryKey(EngDivision record);
    
	/**
	 * 根据工程划分名查找工程划分�?�这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包�?
	 * @param divname
	 * @return
	 */
    @Select({
    	"select ","div_item_code, div_name, skill, div_level, parent_div_item_code ",
    	"from eng_division ","where div_name like concat('%',#{divname,jdbcType=VARCHAR},'%')"
    })
    @Results(id="BaseEngDivision",value= {
    		@Result(column = "div_item_code", property = "divItemCode", jdbcType = JdbcType.VARCHAR, id = true),
    		@Result(column = "div_name", property = "divName", jdbcType = JdbcType.VARCHAR),
    		@Result(column = "skill", property = "skill", jdbcType = JdbcType.VARCHAR),
    		@Result(column = "div_level", property = "divLevel", jdbcType = JdbcType.CHAR),
    		@Result(column = "parent_div_item_code", property = "parentDivItemCode", jdbcType = JdbcType.VARCHAR)
    })
	List<EngDivision> selectByDivName(String divname);
	/**
	 * 查找�?有工程划�?
	 * @return
	 */
    @Select({
    	"select ","div_item_code, div_name, skill, div_level, parent_div_item_code ", "from eng_division"
    })
    @ResultMap("BaseEngDivision")
	List<EngDivision> selectAll();
	/**
	 * 查询�?有divlevel，并降序�?
	 * @return
	 */
    @Select("select distinct div_level from eng_division order by div_level desc;")
	List<String> selectDivLevel();
	/**
	 * 查询树形结构的数据，find_in_set中不能有英文逗号
	 * @param code
	 * @return     目前有问题，没改。带小数点的divitemcode，getChildLst不能查出来�??
	 */
	//@Select("select * from engdivision where find_in_set(DivItemCode,getChildLst(#{code}));")
	//List<EngDivision> findEngDivisionTreeByCode(String code);
	
    @Select("select * from eng_division")
	@Results({ 
		@Result(id = true, column = "div_item_code", property = "id"),
		@Result(column = "div_name", property = "name"), 
		@Result(column = "parent_div_item_code", property = "pId") })
	public List<HashMap> getEngdivisionListForZTree();
    
}