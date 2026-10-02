package com.example.WebDiary;

import org.springframework.boot.SpringApplication;

public class TestWebDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.from(WebDiaryApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
