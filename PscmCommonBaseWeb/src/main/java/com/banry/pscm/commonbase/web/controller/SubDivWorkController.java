package com.banry.pscm.commonbase.web.controller;

//import java.util.Arrays;
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
import com.banry.pscm.commonbase.service.biz.SubDivWork;
import com.banry.pscm.commonbase.service.biz.SubDivWorkService;

/**
 * @author chenshiyu
 *
 */
@RestController
@RequestMapping("/subdivwork")
public class SubDivWorkController {

	@Autowired
	private SubDivWorkService subDivWorkService;
	
	
	@RequestMapping(value="/findbycode")
	public SubDivWork findSubDivWorkByCode(String code, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkService.findSubDivWorkByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/findbysubdivwork")
	public List<SubDivWork> findSubDivWorksByArray(String string, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkService.findSubDivWorksBySubDivCode(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/findbydivname")
	public List<SubDivWork> findSubDivWorkByDivName(String divname, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkService.findSubDivWorkByName(divname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value="/findall")
	public List<SubDivWork> findSubDivWorkList( HttpSession session) {
		DDSController.switchDS(session);
		try {
			return subDivWorkService.findSubDivWorkList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//增删改不走restapi,走service的方法，本地调用，后期用再改。
	/*@RequestMapping(value="/save")
	public void saveSubDivWork(SubDivWork subDivWork) {
		try {
			subDivWorkService.saveSubDivWork(subDivWork);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/saveselective")
	public void saveSubDivWorkSelective(SubDivWork subDivWork) {
		try {
			subDivWorkService.saveSubDivWorkSelective(subDivWork);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/delete")
	public int deleteSubDivWork(String code) {
		try {
			return subDivWorkService.deleteSubDivWork(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@RequestMapping(value="/update")
	public void updateSubDivWork(SubDivWork subDivWork) {
		try {
			subDivWorkService.updateSubDivWork(subDivWork);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/updateselective")
	public void updateSubDivWorkSelective(SubDivWork subDivWork) {
		try {
			subDivWorkService.updateSubDivWorkSelective(subDivWork);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
}
