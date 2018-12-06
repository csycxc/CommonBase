package com.banry.pscm.commonbase.service.biz;

import java.util.List;


import com.banry.pscm.commonbase.service.CommonBaseException;

/**
 * @author crj
 * @Description 分项工程资源对外接口
 * @date 2018-04-15
 * @version 1.0
 */
public interface SubDivWorkQuotaService {
	/**
	 * @Description 根据code查找分项工程资源
	 * 
	 * @param code 分项工程资源编码
	 * @return 分项工程资源
	 * @throws CommonBaseException
	 */
	public SubDivWorkQuota findSubDivWorkQuotaByCode(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据code查找分项工程资源
	 * 
	 * @param divcode 划分编码
	 * @return 分项工程资源
	 * @throws CommonBaseException
	 */
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByDivCode(String divcode) throws CommonBaseException;
	
	/**
	 * @Description 根据定额code查找分项工程资源
	 * 
	 * @param divcode 定额编码
	 * @return 分项工程资源
	 * @throws CommonBaseException
	 */
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByQuotaCode(String quotacode) throws CommonBaseException;
	
	/**
	 * @Description 根据分项工程名称查找分项工程资源
	 * 
	 * @param itemname 分项工程名称
	 * @return 分项工程资源
	 * @throws CommonBaseException
	 */
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByItemName(String itemname) throws CommonBaseException;
	
	/**
	 * @Description 根据资源类型查找分项工程资源
	 * 
	 * @param resourcestype 资源类型
	 * @return 分项工程资源
	 * @throws CommonBaseException
	 */
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByResType(String resourcestype) throws CommonBaseException;
	
	/**
	 * @Description  查询所有分项工程资源
	 * @return 所有分项工程资源
	 * @throws CommonBaseException
	 */
	public List<SubDivWorkQuota> findSubDivWorkQuotaList() throws CommonBaseException;

	/**
	 * @Description 保存分项工程资源信息。无论属性是否为null，都保存。如果为null，则置空。
	 * 
	 * @param consScheme
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveSubDivWorkQuota(SubDivWorkQuota consScheme) throws CommonBaseException;
	
	/**
	 * @Description 保存分项工程资源信息。如果某属性为null，则不保存。
	 * 
	 * @param consScheme
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveSubDivWorkQuotaSelective(SubDivWorkQuota consScheme) throws CommonBaseException;

	/**
	 * @Description 根据指定分项工程资源code删除分项工程资源。由于分项工程资源关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * @param code分项工程资源编码
	 * @return
	 * @throws CommonBaseException
	 */
	public int deleteSubDivWorkQuota(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据指定分项工程资源code，更新相应的该分项工程资源数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * @param consScheme分项工程资源
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateSubDivWorkQuota(SubDivWorkQuota consScheme)throws CommonBaseException;
	
	/**
	 * @Description 根据指定分项工程资源code，更新相应的该分项工程资源数据。如果数据为null，则不更新。
	 * 
	 * @param consScheme分项工程资源
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateSubDivWorkQuotaSelective(SubDivWorkQuota consScheme)throws CommonBaseException;
}
