package com.taobao.fanli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.taobao.fanli.dao.mapper")
public class FanliApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanliApplication.class, args);
	}
}
