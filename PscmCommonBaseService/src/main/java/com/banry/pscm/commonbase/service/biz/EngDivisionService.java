package com.banry.pscm.commonbase.service.biz;
import java.util.HashMap;
import java.util.List;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.modelPOJO.TreeNode;


/**
 * @author crj
 * @Description 工程划分服务。
 * @date 2018-04-15
 * @version 1.0
 */
public interface EngDivisionService {
	/**
	 * @Description 根据code查找工程划分
	 * 
	 * @param code 工程划分编码
	 * @return 工程划分
	 * @throws CommonBaseException
	 */
	public EngDivision findEngDivisionByCode(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据code查找工程划分及其所有的子节点构成的树
	 * @param code 工程划分编码
	 * @return 工程划分
	 * @throws CommonBaseException
	 */
	public TreeNode findEngDivisionTreeByCode(String code) throws CommonBaseException;
	
	/**
	 * @Description 根据工程划分名查找工程划分。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * @param divname 工程划分名
	 * @return 相似名称的所有工程划分
	 * @throws CommonBaseException
	 */
	public List<EngDivision> findEngDivisionByName(String divname) throws CommonBaseException;
	
	/**
	 * @Description  查询所有工程划分
	 * @return 所有工程划分
	 * @throws CommonBaseException
	 */
	public List<HashMap> findEngDivisionList() throws CommonBaseException;

	/**
	 * @Description 保存工程划分信息。无论属性是否为null，都保存。如果为null，则置空。
	 * 
	 * @param division
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveEngDivision(EngDivision division) throws CommonBaseException;
	
	/**
	 * @Description 保存工程划分信息。如果某属性为null，则不保存。
	 * 
	 * @param division
	 * @return
	 * @throws CommonBaseException
	 */
	public void saveEngDivisionSelective(EngDivision division) throws CommonBaseException;

	/**
	 * @Description 根据指定工程划分code删除工程划分。由于工程划分关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * 
	 * @param divCode实体编码
	 * @return
	 * @throws CommonBaseException
	 */
	public int deleteEngDivision(String divCode) throws CommonBaseException;
	
	/**
	 * @Description 根据指定工程划分code，更新相应的该工程划分数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * 
	 * @param division工程划分
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateEngDivision(EngDivision division)throws CommonBaseException;
	
	/**
	 * @Description 根据指定工程划分code，更新相应的该工程划分数据。如果数据为null，则不更新。
	 * 
	 * @param division工程划分
	 * @return
	 * @throws CommonBaseException
	 */
	public void updateEngDivisionSelective(EngDivision division)throws CommonBaseException;
}
