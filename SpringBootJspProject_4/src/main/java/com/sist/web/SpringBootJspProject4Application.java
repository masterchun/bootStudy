package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 		MSA / JWT / Batch / WebSocket
 * 		----------------------------- 카프카, 통합
 * 		|
 * 		=> Spring-Boot / NodeJS
 * 		=> AWS / Docker
 * 		=> ORM (MyBatis / JPA)
 * 		=> DB (Oracle / MySQL / MariaDB)
 * 
 * 		Front : Vue / React
 * 				 |		|
 * 				Nuxt   Next
 */

@SpringBootApplication
public class SpringBootJspProject4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJspProject4Application.class, args);
	}

}
