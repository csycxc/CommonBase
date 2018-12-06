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

import com.banry.pscm.commonbase.persist.DynamicDataSourceContextHolder;
//import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.ItemConsScheme;
import com.banry.pscm.commonbase.service.biz.ItemConsSchemeService;

/**
 * @author chenshiyu
 *
 */
@RestController
@RequestMapping("/itemconsscheme")
public class ItemConsSchemeController {
	
	@Autowired
	private ItemConsSchemeService itemConsSchemeService;
	
	
	@RequestMapping(value = "/findbycode")
	public ItemConsScheme findItemConsSchemeByCode(String code, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return itemConsSchemeService.findItemConsSchemeByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findbydivcode")
	public List<ItemConsScheme> findItemConsSchemeListByDivCode(String divcode, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return itemConsSchemeService.findItemConsSchemeListByDivCode(divcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findbydivname")
	public List<ItemConsScheme> findItemConsSchemeByName(String divname, HttpSession session)  {
		DDSController.switchDS(session);
		try {
			return itemConsSchemeService.findItemConsSchemeByName(divname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findall")
	public List<ItemConsScheme> findItemConsSchemeList(HttpSession session)  {
		DDSController.switchDS(session);
		
		DynamicDataSourceContextHolder.set("com_municipal_bridge_housing_dept");
		
		try {
			return itemConsSchemeService.findItemConsSchemeList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//增删改不走restapi,走service的方法，本地调用，后期用再改。
	/*@RequestMapping(value = "/save")
	public void saveItemConsScheme(ItemConsScheme consScheme)  {
		try {
			itemConsSchemeService.saveItemConsScheme(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/saveselective")
	public void saveItemConsSchemeSelective(ItemConsScheme consScheme) {
		try {
			itemConsSchemeService.saveItemConsSchemeSelective(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/delete")
	public int deleteItemConsScheme(String code) {
		try {
			return itemConsSchemeService.deleteItemConsScheme(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@RequestMapping(value = "/update")
	public void updateItemConsScheme(ItemConsScheme consScheme) {
		try {
			itemConsSchemeService.updateItemConsScheme(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/updateselective")
	public void updateItemConsSchemeSelective(ItemConsScheme consScheme) {
		try {
			itemConsSchemeService.updateItemConsSchemeSelective(consScheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
}
