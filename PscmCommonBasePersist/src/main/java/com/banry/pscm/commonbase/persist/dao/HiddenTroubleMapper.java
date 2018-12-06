package com.banry.pscm.commonbase.persist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.banry.pscm.commonbase.service.biz.HiddenTrouble;

@Mapper
@Component(value = "hiddenTroubleMapper")
public interface HiddenTroubleMapper {
	
	/**
	 * 根据指定隐患code删除隐患。由于隐患关联着其它表，�?以删除前必须进行�?查，如果有关联，则提示，并禁止删除�??
	 * @param troublecode
	 * @return
	 */
    int deleteByPrimaryKey(String troublecode);
    /**
	 * 插入隐患信息。无论属性是否为null，都保存。如果为null，则置空�?
	 * @param troublecode
	 * @return
	 */
    int insert(HiddenTrouble record);
    /**
	 * 插入保存隐患信息。如果某属�?�为null，则不保存�??
	 * @param troublecode
	 * @return
	 */
    int insertSelective(HiddenTrouble record);
    /**
	 * 更新保存隐患信息。如果某属�?�为null，则不保存�??
	 * @param troublecode
	 * @return
	 */
    int updateByPrimaryKeySelective(HiddenTrouble record);
    /**
	 * 更新隐患信息。无论属性是否为null，都保存。如果为null，则置空�?
	 * @param troublecode
	 * @return
	 */
    int updateByPrimaryKey(HiddenTrouble record);
	
    /**
	 * 根据code查找隐患//这是主键code
	 * @param code
	 * @return
	 */
    HiddenTrouble selectByPrimaryKey(String code);
	/**
	 * 根据code查找隐患//这是divitemcode
	 * @param divcode
	 * @return
	 */
	@Select({
		"select ",
		"trouble_code, div_item_code, div_level, trobule_cate, trobule_level, invest_item, invest_content, description ",
		"from hidden_trouble ",
		"where div_item_code = #{divcode,jdbcType=VARCHAR}"
	})
	@Results(id="BaseHiddenTrouble",value={
			@Result(column = "trouble_code", property = "troubleCode", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "div_item_code", property = "divItemCode", jdbcType = JdbcType.VARCHAR),
			@Result(column = "div_level", property = "divLevel", jdbcType = JdbcType.CHAR),
			@Result(column = "trobule_cate", property = "trobuleCate", jdbcType = JdbcType.VARCHAR),
			@Result(column = "trobule_level", property = "trobuleLevel", jdbcType = JdbcType.VARCHAR),
			@Result(column = "invest_item", property = "investItem", jdbcType = JdbcType.VARCHAR),
			@Result(column = "invest_content", property = "investContent", jdbcType = JdbcType.VARCHAR),
			@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
		})
	List<HiddenTrouble> selectHiddenTroubleListByDivCode(String divcode);
	/**
	 * 查找�?�?
	 * @return
	 */
	@Select({
		"select ",
		"trouble_code, div_item_code, div_level, trobule_cate, trobule_level, invest_item, invest_content, description ",
		"from hidden_trouble "
	})
	@ResultMap("BaseHiddenTrouble")
	List<HiddenTrouble> selectAll();
	/**
	 * @param divname
	 * @return
	 */
	@Select({
		"select ",
		"trouble_code, div_item_code, div_level, trobule_cate, trobule_level, invest_item, invest_content, description ",
		"from hidden_trouble h where h.div_item_code in (",
		"select e.div_item_code from eng_division e where e.div_name like concat('%',#{divname,jdbcType=VARCHAR},'%')",
		");"
	})
	@ResultMap("BaseHiddenTrouble")
	List<HiddenTrouble> findHiddenTroubleByName(String divname);
	
}