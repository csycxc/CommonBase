package com.banry.pscm.commonbase.serviceImpl.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banry.pscm.commonbase.persist.dao.EngDivisionMapper;
import com.banry.pscm.commonbase.persist.dao.HiddenTroubleMapper;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.EngDivision;
import com.banry.pscm.commonbase.service.biz.HiddenTrouble;
import com.banry.pscm.commonbase.service.biz.HiddenTroubleService;

/**
 * 隐患对外接口
 * @author chenshiyu
 */
@Service
public class HiddenTroubleServiceImpl implements HiddenTroubleService {

	@Autowired
	HiddenTroubleMapper hiddenTroubleMapper;
	@Autowired
	EngDivisionMapper engDivisionMapper;
	
	/* 根据code查找隐患
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#findHiddenTroubleByCode(java.lang.String)
	 */
	@Override
	public HiddenTrouble findHiddenTroubleByCode(String code) throws CommonBaseException {
		return hiddenTroubleMapper.selectByPrimaryKey(code);//code是troublecode，主键
	}
	/* 根据code查找隐患  divitemcode
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#findHiddenTroubleListByDivCode(java.lang.String)
	 */
	@Override
	public List<HiddenTrouble> findHiddenTroubleListByDivCode(String divcode) throws CommonBaseException {
		return hiddenTroubleMapper.selectHiddenTroubleListByDivCode(divcode);
	}
	/* 根据隐患名查找隐患。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#findHiddenTroubleByName(java.lang.String)
	 */
	@Override
	public List<HiddenTrouble> findHiddenTroubleByName(String divname) throws CommonBaseException {
		return hiddenTroubleMapper.findHiddenTroubleByName(divname);
	}
	/* 查询所有隐患
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#findHiddenTroubleList()
	 */
	@Override
	public List<HiddenTrouble> findHiddenTroubleList() throws CommonBaseException {
		return hiddenTroubleMapper.selectAll();
	}
	
	
	/* 保存隐患信息。无论属性是否为null，都保存。如果为null，则置空。
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#saveHiddenTrouble(com.banry.pscm.commonbase.service.biz.HiddenTrouble)
	 */
	@Transactional
	public void saveHiddenTrouble(HiddenTrouble trouble) throws CommonBaseException {
		if(trouble.getTroubleCode() == null || "".equals(trouble.getTroubleCode()))
			throw new CommonBaseException("主键troublecode为null，保存失败！");
		if(trouble.getDivItemCode() == null || "".equals(trouble.getDivItemCode())) 
			throw new CommonBaseException("外键divitemcode为null，保存失败！");
		
		EngDivision ed = engDivisionMapper.selectByPrimaryKey(trouble.getDivItemCode());
		if(ed != null) {
			//添加冗余字段属性值
			trouble.setDivLevel(ed.getDivLevel());
			//保存隐患信息
			HiddenTrouble ht = hiddenTroubleMapper.selectByPrimaryKey(trouble.getTroubleCode());
			if(ht != null) 
				hiddenTroubleMapper.updateByPrimaryKey(trouble);
			else 
				hiddenTroubleMapper.insert(trouble);
		}else
			throw new CommonBaseException("保存引用的Divitemcode不存在，保存失败！");
	}
	/* 保存隐患信息。如果某属性为null，则不保存。
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#saveHiddenTroubleSelective(com.banry.pscm.commonbase.service.biz.HiddenTrouble)
	 */
	@Transactional
	public void saveHiddenTroubleSelective(HiddenTrouble trouble) throws CommonBaseException {
		if(trouble.getTroubleCode()==null || "".equals(trouble.getTroubleCode()))
			throw new CommonBaseException("主键troublecode为null，保存失败！");
		//保存隐患信息：
		HiddenTrouble ht = hiddenTroubleMapper.selectByPrimaryKey(trouble.getTroubleCode());
		if(ht != null) {//更新
			if(trouble.getDivItemCode() != null && !"".equals(trouble.getDivItemCode())) {
				EngDivision ed = engDivisionMapper.selectByPrimaryKey(trouble.getDivItemCode());
				if(ed != null) {
					trouble.setDivLevel(ed.getDivLevel());
					hiddenTroubleMapper.updateByPrimaryKeySelective(trouble);
				}else
					throw new CommonBaseException("引用的外键DivItemCode不存在，保存失败！");
			}else {
				trouble.setDivLevel(null);
				hiddenTroubleMapper.updateByPrimaryKeySelective(trouble);
			}
		}else {//插入
			if(trouble.getDivItemCode() == null || "".equals(trouble.getDivItemCode()))
				throw new CommonBaseException("插入时引用的外键DivItemCode为null，保存失败！");
			EngDivision ed = engDivisionMapper.selectByPrimaryKey(trouble.getDivItemCode());
			if(ed != null) {
				trouble.setDivLevel(ed.getDivLevel());
				hiddenTroubleMapper.insertSelective(trouble);
			}else
				throw new CommonBaseException("插入时引用的外键DivItemCode不存在，添加失败！");
		}
	}
	
