package com.banry.pscm.commonbase.web.test;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.banry.pscm.commonbase.ApplicationStartup;
import com.banry.pscm.commonbase.PscmCommonBaseWebApplication;
import com.banry.pscm.commonbase.persist.DynamicDataSourceContextHolder;
import com.banry.pscm.commonbase.persist.dao.DDSMaper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PscmCommonBaseWebApplication.class)
@ContextConfiguration(classes = {ApplicationStartup.class})
public class DDSTest {

	@Autowired
	private DDSMaper _DDSMaper;

	@Test
	public void testGetDDS() {

		//默认库是 common_cat
		DynamicDataSourceContextHolder.set("DEFAULT");
		List list = _DDSMaper.getDSList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(((HashMap) list.get(i)).get("source_en"));
		}
	}
}
