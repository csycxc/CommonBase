package com.banry.pscm.commonbase.web.test;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.banry.pscm.commonbase.ApplicationStartup;
import com.banry.pscm.commonbase.PscmCommonBaseWebApplication;
import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.EngDivision;
import com.banry.pscm.commonbase.service.biz.EngDivisionService;
import com.banry.pscm.commonbase.service.modelPOJO.TreeNode;
/**
 * @author chenshiyu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PscmCommonBaseWebApplication.class)
@ContextConfiguration(classes = {ApplicationStartup.class})
public class EngDivisionTest {
	
	@Autowired
	private EngDivisionService engDivisionService;

	@Test
	public void testFindEngDivisionByCode() {
		try {
			EngDivision ed = engDivisionService.findEngDivisionByCode("100");
			if(ed != null) {
				System.out.println(ed.getDivName());
				Assert.assertEquals("测试名", ed.getDivName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindEngDivisionTreeByCode() throws Exception {
		TreeNode tn = engDivisionService.findEngDivisionTreeByCode("1");
		if(tn != null) System.out.println(tn.toString());
	}
	@Test
	public void testfindEngDivisionByName() throws CommonBaseException {
		List<EngDivision> list = engDivisionService.findEngDivisionByName("孙");
		if(list.size()>0) System.out.println(list.toString());
	}
	@Test
	public void testfindEngDivisionList() throws CommonBaseException {
		List<HashMap> list = engDivisionService.findEngDivisionList();//查询所有
		if(list.size()>0) System.out.println(list.toString());
	}
	@Test
	public void testsaveEngDivision() throws CommonBaseException {
		EngDivision division = new EngDivision();
		division.setDivItemCode("1003");
		division.setDivName("测试名孙3");
		division.setDivLevel("3");
		division.setSkill("skill");
		division.setParentDivItemCode("101");
		engDivisionService.saveEngDivision(division);
	}
	@Test
	public void testSaveEngDivisionSelective() throws CommonBaseException {
		EngDivision division = new EngDivision();
		division.setDivItemCode("1003");
		division.setDivName("测试名孙3selecttive测试");
		//division.setDivlevel("3");
		//division.setSkill("");
		division.setParentDivItemCode("101");
		engDivisionService.saveEngDivisionSelective(division);
	}
	@Test
	public void testDeleteEngDivision() throws CommonBaseException {
		int i = engDivisionService.deleteEngDivision("1003");
		if(i==0) {
			System.out.println("删除失败");
		}else if(i==1) {
			System.out.println("删除成功");
		}
	}
	@Test
	public void testUpdateEngDivision() throws CommonBaseException {
		EngDivision division = new EngDivision();
		division.setDivItemCode("1002");
		division.setDivName("测试名孙2");
		division.setDivLevel("3");
		division.setSkill("skill");
		division.setParentDivItemCode("101");
		engDivisionService.updateEngDivision(division);
	}
	@Test
	public void testUpdateEngDivisionSelective() throws CommonBaseException {
		EngDivision division = new EngDivision();
		division.setDivItemCode("1002");
		division.setDivName("测试名孙2Selective");
		//division.setDivlevel("3");
		division.setSkill("skillSelective");
		//division.setParentdivitemcode("101");
		engDivisionService.updateEngDivisionSelective(division);
	}
	
}
