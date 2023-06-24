package com.aitest.jim.aipicbookserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.aitest.jim.aipicbookserver.service.mapper")
public class AiPicBookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiPicBookServerApplication.class, args);
	}

}
