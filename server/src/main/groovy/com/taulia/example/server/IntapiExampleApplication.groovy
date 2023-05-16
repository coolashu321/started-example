package com.taulia.example.server

import com.taulia.example.services.ServicesConfiguration
import com.taulia.starters.intapi.jersey.EnableJerseyApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ServicesConfiguration)
@EnableJerseyApplication
class IntapiExampleApplication {

	static void main(String[] args) {
		SpringApplication.run(IntapiExampleApplication, args)
	}

}
