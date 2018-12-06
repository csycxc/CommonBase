package com.banry.pscm.commonbase.serviceImpl.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banry.pscm.commonbase.persist.dao.EngDivisionMapper;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.EngDivision;
import com.banry.pscm.commonbase.service.biz.EngDivisionService;
import com.banry.pscm.commonbase.service.modelPOJO.TreeNode;

/**
 * 工程划分服务。
 * @author chenshiyu
 *
 */
@Service
public class EngDivisionServiceImpl implements EngDivisionService {
	
	@Autowired
	EngDivisionMapper engDivisionMapper;
	
	/* 根据code查找工程划分
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#findEngDivisionByCode(java.lang.String)
	 */
	public EngDivision findEngDivisionByCode(String code) throws CommonBaseException {
		return engDivisionMapper.selectByPrimaryKey(code);
	}
	/* 根据code查找工程划分及其所有的子节点构成的树
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#findEngDivisionTreeByCode(java.lang.String)
	 */
	public TreeNode findEngDivisionTreeByCode(String code) throws CommonBaseException {
		
		//List<EngDivision> edList = engDivisionMapper.findEngDivisionTreeByCode(code);//查询出符合条件的划分,有问题，不能用。
		List<EngDivision> edList = engDivisionMapper.selectAll();//查出所有
		List<String> levelLst = engDivisionMapper.selectDivLevel();//查询出所有的划分级别，降序排列
		String leafLevel = "0";//叶子节点的划分级别
		//String leafUpLevel = "0";//叶子节点的上一节划分级别
		if (levelLst.size() > 0) {
			for (int i = 0; i < levelLst.size(); i++) {
				if (i == 0) 
					leafLevel = levelLst.get(i);
				//else if (i == 1) 
					//leafUpLevel = levelLst.get(i);
				else 
					break;
			}
		}
		TreeNode tree = new TreeNode();
		if(edList.size()>0) {
			for (EngDivision ed : edList) {
				if(ed.getDivItemCode().equals(code)) {
					tree.setId(ed.getDivItemCode());
					tree.setpId(ed.getParentDivItemCode());
					tree.setName(ed.getDivName());
					tree.setSkill(ed.getSkill());
					tree.setDivlevel(ed.getDivLevel());
					if (leafLevel.equals(ed.getDivLevel())) {
						tree.setOpen(true);
						tree.setIsLeaf("Y");
						break;
					} else {
						tree.setOpen(false);
						tree.setIsLeaf("N");
						tree.setChildren(getTreeNodeList(edList,leafLevel,ed.getDivItemCode()));
					}
				}
			}
		}
		return tree;
	}
	
	/*递归——————为findEngDivisionTreeByCode实现写的方法。*/
	public List<TreeNode> getTreeNodeList(List<EngDivision> edList,String leafLevel,String divitemcode){
		List<TreeNode> list = new ArrayList<TreeNode>();
		if(edList.size()>0) {
			for(EngDivision ed: edList){
	            //遍历出父id等于参数的id，add进子节点集合  
	            if(divitemcode.equals(ed.getParentDivItemCode())){  
	                //递归遍历下一级  
	            	TreeNode tree = new TreeNode();
	            	tree.setId(ed.getDivItemCode());
					tree.setpId(ed.getParentDivItemCode());
					tree.setName(ed.getDivName());
					tree.setSkill(ed.getSkill());
					tree.setDivlevel(ed.getDivLevel());
					if (leafLevel.equals(ed.getDivLevel())) {
						tree.setOpen(true);
						tree.setIsLeaf("Y");
						list.add(tree);
					} else {
						tree.setOpen(false);
						tree.setIsLeaf("N");
						tree.setChildren(getTreeNodeList(edList,leafLevel,ed.getDivItemCode()));
						list.add(tree);
					}
	            }
	        }
		}
		return list;
	}
	
