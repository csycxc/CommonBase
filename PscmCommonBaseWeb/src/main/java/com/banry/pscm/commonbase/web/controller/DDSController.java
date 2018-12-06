package com.banry.pscm.commonbase.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banry.pscm.commonbase.persist.DynamicDataSourceContextHolder;
import com.banry.pscm.commonbase.persist.dao.DDSMaper;
import com.banry.pscm.commonbase.service.biz.EngDivision;

/**
 * 动态数据源切换控制器
 * 
 * @author Haomeng, Wang
 * 
 *
 */
@RestController
@RequestMapping("/dds")
public class DDSController {

	@Autowired
	private DDSMaper ddsmapper;
	
	/**
	 * 设置当前数据源名称，在当前session中加入DDS_NAME变量，值就是当前的数据源名称
	 * 设置后，以后所有restapi调用，都会获取这个session中的DDS_NAME来切换当前数据源
	 * 
	 * @param dsname
	 * @param session
	 */
	@RequestMapping(value = "/setdsname")
	public void setCurrentDS(String dsname, HttpSession session) {
		session.setAttribute("DDS_NAME", dsname);
	}

	/**
	 * 获取当前数据源名称
	 * 
	 * @param dsname
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getdsname")
	public String getCurrentDS(String dsname, HttpSession session) {
		String currentds = (session.getAttribute("DDS_NAME") == null ? ""
				: session.getAttribute("DDS_NAME").toString());
		return currentds;
	}
	
	public static void switchDS(HttpSession session) {
		String currentds = (session.getAttribute("DDS_NAME") == null ? ""
				: session.getAttribute("DDS_NAME").toString());
		
		DynamicDataSourceContextHolder.set(currentds);
	}
	
	@RequestMapping(value = "/getCommonbaseCatalog")
	public List<HashMap> getCommonbaseCatalog() {
		DynamicDataSourceContextHolder.set("DEFAULT");
		return ddsmapper.getDSList();
	}	

}
