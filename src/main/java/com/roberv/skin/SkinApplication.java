package com.roberv.skin;

import com.roberv.skin.models.Skin;
import com.roberv.skin.repository.SkinRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Rest Skins", version = "1", description = "API de prueba para Jump2Digital"))
public class SkinApplication implements CommandLineRunner {

	@Autowired
	SkinRepository skinRepository;

	public static void main(String[] args) {
		SpringApplication.run(SkinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
