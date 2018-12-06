package com.banry.pscm.commonbase.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Haomeng
 * @version 1.0
 * @since 2018/06/05
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
	private static Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

	@Override
	protected Object determineCurrentLookupKey() {
		log.info("当前数据源：" + DynamicDataSourceContextHolder.get());
		return DynamicDataSourceContextHolder.get();
	}
}
