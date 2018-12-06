package com.banry.pscm.commonbase.web.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.banry.pscm.commonbase.service.biz.ItemConsScheme;
import com.banry.pscm.commonbase.service.biz.ItemConsSchemeService;

/**
 * @author chenshiyu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemConsSchemeTest {
	
	@Autowired
	private ItemConsSchemeService itemConsSchemeService;
	
	@Test
	public void testFindItemConsSchemeByCode() {
		try {
			ItemConsScheme ics = itemConsSchemeService.findItemConsSchemeByCode("1001");
			System.out.println(ics.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindItemConsSchemeListByDivCode() {
		try {
			List<ItemConsScheme> icsList = itemConsSchemeService.findItemConsSchemeListByDivCode("100");
			if(icsList.size()>0) {
				for (ItemConsScheme ics : icsList) 
					System.out.println(ics.toString());
			}else 
				System.out.println("没查出来。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindItemConsSchemeByName() {
		try {
			List<ItemConsScheme> icsList = itemConsSchemeService.findItemConsSchemeByName("名2");
			if(icsList.size()>0) {
				for (ItemConsScheme ics : icsList) 
					System.out.println(ics.toString());	
			}else
				System.out.println("没符合条件的数据。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindItemConsSchemeList() {
		try {
			List<ItemConsScheme> ilist = itemConsSchemeService.findItemConsSchemeList();
			if(ilist.size()>0) {
				for (ItemConsScheme ics : ilist) {
					System.out.println(ics.toString());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testsaveItemConsScheme() {
		try {
			ItemConsScheme ics = new ItemConsScheme();
			ics.setSchemeCode("1005");
			ics.setDivItemCode("1001");
			ics.setDivLevel("6");//冗余字段，不能保存。
			ics.setName("name测试");
			ics.setSchType("SchType测试");
			ics.setPaths("Paths测试");
			ics.setDescription("说明测试");
			itemConsSchemeService.saveItemConsScheme(ics);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testsaveItemConsSchemeSelective() {
		try {
			ItemConsScheme ics = new ItemConsScheme();
			ics.setSchemeCode("1005");
			//ics.setDivitemcode("1001");
			ics.setDivLevel("6");//冗余字段，不能保存。
			//ics.setName("name测试");
			ics.setSchType("SchType测试1111");
			ics.setPaths("Paths测试111");
			ics.setDescription("说明测试111");
			itemConsSchemeService.saveItemConsSchemeSelective(ics);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testdeleteItemConsScheme() {
		try {
			int i = itemConsSchemeService.deleteItemConsScheme("1005");
			System.out.println("删除了 "+i+" 条信息。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testupdateItemConsScheme() {
		try {
			ItemConsScheme ics = new ItemConsScheme();
			ics.setSchemeCode("1005");
			ics.setDivItemCode("1001");
			ics.setDivLevel("6");//冗余字段，不能保存。
			ics.setName("name测试11");
			ics.setSchType("SchType测试11");
			ics.setPaths("Paths测试11");
			ics.setDescription("说明测试11");
			itemConsSchemeService.updateItemConsScheme(ics);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testupdateItemConsSchemeSelective() {
		try {
			ItemConsScheme ics = new ItemConsScheme();
			ics.setSchemeCode("1005");
			ics.setDivItemCode("1001");
			ics.setDivLevel("6");//冗余字段，不能保存。
			ics.setName("name测试");
			ics.setSchType("SchType测试");
			ics.setPaths("Paths测试");
			ics.setDescription("说明测试");
			itemConsSchemeService.updateItemConsSchemeSelective(ics);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
