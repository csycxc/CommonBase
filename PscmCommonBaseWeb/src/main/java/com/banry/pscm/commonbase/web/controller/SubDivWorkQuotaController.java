package com.banry.pscm.commonbase.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.SubDivWorkQuota;
import com.banry.pscm.commonbase.service.biz.SubDivWorkQuotaService;

/**
 * @author chenshiyu
 *
 */
@RestController
@RequestMapping("/subdivworkquota")
public class SubDivWorkQuotaController {
	
	@Autowired
	private SubDivWorkQuotaService subDivWorkQuotaService;
	
	@RequestMapping(value="/findbycode")
	public SubDivWorkQuota findSubDivWorkQuotaByCode(String code, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkQuotaService.findSubDivWorkQuotaByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/findbydivcode")
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByDivCode(String divcode, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkQuotaService.findSubDivWorkQuotaListByDivCode(divcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/findbyquotacode")
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByQuotaCode(String quotacode, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkQuotaService.findSubDivWorkQuotaListByQuotaCode(quotacode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/findbyitemname")
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByItemName(String itemname, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkQuotaService.findSubDivWorkQuotaListByItemName(itemname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/findbyrestype")
	public List<SubDivWorkQuota> findSubDivWorkQuotaListByResType(String resourcestype, HttpSession session) {
		DDSController.switchDS(session);
		try {   //资源类型细分：人工 材料  机械               只有这三种。
			return subDivWorkQuotaService.findSubDivWorkQuotaListByResType(resourcestype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/findall")
	public List<SubDivWorkQuota> findSubDivWorkQuotaList(HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkQuotaService.findSubDivWorkQuotaList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//增删改不走restapi,走service的方法，本地调用，后期用再改。
	/*@RequestMapping(value="/save")
	public void saveSubDivWorkQuota(SubDivWorkQuota consScheme) {
		try {
			subDivWorkQuotaService.saveSubDivWorkQuota(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/saveselective")
	public void saveSubDivWorkQuotaSelective(SubDivWorkQuota consScheme) {
		try {
			subDivWorkQuotaService.saveSubDivWorkQuotaSelective(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/delete")
	public int deleteSubDivWorkQuota(String code) {
		try {
			return subDivWorkQuotaService.deleteSubDivWorkQuota(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@RequestMapping(value="/update")
	public void updateSubDivWorkQuota(SubDivWorkQuota consScheme) {
		try {
			subDivWorkQuotaService.updateSubDivWorkQuota(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/updateselective")
	public void updateSubDivWorkQuotaSelective(SubDivWorkQuota consScheme) {
		try {
			subDivWorkQuotaService.updateSubDivWorkQuotaSelective(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}
