package io.github.luizfarias.evaluatorservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableRabbit
public class EvaluatorserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluatorserviceApplication.class, args);
	}

}
