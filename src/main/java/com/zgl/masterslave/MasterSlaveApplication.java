package com.zgl.masterslave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zgl
 * @date 2019/4/1 上午10:59
 */
@MapperScan(basePackages = "com.zgl.masterSlave.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class MasterSlaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterSlaveApplication.class, args);
	}

}
