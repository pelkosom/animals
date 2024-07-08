package com.personal.animals;

import org.springframework.boot.SpringApplication;

public class TestAnimalsApplication {

	public static void main(String[] args) {
		SpringApplication.from(AnimalsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
