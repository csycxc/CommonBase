package com.banry.pscm.commonbase.serviceImpl.biz;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banry.pscm.commonbase.persist.dao.SubDivWorkMapper;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.SubDivWork;
import com.banry.pscm.commonbase.service.biz.SubDivWorkService;

/**
 * 分项工程对外接口
 * @author chenshiyu
 *
 */
@Service
public class SubDivWorkServiceImpl implements SubDivWorkService {

	@Autowired
	SubDivWorkMapper subDivWorkMapper;
	
	/* (non-Javadoc)根据code查找分项工程
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#findSubDivWorkByCode(java.lang.String)
	 */
	@Override
	public SubDivWork findSubDivWorkByCode(String code) throws CommonBaseException {
		return subDivWorkMapper.selectByPrimaryKey(code);
	}
	/* (non-Javadoc)根据分项工程名查找分项工程。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#findSubDivWorkByName(java.lang.String)
	 */
	@Override
	public List<SubDivWork> findSubDivWorkByName(String name) throws CommonBaseException {		
		return subDivWorkMapper.selectByName(name);
	}
	/* (non-Javadoc)查询所有分项工程
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#findSubDivWorkList()
	 */
	@Override
	public List<SubDivWork> findSubDivWorkList() throws CommonBaseException {		
		return subDivWorkMapper.selectAll();
	}
	
	
	/* (non-Javadoc)保存分项工程信息。无论属性是否为null，都保存。如果为null，则置空。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#saveSubDivWork(com.banry.pscm.commonbase.service.biz.SubDivWork)
	 */
	@Transactional
	public void saveSubDivWork(SubDivWork subDivWork) throws CommonBaseException {		
		if(subDivWork.getSubDivCode()==null || "".equals(subDivWork.getSubDivCode()))
			throw new CommonBaseException("主键subdivcode为null，保存失败！");
		SubDivWork sdw = subDivWorkMapper.selectByPrimaryKey(subDivWork.getSubDivCode());
		if(sdw != null ) 
			subDivWorkMapper.updateByPrimaryKey(subDivWork);
		else 
			subDivWorkMapper.insert(subDivWork);
	}
	/* (non-Javadoc)保存分项工程信息。如果某属性为null，则不保存。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#saveSubDivWorkSelective(com.banry.pscm.commonbase.service.biz.SubDivWork)
	 */
	@Transactional
	public void saveSubDivWorkSelective(SubDivWork subDivWork) throws CommonBaseException {
		if(subDivWork.getSubDivCode()==null || "".equals(subDivWork.getSubDivCode()))
			throw new CommonBaseException("主键subdivcode为null，保存失败！");
		SubDivWork sdw = subDivWorkMapper.selectByPrimaryKey(subDivWork.getSubDivCode());
		if(sdw != null ) 
			subDivWorkMapper.updateByPrimaryKeySelective(subDivWork);
		else 
			subDivWorkMapper.insertSelective(subDivWork);
	}
	/* (non-Javadoc)根据指定分项工程code删除分项工程。由于分项工程关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#deleteSubDivWork(java.lang.String)
	 */
	@Transactional
	public int deleteSubDivWork(String code) throws CommonBaseException {
		int i = subDivWorkMapper.deleteByPrimaryKey(code);
		return i;
	}
	/* (non-Javadoc)根据指定分项工程code，更新相应的该分项工程数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#updateSubDivWork(com.banry.pscm.commonbase.service.biz.SubDivWork)
	 */
	@Transactional
	public void updateSubDivWork(SubDivWork subDivWork) throws CommonBaseException {
		if(subDivWork.getSubDivCode()==null || "".equals(subDivWork.getSubDivCode()))
			throw new CommonBaseException("主键subdivcode为null，更新失败！");
		subDivWorkMapper.updateByPrimaryKey(subDivWork);
	}
	/* (non-Javadoc)根据指定分项工程code，更新相应的该分项工程数据。如果数据为null，则不更新。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkService#updateSubDivWorkSelective(com.banry.pscm.commonbase.service.biz.SubDivWork)
	 */
	@Transactional
	public void updateSubDivWorkSelective(SubDivWork subDivWork) throws CommonBaseException {
		if(subDivWork.getSubDivCode()==null || "".equals(subDivWork.getSubDivCode()))
			throw new CommonBaseException("主键subdivcode为null，更新失败！");
		subDivWorkMapper.updateByPrimaryKeySelective(subDivWork);
	}
	@Override
	public List<SubDivWork> findSubDivWorksBySubDivCode(String s) {
		return subDivWorkMapper.findSubDivWorksBySubDivCode(s);
	}
	

}
