package com.github.rafaelsilva91.encriptarsenhausuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EncriptarSenhaUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncriptarSenhaUsuarioApplication.class, args);
	}

}
