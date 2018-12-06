package com.banry.pscm.commonbase.serviceImpl.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banry.pscm.commonbase.persist.dao.SubDivWorkMapper;
import com.banry.pscm.commonbase.persist.dao.SubDivWorkQuotaMapper;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.SubDivWork;
import com.banry.pscm.commonbase.service.biz.SubDivWorkQuota;
import com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService;

/**
 * @Description 分项工程资源对外接口
 * @author chenshiyu
 * 
 */
@Service
public class SubDivWorkQuotaServiceImpl implements SubDivWorkQuotaService {

	@Autowired
	SubDivWorkQuotaMapper subDivWorkQuotaMapper;
	@Autowired
	SubDivWorkMapper subDivWorkMapper;
	
	/* 根据code查找分项工程资源
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#findSubDivWorkQuotaByCode(java.lang.String)
	 */
	@Override
	public SubDivWorkQuota findSubDivWorkQuotaByCode(String code) throws CommonBaseException {
		return subDivWorkQuotaMapper.selectByPrimaryKey(code);
	}
	/* 根据code查找分项工程资源
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#findSubDivWorkQuotaListByDivCode(java.lang.String)
	 */
	@Override
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByDivCode(String divcode) throws CommonBaseException {		
		return subDivWorkQuotaMapper.selectSubDivWorkQuotaListByDivCode(divcode);
	}
	/* 根据定额code查找分项工程资源
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#findSubDivWorkQuotaListByQuotaCode(java.lang.String)
	 */
	@Override
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByQuotaCode(String quotacode) throws CommonBaseException {		
		return subDivWorkQuotaMapper.selectSubDivWorkQuotaListByQuotaCode(quotacode);
	}
	/* 根据分项工程名称查找分项工程资源
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#findSubDivWorkQuotaListByItemName(java.lang.String)
	 */
	@Override
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByItemName(String itemname) throws CommonBaseException {		
		return subDivWorkQuotaMapper.selectSubDivWorkQuotaListByItemName(itemname);
	}
	/* 根据资源类型查找分项工程资源
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#findSubDivWorkQuotaListByResType(java.lang.String)
	 */
	@Override
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByResType(String resourcestype) throws CommonBaseException {	
		return subDivWorkQuotaMapper.selectSubDivWorkQuotaListByResType(resourcestype);
	}
	/* 查询所有分项工程资源
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#findSubDivWorkQuotaList()
	 */
	@Override
	public List<SubDivWorkQuota> findSubDivWorkQuotaList() throws CommonBaseException {	
		return subDivWorkQuotaMapper.selectAll();
	}
	
	
	/* 保存分项工程资源信息。无论属性是否为null，都保存。如果为null，则置空。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#saveSubDivWorkQuota(com.banry.pscm.commonbase.service.biz.SubDivWorkQuota)
	 */
	@Transactional
	public void saveSubDivWorkQuota(SubDivWorkQuota consScheme) throws CommonBaseException {
		if(consScheme.getResCode() == null || "".equals(consScheme.getResCode()))
			throw new CommonBaseException("主键ResCode为null，保存失败！");
		if(consScheme.getSubDivCode() == null || "".equals(consScheme.getSubDivCode()))
			throw new CommonBaseException("外键SubDivCode为null，保存失败！");
		SubDivWork sdw = subDivWorkMapper.selectByPrimaryKey(consScheme.getSubDivCode());
		if(sdw != null) {
			SubDivWorkQuota sdwq = subDivWorkQuotaMapper.selectByPrimaryKey(consScheme.getResCode());
			if(sdwq != null) 
				subDivWorkQuotaMapper.updateByPrimaryKey(consScheme);
			else 
				subDivWorkQuotaMapper.insert(consScheme);
		}else
			throw new CommonBaseException("数据库中不存在引用该条外键SubDivCode的信息，保存失败！");
	}
	/* 保存分项工程资源信息。如果某属性为null，则不保存。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#saveSubDivWorkQuotaSelective(com.banry.pscm.commonbase.service.biz.SubDivWorkQuota)
	 */
	@Transactional
	public void saveSubDivWorkQuotaSelective(SubDivWorkQuota consScheme) throws CommonBaseException {		
		if(consScheme.getResCode() == null || "".equals(consScheme.getResCode()))
			throw new CommonBaseException("主键ResCode为null，保存失败！");
		//保存信息
		SubDivWorkQuota sdwq = subDivWorkQuotaMapper.selectByPrimaryKey(consScheme.getResCode());
		if(sdwq != null) {//更新    要判断是否有外键，如果有，数据库中是否包含。
			if(consScheme.getSubDivCode() != null && !"".equals(consScheme.getSubDivCode())) {
				SubDivWork sdw = subDivWorkMapper.selectByPrimaryKey(consScheme.getSubDivCode());
				if(sdw != null) 
					subDivWorkQuotaMapper.updateByPrimaryKeySelective(consScheme);
				else 
					throw new CommonBaseException("保存引用的外键SubDivCode不存在，保存失败！");
			}else 
				subDivWorkQuotaMapper.updateByPrimaryKeySelective(consScheme);
		}else {//插入
			if(consScheme.getSubDivCode() == null || "".equals(consScheme.getSubDivCode()))
				throw new CommonBaseException("添加时，外键SubDivCode为null，保存失败！");
			SubDivWork sdw = subDivWorkMapper.selectByPrimaryKey(consScheme.getSubDivCode());
			if(sdw != null) {
				subDivWorkQuotaMapper.insertSelective(consScheme);
			}else {
				throw new CommonBaseException("添加时，引用的外键SubDivCode不存在，保存失败！");
			}
		}
	}
	
	
	/* 根据指定分项工程资源code删除分项工程资源。由于分项工程资源关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#deleteSubDivWorkQuota(java.lang.String)
	 */
	@Transactional
	public int deleteSubDivWorkQuota(String code) throws CommonBaseException {		
		int i = subDivWorkQuotaMapper.deleteByPrimaryKey(code);
		return i;
	}
	