	/* 根据工程划分名查找工程划分。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#findEngDivisionByName(java.lang.String)
	 */
	public List<EngDivision> findEngDivisionByName(String divname) throws CommonBaseException {
		return engDivisionMapper.selectByDivName(divname);
	}
	/* 查询所有工程划分
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#findEngDivisionList()
	 */
	public List<HashMap> findEngDivisionList() throws CommonBaseException {
		return engDivisionMapper.getEngdivisionListForZTree();
	}
	
	
	/* 保存工程划分信息。无论属性是否为null，都保存。如果为null，则置空。
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#saveEngDivision(com.banry.pscm.commonbase.service.biz.EngDivision)
	 */
	@Transactional
	public void saveEngDivision(EngDivision division) throws CommonBaseException {
		if(division.getDivItemCode() == null || "".equals(division.getDivItemCode())) 
			throw new CommonBaseException("主键DivItemCode为null，保存失败！");
		
		//判断插入的非空父id是否在主键id中包含
		if(division.getParentDivItemCode() != null && !"".equals(division.getParentDivItemCode())) {
			EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(division.getParentDivItemCode());
			if(engDivision != null) {//可以保存
				EngDivision eng = engDivisionMapper.selectByPrimaryKey(division.getDivItemCode());
				if(eng != null) 
					engDivisionMapper.updateByPrimaryKey(division);
				else 
					engDivisionMapper.insert(division);
			}else
				throw new CommonBaseException("数据库中不存在id为ParentDivItemCode的项，保存失败！");
		}else {
			EngDivision eng = engDivisionMapper.selectByPrimaryKey(division.getDivItemCode());
			if(eng != null) 
				engDivisionMapper.updateByPrimaryKey(division);
			else 
				engDivisionMapper.insert(division);
		}
	}
	/* 保存工程划分信息。如果某属性为null，则不保存。
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#saveEngDivisionSelective(com.banry.pscm.commonbase.service.biz.EngDivision)
	 */
	@Transactional
	public void saveEngDivisionSelective(EngDivision division) throws CommonBaseException {
		if(division.getDivItemCode() == null || "".equals(division.getDivItemCode())) 
			throw new CommonBaseException("主键DivItemCode为null，保存失败！");
		//判断插入的非空父id是否在主键id中包含
		if(division.getParentDivItemCode() != null && !"".equals(division.getParentDivItemCode())) {
			EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(division.getParentDivItemCode());
			if(engDivision != null) {//可以保存
				EngDivision eng = engDivisionMapper.selectByPrimaryKey(division.getDivItemCode());
				if(eng != null) 
					engDivisionMapper.updateByPrimaryKeySelective(division);
				else 
					engDivisionMapper.insertSelective(division);
			}else 
				throw new CommonBaseException("数据库中不存在id为ParentDivItemCode的项，保存失败！");
		}else{
			EngDivision eng = engDivisionMapper.selectByPrimaryKey(division.getDivItemCode());
			if(eng != null) 
				engDivisionMapper.updateByPrimaryKeySelective(division);
			else 
				engDivisionMapper.insertSelective(division);
		}
	}
	/* 根据指定工程划分code删除工程划分。由于工程划分关联着其它表，所以删除前必须进行检查，如果有关联，则提示，并禁止删除。返回值为0为有关联，禁止删除；返回1为无关联，直接删除。
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#deleteEngDivision(java.lang.String)
	 */
	@Transactional
	public int deleteEngDivision(String divCode) throws CommonBaseException {
		int i = engDivisionMapper.deleteByPrimaryKey(divCode);
		return i;
	}
	/* 根据指定工程划分code，更新相应的该工程划分数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#updateEngDivision(com.banry.pscm.commonbase.service.biz.EngDivision)
	 */
	@Transactional
	public void updateEngDivision(EngDivision division) throws CommonBaseException {
		if(division.getDivItemCode() == null || "".equals(division.getDivItemCode())) 
			throw new CommonBaseException("主键DivItemCode为null，更新失败！");
		
		//判断更新的非空父id是否在主键id中包含
		if(division.getParentDivItemCode() != null && !"".equals(division.getParentDivItemCode())) {
			EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(division.getParentDivItemCode());
			if(engDivision != null) 
				engDivisionMapper.updateByPrimaryKey(division);
			else 
				throw new CommonBaseException("数据库中不存在id为ParentDivItemCode的项，更新失败！");
		}else 
			engDivisionMapper.updateByPrimaryKey(division);
	}
	/* 根据指定工程划分code，更新相应的该工程划分数据。如果数据为null，则不更新。
	 * @see com.banry.pscm.commonbase.service.biz.EngDivisionService#updateEngDivisionSelective(com.banry.pscm.commonbase.service.biz.EngDivision)
	 */
	@Transactional
	public void updateEngDivisionSelective(EngDivision division) throws CommonBaseException {
		if(division.getDivItemCode() == null || "".equals(division.getDivItemCode())) 
			throw new CommonBaseException("主键DivItemCode为null，更新失败！");
		
		//判断更新的父id是否在主键id中包含
		if(division.getParentDivItemCode() != null && !"".equals(division.getParentDivItemCode())) {
			EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(division.getParentDivItemCode());
			if(engDivision != null) 
				engDivisionMapper.updateByPrimaryKeySelective(division);
			else 
				throw new CommonBaseException("数据库中不存在id为ParentDivItemCode的项，更新失败！");
		}else 
			engDivisionMapper.updateByPrimaryKeySelective(division);
	}
	
	

}
