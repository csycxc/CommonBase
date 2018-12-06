package com.banry.pscm.commonbase.web.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.banry.pscm.commonbase.service.biz.HiddenTrouble;
import com.banry.pscm.commonbase.service.biz.HiddenTroubleService;

import javassist.bytecode.stackmap.BasicBlock.Catch;

/**
 * @author chenshiyu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HiddenTroubleTest {
	
	@Autowired
	private HiddenTroubleService hiddenTroubleService;
	
	@Test
	public void testFindHiddenTroubleByCode() {
		try {
			HiddenTrouble ht = hiddenTroubleService.findHiddenTroubleByCode("1001");
			System.out.println(ht.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testFindHiddenTroubleListByDivCode() {
		try {
			List<HiddenTrouble> htList = hiddenTroubleService.findHiddenTroubleListByDivCode("100");
			if(htList.size()>0) {
				for (HiddenTrouble ht : htList) {
					System.out.println(ht.toString());					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testfindHiddenTroubleByName() {
		try {
			List<HiddenTrouble> htList = hiddenTroubleService.findHiddenTroubleByName("名2");
			if(htList.size()>0) {
				for (HiddenTrouble ht : htList) {
					System.out.println(ht.toString());					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testFindHiddenTroubleList() {
		try {
			List<HiddenTrouble> htLst = hiddenTroubleService.findHiddenTroubleList();
			if(htLst.size()>0) {
				for (HiddenTrouble ht : htLst) {
					System.out.println(ht.toString());					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveHiddenTrouble() {
		try {
			HiddenTrouble ht = new HiddenTrouble();
			ht.setTroubleCode("1005");
			ht.setDivItemCode("1001");
			ht.setDivLevel("8");//这个不能保存，这里测试用
			ht.setTrobuleCate("类别111");
			ht.setTrobuleLevel("5");
			ht.setInvestItem("排查项目测试");
			ht.setInvestContent("排查内容测试");
			ht.setDescription("描述测试");
			hiddenTroubleService.saveHiddenTrouble(ht);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSaveHiddenTroubleSelective() {
		try {
			HiddenTrouble ht = new HiddenTrouble();
			ht.setTroubleCode("1005");
			ht.setDivItemCode("1001");
			ht.setDivLevel("8");//这个不能保存，这里测试用
			ht.setTrobuleCate("类别a");
			ht.setTrobuleLevel("1");
			ht.setInvestItem("排查项目");
			ht.setInvestContent("排查内容");
			ht.setDescription("描述");
			hiddenTroubleService.saveHiddenTroubleSelective(ht);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDeleteHiddenTrouble() {
		try {
			int i = hiddenTroubleService.deleteHiddenTrouble("1005");
			System.out.println("删除了 "+i+" 条记录");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateHiddenTrouble() {
		try {
			HiddenTrouble ht = new HiddenTrouble();
			ht.setTroubleCode("1005");
			ht.setDivItemCode("1001");
			ht.setDivLevel("8");//这个不能保存，这里测试用
			ht.setTrobuleCate("类别");
			ht.setTrobuleLevel("3");
			ht.setInvestItem("排查项目");
			ht.setInvestContent("排查内容");
			ht.setDescription("描述");
			hiddenTroubleService.updateHiddenTrouble(ht);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateHiddenTroubleSelective() {
		try {
			HiddenTrouble ht = new HiddenTrouble();
			ht.setTroubleCode("1005");
			//ht.setDivitemcode("1001");
			ht.setDivLevel("8");//这个不能保存，这里测试用
			ht.setTrobuleCate("类别111");
			ht.setTrobuleLevel("3");
			ht.setInvestItem("排查项目");
			ht.setInvestContent("排查内容111");
			ht.setDescription("描述");
			hiddenTroubleService.updateHiddenTroubleSelective(ht);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
