package com.banry.pscm.commonbase.serviceImpl.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banry.pscm.commonbase.persist.dao.EngDivisionMapper;
import com.banry.pscm.commonbase.persist.dao.ItemConsSchemeMapper;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.EngDivision;
import com.banry.pscm.commonbase.service.biz.ItemConsScheme;
import com.banry.pscm.commonbase.service.biz.ItemConsSchemeService;

/**
 * 施工方案对外接口
 * @author chenshiyu
 */
@Service
public class ItemConsSchemeServiceImpl implements ItemConsSchemeService {

	@Autowired
	ItemConsSchemeMapper itemConsSchemeMapper;
	@Autowired
	EngDivisionMapper engDivisionMapper;
	
	/* 根据code查找施工方案
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#findItemConsSchemeByCode(java.lang.String)
	 */
	@Override
	public ItemConsScheme findItemConsSchemeByCode(String code) throws CommonBaseException {
		return itemConsSchemeMapper.selectByPrimaryKey(code);
	}
	/* 根据code查找施工方案
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#findItemConsSchemeListByDivCode(java.lang.String)
	 */
	@Override
	public List<ItemConsScheme> findItemConsSchemeListByDivCode(String divcode) throws CommonBaseException {
		return itemConsSchemeMapper.selectItemConsSchemeListByDivCode(divcode);
	}
	/* 根据施工方案名查找施工方案。这里要实现模糊查找，在实现的sql语句中，要用包括含关系，且名称里取出关键字包含
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#findItemConsSchemeByName(java.lang.String)
	 */
	@Override
	public List<ItemConsScheme> findItemConsSchemeByName(String divname) throws CommonBaseException {
		return itemConsSchemeMapper.findItemConsSchemeByName(divname);
	}
	/* 查询所有施工方案
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#findItemConsSchemeList()
	 */
	@Override
	public List<ItemConsScheme> findItemConsSchemeList() throws CommonBaseException {
		return itemConsSchemeMapper.selectAll();
	}
	
	
	/* 保存施工方案信息。无论属性是否为null，都保存。如果为null，则置空。
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#saveItemConsScheme(com.banry.pscm.commonbase.service.biz.ItemConsScheme)
	 */
	@Transactional
	public void saveItemConsScheme(ItemConsScheme consScheme) throws CommonBaseException {
		if(consScheme.getSchemeCode()==null || "".equals(consScheme.getSchemeCode()))
			throw new CommonBaseException("主键SchemeCode为null，保存失败！");
		if(consScheme.getDivItemCode()==null || "".equals(consScheme.getDivItemCode()))
			throw new CommonBaseException("外键DivItemCode为null，保存失败！");
		
		EngDivision ed = engDivisionMapper.selectByPrimaryKey(consScheme.getDivItemCode());
		if(ed != null) {//外键正确
			//添加冗余字段属性值
			consScheme.setDivLevel(ed.getDivLevel());
			//保存施工方案信息
			ItemConsScheme ics = itemConsSchemeMapper.selectByPrimaryKey(consScheme.getSchemeCode());
			if(ics != null) 
				itemConsSchemeMapper.updateByPrimaryKey(consScheme);
			else 
				itemConsSchemeMapper.insert(consScheme);
		}else
			throw new CommonBaseException("保存引用的Divitemcode不存在，保存失败！");
	}
	/* 保存施工方案信息。如果某属性为null，则不保存。
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#saveItemConsSchemeSelective(com.banry.pscm.commonbase.service.biz.ItemConsScheme)
	 */
	@Transactional
	public void saveItemConsSchemeSelective(ItemConsScheme consScheme) throws CommonBaseException {	
		if(consScheme.getSchemeCode() == null || "".equals(consScheme.getSchemeCode()))
			throw new CommonBaseException("主键SchemeCode为null，保存失败！");
		//保存施工方案信息
		ItemConsScheme ics = itemConsSchemeMapper.selectByPrimaryKey(consScheme.getSchemeCode());
		if(ics != null) {//更新
			if(consScheme.getDivItemCode()!=null && !"".equals(consScheme.getDivItemCode())) {//有外键
				EngDivision ed = engDivisionMapper.selectByPrimaryKey(consScheme.getDivItemCode());
				if(ed != null) {//外键有效
					consScheme.setDivLevel(ed.getDivLevel());
					itemConsSchemeMapper.updateByPrimaryKeySelective(consScheme);
				}else
					throw new CommonBaseException("保存引用的DivItemCode不存在，保存失败！");
			}else {//没外键更新
				consScheme.setDivLevel(null);
				itemConsSchemeMapper.updateByPrimaryKeySelective(consScheme);
			}
		}else {//插入
			if(consScheme.getDivItemCode() == null || "".equals(consScheme.getDivItemCode())) 
				throw new CommonBaseException("插入时外键DivItemCode不能为null，保存失败！");
			EngDivision ed = engDivisionMapper.selectByPrimaryKey(consScheme.getDivItemCode());
			if(ed != null) {
				consScheme.setDivLevel(ed.getDivLevel());
				itemConsSchemeMapper.insertSelective(consScheme);
			}else
				throw new CommonBaseException("插入时引用的DivItemCode不存在，保存失败！");
		}
	}
	/* 根据指定施工方案code删除施工方案。由于施工方案关联着其它表，所以删除前必须进行检查，
	 * 如果有关联，则提示，并禁止删除。
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#deleteItemConsScheme(java.lang.String)
	 */
	@Transactional
	public int deleteItemConsScheme(String code) throws CommonBaseException {	
		int i = itemConsSchemeMapper.deleteByPrimaryKey(code);
		return i;
	}
	/* 根据指定施工方案code，更新相应的该施工方案数据。如果数据为null，则同步更新，这样就将原来的数据置空。
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#updateItemConsScheme(com.banry.pscm.commonbase.service.biz.ItemConsScheme)
	 */
	@Transactional
	public void updateItemConsScheme(ItemConsScheme consScheme) throws CommonBaseException {	
		if(consScheme.getSchemeCode()==null || "".equals(consScheme.getSchemeCode()))
			throw new CommonBaseException("主键SchemeCode为null，更新失败！");
		if(consScheme.getDivItemCode()==null || "".equals(consScheme.getDivItemCode()))
			throw new CommonBaseException("外键DivItemCode为null，更新失败！");
		ItemConsScheme ics = itemConsSchemeMapper.selectByPrimaryKey(consScheme.getSchemeCode());
		if(ics != null) {
			EngDivision ed = engDivisionMapper.selectByPrimaryKey(consScheme.getDivItemCode());
			if(ed != null) {
				consScheme.setDivLevel(ed.getDivLevel());
				itemConsSchemeMapper.updateByPrimaryKey(consScheme);
			}else 
				throw new CommonBaseException("引用的外键DivItemCode不存在，更新失败！");
		}else 
			throw new CommonBaseException("数据库中没有该条施工方案的信息，更新失败。");
	}
	/* 根据指定施工方案code，更新相应的该施工方案数据。如果数据为null，则不更新。
	 * (non-Javadoc)
	 * @see com.banry.pscm.commonbase.service.biz.ItemConsSchemeService#updateItemConsSchemeSelective(com.banry.pscm.commonbase.service.biz.ItemConsScheme)
	 */
	@Transactional
	public void updateItemConsSchemeSelective(ItemConsScheme consScheme) throws CommonBaseException {
		if(consScheme.getSchemeCode() == null || "".equals(consScheme.getSchemeCode()))
			throw new CommonBaseException("主键SchemeCode为null，更新失败！");
		ItemConsScheme ics = itemConsSchemeMapper.selectByPrimaryKey(consScheme.getSchemeCode());
		if(ics != null) {
			if(consScheme.getDivItemCode() != null && !"".equals(consScheme.getDivItemCode())) {
				EngDivision ed = engDivisionMapper.selectByPrimaryKey(consScheme.getDivItemCode());
				if(ed != null) {
					consScheme.setDivLevel(ed.getDivLevel());
					itemConsSchemeMapper.updateByPrimaryKeySelective(consScheme);
				}else
					throw new CommonBaseException("引用的外键DivItemCode不存在，更新失败！");
			}else {
				consScheme.setDivLevel(null);
				itemConsSchemeMapper.updateByPrimaryKeySelective(consScheme);
			}
		}else
			throw new CommonBaseException("数据库中没有该条施工方案的信息，更新失败！");
	}

}
