package com.banry.pscm.commonbase.service.biz;

import java.util.List;

import com.banry.pscm.commonbase.service.CommonBaseException;


/**
 * @author crj
 * @Description 危险源对外接口
 * @date 2018-04-15
 * @version 1.0
 */
public interface HazardsService {
	/**
	 * @Description 根据code查找危险源
	 * 
	 * @param code 危险源编码
	 * @return 危险源
	 * @throws CommonBaseException
	 */
	public Hazards findHazardsByCode(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据code查找 n个 危险源
	 * 
	 * @param divcode 划分编码
	 * @return 危险源
	 * @throws CommonBaseException
	 */
	public List<Hazards> findHazardsListByDivCode(String divcode) throws CommonBaseException;
	
	
	/**
	 * @Description 根据危险源名查找危险源。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * 
	 * @param divname 危险源名
	 * @return 相似名称的所有危险源
	 * @throws CommonBaseException
	 */
	public List<Hazards> findHazardsByName(String divname) throws CommonBaseException;
	
	/**
	 * @Description  查询所有危险源
	 * @return 所有危险源
	 * @throws CommonBaseException
	 */
	public List<Hazards> findHazardsList() throws CommonBaseException;

	/**
	 * @Description 保存危险源信息。无论属性是否为null，都保存。如果为null，则置空。
	 * 
	 * @param hazard
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveHazards(Hazards hazard) throws CommonBaseException;
	
	/**
	 * @Description 保存危险源信息。如果某属性为null，则不保存。
	 * 
	 * @param hazard
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveHazardsSelective(Hazards hazard) throws CommonBaseException;

	/**
	 * @Description 根据指定危险源code删除危险源。由于危险源关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * 
	 * @param divCode危险编码
	 * @return
	 * @throws CommonBaseException
	 */
	public int deleteHazards(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据指定危险源code，更新相应的该危险源数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * 
	 * @param hazard危险源
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateHazards(Hazards hazard)throws CommonBaseException;
	
	/**
	 * @Description 根据指定危险源code，更新相应的该危险源数据。如果数据为null，则不更新。
	 * 
	 * @param hazard危险源
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateHazardsSelective(Hazards hazard)throws CommonBaseException;
}
