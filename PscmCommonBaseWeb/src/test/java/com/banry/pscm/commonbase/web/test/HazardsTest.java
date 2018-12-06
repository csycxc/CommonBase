package com.banry.pscm.commonbase.web.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.Hazards;
import com.banry.pscm.commonbase.service.biz.HazardsService;

/**
 * @author chenshiyu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HazardsTest {
	@Autowired
	private HazardsService hazardsService;
	
	@Test
	public void testFindHazardsByCode(){
		try {
			Hazards hazards = hazardsService.findHazardsByCode("1000");
			if(hazards != null) {
				System.out.println(hazards.toString());
			}
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindHazardsListByDivCode(){
		try {
			List<Hazards> list = hazardsService.findHazardsListByDivCode("100");
			if(list.size()>0) {
				for (Hazards hazards : list) {
					System.out.println(hazards.toString());
				}
			}
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindHazardsByName(){
		try {
			List<Hazards> list = hazardsService.findHazardsByName("孙");
			if(list.size()>0) {
				for (Hazards hazards : list) 
					System.out.println(hazards.toString());
			}
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindHazardsList(){
		try {
			List<Hazards> list = hazardsService.findHazardsList();//查询所有
			if(list.size()>0) {
				for (Hazards hazards : list) {
					System.out.println(hazards.toString());
				}
			}
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSaveHazards(){
		Hazards hazard = new Hazards();
		hazard.setHazardsCode("1005");
		hazard.setDivItemCode("1001");
		//divlevel字段不能插入，这里要根据divitemcode查询出divlevel
		hazard.setHazardsFactors("呵呵哒危险源");
		hazard.setHazardsLevel("5");
		hazard.setAccidents("呵呵哒事故");
		hazard.setControlsMeasures("呵呵哒控制措施");
		hazard.setDescription("呵呵哒备注");
		try {
			hazardsService.saveHazards(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSaveHazardsSelective() {
		Hazards hazard = new Hazards();
		hazard.setHazardsCode("1006");
		hazard.setDivItemCode("1001");
		hazard.setAccidents("修改的事故");
		try {
			hazardsService.saveHazardsSelective(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDeleteHazards() {
		try {
			int i = hazardsService.deleteHazards("1006");
			if(i==0) {
				System.out.println("删除失败");
			}else {
				System.out.println("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateHazards() {
		Hazards hazard = new Hazards();
		hazard.setHazardsCode("1005");
		hazard.setDivItemCode("1001");
		//divlevel字段不能插入，这里要根据divitemcode查询出divlevel
		hazard.setHazardsFactors("危险源1111");
		hazard.setHazardsLevel("5");
		hazard.setAccidents("事故");
		hazard.setControlsMeasures("控制措施");
		hazard.setDescription("备注");
		try {
			hazardsService.updateHazards(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateHazardsSelective() {
		Hazards hazard = new Hazards();
		hazard.setHazardsCode("1005");
		hazard.setDivItemCode("1001");
		//divlevel字段不能插入，这里要根据divitemcode查询出divlevel
		//hazard.setDivlevel("555");//测试用，这里并不能更新到数据库中
		hazard.setHazardsFactors("危险源222");
		//hazard.setHazardslevel("5");
		//hazard.setAccidents("事故");
		//hazard.setControlsmeasures("控制措施");
		hazard.setDescription("备注111");
		try {
			hazardsService.updateHazardsSelective(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
