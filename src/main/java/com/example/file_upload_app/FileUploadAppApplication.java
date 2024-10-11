package com.example.file_upload_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FileUploadAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadAppApplication.class, args);
	}
}
