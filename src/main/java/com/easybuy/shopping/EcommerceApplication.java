package com.easybuy.shopping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(EcommerceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
    @Override
    public void run(String... args) {
        log.info("StartApplication...");
    }
}
