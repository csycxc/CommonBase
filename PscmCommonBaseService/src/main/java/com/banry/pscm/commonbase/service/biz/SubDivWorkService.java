package com.banry.pscm.commonbase.service.biz;

import java.util.Arrays;
import java.util.List;

import com.banry.pscm.commonbase.service.CommonBaseException;

/**
 * @author crj
 * @Description 分项工程对外接口
 * @date 2018-04-15
 * @version 1.0
 */
public interface SubDivWorkService {
	/**
	 * @Description 根据code查找分项工程
	 * 
	 * @param code 分项工程编码
	 * @return 分项工程
	 * @throws CommonBaseException
	 */
	public SubDivWork findSubDivWorkByCode(String code) throws CommonBaseException;

	/**
	 * @Description 根据分项工程名查找分项工程。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * 
	 * @param divname 分项工程名
	 * @return 相似名称的所有分项工程
	 * @throws CommonBaseException
	 */
	public List<SubDivWork> findSubDivWorkByName(String name) throws CommonBaseException;
	
	/**
	 * @Description  查询所有分项工程
	 * @return 所有分项工程
	 * @throws CommonBaseException
	 */
	public List<SubDivWork> findSubDivWorkList() throws CommonBaseException;

	/**
	 * @Description 保存分项工程信息。无论属性是否为null，都保存。如果为null，则置空。
	 * 
	 * @param subDivWork
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveSubDivWork(SubDivWork subDivWork) throws CommonBaseException;
	
	/**
	 * @Description 保存分项工程信息。如果某属性为null，则不保存。
	 * 
	 * @param subDivWork
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveSubDivWorkSelective(SubDivWork subDivWork) throws CommonBaseException;

	/**
	 * @Description 根据指定分项工程code删除分项工程。由于分项工程关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * 
	 * @param code分项工程编码
	 * @return
	 * @throws CommonBaseException
	 */
	public int deleteSubDivWork(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据指定分项工程code，更新相应的该分项工程数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * 
	 * @param subDivWork分项工程
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateSubDivWork(SubDivWork subDivWork)throws CommonBaseException;
	
	/**
	 * @Description 根据指定分项工程code，更新相应的该分项工程数据。如果数据为null，则不更新。
	 * 
	 * @param subDivWork分项工程
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateSubDivWorkSelective(SubDivWork subDivWork)throws CommonBaseException;

	/**
	 * 根据字符串数组查询，字符串都是一个个的主键subdivcode
	 * @param arr
	 * @return
	 */
	public List<SubDivWork> findSubDivWorksBySubDivCode(String s);
}
