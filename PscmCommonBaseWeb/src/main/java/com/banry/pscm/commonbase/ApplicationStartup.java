package com.banry.pscm.commonbase;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.banry.pscm.commonbase.persist.DynamicDataSourceConfiguration;
import com.banry.pscm.commonbase.persist.DynamicDataSourceContextHolder;
import com.banry.pscm.commonbase.persist.dao.DDSMaper;

/**
 * 自定义启动类，可以在此类中运行一些需要启动时候做的动作 
 * 1 初始化动态数据源，会吧common_cat.common_db_cat表中所有专业来源的数据源重新再内存中建立
 *   运行时使用DynamicDataSourceContextHolder.set("数据库名")进行数据源动态切换
 * 2 待定。。。
 * 
 * @author Haomeng, Wang
 *
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

	private static boolean initFlag = false;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!initFlag) {
			System.out.println("init dds...");
			initDDSContext(event.getApplicationContext());
			initFlag = true;
		}
	}

	/**
	 * 根据公共目录库中的公共业务库列表，初始化每个公共业务库
	 * @param appCtx
	 */
	private void initDDSContext(ApplicationContext appCtx) {

		// debug
		// printCtx(appCtx);

		// 设置默认数据源
		DynamicDataSourceContextHolder.set("DEFAULT");
		DDSMaper _DDSMaper = (DDSMaper) appCtx.getBean("DDSMaper");
		List list = _DDSMaper.getDSList();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			System.out.println(map);
			String dbName = "com_" + map.get("major_en").toString() + "_" + map.get("source_en").toString();
			DataSource ds = createDS(appCtx, dbName);
			DynamicDataSourceConfiguration.addNewDS(ds, dbName);
		}

		/*
		 * //test other ds
		 * DynamicDataSourceContextHolder.set("com_municipal_bridge_housing_dept"); list
		 * = _DDSMaper.getDSList(); System.out.println(list.size());
		 */
	}

	private void printCtx(ApplicationContext appCtx) {
		String[] beans = appCtx.getBeanDefinitionNames();
		for (int i = 0; i < beans.length; i++) {
			System.out.println(beans[i]);
		}
	}
	
	/**
	 * 创建公共业务库的数据源
	 * @param appCtx
	 * @param dsname
	 * @return
	 */

	public DataSource createDS(ApplicationContext appCtx, String dsname) {
		System.out.println("creating datasource for dsname " + dsname + "...");

		Environment ev = appCtx.getEnvironment();

		String dbhost_port = ev.getProperty("pscm.commonbase.database.host_port");
		String urlTmpl = "jdbc:mysql://" + dbhost_port + "/";
		String username = ev.getProperty("spring.datasource.username");
		String password = ev.getProperty("spring.datasource.password");
		String driverClassName = ev.getProperty("spring.datasource.driverClassName");
		//String initSQLFile = ev.getProperty("pscm.tenant.database.initsql");

		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setUrl(urlTmpl + dsname);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);

		return druidDataSource;
	}

}
