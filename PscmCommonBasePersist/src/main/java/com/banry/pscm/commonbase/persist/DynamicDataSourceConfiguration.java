package com.banry.pscm.commonbase.persist;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Haomeng
 * @version 1.0
 * @since 2018/06/05
 * 
 * 
 * 公共库有两类:<BR>
 * common_cat 公共目录库，只有一个表common_db_cat，里面记录多个 专业+来源 的目录,这个库会默认创建加入到动态数据源，名称是DEFAULT<BR>
 * common_<专业>_<来源> 公共业务库，保存一个特定 专业来源 的 公共业务库，这些库对应的数据源会在springboot启动时候加入到动态数据源列表，<BR>
 *                   用DynamicDataSourceContextHolder.set("数据库名")进行运行时切换 <BR>
 * 
 * 
 * 
 */
@MapperScan(basePackages = "com.banry.pscm.conf")
@Configuration
public class DynamicDataSourceConfiguration {
	
	@Autowired
	Environment ev;	

	private static Logger log = LoggerFactory.getLogger(DynamicDataSourceConfiguration.class);

	public static Map<Object, Object> dataSourceMap = new HashMap<>();
	public static DynamicRoutingDataSource _DDS = new DynamicRoutingDataSource();

/*	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource createDS() {
		log.debug("creating DefaultDS...");
		DataSource ds = DruidDataSourceBuilder.create().build();
		log.info("new ds created:" + ds.toString());
		return ds;
	}*/
	
	public DataSource createDS(String dsname) {
		log.info("creating datasource for dsname " + dsname + "...");
		
		String dbhost_port = ev.getProperty("pscm.commonbase.database.host_port");
		String urlTmpl = "jdbc:mysql://" + dbhost_port + "/";
		String username = ev.getProperty("spring.datasource.username");
		String password = ev.getProperty("spring.datasource.password");
		String driverClassName = ev.getProperty("spring.datasource.driverClassName");
		String initSQLFile = ev.getProperty("pscm.tenant.database.initsql");


		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setUrl(urlTmpl + dsname);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		
		return druidDataSource;
	}

	/**
	 * 核心动态数据源
	 *
	 * @return 数据源实例
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dynamicDataSource() {
		
		log.debug("creating dynamicDataSource...");
		
		//创建默认数据源common_cat
		DataSource ds = createDS("common_cat");	
		// _DDS.setDefaultTargetDataSource(defaultDs);
		dataSourceMap.put("DEFAULT", ds);
		_DDS.setTargetDataSources(dataSourceMap);
		return _DDS;
		
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		log.debug("creating sqlSessionFactory...");
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		//设置动态数据源
		sqlSessionFactoryBean.setDataSource(dynamicDataSource());
		// 此处设置为了解决找不到mapper文件的问题
		// sqlSessionFactoryBean.setMapperLocations(new
		// PathMatchingResourcePatternResolver()
		// .getResources("classpath:com/banry/pscm/conf/persist/mapper/*.xml"));

		return sqlSessionFactoryBean.getObject();
	}

	public static void addNewDS(DataSource ds, String name) {
		log.info("add " + name + " datasource into dynamicDataSource ...");
		dataSourceMap.put(name, ds);
		_DDS.setTargetDataSources(dataSourceMap);
		_DDS.afterPropertiesSet();
		log.info("dynamicDataSource list=" + dataSourceMap);
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(dynamicDataSource());
	}
}
