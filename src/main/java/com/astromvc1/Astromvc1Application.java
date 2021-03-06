package com.astromvc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

//swagger-ui/index.html
@SpringBootApplication
public class Astromvc1Application {

	public static void main(String[] args) {
		SpringApplication.run(Astromvc1Application.class, args);
	}

}
