package com.banry.pscm.commonbase.serviceImpl.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banry.pscm.commonbase.persist.dao.EngDivisionMapper;
import com.banry.pscm.commonbase.persist.dao.HazardsMapper;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.EngDivision;
import com.banry.pscm.commonbase.service.biz.Hazards;
import com.banry.pscm.commonbase.service.biz.HazardsService;

/**
 * 危险源对外接口
 * @author chenshiyu
 */
@Service
public class HazardsServiceImpl implements HazardsService {

	@Autowired
	HazardsMapper hazardsMapper;
	@Autowired
	EngDivisionMapper engDivisionMapper;
	
	/* 根据code查找危险源
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#findHazardsByCode(java.lang.String)
	 */
	@Override
	public Hazards findHazardsByCode(String code) throws CommonBaseException {
		return hazardsMapper.selectByPrimaryKey(code);
	}
	/* 根据divcode查找 n个 危险源
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#findHazardsListByDivCode(java.lang.String)
	 */
	@Override
	public List<Hazards> findHazardsListByDivCode(String divcode) throws CommonBaseException {
		return hazardsMapper.selectHazardsListByDivCode(divcode);
	}
	/* 根据危险源名查找危险源。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#findHazardsByName(java.lang.String)
	 */
	@Override
	public List<Hazards> findHazardsByName(String divname) throws CommonBaseException {
		return hazardsMapper.findHazardsByName(divname);
	}
	/* 查询所有危险源
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#findHazardsList()
	 */
	@Override
	public List<Hazards> findHazardsList() throws CommonBaseException {
		return hazardsMapper.selectAll();
	}
	
	
	/* 保存危险源信息。无论属性是否为null，都保存。如果为null，则置空。
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#saveHazards(com.banry.pscm.commonbase.service.biz.Hazards)
	 */
	@Transactional
	public void saveHazards(Hazards hazard) throws CommonBaseException {
		if(hazard.getHazardsCode()==null || "".equals(hazard.getHazardsCode())) 
			throw new CommonBaseException("主键HazardsCode为null，保存失败！");
		if(hazard.getDivItemCode()==null || "".equals(hazard.getDivItemCode())) 
			throw new CommonBaseException("外键DivitemCode为null，保存失败！");
		
		EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(hazard.getDivItemCode());
		if(engDivision != null) {
			//添加冗余字段属性值
			hazard.setDivLevel(engDivision.getDivLevel());
			//保存危险源信息。
			Hazards hazards = hazardsMapper.selectByPrimaryKey(hazard.getHazardsCode());
			if(hazards != null) 
				hazardsMapper.updateByPrimaryKey(hazard);
			else 
				hazardsMapper.insert(hazard);
		}else
			throw new CommonBaseException("引用的外键Divitemcode不存在，保存失败！");
	}
	/* 保存危险源信息。如果某属性为null，则不保存。
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#saveHazardsSelective(com.banry.pscm.commonbase.service.biz.Hazards)
	 */
	@Transactional
	public void saveHazardsSelective(Hazards hazard) throws CommonBaseException {
		if(hazard.getHazardsCode()==null || "".equals(hazard.getHazardsCode())) 
			throw new CommonBaseException("主键HazardsCode为null，保存失败！");
		//保存危险源信息。
		Hazards hazards = hazardsMapper.selectByPrimaryKey(hazard.getHazardsCode());
		if(hazards != null) {//更新
			if(hazard.getDivItemCode()!=null && !"".equals(hazard.getDivItemCode())) {//当传入了divitemcode时
				EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(hazard.getDivItemCode());
				if(engDivision != null) {
					hazard.setDivLevel(engDivision.getDivLevel());
					hazardsMapper.updateByPrimaryKeySelective(hazard);
				}else 
					throw new CommonBaseException("引用的外键Divitemcode不存在，保存失败！");
			}else {//当没传入divitemcode时
				hazard.setDivLevel(null);
				hazardsMapper.updateByPrimaryKeySelective(hazard);				
			}
		}else {//插入
			if(hazard.getDivItemCode() == null || "".equals(hazard.getDivItemCode())) 
				throw new CommonBaseException("插入时外键DivitemCode为null，保存失败！");
			EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(hazard.getDivItemCode());
			if(engDivision != null) {
				hazard.setDivLevel(engDivision.getDivLevel());
				hazardsMapper.insertSelective(hazard);				
			}else 
				throw new CommonBaseException("引用的外键Divitemcode不存在，保存失败！");
		}
	}
	
	/* 根据指定危险源code删除危险源。由于危险源关联着其它表，所以删除前必须进行检查，如果有关联，则提示，并禁止删除。//返回值 :0 未删除;1 删除
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#deleteHazards(java.lang.String)
	 */
	@Transactional
	public int deleteHazards(String code) throws CommonBaseException {
		int i = hazardsMapper.deleteByPrimaryKey(code);
		return i;
	}
	/* 根据指定危险源code，更新相应的该危险源数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#updateHazards(com.banry.pscm.commonbase.service.biz.Hazards)
	 */
	@Transactional
	public void updateHazards(Hazards hazard) throws CommonBaseException {
		if(hazard.getHazardsCode()==null || "".equals(hazard.getHazardsCode())) 
			throw new CommonBaseException("主键HazardsCode为null，更新失败！");
		if(hazard.getDivItemCode()==null || "".equals(hazard.getDivItemCode())) 
			throw new CommonBaseException("外键DivitemCode为null，更新失败！");
		
		Hazards hazards = hazardsMapper.selectByPrimaryKey(hazard.getHazardsCode());
		if(hazards != null) {
			EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(hazard.getDivItemCode());
			if(engDivision != null) {
				hazard.setDivLevel(engDivision.getDivLevel());
				hazardsMapper.updateByPrimaryKey(hazard);
			}else 
				throw new CommonBaseException("引用的外键Divitemcode不存在，更新失败！");
		}else
			throw new CommonBaseException("数据库中没有该条信息，更新失败。");
	}
	/* 根据指定危险源code，更新相应的该危险源数据。如果数据为null，则不更新。
	 * @see com.banry.pscm.commonbase.service.biz.HazardsService#updateHazardsSelective(com.banry.pscm.commonbase.service.biz.Hazards)
	 */
	@Transactional
	public void updateHazardsSelective(Hazards hazard) throws CommonBaseException {
		if(hazard.getHazardsCode()==null || "".equals(hazard.getHazardsCode()))
			throw new CommonBaseException("主键HazardsCode为null，更新失败！");
		//保存危险源信息。
		Hazards hazards = hazardsMapper.selectByPrimaryKey(hazard.getHazardsCode());
		if(hazards != null) {//如果有该危险源信息,update
			if(hazard.getDivItemCode()!=null && !"".equals(hazard.getDivItemCode())) {//如果更新的时候传入了divitemcode
				EngDivision engDivision = engDivisionMapper.selectByPrimaryKey(hazard.getDivItemCode());
				if(engDivision != null) {
					hazard.setDivLevel(engDivision.getDivLevel());
					hazardsMapper.updateByPrimaryKeySelective(hazard);
				}else 
					throw new CommonBaseException("引用的外键Divitemcode不存在，更新失败！");
			}else {
				hazard.setDivLevel(null);
				hazardsMapper.updateByPrimaryKeySelective(hazard);
			}
		}else 
			throw new CommonBaseException("数据库中没有该条信息，更新失败。");
	}

}
