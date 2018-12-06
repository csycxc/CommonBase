package com.banry.pscm.commonbase.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banry.pscm.commonbase.service.CommonBaseException;
import com.banry.pscm.commonbase.service.biz.Hazards;
import com.banry.pscm.commonbase.service.biz.HazardsService;

/**
 * @author chenshiyu
 *
 */
@RestController
@RequestMapping("/hazards")
public class HazardsController {
	@Autowired
	private HazardsService hazardsService;
	
	@RequestMapping(value = "/findbycode")
	public Hazards findHazardsByCode(String code, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hazardsService.findHazardsByCode(code);
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findbydivcode")
	public List<Hazards> findHazardsListByDivCode(String divcode, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hazardsService.findHazardsListByDivCode(divcode);
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findbyname")
	public List<Hazards> findHazardsByName(String divname, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hazardsService.findHazardsByName(divname);
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findall")
	public List<Hazards> findHazardsList(HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hazardsService.findHazardsList();//查询所有
		} catch (CommonBaseException e) {
			e.printStackTrace();
		}
		return null;
	}
	//增删改不走restapi,走service的方法，本地调用，后期用再改。
	/*
	@RequestMapping(value = "/save")
	public void saveHazards(Hazards hazard) {
		try {
			hazardsService.saveHazards(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/saveselective")
	public void saveHazardsSelective(Hazards hazard) {
		try {
			hazardsService.saveHazardsSelective(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delete")
	public int deleteHazards(String code) {
		try {
			return hazardsService.deleteHazards(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping(value = "/update")
	public void updateHazards(Hazards hazard) {
		try {
			hazardsService.updateHazards(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/updateselective")
	public void updateHazardsSelective(Hazards hazard) {
		try {
			hazardsService.updateHazardsSelective(hazard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
