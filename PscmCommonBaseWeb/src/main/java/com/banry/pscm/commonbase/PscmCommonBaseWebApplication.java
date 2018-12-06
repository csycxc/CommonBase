package com.banry.pscm.commonbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author crj
 * @Description 公共库的对外restapi接口，所有的cotrol全部以@restcontrol进行标记
 * @date 2018-04-15
 * @version 1.0
 */
@SpringBootApplication(exclude={JpaRepositoriesAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.banry.pscm.commonbase"})
@MapperScan("com.banry.pscm.commonbase.persist.dao")
public class PscmCommonBaseWebApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(PscmCommonBaseWebApplication.class);		
		springApplication.addListeners(new ApplicationStartup());		
        springApplication.run(args);
	}
}
