package com.es.ProyectoAPI_Segura;

import com.es.ProyectoAPI_Segura.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ProyectoApiSeguraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApiSeguraApplication.class, args);
	}

}
