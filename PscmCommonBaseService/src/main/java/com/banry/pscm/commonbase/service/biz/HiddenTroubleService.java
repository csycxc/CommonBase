package com.banry.pscm.commonbase.service.biz;

import java.util.List;

import com.banry.pscm.commonbase.service.CommonBaseException;

/**
 * @author crj
 * @Description 隐患对外接口
 * @date 2018-04-15
 * @version 1.0
 */
public interface HiddenTroubleService {
	/**
	 * @Description 根据code查找隐患
	 * 
	 * @param code 隐患编码
	 * @return 隐患
	 * @throws CommonBaseException
	 */
	public HiddenTrouble findHiddenTroubleByCode(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据code查找隐患
	 * 
	 * @param divcode 划分编码
	 * @return 隐患
	 * @throws CommonBaseException
	 */
	public List<HiddenTrouble> findHiddenTroubleListByDivCode(String divcode) throws CommonBaseException;
	
	
	/**
	 * @Description 根据隐患名查找隐患。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * 
	 * @param divname 隐患名
	 * @return 相似名称的所有隐患
	 * @throws CommonBaseException
	 */
	public List<HiddenTrouble> findHiddenTroubleByName(String divname) throws CommonBaseException;
	
	/**
	 * @Description  查询所有隐患
	 * @return 所有隐患
	 * @throws CommonBaseException
	 */
	public List<HiddenTrouble> findHiddenTroubleList() throws CommonBaseException;

	/**
	 * @Description 保存隐患信息。无论属性是否为null，都保存。如果为null，则置空。
	 * 
	 * @param trouble
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveHiddenTrouble(HiddenTrouble trouble) throws CommonBaseException;
	
	/**
	 * @Description 保存隐患信息。如果某属性为null，则不保存。
	 * 
	 * @param trouble
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveHiddenTroubleSelective(HiddenTrouble trouble) throws CommonBaseException;

	/**
	 * @Description 根据指定隐患code删除隐患。由于隐患关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * 
	 * @param code隐患编码
	 * @return
	 * @throws CommonBaseException
	 */
	public int deleteHiddenTrouble(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据指定隐患code，更新相应的该隐患数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * 
	 * @param trouble隐患
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateHiddenTrouble(HiddenTrouble trouble)throws CommonBaseException;
	
	/**
	 * @Description 根据指定隐患code，更新相应的该隐患数据。如果数据为null，则不更新。
	 * 
	 * @param trouble隐患
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateHiddenTroubleSelective(HiddenTrouble trouble)throws CommonBaseException;
}
