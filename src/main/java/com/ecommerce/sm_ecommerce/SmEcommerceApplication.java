package com.ecommerce.sm_ecommerce;

//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication()
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class SmEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmEcommerceApplication.class, args);
	}

}
