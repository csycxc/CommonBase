package com.banry.pscm.commonbase.web.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.banry.pscm.commonbase.service.biz.SubDivWorkQuota;
import com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService;

/**
 * @author chenshiyu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubDivWorkQuotaTest {
	
	@Autowired
	private SubDivWorkQuotaService subDivWorkQuotaService;
	
	@Test
	public void testfindSubDivWorkQuotaByCode() {
		try {
			SubDivWorkQuota sdwq = subDivWorkQuotaService.findSubDivWorkQuotaByCode("1003");
			if(sdwq != null) System.out.println(sdwq.toString());
			else System.out.println("无查询记录。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindSubDivWorkQuotaListByDivCode() {
		try {
			List<SubDivWorkQuota> sdwqList = subDivWorkQuotaService.findSubDivWorkQuotaListByDivCode("1001");
			if(sdwqList.size()>0) 
				for (SubDivWorkQuota sd : sdwqList) 
					System.out.println(sd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindSubDivWorkQuotaListByQuotaCode() {
		try {
			List<SubDivWorkQuota> list = subDivWorkQuotaService.findSubDivWorkQuotaListByQuotaCode("111");
			if(list.size()>0) 
				for (SubDivWorkQuota sd : list) 
					System.out.println(sd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindSubDivWorkQuotaListByItemName() {
		try {
			List<SubDivWorkQuota> list = subDivWorkQuotaService.findSubDivWorkQuotaListByItemName("测试");
			if(list.size()>0)
				for (SubDivWorkQuota sd : list) 
					System.out.println(sd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindSubDivWorkQuotaListByResType() {
		try {   //资源类型细分：人工 材料  机械               只有这三种。
			List<SubDivWorkQuota> list = subDivWorkQuotaService.findSubDivWorkQuotaListByResType("人工");
			if(list.size()>0)
				for (SubDivWorkQuota s : list)
					System.out.println(s.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindSubDivWorkQuotaList() {
		try {
			List<SubDivWorkQuota> list = subDivWorkQuotaService.findSubDivWorkQuotaList();
			if(list.size()>0)
				for (SubDivWorkQuota s : list)
					System.out.println(s.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testsaveSubDivWorkQuota() {
		try {
			SubDivWorkQuota s = new SubDivWorkQuota();
			s.setResCode("1008");
			s.setSubDivCode("1001");
			s.setQuotaCode("111");
			s.setItemName("itemname测试");
			s.setItemDetailName("detailname测试");
			s.setResourcesType("材料");
			s.setResTypeLevel("2");
			s.setResDetailType("细分测试");
			s.setUnit("unit测试");
			s.setUsedNum(1.1);
			s.setLossRate(2.2);
			s.setSaveExcessRate(3.3);
			subDivWorkQuotaService.saveSubDivWorkQuota(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testsaveSubDivWorkQuotaSelective() {
		try {
			SubDivWorkQuota s = new SubDivWorkQuota();
			s.setResCode("1009");
			s.setSubDivCode("1001");
			s.setQuotaCode("111");
			s.setItemName("测试");
			s.setItemDetailName("测试");
			s.setResourcesType("材料");
			s.setResTypeLevel("2");
			s.setResDetailType("测试");
			s.setUnit("测试");
			subDivWorkQuotaService.saveSubDivWorkQuotaSelective(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testdeleteSubDivWorkQuota() {
		try {
			int i = subDivWorkQuotaService.deleteSubDivWorkQuota("1009");
			System.out.println("删除了 "+i+" 条记录");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testupdateSubDivWorkQuota() {
		try {
			SubDivWorkQuota s = new SubDivWorkQuota();
			s.setResCode("1009");
			s.setSubDivCode("1001");
			s.setQuotaCode("111");
			s.setItemName("测试");
			s.setItemDetailName("测试");
			s.setResourcesType("材料");
			s.setResTypeLevel("2");
			s.setResDetailType("测试");
			s.setUnit("测试");
			subDivWorkQuotaService.updateSubDivWorkQuota(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void updateSubDivWorkQuotaSelective() {
		try {
			SubDivWorkQuota s = new SubDivWorkQuota();
			s.setResCode("1009");
			s.setSubDivCode("1001");
			s.setQuotaCode("111");
			s.setItemName("测试");
			s.setItemDetailName("测试");
			s.setResourcesType("材料");
			s.setResTypeLevel("2");
			s.setResDetailType("测试");
			s.setUnit("测试");
			subDivWorkQuotaService.updateSubDivWorkQuotaSelective(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
