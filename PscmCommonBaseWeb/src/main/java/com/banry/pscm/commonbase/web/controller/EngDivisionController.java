/**
 * 
 * @auther chenshiyu
 */
package com.banry.pscm.commonbase.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banry.pscm.commonbase.service.biz.EngDivision;
import com.banry.pscm.commonbase.service.biz.EngDivisionService;
import com.banry.pscm.commonbase.service.modelPOJO.TreeNode;

/**
 * @author chenshiyu
 *
 */
@RestController
@RequestMapping("/engdivision")
public class EngDivisionController {
	
	@Autowired
	private EngDivisionService engDivisionService;
	
	//http://localhost:8080/engdivision/findbycode?code=100
	@RequestMapping(value="/findbycode")
	public EngDivision findEngDivisionByCode(String code, HttpSession session){

		DDSController.switchDS(session);
		try {
			return engDivisionService.findEngDivisionByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//http://localhost:8080/engdivision/findtreebycode?code=100
	@RequestMapping(value="/findtreebycode")
	public TreeNode findEngDivisionTreeByCode(String code, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return engDivisionService.findEngDivisionTreeByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//http://localhost:8080/engdivision/findbydivname?divname=名
	@RequestMapping(value="/findbydivname")
	public List<EngDivision> findEngDivisionByName(String divname, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return engDivisionService.findEngDivisionByName(divname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//http://localhost:8080/engdivision/findall
	@RequestMapping(value="/findall")
	public List<HashMap> findEngDivisionList(HttpSession session) {
		DDSController.switchDS(session);
		try {
			return engDivisionService.findEngDivisionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//增删改不走restapi,走service的方法，本地调用，后期用再改。
	/*@RequestMapping(value="/save")//,method=RequestMethod.POST
	public void saveEngDivision(EngDivision division) {
		try {
			engDivisionService.saveEngDivision(division);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/saveselective")
	public void saveEngDivisionSelective(EngDivision division) {
		try {
			engDivisionService.saveEngDivisionSelective(division);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/delete")
	public int deleteEngDivision(String divCode) {
		try {
			return engDivisionService.deleteEngDivision(divCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@RequestMapping(value="/update")
	public void updateEngDivision(EngDivision division) {
		try {
			engDivisionService.updateEngDivision(division);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/updateselective")
	public void updateEngDivisionSelective(EngDivision division) {
		try {
			engDivisionService.updateEngDivisionSelective(division);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}
