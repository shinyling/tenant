package com.shiny.tenant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shiny.tenant"})
@MapperScan("com.shiny.tenant.dao")
@EnableAutoConfiguration
@ServletComponentScan
public class TenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantApplication.class, args);
	}
}
