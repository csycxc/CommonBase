package com.banry.pscm.commonbase.service.biz;

import java.util.List;

import com.banry.pscm.commonbase.service.CommonBaseException;

/**
 * @author crj
 * @Description 施工方案对外接口
 * @date 2018-04-15
 * @version 1.0
 */
public interface ItemConsSchemeService {
	/**
	 * @Description 根据code查找施工方案
	 * 
	 * @param code 施工方案编码
	 * @return 施工方案
	 * @throws CommonBaseException
	 */
	public ItemConsScheme findItemConsSchemeByCode(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据code查找施工方案
	 * 
	 * @param divcode 划分编码
	 * @return 施工方案
	 * @throws CommonBaseException
	 */
	public List<ItemConsScheme> findItemConsSchemeListByDivCode(String divcode) throws CommonBaseException;
	
	
	/**
	 * @Description 根据施工方案名查找施工方案。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * 
	 * @param divname 施工方案名
	 * @return 相似名称的所有施工方案
	 * @throws CommonBaseException
	 */
	public List<ItemConsScheme> findItemConsSchemeByName(String divname) throws CommonBaseException;
	
	/**
	 * @Description  查询所有施工方案
	 * @return 所有施工方案
	 * @throws CommonBaseException
	 */
	public List<ItemConsScheme> findItemConsSchemeList() throws CommonBaseException;

	/**
	 * @Description 保存施工方案信息。无论属性是否为null，都保存。如果为null，则置空。
	 * 
	 * @param consScheme
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveItemConsScheme(ItemConsScheme consScheme) throws CommonBaseException;
	
	/**
	 * @Description 保存施工方案信息。如果某属性为null，则不保存。
	 * 
	 * @param consScheme
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveItemConsSchemeSelective(ItemConsScheme consScheme) throws CommonBaseException;

	/**
	 * @Description 根据指定施工方案code删除施工方案。由于施工方案关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * 
	 * @param code施工方案编码
	 * @return
	 * @throws CommonBaseException
	 */
	public int deleteItemConsScheme(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据指定施工方案code，更新相应的该施工方案数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * 
	 * @param consScheme施工方案
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateItemConsScheme(ItemConsScheme consScheme)throws CommonBaseException;
	
	/**
	 * @Description 根据指定施工方案code，更新相应的该施工方案数据。如果数据为null，则不更新。
	 * 
	 * @param consScheme施工方案
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateItemConsSchemeSelective(ItemConsScheme consScheme)throws CommonBaseException;
}
