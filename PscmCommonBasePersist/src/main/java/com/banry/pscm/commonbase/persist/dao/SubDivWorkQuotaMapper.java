package com.banry.pscm.commonbase.persist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import com.banry.pscm.commonbase.service.biz.SubDivWorkQuota;

@Mapper
@Component(value = "subDivWorkQuotaMapper")
public interface SubDivWorkQuotaMapper {
	
	/**
	 * 根据指定分项工程资源code删除分项工程资源。由于分项工程资源关联着其它表，�?以删除前必须进行�?查，如果有关联，则提示，并禁止删除�??
	 * @param rescode
	 * @return
	 */
    int deleteByPrimaryKey(String rescode);
    /**
     * 插入分项工程资源信息。无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int insert(SubDivWorkQuota record);
    /**
     * 插入保存分项工程资源信息。如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int insertSelective(SubDivWorkQuota record);
    /**
     * 根据code查找分项工程资源
     * @param rescode
     * @return
     */
    SubDivWorkQuota selectByPrimaryKey(String rescode);
    /**
     * 更新保存分项工程资源信息。如果某属�?�为null，则不保存�??
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SubDivWorkQuota record);
    /**
     * 更新分项工程资源信息。无论属性是否为null，都保存。如果为null，则置空�?
     * @param record
     * @return
     */
    int updateByPrimaryKey(SubDivWorkQuota record);
    
	/**
	 * 根据code查找分项工程资源
	 * @param divcode
	 * @return
	 */
    @Select({
    	"select ",
    	"res_code, sub_div_code, quota_code, item_name, item_detail_name, resources_type, res_type_level, res_detail_type, unit, used_num, loss_rate, save_excess_rate ",
    	"from sub_div_work_quota ",
    	"where sub_div_code = #{divcode,jdbcType=VARCHAR}"
    })
    @Results(id="BaseSubDivWorkQuota",value={
    	@Result(column = "res_code", property = "resCode", jdbcType = JdbcType.VARCHAR, id = true),
    	@Result(column = "sub_div_code", property = "subDivCode", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "quota_code", property = "quotaCode", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "item_name", property = "itemName", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "item_detail_name", property = "itemDetailName", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "resources_type", property = "resourcesType", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "res_type_level", property = "resTypeLevel", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "res_detail_type", property = "resDetailType", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "unit", property = "unit", jdbcType = JdbcType.VARCHAR),
    	@Result(column = "used_num", property = "usedNum", jdbcType = JdbcType.DOUBLE),
    	@Result(column = "loss_rate", property = "lossRate", jdbcType = JdbcType.DOUBLE),
    	@Result(column = "save_excess_rate", property = "saveExcessRate", jdbcType = JdbcType.DOUBLE)
    })
	List<SubDivWorkQuota> selectSubDivWorkQuotaListByDivCode(String divcode);
	/**
	 * 根据定额code查找分项工程资源
	 * @param quotacode
	 * @return
	 */
    @Select({
    	"select ",
    	"res_code, sub_div_code, quota_code, item_name, item_detail_name, resources_type, res_type_level, res_detail_type, unit, used_num, loss_rate, save_excess_rate ",
    	"from sub_div_work_quota ",
    	"where quota_code = #{quotacode,jdbcType=VARCHAR}"
    	
    })
    @ResultMap("BaseSubDivWorkQuota")
	List<SubDivWorkQuota> selectSubDivWorkQuotaListByQuotaCode(String quotacode);
	/**
	 * 根据分项工程名称查找分项工程资源
	 * @param itemname
	 * @return
	 */
    @Select({
    	"select ",
    	"res_code, sub_div_code, quota_code, item_name, item_detail_name, resources_type, res_type_level, res_detail_type, unit, used_num, loss_rate, save_excess_rate ",
    	"from sub_div_work_quota s ",
    	"where s.item_name like concat('%',#{itemname,jdbcType=VARCHAR},'%')"
    })
    @ResultMap("BaseSubDivWorkQuota")
	List<SubDivWorkQuota> selectSubDivWorkQuotaListByItemName(String itemname);
	/**
	 * 根据资源类型查找分项工程资源
	 * @param resourcestype
	 * @return
	 */
    @Select({
    	"select ",
    	"res_code, sub_div_code, quota_code, item_name, item_detail_name, resources_type, res_type_level, res_detail_type, unit, used_num, loss_rate, save_excess_rate ",
    	"from sub_div_work_quota where resources_type = #{resourcestype,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseSubDivWorkQuota")
	List<SubDivWorkQuota> selectSubDivWorkQuotaListByResType(String resourcestype);
	/**
	 * 查询�?有分项工程资�?
	 * @return
	 */
    @Select({
    	"select ",
    	"res_code, sub_div_code, quota_code, item_name, item_detail_name, resources_type, res_type_level, res_detail_type, unit, used_num, loss_rate, save_excess_rate ",
    	"from sub_div_work_quota "
    })
    @ResultMap("BaseSubDivWorkQuota")
	List<SubDivWorkQuota> selectAll();
	
}