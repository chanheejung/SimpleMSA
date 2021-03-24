package kr.pe.chani.simplemsa.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class Service1Application {

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}

}