	/* 根据指定分项工程资源code，更新相应的该分项工程资源数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#updateSubDivWorkQuota(com.banry.pscm.commonbase.service.biz.SubDivWorkQuota)
	 */
	@Transactional
	public void updateSubDivWorkQuota(SubDivWorkQuota consScheme) throws CommonBaseException {		
		if(consScheme.getResCode() == null || "".equals(consScheme.getResCode()))
			throw new CommonBaseException("主键ResCode为null，更新失败！");
		if(consScheme.getSubDivCode() == null || "".equals(consScheme.getSubDivCode()))
			throw new CommonBaseException("外键SubDivCode为null，更新失败！");
		SubDivWorkQuota sdwq = subDivWorkQuotaMapper.selectByPrimaryKey(consScheme.getResCode());
		if(sdwq != null) {//更新     判断外键
			SubDivWork sdw = subDivWorkMapper.selectByPrimaryKey(consScheme.getSubDivCode());
			if(sdw != null) {
				subDivWorkQuotaMapper.updateByPrimaryKey(consScheme);
			}else
				throw new CommonBaseException("数据库中不存在引用该条外键SubDivCode的信息，更新失败！");
		}else
			throw new CommonBaseException("数据库中不存在该主键ResCode的信息，更新失败！");
	}
	/* 根据指定分项工程资源code，更新相应的该分项工程资源数据。如果数据为null，则不更新。
	 * @see com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService#updateSubDivWorkQuotaSelective(com.banry.pscm.commonbase.service.biz.SubDivWorkQuota)
	 */
	@Transactional
	public void updateSubDivWorkQuotaSelective(SubDivWorkQuota consScheme) throws CommonBaseException {		
		if(consScheme.getResCode() == null || "".equals(consScheme.getResCode()))
			throw new CommonBaseException("主键ResCode为null，更新失败！");
		
		SubDivWorkQuota sdwq = subDivWorkQuotaMapper.selectByPrimaryKey(consScheme.getResCode());
		if(sdwq != null) {//更新     判断外键
			if(consScheme.getSubDivCode() != null) {
				SubDivWork sdw = subDivWorkMapper.selectByPrimaryKey(consScheme.getSubDivCode());
				if(sdw != null) 
					subDivWorkQuotaMapper.updateByPrimaryKeySelective(consScheme);	
				else 
					throw new CommonBaseException("数据库中不存在引用该条外键SubDivCode的信息，更新失败！");
			}else 
				subDivWorkQuotaMapper.updateByPrimaryKeySelective(consScheme);	
		}else 
			throw new CommonBaseException("数据库中不存在该主键ResCode的信息，更新失败！");
	}

	
}
