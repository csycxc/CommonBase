package com.banry.pscm.commonbase.web.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.banry.pscm.commonbase.service.biz.SubDivWork;
import com.banry.pscm.commonbase.service.biz.SubDivWorkService;

/**
 * @author chenshiyu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubDivWorkTest {

	@Autowired
	private SubDivWorkService subDivWorkService;
	
	@Test
	public void testfindSubDivWorkByCode() {
		try {
			SubDivWork sdw = subDivWorkService.findSubDivWorkByCode("1001");
			if(sdw != null) {
				System.out.println(sdw.toString());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindSubDivWorkByName() {
		try {
			List<SubDivWork> sdwList = subDivWorkService.findSubDivWorkByName("项目");
			if(sdwList.size()>0) {
				for (SubDivWork sdw : sdwList) {
					System.out.println(sdw.toString());					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testfindSubDivWorkList() {
		try {
			List<SubDivWork> sdwList = subDivWorkService.findSubDivWorkList();
			if(sdwList.size()>0) {
				for (SubDivWork sdw : sdwList) {
					System.out.println(sdw.toString());					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testsaveSubDivWork() {
		try {
			SubDivWork sdw = new SubDivWork();
			sdw.setSubDivCode("1008");
			sdw.setName("ceshi");
			subDivWorkService.saveSubDivWork(sdw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testsaveSubDivWorkSelective() {
		try {
			SubDivWork sdw = new SubDivWork();
			sdw.setSubDivCode("1008");
			sdw.setName("ceshi11");
			sdw.setCharactDes("ceshi特征");
			subDivWorkService.saveSubDivWorkSelective(sdw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testdeleteSubDivWork() {
		try {
			int i = subDivWorkService.deleteSubDivWork("1008");
			System.out.println("删除了 "+i+" 条记录");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testupdateSubDivWork() {
		try {
			SubDivWork sdw = new SubDivWork();
			sdw.setSubDivCode("1008");
			sdw.setName("ceshiupdate");
			sdw.setCharactDes("ceshi特征update");
			sdw.setUnit("摄氏度");
			sdw.setNumber(888.0);
			sdw.setCompUnitPrice(11.0);
			sdw.setTemporaryMeasurePrice(1.1);
			sdw.setQuotaManualFee(2.2);
			subDivWorkService.updateSubDivWork(sdw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testupdateSubDivWorkSelective() {
		try {
			SubDivWork sdw = new SubDivWork();
			sdw.setSubDivCode("1008");
			//sdw.setName("ceshi");
			//sdw.setCharactdes("ceshi特征");
			sdw.setUnit("摄氏度111");
			sdw.setNumber(8.811);
			sdw.setCompUnitPrice(7.711);
			sdw.setTemporaryMeasurePrice(6.611);
			sdw.setQuotaManualFee(5.511);
			subDivWorkService.updateSubDivWorkSelective(sdw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
