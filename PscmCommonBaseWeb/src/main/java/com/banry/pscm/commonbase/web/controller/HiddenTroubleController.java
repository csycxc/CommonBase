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
import com.banry.pscm.commonbase.service.biz.HiddenTrouble;
import com.banry.pscm.commonbase.service.biz.HiddenTroubleService;

//import javassist.bytecode.stackmap.BasicBlock.Catch;

/**
 * @author chenshiyu
 *
 */
@RestController
@RequestMapping("/hiddentrouble")
public class HiddenTroubleController {
	
	@Autowired
	private HiddenTroubleService hiddenTroubleService;
	
	
	@RequestMapping(value = "/findbycode")
	public HiddenTrouble findHiddenTroubleByCode(String code, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hiddenTroubleService.findHiddenTroubleByCode(code);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findbydivcode")
	public List<HiddenTrouble> findHiddenTroubleListByDivCode(String divcode, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hiddenTroubleService.findHiddenTroubleListByDivCode(divcode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findbyname")
	public List<HiddenTrouble> findHiddenTroubleByName(String divname, HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hiddenTroubleService.findHiddenTroubleByName(divname);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/findall")
	public List<HiddenTrouble> findHiddenTroubleList( HttpSession session) {
		DDSController.switchDS(session);
		try {
			return hiddenTroubleService.findHiddenTroubleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//增删改不走restapi,走service的方法，本地调用，后期用再改。
	/*@RequestMapping(value = "/save")
	public void saveHiddenTrouble(HiddenTrouble trouble) {
		try {
			hiddenTroubleService.saveHiddenTrouble(trouble);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/saveselective")
	public void saveHiddenTroubleSelective(HiddenTrouble trouble) {
		try {
			hiddenTroubleService.saveHiddenTroubleSelective(trouble);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/delete")
	public int deleteHiddenTrouble(String code) {
		try {
			return hiddenTroubleService.deleteHiddenTrouble(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@RequestMapping(value = "/update")
	public void updateHiddenTrouble(HiddenTrouble trouble) {
		try {
			hiddenTroubleService.updateHiddenTrouble(trouble);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/updateselective")
	public void updateHiddenTroubleSelective(HiddenTrouble trouble) {
		try {
			hiddenTroubleService.updateHiddenTroubleSelective(trouble);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