	/* 根据指定隐患code删除隐患。由于隐患关联着其它表，所以删除前必须进行检查，如果有关联，则提示，并禁止删除。
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#deleteHiddenTrouble(java.lang.String)
	 */
	@Transactional
	public int deleteHiddenTrouble(String code) throws CommonBaseException {
		int i = hiddenTroubleMapper.deleteByPrimaryKey(code);
		return i;
	}
	/* 根据指定隐患code，更新相应的该隐患数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#updateHiddenTrouble(com.banry.pscm.commonbase.service.biz.HiddenTrouble)
	 */
	@Transactional
	public void updateHiddenTrouble(HiddenTrouble trouble) throws CommonBaseException {
		if(trouble.getTroubleCode() == null || "".equals(trouble.getTroubleCode()))
			throw new CommonBaseException("主键troubleCode为null，更新失败！");
		if(trouble.getDivItemCode() == null || "".equals(trouble.getDivItemCode()))
			throw new CommonBaseException("外键DivItemCode为null，更新失败！");
		
		HiddenTrouble ht = hiddenTroubleMapper.selectByPrimaryKey(trouble.getTroubleCode());
		if(ht != null) {
			EngDivision ed = engDivisionMapper.selectByPrimaryKey(trouble.getDivItemCode());
			if(ed != null) {
				trouble.setDivLevel(ed.getDivLevel());
				hiddenTroubleMapper.updateByPrimaryKey(trouble);
			}else
				throw new CommonBaseException("没有该隐患的外键，更新失败！");
		}else 
			throw new CommonBaseException("数据库中没有该条隐患的信息，更新失败！");
	}
	/* 根据指定隐患code，更新相应的该隐患数据。如果数据为null，则不更新。
	 * @see com.banry.pscm.commonbase.service.biz.HiddenTroubleService#updateHiddenTroubleSelective(com.banry.pscm.commonbase.service.biz.HiddenTrouble)
	 */
	@Transactional
	public void updateHiddenTroubleSelective(HiddenTrouble trouble) throws CommonBaseException {
		if(trouble.getTroubleCode() == null || "".equals(trouble.getTroubleCode()))
			throw new CommonBaseException("主键troubleCode为null，更新失败！");
		//保存隐患信息
		HiddenTrouble ht = hiddenTroubleMapper.selectByPrimaryKey(trouble.getTroubleCode());
		if(ht != null) {//数据库中有该主键对应的隐患，可以更新
			if(trouble.getDivItemCode() != null && !"".equals(trouble.getDivItemCode())) {
				EngDivision ed = engDivisionMapper.selectByPrimaryKey(trouble.getDivItemCode());
				if(ed != null) {
					trouble.setDivLevel(ed.getDivLevel());
					hiddenTroubleMapper.updateByPrimaryKeySelective(trouble);
				}else 
					throw new CommonBaseException("引用的外键Divitemcode不存在，更新失败！");
			}else {
				trouble.setDivLevel(null);
				hiddenTroubleMapper.updateByPrimaryKeySelective(trouble);
			}
		}else
			throw new CommonBaseException("数据库中没有该条信息，更新失败。");
	}

}
