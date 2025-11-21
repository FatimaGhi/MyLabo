package com.example.Mylab;

import com.example.Mylab.Configuration.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(RsaKeys.class)
@SpringBootApplication
public class MylabApplication {

	public static void main(String[] args) {
		SpringApplication.run(MylabApplication.class, args);
	}

}
